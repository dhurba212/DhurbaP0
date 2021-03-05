package dev.ghimire.controller;

import com.google.gson.Gson;
import dev.ghimire.daos.ClientDaoImpl;
import dev.ghimire.daos.ClientDaoPostgresImpl;
import dev.ghimire.entities.Client;
import dev.ghimire.services.ClientService;
import dev.ghimire.services.ClientServiceImpl;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.Set;

public class ClientController {

    //for client dao we pass object of client dao
    //private ClientService clientService = new ClientServiceImpl(ClientDaoImpl.cdao);

    //for client postgres dao we pass object of the CLientDaoPostgresImpl as dependency injection to create the ClientServiceImpl
    private ClientService clientService = new ClientServiceImpl(ClientDaoPostgresImpl.clientDaoPostgres);

    public Handler createClientLambda = (Context ctx)->{
        //javalin Context ctx gets the body from postman as ctx.body() in the form of JSON
        String body = ctx.body();
        //with Gson, string JSON is converted to the Object of a class type
        Gson gson = new Gson();
        Client client = gson.fromJson(body,Client.class);
        clientService.registerClient(client);

        ctx.result("A Client was successfully Created");
        ctx.status(201);
    };
    public Handler getAllClientsLambda =(Context ctx)->{

        Set<Client>clients = clientService.getAllClients();
        Gson gson = new Gson();
        String clientJSON = gson.toJson(clients);

        ctx.result(clientJSON);
        ctx.status(200);


    };
    public Handler getClientByIdLambda = (Context ctx)->{
        int id =Integer.parseInt(ctx.pathParam("id"));

        Client client = clientService.getClientById(id);
        if(client == null)
        {
            ctx.result("Client with id: "+id+" does not exist.");
            ctx.status(404);
        }
        else
        {
            Gson gson = new Gson();
            String clientJSON = gson.toJson(client);
            ctx.result(clientJSON);
            ctx.status(200);
        }




    };

    Handler updateCLientLambda = (Context ctx)->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        String updatedClientJSON = ctx.body();

        Gson gson = new Gson();
        Client updatedClient = gson.fromJson(updatedClientJSON,Client.class);
        if(clientService.getClientById(id)==null)
        {
            ctx.result("No client with id: "+id+" found.");
            ctx.status(404);
        }
        else
        {
            if(id==updatedClient.getClientId())
            {
                Client client =  clientService.updateClient(updatedClient);
                String clientJSON = gson.toJson(client);
                ctx.result("Your Client has been Updated");
                ctx.result(clientJSON);
                ctx.status(200);
            }
            else
            {
                ctx.result("The Client Id in Url and the Client Id in the updated Client doesn't match");
                ctx.status(400);
            }

        }



    };

    Handler deleteClientByIdLambda = (Context ctx)->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        Boolean deleted = clientService.deleteClientById(id);
        if(deleted==true)
        {
            ctx.result("The Client with id: "+id+" is deleted successfully");
        }
        else
        {
            ctx.result("The client with client id: "+id+" doesnt exist");
            ctx.status(404);
        }
    };




}

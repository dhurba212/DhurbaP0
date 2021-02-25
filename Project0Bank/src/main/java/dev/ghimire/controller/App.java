package dev.ghimire.controller;

import io.javalin.Javalin;



public class App {


    public static void main(String[] args) {

        AccountController accountController = new AccountController();
        ClientController clientController = new ClientController();

        Javalin app = Javalin.create();
//        Your project 0 is to create a RESTful API for a bank.
//        This bank has two main resources. clients and accounts
//
//        the API needs to support the following endpoints
//
//        POST /clients => Creates a new client
//        return a 201 status code
        app.post("/clients",clientController.createClientLambda);
//
//        GET /clients => gets all clients
//        return 200
        app.get("/clients",clientController.getAllClientsLambda);

//        GET /clients/10 => get client with id of 10
//        return 404 if no such client exist
        app.get("/clients/:id",clientController.getClientByIdLambda);

//        PUT /clients/12 => updates client with id of 12
//        return 404 if no such client exist
       app.put("/clients/:id", clientController.updateCLientLambda);
//        DELETE /clients/15 => deletes client with the id of15
//        return 404 if no such client exist
       app.delete("/clients/:id",clientController.deleteClientByIdLambda);
//
//        POST /clients/5/accounts =>creates a new account for client with the id of 5
//        return a 201 status code
       app.post("/clients/:id/accounts", accountController.createNewAccountForClient);

//        GET /clients/7/accounts => get all accounts for client 7
//        return 404 if no client exists

//        GET /clients/7/accounts?amountLessThan=2000&amountGreaterThan400 => get all accounts for client 7 between 400 and 2000
//        return 404 if no client exists

       app.get("/clients/:id/accounts",accountController.getAllAccountsForAClientLambda);

//        GET /clients/9/accounts/4 => get account 4 for client 9
//        return 404 if no account or client exists

       app.get("/clients/:id1/accounts/:id2",accountController.getAnAccountForAClientLambda);
//
//        PUT /clients/10/accounts/3 => update account  with the id 3 for client 10
//        return 404 if no account or client exists
        app.put("/clients/:id1/accounts/:id2", accountController.updateAnAccountForAClientLambda);

//        DELETE /clients/15/accounts/6 => delete account 6 for client 15
//        return 404 if no account or client exists
        app.delete("/clients/:id1/accounts/:id2",accountController.deleteAnAccountForAClientLambda);
//        Technical requirements:
//        All DAO and Service methods must have a test proving that they work
//        All endpoints must have a Postman test verifying functionality
//

        app.start();



    }
}

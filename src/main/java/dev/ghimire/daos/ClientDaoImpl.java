package dev.ghimire.daos;

import dev.ghimire.entities.Client;

import java.util.*;

public class ClientDaoImpl implements ClientDAO {

    public static ClientDaoImpl cdao = new ClientDaoImpl();


    private  Map<Integer,Client> allClients = new HashMap<Integer,Client>();

    private int idMaker=0;


    @Override
    public Client registerClient(Client client) {
        client.setClientId(++idMaker);
        allClients.put(client.getClientId(),client);
       // System.out.println(client.getClientId());
        return client;
    }

    @Override
    public Set<Client> getAllClients() {
        Set<Client> clients= new HashSet<>(allClients.values());

        return clients;
    }

    @Override
    public Client getClientById(int clientid) {
        Client client =allClients.get(clientid);
        return client;
    }

    @Override
    public Client updateClient(Client client) {
        allClients.put(client.getClientId(), client);
        return allClients.get(client.getClientId());
    }

    @Override
    public boolean deleteClientById(int clientId) {
        if(allClients.containsKey(clientId))
        {
            Client deletedClient= allClients.remove(clientId);
            return true;
        }else
        {
            return false;
        }


    }

//    public static void main(String[] args) {
//
//        ClientsDaoImpl cdao = new ClientsDaoImpl();
//        Clients c1= new Clients(0,"sangita","dhakal","d@gmail.com");
//        Clients cl11=cdao.registerClient(c1);
//        Clients cl1= cdao.getClientById(cl11.getClientId());
//        System.out.println(cl11);
//        System.out.println(cl1);
//    }
}

package dev.ghimire.daos;

import dev.ghimire.entities.Client;

import java.util.Set;

public interface ClientDAO {

    Client registerClient(Client client);

    Set<Client> getAllClients();
    Client getClientById(int clientid);

    Client updateClient(Client client);

    boolean deleteClientById(int clientId);


}

package dev.ghimire.services;

import dev.ghimire.entities.Client;

import java.util.Set;

public interface ClientService {

    Client registerClient(Client client);

    Set<Client> getAllClients();

    Client getClientById(int clientId);

    Client updateClient(Client client);

    boolean deleteClientById(int clientId);


}

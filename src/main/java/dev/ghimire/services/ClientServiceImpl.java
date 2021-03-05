package dev.ghimire.services;

import dev.ghimire.daos.ClientDAO;
import dev.ghimire.daos.ClientDaoImpl;
import dev.ghimire.entities.Client;

import java.util.Set;

public class ClientServiceImpl implements ClientService {

    private ClientDAO clientDAO;

    public ClientServiceImpl(ClientDAO clientDao)
    {
        this.clientDAO = clientDao;
    }

    @Override
    public Client registerClient(Client client) {
        Client registeredClient = clientDAO.registerClient(client);
        return  registeredClient;
    }

    @Override
    public Set<Client> getAllClients() {
        Set<Client> allClients = clientDAO.getAllClients();
        return allClients;
    }

    @Override
    public Client getClientById(int clientId) {
        Client client = clientDAO.getClientById(clientId);
        return client;
    }

    @Override
    public Client updateClient(Client client) {

        Client updatedClient = clientDAO.updateClient(client);
        return updatedClient;
    }

    @Override
    public boolean deleteClientById(int clientId) {
        boolean deleted = clientDAO.deleteClientById(clientId);

        return deleted;

    }
}

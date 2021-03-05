package dev.ghimire.serviceTests;

import dev.ghimire.daos.ClientDaoImpl;
import dev.ghimire.daos.ClientDaoPostgresImpl;
import dev.ghimire.entities.Client;
import dev.ghimire.services.ClientService;
import dev.ghimire.services.ClientServiceImpl;
import org.junit.jupiter.api.*;

import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientServiceImplTest {

    // //we need to create an object of clientService to call on its methods
    // //private static ClientService clientService = new ClientServiceImpl(new ClientDaoImpl());
    // private static ClientService clientService = new ClientServiceImpl(new ClientDaoPostgresImpl());
    // private static Client dummyClient;

    // @Test
    // @Order(1)
    // void register_client_test_1()
    // {
    //     //Creating  a new Client
    //     Client client1 = new Client(0,"Dhurba","Ghimire","dhurba.g@revature.net");

    //     //registering a client in the database
    //     Client registeredClient = clientService.registerClient(client1);

    //     dummyClient=registeredClient;
    //     //client1's id wont match with its own id once registered because id will be autogenerated when it gets registered
    //     Assertions.assertNotEquals(0,registeredClient.getClientId());
    //     Assertions.assertEquals(client1.getfName(),registeredClient.getfName());
    //     System.out.println(client1.getClientId());
    //     System.out.println(registeredClient);

    // }

    // @Test
    // @Order(2)
    // void get_all_clients_test_1()
    // {
    //     Set<Client> allClients = clientService.getAllClients();

    //     Assertions.assertNotNull(allClients.contains(dummyClient));
    //     Assertions.assertTrue(allClients.size()>0);
    // }

    // @Test
    // @Order(3)
    // void get_client_by_id_test_1()
    // {
    //     int id = dummyClient.getClientId();
    //     //calling getClientById method of ClientServiceImpl class to get a client by id
    //     Client cLient = clientService.getClientById(id);

    //     Assertions.assertEquals(id,cLient.getClientId());
    // }

    // @Test
    // @Order(4)
    // void update_client_test_1()
    // {
    //     //change first name, last name and email of the dummy client
    //     dummyClient.setfName("Sadhiya");
    //     dummyClient.setlName("Ghimire");
    //     dummyClient.setEmail("sadhiya@gmail.com");
    //     //update updated dummy client  in database
    //     Client updatedClient = clientService.updateClient(dummyClient);
    //     Assertions.assertEquals(dummyClient,updatedClient);

    // }

    // @Test
    // @Order(5)
    // void delete_client_by_id_test_1()
    // {
    //     boolean deleted = clientService.deleteClientById(dummyClient.getClientId());
    //     Assertions.assertTrue(deleted);
    // }

}

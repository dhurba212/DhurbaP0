package dev.ghimire.daosTests;

import dev.ghimire.daos.ClientDAO;
import dev.ghimire.daos.ClientDaoImpl;
import dev.ghimire.daos.ClientDaoPostgresImpl;
import dev.ghimire.entities.Client;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientTests {
    static Client newClient1=null;
    static Client newClient2=null;
    static ClientDAO cldao = new ClientDaoPostgresImpl();
    static Client client1 = new Client(0,"Dhurba","Ghimire","dhurbag21@gmail.com");
    static Client client2 = new Client(0,"Sangita","Dhakal","songsdkl9@gmail.com");
    static Set<Client>allClients = new HashSet<>();

    @Test
    @Order(1)
    public void register_client_test_1()
    {


        Client cl1= cldao.registerClient(client1);
        Client cl2 = cldao.registerClient(client2);

        allClients.add(cl1);
        allClients.add(cl2);

        System.out.println(cl1);
        newClient1=cl1;
        newClient2=cl2;
        System.out.println(newClient1.getClientId());
        Assertions.assertEquals(client1.getfName(),cl1.getfName());
        Assertions.assertNotEquals(client2.getlName(),cl1.getlName());

    }

    @Test
    @Order(2)
    public void get_client_by_id_test_1()
    {
        int id=newClient1.getClientId();
        //to test for a non-existing client -1
        int id1 = -1;
        //System.out.println(id);
        Client client=cldao.getClientById(id);
        Client client1 = cldao.getClientById(id1);
       // System.out.println(client);
        Assertions.assertEquals(newClient1.getfName(),client.getfName());

        Assertions.assertNull(client1);
    }

    @Test
    @Order(3)
    public void update_client_test_1()
    {
       // String fName = newClient.getfName();
        newClient1.setfName("Sangita");
        String updatedFirstName = newClient1.getfName();
        //Client updatedClient = newClient;
        Client updatedClient= cldao.updateClient(newClient1);

        Assertions.assertEquals(updatedFirstName,updatedClient.getfName());
        System.out.println(updatedClient);


    }
    @Test
    @Order(4)
    public void get_all_clients_test_1()
    {
        Set<Client> getAllClients = cldao.getAllClients();

        int size=getAllClients.size();
        Assertions.assertTrue(size>0);


    }

    @Test
    @Order(5)
    public void delete_client_test_1()
    {
        int id = newClient2.getClientId();
        boolean deleted = cldao.deleteClientById(id);
        Client deletedClient= cldao.getClientById(id);

        Assertions.assertEquals(true,deleted);
        Assertions.assertNull(deletedClient);



    }

    @Test
    @Order(6)
    void delete_client_that_dont_exist_test_1()
    {
        boolean deleted = cldao.deleteClientById(-1);
        Assertions.assertFalse(deleted);

    }

}

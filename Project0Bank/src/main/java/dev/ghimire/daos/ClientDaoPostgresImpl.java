package dev.ghimire.daos;

import dev.ghimire.entities.Client;
import dev.ghimire.util.ConnectionUtil;
import org.apache.log4j.Logger;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ClientDaoPostgresImpl implements ClientDAO{
    public static ClientDaoPostgresImpl clientDaoPostgres = new ClientDaoPostgresImpl();

    static Logger logger = Logger.getLogger(ClientDaoPostgresImpl.class.getName());


    @Override
    public Client registerClient(Client client) {

        String sql = "insert into client(first_name,last_name,email)values(?,?,?)";
        try(Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,client.getfName());
            ps.setString(2,client.getlName());
            ps.setString(3,client.getEmail());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            System.out.println(rs);
            rs.next();
            int clientId = rs.getInt("client_id");
            client.setClientId(clientId);
            return client;


        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("A new client was not created",e);
            return null;

        }


    }

//   public static void main(String[] args) {
//          ClientDaoPostgresImpl clientDaoPostgres = new ClientDaoPostgresImpl();
//        Client client = new Client(0,"HOM NATH","GHIMIRE","h@gmail.com");
//        clientDaoPostgres.registerClient(client);
//       Set<Client> clients = clientDaoPostgres.getAllClients();
//       for(Client c: clients)
//       {
//           System.out.println(c);
//       }
//
//       //Client client = clientDaoPostgres.getClientById(9);
//       //System.out.println("the client with id 2 is"+client);
//       //clientDaoPostgres.updateClient(client);
//       //clientDaoPostgres.deleteClient(2);
//   }

    @Override
    public Set<Client> getAllClients() {
        Set<Client>allClients = new HashSet<>();
        try(Connection conn = ConnectionUtil.getConnection())
        {
            String sql = "select * from client";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();


            while(rs.next())
            {
                Client client = new Client();
                client.setClientId(rs.getInt("client_id"));
                client.setfName(rs.getString("first_name"));
                client.setlName(rs.getString("last_name"));
                client.setEmail(rs.getString("email"));
                allClients.add(client);

            }
            return allClients;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            logger.error("Wasn't able to get all clients",e);
            return allClients;
        }

    }

    @Override
    public Client getClientById(int clientid) {

        try(Connection conn = ConnectionUtil.getConnection())
        {
            String sql = "select * from client where client_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,clientid);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Client client = new Client();
            client.setClientId(rs.getInt("client_id"));
            client.setfName(rs.getString("first_name"));
            client.setlName(rs.getString("last_name"));
            client.setEmail(rs.getString("email"));

            return client;


        }
        catch(SQLException e)
        {
            e.printStackTrace();
            logger.error("Wasn't able to get client by Id",e);
            return null;
        }

    }

    @Override
    public Client updateClient(Client client) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {


            conn = ConnectionUtil.getConnection();
            String sql = "update client set first_name=?,last_name=?,email=? where client_id=?";
            ps = conn.prepareStatement(sql);

            ps.setString(1,client.getfName());
            ps.setString(2,client.getlName());
            ps.setString(3, client.getEmail());
            ps.setInt(4,client.getClientId());

            ps.executeUpdate();
            return client;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            logger.error("Wasn't able to update",e);
            return null;
        }
        finally
        {
            if(conn!=null & ps!=null)
            {
                try {
                    conn.close();
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    logger.error("Problem connecting with the database",e);
                }
            }



        }
    }

    @Override
    public boolean deleteClientById(int clientId) {

        try(Connection conn = ConnectionUtil.getConnection())
        {
            String sql = "delete from client where client_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,clientId);
            int rs = ps.executeUpdate();
            System.out.println(rs);
            //execute Update return number of rows affected, so if a row is deleted, it should be 1
            return rs>0;

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            logger.error("Something went wrong, wasn't able to delete",e);
        }
        return false;
    }
}

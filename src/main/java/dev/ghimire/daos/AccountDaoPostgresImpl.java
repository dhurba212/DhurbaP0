package dev.ghimire.daos;

import dev.ghimire.entities.Account;
import dev.ghimire.util.ConnectionUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class AccountDaoPostgresImpl implements AccountDAO{

     public static AccountDaoPostgresImpl accountDaoPostgres = new AccountDaoPostgresImpl();

     static Logger logger = Logger.getLogger(AccountDaoPostgresImpl.class.getName());

    @Override
    public Account createAccount(Account account) {
        try(Connection conn = ConnectionUtil.getConnection())
        {
            String sql = "insert into account(account_type,total_balance,client_id) values(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,account.getAccountType());
            ps.setDouble(2,account.getTotalBalance());
            ps.setInt(3,account.getClientId());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();

            rs.next();

            int accountId=rs.getInt("account_id");

            account.setAccountId(accountId);

            return account;

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            logger.error("Couldn't Create a new Account",e);
            return null;
        }

    }

    @Override
    public Set<Account> getAllAccounts() {
        Set<Account>allAccounts = new HashSet<>();

        try(Connection conn =  ConnectionUtil.getConnection())
        {
            String sql = "select * from account";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Account account = new Account();
                account.setAccountId(rs.getInt("account_id"));
                account.setAccountType(rs.getInt("account_type"));
                account.setTotalBalance(rs.getDouble("total_balance"));
                account.setClientId(rs.getInt("client_id"));

                allAccounts.add(account);

            }
            return allAccounts;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            logger.error("Wasn't able to get the accounts",e);
            return allAccounts;
        }

    }

    @Override
    public Account getAccountById(int accountId) {

        try(Connection conn = ConnectionUtil.getConnection())
        {
            String sql = "select * from account where account_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,accountId);

            ResultSet rs = ps.executeQuery();
            rs.next();

            Account account = new Account();
            account.setAccountId(rs.getInt("account_id"));
            account.setAccountType(rs.getInt("account_type"));
            account.setTotalBalance(rs.getDouble("total_balance"));
            account.setClientId(rs.getInt("client_id"));

            return account;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            logger.error("Wasn't able to return account by Id",e);
            return null;
        }

    }

    @Override
    public Account updateAccount(Account account) {

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "update account set account_type=?,total_balance=?,client_id=? where account_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, account.getAccountType());
            ps.setDouble(2, account.getTotalBalance());
            ps.setInt(3, account.getClientId());
            ps.setInt(4, account.getAccountId());

            //returns numbers of rows affected, in this case 1
            int update = ps.executeUpdate();

            if (update > 0)
            {
                return account;
            }
            else
            {
                return  null;
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            logger.error("Wasn't able to return the account by account id.", e);
            return null;
        }
    }


        @Override
    public boolean deleteAccountById(int id) {

        try(Connection conn = ConnectionUtil.getConnection())
        {
            String sql = "delete from account where account_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            //execute update returns number of affected rows, in this case 1 if the account is deleted
            int numOfDeletedAccount = ps.executeUpdate();

            return numOfDeletedAccount>0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            logger.error("Wasn't able to delete the account",e);
            return false;
        }


    }
}

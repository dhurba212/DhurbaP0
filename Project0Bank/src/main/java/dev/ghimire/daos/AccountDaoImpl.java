package dev.ghimire.daos;

import dev.ghimire.entities.Account;

import java.util.*;

public class AccountDaoImpl implements AccountDAO{



   // private Map<Integer, List<Account>> allAccounts = new HashMap<>();
    private static Map<Integer,Account>allAccounts = new HashMap<>();
    // Set<Account>allSetAccounts = new HashSet<>();
    private static int idMaker=0;


    @Override
    public Account createAccount(Account account) {
        account.setAccountId(++idMaker);
        /*
        int clientId=account.getClientId();
        if(allAccounts.containsKey(clientId))
        {
            List<Account> clientAccountList=allAccounts.get(clientId);
            clientAccountList.add(account);
            allAccounts.put(clientId,clientAccountList);

            return account;
        }
        else
        {
            List<Account>clientAccountList = new ArrayList<>();
            clientAccountList.add(account);
            allAccounts.put(clientId,clientAccountList);
            return account;
        }

         */
        int accountId=account.getAccountId();
        allAccounts.put(accountId,account);
        return account;

    }

   /* @Override
    public List<Account> getAllAccounts(int clientId) {
       List<Account> clientAccountList = allAccounts.get(clientId);
        return clientAccountList;
    }


    */
    @Override
    public Set<Account> getAllAccounts() {
        Set<Account>accounts = new HashSet<>(allAccounts.values());

        return accounts;
    }
/*
    @Override
    public List<Account> getAccountByClientId(int clientId) {
        List<Account> clientAccountList = allAccounts.get(clientId);
        return clientAccountList;
    }

 */

    @Override
    public Account getAccountById(int accountId) {
        Account account=allAccounts.get(accountId);
        return account;
    }


    @Override
    public Account updateAccount(Account account) {
        /*
        Account updatedAccount = null;
        int clientId =account.getClientId();
        List<Account>clientAccountList=allAccounts.get(clientId);
        for(Account a:clientAccountList)
        {
            if(a.getAccountId()==account.getAccountId())
            {
                int index = clientAccountList.indexOf(a);
                clientAccountList.remove(index);
                clientAccountList.add(index,account);
                allAccounts.put(clientId, clientAccountList);
                updatedAccount= clientAccountList.get(index);
                return updatedAccount;

            }

        }

        return updatedAccount;

         */
        int accountId = account.getAccountId();
        allAccounts.put(accountId,account);
        return allAccounts.get(accountId);

    }
/*
    @Override
    public boolean deleteAccountByClientIdAndAccountType(int clientId,int accountType) {
        boolean deleted=false;
       List<Account> allAccount = allAccounts.get(clientId);
       for(Account a:allAccount)
       {
           if(a.getAccountType()==accountType)
           {
               int index = allAccount.indexOf(a);
               allAccount.remove(index);
               allAccounts.put(clientId,allAccount);
               deleted=true;
               return deleted;
           }
       }

       return deleted;
    }

 */

    @Override
    public boolean deleteAccountById(int id) {

        Account account=allAccounts.remove(id);
        if(account!=null)
        {
            return true;
        }
        return false;
    }

//    public static void main(String args[]) {
//        // Creating an empty HashSet
//        HashSet<String> set = new HashSet<String>();
//
//        // Use add() method to add elements into the Set
//        set.add("Welcome");
//        set.add("To");
//        set.add("Geeks");
//        set.add("4");
//        set.add("Geeks");
//
//        // Displaying the HashSet
//        System.out.println("HashSet: " + set);
//
//        // Check for the empty set
//        System.out.println("Is the set empty: " + set.isEmpty());
//
//        // Clearing the set using clear() method
//        set.clear();
//
//        // Again Checking for the empty set
//
//
//        HashSet<String> set1 = new HashSet<String>();
//        set1=null;
//        System.out.println(set1);
//    }
}

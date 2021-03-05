package dev.ghimire.controller;

import com.google.gson.Gson;
import dev.ghimire.daos.AccountDaoImpl;
import dev.ghimire.daos.AccountDaoPostgresImpl;
import dev.ghimire.daos.ClientDaoImpl;
import dev.ghimire.daos.ClientDaoPostgresImpl;
import dev.ghimire.entities.Account;
import dev.ghimire.entities.Client;
import dev.ghimire.services.AccountService;
import dev.ghimire.services.AccountServiceImpl;
import dev.ghimire.services.ClientService;
import dev.ghimire.services.ClientServiceImpl;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.HashSet;
import java.util.Set;

public class AccountController {

    //private  ClientService clientService = new ClientServiceImpl(ClientDaoImpl.cdao);
    //private  AccountService accountService = new AccountServiceImpl(new AccountDaoImpl());

    private  ClientService clientService = new ClientServiceImpl(ClientDaoPostgresImpl.clientDaoPostgres);
    private  AccountService accountService = new AccountServiceImpl(AccountDaoPostgresImpl.accountDaoPostgres);


    Handler createNewAccountForClient = (Context ctx)->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        Gson gson =  new Gson();

        String accountJSON = ctx.body();

        Account newAccount = gson.fromJson(accountJSON, Account.class);



        if(clientService.getClientById(id)==null)
        {
            ctx.result("Client with id "+id+" doesn't exist,\n so i politely request you to create a Client with that id first\n and then add account :-D");
            ctx.status(404);
        }
        else
        {
            if(newAccount.getClientId()==id)
            {
                accountService.registerAccount(newAccount);

                ctx.result("New account was created for client "+ id);
                ctx.status(201);
            }
            else
            {
                ctx.result("The Client Id in URL and the Client Id in the new account doesn't match");
                ctx.status(400);
            }



        }


    };

    Handler getAllAccountsForAClientLambda = (Context ctx)->{

        Gson gson = new Gson();
        int id = Integer.parseInt(ctx.pathParam("id"));
        Double greaterThan = Double.parseDouble(ctx.queryParam("isGreaterThan","-1"));
        Double lessThan = Double.parseDouble(ctx.queryParam("isLessThan","-1"));

        //all accounts of a client with id=id
        Set<Account>allAccountsOfAClient = accountService.getAllAccountsByClientId(id);
        //all accounts of all clients with balance between greaterThan and lessThan
        Set<Account> allAccountsBetweenAmounts = accountService.getAccountByBalance(greaterThan,lessThan);
        //account of a client with id=id and balance between greaterThan and lessThan
        Set<Account> accountsBetweenAmountsOfAClient = new HashSet<>();



        //looping around all accounts with the desired balance and checking if it belongs to the client with id=id

        if(allAccountsBetweenAmounts.isEmpty()==false)
        {
            for(Account a:allAccountsBetweenAmounts)
            {
                if(a.getClientId()==id)
                {
                    accountsBetweenAmountsOfAClient.add(a);
                }
            }
        }


        if(clientService.getClientById(id)!=null)
        {


            if(allAccountsOfAClient.isEmpty()==true)
            {
                ctx.result("Client with id "+id+" doesn't have any Accounts");
                ctx.status(404);
            }
            else
            {

                if(greaterThan==-1 && lessThan==-1)
                {
                    int numOfTotalAccountsForAClient = allAccountsOfAClient.size();
                    String allAccountsOfAClientJSON = gson.toJson(allAccountsOfAClient);
                    ctx.result("The client with id: "+id+" has "+numOfTotalAccountsForAClient+" account\n"+allAccountsOfAClientJSON);
                    //ctx.result(allAccountsOfAClientJSON);
                    ctx.status(200);
                }
               else
                {
                    int numOfAccountsBetweenAmounts = accountsBetweenAmountsOfAClient.size();
                    String allAccountsOfAClientJSONBetweenAmounts = gson.toJson(accountsBetweenAmountsOfAClient);
                    ctx.result("The client with id: "+id+" has "+numOfAccountsBetweenAmounts+ " account" +
                            " greater than $"+greaterThan+" and less than $"+lessThan+"\n" +
                            ""+allAccountsOfAClientJSONBetweenAmounts);
                    //ctx.result(allAccountsOfAClientJSONBetweenAmounts);
                    ctx.status(200);
                }


            }
        }
        else
        {
            ctx.result("Client with id "+id+" doesn't exist");
            ctx.status(404);
        }



    };

    Handler getAnAccountForAClientLambda = (Context ctx)->{

        Gson gson = new Gson();
        int clientId =  Integer.parseInt(ctx.pathParam("id1"));
        int accountId =  Integer.parseInt(ctx.pathParam("id2"));


        Client client = clientService.getClientById(clientId);

        Set<Account>allAccountsForAClient = accountService.getAllAccountsByClientId(clientId);

        if(client==null)
        {
            ctx.result("Client with id: "+clientId+" doesn't exist");
            ctx.status(404);
        }
        else
        {
            if(allAccountsForAClient.isEmpty())
            {
                ctx.result("Client with id: "+clientId+" doesn't have any account");
                ctx.status(404);
            }
            else
            {
                Account account=null;
                for(Account a:allAccountsForAClient)
                {
                    if(a.getAccountId()==accountId) {
                        account = a;
                    }

                }
                if(account!=null) {
                    String accountJSON = gson.toJson(account);
                    ctx.result(accountJSON);
                    ctx.status(200);
                }else
                    {
                        ctx.result("Client with id: "+clientId+" doesn't have an account " +
                                "with account id "+accountId);
                        ctx.status(404);
                    }

            }
        }

    };

    Handler updateAnAccountForAClientLambda = (Context ctx)->{
        Gson gson = new Gson();
        int clientId =  Integer.parseInt(ctx.pathParam("id1"));
        int accountId =  Integer.parseInt(ctx.pathParam("id2"));
        String updatedAccountString = ctx.body();
        Account updatedAccount = gson.fromJson(updatedAccountString,Account.class);

        Client client = clientService.getClientById(clientId);

        Set<Account>allAccountsForAClient = accountService.getAllAccountsByClientId(clientId);

        if(accountId!=updatedAccount.getAccountId())
        {
            ctx.result("The account Id in the Url and the account id of the updated account doesn't match");
            ctx.status(400);
        }
        else if(clientId!=updatedAccount.getClientId())
        {
            ctx.result("The Client Id in the Url and the Client id of the updated account doesn't match");
            ctx.status(400);
        }
        else
        {
            if(client==null)
            {
                ctx.result("Client with id: "+clientId+" doesn't exist");
                ctx.status(404);
            }
            else
            {
                if(allAccountsForAClient.isEmpty())
                {
                    ctx.result("Client with id: "+clientId+" doesn't have any account to update");
                    ctx.status(404);
                }
                else
                {
                    Account account=null;
                    for(Account a:allAccountsForAClient)
                    {
                        if(a.getAccountId()==accountId) {
                            account = updatedAccount;
                        }

                    }
                    if(account!=null) {
                        accountService.updateAccount(account);
                        String accountJSON = gson.toJson(account);

                        ctx.result("The account with account id "+accountId+" has been updated to \n"+accountJSON);
                        ctx.status(200);
                    }else
                    {
                        ctx.result("Client with id: "+clientId+" doesn't have an account " +
                                "with account id "+accountId);
                        ctx.status(404);
                    }

                }
            }

        }

    };

    Handler deleteAnAccountForAClientLambda = (Context ctx)->{

        Gson gson = new Gson();
        int clientId =  Integer.parseInt(ctx.pathParam("id1"));
        int accountId =  Integer.parseInt(ctx.pathParam("id2"));

        Client client = clientService.getClientById(clientId);

        Set<Account>allAccountsForAClient = accountService.getAllAccountsByClientId(clientId);

        if(client==null)
        {
            ctx.result("Client with id: "+clientId+" doesn't exist");
            ctx.status(404);
        }
        else
        {
            if(allAccountsForAClient.isEmpty())
            {
                ctx.result("Client with id: "+clientId+" doesn't have any account to delete");
                ctx.status(404);
            }
            else
            {
                Account account=null;
                for(Account a:allAccountsForAClient)
                {
                    if(a.getAccountId()==accountId) {
                        account = a;
                    }

                }
                if(account!=null) {
                    accountService.deleteAccountById(accountId);

                    ctx.result("The account with account id "+accountId+" has been deleted");
                    ctx.status(200);
                }else
                {
                    ctx.result("Client with id: "+clientId+" doesn't have an account " +
                            "with account id "+accountId);
                    ctx.status(404);
                }

            }
        }

    };
}

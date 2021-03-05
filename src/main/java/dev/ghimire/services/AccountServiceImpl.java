package dev.ghimire.services;

import dev.ghimire.daos.AccountDAO;
import dev.ghimire.entities.Account;

import java.util.HashSet;
import java.util.Set;

public class AccountServiceImpl implements AccountService{
    //need to create an object of AccountDAO to use its methods
    //we have written methods in DAO and here at service we use them to get desired results
    private AccountDAO accountDAO;

    //dependency injection, since methods in this class are dependent on AccountDAO to call on its method, we pass it as object
    public AccountServiceImpl(AccountDAO accountDAO)
    {
        this.accountDAO=accountDAO;
    }

    @Override
    public Account registerAccount(Account account) {

        Account createdAccount = accountDAO.createAccount(account);
        return createdAccount;
    }

    @Override
    public Set<Account> getAllAccounts() {
        Set<Account>accounts = accountDAO.getAllAccounts();
        return accounts;
    }

    @Override
    public Set<Account> getAllAccountsByClientId(int clientId) {
        Set<Account> allAccounts = accountDAO.getAllAccounts();
        Set<Account> allAccountsOfAClient = new HashSet<>();

        for(Account a:allAccounts)
        {
            if(a.getClientId()==clientId)
            {
                allAccountsOfAClient.add(a);
            }

        }

        return allAccountsOfAClient;

    }

    @Override
    public Set<Account> getAccountByBalance(double minAmount, double maxAmount) {
        Set<Account> selectedAccount = new HashSet<>();
        Set<Account>accounts = accountDAO.getAllAccounts();

        for(Account a:accounts)
        {
            if(a.getTotalBalance()>=minAmount && a.getTotalBalance()<=maxAmount)
            {
                selectedAccount.add(a);
            }
        }
        return selectedAccount;
    }

    @Override
    public Account getAccountById(int accountId) {
        Account account = accountDAO.getAccountById(accountId);
        return account;
    }

    @Override
    public Account updateAccount(Account account) {
        Account updatedAccount = accountDAO.updateAccount(account);
        return updatedAccount;
    }

    @Override
    public boolean deleteAccountById(int accountId) {
        boolean deleted = accountDAO.deleteAccountById(accountId);

        return deleted;
    }




}

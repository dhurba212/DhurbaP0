package dev.ghimire.services;

import dev.ghimire.entities.Account;

import java.util.Set;

public interface AccountService {

    Account registerAccount(Account account);

    Set<Account> getAllAccounts();

    Set<Account> getAllAccountsByClientId(int clientId);

    Set<Account> getAccountByBalance(double minAmount, double maxAmount);

    Account getAccountById(int accountId);

    Account updateAccount(Account account);

    boolean deleteAccountById(int accountId);


}

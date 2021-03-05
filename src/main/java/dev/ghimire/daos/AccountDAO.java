package dev.ghimire.daos;

import dev.ghimire.entities.Account;

import java.util.List;
import java.util.Set;

public interface AccountDAO {
    Account createAccount(Account account);

   // List<Account> getAllAccounts(int clientId);
    Set<Account>getAllAccounts();

    //List<Account> getAccountByClientId(int clientId);
    Account getAccountById(int accountId);

    Account updateAccount(Account account);

   // boolean deleteAccountByClientIdAndAccountType(int clientId,int accountType);

    boolean deleteAccountById(int id);

}

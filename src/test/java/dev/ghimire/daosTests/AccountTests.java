package dev.ghimire.daosTests;

import dev.ghimire.daos.AccountDAO;
import dev.ghimire.daos.AccountDaoImpl;
import dev.ghimire.daos.AccountDaoPostgresImpl;
import dev.ghimire.entities.Account;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountTests {

//    // private static AccountDAO adao = new AccountDaoImpl();
//     private static AccountDAO adao = new AccountDaoPostgresImpl();
//     private static Account newAccount=null;
//     @Test
//     @Order(1)
//     void create_account_test_1(){
//         //creating a new account
//         Account account1 = new Account(0,1,500.00,1);

//         //createAccount method returns an Account, so save it on variable accountCreated
//         Account accountCreated = adao.createAccount(account1);
//        //set newAccount to the accountCreated, so we do do other tests with it like update and delete in other methods
//         newAccount=accountCreated;
//        // System.out.println(adao.getAllAccounts(1));
//         //testing if the account we passed to create and the account created match
//         Assertions.assertEquals(account1.getTotalBalance(),accountCreated.getTotalBalance());
//         //although we have set accountid=0, the createAccount method uses idMaker to automatically increment that
//         //and since it is the first Account created, its account id is 1.
//         Assertions.assertNotEquals(0,accountCreated.getAccountId());
//     }

//     @Test
//     @Order(2)
//     void update_account_test_1(){
//         //deposited 1000 to newAccount, so the total balance now is 1500
//         newAccount.deposit(1000);
//         double balance = newAccount.getTotalBalance();
//         //using updateAccount method of AccountDAOImpl class
//        Account updatedAccount = adao.updateAccount(newAccount);

//        Assertions.assertEquals(balance,updatedAccount.getTotalBalance());

//     }

//     @Test
//     @Order(3)
//     void get_all_accounts_test_1()
//     {
// //        Account account2 = new Account(0,2,100.00,1);
// //        Account account3 = new Account(0,3,200.00,1);
// //        Account account4 = new Account(0,4,300.00,1);
// //        adao.createAccount(account2);
// //        adao.createAccount(account3);
// //        adao.createAccount(account4);
//         int clientId = newAccount.getClientId();
//         Set<Account> allAccount = adao.getAllAccounts();
//        // System.out.println(allAccount);

//         Assertions.assertTrue(allAccount.size()>0);


//     }

//     @Test
//     @Order(4)
//     void delete_account_test_1()
//     {
//         boolean deleted =adao.deleteAccountById(newAccount.getAccountId());
//         Assertions.assertTrue(deleted);
//     }
//     /*
//     @Test
//     @Order(4)
//     void delete_account_by_client_id_and_account_type()
//     {
//         int clientId = newAccount.getClientId();
//         int accountType = newAccount.getAccountType();
//         List<Account> allAccountList = adao.getAllAccounts(clientId);
//         System.out.println(allAccountList);
//         boolean deleted = adao.deleteAccountByClientIdAndAccountType(clientId,accountType);
//         List<Account> allAccountListAfterDelete = adao.getAllAccounts(clientId);
//         System.out.println(allAccountListAfterDelete);
//         //Assertions.assertNotEquals(allAccountList.size(),allAccountListAfterDelete.size());
//        // Assertions.assertTrue(deleted);
//     }

//      */


}

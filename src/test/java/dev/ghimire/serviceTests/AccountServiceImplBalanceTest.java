package dev.ghimire.serviceTests;


import dev.ghimire.daos.AccountDAO;
import dev.ghimire.entities.Account;
import dev.ghimire.services.AccountService;
import dev.ghimire.services.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplBalanceTest {

    // @Mock
    // AccountDAO accountDAO=null;

    // AccountService accountService=null;

    // @BeforeEach
    // void set_up()
    // {

    //     Account ac1= new Account(1,1,500,1);
    //     Account ac2= new Account(2,2,1000,1);
    //     Account ac3= new Account(3,3,1500,1);
    //     Account ac4= new Account(4,1,50,2);
    //     Account ac5= new Account(5,2,1000,2);
    //     Account ac6= new Account(6,3,5000,2);

    //     Set<Account>mockedAccounts = new HashSet<>();
    //     mockedAccounts.add(ac1);
    //     mockedAccounts.add(ac2);
    //     mockedAccounts.add(ac3);
    //     mockedAccounts.add(ac4);
    //     mockedAccounts.add(ac5);
    //     mockedAccounts.add(ac6);

    //     Mockito.when(accountDAO.getAllAccounts()).thenReturn(mockedAccounts);

    //     this.accountService= new AccountServiceImpl(this.accountDAO);
    // }

    // @Test
    // void get_account_by_balance_test_1()
    // {
    //     Set<Account>selectedAccount = accountService.getAccountByBalance(1000,2000);
    //     for(Account a:selectedAccount)
    //     {
    //         System.out.println(a);
    //         Assertions.assertTrue(a.getTotalBalance()>=1000 && a.getTotalBalance()<=2000);

    //     }
    // }
}

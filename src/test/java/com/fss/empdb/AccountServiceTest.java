//package com.fss.empdb;
//
//import com.fss.empdb.domain.Account;
//import com.fss.empdb.domain.AccountSearchCriteria;
//import com.fss.empdb.domain.Region;
//import com.fss.empdb.service.AccountService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@DirtiesContext
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ActiveProfiles("local")
//@SpringBootTest
//public class AccountServiceTest {
//
//
////    @Autowired
////    AccountService accountService;
////
////
////    private Account newAccount() {
////        Account acc = new Account();
////        acc.setAccountName("RBI Bank3");
////        Region reg = new Region();
////        reg.setRegionId(3L);
////        acc.setRegion(reg);
////        return acc;
////    }
////
////    private Account updateAccount() {
////        Account acc = new Account();
////        acc.setAccountId(7L);
////        acc.setAccountName("Yes Bank3");
////        Region reg = new Region();
////        reg.setRegionId(5L);
////        acc.setRegion(reg);
////        return acc;
////    }
////
////    private AccountSearchCriteria searchAccount() {
////        AccountSearchCriteria acc = new AccountSearchCriteria();
////        acc.setAccountName("");
////        Region reg = new Region();
////        reg.setRegionId(1L);
////        Region reg1 = new Region();
////        reg1.setRegionId(5L);
////        Region[] regArr = {reg, reg1};
////
////        acc.setRegion(regArr);
////        return acc;
////    }
////
////    @Test
////    public void createAccount_success() {
////        Account acc = newAccount();
////        Account newAcc = accountService.createAccount(acc);
////        assertNotNull(newAcc);
////        assertNotNull(newAcc.getAccountId());
////    }
////
////    @Test
////    public void updateAccount_success() {
////        Account acc = updateAccount();
////        Account newAcc = accountService.updateAccount(acc);
////        assertNotNull(newAcc);
////        assertNotNull(newAcc.getAccountId());
////    }
////
////    @Test
////    public void deleteAccount_success() {
////        accountService.deleteAccount(5L);
////    }
////
////    @Test
////    public void searchAccount_success() {
////        AccountSearchCriteria acc = searchAccount();
////        System.out.println("Test Case " + acc);
////        List<Account> newAcc = accountService.accountBySearch(acc);
////        assertNotNull(newAcc);
////    }
//
//
//}

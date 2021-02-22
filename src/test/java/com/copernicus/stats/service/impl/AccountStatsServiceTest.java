package com.copernicus.stats.service.impl;

import com.copernicus.stats.enums.Industry;
import com.copernicus.stats.enums.Product;
import com.copernicus.stats.enums.Status;
import com.copernicus.stats.model.Account;
import com.copernicus.stats.model.Contact;
import com.copernicus.stats.model.Opportunity;
import com.copernicus.stats.repository.AccountRepository;
import com.copernicus.stats.repository.ContactRepository;
import com.copernicus.stats.repository.OpportunityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountStatsServiceTest {

    @Autowired
    OpportunityRepository opportunityRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountStatsService accountStatsService;

    Optional<String> status1 = Optional.empty();
    Optional<String> status2 = Optional.of("OPEN");


    @BeforeEach
    void setUp() {

        Account account1 = new Account(Industry.OTHER, 40, "Albacete", "ESPAÑA");
        Account account2 = new Account(Industry.MEDICAL, 80, "Buguibugui", "EZPAÑA");
        accountRepository.saveAll(List.of(account1,account2));

        Contact contact1 = new Contact("Pepa Pig", "676767676", "pepa@pig.pp", "Pigs", account1);
        Contact contact2 = new Contact("Ana Cardo", "656565656", "ana@car.do", "Cards", account1);
        Contact contact3 = new Contact("Hula Hop", "656565656", "hu@la.hop", "Huli", account2);

        Opportunity opportunity1 = new Opportunity(Product.BOX, 40, contact1, 1, account1);
        Opportunity opportunity2 = new Opportunity(Product.FLATBED, 23, contact2, 2, account1);
        Opportunity opportunity3 = new Opportunity(Product.HYBRID, 77, contact3, 2, account2);

        opportunity2.setStatus(Status.CLOSED_LOST);
        opportunity3.setStatus(Status.CLOSED_WON);

        contactRepository.saveAll(List.of(contact1,contact2, contact3));

        opportunityRepository.saveAll(List.of(opportunity1, opportunity2, opportunity3));


        account1.setContactList(List.of(contact1,contact2));
        account2.setContactList(List.of(contact3));

        opportunity1.setAccount(account1);
        opportunity2.setAccount(account1);
        opportunity3.setAccount(account2);
        contact1.setAccount(account1);
        contact2.setAccount(account1);
        contact3.setAccount(account2);
        contactRepository.saveAll(List.of(contact1,contact2, contact3));

        opportunityRepository.saveAll(List.of(opportunity1,opportunity2,opportunity3));
    }


    @AfterEach
    void tearDown() {
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    void avgEmployeeCount() {
        Double result = accountStatsService.avgEmployeeCount();
        assertEquals(60.0, result);
    }

    @Test
    void medianEmployeeCount() {
        Double result = accountStatsService.medianEmployeeCount();
        assertEquals(60.0, result);
    }

    @Test
    void minEmployeeCount() {
        Integer result = accountStatsService.minEmployeeCount();
        assertEquals(40, result);
    }

    @Test
    void maxEmployeeCount() {
        Integer result = accountStatsService.maxEmployeeCount();
        assertEquals(80, result);
    }
}
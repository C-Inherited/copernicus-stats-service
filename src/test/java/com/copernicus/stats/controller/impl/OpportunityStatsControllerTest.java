package com.copernicus.stats.controller.impl;

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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpportunityStatsControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    OpportunityRepository opportunityRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Account account1 = new Account(Industry.OTHER, 40, "Albacete", "ESSSSPANA");
        Account account2 = new Account(Industry.MEDICAL, 75, "Buguibugui", "EZPANA");
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
    void countOpportunitiesByProduct() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/opportunity/count/by/product"))
                .andReturn();

        assertTrue(result.getResponse().getContentAsString()
                .contains("[\"BOX\",1],[\"FLATBED\",1],[\"HYBRID\",1]"));
    }

    @Test
    void countOpportunitiesByCity() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/opportunity/count/by/city"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
        assertTrue(result.getResponse().getContentAsString()
                .contains("[\"Albacete\",2],[\"Buguibugui\",1]"));
    }

    @Test
    void countOpportunitiesByCountry() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/opportunity/count/by/country"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
        assertTrue(result.getResponse().getContentAsString()
                .contains("[\"ESSSSPANA\",2],[\"EZPANA\",1]"));
    }

    @Test
    void countOpportunitiesByIndustry() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/opportunity/count/by/industry"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
        assertTrue(result.getResponse().getContentAsString()
                .contains("[\"OTHER\",2],[\"MEDICAL\",1]"));
    }

    @Test
    void avgOpportunitiesByAccount() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/opportunity/avg/by/account"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
        assertTrue(result.getResponse().getContentAsString()
                .contains("1.5"));
    }

    @Test
    void maxOpportunitiesByAccount() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/opportunity/max/by/account"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
        assertTrue(result.getResponse().getContentAsString()
                .contains("2"));
    }


    @Test
    void minOpportunitiesByAccount() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/opportunity/min/by/account"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
        assertTrue(result.getResponse().getContentAsString()
                .contains("1"));
    }

    @Test
    void medianOpportunitiesByAccount() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/opportunity/median/by/account"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
        assertTrue(result.getResponse().getContentAsString()
                .contains("1.5"));
    }

    @Test
    void avgQuantityByProduct() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/opportunity/avg/quantity/by/product"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
        assertTrue(result.getResponse().getContentAsString()
                .contains("[\"BOX\",40.0],[\"FLATBED\",23.0],[\"HYBRID\",77.0]"));
    }


    @Test
    void maxQuantityByProduct() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/opportunity/max/quantity/by/product"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
        assertTrue(result.getResponse().getContentAsString()
                .contains("[\"BOX\",40],[\"FLATBED\",23],[\"HYBRID\",77]"));
    }


    @Test
    void minQuantityByProduct() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/opportunity/min/quantity/by/product"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
        assertTrue(result.getResponse().getContentAsString()
                .contains("[\"BOX\",40],[\"FLATBED\",23],[\"HYBRID\",77]"));
    }


    @Test
    void medianQuantityByProduct() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/opportunity/median/quantity/by/product"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
        assertTrue(result.getResponse().getContentAsString()
                .contains("[\"BOX\",40.0],[\"FLATBED\",23.0],[\"HYBRID\",77.0]"));
    }
}
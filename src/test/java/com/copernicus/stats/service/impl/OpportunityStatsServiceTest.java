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
import com.copernicus.stats.service.interfaces.IOpportunityStatsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OpportunityStatsServiceTest {

    @Autowired
    OpportunityRepository opportunityRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    IOpportunityStatsService opportunityStatsService;

    Optional<String> status1 = Optional.empty();
    Optional<String> status2 = Optional.of("OPEN");


    @BeforeEach
    void setUp() {

        Account account1 = new Account(Industry.OTHER, 40, "Albacete", "ESPAÑA");
        Account account2 = new Account(Industry.MEDICAL, 75, "Buguibugui", "EZPAÑA");
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
    void countOpportunitiesByProduct() {
        List<Object[]> result = opportunityStatsService.countOpportunitiesByProduct(status1);
        assertEquals(1L, result.get(0)[1]);
        assertEquals(1L, result.get(1)[1]);
        assertEquals(1L, result.get(2)[1]);
    }

    @Test
    void countOpportunitiesByProductWithStatus() {
        List<Object[]> result = opportunityStatsService.countOpportunitiesByProduct(status2);
        assertEquals(1L, result.get(0)[1]);
    }

    @Test
    void countOpportunitiesByCity() {
        List<Object[]> result = opportunityStatsService.countOpportunitiesByCity(status1);
        assertEquals(2L, result.get(0)[1]);
        assertEquals("Albacete", result.get(0)[0]);
        assertEquals(1L, result.get(1)[1]);
        assertEquals("Buguibugui", result.get(1)[0]);
    }

    @Test
    void countOpportunitiesByCityWithStatus() {
        List<Object[]> result = opportunityStatsService.countOpportunitiesByCity(status2);
        assertEquals(1L, result.get(0)[1]);
        assertEquals("Albacete", result.get(0)[0]);
    }

    @Test
    void countOpportunitiesByCountry() {
        List<Object[]> result = opportunityStatsService.countOpportunitiesByCountry(status1);
        assertEquals(2L, result.get(0)[1]);
        assertEquals("ESPAÑA", result.get(0)[0]);
        assertEquals(1L, result.get(1)[1]);
        assertEquals("EZPAÑA", result.get(1)[0]);
    }

    @Test
    void countOpportunitiesByCountryWithStatus() {
        List<Object[]> result = opportunityStatsService.countOpportunitiesByCountry(status2);
        assertEquals(1L, result.get(0)[1]);
        assertEquals("ESPAÑA", result.get(0)[0]);
    }

    @Test
    void countOpportunitiesByIndustry() {
        List<Object[]> result = opportunityStatsService.countOpportunitiesByIndustry(status1);
        assertEquals(2L, result.get(0)[1]);
        assertEquals(Industry.OTHER, result.get(0)[0]);
        assertEquals(1L, result.get(1)[1]);
        assertEquals(Industry.MEDICAL, result.get(1)[0]);
    }

    @Test
    void avgQuantityByProduct() {
        List<Object[]> result = opportunityStatsService.avgQuantityByProduct();
        assertEquals(40.0, result.get(0)[1]);
        assertEquals(Product.BOX, result.get(0)[0]);
        assertEquals(23.0, result.get(1)[1]);
        assertEquals(Product.FLATBED, result.get(1)[0]);
        assertEquals(77.0, result.get(2)[1]);
        assertEquals(Product.HYBRID, result.get(2)[0]);
    }

    @Test
    void maxQuantityByProduct() {
        List<Object[]> result = opportunityStatsService.maxQuantityByProduct();
        assertEquals(40, result.get(0)[1]);
        assertEquals(Product.BOX, result.get(0)[0]);
        assertEquals(23, result.get(1)[1]);
        assertEquals(Product.FLATBED, result.get(1)[0]);
        assertEquals(77, result.get(2)[1]);
        assertEquals(Product.HYBRID, result.get(2)[0]);
    }

    @Test
    void minQuantityByProduct() {
        List<Object[]> result = opportunityStatsService.minQuantityByProduct();
        assertEquals(40, result.get(0)[1]);
        assertEquals(Product.BOX, result.get(0)[0]);
        assertEquals(23, result.get(1)[1]);
        assertEquals(Product.FLATBED, result.get(1)[0]);
        assertEquals(77, result.get(2)[1]);
        assertEquals(Product.HYBRID, result.get(2)[0]);
    }

    @Test
    void medianQuantityByProduct() {
        List<Object[]> result = opportunityStatsService.medianQuantityByProduct();
        assertEquals(40.0, result.get(0)[1]);
        assertEquals(Product.BOX, result.get(0)[0]);
        assertEquals(23.0, result.get(1)[1]);
        assertEquals(Product.FLATBED, result.get(1)[0]);
        assertEquals(77.0, result.get(2)[1]);
        assertEquals(Product.HYBRID, result.get(2)[0]);
    }

    @Test
    void avgOpportunitiesByAccount() {
        Double result = opportunityStatsService.avgOpportunitiesByAccount();
        assertEquals(1.5, result);
    }

    @Test
    void maxOpportunitiesByAccount() {
        Integer result = opportunityStatsService.maxOpportunitiesByAccount();
        assertEquals(2, result);
    }

    @Test
    void minOpportunitiesByAccount() {
        Integer result = opportunityStatsService.minOpportunitiesByAccount();
        assertEquals(1, result);
    }

    @Test
    void medianOpportunitiesByAccount() {
        Double result = opportunityStatsService.medianOpportunitiesByAccount();
        assertEquals(1.5, result);
    }

}
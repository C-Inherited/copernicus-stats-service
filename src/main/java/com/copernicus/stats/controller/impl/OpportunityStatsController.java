package com.copernicus.stats.controller.impl;

import com.copernicus.stats.controller.interfaces.IOpportunityStatsController;
import com.copernicus.stats.service.interfaces.IOpportunityStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OpportunityStatsController implements IOpportunityStatsController {

    @Autowired
    IOpportunityStatsService opportunityService;

    @GetMapping("/opportunity/count/by/product")
    public List<Object[]> countOpportunitiesByProduct(Optional<String> status) {
        return opportunityService.countOpportunitiesByProduct(status);
    }

    @GetMapping("/opportunity/count/by/city")
    public List<Object[]> countOpportunitiesByCity(Optional<String> status) {
        return opportunityService.countOpportunitiesByCity(status);
    }

    @GetMapping("/opportunity/count/by/country")
    public List<Object[]> countOpportunitiesByCountry(Optional<String> status) {
        return opportunityService.countOpportunitiesByCountry(status);
    }

    @GetMapping("/opportunity/count/by/industry")
    public List<Object[]> countOpportunitiesByIndustry(Optional<String> status) {
        return opportunityService.countOpportunitiesByIndustry(status);
    }

    @GetMapping("/opportunity/avg/by/account")
    public Double avgOpportunitiesByAccount() {
        return opportunityService.avgOpportunitiesByAccount();
    }

    @GetMapping("/opportunity/avg/quantity/by/product")
    public List<Object[]> avgQuantityByProduct() {
        return opportunityService.avgQuantityByProduct();
    }

    @GetMapping("/opportunity/max/by/account")
    public Integer maxOpportunitiesByAccount() {
        return opportunityService.maxOpportunitiesByAccount();
    }

    @GetMapping("/opportunity/max/quantity/by/product")
    public List<Object[]> maxQuantityByProduct() {
        return opportunityService.maxQuantityByProduct();
    }

    @GetMapping("/opportunity/min/by/account")
    public Integer minOpportunitiesByAccount() {
        return opportunityService.minOpportunitiesByAccount();
    }

    @GetMapping("/opportunity/min/quantity/by/product")
    public List<Object[]> minQuantityByProduct() {
        return opportunityService.minQuantityByProduct();
    }

    @GetMapping("/opportunity/median/by/account")
    public Double medianOpportunitiesByAccount() {
        return opportunityService.medianOpportunitiesByAccount();
    }

    @GetMapping("/opportunity/median/quantity/by/product")
    public List<Object[]> medianQuantityByProduct() {
        return opportunityService.medianQuantityByProduct();
    }
}

package com.copernicus.stats.controller.impl;

import com.copernicus.stats.controller.interfaces.IOpportunityStatsController;
import com.copernicus.stats.service.interfaces.IOpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OpportunityStatsController implements IOpportunityStatsController {

    @Autowired
    IOpportunityService opportunityService;

    @GetMapping("/opportunities/count/by/product")
    public List<Object[]> countOpportunitiesByProduct(Optional<String> status) {
        return opportunityService.countOpportunitiesByProduct(status);
    }

    @GetMapping("/opportunities/count/by/city")
    public List<Object[]> countOpportunitiesByCity(Optional<String> status) {
        return opportunityService.countOpportunitiesByCity(status);
    }

    @GetMapping("/opportunities/count/by/country")
    public List<Object[]> countOpportunitiesByCountry(Optional<String> status) {
        return opportunityService.countOpportunitiesByCountry(status);
    }

    @GetMapping("/opportunities/count/by/industry")
    public List<Object[]> countOpportunitiesByIndustry(Optional<String> status) {
        return opportunityService.countOpportunitiesByIndustry(status);
    }

    @GetMapping("/opportunities/avg/by/account")
    public Double avgOpportunitiesByAccount() {
        return opportunityService.avgOpportunitiesByAccount();
    }

    @GetMapping("/opportunities/avg/quantity/by/product")
    public List<Object[]> avgQuantityByProduct() {
        return opportunityService.avgQuantityByProduct();
    }

    @GetMapping("/opportunities/max/by/account")
    public Integer maxOpportunitiesByAccount() {
        return opportunityService.maxOpportunitiesByAccount();
    }

    @GetMapping("/opportunities/max/quantity/by/product")
    public List<Object[]> maxQuantityByProduct() {
        return opportunityService.maxQuantityByProduct();
    }

    @GetMapping("/opportunities/min/by/account")
    public Integer minOpportunitiesByAccount() {
        return opportunityService.minOpportunitiesByAccount();
    }

    @GetMapping("/opportunities/min/quantity/by/product")
    public List<Object[]> minQuantityByProduct() {
        return opportunityService.minQuantityByProduct();
    }

    @GetMapping("/opportunities/median/by/account")
    public Double medianOpportunitiesByAccount() {
        return opportunityService.medianOpportunitiesByAccount();
    }

    @GetMapping("/opportunities/median/quantity/by/product")
    public List<Object[]> medianQuantityByProduct() {
        return opportunityService.medianQuantityByProduct();
    }
}

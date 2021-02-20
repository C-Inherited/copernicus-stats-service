package com.ironhack.opportunity.controller.impl;

import com.ironhack.opportunity.controller.DTO.OpportunityDTO;
import com.ironhack.opportunity.controller.interfaces.IOpportunityController;
import com.ironhack.opportunity.model.Opportunity;
import com.ironhack.opportunity.service.interfaces.IOpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OpportunityController implements IOpportunityController {

    @Autowired
    IOpportunityService opportunityService;

    @GetMapping("/opportunity/{id}")
    public OpportunityDTO getOpportunity(@PathVariable Integer id) {
        return opportunityService.getOpportunity(id);
    }

    @GetMapping("/opportunity/all")
    public List<OpportunityDTO> getAllOpportunities() {
        return opportunityService.getAllOpportunities();
    }

    @PostMapping("/opportunity")
    public OpportunityDTO postOpportunity(@RequestBody OpportunityDTO opportunityDTO) {
        return opportunityService.postOpportunity(opportunityDTO);
    }

    @PutMapping("/opportunity/{id}")
    public OpportunityDTO putOpportunity(@PathVariable Integer id, @RequestBody OpportunityDTO opportunityDTO) {
        return opportunityService.putOpportunity(id, opportunityDTO);
    }

    @DeleteMapping("/opportunity/{id}")
    public boolean deleteOpportunity(@PathVariable Integer id) {
        return opportunityService.deleteOpportunity(id);
    }

    @GetMapping("/opportunities/all/{salesRepId}")
    public List<OpportunityDTO> countOpportunitiesBySalesRep(@PathVariable Integer salesRepId, @RequestParam Optional<String> status) {
        return opportunityService.countOpportunitiesBySalesRep(salesRepId, status);
    }

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

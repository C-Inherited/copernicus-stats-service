package com.ironhack.opportunity.controller.interfaces;

import com.ironhack.opportunity.controller.DTO.OpportunityDTO;
import com.ironhack.opportunity.model.Opportunity;

import java.util.List;
import java.util.Optional;

public interface IOpportunityController {
    OpportunityDTO getOpportunity(Integer id);

    List<OpportunityDTO> getAllOpportunities();

    OpportunityDTO postOpportunity(OpportunityDTO opportunityDTO);

    OpportunityDTO putOpportunity(Integer id, OpportunityDTO opportunityDTO);

    boolean deleteOpportunity(Integer id);

    List<OpportunityDTO> countOpportunitiesBySalesRep(Integer salesRepId, Optional<String> status);

    List<Object[]> countOpportunitiesByProduct(Optional<String> status);

    List<Object[]> countOpportunitiesByCity(Optional<String> status);

    List<Object[]> countOpportunitiesByCountry(Optional<String> status);

    List<Object[]> countOpportunitiesByIndustry(Optional<String> status);

    Double avgOpportunitiesByAccount();

    List<Object[]> avgQuantityByProduct();

    Integer maxOpportunitiesByAccount();

    List<Object[]> maxQuantityByProduct();

    Integer minOpportunitiesByAccount();

    List<Object[]> minQuantityByProduct();

    Double medianOpportunitiesByAccount();

    List<Object[]> medianQuantityByProduct();
}

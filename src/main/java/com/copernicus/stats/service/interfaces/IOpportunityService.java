package com.copernicus.stats.service.interfaces;

import com.copernicus.stats.controller.DTO.OpportunityDTO;

import java.util.List;
import java.util.Optional;

public interface IOpportunityService {

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

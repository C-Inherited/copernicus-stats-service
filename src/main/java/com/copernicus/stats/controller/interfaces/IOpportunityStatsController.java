package com.copernicus.stats.controller.interfaces;

import java.util.List;
import java.util.Optional;

public interface IOpportunityStatsController {


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

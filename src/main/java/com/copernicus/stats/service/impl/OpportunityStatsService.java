package com.copernicus.stats.service.impl;

import com.copernicus.stats.enums.Product;
import com.copernicus.stats.enums.Status;
import com.copernicus.stats.repository.OpportunityRepository;
import com.copernicus.stats.service.interfaces.IOpportunityStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.copernicus.stats.utils.Math.median;

@Service
public class OpportunityStatsService implements IOpportunityStatsService {

    @Autowired
    OpportunityRepository opportunityRepository;

    @Override
    public List<Object[]> countOpportunitiesByProduct(Optional<String> status) {
        if (status.isEmpty()) {
            return opportunityRepository.findNumberOfOpportunitiesPerProduct();
        }else{
            return opportunityRepository.findNumberOfOpportunitiesPerProductWithStatus(Status.valueOf(status.get()));
        }
    }

    @Override
    public List<Object[]> countOpportunitiesByCity(Optional<String> status) {
        if (status.isEmpty()) {
            return opportunityRepository.findNumberOfOpportunitiesPerCity();
        }else{
            return opportunityRepository.findNumberOfOpportunitiesPerCityWithStatus(Status.valueOf(status.get()));
        }
    }

    @Override
    public List<Object[]> countOpportunitiesByCountry(Optional<String> status) {
        if (status.isEmpty()) {
            return opportunityRepository.findNumberOfOpportunitiesPerCountry();
        }else{
            return opportunityRepository.findNumberOfOpportunitiesPerCountryWithStatus(Status.valueOf(status.get()));
        }
    }

    @Override
    public List<Object[]> countOpportunitiesByIndustry(Optional<String> status) {
        if (status.isEmpty()) {
            return opportunityRepository.findNumberOfOpportunitiesPerIndustry();
        }else{
            return opportunityRepository.findNumberOfOpportunitiesPerIndustryWithStatus(Status.valueOf(status.get()));
        }
    }

    @Override
    public Double avgOpportunitiesByAccount() {
        return opportunityRepository.findAvgOpportunitiesByAccountId();
    }

    @Override
    public List<Object[]> avgQuantityByProduct() {
        return opportunityRepository.findAvgQuantityGroupByProduct();
    }

    @Override
    public Integer maxOpportunitiesByAccount() {
        return opportunityRepository.findMaxOpportunitiesByAccountId();
    }

    @Override
    public List<Object[]> maxQuantityByProduct() {
        return opportunityRepository.findMaxQuantityGroupByProduct();
    }

    @Override
    public Integer minOpportunitiesByAccount() {
        return opportunityRepository.findMinOpportunitiesByAccountId();
    }

    @Override
    public List<Object[]> minQuantityByProduct() {
        return opportunityRepository.findMinQuantityGroupByProduct();
    }

    @Override
    public Double medianOpportunitiesByAccount() {
        return median(opportunityRepository.findOrderOpportunitiesByAccountId());
    }

    @Override
    public List<Object[]> medianQuantityByProduct() {
        List<Object[]> objectsList = List.of(new Object[]{Product.BOX, median(opportunityRepository.findOrderedQuantity("BOX"))},
                                             new Object[]{Product.FLATBED, median(opportunityRepository.findOrderedQuantity("FLATBED"))},
                                             new Object[]{Product.HYBRID, median(opportunityRepository.findOrderedQuantity("HYBRID"))});
        return objectsList;
    }
}

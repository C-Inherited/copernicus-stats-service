package com.copernicus.stats.service.impl;

import com.copernicus.stats.enums.Product;
import com.copernicus.stats.repository.AccountRepository;
import com.copernicus.stats.repository.ContactRepository;
import com.copernicus.stats.repository.OpportunityRepository;
import com.copernicus.stats.service.interfaces.IOpportunityService;
import com.copernicus.stats.controller.DTO.OpportunityDTO;
import com.copernicus.stats.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.copernicus.stats.utils.Math.median;

@Service
public class OpportunityService implements IOpportunityService {

    @Autowired
    OpportunityRepository opportunityRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    AccountRepository accountRepository;


    @Override
    public List<OpportunityDTO> countOpportunitiesBySalesRep(Integer salesRepId, Optional<String> status) {
        if (status.isEmpty()){
            return opportunityRepository.getOpportunityBySalesRepId(salesRepId).stream()
                    .map(opportunity -> OpportunityDTO.parseFromOpportunity(opportunity)).collect(Collectors.toList());
        }else{
            return opportunityRepository.getOpportunityBySalesRepIdAndStatus(salesRepId, Status.valueOf(status.get())).stream()
                    .map(opportunity -> OpportunityDTO.parseFromOpportunity(opportunity)).collect(Collectors.toList());
        }
    }

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
        List<Object[]> objectsList = new ArrayList<>();
        Object[] objects = new Object[2];
        for (Product product: Product.values()){
            objects[0] = product.toString();
            objects[1] = opportunityRepository.findOrderedQuantity(product.toString());
            objectsList.add(objects);
        }
        return objectsList;
    }
}

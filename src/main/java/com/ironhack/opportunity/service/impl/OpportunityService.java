package com.ironhack.opportunity.service.impl;

import com.ironhack.opportunity.controller.DTO.OpportunityDTO;
import com.ironhack.opportunity.enums.Product;
import com.ironhack.opportunity.enums.Status;
import com.ironhack.opportunity.model.Opportunity;
import com.ironhack.opportunity.repository.AccountRepository;
import com.ironhack.opportunity.repository.ContactRepository;
import com.ironhack.opportunity.repository.OpportunityRepository;
import com.ironhack.opportunity.service.interfaces.IOpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ironhack.opportunity.utils.Math.median;

@Service
public class OpportunityService implements IOpportunityService {

    @Autowired
    OpportunityRepository opportunityRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    AccountRepository accountRepository;

    public OpportunityDTO getOpportunity(Integer id) {
        Optional<Opportunity> opportunityOptional = opportunityRepository.findById(id);
        if (!opportunityOptional.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Opportunity with ID "+id+" not found");

        OpportunityDTO opportunityDTO = new OpportunityDTO();
        opportunityDTO.setId(opportunityOptional.get().getId());
        opportunityDTO.setProduct(opportunityOptional.get().getProduct().toString());
        opportunityDTO.setQuantity(opportunityOptional.get().getQuantity());
        opportunityDTO.setSalesRepId(opportunityOptional.get().getSalesRepId());
        opportunityDTO.setContactId(opportunityOptional.get().getDecisionMaker().getId());
        opportunityDTO.setAccountId(opportunityOptional.get().getAccount().getId());

        return opportunityDTO;
    }


    public List<OpportunityDTO> getAllOpportunities() {
        List<Opportunity> opportunityList = opportunityRepository.findAll();

        List<OpportunityDTO> opportunityDTOList = new ArrayList<>();

        for (Opportunity opportunity: opportunityList){
            OpportunityDTO opportunityDTO = new OpportunityDTO();
            opportunityDTO.setId(opportunity.getId());
            opportunityDTO.setProduct(opportunity.getProduct().toString());
            opportunityDTO.setQuantity(opportunity.getQuantity());
            opportunityDTO.setSalesRepId(opportunity.getSalesRepId());
            opportunityDTO.setContactId(opportunity.getDecisionMaker().getId());
            opportunityDTO.setAccountId(opportunity.getAccount().getId());
        }

        return opportunityDTOList;
    }


    public OpportunityDTO postOpportunity(OpportunityDTO opportunityDTO) {
        Opportunity opportunity = new Opportunity(Product.valueOf(opportunityDTO.getProduct()),
                                                  opportunityDTO.getQuantity(),
                                                  contactRepository.findById(opportunityDTO.getContactId()).get(),
                                                  opportunityDTO.getSalesRepId());

        opportunity = opportunityRepository.save(opportunity);
        opportunityDTO.setId(opportunity.getId());

        return opportunityDTO;
    }


    public OpportunityDTO putOpportunity(Integer id, OpportunityDTO opportunityDTO) {
        Opportunity opportunity = new Opportunity(Product.valueOf(opportunityDTO.getProduct()),
                opportunityDTO.getQuantity(),
                contactRepository.findById(opportunityDTO.getContactId()).get(),
                opportunityDTO.getSalesRepId());
        opportunity.setId(id);
        opportunityDTO.setId(id);

        opportunityRepository.save(opportunity);

        return opportunityDTO;
    }


    public boolean deleteOpportunity(Integer id) {
        try{
            opportunityRepository.deleteById(id);
        }catch(Exception e){
            return false;
        }
        return true;
    }

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

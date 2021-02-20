package com.ironhack.opportunity.service.impl;

import com.ironhack.opportunity.controller.DTO.OpportunityDTO;
import com.ironhack.opportunity.enums.Product;
import com.ironhack.opportunity.model.Opportunity;
import com.ironhack.opportunity.repository.AccountRepository;
import com.ironhack.opportunity.repository.ContactRepository;
import com.ironhack.opportunity.repository.OpportunityRepository;
import com.ironhack.opportunity.service.interfaces.IOpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        // todo: comprobaciones de que las ids de contact y account existen?
        // puesto que las opportunities se crean junto con su contact con un "convert lead" no tiene demasiado sentido
        // para este, pero la cuenta podría ser una existente y sí que hay que hacer la comprobación
        Opportunity opportunity = new Opportunity(Product.valueOf(opportunityDTO.getProduct()),
                                                  opportunityDTO.getQuantity(),
                                                  contactRepository.findById(opportunityDTO.getContactId()).get(),
                                                  opportunityDTO.getSalesRepId());

        opportunity = opportunityRepository.save(opportunity);
        opportunityDTO.setId(opportunity.getId());

        return opportunityDTO;
    }


    public OpportunityDTO putOpportunity(Integer id, OpportunityDTO opportunityDTO) {
        // todo: comprobaciones de que las ids de contact y account existen?
        // puesto que las opportunities se crean junto con su contact con un "convert lead" no tiene demasiado sentido
        // para este, pero la cuenta podría ser una existente y sí que hay que hacer la comprobación
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
}

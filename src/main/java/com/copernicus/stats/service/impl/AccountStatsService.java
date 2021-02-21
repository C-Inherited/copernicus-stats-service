package com.copernicus.stats.service.impl;

import com.copernicus.stats.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.copernicus.stats.utils.Math.median;

@Service
public class AccountStatsService {

    @Autowired
    private AccountRepository accountRepository;

    public Double avgEmployeeCount(){
        return accountRepository.findMeanEmployeeCount();
    }

    public Double medianEmployeeCount(){
        return median(accountRepository.orderEmployeeCount());
    }

    public Integer minEmployeeCount(){
        return accountRepository.findMinEmployeeCount();
    }

    public Integer maxEmployeeCount(){
        return  accountRepository.findMaxEmployeeCount();
    }
}

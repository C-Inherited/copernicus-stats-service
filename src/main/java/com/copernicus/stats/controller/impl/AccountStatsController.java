package com.copernicus.stats.controller.impl;

import com.copernicus.stats.service.impl.AccountStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountStatsController {

    @Autowired
    private AccountStatsService accountStatsService;

    @GetMapping("/account/avg/employee-count")
    @ResponseStatus(HttpStatus.OK)
    public Double avgEmployeeCount(){
        return accountStatsService.avgEmployeeCount();
    }

    @GetMapping("/account/median/employee-count")
    @ResponseStatus(HttpStatus.OK)
    public Double medianEmployeeCount(){
        return accountStatsService.medianEmployeeCount();
    }

    @GetMapping("/account/min/employee-count")
    @ResponseStatus(HttpStatus.OK)
    public Integer minEmployeeCount(){
        return accountStatsService.minEmployeeCount();
    }

    @GetMapping("/account/max/employee-count")
    @ResponseStatus(HttpStatus.OK)
    public Integer maxEmployeeCount(){
        return  accountStatsService.maxEmployeeCount();
    }
}

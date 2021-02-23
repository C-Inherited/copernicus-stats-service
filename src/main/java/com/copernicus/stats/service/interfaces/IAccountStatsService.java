package com.copernicus.stats.service.interfaces;

public interface IAccountStatsService {
    Double avgEmployeeCount();

    Double medianEmployeeCount();

    Integer minEmployeeCount();

    Integer maxEmployeeCount();
}

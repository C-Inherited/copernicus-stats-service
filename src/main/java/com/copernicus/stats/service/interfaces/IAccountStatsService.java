package com.copernicus.stats.service.interfaces;

import java.util.List;

public interface IAccountStatsService {
    public Double findMeanEmployeeCount();
    public List<Double> orderEmployeeCount();
    public Integer findMinEmployeeCount();
    public Integer findMaxEmployeeCount();
}

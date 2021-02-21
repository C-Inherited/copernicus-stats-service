package com.copernicus.stats.controller.interfaces;

import java.util.List;

public interface IAccountStatsController {

    public Double avgEmployeeCount();

    public Double orderEmployeeCount();

    public Integer minEmployeeCount();

    public Integer maxEmployeeCount();
}

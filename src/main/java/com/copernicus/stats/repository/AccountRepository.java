package com.copernicus.stats.repository;

import com.copernicus.stats.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("SELECT CAST(AVG(a.employeeCount) AS DOUBLE) FROM Account a")
    Double findMeanEmployeeCount();

    @Query(value = "SELECT CAST(employee_count AS DOUBLE) FROM account ORDER BY employee_count", nativeQuery = true)
    List<Double> orderEmployeeCount();

    @Query("SELECT CAST(MIN(a.employeeCount) AS INTEGER) FROM Account a")
    Integer findMinEmployeeCount();

    @Query("SELECT CAST(MAX(a.employeeCount) AS INTEGER) FROM Account a")
    Integer findMaxEmployeeCount();
}

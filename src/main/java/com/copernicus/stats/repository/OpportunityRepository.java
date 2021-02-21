package com.copernicus.stats.repository;

import com.copernicus.stats.enums.Status;
import com.copernicus.stats.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {


    @Query("SELECT o.product, COUNT(*) FROM Opportunity o GROUP BY o.product")
    List<Object[]> findNumberOfOpportunitiesPerProduct();

    @Query("SELECT o.product, COUNT(*) FROM Opportunity o WHERE o.status = :status GROUP BY o.product")
    List<Object[]> findNumberOfOpportunitiesPerProductWithStatus(@Param("status") Status status);

    @Query("SELECT a.city, COUNT(*) FROM Opportunity o JOIN Account a ON a.id = o.account GROUP BY a.city")
    List<Object[]> findNumberOfOpportunitiesPerCity();

    @Query("SELECT a.city, COUNT(*) FROM Opportunity o JOIN Account a ON a.id = o.account WHERE o.status = :status GROUP BY a.city")
    List<Object[]> findNumberOfOpportunitiesPerCityWithStatus(@Param("status") Status status);

    @Query("SELECT a.country, COUNT(*) FROM Opportunity o JOIN Account a ON a.id = o.account GROUP BY a.country")
    List<Object[]> findNumberOfOpportunitiesPerCountry();

    @Query("SELECT a.country, COUNT(*) FROM Opportunity o JOIN Account a ON a.id = o.account WHERE o.status = :status GROUP BY a.country")
    List<Object[]> findNumberOfOpportunitiesPerCountryWithStatus(@Param("status") Status status);

    @Query("SELECT a.industry, COUNT(*) FROM Opportunity o JOIN Account a ON a.id = o.account GROUP BY a.industry")
    List<Object[]> findNumberOfOpportunitiesPerIndustry();

    @Query("SELECT a.industry, COUNT(*) FROM Opportunity o JOIN Account a ON a.id = o.account WHERE o.status = :status GROUP BY a.industry")
    List<Object[]> findNumberOfOpportunitiesPerIndustryWithStatus(@Param("status") Status status);

    @Query(value = "SELECT CAST(AVG(oo.count) AS double) FROM (SELECT COUNT(o.id) AS count FROM `account` a " +
            "LEFT JOIN opportunity o ON a.id = o.account_id GROUP BY a.id) AS oo", nativeQuery = true)
    Double findAvgOpportunitiesByAccountId();

    @Query(value = "SELECT CAST(MAX(oo.count) AS UNSIGNED) FROM (SELECT COUNT(o.id) AS count FROM `account` a " +
            "LEFT JOIN opportunity o ON a.id = o.account_id GROUP BY a.id) AS oo", nativeQuery = true)
    Integer findMaxOpportunitiesByAccountId();

    @Query(value = "SELECT CAST(MIN(oo.count) AS UNSIGNED) FROM (SELECT COUNT(o.id) AS count FROM `account` a " +
            "LEFT JOIN opportunity o ON a.id = o.account_id GROUP BY a.id) AS oo", nativeQuery = true)
    Integer findMinOpportunitiesByAccountId();

    @Query(value = "SELECT CAST(oo.count AS DOUBLE) FROM (SELECT COUNT(o.id) AS count FROM `account` a " +
            "LEFT JOIN opportunity o ON a.id = o.account_id GROUP BY a.id) AS oo ORDER BY count", nativeQuery = true)
    List<Double> findOrderOpportunitiesByAccountId();

//  Quantity statistics grouped by product
    @Query("SELECT product, CAST(AVG(quantity) AS double) FROM Opportunity GROUP BY product")
    List<Object[]> findAvgQuantityGroupByProduct();

    @Query("SELECT product, CAST(MAX(quantity) AS integer) FROM Opportunity GROUP BY product")
    List<Object[]> findMaxQuantityGroupByProduct();

    @Query("SELECT product, CAST(MIN(quantity) AS integer) FROM Opportunity GROUP BY product")
    List<Object[]> findMinQuantityGroupByProduct();

    @Query(value = "SELECT CAST(quantity AS DOUBLE) FROM opportunity WHERE product=:product ORDER BY quantity", nativeQuery = true)
    List<Double> findOrderedQuantity(@Param("product") String product);

}

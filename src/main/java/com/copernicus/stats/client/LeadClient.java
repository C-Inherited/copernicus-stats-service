package com.copernicus.stats.client;

import com.copernicus.stats.dto.LeadDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("leads-service")
public interface LeadClient {
    @GetMapping("/leads/all/{salesRepId}")
    public List<LeadDTO> findAllBySalesRepId(@PathVariable int salesRepId);
}

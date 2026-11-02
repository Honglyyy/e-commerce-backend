package com.ly.orderservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

@FeignClient(value = "inventory", url = "${inventory.url}")
public interface InventoryClient {

    @GetExchange("/api/inventory")
    boolean isInStock(@RequestParam String sku, @RequestParam Integer quantity);
}

package com.miempresa.microserviciosinventary.Controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "catalog-service",
        url  = "${catalog.url}",
        path = "/api/products"
)
public interface ProductClient {

    @GetMapping("/{id}")
    ProductDto getProduct(@PathVariable Long id);

    record ProductDto(Long id, String sku, String name) {}
}

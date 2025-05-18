package com.miempresa.microsercatalogo.repositorios;

import com.miempresa.microsercatalogo.Entidades.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySku (String sku);

    void deleteByName(String sku);

    String sku(String sku);

}

package com.miempresa.microserviciosinventary.Repositorios;

import com.miempresa.microserviciosinventary.Entidades.StockItem;
import com.miempresa.microserviciosinventary.Entidades.StockReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockItemRepository
        extends JpaRepository<StockItem, Long> {

    Optional<StockItem> findByWarehouseIdAndProductId(Long warehouseId, Long productId);
}


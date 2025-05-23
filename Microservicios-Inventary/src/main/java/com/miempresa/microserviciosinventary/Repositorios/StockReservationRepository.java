package com.miempresa.microserviciosinventary.Repositorios;

import com.miempresa.microserviciosinventary.Entidades.StockReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockReservationRepository
        extends JpaRepository<StockReservation, Long> {

    List<StockReservation> findByOrderId(Long orderId);
}

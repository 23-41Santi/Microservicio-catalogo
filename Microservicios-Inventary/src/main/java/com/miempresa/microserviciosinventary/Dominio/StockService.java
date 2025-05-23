package com.miempresa.microserviciosinventary.Dominio;

import com.miempresa.microserviciosinventary.Controladores.ProductClient;
import com.miempresa.microserviciosinventary.Entidades.StockReservation;
import com.miempresa.microserviciosinventary.Repositorios.StockItemRepository;
import com.miempresa.microserviciosinventary.Repositorios.StockReservationRepository;
import com.miempresa.microserviciosinventary.enums.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockItemRepository stockRepo;
    private final StockReservationRepository reservationRepo;
    private final ProductClient productClient;   // para validar producto

    @Transactional
    public ReserveResult reserveStock(Long orderId, List<Line> lines) {

        List<Long> missing = new ArrayList<>();

        lines.forEach(line -> {
            // Valida que el producto exista en Catalog; lanza excepción 404 si no
            productClient.getProduct(line.productId());

            stockRepo.findByWarehouseIdAndProductId(1L, line.productId())      // único warehouse demo
                    .filter(item -> item.getAvailable() >= line.quantity())
                    .ifPresentOrElse(item -> {
                        item.setReserved(item.getReserved() + line.quantity());
                        reservationRepo.save(
                                StockReservation.builder()
                                        .orderId(orderId)
                                        .stockItem(item)
                                        .quantity(line.quantity())
                                        .status(ReservationStatus.RESERVED)
                                        .build());
                    }, () -> missing.add(line.productId()));
        });

        return missing.isEmpty()
                ? ReserveResult.success()
                : ReserveResult.failure(missing);
    }

    public record Line(Long productId, Long quantity) {}
    public record ReserveResult(boolean fulfilled, List<Long> missing) {
        static ReserveResult success() { return new ReserveResult(true, List.of()); }
        static ReserveResult failure(List<Long> missing) { return new ReserveResult(false, missing); }
    }
}

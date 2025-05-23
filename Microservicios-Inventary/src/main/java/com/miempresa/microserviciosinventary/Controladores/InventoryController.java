package com.miempresa.microserviciosinventary.Controladores;

import com.miempresa.microserviciosinventary.Dominio.StockService;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final StockService stockService;

    /* ------------ RESERVAR ------------ */
    @PostMapping("/reserve")
    public ResponseEntity<?> reserve(@Valid @RequestBody ReserveRequest req) {
        var result = stockService.reserveStock(req.orderId(), req.lines());
        return result.fulfilled()
                ? ResponseEntity.ok().build()                // 200: todo bien
                : ResponseEntity.status(409).body(result);   // 409: faltantes
    }

    /* ------------ LIBERAR ------------ */
    @PostMapping("/release/{orderId}")
    public ResponseEntity<Void> release(@PathVariable Long orderId) {
        // cambia status a RELEASED y disminuye reserved (implementación análoga)
        // ...
        return ResponseEntity.ok().build();
    }

    /* ------------ DTOs ------------ */
    public record ReserveRequest(
            @NotNull Long orderId,
            @Size(min = 1) List<Line> lines) {
        public record Line(@NotNull Long productId,
                           @Positive Long quantity) {}
    }
}
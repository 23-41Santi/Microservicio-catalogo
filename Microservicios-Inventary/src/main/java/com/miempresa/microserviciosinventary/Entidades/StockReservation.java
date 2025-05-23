package com.miempresa.microserviciosinventary.Entidades;

//Entidad Reserva de Stock

import com.miempresa.microserviciosinventary.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "stock_reservations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class StockReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long orderId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private StockItem stockItem;

    @Column(nullable = false)
    private Long quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ReservationStatus status;

    private OffsetDateTime createdAt;
    private OffsetDateTime releasedAt;

}
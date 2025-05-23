package com.miempresa.microserviciosinventary.Entidades;

//Entidad Ajuste de Stock

import com.miempresa.microserviciosinventary.enums.Reason;
import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "stock_adjustments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class StockAdjustment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private StockItem stockItem;

    @Column(nullable = false)
    private Long delta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Reason reason;

    private String comment;
    private OffsetDateTime createdAt;

}
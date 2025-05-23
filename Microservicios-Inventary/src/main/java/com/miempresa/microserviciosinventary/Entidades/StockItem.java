package com.miempresa.microserviciosinventary.Entidades;

//Entidad Ítem de Stock

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "stock_items", uniqueConstraints = @UniqueConstraint(columnNames = {"warehouse_id", "product_id"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class StockItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Warehouse warehouse;

    @Column(nullable = false)
    private Long onHand;

    @Column(nullable = false)
    private Long reserved;

    @CreatedDate
    private OffsetDateTime createdAt;

    @LastModifiedDate
    private OffsetDateTime updatedAt;

    public long getAvailable() {
        return onHand - reserved;
    }
}
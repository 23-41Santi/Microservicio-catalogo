package com.miempresa.microserviciosinventary.Entidades;

//Entidad Almacén

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "warehouses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    private String location;
}
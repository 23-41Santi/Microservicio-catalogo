package com.miempresa.microsercatalogo.Entidades;

import com.miempresa.microsercatalogo.Data.CategoryNames;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false, length = 60, unique = true)
    private String sku;

    @Column(nullable = false, length = 150)
    private String name;

    @Column()
    private String description;

    @Enumerated(EnumType.STRING)   // Guarda el “name” del enum
    private CategoryNames category;

    public Product(String sku, String name, String description, CategoryNames category) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.category = category;
    }
}




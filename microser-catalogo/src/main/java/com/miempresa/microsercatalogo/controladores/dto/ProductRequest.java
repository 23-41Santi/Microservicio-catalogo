package com.miempresa.microsercatalogo.controladores.dto;

import com.miempresa.microsercatalogo.Data.CategoryNames;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    private String sku;
    private String name;
    private String description;
    private CategoryNames category;

    public void setProductId(Long id) {
    }
}

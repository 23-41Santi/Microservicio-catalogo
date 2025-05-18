package com.miempresa.microsercatalogo.controladores.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class ProductRasponse {
    private String sku;
    private String name;
    private String description;
    private String category;
    private String messagge;
}

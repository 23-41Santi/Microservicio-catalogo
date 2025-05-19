package com.miempresa.microsercatalogo.servicios;

import aj.org.objectweb.asm.commons.Remapper;
import com.miempresa.microsercatalogo.Entidades.Product;
import com.miempresa.microsercatalogo.controladores.dto.ProductRasponse;
import com.miempresa.microsercatalogo.controladores.dto.ProductRequest;
import com.miempresa.microsercatalogo.repositorios.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Optional<Product> findBySku(String sku) {
        return productRepo.findBySku(sku);
    }

    public String save(ProductRequest product) {
        Product newProduct = new Product(product.getSku(), product.getName(), product.getDescription(), product.getCategory());
        newProduct = productRepo.save(newProduct);
        if (newProduct != null) {
            return "Producto agregado con exito";
        } else {
            return "Error al agregar el producto";
        }
    }

    public Optional<ProductRasponse> update(ProductRequest productRequest, Product producto) {
      producto.setName(productRequest.getName());
      producto.setDescription(productRequest.getDescription());
        Product updateProduct= productRepo.save(producto);
        ProductRasponse productRasponse =  ProductRasponse.builder()
                .name(updateProduct.getName())
                .description(updateProduct.getDescription())
                .sku(updateProduct.getSku())
                .category(updateProduct.getCategory().name())
                .build();


        if (productRasponse != null) {
            productRasponse.setMessagge("Producto actualizado con exito");
        }
        else {
            productRasponse.setMessagge("Producto no actualizado");
        }
        return Optional.of(productRasponse);
    }
    @Transactional
    public void delete(String sku) {
        productRepo.deleteBySku(sku);
    }



}

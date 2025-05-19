package com.miempresa.microsercatalogo.controladores;

import com.miempresa.microsercatalogo.Entidades.Product;
import com.miempresa.microsercatalogo.controladores.dto.ProductRasponse;
import com.miempresa.microsercatalogo.controladores.dto.ProductRequest;
import com.miempresa.microsercatalogo.servicios.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{sku}")
    public ResponseEntity<Product> getProductBySku(@PathVariable String sku) {
        return productService.findBySku(sku)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public String createProduct(@RequestBody ProductRequest product) {
        return productService.save(product);
    }

    @PutMapping("/{sku}")
    public ResponseEntity<Optional<ProductRasponse>> updateProduct(@PathVariable String sku, @RequestBody ProductRequest updatedProduct) {
        return productService.findBySku(sku)
                .map(existing -> {
                    return ResponseEntity.ok(productService.update(updatedProduct, existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String sku) {
        if (productService.findBySku(sku).isPresent()) {
            productService.delete(sku);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }



}



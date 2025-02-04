package com.microcore.springcloud.msvc.products.controllers;

import com.microcore.springcloud.msvc.products.entities.Product;
import com.microcore.springcloud.msvc.products.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
public class ProductController {

    final ProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> list() { //In the response entity we can put ? to return any type of object
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> details(@PathVariable Long id) throws InterruptedException {
        if(id.equals(10L)){
            throw new IllegalStateException("Error to get the product information");
        }
        if(id.equals(7L)){
            TimeUnit.SECONDS.sleep(5L);
        }
        Optional<Product> productOptional = productService.findById(id);

        if(productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }else{
            return ResponseEntity.notFound().build();
        }


    }

}

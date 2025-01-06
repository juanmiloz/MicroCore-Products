package com.microcore.springcloud.msvc.products.repositories;

import com.microcore.springcloud.msvc.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

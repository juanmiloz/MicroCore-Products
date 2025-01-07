package com.microcore.springcloud.msvc.products.services;

import com.microcore.springcloud.msvc.products.entities.Product;
import com.microcore.springcloud.msvc.products.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    final private ProductRepository productRepository;
    final private Environment environment;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        int port = Integer.parseInt(environment.getProperty("local.server.port"));

        return productRepository.findAll().stream().map(product -> {
            product.setPort(port);
            return product;
        }).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id).map(product -> {
            product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return product;
        });
    }
}

package br.com.msf.springboot.api.application.services;

import br.com.msf.springboot.api.application.usecases.ProductUseCase;
import br.com.msf.springboot.api.domain.product.Product;
import br.com.msf.springboot.api.domain.product.ProductRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductUseCase {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public List<Product> getProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> getProduct(UUID id) {
        return this.productRepository.findById(id);
    }

    @Override
    public void delete(Product product) {
        this.productRepository.delete(product);
    }
}
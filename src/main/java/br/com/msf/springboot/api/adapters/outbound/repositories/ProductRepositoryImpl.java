package br.com.msf.springboot.api.adapters.outbound.repositories;

import br.com.msf.springboot.api.adapters.outbound.entities.ProductEntity;
import br.com.msf.springboot.api.domain.product.Product;
import br.com.msf.springboot.api.domain.product.ProductRepository;
import br.com.msf.springboot.api.utils.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private JpaProductRepository jpaProductRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = productMapper.toEntity(product);
        return productMapper.toDomain(this.jpaProductRepository.save(productEntity));
    }

    @Override
    public List<Product> findAll(Pageable pageable) {
        return this.jpaProductRepository.findAll(pageable)
                .stream()
                .map(entity -> productMapper.toDomain(entity))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(UUID id) {
        var productEntity = this.jpaProductRepository.findById(id);
        return productEntity.map(productMapper::toDomain);
    }

    @Override
    public void delete(Product product) {
        this.jpaProductRepository.delete(productMapper.toEntity(product));
    }
}
package br.com.msf.springboot.api.application.usecases;

import br.com.msf.springboot.api.domain.product.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductUseCase {

    Product save(Product product);

    List<Product> getProducts(Pageable pageable);

    Optional<Product> getProduct(UUID id);

    void delete(Product product);
}
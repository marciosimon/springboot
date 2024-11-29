package br.com.msf.springboot.api.domain.product;

import br.com.msf.springboot.api.adapters.outbound.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    Product save(Product product);

    List<Product> findAll(Pageable pageable);

    Optional<Product> findById(UUID id);

    void delete(Product product);
}

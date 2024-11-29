package br.com.msf.springboot.api.adapters.outbound.repositories;

import br.com.msf.springboot.api.adapters.outbound.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, UUID> {
}

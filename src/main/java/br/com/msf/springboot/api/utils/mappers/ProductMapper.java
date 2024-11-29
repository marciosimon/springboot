package br.com.msf.springboot.api.utils.mappers;

import br.com.msf.springboot.api.adapters.outbound.entities.ProductEntity;
import br.com.msf.springboot.api.domain.product.Product;
import br.com.msf.springboot.dtos.ProductRecordDto;

public class ProductMapper {

    public Product toDomain(ProductEntity productEntity) {
        return new Product(productEntity.getId(), productEntity.getNome(), productEntity.getValor(), productEntity.getRegistrationDate());
    }

    public ProductEntity toEntity(Product product) {
        return new ProductEntity(product.getId(), product.getNome(), product.getValor(), product.getRegistrationDate());
    }

    public Product toDomain(ProductRecordDto productRecord) {
        return new Product(null, productRecord.nome(), productRecord.valor(), null);
    }

}
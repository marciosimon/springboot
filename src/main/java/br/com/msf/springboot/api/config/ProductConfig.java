package br.com.msf.springboot.api.config;


import br.com.msf.springboot.api.adapters.outbound.repositories.ProductRepositoryImpl;
import br.com.msf.springboot.api.application.services.ProductServiceImpl;
import br.com.msf.springboot.api.application.usecases.ProductUseCase;
import br.com.msf.springboot.api.domain.product.ProductRepository;
import br.com.msf.springboot.api.utils.mappers.ProductMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    ProductUseCase productUseCase(ProductRepository productRepository) {
        return new ProductServiceImpl(productRepository);
    }

    @Bean
    ProductMapper productMapper() {
        return new ProductMapper();
    }

    @Bean
    ProductRepository productRepository() {
        return new ProductRepositoryImpl();
    }

}
package br.com.msf.springboot.api.adapters.inbound.controllers;

import br.com.msf.springboot.api.application.usecases.ProductUseCase;
import br.com.msf.springboot.api.utils.mappers.ProductMapper;
import br.com.msf.springboot.dtos.ProductRecordDto;
import br.com.msf.springboot.api.domain.product.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductUseCase productUseCase;
    private final ProductMapper productMapper;

    public ProductController(ProductUseCase productUseCase, ProductMapper productMapper) {
        this.productUseCase = productUseCase;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody @Valid ProductRecordDto productRecordDto) {
        var product = productMapper.toDomain(productRecordDto);
        product.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(productUseCase.save(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        List<Product> products = productUseCase.getProducts(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
        Optional<Product> product = productUseCase.getProduct(id);

        if(product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable(value = "id") UUID id,
            @RequestBody @Valid ProductRecordDto productRecordDto) {
        Optional<Product> product = productUseCase.getProduct(id);

        if(product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
        }

        var productModel = productMapper.toDomain(productRecordDto);
        productModel.setId(product.get().getId());
        productModel.setRegistrationDate(product.get().getRegistrationDate());

        return ResponseEntity.status(HttpStatus.OK).body(productUseCase.save(productModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable(value = "id") UUID id) {
        Optional<Product> product = productUseCase.getProduct(id);

        if(product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
        }

        productUseCase.delete(product.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted sucessfully");
    }

}
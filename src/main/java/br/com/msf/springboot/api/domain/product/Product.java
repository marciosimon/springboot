package br.com.msf.springboot.api.domain.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Product {
   private UUID id;
   private String nome;
   private BigDecimal valor;
   private LocalDateTime registrationDate;

    public Product(UUID id, String nome, BigDecimal valor, LocalDateTime registrationDate) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.registrationDate = registrationDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
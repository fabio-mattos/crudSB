package com.fabiomattos.crudSB.dto;

import com.fabiomattos.crudSB.entities.Customers;
import org.springframework.beans.BeanUtils;

public class CustomersDTO {
    private Long id;
    private String nome;

    public CustomersDTO() {

    }
    public CustomersDTO(Customers entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

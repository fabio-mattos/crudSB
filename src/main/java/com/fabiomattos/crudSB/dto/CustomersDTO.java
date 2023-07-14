package com.fabiomattos.crudSB.dto;

import com.fabiomattos.crudSB.entities.Customers;
import org.springframework.beans.BeanUtils;

public class CustomersDTO {
    private Long id;
    private String name;

    public CustomersDTO() {

    }

    public CustomersDTO(Customers entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}

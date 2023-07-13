package com.fabiomattos.crudSB.dto;

import com.fabiomattos.crudSB.entities.Pessoa;
import org.springframework.beans.BeanUtils;

public class PessoaDTO {
    private Long id;
    private String nome;

    public PessoaDTO() {

    }
    public PessoaDTO(Pessoa entity) {
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

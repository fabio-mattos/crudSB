package com.fabiomattos.crudSB.controller;

import com.fabiomattos.crudSB.dto.PessoaDTO;
import com.fabiomattos.crudSB.entities.Pessoa;
import com.fabiomattos.crudSB.repository.PessoaRepository;
import com.fabiomattos.crudSB.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @RequestMapping
    public List<PessoaDTO> findAll(){
        List<PessoaDTO> result = pessoaService.findAll();
        return result;
    }

    @GetMapping(value = "/{id}")
    public PessoaDTO findById(@PathVariable Long id) {
        PessoaDTO result = pessoaService.findById(id);
        return result;

    }

}

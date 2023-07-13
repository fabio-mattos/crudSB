package com.fabiomattos.crudSB.service;

import com.fabiomattos.crudSB.dto.PessoaDTO;
import com.fabiomattos.crudSB.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional(readOnly = true)
    public PessoaDTO findById(Long id) {
        try {
             return new PessoaDTO(pessoaRepository.findById(id).get());
         } catch (NoSuchElementException e) {
            throw new RuntimeException("ID não encontrado: " + id);
         }
    }

    @Transactional(readOnly = true)
    public List<PessoaDTO> findAll() {
        var result = pessoaRepository.findAll();
        return result.stream().map(x -> new PessoaDTO(x)).toList();
    }

}

package com.fabiomattos.crudSB.service;

import com.fabiomattos.crudSB.dto.CustomersDTO;
import com.fabiomattos.crudSB.repository.CustomersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomersService {

    @Autowired
    private CustomersRepository pessoaRepository;

    @Transactional(readOnly = true)
    public CustomersDTO findById(Long id) {
        try {
             return new CustomersDTO(pessoaRepository.findById(id).get());
         } catch (NoSuchElementException e) {
            throw new RuntimeException("ID não encontrado: " + id);
         }
    }

    @Transactional(readOnly = true)
    public List<CustomersDTO> findAll() {
        var result = pessoaRepository.findAll();
        return result.stream().map(x -> new CustomersDTO(x)).toList();
    }

}

package com.fabiomattos.crudSB.service;

import com.fabiomattos.crudSB.dto.CustomersDTO;
import com.fabiomattos.crudSB.entities.Customers;
import com.fabiomattos.crudSB.repository.CustomersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomersService {

    @Autowired
    private CustomersRepository customersRepository;

    @Transactional(readOnly = true)
    public CustomersDTO findById(Long id) {
        try {
             return new CustomersDTO(customersRepository.findById(id).get());
         } catch (NoSuchElementException e) {
            throw new RuntimeException("ID não encontrado: " + id);
         }
    }

    @Transactional(readOnly = true)
    public List<CustomersDTO> findAll() {
        var result = customersRepository.findAll();
        return result.stream().map(x -> new CustomersDTO(x)).toList();
    }

    public ResponseEntity<Customers> update(@PathVariable(value = "id") long id, @Validated @RequestBody Customers newPessoa)
    {
        Optional<Customers> oldPessoa = customersRepository.findById(id);
        if(oldPessoa.isPresent()){
            Customers pessoa = oldPessoa.get();
            pessoa.setName(newPessoa.getName());
            customersRepository.save(pessoa);
            return new ResponseEntity<Customers>(pessoa, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    public ResponseEntity<Object> delete(@PathVariable(value = "id") long id) {
        Optional<Customers> customers = customersRepository.findById(id);
        if(customers.isPresent()){
            customersRepository.delete(customers.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

package com.fabiomattos.crudSB.controller;

import com.fabiomattos.crudSB.dto.CustomersDTO;
import com.fabiomattos.crudSB.entities.Customers;
import com.fabiomattos.crudSB.repository.CustomersRepository;
import com.fabiomattos.crudSB.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomersController {
    @Autowired
    private CustomersService pessoaService;

    @Autowired
    private CustomersRepository pessoaRepository;

    @RequestMapping
    public List<CustomersDTO> findAll(){
        List<CustomersDTO> result = pessoaService.findAll();
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CustomersDTO findById(@PathVariable Long id) {
        CustomersDTO result = pessoaService.findById(id);
        return result;

    }

    @PostMapping
    public Customers Post(@Validated @RequestBody Customers pessoa)
    {
        return pessoaRepository.save(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customers> Put(@PathVariable(value = "id") long id, @Validated @RequestBody Customers newPessoa)
    {
        Optional<Customers> oldPessoa = pessoaRepository.findById(id);
        if(oldPessoa.isPresent()){
            Customers pessoa = oldPessoa.get();
            pessoa.setName(newPessoa.getName());
            pessoaRepository.save(pessoa);
            return new ResponseEntity<Customers>(pessoa, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Customers> pessoa = pessoaRepository.findById(id);
        if(pessoa.isPresent()){
            pessoaRepository.delete(pessoa.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

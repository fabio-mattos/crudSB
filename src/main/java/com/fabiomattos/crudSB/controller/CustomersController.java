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
    private CustomersService customersService;

    @Autowired
    private CustomersRepository customersRepository;

    @RequestMapping
    public List<CustomersDTO> findAll(){
        List<CustomersDTO> result = customersService.findAll();
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CustomersDTO findById(@PathVariable Long id) {
        CustomersDTO result = customersService.findById(id);
        return result;
    }

    @PostMapping
    public Customers Post(@Validated @RequestBody Customers pessoa)
    {
        return customersRepository.save(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customers> Put(@PathVariable(value = "id") long id, @Validated @RequestBody Customers newCustomers)
    {
              return customersService.update(id, newCustomers);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
              return customersService.delete(id);
    }

}

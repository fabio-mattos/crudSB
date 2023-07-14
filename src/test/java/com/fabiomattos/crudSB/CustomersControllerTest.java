package com.fabiomattos.crudSB;

import com.fabiomattos.crudSB.controller.CustomersController;
import com.fabiomattos.crudSB.dto.CustomersDTO;
import com.fabiomattos.crudSB.entities.Customers;
import com.fabiomattos.crudSB.repository.CustomersRepository;
import com.fabiomattos.crudSB.service.CustomersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomersControllerTest {

    @Mock
    private CustomersService customersService;

    @Mock
    private CustomersRepository customersRepository;

    @InjectMocks
    private CustomersController customersController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        // Mock data
        List<CustomersDTO> mockResult = new ArrayList<>();
        when(customersService.findAll()).thenReturn(mockResult);

        // Test
        List<CustomersDTO> result = customersController.findAll();

        // Verify
        assertEquals(mockResult, result);
        verify(customersService, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        // Mock data
        long customerId = 1L;
        CustomersDTO mockResult = new CustomersDTO();
        when(customersService.findById(customerId)).thenReturn(mockResult);

        // Test
        CustomersDTO result = customersController.findById(customerId);

        // Verify
        assertEquals(mockResult, result);
        verify(customersService, times(1)).findById(customerId);
    }

    @Test
    public void testPost() {
        // Mock data
        Customers mockCustomer = new Customers();
        when(customersRepository.save(mockCustomer)).thenReturn(mockCustomer);

        // Test
        Customers result = customersController.Post(mockCustomer);

        // Verify
        assertEquals(mockCustomer, result);
        verify(customersRepository, times(1)).save(mockCustomer);
    }

    @Test
    public void testPut() {
        // Mock data
        long customerId = 1L;
        Customers newCustomers = new Customers();
        ResponseEntity<Customers> mockResponse = new ResponseEntity<>(HttpStatus.OK);
        when(customersService.update(customerId, newCustomers)).thenReturn(mockResponse);

        // Test
        ResponseEntity<Customers> result = customersController.Put(customerId, newCustomers);

        // Verify
        assertEquals(mockResponse, result);
        verify(customersService, times(1)).update(customerId, newCustomers);
    }

    @Test
    public void testDelete() {
        // Mock data
        long customerId = 1L;
        ResponseEntity<Object> mockResponse = new ResponseEntity<>(HttpStatus.OK);
        when(customersService.delete(customerId)).thenReturn(mockResponse);

        // Test
        ResponseEntity<Object> result = customersController.Delete(customerId);

        // Verify
        assertEquals(mockResponse, result);
        verify(customersService, times(1)).delete(customerId);
    }
}
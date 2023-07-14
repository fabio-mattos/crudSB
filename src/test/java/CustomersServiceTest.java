import com.fabiomattos.crudSB.dto.CustomersDTO;
import com.fabiomattos.crudSB.entities.Customers;
import com.fabiomattos.crudSB.repository.CustomersRepository;
import com.fabiomattos.crudSB.service.CustomersService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomersServiceTest {

    @Mock
    private CustomersRepository customersRepository;

    @InjectMocks
    private CustomersService customersService;

    public CustomersServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_ExistingId_ReturnsCustomersDTO() {
        Long customerId = 1L;
        Customers customer = new Customers();
        customer.setId(customerId);
        customer.setName("Teste 01");
        when(customersRepository.findById(customerId)).thenReturn(Optional.of(customer));

        CustomersDTO result = customersService.findById(customerId);

        assertEquals(customerId, result.getId());
        assertEquals("Teste 01", result.getName());
    }

    @Test
    void findById_NonExistingId_ThrowsException() {
        Long customerId = 1L;
        when(customersRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> customersService.findById(customerId));
    }

    @Test
    void findAll_ReturnsListOfCustomersDTO() {
        // Arrange
        List<Customers> customersList = new ArrayList<>();
        Customers customer1 = new Customers();
        customer1.setId(1L);
        customer1.setName("Nome Teste 01");
        Customers customer2 = new Customers();
        customer2.setId(2L);
        customer2.setName("Nome Teste 02");
        customersList.add(customer1);
        customersList.add(customer2);
        when(customersRepository.findAll()).thenReturn(customersList);

        // Act
        List<CustomersDTO> result = customersService.findAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Nome Teste 01", result.get(0).getName());
        assertEquals(2L, result.get(1).getId());
        assertEquals("Nome Teste 02", result.get(1).getName());
    }

    @Test
    void update_ExistingId_ReturnsUpdatedCustomers() {
        // Arrange
        long customerId = 1L;
        Customers existingCustomer = new Customers();
        existingCustomer.setId(customerId);
        existingCustomer.setName("Nome Teste 01");
        Customers newCustomer = new Customers();
        newCustomer.setName("Nome Teste 02");
        when(customersRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(customersRepository.save(existingCustomer)).thenReturn(existingCustomer);

        // Act
        ResponseEntity<Customers> result = customersService.update(customerId, newCustomer);

        // Assert
        assertEquals(HttpStatusCode.valueOf(200), result.getStatusCode());
        assertEquals(customerId, result.getBody().getId());
        assertEquals("Nome Teste 02", result.getBody().getName());
    }

    @Test
    void update_NonExistingId_ReturnsNotFoundStatus() {
        // Arrange
        long customerId = 1L;
        Customers newCustomer = new Customers();
        newCustomer.setName("Nome Teste 01");
        when(customersRepository.findById(customerId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Customers> result = customersService.update(customerId, newCustomer);

        // Assert
        assertEquals(HttpStatusCode.valueOf(404), result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    void delete_ExistingId_ReturnsOkStatus() {
        // Arrange
        long customerId = 1L;
        Customers existingCustomer = new Customers();
        existingCustomer.setId(customerId);
        existingCustomer.setName("Nome Teste 01");
        when(customersRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));

        // Act
        ResponseEntity<Object> result = customersService.delete(customerId);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void delete_NonExistingId_ReturnsNotFoundStatus() {
        // Arrange
        long customerId = 1L;
        when(customersRepository.findById(customerId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Object> result = customersService.delete(customerId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}
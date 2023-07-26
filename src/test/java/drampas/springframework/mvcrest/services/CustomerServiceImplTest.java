package drampas.springframework.mvcrest.services;

import drampas.springframework.mvcrest.api.mapper.CustomerMapper;
import drampas.springframework.mvcrest.api.model.CustomerDTO;
import drampas.springframework.mvcrest.domain.Customer;
import drampas.springframework.mvcrest.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {
    @Mock
    CustomerRepository customerRepository;
    CustomerService customerService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService=new CustomerServiceImpl(customerRepository,CustomerMapper.INSTANCE);
    }

    @Test
    void getCustomers() {
        List<Customer> customers= Arrays.asList(new Customer(),new Customer(),new Customer());
        Mockito.when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOS=customerService.getCustomers();
        assertEquals(3,customerDTOS.size());
    }

    @Test
    void getCustomerById() {
        Customer customer=new Customer(1L,"John","Doe");
        Mockito.when(customerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO=customerService.getCustomerById(1L);
        assertEquals(1L,customerDTO.getId());
        assertEquals("John",customerDTO.getFirstName());
        assertEquals("Doe",customerDTO.getLastName());
    }

    @Test
    void createNewCustomer() {
        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setFirstName("John");
        customerDTO.setLastName("Doe");

        Customer savedCustomer=new Customer(1L,customerDTO.getFirstName(),customerDTO.getLastName());
        Mockito.when(customerRepository.save(ArgumentMatchers.any(Customer.class))).thenReturn(savedCustomer);

        CustomerDTO savedDTO=customerService.createNewCustomer(customerDTO);

        assertEquals(customerDTO.getFirstName(),savedDTO.getFirstName());
        assertEquals(customerDTO.getLastName(),savedDTO.getLastName());
        assertEquals(1L,savedDTO.getId());
        assertEquals("/api/customers/1",savedDTO.getCustomerUri());
    }
}
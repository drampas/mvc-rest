package drampas.springframework.mvcrest.api.mapper;

import drampas.springframework.mvcrest.api.model.CustomerDTO;
import drampas.springframework.mvcrest.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {
    CustomerMapper customerMapper=CustomerMapper.INSTANCE;

    @Test
    void customerToCustomerDTO() {
        Customer customer=new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");

        CustomerDTO customerDTO=customerMapper.customerToCustomerDTO(customer);

        assertEquals(1L,customerDTO.getId());
        assertEquals("John",customerDTO.getFirstName());
        assertEquals("Doe",customerDTO.getLastName());
    }
}
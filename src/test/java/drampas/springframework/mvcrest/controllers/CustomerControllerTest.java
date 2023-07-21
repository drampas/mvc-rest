package drampas.springframework.mvcrest.controllers;

import drampas.springframework.mvcrest.api.model.CustomerDTO;
import drampas.springframework.mvcrest.services.CustomerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.junit.jupiter.api.Assertions.*;

class CustomerControllerTest {
    @Mock
    CustomerService customerService;
    @InjectMocks
    CustomerController customerController;
    MockMvc mvc;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mvc=MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void getAllCustomers() throws Exception {
        List<CustomerDTO> customerDTOS= Arrays.asList(new CustomerDTO(),new CustomerDTO(),new CustomerDTO());
        Mockito.when(customerService.getCustomers()).thenReturn(customerDTOS);
        mvc.perform(get("/api/customers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", Matchers.hasSize(3)));
    }

    @Test
    void getCustomerByLastName() throws Exception {
        CustomerDTO customerDTO=new CustomerDTO(1L,"John","Doe","/api/customers/1");
        Mockito.when(customerService.getCustomerById(ArgumentMatchers.anyLong())).thenReturn(customerDTO);
        mvc.perform(get("/api/customers/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",Matchers.equalTo(1)))
                .andExpect(jsonPath("$.lastName",Matchers.equalTo("Doe")));
    }
}
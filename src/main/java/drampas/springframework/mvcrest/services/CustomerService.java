package drampas.springframework.mvcrest.services;

import drampas.springframework.mvcrest.api.model.CustomerDTO;

import java.util.List;

public interface CustomerService {
    public List<CustomerDTO> getCustomers();
    public CustomerDTO getCustomerById(Long id);
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO);

}

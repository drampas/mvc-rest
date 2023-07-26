package drampas.springframework.mvcrest.services;

import drampas.springframework.mvcrest.api.model.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getCustomers();
    CustomerDTO getCustomerById(Long id);
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(Long id,CustomerDTO customerDTO);
    CustomerDTO patchCustomer(Long id,CustomerDTO customerDTO);
    void deleteCustomerById(Long id);
}

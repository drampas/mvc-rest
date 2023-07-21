package drampas.springframework.mvcrest.controllers;

import drampas.springframework.mvcrest.api.model.CustomerDTO;
import drampas.springframework.mvcrest.api.model.CustomerListDTO;
import drampas.springframework.mvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers(){
        CustomerListDTO customers=new CustomerListDTO(customerService.getCustomers());
        return new ResponseEntity<CustomerListDTO>(customers, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerByLastName(@PathVariable String id){
        CustomerDTO customer=customerService.getCustomerById(Long.valueOf(id));
        return new ResponseEntity<CustomerDTO>(customer, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO){
        CustomerDTO returnCustomer=customerService.createNewCustomer(customerDTO);
        return new ResponseEntity<CustomerDTO>(returnCustomer,HttpStatus.CREATED);
    }
}

package drampas.springframework.mvcrest.services;

import drampas.springframework.mvcrest.api.mapper.CustomerMapper;
import drampas.springframework.mvcrest.api.model.CustomerDTO;
import drampas.springframework.mvcrest.domain.Customer;
import drampas.springframework.mvcrest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> {
                    CustomerDTO customerDTO=customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUri("/api/customers/"+customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> optionalCustomer=customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
            CustomerDTO customerDTO=customerMapper.customerToCustomerDTO(optionalCustomer.get());
            customerDTO.setCustomerUri("/api/customers/"+customerDTO.getId());
            return customerDTO;
        }else {
            throw new RuntimeException("Customer not found,id: " + Long.valueOf(id));
        }
    }
    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO){
        Customer customer=customerMapper.customerDTOToCustomer(customerDTO);
        Customer savedCustomer=customerRepository.save(customer);
        CustomerDTO returnCustomer=customerMapper.customerToCustomerDTO(savedCustomer);
        returnCustomer.setCustomerUri("api/customers/"+savedCustomer.getId());
        return returnCustomer;
    }
    @Override
    public CustomerDTO updateCustomer(Long id,CustomerDTO customerDTO){
        Customer customer=customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);
        Customer savedCustomer=customerRepository.save(customer);
        CustomerDTO returnCustomer=customerMapper.customerToCustomerDTO(savedCustomer);
        returnCustomer.setCustomerUri("api/customers/"+savedCustomer.getId());
        return returnCustomer;
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        Optional<Customer> customerOptional=customerRepository.findById(id);
        if(customerOptional.isPresent()){
            Customer customer=customerOptional.get();
            if(customerDTO.getFirstName()!=null){
                customer.setFirstName(customerDTO.getFirstName());
            }
            if(customerDTO.getLastName()!=null){
                customer.setLastName(customerDTO.getLastName());
            }
            CustomerDTO returnCustomer=customerMapper.customerToCustomerDTO(customerRepository.save(customer));
            returnCustomer.setCustomerUri("/api/customer/" + id);
            return returnCustomer;
        }else {
            throw new RuntimeException("Customer not found,id:"+id);
            //todo handle the exception
        }
    }
    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}

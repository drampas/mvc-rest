package drampas.springframework.mvcrest.api.mapper;

import drampas.springframework.mvcrest.api.model.CustomerDTO;
import drampas.springframework.mvcrest.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE= Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);
}

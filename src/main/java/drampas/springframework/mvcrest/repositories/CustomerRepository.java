package drampas.springframework.mvcrest.repositories;

import drampas.springframework.mvcrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}

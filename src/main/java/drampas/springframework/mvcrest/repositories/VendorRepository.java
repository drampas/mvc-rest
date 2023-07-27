package drampas.springframework.mvcrest.repositories;

import drampas.springframework.mvcrest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor,Long> {
}

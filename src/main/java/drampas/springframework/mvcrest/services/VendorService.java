package drampas.springframework.mvcrest.services;

import drampas.springframework.mvcrest.api.model.VendorDTO;
import drampas.springframework.mvcrest.api.model.VendorListDTO;

import java.util.List;

public interface VendorService {
    List<VendorDTO> getAllVendors();
    VendorDTO getVendorById(Long id);
    VendorDTO createNewVendor(VendorDTO vendorDTO);
    VendorDTO updateVendor(VendorDTO vendorDTO,Long id);

    VendorDTO patchVendor(VendorDTO vendorDTO, Long id);

    void deleteVendorById(Long id);
}

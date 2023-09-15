package drampas.springframework.mvcrest.api.mapper;

import drampas.springframework.mvcrest.api.model.VendorDTO;
import drampas.springframework.mvcrest.domain.Vendor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendorMapperTest {
    VendorMapper vendorMapper=VendorMapper.INSTANCE;
    @Test
    void vendorToVendorDTO() {
        Vendor vendor=new Vendor();
        vendor.setName("Vendor 1");
        vendor.setId(1L);

        VendorDTO vendorDTO=vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(1L,vendorDTO.getId());
        assertEquals("Vendor 1",vendorDTO.getName());
    }

    @Test
    void vendorDTOToVendor() {
        VendorDTO vendorDTO=new VendorDTO(1L,"Vendor 2","api/vendors,1");

        Vendor vendor=vendorMapper.vendorDTOToVendor(vendorDTO);

        assertEquals(vendorDTO.getId(),vendor.getId());
        assertEquals(vendorDTO.getName(),vendor.getName());
    }
}
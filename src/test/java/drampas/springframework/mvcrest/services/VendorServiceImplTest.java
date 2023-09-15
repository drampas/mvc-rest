package drampas.springframework.mvcrest.services;

import drampas.springframework.mvcrest.api.mapper.VendorMapper;
import drampas.springframework.mvcrest.api.model.VendorDTO;
import drampas.springframework.mvcrest.domain.Vendor;
import drampas.springframework.mvcrest.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class VendorServiceImplTest {
    @Mock
    VendorRepository vendorRepository;
    VendorService vendorService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        vendorService=new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }
    @Test
    void getAllVendors() {
        List<Vendor> vendors= Arrays.asList(new Vendor(),new Vendor(),new Vendor());
        Mockito.when(vendorRepository.findAll()).thenReturn(vendors);

        List<VendorDTO> vendorDTOS=vendorService.getAllVendors();

        assertEquals(3,vendorDTOS.size());
    }

    @Test
    void getVendorById() {
        Vendor vendor=new Vendor();
        vendor.setId(1L);
        vendor.setName("Vendor 1");

        Mockito.when(vendorRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(vendor));

        VendorDTO vendorDTO=vendorService.getVendorById(1L);
        assertEquals(1L,vendorDTO.getId());
        assertEquals("Vendor 1",vendorDTO.getName());
        assertEquals("api/vendors/1",vendorDTO.getVendorUrl());
    }

    @Test
    void createNewVendor() {
        Vendor vendor=new Vendor();
        vendor.setId(1L);
        vendor.setName("Vendor 1");

        VendorDTO vendorDTO=new VendorDTO(vendor.getId(),vendor.getName(),"api/vendor/1");

        Mockito.when(vendorRepository.save(ArgumentMatchers.any(Vendor.class))).thenReturn(vendor);

        VendorDTO savedVendorDTO=vendorService.createNewVendor(vendorDTO);
        assertEquals(1L,savedVendorDTO.getId());
        assertEquals("Vendor 1",savedVendorDTO.getName());
        assertEquals("api/vendors/1",savedVendorDTO.getVendorUrl());
    }

}
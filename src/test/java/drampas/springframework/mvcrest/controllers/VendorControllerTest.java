package drampas.springframework.mvcrest.controllers;

import drampas.springframework.mvcrest.api.model.VendorDTO;
import drampas.springframework.mvcrest.api.model.VendorListDTO;
import drampas.springframework.mvcrest.services.VendorService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendorControllerTest extends AbstractRestControllerTest{
    @Mock
    VendorService vendorService;
    @InjectMocks
    VendorController vendorController;
    MockMvc mvc;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mvc= MockMvcBuilders.standaloneSetup(vendorController).build();
    }
    @Test
    void getAllVendors() throws Exception {
        List<VendorDTO> vendorListDTO= Arrays.asList(new VendorDTO(),new VendorDTO(),new VendorDTO());
        Mockito.when(vendorService.getAllVendors()).thenReturn(vendorListDTO);

        mvc.perform(get("/api/vendors").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", Matchers.hasSize(3)));
    }

    @Test
    void getVendorById() throws Exception {
        VendorDTO vendorDTO=new VendorDTO(1L,"Vendor 1","/api/vendors/1");
        Mockito.when(vendorService.getVendorById(ArgumentMatchers.anyLong())).thenReturn(vendorDTO);

        mvc.perform(get("/api/vendors/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",Matchers.equalTo(1)))
                .andExpect(jsonPath("$.name",Matchers.equalTo("Vendor 1")));
    }

    @Test
    void createNewVendor() throws Exception {
        VendorDTO vendorDTO=new VendorDTO();
        vendorDTO.setName("Vendor 1");
        VendorDTO returnDTO=new VendorDTO(1L,"Vendor 1","/api/vendors/1");
        Mockito.when(vendorService.createNewVendor(ArgumentMatchers.any(VendorDTO.class))).thenReturn(returnDTO);

        mvc.perform(post("/api/vendors").contentType(MediaType.APPLICATION_JSON).content(asJsonString(vendorDTO)))
                .andExpect(jsonPath("$.id",Matchers.equalTo(1)))
                .andExpect(jsonPath("$.name",Matchers.equalTo("Vendor 1")))
                .andExpect(jsonPath("$.vendorUrl",Matchers.equalTo("/api/vendors/1")));
    }

    @Test
    void updateVendor() {
    }

    @Test
    void patchVendor() {
    }

    @Test
    void deleteVendor() {
    }
}
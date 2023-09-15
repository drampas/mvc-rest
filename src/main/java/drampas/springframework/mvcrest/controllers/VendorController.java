package drampas.springframework.mvcrest.controllers;

import drampas.springframework.mvcrest.api.model.VendorDTO;
import drampas.springframework.mvcrest.api.model.VendorListDTO;
import drampas.springframework.mvcrest.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//trying @restController,seems easier!
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {
    public static final String BASE_URL="/api/vendors";
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllVendors(){
        return new VendorListDTO(vendorService.getAllVendors());
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id){
        return vendorService.getVendorById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO){
        return vendorService.createNewVendor(vendorDTO);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@RequestBody VendorDTO vendorDTO,@PathVariable Long id){
        return vendorService.updateVendor(vendorDTO,id);
    }
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@RequestBody VendorDTO vendorDTO,@PathVariable Long id){
        return vendorService.patchVendor(vendorDTO,id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id){
        vendorService.deleteVendorById(id);
    }
}

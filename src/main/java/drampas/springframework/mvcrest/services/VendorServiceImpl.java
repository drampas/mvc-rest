package drampas.springframework.mvcrest.services;

import drampas.springframework.mvcrest.api.mapper.VendorMapper;
import drampas.springframework.mvcrest.api.model.VendorDTO;
import drampas.springframework.mvcrest.controllers.VendorController;
import drampas.springframework.mvcrest.domain.Vendor;
import drampas.springframework.mvcrest.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService{
    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll().stream()
                .map(vendor->{
                    VendorDTO vendorDTO=vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getUrl(vendor.getId()));
                    return vendorDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .map(vendorDTO -> {
                    vendorDTO.setVendorUrl(getUrl(id));
                    return vendorDTO;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        Vendor vendor=vendorMapper.vendorDTOToVendor(vendorDTO);
        VendorDTO returnDTO=vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
        returnDTO.setVendorUrl(getUrl(returnDTO.getId()));
        return returnDTO;
    }

    @Override
    public VendorDTO updateVendor(VendorDTO vendorDTO,Long id) {
        Vendor vendor=vendorMapper.vendorDTOToVendor(vendorDTO);
        vendor.setId(id);
        VendorDTO returnDTO=vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
        returnDTO.setVendorUrl(getUrl(id));
        return returnDTO;
    }
    @Override
    public VendorDTO patchVendor(VendorDTO vendorDTO, Long id) {
        Optional<Vendor> vendorOptional=vendorRepository.findById(id);
        if(vendorOptional.isPresent()){
            Vendor vendor=vendorOptional.get();
            if (!vendorDTO.getName().isBlank()){
                vendor.setName(vendorDTO.getName());
            }
            Vendor savedVendor=vendorRepository.save(vendor);
            VendorDTO returnDTO=vendorMapper.vendorToVendorDTO(savedVendor);
            returnDTO.setVendorUrl(getUrl(id));
            return returnDTO;
        }else {
         throw new ResourceNotFoundException();
        }
    }
    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }
    private String getUrl(Long id){
        return VendorController.BASE_URL+"/"+id;
    }
}

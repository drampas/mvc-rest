package drampas.springframework.mvcrest.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {
    private Long id;
    private String name;
    private String vendorUrl;
}

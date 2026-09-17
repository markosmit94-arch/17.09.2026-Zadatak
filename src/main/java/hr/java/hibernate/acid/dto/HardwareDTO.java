package hr.java.hibernate.acid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HardwareDTO {
    private String hardwareSifra;
    private String hardwareNaziv;
    private double hardwareCijena;
    private String categoryTip;
    private int hardwareKolicina;
}

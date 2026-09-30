package hr.java.hibernate.acid.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HardwareDTO {
    @NotBlank(message = "Sifra cannot be blank")
    private String hardwareSifra;

    @NotBlank(message = "Naziv cannot be blank")
    private String hardwareNaziv;

    @DecimalMin(value = "0.0", message = "Hardware price must be positive")
    private double hardwareCijena;

    @NotBlank(message = "Category cannot be blank")
    private String categoryTip;

    @Positive(message = "Kolicina cannot be negative")
    private int hardwareKolicina;
}

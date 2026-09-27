package hr.java.hibernate.acid.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hardware {
    private Integer id;
    private String naziv;
    private String sifra;
    private double cijena;
    private Tip tip;
    private int kolicina;
}

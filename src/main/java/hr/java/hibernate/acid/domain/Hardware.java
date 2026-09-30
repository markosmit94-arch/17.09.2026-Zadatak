package hr.java.hibernate.acid.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hardware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String naziv;
    private String sifra;
    private double cijena;
    @ManyToOne
    @JoinColumn(name = "tipId")
    private Type type;
    private int kolicina;
}

package hr.java.hibernate.acid.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*
    CPU(1, "Procesor", "New and used"),
    RAM(2, "Ram", "New and used"),
    SSD(3, "Ssd", "New and used"),
    MOTHERBOARD(4, "Maticna ploca", "New and used");

     */

    private Integer id;
    private String name;
    private String description;

    /*

    public static Type fromId(Integer id) {
        for (Type type : values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown tip id: " + id);
    }

     */
}

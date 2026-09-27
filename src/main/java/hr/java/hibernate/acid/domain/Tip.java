package hr.java.hibernate.acid.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Tip {

    CPU(1, "Procesor", "New and used"),
    RAM(2, "Ram", "New and used"),
    SSD(3, "Ssd", "New and used");

    private final Integer id;
    private final String name;
    private final String description;
}

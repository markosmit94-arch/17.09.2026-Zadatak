package hr.java.hibernate.acid.repository;

import hr.java.hibernate.acid.domain.Hardware;

import java.util.List;

public interface HardwareRepository {
    List<Hardware> getAllHardware();
    List<Hardware> getHardwareBySifra(String hardwareSifra);
}

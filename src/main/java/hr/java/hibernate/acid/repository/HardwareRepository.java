package hr.java.hibernate.acid.repository;

import hr.java.hibernate.acid.domain.Hardware;
import hr.java.hibernate.acid.dto.HardwareDTO;

import java.util.List;
import java.util.Optional;

public interface HardwareRepository {
    List<Hardware> getAllHardware();
    List<Hardware> getHardwareBySifra(String hardwareSifra);
    Integer saveNewHardware(Hardware hardware);
    Optional<Hardware> updateHardware(Hardware hardwareToUpdate, Integer id);
    boolean hardwareByIdExists(Integer id);
    boolean deleteHardwareById(Integer id);
}

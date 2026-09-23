package hr.java.hibernate.acid.service;

import hr.java.hibernate.acid.dto.HardwareDTO;

import java.util.List;
import java.util.Optional;

public interface HardwareService {
    List<HardwareDTO> getAllHardware();
    List<HardwareDTO> getHardwareBySifra(String hardwareSifra);
    Integer saveNewHardware(HardwareDTO hardware);
    Optional<HardwareDTO> updateHardware(HardwareDTO hardwareDTO, Integer id);
    boolean hardwareByIdExists(Integer id);
    boolean deleteHardwareById(Integer id);
}

package hr.java.hibernate.acid.service;

import hr.java.hibernate.acid.dto.HardwareDTO;

import java.util.List;

public interface HardwareService {
    List<HardwareDTO> getAllHardware();
    List<HardwareDTO> getHardwareBySifra(String hardwareSifra);
}

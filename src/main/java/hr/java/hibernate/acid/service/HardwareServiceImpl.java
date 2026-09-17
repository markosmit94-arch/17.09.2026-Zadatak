package hr.java.hibernate.acid.service;

import hr.java.hibernate.acid.domain.Hardware;
import hr.java.hibernate.acid.dto.HardwareDTO;
import hr.java.hibernate.acid.repository.HardwareRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class HardwareServiceImpl  implements HardwareService {

    private HardwareRepository hardwareRepository;

    @Override
    public List<HardwareDTO> getAllHardware(){
        return hardwareRepository.getAllHardware().stream()
                .map(this::convertHardwareToHardwareDTO)
                .toList();
    }

    @Override
    public List<HardwareDTO> getHardwareBySifra(String hardwareSifra) {
        return hardwareRepository.getHardwareBySifra(hardwareSifra).stream()
                .map(this::convertHardwareToHardwareDTO)
                .toList();
    }

    private HardwareDTO convertHardwareToHardwareDTO(Hardware hardware) {
        return new HardwareDTO(hardware.getSifra(),
                hardware.getNaziv(), hardware.getCijena(),
                hardware.getTip(), hardware.getKolicina());
    }
}

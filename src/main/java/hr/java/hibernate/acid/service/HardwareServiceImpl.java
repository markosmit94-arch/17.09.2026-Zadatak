package hr.java.hibernate.acid.service;

import hr.java.hibernate.acid.domain.Hardware;
import hr.java.hibernate.acid.dto.HardwareDTO;
import hr.java.hibernate.acid.repository.HardwareRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Integer saveNewHardware(HardwareDTO hardware) {
        return hardwareRepository.saveNewHardware(convertHardwareDTOToHardware(hardware));
    }

    @Override
    public Optional<HardwareDTO> updateHardware(HardwareDTO hardwareDTO, Integer id) {
        Optional<Hardware> updatedHardwareOptional =
                hardwareRepository.updateHardware(convertHardwareDTOToHardware(hardwareDTO), id);

        if (updatedHardwareOptional.isPresent()) {
            return Optional.of(convertHardwareToHardwareDTO(updatedHardwareOptional.get()));
        }

        return Optional.empty();
    }

    @Override
    public boolean hardwareByIdExists(Integer id) {
        return hardwareRepository.hardwareByIdExists(id);
    }

    @Override
    public boolean deleteHardwareById(Integer id) {
        return hardwareRepository.deleteHardwareById(id);
    }

    private HardwareDTO convertHardwareToHardwareDTO(Hardware hardware) {
        return new HardwareDTO(hardware.getSifra(),
                hardware.getNaziv(), hardware.getCijena(),
                hardware.getTip(), hardware.getKolicina());
    }

    private Hardware convertHardwareDTOToHardware(HardwareDTO hardwareDTO) {
        Integer latestId =
                hardwareRepository.getAllHardware().stream()
                        .max((h1, h2) -> h1.getId().compareTo(h2.getId()))
                        .get().getId();

        return new Hardware(latestId + 1, hardwareDTO.getHardwareNaziv(),
                hardwareDTO.getHardwareSifra(), hardwareDTO.getHardwareCijena(),
                hardwareDTO.getCategoryTip(), hardwareDTO.getHardwareKolicina());
    }

}

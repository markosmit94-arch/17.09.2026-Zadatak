package hr.java.hibernate.acid.service;

import hr.java.hibernate.acid.domain.Hardware;
import hr.java.hibernate.acid.domain.Type;
import hr.java.hibernate.acid.dto.HardwareDTO;
import hr.java.hibernate.acid.repository.SpringDataHardwareRepository;
import hr.java.hibernate.acid.repository.SpringDataTypeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class HardwareServiceImpl  implements HardwareService {

    //private HardwareRepository hardwareRepository;
    private SpringDataHardwareRepository hardwareRepository;
    private SpringDataTypeRepository dataTypeRepository;

    @Override
    public List<HardwareDTO> getAllHardware(){
        return hardwareRepository.findAll().stream()
                .map(this::convertHardwareToHardwareDTO)
                .toList();

        /*
        return hardwareRepository.getAllHardware().stream()
                .map(this::convertHardwareToHardwareDTO)
                .toList();

         */
    }

    @Override
    public List<HardwareDTO> getHardwareBySifra(String hardwareSifra) {
        return hardwareRepository.findBySifra(hardwareSifra).stream()
                .map(this::convertHardwareToHardwareDTO)
                .toList();

        /*
        return hardwareRepository.getHardwareBySifra(hardwareSifra).stream()
                .map(this::convertHardwareToHardwareDTO)
                .toList();

         */
    }

    @Override
    public Integer saveNewHardware(HardwareDTO hardware) {
        return hardwareRepository.save(convertHardwareDTOToHardware(hardware)).getId();
        //return hardwareRepository.saveNewHardware(convertHardwareDTOToHardware(hardware));
    }

    @Override
    public Optional<HardwareDTO> updateHardware(HardwareDTO hardwareDTO, Integer id) {

        Optional<Hardware> hardwareToUpdate = hardwareRepository.findById(id);

        if(hardwareToUpdate.isPresent()) {
            Hardware hardware = hardwareToUpdate.get();
            hardware.setType(dataTypeRepository.findByName(hardwareDTO.getCategoryTip()));
            hardware.setNaziv(hardwareDTO.getHardwareNaziv());
            hardware.setSifra(hardwareDTO.getHardwareSifra());
            hardware.setCijena(hardwareDTO.getHardwareCijena());
            hardware.setKolicina(hardwareDTO.getHardwareKolicina());
            Hardware updatedHardware = hardwareRepository.save(hardware);
            return Optional.of(convertHardwareToHardwareDTO(updatedHardware));
        }

        return Optional.empty();

        /*
        Optional<Hardware> updatedHardwareOptional =
                hardwareRepository.updateHardware(convertHardwareDTOToHardware(hardwareDTO), id);

        if (updatedHardwareOptional.isPresent()) {
            return Optional.of(convertHardwareToHardwareDTO(updatedHardwareOptional.get()));
        }

        return Optional.empty();

         */
    }

    @Override
    public boolean hardwareByIdExists(Integer id) {
        return hardwareRepository.findById(id).isPresent();
        //return hardwareRepository.hardwareByIdExists(id);
    }

    @Override
    public boolean deleteHardwareById(Integer id) {
        if (hardwareByIdExists(id)) {
            hardwareRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
        //return hardwareRepository.deleteHardwareById(id);
    }

    private Hardware convertHardwareDTOToHardware(HardwareDTO hardwareDTO) {
        Integer latestId =
                hardwareRepository.findAll().stream()
                        .max((h1, h2) -> h1.getId().compareTo(h2.getId()))
                        .get().getId();

        return new Hardware(latestId + 1, hardwareDTO.getHardwareNaziv(),
                hardwareDTO.getHardwareSifra(), hardwareDTO.getHardwareCijena(),
                dataTypeRepository.findByName(hardwareDTO.getCategoryTip()), hardwareDTO.getHardwareKolicina());

        /*
        Integer latestId =
                hardwareRepository.getAllHardware().stream()
                        .max((h1, h2) -> h1.getId().compareTo(h2.getId()))
                        .get().getId();



        return new Hardware(latestId + 1, hardwareDTO.getHardwareNaziv(),
                hardwareDTO.getHardwareSifra(), hardwareDTO.getHardwareCijena(),
                Type.valueOf(hardwareDTO.getCategoryTip()), hardwareDTO.getHardwareKolicina());

         */
    }

    private HardwareDTO convertHardwareToHardwareDTO(Hardware hardware) {

        return new HardwareDTO(
                hardware.getSifra(),
                hardware.getNaziv(),
                hardware.getCijena(),
                hardware.getType().getName(),
                hardware.getKolicina());
    }
}

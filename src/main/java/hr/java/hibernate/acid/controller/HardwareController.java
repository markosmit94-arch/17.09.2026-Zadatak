package hr.java.hibernate.acid.controller;

import hr.java.hibernate.acid.dto.HardwareDTO;
import hr.java.hibernate.acid.service.HardwareService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hardware")
@AllArgsConstructor
public class HardwareController {

    private HardwareService hardwareService;

    @GetMapping
    public List<HardwareDTO> getAllHardware() {
        return hardwareService.getAllHardware().stream().toList();
    }

    @GetMapping("/{articleName}")
    public List<HardwareDTO> filterHardwareBySifra(@PathVariable String hardwareSifra) {
        return hardwareService.getHardwareBySifra(hardwareSifra).stream().toList();
    }

    @PostMapping("/new")
    public ResponseEntity<Void> saveHardware(@Valid @RequestBody HardwareDTO hardwareDTO) {
        hardwareService.saveNewHardware(hardwareDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/hardware/{hardwareId}")
    public ResponseEntity<HardwareDTO> updateHardware(@Valid @RequestBody HardwareDTO hardwareDTO, @PathVariable Integer hardwareId) {
        if(hardwareService.hardwareByIdExists(hardwareId)) {
            hardwareService.updateHardware(hardwareDTO, hardwareId);
            return ResponseEntity.ok(hardwareDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/hardware/{hardwareId}")
    public ResponseEntity<?> deleteHardware(@PathVariable Integer hardwareId) {
        if(hardwareService.hardwareByIdExists(hardwareId)) {
            boolean result = hardwareService.deleteHardwareById(hardwareId);
            if(result) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}

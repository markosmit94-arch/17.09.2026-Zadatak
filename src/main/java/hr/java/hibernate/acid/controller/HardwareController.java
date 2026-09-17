package hr.java.hibernate.acid.controller;

import hr.java.hibernate.acid.dto.HardwareDTO;
import hr.java.hibernate.acid.service.HardwareService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

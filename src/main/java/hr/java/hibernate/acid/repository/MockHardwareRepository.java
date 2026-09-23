package hr.java.hibernate.acid.repository;

import hr.java.hibernate.acid.domain.Hardware;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MockHardwareRepository implements HardwareRepository {

    private static List<Hardware> hardwareList;

    static {
        hardwareList = new ArrayList<>();

        Hardware h1 = new Hardware(1,"Silicon Power", "FJH6K67D", 23.45, "RAM", 7);
        Hardware h2 = new Hardware(2,"Intel Core i3", "12100F", 124.99, "CPU", 9);
        Hardware h3 = new Hardware(3,"GeForce RTX5060", "GFJ49DFJG", 611.35, "Graficka kartica", 11);

        hardwareList.add(h1);
        hardwareList.add(h2);
        hardwareList.add(h3);
    }

    @Override
    public List<Hardware> getAllHardware() {return hardwareList;}

    @Override
    public List<Hardware> getHardwareBySifra(String hardwareSifra) {
        return hardwareList.stream()
                .filter(hardware -> hardware.getSifra().contains(hardwareSifra))
                .collect(Collectors.toList());
    }

    @Override
    public Integer saveNewHardware(Hardware hardware) {
        Integer generatedId = hardwareList.size() + 1;
        hardware.setId(generatedId);
        hardwareList.add(hardware);
        return generatedId;
    }

    @Override
    public Optional<Hardware> updateHardware(Hardware hardwareToUpdate, Integer id) {
        Optional<Hardware> storedHardwareOptional = hardwareList.stream().filter(hardware -> hardware.getId().equals(id)).findFirst();
        if (storedHardwareOptional.isPresent()) {
            Hardware storedHardware = storedHardwareOptional.get();
            storedHardware.setSifra(hardwareToUpdate.getSifra());
            storedHardware.setNaziv(hardwareToUpdate.getNaziv());
            storedHardware.setCijena(hardwareToUpdate.getCijena());
            storedHardware.setTip(storedHardware.getTip());
            storedHardware.setKolicina(storedHardware.getKolicina());

            return Optional.of(storedHardware);
        }

        return Optional.empty();
    }

    @Override
    public boolean hardwareByIdExists(Integer id){
        return hardwareList.stream().filter(hardware -> hardware.getId().equals(id)).findFirst().isPresent();
    }

    @Override
    public boolean deleteHardwareById(Integer id) {
        return hardwareList.removeIf(hardware -> hardware.getId().equals(id));
    }
}

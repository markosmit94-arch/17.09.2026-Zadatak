package hr.java.hibernate.acid.repository;

import hr.java.hibernate.acid.domain.Hardware;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MockHardwareRepository implements HardwareRepository {

    private static List<Hardware> hardwareList;

    static {
        hardwareList = new ArrayList<>();

        Hardware h1 = new Hardware("Silicon Power", "FJH6K67D", 23.45, "RAM", 7);
        Hardware h2 = new Hardware("Intel Core i3", "12100F", 124.99, "CPU", 9);
        Hardware h3 = new Hardware("GeForce RTX5060", "GFJ49DFJG", 611.35, "Graficka kartica", 11);

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
}

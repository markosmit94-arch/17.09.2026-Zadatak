package hr.java.hibernate.acid.repository;

import hr.java.hibernate.acid.domain.Hardware;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public interface SpringDataHardwareRepository
        extends JpaRepository<Hardware, Integer>, JpaSpecificationExecutor<Hardware> {
    List<Hardware> findByName(String name);
    List<Hardware> findBySifra(String sifra);
}

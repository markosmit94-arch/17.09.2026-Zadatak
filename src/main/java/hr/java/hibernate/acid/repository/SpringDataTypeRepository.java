package hr.java.hibernate.acid.repository;

import hr.java.hibernate.acid.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataTypeRepository extends JpaRepository<Type, Integer> {
    Type findByName(String name);
}

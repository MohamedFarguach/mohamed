package ma.richebois.transport.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import ma.richebois.transport.entity.TypeVehicule;

@CrossOrigin(origins="*")
@Repository
public interface TypeVehiculeRepository extends JpaRepository<TypeVehicule, Long> {

}

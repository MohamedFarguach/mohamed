package ma.richebois.transport.repositorie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import ma.richebois.transport.entity.Vehicule;
@CrossOrigin(origins="*")
@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long>  {
 
	
	@Query("SELECT u FROM  Vehicule u WHERE u.typeVehicule.id = :id ")
	List<Vehicule> find2(@Param("id") Long id);
}

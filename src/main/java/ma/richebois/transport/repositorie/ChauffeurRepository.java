package ma.richebois.transport.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import ma.richebois.transport.entity.Chauffeur;

@CrossOrigin(origins="*")
@Repository 
public interface ChauffeurRepository extends JpaRepository<Chauffeur, Long> {

}

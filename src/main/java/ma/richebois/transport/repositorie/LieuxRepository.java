package ma.richebois.transport.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import ma.richebois.transport.entity.Lieux;

@CrossOrigin(origins="*")
@Repository
public interface LieuxRepository extends JpaRepository<Lieux, Long> {

}

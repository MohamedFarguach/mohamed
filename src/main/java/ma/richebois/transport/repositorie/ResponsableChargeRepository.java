package ma.richebois.transport.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import ma.richebois.transport.entity.ResponsableCharge;

@CrossOrigin(origins="*")
@Repository
public interface ResponsableChargeRepository  extends  JpaRepository<ResponsableCharge, Long> {

}

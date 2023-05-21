package ma.richebois.transport.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import ma.richebois.transport.entity.Users;


@CrossOrigin(origins="*")
@Repository 
public interface AppUserRepository extends  JpaRepository<Users, Long> {

	Users findByUsername(String username);

}

package ma.richebois.transport.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import ma.richebois.transport.entity.AppRole;

@CrossOrigin(origins="*")
@Repository 
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {	
	AppRole findByRoleName(String roleName);
}

package ma.richebois.transport.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ma.richebois.transport.entity.AppRole;
import ma.richebois.transport.entity.Users;

@Service
@Transactional
public interface ServiceSec {
	
	Users addNewUser(Users appUser);
	AppRole addNewRole(AppRole appRole);
     Users loadUserByUsername(String username) throws UsernameNotFoundException;
 	List<Users> listUsers();
 	
	
}

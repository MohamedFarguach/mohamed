package ma.richebois.transport.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ma.richebois.transport.entity.AppRole;
import ma.richebois.transport.entity.Users;
import ma.richebois.transport.repositorie.AppRoleRepository;
import ma.richebois.transport.repositorie.AppUserRepository;

@Service
@Transactional 
@RequiredArgsConstructor
public class AccountServiceImp implements ServiceSec {
	   
    @Autowired
	private AppRoleRepository appRoleRepositorie;
    @Autowired
     private AppUserRepository appUserRepositorie;
    @Autowired
 	private PasswordEncoder passwordEncoder;
   
      
	@Override
	public Users addNewUser(Users appUser) {
		// TODO Auto-generated method stub
		  String pw=appUser.getPassword();
			  appUser.setPassword(passwordEncoder.encode(pw));
			
		return appUserRepositorie.save(appUser);
	}

	@Override
	public AppRole addNewRole(AppRole appRole) {
		// TODO Auto-generated method stub
		return appRoleRepositorie.save(appRole);
	}

	@Override
	public Users loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return appUserRepositorie.findByUsername(username);
	}

	@Override
	public List<Users> listUsers() {
		// TODO Auto-generated method stub
		return appUserRepositorie.findAll();
	}

	
}

package ma.richebois.transport.service;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ma.richebois.transport.entity.Users;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private ServiceSec seriviceSec;
	
	public UserDetailsServiceImpl(ServiceSec seriviceSec) {
		super();
		this.seriviceSec = seriviceSec;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub/
		
		 Users appUser=seriviceSec.loadUserByUsername(username);
		  if(appUser == null) { throw new
			  UsernameNotFoundException(" UserDetails loadUserByUsername ===null :::::::::::"+username);
			  
			  }
		  
		  return User.builder()
	                .username(appUser.getUsername())
	                .password(appUser.getPassword())
	                .roles(appUser.getAppRole().getRoleName())
	                .build();
	    }
		

}

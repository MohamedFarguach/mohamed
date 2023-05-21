package ma.richebois.transport;


import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ma.richebois.transport.config.RsakeysConfig;
import ma.richebois.transport.service.ServiceSec;
import ma.richebois.transport.service.UsersService;

@SpringBootApplication
@EnableConfigurationProperties(RsakeysConfig.class)
public class AppSecu3Application extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(AppSecu3Application.class, args);
	}
	 @Bean
	    public ModelMapper modelMapper() {
	        return new ModelMapper();
	    }
	  @Bean
	  public PasswordEncoder passwordEncoder(){ 
		  return new
	                BCryptPasswordEncoder(); 
		  }
		CommandLineRunner start(ServiceSec accountService,UsersService service) {
			
			return args -> {
			//
				/*
				 * accountService.addNewRole(new AppRole(null,"Superviseur"));
				 * accountService.addNewRole(new AppRole(null,"ADMIN"));
				 * accountService.addNewRole(new AppRole(null,"Consultant"));
				 * accountService.addNewRole(new AppRole(null,"Demandeur"));
				 */
				     
				/*
				 * AppRoleDTO role=new AppRoleDTO(); role.setId((long) 8);
				 * role.setRoleName("ADMIN");
				 */
				  
				// accountService.addNewUser(new Users("null","mohamed"));
				// accountService.addNewUser(new Users());
				  //   UserDTO userdto = new UserDTO();
				  //   userdto.setId((long) 8);
				/*
				 * userdto.setPassword("1234"); userdto.setPrenom("e");
				 * userdto.setUsername("ee"); userdto.setEmail("r"); userdto.setAppRole(role);
				 * 
				 * service.AjouteInfoUser(userdto);
				 */
				 // accountService.addNewUser(new Users(null,"user3","1234",new ArrayList<>()));
				  
				 // accountService.addRoleToUser("user1", "ADMIN");
				//  accountService.addRoleToUser("user3", "USER");
				//  accountService.addRoleToUser("user2", "ADMIN"); 
			 // accountService.addRoleToUser("user4", "ADMIN");
				//  accountService.addRoleToUser("user4", "ADMIN");
				 
					//  
			
			};
	  }
	 

}

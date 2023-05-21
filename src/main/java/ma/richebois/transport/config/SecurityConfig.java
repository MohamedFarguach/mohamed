package ma.richebois.transport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;

import ma.richebois.transport.service.UserDetailsServiceImpl;
 

@CrossOrigin(origins="*")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	private UserDetailsServiceImpl userDetailsService;
	
	
	private RsakeysConfig rsakeysConfig;
	  private PasswordEncoder passwordEncoder;

	public SecurityConfig(RsakeysConfig rsakeysConfig,PasswordEncoder passwordEncoder,UserDetailsServiceImpl userDetailsService ) {
        this.rsakeysConfig = rsakeysConfig;
      this.passwordEncoder = passwordEncoder;
      this.userDetailsService = userDetailsService;
    }


	 //@Override 
	  protected void configure(AuthenticationManagerBuilder auth) throws
	  Exception 
	  { 
	              auth.userDetailsService(userDetailsService);
	  }
			
		
		
		//@Bean
		public AuthenticationManager   authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
			return authenticationConfiguration.getAuthenticationManager();
			
		}
		@Bean
		public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
           DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(); 
           authProvider.setPasswordEncoder(passwordEncoder);
		    authProvider.setUserDetailsService(userDetailsService);
		     return new ProviderManager(authProvider);
		}
		
		  @Bean
		  public SecurityFilterChain filterChain(HttpSecurity httpSecurity)
		  throws Exception { 
			  System.out.println("ana hna 6"); 
			  return httpSecurity
		  
		  .csrf(csrf->csrf.disable())
		  .authorizeRequests(auth->auth.antMatchers("/authenticate/**").permitAll())
		  .authorizeRequests(auth->auth.antMatchers(HttpMethod.OPTIONS,"/**").permitAll())
		  .authorizeRequests(auth->auth.antMatchers(HttpMethod.OPTIONS,"/**/**").permitAll())
		  .authorizeRequests(auth->auth.anyRequest().authenticated())
		  .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)		
		  .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.
		  STATELESS))
		  .httpBasic(Customizer.withDefaults()) 
		  .build(); 
			  }
		  @Bean
		  public JwtDecoder jwtDecoder() { 
			  System.out.println("ana hna 8");
		  return
				  NimbusJwtDecoder.withPublicKey (rsakeysConfig.getPublicKey()).build();
		  		  
		  }
		  
		  @Bean
		  public JwtEncoder jwtEncoder() { 
			  System.out.println("ana hna 9"); 
			  JWK jwk = new
		  RSAKey.Builder(rsakeysConfig.getPublicKey()).privateKey(rsakeysConfig.getPrivateKey()).build(); 
			  JWKSource<com.nimbusds.jose.proc.SecurityContext> jwkSource =new ImmutableJWKSet<>(new JWKSet(jwk));
		  
		  return new NimbusJwtEncoder(jwkSource);
		  
		  }
		 
			
	}

	
		

package ma.richebois.transport.web;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ma.richebois.transport.entity.Tokener;
import ma.richebois.transport.entity.Users;
import ma.richebois.transport.service.ServiceSec;
@RestController
@CrossOrigin(origins="*")
@ResponseStatus(code = HttpStatus.CREATED)
public class AuthController {
  
	private JwtEncoder jwtEncoder;
	
	private AuthenticationManager authenticationManager;
	
	public AuthController(JwtEncoder jwtEncoder,
			AuthenticationManager authenticationManager,
			JwtDecoder jwtDecoder,
			UserDetailsService userDetailsService,ServiceSec accountService) {
		super();
		this.jwtEncoder = jwtEncoder;
		this.authenticationManager=authenticationManager;
		
	}
	
	
	
	
	@PostMapping("/authenticate")
	public Tokener jwtToken(
		@RequestBody
		Users request,HttpServletResponse response,HttpServletRequest req,
			boolean withRefreshToken,
			String refreshToken)
	{
		String subject=null;
		String role=null;
		

			Authentication authentication =authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
				);
			User user=(User)  authentication.getPrincipal();
			subject=authentication.getName();
			role=authentication.getAuthorities()
			    	.stream().map(aut -> aut.getAuthority())
			    	.collect(Collectors.joining(" "));
		
			
		Map<String,  String> idToken = new HashMap<>();		
		Instant instant=Instant.now();
		
		JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
				.subject(subject)
				.issuedAt(instant)
				.expiresAt(instant.plus(withRefreshToken?349:299, ChronoUnit.MINUTES))
				.issuer("security-service")
				.claim("roles",user.getAuthorities().stream().map(ga->ga.getAuthority()).collect(Collectors.toList()))
				.build();
		String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
		idToken.put("accessToken", jwtAccessToken);
		System.out.println(idToken);
		System.out.println(role);
		if(withRefreshToken) {
			JwtClaimsSet jwtClaimsSetRefresh=JwtClaimsSet.builder()
					.subject(subject)
					.issuedAt(instant)
					.expiresAt(instant.plus(310, ChronoUnit.MINUTES))
					.issuer("security-service")					
					.build();
			String jwtRefreshToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
			idToken.put("refreshToken", jwtRefreshToken);
		}
		return  new Tokener(null,idToken.get("accessToken"),role);
		
		
	}
	
	
}

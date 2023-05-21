package ma.richebois.transport.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor @Data @NoArgsConstructor
public class Tokener {
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String accessToken;
	private String role;
	
}

package ma.richebois.transport.dto;


import lombok.Data;

@Data
public class UserDTO {
	private Long id;
	private String username;
	   private String password ;
	   private String nom;
		private String prenom ;
		private String email ;
	   private AppRoleDTO appRole;


}

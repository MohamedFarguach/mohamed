package ma.richebois.transport.dto;


import lombok.Data;
 
@Data
public class ResponsableChargeDTO {
	private Long id;
	private String nom;
	private String prenom;
	private String matricule;
	private Long telephone;
}

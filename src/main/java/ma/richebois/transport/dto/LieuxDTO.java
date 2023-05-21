package ma.richebois.transport.dto;

 
import lombok.Data;
@Data
public class LieuxDTO {

    private Long id;
	private String code;
	private String designation;
	private String type;
	private String adresse;
	private String statut;
	private TypeLieuxDTO typelieux;
}

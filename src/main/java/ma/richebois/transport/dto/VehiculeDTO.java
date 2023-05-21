package ma.richebois.transport.dto;


import lombok.Data;
 
@Data
public class VehiculeDTO {

	private Long id;
	private String type;
	private String matricule;
	
	private String statut;
   	private String designation;
	private Long ancienKm;
	private Long nouveauKm;
	  private MarqueDTO marques;	
	    private TypeVehiculeDTO typeVehicule;

}

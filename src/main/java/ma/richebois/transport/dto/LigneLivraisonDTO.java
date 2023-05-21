package ma.richebois.transport.dto;


import java.time.LocalDateTime;


import lombok.Data;

@Data
public class LigneLivraisonDTO {

	private Long id;

	private LocalDateTime datePrevuCharge;
	private LocalDateTime dateLivraison;
	private LocalDateTime  dateDemande;
	private Long quantite;
	private String designation;
	private String unite;
	private String note;
	private String statut;
	private VehiculeDTO vehicule;
   private	 ChauffeurDTO chauffeur;
   private FilelivraisonDTO filelivraison;
   private LieuxDTO lieuxlivraison;
   private TypeVehiculeDTO typeVehicule;
   private LieuxDTO lieucharge;
   private ResponsableChargeDTO responsableCharge;


}

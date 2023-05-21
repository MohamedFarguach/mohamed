package ma.richebois.transport.entity;



import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners({AuditingEntityListener.class})
@AllArgsConstructor @Data @NoArgsConstructor

public class LigneLivraison {
	@Id @GeneratedValue
	(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@NotNull      @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")  
	private LocalDateTime datePrevuCharge;
	
	@NotNull 
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dateLivraison;
	
	@NotNull     @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime  dateDemande;
	@NotNull
	private Long quantite;
	@NotEmpty
	private String designation;
	@NotEmpty
	private String unite;
	
	private String note;
	@NotEmpty
	private String statut;
	 @ManyToOne
	    private Filelivraison filelivraison;
	 @ManyToOne
	    private Vehicule vehicule;
	 @ManyToOne
	    private Chauffeur chauffeur;
	 @ManyToOne
	    private TypeVehicule typeVehicule;
	
	 @ManyToOne
	    private Lieux lieuxlivraison;
	 @ManyToOne
	    private Lieux lieucharge;
	 @ManyToOne
	    private ResponsableCharge responsableCharge;
	
	 @Column(updatable = false)
	    @CreatedBy
	    private String creePar;
	    
	    @Column( updatable = false)
	    @CreatedDate
	    private Date  creeDate;
	    
	    
	    @LastModifiedBy
	    private String modifierPar; 
	   
	    @LastModifiedDate
	    @Temporal(TemporalType.TIME)
	    private Date  dateModifier;

	
}

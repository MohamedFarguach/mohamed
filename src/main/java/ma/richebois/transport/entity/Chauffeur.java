package ma.richebois.transport.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners({AuditingEntityListener.class})
@AllArgsConstructor @Data @NoArgsConstructor
public class Chauffeur {
	@Id @GeneratedValue
	(strategy = GenerationType.IDENTITY)	
	private Long id;
	@NotEmpty
	private String nom;
	@NotEmpty
	private String prenom;
    @Column(unique = true) @NotEmpty
	private String matricule;
    @Column(unique = true) @NotNull
	private Long telephone;
	 
	
	@OneToMany( mappedBy = "chauffeur",fetch=FetchType.LAZY) 
    private Collection<LigneLivraison> ligneLivraison  = new ArrayList<>();


	 @Column(updatable = false)
	    @CreatedBy
	    private String creePar;
	    
	    @Column( updatable = false)
	    @CreatedDate
	    private Date  creeDate;
	    
	    
	    @LastModifiedBy
	    private String modifierPar; 
	   
	    @LastModifiedDate
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date datemodifier;

		
	

	
}

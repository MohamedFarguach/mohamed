package ma.richebois.transport.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
 @Data @NoArgsConstructor
@AllArgsConstructor 
public class Users {
	

@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
   @Column(unique = true) @NotEmpty
	private String username;
   @NotEmpty
	private String nom;

   @NotEmpty
	   private String password ;
   @NotEmpty @Column(unique = true)
   private String prenom ;
   @NotEmpty  @Column(unique = true)
	   private String email;
	   
   @ManyToOne
   private AppRole appRole;



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
	    private Date dateModifier;

		

}

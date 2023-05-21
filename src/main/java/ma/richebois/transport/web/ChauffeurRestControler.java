package ma.richebois.transport.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.richebois.transport.dto.ChauffeurDTO;
import ma.richebois.transport.service.ChauffeurService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins="*")
@ResponseStatus(code = HttpStatus.CREATED)

//@EnableJpaRepositories 
@Slf4j
public class ChauffeurRestControler {

	@Autowired
	private ChauffeurService serviceChauffeur;
	 
	
/////////////////  AjouteChauffeur /////////////////

@PostMapping(path="/AjouteChauffeur")
@ResponseStatus(code = HttpStatus.CREATED)

public ChauffeurDTO AjouteChauffeur(@RequestBody ChauffeurDTO chauffeurDTO){
	log.trace(" RestControlerChauffeur la methode AjouteChauffeur");

return  serviceChauffeur.AjouteInfoChauffeur(chauffeurDTO);
}

///////////Modefier ChauffeurDTO///////////////////////////
@PutMapping(path="/PutChauffeur/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public void ModefierChauffeurn(@RequestBody ChauffeurDTO  chauffeurDTO, @PathVariable long id ) {
	log.trace(" RestControlerChauffeur la methode ModefierChauffeurn");
System.out.println("id"+id);
	serviceChauffeur.ModefierChauffeur(chauffeurDTO,id);

}


///////////////////////////   delete  chauffeur  ///////////////////////////////

@DeleteMapping(path="/deletChauffeur/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public void DeletechauffeurDTO(@PathVariable long id ) {
	log.trace(" RestControlerChauffeur la methode DeletechauffeurDTO");

	serviceChauffeur.Deletechauffeur(id);

}

////////Affichage  Chauffeur ////////////////
@GetMapping(path="/GetChauffeur")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public List<ChauffeurDTO> GetChauffeur(){
	log.trace(" RestControlerChauffeur la methode GetChauffeur");

return   serviceChauffeur.GetChauffeur();
}
////////Find  Chauffeur ////////////////
@GetMapping(path="/afficheChauffeur/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public ChauffeurDTO findChauffeur( @PathVariable Long id){
log.trace(" RestControlerChauffeur la methode GetChauffeur");

return   serviceChauffeur.FindChauffeur(id);
}

/////////////////  AjouteChauffeur /////////////////

@PostMapping(path="/AjouterChauffeur")
@ResponseStatus(code = HttpStatus.CREATED)

public ChauffeurDTO AjouterChauffeur(@RequestBody ChauffeurDTO chauffeurDTO){
log.trace(" RestControlerChauffeur la methode AjouteChauffeur");

return  serviceChauffeur.AjouteInfoChauffeur(chauffeurDTO);
}


}

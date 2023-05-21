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
import ma.richebois.transport.dto.ResponsableChargeDTO;
import ma.richebois.transport.service.ResponsableChargeService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins="*")
@ResponseStatus(code = HttpStatus.CREATED)

//@EnableJpaRepositories 
@Slf4j
public class ResponsableChargeRestController {
	 
	@Autowired
	public ResponsableChargeService responsableChargeService;

	
/////////////////  AjouteResponsable /////////////////

@PostMapping(path="/AjouteResponsable")
@ResponseStatus(code = HttpStatus.CREATED)

public ResponsableChargeDTO AjouteChauffeur(@RequestBody ResponsableChargeDTO responsableChargeDTO){
log.trace(" RestControlerResponsable la methode AjouteResponsable");

return  responsableChargeService.AjouteInfoResponsableChargeDTO(responsableChargeDTO);
}

///////////Modefier Responsable///////////////////////////
@PutMapping(path="/PutResponsable/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public void ModefierResponsableChargeDTO(@RequestBody ResponsableChargeDTO  responsableDTO, @PathVariable long id ) {
log.trace(" RestControlerResponsableChargeDTO la methode ModefierResponsableChargeDTO");
System.out.println("id"+id);
responsableChargeService.ModefierResponsableCharge(responsableDTO,id);

}


///////////////////////////   delete  Responsable  ///////////////////////////////

@DeleteMapping(path="/deleteResponsable/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public void DeleteResponsableDTO(@PathVariable long id ) {
log.trace(" RestControlerResponsable la methode DeleteResponsableDTO");

responsableChargeService.DeleteResponsableCharge(id);

}

////////Affichage  ResponsableDTO ////////////////
@GetMapping(path="/GetResponsable")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public List<ResponsableChargeDTO> GetResponsable(){
log.trace(" RestControlerResponsable la methode GetResponsableDTO");

return   responsableChargeService.GetResponsableChargeDTO();
}
////////Find  ResponsableChargeDTO ////////////////
@GetMapping(path="/afficheResponsableCharge/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public ResponsableChargeDTO findResponsable( @PathVariable Long id){
log.trace(" RestControlerResponsableChargeDTO la methode GetResponsableChargeDTO");

return   responsableChargeService.FindResponsableCharge(id);
}

/////////////////  AjouterResponsable/////////////////

@PostMapping(path="/AjouterResponsableCharge")
@ResponseStatus(code = HttpStatus.CREATED)

public ResponsableChargeDTO AjouterResponsableChargeDTO(@RequestBody ResponsableChargeDTO responsableChargeDTO){
log.trace(" RestControlerChauffeur la methode AjouteChauffeur");

return  responsableChargeService.AjouteInfoResponsableChargeDTO(responsableChargeDTO);
}


}

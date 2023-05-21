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

import lombok.extern.slf4j.Slf4j;
import ma.richebois.transport.dto.VehiculeDTO;
import ma.richebois.transport.service.VehiculeService;

@RestController
@CrossOrigin(origins="*")

//@EnableJpaRepositories 
@Slf4j
public class VehiculeRestControler {
 
	@Autowired
	private VehiculeService serviceVehicule;
	
///////////Ajoute AjouteVehicule///////////////////////////

	@PostMapping(path="/AjouteVehicule")
	@ResponseStatus(code = HttpStatus.CREATED)

	public VehiculeDTO AjouteVehicule(@RequestBody VehiculeDTO vehiculeDTO){
		log.trace(" RestControlerVehicule la methode AjouteChantier");
	return  serviceVehicule.AjouteInfoVehicule(vehiculeDTO);
	}
	
///////////Modefier PutVehicule///////////////////////////
@PutMapping(path="/PutVehicule/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public void ModefieVehicule(@RequestBody VehiculeDTO vehiculeDTO, @PathVariable long id ) {
	log.trace(" RestControlerVehicule la methode ModefieVehicule");

	serviceVehicule.ModefierVehicule(vehiculeDTO,id);
}
///////////////////////////   delet Vehicule  ///////////////////////////////
	 
@DeleteMapping(path="/deletVehicule/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public void DeleteVehiculeDTO(@PathVariable long id ) {
	log.trace(" RestControlerVehicule la methode DeleteVehiculeDTO");

	serviceVehicule.DeleteVehicule(id);
}

/////////Affichage  Vehicule ////////////////
@GetMapping(path="/GetVehicule")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public List<VehiculeDTO> GetVehicule(){
	log.trace(" RestControlerVehicule la methode GetVehicule");

return   serviceVehicule.GetVehicule();
}
////////Find  Vehicule ////////////////
@GetMapping(path="/afficheVehicule/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public VehiculeDTO findVehicule( @PathVariable Long id){
log.trace(" RestControlerVehicule la methode VehiculeDTO");

return   serviceVehicule.FindVehicule(id);
}
///////Find  VehiculeDTO ////////////////
@GetMapping(path="/GetVehiculeByTypeId/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public List<VehiculeDTO> GetVehiculeByTypeId( @PathVariable Long id){
log.trace(" RestControlerVehicule la methode VehiculeDTO");

return   serviceVehicule.FindVehiculeDeType(id);
}


}

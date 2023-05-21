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
import ma.richebois.transport.dto.LieuxDTO;
import ma.richebois.transport.service.LieuxService;

@RestController
@CrossOrigin(origins="*")

//@EnableJpaRepositories 
@Slf4j
public class LieuxRestController {

	@Autowired
	private LieuxService lieuxService;
	
	 
///////////Ajoute AjouteVehicule///////////////////////////

@PostMapping(path="/AjouteLieux")
@ResponseStatus(code = HttpStatus.CREATED)

public LieuxDTO AjouteLieux(@RequestBody LieuxDTO lieuxDTO){
log.trace(" RestControlerLieux la methode AjouteLieux");
return  lieuxService.AjouteInfoLieux(lieuxDTO);
}

///////////Modefier PutVehicule///////////////////////////
@PutMapping(path="/PutLieux/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public void ModefieLieux(@RequestBody LieuxDTO lieuxDTO, @PathVariable long id ) {
log.trace(" RestControlerlieux la methode ModefieLieux");

lieuxService.ModefierLieux(lieuxDTO,id);
}
///////////////////////////   delet Vehicule  ///////////////////////////////

@DeleteMapping(path="/deletLieux/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public void DeleteLieuxDTO(@PathVariable long id ) {
log.trace(" RestControlerLieux la methode DeleteLieuxDTO");

lieuxService.DeleteLieux(id);
}
/////////Affichage  Vehicule ////////////////
@GetMapping(path="/GetLieux")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public List<LieuxDTO> GetVehicule(){
log.trace(" RestControlerLieux la methode GetLieux");

return   lieuxService.GetLieux();
}
///////Find  Lieux ////////////////
@GetMapping(path="/afficheLieux/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public LieuxDTO findLieux( @PathVariable Long id){
log.trace(" RestControlerLieux la methode LieuxDTO");

return   lieuxService.FindLieux(id);
}
}

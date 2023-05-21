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
import ma.richebois.transport.dto.MarqueDTO;
import ma.richebois.transport.service.MarqueService;

@RestController
@CrossOrigin(origins="*")

//@EnableJpaRepositories 
@Slf4j
public class MarqueRestControler {

	@Autowired
	private MarqueService marqueService;
	
///////////Ajoute MarqueDTO///////////////////////////
 
@PostMapping(path="/AjouteMarque")
@ResponseStatus(code = HttpStatus.CREATED)

public MarqueDTO AjouteMarque(@RequestBody MarqueDTO marqueDTO){
log.trace(" RestControlerMarque la methode AjouteMarque");
return  marqueService.AjouteInfoMarque(marqueDTO);
}

///////////Modefier MraqueDTO///////////////////////////
@PutMapping(path="/PutMarque/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public void ModefieMarque(@RequestBody MarqueDTO marqueDTO, @PathVariable long id ) {
log.trace(" RestControlerMarque la methode Modefiemarque");

marqueService.ModefierMarque(marqueDTO,id);
}
///////////////////////////   delete marque  ///////////////////////////////

@DeleteMapping(path="/deletMarque/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public void DeleteMarqueDTO(@PathVariable long id ) {
log.trace(" RestControlerMarque la methode DeleteMarqueDTO");

marqueService.DeleteMarque(id);
}

/////////Affichage  Marque ////////////////
@GetMapping(path="/GetMarque")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public List<MarqueDTO> GetMarque(){
log.trace(" RestControlerVehicule la methode GetVehicule");

return   marqueService.GetMarque();
}
////////Find  Marque ////////////////
@GetMapping(path="/afficheMarque/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public MarqueDTO findMarque( @PathVariable Long id){
log.trace(" RestControlerMarque la methode MarqueDTO");

return   marqueService.FindMarque(id);
}
}

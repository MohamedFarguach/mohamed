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
import ma.richebois.transport.dto.TypeLieuxDTO;
import ma.richebois.transport.service.TypeLieuxService;
 
@RestController
@CrossOrigin(origins="*")

//@EnableJpaRepositories 
@Slf4j
public class TypeLieuxRestController {

	@Autowired
	private TypeLieuxService typeLieuxService;
		

///////////Ajoute TypeLieuxDTO///////////////////////////

@PostMapping(path="/AjouteTypelieux")
@ResponseStatus(code = HttpStatus.CREATED)

public TypeLieuxDTO AjouteMarque(@RequestBody TypeLieuxDTO typeLieuxDTO){
log.trace(" RestControlerTypeLieux la methode TypeLieux");
return  typeLieuxService.AjouteTypeLieux(typeLieuxDTO);
}

///////////Modefier MraqueDTO///////////////////////////
@PutMapping(path="/PutTypelieux/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public void Modefietypelieux(@RequestBody TypeLieuxDTO typelieuxDTO, @PathVariable long id ) {
log.trace(" RestControlerMarque la methode Modefiemarque");

typeLieuxService.ModefierTypeLieux(typelieuxDTO,id);
}
///////////////////////////   delet marque  ///////////////////////////////

@DeleteMapping(path="/deletTypelieux/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public void DeleteTypelieuxDTO(@PathVariable long id ) {
log.trace(" RestControlertypelieux la methode DeleteTypelieuxDTO");

typeLieuxService.DeletetypeLieux(id);
}

/////////Affichage  Typelieux ////////////////
@GetMapping(path="/GetTypelieux")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public List<TypeLieuxDTO> GetTypelieux(){
log.trace(" RestControlerTypelieux la methode GetTypelieux");

return   typeLieuxService.GetTypelieux();
}
////////Find  TypeLieux ////////////////
@GetMapping(path="/afficheTypelieux/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public TypeLieuxDTO findTypelieux( @PathVariable Long id){
log.trace(" RestControlertypelieux la methode TypelieuxDTO");

return   typeLieuxService.FindtypeLieux(id);
}
}

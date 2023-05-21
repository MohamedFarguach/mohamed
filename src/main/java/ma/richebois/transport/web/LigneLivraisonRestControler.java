package ma.richebois.transport.web;

import java.io.IOException;
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
import ma.richebois.transport.dto.LigneLivraisonDTO;
import ma.richebois.transport.service.LigneLivraisonService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins="*")
@ResponseStatus(code = HttpStatus.CREATED)

//@EnableJpaRepositories 
@Slf4j
public class LigneLivraisonRestControler {
	
	@Autowired
	private LigneLivraisonService serviceLigneLivraison;
 	 
 
/////////////////  AjouteLigneLivraisonDTO /////////////////
@PostMapping(path="/AjouteLigneLivraison")
@ResponseStatus(code = HttpStatus.CREATED)

public LigneLivraisonDTO AjouteLigneLivraisonDTO(@RequestBody LigneLivraisonDTO ligneLivraisonDTO) throws IOException{
	log.trace(" RestControlerLigneLivraison la methode AjouteLigneLivraisonDTO");

return  serviceLigneLivraison.AjouteInfoligneLivraison(ligneLivraisonDTO);
}



///////////Modefier////////////////////////////
@PutMapping(path="/PutLigneLivraison/{id}/file/{fileid}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public void ModefierLigneLivraisonDTO(@RequestBody LigneLivraisonDTO ligneLivraisonDTO, @PathVariable long id,@PathVariable long fileid ) {
	log.trace(" RestControlerLigneLivraison la methode ModefierLigneLivraisonDTO");

	serviceLigneLivraison.ModefierLigneLivraisonDTO(ligneLivraisonDTO,id,fileid);

}
///////////////////////// DELETE  LigneLivraison ///////////////////

@DeleteMapping(path="/deletLigneLivraison/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public void DeleteLigneLivraisonDTO(@PathVariable long id ) {
	log.trace(" RestControlerLigneLivraison la methode DeleteLigneLivraisonDTO");

	serviceLigneLivraison.DeleteLigneLivraison(id);

}
/////////Affichage  LigneLivraison ////////////////
@GetMapping(path="/GetLigneLivraison")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public List<LigneLivraisonDTO> GetligneLivraison(){
	log.trace(" RestControlerLigneLivraison la methode GetligneLivraison");

return   serviceLigneLivraison.GetLigneLivraison();
} 
////////Find  livraison ////////////////
@GetMapping(path="/affichelivraison/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public LigneLivraisonDTO findLigneLivraison( @PathVariable Long id){
log.trace(" RestControlerLigneLivraisonDTO la methode findLigneLivraison");

return   serviceLigneLivraison.FindLivraison(id);
}
///////////////////////////



}
////////////////////////

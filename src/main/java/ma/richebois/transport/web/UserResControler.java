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
import ma.richebois.transport.dto.UserDTO;
import ma.richebois.transport.service.UsersService;

@RestController
@CrossOrigin(origins="*")
//@EnableJpaRepositories 
@Slf4j
public class UserResControler {
 
	@Autowired
	private UsersService userservice;

///////////Ajoute ChauffeurDTO///////////////////////////

@PostMapping(path="/AjouteUser")
@ResponseStatus(code = HttpStatus.CREATED)

public UserDTO AjouteChantier(@RequestBody UserDTO userDTO){
log.trace(" RestControlerChantier la methode AjouteChantier");
return  userservice.AjouteInfoUser(userDTO);
}

///////////Modefier ChauffeurDTO///////////////////////////
@PutMapping(path="/PutUser/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public void ModefieVehicule(@RequestBody UserDTO userDTO, @PathVariable long id ) {
log.trace(" RestControlerChantier la methode ModefieVehicule");

userservice.ModefierUser(userDTO,id);
}
///////////////////////////   delet Vehicule  ///////////////////////////////

@DeleteMapping(path="/deletUser/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public void DeleteVehiculeDTO(@PathVariable long id ) {
log.trace(" RestControlerChantier la methode DeleteUserDTO");

userservice.DeleteUser(id);
}
/////////Affichage  getuser ////////////////
@GetMapping(path="/GetUser")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public List<UserDTO> GetUtilisateurs(){
log.trace(" RestControlerChantier la methode GetVehicule");

return   userservice.GetUser();
} 
////////Find  Utlisateurs ////////////////
@GetMapping(path="/afficheUser/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public UserDTO findUtilisateurs( @PathVariable Long id){
log.trace(" RestControlerUtilisateurs la methode UserDTO");

return   userservice.FindUtlisateurs(id);
}
}

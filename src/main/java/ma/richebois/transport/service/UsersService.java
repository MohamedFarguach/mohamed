package ma.richebois.transport.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ma.richebois.transport.dto.UserDTO;
import ma.richebois.transport.entity.Users;
import ma.richebois.transport.mapper.MyMapper;
import ma.richebois.transport.repositorie.AppUserRepository;
@Transactional
@Slf4j
@Service

public class UsersService {

@Autowired
private  AppUserRepository appUserRepository;
@Autowired
private ModelMapper modelMapper; 
@Autowired
private  MyMapper myMapper;
@Autowired
	private PasswordEncoder passwordEncoder;


////////Ajoute Info User ///////////////////

public UserDTO AjouteInfoUser(UserDTO userDTO){
Users  user  = modelMapper.map(userDTO, Users.class);
try {
	 String pw=user.getPassword();
	 user.setPassword(passwordEncoder.encode(pw));
	
appUserRepository.save(user);

userDTO.setId(user.getId());
System.out.println("veee"+user);
return  userDTO;

} catch (Exception e) {
//TODO: handle exception
log.trace(" vehicule la methode AjouteInfovehicule"+user);

System.out.println("Ajoute Info Vehicule   n "+e);
}
return  null;


} 
//////////////////Modefier User //////////////////////

public UserDTO ModefierUser(UserDTO userDTO,long id){
Users user = modelMapper.map(userDTO, Users.class);
try {
user.setId(id);
String pw=user.getPassword();
user.setPassword(passwordEncoder.encode(pw));
appUserRepository.save(user);
userDTO.setId(user.getId());

return   userDTO;

} catch (Exception e) {
//TODO: handle exception
log.trace("  la methode ModefiervehiculeDTO"+user);

System.out.println("Modefier Vehicule "+e);
}
return  null;


} 
///////// Affichage User ////////////////
public List<UserDTO> GetUser(){
List<Users> user =   appUserRepository.findAll();
try {

return     user.stream().map(myMapper::toUserDTO).collect(Collectors.toList());
} catch (Exception e) {
log.trace("  la methode GetVehicule"+user);
System.out.println("GetVehicule methode exception"+e);
return null;

}

}
////////delete User //////////////::
public  void DeleteUser(Long id) {	
log.trace("  la methode DeleteUser");
appUserRepository.deleteById(id);
}
/////////////  FindByID///////////////
public UserDTO FindUtlisateurs(Long id){
Optional<Users> user =   appUserRepository.findById(id);
UserDTO userDTO  = myMapper.FindByUsers(user);
try {
return userDTO;
} catch (Exception e) {
log.trace("  la methode FindLieux"+userDTO);
System.out.println("FindLieux methode exception"+e);
return null;


}
}
}

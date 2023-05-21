package ma.richebois.transport.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ma.richebois.transport.dto.TypeVehiculeDTO;
import ma.richebois.transport.entity.TypeVehicule;
import ma.richebois.transport.mapper.MyMapper;
import ma.richebois.transport.repositorie.TypeVehiculeRepository;

@Transactional
@Slf4j
@Service
public class TypeVehiculeService {
	@Autowired
	  private TypeVehiculeRepository typevehiculeRepository;
	@Autowired
	 private ModelMapper modelMapper; 
	@Autowired
	  private  MyMapper myMapper;

////////Ajoute Info typevehiculeDTO ///////////////////

public TypeVehiculeDTO AjouteTypeVehicule(TypeVehiculeDTO typevehiculeDTO){
TypeVehicule  typevehicule  = modelMapper.map(typevehiculeDTO, TypeVehicule.class);
try {
typevehiculeRepository.save(typevehicule);
typevehiculeDTO.setId(typevehicule.getId());
System.out.println("veee"+typevehicule);
return  typevehiculeDTO;

} catch ( MappingException r) {
//TODO: handle exception
System.out.println("mapping exception"+r);
log.trace("la methode AjouteInfotypevehiculeDTO"+typevehiculeDTO);

System.out.println("Ajoute Info TypeVehicule   n "+r);
}
return  null;


} 
//////////////////Modefier typevehiculeDTO //////////////////////

public TypeVehiculeDTO ModefierTypeLieux(TypeVehiculeDTO typeVehiculeDTO,long id){
	TypeVehicule typeVehicule = modelMapper.map(typeVehiculeDTO, TypeVehicule.class);
try {
	typeVehicule.setId(id);
typevehiculeRepository.save(typeVehicule);
typeVehiculeDTO.setId(typeVehicule.getId());

return  typeVehiculeDTO;

} catch (Exception e) {
//TODO: handle exception
log.trace("  la methode ModefiertypevehiculeDTO"+typeVehiculeDTO);

System.out.println("Modefier typevehicule "+e);
}
return  null;
}
////////delete typevehicule //////////////::
public  void DeletetypeVehicule(Long id) {	
log.trace("  la methode Deletevehicule");
typevehiculeRepository.deleteById(id);

} 
///////// Affichage typelieux ////////////////
public List<TypeVehiculeDTO> GetTypeVehicule(){
List<TypeVehicule> typevehicule =   typevehiculeRepository.findAll();
try {

return     typevehicule.stream().map(myMapper::toTypevihiculeDTO).collect(Collectors.toList());
} catch (Exception e) {
log.trace("  la methode Gettypevehicule"+typevehicule);
System.out.println("Gettypevehicule methode exception"+e);
return null;

}
}
/////////////  FindByID///////////////
public TypeVehiculeDTO FindtypeVehicule(Long id){
Optional<TypeVehicule> typelieux =   typevehiculeRepository.findById(id);
TypeVehiculeDTO typevehiculeDTO  = myMapper.FindByTypeVehicule(typelieux);
try {
return typevehiculeDTO;
} catch (Exception e) {
log.trace("  la methode Gettypevehicule"+typevehiculeDTO);
System.out.println("Gettypevehicule methode exception"+e);
return null;



}
}
}

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
import ma.richebois.transport.dto.VehiculeDTO;
import ma.richebois.transport.entity.Vehicule;
import ma.richebois.transport.mapper.MyMapper;
import ma.richebois.transport.repositorie.VehiculeRepository;

@Transactional
@Slf4j
@Service

public class VehiculeService {
	@Autowired
	  private VehiculeRepository vehiculeRepository;
	@Autowired
	 private ModelMapper modelMapper; 
	@Autowired
	  private  MyMapper myMapper;


////////Ajoute Info Vehicule ///////////////////

public VehiculeDTO AjouteInfoVehicule(VehiculeDTO vehiculeDTO){
	//System.out.println(vehiculeDTO.getMarq().getId());
Vehicule  vehicule  = modelMapper.map(vehiculeDTO, Vehicule.class);
try {
vehiculeRepository.save(vehicule);
vehiculeDTO.setId(vehicule.getId());
System.out.println("veee"+vehicule);
return  vehiculeDTO;

} catch ( MappingException r) {
//TODO: handle exception
	System.out.println("mapping exception"+r);
log.trace(" vehicule la methode AjouteInfovehicule"+vehicule);

System.out.println("Ajoute Info Vehicule   n "+r);
}
return  null;


} 
//////////////////Modefier Vehicule //////////////////////

public VehiculeDTO ModefierVehicule(VehiculeDTO vehiculeDTO,long id){
Vehicule vehicule = modelMapper.map(vehiculeDTO, Vehicule.class);
try {
vehicule.setId(id);
vehiculeRepository.save(vehicule);
vehiculeDTO.setId(vehicule.getId());

return  vehiculeDTO;

} catch (Exception e) {
//TODO: handle exception
log.trace("  la methode ModefiervehiculeDTO"+vehicule);

System.out.println("Modefier Vehicule "+e);
}
return  null;


} 
///////// Affichage Vehicule ////////////////
public List<VehiculeDTO> GetVehicule(){
List<Vehicule> vehicule =   vehiculeRepository.findAll();
try {

return     vehicule.stream().map(myMapper::toVehiculeDTO).collect(Collectors.toList());
} catch (Exception e) {
log.trace("  la methode GetVehicule"+vehicule);
System.out.println("GetVehicule methode exception"+e);
return null;

}
}
////////delete Vehicule //////////////::
public  void DeleteVehicule(Long id) {	
log.trace("  la methode DeleteVehicule");
vehiculeRepository.deleteById(id);
}
/////////////  FindByID///////////////
public VehiculeDTO FindVehicule(Long id){
Optional<Vehicule> vehicule =   vehiculeRepository.findById(id);
VehiculeDTO vehiculeDTO  = myMapper.FindByVehicule(vehicule);
try {
return vehiculeDTO;
} catch (Exception e) {
log.trace("  la methode Getchauffeur"+vehiculeDTO);
System.out.println("Getchauffeur methode exception"+e);
return null;


}
}
/////////////  FindByID///////////////
public List<VehiculeDTO> FindVehiculeDeType(Long id){
List<Vehicule> vehicule =   vehiculeRepository.find2(id);
//VehiculeDTO vehiculeDTO  = myMapper.FindByVehiculeBytype(vehicule);
try {
	return     vehicule.stream().map(myMapper::toVehiculeDTO).collect(Collectors.toList());

} catch (Exception e) {
log.trace("  la methode Getchauffeur"+vehicule);
System.out.println("Getchauffeur methode exception"+e);
return null;


}
}

}
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
import ma.richebois.transport.dto.TypeLieuxDTO;
import ma.richebois.transport.entity.TypeLieux;
import ma.richebois.transport.mapper.MyMapper;
import ma.richebois.transport.repositorie.TypeLieuxRepository;

@Transactional
@Slf4j
@Service
public class TypeLieuxService {
 //
	@Autowired
	  private TypeLieuxRepository typelieuxRepository;
	@Autowired
	 private ModelMapper modelMapper; 
	@Autowired
	  private  MyMapper myMapper;

////////Ajoute Info typelieuxDTO ///////////////////

public TypeLieuxDTO AjouteTypeLieux(TypeLieuxDTO typelieuxDTO){
TypeLieux  typelieux  = modelMapper.map(typelieuxDTO, TypeLieux.class);
try {
	typelieuxRepository.save(typelieux);
	typelieuxDTO.setId(typelieux.getId());
System.out.println("veee"+typelieux);
return  typelieuxDTO;

} catch ( MappingException r) {
//TODO: handle exception
System.out.println("mapping exception"+r);
log.trace("la methode AjouteInfotypelieuxDTO"+typelieuxDTO);

System.out.println("Ajoute Info Vehicule   n "+r);
}
return  null;


} 
//////////////////Modefier typelieuxDTO //////////////////////

public TypeLieuxDTO ModefierTypeLieux(TypeLieuxDTO typelieuxDTO,long id){
TypeLieux typelieux = modelMapper.map(typelieuxDTO, TypeLieux.class);
try {
	typelieux.setId(id);
	typelieuxRepository.save(typelieux);
	typelieuxDTO.setId(typelieux.getId());

return  typelieuxDTO;

} catch (Exception e) {
//TODO: handle exception
log.trace("  la methode ModefiertypelieuxDTO"+typelieuxDTO);

System.out.println("Modefier typelieux "+e);
}
return  null;


} 
///////// Affichage typelieux ////////////////
public List<TypeLieuxDTO> GetTypelieux(){
List<TypeLieux> typelieux =   typelieuxRepository.findAll();
try {

return     typelieux.stream().map(myMapper::toTypelieuxDTO).collect(Collectors.toList());
} catch (Exception e) {
log.trace("  la methode Gettypelieux"+typelieux);
System.out.println("GettypeLieux methode exception"+e);
return null;

}
}
////////delete typelieux //////////////::
public  void DeletetypeLieux(Long id) {	
log.trace("  la methode DeleteLieux");
typelieuxRepository.deleteById(id);
}
/////////////  FindByID///////////////
public TypeLieuxDTO FindtypeLieux(Long id){
Optional<TypeLieux> typelieux =   typelieuxRepository.findById(id);
TypeLieuxDTO typelieuxDTO  = myMapper.FindByTypelieux(typelieux);
try {
return typelieuxDTO;
} catch (Exception e) {
log.trace("  la methode Gettypelieux"+typelieuxDTO);
System.out.println("Gettypelieux methode exception"+e);
return null;


}
}
}

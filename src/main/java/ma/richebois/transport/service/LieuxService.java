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
import ma.richebois.transport.dto.LieuxDTO;
import ma.richebois.transport.entity.Lieux;
import ma.richebois.transport.mapper.MyMapper;
import ma.richebois.transport.repositorie.LieuxRepository;

@Transactional
@Slf4j
@Service
public class LieuxService {
	@Autowired
	  private LieuxRepository lieuxRepository;
	@Autowired
	 private ModelMapper modelMapper; 
	@Autowired
	  private  MyMapper myMapper;

////////Ajoute Info Lieux ///////////////////
	
public LieuxDTO AjouteInfoLieux(LieuxDTO lieuxDTO){
//System.out.println(vehiculeDTO.getMarq().getId());
Lieux  lieux  = modelMapper.map(lieuxDTO, Lieux.class);
try {
	lieuxRepository.save(lieux);
	lieuxDTO.setId(lieux.getId());
System.out.println("veee"+lieux);
return  lieuxDTO;

} catch ( MappingException r) {
//TODO: handle exception
System.out.println("mapping exception"+r);
log.trace(" vehicule la methode AjouteInfovehicule"+lieuxDTO);

System.out.println("Ajoute Info Vehicule   n "+r);
}
return  null;


} 
//////////////////Modefier Lieux //////////////////////

public LieuxDTO ModefierLieux(LieuxDTO lieuxDTO,long id){
Lieux lieux = modelMapper.map(lieuxDTO, Lieux.class);
try {
	lieux.setId(id);
	lieuxRepository.save(lieux);
	lieuxDTO.setId(lieux.getId());

return  lieuxDTO;

} catch (Exception e) {
//TODO: handle exception
log.trace("  la methode ModefierlieuxDTO"+lieuxDTO);

System.out.println("Modefier lieux "+e);
}
return  null;


} 
///////// Affichage lieux ////////////////
public List<LieuxDTO> GetLieux(){
List<Lieux> lieux =   lieuxRepository.findAll();
try {

return     lieux.stream().map(myMapper::toLieuxDTO).collect(Collectors.toList());
} catch (Exception e) {
log.trace("  la methode Getlieux"+lieux);
return null;

}
}
////////delete Lieux //////////////::
public  void DeleteLieux(Long id) {	
log.trace("  la methode DeleteLieux");
lieuxRepository.deleteById(id);
}
/////////////  FindByID///////////////
public LieuxDTO FindLieux(Long id){
Optional<Lieux> lieux =   lieuxRepository.findById(id);
LieuxDTO lieuxDTO  = myMapper.FindByLieux(lieux);
try {
return lieuxDTO;
} catch (Exception e) {
log.trace("  la methode Getlieux"+lieuxDTO);
return null;


}
}
}

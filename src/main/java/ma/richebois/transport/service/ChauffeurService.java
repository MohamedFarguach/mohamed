package ma.richebois.transport.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ma.richebois.transport.dto.ChauffeurDTO;
import ma.richebois.transport.entity.Chauffeur;
import ma.richebois.transport.mapper.MyMapper;
import ma.richebois.transport.repositorie.ChauffeurRepository;

@Transactional
@Slf4j
@Service
public class ChauffeurService {
	 //
	@Autowired
	  private ChauffeurRepository chauffeurRepository;
	@Autowired
	 private ModelMapper modelMapper; 
	 
	
	@Autowired
	  private  MyMapper myMapper;



////////Ajoute Info Chauffeur ///////////////////

public ChauffeurDTO AjouteInfoChauffeur(ChauffeurDTO chauffeurDTO){
	Chauffeur  chauffeur  = modelMapper.map(chauffeurDTO, Chauffeur.class);
  try {
 	 chauffeurRepository.save(chauffeur);
 	 chauffeurDTO.setId(chauffeur.getId());

  return  chauffeurDTO;

 } catch (Exception e) {
 	log.trace(" chauffeur la methode AjouteInfochauffeur"+chauffeur);

System.out.println("Ajoute Info Chauffeur exception   n "+e);
  }
 return  null;

}

//////////////////Modefier Chauffeur //////////////////////

public ChauffeurDTO  ModefierChauffeur(ChauffeurDTO  chauffeurDTO,long id){
Chauffeur chauffeur = modelMapper.map(chauffeurDTO, Chauffeur.class);
try {
chauffeur.setId(id);
chauffeurRepository.save(chauffeur);
((ChauffeurDTO) chauffeurDTO).setId(chauffeur.getId());

return  chauffeurDTO;

} catch (Exception e) {
//TODO: handle exception
log.trace("  la methode ModefierchauffeurDTO"+chauffeur);

System.out.println("Modefier Chauffeur "+e);
}
return  null;


} 

///////// Affichage Chauffeur ////////////////
public List<ChauffeurDTO> GetChauffeur(){
List<Chauffeur> chauffeur =   chauffeurRepository.findAll();
try {

return     chauffeur.stream().map(myMapper::toChauffeurDTO).collect(Collectors.toList());
} catch (Exception e) {
log.trace("  la methode Getchauffeur"+chauffeur);
System.out.println("Getchauffeur methode exception"+e);
return null;

}

}

////////delete chauffeur //////////////::
public  void Deletechauffeur(Long id) {	
log.trace("  la methode Deletechauffeur");
chauffeurRepository.deleteById(id);
}
/////////////  FindChauffeurBYID///////////////
public ChauffeurDTO FindChauffeur(Long id){
Optional<Chauffeur> chauffeur =   chauffeurRepository.findById(id);
ChauffeurDTO chauffeurDTO  = myMapper.toChauffeurDTO2(chauffeur);
try {
return chauffeurDTO;
//return     (ChauffeurDTO) chauffeur.stream().map(myMapper::toChauffeurDTO).collect(Collectors.toList());
} catch (Exception e) {
log.trace("  la methode Getchauffeur"+chauffeur);
System.out.println("Getchauffeur methode exception"+e);
return null;

}
}

}

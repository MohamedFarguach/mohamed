package ma.richebois.transport.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ma.richebois.transport.dto.MarqueDTO;
import ma.richebois.transport.entity.Marque;
import ma.richebois.transport.mapper.MyMapper;
import ma.richebois.transport.repositorie.MarqueRepository;

@Transactional
@Slf4j
@Service
public class MarqueService {
 ///////
	@Autowired
    private MarqueRepository marqueRepository;
	@Autowired
	 private ModelMapper modelMapper; 
	@Autowired
	  private  MyMapper myMapper;

////////Ajoute Info Marque ///////////////////

	
public MarqueDTO AjouteInfoMarque(MarqueDTO marqueDTO){

		Marque  marque  = modelMapper.map(marqueDTO, Marque.class);
		try {
			
			marqueRepository.save(marque);
			marqueDTO.setId(marque.getId());

		return  marqueDTO;

		} catch (Exception e) {
		//TODO: handle exception
		log.trace("  la methode AjouteInfoMarque"+marqueDTO);

		System.out.println("Ajoute Info Marque exception   n "+e);
		}
		return  null;

		
	}
	




//////////////////Modefier Marque //////////////////////

public MarqueDTO  ModefierMarque(MarqueDTO  marqueDTO,long id){
Marque marque = modelMapper.map(marqueDTO, Marque.class);
try {
	marque.setId(id);
marqueRepository.save(marque);
 marqueDTO.setId(marque.getId());

return  marqueDTO;

} catch (Exception e) {
//TODO: handle exception
log.trace("  la methode ModefierMarque"+marqueDTO);

System.out.println("Modefier Marque "+e);
}
return  null;


} 

///////// Affichage Chauffeur ////////////////
public List<MarqueDTO> GetMarque(){
List<Marque> marque =   marqueRepository.findAll();
try {

return     marque.stream().map(myMapper::toMarqueDTO).collect(Collectors.toList());
} catch (Exception e) {
log.trace("  la methode GetMarque"+marque);
System.out.println("GetMarque methode exception"+e);
return null;

}

}

////////delete chauffeur //////////////::
public  void DeleteMarque(Long id) {	
log.trace("  la methode DeleteMarque");
marqueRepository.deleteById(id);
}
////////////FindByID///////////////
public MarqueDTO FindMarque(Long id){
Optional<Marque> marque =   marqueRepository.findById(id);
MarqueDTO marqueDTO  = myMapper.FindByMarque(marque);
try {
return marqueDTO;
} catch (Exception e) {
log.trace("  la methode Getmarque"+marqueDTO);
System.out.println("GetMarque methode exception"+e);
return null;


}
}
}
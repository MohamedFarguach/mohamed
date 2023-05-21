package ma.richebois.transport.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import ma.richebois.transport.dto.FilelivraisonDTO;
import ma.richebois.transport.dto.LigneLivraisonDTO;
import ma.richebois.transport.entity.Filelivraison;
import ma.richebois.transport.entity.LigneLivraison;
import ma.richebois.transport.mapper.MyMapper;
import ma.richebois.transport.repositorie.FilelivraisonRepository;
import ma.richebois.transport.repositorie.LigneLivraisonRepository;

@Transactional
@Slf4j
@Service
public class LigneLivraisonService { 
	@Autowired
	private FilelivraisonRepository filelivraisonRepository;
	@Autowired
	  private LigneLivraisonRepository ligneLivraisonRepository;
	@Autowired
	 private ModelMapper modelMapper; 
	@Autowired
	  private  MyMapper myMapper;

/////////////////////// 
////////Ajoute Info LigneLivraison ///////////////////
Long id;
public LigneLivraisonDTO AjouteInfoligneLivraison(LigneLivraisonDTO ligneLivraisonDTO) throws IOException{
	
	FilelivraisonDTO filelivraison = new FilelivraisonDTO();
	filelivraison.setId(id);
	ligneLivraisonDTO.setFilelivraison(filelivraison);
LigneLivraison  ligneLivraison  = modelMapper.map(ligneLivraisonDTO, LigneLivraison.class);
try {
	
	 
ligneLivraisonRepository.save(ligneLivraison);
 ligneLivraisonDTO.setId(ligneLivraison.getId());
 
 this.id = ligneLivraison.getId();
return  ligneLivraisonDTO;

} catch (Exception e) {
// TODO: handle exception
log.trace(" ligneLivraison la methode AjouteInfoligneLivraison"+ligneLivraison);

System.out.println("Ajoute Info LigneLivraison exception   n "+e);
}
return  null;


} 

long id2;
//////////////////Modefier LigneLivraison //////////////////////

public LigneLivraisonDTO ModefierLigneLivraisonDTO(LigneLivraisonDTO ligneLivraisonDTO,long id,long fileid){
	FilelivraisonDTO filelivraison = new FilelivraisonDTO();
	filelivraison.setId(fileid);
	ligneLivraisonDTO.setFilelivraison(filelivraison);
LigneLivraison ligneLivraison = modelMapper.map(ligneLivraisonDTO, LigneLivraison.class);
try {
ligneLivraison.setId(id);
//ligneLivraisonDTO.setId(id);
ligneLivraisonRepository.save(ligneLivraison);
ligneLivraisonDTO.setId(ligneLivraison.getId());

return  ligneLivraisonDTO;

} catch (Exception e) {
// TODO: handle exception
log.trace("  la methode ModefierLigneLivraisonDTO"+ligneLivraison);

}
return  null;


} 


////////delete LigneLivraison //////////////


public  void DeleteLigneLivraison(Long id) {	
log.trace("  la methode DeleteLigneLivraison");
ligneLivraisonRepository.deleteById(id);
}


////////////////////upload file/////////////////////////

public Filelivraison saveFile(MultipartFile file) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    Filelivraison fileEntity = new Filelivraison();
  
    fileEntity.setFileName(fileName);
    fileEntity.setFileType(file.getContentType());
    fileEntity.setData(file.getBytes());
    Filelivraison fileEntity2 = filelivraisonRepository.save(fileEntity);
	this.id = fileEntity2.getId();
	return fileEntity2;
 
}
////////////////////// Modefier file ////////////////////

public Filelivraison ModefierFile(MultipartFile file,Long idd) throws IOException {
String fileName = StringUtils.cleanPath(file.getOriginalFilename());
Filelivraison fileEntity = new Filelivraison();
fileEntity.setId(idd);
fileEntity.setFileName(fileName);
fileEntity.setFileType(file.getContentType());
fileEntity.setData(file.getBytes());
Filelivraison fileEntity2 = filelivraisonRepository.save(fileEntity);
this.id2 = fileEntity2.getId();
return fileEntity2;

}



///////// Affichage LigneLivraison ////////////////
public List<LigneLivraisonDTO> GetLigneLivraison(){
List<LigneLivraison> ligneLivraison =   ligneLivraisonRepository.findAll();
try {

return     ligneLivraison.stream().map(myMapper::toLigneLivraisonDTO).collect(Collectors.toList());
} catch (Exception e) {
log.trace("  la methode GetLigneLivraison"+ligneLivraison);

System.out.println("GetLigneLivraison methode exception"+e);
return null;

} 

}
/////////////  FindLivraison///////////////
public LigneLivraisonDTO FindLivraison(Long id){
Optional<LigneLivraison> ligneLivraison =   ligneLivraisonRepository.findById(id);
LigneLivraisonDTO ligneLivraisonDTO  = myMapper.FindLivraisonDTO(ligneLivraison);
try {
return ligneLivraisonDTO;
} catch (Exception e) {
log.trace("  la methode FindLivraison"+ligneLivraisonDTO);
System.out.println("FindLivraison methode exception"+e);
return null;

}
}


}

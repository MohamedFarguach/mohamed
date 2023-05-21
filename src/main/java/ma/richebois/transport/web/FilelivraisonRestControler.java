package ma.richebois.transport.web;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.richebois.transport.entity.Filelivraison;
import ma.richebois.transport.service.FileService;
import ma.richebois.transport.service.LigneLivraisonService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins="*")

//@EnableJpaRepositories 
@Slf4j
public class FilelivraisonRestControler {
	
	@Autowired
   private	FileService fileService;
	@Autowired
	private LigneLivraisonService serviceLigneLivraison;
	 
	
////////////////////upload file/////////////////////////
	
	  @PostMapping(path="/upload") 
	  public ResponseEntity<Filelivraison> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
	  Filelivraison fileEntity = serviceLigneLivraison.saveFile(file);
	  return ResponseEntity.ok().body(fileEntity); }
	 
//////////////////////////////////////////////
	  @PutMapping(path="/upload/{id}") 
	  public ResponseEntity<Filelivraison> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable long id ) throws IOException {
		 
	  Filelivraison fileEntity = serviceLigneLivraison.ModefierFile(file,id); 
	  return ResponseEntity.ok().body(fileEntity);
	  }
	  

///////////////////////////   delete  file  ///////////////////////////////

@DeleteMapping(path="/deletfile/{id}")
@ResponseStatus(code = HttpStatus.ACCEPTED)

public void DeleteFile(@PathVariable long id ) {
log.trace(" DeleteFile la methode DeleteFile");

fileService.DeleteFile(id);

}
////////////////////upload file/////////////////////////


//////////////////////
@GetMapping("/bytes/{id}")
public ResponseEntity<byte[]> getBytes(@PathVariable Long id) throws IOException {
    byte[] bytes;
	try {
		bytes = fileService.getFileFromTable(id);
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    headers.setContentDisposition(ContentDisposition.builder("attachment").filename("bytefile").build());
	    return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		log.trace(" la methode getBytes");

	}
	return null;
    
}
}


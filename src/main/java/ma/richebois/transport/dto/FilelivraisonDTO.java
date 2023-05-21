package ma.richebois.transport.dto;


import javax.persistence.Lob;

import lombok.Data;
 
@Data
public class FilelivraisonDTO {

	 private Long id;

	    private String fileName;

	    private String fileType;

	    @Lob
	    private byte[] data;
}

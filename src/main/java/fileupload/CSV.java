package fileupload;

import org.apache.tika.Tika;


import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class CSV {
	
	public boolean isCSVFile(MultipartFile file) throws IOException {
	    Tika tika = new Tika();
	    String mimeType = tika.detect(file.getInputStream());
	    return mimeType.equals("text/csv");
	}

}

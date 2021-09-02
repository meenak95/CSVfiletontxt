package net.meena.spring.batch;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextFileWriting {
    private static final Logger LOGGER = LoggerFactory.getLogger(TextFileWriting.class);
    validations v=new validations();
public void TextFileWritingmethod(List<? extends SimCardDTO> list) {
     try {
    	LOGGER.info("Writing students test================="+list.size());
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");  
    	LocalDateTime now = LocalDateTime.now();  
    	String filename="Simcarddate"+dtf.format(now);
    	
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        } 
        FileWriter writer = new FileWriter("C:\\Users\\meena.kannan\\Documents\\"+filename+".txt",true);
        
        for(SimCardDTO checklist:list) {
        	 String validationerror=v.validateRegistrationParameters(checklist);
        	 if(v.validateRegistrationParameters(checklist).equals("")) {
        	 writer.write(checklist.getMSISDN()+"|"+checklist.getSimType()+"|"+checklist.getName()+"|"+checklist.getDateOfBirth()+"|"+checklist.getGender()+"|"+checklist.getAddress()+"|"+checklist.getIdNumber());
             writer.write("\r\n");  
             SMSsending(checklist);
             LOGGER.info(checklist.getMSISDN()+"- registred Sucessfully registered");
        	 }else {
        	 LOGGER.info(validationerror);
        	 }
        }
          writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
}

	private void SMSsending(SimCardDTO s) {
		
		if(s.getGender().equals("F")) {
			LOGGER.info("Hi Mam, Your SIMCARD - "+s.getMSISDN()+" Sucessfully registered");
		}else {
			LOGGER.info("Hi Sir, Your SIMCARD - "+s.getMSISDN()+" Sucessfully registered");
		}
	}   
	 
	
}

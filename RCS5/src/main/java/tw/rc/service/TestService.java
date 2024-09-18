package tw.rc.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tw.rc.model.Test;
import tw.rc.repository.TestRepository;

@Service
public class TestService {
	//上船圖檔的service
	@Autowired
	private TestRepository testRepository;
	
	public Test uploadFile(MultipartFile file) throws IOException{
		String filename = file.getOriginalFilename();
		byte[] uploadData = file.getBytes();
		
		Test test = new Test("RC", uploadData);
		
		return testRepository.save(test);
		
 	}
}

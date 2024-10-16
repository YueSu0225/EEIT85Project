package tw.Final.FinalS1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.Final.FinalS1.model.ContentManagementModel;
import tw.Final.FinalS1.repository.ContentManagementRepository;

@Service
public class ContentManagementService {
	
	@Autowired
	private ContentManagementRepository CMRepository;
	
	public ContentManagementModel addContent(ContentManagementModel content) {
		content.setName(content.getName());
		content.setImage(content.getImage());
		content.setDescription(content.getDescription());
		content.setDescription2(content.getDescription2());		
		
		return CMRepository.save(content);
	}
	
	public Optional<ContentManagementModel> getContentById(Long id){
		 return CMRepository.findById(id);
	}

	public List<ContentManagementModel> getAllContent() {
        return CMRepository.findAll();
    }


}

package tw.Final.FinalS1.controller;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import tw.Final.FinalS1.dto.ContentManagementDto;
import tw.Final.FinalS1.model.ContentManagementModel;
import tw.Final.FinalS1.service.ContentManagementService;

@RestController
@RequestMapping("/cm")
public class ContentManagementController {
	@Autowired
	private ContentManagementService contentService;
	
	@PostMapping("/add")
	public String addCotent(@ModelAttribute ContentManagementDto contentDto) throws IOException {
		ContentManagementModel content;
		if(contentDto.getId() != null) {
			content = contentService.getContentById(contentDto.getId()).orElse(new ContentManagementModel());
		} else {
			content = new ContentManagementModel();
		}
		
		System.out.println("step1");
		
		content.setName(contentDto.getName());
		content.setDescription(contentDto.getDescription());
		content.setDescription2(contentDto.getDescription2());
		
//		if(contentDto.getImage() != null && !contentDto.getImage().isEmpty()) {
//			String encodeImage = Base64.getEncoder().encodeToString(contentDto.getImage().getBytes());
//			content.setImage(encodeImage);		
//		} else {
//			content.setImage(null);
//		}
		
		 if (contentDto.getImage() != null && !contentDto.getImage().isEmpty()) {
		        byte[] bytes = contentDto.getImage().getBytes();
		        String encodeImage = Base64.getEncoder().encodeToString(bytes);
		        content.setImage(encodeImage);
		    } else {
		        content.setImage(null);
		    }
		
		System.out.println("step2");
		
		contentService.addContent(content);
		return "redirect:/cm";

	}
	
	 @GetMapping("/edit/{id}")
	    public String editContent(@PathVariable("id") Long id, Model model) {
	        Optional<ContentManagementModel> content = contentService.getContentById(id);
	        if (content.isPresent()) {
	            model.addAttribute("content", content.get());
	        } else {
	            return "redirect:/admin/content";
	        }
	        return "content-edit";
	    }



}

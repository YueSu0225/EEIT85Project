package tw.Final.FinalS1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.Final.FinalS1.model.Category;
import tw.Final.FinalS1.repository.CategoryRepository;


@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> getAllCategories(){
		return categoryRepository.findAll(); 
	}
	
	public Category getCategoryById(Long id) {
	    return categoryRepository.findById(id).orElse(null);
	}
	
	public Category addCategory(Category category) {
		return categoryRepository.save(category);
	}
	
	
}
	
	
			
	 
	

	
	


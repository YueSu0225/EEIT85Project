package tw.Final.FinalS1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.Final.FinalS1.model.Type;
import tw.Final.FinalS1.repository.TypeRepository;

@Service
public class TypeService {

	@Autowired
	private TypeRepository typeRepository;
	
	public List<Type>getAllTypes(){
		return typeRepository.findAll();
	}
	
	public Type getTypeById(Long id) {
		return typeRepository.findById(id).orElse(null);
	}
	
	public Type addType(Type type) {
		return typeRepository.save(type);
	}
	
	
}

package tw.Final.FinalS1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.Final.FinalS1.model.Size;
import tw.Final.FinalS1.service.SizeService;

@RestController
@RequestMapping("/sizes")
public class SizeController {
	
	@Autowired
	private SizeService sizeService;
	
	@GetMapping
	public List<Size>getAllSizes(){
		return sizeService.getAllSizes();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Size> getSizeById(@PathVariable Long id){
		Size size = sizeService.getSizeById(id);
		if(size!=null) {
			return ResponseEntity.ok(size);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping(value = "/add", consumes = "application/json")
	public Size addSize(@RequestBody Size size) {
	    return sizeService.addSize(size);
	}

   
	
	
}

package tw.Final.FinalS1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.Final.FinalS1.model.Size;
import tw.Final.FinalS1.repository.SizeRepository;


@Service
public class SizeService {
    @Autowired
    private SizeRepository sizeRepository;

    public List<Size> getAllSizes() {
        return sizeRepository.findAll();
    }

    public Size getSizeById(Long id) {
        return sizeRepository.findById(id).orElse(null);
    }

    public Size addSize(Size size) {
        return sizeRepository.save(size);
    }

   
}


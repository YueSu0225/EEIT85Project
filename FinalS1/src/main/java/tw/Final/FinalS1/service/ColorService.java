package tw.Final.FinalS1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.Final.FinalS1.model.Color;
import tw.Final.FinalS1.repository.ColorRepository;

@Service

public class ColorService {
    @Autowired
    private ColorRepository colorRepository;

    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    public Color getColorById(Long id) {
        return colorRepository.findById(id).orElse(null);
    }

    public Color addColor(Color color) {
        return colorRepository.save(color);
    }

    
}


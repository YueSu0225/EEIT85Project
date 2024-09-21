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

import tw.Final.FinalS1.model.Color;
import tw.Final.FinalS1.service.ColorService;

@RestController
@RequestMapping("/colors")
public class ColorController {
    @Autowired
    private ColorService colorService;

    @GetMapping
    public List<Color> getAllColors() {
        return colorService.getAllColors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Color> getColorById(@PathVariable Long id) {
        Color color = colorService.getColorById(id);
        if (color != null) {
            return ResponseEntity.ok(color);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<Color> addColor(@RequestBody Color color) {
        System.out.println("Received color: " + color.getName()); // 打印接收到的color

        Color newColor = colorService.addColor(color);
        return ResponseEntity.ok(newColor);
    }



   
}


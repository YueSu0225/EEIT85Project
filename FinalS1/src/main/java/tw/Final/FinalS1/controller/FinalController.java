package tw.Final.FinalS1.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.Final.FinalS1.model.FinalMember;
import tw.Final.FinalS1.repository.Finalface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/finalspring")
public class FinalController {

    @Autowired
    private Finalface finalface;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getMembers(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        String keyPattern = (key != null && !key.isEmpty()) ? key : "";
        
        Page<FinalMember> memberPage = finalface.search(keyPattern, pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("content", memberPage.getContent());
        response.put("totalElements", memberPage.getTotalElements());
        response.put("totalPages", memberPage.getTotalPages());
        response.put("number", memberPage.getNumber());
        response.put("size", memberPage.getSize());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<FinalMember> updateMember(@RequestBody FinalMember member) {
        FinalMember updatedMember = finalface.save(member);
        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable String id) {
    	try {
            // 將 String 類型的 id 轉換為 Long
            Long longId = Long.parseLong(id);
            // 使用轉換後的 Long 類型 id 刪除成員
            finalface.deleteById(longId);
            return ResponseEntity.noContent().build();
        } catch (NumberFormatException e) {
            // 處理 id 格式錯誤的情況
            return ResponseEntity.badRequest().build();
        }
    }
}
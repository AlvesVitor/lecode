package com.lecode.controller;

import com.lecode.service.LecodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LecodeController {
    
    @Autowired
    private LecodeService lecodeService;

    @PostMapping("code")
    public ResponseEntity<String> keyboard(@RequestBody String code) throws Exception {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(lecodeService.typingCode(code));
    }
    
    @GetMapping("test")
    public ResponseEntity<?> testConnection() throws Exception {
        System.err.println("Conectado!");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Conexão estabelecida!");
    }

}

package com.example.validator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/validate")
public class ValidatorRest {

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> postTest(@RequestBody @Valid DataTest dataTest){
        return ResponseEntity.ok(dataTest);
    }
}

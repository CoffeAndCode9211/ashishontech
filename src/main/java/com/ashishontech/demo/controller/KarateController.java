package com.ashishontech.demo.controller;

import com.ashishontech.demo.dto.KarateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@Validated
@RequestMapping(value = "/karate/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class KarateController {

    private final HashMap<String, KarateDTO> karateDTOS = new HashMap<>();

    public KarateController(){
        KarateDTO karateDTO = new KarateDTO();
        karateDTO.setUserName("test");
        karateDTO.setPassword("test123");
        karateDTOS.put("test",karateDTO);
    }

    @GetMapping
    public List<KarateDTO> getUser(@RequestParam(required = false) String userName) {
        if(ObjectUtils.isEmpty(userName)){
            return new ArrayList<>(karateDTOS.values());
        }
        return karateDTOS.containsKey(userName) ? Collections.singletonList(karateDTOS.get(userName)) : Collections.EMPTY_LIST;
    }

    @PostMapping
    public ResponseEntity<Object> addUser(@Valid @RequestBody KarateDTO karateDTO) {
        karateDTOS.put(karateDTO.getUserName(), karateDTO);
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
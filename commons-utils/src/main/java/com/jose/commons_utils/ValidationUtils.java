package com.jose.commons_utils;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import java.util.HashMap;
import java.util.Map;

public class ValidationUtils {
    public static ResponseEntity<?> validar(BindingResult resultado){
        Map<String, Object> errores = new HashMap<>();
        resultado.getFieldErrors().forEach(err -> {
            String mensajeError = "El campo " + err.getField() + " " + err.getDefaultMessage();
            errores.put(err.getField(), mensajeError);
        });
        return ResponseEntity.badRequest().body(errores);
    }
}

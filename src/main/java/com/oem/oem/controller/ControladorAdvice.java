package com.oem.oem.controller;
import com.oem.oem.Excepcion.MiExcepcionPersonalizada;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControladorAdvice {

    @ExceptionHandler(MiExcepcionPersonalizada.class)
    public String handleMiExcepcion(MiExcepcionPersonalizada ex) {
        // Aquí puedes personalizar cómo manejar la excepción.
        // Por ejemplo, devolver un mensaje de error específico o registrar el error en logs.
        return "Error: " + ex.getMessage();
    }
}


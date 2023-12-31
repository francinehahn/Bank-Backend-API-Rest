package br.com.banco.handler;

import br.com.banco.exception.ParametroDeTempoException;
import br.com.banco.model.RespostaDeErro;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ParametroDeTempoException.class)
    public ResponseEntity<RespostaDeErro> handleParametroDeTempoException(ParametroDeTempoException ex) {
        RespostaDeErro respostaDeErro = new RespostaDeErro(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(respostaDeErro, HttpStatus.BAD_REQUEST);
    }
}

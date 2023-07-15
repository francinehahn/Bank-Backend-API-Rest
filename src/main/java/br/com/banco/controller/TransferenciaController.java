package br.com.banco.controller;

import br.com.banco.services.TransferenciaService;
import br.com.banco.entities.Transferencia;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/usuario/{contaId}/transferencias")
public class TransferenciaController {

    private final TransferenciaService service;

    @Autowired
    public TransferenciaController(TransferenciaService service) {
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<List<Transferencia>> buscarTransferencias(
        @PathVariable(required = true) Integer contaId,
        @RequestParam(required = false) String nomeOperador,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String dataInicio,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String dataFim
    ) {
        List<Transferencia> transferencias = service.buscarTransferencias(contaId, nomeOperador, dataInicio, dataFim);
        return ResponseEntity.ok(transferencias);
    }
}

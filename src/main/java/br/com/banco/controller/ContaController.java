package br.com.banco.controller;

import br.com.banco.entities.Conta;
import br.com.banco.services.ContaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class ContaController {
    private final ContaService service;

    @Autowired
    public ContaController(ContaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Conta>> retornaContas() {
        List<Conta> contas = service.retornaContas();
        return ResponseEntity.ok(contas);
    }
}

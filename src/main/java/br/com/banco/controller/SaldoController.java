package br.com.banco.controller;

import br.com.banco.services.SaldoService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

@RestController
@RequestMapping("/usuario/{contaId}/saldo")
public class SaldoController {
    private final SaldoService service;

    @Autowired
    public SaldoController(SaldoService service) {
        this.service = service;
    }

    @GetMapping
    public double calculaSaldo(
        @PathVariable(required = true) Integer contaId,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String dataInicio,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String dataFim
    ) {
        return service.calculaSaldo(contaId, dataInicio, dataFim);
    }
}

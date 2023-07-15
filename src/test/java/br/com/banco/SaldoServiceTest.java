package br.com.banco;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.exception.ParametroDeTempoException;
import br.com.banco.repositories.SaldoRepository;
import br.com.banco.services.SaldoService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SaldoServiceTest {
    @Mock
    private SaldoRepository repository;

    @InjectMocks
    private SaldoService service;

    public SaldoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    //Setando informações da TRANSFERENCIA1
    Conta conta1 = new Conta(1, "Fulano");
    Transferencia transferencia1 = new Transferencia(
        1, 
        Timestamp.valueOf(LocalDateTime.parse("2023-01-01 12:00:00+03", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))), 
        100.40, 
        "DEPÓSITO", 
        null,
        conta1
    );

    //Setando informações da TRANSFERENCIA2
    Conta conta2 = new Conta(2, "Sicrano");
    Transferencia transferencia2 = new Transferencia(
        2, 
        Timestamp.valueOf(LocalDateTime.parse("2023-01-21 12:00:00+03", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))), 
        200.50, 
        "Transferência",
        "Beltrano",
        conta2
    );

    //Setando algumas datas para serem usadas nos testes
    LocalDate dataInicio = LocalDate.parse("2023-01-10");
    LocalDate dataFim = LocalDate.parse("2023-02-10");
    Date dataInicioEditada = Date.from(dataInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
    Date dataFimEditada = Date.from(dataFim.atStartOfDay(ZoneId.systemDefault()).toInstant());

    @Test
    public void calcularSaldoTotal_DeveRetornarSaldoCorreto() {
        when(repository.calculaSaldoTotal(1)).thenReturn(100.40);
        double saldo = service.calculaSaldo(1, null, null);

        assertEquals(100.40, saldo);
        verify(repository, times(1)).calculaSaldoTotal(1);
    }

    @Test
    public void calcularSaldoPeriodo_DeveRetornarSaldoCorreto() {
        when(repository.calculaSaldoNoPeriodo(1, dataInicioEditada, dataFimEditada)).thenReturn(100.40);
        double saldo = service.calculaSaldo(1, "2023-01-10", "2023-02-10");

        assertEquals(100.40, saldo);
        verify(repository, times(1)).calculaSaldoNoPeriodo(1, dataInicioEditada, dataFimEditada);
    }

    @Test
    public void calculaSaldo_ApenasDataFimComoParametro_EsperaExcecao() {
        assertThrows(ParametroDeTempoException.class, () -> {
            service.calculaSaldo(null, null, "2023-01-02");
        });
    }

    @Test
    public void calculaSaldo_ApenasDataInicioComoParametro_EsperaExcecao() {
        assertThrows(ParametroDeTempoException.class, () -> {
            service.calculaSaldo(1, "2022-10-05", null);
        });
    }

    @Test
    public void calculaSaldo_DataInicioAposDataFim_EsperaExcecao() {
        assertThrows(ParametroDeTempoException.class, () -> {
            service.calculaSaldo(1, "2022-10-05", "2021-01-10");
        });
    }

    @Test
    public void calculaSaldo_DataFimAposDataAtual_EsperaExcecao() {
        assertThrows(ParametroDeTempoException.class, () -> {
            service.calculaSaldo(1, "2022-10-05", "2024-01-10");
        });
    }
}

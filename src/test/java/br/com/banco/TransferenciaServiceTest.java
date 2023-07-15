package br.com.banco;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.exception.ParametroDeTempoException;
import br.com.banco.repositories.TransferenciaRepository;
import br.com.banco.services.TransferenciaService;

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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransferenciaServiceTest {

    /*@Mock
    private TransferenciaRepository repository;

    @InjectMocks
    private TransferenciaService service;

    public TransferenciaServiceTest() {
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

    List<Transferencia> transferenciasEsperadas = Arrays.asList(transferencia1, transferencia2);

    //Setando algumas datas para serem usadas nos testes
    LocalDate dataInicio = LocalDate.parse("2023-01-10");
    LocalDate dataFim = LocalDate.parse("2023-02-10");
    Date dataInicioEditada = Date.from(dataInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
    Date dataFimEditada = Date.from(dataFim.atStartOfDay(ZoneId.systemDefault()).toInstant());

    @Test
    public void buscarTodasTransferencias_DeveRetornarTransferenciasCorretas() {
        when(repository.buscarTodasTransferencias(1)).thenReturn(Arrays.asList(transferencia1));
        List<Transferencia> transferencias = service.buscarTransferencias(1, null, null, null);

        assertEquals(1, transferencias.size());
        assertEquals(transferencia1, transferencias.get(0));
        verify(repository, times(1)).buscarTodasTransferencias(1);
    }

    @Test
    public void buscarTransferencias_PorPeriodo_DeveRetornarTransferenciasCorretas() {
        when(repository.buscarTransferenciasPorPeriodo(2, dataInicioEditada, dataFimEditada)).thenReturn(Arrays.asList(transferencia2));
        List<Transferencia> transferencias = service.buscarTransferencias(2, null, "2023-01-10", "2023-02-10");

        assertEquals(1, transferencias.size());
        assertEquals(transferencia2, transferencias.get(0));
        verify(repository, times(1)).buscarTransferenciasPorPeriodo(2, dataInicioEditada, dataFimEditada);
    }

    @Test
    public void buscarTransferencias_PorNomeOperador_DeveRetornarTransferenciasCorretas() {
        when(repository.buscarTransferenciasPorNomeOperador(2, "Beltrano")).thenReturn(Arrays.asList(transferencia2));
        List<Transferencia> transferencias = service.buscarTransferencias(2, "Beltrano", null, null);

        assertEquals(1, transferencias.size());
        assertEquals(transferencia2, transferencias.get(0));
        assertEquals(transferencia2.getNomeOperadorTransacao(), transferencias.get(0).getNomeOperadorTransacao());
        verify(repository, times(1)).buscarTransferenciasPorNomeOperador(2, "Beltrano");
    }

    @Test
    public void buscarTransferencias_PorNomeOperadorEMesAno_DeveRetornarTransferenciasCorretas() {
        when(repository.buscarTransferenciasPorPeriodoEoperador(2, "Beltrano", dataInicioEditada, dataFimEditada)).thenReturn(Arrays.asList(transferencia2));
        List<Transferencia> transferencias = service.buscarTransferencias(2, "Beltrano", "2023-01-10", "2023-02-10");

        assertEquals(1, transferencias.size());
        assertEquals(transferencia2, transferencias.get(0));
        assertEquals(transferencia2.getNomeOperadorTransacao(), transferencias.get(0).getNomeOperadorTransacao());
        verify(repository, times(1)).buscarTransferenciasPorPeriodoEoperador(2, "Beltrano", dataInicioEditada, dataFimEditada);
    }

    @Test
    public void buscarTransferencias_ApenasDataFimComoParametro_EsperaExcecao() {
        assertThrows(ParametroDeTempoException.class, () -> {
            service.buscarTransferencias(1, null, null, "2022-10-05");
        });
    }

    @Test
    public void buscarTransferencias_ApenasDataInicioComoParametro_EsperaExcecao() {
        assertThrows(ParametroDeTempoException.class, () -> {
            service.buscarTransferencias(1, null, "2022-10-05", null);
        });
    }

    @Test
    public void buscarTransferencias_DataInicioAposDataFim_EsperaExcecao() {
        assertThrows(ParametroDeTempoException.class, () -> {
            service.buscarTransferencias(1, null, "2022-10-05", "2021-01-10");
        });
    }

    @Test
    public void buscarTransferencias_DataFimAposDataAtual_EsperaExcecao() {
        assertThrows(ParametroDeTempoException.class, () -> {
            service.buscarTransferencias(1, null, "2022-10-05", "2024-01-10");
        });
    }*/
}

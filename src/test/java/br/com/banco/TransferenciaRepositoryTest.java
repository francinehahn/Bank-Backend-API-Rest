package br.com.banco;

import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class TransferenciaRepositoryTest {
    
    @Autowired
    private TransferenciaRepository repository;

    //Setando algumas datas para serem usadas nos testes
    LocalDate dataInicio = LocalDate.parse("2020-02-01");
    LocalDate dataFim = LocalDate.parse("2020-08-10");
    Date dataInicioEditada = Date.from(dataInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
    Date dataFimEditada = Date.from(dataFim.atStartOfDay(ZoneId.systemDefault()).toInstant());

    @Test
    public void testarBuscarTodasTransferencias() {
        List<Transferencia> resposta = repository.buscarTodasTransferencias(1);
        List<Transferencia> resposta2 = repository.buscarTodasTransferencias(2);
  
        assertNotNull(resposta);
        assertEquals(resposta.get(0).getId(), 1);
        assertNotNull(resposta2);
        assertNotEquals(resposta2.get(0).getId(), 1);
    }

    @Test
    public void testarBuscarTodasTransferenciasPorNomeOperador() {
        List<Transferencia> resposta = repository.buscarTransferenciasPorNomeOperador(1, "Beltrano");

        assertNotNull(resposta);
        assertEquals(resposta.get(0).getTipo(), "TRANSFERENCIA");
        assertEquals(resposta.get(0).getNomeOperadorTransacao(), "Beltrano");
    }

    @Test
    public void testarBuscarTransferenciasPorMesAno() {
        List<Transferencia> resposta = repository.buscarTransferenciasPorPeriodo(1, dataInicioEditada, dataFimEditada);
        assertEquals(1, resposta.size());

        List<Transferencia> resposta2 = repository.buscarTransferenciasPorPeriodo(2, dataInicioEditada, dataFimEditada);
        assertEquals(0, resposta2.size());
    }

    @Test
    public void testarBuscarTransferenciasPorNomeOperadorMesAno() {
        List<Transferencia> resposta = repository.buscarTransferenciasPorPeriodoEoperador(1, "Beltrano", dataInicioEditada, dataFimEditada);
        assertNotNull(resposta);
        assertEquals(resposta.get(0).getNomeOperadorTransacao(), "Beltrano");

        List<Transferencia> resposta2 = repository.buscarTransferenciasPorPeriodoEoperador(2, "Beltrano", dataInicioEditada, dataFimEditada);
        assertEquals(0, resposta2.size());
    }
}

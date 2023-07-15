package br.com.banco;

import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    Pageable pageable = PageRequest.of(0, 4);

    @Test
    public void testarBuscarTodasTransferencias() {
        Page<Transferencia> resposta = repository.buscarTodasTransferencias(1, pageable);
        Page<Transferencia> resposta2 = repository.buscarTodasTransferencias(2, pageable);
  
        assertNotNull(resposta);
        assertEquals(resposta.getContent().get(0).getId(), 1);
        assertNotNull(resposta2);
        assertNotEquals(resposta2.getContent().get(0).getId(), 1);
    }

    @Test
    public void testarBuscarTodasTransferenciasPorNomeOperador() {
        Page<Transferencia> resposta = repository.buscarTransferenciasPorNomeOperador(
            1, "Beltrano", pageable
        );

        assertNotNull(resposta);
        assertEquals(resposta.getContent().get(0).getTipo(), "TRANSFERENCIA");
        assertEquals(resposta.getContent().get(0).getNomeOperadorTransacao(), "Beltrano");
    }

    @Test
    public void testarBuscarTransferenciasPorMesAno() {
        Page<Transferencia> resposta = repository.buscarTransferenciasPorPeriodo(
            1, dataInicioEditada, dataFimEditada, pageable
        );
        assertEquals(1, resposta.getContent().size());

        Page<Transferencia> resposta2 = repository.buscarTransferenciasPorPeriodo(2, dataInicioEditada, dataFimEditada, pageable);
        assertEquals(0, resposta2.getContent().size());
    }

    @Test
    public void testarBuscarTransferenciasPorNomeOperadorMesAno() {
        Page<Transferencia> resposta = repository.buscarTransferenciasPorPeriodoEoperador(
            1, "Beltrano", dataInicioEditada, dataFimEditada, pageable
        );
        assertNotNull(resposta);
        assertEquals(resposta.getContent().get(0).getNomeOperadorTransacao(), "Beltrano");

        Page<Transferencia> resposta2 = repository.buscarTransferenciasPorPeriodoEoperador(
            2, "Beltrano", dataInicioEditada, dataFimEditada, pageable
        );
        assertEquals(0, resposta2.getContent().size());
    }
}

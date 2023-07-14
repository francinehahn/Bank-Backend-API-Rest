package br.com.banco;

import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    
    /*@Autowired
    private TransferenciaRepository repository;

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
        List<Transferencia> resposta = repository.buscarTransferenciasPorMesAno(1, 2, 2020);
        assertEquals(0, resposta.size());

        List<Transferencia> resposta2 = repository.buscarTransferenciasPorMesAno(1, 3, 2023);
        assertNotNull(resposta2);
    }

    @Test
    public void testarBuscarTransferenciasPorNomeOperadorMesAno() {
        List<Transferencia> resposta = repository.buscarTransferenciasPorMesAnoEoperador(1, "Beltrano", 6, 2020);
        assertNotNull(resposta);
        assertEquals(resposta.get(0).getNomeOperadorTransacao(), "Beltrano");

        List<Transferencia> resposta2 = repository.buscarTransferenciasPorMesAnoEoperador(1, "Beltrano", 6, 2023);
        assertEquals(0, resposta2.size());
    }*/
}

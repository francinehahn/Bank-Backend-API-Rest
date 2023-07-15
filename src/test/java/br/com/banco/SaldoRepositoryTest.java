package br.com.banco;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import br.com.banco.repositories.SaldoRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class SaldoRepositoryTest {

    @Autowired
    private SaldoRepository repository;

    //Setando algumas datas para serem usadas nos testes
    LocalDate dataInicio = LocalDate.parse("2020-02-01");
    LocalDate dataFim = LocalDate.parse("2022-08-10");
    Date dataInicioEditada = Date.from(dataInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
    Date dataFimEditada = Date.from(dataFim.atStartOfDay(ZoneId.systemDefault()).toInstant());

    @Test
    public void calcularEretornarSaldoTotal() {
        double saldo = repository.calculaSaldoTotal(1);
        assertNotEquals(saldo, 0);
    }

    @Test
    public void calcularEretornarSaldoPeriodo() {
        double saldo = repository.calculaSaldoNoPeriodo(1, dataInicioEditada, dataFimEditada);
        assertNotEquals(saldo, 0);
    }
}

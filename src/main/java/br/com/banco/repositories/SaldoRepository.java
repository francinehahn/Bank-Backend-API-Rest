package br.com.banco.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.banco.entities.Transferencia;

public interface SaldoRepository extends JpaRepository<Transferencia, Long> {
    //Calcular o saldo total
    @Query("SELECT SUM(t.valor) FROM Transferencia t WHERE t.conta.id = :contaId")
    double calculaSaldoTotal(@Param("contaId") Integer contaId);

    //Calcular o saldo no periodo
    @Query("SELECT SUM(t.valor) FROM Transferencia t WHERE t.conta.id = :contaId AND t.dataTransferencia >= :dataInicio AND t.dataTransferencia <= :dataFim")
    Double calculaSaldoNoPeriodo(
        @Param("contaId") Integer contaId,
        @Param("dataInicio") Date dataInicio, 
        @Param("dataFim") Date dataFim
    );
}

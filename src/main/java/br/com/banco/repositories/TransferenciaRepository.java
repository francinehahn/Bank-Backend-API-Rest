package br.com.banco.repositories;

import br.com.banco.entities.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    @Query("SELECT t FROM Transferencia t JOIN FETCH t.conta c WHERE c.idConta = :contaId")
    List<Transferencia> buscarTodasTransferencias(@Param("contaId") Integer contaId);

    @Query("SELECT t FROM Transferencia t JOIN FETCH t.conta c WHERE c.idConta = :contaId AND t.nomeOperadorTransacao = :nomeOperador")
    List<Transferencia> buscarTransferenciasPorNomeOperador(
        @Param("contaId") Integer contaId,
        @Param("nomeOperador") String nomeOperador
    );

    @Query("SELECT t FROM Transferencia t JOIN FETCH t.conta c WHERE c.idConta = :contaId AND t.dataTransferencia >= :dataInicio AND t.dataTransferencia <= :dataFim")
    List<Transferencia> buscarTransferenciasPorMesAno(
        @Param("contaId") Integer contaId,
        @Param("dataInicio") Date dataInicio, 
        @Param("dataFim") Date dataFim
    );

    @Query("SELECT t FROM Transferencia t JOIN FETCH t.conta c WHERE c.idConta = :contaId AND t.nomeOperadorTransacao = :nomeOperador AND t.dataTransferencia >= :dataInicio AND t.dataTransferencia <= :dataFim")
    List<Transferencia> buscarTransferenciasPorMesAnoEoperador(
        @Param("contaId") Integer contaId,
        @Param("nomeOperador") String nomeOperador,
        @Param("dataInicio") Date dataInicio, 
        @Param("dataFim") Date dataFim
    );
}


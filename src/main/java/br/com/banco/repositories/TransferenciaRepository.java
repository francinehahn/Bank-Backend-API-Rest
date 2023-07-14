package br.com.banco.repositories;

import br.com.banco.entities.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    //Buscar todas as transferencias do usuario - sem uso de filtro
    @Query("SELECT t FROM Transferencia t JOIN FETCH t.conta c WHERE c.idConta = :contaId")
    List<Transferencia> buscarTodasTransferencias(@Param("contaId") Integer contaId);

    //Buscar as transferencias do usuario - filtro por nome do operador
    @Query("SELECT t FROM Transferencia t JOIN FETCH t.conta c WHERE c.idConta = :contaId AND t.nomeOperadorTransacao = :nomeOperador")
    List<Transferencia> buscarTransferenciasPorNomeOperador(
        @Param("contaId") Integer contaId,
        @Param("nomeOperador") String nomeOperador
    );

    //Buscar as transferencias do usuario - filtro por periodo
    @Query("SELECT t FROM Transferencia t JOIN FETCH t.conta c WHERE c.idConta = :contaId AND t.dataTransferencia >= :dataInicio AND t.dataTransferencia <= :dataFim")
    List<Transferencia> buscarTransferenciasPorPeriodo(
        @Param("contaId") Integer contaId,
        @Param("dataInicio") Date dataInicio, 
        @Param("dataFim") Date dataFim
    );

    //Buscar as transferencias do usuario - filtro por nome do operador e periodo
    @Query("SELECT t FROM Transferencia t JOIN FETCH t.conta c WHERE c.idConta = :contaId AND t.nomeOperadorTransacao = :nomeOperador AND t.dataTransferencia >= :dataInicio AND t.dataTransferencia <= :dataFim")
    List<Transferencia> buscarTransferenciasPorPeriodoEoperador(
        @Param("contaId") Integer contaId,
        @Param("nomeOperador") String nomeOperador,
        @Param("dataInicio") Date dataInicio, 
        @Param("dataFim") Date dataFim
    );
}


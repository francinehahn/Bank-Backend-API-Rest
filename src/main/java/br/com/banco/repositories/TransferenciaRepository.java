package br.com.banco.repositories;

import br.com.banco.entities.Transferencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    //Buscar todas as transferencias do usuario - sem uso de filtro
    @Query(value = "SELECT t FROM Transferencia t JOIN FETCH t.conta c WHERE c.idConta = :contaId",
    countQuery = "SELECT COUNT(t) FROM Transferencia t JOIN t.conta c WHERE c.idConta = :contaId")
    Page<Transferencia> buscarTodasTransferencias(
        @Param("contaId") Integer contaId,
        @Param("pagina") Pageable pageable
    );

    //Buscar as transferencias do usuario - filtro por nome do operador
    @Query(value = "SELECT t FROM Transferencia t JOIN FETCH t.conta c WHERE c.idConta = :contaId AND t.nomeOperadorTransacao = :nomeOperador",
    countQuery = "SELECT COUNT(t) FROM Transferencia t JOIN t.conta c WHERE c.idConta = :contaId")
    Page<Transferencia> buscarTransferenciasPorNomeOperador(
        @Param("contaId") Integer contaId,
        @Param("nomeOperador") String nomeOperador,
        @Param("pagina") Pageable pageable
    );

    //Buscar as transferencias do usuario - filtro por periodo
    @Query(value = "SELECT t FROM Transferencia t JOIN FETCH t.conta c WHERE c.idConta = :contaId AND t.dataTransferencia >= :dataInicio AND t.dataTransferencia <= :dataFim",
    countQuery = "SELECT COUNT(t) FROM Transferencia t JOIN t.conta c WHERE c.idConta = :contaId")
    Page<Transferencia> buscarTransferenciasPorPeriodo(
        @Param("contaId") Integer contaId,
        @Param("dataInicio") Date dataInicio, 
        @Param("dataFim") Date dataFim,
        @Param("pagina") Pageable pageable
    );

    //Buscar as transferencias do usuario - filtro por nome do operador e periodo
    @Query(value = "SELECT t FROM Transferencia t JOIN FETCH t.conta c WHERE c.idConta = :contaId AND t.nomeOperadorTransacao = :nomeOperador AND t.dataTransferencia >= :dataInicio AND t.dataTransferencia <= :dataFim",
    countQuery = "SELECT COUNT(t) FROM Transferencia t JOIN t.conta c WHERE c.idConta = :contaId")
    Page<Transferencia> buscarTransferenciasPorPeriodoEoperador(
        @Param("contaId") Integer contaId,
        @Param("nomeOperador") String nomeOperador,
        @Param("dataInicio") Date dataInicio, 
        @Param("dataFim") Date dataFim,
        @Param("pagina") Pageable pageable
    );
}


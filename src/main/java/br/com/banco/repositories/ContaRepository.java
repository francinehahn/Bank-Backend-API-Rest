package br.com.banco.repositories;

import br.com.banco.entities.Conta;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    //Retorna todas as contas
    @Query("SELECT t FROM Conta t")
    List<Conta> retornaContas();
}
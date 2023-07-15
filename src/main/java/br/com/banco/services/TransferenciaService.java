package br.com.banco.services;

import br.com.banco.entities.Transferencia;
import br.com.banco.exception.ParametroDeTempoException;
import br.com.banco.repositories.TransferenciaRepository;
import br.com.banco.utils.Datas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransferenciaService {
  private TransferenciaRepository repository;

  @Autowired
  public TransferenciaService(TransferenciaRepository repository) {
      this.repository = repository;
  }

  public Page<Transferencia> buscarTransferencias(
    Integer contaId, String nomeOperador, String dataInicio, String dataFim, Integer numeroPagina
  ) {
    //O usuário não pode passar apenas a data de início ou apenas a data de fim
    if ((dataInicio == null && dataFim != null) || (dataInicio != null && dataFim == null)) {
        throw new ParametroDeTempoException("Você não pode passar como parâmetro apenas a data de inicio ou apenas a data de fim.");
    }

    Date dataInicioEditada = null;
    Date dataFimEditada = null;

    if (dataInicio != null && dataFim != null) {
        //Transformando as datas para o tipo Date
        dataInicioEditada = Datas.transformarData(dataInicio);
        dataFimEditada = Datas.transformarData(dataFim);

        //Validação das datas
        Datas.validarDatas(dataInicioEditada, dataFimEditada);
    }

    int tamanhoPagina = 4;
    Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);

    //Cada query será chamada de acordo com o filtro usado
    if (nomeOperador != null && dataInicio != null & dataFim != null) {
      return repository.buscarTransferenciasPorPeriodoEoperador(contaId, nomeOperador, dataInicioEditada, dataFimEditada, pageable);
    } else if (nomeOperador != null) {
      return repository.buscarTransferenciasPorNomeOperador(contaId, nomeOperador, pageable);
    } else if (dataInicio != null && dataFim != null) {
      return repository.buscarTransferenciasPorPeriodo(contaId, dataInicioEditada, dataFimEditada, pageable);
    } else {
      return repository.buscarTodasTransferencias(contaId, pageable);
    }
  }
}

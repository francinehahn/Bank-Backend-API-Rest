package br.com.banco.services;

import br.com.banco.entities.Datas;
import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
import br.com.banco.utils.ValidacaoDeDatas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;

@Service
public class TransferenciaService {
  private TransferenciaRepository repository;
  private ValidacaoDeDatas validacaoDeDatas;

  @Autowired
  public TransferenciaService(TransferenciaRepository repository, ValidacaoDeDatas validacaoDeDatas) {
      this.repository = repository;
      this.validacaoDeDatas = validacaoDeDatas;
  }

  public List<Transferencia> buscarTransferencias(
    Integer contaId, String nomeOperador, String dataInicio, String dataFim
  ) {
    Datas datas = validacaoDeDatas.validaDataInicioEdataFim(dataInicio, dataFim);
    Date dataInicioEditada = datas.getDataInicio();
    Date dataFimEditada = datas.getDataFim();

    //Cada query será chamada de acordo com o filtro usado
    if (nomeOperador != null && dataInicio != null & dataFim != null) {
      return repository.buscarTransferenciasPorPeriodoEoperador(contaId, nomeOperador, dataInicioEditada, dataFimEditada);
    } else if (nomeOperador != null) {
      return repository.buscarTransferenciasPorNomeOperador(contaId, nomeOperador);
    } else if (dataInicio != null && dataFim != null) {
      return repository.buscarTransferenciasPorPeriodo(contaId, dataInicioEditada, dataFimEditada);
    } else {
      return repository.buscarTodasTransferencias(contaId);
    }
  }
}

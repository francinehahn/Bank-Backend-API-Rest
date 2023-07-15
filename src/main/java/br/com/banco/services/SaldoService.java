package br.com.banco.services;

import br.com.banco.exception.ParametroDeTempoException;
import br.com.banco.repositories.SaldoRepository;
import br.com.banco.utils.Datas;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaldoService {
    private SaldoRepository repository;

    @Autowired
    public SaldoService(SaldoRepository repository) {
        this.repository = repository;
    }

    public double calculaSaldo(Integer contaId, String dataInicio, String dataFim) {
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

        if (dataInicio != null && dataFim != null) {
            return repository.calculaSaldoNoPeriodo(contaId, dataInicioEditada, dataFimEditada);
        } else {
            return repository.calculaSaldoTotal(contaId);
        }
    }
}

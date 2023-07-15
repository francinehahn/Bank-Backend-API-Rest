package br.com.banco.services;

import br.com.banco.entities.Datas;
import br.com.banco.repositories.SaldoRepository;
import br.com.banco.utils.ValidacaoDeDatas;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaldoService {
    private SaldoRepository repository;
    private ValidacaoDeDatas validacaoDeDatas;

    @Autowired
    public SaldoService(SaldoRepository repository, ValidacaoDeDatas validacaoDeDatas) {
        this.repository = repository;
        this.validacaoDeDatas = validacaoDeDatas;
    }

    public double calculaSaldo(Integer contaId, String dataInicio, String dataFim) {
        Datas datas = validacaoDeDatas.validaDataInicioEdataFim(dataInicio, dataFim);
        Date dataInicioEditada = datas.getDataInicio();
        Date dataFimEditada = datas.getDataFim();

        //Cada query será chamada de acordo com o filtro usado
        if (dataInicio != null && dataFim != null) {
            return repository.calculaSaldoNoPeriodo(contaId, dataInicioEditada, dataFimEditada);
        } else {
            return repository.calculaSaldoTotal(contaId);
        }
    }
}

package br.com.banco.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.banco.entities.Datas;
import br.com.banco.exception.ParametroDeTempoException;

@Service
public class ValidacaoDeDatas {
    
    public Datas validaDataInicioEdataFim (String dataInicio, String dataFim) {
        //O usuário não pode passar apenas a data de início ou apenas a data de fim
        if ((dataInicio == null && dataFim != null) || dataInicio != null && dataFim == null) {
            throw new ParametroDeTempoException("Você não pode passar como parâmetro apenas a data de inicio ou apenas a data de fim.");
        }

        Date dataInicioEditada = null;
        Date dataFimEditada = null;

        if (dataInicio != null & dataFim != null) {
            //Transformando as datas para o tipo Date
            LocalDate dataInicioLocalDate = LocalDate.parse(dataInicio);
            LocalDate dataFimLocalDate = LocalDate.parse(dataFim);
            dataInicioEditada = Date.from(dataInicioLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            dataFimEditada = Date.from(dataFimLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            //A data de início não pode ser após a data de fim
            if (dataInicioEditada.compareTo(dataFimEditada) > 0) {
                throw new ParametroDeTempoException("A data de início não pode ser após a data de fim.");
            }

            //A data de fim não pode ser após a data do dia atual
            if (new Date().compareTo(dataFimEditada) < 0) {
                throw new ParametroDeTempoException("A data de fim não pode ser após a data atual.");
            }

            //A data de início não pode ser após a data do dia atual
            if (new Date().compareTo(dataInicioEditada) < 0) {
                throw new ParametroDeTempoException("A data de início não pode ser após a data atual.");
            }
        }

        return new Datas(dataInicioEditada, dataFimEditada);
    }
}

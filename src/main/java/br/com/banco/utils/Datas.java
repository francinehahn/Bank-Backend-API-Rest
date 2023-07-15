package br.com.banco.utils;

import java.util.Date;
import br.com.banco.exception.ParametroDeTempoException;

public class Datas {
    public static void validarDatas(Date dataInicio, Date dataFim) {
        //A data de início não pode ser após a data de fim
        if (dataInicio.compareTo(dataFim) > 0) {
            throw new ParametroDeTempoException("A data de início não pode ser após a data de fim.");
        }

        //A data de fim não pode ser após a data do dia atual
        if (new Date().compareTo(dataFim) < 0) {
            throw new ParametroDeTempoException("A data de fim não pode ser após a data atual.");
        }

        //A data de início não pode ser após a data do dia atual
        if (new Date().compareTo(dataInicio) < 0) {
            throw new ParametroDeTempoException("A data de início não pode ser após a data atual.");
        }   
    }
}

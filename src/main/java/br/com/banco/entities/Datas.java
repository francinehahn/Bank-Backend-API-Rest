package br.com.banco.entities;

import java.util.Date;

public class Datas {
    private Date dataInicio;
    private Date dataFim;

    public Datas(Date dataInicio, Date dataFim) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Date getDataInicio () {
        return dataInicio;
    }

    public Date getDataFim () {
        return dataFim;
    }
}

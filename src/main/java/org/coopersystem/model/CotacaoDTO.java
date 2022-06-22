package org.coopersystem.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CotacaoDTO {

    public Long idRequisicao;
    public LocalDateTime dataRequisicao;
    public LocalDate dataCotacaoDolar;
    public BigDecimal cotacaoCompra;
    public BigDecimal cotacaoVenda;
    public LocalDateTime dataHoraCotacao;

}

package org.coopersystem.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NamedQueries({
        @NamedQuery(name = "Cotacao.getByDataRequisicao", query = "from Cotacao where datarequisicao = ?1"),
})
public class Cotacao extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "cotacaoSequence",
            sequenceName = "cotacao_id_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cotacaoSequence")
    public Long idRequisicao;

    public LocalDateTime dataRequisicao;
    public LocalDate dataCotacaoDolar;
    public BigDecimal cotacaoCompra;
    public BigDecimal cotacaoVenda;
    public LocalDateTime dataHoraCotacao;

    public static Cotacao findByDataRequisicao(LocalDateTime dataRequisicao){
        return find("#Cotacao.getByDataRequisicao", dataRequisicao).firstResult();
    }

    @Override
    public String toString() {
        return "Cotacao{" +
                "idRequisicao=" + idRequisicao +
                ", dataRequisicao=" + dataRequisicao +
                ", dataCotacaoDolar=" + dataCotacaoDolar +
                ", cotacaoCompra=" + cotacaoCompra +
                ", cotacaoVenda=" + cotacaoVenda +
                ", dataHoraCotacao=" + dataHoraCotacao +
                '}';
    }

}

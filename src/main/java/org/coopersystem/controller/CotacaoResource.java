package org.coopersystem.controller;

import org.coopersystem.model.Cotacao;
import org.coopersystem.model.CotacaoDTO;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.coopersystem.client.CotacaoService;
import org.coopersystem.model.CotacaoDolarDiaResponse;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/cotacoes")
@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
public class CotacaoResource {

    @Inject
    @RestClient
    CotacaoService cotacaoService;

    @GET
    @Transactional
    @Path("/{dataCotacao}")
    @Operation(description = "Api que recebe uma data no formato MM-DD-YYYY e retorna a cotação do dolar da data recebida")
    public Response buscar(@PathParam("dataCotacao") String dataCotacaoRequest) throws CotacaoNotFoundException {
        // busca cotacao na api externa
        CotacaoDolarDiaResponse cotacaoDolarDiaResponse = cotacaoService.getByDataCotacao("'" + dataCotacaoRequest + "'");

        if (cotacaoDolarDiaResponse.getValue().size() == 0) {
            throw new CotacaoNotFoundException("Cotacao nao encontrada");
        }

        LocalDateTime dataRequisicao = LocalDateTime.now();

        // salva no banco de dados
        Cotacao.persist(cotacaoBuilder(cotacaoDolarDiaResponse, dataCotacaoRequest, dataRequisicao));

        // busca no banco de dados
        Cotacao result = Cotacao.findByDataRequisicao(dataRequisicao);

        return Response.ok(parseObjectToDTO(result)).build();
    }

    public Cotacao cotacaoBuilder(CotacaoDolarDiaResponse cotacaoDolarDiaResponse, String dataCotacaoRequest, LocalDateTime dataRequisicao) {
        String dataHoraCotacaoStr = cotacaoDolarDiaResponse.getValue().get(0).getDataHoraCotacao().substring(0, 19);

        DateTimeFormatter formatterDataCotacao = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate dataCotacao = LocalDate.parse(dataCotacaoRequest, formatterDataCotacao);

        DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dataHoraCotacao = LocalDateTime.parse(dataHoraCotacaoStr, formatterDateTime);

        Cotacao cotacao = new Cotacao();
        cotacao.setDataRequisicao(dataRequisicao);
        cotacao.setDataCotacaoDolar(dataCotacao);
        cotacao.setCotacaoCompra(BigDecimal.valueOf(cotacaoDolarDiaResponse.getValue().get(0).getCotacaoCompra()));
        cotacao.setCotacaoVenda(BigDecimal.valueOf(cotacaoDolarDiaResponse.getValue().get(0).getCotacaoVenda()));
        cotacao.setDataHoraCotacao(dataHoraCotacao);

        return cotacao;
    }

    public CotacaoDTO parseObjectToDTO(Cotacao cotacao) {
        CotacaoDTO cotacaoDto = new CotacaoDTO();
        cotacaoDto.setIdRequisicao(cotacao.getIdRequisicao());
        cotacaoDto.setDataRequisicao(cotacao.getDataRequisicao());
        cotacaoDto.setDataCotacaoDolar(cotacao.getDataCotacaoDolar());
        cotacaoDto.setCotacaoCompra(cotacao.getCotacaoCompra());
        cotacaoDto.setCotacaoVenda(cotacao.getCotacaoVenda());
        cotacaoDto.setDataHoraCotacao(cotacao.getDataHoraCotacao());

        return cotacaoDto;
    }

}

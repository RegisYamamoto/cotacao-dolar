package org.coopersystem.client;

import org.coopersystem.model.CotacaoDolarDiaResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/odata/CotacaoDolarDia(dataCotacao=@dataCotacao)")
@RegisterRestClient(configKey="extensions-api")
public interface CotacaoService {

    @GET
    CotacaoDolarDiaResponse getByDataCotacao(@QueryParam("@dataCotacao") String dataCotacao);

}

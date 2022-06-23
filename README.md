# história

Eu como responsável pelo projeto de cotações
Quero que seja possível consultar cotações do dolar
Para que eu possa precificar os produtos de minha loja


## Visão de implementação

- Criar projeto em quarkus com o nome de "cotacao-dolar"
- Criar endpoint GET que receba a data da cotação desejada por parametro
- Criar client para buscar a cotação na api pública do governo https://dadosabertos.bcb.gov.br/dataset/dolar-americano-usd-todos-os-boletins-diarios
- Salvar os dados retornados da api https://dadosabertos.bcb.gov.br/dataset/dolar-americano-usd-todos-os-boletins-diarios no banco Postgres, tabela "cotacao"
- Retornar DTO com os seguintes campos:
    id da requisição,
    timestamp da requisição,
    Data da cotação do dólar (data da cotação solicitada e não a data da requisição),
    Cotação de compra,
    Cotação de venda,
    Data e Hora da Cotação,
- Criar testes unitários


## Cenários de teste
- Testar com grande quantidade de reuisições
- Testar cenário de sucesso retornando status 200
- Testar cenário onde não exista cotação
- Testar cenário onde a api externa esteja fora do ar
- Testar cenário onde o banco de dados esteja fora do ar


## Critérios de aceite

- Ter pelo menos 80% do código coberto por testes
- Que retorne 1000 requisições 10 segundos
- Que seja feito o deploy com sucesso nos ambiente Desenvolvimento, Homologaçõe e Produção



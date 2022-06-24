# Como rodar o projeto

- Faça um clone do projeto para a sua máquina
- Tenha o Docker instalado
- Abra o PowerShell ou algum outro terminal, vá até a pasta raíz do projeto e digite o comando "docker compose up -d"
- Aguarde o docker criar as imagens e os containers da aplicação e do banco Postgres
- Teste a aplicação pelo swagger: http://localhost:8080/q/swagger-ui/#/
<br>
<br>
<br>

# História

Eu como responsável pelo projeto de cotações<br>
Quero que seja possível consultar cotações do dolar<br>
Para que eu possa precificar os produtos de minha loja<br>


## Visão de implementação

- Criar projeto em quarkus com o nome de "cotacao-dolar"
- Criar endpoint GET que receba a data da cotação desejada por parametro
- Criar client para buscar a cotação na api pública do governo https://dadosabertos.bcb.gov.br/dataset/dolar-americano-usd-todos-os-boletins-diarios
- Salvar os dados retornados da api https://dadosabertos.bcb.gov.br/dataset/dolar-americano-usd-todos-os-boletins-diarios no banco Postgres, tabela "cotacao"
- Retornar DTO com os seguintes campos:<br>
&nbsp;&nbsp;id da requisição<br>
&nbsp;&nbsp;timestamp da requisição<br>
&nbsp;&nbsp;Data da cotação do dólar (data da cotação solicitada e não a data da requisição)<br>
&nbsp;&nbsp;Cotação de compra<br>
&nbsp;&nbsp;Cotação de venda<br>
&nbsp;&nbsp;Data e Hora da Cotação
- Criar testes unitários


## Cenários de teste

- Cenário 1:<br>
&nbsp;&nbsp;Dado que preciso consultar as cotações<br>
&nbsp;&nbsp;Quando envio uma 1000 consultas a api<br>
&nbsp;&nbsp;Quero que todas as 1000 requisições retorne em no máximo 10 segundos

- Cenário 2:
&nbsp;&nbsp;Dado que preciso consultar uma cotação<br>
&nbsp;&nbsp;Quando envio uma requisição com sucesso<br>
&nbsp;&nbsp;Quero que retorne a cotação correta da data passada como parâmetro<br>
&nbsp;&nbsp;E que retorno status code 200

- Cenário 3:
&nbsp;&nbsp;Dado que preciso consulta uma cotação<br>
&nbsp;&nbsp;Quando envio uma data que não existe<br>
&nbsp;&nbsp;Quero que retorne status 404

- Cenário 4:
&nbsp;&nbsp;Dado que preciso consultar uma cotação<br>
&nbsp;&nbsp;Quando envio uma requisição mas a api externa do governo estiver fora do ar<br>
&nbsp;&nbsp;Quero que retorne status code 500 e a mensagem explicando o ocorrido

- Cenário 5
&nbsp;&nbsp;Dado que preciso consultar uma cotação<br>
&nbsp;&nbsp;Quando envio uma requisição mas banco de dados estiver fora do ar<br>
&nbsp;&nbsp;Quero que retorne status code 500 e a mensagem explicando o ocorrido


## Critérios de aceite

- Ter pelo menos 80% do código coberto por testes
- Que retorne 1000 requisições 10 segundos
- Que seja feito o deploy com sucesso nos ambiente Desenvolvimento, Homologaçõe e Produção



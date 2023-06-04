# LABSky-API
## _Gestão de Passagens_

LABSky-API é uma API Rest para um sistema fictício de passageiros, designado para fazer a comunicação com o banco de dados, foi construido com Spring, em Java, e pode facilmente alterar seu banco relacional.

### Sumário

1. [Funcionalidades](#funcoes)
2. [Tecnologias](#tech)
3. [Instalação](#install)

<br>
<a id="funcoes"></a>

## Funcionalidades

Sendo uma API Rest, o LABSky-API esta preparado para receber requisições HTTP para cadastros (POSTs) e consultas (GETs) no banco de dados.
A API está em modo de teste, então possui registros fictícios (seeders) inclusos no código.

- Cadastro de Passagens (check-in)
- Consultas de Passageiros, Assentos.
- Possui uma base de dados (seeders) para testes, inclusa no código.
- É suportado por testes unitários feitos para garantir a consistência das regras de negócio.

### Sumário de Funcionalidades

1. [Cadastro](#cadastro)
2. [Consultas](#consultas)
2.1 [Consulta de Passageiros](#consultaPassageiro)
2.2 [Consulta de Assentos](#consultaAssento)
<br>
<a  id="cadastro"></a>

### Cadastro
É possível o cadastro de Passagens (check-in). Deve vir por HTTP Requests de POST com corpo no formato JSON e recebem um Response com o E-Ticket gerado junto da data e horário da confirmação.
<br>

- Todos os campos são obrigatórios. CPF, Assento e Despache de malas.
Exemplo do Request em JSON:
```
{
	"cpf": "111.111.111-11",
	"assentoNome": "3A",
	"malasDespachadas": true
}
```
Exemplo do Response em JSON:
```
{
    "dataHoraConfirmacao": "04/06/2023 12:56:52",
    "eticket": "92f299c8-c9a3-4eae-953a-7ab82946c203"
}
```
<br>
<a id="consultas"></a>

### Consultas
É possível consultar os registros de Passageiros e  Assentos. Ambas devem vir por HTTP Requests de GET sem a necessidade de um corpo em JSON.
<br>
<a id="consultaPassageiro"></a>

#### Consulta de Passageiro
A consulta de Passageiro pode ser feita de duas maneiras: Buscar todos ou Buscar por CPF. Todas são feitas por Requests de GET e recebem uma resposta em JSON.

- A consulta de todos os passageiros é feita pelo endereço "../api/passageiros".
- A consulta por CPF requer um número correspondente a um CPF de passageiro como caminho da URL, por exemplo "../api/passageiros/111.111.111-11"


Exemplo do Response em JSON para buscar todos:
```
{
        "cpf": "000.000.000-00",
        "nome": "Rachel Green",
        "dataNascimento": "11/01/1969",
        "classificacao": "VIP",
        "milhas": 100,
        "assento": null,
        "malasDespachadas": null,
        "dataHoraConfirmacao": null,
        "eticket": null
    },
	{
        "cpf": "111.111.111-11",
        "nome": "Phoebe Buffay",
        "dataNascimento": "30/07/1963",
        "classificacao": "OURO",
        "milhas": 100,
        "assento": null,
        "malasDespachadas": null,
        "dataHoraConfirmacao": null,
        "eticket": null
    }
```
Exemplo do Response em JSON para buscar por CPF:
```
{
    "cpf": "222.222.222-22",
    "nome": "Ross Geller",
    "dataNascimento": "02/11/1966",
    "classificacao": "PRATA",
    "milhas": 150
}
```
<br>
<a id="consultaAssento"></a>

#### Consulta de Assento
A consulta de Assento pode ser feita apenas por todos com Requests de GET  no endereço "../api/assentos/{id}" e recebem uma resposta em JSON.

Exemplo do Response em JSON:
```
[
    {
        "id": 1,
        "nome": "1A",
        "ocupado": false,
        "emergencia": false
    },
    {
        "id": 2,
        "nome": "1B",
        "ocupado": false,
        "emergencia": false
    },
    {
        "id": 3,
        "nome": "1C",
        "ocupado": false,
        "emergencia": false
    },
]
```
<br>
<a id="tech"></a>

## Tecnologias

É utilizado para seu funcionamento:
- [Java] - Linguagem de programação orientada a objetos.
- [Maven] - Software de gerenciamento baseado em modelo POM.
- [Spring] - Framework open source para Java.
- [H2] - Banco de dados.
- [Lombok] - Biblioteca java para redução de código boilerplate.
- [Mapstruct] - Processador de anotações Java.
- [Hibernate Validation] - Validador por implementação de beans de referência.
<br>

<a id="install"></a>

## Instalação
Por se tratar de uma API Rest, o LABSky-API não possui views. É necessária uma plataforma para envios de requests HTTPs como por exemplo o Postman para a interação com o sistema.

O LABSky-API está configurado para a conexão com o banco de dados H2. É possível a utilização de outros bancos relacionais como MySQL, apenas realize alterações no arquivo "..\LABSky-API\projeto\src\main\resources\application.properties".

Exemplo para a conexão com o Banco de Dados Oracle:
```
#URL do banco de dados desejado
spring.datasource.url= jdbc:oracle:thin:@localhost:1521/labsky-api

#Nome de usuário e senha do usuário do banco
spring.datasource.username= sys as sysdba
spring.datasource.password= admin
```

Driver para o Banco de Dados:
```
spring.datasource.driver-class-name= oracle.jdbc.OracleDriver
```
<br>

[Java]: <https://www.java.com/>
[Maven]: <https://maven.apache.org/>
[Spring]: <https://spring.io/>
[H2]: <https://www.h2database.com/html/main.html>
[Lombok]: <https://projectlombok.org/>
[Mapstruct]: <https://mapstruct.org/>
[Hibernate Validation]: <https://hibernate.org/validator/>

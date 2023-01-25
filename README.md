# API TESTE ATTORNATUS

### Documentação de Referência
***
### Introdução
A API em questão pode ser usada para os seguintes processos:
- Criar Pessoas
- Criar endereços
- Associar endereços principais a pessoas
- Listar endereços
- Listar endereços daquela pessoa

# Instruções de uso

Compilar e executar o projeto na sua IDE de escolha

---

## Endpoints de Pessoa

### Criar Pessoa - POST - /api/v1/pessoa
Para criar uma pessoa, enviar uma request no modelo do curl abaixo:

`curl --location --request POST 'http://localhost:8080/api/v1/pessoa' \
     --header 'Content-Type: application/json' \
    --data-raw '{
        "nome": "Pessoa Insert",
        "dtNascimento": "2001-01-01"
}'`

A request possui os seguinte campos:
- nome (Obrigatório), String
- dtNascimento (Obrigatório), String no formato AAAA-MM-DD

A API não realiza validações alem do formato para criação de pessoa, exceções serão retornadas como resposta de API.

A Resposta esperada segue o formato:
`{
    "content": {
        "id": 1,
        "nome": "Nome Inserido",
        "dtNascimento": "2001-01-01"
    }
}`

### Buscar Pessoa - POST - /api/v1/pessoa/lista
Para buscar uma pessoa, é utilizado um gerador de specifications do JPA, as request são genéricas, utilizadas para filtrar diversos parâmetros de pessoa, e seguem o seguinte padrão:

`curl --location --request POST 'http://localhost:8080/api/v1/pessoa/lista' \
--header 'Content-Type: application/json' \
--data-raw '{
    "filters": [
        {
            "key": "id",
            "operator": "EQUAL",
            "field_type": "INTEGER",
            "value": "1"
        }
    ],
    "sorts": [{
        "key": "nome",
        "direction": "ASC"
    }
    ],
    "page": 0,
    "size": 10
}'`

Dentro da lista de filtros, passamos o objeto no seguinte formato:
- key: O nome da chave que será utilizada para filtrar
- operator: O Operador que será utilizado para busca, pode ser EQUAL, NOT_EQUAL, LIKE, IN e BETWEEN, e seguem a mesma funcionalidade dos operadores SQL.
- field_type: O tipo do campo, pode ser BOOLEAN, CHAR, DATE (AAAA-MM-DD), DOUBLE, INTEGER, LONG e STRING e seguem as mesmas convenções de tipo do Java
- value: O valor do campo em questão
Dentro da lista de Sorts, podemos passar os seguintes parâmetros:
- key: A chave usada para ordenação
- direction: A sequência que será ordenada, pode ser ASC ou DESC, seguindo o padrão do SQL
Os dois ultimos parâmetros são a página atual da paginação e o tamanho da pagina

A resposta paginada segue o formato:

`{
    "content": [
        {
            "id": 1,
            "nome": "Nome Teste 1",
            "dtNascimento": "2000-01-01"
        },
        {
            "id": 2,
            "nome": "Nome Teste 2",
            "dtNascimento": "2000-01-01"
        },
        {
            "id": 3,
            "nome": "Nome Teste 3",
            "dtNascimento": "2000-01-01"
        },
        {
            "id": 4,
            "nome": "Nome Teste 4",
            "dtNascimento": "2000-01-01"
        },
        {
            "id": 5,
            "nome": "Nome Teste 5",
            "dtNascimento": "2000-01-01"
        }
    ],
    "pageable": {
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "pageSize": 100,
        "pageNumber": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 8,
    "size": 100,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 8,
    "empty": false
}`

### Editar pessoa - PUT - /api/v1/pessoa/{idPessoa}
Este endpoint pode ser utilizado para alterar informações pessoais e associar um endereço a uma pessoa, e segue o seguinte padrão:

`curl --location --request PUT 'http://localhost:8080/api/v1/pessoa/1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nome": "Teste de alteração de pessoa",
    "dtNascimento": "2001-01-01",
    "idEndereco": 1
}'`

A request possui os seguinte campos:
- nome (Obrigatório), String
- dtNascimento (Obrigatório), String no formato AAAA-MM-DD
- idEndereco, Integer

Os campos nome e dtNascimento são mandatórios mesmo que não haja alteração, o idEndereço é o ID do registro do endereço no serviço de endereços, a resposta esperada segue o formato:


`{
    "content": {
        "id": 1,
        "nome": "Teste de alteração de pessoa",
        "dtNascimento": "2001-01-01",
        "endereco": {
            "id": 1,
            "cep": "01206000",
            "logradouro": "Avenida Rio Branco",
            "numero": "744",
            "cidade": "Sao Paulo"
        }
    }
}`

### Listar endereços da Pessoa - GET - /api/v1/pessoa/lista-enderecos/{idPessoa}
O endpoint em questão é utilizado para listar todos endereços de uma pessoa em questão, e segue o padrão:

`curl --location --request GET 'localhost:8080/api/v1/pessoa/lista-enderecos/1'`

A resposta esperada é no formato:
`{
    "content": [
        {
            "id": 1,
            "cep": "01206000",
            "logradouro": "Avenida Rio Branco 1",
            "numero": "744",
            "cidade": "Sao Paulo",
            "idPessoa": 1
        },
        {
            "id": 2,
            "cep": "01206000",
            "logradouro": "Avenida Rio Branco 2",
            "numero": "744",
            "cidade": "Sao Paulo",
            "idPessoa": 1
        },
        {
            "id": 3,
            "cep": "01206000",
            "logradouro": "Avenida Rio Branco 3",
            "numero": "744",
            "cidade": "Sao Paulo",
            "idPessoa": 1
        },
        {
            "id": 4,
            "cep": "01206000",
            "logradouro": "Avenida Rio Branco 4",
            "numero": "744",
            "cidade": "Sao Paulo",
            "idPessoa": 1
        }
    ]
}`

---
## Endpoints de Endereço

### Criar Endereço - POST - /api/vi/endereco
Para criar um endereço, enviar uma request no modelo do curl abaixo:

`curl --location --request POST 'http://localhost:8080/api/v1/endereco' \
--header 'Content-Type: application/json' \
--data-raw '{
    "cep": "01206000",
    "logradouro": "Avenida Rio Branco 4",
    "numero": "744",
    "cidade": "Sao Paulo",
    "idPessoa": 1
}'`

A request possui os seguintes campos:
- cep (Obrigatório), String
- logradouro (Obrigatório), String
- numero (Obrigatório), String (Caso seja sem número, ou tenha fundos)
- cidade (Obrigatório), String
- idPesoa (Obrigatório), String

Não é possível atribuir a uma pessoa um endereço que não tenha sido cadastrado por ela, resultando em resposta negativa da API.
A resposta esperada para este procedimento segue o padrão:

`
{
    "content": {
        "id": 4,
        "cep": "01206000",
        "logradouro": "Avenida Rio Branco 4",
        "numero": "744",
        "cidade": "Sao Paulo",
        "idPessoa": 1
    }
}
`

### Buscar Endereço - POST - /api/v1/endereco/lista
Para buscar um endereço, é utilizado um gerador de specifications do JPA, as request são genéricas, utilizadas para filtrar diversos parâmetros de endereço, e seguem o seguinte padrão:

`
curl --location --request POST 'http://localhost:8080/api/v1/endereco/lista' \
--header 'Content-Type: application/json' \
--data-raw '{
    "filters": [
        {
            "key": "logradouro",
            "operator": "LIKE",
            "field_type": "STRING",
            "value": "Rua"
        }
    ],
    "sorts": [
        {
        "key": "id",
        "direction": "ASC"
        }
    ],
    "page": null,
    "size": null
}'
`

A resposta esperada segue o formato:

`
{
    "content": [
        {
            "id": 1,
            "cep": "01206000",
            "logradouro": "Avenida Rio Branco 1",
            "numero": "744",
            "cidade": "Sao Paulo",
            "idPessoa": 1
        },
        {
            "id": 2,
            "cep": "01206000",
            "logradouro": "Avenida Rio Branco 2",
            "numero": "744",
            "cidade": "Sao Paulo",
            "idPessoa": 1
        },
        {
            "id": 3,
            "cep": "01206000",
            "logradouro": "Avenida Rio Branco 3",
            "numero": "744",
            "cidade": "Sao Paulo",
            "idPessoa": 1
        },
        {
            "id": 4,
            "cep": "01206000",
            "logradouro": "Avenida Rio Branco 4",
            "numero": "744",
            "cidade": "Sao Paulo",
            "idPessoa": 1
        }
    ],
    "pageable": {
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "pageSize": 100,
        "pageNumber": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 4,
    "size": 100,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 4,
    "empty": false
}
`

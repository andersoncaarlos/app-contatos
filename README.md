# Aplicação de Cadastro de Pessoas e Contatos

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

Este projeto é uma aplicação Spring Boot que permite o cadastro e gerenciamento de pessoas e seus contatos. Utiliza um banco de dados MySQL para armazenamento local, PostgreSQL para armazenamento em nuvem e o Swagger para documentação da API.

## Descrição

Esta aplicação permite o cadastro e gerenciamento de pessoas e contatos, com métodos CRUD completos. As pessoas podem ser cadastradas com informações de endereço, e contatos podem ser vinculados a elas.

## Tecnologias Utilizadas

- **Java**
- **Spring Boot**
- **MySQL**
- **PostgreSQL**
- **Open API - Swagger**
- **Maven**

## Endpoints da API

### Pessoa

- **POST /api/pessoas**: Cadastrar uma nova pessoa.
  - **Campos**: `id`, `nome`, `endereco`, `cep`, `cidade`, `uf`.
- **GET /api/pessoas**: Listar todas as pessoas.
- **GET /api/pessoas/{id}**: Buscar pessoa por ID.
- **GET /api/pessoas/malaDireta/{id}**: Buscar pessoa por ID com seu endereço completo em um único registro.
- **PUT /api/pessoas**: Atualizar uma pessoa.
- **DELETE /api/pessoas/{id}**: Deletar uma pessoa.

### Contato

- **POST /api/contatos**: Adicionar contato para uma pessoa.
  - **Campos**: `id`, `contato`, `tipo`.
- **GET /api//contatos**: Listar todos os contatos de uma pessoa.
- **GET /api/contatos/{id}**: Buscar contato por ID.
- **PUT /api/contatos**: Atualizar um contato.
- **DELETE /api/contatos/{id}**: Deletar um contato.

### Link da Documentação OpenAPI:

- Local - http://localhost:8080/swagger-ui.html
- ** Para acessar a documentação localmente deve-se alterar de "prod" para "dev" na linha 25 do arquivo application-properties. **

- Nuvem - https://appcontatos.onrender.com


  


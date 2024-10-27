# Biblioteca API

## Descrição

Este projeto é uma API REST para uma biblioteca que permite gerenciar **Usuários**, **Livros** e **Empréstimos**.
Inclui uma funcionalidade de recomendação de livros baseada nas categorias de livros já emprestados pelo usuário.

### Funcionalidades

- **CRUD de Usuários**: Criação, leitura, atualização e deleção de usuários.
- **CRUD de Livros**: Criação, leitura, atualização e deleção de livros.
- **Empréstimos**: Criação e atualização de empréstimos, associando livros a usuários.
- **Recomendação de Livros**: Sugestão de livros com base nas categorias emprestadas pelo usuário.
- **Testes Unitários**: Cobertura dos principais serviços da aplicação com JUnit.

## Requisitos

- **Java**: versão 17 ou superior
- **Maven**: versão 3.6+
- **Docker**: Para gerenciamento do banco de dados
- **Spring Boot**: Framework usado para criar a aplicação
- **JUnit**: Para testes unitários

## Configuração e Execução

O projeto foi desenvolvido usando IntelliJ IDEA, o banco de dados configurado foi PostgreSQL, com as seguintes
informações:

- host: localhost
- porta: 5432
- database: library-dev
- usuário: postgres
- senha: admin

Abaixo estão o passo a passo de como configurar o banco local criando um container Docker, caso o tenha instalado.

### Configuração do Banco de Dados com Docker

1. Certifique-se de que o Docker está instalado e em execução.
   a. Aqui o Docker se faz necessário para a configuração do banco de dados, caso não haja a possibilidade de
   instalação, é necessário a instalação do PostgreSQL com requisições para a porta 5432, e uma database de nome
   `library-dev` criada.
2. Execute o comando para iniciar os serviços:
   ```bash
      docker compose up -d
   ```
3. Dessa forma, será disponibilizado também o pgAdmin, acessando pelo endereço http://localhost:16543, sendo necessário
   a configuração ao banco: endereço: `db`, porta: `5432`, usuário: `postgres`, senha: `admin`

### Executando a Aplicação

Você pode executar a aplicação abrindo o projeto por sua IDE preferida e após realizar todos os imports do maven, clicar
para executar

1. Na linha de comando, navegue até a raiz do projeto.
2. Execute o comando:
   ```bash
        mvn spring-boot:run
   ```

### Endpoints da Aplicação

Os endpoints estão organizados dentro do diretório [docs](docs).


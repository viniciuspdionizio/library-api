Abaixo estão todos os endpoints criados para consulta e manipulação dos dados, caso prefira consultar manualmente
importe no Postman o arquivo desse mesmo diretório [endpoints](endpoints.json)

## Endpoints da API - Livros

- **GET /api/books/{id}**  
  **Descrição**: Obtém um livro específico pelo seu ID. Retorna os detalhes do livro como um `BookResponseDTO`.

- **GET /api/books/page**  
  **Descrição**: Lista todos os livros com suporte a filtros e paginação. Aceita parâmetros opcionais para filtrar os
  resultados por `filter` e `status`, além de parâmetros de paginação.

- **POST /api/books**  
  **Descrição**: Registra um novo livro. O corpo da requisição deve conter os dados do livro a serem criados,
  representados como um `BookRequestDTO`. Retorna o livro criado, incluindo seu ID.

- **PUT /api/books/{id}**  
  **Descrição**: Atualiza os dados de um livro existente com base no ID fornecido. O corpo da requisição deve conter os
  dados atualizados representados como um `BookRequestDTO`. Retorna o livro atualizado.

- **DELETE /api/books/{id}**  
  **Descrição**: Deleta um livro específico pelo seu ID. Retorna um status 204 No Content se a deleção for bem-sucedida.

## Endpoints da API - Usuários

- **GET /api/users/{id}**  
  **Descrição**: Obtém um usuário específico pelo seu ID. Retorna os detalhes do usuário como um `UserResponseDTO`.

- **GET /api/users/page**  
  **Descrição**: Lista todos os usuários com suporte a filtros e paginação. Aceita um parâmetro opcional para filtrar os
  resultados por `filter`, além de parâmetros de paginação.

- **GET /api/users/{id}/recommendations**  
  **Descrição**: Retorna uma lista de recomendações de livros para o usuário com base no seu ID e status opcional.

- **POST /api/users**  
  **Descrição**: Registra um novo usuário. O corpo da requisição deve conter os dados do usuário a serem criados,
  representados como um `UserRequestDTO`. Retorna o usuário criado, incluindo seu ID.

- **PUT /api/users/{id}**  
  **Descrição**: Atualiza os dados de um usuário existente com base no ID fornecido. O corpo da requisição deve conter
  os dados atualizados representados como um `UserRequestDTO`. Retorna o usuário atualizado.

- **DELETE /api/users/{id}**  
  **Descrição**: Deleta um usuário específico pelo seu ID. Retorna um status 204 No Content se a deleção for
  bem-sucedida.

## Endpoints da API - Empréstimos

- **GET /api/loans/page**  
  **Descrição**: Lista todos os empréstimos com suporte a filtros e paginação. Aceita parâmetros opcionais para filtrar
  os resultados por `userId`, `bookId` e `status`. Também permite a paginação.

- **POST /api/loans**  
  **Descrição**: Registra um novo empréstimo. O corpo da requisição deve conter os dados do empréstimo a serem criados,
  representados como um `LoanRequestDTO`. Retorna o empréstimo criado, incluindo seu ID.

- **PATCH /api/loans/{id}**  
  **Descrição**: Atualiza os dados de um empréstimo existente com base no ID fornecido. Aceita parâmetros opcionais para
  alterar o `status` do empréstimo e a `returnDate` (data de devolução). Retorna o empréstimo atualizado.

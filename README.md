# api-challenge-backend
<h2>API CHALLENGE BACKEND - Desenvolvendo esta API REST com Java/Spring Boot</h2>

Este projeto consiste em atender a alguns requisitos de um desafio backend, onde, utilizando de dados da API Star Wars [SWAPI.DEV](https://swapi.dev/), criei alguns recursos para consumo. São eles:

* 1 - Listar todos os filmes da saga Star Wars:
    ```
    http://localhost:8080/v1/api/starwars/movies
    ```
* 2 - Detalhar um filme da saga sem especificar qual filme:
    ```
    http://localhost:8080/v1/api/starwars/movie/detail
    ```
* 3 - Detalhar um filme específico (usei o identificador do episódio)
    ```
    http://localhost:8080/v1/api/starwars/movie/detail/3
    ```
* 4 - Atualizar a descrição de um filme específico (usei o identificador do episódio). Neste cenário, é necessário enviar a nova descrição no corpo da requisição.
    ```
    http://localhost:8080/v1/api/starwars/movie/3
    ```


Para executar o projeto no terminal, esteja na pasta raiz do projeto e digite o seguinte comando:

```
mvn spring-boot:run 
```

Após executar o comando acima, basta abrir um navegador de sua preferência e executar as urls acima (itens de 1 a 3, o item 4 execute a partir de uma ferramenta como por exemplo, o Postman).

Para persistência de dados, utilizei o banco em memória H2DATABASE.

"Ah, Hugo... mas eu quero ver com meus próprios olhos que realmente está salvando na base de dados em memória!"

Sem problemas, siga os 3 passos abaixo e seja feliz:

* Passo 1: Para acessar a base em memória, basta abrir um navegador e digitar na barra de endereço a seguinte url:
    ```
    http://localhost:8080/h2 
    ``` 

* Passo 2: Deves acessar a base com as seguintes credenciais:
    ```
    JDBC URL: jdbc:h2:file:./moviedb
    User Name: sa
    Password: (aqui é só deixar em branco mesmo rs)
    ```

* Passo 3: Para visualizar os dados na base, na caixa de instruções SQL (SQL statement) digite o seguinte comando abaixo, clique em "Run" para executar o comando e assim verás os dados.
    ```
    SELECT * FROM MOVIE
    ```

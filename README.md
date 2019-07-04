# [Cursos UFCG](https://api-cursosufcg.herokuapp.com/api/swagger-ui.html)
> Repositório para o Back-end do projeto da disciplina de Projeto de Software

----------------
## Rotas:
> Rotas da API Rest.

- ### Comentários: Responsável por controlar as funcionalidades que um comentário possui
Rota       | Método   |  Função    |  Parâmetros   |
:--------- | :------: | :--------- | :-----------  |
/v1/comentario/{id}/{email} | POST | Cria um comentário no perfil especificado pelo ID | id: Id do perfil, email: Email do usuário
/v1/comentario/usuario/{email} | GET | Retorna todos os comentários de um usuário | email: Email do usuário
/v1/comentario/respostas/{id}/{email} | GET | Retorna as respostas de um comentário | id: id do comentário, email: Email do usuário
/v1/comentario/responderComentario/{idComentario}/{email} | POST | Cria resposta de um comentário | id: id do comentário, email: Email do usuário
/v1/comentario/apagarComentario/{idPerfil}/{idComentario}/{email} | DELETE | Apaga um comentário de um perfi | idPerfil: id do perfil, idComentario: id do comentário, email: email do usuário que quer apagar o comentário.

- ### Disciplinas: Responsável por controlar as funcionalidades que uma disciplina possui
Rota       | Método   |  Função    |  Parâmetros   |
:--------- | :------: | :--------- | :-----------  |
/v1/disciplina/ | GET | Retorna todas as disciplinas |
/v1/disciplina/{id} | GET | Retorna uma discicplina pelo ID | id: id da disciplina
/v1/disciplina/buscar/{query} | GET | Retorna as disciplinas por substring | query: substring a ser procurada
/v1/disciplina/ | POST | Cria uma disciplina | 
/v1/disciplina/admin | POST | Cria várias disciplinas | 
/v1/disciplina/{id} | DELETE | Apaga uma disciplina | id: id da disciplina a ser deletada

- ### Login:  Responsável por controlar as funcionalidades de login
Rota       | Método   |  Função    |  Parâmetros   |
:--------- | :------: | :--------- | :-----------  |
/v1/login/ | POST | Realiza um login de um usuário

- ### Perfil: Responsável por controlar as funcionalidades que um perfil possui
Rota       | Método   |  Função    |  Parâmetros   |
:--------- | :------: | :--------- | :-----------  |
/v1/perfil/ | GET | Retorna todos os perfis | 
/v1/perfil/buscar/{query} | GET | Retorna todos os perfis por substring | query: substring a ser procurada
/v1/perfil/curtir/{id}/{email} | POST | Cria um like no perfil | id: id do perfil, email: email do usuário a dar o like
/v1/perfil/ordenarPorComentarios | GET | Retorna perfis ordenados por quantidade de comentários | 
/v1/perfil/ordenarPorLikes | GET | Retorna perfis ordenados por quantidade de like |
/v1/perfil/{idDisciplina} | POST | Cadastra um perfil | idDisciplina: id da disciplina
/v1/perfil/{id}/{email} | GET | Retorna um perfil pelo id | id: id do perfil, email: email do usuário

- ### Usuário: Responsável por controlar as funcionalidades que uma usuário possui
Rota       | Método   |  Função    |  Parâmetros   |
:--------- | :------: | :--------- | :-----------  |
/v1/usuario/ | POST | Cria um usuário |
/v1/usuario/{email} | DELETE | Apaga um usuário | 
/v1/usuario/{email} | GET | Retorna um usuário pelo email | 

------------------

## Explicação básica da API Rest
> Explicação básica sobre o funcionamento da API.

- A API Rest trata-se de mostrar as informações básicas sobre uma disciplina da UFCG, por enquanto apenas do Curso de Computação.

- Algumas rotas são privadas, apenas usuário com `token` poderão fazer requisições. Caso não tenham, terão que se cadastrar e depois realizar o login, essa última irá retornar um `token` no corpo da resposta.

- Existe uma rota: `/v1/disciplina/admin` que é usada para cadastrar todas as disciplinas e juntamente criando os perfis das mesmas. A rota recebe uma lista com os nomes das disciplinas.

- Um usuário autenticado consegue: Criar comentários, responder comentários, curtir(descurtir) os perfis, fazer listagens dos perfis por quantidade de likes e por comentários. Todas as rotas necessitam que o `token`seja passado no `header`da requisição.

- Um usuário não autenticado: Consegue apenas listar as disicplinas(não consegue ver os perfis).

## Diretórios:
> Explicação básica de cada diretório.

- `./authentication`: Onde os arquivos de Filtragem de token ficam, cada requisição feita irá passar pela classe `TokenFilter` para ser realizado a verificação do `token`

- `./config`: Onde encontra apenas um arquivo, responsável pela configuração da documentação da API, Swagger.

- `./controllers`: Onde encontra-se todos os meus controles da aplicação, todas as rotas possiveis estão descritas nos meus controles.

- `./exceptions`: Onde estão as minhas exceções, e padronização das minhas Exceções para o `response`

- `./ models`: Onde estão os meus modelos, com a anotação `Entity`, tornando elas uma entidade no meu banco de dados.

- `./ordenation`: Onde estão as classes de ordenação de meus perfis.

- `./repository`: Onde estão os meus `DAO`(Data Access Object ou Objeto de Acesso a Dados). Onde seu principal objetivo é separar regras de negócio de regras de acesso a banco de dados.

- `./services`: Onde estão meus servições de cada `DAO` da minha aplicação. Onde irá ocorrer cada verificação ou regra de negócio antes de chamar algum método do meu `DAO`.

- `./utils`: Onde está minha classe responsável por enviar um email para o usuário após ele se cadastrar.

## Testando a API localmente(Caso vá usar a URL do heroku, as disicplinas e perfis já estão criadas):

- Antes de tudo devemos criar um usuário pela rota: `/v1/usuario/`, passando no body da requisição:
  ~~~javascript
  {
      "email": "string",
      "primeiroNome": "string",
      "senha": "string",
      "ultimoNome": "string"
  }
  ~~~

- Após a criação, devemos realizar o login, para termos o nosso `token`, pela rota: `/v1/login/`, passando no body da requisição:
~~~javascript
  {
      "email": "string",
      "senha": "string",
  }
  ~~~

- Após isso devemos cadastrar nossas disciplinas: encontra-se no [arquivo](https://drive.google.com/file/d/1r3WFTRwqh8TrQ0f5DvNO6362hhkYnglz/view). Pode ser cadastradas pela rota: `/v1/disciplina/admin`. Passando no corpo da requisição o contéudo do arquivo acima, passando o `token` na requisição(no header).

- Depois disso, poderá ser realizado todas as demais funcionalidades do sistema: Criar outros usuários, cadastrar disciplinas, ver perfis e suas informações, comentar, responder, curtir e etc.





# [Cursos UFCG](https://api-cursosufcg.herokuapp.com/api/swagger-ui.html)
> Repositório para o Back-end do projeto da disciplina de Projeto de Software

## Rotas:
> Rotas da API Rest.

### Comentários: Responsável por controlar as funcionalidades que um comentário possui
Rota       | Método   |  Função    |  Parâmetros   |
:--------- | :------: | :--------- | :-----------  |
/v1/comentario/{id}/{email} | POST | Cria um comentário no perfil especificado pelo ID | id: Id do perfil, email: Email do usuário
/v1/comentario/usuario/{email} | GET | Retorna todos os comentários de um usuário | email: Email do usuário
/v1/comentario/respostas/{id}/{email} | GET | Retorna as respostas de um comentário | id: id do comentário, email: Email do usuário
/v1/comentario/responderComentario/{idComentario}/{email} | POST | Cria resposta de um comentário | id: id do comentário, email: Email do usuário
/v1/comentario/apagarComentario/{idPerfil}/{idComentario}/{email} | DELETE | Apaga um comentário de um perfi | idPerfil: id do perfil, idComentario: id do comentário, email: email do usuário que quer apagar o comentário.

### Disciplinas: Responsável por controlar as funcionalidades que uma disciplina possui
Rota       | Método   |  Função    |  Parâmetros   |
:--------- | :------: | :--------- | :-----------  |
/v1/disciplina/ | GET | Retorna todas as disciplinas |
/v1/disciplina/{id} | GET | Retorna uma discicplina pelo ID | id: id da disciplina
/v1/disciplina/buscar/{query} | GET | Retorna as disciplinas por substring | query: substring a ser procurada
/v1/disciplina/ | POST | Cria uma disciplina | 
/v1/disciplina/admin | POST | Cria várias disciplinas | 
/v1/disciplina/{id} | DELETE | Apaga uma disciplina | id: id da disciplina a ser deletada

### Login:  Responsável por controlar as funcionalidades de login
Rota       | Método   |  Função    |  Parâmetros   |
:--------- | :------: | :--------- | :-----------  |
/v1/login/ | POST | Realiza um login de um usuário

### Perfil: Responsável por controlar as funcionalidades que um perfil possui
Rota       | Método   |  Função    |  Parâmetros   |
:--------- | :------: | :--------- | :-----------  |
/v1/perfil/ | GET | Retorna todos os perfis | 
/v1/perfil/buscar/{query} | GET | Retorna todos os perfis por substring | query: substring a ser procurada
/v1/perfil/curtir/{id}/{email} | POST | Cria um like no perfil | id: id do perfil, email: email do usuário a dar o like
/v1/perfil/ordenarPorComentarios | GET | Retorna perfis ordenados por quantidade de comentários | 
/v1/perfil/ordenarPorLikes | GET | Retorna perfis ordenados por quantidade de like |
/v1/perfil/{idDisciplina} | POST | Cadastra um perfil | idDisciplina: id da disciplina
/v1/perfil/{id}/{email} | GET | Retorna um perfil pelo id | id: id do perfil, email: email do usuário

### Usuário: Responsável por controlar as funcionalidades que uma usuário possui
Rota       | Método   |  Função    |  Parâmetros   |
:--------- | :------: | :--------- | :-----------  |
/v1/usuario/ | GET | Cria um usuário |
/v1/usuario/{email} | DELETE | Apaga um usuário | 
/v1/usuario/{email} | GET | Retorna um usuário pelo email | 

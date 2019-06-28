# [Cursos UFCG](http://api-cursosufcg.herokuapp.com/api/)
> Repositório para o Back-end do projeto da disciplina de Projeto de Software

## UCDb: classificações e reviews de cursos da UFCG
> Objetivo: organizar plataforma colaborativa para avaliações e informações sobre disciplinas de cursos da UFCG

O UFCG Cursos database é uma aplicação para classificação e reviews de disciplinas de cursos da UFCG. Por enquanto, a versão 1 do sistema será alimentada apenas com disciplinas do curso de Ciência da Computação. Os usuários dessa aplicação irão construir conteúdo sobre as disciplinas de forma colaborativa através de comentários e likes nas disciplinas. O sistema deve usar essa informação construída para rankear as disciplinas do curso.

## Casos de Uso:

- [X] - Caso de uso 1: cadastrar/autenticar usuários.

  - Cadastrar usuários no sistema com primeiro nome, ultimo nome, e-mail e senha. O e-mail deve ser o identificador único dos usuários, assim se já existe um usuário com um certo e-mail, outro usuário com o mesmo e-mail não poderá ser cadastrado. Se usuário com o e-mail informado não existir ainda, ele deverá ser criado no sistema.

  - Extra: o sistema deve enviar um email de boas vindas para o usuário e um link para o sistema.

  - Usuários cadastrados podem se autenticar em uma tela de login onde deve passar e-mail e senha. Um usuário que foi autenticado via email/senha deve receber um JWT. Esse token será usado posteriormente no header authorization HTTP, para interações futuras com a API.

- [X] - Caso de uso 2: pesquisar disciplinas a partir de uma (sub)string.

  - Deve ser possível recuperar o nome completo de disciplinas a partir de substrings. Por exemplo, ao pesquisar "laboratório" uma listagem de nomes e links para as disciplinas deve aparecer, ao estilo:
    
    - 2 - Laboratório de Programação 1
    - 13 - Laboratório de Programação 2
    - 27 - Laboratório de Estrutura de Dados

  - Naturalmente, se espera que a listagem seja apropriadamente formatada em termos visuais. Os números que antecedem as disciplinas são identificadores das disciplinas que podem ser usados para recuperá-las em outras interações com a API. Com esta funcionalidade o usuário pode identificar os nomes das disciplinas cadastradas no UCDb. Esta funcionalidade é pública, pode ser acessada por qualquer usuário, mesmo que não esteja autenticado.

  - Todos os nomes das disciplinas do curso de Ciência da Computação estão neste arquivo json. Apenas os nomes estão aí, IDs únicos devem ser gerados para cada disciplina.

- [X] - Caso de uso 3: Deve ser possível recuperar o perfil de uma disciplina a partir do seu código numérico.

  - Cada disciplina deve estar associada a um perfil. Apenas usuários autenticados podem ter acesso a esta funcionalidade. O perfil de uma disciplina mantém informações que são definidas de forma colaborativa pelos usuários do UCDb. As seguintes informações fazem parte do perfil de uma disciplina: número de likes (lembrando que deve ser possível identificar se um certo usuário deu ou não like no sistema), coleção de notas dadas pelos alunos sobre a disciplina, coleção de comentários que os alunos escreveram sobre a disciplina. Ao recuperar o perfil da disciplina, deve ser possível ver a nota da disciplina, que deve ser a média aritmética de todas as notas dadas à disciplina, o número de likes e dislikes. Os comentários mantém não apenas o texto do comentário, mas o usuário que comentou e a data/hora em que o comentário foi realizado.

  - Detalhes da API: para esta rota, a API deve retornar além do nome da disciplina, o número de likes, a média das notas dadas pelos alunos sobre a disciplina, número de alunos que já deram nota para a disciplina, coleção de comentários que os alunos escreveram sobre a disciplina, e informar se o usuário interagindo com o sistema (que chamou esta rota) deu like (true ou false) e também marcar de alguma forma que comentários são desse usuário interagindo com o sistema.

  - Detalhes da view: o frontend deve exibir todos os comentários feitos, ordenados da mais recente para a mais antiga. Acima da listagem de comentários deve ser exibido algum widget para permitir ao usuário fazer um novo comentário.

- [X] - Caso de uso 4: Adicionar comentários de uma disciplina.

  - Usuários autenticados podem adicionar comentários aos perfis das disciplinas. Deve ser possível inserir comentários em resposta a outros comentários, sendo assim, um comentário deve manter uma referência para outro comentário.

  - Detalhes da view: o frontend fica livre para decidir como mostrar os comentários e os comentários em resposta a outros comentários. Diferentes sistemas colaborativos decidem diferentes formas de exibição, por exemplo, twitter e facebook.

- [X] - Caso de uso 5: Apagar comentários de uma disciplina.

  - Um usuário autenticado pode apagar comentários que tenha feito anteriormente.

  - Detalhes da API: a deleção é lógica: o texto não será mais fornecido pela API, porém, ele é mantido na base de dados. Assim, no backend os comentários devem manter esta informação: se o comentário foi apagado ou não. Ao responder uma requisição o backend deve enviar o texto vazio para os comentários apagados.

  - Detalhe da view: na listagem de comentários, cada comentário feito pelo próprio usuário operando o sistema deve ter um widget que permita apagar o comentário.

- [X] - Caso de uso 6: Dar/retirar like em uma disciplina.

  - Um usuário autenticado pode dar no máximo um like na view de um perfil de uma disciplina. Significa um like para a disciplina. Um usuário que já deu like em uma disciplina não pode dar um segundo like. Depois que o usuário já deu o like ele passa a poder retirar o like (desistir de dar like).

  - Detalhes da API: esta rota deve atualizar o perfil da disciplina mantendo a informação dos usuários que deram like.

  - Detalhe da view: sugere-se o uso de um widget ao estilo dos que são usados pelo youtube para funcionalidade semelhante.

- [ ] - Caso de uso 7: mostrar ranking das disciplinas.

  - Um usuário autenticado pode ver o ranking das disciplinas de acordo com algum critério de livre escolha. Por exemplo, pelo número de likes, pelo número de comentários, pelo número de dislikes, pela proporção de likes/(likes+dislikes), etc.

# Forum Api

## Uma api para realizar posts de perguntas e respostas

### 🚧 Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina o [Docker](https://docs.docker.com/)

### 🎲 Rodando a aplicação

```bash
# Clone este repositório
$ git clone <https://github.com/alanjuniorn8/ForumSpringAPI>

#  No diretório raiz da aplicação construa a imagem do container
$ docker build -t springForum .

# Crie o container
$ dotnet run -p 8080:8080 -e SPRING_PROFILES_ACTIVE='prod' -e FORUM_DATABASE_DRIVER='org.h2.Driver' -e FORUM_DATABASE_URL='jdbc:h2:mem:alura-forum'
  -e FORUM_DATABASE_USERNAME ='sa' -e FORUM_DATABASE_PASSWORD='' -e FORUM_JWT_SECRET='your-secret' springForum

# O servidor inciará na porta:8080


### 🛠 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:
- :coffee: [Java](https://www.java.com/pt-BR/)
- :leaves: [Spring Boot](https://spring.io/projects/spring-boot)
- :black_joker: [JUnit](https://junit.org/junit4/)
- :whale: [Docker](https://docs.docker.com/)

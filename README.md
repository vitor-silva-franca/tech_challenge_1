# 🚀 FIAP Tech Challenge

Este projeto foi desenvolvido como parte do Tech Challenge da FIAP. Ele simula um sistema de gestão de restaurantes e pedidos, seguindo os princípios da Clean Architecture e Domain-Driven Design (DDD).

---

## ✅ Pré-requisitos

- Java 21 instalado  
- Maven 3.9+ instalado  
- Docker + Docker Compose (para subir o banco de dados)  
- IDE de sua preferência (IntelliJ IDEA, VSCode, Eclipse)  

---

## ▶️ Rodar a aplicação

Execute o comando abaixo na raiz do projeto:

```bash
docker-compose up --build
```

Isso irá:

- Subir o banco de dados PostgreSQL
- Construir e rodar a aplicação Spring Boot
- Deixar a API disponível na porta `8080`

---

## 📄 Acessar a documentação Swagger

Após iniciar o projeto, acesse no navegador:

👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Você verá todos os endpoints da API, com exemplos de requests/responses e possibilidade de testes diretamente na interface.

---

## 🛠️ Tecnologias utilizadas

- Java 21  
- Spring Boot 3  
- Spring Data JPA  
- PostgreSQL  
- Docker  
- Swagger (SpringDoc OpenAPI)  
- Maven  
- Hibernate  

---

## 📚 Mais informações

Este projeto é uma simulação acadêmica com fins educacionais e segue boas práticas de arquitetura e desenvolvimento em Java com Spring Boot.

Desenvolvido por [Vitor França](https://vitorsilvafranca.com/)

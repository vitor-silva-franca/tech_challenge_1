package com.vitorsilvafranca.tech_challenge_1.infrastructure.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FIAP - Tech Challenge - Fase 1 de Arquitetura e Desenvolvimento Java")
                        .description("API desenvolvida para o Tech Challenge da Fase 1 de 5.\n\n" +
                                "Esta aplicação simula um sistema de gerenciamento de usuários " +
                                "seguindo princípios de Clean Architecture, DDD e boas práticas com Java, Spring Boot e PostgreSQL.")
                        .version("v1")
                        .contact(new Contact()
                                .name("Vitor França")
                                .email("contato@vitorsilvafranca.com")
                                .url("https://vitorsilvafranca.com/"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Documentação Técnica Completa")
                        .url("https://github.com/vitor-silva-franca/tech_challenge_1"))
                .addTagsItem(new Tag().name("Buscar Usuário pelo ID").description("Busca de usuários"))
                .addTagsItem(new Tag().name("Alterar Senha de Usuário").description("Alteração de senha de usuários"))
                .addTagsItem(new Tag().name("Atualizar Usuário").description("Atualização de usuários"))
                .addTagsItem(new Tag().name("Criar Usuário").description("Criação de usuários"))
                .addTagsItem(new Tag().name("Deletar Usuário pelo ID").description("Remoção de usuários"))
                .addTagsItem(new Tag().name("Login Usuário").description("Acesso de usuários"))
                .path("/api/buscarUsuario/{id}", new PathItem().get(new Operation()
                        .summary("Busca um usuário pelo ID")
                        .addTagsItem("Buscar Usuário pelo ID")
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Usuário encontrado")
                                        .content(new Content().addMediaType("application/json", new MediaType()
                                                .schema(new Schema().$ref("#/components/schemas/UsuarioResponse")))))
                                .addApiResponse("404", new ApiResponse().description("Usuário não encontrado"))
                                .addApiResponse("500", new ApiResponse().description("Erro interno do servidor")))))
                .path("/api/alterarSenhaUsuario", new PathItem().put(new Operation()
                        .summary("Altera a senha de um usuário")
                        .addTagsItem("Alterar Senha de Usuário")
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Senha do usuário atualizada com sucesso")
                                        .content(new Content().addMediaType("application/json", new MediaType()
                                                .schema(new Schema().$ref("#/components/schemas/UsuarioResponse")))))
                                .addApiResponse("400", new ApiResponse().description("Requisição inválida"))
                                .addApiResponse("404", new ApiResponse().description("Usuário não encontrado"))
                                .addApiResponse("500", new ApiResponse().description("Erro interno do servidor")))))
                .path("/api/atualizarUsuario/{id}", new PathItem().put(new Operation()
                        .summary("Atualiza um usuário pelo ID")
                        .addTagsItem("Atualizar Usuário")
                        .responses(new ApiResponses()
                                .addApiResponse("200", new ApiResponse().description("Usuário atualizado com sucesso")
                                        .content(new Content().addMediaType("application/json", new MediaType()
                                                .schema(new Schema().$ref("#/components/schemas/UsuarioResponse")))))
                                .addApiResponse("400", new ApiResponse().description("Requisição inválida"))
                                .addApiResponse("404", new ApiResponse().description("Usuário não encontrado"))
                                .addApiResponse("500", new ApiResponse().description("Erro interno do servidor")))))
                .path("/api/criarUsuario", new PathItem().post(new Operation()
                        .summary("Cria um novo usuário")
                        .addTagsItem("Criar Usuário")
                        .responses(new ApiResponses()
                                .addApiResponse("201", new ApiResponse().description("Usuário criado com sucesso")
                                        .content(new Content().addMediaType("application/json", new MediaType()
                                                .schema(new Schema().$ref("#/components/schemas/UsuarioResponse")))))
                                .addApiResponse("400", new ApiResponse().description("Requisição inválida"))
                                .addApiResponse("500", new ApiResponse().description("Erro interno do servidor")))))
                .path("/api/deletarUsuario/{id}", new PathItem().delete(new Operation()
                        .summary("Deleta um usuário pelo ID")
                        .addTagsItem("Deletar Usuário pelo ID")
                        .responses(new ApiResponses()
                                .addApiResponse("204", new ApiResponse().description("Usuário deletado com sucesso"))
                                .addApiResponse("404", new ApiResponse().description("Usuário não encontrado"))
                                .addApiResponse("500", new ApiResponse().description("Erro interno do servidor")))))
                .path("/api/loginUsuario", new PathItem().post(new Operation()
                        .summary("Usuário faz acesso ao sistema")
                        .addTagsItem("Login Usuário")
                        .responses(new ApiResponses()
                                .addApiResponse("201", new ApiResponse().description("Usuário logado com sucesso")
                                        .content(new Content().addMediaType("application/json", new MediaType()
                                                .schema(new Schema().$ref("#/components/schemas/UsuarioResponse")))))
                                .addApiResponse("400", new ApiResponse().description("Requisição inválida"))
                                .addApiResponse("500", new ApiResponse().description("Erro interno do servidor")))));
    }
}
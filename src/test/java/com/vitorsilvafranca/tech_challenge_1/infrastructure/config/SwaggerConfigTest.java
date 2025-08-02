package com.vitorsilvafranca.tech_challenge_1.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwaggerConfigTest {

    private SwaggerConfig swaggerConfig;

    @BeforeEach
    void setUp() {
        swaggerConfig = new SwaggerConfig();
    }

    @Test
    void deveConfigurarOpenAPIComSucesso() {
        OpenAPI openAPI = swaggerConfig.springOpenAPI();

        assertNotNull(openAPI);
        assertNotNull(openAPI.getInfo());
        assertEquals("FIAP - Tech Challenge - Fase 2 de Arquitetura e Desenvolvimento Java", 
                openAPI.getInfo().getTitle());
        assertEquals("v1", openAPI.getInfo().getVersion());
    }

    @Test
    void deveConfigurarInformacoesDeContato() {
        OpenAPI openAPI = swaggerConfig.springOpenAPI();

        assertNotNull(openAPI.getInfo().getContact());
        assertEquals("Vitor França", openAPI.getInfo().getContact().getName());
        assertEquals("contato@vitorsilvafranca.com", openAPI.getInfo().getContact().getEmail());
        assertEquals("https://vitorsilvafranca.com/", openAPI.getInfo().getContact().getUrl());
    }

    @Test
    void deveConfigurarLicenca() {
        OpenAPI openAPI = swaggerConfig.springOpenAPI();

        assertNotNull(openAPI.getInfo().getLicense());
        assertEquals("Apache 2.0", openAPI.getInfo().getLicense().getName());
        assertEquals("https://www.apache.org/licenses/LICENSE-2.0.html", 
                openAPI.getInfo().getLicense().getUrl());
    }

    @Test
    void deveConfigurarDocumentacaoExterna() {
        OpenAPI openAPI = swaggerConfig.springOpenAPI();

        assertNotNull(openAPI.getExternalDocs());
        assertEquals("Documentação Técnica Completa", openAPI.getExternalDocs().getDescription());
        assertEquals("https://github.com/vitor-silva-franca/tech_challenge_1", 
                openAPI.getExternalDocs().getUrl());
    }

    @Test
    void deveConfigurarTags() {
        OpenAPI openAPI = swaggerConfig.springOpenAPI();

        assertNotNull(openAPI.getTags());
        assertTrue(openAPI.getTags().size() > 0);
        assertTrue(openAPI.getTags().stream()
                .anyMatch(tag -> "Criar Usuário".equals(tag.getName())));
        assertTrue(openAPI.getTags().stream()
                .anyMatch(tag -> "Criar Restaurante".equals(tag.getName())));
        assertTrue(openAPI.getTags().stream()
                .anyMatch(tag -> "Criar Item".equals(tag.getName())));
    }

    @Test
    void deveConfigurarPaths() {
        OpenAPI openAPI = swaggerConfig.springOpenAPI();

        assertNotNull(openAPI.getPaths());
        assertTrue(openAPI.getPaths().containsKey("/api/criarUsuario"));
        assertTrue(openAPI.getPaths().containsKey("/api/buscarUsuario/{id}"));
        assertTrue(openAPI.getPaths().containsKey("/api/criarRestaurante"));
        assertTrue(openAPI.getPaths().containsKey("/api/criarItem"));
    }
}
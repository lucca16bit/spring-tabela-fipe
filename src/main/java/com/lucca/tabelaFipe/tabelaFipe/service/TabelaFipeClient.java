package com.lucca.tabelaFipe.tabelaFipe.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class TabelaFipeClient {
    private static final String apiKey;
    private static final String baseUrl = "https://parallelum.com.br/fipe/api/v1";

    static {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("src/resources/config.properties")) {
            if (input == null) {
                throw new IOException("Configuração de arquivo não encontrada!");
            }
            properties.load(input);
            apiKey = properties.getProperty("FIPE_API_KEY");
        } catch (IOException e) {
            throw new RuntimeException("Falha ao carregar a chave da API.", e);
        }
    }

    public String obterDados(String endereco) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String json = response.body();
        return json;
    }
}

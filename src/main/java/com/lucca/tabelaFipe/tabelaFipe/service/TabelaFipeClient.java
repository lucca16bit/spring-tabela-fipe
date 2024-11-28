package com.lucca.tabelaFipe.tabelaFipe.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TabelaFipeClient {
    private static final String URL_BASE = "https://parallelum.com.br/fipe/api/v1";

    public String obterDados(String endereco) {
        String endpoint;

        switch (endereco.toLowerCase()) {
            case "carro":
                endpoint = URL_BASE + "/carros/marcas";
                break;
            case "moto":
                endpoint = URL_BASE + "/motos/marcas";
                break;
            case "caminhao":
                endpoint = URL_BASE + "/caminhoes/marcas";
                break;
            default:
                throw new IllegalArgumentException("Entrada inválida, tente novamente!");
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
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

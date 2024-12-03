package com.lucca.tabelaFipe.main;

import com.lucca.tabelaFipe.service.ConverteDados;
import com.lucca.tabelaFipe.model.Dados;
import com.lucca.tabelaFipe.model.Modelos;
import com.lucca.tabelaFipe.service.TabelaFipeClient;

import java.util.Comparator;
import java.util.Scanner;

public class Main {
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private final Scanner sc = new Scanner(System.in);
    TabelaFipeClient client = new TabelaFipeClient();
    ConverteDados converteDados = new ConverteDados();

    public void exibeMenu() {
        var menu = """
                ============ OPÇÕES ============
                1. Carro
                2. Moto
                3. Caminhão
                
                Digite a opção desejada:
                """;
        System.out.println(menu);
        var opcao = sc.nextInt();
        String endereco = switch (opcao) {
            case 1 -> URL_BASE + "carros/marcas";
            case 2 -> URL_BASE + "motos/marcas";
            case 3 -> URL_BASE + "caminhoes/marcas";
            default -> throw new IllegalArgumentException("Entrada inválida, tente novamente!");
        };

        var json = client.obterDados(endereco);
        System.out.println(json);

        var marcas = converteDados.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta: ");
        int codigoMarca = sc.nextInt();

        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = client.obterDados(endereco);
        var modeloLista = converteDados.obterDados(json, Modelos.class);

        System.out.println("\nModelos dessa marca: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);
    }
}

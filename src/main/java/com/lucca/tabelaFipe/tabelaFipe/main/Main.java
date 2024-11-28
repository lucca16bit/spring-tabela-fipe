package com.lucca.tabelaFipe.tabelaFipe.main;

import com.lucca.tabelaFipe.tabelaFipe.service.TabelaFipeClient;

import java.util.Scanner;

public class Main {
    private Scanner sc = new Scanner(System.in);

    public void exibeMenu() {
        TabelaFipeClient client = new TabelaFipeClient();

        var menu = """
                ============ OPÇÕES ============
                1. Carro
                2. Moto
                3. Caminhão
                
                Digite a opção desejada:
                """;

        System.out.println(menu);

        var opcao = sc.nextLine();
        String endereco;

        switch (opcao) {
            case "1":
                endereco = "carro";
                break;
            case "2":
                endereco = "moto";
                break;
            case "3":
                endereco = "caminhao";
                break;
            default:
                System.out.println("Opção inválida, tente novamente!");
                return;
        }

        var json = client.obterDados(endereco);
        System.out.println(json);
    }
}

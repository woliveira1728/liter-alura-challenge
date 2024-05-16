package com.woliveira1728.principal;

import com.woliveira1728.model.DadosBook;
import com.woliveira1728.service.ConsumoApi;
import com.woliveira1728.service.ConverteDados;

import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books";


    public void exibeMenu() {
        var opcao = -1;
        while(opcao != 0) {
            var menu = """
                    Escolha o número de sua opção:
                    1 - buscar livro pelo título
                    2 - listar livros registrados
                    3 - listar autores registrados
                    4 - listar autores vivos em um determinado  ano
                    5 - listar livro em um determinado idioma
                                    
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    findBookByTitle();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void findBookByTitle() {

        System.out.println("Informe o título do livro");
        var bookTitle = leitura.nextLine();

        var json = consumo.obterDados(ENDERECO + "?search=" + bookTitle.replace(" ", "%20"));

        DadosBook dadosBook = conversor.obterDados(json, DadosBook.class);
        System.out.println(dadosBook);

    }
}
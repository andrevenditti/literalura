package br.com.alura.literalura.principal;

import br.com.alura.literalura.repository.LivroRepository;

import java.util.Scanner;

public class Principal {
    private Scanner input = new Scanner(System.in);
    private LivroRepository repository;

    public Principal(LivroRepository repository) {
        this.repository = repository;
    }

    public void exibeMenu() {
        var option = -1;
        while (option != 0){
            var menu = """
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em determinado idioma
                    0 - Sair
                    """;
            System.out.println(menu);
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    buscarLivro();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarLivro() {
        System.out.println("Buscando livro");
    }
}

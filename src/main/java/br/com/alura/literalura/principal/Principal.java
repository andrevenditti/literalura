package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.DadosApiResult;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.Scanner;

public class Principal {
    private Scanner input = new Scanner(System.in);

    ConsumoApi consumoApi = new ConsumoApi();

    ConverteDados converteDados = new ConverteDados();

    private final String ENDERECO = "https://gutendex.com/books/?search=";

    private LivroRepository repository;

    private AutorRepository autorRepository;

    public Principal(LivroRepository repository, AutorRepository autorRepository) {
        this.repository = repository;
        this.autorRepository = autorRepository;
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
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresPorIdade();
                    break;
                case 5:
                    listarLivrosPorIdioma();
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
        System.out.println("Digite o livro que deseja buscar:");
        var nomeLivro = input.nextLine();
        var json = consumoApi.obterDados(ENDERECO + nomeLivro.replace(" ", "+"));
        DadosApiResult dadosApiResult = converteDados.obterDados(json, DadosApiResult.class);
        Autor autor = new Autor(dadosApiResult.resultado().get(0).autor().get(0));
        Autor autorSalvo = autorRepository.save(autor);
        Livro livro = new Livro(dadosApiResult, autorSalvo);
        repository.save(livro);
    }

    private void listarLivrosRegistrados() {
        System.out.println("***Livros Registrado***");
    }

    private void listarAutoresRegistrados() {
        System.out.println("***Autores***");
    }

    private void listarAutoresPorIdade() {
        System.out.println("***Autores vivos no ano***");
    }

    private void listarLivrosPorIdioma() {
        System.out.println("***Livros por idioma escolhido***");
    }

}

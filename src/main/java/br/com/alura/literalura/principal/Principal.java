package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.DadosApiResult;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.*;

public class Principal {
    private Scanner input = new Scanner(System.in);
    ConsumoApi consumoApi = new ConsumoApi();
    ConverteDados converteDados = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private LivroRepository repository;
    private AutorRepository autorRepository;

    private List<Livro> livros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();

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
                    
                    Digite uma opção: 
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

        if(dadosApiResult.resultado().isEmpty()) {
            System.out.println("Livro não encontrado");
            buscarLivro();
        } else {
            System.out.println("Encontrado");
        }

        Autor autor = new Autor(dadosApiResult.resultado().get(0).autor().get(0));
        Autor autorSalvo = autorRepository.save(autor);
        Livro livro = new Livro(dadosApiResult, autorSalvo);
        repository.save(livro);
    }

    private void listarLivrosRegistrados() {
        System.out.println("***Livros registrados***");
        livros = repository.findAll();
        livros.stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        System.out.println("***Autores***");
        autores = autorRepository.findAll();
        autores.stream()
                .sorted(Comparator.comparing(Autor::getNome))
                .forEach(System.out::println);
    }

    private void listarAutoresPorIdade() {
        System.out.println("Digite o ano que do autor que deseja procurar:");
        var anoProcura = input.nextInt();
        System.out.println("***Autores vivos no ano***");
        List<Autor> autores = autorRepository.encontrarAutorPeloAno(anoProcura);
        if(autores.isEmpty()) {
            System.out.println("Nao foi encontrado");
        } else {
            autores.stream().forEach(System.out::println);
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("***Livros por idioma escolhido***");
        System.out.println("Digite o idioma que gostaria de procurar:");
        System.out.println("es - espanhol\npt - português\nfr - francês\nen - inglês\n");
        var idiomaEscolhido = input.nextLine();
        Optional<Livro> livros = repository.findByIdioma(idiomaEscolhido);
        if(livros.isPresent()) {
            livros.stream().forEach(System.out::println);
        } else {
            System.out.println("Não existem livros com esse idioma no sistema");
        }
    }

}

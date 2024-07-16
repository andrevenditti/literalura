package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalescimento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros;

    public Autor(){}

    public Autor(DadosAutor autor) {
        this.nome = autor.nome();
        this.anoNascimento = autor.anoNascimento();
        this.anoFalescimento = autor.anosFalescimento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalescimento() {
        return anoFalescimento;
    }

    public void setAnoFalescimento(Integer anoFalescimento) {
        this.anoFalescimento = anoFalescimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros() {
        this.livros = livros;
    }

    @Override
    public String toString() {
        return "Nome do autor:'" + nome + '\n' +
                "Ano de Nascimento:" + anoNascimento +'\n' +
                "Ano de Falescimento:" + anoFalescimento +'\n' +
                "Livros:" + livros.stream()
                .map(l -> l.getTitulo()).collect(Collectors.toList()) + '\n';
    }
}

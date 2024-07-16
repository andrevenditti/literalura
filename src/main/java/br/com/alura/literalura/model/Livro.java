package br.com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private String numeroDownload;
    @ManyToOne
    private Autor autor;

    public Livro() {}

    public Livro(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getNumeroDownload() {
        return numeroDownload;
    }

    public void setNumeroDownload(String numeroDowload) {
        this.numeroDownload = numeroDowload;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Titlo do livro: '" + titulo + '\'' +
                "Autor='" + autor + '\'' +
                "Numero de Downloads='" + numeroDownload + '\'';
    }
}

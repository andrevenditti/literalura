package br.com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private Integer numeroDownload;
    @ManyToOne
    private Autor autor;

    public Livro() {}

    public Livro(DadosApiResult dadosApiResult, Autor autor) {
        this.titulo = dadosApiResult.resultado().get(0).titulo();
        this.idioma = dadosApiResult.resultado().get(0).idioma().get(0);
        this.numeroDownload = dadosApiResult.resultado().get(0).numeroDownloads();
        this.autor = autor;
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

    public Integer getNumeroDownload() {
        return numeroDownload;
    }

    public void setNumeroDownload(String numeroDowload) {
        this.numeroDownload = numeroDownload;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Titlo do livro:" + titulo + '\n' +
                "Autor:" + autor.getNome() + '\n' +
                "Idioma: " + idioma + '\n' +
                "Numero de Downloads:" + numeroDownload + '\n';
    }
}

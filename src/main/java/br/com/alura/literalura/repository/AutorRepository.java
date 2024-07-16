package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM autores a where :anoProcura between a.ano_nascimento and a.ano_falescimento;")
    List<Autor> encontrarAutorPeloAno(Integer anoProcura);
}

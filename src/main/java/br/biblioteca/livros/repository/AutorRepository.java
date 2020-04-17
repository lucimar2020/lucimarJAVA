package br.biblioteca.livros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.biblioteca.livros.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

	@Query(value = " from #{#entityName} a order by a.nome ")
	List<Autor> listaAutor();

	
}

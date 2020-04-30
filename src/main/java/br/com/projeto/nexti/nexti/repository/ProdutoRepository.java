package br.com.projeto.nexti.nexti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.nexti.nexti.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	Produto findById(long id);

}

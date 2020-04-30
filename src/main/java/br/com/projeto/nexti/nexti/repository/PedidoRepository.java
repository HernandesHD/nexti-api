package br.com.projeto.nexti.nexti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.nexti.nexti.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	Pedido findById(long id);
	
}

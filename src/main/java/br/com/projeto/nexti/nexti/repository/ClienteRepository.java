package br.com.projeto.nexti.nexti.repository;

import br.com.projeto.nexti.nexti.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	Cliente findById(long id);
	
	Boolean existsByCpf(String cpf);
	
}

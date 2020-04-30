package br.com.projeto.nexti.nexti.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.nexti.exception.BadRequestException;
import br.com.projeto.nexti.nexti.model.Cliente;
import br.com.projeto.nexti.nexti.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/buscar/{id}")
	public Cliente buscaCliente(@PathVariable("id") long id) {
		Cliente cliente = clienteRepository.findById(id);
		return cliente;
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/cadastrar")
	@Transactional
	public Cliente cadastrarCliente(@RequestBody Cliente cadastrarCliente) {
		if(clienteRepository.existsByCpf(cadastrarCliente.getCpf())) {
			throw new BadRequestException("CPF já esta cadastrado!");
		}
		
		Cliente novoCliente = new Cliente();
		
		novoCliente.setNome(cadastrarCliente.getNome());
		novoCliente.setCpf(cadastrarCliente.getCpf());
		novoCliente.setDataDeNascimento(cadastrarCliente.getDataDeNascimento());
		
		System.out.println(novoCliente.getNome());
		System.out.println(novoCliente.getCpf());
		System.out.println(novoCliente.getDataDeNascimento());

		return clienteRepository.save(novoCliente);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/listar")
	public List<Cliente> listaClientes() {
		List<Cliente> clientes = clienteRepository.findAll();
		return clientes;
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/editar/{id}")
	@Transactional
	public void editaCliente(@PathVariable("id") long id, @RequestBody Cliente cliente) {
		Cliente clienteEditado = clienteRepository.findById(id);
		
		clienteEditado.setNome(cliente.getNome());
		clienteEditado.setCpf(cliente.getCpf());
		clienteEditado.setDataDeNascimento(cliente.getDataDeNascimento());
		
		clienteRepository.saveAndFlush(clienteEditado);
		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<?> deletaCliente(@PathVariable("id") Long id) {
		clienteRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}

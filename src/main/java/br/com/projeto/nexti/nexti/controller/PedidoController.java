package br.com.projeto.nexti.nexti.controller;

import java.util.ArrayList;
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

import br.com.projeto.nexti.nexti.model.Cliente;
import br.com.projeto.nexti.nexti.model.Pedido;
import br.com.projeto.nexti.nexti.model.Produto;
import br.com.projeto.nexti.nexti.repository.ClienteRepository;
import br.com.projeto.nexti.nexti.repository.PedidoRepository;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/buscar/{id}")
	public Pedido buscaCliente(@PathVariable("id") long id) {
		Pedido pedido = pedidoRepository.findById(id);
		return pedido;
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/cadastrar")
	@Transactional
	public Pedido cadastrarPedido(@RequestBody Pedido pedido) {		
		Pedido novoPedido = new Pedido();
		Cliente novoCliente = new Cliente();
		List<Produto> novoProduto = new ArrayList<Produto>();
		
		novoPedido.setDataDaCompra(pedido.getDataDaCompra());

		novoCliente.setCpf(pedido.getCliente().getCpf());
		novoCliente.setNome(pedido.getCliente().getNome());
		novoCliente.setDataDeNascimento(pedido.getCliente().getDataDeNascimento());
		
		novoPedido.setCliente(novoCliente);
		novoPedido.setProduto(novoProduto);
		novoPedido.setTotalDaCompra(pedido.getTotalDaCompra());
		
		System.out.println(novoPedido.getDataDaCompra());
		System.out.println(novoPedido.getCliente());
		System.out.println(novoPedido.getProduto());
		System.out.println(novoPedido.getTotalDaCompra());

		return pedidoRepository.save(novoPedido);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/listar")
	public List<Pedido> listaPedidos() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		return pedidos;
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/editar/{id}")
	@Transactional
	public void editarPedido(@PathVariable("id") long id, @RequestBody Pedido pedido) {
		Pedido novoPedido = new Pedido();
		Cliente novoCliente = new Cliente();
		List<Produto> novoProduto = new ArrayList<Produto>();
		
		novoPedido.setDataDaCompra(pedido.getDataDaCompra());

		novoCliente.setCpf(pedido.getCliente().getCpf());
		novoCliente.setNome(pedido.getCliente().getNome());
		novoCliente.setDataDeNascimento(pedido.getCliente().getDataDeNascimento());
		
		novoPedido.setCliente(novoCliente);
		novoPedido.setProduto(novoProduto);
		novoPedido.setTotalDaCompra(pedido.getTotalDaCompra());
		
		System.out.println(novoPedido.getDataDaCompra());
		System.out.println(novoPedido.getCliente());
		System.out.println(novoPedido.getProduto());
		System.out.println(novoPedido.getTotalDaCompra());
		
		pedidoRepository.saveAndFlush(novoPedido);
		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<?> deletarPedido(@PathVariable("id") Long id) {
		pedidoRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}

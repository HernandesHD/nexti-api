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

import br.com.projeto.nexti.nexti.model.Produto;
import br.com.projeto.nexti.nexti.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/buscar/{id}")
	public Produto buscaProduto(@PathVariable("id") long id) {
		Produto produto = produtoRepository.findById(id);
		return produto;
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/cadastrar")
	@Transactional
	public Produto cadastrarProduto(@RequestBody Produto produto) {		
		Produto novoProduto = new Produto();
		
		novoProduto.setNome(produto.getNome());
		novoProduto.setSku(produto.getSku());
		novoProduto.setDescricao(produto.getDescricao());
		novoProduto.setPreco(produto.getPreco());
		novoProduto.setQuantidade(produto.getQuantidade());
		
		System.out.println(novoProduto.getNome());
		System.out.println(novoProduto.getSku());
		System.out.println(novoProduto.getDescricao());
		System.out.println(novoProduto.getPreco());
		System.out.println(novoProduto.getQuantidade());

		return produtoRepository.save(novoProduto);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/listar")
	public List<Produto> listaProduto() {
		List<Produto> produtos = produtoRepository.findAll();
		return produtos;
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/editar/{id}")
	@Transactional
	public void editarProduto(@PathVariable("id") long id, @RequestBody Produto produto) {
		Produto produtoEditado = produtoRepository.findById(id);
		
		produtoEditado.setNome(produto.getNome());
		produtoEditado.setSku(produto.getSku());
		produtoEditado.setDescricao(produto.getDescricao());
		produtoEditado.setPreco(produto.getPreco());
		produtoEditado.setQuantidade(produto.getQuantidade());
		
		System.out.println(produtoEditado.getNome());
		System.out.println(produtoEditado.getSku());
		System.out.println(produtoEditado.getDescricao());
		System.out.println(produtoEditado.getPreco());
		System.out.println(produtoEditado.getQuantidade());
		
		produtoRepository.saveAndFlush(produtoEditado);
		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<?> deletarProduto(@PathVariable("id") Long id) {
		produtoRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}

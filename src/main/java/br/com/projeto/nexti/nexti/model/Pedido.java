package br.com.projeto.nexti.nexti.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pedido")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pedido implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "pedido_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// UM pedido tem UM cliente - OneToOne
	@OneToOne(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Cliente cliente;
	
	@Column(nullable = false)
	private Double totalDaCompra;
	
	@Column(nullable = false)
	private String dataDaCompra;
	
	// UM pedido tem VÁRIOS produtos - OntoMany
	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Produto> produtos;

	/**
	 * GETTERS AND SETTERS
	 */
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getTotalDaCompra() {
		return totalDaCompra;
	}

	public void setTotalDaCompra(Double totalDaCompra) {
		this.totalDaCompra = totalDaCompra;
	}

	public String getDataDaCompra() {
		return dataDaCompra;
	}

	public void setDataDaCompra(String dataDaCompra) {
		this.dataDaCompra = dataDaCompra;
	}

	public List<Produto> getProduto() {
		return produtos;
	}

	public void setProduto(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Long getId() {
		return id;
	}
	
	
	
	
}

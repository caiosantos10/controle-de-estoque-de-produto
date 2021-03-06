package com.projeto.controleDeEstoque.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String descricao;
	private double preco;
	private Integer unidade;
	private Long dataCadastro;
	private Integer quantidade;
	private boolean ativo;
	
	
	public Produto() {}
	
	public Produto(Integer id, String nome, String descricao, double preco, Integer unidade, 
			Integer quantidade, boolean ativo, Long dataCadastro) {
		//
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.unidade = unidade;
		this.dataCadastro = dataCadastro;
		this.quantidade = quantidade;
		this.ativo = ativo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public Unidade getUnidade() {
		return Unidade.toEnum(unidade);
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade.getCod();
	}
	
	public Long getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Long dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public boolean getAtivo() {
//		if(ativo)
//			return "SIM";
//		return "NAO";
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
}

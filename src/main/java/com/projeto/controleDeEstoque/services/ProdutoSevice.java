package com.projeto.controleDeEstoque.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.controleDeEstoque.domain.Produto;
import com.projeto.controleDeEstoque.repositories.ProdutoRepository;
import com.projeto.controleDeEstoque.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoSevice {
	
	@Autowired
	private ProdutoRepository repo;
	
	public Produto buscar(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
}

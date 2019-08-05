package com.projeto.controleDeEstoque.services;

import java.util.List;
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
	
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Produto findByNome(String nome) {
		Produto obj = repo.findByNome(nome);
			if (obj == null) {
				throw new ObjectNotFoundException("Objeto não encontrado! Nome: " + nome
						+ ", Tipo: " + Produto.class.getName());
			}
			return obj;
	}
	
	@SuppressWarnings("finally")
	public Produto insert(Produto obj) {
		try {
			findByNome(obj.getNome());
		} catch(ObjectNotFoundException e) {
			obj.setId(null);
			return repo.save(obj);
		} finally {
			int aux = obj.getQuantidade();
			obj.setQuantidade(aux++);
			return update(obj);
		}
	}
	
	public Produto update (Produto obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete (Integer id) {
		find(id);
		repo.deleteById(id);
	}

	public List<Produto> findAll() {
		return repo.findAll();
	}
}

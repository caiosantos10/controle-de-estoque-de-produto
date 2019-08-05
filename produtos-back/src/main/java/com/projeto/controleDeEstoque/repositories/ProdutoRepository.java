package com.projeto.controleDeEstoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.controleDeEstoque.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	@Query("SELECT p FROM Produto p WHERE p.nome = ?1")
	Produto findByNome(String nome);
}

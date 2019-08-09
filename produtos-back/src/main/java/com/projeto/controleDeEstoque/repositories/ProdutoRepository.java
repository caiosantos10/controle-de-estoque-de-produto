package com.projeto.controleDeEstoque.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projeto.controleDeEstoque.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	@Query("SELECT p FROM Produto p WHERE p.nome = ?1")
	Produto findByNome(String nome);
	
	@Query("SELECT p FROM Produto p WHERE p.nome LIKE CONCAT('%', :nome, '%')")
	List<Produto> findProdutosWithPartOfNome(@Param("nome") String nome);
}

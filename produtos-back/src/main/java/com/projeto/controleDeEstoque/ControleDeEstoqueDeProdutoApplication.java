package com.projeto.controleDeEstoque;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projeto.controleDeEstoque.domain.Produto;
import com.projeto.controleDeEstoque.repositories.ProdutoRepository;

@SpringBootApplication
public class ControleDeEstoqueDeProdutoApplication implements CommandLineRunner {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ControleDeEstoqueDeProdutoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Produto bolacha = new Produto(null, "Bolacha", "Bolacha 3 de maio", 3.50, 2, 2, false, System.currentTimeMillis());
		Produto leite = new Produto(null, "Leite em po", "leite em po Molico", 6.90, 3, 10, false, System.currentTimeMillis());
		Produto pao = new Produto(null, "Pao", "Pao integral", 3.50, 1, 5, true, System.currentTimeMillis());
		
		produtoRepository.saveAll(Arrays.asList(bolacha, leite, pao)); 
	}
}

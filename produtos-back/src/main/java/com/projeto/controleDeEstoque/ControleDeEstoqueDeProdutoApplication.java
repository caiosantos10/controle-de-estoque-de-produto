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
		Produto bolacha = new Produto(null, "Bolacha", "Bolacha 3 de maio", 3.50, 2);
		Produto leite = new Produto(null, "Leite em po", "leite em po Molico", 6.90, 2);
		Produto pao = new Produto(null, "Pao", "Pao integral", 3.50, 1);
		
		produtoRepository.saveAll(Arrays.asList(bolacha, leite, pao)); 
	}
}
package com.projeto.controleDeEstoque.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.controleDeEstoque.domain.Produto;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Produto> listar(){
		
		Produto prod1 = new Produto(1, "Queijo", "Queijo de coalho sem sal", 2.30, 1);
		Produto prod2 = new Produto(2, "Banana", "Banana nanica", 0.50, 2);
		
		List<Produto> lista = new ArrayList<Produto>();
		lista.add(prod1);
		lista.add(prod2);
		
		return lista;
	}

}

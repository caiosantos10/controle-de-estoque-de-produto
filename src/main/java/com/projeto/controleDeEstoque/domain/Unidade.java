package com.projeto.controleDeEstoque.domain;

public enum Unidade {
	
	KG(1, "quilograma"), UN(2, "unidade"), LT(3, "litro");
	
	private int cod;
	private String descricao;
	
	private Unidade(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Unidade toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (Unidade x : Unidade.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido " + cod);
	}
}

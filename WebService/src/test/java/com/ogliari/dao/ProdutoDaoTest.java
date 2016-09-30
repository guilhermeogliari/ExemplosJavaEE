package com.ogliari.dao;

import org.junit.Test;

import com.ogliari.domain.Produto;

public class ProdutoDaoTest {

	@Test
	public void salvar() {
		Produto produto = new Produto();
		ProdutoDao dao = new ProdutoDao();
		dao.create(produto);
	}
	
}

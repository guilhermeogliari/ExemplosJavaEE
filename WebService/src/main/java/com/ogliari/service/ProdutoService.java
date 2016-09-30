package com.ogliari.service;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

import com.ogliari.dao.ProdutoDao;
import com.ogliari.domain.Produto;

@Path("produto")
@Stateless
public class ProdutoService extends GenericService<Produto, Long>{

	private static ProdutoDao dao = new ProdutoDao();
	
	public ProdutoService() {
		super(dao);
	}
}

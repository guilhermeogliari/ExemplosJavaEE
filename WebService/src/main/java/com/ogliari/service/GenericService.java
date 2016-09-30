package com.ogliari.service;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.ogliari.dao.GenericDao;

public abstract class GenericService<T, I extends Serializable> {

	GenericDao<T, I> dao;

	public GenericService(GenericDao<T, I> dao) {
		this.dao = dao;
	}

	@POST
	@Consumes("application/json")
	public void create(T t) {
		dao.create(t);
	}

	@GET
	@Produces("application/json")
	public List<T> findAll() {
		return dao.read();
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public T findOne(@PathParam("id") I id) {
		return dao.search(id);
	}
	
	@GET
	@Path("count")
	@Produces("text/plain")
	private String countValues() {
		return String.valueOf(dao.count());
	}

	@PUT
	public void update(T t) {
		dao.update(t);
	}

	@DELETE
	@Path("{id}")
	public void remove(@PathParam("id") I id) {
		dao.delete(id);
	}

}

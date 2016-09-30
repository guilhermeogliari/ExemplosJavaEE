package com.ogliari.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@Stateless
public abstract class GenericDao<T, I extends Serializable> {

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("OgliariPersistence");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();
	
	private Class<T> persistClass;

	@SuppressWarnings("unchecked")
	protected GenericDao() {
		this.persistClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public T create(T entity) {
		EntityTransaction entityTransaction = getEntityManager().getTransaction();
		entityTransaction.begin();
		getEntityManager().persist(entity);
		entityTransaction.commit();
		return entity;
	}

	public List<T> read() {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(persistClass);
		query.from(persistClass);
		return getEntityManager().createQuery(query).getResultList();
	}

	public T search(I id) {
		return getEntityManager().find(persistClass, id);
	}

	public int count() {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(persistClass);
		query.from(persistClass);
		return getEntityManager().createQuery(query).getMaxResults(); 
	}
	
	public T update(T entity) {
		EntityTransaction entityTransaction = getEntityManager().getTransaction();
		entityTransaction.begin();
		getEntityManager().merge(entity);
		getEntityManager().flush();
		entityTransaction.commit();
		return entity;
	}

	public void delete(I id) {
		T entity = search(id);
		EntityTransaction entityTransaction = getEntityManager().getTransaction();
		entityTransaction.begin();
		T mergedEntity = getEntityManager().merge(entity);
		getEntityManager().remove(mergedEntity);
		getEntityManager().flush();
		entityTransaction.commit();
	}
	
}

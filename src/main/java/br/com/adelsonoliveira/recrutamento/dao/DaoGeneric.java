package br.com.adelsonoliveira.recrutamento.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.adelsonoliveira.recrutamento.util.JPAUtil;

public class DaoGeneric<E> {

	public void salvar(E entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(entidade);
		entityTransaction.commit();
		entityManager.close();
	}

	public E atualizar(E entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		E retorno = entityManager.merge(entidade);
		entityTransaction.commit();
		entityManager.close();
		return retorno;
	}

	public void remover(E entidade, Long id) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.remove(entityManager.find(entidade.getClass(), id));
		entityTransaction.commit();
		entityManager.close();
	}

	public List<E> consultar(Class<E> entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		List<E> retorno = entityManager.createQuery("from " + entidade.getName()).getResultList();
		entityTransaction.commit();
		entityManager.close();
		return retorno;
	}
	
	public E selecionar(E entidade, Long id) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		E retorno = (E) entityManager.find(entidade.getClass(), id);
		entityTransaction.commit();
		entityManager.close();
		return retorno;
	}

}

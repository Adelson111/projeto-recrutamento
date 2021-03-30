package br.com.adelsonoliveira.recrutamento.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory factory;
	
	public static EntityManager getEntityManager() {
		if(factory == null) {
			factory = Persistence.createEntityManagerFactory("recrutamento");
		}
		return factory.createEntityManager();
	}
	
	public static Object getPrimaryKey(Object entidade) {
		return factory.getPersistenceUnitUtil().getIdentifier(entidade);
	}
	
}

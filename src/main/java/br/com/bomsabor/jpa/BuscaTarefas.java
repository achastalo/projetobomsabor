package br.com.bomsabor.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.bomsabor.modelo.Tarefa;

public class BuscaTarefas {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tarefa");
		EntityManager manager = factory.createEntityManager();
		
		//cuidado,	use	o	import	javax.persistence.Query
		Query query = manager
				.createQuery("select t from	tarefa as t " + "where t.finalizado	= :paramFinalizado");
		query.setParameter("paramFinalizado", true);
		
		List<Tarefa> lista = query.getResultList();
		for (Tarefa t : lista) {
			System.out.println(t.getDescricao());
		}
		manager.close();
	}
}

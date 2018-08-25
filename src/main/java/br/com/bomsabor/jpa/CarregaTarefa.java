package br.com.bomsabor.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.bomsabor.modelo.Tarefa;

public class CarregaTarefa {
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tarefa");
		EntityManager manager = factory.createEntityManager();
		
		Tarefa encontrada = manager.find(Tarefa.class, 3L);
		System.out.println(encontrada.getDescricao());
		manager.close();
	}

}

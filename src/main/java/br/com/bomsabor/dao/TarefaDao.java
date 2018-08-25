package br.com.bomsabor.dao;

import java.util.List;

import br.com.bomsabor.modelo.Tarefa;

public interface TarefaDao {

	Tarefa buscaPorId(Long id);

	List<Tarefa> lista();

	void adiciona(Tarefa t);

	void altera(Tarefa t);

	void remove(Tarefa t);

	void finaliza(Tarefa t);

}

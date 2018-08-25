package br.com.bomsabor.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.bomsabor.dao.TarefaDao;
import br.com.bomsabor.modelo.Tarefa;

/**
 * O padrão de projetos Dependency Injection (DI) (Injeção de dependências). A
 * ideia é que a classe não mais resolva as suas dependências por conta própria
 * mas apenas declara que depende de alguma outra classe. E de alguma outra
 * forma que não sabemos ainda, alguém resolverá essa dependência para nós.
 * Alguém pega o controle dos objetos que liga (ou amarra) as dependências. Não
 * estamos mais no controle, há algum container que gerencia as dependências e
 * amarra tudo. 
 * Esse container já existia e já usamos ele sem saber. Repare que
 * com @Controller já definimos que a nossa classe faz parte do Spring MVC, mas
 * a anotação vai além disso. Também definimos que queremos que o Spring
 * controle o objeto. Spring é no fundo um container que dá new para nós e
 * também sabe resolver e ligar as dependências.
 */

/**
 * @Transactional significa que todos os métodos da classe serão executados
 *                dentro de uma transação e gerenciados pelo Spring.
 */
@Transactional
@Controller
public class TarefasController {

	/**
	 * Há um problema ainda, usando o gerenciamento de transação pelo Spring exige a
	 * presença do construtor padrão sem parâmetros. Vamos trocar aqui também o
	 * ponto de injeção do construtor para o atributo
	 */
	@Autowired
	TarefaDao dao; // usa apenas a interface!


	/***
	 * É preciso carregar o JSP no navegador, mas o acesso direto ao pasta WEB-INF é
	 * proibido pelo servlet-container e consequentemente não é possível acessar o
	 * formulário. Para resolver isso vamos criar uma nova ação (um novo método)
	 * dentro da classe TarefasController que tem a finalidade de Melhorar a
	 * organização dos arquivos JSPs . chamar o formulário apenas. O método usa
	 * também a anotação @RequestMappping e retorna um String para chamar o
	 * formulário.
	 * 
	 */
	@RequestMapping("novaTarefa")
	public String form() {
		return "tarefa/formulario";
	}

	@RequestMapping("adicionaTarefa")
	public String adiciona(@Valid Tarefa tarefa, BindingResult result) {

		if (result.hasFieldErrors("descricao")) {
			return "tarefa/formulario";
		}

		dao.adiciona(tarefa);
		return "tarefa/adicionada";
	}

	/***
	 * A ideia é que o Spring MVC não só recebe o nome da página JSP (tarefa/lista)
	 * quando chama o método lista , mas também os dados para o JSP.
	 */
	@RequestMapping("listaTarefas")
	public String lista(Model model) {
		model.addAttribute("tarefas", dao.lista());
		return "tarefa/lista";
	}

	@RequestMapping("removeTarefa")
	public String remove(Tarefa tarefa) {
		dao.remove(tarefa);
		return "redirect:listaTarefas";
	}

	@RequestMapping("mostraTarefa")
	public String mostra(Long id, Model model) {
		model.addAttribute("tarefa", dao.buscaPorId(id));
		return "tarefa/mostra";
	}

	@RequestMapping("alteraTarefa")
	public String altera(Tarefa tarefa) {
		dao.altera(tarefa);
		return "redirect:listaTarefas";
	}

	/***
	 * Quando estamos usando um Framework MVC como o Spring, queremos que toda a
	 * parte de manipulação de request e response fique por conta do framework. Da
	 * forma que mostramos antes é possível obter o resultado que esperamos, mas
	 * manipular o status da resposta é uma tarefa que não queremos fazer
	 * diretamente. O Spring nos ajuda nessa tarefa, fazendo com que o retorno do
	 * nosso método seja HTTP 200, exceto em caso de alguma exception. Para isso,
	 * precisamos usar uma annotation chamada @ResponseBody . Como o próprio nome da
	 * annotation fala, tudo que for retornado, será o corpo da nossa resposta. Ou
	 * se nada for retornado, então a resposta será vazia porém o status HTTP será
	 * 200. Nosso código pode ficar mais enxuto e sem a manipulação do response:
	 * 
	 */
	@RequestMapping("finalizaTarefa")
	public String finaliza(Tarefa tarefa, Model model) {
		dao.finaliza(tarefa);

		model.addAttribute("tarefa", dao.buscaPorId(tarefa.getId()));
		return "tarefa/finalizada";
	}

}

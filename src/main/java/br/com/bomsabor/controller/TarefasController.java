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
 * O padr�o de projetos Dependency Injection (DI) (Inje��o de depend�ncias). A
 * ideia � que a classe n�o mais resolva as suas depend�ncias por conta pr�pria
 * mas apenas declara que depende de alguma outra classe. E de alguma outra
 * forma que n�o sabemos ainda, algu�m resolver� essa depend�ncia para n�s.
 * Algu�m pega o controle dos objetos que liga (ou amarra) as depend�ncias. N�o
 * estamos mais no controle, h� algum container que gerencia as depend�ncias e
 * amarra tudo. 
 * Esse container j� existia e j� usamos ele sem saber. Repare que
 * com @Controller j� definimos que a nossa classe faz parte do Spring MVC, mas
 * a anota��o vai al�m disso. Tamb�m definimos que queremos que o Spring
 * controle o objeto. Spring � no fundo um container que d� new para n�s e
 * tamb�m sabe resolver e ligar as depend�ncias.
 */

/**
 * @Transactional significa que todos os m�todos da classe ser�o executados
 *                dentro de uma transa��o e gerenciados pelo Spring.
 */
@Transactional
@Controller
public class TarefasController {

	/**
	 * H� um problema ainda, usando o gerenciamento de transa��o pelo Spring exige a
	 * presen�a do construtor padr�o sem par�metros. Vamos trocar aqui tamb�m o
	 * ponto de inje��o do construtor para o atributo
	 */
	@Autowired
	TarefaDao dao; // usa apenas a interface!


	/***
	 * � preciso carregar o JSP no navegador, mas o acesso direto ao pasta WEB-INF �
	 * proibido pelo servlet-container e consequentemente n�o � poss�vel acessar o
	 * formul�rio. Para resolver isso vamos criar uma nova a��o (um novo m�todo)
	 * dentro da classe TarefasController que tem a finalidade de Melhorar a
	 * organiza��o dos arquivos JSPs . chamar o formul�rio apenas. O m�todo usa
	 * tamb�m a anota��o @RequestMappping e retorna um String para chamar o
	 * formul�rio.
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
	 * A ideia � que o Spring MVC n�o s� recebe o nome da p�gina JSP (tarefa/lista)
	 * quando chama o m�todo lista , mas tamb�m os dados para o JSP.
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
	 * parte de manipula��o de request e response fique por conta do framework. Da
	 * forma que mostramos antes � poss�vel obter o resultado que esperamos, mas
	 * manipular o status da resposta � uma tarefa que n�o queremos fazer
	 * diretamente. O Spring nos ajuda nessa tarefa, fazendo com que o retorno do
	 * nosso m�todo seja HTTP 200, exceto em caso de alguma exception. Para isso,
	 * precisamos usar uma annotation chamada @ResponseBody . Como o pr�prio nome da
	 * annotation fala, tudo que for retornado, ser� o corpo da nossa resposta. Ou
	 * se nada for retornado, ent�o a resposta ser� vazia por�m o status HTTP ser�
	 * 200. Nosso c�digo pode ficar mais enxuto e sem a manipula��o do response:
	 * 
	 */
	@RequestMapping("finalizaTarefa")
	public String finaliza(Tarefa tarefa, Model model) {
		dao.finaliza(tarefa);

		model.addAttribute("tarefa", dao.buscaPorId(tarefa.getId()));
		return "tarefa/finalizada";
	}

}

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery-3.2.1.min.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!--  De alguma forma precisamos mandar a requisição para a ação, mas ainda assim
	 * precisamos manter a página do jeito que ela estava ao clicar no link. Podemos
	 * fazer isso através de uma técnica chamada AJAX, que significa Asynchronous
	 * Javascript and XML. 
	 * 	AJAX nada mais é do que uma técnica que nos permite enviar requisições 
	 * assíncronas, ou seja, manter a página que estava aberta
	 * intacta, e recuperar a resposta dessa requisição para fazermos qualquer
	 * processamento com eles. Essas respostas costumam ser XML, HTML ou um formato
	 * de transferência de dados chamado JSON (Javascript Object Notation). -->
	<script type="text/javascript">
		function finalizaAgora(id) {
			$.post("finalizaTarefa", {'id' : id}, function(resposta) {
			// selecionando o elemento html através	da ID e	alterando o	HTML dele	
			$("#tarefa_"+id).html(resposta);
			});
		}
 	</script>
	<a href="novaTarefa">Criar nova tarefa</a>
	<br />
	<br />
	<table>
		<tr>
			<th>Id</th>
			<th>Descrição</th>
			<th>Finalizado?</th>
			<th>Data de finalização</th>
		</tr>
		<c:forEach items="${tarefas}" var="tarefa">
			<tr id="tarefa_${tarefa.id}">
				<td>${tarefa.id}</td>
				<td>${tarefa.descricao}</td>
				
				<c:if test="${tarefa.finalizado	eq	false}">
					<td>
						<a href="#" onClick="finalizaAgora(${tarefa.id})">
							Finalizar
						</a>
					</td>
				</c:if>
				
				<c:if test="${tarefa.finalizado	eq	true}">
					<td>Finalizado</td>
				</c:if>
				
				<td><fmt:formatDate value="${tarefa.dataFinalizacao.time}"
						pattern="dd/MM/yyyy" /></td>
				<td><a href="removeTarefa?id=${tarefa.id}">Remover</a></td>
				<td><a href="mostraTarefa?id=${tarefa.id}">Alterar</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
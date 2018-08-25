<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@	taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- O nosso form, ao  ser  submetido, chama uma a��o, um m�todo dentro	de	um @Controller que
	responde pela URL adicionaTarefa. Esse m�todo precisa receber os dados da requisi��o e gravar a
	tarefa que o usu�rio informou na tela. -->

	<h3>Adicionar tarefas</h3>
	<form:errors path="tarefa.descricao" />
	<form action="adicionaTarefa" method="post">
		Descri��o: <br />
		<textarea name="descricao" rows="5" cols="100"></textarea>
		<br /> <input type="submit" value="Adicionar">
	</form>
</body>
</html>
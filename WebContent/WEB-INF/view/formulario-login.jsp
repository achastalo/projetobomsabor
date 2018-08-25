<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" href="resources/tarefas.css" rel="stylesheet"	/>
</head>
<body>
	<h2>Página de Login das Tarefas</h2>
	<form action="efetuaLogin" method="post">
		Login: <input type="text" name="login" /> <br /> 
		Senha: <input type="password" name="senha" /> <br /> 
		<input type="submit" value="Entrar nas tarefas" />
	</form>
</body>
</html>
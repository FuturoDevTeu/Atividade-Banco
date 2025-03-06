<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.ContaBancaria" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Banco Brasil</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="styles/style.css">
</head>
<body>
	<form name="frmSaque" action="saque" method="post">
		<div class="box-content" >
			<div class="box-inputs">
				<% String erro = (String) request.getAttribute("erro"); %>
    			<% if(erro != null) { %>
        			<div class="erro"><%= erro %></div>
    			<% } %>
				<input type="text" id="valor" name="valor" placeholder="Insira o valor do saque" >
				<input type="submit" value="Sacar" class="Caixa1" onclick="validarSaque()">;
			</div>
		</div>
	</form>
	<script src="scripts/validadorSaque.js"></script>
</body>
</html>
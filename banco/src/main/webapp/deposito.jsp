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
	<form name="frmDeposito" action="deposito" method="post">
		<div class="box-content" >
			<div class="box-inputs">
				<% String erro = (String) request.getAttribute("erro"); %>
    			<% if(erro != null) { %>
        			<div class="erro"><%= erro %></div>
    			<% } %>
				<input type="text" id="valor" name="valor" placeholder="Insira o valor do valor" >
				<input type="submit" value="Depositar" class="Caixa1" onclick="validarDeposito()">;
			</div>
		</div>
	</form>
	<script src="scripts/validadorDeposito.js"></script>
</body>
</html>
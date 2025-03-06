
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.ContaBancaria" %>
<%
    ContaBancaria conta = (ContaBancaria) session.getAttribute("contaLogada");
    if(conta == null) {
        response.sendRedirect("login.jsp");
    }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Banco Brasil</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="styles/style.css">
</head>
<body>
	<div class="box-content-main">
		<div class="informacao">
			<div class="saldo">
				<h3>R$<%= conta.getSaldo()%></h3>
			</div>
			<div class="titular">
				<h3>Titular: <%=conta.getTitular()%></h3>
			</div>
		</div>
		<div class="box-buttons">
			<a href="requestSaque" class="button1">Saque</a>
			<a href="requestDeposito" class="button2">Deposito</a>
		</div>
		
	
	</div>
	
</body>
</html>
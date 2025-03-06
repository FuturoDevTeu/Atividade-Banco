<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.ContaBancaria" %>
<%@ page import="java.util.ArrayList" %>

<%
	@ SuppressWarnings ("unchecked")
	ArrayList<ContaBancaria> lista = (ArrayList<ContaBancaria>) request.getAttribute("contas");
	Gson json = new Gson();
	String contasJson = json.toJson(lista);
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
	<form name="frmLogin" action="logado" method="post">
		<div class="box-content" >
			<div class="box-inputs">
			<%
				String erro = (String) request.getAttribute("erro"); 
				if(erro != null){%>
					<div class="erro"><%= erro%></div>
				<%}%>
				<input type="text" id="titular" name="titular" placeholder="Titular" >
				<input type="submit" value="Logar" class="Caixa1" onclick="validarLogin()">;
			</div>
		</div>
	</form>
	<script>
		const contas = <%=contasJson%>;
		console.log(contas);
	</script>
	<script src="scripts/validadorLogin.js"></script>
</body>
</html>
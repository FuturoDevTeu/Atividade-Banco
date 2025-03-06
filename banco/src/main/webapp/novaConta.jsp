<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Banco Brasil</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="styles/style.css">
</head>
<body>
	<form name="frmConta" action="insert" method="post">
		<div class="box-content" >
			<div class="box-inputs">
			<% 
				String erro = (String) request.getAttribute("erro");
				if(erro != null) { %>
					<div class="erro"><%= erro%></div>		
				<%}%>
				<input type="text" name="titular" placeholder="Titular" >
				<input type="button" value="Adicionar Conta" class="Caixa1" onclick="validar()">;
			</div>
		</div>
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>
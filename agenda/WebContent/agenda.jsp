<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="br.com.model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
	@ SuppressWarnings("unchecked")
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>agenda de contatos</title>
<link rel="icon" href="imagens/telefone.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>agendas de contatos</h1>
	<a href="novo.html" class="botao1">Novo contato</a>
	<a href="report" class="botao2">Relatório</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>E-mail</th>
				<th>Opções</th>
			</tr>
			<tbody>
				<% for (int i = 0; i < lista.size(); i++) { %>
					<tr>
						<td><%=lista.get(i).getIdcon() %></td>
						<td><%=lista.get(i).getNome() %></td>
						<td><%=lista.get(i).getFone() %></td>
						<td><%=lista.get(i).getEmail() %></td>
						<td><a href="select?idcon=<%=lista.get(i).getIdcon() %>" class="botao1">Editar</a>
							<a href="javascript: confirmar(<%=lista.get(i).getIdcon() %>)" class="botao2">Excluir</a>
						</td>
					</tr>
				<% } %>
			</tbody>
		</thead>
	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>
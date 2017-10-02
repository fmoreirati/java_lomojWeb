<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="msg.jsp"></c:import>

    <form action="sys" method="post" class="container-fluid">
        <!--Envio de logica e action via POST-->
        <input type="hidden" name="logica" value="ClienteLogica"> 
        <input type="hidden" name="action" value="list"> 

        <fieldset class="form-group">
            <label> Digite o nome do cliente </label>
            <input type="text" name="busca" class="form-control">
        </fieldset>

        <p class="form-group">
            <button class="btn btn-primary">Buscar</button>
            <button type="reset" class="btn btn-primary" onclick="window.location.replace('index.jsp')">Cancelar</button>
        </p>
    </form>

    <table class="table">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>E-mail</th>
            <th class="col-md-1 right"><span class="glyphicon glyphicon-pencil"></span></th>
            <th class="col-md-1 right"><span class="glyphicon glyphicon-remove"></span></th>
        </tr>

    <c:forEach var="cliente" items="${clientes}">
        <tr> 
            <td>${cliente.id}</td>
             <td class="col-md-2">
                <c:if test="${not empty cliente.foto}">
                    <img src="img/user/${cliente.foto}" class="img-responsive">
                </c:if>
            </td>
            <td>${cliente.nome}</td>
            <td>${cliente.email}</td>
            <td class="col-md-1 right"><a href="sys?logica=ClienteLogica&action=edit&id=${cliente.id}"><span class="glyphicon glyphicon-pencil"></span></td>
            <td class="col-md-1 rightk"><a href="sys?logica=ClienteLogica&action=remove&id=${cliente.id}" onclick="if(confirm('apagar?'))"><span class="glyphicon glyphicon-remove"></span></td>
        </tr>
    </c:forEach>
</table>

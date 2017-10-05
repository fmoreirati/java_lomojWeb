<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="msg.jsp"></c:import>

    <form action="sys" method="post" class="container-fluid">
        <!--Envio de logica e action via POST-->
        <input type="hidden" name="logica" value="ProdutoLogica"> 
        <input type="hidden" name="action" value="list"> 

        <fieldset class="form-group">
            <label> Digite a descrição do produto </label>
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
            <th>Descricao</th>
            <th>Quant.</th>
            <th>Valor</th>
            <th class="col-md-2">Foto 1</th>
            <th class="col-md-2">Foto 2</th>
            <th class="col-md-2">Foto 3</th>
            <th>Ativo</th>
            <th class="col-md-1 right"><span class="glyphicon glyphicon-pencil"></span></th>
            <th class="col-md-1 right"><span class="glyphicon glyphicon-remove"></span></th>
        </tr>

    <c:forEach var="produto" items="${produtos}">
        <tr> 
            <td>${produto.id}</td>
            <td>${produto.descricao}</td>
            <td>${produto.quant}</td>
            <td>${produto.valor}</td>
            <td class="col-md-2">
                <c:if test="${not empty produto.foto1}">
                    <img src="img/produto/${produto.foto1}" class="img-responsive produtopq">
                </c:if>
            </td>
            <td class="col-md-2">
                <c:if test="${not empty produto.foto2}">
                    <img src="img/produto/${produto.foto2}" class="img-responsive produtopq">
                </c:if>
            </td>
            <td class="col-md-2">
                <c:if test="${not empty produto.foto3}">
                    <img src="img/produto/${produto.foto3}" class="img-responsive produtopq">
                </c:if>
            </td>

            <td>
                <c:if test="${produto.ativo}">Ativo</c:if>
                <c:if test="${not produto.ativo}">Bloquado</c:if>    
            </td>

            <td class="col-md-1 right"><a href="sys?logica=ProdutoLogica&action=edit&id=${produto.id}"><span class="glyphicon glyphicon-pencil"></span></td>
            <td class="col-md-1 rightk"><a href="sys?logica=ProdutoLogica&action=remove&id=${produto.id}" onclick="if (confirm('apagar?'))"><span class="glyphicon glyphicon-remove"></span></td>
        </tr>
    </c:forEach>
</table>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <c:import url="msg.jsp"/>
    <form action="sys" method="POST" class="form" enctype="multipart/form-data">

        <input type="hidden" name="logica" value="ProdutoLogica">

        <c:if test="${empty produto.id}">
            <input type="hidden" name="action" value="cad">
        </c:if>
        <c:if test="${not empty produto.id}">
            <input type="hidden" name="action" value="alt">
            <input type="text" name="id" value="${produto.id}">
        </c:if>

        <div class="container col-md-6">
            <legend>Cadastro de Produto</legend>
            <div class="form-group">
                <label for="descricao">Descrição</label>
                <input type="text" name="descricao" class="form-control" value="${produto.descricao}">
            </div>

            <div class="form-group">
                <label for="quant">Quantidade</label>
                <input type="number" name="quant" min="0" step="1" class="form-control" value="${produto.quant}">
            </div>

            <div class="form-group">
                <label for="valor">Valor</label>
                <input type="number" name="valor" min="0" step="0.01" class="form-control" value="${produto.valor}">
            </div>

            <div class="form-group">
                <label for="foto1">Foto 1</label>
                <input type="file" name="foto1" class="form-control" value="">
            </div>

            <div class="form-group">
                <label for="foto2">Foto 2</label>
                <input type="file" name="foto2" class="form-control" value="">
            </div>

            <div class="form-group">
                <label for="foto3">Foto 3</label>
                <input type="file" name="foto3" class="form-control" value="">
            </div>

            <div class="form-group">
                <button class="btn btn-default"> Salvar </button>
                <button type="reset" class="btn btn-danger" onclick="history.back()"> Cancelar </button>
            </div>
        </div>
    </form>
</div>
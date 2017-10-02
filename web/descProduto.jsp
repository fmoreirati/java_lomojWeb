<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="msg.jsp"></c:import>

    <div class="col-md-6">

        <div class="col-md-8">
        <c:if test="${not empty produto.foto1}">
            <img src="img/produto/${produto.foto1}" class="img-responsive">
        </c:if>
    </div>
    <div class="col-md-4">
        <div class="col-md-12">
            <c:if test="${not empty produto.foto2}">
                <img src="img/produto/${produto.foto2}" class="img-responsive">
            </c:if>
        </div>
        <div class="col-md-12">
            <c:if test="${not empty produto.foto3}">
                <img src="img/produto/${produto.foto3}" class="img-responsive">
            </c:if>
        </div>
    </div>
</div>
<div class="col-md-6">
    <h4>Descrição do Produto</h4>
    <p>${produto.descricao}</p>
    <h4>Valor</h4>
    <p class="redValor">
        <strong>
          R$
            <f:formatNumber minFractionDigits="2" currencySymbol="R$">
                ${produto.valor}
            </f:formatNumber>
        </strong>
    </p>
    <a href="sys?logica=Carrinho&action=add&id=${produto.id}">
        <button class="btn btn-danger"> Comprar </button>
    </a>
</div>


</div>




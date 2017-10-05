<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="dao" class="br.com.lojaWEB.controller.CtrlProduto"/>
<c:import url="msg.jsp"></c:import>
    <h2> Bem vindo! </h2> 

    <div class="row">
    <c:forEach items="${dao.listar('')}" var="p">
        <div class="col-md-3 produto"> 
            <a href="sys?logica=ProdutoLogica&action=desc&id=${p.id}">
                <div class="well">
                    <img src="img/produto/${p.foto1}" title="${p.foto1}" alt="${p.descricao}" class="img-responsive">
                    <p class="descricao">${p.descricao}</p>
                    <p class="valor">
                        R$
                        <f:formatNumber minFractionDigits="2" currencySymbol="R$">
                            ${p.valor}
                        </f:formatNumber>
                </div>
            </a>    
        </div>
    </c:forEach>
</div>

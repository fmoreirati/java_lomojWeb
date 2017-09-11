<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty erros}">
    <div class="alert alert-danger"><strong>Erros:</strong><br>${erros}</div>
</c:if>
<c:if test="${not empty avisos}">
    <div class="alert alert-success"><strong>Avisos:</strong><br>${avisos}</div>
</c:if>
<c:if test="${not empty alertas}">
    <div class="alert alert-warning"><strong>Alertas:</strong><br>${alertas}</div>
</c:if>
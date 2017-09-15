<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <c:import url="msg.jsp"/>
    <h3 class="col-md-12">Identificação</h3>
    <div class="container col-md-6">
        <form action="sys" method="POST" class="form">
            <input type="hidden" name="logica" value="ClienteLogica">
            <input type="hidden" name="action" value="log">
            <legend>Já tenho cadastro</legend>
            <div class="form-group">
                <label for="email">E-mail</label>
                <input type="email" name="email" class="form-control">
            </div>

            <div class="form-group">
                <label for="pws1">Senha</label>
                <input type="password" name="pws1" class="form-control">
                <p class="text-right"><a href="index.jsp?p=esqueci">Esqueci minha senha!</a></p>
            </div>

            <div class="form-group">
                <button class="btn btn-group-justified"> Confirmar </button>
            </div>
        </form>     
    </div>


    <div class="container col-md-6">
        <form action="ServletCliente" method="POST" class="form">
            <legend>Não tenho cadastro</legend>
            <div class="form-group">
                <strong>criar cadastro</strong>
                <ul>
                    <li>receba promoções e ofertas exclusivas</li>
                    <li>agilize suas compras com 1 clique</li>
                    <li>salve seus dados e facilite compras futuras</li>
                </ul>
            </div>
            <div class="form-group">
                <label for="email">E-mail</label>
                <input type="email" name="email" class="form-control"  value="${cliente.email}">
            </div>
            <div class="form-group">
                <button class="btn btn-group-justified"> Criar cadastro </button>
            </div>
    </div>
</form>
<script src="js/ceps.js"></script>
</div>
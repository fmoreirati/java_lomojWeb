<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <form action="ServletCliente" method="POST" class="form">
        <div class="container col-md-6">
            <legend>Cadastro de Cliente</legend>
            <legend>Dados Pessoais</legend>
            <div class="form-group">
                <label for="nome">Nome</label>
                <input type="text" name="nome" class="form-control">
            </div>

            <div class="form-group">
                <label for="email">E-mail</label>
                <input type="email" name="email" class="form-control">
            </div>

            <div class="form-group">
                <label for="cpf">CPF</label>
                <input type="number" name="cpf" min="00000000000" max="99999999999"class="form-control">
            </div>

            <div class="form-group">
                <label for="tel">Tel</label>
                <input type="number" name="tel" class="form-control">
            </div>

            <div class="form-group">
                <label for="pws">Senha</label>
                <input type="password" name="pws" class="form-control">
            </div>

            <div class="form-group">
                <label for="pws2">Confirmação Senha</label>
                <input type="password" name="pws2" class="form-control">
            </div>
        </div>
        <div class="container col-md-6">
            <legend>Dados Endereço</legend>
            <div class="form-group">
                <label for="cep">CEP</label>
                <input type="text" name="cep" class="form-control" id="cep">
            </div>

            <div class="form-group">
                <label for="logradouro">Endereço</label>
                <input type="text" name="logradouro" class="form-control" id="rua">
            </div>

            <div class="form-group">
                <label for="bairro">Bairro</label>
                <input type="text" name="bairro" class="form-control" id="bairro">
            </div>

            <div class="form-group">
                <label for="cidade">Cidade</label>
                <input type="text" name="cidade" class="form-control" id="cidade">
            </div>

            <div class="form-group">
                <label for="uf">UF</label>
                <input type="text" name="uf" class="form-control" id="uf">
            </div>

            <div class="form-group">
                <label for="numero">Numero</label>
                <input type="text" name="numero" class="form-control">
            </div>

            <div class="form-group">
                <label for="complemento">Complemento</label>
                <input type="text" name="complemento" class="form-control">
            </div>

            <div class="form-group">
                <label for="bairro">Bairro</label>
                <input type="text" name="bairro" class="form-control" id="bairro">
            </div>

            <div class="form-group">
                <button class="btn btn-default"> Salvar </button>
                <button type="reset" class="btn btn-danger" onclick="history.back()"> Cancelar </button>
            </div>
        </div>
    </form>
</div>
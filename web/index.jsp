<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>LojaWEB</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/bootstrap-theme.min.css"/>
        <link rel="stylesheet" href="css/estilo.css"/>
    </head>
    <body class="container">
        <nav class="nav navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Menu</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="?">Loja WEB</a>
                </div>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a href="index.jsp">Inicio</a></li>
                        <li><a href="?#produtos">Produtos</a></li>
                        <li><a href="?#lojas">Lojas</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="index.jsp?p=cadCliente">Cadastro</a></li>
                        <li><a href="index.jsp?p=login">Login</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <header class="jumbotron">
            <h2>LojaWEB.</h2><h3> Tudo em um só lugar...</h3>
        </header>
        <section class="container row">
            <c:choose>
                <c:when test="${param.p == 'cadCliente'}">
                    <c:set var="pagina" value="formCliente.jsp"/>
                </c:when>
                <c:when test="${param.p == 'login'}">
                    <c:set var="pagina" value="login.jsp"/>
                </c:when>
                <c:otherwise>
                    <c:set var="pagina" value="inicio.jsp"/>
                </c:otherwise>
            </c:choose>
            <c:import url="${pagina}"/>

        </section>
        <footer class="row text-center">
            <h5> Feito por Fabiano Moreira</h5>
        </footer>
        <!-- Scripts-->
        <script src="js/jquery-3.2.1.slim.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </body>
</html>

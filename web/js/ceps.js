//JavaScript
function limpa_formulario_cep() {
    //Limpa valores do formulário de cep.
    document.getElementById('rua').value = ("");
    document.getElementById('bairro').value = ("");
    document.getElementById('cidade').value = ("");
    document.getElementById('uf').value = ("");
    //document.getElementById('ibge').value = ("");
}

function meu_callback(conteudo) {
    if (!("erro" in conteudo)) {
        //Atualiza os campos com os valores.
        document.getElementById('rua').value = (conteudo.logradouro);
        document.getElementById('bairro').value = (conteudo.bairro);
        document.getElementById('cidade').value = (conteudo.localidade);
        document.getElementById('uf').value = (conteudo.uf);
        //document.getElementById('ibge').value = (conteudo.ibge);
    } //end if.
    else {
        //CEP não Encontrado.
        limpa_formulario_cep();
        alert("CEP não encontrado.");
    }
}

function pesquisacep(valor) {

    //Nova variável "cep" somente com dígitos.
    var cep = valor.replace(/\D/g, '');

    //Verifica se campo cep possui valor informado.
    if (cep != "") {

        //Expressão regular para validar o CEP.
        var validacep = /^[0-9]{8}$/;

        //Valida o formato do CEP.
        if (validacep.test(cep)) {

            //Preenche os campos com "..." enquanto consulta webservice.
            document.getElementById('rua').value = "...";
            document.getElementById('bairro').value = "...";
            document.getElementById('cidade').value = "...";
            document.getElementById('uf').value = "...";
            //document.getElementById('ibge').value = "...";

            //Cria um elemento javascript.
            var script = document.createElement('script');

            //Sincroniza com o callback.
            script.src = '//viacep.com.br/ws/' + cep + '/json/?callback=meu_callback';

            //Insere script no documento e carrega o conteúdo.
            document.body.appendChild(script);
            document.getElementById('cep').style.border = "none";

        } //end if.
        else {
            //cep é inválido.
            limpa_formulario_cep();
            document.getElementById('cep').style.border = "solid 2px #f00";
            //alert("Formato de CEP inválido.");
        }
    } //end if.
    else {
        //cep sem valor, limpa formulário.
        limpa_formulario_cep();
        document.getElementById('cep').style.border = "none";
    }
}
;

/*
 //JQuery  <script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
 $().ready(function () {
 
 function limpa_formulario_cep() {
 // Limpa valores do formulario de cep.
 $("#rua").val("");
 $("#bairro").val("");
 $("#cidade").val("");
 $("#uf").val("");
 $("#ibge").val("");
 }
 
 //Quando o campo cep perde o foco.
 $("#cep").blur(function () {
 
 //Nova variável "cep" somente com dígitos.
 var cep = $(this).val().replace(/\D/g, '');
 
 //Verifica se campo cep possui valor informado.
 if (cep != "") {
 
 //Expressão regular para validar o CEP.
 var validacep = /^[0-9]{8}$/;
 
 //Valida o formato do CEP.
 if (validacep.test(cep)) {
 
 //Preenche os campos com "..." enquanto consulta webservice.
 $("#rua").val("...");
 $("#bairro").val("...");
 $("#cidade").val("...");
 $("#uf").val("...");
 $("#ibge").val("...");
 
 //Consulta o webservice viacep.com.br/
 $.getJSON("//viacep.com.br/ws/" + cep + "/json/?callback=?", function (dados) {
 
 if (!("erro" in dados)) {
 //Atualiza os campos com os valores da consulta.
 $("#rua").val(dados.logradouro);
 $("#bairro").val(dados.bairro);
 $("#cidade").val(dados.localidade);
 $("#uf").val(dados.uf);
 $("#ibge").val(dados.ibge);
 } //end if.
 else {
 //CEP pesquisado não foi encontrado.
 limpa_formulario_cep();
 alert("CEP não encontrado.");
 }
 });
 } //end if.
 else {
 //cep é inválido.
 limpa_formulario_cep();
 alert("Formato de CEP inválido.");
 }
 } //end if.
 else {
 //cep sem valor, limpa formulário.
 limpa_formulario_cep();
 }
 });
 });
 */
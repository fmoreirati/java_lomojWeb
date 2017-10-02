
$(function () {
    
    //FUNÇÃO PARA ATIVAR O SCROOL SUAVE COM A class="scroll" ------------------------
    $(".scroll").click(function (event) {
        event.preventDefault();
        $('html, body').animate({scrollTop: $(this.hash).offset().top - 50}, 1000);
    });
    
    
    //Modal com fotos no valor ------------------------
    $('.foto').click(function () {
        var foto = $('.foto'); //Seleciona todos os elemento com a class .foto
        var index = foto.index(this); //Busca o index do elemento selecionado
        var nomeFoto = foto[index].value; //Pega o nome da foto do elemento na posição do index
        $('#myModal').show(function () {
            $('.conteudoModal').html("<img src='" + nomeFoto + "' class='col-xs-12'>");
        });
    });
    
    
    //DatePicket - JQueryUI
    $("#date").datepicker({maxDate: "+0M +0D"});
    $("#date").datepicker("option", "dateFormat", "dd/mm/yy");
    $("#date").datepicker();


    // Carrega a imagem selecionada no elemento <img>
    $("input[type=file]").on("load change", function () {
        var files = !!this.files ? this.files : [];
        if (!files.length || !window.FileReader)
            return;
        if (/^image/.test(files[0].type)) {
            var reader = new FileReader();
            reader.readAsDataURL(files[0]);
            reader.onload = function () {
                $("#foto").attr('src', this.result);
            };
        }
    });

    //Campos de Formulario
    $(".validar").submit(function () {
        if ($(this).val() === "")
        {
            $(this).css({"border-color": "#F00", "padding": "4px"});
            $(this).placeholder = "Obrigatório";
        }
    });

});

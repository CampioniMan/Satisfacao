Date.prototype.yyyymmdd = function() {
  var mm = this.getMonth() + 1; // getMonth() is zero-based
  var dd = this.getDate();

  return [this.getFullYear(),
          (mm>9 ? '' : '0') + mm,
          (dd>9 ? '' : '0') + dd
         ].join('-');
};

if (typeof jQuery == 'undefined')
	console.log("jQuery não definido");

const localhost = "api/";

function atualizarData(){
	$("#dataRecla").attr('value', dataAtual());
}

function dataAtual()
{
    var hoje = new Date();
    return hoje.yyyymmdd();
}

function alterarRadio(id)
{
	if ($("#"+id).attr('checked'))
		$("#"+id).attr('checked', false);
	else
		$("#"+id).attr('checked', true);
}

/**
    Removendo o título na caixa de diálogo
*/
window.alert = function(string)
{
    var iframe = document.createElement("IFRAME");
    iframe.setAttribute("src", 'data:text/plain,');
    document.documentElement.appendChild(iframe);
    window.frames[0].window.alert(string);
    iframe.parentNode.removeChild(iframe);
}


/* Métodos com ajax */

function acessarTudo(tabela)
{
	$.ajax(localhost+tabela, {
		type:"GET",
		success: function(informacao, informacao2, informacao3){
			return informacao;
		},
		erro: function(informacao, informacao2, informacao3){
			return informacao;
		}
	});
}

function estaVazio(input)
{
	return $("#"+input).val().length == 0;
}

function estaSobrecarregado(input, max)
{
	return $("#"+input).val().length > max;
}

/**
    Retorna um vetor que contém os valores do parâmetro em porcentagem em relação ao total do parâmetro.
*/
function emPorcentagem(vetor)
{
    var total = 0;
    for (var i = 0; i < vetor.length; i++)
        total += vetor[i];

    var retorno = new Array(vetor.length);
    for (var i = 0; i < vetor.length; i++)
    {
        retorno[i] = vetor[i]/total * 100;
        if (i > 0)
            retorno[i] += retorno[i-1];
    }
    retorno[retorno.length-1] = 100.00;
    return retorno;
}

function maiorValorDe(vetor)
{
    var maior = Number.MIN_VALUE;
    for(var i = 0; i < vetor.length; i++)
        maior = Math.max(maior, vetor[i]);
    return maior;
}

$(() => {
    $("#EnviarCadastro").click((e) => {
	if (estaVazio("NomeCliente") || estaSobrecarregado("NomeCliente") || estaVazio("EmailCliente") || estaSobrecarregado("EmailCliente")) // está em branco
	    return;

	inserirNaTabela("Usuario", {"Nome":$("#NomeCliente").val(), "Email": $("#EmailCliente").val()});
    })});

function inputInvalido(input)
{
    return (estaVazio(input) && estaSobrecarregado(input));
}

function inserirNaTabela(tabela, dados)
{
    $.ajax(localhost+tabela, 
    {
        type:"PUT",
        data : dados,
        success: function(informacao, informacao2, informacao3){
            alert("Insersão realizada com sucesso!");
        },
        error: function(informacao, informacao2, informacao3){
            alert("Ocorreu um erro de conexão com o servidor. Tente novamente mais tarde.");
        }
    });
}

function enviarAtendimento()
{
    if (inputInvalido("EmailCliente") || ($("#TipoRecla").val() == "7" && inputInvalido("DescRecla"))) // está em branco
        return;
    var IDUsuario = $("#EmailCliente").val();
    inserirNaTabela("Atendimento", {
        "Tipo": $("#TipoRecla").val(),
        "IDUsuario":IDUsuario,
        "Mensagem": $("#DescRecla").val(),
        "Data":dataAtual()
    });
}

function desenharGrafico(dados, labelsEixoX, labelsEixoY, cores, contexto)
{
    var porcentagem = emPorcentagem(dados);
    var maximo = maiorValorDe(dados) + 4;
    var data = {
        labels: labelsEixoX,
        datasets: [{
            type: "line",
            label: labelsEixoY[0],
            borderColor: cores[0],
            backgroundColor: cores[0],
            pointBorderWidth: 5,
            fill: false,
            data: porcentagem,
            yAxisID: 'y-axis-2'
        },{
            type: "bar",
            label: labelsEixoY[1],
            borderColor: cores[1],
            backgroundColor: cores[1],
            data: dados,
            yAxisID: 'y-axis-1'
        }]
    };

    var options = {
        scales: {
            xAxes: [{
                stacked: false
            }],

            yAxes: [{
                type: "linear",
                position: "left",
                id: "y-axis-1",
                stacked: false,
                ticks: {
                    suggestedMin: 0,
                    max: maximo
                },
                scaleLabel: {
                    display: true,
                    labelString: labelsEixoY[2]
                }
            },{
                type: "linear",
                position: "right",
                id: "y-axis-2",
                ticks: {
                    suggestedMin: 0,
                    max : 100,
                    callback: function(value) {
                        return value + "%";
                    }
                },
                scaleLabel: {
                    display: true,
                    labelString: labelsEixoY[3]
                }
            }]
        }
    };
    new Chart(contexto, {
        type: 'bar',
        data: data,
        options: options
    });
}

function getQtosTabela(tabela, dados)
{
    var ret
    $.ajax(localhost+tabela,
           {type:"GET", data:dados, 
            success: function(informacao, informacao2, informacao3){
                ret = JSON.parse(informacao).length;
            },
            erro: function(informacao, informacao2, informacao3){
                return 0;
            },
            async: false
           });
    return ret;
}

$(function loadGraficos()
{
    var d1 = getQtosTabela("Atendimento", {"Tipo":1});
    var d2 = getQtosTabela("Atendimento", {"Tipo":2});
    var d3 = getQtosTabela("Atendimento", {"Tipo":3});
    var d4 = getQtosTabela("Atendimento", {"Tipo":4});
    var d5 = getQtosTabela("Atendimento", {"Tipo":5});
    var d6 = getQtosTabela("Atendimento", {"Tipo":6});
    var d7 = getQtosTabela("Atendimento", {"Tipo":7});

    var dados = [{k: "Atendimento", v: d1},
                 {k: "Dúvida", v: d2},
                 {k: "Solicitação", v: d3},
                 {k: "Avaliação", v: d4},
                 {k: "Prazo de preparação", v: d5},
                 {k: "Avaria", v: d6},
                 {k: "Logística", v: d7}];

    dados.sort((a, b) => {
        return b.v - a.v;
    });
    
    var porcentagem = emPorcentagem(dados);
    desenharGrafico(dados.map((a) => a.v), dados.map((a) => a.k),
        ["Acumulado", "Reclamação", "Nº de reclamações", "Porcentagem"],
        ["#BA1E14", "#009900"], document.getElementById("GraPareto").getContext("2d"));

    // dados = [5,7,1,5,3];
    // desenharGrafico(dados, ["Mês atual","1 mês atrás","2 meses atrás","3 meses atrás","4 meses atrás"],
    //     ["Acumulado", "Dúvidas", "Nº de dúvidas", "Porcentagem"],
    //     ["#ff9966", "#00ff99"], document.getElementById("GraPareto2").getContext("2d"));

    // dados = [14,21,4,15,32];
    // desenharGrafico(dados, ["Mês atual","1 mês atrás","2 meses atrás","3 meses atrás","4 meses atrás"],
    //     ["Acumulado", "Solicitações", "Nº de solicitações", "Porcentagem"],
    //     ["#cc00cc", "#9999ff"], document.getElementById("GraPareto3").getContext("2d"));
});


if (typeof jQuery == 'undefined')
	console.log("jQuery não definido");

const localhost = "http://localhost:5000/";

function atualizarData(){
	$("#dataRecla").attr('value', dataAtual());
}

function dataAtual()
{
	var hoje = new Date();
	var dd = hoje.getDate();
	var mm = hoje.getMonth()+1; //Janeiro é 0!
	var yyyy = hoje.getFullYear();

	if(dd < 10) {
	    dd = '0' + dd;
	} 

	if(mm < 10) {
	    mm = '0' + mm;
	} 

	hoje = yyyy + '-' + mm + '-' + dd;
	return hoje;
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
	$.ajax("http://localhost:5000/"+tabela, {
		type:"GET",
		success: function(trem, trem2, trem3){
			return trem;
		},
		erro: function(trem, trem2, trem3){
			return trem;
		}
	});
}

function inserirNaTabela(tabela, dados)
{
	$.ajax(localhost+tabela+"/", {
		type:"POST",
        data : dados,
		success: function(trem, trem2, trem3){
			alert("Você está cadastrado!");
		},
		erro: function(trem, trem2, trem3){
			alert("Já existe alguém com esse nome ou email");
		}
	});
}

function estaVazio(input)
{
	return $("#input").val().length == 0;
}

function estaSobrecarregado(input, max)
{
	return $("#input").val().length > max;
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

function enviarCadastro()
{
	if (estaVazio("NomeCliente") || estaSobrecarregado("NomeCliente") || estaVazio("SenhaCliente") || estaSobrecarregado("SenhaCliente") || estaVazio("EmailCliente") || estaSobrecarregado("EmailCliente")) // está em branco
		return;

	inserirNaTabela("Usuario", {"Nome":$("NomeCliente").val(), "Email": $("EmailCliente").val()});
}

function enviarAtendimento()
{

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

function loadGraficos()
{
    var dados = [16,11,9,6,5];
    var porcentagem = emPorcentagem(dados);
    desenharGrafico(dados, ["Atendimento","Prazo de preparação","Avaria","Logística","Outros"],
        ["Acumulado", "Reclamação", "Nº de reclamações", "Porcentagem"],
        ["#BA1E14", "#009900"], document.getElementById("GraPareto").getContext("2d"));

    dados = [5,7,1,5,3];
    desenharGrafico(dados, ["Mês atual","1 mês atrás","2 meses atrás","3 meses atrás","4 meses atrás"],
        ["Acumulado", "Dúvidas", "Nº de dúvidas", "Porcentagem"],
        ["#ff9966", "#00ff99"], document.getElementById("GraPareto2").getContext("2d"));

    dados = [14,21,4,15,32];
    desenharGrafico(dados, ["Mês atual","1 mês atrás","2 meses atrás","3 meses atrás","4 meses atrás"],
        ["Acumulado", "Solicitações", "Nº de solicitações", "Porcentagem"],
        ["#cc00cc", "#9999ff"], document.getElementById("GraPareto3").getContext("2d"));
}
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
	$.ajax("http://localhost:5000/"+tabela, {
		type:"POST",
		success: function(trem, trem2, trem3){
			return trem;
		},
		erro: function(trem, trem2, trem3){
			return trem;
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

function enviarCadastro()
{
	if (estaVazio("NomeCliente") || estaSobrecarregado("NomeCliente") || estaVazio("SenhaCliente") || estaSobrecarregado("SenhaCliente") || estaVazio("DescRecla") || estaSobrecarregado("DescRecla")) // está em branco
		return;

	//$.ajax(localhost+"Usuario",{type:"POST", });
}

function loadGraficos()
{
	var data = {
        labels: ["Reclamação 1","Reclamação 2","Reclamação 3","Reclamação 4","Outros"],
        datasets: [{
            type: "line",
            label: "Acumulado",
            borderColor: "#BA1E14",
            backgroundColor: "#BA1E14",
            pointBorderWidth: 5,
            fill: false,
            data: [34.00,57.00,76.00,89.00,100.00],
            yAxisID: 'y-axis-2'
        },{
            type: "bar",
            label: "Reclamação",
            borderColor: "#56B513",
            backgroundColor: "#56B513",
            data: [16,11,9,6,5],
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
                    max: 20
                },
                scaleLabel: {
                    display: true,
                    labelString: "Nº de Reclamações"
                }
            },{
                type: "linear",
                position: "right",
                id: "y-axis-2",
                ticks: {
                    suggestedMin: 0,
                    callback: function(value) {
                        return value + "%";
                    }
                },
                scaleLabel: {
                    display: true,
                    labelString: "Porcentagem"
                }
            }]
        }
    };
	var ctx = document.getElementById("GraPareto").getContext("2d");
	var ctx2 = document.getElementById("GraPareto2").getContext("2d");
	var ctx3 = document.getElementById("GraPareto3").getContext("2d");

    new Chart(ctx, {
        type: 'bar',
        data: data,
        options: options
    });

    new Chart(ctx2, {
        type: 'bar',
        data: data,
        options: options
    });

    new Chart(ctx3, {
        type: 'bar',
        data: data,
        options: options
    });

/*
var ctx = document.getElementById('GraBola').getContext('2d');
var myChart = new Chart(ctx, {
  type: 'line',
  data: {
    labels: ['M', 'T', 'W', 'T', 'F', 'S', 'S'],
    datasets: [{
      label: 'apples',
      data: [12, 19, 3, 17, 6, 3, 7],
      backgroundColor: "rgba(153,255,51,0.4)"
    }, {
      label: 'oranges',
      data: [2, 29, 5, 5, 2, 3, 10],
      backgroundColor: "rgba(255,153,0,0.4)"
    }]
  }
});*/
}
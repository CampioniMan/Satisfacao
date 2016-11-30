<?php

?>
<html>
	<head>
		<title>Seja bem-vindo ao Reclame!</title>
		<script type="text/javascript" src="dependencies/jQuery/jquery-2.1.4.js"></script>
		<script type="text/javascript" src="dependencies/bootstrap-switch/dist/js/bootstrap-switch.js"></script>
		<script type="text/javascript" src="index.js"></script>
		<link rel="stylesheet" href="dependencies/bootstrap/css/normalize.css">
  		<link rel="stylesheet" href="dependencies/bootstrap/css/bootstrap.css">
  		<link rel="stylesheet" href="dependencies/bootstrap-switch/dist/css/bootstrap3/bootstrap-switch.css">
  		<link rel="stylesheet" href="index.css">
	</head>
	<body>
		<style >
		.labelNormal{
			font-weight: normal;
		}
		</style>
		<nav class="navbar navbar-inverse">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="atendimento.php">Atendimento!</a>
		    </div>
		    <ul class="nav navbar-nav">
		      <li><a href="index.php">Home</a></li>
		      <li><a href="relat.php">Relatório</a></li>
		    </ul>
		  </div>
		</nav>
		<!-- Fim da navBar -->


		<div id="content" class="col-xs-12 col-sm-12 col-md-8 col-lg-8 central">
		<table class="table" cellspacing="5" cellpadding="3">
			<tr>
				<th colspan="2">
					Preencha o formulário para nós validarmos a sua reclamação
				</th>
			</tr>
			<tr>
				<td >
					<label for="NomeCliente">Cliente :</label>
					<div class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
						<input type="text" class="form-control" id="NomeCliente">
					</div>
					<label for="NumCliente">Email :</label>
					<div class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
						<input type="email" class="form-control" id="EmailCliente">
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<label for="TipoRecla">Tipo de reclamação :</label><br>
						<select id="TipoRecla" class="form-control">
						    <option value="one">Atendimento</option>
						    <option value="two">Prazo de preparação</option>
						    <option value="three">Avaria</option>
						    <option value="four">Logística</option>
						    <option value="five">Dúvida</option>
						    <option value="six">Solicitação de serviço</option>
						    <option value="seven">Outros</option>
						</select><br>
			            <div class="input-group">
							<span id="GlyTipoOutros" class="input-group-addon" style="visibility: hidden;"><i class="glyphicon glyphicon-text-width"></i></span>
							<input type="text" class="form-control" id="TipoOutros" maxlength="35" style="visibility: hidden;">
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<label>Descrição da reclamação(300) :</label>
					<div class="form-group">
						<textarea id="DescRecla" rows="8" maxlength="300" style="resize: vertical; width: 80%; position:relative; left:10%; border:1px black solid;"></textarea>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<input type="button" value="Enviar" onclick="enviarAtendimento();">
				</td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">

	$('[data-onload]').each(function(){ // fazer os "onload"s de elementos
	    eval($(this).data('onload'));
	});

	$('#TipoRecla').on('change', function() {
		if (this.value == "seven")
		{
			$("span[id='GlyTipoOutros']").css('visibility', 'visible');
			$("#TipoOutros").css('visibility', 'visible');
			$("#TipoOutros").val("");
		}
		else
		{
			$("span[id='GlyTipoOutros']").css('visibility', 'hidden');
			$("#TipoOutros").css('visibility', 'hidden');
		}
	});

	$("label[id^=Tcbx]").css({"background-color": "rgba(1, 11, 53, 0.41)", "border-color": "rgba(4, 14, 60, 0.41)","border-radius": "0px 15px 15px 0px"});

	</script>
	</body>
</html>
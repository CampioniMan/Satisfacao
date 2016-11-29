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
		<nav class="navbar navbar-inverse">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="#">Reclame!</a>
		    </div>
		    <ul class="nav navbar-nav">
		      <li class="active"><a href="index.php">Home</a></li>
		      <li><a href="atendimento.php">Atendimento</a>
		      <li><a href="relat.php">Relatório</a></li>
		    </ul>
		    <ul class="nav navbar-nav navbar-right">
		      <li><a href="cadastro.php"><span class="glyphicon glyphicon-user"></span> Cadastrar</a></li>
		      <li><a href="cadastro.php?log=true"><span class="glyphicon glyphicon-log-in"></span> Entrar</a></li>
		    </ul>
		  </div>
		</nav>
		<div id="content" class="col-xs-12 col-sm-12 col-md-6 col-lg-6 central">
			<div>
				<h2>Registre-se gratuitamente!</h2>
			</div>
			<table>
				<tr>
					<td>
						<label for="NomeCliente">Nome :</label>
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
							<input type="text" class="form-control" id="NomeCliente">
						</div>
						<label for="TelCliente">Email :</label>
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
							<input type="text" class="form-control" id="EmailCliente">
						</div>
					</td>
				</tr>
				<tr>
					<th colspan="2"><input type="button" value="Cadastrar" onclick="enviarCadastro();"></th>
				</tr>
			</table>
		</div>
	</body>
</html>
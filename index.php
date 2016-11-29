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
		      <a class="navbar-brand" href="atendimento.php">Atendimento!</a>
		    </div>
		    <ul class="nav navbar-nav">
		      <li class="active"><a href="index.php">Home</a></li>
		      <li><a href="relat.php">Relatório</a></li>
		    </ul>
		  </div>
		</nav>
		<div id="content" class="col-xs-12 col-sm-12 col-md-6 col-lg-6 central">
			<div>
				<h1>Bem vindo ao Reclame!</h1>
				<p>Aqui você pode se aproveitar de nossas ferramentas para desenvolver dúvidas, reclamações ou áté mesmo solicitações de produtos.</p>
				<p>Nós iremos responder as perguntas que você fez via e-mail, por isso é importante que você se cadastre e utilize o seu e-mail.</p>
				<p>Também temos um suporte a registro de reclamações, que podem ser feitas por causa de vários motivos(avaria, atendimento, etc).
				As reclamações que tiverem vínculo conosco serão absorvidas e podem até ser implementadas pela nossa equipe de desenvolvimento!</p>
				<p>E há a possibilidade de você pedir um produto que nós oferecemos.</p>
			</div><br><br><br>
			<div>
				<h3>Registre-se gratuitamente!</h3>
			</div>
			<table class="table" cellspacing="5" cellpadding="3">
				<tr>
					<td>
						<label for="NomeCliente">Nome :</label>
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
							<input type="text" class="form-control" id="NomeCliente">
						</div>
						<label for="EmailCliente">Email :</label>
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
							<input type="text" class="form-control" id="EmailCliente">
						</div>
						<label for="SenhaCliente">Senha :</label>
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
							<input type="password" class="form-control" id="SenhaCliente">
						</div>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="button" value="Cadastrar" onclick="enviarCadastro();">
					</th>
				</tr>
			</table>
		</div>
	</body>
</html>
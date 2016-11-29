<?php

?>
<html>
	<head>
		<title>Seja bem-vindo ao Reclame!</title>
		<script type="text/javascript" src="dependencies/jQuery/jquery-2.1.4.js"></script>
		<script type="text/javascript" src="dependencies/bootstrap-switch/dist/js/bootstrap-switch.js"></script>
		<script type="text/javascript" src="index.js"></script>
		<script type="text/javascript" src="dependencies/Chart.min.js"></script>
		<link rel="stylesheet" href="dependencies/bootstrap/css/normalize.css">
  		<link rel="stylesheet" href="dependencies/bootstrap/css/bootstrap.css">
  		<link rel="stylesheet" href="dependencies/bootstrap-switch/dist/css/bootstrap3/bootstrap-switch.css">
  		<link rel="stylesheet" href="index.css">
	</head>
	<body onload="loadGraficos();">
		<nav class="navbar navbar-inverse">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="atendimento.php">Reclame!</a>
		    </div>
		    <ul class="nav navbar-nav">
		      <li><a href="index.php">Home</a></li>
		      <li  class="active"><a href="relat.php">Relatório</a></li>
		    </ul>
		  </div>
		</nav>
		<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8" style="left: 50%; transform: translateX(-50%);">
			<canvas id="GraPareto"></canvas>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8" style="left: 50%; transform: translateX(-50%);">
			<canvas id="GraPareto2"></canvas>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-8 col-lg-8" style="left: 50%; transform: translateX(-50%);">
			<canvas id="GraPareto3"></canvas>
		</div>
	</body>
</html>
<?php

?>
<html>
	<head>
		<title>Seja bem-vindo ao Reclame!</title>
		<script type="text/javascript" src="dependencies/jQuery/jquery-2.1.4.js"></script>
		<link rel="stylesheet" href="dependencies/bootstrap/css/normalize.css">
  		<link rel="stylesheet" href="dependencies/bootstrap/css/bootstrap.css">
	</head>
	<body>
		<style >
		.labelNormal{
			font-weight: normal;
		}
		</style>
		<div id="content" class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
		Nº da Reclamacao: <br>
		<table class="table" cellspacing="5" cellpadding="3" border="5">
			<tr>
				<th colspan="2">
					I - Identificação e Registro da Reclamação
					(A preencher pelo receptor da reclamação ou cliente)
				</th>
			</tr>
			<tr>
				<td >
					<label for="NomeCliente">Cliente :</label><input type="text" class="form-control" id="NomeCliente">
					<label for="NumCliente">Nº do Cliente :</label><input type="text" class="form-control" id="NumCliente">
					<label for="TelCliente">Telefone/email :</label><input type="text" class="form-control" id="TelCliente">
				</td>
				<td>
					<label for="dataRecla">Data da reclamação :</label><input type="date" class="form-control" id="dataRecla">
					<label for="NomeAtend">Nome do receptor da reclamação :</label><input type="text" class="form-control" id="NomeAtend">
				</td>
			</tr>
			<tr>
				<td>
					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<label for="TipoRecla">Tipo de reclamação :</label><br>
						<div class="form-group">
							<input type="checkbox" value="1" class="form-control" id="TipoRecla"> Atendimento<br>
							<input type="checkbox" value="2" class="form-control" id="TipoRecla"> Prazo de reparação<br>
							<input type="checkbox" value="3" class="form-control" id="TipoRecla"> Avaria<br>
							<input type="checkbox" value="4" class="form-control" id="TipoRecla"> Logística<br>
							<input type="checkbox" value="5" class="form-control" id="TipoRecla"> Outros<br>
							<input type="text" class="form-control" id="TipoOutros">
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
						<label for="TipoRegis">Registrada via :</label><br><div class="form-group">
						<label for="TipoRegis" class="control-label labelNormal">Telefone</label>
							<input type="checkbox" class="form-control" id="TipoRegis"><br>
						<label for="TipoRegis" class="control-label labelNormal">Presencial</label>
							<input type="checkbox" class="form-control" id="TipoRegis"> <br>
						<label for="TipoRegis" class="control-label labelNormal">Escrito</label>
							<input type="checkbox" class="form-control" id="TipoRegis"> <br>
						<label for="TipoRegis" class="control-label labelNormal">E-mail</label>
							<input type="checkbox" class="form-control" id="TipoRegis"> 
						</div>
					</div>
				</td>
				<td>
					<label>Descrição da reclamação :</label>
					<div class="form-group">
						<textarea id="DescRecla" rows="8" style="resize: vertical;" cols="30"></textarea>
					</div>
				</td>
			</tr>
		</table>
		<table border="5">
			<tr>
				<th colspan="2">
					II - Análise da reclamação
					(A preencher pela diretoria ou farmacêutico responsável)
				</th>
			</tr>
			<tr>
				<td>
					<label for="ReclaAceita">Reclamação :</label><br>
					<div class="form-group">
						<input type="radio" class="form-control" name="ReclaAceita" id="ReclaAceita"> Aceita<br>
						<input type="radio" class="form-control" name="ReclaAceita" id="ReclaAceita"> Não Aceita
					</div>
				</td>
				<td>
					<div class="form-group">
						<label for="ReclaAceita">Motivo da não aceitação :</label><br><input type="text" class="form-control" id="MotivoNAceita">
					</div>
				</td>
			</tr>
		</table>
		<table border="5">
			<tr>
				<th colspan="2">
					III - Resolução da Reclamação
					(A preencher pela diretoria ou farmacêutico responsável)
				</th>
			</tr>
			<tr>
				<td colspan="2">
					<label for="ReclaAceita">Causas apuradas da reclamação :</label><br>
					<div class="form-group">
						<input type="radio" class="form-control" name="ReclaAceita" id="ReclaAceita"> Aceita<br>
						<input type="radio" class="form-control" name="ReclaAceita" id="ReclaAceita"> Não Aceita
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label for="ReclaAceita">Correções :</label><br>
					<div class="form-group">
						<input type="radio" class="form-control" name="ReclaAceita" id="ReclaAceita"> Aceita<br>
						<input type="radio" class="form-control" name="ReclaAceita" id="ReclaAceita"> Não Aceita
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<label for="dataCorrec">Data prevista de correção :</label><br>
					<div class="form-group">
						<input type="date" class="form-control" id="dataCorrec">
					</div>
				</td>
				<td>
					<div class="form-group">
						<label for="Respons">Responsável :</label><br><input type="text" class="form-control" id="Respons">
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<label for="dataConcl">Data de conclusão :</label><br>
					<div class="form-group">
						<input type="date" class="form-control" id="dataConcl">
					</div>
				</td>
				<td>
					<div class="form-group">
						<label for="NomeConcl">Concluído por :</label><br><input type="text" class="form-control" id="NomeConcl">
					</div>
				</td>
			</tr>
		</table>
	</div>
	</body>
</html>
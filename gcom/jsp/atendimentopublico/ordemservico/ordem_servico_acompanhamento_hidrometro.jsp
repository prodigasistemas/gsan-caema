<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de
	Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">


<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"
	formName="GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm"
	dynamicJavascript="true" />

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.jquery"/>jquery.js"></script>

<script language="JavaScript">
	function validarForm() {
		var form = document.forms[0];

		if (form.firma.selectedIndex == -1 || form.firma.selectedIndex == 0) {
			form.firma.focus();
			alert('Infome a Firma');
			return;
		}

		if (form.rotaInicial.value == ''
				&& form.rotaSequenciaInicial.value != '') {
			alert('Informe a Rota Inicial');
			return;
		}

		if (form.rotaFinal.value == '' && form.rotaSequenciaFinal.value != '') {
			alert('Informe a Rota Final');
			return;
		}

		//if (!validateInteger(form)) { return false; }

		if (validateGerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm(form)) {
			if (!verificaDataMensagemPersonalizada(
					form.dataEncerramentoInicial,
					'Data de Encerramento Inicial � inv�lida')) {
				return false;
			}
			if (!verificaDataMensagemPersonalizada(form.dataEncerramentoFinal,
					'Data de Encerramento Final � inv�lida')) {
				return false;
			}
			if (!verificaDataMensagemPersonalizada(form.dataExecucaoInicial,
					'Data de Execu��o Inicial � inv�lida')) {
				return false;
			}
			if (!verificaDataMensagemPersonalizada(form.dataExecucaoFinal,
					'Data de Execu��o Final � inv�lida')) {
				return false;
			}
			if (!validaTodosPeriodos()) {
				return false;
			}

			form.descricaoMotivoEncerramento.value = form.idMotivoEncerramento.options[form.idMotivoEncerramento.selectedIndex].text;
			form.nomeFirma.value = form.firma.options[form.firma.selectedIndex].text;

			toggleBox('demodiv', 1);
		}
	}

	function validaTodosPeriodos() {
		var form = document.forms[0];

		if (validaPeriodoEncerramento(form)
				&& comparaData(form.dataEncerramentoInicial.value, '>',
						form.dataEncerramentoFinal.value)) {
			alert('Data Final do Per�odo de Encerramento � anterior � Data Inicial');
			return false;
		} else if (validaPeriodoExecucao(form)
				&& comparaData(form.dataExecucaoInicial.value, '>',
						form.dataExecucaoFinal.value)) {
			alert('Data Final do Per�odo de Execu��o � anterior � Data Inicial');
			return false;
		}

		return true;
	}

	function validaPeriodoEncerramento(form) {
		var form = document.forms[0];

		var periodoEncerramentoInicial = trim(form.dataEncerramentoInicial.value);
		var periodoEncerramentoFinal = trim(form.dataEncerramentoFinal.value);

		if ((periodoEncerramentoInicial != null && periodoEncerramentoInicial != '')
				&& ((periodoEncerramentoFinal == null || periodoEncerramentoFinal == ''))) {
			alert('Informe Data Final Per�odo de Encerramento');
			return false;
		} else if ((periodoEncerramentoFinal != null && periodoEncerramentoFinal != '')
				&& ((periodoEncerramentoInicial == null || periodoEncerramentoInicial == ''))) {
			alert('Informe Data Inicial Per�odo de Encerramento');
			return false;
		}

		return true;
	}

	function validaPeriodoExecucao(form) {
		var form = document.forms[0];

		var periodoExecucaoInicial = trim(form.dataExecucaoInicial.value);
		var periodoExecucaoFinal = trim(form.dataExecucaoFinal.value);

		if ((periodoExecucaoInicial != null && periodoExecucaoInicial != '')
				&& ((periodoExecucaoFinal == null || periodoExecucaoFinal == ''))) {
			alert('Informe Data Final Per�odo de Execu��o');
			return false;
		} else if ((periodoExecucaoFinal != null && periodoExecucaoFinal != '')
				&& ((periodoExecucaoInicial == null || periodoExecucaoInicial == ''))) {
			alert('Informe Data Inicial Per�odo de Execu��o');
			return false;
		}

		return true;
	}

	//So chama a fun��o abrirCalendario caso o campo esteja habilitado
	function chamarCalendario(fieldNameOrigem, objetoRelacionado,
			fieldNameDestino) {

		if (objetoRelacionado.disabled != true) {
			if (fieldNameDestino != '') {
				abrirCalendarioReplicando(
						'GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm',
						fieldNameOrigem, fieldNameDestino);
			} else {
				abrirCalendario(
						'GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm',
						fieldNameOrigem);
			}
		}
	}

	// ===============================================================================================
	function chamarPopup1(url, tipo, objeto, codigoObjeto, altura, largura, msg) {
		if (objeto == null || codigoObjeto == null) {
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		} else {
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)) {
				alert(msg);
			} else {
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "="
						+ codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo,
						altura, largura);
			}
		}
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro,
			tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'localidadeOrigem') {
			form.localidadeInicial.value = codigoRegistro;
			form.nomeLocalidadeInicial.value = descricaoRegistro;
			form.nomeLocalidadeInicial.style.color = "#000000";

			form.localidadeFinal.value = codigoRegistro;
			form.nomeLocalidadeFinal.value = descricaoRegistro;
			form.nomeLocalidadeFinal.style.color = "#000000";

			form.codigoSetorComercialInicial.focus('desabilita');
		} else if (tipoConsulta == 'localidadeDestino') {
			form.localidadeFinal.value = codigoRegistro;
			form.nomeLocalidadeFinal.value = descricaoRegistro;
			form.nomeLocalidadeFinal.style.color = "#000000";
			form.codigoSetorComercialFinal.focus();

			habilitarDesabilitarSetorComercial();
		}

	}

	function limparBorrachaOrigem(tipo) {

		var form = document.forms[0];

		switch (tipo) {
		case 1: //De localidara pra baixo

			if (!form.localidadeInicial.disabled) {

				form.localidadeInicial.value = "";
				form.nomeLocalidadeInicial.value = "";

				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";

				form.codigoSetorComercialInicial.value = "";
				form.setorComercialInicial.value = "";
				form.nomeSetorComercialInicial.value = "";

				form.quadraInicial.value = "";
				form.idQuadraInicial.value = "";
				form.rotaInicial.value = "";
				form.rotaSequenciaInicial.value = "";

				form.quadraInicial.disabled = true;
				form.rotaInicial.disabled = true;
				form.rotaSequenciaInicial.disabled = true;

			}

			break;

		case 2: //De setor para baixo

			if (!form.codigoSetorComercialInicial.disabled) {

				form.codigoSetorComercialInicial.value = "";
				form.setorComercialInicial.value = "";
				form.nomeSetorComercialInicial.value = "";

				form.quadraInicial.value = "";
				form.idQuadraInicial.value = "";
				form.rotaInicial.value = "";
				form.rotaSequenciaInicial.value = "";

				form.quadraInicial.disabled = true;
				form.rotaInicial.disabled = true;
				form.rotaSequenciaInicial.disabled = true;

				form.codigoSetorComercialFinal.focus();
			}

			break;
		}

		limparBorrachaDestino(tipo);
	}

	/*
	 Limpa os campos da faixa final pelo click na borracha(conforme parametro passado)
	 */
	function limparBorrachaDestino(tipo) {
		var form = document.forms[0];

		switch (tipo) {
		case 1: //De localidade pra baixo

			if (!form.localidadeFinal.disabled) {

				form.localidadeFinal.value = "";
				form.nomeLocalidadeFinal.value = "";

				form.setorComercialFinal.value = "";
				form.nomeSetorComercialFinal.value = "";
				form.codigoSetorComercialFinal.value = "";

				form.quadraFinal.value = "";
				form.idQuadraFinal.value = "";

				form.rotaFinal.value = "";
				form.rotaSequenciaFinal.value = "";

				form.quadraFinal.disabled = true;
				form.rotaFinal.disabled = true;
				form.rotaSequenciaFinal.disabled = true;

				form.localidadeFinal.focus();
			}

			break;

		case 2: //De setor para baixo		 

			if (!form.codigoSetorComercialInicial.disabled) {

				form.setorComercialFinal.value = "";
				form.nomeSetorComercialFinal.value = "";
				form.codigoSetorComercialFinal.value = "";

				form.quadraFinal.value = "";
				form.idQuadraFinal.value = "";
				form.rotaFinal.value = "";
				form.rotaSequenciaFinal.value = "";

				form.quadraFinal.disabled = true;
				form.rotaFinal.disabled = true;
				form.rotaSequenciaFinal.disabled = true;

				form.codigoSetorComercialFinal.focus();
			}

			break;

		default:
			break
		}
	}

	function controleDataEncerramento() {
		var form = document.forms[0];

		if (form.situacaoOrdemServico[0].checked) {
			form.dataEncerramentoInicial.disabled = false;
			form.dataEncerramentoFinal.disabled = false;
			document.getElementById("calendario1").style.display = '';
			document.getElementById("calendario2").style.display = '';
		} else if (form.situacaoOrdemServico[1].checked) {
			form.dataEncerramentoInicial.disabled = true;
			form.dataEncerramentoInicial.value = "";
			form.dataEncerramentoFinal.disabled = true;
			form.dataEncerramentoFinal.value = "";
			document.getElementById("calendario1").style.display = 'none';
			document.getElementById("calendario2").style.display = 'none';
		}
	}

	function controleDataExecucao() {
		var form = document.forms[0];

		if (form.situacaoOrdemServico[0].checked) {
			form.dataExecucaoInicial.disabled = false;
			form.dataExecucaoFinal.disabled = false;
			document.getElementById("calendario3").style.display = '';
			document.getElementById("calendario4").style.display = '';
		} else if (form.situacaoOrdemServico[1].checked) {
			form.dataExecucaoInicial.disabled = true;
			form.dataExecucaoInicial.value = "";
			form.dataExecucaoFinal.disabled = true;
			form.dataExecucaoFinal.value = "";
			document.getElementById("calendario3").style.display = 'none';
			document.getElementById("calendario4").style.display = 'none';
		}
	}

	function controleTipoRelatorio() {
		var form = document.forms[0];

		if (form.situacaoOrdemServico[0].checked) {
			form.tipoRelatorioAcompanhamento[0].disabled = false;
			form.tipoRelatorioAcompanhamento[1].disabled = false;
			form.tipoRelatorioAcompanhamento[2].disabled = false;
			form.tipoRelatorioAcompanhamento[3].disabled = false;
			form.tipoRelatorioAcompanhamento[0].checked = true;
		}else if (form.situacaoOrdemServico[1].checked) {
			form.tipoRelatorioAcompanhamento[0].disabled = false;
			form.tipoRelatorioAcompanhamento[1].disabled = false;
			form.tipoRelatorioAcompanhamento[2].disabled = true;
			form.tipoRelatorioAcompanhamento[3].disabled = true;
			form.tipoRelatorioAcompanhamento[0].checked = true;
		}
	}

	function controleMotivoEncerramento() {
		var form = document.forms[0];

		if (form.situacaoOrdemServico[0].checked) {
			form.idMotivoEncerramento.disabled = false;
		} else if (form.situacaoOrdemServico[1].checked) {
			form.idMotivoEncerramento.disabled = true;
			form.idMotivoEncerramento.selectedIndex = 0;
		}
	}

	function replicaDataEncerramento() {
		var form = document.forms[0];
		form.dataEncerramentoFinal.value = form.dataEncerramentoInicial.value;
	}

	function replicaDataExecucao() {
		var form = document.forms[0];
		form.dataExecucaoFinal.value = form.dataExecucaoInicial.value;
	}

	function chamarPopup7param(url, tipo, objeto, codigoObjeto, altura,
			largura, msg) {
		if (objeto == null || codigoObjeto == null) {
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		} else {
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)) {
				alert(msg);
			} else {
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "="
						+ codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo,
						altura, largura);
			}
		}
	}

	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,
			campo) {
		if (!campo.disabled) {
			if (objeto == null || codigoObjeto == null) {
				if (tipo == "") {
					abrirPopup(url, altura, largura);
				} else {
					abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
				}
			} else {
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)) {
					alert(msg);
				} else {
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "="
							+ codigoObjeto, altura, largura);
				}
			}
		}
	}

	/*
	recupera os dados vindos pelo metodo enviarDadosQuatroParametros 
	 */

	function recuperarDadosQuatroParametros(idRegistro, descricaoRegistro,
			codigoRegistro, tipoConsulta) {
		var form = document.forms[0];
		if (tipoConsulta == 'setorComercialOrigem') {
			form.codigoSetorComercialInicial.value = codigoRegistro;
			form.setorComercialInicial.value = idRegistro;
			form.nomeSetorComercialInicial.value = descricaoRegistro;
			form.nomeSetorComercialInicial.style.color = "#000000";

			form.codigoSetorComercialFinal.value = codigoRegistro;
			form.setorComercialFinal.value = idRegistro;
			form.nomeSetorComercialFinal.value = descricaoRegistro;
			form.nomeSetorComercialFinal.style.color = "#000000";

		}

		if (tipoConsulta == 'setorComercialDestino') {
			form.codigoSetorComercialFinal.value = codigoRegistro;
			form.setorComercialFinal.value = idRegistro;
			form.nomeSetorComercialFinal.value = descricaoRegistro;
			form.nomeSetorComercialFinal.style.color = "#000000";
		}

		validarSetorInscricao('desabilita');
	}

	function duplicarLocalidade() {
		var formulario = document.forms[0];
		formulario.localidadeFinal.value = formulario.localidadeInicial.value;
		formulario.nomeLocalidadeFinal.value = formulario.nomeLocalidadeInicial.value;
	}

	function duplicarSetorComercial() {
		var formulario = document.forms[0];
		formulario.codigoSetorComercialFinal.value = formulario.codigoSetorComercialInicial.value;
		formulario.nomeSetorComercialFinal.value = formulario.nomeSetorComercialInicial.value;
	}

	function duplicarQuadra() {
		var formulario = document.forms[0];
		formulario.quadraFinal.value = formulario.quadraInicial.value;
	}

	function duplicarRota() {
		var formulario = document.forms[0];
		formulario.rotaFinal.value = formulario.rotaInicial.value;
	}

	function duplicarSequenciaRota() {
		var formulario = document.forms[0];
		formulario.rotaSequenciaFinal.value = formulario.rotaSequenciaInicial.value;
	}

	function habilitarDesabilitarLupaSetor(tipo) {
		var form = document.forms[0];
		if (!form.codigoSetorComercialInicial.disabled
				&& !form.codigoSetorComercialFinal.disabled) {
			//se for a lupa SetorComercialInicial
			if (tipo == 1) {
				chamarPopup('exibirPesquisarSetorComercialAction.do',
						'setorComercialOrigem', 'idLocalidade',
						document.forms[0].localidadeInicial.value, 275, 480,
						'Informe Localidade Inicial.',
						document.forms[0].setorComercialInicial);
				//senao lupa do SetorComercialFinal	
			} else {
				chamarPopup('exibirPesquisarSetorComercialAction.do',
						'setorComercialDestino', 'idLocalidade',
						document.forms[0].localidadeFinal.value, 275, 480,
						'Informe Localidade Final.',
						document.forms[0].setorComercialFinal);
			}
		}
	}

	function validarInscricoes() {
		validarLocalidadeInscricao();
		validarSetorInscricao();
		validarQuadraInscricao();
		validarRotaInscricao();
		validarSequenciaRotaInscricao();
	}

	function validarLocalidadeInscricao(opcao, origem) {
		var form = document.forms[0];
		if (parseInt(form.localidadeInicial.value) > parseInt(form.localidadeFinal.value)) {
			alert('A Localidade Final deve ser Maior ou Igual a Inicial.');
			form.nomeLocalidadeFinal.value = '';
			form.localidadeFinal.value = form.localidadeInicial.value;
			form.localidadeFinal.focus();
		} else {
			if (form.localidadeInicial.value == ''
					&& form.nomeLocalidadeInicial.value != 'Localidade inexistente'
					&& origem == true) {
				limparBorrachaOrigem(1);
			} else if (form.localidadeFinal.value == ''
					&& form.nomeLocalidadeFinal.value != 'Localidade inexistente.'
					&& origem != true) {
				limparBorrachaDestino(1);
			}

			//habilitar ou desabilitar campos inferiores.
			habilitarDesabilitarSetorComercial(opcao);
		}
	}

	function validarSetorInscricao(opcao) {
		var form = document.forms[0];
		if (parseInt(form.codigoSetorComercialInicial.value) > parseInt(form.codigoSetorComercialFinal.value)) {
			alert('O C�digo do Setor Comercial Final deve ser Maior ou Igual ao Inicial.');
			form.nomeSetorComercialFinal.value = '';
			form.codigoSetorComercialFinal.value = form.codigoSetorComercialInicial.value;
			form.codigoSetorComercialFinal.focus();
		} else {
			if (form.codigoSetorComercialInicial.value == ''
					&& form.nomeSetorComercialInicial.value != 'Setor comercial inexistente.'
					&& origem == true) {
				limparBorrachaOrigem(2);
			} else if (form.codigoSetorComercialFinal.value == ''
					&& form.nomeSetorComercialFinal.value != 'Setor comercial inexistente.'
					&& origem != true) {
				limparBorrachaDestino(2);
			}

			//habilitar ou desabilitar campos inferiores.
			habilitarDesabilitarQuadra(opcao);
		}
	}

	function validarQuadraInscricao(opcao) {
		var form = document.forms[0];
		if (parseInt(form.quadraInicial.value) > parseInt(form.quadraFinal.value)) {
			alert('A Quadra Final deve ser Maior ou Igual a Inicial.');
			form.quadraFinal.value = form.quadraInicial.value;
			form.quadraFinal.focus();
		} else {
			//habilitar ou desabilitar campos inferiores.
			habilitarDesabilitarRota(opcao);
		}
	}

	function validarRotaInscricao(opcao) {
		var form = document.forms[0];
		if (parseInt(form.rotaInicial.value) > parseInt(form.rotaFinal.value)) {
			alert('A Rota Final deve ser Maior ou Igual a Inicial.');
			form.rotaFinal.value = form.rotaInicial.value;
			form.rotaFinal.focus();
		}
	}

	function validarSequenciaRotaInscricao() {
		var form = document.forms[0];
		if (parseInt(form.rotaSequenciaInicial.value > form.rotaSequenciaFinal.value)) {
			alert('O Sequencial Final deve ser Maior ou Igual a Inicial.');
			form.rotaSequenciaFinal.value = form.rotaSequencialInicial.value;
			form.rotaSequenciaFinal.focus();
		}
	}

	function habilitarDesabilitarSetorComercial(opcao) {
		var form = document.forms[0];
		if (form.localidadeInicial.value != form.localidadeFinal.value) {
			form.codigoSetorComercialInicial.value = '';
			form.codigoSetorComercialInicial.disabled = true;
			form.nomeSetorComercialInicial.value = '';
			document.getElementById("btPesqSetorComercialInicial").disabled = true;

			form.codigoSetorComercialFinal.value = '';
			form.codigoSetorComercialFinal.disabled = true;
			form.nomeSetorComercialFinal.value = '';
			document.getElementById("btPesqSetorComercialFinal").disabled = true;
			//desabilita os campos abaixo
			habilitarDesabilitarQuadra();
		} else if (opcao == 'desabilita') {
			form.codigoSetorComercialInicial.value = '';
			form.codigoSetorComercialInicial.disabled = false;
			form.nomeSetorComercialInicial.value = '';
			document.getElementById("btPesqSetorComercialInicial").disabled = false;

			form.codigoSetorComercialFinal.value = '';
			form.codigoSetorComercialFinal.disabled = false;
			form.nomeSetorComercialFinal.value = '';
			document.getElementById("btPesqSetorComercialFinal").disabled = false;
		}
	}

	function habilitarDesabilitarQuadra(opcao) {
		var form = document.forms[0];
		if ((form.codigoSetorComercialInicial.value != form.codigoSetorComercialFinal.value)
				|| form.codigoSetorComercialInicial.disabled == true) {
			form.quadraInicial.value = '';
			form.quadraInicial.disabled = true;

			form.quadraFinal.value = '';
			form.quadraFinal.disabled = true;

			//desabilita os campos abaixo
			habilitarDesabilitarRota();
		} else if (opcao == 'desabilita') {
			form.quadraInicial.value = '';
			form.quadraInicial.disabled = false;

			form.quadraFinal.value = '';
			form.quadraFinal.disabled = false;
		}
	}

	function habilitarDesabilitarRota(opcao) {
		var form = document.forms[0];
		if (form.quadraInicial.value != form.quadraFinal.value
				|| form.quadraInicial.disabled == true
				|| form.quadraInicial.value == ""
				|| form.quadraFinal.value == "") {

			form.rotaInicial.value = '';
			form.rotaInicial.disabled = true;

			form.rotaFinal.value = '';
			form.rotaFinal.disabled = true;

			form.rotaSequenciaInicial.value = '';
			form.rotaSequenciaInicial.disabled = true;

			form.rotaSequenciaFinal.value = '';
			form.rotaSequenciaFinal.disabled = true;

		} else {
			form.rotaInicial.value = '';
			form.rotaInicial.disabled = false;

			form.rotaFinal.value = '';
			form.rotaFinal.disabled = false;

			form.rotaSequenciaInicial.value = '';
			form.rotaSequenciaInicial.disabled = false;

			form.rotaSequenciaFinal.value = '';
			form.rotaSequenciaFinal.disabled = false;

		}
	}

</script>

<script type="text/javascript">
	$(document).ready(function() {

		$('.OSSituacao').change(function() {
			if ($('.OSSituacao:checked').val() == 'ENCERRADAS') {
				$('#tipoRelatorioMtvEnc').removeAttr("disabled");
			} else if ($('.OSSituacao:checked').val() == 'PENDENTES') {
				if ($('#tipoRelatorioMtvEnc:checked').val() == 4) {
					$('#tipoRelatorioAnalitico').attr('checked', 'true');
				}
				$('#tipoRelatorioMtvEnc').attr('disabled', 'true');
			}
		});
	});
</script>

</head>

<body leftmargin="5" topmargin="5" onload="validarInscricoes();">

	<html:form
		action="/gerarRelatorioAcompanhamentoOrdemServicoHidrometroAction"
		method="post"
		name="GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm"
		type="gcom.gui.atendimentopublico.ordemservico.GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm"
		onsubmit="validateGerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm(this);">


		<%@ include file="/jsp/util/cabecalho.jsp"%>
		<%@ include file="/jsp/util/menu.jsp"%>

		<table width="770" border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td width="115" valign="top" class="leftcoltext">
					<div align="center">
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>

						<%@ include file="/jsp/util/informacoes_usuario.jsp"%>

						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>

						<%@ include file="/jsp/util/mensagens.jsp"%>

						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
						<p align="left">&nbsp;</p>
					</div>
				</td>
				<td valign="top" class="centercoltext">
					<table height="100%">
						<tr>
							<td></td>
						</tr>
					</table>

					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
							<td class="parabg">Filtrar Relat�rio de Acompanhamento Ordem
								de Servi�o de Hidr&ocirc;metro</td>
							<td width="11"><img border="0"
								src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
						</tr>
					</table>
					<p>&nbsp;</p>
					<table width="100%" border="0">

						<tr>
							<td colspan="2">Para selecionar ordens de servi�o para
								gera��o do relat�rio de acompanhamento, informe os dados abaixo:</td>
						</tr>
						<tr>
							<td><strong>Tipo de Ordem:</strong></td>
							<td align="left">
								<table width="100%" border="0">
									<tr>
										<logic:present name="tipoOrdem">
											<td width="180" nowrap><html:radio value="INSTALACAO"
													property="tipoOrdem" tabindex="1"></html:radio>&nbsp;Instala&ccedil;&atilde;o
												de Hidr&ocirc;metro</td>
											<td align="left" nowrap><html:radio value="SUBSTITUICAO"
													property="tipoOrdem" tabindex="2"></html:radio>&nbsp;Substitui&ccedil;&atilde;o
												Hidr&ocirc;metro</td>
											<td align="left" nowrap><html:radio value="REMOCAO"
													property="tipoOrdem" tabindex="3"></html:radio>Remo��o
												Hidr&ocirc;metro</td>
										</logic:present>
										<logic:notPresent name="tipoOrdem">
											<td width="180" nowrap><input type="radio"
												name="tipoOrdem" value="INSTALACAO" checked="checked"
												tabindex="1">Instala&ccedil;&atilde;o de
												Hidr&ocirc;metro</td>
											<td align="left" nowrap><input type="radio"
												name="tipoOrdem" value="SUBSTITUICAO" tabindex="2">Substitui&ccedil;&atilde;o
												Hidr&ocirc;metro</td>
											<td align="left" nowrap><input type="radio"
												name="tipoOrdem" value="REMOCAO" tabindex="3">Remo��o
												Hidr&ocirc;metro</td>
										</logic:notPresent>
									</tr>
									<tr>
										<logic:present name="tipoOrdem">
											<td align="left" nowrap><html:radio
													value="INSPECAO DE ANORMALIDADE" property="tipoOrdem"
													tabindex="4"></html:radio>Inspe��o de Anormalidade</td>
											<td align="left" nowrap><html:radio
													value="SUBSTITUICAO COM REMOCAO" property="tipoOrdem"
													tabindex="5"></html:radio>Substitui��o com Remo��o</td>
										</logic:present>
										<logic:notPresent name="tipoOrdem">
											<td align="left" nowrap><input type="radio"
												name="tipoOrdem" value="INSPECAO DE ANORMALIDADE"
												tabindex="4">Inspe��o de Anormalidade</td>
											<td align="left" nowrap><input type="radio"
												name="tipoOrdem" value="SUBSTITUICAO COM REMOCAO"
												tabindex="5">Substitui��o com Remo��o</td>
										</logic:notPresent>
									</tr>
									<tr>
										<logic:present name="tipoOrdem">
											<td colspan="2" align="left" nowrap><html:radio
													value="INSTALACAO DA CAIXA DE PROTECAO"
													property="tipoOrdem" tabindex="6"></html:radio>Instala��o
												da Caixa de Prote��o</td>
										</logic:present>
										<logic:notPresent name="tipoOrdem">
											<td colspan="2" align="left" nowrap><input type="radio"
												name="tipoOrdem" value="INSTALACAO DA CAIXA DE PROTECAO"
												tabindex="6">Instala��o da Caixa de Prote��o</td>
										</logic:notPresent>
									</tr>
								</table>
							</td>
						</tr>

						<tr>
							<td height="10" colspan="3"><hr></td>
						</tr>

						<tr>
							<td><strong>Situa&ccedil;&atilde;o da Ordem:</strong></td>
							<td align="left">
								<table width="100%" border="0">
									<tr>
										<td width="180" nowrap><html:radio
												styleClass="OSSituacao" value="ENCERRADAS"
												property="situacaoOrdemServico"
												onclick="controleDataEncerramento();controleMotivoEncerramento();controleDataExecucao();controleTipoRelatorio();"
												tabindex="6"></html:radio>&nbsp;Encerradas</td>
										<td align="left" nowrap><html:radio
												styleClass="OSSituacao" value="PENDENTES"
												property="situacaoOrdemServico"
												onclick="controleDataEncerramento();controleMotivoEncerramento();controleDataExecucao();controleTipoRelatorio();"
												tabindex="7"></html:radio>&nbsp;Pendentes</td>
									</tr>
								</table>
							</td>
						</tr>

						<tr>
							<td><strong>Motivo Encerramento:&nbsp;</strong></td>
							<td align="right">
								<div align="left">
									<strong> <html:select property="idMotivoEncerramento"
											tabindex="8">
											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="colecaoMotivoEncerramento"
												property="id" labelProperty="descricao" />
										</html:select> <html:hidden property="descricaoMotivoEncerramento" />
									</strong>
								</div>
							</td>
						</tr>

						<tr>
							<td><strong>Firma:&nbsp;<font color="#FF0000">*</font></strong></td>
							<td align="right">
								<div align="left">
									<strong> <html:select property="firma" tabindex="9">
											<html:option value="-1">&nbsp;</html:option>
											<html:options collection="colecaoFirma" property="id"
												labelProperty="descricao" />
										</html:select> <html:hidden property="nomeFirma" />
									</strong>
								</div>
							</td>
						<tr>
							<td><strong>Ger�ncia Reginal:&nbsp;</strong></td>
							<td align="right">
								<div align="left">
									<strong> <html:select property="idGerenciaRegional"
											style="width: 200px;" tabindex="10">
											<html:option
												value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
											<html:options collection="colecaoGerencia"
												labelProperty="nome" property="id" />
										</html:select>
									</strong>
								</div>
							</td>
						</tr>
						<tr>
							<td><strong>Capacidade do Hidr�metro:</strong></td>
							<td align="right">
								<div align="left">
									<strong> <html:select property="hidrometroCapacidade"
											style="width: 200px; height:100px;" multiple="true"
											tabindex="11">

											<html:option
												value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>

											<logic:present name="colecaoHidrometroCapacidade"
												scope="session">
												<html:options collection="colecaoHidrometroCapacidade"
													labelProperty="descricao" property="id" />
											</logic:present>
										</html:select>
									</strong>
								</div>
							</td>
						</tr>


						<tr>
							<td colspan="2">

								<table width="100%" align="center" bgcolor="#90c7fc" border="0">
									<tr>
										<td><strong>Inscri��o Inicial</strong></td>
									</tr>
									<tr bgcolor="#cbe5fe">
										<td width="100%" align="center">

											<table width="100%" border="0">

												<tr>
													<td width="21%"><strong>Localidade:</strong></td>
													<td><html:text tabindex="12" maxlength="3"
															property="localidadeInicial" size="5"
															onkeypress="duplicarLocalidade(); validaEnter(event, 'exibirFiltrarAcompanhamentoOrdemServicoHidrometro.do?objetoConsulta=1&inscricaoTipo=origem', 'localidadeInicial');return isCampoNumerico(event);"
															onchange="duplicarLocalidade();validarLocalidadeInscricao('desabilita', true);"
															style=" width : 56px; height : 22px;" /> <a
														href="javascript:chamarPopup7param('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, document.forms[0].localidadeInicial); "
														id="btPesqLocalidadeInicial" tabindex="13"> <img
															width="23" height="21" border="0"
															src="<bean:message key="caminho.imagens"/>pesquisa.gif"
															title="Pesquisar" /></a> <logic:present
															name="corLocalidadeOrigem">
															<logic:equal name="corLocalidadeOrigem" value="exception">
																<html:text property="nomeLocalidadeInicial" size="35"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0; color: #ff0000" />
															</logic:equal>

															<logic:notEqual name="corLocalidadeOrigem"
																value="exception">
																<html:text property="nomeLocalidadeInicial" size="35"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0; color: #000000" />
															</logic:notEqual>
														</logic:present> <logic:notPresent name="corLocalidadeOrigem">
															<logic:empty
																name="GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm"
																property="localidadeInicial">
																<html:text property="nomeLocalidadeInicial" value=""
																	size="35" readonly="true"
																	style="background-color:#EFEFEF; border:0; color: #ff0000" />
															</logic:empty>

															<logic:notEmpty
																name="GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm"
																property="localidadeInicial">
																<html:text property="nomeLocalidadeInicial" size="35"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0; color: 	#000000" />
															</logic:notEmpty>
														</logic:notPresent> <a href="javascript:limparBorrachaOrigem(1);"
														tabindex="14"> <img
															src="<bean:message key="caminho.imagens"/>limparcampo.gif"
															border="0" title="Apagar" /></a></td>
												</tr>
												<tr>
													<td><strong>Setor Comercial:</strong></td>
													<td><html:text tabindex="15" maxlength="3"
															property="codigoSetorComercialInicial" size="5"
															onkeypress="duplicarSetorComercial(); validaEnterDependencia(event, 'exibirFiltrarAcompanhamentoOrdemServicoHidrometro.do?action=exibirFiltrarAcompanhamentoOrdemServicoHidrometro&objetoConsulta=2&inscricaoTipo=origem', document.forms[0].codigoSetorComercialInicial, document.forms[0].localidadeInicial.value, 'Localidade Inicial.'); return isCampoNumerico(event);"
															onchange="duplicarSetorComercial();validarSetorInscricao('desabilita');"
															style=" width : 56px; height : 22px;" /> <a
														href="javascript:habilitarDesabilitarLupaSetor(1);"
														tabindex="16" id="btPesqSetorComercialInicial"> <img
															width="23" height="21" border="0"
															src="<bean:message key="caminho.imagens"/>pesquisa.gif"
															title="Pesquisar" /></a> <logic:present
															name="corSetorComercialOrigem">
															<logic:equal name="corSetorComercialOrigem"
																value="exception">
																<html:text property="nomeSetorComercialInicial"
																	size="35" readonly="true"
																	style="background-color:#EFEFEF; border:0; color: #ff0000" />
															</logic:equal>

															<logic:notEqual name="corSetorComercialOrigem"
																value="exception">
																<html:text property="nomeSetorComercialInicial"
																	size="35" readonly="true"
																	style="background-color:#EFEFEF; border:0; color: #000000" />
															</logic:notEqual>

														</logic:present> <logic:notPresent name="corSetorComercialOrigem">
															<logic:empty
																name="GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm"
																property="codigoSetorComercialInicial">
																<html:text property="nomeSetorComercialInicial" value=""
																	size="35" readonly="true"
																	style="background-color:#EFEFEF; border:0; color: #ff0000" />
															</logic:empty>
															<logic:notEmpty
																name="GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm"
																property="codigoSetorComercialInicial">
																<html:text property="nomeSetorComercialInicial"
																	size="35" readonly="true"
																	style="background-color:#EFEFEF; border:0; color: #000000" />
															</logic:notEmpty>
														</logic:notPresent> <a href="javascript:limparBorrachaOrigem(2);"
														tabindex="17"> <img
															src="<bean:message key="caminho.imagens"/>limparcampo.gif"
															border="0" title="Apagar" />
													</a> <html:hidden property="setorComercialInicial" /></td>
												</tr>
												<tr>
													<td><strong>Quadra:</strong></td>
													<td colspan="3"><html:text tabindex="18" maxlength="4"
															property="quadraInicial" size="5"
															onkeypress="duplicarQuadra(); validaEnterDependencia(event, 'exibirFiltrarAcompanhamentoOrdemServicoHidrometro.do?action=exibirFiltrarAcompanhamentoOrdemServicoHidrometro&objetoConsulta=3&inscricaoTipo=origem', document.forms[0].quadraInicial, document.forms[0].codigoSetorComercialInicial.value, 'setorComercialInicial');return isCampoNumerico(event);"
															onchange="duplicarQuadra();validarQuadraInscricao('desabilita');" />


														<logic:present name="corQuadraOrigem" scope="request">
															<span style="color: #ff0000" id="msgQuadraInicial">
																<bean:write scope="request" name="msgQuadraInicial" />
															</span>
														</logic:present> <logic:notPresent name="corQuadraOrigem" scope="request"></logic:notPresent>

														<html:hidden property="idQuadraInicial" /></td>
												</tr>

												<tr>
													<td><strong>Rota inicial:</strong></td>
													<td align="left"><html:text property="rotaInicial"
															tabindex="19" size="5" maxlength="4" disabled="true"
															onkeypress="duplicarRota(); return isCampoNumerico(event);"
															onblur="duplicarRota();">
														</html:text> seq.: <html:text property="rotaSequenciaInicial"
															tabindex="20" size="7" maxlength="6" disabled="true"
															onkeypress="duplicarSequenciaRota(); return isCampoNumerico(event);"
															onblur="duplicarSequenciaRota();"></html:text></td>
												</tr>
											</table>

										</td>
									</tr>
								</table>

							</td>
						</tr>

						<tr>
							<td colspan="2">

								<table width="100%" align="center" bgcolor="#90c7fc" border="0">
									<tr>
										<td><strong>Inscri��o Final</strong></td>
									</tr>
									<tr bgcolor="#cbe5fe">
										<td width="100%" align="center">

											<table width="100%" border="0">
												<tr>
													<td width="21%"><strong>Localidade:</strong></td>
													<td><html:text maxlength="3"
															property="localidadeFinal" size="5"
															onkeypress="validaEnter(event, 'exibirFiltrarAcompanhamentoOrdemServicoHidrometro.do?objetoConsulta=1&inscricaoTipo=destino', 'localidadeFinal');return isCampoNumerico(event);"
															tabindex="21"
															onchange="validarLocalidadeInscricao('desabilita', false);"
															style=" width : 56px; height : 22px;" /> <a
														href="javascript:chamarPopup7param('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, ''); limparDestino(1); validarLocalidadeInscricao('desabilita', false); "
														id="btPesqLocalidadeFinal" tabindex="22"> <img
															width="23" height="21" border="0"
															src="<bean:message key="caminho.imagens"/>pesquisa.gif"
															title="Pesquisar" /></a> <logic:present
															name="corLocalidadeDestino">
															<logic:equal name="corLocalidadeDestino"
																value="exception">
																<html:text property="nomeLocalidadeFinal" size="35"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0; color: #ff0000" />
															</logic:equal>

															<logic:notEqual name="corLocalidadeDestino"
																value="exception">
																<html:text property="nomeLocalidadeFinal" size="35"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0; color: #000000" />
															</logic:notEqual>
														</logic:present> <logic:notPresent name="corLocalidadeDestino">
															<logic:empty
																name="GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm"
																property="localidadeFinal">
																<html:text property="nomeLocalidadeFinal" value=""
																	size="35" readonly="true"
																	style="background-color:#EFEFEF; border:0; color: #ff0000" />
															</logic:empty>
															<logic:notEmpty
																name="GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm"
																property="localidadeFinal">
																<html:text property="nomeLocalidadeFinal" size="35"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0; color: 	#000000" />
															</logic:notEmpty>
														</logic:notPresent> <a href="javascript:limparBorrachaDestino(1);"
														tabindex="23"> <img
															src="<bean:message key="caminho.imagens"/>limparcampo.gif"
															border="0" title="Apagar" />
													</a></td>
												</tr>
												<tr>
													<td><strong>Setor Comercial :</strong></td>
													<td><html:text maxlength="3"
															property="codigoSetorComercialFinal" size="5"
															onkeypress=" validaEnterDependencia(event, 'exibirFiltrarAcompanhamentoOrdemServicoHidrometro.do?action=exibirFiltrarAcompanhamentoOrdemServicoHidrometro&objetoConsulta=2&inscricaoTipo=destino', document.forms[0].codigoSetorComercialFinal, document.forms[0].localidadeFinal.value, 'Localidade Final.'); return isCampoNumerico(event);"
															tabindex="24"
															onchange="validarSetorInscricao('desabilita');"
															style=" width : 56px; height : 22px;" /> <a
														href="javascript:habilitarDesabilitarLupaSetor(2); validarSetorInscricao('desabilita');"
														tabindex="25" id="btPesqSetorComercialFinal"> <img
															width="23" height="21" border="0"
															src="<bean:message key="caminho.imagens"/>pesquisa.gif"
															title="Pesquisar" /></a> <logic:present
															name="corSetorComercialDestino">
															<logic:equal name="corSetorComercialDestino"
																value="exception">
																<html:text property="nomeSetorComercialFinal" size="35"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0; color: #ff0000" />
															</logic:equal>

															<logic:notEqual name="corSetorComercialDestino"
																value="exception">
																<html:text property="nomeSetorComercialFinal" size="35"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0; color: #000000" />
															</logic:notEqual>
														</logic:present> <logic:notPresent name="corSetorComercialDestino">
															<logic:empty
																name="GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm"
																property="codigoSetorComercialFinal">
																<html:text property="nomeSetorComercialFinal" value=""
																	size="35" readonly="true"
																	style="background-color:#EFEFEF; border:0; color: #ff0000" />
															</logic:empty>
															<logic:notEmpty
																name="GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm"
																property="codigoSetorComercialFinal">
																<html:text property="nomeSetorComercialFinal" size="35"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0; color: #000000" />
															</logic:notEmpty>
														</logic:notPresent> <a href="javascript:limparBorrachaDestino(2);"
														tabindex="26"> <img
															src="<bean:message key="caminho.imagens"/>limparcampo.gif"
															border="0" title="Apagar" /></a> <html:hidden
															property="setorComercialFinal" /></td>
												</tr>
												<tr>
													<td><strong>Quadra:</strong></td>
													<td><html:text maxlength="4" property="quadraFinal"
															size="5"
															onkeypress=" validaEnterDependencia(event, 'exibirFiltrarAcompanhamentoOrdemServicoHidrometro.do?action=exibirFiltrarAcompanhamentoOrdemServicoHidrometro&objetoConsulta=3&inscricaoTipo=destino', document.forms[0].quadraFinal, document.forms[0].codigoSetorComercialFinal.value, 'setorComercialFinal');return isCampoNumerico(event);"
															onchange="validarQuadraInscricao('desabilita');"
															tabindex="27" /> <logic:present name="corQuadraDestino"
															scope="request">
															<span style="color: #ff0000" id="msgQuadraFinal">
																<bean:write scope="request" name="msgQuadraFinal" />
															</span>
														</logic:present> <logic:notPresent name="corQuadraDestino" scope="request"></logic:notPresent>

														<html:hidden property="idQuadraFinal" /></td>
												</tr>

												<tr>
													<td><strong>Rota Final:</strong></td>
													<td align="left"><html:text property="rotaFinal"
															tabindex="28" size="5" maxlength="4" disabled="true"
															onkeypress="return isCampoNumerico(event);">
														</html:text> seq.: <html:text property="rotaSequenciaFinal"
															tabindex="29" size="7" maxlength="6" disabled="true"
															onkeypress="return isCampoNumerico(event);">
														</html:text></td>
												</tr>
											</table>

										</td>
									</tr>
								</table>
							</td>
						</tr>

						<tr>
							<td><strong>Per&iacute;odo de Encerramento:</strong></td>
							<td colspan="6"><span class="style2"> <strong>
										<html:text property="dataEncerramentoInicial" size="9"
											maxlength="10" tabindex="30"
											onkeyup="mascaraData(this, event);replicaDataEncerramento();"
											onkeypress="return isCampoNumerico(event);" /> <a
										id="calendario1" tabindex="31"
										href="javascript:abrirCalendarioReplicando('GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm', 'dataEncerramentoInicial','dataEncerramentoFinal');">
											<img border="0"
											src="<bean:message key='caminho.imagens'/>calendario.gif"
											width="16" height="15" border="0" title="Exibir Calend�rio" />
									</a> a <html:text property="dataEncerramentoFinal" size="9"
											maxlength="10" tabindex="32"
											onkeyup="mascaraData(this, event)"
											onkeypress="return isCampoNumerico(event);" /> <a
										id="calendario2" tabindex="33"
										href="javascript:abrirCalendario('GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm', 'dataEncerramentoFinal');">
											<img border="0"
											src="<bean:message key='caminho.imagens'/>calendario.gif"
											width="16" height="15" border="0" title="Exibir Calend�rio" />
									</a>
								</strong>(dd/mm/aaaa)<strong> </strong>
							</span></td>
						</tr>

						<tr>
							<td><strong>Per&iacute;odo de
									Execu&ccedil;&atilde;o:</strong></td>
							<td colspan="6"><span class="style2"> <strong>
										<html:text property="dataExecucaoInicial" size="9"
											maxlength="10" tabindex="34"
											onkeyup="mascaraData(this, event);replicaDataExecucao();"
											onkeypress="return isCampoNumerico(event);" /> <a
										id="calendario3" tabindex="35"
										href="javascript:abrirCalendarioReplicando('GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm', 'dataExecucaoInicial','dataExecucaoFinal');">
											<img border="0"
											src="<bean:message key='caminho.imagens'/>calendario.gif"
											width="16" height="15" border="0" title="Exibir Calend�rio" />
									</a> a <html:text property="dataExecucaoFinal" size="9"
											maxlength="10" tabindex="36"
											onkeyup="mascaraData(this, event)"
											onkeypress="return isCampoNumerico(event);" /> <a
										id="calendario4" tabindex="37"
										href="javascript:abrirCalendario('GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm', 'dataExecucaoFinal');">
											<img border="0"
											src="<bean:message key='caminho.imagens'/>calendario.gif"
											width="16" height="15" border="0" title="Exibir Calend�rio" />
									</a>
								</strong>(dd/mm/aaaa)<strong> </strong>
							</span></td>
						</tr>

						<tr>
							<td height="10" colspan="3"><hr></td>
						</tr>

						<tr>
							<td><strong>Tipo do Relat&oacute;rio:</strong></td>
							<td align="left">
								<table width="100%" border="0">
									<tr>
										<td><html:radio value="1" tabindex="38"
												styleId="tipoRelatorioAnalitico"
												property="tipoRelatorioAcompanhamento"></html:radio>&nbsp;Anal&iacute;tico</td>
										<td align="left"><html:radio tabindex="39" value="2"
												property="tipoRelatorioAcompanhamento"></html:radio>&nbsp;Sint&eacute;tico</td>
										<td align="left"><html:radio tabindex="40" value="3"
												property="tipoRelatorioAcompanhamento"></html:radio>&nbsp;Por
											Local de Instala&ccedil;&atilde;o</td>
										<td align="left"><html:radio tabindex="41"
												styleId="tipoRelatorioMtvEnc" value="4"
												property="tipoRelatorioAcompanhamento"></html:radio>&nbsp;Por
											Motivo de Encerramento</td>
									</tr>
								</table>
							</td>
						</tr>

						<tr>
							<td height="10" colspan="3"><hr></td>
						</tr>

						<tr>
							<td align="left" colspan="2"><strong><font
									color="#FF0000">*</font></strong>Campos obrigat&oacute;rios</td>
						</tr>
						<tr>
							<td align="left" style="padding-left: 0px; padding-right: 0px">
								<input type="button" name="Button" tabindex="44"
								class="bottonRightCol" value="Cancelar"
								onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>
							<td><input type="button" class="bottonRightCol"
								value="Limpar" tabindex="43"
								onclick="window.location.href='/gsan/exibirFiltrarAcompanhamentoOrdemServicoHidrometro.do?menu=sim';">

								<span style="padding-left: 370px; padding-right: 0px"> <input
									type="button" name="Button" tabindex="42"
									class="bottonRightCol" value="Filtrar"
									onclick="javascript:validarForm()">
							</span></td>

						</tr>
					</table>
					<p>&nbsp;</p>
				</td>
			</tr>
		</table>
		<jsp:include
			page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioAcompanhamentoOrdemServicoHidrometroAction.do" />
		<%@ include file="/jsp/util/rodape.jsp"%>


	</html:form>

	<script>
		controleDataEncerramento();
		controleMotivoEncerramento();
		controleDataExecucao();
		controleTipoRelatorio();
	</script>
</body>
</html:html>

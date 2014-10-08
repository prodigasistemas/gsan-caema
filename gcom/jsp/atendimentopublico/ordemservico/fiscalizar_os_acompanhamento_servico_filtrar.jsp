<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.atendimentopublico.ordemservico.OSProgramacaoAcompanhamentoServico"%>
<%@ page import="gcom.atendimentopublico.ordemservico.bean.PesquisarFiscalizarOSEncerradaAcompanhamentoHelper"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<link rel="stylesheet" type="text/css" href="<bean:message key="caminho.css"/>jquery.lightbox-0.5.css" media="screen" />

<%@ page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.atendimentopublico.ordemservico.OrdemServico"%>
		<script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarFiscalizarOSAcompanhamentoServicoActionForm"/>

		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>
		
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>lightbox/jquery.lightbox-0.5.js"></script>
<style type="text/css">
			/* jQuery lightBox plugin - Gallery style */
			#gallery {
				background-color: #90C7FC;
				padding: 10px;
				width: 520px;
			}
			#gallery ul { list-style: none; }
			#gallery ul li { display: inline; }
			#gallery ul img {
				border: 5px solid #CBE5FE;
				border-width: 5px 5px 20px;
			}
			#gallery ul a:hover img {
				border: 5px solid #fff;
				border-width: 5px 5px 20px;
				color: #fff;
			}
			#gallery ul a:hover { color: #fff; }
		</style>
<script language="JavaScript">


	function validarForm(){		
   		var form = document.forms[0];   		
   	   	
   		if(form.numeroOS.value == '' && form.matriculaImovel.value ==  '' && form.idLocalidade.value ==  ''  &&
	   		   		form.periodoAtendimentoInicial.value =='' && form.periodoGeracaoInicial.value =='' &&
	   		   		form.periodoEncerramentoInicial.value =='' && form.codigoBairro.value  == '' &&
	   		   		form.municipio.value =='' && form.logradouro.value=='' && form.idUnidade.value == '')   		{
			alert("Informe pelo menos uma opção de seleção");
   			
	   	}else if((form.codigoBairro.value  != '' ||
   		   		form.municipio.value !='' || form.logradouro.value!='') && 
	   		   	(form.periodoAtendimentoInicial.value =='' && form.periodoGeracaoInicial.value =='' &&
   		   		form.periodoEncerramentoInicial.value =='')){
		   		
	   		alert("É obrigatório pelo menos o preenchimento de uma das datas.")
	   		
  		 }else{
		   	
	   		if(validateFiltrarFiscalizarOSAcompanhamentoServicoActionForm(form)){
		   		if(validaTodosPeriodos()){
			   		form.action = 'filtrarOSEncerradaAcompanhamentoServicoAction.do';
				    form.submit();
		   		}
	   		}
	   	}
    }

	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado,nomeDependencia,valorDependencia)
	{
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo + "&"+ nomeDependencia + "=" + valorDependencia, altura, largura);
			}
			else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}
				else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo + "&" + nomeDependencia + "=" + valorDependencia, altura, largura);
				}
			}
		}
	}
	
   function concluir(){
 		var form = document.forms[0];
 		var selecionados = form.checks;
 		var motivo = 1;
		if ($('[id=checks]:checked').size() == 0) {
			alert("É necessário marcar algum Registro de Atendimento.");
			return false;
		}
		
		var qnt = 0;
		
		
		for (i=0; i< selecionados.length; i++) {
			if(selecionados[i].checked == true){
				if(selecionados[i].value=='2'){
					motivo = 2;
				}
				if(selecionados[i].value=='3'){
					motivo = 3;
					qnt = qnt+1;					
				}
			}
		}
		if(motivo == 1){
  			form.action = 'atualizarFiscalizarOSAcompanhamentoServicoAction.do?metodo=concluir';  
  			form.submit();
   		}else if(motivo == 2){
   			abrirPopup('fiscalizarOSAcompanhamentoServicoMotivoPopupAction.do?inicio=sim', 275, 480);	
   	   	} else if(motivo == 3){
   	   		if (qnt>1){
   	   			qnt = 0;
   	   		} else {
   	   			qnt = form.idOS.value;
   	   		}		
   	   		abrirPopup('fiscalizarOSAcompanhamentoServicoSimRessalvaPopupAction.do?os='+qnt, 275, 480);	
   	   	}
	  
   }
    function validaTodosPeriodos() {

		var form = document.forms[0];
    	if (comparaData(form.periodoAtendimentoInicial.value, '>', form.periodoAtendimentoFinal.value)){

			alert('Data Final do Período de Atendimento é anterior à Data Inicial do Período de Atendimento');
			return false;

		} else if (comparaData(form.periodoGeracaoInicial.value, '>', form.periodoGeracaoFinal.value)){

			alert('Data Final do Período da Geração é anterior à Data Inicial do Período da Geração');
			return false;

		} else if (comparaData(form.periodoEncerramentoInicial.value, '>', form.periodoEncerramentoFinal.value)){

			alert('Data Final do Período de Encerramento é anterior à Data Inicial do Período de Encerramento');
			return false;

		} else if(form.codigoBairro.value != '' && form.municipio.value == '')   { 

			alert('Informe o Município');
			return false;
		}


		return true;
    }

    function abreFotos(idRA){
    	var action = 'filtrarOSEncerradaAcompanhamentoServicoAction.do?metodo=visualizarFotos&idRA=' + idRA;
		
		$('#result').load(action, function(response, status, xhr) {
			if (status == "error") {
			    alert("Não existem fotos cadastradas para a Ordem de Serviço selecionada.");
		    } else {
				$('#gallery a').lightBox();
				$('#gallery a:first').click();
				window.scroll(0,0);
		    }
		});
    }

	function limparForm(){

		var form = document.forms[0];

		form.idLocalidade.value="";
		form.nomeLocalidade.value="";
		form.numeroOS.value="";
		form.periodoAtendimentoInicial.value="";
		form.periodoAtendimentoFinal.value="";
		form.periodoGeracaoInicial.value="";
		form.periodoGeracaoFinal.value="";
		form.idUnidade.value = "";
		form.nomeUnidade.value = "";

		limparImovel();
		limparPeriodoEncerramento();
		limparMunicipio();
		limparBairro();
		limparLogradouro();
				
		form.areaBairro.selectedIndex = 0;
		form.matriculaImovel.disabled = false;		
		form.matriculaImovel.readOnly = false;

		form.action = 'exibirFiltrarFiscalizarOSAcompanhamentoServicoAction.do?menu=sim&limpar=sim';
	    form.submit();
		
	}
	
	function limparOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
		
		case 1: //localidade 
			form.nomeLocalidade.value = "";
			break;
		  	
		case 2: //imovel
			form.inscricaoImovel.value = "";
			break;
		
		case 3: //municipio 
			form.descricaoMunicipio.value = "";
			form.descricaoBairro.value = "";
			form.codigoBairro.value = "";
			break;
			
		case 4: //bairro
			form.descricaoBairro.value = "";
			break;
			
		case 5: //logradouro
			form.descricaoLogradouro.value = "";
			break;
			
		}
	}
	function limparImovel() {

    	var form = document.forms[0];

    	form.matriculaImovel.value = "";
    	form.inscricaoImovel.value = "";
  	}
	function limparLocalidade() {
		var form = document.forms[0];
	     form.idLocalidade.value = '';
	     form.nomeLocalidade.value = '';
	}
	
    function limparMunicipio() {
        var form = document.forms[0];

        form.municipio.value = "";
    	form.descricaoMunicipio.value = "";
    	limparBairro();
    	
    }

    function limparBairro() {
        var form = document.forms[0];

        form.codigoBairro.value = "";
    	form.descricaoBairro.value = "";
    	limparAreaBairro();
    }
    
    function limparLogradouro() {
        var form = document.forms[0];

        form.logradouro.value = "";
    	form.descricaoLogradouro.value = "";
    }

    function limparPeriodoEncerramento() {

        var form = document.forms[0];
        
		form.periodoEncerramentoInicial.disabled = false;
		form.periodoEncerramentoFinal.disabled = false;
		
		form.periodoEncerramentoInicial.value="";
		form.periodoEncerramentoFinal.value="";
  	}

    function limparPeriodoGeracao() {
        var form = document.forms[0];
        
        if (form.periodoGeracaoInicial.value != ''){
			form.periodoGeracaoInicial.value="";
		}
		
		if (form.periodoGeracaoFinal.value != ''){
			form.periodoGeracaoFinal.value="";
		}			
  	}
    
    
	//Replica Data de Atendimento
	function replicaDataAtendimento() {
		var form = document.forms[0];
		form.periodoAtendimentoFinal.value = form.periodoAtendimentoInicial.value;
	}

	//Replica Data de Geracao
	function replicaDataGeracao() {
		var form = document.forms[0];
		form.periodoGeracaoFinal.value = form.periodoGeracaoInicial.value;
	}
    
	//Replica Data de Encerramento
	function replicaDataEncerramento() {
		var form = document.forms[0];
		form.periodoEncerramentoFinal.value = form.periodoEncerramentoInicial.value;
	}

	function validaForm(){
		var form = document.forms[0];
				
		if(form.numeroOS.value != null && form.numeroOS.value != ''){

	    	limparImovel();
    		form.matriculaImovel.disabled = true;		
		
		}
		
	}
	
	/* Chama Popup */ 
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}
	var unidade = 0;
	
	function setUnidade(tipo) {
		unidade = tipo;
	}

	// Esconder/mostrar tabela com a coleção
	function extendeTabelaArquivos(display){
		var form = document.forms[0];
	
		if(display){
		  	eval('layerHideDadosArquivos').style.display = 'none';
				eval('layerShowDadosArquivos').style.display = 'block';
		}else{
		  	eval('layerHideDadosArquivos').style.display = 'block';
				eval('layerShowDadosArquivos').style.display = 'none';
		}
	}
	
	function verificaTabela(achou){
		 if (achou == '2'){
		  	eval('layerHideDadosArquivos').style.display = 'block';
			eval('layerShowDadosArquivos').style.display = 'none';
		 }else if (achou == '1'){
			eval('layerHideDadosArquivos').style.display = 'none';
			eval('layerShowDadosArquivos').style.display = 'block';
		 }
	}
	
	/* Recuperar Popup */
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0]; 
	    
	   if (tipoConsulta == 'imovel') {

	      form.matriculaImovel.value = codigoRegistro;
	      form.inscricaoImovel.value = descricaoRegistro;
	      form.inscricaoImovel.style.color = "#000000";
	      form.matriculaImovel.focus();
	    
	   } else if (tipoConsulta == 'localidade') {
		      form.idLocalidade.value = codigoRegistro;
			  form.nomeLocalidade.value = descricaoRegistro;
			  form.nomeLocalidade.style.color = "#000000";
		 	}
	   else if (tipoConsulta == 'municipio') {
		    form.municipio.value = codigoRegistro;
		    form.descricaoMunicipio.value = descricaoRegistro;
   			form.descricaoMunicipio.style.color = "#000000";
	    
	    } else if (tipoConsulta == 'bairro') {
		    form.codigoBairro.value = codigoRegistro;
		    form.descricaoBairro.value = descricaoRegistro;
   			form.descricaoBairro.style.color = "#000000";
	    
	    } else if (tipoConsulta == 'logradouro') {
  	      	form.logradouro.value = codigoRegistro;
	      	form.descricaoLogradouro.value = descricaoRegistro;
   			form.descricaoLogradouro.style.color = "#000000";
	    }else if (tipoConsulta == 'motivo') {
		    form.observacao.value = descricaoRegistro;
		    form.idMotivoNaoAceite.value = codigoRegistro;
			form.action = 'atualizarFiscalizarOSAcompanhamentoServicoAction.do?metodo=concluir';
			form.submit();
		} else if (tipoConsulta == 'ressalva') {
			form.idTipoServico.value = codigoRegistro;
			form.action = '/gsan/atualizarFiscalizarOSAcompanhamentoServicoAction.do?metodo=ressalva';
			form.submit();
		}else if (tipoConsulta == 'unidadeOrganizacional') {
       
			form.idUnidade.value = codigoRegistro;
			form.nomeUnidade.value = descricaoRegistro;
			form.nomeUnidade.style.color = "#000000";
        
		}

	   if((!form.numeroOS.disabled) && (tipoConsulta != 'motivo' && tipoConsulta != 'ressalva')){
		      reload();
	   }
	}
	
	
	function reload() {
		var form = document.forms[0];
		form.action = "/gsan/exibirFiltrarFiscalizarOSAcompanhamentoServicoAction.do";
		form.submit();
	}
	
	//So chama a função abrirCalendario caso o campo esteja habilitado
	function chamarCalendario(fieldNameOrigem,objetoRelacionado,fieldNameDestino){
		
		if(objetoRelacionado.disabled != true){
			if(fieldNameDestino != ''){
				abrirCalendarioReplicando('FiltrarFiscalizarOSAcompanhamentoServicoActionForm', fieldNameOrigem,fieldNameDestino);
			}else{
				abrirCalendario('FiltrarFiscalizarOSAcompanhamentoServicoActionForm', fieldNameOrigem);
			}
		}
	}
	
	
	function MoveSelectedDataFromMenu1TO2(form, object, selectedObject){
		var form = document.forms[0];
		
		if (form.tipoServico.value != '-1') {
			MoverDadosSelectMenu1PARAMenu2(form, object, selectedObject);
		}
	}
	
	function MoveSelectedDataFromMenu2TO1(form, object, selectedObject){
		var form = document.forms[0];
		
		if (form.tipoServico.value != '-1') {
			MoverDadosSelectMenu2PARAMenu1(form, object, selectedObject);
		}
	}

	/* Clear Área Bairro */
	function limparAreaBairro() {
		var form = document.forms[0];
		for(i=form.areaBairro.length-1; i>0; i--) {
			form.areaBairro.options[i] = null;
		}
	}
	
	function validarBairro(){
		var form = document.forms[0];
		if(form.municipio.value == '')   { 
			alert('Informe o Município');
		}else{
			abrirPopup('exibirPesquisarBairroAction.do?idMunicipio='+document.forms[0].municipio.value+'&indicadorUsoTodos=1', 400, 800);
		}
	}
	
    function limparPeriodos() {
        var form = document.forms[0];
        
        if (form.periodoAtendimentoInicial.value != ''){
			form.periodoAtendimentoInicial.value="";
		}
		if (form.periodoAtendimentoFinal.value != ''){
			form.periodoAtendimentoFinal.value="";
		}			

        if (form.periodoGeracaoInicial.value != ''){
			form.periodoGeracaoInicial.value="";
		}
		
		if (form.periodoGeracaoFinal.value != ''){
			form.periodoGeracaoFinal.value="";
		}		
        if (form.periodoEncerramentoInicial.value != ''){
			form.periodoEncerramentoInicial.value="";
		}
		if (form.periodoEncerramentoFinal.value != ''){
			form.periodoEncerramentoFinal.value="";
		}			
  	}

    function limparPesquisaRA() {

    	var form = document.forms[0];

    	limparImovel();
    	
  		form.numeroOS.disabled = false;
    	
  	}
    

    function numeroOSInformado() {
        var form = document.forms[0];
	
		if(form.numeroOS.value != null && form.numeroOS.value != ''){

			limparPesquisaRA();
   	    	form.matriculaImovel.readOnly = true;	
		}else{		
			form.matriculaImovel.readOnly = false;
			form.matriculaImovel.disabled = false;
		}
	}
	
	
	function habilitaPeriodoAtendimento(){
	  	var form = document.forms[0];
	
		form.periodoAtendimentoInicial.readOnly = false;
		form.periodoAtendimentoFinal.readOnly = false;
	}
	
	function desabilitaPeriodoAtendimento(){
	  	var form = document.forms[0];
	
		form.periodoAtendimentoInicial.value = "";
    	form.periodoAtendimentoFinal.value = "";
//		form.periodoAtendimentoInicial.disabled = true;
	//	form.periodoAtendimentoFinal.disabled = true;
		form.periodoAtendimentoInicial.readOnly = true;
		form.periodoAtendimentoFinal.readOnly= true;

	}
	
	function habilitaAreaBairro(){
	  	var form = document.forms[0];
	
		form.areaBairro.readOnly = false;
	}
	
	function desabilitaAreaBairro(){
	  	var form = document.forms[0];
	  	
		form.areaBairro.selectedIndex = 0;
		form.areaBairro.readOnly = true;
	}
	
	function origemOrdemServicoInformado(){
	 	var form = document.forms[0];
	 	
		if (form.origemOrdemServico[0].checked){
			habilitaRA();
			desabilitaDocumentoCobranca();
			habilitaPeriodoAtendimento();
			habilitaAreaBairro();
		}else if (form.origemOrdemServico[1].checked){
			desabilitaRA();
			habilitaDocumentoCobranca();
			desabilitaPeriodoAtendimento();
			desabilitaAreaBairro();
		}else if (form.origemOrdemServico[2].checked){
			desabilitaRA();
			desabilitaDocumentoCobranca();
			desabilitaPeriodoAtendimento();
			desabilitaAreaBairro();
		}else if (form.origemOrdemServico[3].checked){
			habilitaRA();
			habilitaDocumentoCobranca();
			desabilitaPeriodoAtendimento();
			desabilitaAreaBairro();
		}else{
			form.origemOrdemServico[0].checked = true;
			habilitaRA();
			desabilitaDocumentoCobranca();
			habilitaPeriodoAtendimento();
			habilitaAreaBairro();
		}
	}

	function verificaOs(){
		var form = document.forms[0];
		if(form.matriculaImovel.readOnly == false){
			chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '', form.matriculaImovel);
		}
	}
	
	//Detecta mudança nos radios e verifica marcação
	$(document).ready(function(){
		$("input[type=radio]").change(function(){
			verificarMarcacao();
		});			
	});
	
	//bloqueia o campo NAO se o campo SIM COM RESSALVA for marcado 
	//e vice-versa
	function verificarMarcacao(){
		var form = document.forms[0];
 		var selecionados = form.checks;
 		
 		desabilitarRadios(1);
 		
		for (i=0; i< selecionados.length; i++) {
			if(selecionados[i].checked == true){
				if(selecionados[i].value=='2'){
					desabilitarRadios(3);	
					break;
				} else if(selecionados[i].value=='3'){
					desabilitarRadios(2);
					break;
				}			
			}
		}
   }

	function setIdOS(numero){
		var form = document.forms[0];
		form.idOS.value = numero;
	}
	
	function desabilitarRadios(numero){
		if (numero==2){
			//Desabilita NAO
			$(".nao").attr({disabled: true});
			$(".ressalva").attr({disabled: false});			
		} else if (numero==3){
			//Desabilita RESSALVA
			$(".nao").attr({disabled: false});
			$(".ressalva").attr({disabled: true});
		} else { //HABILITA TODOS
			$(".nao").attr({disabled: false});
			$(".ressalva").attr({disabled: false});
		}
	}
	
	
	function limparUnidadeTecla() {
		
		var form = document.forms[0];
	
		form.nomeUnidade.value = "";
	
	}
	
	function limparUnidade() {
		
		var form = document.forms[0];
	
		form.idUnidade.value = "";
		form.nomeUnidade.value = "";
	
	}
	
	
	
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="validaForm();javascript:setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">
<html:form action="/filtrarOSEncerradaAcompanhamentoServicoAction"
	name="FiltrarFiscalizarOSAcompanhamentoServicoActionForm"
	type="gcom.gui.atendimentopublico.ordemservico.FiltrarFiscalizarOSAcompanhamentoServicoActionForm"
	method="post">

	<html:hidden property="idOS"/>
	<html:hidden property="idTipoServico"/>
	
<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	  <tr>
	    <td width="130" valign="top" class="leftcoltext">
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
			<td width="600" valign="top" class="centercoltext">
		        <table height="100%">
			        <tr>
			          <td></td>
			        </tr>
		      	</table>

			<html:hidden property="idMotivoNaoAceite"/>
			<html:hidden property="observacao"/>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Fiscalizar OS Acompanhamento Servi&ccedil;o</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Preencha os campos para filtrar registros de atendimento</td>
				</tr>
				
				
              	<tr> 
                	<td><strong>N&uacute;mero da OS:</strong></td>
                	<td colspan="6">
                		<strong>
                		    <!-- limparPeriodoGeracao() -->
                  			<html:text property="numeroOS" size="9" maxlength="9" 
                  						onkeyup="limparPeriodos();numeroOSInformado();"
                  						onkeypress="return isCampoNumerico(event);"/>
                  		</strong>
                  	</td>
              	</tr>

           		<tr>
             		<td height="10" colspan="3"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>

				<tr>
					<td><strong>Matr&iacute;cula do Im&oacute;vel:</strong></td>
					
					<td>
						
						<html:text maxlength="9" 
							tabindex="1"
							property="matriculaImovel" 
							size="9"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarFiscalizarOSAcompanhamentoServicoAction.do?objetoConsulta=3','matriculaImovel','Matricula Imóvel');return isCampoNumerico(event);"
							onkeyup="limparPeriodoGeracao();limparOrigem(2);"
							/>
							
							<a href="javascript:verificaOs();">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Matricula Imóvel" /></a> 

							<logic:present name="matriculaImovelEncontrada" scope="request">
								<html:text property="inscricaoImovel" 
									size="30"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present> 

							<logic:notPresent name="matriculaImovelEncontrada" scope="request">
								<html:text property="inscricaoImovel" 
									size="30"
									maxlength="30" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
							</logic:notPresent>

							
							<a href="javascript:limparImovel();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" /></a>
						</td>
				</tr>
				
				

           		<tr>
             		<td height="10" colspan="3"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>

           		<tr>
					<td width="15%">
						<strong>
							Localidade:
						</strong>
					</td>					
					<td><strong> <html:text property="idLocalidade" size="5"
								maxlength="3"
								onkeyup="validaEnterComMensagem(event, 'exibirFiltrarFiscalizarOSAcompanhamentoServicoAction.do?objetoConsulta=2', 'idLocalidade', 'Localidade');limparOrigem(1)"
								onchange="controleEloLocalidade(this.value);" 
								onkeypress="return isCampoNumerico(event);"/> <a
								href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'localidade', null, null, 400, 800, '',document.forms[0].idLocalidade,'idLocalidade',document.forms[0].idLocalidade.value);">
							<img border="0" src="imagens/pesquisa.gif" height="21" width="23" title="Pesquisar Localidade"></a>
							<logic:present name="localidadeEncontrada">
								
									<html:text property="nomeLocalidade" readonly="true"
									style="background-color:#EFEFEF; border:0" size="40" 
									maxlength="40" />
							</logic:present> <logic:notPresent name="localidadeEncontrada"
								>
								<html:text property="nomeLocalidade" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									size="40" maxlength="40" />
							</logic:notPresent> <a href="javascript:limparLocalidade();"> <img
								src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar" /></a> </strong></td>
				</tr>
				
				<tr>
             		<td height="10" colspan="3"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>
				           		
              	<tr>
	                <td>
	                	<strong>Per&iacute;odo de Atendimento:</strong>
	                </td>
                
	                <td colspan="6">
	                	<span class="style2">
	                	
	                	<strong> 
							
							<html:text property="periodoAtendimentoInicial" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event);replicaDataAtendimento();"
								onkeypress="return isCampoNumerico(event);"/>
							
							<a href="javascript:abrirCalendarioReplicando('FiltrarFiscalizarOSAcompanhamentoServicoActionForm', 'periodoAtendimentoInicial','periodoAtendimentoFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" alt="Exibir Calendário" 
									tabindex="4"/></a>
							a 
							
							<html:text property="periodoAtendimentoFinal" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event)"
								onkeypress="return isCampoNumerico(event);"/>
								
							<a href="javascript:abrirCalendario('FiltrarFiscalizarOSAcompanhamentoServicoActionForm', 'periodoAtendimentoFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calendário" 
									tabindex="4"/></a>
							
							</strong>(dd/mm/aaaa)<strong> 
	                  	</strong>
	                  	</span>
	              	</td>
              	</tr>

              	<tr>
	                <td>
	                	<strong>Per&iacute;odo de Gera&ccedil;&atilde;o:</strong>
	                </td>
                
	                <td colspan="6">
	                	<span class="style2">
	                	
	                	<strong> 
							
							<html:text property="periodoGeracaoInicial" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event);replicaDataGeracao();"
								onkeypress="return isCampoNumerico(event);"/>
							
							<a href="javascript:abrirCalendarioReplicando('FiltrarFiscalizarOSAcompanhamentoServicoActionForm', 'periodoGeracaoInicial','periodoGeracaoFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calendário" 
									tabindex="4"/></a>
							a 
							
							<html:text property="periodoGeracaoFinal" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event)"
								onkeypress="return isCampoNumerico(event);"/>
								
							<a href="javascript:abrirCalendario('FiltrarFiscalizarOSAcompanhamentoServicoActionForm', 'periodoGeracaoFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calendário" 
									tabindex="4"/></a>
							
							</strong>(dd/mm/aaaa)<strong> 
	                  	</strong>
	                  	</span>
	              	</td>
              	</tr>
              	<tr>
	                <td>
	                	<strong>Per&iacute;odo de Encerramento:</strong>
	                </td>
                
	                <td colspan="6">
	                	<span class="style2">
	                	
	                	<strong> 
							
							<html:text property="periodoEncerramentoInicial" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event);replicaDataEncerramento();"
								onkeypress="return isCampoNumerico(event);"/>
							
							<a href="javascript:chamarCalendario('periodoEncerramentoInicial',document.forms[0].periodoEncerramentoInicial,'periodoEncerramentoFinal');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calendário" 
									tabindex="4"/></a>
							a 
							
							<html:text property="periodoEncerramentoFinal" 
								size="11" 
								maxlength="10" 
								tabindex="3" 
								onkeyup="mascaraData(this, event)"
								onkeypress="return isCampoNumerico(event);"/>
							
							<a href="javascript:chamarCalendario('periodoEncerramentoFinal',document.forms[0].periodoEncerramentoFinal,'');">
								<img border="0" 
									src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									alt="Exibir Calendário" 
									tabindex="4"/></a>
							
							</strong>(dd/mm/aaaa)<strong> 
	                  	</strong>
	                  	</span>
	              	</td>
              	</tr>

           		<tr>
             		<td height="10" colspan="3"> 
	             		<div align="right"> 
	                 		<hr>
	               		</div>
	               		<div align="right"> </div>
               		</td>
           		</tr>

				<tr>
					<td><strong>Munic&iacute;pio:</strong></td>
					<td>
						<html:text maxlength="4" 
							tabindex="1"
							property="municipio" 
							size="4"
							onkeyup="limparOrigem(3)"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarFiscalizarOSAcompanhamentoServicoAction.do?objetoConsulta=8','municipio','Município');return isCampoNumerico(event);"
							/> 
							
							<a href="javascript:chamarPopup('exibirPesquisarMunicipioAction.do', 'municipio', null, null, 275, 480, '', document.forms[0].municipio);">

								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Município" /></a> 

						<logic:present name="municipioEncontrada" scope="request">
							<html:text property="descricaoMunicipio" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="municipioEncontrada" scope="request">
							<html:text property="descricaoMunicipio" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						<a href="javascript:limparMunicipio();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					
					</td>
				</tr>

				<tr>
					<td><strong>Bairro:</strong></td>
					<td>
						<html:text maxlength="4" 
							tabindex="1"
							property="codigoBairro" 
							size="4"
							onkeyup="limparOrigem(4)"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarFiscalizarOSAcompanhamentoServicoAction.do?objetoConsulta=9','codigoBairro','Bairro');return isCampoNumerico(event);"
							/> 
							
						<a href="javascript:validarBairro();">
							<img width="23" 
								height="21" 
								border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Bairro" /></a> 

						<logic:present name="bairroEncontrada" scope="request">
							<html:text property="descricaoBairro" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="bairroEncontrada" scope="request">
							<html:text property="descricaoBairro" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						<a href="javascript:limparBairro();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					
					</td>
				</tr>
		        
				<tr>
					<td>
						<strong>&Aacute;rea do Bairro:</strong>
					</td>

					<td>
						<strong> 
						<html:select property="areaBairro" style="width: 230px;">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>
					
							<logic:present name="colecaoAreaBairro" scope="request">
								<html:options collection="colecaoAreaBairro"
									labelProperty="nome" 
									property="id" />
							</logic:present>
						</html:select> 														
						</strong>
					</td>
				</tr>

				<tr>
					<td><strong>Logradouro:</strong></td>
					<td>
						<html:text maxlength="9" 
							tabindex="1"
							property="logradouro" 
							size="9"
							onkeyup="limparOrigem(5)"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarFiscalizarOSAcompanhamentoServicoAction.do?objetoConsulta=10','logradouro','Logradouro'); return isCampoNumerico(event);"
							/> 
							
							<a href="javascript:abrirPopup('exibirPesquisarLogradouroAction.do?codigoMunicipio='+document.forms[0].municipio.value+'&codigoBairro='+document.forms[0].codigoBairro.value+'&indicadorUsoTodos=1&primeriaVez=1', 275, 480);">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Logradouro" /></a> 

						<logic:present name="logradouroEncontrado" scope="request">
							<html:text property="descricaoLogradouro" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="logradouroEncontrado" scope="request">
							<html:text property="descricaoLogradouro" 
								size="40"
								maxlength="40" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						<a href="javascript:limparLogradouro();">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>

					</td>
				</tr>				
				
				<tr>
					<td width="130"><strong>Unidade Atual:</strong></td>
					<td colspan="2"><html:text maxlength="4" property="idUnidade"
						tabindex="1" size="4" onkeyup="javascript:limparUnidadeTecla();"
						onkeypress="javascript:validaEnterComMensagem(event, 'exibirFiltrarFiscalizarOSAcompanhamentoServicoAction.do?objetoConsulta=11' , 'idUnidade', 'Unidade Organizacional'); return isCampoNumerico(event);" />																						
					<a href="javascript:abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do');">
					<img width="23" height="21" border="0"
						src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						title="Pesquisar Unidade Organizacional" /></a> <logic:present
						name="idUnidadeNaoEncontrado" scope="request">
						<html:text maxlength="30" property="nomeUnidade" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #ff0000"
							size="40" />
					</logic:present> <logic:notPresent name="idUnidadeNaoEncontrado"
						scope="request">
						<html:text maxlength="30" property="nomeUnidade" readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="40" />
					</logic:notPresent> <a href="javascript:limparUnidade();"> <img
						src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						border="0" title="Apagar" /></a></td>
				</tr>		
				
				
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
				<td height="24" >
					<input type="button" onclick="javascript:limparForm();" value="Limpar" class="bottonRightCol">
					
				</td>
				<td height="24" >
							<input name="Submit23" class="bottonRightCol" value="Cancelar"
					type="button"
					onclick="window.location.href='/gsan/telaPrincipal.do'"> 		
					</td>
				<td align="right">
					<input type="button" onclick="javascript:validarForm();" value="Selecionar" class="bottonRightCol" name="Button">
											
				</td>
				</tr>
				<logic:present name="colecaoFiscalizarOSAcompanhamento">
					<table width="100%" align="center" bgcolor="#90c7fc" border="0" cellpadding="0" cellspacing="0">
						<tr bgcolor="#99CCFF">
	                   		<td align="center">
	           					<span class="style2">
	             					<a href="javascript:extendeTabelaArquivos(false);" tabindex="12"/>
	             						<b>Dados</b>
	             					</a>
	           					</span>
	               			</td>
	              		</tr>
	              		<tr bgcolor="#cbe5fe" >
	              			<td width="100%" align="center">
								<div style="height:400px; overflow: auto;">
									<table width="100%" bgcolor="#99CCFF">
										<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
											<td width="10%" bgcolor="#90c7fc">
											<div align="center"><strong>Registro Atendimento</strong></div>
											</td>
											<td width="10%" bgcolor="#90c7fc">
												<div align="center"><strong>OS</strong></div>
											</td>
											<td width="15%" bgcolor="#90c7fc">
											<div align="center"><strong>Endereço</strong></div>
											</td>
											<td colspan="2" width="10%" bgcolor="#90c7fc">
												<div align="center"><strong>Confirma</strong></div>
												<div align="center">
													<span style="margin-right:20px">Sim</span>
													<span style="margin-right:20px">Sim com Ressalva</span>
													<span>Não</span>
												</div>
											</td>
												
										</tr>
										
										<logic:present name="colecaoFiscalizarOSAcompanhamento">
											<%int cont = 0;%>
											<logic:iterate name="colecaoFiscalizarOSAcompanhamento" 
												id="fiscalizarOSAcompanhamento" type="PesquisarFiscalizarOSEncerradaAcompanhamentoHelper">
												<%cont = cont + 1;
												if (cont % 2 == 0) {%>
													<tr bgcolor="#cbe5fe" class="styleFontePequena" >
														<%} else {%>
													<tr bgcolor="#FFFFFF" class="styleFontePequena">
														<%}%>
														<!-- <pg:item>  -->
														<td width="10%">
															<div align="center">
															
																<a href="javascript:abrirPopup('exibirConsultarDadosOrdemServicoPopupAction.do?numeroOS=<%=fiscalizarOSAcompanhamento.getIdOsAs()%>&acompanhamento=sim',480,650);"')"><%=fiscalizarOSAcompanhamento.getIdRA()%></a>
																
															</div>
														</td>
														
														<td width="10%" align="center">	
								                     		<span class="style2">
								                      			<b><%=fiscalizarOSAcompanhamento.getIdOsAs()%></b>
								                     		</span>
														</td>
														
														<td width="15%" align="center">	
								                     		<span class="style2">
								                      			<b><%=fiscalizarOSAcompanhamento.getDsEndereco()%></b>
								                     		</span>
														</td>
														<td width="20%">
															<div align="center">
																<span style="margin-right:60px">
																	<input type="radio" id='checks' name="idFiscalizarOSAcompanhamento<bean:write name="fiscalizarOSAcompanhamento" property="idOsAs"/>" 
																	value="1" /></span>
																<span style="margin-right:60px">
																	<input type="radio" onchange="javascript:setIdOS(<%=fiscalizarOSAcompanhamento.getIdOsAs()%>)" class="ressalva" id='checks' name="idFiscalizarOSAcompanhamento<bean:write name="fiscalizarOSAcompanhamento" property="idOsAs"/>" 
																	value="3" /></span>
																<span><input type="radio" class="nao" id='checks' name="idFiscalizarOSAcompanhamento<bean:write name="fiscalizarOSAcompanhamento" property="idOsAs"/>"
																	value="2" /></span>
																			
															</div>
														</td>
													</tr>
												</logic:iterate>
											</logic:present>
											
													
											</td>
										</table>
										
									</div>
									
								</td>
								
							</tr>
							
							<td align="right">
									<input type="button" 
										name="Button" 
										class="bottonRightCol" 
										value="Concluir" 
										onclick="javascript:concluir();"/>
										
							</td>
						
						</table>
									
					</logic:present>
				
				
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<div id="result" style="display:none;">
		</div>
<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form>
</div>
</body>

<%@ include file="/jsp/util/telaespera.jsp"%>

</html:html>

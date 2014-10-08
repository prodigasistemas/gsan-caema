<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.ConstantesSistema" %>
<%@ page import="gcom.cadastro.sistemaparametro.SistemaParametro"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<html:javascript staticJavascript="false"  formName="EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm"/>

<script language="JavaScript">
	function limparCamposPesquisa(objetoConsulta){
		var form = document.forms[0];

		switch(objetoConsulta){
		//Imovel
		case 1:
			limparFormularioPesquisaImovel();
			break;
		//Cadastrador
		case 2:
			form.nomeCadastrador.value = "";
		  	break;
		//Fone Tipo
		case 3:
			form.descricaoFoneTipoClienteUsuario.value = "";
		  	break;
		//Endereco Referencia
		case 4:
			form.descricaoReferencia.value = "";
		  	break;
		//Pavimento Rua
		case 5:
			form.descricaoPavimentoRua.value = "";
		  	break;
		//Pavimento Calcada
		case 6:
			form.descricaoPavimentoCalcada.value = "";
		  	break;

		//Fonte de Abastecimento
		case 7:
			form.descricaoFonteAbastecimento.value = "";
		  	break;
		//Ligacao de Agua
		case 8:
			form.descricaoSituacaoAgua.value = "";
		  	break;
		//Ligacao de Esgoto
		case 9:
			form.descricaoSituacaoEsgoto.value = "";
		  	break;

		//Hidrometro
		case 10:

			form.marca.value = "";
			form.capacidade.value = "";
			form.anoFabricacao.value = ""

			form.localInstalacao.value = "";
			form.descricaoLocalInstalacao.value = ""

			form.protecao.value = "";
			form.descricaoProtecao.value = "";

			form.cavalete.value = "";
				
		  	break;

		//Local de Instalacao
		case 11:
			form.descricaoLocalInstalacao.value = "";
		  	break;
		//Protecao
		case 12:
			form.descricaoProtecao.value = "";
		  	break;
		//Ocorrencia Cadastro
		case 13:
			form.descricaoOcorrenciaCadastro.value = "";
		  	break;
		//Perfil Imovel
		case 14:
			form.descricaoPerfil.value = "";
			break;  	
		}
	}
	
	function desabilitar(){
		document.onhelp = function() { return (false); }
		window.onhelp = function() { return (false); }
	}

	function enter(tecla,nomeCampo){
		var form = document.forms[0];
		
		if (document.all) {
			var codigo = event.keyCode;
	    } else {
	   		var codigo = tecla.which;
	    }

        if(codigo == 13){
			
			var objetoCampo = eval("form." + nomeCampo);
        	objetoCampo.focus();
        	//document.getElementById(idinput).focus();     
            return false;           
        }
	}
	
	function ajuda(event, nomeCampo){
		var form = document.forms[0];
		
		if(document.all) {
			var codigo = event.keyCode;
		} else {
			var codigo = event.which;
		}
		
		var objetoCampo = eval("form." + nomeCampo);
		if(codigo == 112){
			var title = document.getElementById("texto" + nomeCampo).getAttribute("title");
			showToolTip(event, title, nomeCampo);
			return false; 
		}else{
			//objetoCampo.title = ""; 
			
			hideToolTip();
			
			return false;
		}
	}
	
	function showToolTip(e,text, nomeCampo){
		var obj = document.getElementById('bubble_tooltip');
		var obj2 = document.getElementById('bubble_tooltip_content');
		var texto = text.split(';');
		
		obj2.innerHTML="";
		for(i=0; i<texto.length; i++){
			obj2.innerHTML+=texto[i]+"<br/>";
		}

		//obj2.innerHTML = text;
		obj.style.display = 'block';
		var st = Math.max(document.body.scrollTop,document.documentElement.scrollTop);
		if(navigator.userAgent.toLowerCase().indexOf('safari')>=0)st=0; 
		//var rightPos = e.clientX - 100;
		
		if(navigator.appName.indexOf('Internet Explorer')>=0 ||
			navigator.userAgent.toLowerCase().indexOf('Internet Explorer')>=0){
			obj.style.width = '170px';
		}
		
		if(nomeCampo == 'foneTipoClienteUsuario'){
			var rightPos = 730;
			var topPos = 340;
		}else if(nomeCampo == 'perfil'){
			var rightPos = 730;
			var topPos = 350;
		}else if(nomeCampo == 'referencia'){
			var rightPos = 730;
			var topPos = 380;
		}else if(nomeCampo == 'pavimentoRua'){
			var rightPos = 730;
			var topPos = 520;
		}else if(nomeCampo == 'pavimentoCalcada'){
			var rightPos = 730;
			var topPos = 480;
		}else if(nomeCampo == 'fonteAbastecimento'){
			var rightPos = 730;
			var topPos = 590;
		}else if(nomeCampo == 'situacaoAgua'){
			var rightPos = 730;
			var topPos = 650;
		}else if(nomeCampo == 'situacaoEsgoto'){
			var rightPos = 740;
			var topPos = 640;
		}else if(nomeCampo == 'localInstalacao'){
			var rightPos = 730;
			var topPos = 720;
		}else if(nomeCampo == 'protecao'){
			var rightPos = 700;
			var topPos = 700;
		}else{
			var rightPos = 730;
			var topPos = 680;
		}
		//if(rightPos<0)rightPos = 0;
		//obj.style.top = e.clientY - obj.offsetHeight - 1 + st + 'px';
		
		obj.style.right = rightPos + 'px';
		obj.style.top = topPos +'px';
	}	

	function hideToolTip()
	{
		document.getElementById('bubble_tooltip').style.display = 'none';
	}
	
	function mostrarCep(){
		var form = document.forms[0];
		
		form.action = "exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovelAction.do?objetoConsulta=14";
		form.submit();
	}
	
	function habilitarPerfilImovel(){
		var form = document.forms[0];
		
		if(form.matriculaImovel.value == ""){
			form.perfil.disabled = false;
		}else{
			form.perfil.disabled = true;	
		}
		
	}
	
	function verificarCampoPerfilImovel(tecla){
		var form = document.forms[0];
		
		if(form.matriculaImovel.value == ""){
			enter(tecla, 'perfil');
		}else{
			enter(tecla, 'analisarTarifaSocial');
		}
	}
	
	function validarHidrometro(){
		var form = document.forms[0];
		
		if(form.indicadorHidrometro.value == "1"){
			if(form.numeroHidrometro.value == ""){
				alert ('Informe Número do Hidrômetro');
				form.numeroHidrometro.focus();
				return false;
			}
			if(form.localInstalacao.value == ""){
				alert ('Informe Local Instalação');
				form.localInstalacao.focus();
				return false;
			}
			if(form.protecao.value == ""){
				alert('Informe Tipo Proteção');
				form.protecao.focus();
				return false;
			}
			if(form.cavalete.value == ""){
				alert ('Informe Cavalete');
				form.cavalete.focus();
				return false;
			}
			if(form.ocorrenciaCadastro.value == ""){
				alert ('Informe Ocorrência Cadastro');
				form.ocorrenciaCadastro.focus();
				return false;
			}
		}else if(form.indicadorHidrometro.value == "2"){
			return true;
		}
		return true;
	}
	
	function validarIndicadores(){
		var form = document.forms[0];
		
		if(form.sexoClienteUsuario.value != ""){
			if(form.sexoClienteUsuario.value != "1" && form.sexoClienteUsuario.value != "2"){
				alert ('Sexo Cliente Usuário inválido');
				form.sextoClienteUsuario.focus();
				return false;
			}
		}
		
		if(form.analisarTarifaSocial.value != ""){
			if(form.analisarTarifaSocial.value != "1" && form.analisarTarifaSocial.value != "2"){
				alert ('Analisar Tarifa Social inválido');
				form.analisarTarifaSocial.focus();
				return false;
			}
		}
		
		if(form.indicadorHidrometro.value != ""){
			if(form.indicadorHidrometro.value != "1" && form.indicadorHidrometro.value != "2"){
				alert ('Indicado Hidrômetro inválido');
				form.indicadorHidrometro.focus();
				return false;
			}
		}
		
		if(form.cavalete.value != ""){
			if(form.cavalete.value != "1" && form.cavalete.value != "2"){
				alert ('Indicador Cavalete inválido');
				form.cavalete.focus();
				return false;
			}
		}
		
		return true;
	}
	
	function validarSubcategorias(){
		var form = document.forms[0];
		
		if(form.subCategoria2.value != "" && form.numeroEconomias2.value == ""){
			alert ('Informe Numero Economias 2');
			form.numeroEconomias2.focus();
			return false;
		}
		
		if(form.subCategoria3.value != "" && form.numeroEconomias3.value == ""){
			alert ('Informe Numero Economias 3');
			form.numeroEconomias3.focus();
			return false;
		}
		
		if(form.subCategoria4.value != "" && form.numeroEconomias4.value == ""){
			alert ('Informe Numero Economias 4');
			form.numeroEconomias4.focus();
			return false;
		}
		
		if(form.subCategoria5.value != "" && form.numeroEconomias5.value == ""){
			alert ('Informe Numero Economias 5');
			form.numeroEconomias5.focus();
			return false;
		}
		
		if(form.subCategoria6.value != "" && form.numeroEconomias6.value == ""){
			alert ('Informe Numero Economias 6');
			form.numeroEconomias6.focus();
			return false;
		}
		
		return true;
	}

	function validarTelefone(){
		var form = document.forms[0];
		
		if(form.foneClienteUsuario.value != "" && form.foneTipoClienteUsuario.value == ""){
			alert ('Informe Tipo de Telefone');
			form.foneTipoClienteUsuario.focus();
			return false;
		}

		if( (form.foneTipoClienteUsuario.value != "" || form.dddFoneClienteUsuario.value != "") && 
			form.foneClienteUsuario.value == ""){
			alert ('Informe Número de Telefone');
			form.foneClienteUsuario.focus();
			return false;
		}

		if( (form.foneClienteUsuario.value != "" || form.foneTipoClienteUsuario.value != "") && 
			form.dddFoneClienteUsuario.value == "" ) {
			alert ('Informe DDD do Telefone');
			form.dddFoneClienteUsuario.focus();
			return false;
		}

		
		return true;
	}
	
	
	function limparForm(){
		var form = document.forms[0];
		
		form.matriculaImovel.value = "";
		
		limparCamposPesquisa(1);
		
		habilitarPerfilImovel();
	}

	function limparFormularioPesquisaImovel(){

		var form = document.forms[0];

		form.cadastrador.value = ""
		form.nomeCadastrador.value = "";
		form.dataAtualizacao.value = "";
			
		//Dados do cliente proprietário
		form.documentoClienteProprietario.value = "";
		form.nomeClienteProprietario.value = "";
		form.sexoClienteProprietario.value = "";
		form.rgClienteProprietario.value = "";
		form.ufClienteProprietario.value = "";

		form.dddFoneClienteProprietario.value = "";
		form.foneClienteProprietario.value = "";
		form.foneTipoClienteProprietario.value = "";
		form.descricaoFoneTipoClienteProprietario.value = "";
		
		form.enderecoClienteProprietario.value = "";
		form.complementoClienteProprietario.value = "";
		form.bairroClienteProprietario.value = "";
		form.municipioClienteProprietario.value = "";
		form.cepClienteProprietario.value = "";
			
		//Dados do cliente usuário
		form.documentoClienteUsuario.value = "";
		form.nomeClienteUsuario.value = "";
		form.sexoClienteUsuario.value = "";
		form.rgClienteUsuario.value = "";
		form.ufClienteUsuario.value = "";

		form.dddFoneClienteUsuario.value = "";
		form.foneClienteUsuario.value = "";
		form.foneTipoClienteUsuario.value = "";
		form.descricaoFoneTipoClienteUsuario.value = "";

		
		//Dados do imóvel
		form.lote.value = "";
		form.subLote.value = "";
		form.perfil.value = "5";
		form.descricaoPerfil.value = "NORMAL";
		form.analisarTarifaSocial.value = "";
		form.logradouro.value = "-1";
		form.cep.value = "-1";
		form.referencia.value = "";
		form.descricaoReferencia.value = "";
		form.numero.value = "";
		form.complemento.value = "";

		form.subCategoria1.value = "";
		form.subCategoria2.value = "";
		form.subCategoria3.value = "";
		form.subCategoria4.value = "";
		form.subCategoria5.value = "";
		form.subCategoria6.value = "";
			
			
		form.numeroEconomias1.value = "";
		form.numeroEconomias2.value = "";
		form.numeroEconomias3.value = "";
		form.numeroEconomias4.value = "";
		form.numeroEconomias5.value = "";
		form.numeroEconomias6.value = "";
			
		form.numeroMoradores.value = "";
		form.medidorCelpe.value = "";
		form.pavimentoRua.value = "";
		form.descricaoPavimentoRua.value = "";
		form.pavimentoCalcada.value = "";
		form.descricaoPavimentoCalcada.value = "";
			
		form.fonteAbastecimento.value = "";
		form.descricaoFonteAbastecimento.value = "";
			
		//Dados da ligação
		form.situacaoAgua.value = "";
		form.descricaoSituacaoAgua.value = "";
			
		form.situacaoEsgoto.value = "";
		form.descricaoSituacaoEsgoto.value = "";
			
		form.indicadorHidrometro.value = "";
		form.numeroHidrometro.value = "";
		form.marca.value = "";
		form.capacidade.value = "";
		form.anoFabricacao.value = ""
		form.localInstalacao.value = "";
		form.descricaoLocalInstalacao.value = "";
			
		form.protecao.value = "";
		form.descricaoProtecao.value = "";
			
		form.cavalete.value = "";
		form.ocorrenciaCadastro.value = "";
		form.descricaoOcorrenciaCadastro.value = "";
			
		form.leitura.value = "";
		form.criterioTarifaSocial.value = "";
		form.observacao.value = "";
		form.confimacaoClienteUsuarioDesconhecido.value = null;
	}
	
	function voltar(){
		var form = document.forms[0];
		form.matriculaNovoImovel.value = null;
		form.action = "exibirEfetuarDigitacaoDadosAtualizacaoCadastralAction.do";
		form.submit();
	}
	
	function finalizarLote(){
		var form = document.forms[0];
		var msg = "A quantidade de documentos digitados é superior a quantidade de documentos. Deseja finalizar o lote?";
		
		if(form.quantidadeDocumentosIncluidos.value != "" && form.quantidadeDocumentos.value != ""){
			if(parseInt(form.quantidadeDocumentosIncluidos.value) > parseInt(form.quantidadeDocumentos.value)){
				if(confirm(msg)){
					form.action = "exibirEfetuarDigitacaoDadosAtualizacaoCadastralAction.do?finalizar=sim";
					form.submit();
				}				
			}else{
				form.action = "exibirEfetuarDigitacaoDadosAtualizacaoCadastralAction.do?finalizar=sim";
				form.submit();
			}
		}
	}
	
	function inserirImovel(){
		var form = document.forms[0];
		
		if(validateEfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm(form)){
			if(validarHidrometro() && validarIndicadores() && validarSubcategorias() && validarTelefone() ){
				form.action = 'efetuarDigitacaoDadosAtualizacaoCadastralImovelAction.do';
				form.submit();
			}
		}
	}
	function novoImovel(){
		var form = document.forms[0];

		if ( form.matriculaNovoImovel.value != null && form.matriculaNovoImovel.value != '') {
			alert("Movimento atualizado com sucesso - matrícula gerada para o novo imóvel " + form.matriculaNovoImovel.value);
		}
		form.matriculaNovoImovel.value = '';
		
	}
	
	function recuperarDadosQuatroParametros(idCadastrador, nomeCadastrador, cpfCadastrador, tipoConsulta) {

	    var form = document.forms[0];

		if (tipoConsulta == 'leiturista') {
	    	form.cadastrador.value = cpfCadastrador;
	    	form.nomeCadastrador.value = nomeCadastrador;
	    	form.nomeCadastrador.style.color = "#000000";
	    } 
    }
	
	function limparCadastrador() {
		var form = document.forms[0];
	
    	form.cadastrador.value = "";
    	form.nomeCadastrador.value = "";
    	form.cadastrador.focus();
	}	
		
	
	function pesquisarCadastrador(){
	  var form = document.forms[0];
	  
	  abrirPopup('exibirPesquisarLeituristaAction.do?retornarCPF=sim', 200, 400 );
	}

</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');habilitarPerfilImovel();desabilitar();novoImovel();">

<html:form action="/exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovelAction"
	name="EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm"
	type="gcom.gui.cadastro.atualizacaocadastral.EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	
	<html:hidden property="idMunicipio"/>
	<html:hidden property="bairro"/>
	<html:hidden property="matriculaNovoImovel"/>
	<html:hidden property="confimacaoClienteUsuarioDesconhecido"/>

	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
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
			
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
				
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
				
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
						<td class="parabg">Efetuar Digitação de Dados para Atualização Cadastral</td>
						<td width="11" valign="top">
							<img border="0" src="imagens/parahead_right.gif" />
						</td>
					</tr>
				</table>
				
				<p>&nbsp;</p>
				
				<table width="100%" border="0">
					<tr>
						<td colspan="6">Para efetuar a digitação de dados para atualização cadastral,informe os dados abaixo:</td>
					</tr>
					
					<tr>
						<td><strong>Matrícula:</strong></td>
						
						<td>
							<html:text maxlength="9" 
								tabindex="1"
								property="matriculaImovel" 
								size="9"
								onkeyup="habilitarPerfilImovel();limparCamposPesquisa(1);enter(event, 'dataAtualizacao');"
								onkeypress="javascript:limparCamposPesquisa(1);habilitarPerfilImovel();validaEnter(event, 'exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovelAction.do?objetoConsulta=1','matriculaImovel');return isCampoNumerico(event);"/>
								
							<strong>Doc. Digitados:</strong>
							
							<html:text
								property="quantidadeDocumentosIncluidos"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="4" 
								maxlength="4" /> /							
							
							<html:text
								property="quantidadeDocumentos"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="4" 
								maxlength="4" />							
								
						</td>
						
						<td align="left">
							
							<input name="Button" 
								type="button" 
								class="bottonRightCol" 
								value="Finalizar Lote" 
								onclick="javascript:finalizarLote();">
							
						</td>
						
						<logic:present name="documentosExcedidos" scope="request">
							<td>
								<strong style="color: #ff0000">Qtd Doc Excedidos</strong>
							</td>
						</logic:present>
						
					</tr>
					
					<tr>
						<td><strong>CPF Cadastrador:<font color="#FF0000">*</font></strong></td>
						
						<td>
							<!--<html:text maxlength="9" 
								tabindex="2"
								property="cadastrador" 
								size="9"
								onkeyup="javascript:limparCamposPesquisa(2);"
								onkeypress="javascript:limparCamposPesquisa(2);validaEnterComMensagem(event, 'exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovelAction.do?objetoConsulta=2','cadastrador','Cadastrador');return isCampoNumerico(event);"/>
				
							<html:text maxlength="9" 
								tabindex="2"
								property="cadastrador"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="9"/>
														
							<logic:notPresent name="cadastradorInexistente" scope="session">	
								<html:text
									property="nomeCadastrador"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									size="30" 
									maxlength="30" />							
							</logic:notPresent>
							<logic:present name="cadastradorInexistente" scope="session" >
								<html:text
									property="nomeCadastrador"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									size="30" 
									maxlength="30" />
							</logic:present>
							-->

							<html:text maxlength="11" property="cadastrador"
								tabindex="2" size="10"
								onkeypress="javascript:validaEnterComMensagemAceitaZERO(event, 'exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovelAction.do?objetoConsulta=2', 'cadastrador', 'Cadastrador');" />
							<a href="javascript:pesquisarCadastrador();">
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Cadastrador" /></a> 
							<logic:present name="cadastradorInexistente" scope="session">
								<html:text maxlength="30" property="nomeCadastrador"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #ff0000"
									size="30" />
							</logic:present> 
							<logic:notPresent name="cadastradorInexistente" scope="session">
								<html:text maxlength="30" property="nomeCadastrador"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									size="35" />
							</logic:notPresent> 
							<a href="javascript:limparCadastrador();"><img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar"/></a>
					</tr>
					<tr>
						<td><strong>Data Atualização:<font color="#FF0000">*</font></strong></td>
						
						<td colspan="3">
							<html:text property="dataAtualizacao" 
								size="10" 
								maxlength="10" 
								tabindex="3" 
								onkeypress="return isCampoNumerico(event);"
								onkeyup="mascaraData(this, event);enter(event,'documentoClienteUsuario');"/>
							<a href="javascript:abrirCalendario('EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm', 'dataAtualizacao');" tabindex="5">
								<img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" 
									width="16" 
									height="15" 
									border="0" 
									title="Exibir Calendário" /></a>
							<strong>dd/mm/aaaa</strong>
						</td>
					</tr>
										
					<tr>
						<table width="100%" border="0" bordercolor="#79bbfd">
							<tr>
								<td colspan="6" align="center" bgcolor="#79bbfd">
									<strong>Dados do Cliente Proprietário</strong>
								</td>
							</tr>
							
							<tr>
			
								<td><strong>CPF/CNPJ:</strong></td>
			
								<td>
									<html:text
										property="documentoClienteProprietario"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="20" maxlength="20" />
								</td>
								

							</tr>
							
							
							<tr>
								<td><strong>Cliente:</strong></td>
			
								<td>
									<html:text
										property="nomeClienteProprietario"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="50" maxlength="100" />
								</td>
								
								
							</tr>
							
							<tr>
								<td><strong>RG:</strong></td>
								
								<td colspan="4">
									<html:text
										property="rgClienteProprietario"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="12" maxlength="12" />
										
									<strong>UF:</strong>
									
									<html:text
										property="ufClienteProprietario"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="2" maxlength="2" />
										
									<strong>Sexo:</strong>
									
									<html:text
										property="sexoClienteProprietario"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="2" 
										maxlength="2" />
								
									<strong>1-Masc. 2-Fem.</strong>
										
								</td>
							</tr>
							<tr>
									<td><strong>Fone/Tipo:</strong></td>
									
									<td colspan="3">

										<html:text
											property="dddFoneClienteProprietario"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="2" 
											maxlength="2" /> -
											
										<html:text
											property="foneClienteProprietario"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="9" 
											maxlength="9" /> 

										<html:text
											property="foneTipoClienteProprietario"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="2" 
											maxlength="2" /> 
											
										<html:text
											property="descricaoFoneTipoClienteProprietario"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="20" 
											maxlength="20" />
									</td>
							
							</tr>
							<tr>
								<td><strong>Endereço:</strong></td>
			
								<td colspan="5">
									<html:text
										property="enderecoClienteProprietario"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="70" maxlength="70" />
							</tr>
							<tr>
								<td><strong>Complemento:</strong></td>

								<td colspan="5">
									<html:text
										property="complementoClienteProprietario"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="25" maxlength="25" />
										
									<strong>Bairro:</strong>
			
									<html:text
										property="bairroClienteProprietario"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="30" maxlength="30" />
										
										
								</td>
							</tr>
							
							<tr>
								<td><strong>Municipio:</strong></td>
			
								<td colspan="4">
								
									<html:text
										property="municipioClienteProprietario"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="30" 
										maxlength="30" />
									
									<strong>CEP:</strong>
								
								
									<html:text
										property="cepClienteProprietario"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="10" maxlength="10" />
										
										
								</td>
							</tr>

							
							<tr>
								<td colspan="6" align="center" bgcolor="#79bbfd">
									<strong>Dados do Cliente Usuário</strong>
								</td>
							</tr>
							
							<tr>
			
								<td><strong>CPF/CNPJ:</strong></td>
			
								<td colspan="2" width="100%">
									<html:text
										property="documentoClienteUsuario"
										tabindex="4"
										size="14" 
										maxlength="14" 
										onkeyup="enter(event,'nomeClienteUsuario');return isCampoNumerico(event);"/>
																
								<logic:present name="indicadorConsultaDocumentoReceita" scope="session" >
									<logic:equal name="indicadorConsultaDocumentoReceita" value="2">
									<font color="#FF0000">A PESQUISA PARA RECEITA FED. ESTÁ DESABILITADA</font>
									</logic:equal>
								</logic:present>
								</td>
							</tr>
							
							<tr>
								<td><strong>Cliente:</strong></td>
			
								<td>
									<html:text
										property="nomeClienteUsuario"
										tabindex="5"
										size="50" 
										maxlength="100" 
										onkeyup="enter(event,'rgClienteUsuario');"/>
								</td>

							</tr>
							
							<tr>
								<td><strong>RG:</strong></td>
								
								<td colspan="3">
									<html:text
										property="rgClienteUsuario"
										tabindex="7"
										size="12" 
										maxlength="12" 
										onkeyup="enter(event,'ufClienteUsuario');return isCampoNumerico(event);"/>
										
									<strong>UF:</strong>
									
									<html:text
										property="ufClienteUsuario"
										tabindex="8"
										size="2" 
										maxlength="2" 
										onkeyup="enter(event,'sexoClienteUsuario');"/>
										
									<strong>Sexo:</strong>
								
								
									<html:text
										property="sexoClienteUsuario"
										tabindex="6"
										size="1" 
										maxlength="1" 
										onkeyup="enter(event,'dddFoneClienteUsuario');"
										onkeypress="javascript:return isCampoNumerico(event);"/>
								
								
									<strong>1-Masc. 2-Fem.</strong>										
										
								</td>
								
							</tr>
							
							<tr>
								<td>
									<a id="textofoneTipoClienteUsuario" title="${requestScope.helpFoneTipo}">
										<strong>Fone/Tipo:</strong>
									</a>
								</td>
								
								<td colspan="2">
									<div id="bubble_tooltip" style="border: 1px solid gray; background-color: white; " >
										<div class="bubble_top"><span></span></div>
										<div class="bubble_middle"><span id="bubble_tooltip_content"></span></div>
										<div class="bubble_bottom"></div>
									</div>
									
									<html:text
										property="dddFoneClienteUsuario"
										size="2" 
										maxlength="2" 
										onkeyup="enter(event,'foneClienteUsuario');return isCampoNumerico(event);"/> -
										
									<html:text
										property="foneClienteUsuario"
										size="9" 
										maxlength="9" 
										onkeyup="enter(event,'foneTipoClienteUsuario');return isCampoNumerico(event);"/> 

									<html:text
										property="foneTipoClienteUsuario"
										size="2" 
										maxlength="2" 
										onkeyup="javascript:limparCamposPesquisa(3);enter(event,'lote');ajuda(event, 'foneTipoClienteUsuario');"
										onkeypress="javascript:limparCamposPesquisa(3);validaEnter(event, 'exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovelAction.do?objetoConsulta=3','foneTipoClienteUsuario');return isCampoNumerico(event);"/>
									
									<logic:notPresent name="foneTipoClienteUsuarioInexistente" scope="session" >	
										<html:text
											property="descricaoFoneTipoClienteUsuario"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="20" 
											maxlength="20" />
									</logic:notPresent>
									<logic:present name="foneTipoClienteUsuarioInexistente" scope="session" >
										<html:text
											property="descricaoFoneTipoClienteUsuario"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="20" 
											maxlength="20" />
									</logic:present>
								</td>
							</tr>
														
							<tr>
								<td colspan="6" align="center" bgcolor="#79bbfd">
									<strong>Dados do Imóvel</strong>
								</td>
							</tr>
							
							<tr>
								<td>
									<strong>Localidade:</strong>
								</td>
								
								<td colspan="4">
									<html:text
										property="idLocalidade"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="4" 
										maxlength="4" />

									<strong>Setor:</strong>
								
									<html:text
										property="codigoSetorComercial"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="4" maxlength="4" />

									<strong>Quadra:</strong>
								
									<html:text
										property="numeroQuadra"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="4" 
										maxlength="4" />
										
									<strong>Lote:<font color="#FF0000">*</font></strong>
									<html:text
										property="lote"
										tabindex="13"
										size="4" 
										maxlength="4" 
										onkeyup="enter(event,'subLote');"
										onkeypress="javascript:return isCampoNumerico(event);"/>
										
									<strong>Sublote:<font color="#FF0000">*</font></strong>
									
									<html:text
										property="subLote"
										tabindex="14"
										size="4" 
										maxlength="3"
										onkeyup="verificarCampoPerfilImovel(event);"
										onkeypress="javascript:return isCampoNumerico(event);"/>
										
								</td>

								
							</tr>							
							
							<tr>
								<td>
									<a id="textoperfil" title="${requestScope.helpPerfilImovel}">
										<strong>Perfil:<font color="#FF0000">*</font></strong>
									</a>
								</td>
								
								<td colspan="4">
									<html:text
										property="perfil"
										size="2" 
										maxlength="2"
										disabled="true"
										onkeyup="javascript:limparCamposPesquisa(14);ajuda(event, 'perfil');"
										onkeypress="javascript:limparCamposPesquisa(14);validaEnterComMensagem(event, 'exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovelAction.do?objetoConsulta=15','perfil', 'Perfil');return isCampoNumerico(event);" />
									
									<logic:notPresent name="perfilImovelInexistente" scope="session">	
										<html:text
											property="descricaoPerfil"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="20" 
											maxlength="20" />
									</logic:notPresent>
									<logic:present name="perfilImovelInexistente" scope="session">
										<html:text
											property="descricaoPerfil"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="20" 
											maxlength="20" />
									</logic:present>
										
									<strong>Analisar Tarifa Social:</strong>

									<html:text
										property="analisarTarifaSocial"
										tabindex="15"
										size="1" 
										maxlength="1"
										onkeyup="enter(event,'logradouro');" 
										onkeypress="javascript:return isCampoNumerico(event);"/>
										
									<strong>1-SIM 2-NÃO</strong>
										
								</td>
							</tr>
							
							<tr>
								<td><strong>Logradouro:<font color="#FF0000">*</font></strong></td>
								<td colspan="3">
									<strong> 
									<html:select property="logradouro"
										tabindex="16"
										style="width: 230px;"
										onchange="mostrarCep();" >
										
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<logic:present name="colecaoLogradourosSelecionados" scope="session">
											<html:options collection="colecaoLogradourosSelecionados"
												labelProperty="descricaoFormatada" 
												property="id" />
										</logic:present>
									</html:select> 														
									</strong>
									
									<strong>CEP:<font color="#FF0000">*</font></strong>
									
									<strong> 
									<logic:present name="bloquearCep" scope="session">
										<html:select property="cep"
											tabindex="16" disabled="true"
											style="width: 100px;" >
											
											<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
											<logic:present name="colecaoCep" scope="session">
												<html:options collection="colecaoCep"
													labelProperty="cep.codigo" 
													property="cep.cepId" />
											</logic:present>
										</html:select> 														
									</logic:present>
									<logic:notPresent name="bloquearCep" scope="session" >
										<html:select property="cep"
											tabindex="16"
											style="width: 100px;" >
											
											<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
											<logic:present name="colecaoCep" scope="session">
												<html:options collection="colecaoCep"
													labelProperty="cep.codigo" 
													property="cep.cepId" />
											</logic:present>
										</html:select> 	
									</logic:notPresent>
									</strong>
								</td>
							</tr>

							<tr>
								<td>
									
									<a id="textoreferencia" title="${requestScope.helpEnderecoReferencia}">
										<strong>REF/Número:<font color="#FF0000">*</font></strong>
									</a>
								</td>
								<td  colspan="5">
									<html:text
										property="referencia"
										tabindex="17"
										size="2" 
										maxlength="2" 
										onkeyup="javascript:limparCamposPesquisa(4);ajuda(event, 'referencia');"
										onkeypress="javascript:limparCamposPesquisa(4);validaEnterComMensagem(event, 'exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovelAction.do?objetoConsulta=4','referencia','Referência');return isCampoNumerico(event);"/>
									
									<logic:notPresent name="referenciaInexistente" scope="session">
										<html:text
											property="descricaoReferencia"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="27" 
											maxlength="25" /> / 
									</logic:notPresent>
									<logic:present name="referenciaInexistente" scope="session">
										<html:text
											property="descricaoReferencia"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="27" 
											maxlength="25" /> /
									</logic:present>
									
									<html:text
										property="numero"
										tabindex="18"
										size="5" 
										maxlength="5" 
										onkeypress="return isCampoNumerico(event);"
										onkeyup="enter(event,'complemento');"/>
										
								</td>
							</tr>

							<tr>
								<td><strong>Complemento:</strong></td>
								<td>
									<html:text
										property="complemento"
										tabindex="19"
										size="25" 
										maxlength="25" 
										onkeyup="enter(event,'subCategoria1');"/>
								
								</td>
							</tr>							
							
							<tr>
								<td><strong>Bairro:</strong></td>
			
								<td colspan="4">
									<html:text
										property="nomeBairro"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="30" 
										maxlength="30" />
										
								<strong>Municipio:</strong>
								
								<html:text
									property="nomeMunicipio"
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000"
									size="30" 
									maxlength="30" />
										
								</td>
							</tr>
							
							<tr>
								<td><strong>Subcategorias:<font color="#FF0000">*</font></strong></td>
			
								<td colspan="4">
									<html:text
										property="subCategoria1"
										tabindex="20"
										size="2" 
										maxlength="2" 
										onkeyup="enter(event,'subCategoria2');"
										onkeypress="javascript:return isCampoNumerico(event);"/>
										
									<html:text
										property="subCategoria2"
										tabindex="21"
										size="2" 
										maxlength="2"
										onkeyup="enter(event,'subCategoria3');" 
										onkeypress="javascript:return isCampoNumerico(event);"/>

									<html:text
										property="subCategoria3"
										tabindex="22"
										size="2" 
										maxlength="2"
										onkeyup="enter(event,'subCategoria4');" 
										onkeypress="javascript:return isCampoNumerico(event);"/>

									<html:text
										property="subCategoria4"
										tabindex="23"
										size="2" 
										maxlength="2"
										onkeyup="enter(event,'subCategoria5');" 
										onkeypress="javascript:return isCampoNumerico(event);"/>

									<html:text
										property="subCategoria5"
										tabindex="24"
										size="2" 
										maxlength="2"
										onkeyup="enter(event,'subCategoria6');"
										onkeypress="javascript:return isCampoNumerico(event);"/>

									<html:text
										property="subCategoria6"
										tabindex="25"
										size="2" 
										maxlength="2"
										onkeyup="enter(event,'numeroEconomias1');"
										onkeypress="javascript:return isCampoNumerico(event);"/>
										
										
								</td>
							</tr>
							
							<tr>
								<td><strong>Nº Econômias:<font color="#FF0000">*</font></strong></td>
			
								<td colspan="4">
									<html:text
										property="numeroEconomias1"
										tabindex="26"
										size="2" 
										maxlength="2" 
										onkeyup="enter(event,'numeroEconomias2');"
										onkeypress="javascript:return isCampoNumerico(event);"/>
										
									<html:text
										property="numeroEconomias2"
										tabindex="27"
										size="2" 
										maxlength="2"
										onkeyup="enter(event,'numeroEconomias3');" 
										onkeypress="javascript:return isCampoNumerico(event);"/>

									<html:text
										property="numeroEconomias3"
										tabindex="28"
										size="2" 
										maxlength="2"
										onkeyup="enter(event,'numeroEconomias4');" 
										onkeypress="javascript:return isCampoNumerico(event);"/>

									<html:text
										property="numeroEconomias4"
										tabindex="29"
										size="2" 
										maxlength="2" 
										onkeyup="enter(event,'numeroEconomias5');"
										onkeypress="javascript:return isCampoNumerico(event);"/>

									<html:text
										property="numeroEconomias5"
										tabindex="30"
										size="2" 
										maxlength="2"
										onkeyup="enter(event,'numeroEconomias6');" 
										onkeypress="javascript:return isCampoNumerico(event);"/>

									<html:text
										property="numeroEconomias6"
										tabindex="31"
										size="2" 
										maxlength="2"
										onkeyup="enter(event,'numeroMoradores');" 
										onkeypress="javascript:return isCampoNumerico(event);"/>
								</td>
							</tr>
							
							<tr>
								<td><strong>Nº moradores:</strong></td>
			
								<td colspan="2">
									<html:text
										property="numeroMoradores"
										tabindex="32"
										size="2" 
										maxlength="2" 
										onkeyup="enter(event,'medidorCelpe');"
										onkeypress="javascript:return isCampoNumerico(event);"/>
									
									<strong>Nº medidor CELPE:</strong>
									<html:text
										property="medidorCelpe"
										tabindex="33"
										size="10" 
										maxlength="10" 
										onkeyup="enter(event,'pavimentoRua');"/>
										
								</td>
							</tr>
							
							<tr>
								<td>
									
									<a id="textopavimentoRua" title="${requestScope.helpPavimentoRua}">
										<strong>Pav. Rua:<font color="#FF0000">*</font></strong>
									</a>
								</td>
								<td colspan="4">
									<html:text
										property="pavimentoRua"
										size="2" 
										maxlength="2"
										onkeyup="javascript:limparCamposPesquisa(5);ajuda(event, 'pavimentoRua');"
										onkeypress="javascript:limparCamposPesquisa(5);validaEnterComMensagem(event, 'exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovelAction.do?objetoConsulta=5','pavimentoRua','Pavimento Rua');return isCampoNumerico(event);"/>
									
									<logic:notPresent name="pavimentoRuaInexistente" scope="session" >	
										<html:text
											property="descricaoPavimentoRua"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="20" 
											maxlength="20" />
									</logic:notPresent>
									<logic:present name="pavimentoRuaInexistente" scope="session">
										<html:text
											property="descricaoPavimentoRua"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="20" 
											maxlength="20" />
									</logic:present>
										
									<a id="textopavimentoCalcada" title="${requestScope.helpPavimentoCalcada}">
										<strong>Pav. Calçada:<font color="#FF0000">*</font></strong>
									</a>
								
									<html:text
										property="pavimentoCalcada"
										size="2" 
										maxlength="2" 
										onkeyup="javascript:limparCamposPesquisa(6);ajuda(event, 'pavimentoCalcada');"
										onkeypress="javascript:limparCamposPesquisa(6);validaEnterComMensagem(event, 'exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovelAction.do?objetoConsulta=6','pavimentoCalcada','Pavimento Calçada');return isCampoNumerico(event);"/>
									
									<logic:notPresent name="pavimentoCalcadaInexistente" scope="session">	
										<html:text
											property="descricaoPavimentoCalcada"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="20" 
											maxlength="20" />
									</logic:notPresent>
									
									<logic:present name="pavimentoCalcadaInexistente" scope="session">
										<html:text
											property="descricaoPavimentoCalcada"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="20" 
											maxlength="20" />
									</logic:present> 
										
								</td>
							</tr>
							
							<tr>
								<td>
									
									<a id="textofonteAbastecimento" title="${requestScope.helpFonteAbastecimento}">
										<strong>Fonte Abast.:<font color="#FF0000">*</font></strong>
									</a>
								</td>
								<td colspan="2">
									<html:text
										property="fonteAbastecimento"
										size="2" 
										maxlength="2" 
										onkeyup="javascript:limparCamposPesquisa(7);ajuda(event, 'fonteAbastecimento');"
										onkeypress="javascript:limparCamposPesquisa(7);validaEnterComMensagem(event, 'exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovelAction.do?objetoConsulta=7','fonteAbastecimento','Fonte de Abastecimento');return isCampoNumerico(event);"/>
									
									<logic:notPresent name="fonteAbastecimentoInexistente" scope="session" >	
										<html:text
											property="descricaoFonteAbastecimento"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="22" 
											maxlength="22" />
									</logic:notPresent>
									<logic:present name="fonteAbastecimentoInexistente" scope="session" >
										<html:text
											property="descricaoFonteAbastecimento"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="22" 
											maxlength="22" />
									</logic:present>
								</td>
								
							</tr>
							
							<tr>
								<td colspan="6" align="center" bgcolor="#79bbfd">
									<strong>Dados da Ligação</strong>
								</td>
							</tr>
							
							<tr>
								<td>
									<a id="textosituacaoAgua" title="${requestScope.helpSituacaoAgua}">
										<strong>Sit. Água:<font color="#FF0000">*</font></strong>
									</a>
								</td>
								<td colspan="4">
									<html:text
										property="situacaoAgua"
										size="2" 
										maxlength="2"
										onkeyup="javascript:limparCamposPesquisa(8);ajuda(event, 'situacaoAgua');"
										onkeypress="javascript:limparCamposPesquisa(8);validaEnterComMensagem(event, 'exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovelAction.do?objetoConsulta=8','situacaoAgua','Ligação de Água');return isCampoNumerico(event);"/> 
									
									<logic:notPresent name="situacaoAguaInexistente" scope="session">	
										<html:text
											property="descricaoSituacaoAgua"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="20" 
											maxlength="20" />
									</logic:notPresent>
									<logic:present name="situacaoAguaInexistente" scope="session">
										<html:text
											property="descricaoSituacaoAgua"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="20" 
											maxlength="20" />
									</logic:present>
										
									<a id="textosituacaoEsgoto" title="${requestScope.helpSituacaoEsgoto}">
										<strong>Sit. Esgoto:<font color="#FF0000">*</font></strong>
									</a>

									<html:text
										property="situacaoEsgoto"
										size="2" 
										maxlength="2" 
										onkeyup="javascript:limparCamposPesquisa(9);ajuda(event, 'situacaoEsgoto');"
										onkeypress="javascript:limparCamposPesquisa(9);validaEnterComMensagem(event, 'exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovelAction.do?objetoConsulta=9','situacaoEsgoto','Ligação de Esgoto');return isCampoNumerico(event);"/>
									
									<logic:notPresent name="situacaoEsgotoInexistente" scope="session">	
										<html:text
											property="descricaoSituacaoEsgoto"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="20" 
											maxlength="20" />
									</logic:notPresent>
									<logic:present name="situacaoEsgotoInexistente" scope="session">
										<html:text
											property="descricaoSituacaoEsgoto"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="20" 
											maxlength="20" />
									</logic:present>
								</td>
								
							</tr>
							
							<tr>
								<td><strong>Hidrômetro:<font color="#FF0000">*</font></strong></td>
								
								<td>
									<html:text
										property="indicadorHidrometro"
										size="2" 
										maxlength="2"
										onkeyup="enter(event,'numeroHidrometro');" 
										onkeypress="javascript:return isCampoNumerico(event);"/>
										
									<strong>1-SIM 2-NÃO</strong>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<strong>Número do hidrômetro:</strong>
									
									<html:text
										property="numeroHidrometro"
										size="13" 
										maxlength="10" 
										onkeyup="javascript:limparCamposPesquisa(10);enter(event,'localInstalacao');"
										onkeypress="javascript:limparCamposPesquisa(10);validaEnterString(event, 'exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovelAction.do?objetoConsulta=10','numeroHidrometro');"/>
									
									<strong>Ano Fabricação:</strong>
									<html:text
										property="anoFabricacao"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="10" 
										maxlength="10" />
										
								</td>										
							</tr>

							<tr>
								<td>
									<strong>Marca:</strong>
								</td>
								
								<td colspan="5">
									<html:text
										property="marca"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="33" 
										maxlength="33" />
										
									<strong>Capacidade:</strong>
									
									<html:text
										property="capacidade"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="23" 
										maxlength="23" />										
								</td>
							</tr>
							
							<tr>
								<td>
									<a id="textolocalInstalacao" title="${requestScope.helpLocalInstalacao}">
										<strong>Local Inst.:</strong>
									</a>
								</td>
								
								<td colspan="4">
									<html:text
										property="localInstalacao"
										size="2" 
										maxlength="2" 
										onkeyup="javascript:limparCamposPesquisa(11);enter(event,'protecao');ajuda(event, 'localInstalacao');"
										onkeypress="javascript:limparCamposPesquisa(11);validaEnter(event, 'exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovelAction.do?objetoConsulta=11','localInstalacao');return isCampoNumerico(event);"/>

									<logic:notPresent name="localInstalacaoInexistente" scope="session" >
										<html:text
											property="descricaoLocalInstalacao"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="20" 
											maxlength="20" />
									</logic:notPresent>
									<logic:present name="localInstalacaoInexistente" scope="session">
										<html:text
											property="descricaoLocalInstalacao"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="20" 
											maxlength="20" />
									</logic:present>
									
									<a id="textoprotecao" title="${requestScope.helpProtecao}">
										<strong>Tipo de Prot.:</strong>
									</a>
									
									<html:text
										property="protecao"
										size="2" 
										maxlength="2" 
										onkeyup="javascript:limparCamposPesquisa(12);enter(event,'cavalete');ajuda(event, 'protecao');"
										onkeypress="javascript:limparCamposPesquisa(12);validaEnterAceitaZERO(event, 'exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovelAction.do?objetoConsulta=12','protecao');return isCampoNumerico(event);"/>

									<logic:notPresent name="tipoProtecaoInexistente" scope="session">
										<html:text
											property="descricaoProtecao"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="20" 
											maxlength="20" />
									</logic:notPresent>
									<logic:present name="tipoProtecaoInexistente" scope="session">
										<html:text
											property="descricaoProtecao"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="20" 
											maxlength="20" />
									</logic:present>									
								</td>
							</tr>
							
							<tr>
								<td>
									<strong>Cavalete:</strong>
								</td>
								
								<td colspan="4">
									<html:text
										property="cavalete"
										size="1" 
										maxlength="1" 
										onkeyup="enter(event,'ocorrenciaCadastro');"
										onkeypress="javascript:return isCampoNumerico(event);"/>
										
									<strong>1-SIM 2-NÃO</strong>

								</td>
							</tr>
							<tr>
								
								<td>
									<a id="textoocorrencia" title="${requestScope.helpOcorrencia}">
										<strong>Ocorrência Cad.:</strong>
									</a>
								</td>
								
								
								<td colspan="2">
									<html:text
										property="ocorrenciaCadastro"
										size="2" 
										maxlength="2" 
										onkeyup="javascript:limparCamposPesquisa(13);enter(event,'leitura');ajuda(event, 'ocorrencia');"
										onkeypress="javascript:limparCamposPesquisa(13);validaEnter(event, 'exibirEfetuarDigitacaoDadosAtualizacaoCadastralImovelAction.do?objetoConsulta=13','ocorrenciaCadastro');return isCampoNumerico(event);"/>

									<logic:notPresent name="ocorrenciaCadastroInexistente" scope="session">
										<html:text
											property="descricaoOcorrenciaCadastro"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="25" 
											maxlength="25" />
									</logic:notPresent>
									<logic:present name="ocorrenciaCadastroInexistente" scope="session">
										<html:text
											property="descricaoOcorrenciaCadastro"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000"
											size="25" 
											maxlength="25" />
									</logic:present>										
								</td>
							</tr>
							
							<tr>
								<td><strong>Leitura:</strong></td>
								
								<td colspan="3">
									<html:text
										property="leitura"
										size="6" 
										maxlength="6"
										onkeyup="enter(event,'observacao');" 
										onkeypress="javascript:return isCampoNumerico(event);"/>

										
									<strong>Critério Tarifa Social:</strong>
									
									<html:text
										property="criterioTarifaSocial"
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: #000000"
										size="2" 
										maxlength="2" />
								
								</td>
							</tr>							
							<tr>
								<td><strong>Observação:</strong></td>
								<td>
									<html:textarea property="observacao" 
										cols="40" 
										rows="4" 
										onkeyup="limitTextArea(document.forms[0].observacao, 400, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
									<strong><span id="utilizado">0</span>/<span id="limite">400</span></strong>
								</td>
							</tr>
							
							<tr>
								<td></td>
								<td align="right">
									<div align="left">
									<strong><font color="#FF0000">*</font></strong>
									Campos obrigatórios</div>
								</td>
							</tr>
							
							<tr>
								<td>&nbsp;</td>
							</tr>
							
							<tr>
								<td colspan="3" align="left" >
									<input name="Button" 
												type="button" 
												class="bottonRightCol" 
												value="Voltar" 
												align="left"
												onclick="javascript:voltar();">
									
									<input name="Button"
												type="button"
												class="bottonRightCol"
												value="Desfazer"
												align="left"
												onclick="javascript:limparForm();" >
									
									<input name="Button" 
												type="button" 
												class="bottonRightCol" 
												value="Cancelar" 
												align="left"
												onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
									
								</td>
								
								<td align="right">
									<input name="Button" 
											type="button" 
											class="bottonRightCol" 
											value="Inserir" 
											align="right"
											onclick="javascript:inserirImovel();">
								</td>
							</tr>
							
						</table>
					</tr>
					
				</table>
			</tr>

				
		</table>
		<%@ include file="/jsp/util/rodape.jsp"%>
		<%@ include file="/jsp/util/tooltip.jsp"%> 
	</html:form>
</body>
</html:html>

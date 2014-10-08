<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<%--<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InformarParametrosSistemaActionForm" dynamicJavascript="false" />--%>
<html:javascript formName="InformarSistemaParametrosActionForm" dynamicJavascript="false" staticJavascript="true" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script>

	var bCancel = false;
	var ehPresidente;
	var clienteFicticio;
	var clienteUsuario;

	function naoTemZeroComMsg(campo, nomeCampo){
		if(campo.value == '0' || campo.value == '00'){
			alert(nomeCampo+" deve somente conter números decimais positivos");
			return false;	
		}
		else{
			return true;
		}
	}

	function validateInformarSistemaParametrosActionForm(form) {

		if (bCancel){
			return true;
		}else{
			limparClienteFicticioInexistente();
			
			return verificaAnoMesMensagemPersonalizada(form.faturamentoInicioContratoPPP,"Mês/Ano Faturamento Início Contrato PPP inválido") &&
			    testarCampoValorZero(document.InformarSistemaParametrosActionForm.logradouro, 'Logradouro') && 
				validateCaracterEspecial(form) && 
				validateRequired(form) && 
				validateCnpj(form) && 
				validateLong(form) && 
				validateEmail(form)&&
				validateEndereco(form) &&
				validaCampoNumericoComPonto() && 
				naoTemZeroComMsg(form.valorExtratoFichaComp, 'Valor para Emissão de Extrato Tipo Ficha de Compensação')     			
       			&& (form.valorExtratoFichaComp == null 
       					|| form.valorExtratoFichaComp.value == ''           				
       	       			|| testarCampoValorZeroDecimal(form.valorExtratoFichaComp, 'Valor para Emissão de Extrato Tipo Ficha de Compensação'))
      			&& naoTemZeroComMsg(form.valorGuiaFichaComp, 'Valor para Emissão de Guia de Pagamento no Formato Ficha de Compensação')     			
       	       			&& (form.valorGuiaFichaComp == null 
       	       					|| form.valorGuiaFichaComp.value == ''           				
       	       	       			|| testarCampoValorZeroDecimal(form.valorExtratoFichaComp, 'Valor para Emissão de Guia de Pagamento no Formato Ficha de Compensação'))
       	    	&& naoTemZeroComMsg(form.valorDemonstrativoParcelamentoFichaComp, 'Valor para Emissão de Demonstrativo de Parcelamento no Formato Ficha de Compensação')     			
       	       			&& (form.valorDemonstrativoParcelamentoFichaComp == null 
       	       					|| form.valorDemonstrativoParcelamentoFichaComp.value == ''           				
       	       	       			|| testarCampoValorZeroDecimal(form.valorDemonstrativoParcelamentoFichaComp, 'Valor para Emissão de Demonstrativo de Parcelamento no Formato Ficha de Compensação'))
       	       	       			
		}
	}
	
	function limparClienteFicticioInexistente(){
		var retorno = true;
		var form = document.forms[0];
		
		if(form.nomeClienteFicticioAssociarPagamentosNaoIdentificados.value == "Código do Cliente inexistente" || form.nomeClienteFicticioAssociarPagamentosNaoIdentificados.value == ""){
			form.clienteFicticioAssociarPagamentosNaoIdentificados.value = "";
	   		form.nomeClienteFicticioAssociarPagamentosNaoIdentificados.value = "";
		}
		
		return retorno;
	}

	function validateEndereco(form){
		
		retorno = true;
		var enderecoInformado = document.getElementById("enderecoInformado");
		
		if (enderecoInformado == null){
		
			alert("Informe Endereço \n");
			retorno = false;
		}
		
		return retorno; 
	} 
	
	function IntegerValidations () {
		this.aa = new Array("cnpj", "CNPJ deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("unidadeOrganizacionalPresidencia", "Unidade Organizacional deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("presidente", "Presidente deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ae = new Array("diretorComercial", "Diretor Comercial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ae = new Array("diretorGestao", "Diretor de Gestão deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.af = new Array("numeroTelefoneAtendimento", "Telefone de Atendimento deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ag = new Array("quantidadeDigitosQuadra", "Quantidade Dígitos da Quadra deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
		this.ah = new Array("clienteFicticioAssociarPagamentosNaoIdentificados", "Cliente fictício para associar os pagamentos não identíficados deve conter somente números positivos.", new Function ("varName", " return this[varName];"));
		this.ai = new Array("clienteUsuarioDesconhecido", "Cliente Usuário Desconhecido não identíficados deve conter somente números positivos.", new Function ("varName", " return this[varName];"));
		this.aj = new Array("idClienteResponsavelProgramaEspecial", "Cliente Responsável Programa Especial não identíficados deve conter somente números positivos.", new Function ("varName", " return this[varName];"));
		this.ak = new Array("tamanhoMaximoAnexoRA", "Tamanho Máximo de Anexo para Registro de Atendimento deve conter somente números positivos.", new Function ("varName", " return this[varName];"));
	}

	function cnpj () {
		this.aa = new Array("cnpj", "CNPJ inválido.", new Function ("varName", " return this[varName];"));
	}

	function caracteresespeciais () {
		this.aa = new Array("nomeEstado", "Nome do Estado possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("nomeEmpresa", "Nome da Empresa possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("abreviaturaEmpresa", "Abreviatura da Empresa possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("cnpj", "CNPJ possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.af = new Array("unidadeOrganizacionalPresidencia", "Unidade Organizacional possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ag = new Array("presidente", "Presidente possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ah = new Array("diretorComercial", "Diretor Comercial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ah = new Array("diretorGestao", "Diretor de Gestão possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ai = new Array("numeroTelefoneAtendimento", "Telefone de Atendimento possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.aj = new Array("quantidadeDigitosQuadra", "Quantidade Dígitos da Quadra possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.al = new Array("clienteFicticioAssociarPagamentosNaoIdentificados", "Cliente fictício para associar os pagamentos não identíficados possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.am = new Array("clienteUsuarioDesconhecido", "Cliente Usuário Desconhecido não identíficados possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.aj = new Array("idClienteResponsavelProgramaEspecial", "Cliente Responsável Programa Especial não identíficados possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.an = new Array("inscricaoEstadual", "Incrição Estadual possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ao = new Array("inscricaoMunicipal", "Incrição Municipal possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ap = new Array("numeroContrato", "Número de Contrato possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.aq = new Array("fax", "Fax possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ar = new Array("ramal", "Ramal possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.as = new Array("titulosRelatorio", "Título de Relatório possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		
	}

	function required () {
		
		this.aa = new Array("nomeEstado", "Informe Nome do Estado.", new Function ("varName", " return this[varName];"));
		this.ab = new Array("nomeEmpresa", "Informe Nome da Empresa.", new Function ("varName", " return this[varName];"));
		this.ac = new Array("abreviaturaEmpresa", "Informe Abreviatura da Empresa.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("cnpj", "Informe CNPJ.", new Function ("varName", " return this[varName];"));
		this.af = new Array("titulosRelatorio", "Informe Títulos de Relatório.", new Function ("varName", " return this[varName];"));
		this.ag = new Array("imagemLogomarca", "Informe Caminho Imagem da Logomarca.", new Function ("varName", " return this[varName];"));
		this.ah = new Array("imagemRelatorio", "Informe Caminho Imagem do Relatório.", new Function ("varName", " return this[varName];"));
		this.ai = new Array("imagemConta", "Informe Caminho Imagem da Conta.", new Function ("varName", " return this[varName];"));
		this.aj = new Array("quantidadeDigitosQuadra", "Informe a Quantidade de Dígitos da Quadra.", new Function ("varName", " return this[varName];"));
		this.al = new Array("indicadorVariaHierarquiaUnidade", "Informe se na empresa poderá ocorrer a variação de hierarquia entre as unidades organizacionais .", new Function ("varName", " return this[varName];"));
		this.am = new Array("imagemArpe", "Informe Caminho Imagem da ARPE.", new Function ("varName", " return this[varName];"));
		this.an = new Array("imagemRodapeConta", "Informe Caminho Imagem de rodapé da Conta.", new Function ("varName", " return this[varName];"));	
		this.ao = new Array("tamanhoMaximoAnexoRA", "Informe Tamanho Máximo de Anexo para Registro de Atendimento.", new Function ("varName", " return this[varName];"));		
		this.ap = new Array("resolucaoImagem", "Informe Resolução da Imagem.", new Function ("varName", " return this[varName];"));
	}


	function email () {
		this.aa = new Array("email", "E-Mail inválido.", new Function ("varName", " return this[varName];"));
	}

	

	//Chama Popup
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			}
			else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}
				else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisaLogradouro=" + tipo, altura, largura);
				}
			}
		}
	}

	//Recupera Dados Popup
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];

		if(tipoConsulta == 'unidadeOrganizacional'){

	    	form.unidadeOrganizacionalPresidencia.value = codigoRegistro;
	    	form.nomeUnidadeOrganizacionalPresidencia.value = descricaoRegistro;
	      	form.nomeUnidadeOrganizacionalPresidencia.style.color = "#000000";

	    }else if(tipoConsulta == 'cliente'){
			if(presidente){
		    	form.presidente.value = codigoRegistro;
		    	form.nomePresidente.value = descricaoRegistro;
		      	form.nomePresidente.style.color = "#000000";
			}else if(diretorComercial){
				form.diretorComercial.value = codigoRegistro;
		    	form.nomeDiretorComercial.value = descricaoRegistro;
		      	form.nomeDiretorComercial.style.color = "#000000";
			}else if(diretorGestao){
				form.diretorGestao.value = codigoRegistro;
		    	form.nomeDiretorGestao.value = descricaoRegistro;
		      	form.nomeDiretorGestao.style.color = "#000000";
			}else if(clienteResponsavel){		    	
            	form.idClienteResponsavelProgramaEspecial.value = codigoRegistro;
				form.nomeClienteResponsavelProgramaEspecial.value = descricaoRegistro;
			    form.nomeClienteResponsavelProgramaEspecial.style.color = "#000000";
            }else if (clienteFicticio){
				form.clienteFicticioAssociarPagamentosNaoIdentificados.value = codigoRegistro;
				form.nomeClienteFicticioAssociarPagamentosNaoIdentificados.value = descricaoRegistro;
			    form.nomeClienteFicticioAssociarPagamentosNaoIdentificados.style.color = "#000000";
			}else if(clienteDesconhecido){
            	form.clienteUsuarioDesconhecido.value = codigoRegistro;
				form.nomeClienteUsuarioDesconhecido.value = descricaoRegistro;
			    form.nomeClienteUsuarioDesconhecido.style.color = "#000000";
            }				
		}	
	}
	
	/*
	* Autor: Jonathan Marcos
	* Data: 20/09/2013
	* Observacao : funcao escolhe que tipo de cliente setar as informacoes do popup cliente
	*/
	function setarTipoCliente(tipoCliente){
		presidente = false;
		diretorComercial = false;
		diretorGestao = false;
		clienteResponsavel = false;
		clienteFicticio = false;
		clienteDesconhecido = false;

		if(tipoCliente=='presidente'){
			presidente = true;			
		}else if(tipoCliente=='diretorComercial'){
			diretorComercial = true;
		}else if(tipoCliente=='diretorGestao'){
			diretorGestao = true;
		}else if(tipoCliente=='responsavel'){
			clienteResponsavel = true;
		}else if(tipoCliente=='ficticio'){
			clienteFicticio = true;
		}else if(tipoCliente=='desconhecido'){
			clienteDesconhecido = true;
		}
	}

	function limparCliente(ehPresidente) {

    	var form = document.forms[0];
		if(ehPresidente == true){
	    	form.presidente.value = "";
	    	form.nomePresidente.value = "";
		}else{
	    	form.diretorComercial.value = "";
	    	form.nomeDiretorComercial.value = "";
		}
  	}

  	function limparDiretorGestao(){
  	  	var form = document.forms[0];
  		  
		form.diretorGestao.value = "";
		form.nomeDiretorGestao.value = "";
	}
  	
  	function limparClienteFicticio(){
  		var form = document.forms[0];
	  
 		form.clienteFicticioAssociarPagamentosNaoIdentificados.value = "";
   		form.nomeClienteFicticioAssociarPagamentosNaoIdentificados.value = "";
  	}

  	function limparClienteUsuarioDesconhecido(){
  	  	var form = document.forms[0];
  	  	
  		 
  	 		form.clienteUsuarioDesconhecido.value = "";
  	   		form.nomeClienteUsuarioDesconhecido.value = "";
  		  	
  	  	}

  	function limparClienteResponsavel(){
  	  	var form = document.forms[0];
  	  	
  		 
  	 		form.idClienteResponsavelProgramaEspecial.value = "";
  	   		form.nomeClienteResponsavelProgramaEspecial.value = "";
  		  	
  	  	}

    function limparUnidade() {
        var form = document.forms[0];

    	form.unidadeOrganizacionalPresidencia.value = "";
    	form.nomeUnidadeOrganizacionalPresidencia.value = "";
    }

    function validateInformarParametrosSistemaActionForm(form) {
		var endereco = document.getElementById("validarEndereco").value;
		var retorno = false;

		if (endereco == "1"){
			retorno = true;
		} else{
			alert("Informe Endereço.");
		}

		return retorno;
	}
	
	//Remover Endereço
	function remover(){
		redirecionarSubmitSemUpperCase('informarParametrosSistemaWizardAction.do?action=exibirInformarParametrosSistemaDadosGeraisEmpresaAction&removerEndereco=OK');
	}
	

	function removerEndereco(url){

		if(confirm('Confirma remoção ?')){
       		var form = document.forms[0];
    		form.action = url;
	    	form.submit()	
		}
	}

	
	
	/*function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	    var form = document.forms[0];

	    if (tipoConsulta == 'cliente') {
	      redirecionarSubmit("exibirInformarParametrosSistemaAction.do?pesquisarCliente=sim&codigoCliente="+codigoRegistro);
	    }
	}*/    
	
	
	/* 
	 * Verifica se o campo aceita apenas numeros e pontos
   	 *	
   	 * Data: 19/02/2010
   	 * Author: Hugo Leonardo
	*/
	function validaCampoNumericoComPonto() {
	
		var form = document.forms[0];

		var valorCampo = trim(form.versaoCelular.value);
		var valorCampoSemPontos = valorCampo.replace(".","");
		valorCampoSemPontos = valorCampoSemPontos.replace(".","");
		valorCampoSemPontos = valorCampoSemPontos.replace(".","");
		valorCampoSemPontos = valorCampoSemPontos.replace(".","");
		valorCampoSemPontos = valorCampoSemPontos.replace(".","");
		var indesejaveis = "~{}^%$[]@|`\\<?\#?!;*>\"\'";

		var teste = true;
		
		// alterado para verificar se foi digitado algum caracter especial - Fernanda Paiva - 11/07/2006
		for (var i=0; i<indesejaveis.length; i++) {
			if ((valorCampo.indexOf(indesejaveis.charAt(i))) != -1 ){
				teste = false;
			}
      	}
      	if(teste == false) {
	      	alert("O campo versão do celular não pode possuir caracteres especiais.");
			return false;
		} else if (valorCampo.length > 0 && (isNaN(valorCampoSemPontos) || valorCampo.indexOf(',') != -1 )){

			alert("O campo versão do celular deve apenas conter números positivos e pontos.");
			return false;
		} else{
			return true;
		}
	}

	
</script>
</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/informarParametrosSistemaWizardAction" method="post"
	onsubmit="return validateInformarSistemaParametrosActionForm(this);">

	<jsp:include
		page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=1" />

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	<input type="hidden" name="numeroPagina" value="1" />
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
			<td width="655" valign="top" class="centercoltext">
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
					<td class="parabg">Informar Parâmetros do Sistema</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0" dwcopytype="CopyTableRow">
				<tr>
					<td>Para informar parâmetros do sistema, informe os dados abaixo:
					<td align="right"><a
						href="javascript: abrirPopup('/gsan/help/help.jsp?mapIDHelpSet=clienteInserirAbaNomeTipo', 500, 700);"><span
						style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>
				</tr>
			</table>

			<table width="100%" border="0">

				<tr>
					<td colspan="2" align="center"><strong>Dados Gerais da Empresa</strong></td>
				</tr>
				
				<tr>
					<td width="25%" align="left">
						<strong>Nome do Estado:<font color="#FF0000">*</font></strong>
					</td>
					<td width="82%">
						<html:text maxlength="25" 
							property="nomeEstado" 
							size="25" /></td>
				</tr>
				
				<tr>
					<td width="25%" align="left">
						<strong>Nome da Empresa:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<html:text maxlength="45" 
							property="nomeEmpresa" 
							size="50" />
					</td>
				</tr>
				
				<tr>
					<td width="25%" align="left">
						<strong>Abreviatura da Empresa:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<html:text maxlength="10" 
							property="abreviaturaEmpresa"
							size="10" />
					</td>
				</tr>
				
				<tr>
					<td width="25%" align="left">
						<strong> CNPJ: <font color="#FF0000">*</font></strong>
					</td>
					<td width="87%">
						<html:text property="cnpj" 
							size="14" 
							maxlength="14" />
					</td>
				</tr>

				<tr>
					<td width="25%" align="left">
						<strong> Inscrição Estadual: </strong>
					</td>
					<td width="87%">
						<html:text property="inscricaoEstadual" 
							maxlength="20" 
							size="22" />
					</td>
				</tr>

				<tr>
					<td width="25%" align="left">
						<strong> Inscrição Municipal: </strong>
					</td>
					<td width="87%">
						<html:text property="inscricaoMunicipal" 
							maxlength="20" 
							size="22" />
					</td>
				</tr>

				<tr>
					<td width="25%" align="left">
						<strong> Número do Contrato: </strong>
					</td>
					<td width="87%">
						<html:text property="numeroContrato" 
							maxlength="20" 
							size="20" />
					</td>
				</tr>
				
				<tr>
					<td width="25%" align="left">
						<strong>Unidade Organizacional da Presidência:</strong>
					</td>
					
					<td colspan="2">
						<html:text maxlength="5" 
							property="unidadeOrganizacionalPresidencia"
							size="5"
							onkeypress="validaEnterComMensagem(event, 'exibirInformarParametrosSistemaAction.do?objetoConsulta=1', 'unidadeOrganizacionalPresidencia','Código da Unidade Organizacional');" />
							<a href="javascript:chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 'unidadeOrganizacionalPresidencia', null, null, 275, 480, '',document.forms[0].unidadeOrganizacionalPresidencia);">
								<img width="23" 
									height="21" 
									border="0" 
									style="cursor:hand;"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Unidade Organizacional" /></a> 
						<logic:present name="unidadeOrganizacionalPresidenciaEncontrada" scope="request">

							<html:text property="nomeUnidadeOrganizacionalPresidencia" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"
								size="37" 
								maxlength="40" />
						</logic:present> 
						
						<logic:notPresent name="unidadeOrganizacionalPresidenciaEncontrada" scope="request">
							<html:text property="nomeUnidadeOrganizacionalPresidencia" 
								size="30" 
								maxlength="30"
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent> 
						
						<a href="javascript:limparUnidade();">
							<img border="0" 
								title="Apagar"
								src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Presidente:</strong></td>
					
					<td>
						
						<html:text maxlength="9" 
							tabindex="1"
							property="presidente" 
							size="9"
							onkeypress="validaEnterComMensagem(event, 'exibirInformarParametrosSistemaAction.do?objetoConsulta=2','presidente','Presidente');"/>
							
							<a href="javascript:setarTipoCliente('presidente');chamarPopup('exibirPesquisarClienteAction.do', 'presidente', null, null, 275, 480, '', document.forms[0].presidente);">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Presidente" /></a> 

							<logic:present name="presidenteEncontrado" scope="request">
								<html:text property="nomePresidente" 
									size="45"
									maxlength="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present> 

							<logic:notPresent name="presidenteEncontrado" scope="request">
								<html:text property="nomePresidente" 
									size="45"
									maxlength="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
							</logic:notPresent>

							
							<a href="javascript:limparCliente(true);"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" />
							</a>					
						</td>
				</tr>
				
				<tr>
					<td><strong>Diretor Comercial:</strong></td>
					
					<td>
						
						<html:text maxlength="9" 
							tabindex="1"
							property="diretorComercial" 
							size="9"
							onkeypress="validaEnterComMensagem(event, 'exibirInformarParametrosSistemaAction.do?objetoConsulta=3','diretorComercial','Diretor Comercial');"/>
							
							<a href="javascript:setarTipoCliente('diretorComercial');chamarPopup('exibirPesquisarClienteAction.do', 'diretorComercial', null, null, 275, 480, '', document.forms[0].diretorComercial);">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Diretor Comercial" /></a> 

							<logic:present name="diretorComercialEncontrado" scope="request">
								<html:text property="nomeDiretorComercial" 
									size="45"
									maxlength="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present> 

							<logic:notPresent name="diretorComercialEncontrado" scope="request">
								<html:text property="nomeDiretorComercial" 
									size="45"
									maxlength="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
							</logic:notPresent>

							
							<a href="javascript:limparCliente(false);"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" />
							</a>					
						</td>
				</tr>
				
				<tr>
					<td><strong>Diretor de Gestão:</strong></td>
					
					<td>
						
						<html:text maxlength="9" 
							tabindex="1"
							property="diretorGestao" 
							size="9"
							onkeypress="validaEnterComMensagem(event, 'exibirInformarParametrosSistemaAction.do?objetoConsulta=10','diretorGestao','Diretor Gestão');"/>
							
							<a href="javascript:setarTipoCliente('diretorGestao');chamarPopup('exibirPesquisarClienteAction.do', 'diretorGestao', null, null, 275, 480, '', document.forms[0].diretorGestao);">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Diretor de Gestão" /></a> 

							<logic:present name="diretorGestaoEncontrado" scope="request">
								<html:text property="nomeDiretorGestao" 
									size="45"
									maxlength="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:present> 

							<logic:notPresent name="diretorGestaoEncontrado" scope="request">
								<html:text property="nomeDiretorGestao" 
									size="45"
									maxlength="45" 
									readonly="true"
									style="background-color:#EFEFEF; border:0; color: red" />
							</logic:notPresent>

							
							<a href="javascript:limparDiretorGestao();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" />
							</a>					
						</td>
				</tr>
				
				<tr>
        			<td colspan="3">
					<table width="100%" border="0">
			      		<tr>
			      			<td>
			      				<strong>Endereço:</strong>
			      				<font color="#FF0000">*</font>
			      			</td>

			      			<td align="right">
      					
      							<logic:present name="colecaoEnderecos">
	 							
	 								<input type="hidden" id="enderecoInformado">
	 							
									<logic:empty name="colecaoEnderecos">
										<input type="button" 
											class="bottonRightCol" 
											value="Adicionar" 
											tabindex="3" 
											id="botaoEndereco" 
											onclick="javascript:abrirPopupComSubmit('exibirInserirEnderecoAction.do?tipoPesquisaEndereco=sistemaParametro', 570, 700, 'Endereco');">
									</logic:empty>
								
									<logic:notEmpty name="colecaoEnderecos">
										<input type="button" 
											class="bottonRightCol" 
											value="Adicionar" 
											tabindex="3" 
											id="botaoEndereco" disabled>
									</logic:notEmpty>
			 
			 					</logic:present>
			 				
			 					<logic:notPresent name="colecaoEnderecos">
									<input type="button" 
										class="bottonRightCol" 
										value="Adicionar" 
										tabindex="3"  
										id="botaoEndereco" 
										onclick="javascript:abrirPopupComSubmit('exibirInserirEnderecoAction.do?tipoPesquisaEndereco=sistemaParametro', 570, 700, 'Endereco');">
			 					</logic:notPresent>
      						</td>
      					</tr>
	 				</table>
	 				</td>
    			</tr>
	     		
	     		<tr>
	         		<td colspan="3" height="50" valign="top">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<table width="100%" border="0" bgcolor="#90c7fc">
								<tr bgcolor="#90c7fc" height="18">
									<td width="10%" align="center"><strong>Remover</strong></td>
									<td align="center"><strong>Endereço</strong></td>
								</tr>
							</table>
							</td>
						</tr>
			
						<logic:present name="colecaoEnderecos">
							<input type="hidden" id="enderecoInformado">

							<tr>
								<td>
								<table width="100%" align="center" bgcolor="#99CCFF">
									<!--corpo da segunda tabela-->
				
								<%	String cor = "#cbe5fe";	%>
				
									<logic:iterate name="colecaoEnderecos" id="endereco">
									
									<%	if (cor.equalsIgnoreCase("#cbe5fe")){	
											cor = "#FFFFFF";	%>
											<tr bgcolor="#FFFFFF" height="18">	
									<%	} else{	
											cor = "#cbe5fe";	%>
											<tr bgcolor="#cbe5fe" height="18">		
									<%	}	%>
					
										<td width="10%" align="center">

											<a href="javascript:if(confirm('Confirma remoção?')){remover();}" 
												alt="Remover">
											<img src="<bean:message key='caminho.imagens'/>Error.gif" 
												width="14" 
												height="14" 
												border="0"></a>
										</td>
							
										<td>
											<a href="javascript:abrirPopup('exibirInserirEnderecoAction.do?exibirEndereco=OK&tipoPesquisaEndereco=sistemaParametro&operacao=1', 570, 700)">
												<bean:write name="endereco" 
													property="enderecoFormatado"/></a>
										</td>
										</tr>
									</logic:iterate>

								</table>
		  						</td>
							</tr>
						</logic:present>
					</table>
   					</td>
  	 			</tr>

				<tr>
					<td width="25%" align="left">
						<strong>Número do Telefone:</strong>
					</td>
					<td>
						<html:text maxlength="9" 
							property="numeroTelefone" 
							size="9" 
							onkeyup="javascript:verificaNumeroInteiro(this);"/>
					</td>
				</tr>

				<tr>
					<td width="25%" align="left">
						<strong>Ramal:</strong>
					</td>
					<td>
						<html:text maxlength="4" 
							property="ramal" 
							size="4" 
							onkeyup="javascript:verificaNumeroInteiro(this);"/>
					</td>
				</tr>
				
				<tr>
					<td width="25%" align="left">
						<strong>Fax:</strong>
					</td>
					<td>
						<html:text maxlength="9" 
							property="fax" 
							size="9" 
							onkeyup="javascript:verificaNumeroInteiro(this);"/>
					</td>
				</tr>

				<tr>
					<td width="25%" align="left">
						<strong>Site:</strong>
					</td>
					<td>
						<html:text maxlength="150" 
							property="site" 
							size="60" style="text-transform: none;" />
					</td>
				</tr>
				
				<tr>
					<td width="25%" align="left">
						<strong>E-Mail:</strong>
					</td>
					<td>
						<html:text maxlength="40" 
							property="email" 

							size="40" style="text-transform: none;" />


					</td>
				</tr>

				<tr>
					<td width="25%" align="left">
						<strong>Número do Telefone de Atendimento:</strong>
					</td>
					<td>
						<html:text maxlength="12" 
							property="numeroTelefoneAtendimento" 
							size="15" />
					</td>
				</tr>
				
				<tr>
					<td width="25%" align="left">
						<strong>Quantidade de dígitos da quadra:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<html:text maxlength="4" 
							property="quantidadeDigitosQuadra" 
							size="4" />
					</td>
				</tr>
				
				<tr>
						<td width="40%">
							<strong>Indicador Quadra Face: </strong>
						</td>
						<td>
							<strong> 
							<html:radio property="indicadorQuadraFace" value="1" /> Sim 
							<html:radio property="indicadorQuadraFace" value="2" /> N&atilde;o
							</strong>
						</td>
				</tr>
				
				<tr>
					<td><strong>Cliente Responsável Programa Especial :</strong></td>
					
					<td>
						
						<html:text maxlength="9" 
							tabindex="1"
							property="idClienteResponsavelProgramaEspecial" 
							size="9"
							onkeyup="javascript:verificaNumeroInteiro(this);"
							onchange="javascript:verificaNumeroInteiroComAlerta(this, 'cliente Responsável Programa Especial');" 
							onkeypress="validaEnterComMensagem(event, 'exibirInformarParametrosSistemaAction.do?objetoConsulta=9','idClienteResponsavelProgramaEspecial','Cliente Responsável Não Identíficados');"/>
							
							<a href="javascript:setarTipoCliente('responsavel');chamarPopup('exibirPesquisarClienteAction.do', 'idClienteResponsavelProgramaEspecial', null, null, 275, 480, '', document.forms[0].idClienteResponsavelProgramaEspecial);">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Cliente Responsável Programa Especial" /></a> 

							<logic:present name="clienteResponsavelProgramaEspecial" scope="request">
								<logic:equal value="true" name="clienteProgramaEspecialEncontrado" scope="request" >
									<html:text property="nomeClienteResponsavelProgramaEspecial" 
										size="45"
										maxlength="45" 
										readonly="true" 
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:equal>
								<logic:equal value="false" name="clienteProgramaEspecialEncontrado" scope="request" >
									<html:text property="nomeClienteResponsavelProgramaEspecial" 
										size="45" 
										maxlength="45" 
										readonly="true" value="Código do Cliente Responsável Programa Especial"
										style="background-color:#EFEFEF; border:0; color: red" />
								</logic:equal>
							</logic:present> 
							
							<logic:notPresent name="clienteResponsavelProgramaEspecial" scope="request">
								<html:text property="nomeClienteResponsavelProgramaEspecial" 
										size="45" 
										maxlength="45" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: red" />
							</logic:notPresent>
							
							
							<a href="javascript:limparClienteResponsavel();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" />
							</a>					
						</td>
				</tr>
				
				<tr> 
						<td><strong>Perfil do Programa Especial:</strong></td>
						<td align="right" colspan="2"><div align="left"> <span class="style2">
				  			<html:select property="perfilProgramaEspecial" tabindex="1">
							<html:option value="<%= ""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>				  
							
							<html:options collection="colecaoPerfisImovel"
								labelProperty="descricao" property="id" />
				  			</html:select>
				  			</span></div>
						</td>
              	</tr>
              	
              	<tr>
					<td width="40%">
						<strong>Indicador Popup de Atualização Cadastral:</strong>
					</td>
					<td><strong> 
						<html:radio property="indicadorPopupAtualizacaoCadastral" value="1" /> Sim 
						<html:radio property="indicadorPopupAtualizacaoCadastral" value="2" /> N&atilde;o 
						</strong>
					</td>
				</tr>
				
				<tr>
					<td width="40%" align="left"><strong>URL Segunda Via de Conta:</strong></td>
					<td><html:text maxlength="60" property="url2ViaConta" size="55" style="text-transform: none;" /></td>
				</tr> 

				<tr>
					<td colspan="2" align="center"><strong>Parâmetros para Relatório:</strong></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td width="25%" align="left">
						<strong>Títulos de Relatório:</strong>
						<font color="#FF0000">*</font>
					</td>
					<td>
						<html:text maxlength="40" 
							property="titulosRelatorio" 
							size="55" />
					</td>
				</tr>

				<tr>
					<td width="25%" align="left">
						<strong>Caminho Imagem da Logomarca:</strong>
						<font color="#FF0000">*</font>
					</td>
					<td>
						<html:text maxlength="30" 
							property="imagemLogomarca"
							style="text-transform: none;"
							size="32" />

					</td>
				</tr>
				
				<tr>
					<td width="25%" align="left">
						<strong>Caminho para Imagem ARPE:</strong>
						<font color="#FF0000">*</font>
					</td>
					<td>
						<html:text maxlength="30" 
							property="imagemArpe"
							style="text-transform: none;"
							size="32" />

					</td>
				</tr>
				
				<tr>
					<td width="25%" align="left">
						<strong>Caminho para Imagem de rodapé da Conta:</strong>
						<font color="#FF0000">*</font>
					</td>
					<td>
						<html:text maxlength="30" 
							property="imagemRodapeConta"
							style="text-transform: none;"
							size="32" />

					</td>
				</tr>

				<tr>
					<td width="25%" align="left">
						<strong>Caminho Imagem do Relatório:</strong>
						<font color="#FF0000">*</font>
					</td>
					<td>
						<html:text maxlength="30" 
							property="imagemRelatorio"
							style="text-transform: none;"
							size="32" />
					</td>
				</tr>

				<tr>
					<td width="25%" align="left">
						<strong>Caminho Imagem da Conta:</strong>
						<font color="#FF0000">*</font>
					</td>
					<td>
						<html:text maxlength="30" 
							property="imagemConta"
							style="text-transform: none;"
							size="32" />

					</td>
				</tr>
				<tr>
					<td width="25%" align="left">
						<strong>Execução do Resumo de Negativação:</strong>
						<font color="#FF0000">*</font>
					</td>
					<td>
						<html:text maxlength="2" 
							property="numeroExecucaoResumoNegativacao"
							size="2" disabled="true"/>
					</td>
				</tr>
				
				<tr>
					<td width="40%">
						<strong>Controlar os autos de infração:</strong>
					</td>
					<td><strong> 
						<html:radio property="indicadorControlaAutoInfracao" value="1" /> Sim 
						<html:radio property="indicadorControlaAutoInfracao" value="2" /> N&atilde;o 
						</strong>
					</td>
				</tr>
			 	<tr>
					<td width="40%">
						<strong>Indicador Exibir Mensagem:</strong>
					</td>
					<td><strong> 
						<html:radio property="indicadorExibirMensagem" value="1" /> Sim 
						<html:radio property="indicadorExibirMensagem" value="2" /> N&atilde;o 
						</strong>
					</td>
				</tr> 
			 	
				<tr>
					<td width="40%">
						<strong>Documento Principal Obrigatório:</strong>
					</td>
					<td><strong> 
						<html:radio property="indicadorDocumentoObrigatorio" value="1" /> Sim 
						<html:radio property="indicadorDocumentoObrigatorio" value="2" /> N&atilde;o 
						</strong>
					</td>
				</tr>
				

				<tr>
					<td width="40%">
						<strong>Consultar Receita Federal:</strong>
					</td>
					<td><strong> 
						<html:radio property="indicadorCpfCnpj" value="1" /> Sim 
						<html:radio property="indicadorCpfCnpj" value="2" /> N&atilde;o 
						</strong>
					</td>
				</tr>

				<!-- Indicador Documento Obrigatorio Manter Cliente - RM10358 - Alterado por Gustavo Amaral - 20/03/2014 -->
				<tr>
					<td width="40%">
						<strong>Documento Obrigatório Manter Cliente:</strong>
					</td>
					<td><strong> 
						<html:radio property="indicadorDocumentoObrigatorioManterCliente" value="1" /> Sim 
						<html:radio property="indicadorDocumentoObrigatorioManterCliente" value="2" /> Não 
						</strong>
					</td>
				</tr>
				
				
				<tr>
					<td width="25%" align="left">
						<strong>Tamanho Máximo de Anexo para Registro de Atendimento:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<html:text maxlength="1" 
							property="tamanhoMaximoAnexoRA" 
							size="2" 
							onkeyup="javascript:verificaNumeroInteiro(this);"/> MB
					</td>
				</tr>
				
				
				<tr>
					<td width="25%" align="left">
						<strong>Redimensionar Imagem para:<font color="#FF0000">*</font></strong>
					</td>
					<td>
			  			<html:select property="resolucaoImagem" tabindex="1">									  
							<html:options collection="colResolucaoImagem"
								labelProperty="descricao" property="id" />
			  			</html:select> pixels        
					</td>	
				</tr>
				

				<tr>
					<td width="25%" align="left">
						<strong>Valor para Emissão de Extrato Tipo Ficha de Compensação:</strong>
					</td>
					<td>
						<html:text maxlength="13" 
							property="valorExtratoFichaComp" 
							size="13"
							onkeyup="javascript:formataValorMonetario(this,13);"
							onblur="javascript:mascara(this, mascaraMoeda);" />
					</td>
				</tr>
				<tr>
					<td width="25%" align="left">
						<strong>Valor para Emissão de Guia de Pagamento no Formato Ficha de Compensação:</strong>
					</td>
					<td>
						<html:text maxlength="13" 
							property="valorGuiaFichaComp" 
							size="13"
							onkeyup="javascript:formataValorMonetario(this,13);"
							onblur="javascript:mascara(this, mascaraMoeda);" />
					</td>
				</tr>
				<tr>
					<td width="25%" align="left">
						<strong>Valor para Emissão de Demonstrativo de Parcelamento no Formato Ficha de Compensação:</strong>
					</td>
					<td>
						<html:text maxlength="13" 
							property="valorDemonstrativoParcelamentoFichaComp" 
							size="13"
							onkeyup="javascript:formataValorMonetario(this,13);"
							onblur="javascript:mascara(this, mascaraMoeda);" />
					</td>
				</tr>
				<tr>
					<td width="25%">
						<strong>Indicador de Uso do Nome Receita e Nome Fantasia em Substituição ao Nome e Nome Abreviado nas Telas Inserir e Manter Cliente:</strong>
					</td>
					<td><strong> 
						<html:radio property="indicadorUsoNMCliReceitaFantasia" value="1" /> Sim 
						<html:radio property="indicadorUsoNMCliReceitaFantasia" value="2" /> N&atilde;o 
						</strong>
					</td>
				</tr>
				<tr>
					<td width="25%">
						<strong>Mês/Ano Faturamento Início Contrato PPP:</strong>
					</td>
					<td>
						<html:text maxlength="7" 
							tabindex="1"
							property="faturamentoInicioContratoPPP"		
							onkeyup="mascaraAnoMes(this, event);somente_numero(this);"					
							onkeypress="javascript:return isCampoNumerico(event);mascaraAnoMes(this, event);"
							onkeydown="mascaraAnoMes(this, event);"
							onblur="javascript:mascara(this, mascaraAnoMes)"
							size="8"/>mm/aaaa
					</td>
				</tr>
				<tr>
					<td width="25%">
						<strong>Percentual PPP:</strong>
					</td>
					<td><html:text maxlength="6" 
							property="percentualPPP" 
							size="8"
							onkeyup="javascript:formataValorMonetario(this, 5);"
							onkeypress="javascript:formataValorMonetario(this, 5);"
							onblur="javascript:mascara(this, mascaraMoeda);"/>
					</td>
				</tr>
				<tr>
					<td width="25%">
						<strong>Quantidade de Dias Faturar Factível:</strong>
					</td>
					<td><html:text maxlength="3"
					        onkeyup="somente_numero(this);"					
							onkeypress="javascript:return isCampoNumerico(event);"
							onkeydown="somente_numero(this);" 
							onblur="javascript:mascara(this, mascaraInteiro);"
							property="quantidadeDiasFaturarFactivel" 
							size="8"/>	
					</td>
				</tr>
				<tr>
					<td width="40%">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>	
				<tr>
					<td colspan="2" align="center"><strong>Dados Gerais de Cadastro</strong></td>
				</tr>
				<p>&nbsp;</p>
				<tr>
					<td width="40%">
						<strong>Indicador Usa Rota:</strong>
					</td>
					<td><strong> 
						<html:radio property="indicadorUsaRota" value="1" /> Sim 
						<html:radio property="indicadorUsaRota" value="2" /> N&atilde;o 
						</strong>
					</td>
				</tr>
				<p>&nbsp;</p>
				
				<tr>
					<td width="15%" align="left">
						<strong>Versão do celular:</strong>
						<font color="#FF0000"></font>
					</td>
					<td>
						<html:text maxlength="10" 
							property="versaoCelular"
							size="10" style="text-transform: none;"
							/>
					</td>
				</tr>				
				
				<tr>
					<td width="15%" align="left">
						<strong>Número de Dias Bloqueio Celular:</strong>
						<font color="#FF0000"></font>
					</td>
					<td>
						<html:text maxlength="4" 
						property="numeroDiasBloqueioCelular" 
						size="4"
						onkeyup="javascript:verificaNumeroInteiro(this);" />
					</td>
				</tr>
								
				<tr>
					<td width="40%" align="left">
						<strong>Percentual de Convergência da Repavimentação:</strong>
					</td>
					<td>
						<html:text maxlength="6" 
							property="percentualConvergenciaRepavimentacao" 
							size="6"
							onkeyup="javascript:formataValorMonetario(this, 5);" />
					</td>
				</tr>
				
				<tr>
					<td width="40%">
						<strong>Cadastrar Cliente em duplicidade:</strong>
					</td>
					<td><strong> 
						<html:radio property="indicadorDuplicidadeCliente" value="1" /> Sim 
						<html:radio property="indicadorDuplicidadeCliente" value="2" /> N&atilde;o 
						</strong>
					</td>
				</tr>
				<tr>
					<td width="40%">
						<strong>Incluir Nome de Cliente com menos de 10 posições:</strong>
					</td>
					<td><strong> 
						<html:radio property="indicadorNomeMenorDez" value="1" /> Sim 
						<html:radio property="indicadorNomeMenorDez" value="2" /> N&atilde;o 
						</strong>
					</td>
				</tr>
				<tr>
					<td width="40%">
						<strong>Incluir Nome de Cliente com descrição genérica:</strong>
					</td>
					<td><strong> 
						<html:radio property="indicadorNomeClienteGenerico" value="1" /> Sim 
						<html:radio property="indicadorNomeClienteGenerico" value="2" /> N&atilde;o 
						</strong>
					</td>
				</tr>
				
				<tr>
					<td width="40%">
						<strong>Variar Hierarquia da Unidade Organizacional:<font color="#FF0000">*</font></strong>
					</td>
					<td><strong> 
						<html:radio property="indicadorVariaHierarquiaUnidade" value="1" /> Sim 
						<html:radio property="indicadorVariaHierarquiaUnidade" value="2" /> N&atilde;o 
						</strong>
					</td>
				</tr>
				<tr>
					<td><strong>Cliente Fictício para Associar os Pagamentos Não Identíficados:</strong></td>
					
					<td>
						
						<html:text maxlength="9" 
							tabindex="1"
							property="clienteFicticioAssociarPagamentosNaoIdentificados" 
							size="9"
							onkeyup="javascript:verificaNumeroInteiro(this);"
							onchange="javascript:verificaNumeroInteiroComAlerta(this, 'Cliente Fictício para Associar os Pagamentos');" 
							onkeypress="validaEnterComMensagem(event, 'exibirInformarParametrosSistemaAction.do?objetoConsulta=7','clienteFicticioAssociarPagamentosNaoIdentificados','Cliente Fictício para Associar os Pagamentos Não Identíficados');"/>
							
							<a href="javascript:setarTipoCliente('ficticio');chamarPopup('exibirPesquisarClienteAction.do', 'clienteFicticio', null, null, 275, 480, '', document.forms[0].clienteFicticioAssociarPagamentosNaoIdentificados);">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Cliente Ficticio Associar Pagamentos Nao Identificados" /></a> 

							<logic:present name="clienteFicticioEncontrado" scope="request">
								<logic:equal value="true" name="clienteFicticioEncontrado" scope="request" >
									<html:text property="nomeClienteFicticioAssociarPagamentosNaoIdentificados" 
										size="45"
										maxlength="45" 
										readonly="true" 
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:equal>
								<logic:equal value="false" name="clienteFicticioEncontrado" scope="request" >
									<html:text property="nomeClienteFicticioAssociarPagamentosNaoIdentificados" 
										size="45" 
										maxlength="45" 
										readonly="true" value="Código do Cliente inexistente"
										style="background-color:#EFEFEF; border:0; color: red" />
								</logic:equal>
							</logic:present> 

							<logic:notPresent name="clienteFicticioEncontrado" scope="request">
								<html:text property="nomeClienteFicticioAssociarPagamentosNaoIdentificados" 
										size="45" 
										maxlength="45" 
										readonly="true" value=""
										style="background-color:#EFEFEF; border:0; color: #000000" />
							</logic:notPresent>
							
							
							<a href="javascript:limparClienteFicticio();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" />
							</a>					
						</td>
				</tr>
				<tr>
					<td width="40%">
						<strong>Indicador de bloqueio funcionalidade inserir/atualizar usuário :</strong>
					</td>
					<td><strong> 
						<html:radio property="indicadorBloquearFunUsuario" value="1" />Sim
						<html:radio property="indicadorBloquearFunUsuario" value="2" />N&atilde;o 
 						</strong>
					</td>
				</tr>
				
				<tr>
					<td width="25%" align="left">
						<strong>Domínio do E-mail corporativo:</strong>
					</td>
					<td>
						<html:text maxlength="20" 
							property="dominioEmailCorporativo" 

							size="40" style="text-transform: none;" />
					</td>
				</tr>
				
				
				<tr>
					<td><strong>Cliente Usuário Desconhecido :</strong></td>
					
					<td>
						
						<html:text maxlength="9" 
							tabindex="1"
							property="clienteUsuarioDesconhecido" 
							size="9"
							onkeyup="javascript:verificaNumeroInteiro(this);"
							onchange="javascript:verificaNumeroInteiroComAlerta(this, 'Cliente Usuário Desconhecido');" 
							onkeypress="validaEnterComMensagem(event, 'exibirInformarParametrosSistemaAction.do?objetoConsulta=8','clienteUsuarioDesconhecido','Cliente Usuário Desconhecido Não Identíficados');"/>
							
							<a href="javascript:setarTipoCliente('desconhecido');chamarPopup('exibirPesquisarClienteAction.do', 'clienteUsuario', null, null, 275, 480, '', document.forms[0].clienteUsuarioDesconhecido);">
								<img width="23" 
									height="21" 
									border="0"
									src="<bean:message key="caminho.imagens"/>pesquisa.gif"
									title="Pesquisar Cliente Usuário Desconhecido" /></a> 

							<logic:present name="clienteUsuarioDesconhecido" scope="request">
								<logic:equal value="true" name="clienteUsuarioDesconhecido" scope="request" >
									<html:text property="nomeClienteUsuarioDesconhecido" 
										size="45"
										maxlength="45" 
										readonly="true" 
										style="background-color:#EFEFEF; border:0; color: #000000" />
								</logic:equal>
								<logic:equal value="false" name="clienteUsuarioDesconhecido" scope="request" >
									<html:text property="nomeClienteUsuarioDesconhecido" 
										size="45" 
										maxlength="45" 
										readonly="true" value="Código do cliente Usuário Desconhecido"
										style="background-color:#EFEFEF; border:0; color: red" />
								</logic:equal>
							</logic:present> 
							
							<logic:notPresent name="clienteUsuarioDesconhecido" scope="request">
								<html:text property="nomeClienteUsuarioDesconhecido" 
										size="45" 
										maxlength="45" 
										readonly="true"
										style="background-color:#EFEFEF; border:0; color: red" />
							</logic:notPresent>
							
							
							<a href="javascript:limparClienteUsuarioDesconhecido();"> 
								<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
									border="0" 
									title="Apagar" />
							</a>					
						</td>
				</tr>
				
				<tr>
					<td width="40%"><strong>Data Limite para Cadastramento no Sorteio:</strong></td>
					<td><html:text property="dataLimiteCadastroSorteio" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);somente_numero(this);"
						onkeypress="javascript:return isCampoNumerico(event);" />
					<strong>(dd/mm/aaaa)</strong></td>
				</tr>
				<tr>
					<td width="40%"><strong>Data do Sorteio:</strong></td>
					<td><html:text property="dataSorteio" size="10"
						maxlength="10" onkeyup="mascaraData(this, event);somente_numero(this);"
						onkeypress="javascript:return isCampoNumerico(event);" />
					<strong>(dd/mm/aaaa)</strong></td>
				</tr>
				<p>&nbsp;</p>
				<p>&nbsp;</p>			 	
				<tr>
					<td></td>
					<td><strong><font color="#FF0000">*</font></strong>Campos obrigat&oacute;rios</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<div align="right">
							<jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=1" />
						</div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>

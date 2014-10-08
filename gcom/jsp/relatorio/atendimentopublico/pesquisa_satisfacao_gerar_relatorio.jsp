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
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirMaterialActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	var bCancel = false; 

    function validateGerarRelatorioPesquisaSatisfacaoActionForm(form) {                                                                   
    	if (bCancel) 
      		return true; 
        else 
       		return validateCaracterEspecial(form) && validateRequired(form) && validateDate(form) && validateInteger(form);
   	}

	function caracteresespeciais () { 
	    this.aa = new Array("idUnidade", "Unidade de Atendimento possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	    this.ab = new Array("idImovel", "Imóvel possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	    this.ac = new Array("dataPesquisaInicio", "Data Inicial de Pesquisa possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	    this.ad = new Array("dataPesquisaFim", "Data Final de Pesquisa possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	}
    
    function required () { 
	    this.aa = new Array("dataPesquisaInicio", "Informe Data de Início da pesquisa.", new Function ("varName", " return this[varName];"));
	    this.ab = new Array("dataPesquisaFim", "Informe Data Final da pesquisa.", new Function ("varName", " return this[varName];"));
	} 

    function DateValidations () { 
    	this.aa = new Array("dataPesquisaInicio", "Data de Início inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    	this.ab = new Array("dataPesquisaFim", "Data Final inválida.", new Function ("varName", "this.datePattern='dd/MM/yyyy';  return this[varName];"));
    } 

    function IntegerValidations () { 
	    this.aa = new Array("idImovel", "Imóvel deve ser numérico.", new Function ("varName", " return this[varName];"));
	    this.ab = new Array("idUnidade", "Unidade de Atendimento deve ser numérico.", new Function ("varName", " return this[varName];"));
    	this.ac = new Array("dataPesquisaInicio", "Data Inicial deve ser numérica.", new Function ("varName", " return this[varName];"));
    	this.ad = new Array("dataPesquisaFim", "Data Final deve ser numérica.", new Function ("varName", " return this[varName];"));
    }
    
    function validarForm(){
		var form = document.forms[0];
		
		if(validateGerarRelatorioPesquisaSatisfacaoActionForm(form)){
			submeterFormPadrao(form);
		}
	}
	
	function limparForm() {
		var form = document.forms[0];
		
		form.idImovel.value = "";
		form.descricaoImovel.value = "";
		form.idUnidade.value = "";
		form.descricaoUnidade.value = "";
		form.criterio.selectedIndex = 0;
		form.dataPesquisaInicio.value = "";
		form.dataPesquisaFim.value = "";
	
		formatarRadio();	
	}
	
	function formatarRadio(){
		var form = document.forms[0];
		form.tipo[1].checked = true;
	}
	
	function replicarCampo(fim,inicio) {
    	fim.value = inicio.value;
	}
	
	function limparImovel() {
	 	document.forms[0].idImovel.value = '';
	 	document.forms[0].descricaoImovel.value = '';
	}
	
	function limparUnidadeAtendimento(){
		document.forms[0].idUnidade.value = '';
		document.forms[0].descricaoUnidade.value = '';
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];
		
		form.action = 'exibirGerarRelatorioPesquisaSatisfacaoAction.do';
	    if (tipoConsulta == 'imovel') {
		    form.idImovel.value = codigoRegistro;
		    form.descricaoImovel.value = descricaoRegistro;
		    form.descricaoImovel.style.color = "#000000";
			submeterFormPadrao(form);
	    }
	    
	    if(tipoConsulta == 'unidadeOrganizacional') {
	    	form.idUnidade.value = codigoRegistro;
      		form.descricaoUnidade.value = descricaoRegistro;
      		form.descricaoUnidade.style.color = "#000000";
	    	submeterFormPadrao(form);	
	    }    
	}
	
</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<html:form action="/gerarRelatorioPesquisaSatisfacaoAction" method="post"
	name="GerarRelatorioPesquisaSatisfacaoActionForm"
	type="gcom.gui.relatorio.atendimentopublico.GerarRelatorioPesquisaSatisfacaoActionForm"
	onsubmit="return validateGerarRelatorioPesquisaSatisfacaoActionForm(this);">
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
			<td width="610" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>

			<!--Início Tabela Reference a Páginação da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Gerar Relatório Pesquisa de Satisfa&ccedil;&atilde;o do Cliente na Loja de Atendimento</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			
			<table width="100%" border="0">
				<tr>
      				<td colspan="2">Para filtrar, informe os dados abaixo:</td>
      			</tr>
      			
      			<tr>
      				<td>&nbsp;</td>
      			</tr>
      			
      			<tr>
			      	<td height="30"><strong>Data de Pesquisa:<font color="#FF0000">*</font></strong></td>
			        <td width="70%" colspan="3" ><strong>
			        	<html:text property="dataPesquisaInicio" size="10" maxlength="10" tabindex="2"
							onkeypress="replicarCampo(document.forms[0].dataPesquisaFim,this); return isCampoNumerico(event);"
							onkeyup="mascaraData(this, event); replicarCampo(document.forms[0].dataPesquisaFim,this);" />
						<a href="javascript:abrirCalendarioReplicando('GerarRelatorioPesquisaSatisfacaoActionForm', 'dataPesquisaInicio', 'dataPesquisaFim');" >
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
								 width="20" border="0" align="absmiddle" title="Exibir Calendário"/>
					 	</a> a</strong> 
						<html:text  property="dataPesquisaFim" size="10" maxlength="10" tabindex="3" 
							onkeypress="return isCampoNumerico(event);" onkeyup="mascaraData(this, event);" /> 
						<a href="javascript:abrirCalendario('GerarRelatorioPesquisaSatisfacaoActionForm', 'dataPesquisaFim')">
							<img border="0" src="<bean:message key="caminho.imagens"/>calendario.gif"
								 width="20" border="0" align="absmiddle" title="Exibir Calendário" />
						</a>dd/mm/aaaa
					</td>
				</tr>
				
				<tr>
			    	<td HEIGHT="30"><strong>Unidade de Atendimento:</strong></td>
			        <td colspan="2">
			        	<html:text property="idUnidade" size="5" maxlength="4" tabindex="8" onkeypress="validaEnter(event, 'exibirGerarRelatorioPesquisaSatisfacaoAction.do?tipoConsulta=unidade', 'idUnidade');return isCampoNumerico(event);"/>
						<a href="javascript:abrirPopup('exibirPesquisarUnidadeOrganizacionalAction.do', 410, 790);">
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" alt="Pesquisar" border="0" title="Pesquisar Unidade de Atendimento">
						</a>
						<logic:present name="idUnidadeNaoEncontrado" scope="request">
							<html:text maxlength="30" property="descricaoUnidade" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" size="36" />
						</logic:present> 
						<logic:notPresent name="idUnidadeNaoEncontrado" scope="request">
							<html:text maxlength="30" property="descricaoUnidade" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" size="36" />
						</logic:notPresent>
			        	<a href="javascript:limparUnidadeAtendimento();">
			        		<img src="<bean:message key='caminho.imagens'/>limparcampo.gif" alt="Apagar" border="0" title="Apagar">
			        	</a>
					</td>
			    </tr>
			    
			    <tr>
					<td width="30%"><strong>Critério:</strong></td>
					<td width="70%"><html:select property="criterio" tabindex="17" >
						<html:option value="-1">&nbsp;</html:option>
						<html:option value="1">Avaliação do Atendente</html:option>
						<html:option value="2">Agilidade do Atendimento</html:option>
						<html:option value="3">Tempo de Espera</html:option>
						<html:option value="4">Conforto e Limpeza do Ambiente</html:option>
						<html:option value="5">Localização do Atendimento</html:option>
						<html:option value="6">Segurança</html:option>
						<html:option value="7">Estacionamento</html:option>
					</html:select> </td>
				</tr>
				
				<tr>
					<td width="25%"><strong>Matrícula do Imóvel:</strong></td>
					<td width="75%"><html:text maxlength="9" name="GerarRelatorioPesquisaSatisfacaoActionForm" property="idImovel" size="9"
						onkeypress="javascript:validaEnter(event, 'exibirGerarRelatorioPesquisaSatisfacaoAction.do?tipoConsulta=imovel', 'idImovel');return isCampoNumerico(event);" />
						<a href="javascript:abrirPopup('exibirPesquisarImovelAction.do', 410, 790);">
							<img width="23" height="21" title="Pesquisar Imóvel" src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0" />
						</a>
						<logic:present name="idImovelNaoEncontrado" scope="request">
							<html:text maxlength="30" property="descricaoImovel" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #ff0000" size="36" />
						</logic:present> 
						<logic:notPresent name="idImovelNaoEncontrado" scope="request">
							<html:text maxlength="30" property="descricaoImovel" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" size="36" />
						</logic:notPresent>
						<a href="javascript:limparImovel();"> <img
							border="0" title="Apagar" src="imagens/limparcampo.gif" height="21" width="23">
						</a>
					</td>
				</tr>
				
				<tr>					    
					<td width="26%">
					  <STRONG>Tipo de Relat&oacute;rio:<FONT color=#ff0000>*</FONT></STRONG>
					</td>						
					<td width="74%">
					  <html:radio property="tipo" value="1" />Anal&iacute;tico						  
					  <html:radio property="tipo" value="2" />Sint&eacute;tico						 
					</td>
				</tr>
				
				<tr>
					<td height="19"><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<input name="Button" type="button"
							class="bottonRightCol" value="Limpar" align="left"
							onclick="javascript:limparForm();">
						<input tabindex="10" name="Button" type="button" class="bottonRightCol" value="Cancelar" align="left"
							onclick="window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right" height="24"><input type="button" name="Button"
						class="bottonRightCol" value="Gerar" onclick="javascript:validarForm();" />
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
	</tr>
</html:form>
</body>
</html:html>


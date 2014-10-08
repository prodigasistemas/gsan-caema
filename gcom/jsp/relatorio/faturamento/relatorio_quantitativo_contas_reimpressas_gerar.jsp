<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="GerarRelatorioQuantitativoContasReimpressasActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript">
	
	var bCancel = false; 

    function validateGerarRelatorioQuantitativoContasReimpressasActionForm(form) {                                                                   
    	if (bCancel) 
      		return true; 
        else 
       		return validateCaracterEspecial(form) && validateRequired(form) && validateMesAno(form) && validateInteger(form);
   	}
   	
   	function caracteresespeciais () { 
	    this.aa = new Array("anoMesReferencia", "Mês/Ano Referência possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	    this.ab = new Array("localidade", "Localidade possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	    this.ac = new Array("setorComercial", "Setor Comercial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
		this.ad = new Array("rota", "Rota possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	}
	
	function required () { 
	    this.aa = new Array("anoMesReferencia", "Informe Mês/Ano Referência.", new Function ("varName", " return this[varName];"));
	}
	
	function MesAnoValidations () {
    	this.aa = new Array("anoMesReferencia", "Mês/ano de Referência inválido.", new Function ("varName", " return this[varName];"));
   	}
   	
   	function IntegerValidations () { 
	    this.ab = new Array("localidade", "Localidade deve ser numérico.", new Function ("varName", " return this[varName];"));
    	this.ac = new Array("setorComercial", "Setor Comercial deve ser numérico.", new Function ("varName", " return this[varName];"));
    	this.ad = new Array("rota", "Rota deve ser númerico.", new Function ("varName", "return this[varName];"));
    }
	
	function validarForm(){
		var form = document.forms[0];
		
		if(validateGerarRelatorioQuantitativoContasReimpressasActionForm(form)){
			javascript:botaoAvancarTelaEspera('/gsan/gerarRelatorioQuantitativoContasReimpressasAction.do');	
		}
	}
	
	function reloadForm(){
		var form = document.forms[0];
	
		form.action='exibirGerarRelatorioQuantitativoContasReimpressasAction.do?menu=sim';
	    form.submit();	
	}
	
  	function limpar(){
  		var form = document.forms[0];
  		
  		form.action='exibirGerarRelatorioQuantitativoContasReimpressasAction.do?limpar=sim';
	    form.submit();
  	}
  	
  	function limparBorracha(tipo){
		var form = document.forms[0];
		
		switch(tipo){
			case 1: //Localidade
				form.localidade.value = "";
				form.nomeLocalidade.value = "";
				form.setorComercial.value = "";
		     	form.nomeSetorComercial.value = "";
		     	form.rota.value = "";
				break;
			
			case 2: //Setor Comercial
		    	form.setorComercial.value = "";
		     	form.nomeSetorComercial.value = "";
		     	form.rota.value = "";
		     	break;
		    
		    case 3: //Rota
		    	form.rota.value = "";
		    	break;	
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];

		if (tipoConsulta == 'localidade') {
      		form.localidade.value = codigoRegistro;
	  		form.nomeLocalidade.value = descricaoRegistro;
	  		form.nomeLocalidade.style.color = "#000000";
	  		
	  		form.setorComercial.focus();
	  	}

		if (tipoConsulta == 'setorComercial') {
			form.setorComercial.value = codigoRegistro;
		  	form.nomeSetorComercial.value = descricaoRegistro;
		  	form.nomeSetorComercial.style.color = "#000000";

	  		form.rota.focus();
	  	}
	}
	
	function receberRota(codigoRota,destino) {
	 	var form = document.forms[0];
		
		if(destino == "inicial"){
			form.rota.value = codigoRota;		
			form.empresa.focus();
		}
	}
	
	function pesquisarSetorComercial(){
		var form = document.forms[0];
		
		if(form.localidade.value == ""){
			alert("Informe Localidade");
		}
		else{
			abrirPopup('exibirPesquisarSetorComercialAction.do?idLocalidade='+form.localidade.value, 275, 480);
		}
	}
	
	function pesquisarRota(destino){
		var form = document.forms[0];
	   	var msg = '';
	   
	   	if(form.localidade.value == ""){
			msg = 'Informe Localidade \n';
	   	}
	   
	   	if(form.setorComercial.value == ""){
			msg = msg +'Informe Setor Comercial\n';
	   	}
	   
		if( msg != '' ){
			alert(msg);
	   	}else{
			abrirPopup('exibirPesquisarRotaAction.do?idLocalidade='+form.localidade.value+'&codigoSetorComercial='+form.setorComercial.value+'&destino='+destino, 250, 495);
		}
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.mesAnoFaturamento}');">

<div id="formDiv">
<html:form action="/gerarRelatorioQuantitativoContasReimpressasAction.do"
	name="GerarRelatorioQuantitativoContasReimpressasActionForm"
	type="gcom.gui.relatorio.faturamento.GerarRelatorioQuantitativoContasReimpressasActionForm"
	method="post">

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

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Relat&oacute;rio Quantitativo de Contas Reimpressas</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para gerar o relat&oacute;rio, informe os dados abaixo:</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td style="width:120px;"><strong>Mês/Ano de Referência: </strong><font color="#FF0000">*</font></td>
					<td>
						<html:text maxlength="7" tabindex="1" property="anoMesReferencia" size="8" 
							onkeyup="mascaraAnoMes(this, event);" onkeypress="return isCampoNumerico(event);" />&nbsp;mm/aaaa
					</td>
				</tr>

				<tr>
					<td><strong>Ger&ecirc;ncia Regional: </strong></td>
					<td>
						<strong> 
							<html:select property="gerenciaRegional" style="width: 230px;" onchange="javascript:reloadForm();">
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								
								<logic:present name="colecaoGerenciaRegional" scope="request">
									<html:options collection="colecaoGerenciaRegional" labelProperty="nome" property="id" />
								</logic:present>
							</html:select> 														
						</strong>
					</td>
				</tr>

				<tr>
					<td><strong>Unidade de Neg&oacute;cio: </strong></td>
					<td>
						<strong> 
							<html:select property="unidadeNegocio" style="width: 230px;" onchange="javascript:reloadForm();" >
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
						
								<logic:present name="colecaoUnidadeNegocio" scope="request">
									<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
								</logic:present>
							</html:select> 														
						</strong>
					</td>
				</tr>				
              	
				<tr>
					<td><strong>Localidade: </strong></td>
					<td colspan="3">
						<html:text maxlength="3" tabindex="1" property="localidade" size="3"
							onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioQuantitativoContasReimpressasAction.do?objetoConsulta=1','localidade','Localidade');return isCampoNumerico(event);">
						</html:text>
							
						<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 400, 800);">
							<img width="23" height="21" border="0" style="cursor:hand;" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Localidade" />
						</a>
								
						<logic:present name="localidadeNaoEncontrada" scope="request">
							<html:text property="nomeLocalidade" size="30" maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:present> 

						<logic:notPresent name="localidadeNaoEncontrada" scope="request">
							<html:text property="nomeLocalidade" size="30" maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent>

						
						<a href="javascript:limparBorracha(1);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
						</a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Setor Comercial: </strong></td>
					<td colspan="3">
						<html:text maxlength="3" tabindex="1" property="setorComercial" size="3"
							onkeypress="validaEnterDependenciaComMensagem(event, 'exibirGerarRelatorioQuantitativoContasReimpressasAction.do?objetoConsulta=2', this, document.forms[0].localidade.value, 'Localidade', 'Setor Comercial');return isCampoNumerico(event);">
						</html:text>
							
						<a href="javascript:pesquisarSetorComercial();">
							<img width="23" height="21" border="0" style="cursor:hand;" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Setor Comercial" />
						</a>
								
						<logic:present name="setorComercialNaoEncontrado" scope="request">
							<html:text property="nomeSetorComercial" size="30" maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />							
						</logic:present> 

						<logic:notPresent name="setorComercialNaoEncontrado" scope="request">
							<html:text property="nomeSetorComercial" size="30" maxlength="30" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent>
						
						<a href="javascript:limparBorracha(2);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
						</a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Rota: </strong></td>
					<td align="left" colspan="3">
						<html:text maxlength="5" tabindex="1" property="rota" size="5" onkeypress="return isCampoNumerico(event);" /> 
						
						<a href="javascript:pesquisarRota('inicial');">
							<img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Rota" />
						</a>
						
						<a href="javascript:limparBorracha(3);"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
						</a>						
					</td>
				</tr>
				
				<tr>
					<td><strong>Empresa:</strong></td>
					<td>
						<strong> 
							<html:select property="empresa" style="width: 230px;" tabindex="2">
								<html:option
									value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
								</html:option>
						
								<logic:present name="colecaoEmpresa" scope="request">
								   <html:options collection="colecaoEmpresa" labelProperty="descricao" property="id"/>
								</logic:present>
							</html:select> 														
						</strong>
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
					<td height="24" colspan="2">
			          	<input type="button" class="bottonRightCol" value="Cancelar" 
			          		onclick="javascript:window.location.href='/gsan/telaPrincipal.do'"/>	

			          	<input type="button" class="bottonRightCol" value="Limpar" 
			          		onclick="javascript:limpar();"/>
					</td>
				
					<td align="right">
						<input type="button" name="Button" class="bottonRightCol" value="Gerar" 
							onClick="javascript:validarForm()" />
					</td>
					
				</tr>							
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
<%@ include file="/jsp/util/rodape.jsp"%>	
</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>

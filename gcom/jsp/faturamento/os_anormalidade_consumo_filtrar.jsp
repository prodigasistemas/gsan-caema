<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gcom.util.ConstantesSistema"%>
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="OsAnormalidadeConsumoFiltrarActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
<script language="JavaScript">
<!-- Begin
var bCancel = false; 

function validate(form) {                                                                   
    if (bCancel) 
  return true; 
    else 
   return validateCaracterEspecial(form) && validateLong(form); 
} 

function caracteresespeciais () { 
 this.ab = new Array("localidadeOrigemID", "Localidade da inscrição inicial possui caracteres especiais.", new Function ("varName", " return this[varName];"));
 this.af = new Array("localidadeDestinoID", "Localidade da inscrição final possui caracteres especiais.", new Function ("varName", " return this[varName];"));
} 

function IntegerValidations () { 
 this.ab = new Array("localidadeOrigemID", "Localidade da inscrição inicial deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
this.af = new Array("localidadeDestinoID", "Localidade da inscrição final deve somente conter números positivos.", new Function ("varName", " return this[varName];"));
} 

function chamarPesquisaLocalidadeInicial() {
	document.forms[0].tipoPesquisa.value = 'inicial';
	abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800);
}

function chamarPesquisaLocalidadeFinal() {
	document.forms[0].tipoPesquisa.value = 'final';
	abrirPopup('exibirPesquisarLocalidadeAction.do?tipo=imovelLocalidade', 400, 800);
}
	
function replicaDados(campoOrigem, campoDestino)
{
	campoDestino.value = campoOrigem.value;
}


function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.OsAnormalidadeConsumoFiltrarActionForm;

	 if (tipoConsulta == 'localidade') {
	     
	    if (form.tipoPesquisa.value == 'inicial') {
	    	form.localidadeDestinoID.value = codigoRegistro;
	        form.nomeLocalidadeDestino.value = descricaoRegistro;
	  	  form.nomeLocalidadeDestino.style.color = "#000000";	  
		    form.localidadeOrigemID.value = codigoRegistro;
		    form.nomeLocalidadeOrigem.value = descricaoRegistro;
		    form.nomeLocalidadeOrigem.style.color = "#000000";
		    form.localidadeOrigemID.focus();
	    } else {
	 		form.localidadeDestinoID.value = codigoRegistro;
		    form.nomeLocalidadeDestino.value = descricaoRegistro;
		    form.nomeLocalidadeDestino.style.color = "#000000";
	    }
    }

	if (tipoConsulta == 'localidade') {
      form.localidadeDestinoID.value = codigoRegistro;
      form.nomeLocalidadeDestino.value = descricaoRegistro;
	  form.nomeLocalidadeDestino.style.color = "#000000";	  
	  if(form.localidadeOrigemID.value == "")
	  {
	  	form.localidadeOrigemID.value = codigoRegistro;
	  }
	  if(form.nomeLocalidadeOrigem.value == "")
	  {
	  	form.nomeLocalidadeOrigem.value = descricaoRegistro;
	  }
	  
	}	
	

}


function limparLocalidade(tipo) {
    var form = document.OsAnormalidadeConsumoFiltrarActionForm;
   	switch (tipo){
   		case 1:
   	   		
	   		if (!form.localidadeOrigemID.readOnly) {
			    form.localidadeDestinoID.readOnly = false;
			}
	    	form.localidadeDestinoID.value = "";
		    form.nomeLocalidadeDestino.value = "";
		    form.localidadeOrigemID.value = "";
		    form.nomeLocalidadeOrigem.value = "";
  	    break;   
		case 2:
   			form.localidadeDestinoID.value = "";
		    form.nomeLocalidadeDestino.value = "";
  	    break;   
   }
}


function limparOrigem(tipo){
		var form = document.forms[0];
		
		switch(tipo){
			case 1: //De localidade pra baixo
		
				form.nomeLocalidadeOrigem.value = "";
				form.localidadeDestinoID.value = "";
				form.nomeLocalidadeDestino.value = "";
				form.localidadeOrigemID.value = "";
		}
	}
	
function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){

	if (objeto == null || codigoObjeto == null){
		abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
	}
	else{
		if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
			alert(msg);
		}
		else{
			abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto , altura, largura);
		}
	}
}
  
  
function validarForm(form){
	
	if(form.anoMesReferencia.value != ""){
		
		 if(!verificaAnoMes(form.anoMesReferencia)){				
			  return false;			
		 }else{
			 form.submit();
		}
			
	}else{
		alert("Informe o Ano/Mês Referência.")
	}
	
}


function campoObrigatorio(campoDependencia, dependente, msg){
	if (dependente.value.length < 1){
		return false
	}
	else if (campoDependencia.value.length < 1){
		alert(msg);
		campoDependencia.focus();
		return true
	}
}


  function bloquearLocalidadeDestino(){
    var form = document.OsAnormalidadeConsumoFiltrarActionForm;
      if(form.localidadeOrigemID.value.length > 0){
        form.localidadeDestinoID.readOnly = true;
      }else{
        form.localidadeDestinoID.readOnly = false;
      }
  }
  
  
  
  function chmarPopupQuadra() {
  	var form = document.OsAnormalidadeConsumoFiltrarActionForm;
     if(form.localidadeOrigemID.value == "" && form.localidadeDestinoID.value == ""){
		alert(" Informe Localidade ");      
     }else{
	    abrirPopup('exibirSelecionarQuadraImovelInserirManterContaAction.do?idLocalidade='+form.localidadeOrigemID.value, 275, 480);	
	}
  }

  function limparLocalidadeAjax(){
		var form = document.forms[0];
	
			$.ajax({
			   type: "POST",
			   url: "exibirFiltrarOsAnormalidadeConsumoAction.do?limpaLocalidade=ok",
			   data: "",
			   success: function(msg){
				   
			   }
		 	});

	}
  
-->
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/exibirExcluirOsAnormalidadeConsumoAction"
	name="OsAnormalidadeConsumoFiltrarActionForm"
	type="gcom.gui.faturamento.OsAnormalidadeConsumoFiltrarActionForm"
	method="post"
	onsubmit="return validateOsAnormalidadeConsumoFiltrarActionForm(this);"
	>

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	<input type="hidden" name="tipoPesquisa" />

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
			<p>&nbsp;</p>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Filtrar Comando OS de Consumo Anormalidades </td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">
					<table width="100%">
						<tr>
							<td>Para gerar ordens de serviço nos casos de BC, AC e EC, informe os dados abaixo:</td>
							
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td><strong>Mês/Ano Referência:<font color="#FF0000">*</font></strong></td>
				    <td colspan="6">
				    	<span class="style2">
				    		<strong> 
								<html:text property="anoMesReferencia" onkeyup="mascaraAnoMes(this, event);" size="9" maxlength="7"></html:text>
							</strong>                
						</span>
					</td>
				</tr>
				<tr>
					<td><strong>Grupo:</strong></td>
				    <td colspan="6">
				    	<span class="style2">
				    		<strong> 
								<html:select property="idGrupo" style="width: 230px;">
									<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
									<html:options collection="colecaoGrupo" labelProperty="descricao" property="id" />
								</html:select>
							</strong>                
						</span>
					</td>
				</tr>
				
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%" align="center" bgcolor="#99CCFF" border="0">
							<tr>
								<td colspan="2">
									<strong>Informe os dados da inscri&ccedil;&atilde;o inicial:</strong>
								</td>
							</tr>
							
							<tr bgcolor="#cbe5fe">
								<td width="100%" align="center" colspan="2">
									<table width="100%" border="0">
										<tr bgcolor="#cbe5fe">
											<td width="20%"><strong>Localidade:</strong></td>
											<td colspan="3">
												<html:text maxlength="3"
													property="localidadeOrigemID" size="3"
													onkeypress="validaEnterComMensagem(event, 'exibirFiltrarOsAnormalidadeConsumoAction.do?objetoConsulta=1&inscricaoTipo=origem','localidadeOrigemID','Código da localidade de origem');return isCampoNumerico(event);"
													onkeyup="replicaDados(document.forms[0].localidadeOrigemID, document.forms[0].localidadeDestinoID);"
													tabindex="1" /> 
													<a href="javascript:chamarPesquisaLocalidadeInicial();"><img
													   src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"
													   width="23" height="21" title="Pesquisar Localidade">
													</a> 
												<logic:present name="corLocalidadeOrigem">
									
													<logic:equal name="corLocalidadeOrigem" value="exception">
														<html:text property="nomeLocalidadeOrigem" size="45"
															readonly="true"
															style="background-color:#EFEFEF; border:0; color: #ff0000" />
													</logic:equal>
									
													<logic:notEqual name="corLocalidadeOrigem" value="exception">
														<html:text property="nomeLocalidadeOrigem" size="45"
															readonly="true"
															style="background-color:#EFEFEF; border:0; color: #000000" />
													</logic:notEqual>
									
												</logic:present>
												<logic:notPresent name="corLocalidadeOrigem">
									
													<logic:empty name="OsAnormalidadeConsumoFiltrarActionForm"
															property="localidadeOrigemID">
															<html:text property="nomeLocalidadeOrigem" value="" size="45"
																readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000" />
													</logic:empty>
													<logic:notEmpty name="OsAnormalidadeConsumoFiltrarActionForm"
														property="localidadeOrigemID">
														<html:text property="nomeLocalidadeOrigem" size="45"
															readonly="true"
															style="background-color:#EFEFEF; border:0; color: 	#000000" />
													</logic:notEmpty>
												</logic:notPresent> 
												<a href="javascript:limparLocalidade(1);limparLocalidadeAjax();"> 
													<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
														 border="0" title="Apagar" /> 
												</a>
											</td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td colspan="4">
						<table width="100%" align="center" bgcolor="#99CCFF" border="0">
							<tr>
								<td colspan="2">
									<strong>Informe os dados da inscri&ccedil;&atilde;o Final:</strong></td>
							</tr>
							<tr bgcolor="#cbe5fe">
								<td width="100%" align="center" colspan="2">
									<table width="100%" border="0">
										<tr bgcolor="#cbe5fe">
											<td width="20%"><strong>Localidade:</strong></td>
											<td colspan="3" height="24">
											<html:text  maxlength="3"
														property="localidadeDestinoID" 
														size="3" 
														tabindex="6"
														onkeypress="validaEnterComMensagem(event, 'exibirFiltrarOsAnormalidadeConsumoAction.do?objetoConsulta=1&inscricaoTipo=destino','localidadeDestinoID','Código da localidade de destino');return isCampoNumerico(event);" /> 
														<a href="javascript:chamarPesquisaLocalidadeFinal();"><img
													   src="<bean:message key="caminho.imagens"/>pesquisa.gif" border="0"
													   width="23" height="21" title="Pesquisar Localidade">
													</a> 
											<logic:present name="corLocalidadeDestino">
						
												<logic:equal name="corLocalidadeDestino" value="exception">
													<html:text property="nomeLocalidadeDestino" size="45"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #ff0000" />
												</logic:equal>
						
												<logic:notEqual name="corLocalidadeDestino" value="exception">
													<html:text property="nomeLocalidadeDestino" size="45"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:notEqual>
						
											</logic:present> 
											<logic:notPresent name="corLocalidadeDestino">
						
												<logic:empty name="OsAnormalidadeConsumoFiltrarActionForm"
													property="localidadeDestinoID">
													<html:text property="nomeLocalidadeDestino" value="" size="45"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: #000000" />
												</logic:empty>
												<logic:notEmpty name="OsAnormalidadeConsumoFiltrarActionForm"
													property="localidadeDestinoID">
													<html:text property="nomeLocalidadeDestino" size="45"
														readonly="true"
														style="background-color:#EFEFEF; border:0; color: 	#000000" />
												</logic:notEmpty>
											</logic:notPresent> 
											<a href="javascript:limparLocalidade(2);limparLocalidadeAjax();"> 
											 <img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
													 border="0" 
													 title="Apagar" /> 
											</a>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
	       	 <tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
	        <tr>
				<td>&nbsp;</td>
				<td align="left"><font color="#FF0000">*</font> Campo
				Obrigat&oacute;rio</td>
			</tr>
					
			
					
			
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3" class="style1">
						<input name="Submit22"
							   class="bottonRightCol" 
							   value="Limpar" 
							   tabindex="16" 
							   type="button"
							   onclick="window.location.href='/gsan/exibirFiltrarOsAnormalidadeConsumoAction.do?menu=sim';">
					&nbsp; 
						<input type="button" 
							   name="Button" 
							   class="bottonRightCol"
							   value="Cancelar" 
							   tabindex="17"
							   onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"
							   style="width: 80px" />
					</td>
					<td align="right">
						<input type="button" name="Button" value="Filtrar" 
							class="bottonRightCol"	tabindex="18" onclick="javascript:validarForm(document.forms[0]);"
								 />
								
					</td>
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

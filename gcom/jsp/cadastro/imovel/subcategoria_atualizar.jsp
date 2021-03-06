<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.ConstantesSistema,java.util.Collection"%>

<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ page import="gcom.util.Pagina,gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>


<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarSubcategoriaActionForm"	dynamicJavascript="false" />


<script language="Javascript">
<!--

var bCancel = false; 

    function validateFiltrarSubcategoriaActionForm(form){
	if(bCancel){
		return true;
    }else{
       return testarCampoValorZero(document.FiltrarSubcategoriaActionForm.codigoSubcategoria, 'C�digo da Subcategoria') 
       && validateCaracterEspecial(form) 
       && validateRequired(form) 
       && validateLong(form);
   	}	
}  	

    function caracteresespeciais () { 
     	this.aa = new Array("codigoSubcategoria", "C�digo da Subcategoria possui caracteres especiais.", new Function ("varName", " return this[varName];"));
     	this.ac = new Array("descricaoSubcategoria", "Descri��o da Subcategoria possui caracteres especiais.", new Function ("varName", " return this[varName];"));
    } 
	function IntegerValidations(){
		this.ab = new Array("codigoSubcategoria", "C�digo da Subcategoria deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	}

    function required () { 
     	this.aa = new Array("codigoSubcategoria", "Informe C�digo da Subcategoria.", new Function ("varName", " return this[varName];"));
     	this.ac = new Array("descricaoSubcategoria", "Informe Descri��o da Subcategoria.", new Function ("varName", " return this[varName];"));
    } 
    
-->
</script>


<script language="Javascript">
<!--
    
function desabilitaCategoria(){
  var form = document.FiltrarSubcategoriaActionForm;
  if (form.idCategoria.value != ''){
      form.idCategoria.disabled = true;
   }   
}

// Faz as valida��es do formul�rio
function validarForm(form){
	if(validateFiltrarSubcategoriaActionForm(form)){
	    submeterFormPadrao(form);
	}
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5" onload="javascript:desabilitaCategoria();">

<html:form action="/atualizarSubcategoriaAction" method="post">

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
			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Atualizar Subcategoria</td>
					<td width="11" valign="top"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<!-- In�cio do Corpo - Fernanda Paiva -->
			<p>&nbsp;</p>

			<table bordercolor="#000000" border="0" width="100%" cellspacing="0">
				<tr>
					<td colspan="2">
					<p>Para atualizar uma subcategoria, informe os dados abaixo:</p>
					<p>&nbsp;</p>
					</td>
				<logic:present scope="application" name="urlHelp">
						<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroSubcategoriaAtualizar', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
						<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>								
					</logic:notPresent>
				</tr>
				</table>
				<table bordercolor="#000000" border="0" width="100%" cellspacing="0">
				<tr>
					<td><strong>Categoria:<font color="#FF0000">*</font></strong></td>
					<td align="right">
					<div align="left"><strong> <html:select property="idCategoria">
						<html:options collection="collectionImovelCategoria"
							labelProperty="descricao" property="id" />
					</html:select></strong></div>
					</td>
				</tr>
				<tr>
					<td><strong>C&oacute;digo da Subcategoria:<font color="#FF0000">*</font></strong></td>
					<td align="right">
					<div align="left"><html:text property="codigoSubcategoria" size="4"
						maxlength="4" readonly="true" style="background-color:#EFEFEF; border:0;"/></div>
					</td>
				</tr>
				<tr>
					<td><strong>Descri&ccedil;&atilde;o da Subcategoria:<font
						color="#FF0000">*</font></strong></td>
					<td align="right">
					<div align="left"><html:text property="descricaoSubcategoria" size="50"
						maxlength="50" /></div>
					</td>
				</tr>
				<tr>
					<td><strong>Descri&ccedil;&atilde;o Abreviada:</strong></td>
					<td align="right">
					<div align="left"><html:text property="descricaoAbreviada" size="20"
						maxlength="20" /></div>
					</td>
				</tr>
				<tr>
					<td><strong>C�digo da Tarifa Social:</strong></td>
					<td align="right">
					<div align="left"><html:text property="codigoTarifaSocial" size="1"
						maxlength="1" /></div>
					</td>
				</tr>
				<tr>
					<td><strong>C�digo do Grupo da Subcategoria:</strong></td>
					<td align="right">
					<div align="left"><html:text property="codigoGrupoSubcategoria" size="4"
						maxlength="4" /></div>
					</td>
				</tr>
				<tr>
					<td><strong>Fator Fiscaliza��o:<font color="#FF0000">*</font></strong></td>
					<td align="right">
					<div align="left"><html:text property="numeroFatorFiscalizacao" size="2"
						maxlength="2" /></div>
					</td>
				</tr>
				<tr>
					<td>
					<strong>Indicador da Tarifa de Consumo:</strong></td>
					<td align="right">
					<div align="left"><html:radio property="indicadorTarifaConsumo"
						value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" /> 
						<strong>Sim</strong>
					<html:radio property="indicadorTarifaConsumo"
						value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" />
						<strong>N�o</strong></div>
					</td>
				</tr>
				<tr>
					<td>
					<strong>Indicador de Sazonalidade de Abastecimento:<font color="#FF0000">*</font></strong></td>
					<td align="right">
					<div align="left"><html:radio property="indicadorSazonalidade"
						value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" /> 
						<strong>Sim</strong>
					<html:radio property="indicadorSazonalidade"
						value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" />
						<strong>N�o</strong></div>
					</td>
				</tr>
				<tr>
					<td>
					<strong>Indicador de Uso:</strong></td>
					<td align="right">
					<div align="left"><html:radio property="indicadorUso"
						value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" /> <strong>Ativo</strong>
					<html:radio property="indicadorUso"
						value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" />
					<strong>Inativo</strong></div>
					</td>
				</tr>
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr> 
					<td>
						<logic:present scope="session" name="manter">
							<input name="Submit222"
								class="bottonRightCol" value="Voltar" type="button"
								onclick="javascript:history.back();">
						</logic:present>
						<logic:notPresent scope="session" name="manter">
							<logic:present scope="session" name="filtrar_manter">
								<input name="Submit222"
								class="bottonRightCol" value="Voltar" type="button"
								onclick="javascript:history.back();">
							</logic:present>
							<logic:notPresent scope="session" name="filtrar_manter">
								<input name="Submit222"
									class="bottonRightCol" value="Voltar" type="button"
									onclick="window.location.href='/gsan/exibirFiltrarSubcategoriaAction.do?menu=sim';">
							</logic:notPresent>
						</logic:notPresent>
					
						<input name="Submit22"
							class="bottonRightCol" value="Desfazer" type="button"
							onclick="window.location.href='/gsan/exibirAtualizarSubcategoriaAction.do';">	
						<input name="Submit23" class="bottonRightCol" value="Cancelar"
							type="button"
							onclick="window.location.href='/gsan/telaPrincipal.do'"> 
					</td>
					<td align="right">
					<gsan:controleAcessoBotao name="Button" value="Atualizar" onclick="javascript:validarForm(document.forms[0]);" url="atualizarSubcategoriaAction.do" align="left"/><!-- 
						<input type="button" name="Button" class="bottonRightCol" value="Atualizar" onClick="validarForm(document.forms[0]);" />
				-->	</td>
				</tr>
				
			</table>

			<p>&nbsp;</p>
			<!-- Fim do Corpo - Fernanda Paiva --></td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	<script>
	
			setCookie("desativaHistoryBack", "true", "1");
		
		</script>
</html:form>
</body>
</html:html>

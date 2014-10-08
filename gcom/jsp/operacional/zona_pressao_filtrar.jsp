<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.util.ConstantesSistema"%>

<html:html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarZonaPressaoActionForm" dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(formulario){
		if(validateFiltrarZonaPressaoActionForm(formulario)){
			if(validateInteger){
				botaoAvancarTelaEspera('/gsan/filtrarZonaPressaoAction.do');
			}
		}
	}

	function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}

    function carregar(tipo){
		var form = document.forms[0];
		
		redirecionarSubmit('exibirFiltrarZonaPressaoAction.do?objetoConsulta=' + tipo);
	}	
	
</script>

</head>

<body leftmargin="5" topmargin="5">
<div id="formDiv">
<html:form action="/filtrarZonaPressaoAction"
	name="FiltrarZonaPressaoActionForm"
	type="gcom.gui.operacional.FiltrarZonaPressaoActionForm"
	method="post"
	onsubmit="return validateFiltrarZonaPressaoActionForm(this);">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

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
			<td width="615" valign="top" class="centercoltext">
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
					<td class="parabg">Filtrar Zona de Press&atilde;o</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para filtrar uma zona de press&atilde;o, informe os dados
					abaixo:</td>
					<td width="100" align="right"><html:checkbox
						property="indicadorAtualizar" value="1" /><strong>Atualizar</strong></td>
				</tr>
				<tr>
					<td><strong>C&oacute;digo:</strong></td>
					<td><html:text property="id" size="2" maxlength="2" /><font
						size="1">&nbsp;(somente números)</font> <br>
					<font color="red"><html:errors property="id" /></font></td>
					<td>&nbsp;</td>
				</tr>
		       <tr>
					<td><strong>Descri&ccedil;&atilde;o:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricao" size="30" maxlength="30" /> </span></td>
			   </tr>
				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa" tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto <html:radio property="tipoPesquisa"
						tabindex="6"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Descri&ccedil;&atilde;o Abreviada:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricaoAbreviada" size="6" maxlength="6" /> </span></td>
				</tr>
      			
      			<tr>
					<td><strong> Sistema de Abastecimento:</strong> <span class="style2">
					<strong> <font color="#FF0000"></font> </strong> </span></td>
					<td><strong> <html:select property="idSistemaAbastecimento"
					style="width: 230px;" onchange="carregar(1);">
					<html:option
						value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
					</html:option>

					<logic:present name="colecaoSistemaAbastecimento" scope="session">
					<html:options collection="colecaoSistemaAbastecimento"
						labelProperty="descricao" property="id" />

					</logic:present>
					</html:select> </strong></td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong> Subsistema de Abastecimento:</strong> <span class="style2">
					<strong> <font color="#FF0000"></font> </strong> </span></td>
					<td><strong> <html:select property="idSubsistemaAbastecimento"
					style="width: 230px;" onchange="carregar(2);">
					<html:option
						value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
					</html:option>

					<logic:present name="colecaoSubsistemaSistemaAbastecimento" scope="session">
					<html:options collection="colecaoSubsistemaSistemaAbastecimento"
						labelProperty="subsistemaAbastecimento.descricao" property="subsistemaAbastecimento.id" />

					</logic:present>
					</html:select> </strong></td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><strong> Setor de Abastecimento:</strong> <span class="style2">
					<strong> <font color="#FF0000"></font> </strong> </span></td>
					<td><strong> <html:select property="idSetorAbastecimento"
					style="width: 230px;" onchange="carregar(3);">
					<html:option
						value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
					</html:option>

					<logic:present name="colecaoSetorSubsistemaAbastecimento" scope="session">
					<html:options collection="colecaoSetorSubsistemaAbastecimento"
						labelProperty="setorAbastecimento.descricao" property="setorAbastecimento.id" />

					</logic:present>
					</html:select> </strong></td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					 <td><strong>Distrito Operacional:</strong></td>
					 <td>
			  			<html:select property="idDistritoOperacional" tabindex="5" style="width:230px;height:20px;" onchange="carregar(4);">
							<html:option value="-1"> &nbsp; </html:option>
							<logic:present name="colecaoDistritoOperacional" scope="session">
								<logic:iterate name="colecaoDistritoOperacional" id="distritoOperacional">
									<html:option value="${distritoOperacional[0]}"> ${distritoOperacional[1]} </html:option>
								</logic:iterate>
							</logic:present>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td><strong> Área Operacional:</strong> <span class="style2">
					<strong> <font color="#FF0000"></font> </strong> </span></td>
					<td><strong> <html:select property="idAreaOperacional" style="width: 230px;">
							<html:option
								value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
							</html:option>

							<logic:present name="colecaoAreaOperacionalHelper" scope="session">
								
								<html:options collection="colecaoAreaOperacionalHelper"
								labelProperty="descricaoAreaOperacional" property="idAreaOperacional" />
		
							</logic:present>
						</html:select> </strong></td>
					<td>&nbsp;</td>
				</tr>
      			
				<tr>
					<td><strong>Indicador de uso:</strong></td>
					<td><html:radio property="indicadorUso" tabindex="2" value="1" /><strong>Ativo</strong>
						<html:radio property="indicadorUso" tabindex="3" value="2" /><strong>Inativo</strong>
						<html:radio property="indicadorUso" tabindex="4" value="3" /><strong>Todos</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
					
				<tr>
					<td colspan="2"><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirFiltrarZonaPressaoAction.do?menu=sim'"
						tabindex="8"></td>
					<td width="65" align="right"><input name="Button2" type="button"
						class="bottonRightCol" value="Filtrar" align="right"
						onClick="javascript:validarForm(document.forms[0]);" tabindex="9"></td>
				</tr>
			</table>

		</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>

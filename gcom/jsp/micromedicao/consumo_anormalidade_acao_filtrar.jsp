<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gcom.util.ConstantesSistema" isELIgnored="false"%>
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript">

function validaForm() {
  	botaoAvancarTelaEspera('/gsan/filtrarConsumoAnormalidadeAcaoAction.do?menu=sim');		 	    		  		  
}
function validateFiltrarConsumoAnormalidadeAcaoActionForm(form) { return true;}
     
function limparForm() {
	var form = document.FiltrarConsumoAnormalidadeAcaoActionForm;
	form.consumoAnormalidade.value = "-1";
	form.categoria.value = "-1";
    form.imovelPerfil.value = "-1";
    form.indicadorUso.value = "1";
		
}

function reload() {
	var form = document.FiltrarConsumoAnormalidadeAcaoActionForm;
	form.action = "/gsan/exibirFiltrarConsumoAnormalidadeAcaoAction.do";
	form.submit();
}  

function verificarChecado(valor){
	form = document.forms[0];
	if(valor == "1"){
	 	form.indicadorAtualizar.checked = true;
	 }else{
	 	form.indicadorAtualizar.checked = false;
	}
}
</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="verificarChecado('${sessionScope.indicadorAtualizar}');">
<div id="formDiv">
<html:form action="/filtrarAnormalidadeLeituraAction"
	name="FiltrarConsumoAnormalidadeAcaoActionForm"
	type="gcom.gui.micromedicao.FiltrarConsumoAnormalidadeAcaoActionForm"
	method="post" >

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
					<td class="parabg">Filtrar Consumo Anormalidade e Ação</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="85%" border="0">
				<tr>
					<td height="10" colspan="2">Para filtrar um Consumo Anormalidade e
					Ação, informe os dados abaixo:</td>
					<td width="80" align="right"><html:checkbox property="indicadorAtualizar" value="1" /><strong>Atualizar</strong>
				</td>
				
				<!-- Consumo Anormalidade -->
				<tr>
					<td><strong>Consumo Anormalidade:</strong></td>
					<td colspan="2" align="left"><html:select property="consumoAnormalidade">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoConsumoAnormalidade"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<!-- Categoria -->
				<tr>
					<td><strong>Categoria:</strong></td>
					<td colspan="2" align="left"><html:select property="categoria">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoCategoria"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Perfil do Imóvel:</strong></td>
					<td colspan="2" align="left"><html:select property="imovelPerfil">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoImovelPerfil"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Uso:</strong></td>
					<td><strong> <html:radio property="indicadorUso" value="1" />
					<strong>Ativo <html:radio property="indicadorUso" value="2" />
					Inativo <html:radio property="indicadorUso" value="3" />
					<strong>Todos 
					</strong> </strong></td>

				</tr>
				
								
				<tr>
					<td><strong> <font color="#FF0000"> <input type="button"
						name="Submit22" class="bottonRightCol" value="Limpar"
						onClick="javascript:window.location.href='/gsan/exibirFiltrarConsumoAnormalidadeAcaoAction.do?menu=sim'"><!-- <input type="button"
								name="Submit23" class="bottonRightCol" value="Cancelar"
								onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"> -->
					</font> </strong></td>
					<td colspan="2" align="right"><input type="button" name="Submit2"
						class="bottonRightCol" value="Filtrar"
						onclick="validaForm(document.forms[0]);"></td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</tr>
		<!-- Rodapé -->
		<%@ include file="/jsp/util/rodape.jsp"%>
	</table>
	<p>&nbsp;</p>

	<tr>

	</tr>
</html:form>
</div>
	
</body>
	
</html:html>
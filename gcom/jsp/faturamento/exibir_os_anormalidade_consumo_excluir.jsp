<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ page import="gcom.util.Pagina,gcom.util.ConstantesSistema,java.util.Collection"%>
<%@ page import="gcom.faturamento.ComandoOsAnormalidade"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.util.Util"%>
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

var bCancel = false; 

function facilitador(objeto){
	if (objeto.value == "0"){
		objeto.value = "1";
		marcarTodos();
	}
	else{
		objeto.value = "0";
		desmarcarTodos();
	}
}

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

	
function replicaDados(campoOrigem, campoDestino)
{
	campoDestino.value = campoOrigem.value;
}


function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	var form = document.OsAnormalidadeConsumoFiltrarActionForm;

	if (tipoConsulta == 'localidadeOrigem') {
      form.localidadeOrigemID.value = codigoRegistro;
	  form.nomeLocalidadeOrigem.value = descricaoRegistro;
	  if(form.localidadeDestinoID.value == "")
	  {
	  	form.localidadeDestinoID.value = codigoRegistro;
	  }
	  if(form.nomeLocalidadeDestino.value == "")
	  {
	  	form.nomeLocalidadeDestino.value = descricaoRegistro;
	  }
	  
	  form.nomeLocalidadeOrigem.style.color = "#000000";
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

function remover(objeto){

	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma remoção?")) {
			document.forms[0].action = "/gsan/excluirOsAnormalidadeConsumoAction.do"
			document.forms[0].submit();
		 }
	}
}

function limparLocalidade(tipo) {
    var form = document.OsAnormalidadeConsumoFiltrarActionForm;
   	switch (tipo){
   		case 1:
   	   		
	   		if (!form.localidadeOrigemID.readOnly) {
			    form.localidadeDestinoID.readOnly = false;
			}alert('we')
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
	    abrirPopup('exibirSelecionarQuadraImovelInserirManterContaAction.do?idLocalidade='+form.localidadeOrigemID.value+'&codigoSetorComercial='+form.setorComercialOrigemCD.value, 275, 480);	
	}
  }

  function limparLocalidadeAjax(){
		var form = document.forms[0];
	
			$.ajax({
			   type: "POST",
			   url: "exibirFiltrarGerarOsAnormalidadeConsumoAction.do?limpaLocalidade=ok",
			   data: "",
			   success: function(msg){
				   
			   }
		 	});

	}
  
</script>
</head>

<body leftmargin="5" topmargin="5">
<html:form action="/exibirFiltrarOsAnormalidadeConsumoAction"
	name="OsAnormalidadeConsumoFiltrarActionForm"
	type="gcom.gui.faturamento.OsAnormalidadeConsumoFiltrarActionForm"
	method="post"
	onsubmit="return validateOsAnormalidadeConsumoFiltrarActionForm(this);"
	>

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
			<p>&nbsp;</p>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Excluir Comando OS para Anormalidade de Consumo </td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="5" bgcolor="#000000" height="2"></td>
				</tr>
				<tr bordercolor="#000000">
					<td width="7%" bgcolor="#90c7fc" height="20">
					<div align="center"><strong><a href="javascript:facilitador(this);">Todos</a></strong></div>
					</td>
					<td width="15%" align="center" bgcolor="#90c7fc"><strong>Comando</strong></td>
					<td width="59%" align="center" bgcolor="#90c7fc"><strong>Referência</strong></td>
									
				</tr>

				<tr>
					<td colspan="5">
					<table width="100%" bgcolor="#99CCFF">


						<%--Esquema de paginação--%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg" />
							<pg:param name="q" />
							<logic:present name="colComandoOsAnormalidade">
								<%int cont = 1;%>
								<logic:iterate name="colComandoOsAnormalidade" id="comandoOsAnormalidade" type="ComandoOsAnormalidade">
									<pg:item>
										<%cont = cont + 1;
			if (cont % 2 == 0) {%>
										<tr bgcolor="#FFFFFF">
											<%} else {

			%>
										<tr bgcolor="#cbe5fe">
											<%}%>
											
												<td width="6%">
												<div align="center"><input type="checkbox"
													name="idRegistrosRemocao"
													value="<bean:write name="comandoOsAnormalidade" property="id"/>" /></div>
												</td>
												<td width="15%">
													<bean:write name="comandoOsAnormalidade" property="id" />
												</td>
												
												<td width="59%">
													<a href="consultarOsAnormalidadeConsumoAction.do?id=<bean:write name="comandoOsAnormalidade" property="id" />">
														<%= Util.formatarAnoMesParaMesAno(comandoOsAnormalidade.getAnoMesReferencia())%>
													</a>
											    </td>
											
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
					</table><br>
					<table width="100%" border="0">
						<tr>
							<td valign="top">
								<input type="button" class="bottonRightCol"
								value="Remover" name="removerOsAnormalidadeConsumo" onclick="remover(idRegistrosRemocao);"/>
												
							
								<input name="button"
								type="button" class="bottonRightCol" value="Voltar Filtro"
								onclick="window.location.href='<html:rewrite page="/exibirFiltrarOsAnormalidadeConsumoAction.do"/>'"
								align="left" style="width: 80px;">
							</td>		
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td>
					<div align="center"><strong><%@ include
						file="/jsp/util/indice_pager_novo.jsp"%></strong></div>
					</td>
					</pg:pager>
					<%-- Fim do esquema de paginação --%>
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
	<jsp:include
		page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioLogradouroManterAction.do" />
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>
			
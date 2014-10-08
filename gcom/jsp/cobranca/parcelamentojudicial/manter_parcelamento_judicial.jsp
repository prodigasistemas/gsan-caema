<%@page import="gcom.util.ConstantesSistema"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
	<title>COMPESA - GSAN</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

	<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
	
	<%@ page import="gcom.util.ConstantesSistema"%>

	<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
	
	<html:javascript staticJavascript="false" formName="ManterParcelamentoJudicialActionForm" />
	
	<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
	
	<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
	
	<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
	
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery_util.js"></script>
	<script type="text/javascript">

	function validarForm(){

		var form = document.forms[0];

		if(validateManterParcelamentoJudicialActionForm(form) && camposVazios()){

			form.action='manterParcelamentoJudicialObterListaParcelamentoJudicialAction.do';
			form.submit();
		}
	}

	function camposVazios(){
		var form = document.forms[0];

		if(form.codigoClienteResponsavel.value=="" && form.codigoClienteUsuario.value==""
			&& form.processoJudicial.value=="" && form.matriculaImovel.value==""
				&& form.periodoInicialParcelamento.value=="" && form.periodoFinalParcelamento.value==""){
				alert("Informe pelo menos uma opção de seleção");
				return false;
		}else{
				return true;
			}
	}
	
	function replicarPeriodo(){
		var form = document.forms[0];

		form.periodoFinalParcelamento.value = form.periodoInicialParcelamento.value;

	}

	function limpar(){

		var form = document.forms[0];

		form.codigoClienteResponsavel.value = "";
		form.nomeClienteResponsavel.value = "";
		form.codigoClienteUsuario.value = "";
		form.nomeClienteUsuario.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.periodoInicialParcelamento.value = "";
		form.periodoFinalParcelamento.value = "";
		form.processoJudicial.value = "";

		form.action='exibirManterParcelamentoJudicialAction.do?menu=sim';
	    form.submit();
		
	}

	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){


		if(objetoRelacionado.disabled != true && objetoRelacionado.readOnly != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			}
			else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}
				else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisa=" + tipo, altura, largura);
				}
			}
		}
	}
		

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    var form = document.forms[0];

	   if(tipoConsulta == 'cliente'){


		  if(form.tipoPesquisa.value != null && form.tipoPesquisa.value == 'clienteResponsavel'){
		      form.codigoClienteResponsavel.value = codigoRegistro;
		      form.nomeClienteResponsavel.value = descricaoRegistro;
		   }

		   if(form.tipoPesquisa.value != null && form.tipoPesquisa.value == 'clienteUsuario'){

			   form.codigoClienteUsuario.value = codigoRegistro;
			   form.nomeClienteUsuario.value = descricaoRegistro;
			   
		   }

		}

		if(tipoConsulta=="imovel"){

			if(form.tipoPesquisa.value!=null && form.tipoPesquisa.value=="imovel"){
				form.matriculaImovel.value=codigoRegistro;
				form.inscricaoImovel.value=descricaoRegistro;
    		}

			
		}
	      
	}
		
	
	function limparMatriculaImovelForm() {
		
		var form = document.forms[0];
		
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
	}
	
	function limparMatriculaImovelTecla() {
		var form = document.forms[0];
	
		form.inscricaoImovel.value = "";
	}

	function habilitarPesquisaMatriculaImovel(form){
		if (form.matriculaImovel.disabled == false) {
			form.tipoPesquisa.value = 'imovel';
			chamarPopup('exibirPesquisarImovelAction.do?', 'imovel', null, null, 275, 480, '',form.matriculaImovel.value);
		}
		
	}

	function habilitarPesquisaClienteResponsavel(form) {
		if (form.codigoClienteResponsavel.disabled == false) {
			form.tipoPesquisa.value = 'clienteResponsavel';
			chamarPopup('exibirPesquisarClienteAction.do?indicadorUsoTodos=1&limparForm=OK', 'clienteResponsavel', null, null, 275, 480, '',form.codigoClienteResponsavel.value);
		}	
	}	

	function habilitarPesquisaClienteUsuario(form) {
		if (form.codigoClienteUsuario.disabled == false) {
			form.tipoPesquisa.value = 'clienteUsuario';
			chamarPopup('exibirPesquisarClienteAction.do?indicadorUsoTodos=1&limparForm=OK', 'clienteUsuario', null, null, 275, 480, '',form.codigoClienteUsuario.value);
		}	
	}	
		
	function limparClienteResponsavelForm() {
		
		var form = document.forms[0];
		
		form.codigoClienteResponsavel.value = "";
		form.nomeClienteResponsavel.value = "";
	}

	function limparClienteResponsavelTecla() {
		var form = document.forms[0];
	
		form.nomeClienteResponsavel.value = "";
	}
	
	function limparClienteUsuarioForm() {
		
		var form = document.forms[0];
		
		form.codigoClienteUsuario.value = "";
		form.nomeClienteUsuario.value = "";
	}

	function limparClienteUsuarioTecla() {
		var form = document.forms[0];
	
		form.nomeClienteUsuario.value = "";
	}

	function replicarPeriodo(){
		var form = document.forms[0];

		form.periodoFinalParcelamento.value = form.periodoInicialParcelamento.value

	}

	function verifcarPopupVisualizarArquivo(verificadorPopup){

		var verificador = verificadorPopup;
		
		if(verificadorPopup==true){
			window.close();
		}
	}
	
	</script>
	
	</head>
	
	<body leftmargin="5" topmargin="5" onload="verifcarPopupVisualizarArquivo(${requestScope.verificadorPopup});">
		
		<div id="formDiv">
		<html:form action="/manterParcelamentoJudicialObterListaParcelamentoJudicialAction"
		 name="ManterParcelamentoJudicialActionForm"
		 type="gcom.cobranca.parcelamentojudicial.ManterParcelamentoJudicialActionForm"
		 method="post">
			
		<input type="hidden" name="tipoPesquisa" />
		
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
					
					<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
					<table height="100%">
		
						<tr>
							<td></td>
						</tr>
					</table>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
							<td class="parabg">Filtrar Parcelamento Judicial </td>
							<td width="11" valign="top"><img border="0"
								src="imagens/parahead_right.gif" /></td>
						</tr>
					</table>
					<p>&nbsp;</p>
					
					<table width="100%">
					
						<tr>
						
							<td colspan="2">
								Para filtrar o Parcelamento Judicial, informe os dados abaixo:
							</td>
							
						</tr>
						
						<tr>
						
							<td>
							
								<strong>C&oacute;digo do Cliente Respons&aacute;vel:</strong>
							
							</td>
							
							<td>
							
								<html:text property="codigoClienteResponsavel" maxlength="9" size="8"
								onkeyup="validaEnter(event, 'exibirManterParcelamentoJudicialAction.do', 'codigoClienteResponsavel');javaScript:somente_numero_zero_a_nove(this);"
								onkeypress="limparClienteResponsavelTecla();"
								onblur="somente_numero_zero_a_nove(this);"
								/>		
								
								<a href="javascript:habilitarPesquisaClienteResponsavel(document.forms[0]);">	
										<img width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											style="cursor:hand;" alt="Pesquisar" border="0"/></a> 
									<logic:present name="codigoClienteResponsavelInexistente" scope="request">
										<html:text property="nomeClienteResponsavel" size="35" maxlength="35"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" />
									</logic:present>
									<logic:notPresent name="codigoClienteResponsavelInexistente" scope="request">
										<html:text property="nomeClienteResponsavel" size="35" maxlength="35"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notPresent> 
									<a href="javascript:limparClienteResponsavelForm();">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"border="0" title="Apagar" />
									</a>
							</td>
							
						</tr>
						
						<tr>
								
							<td>
							
								<strong>C&oacute;digo do Cliente Usu&aacute;rio:</font></strong>
							
							</td>
							
							<td>
								
								<html:text property="codigoClienteUsuario" maxlength="9" size="8"
								onkeyup="validaEnter(event, 'exibirManterParcelamentoJudicialAction.do', 'codigoClienteUsuario');javaScript:somente_numero_zero_a_nove(this);"
								onkeypress="limparClienteUsuarioTecla();"
								onblur="somente_numero_zero_a_nove(this);"
								/>	
								<a href="javascript:habilitarPesquisaClienteUsuario(document.forms[0]);">	
										<img width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											style="cursor:hand;" alt="Pesquisar" border="0"/></a> 
									<logic:present name="codigoClienteUsuarioInexistente" scope="request">
										<html:text property="nomeClienteUsuario" size="35" maxlength="35"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" />
									</logic:present>
									<logic:notPresent name="codigoClienteUsuarioInexistente" scope="request">
										<html:text property="nomeClienteUsuario" size="35" maxlength="35"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notPresent> 
									<a href="javascript:limparClienteUsuarioForm();">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"border="0" title="Apagar" />
									</a>			
							</td>
							
						</tr>
						
						<tr>
						
							<td>
							
								<strong>Matr&iacute;cula do Im&oacute;vel:</strong>
							
							</td>
							
							<td>
							
							<html:text property="matriculaImovel"
							onkeyup="validaEnter(event, 'exibirManterParcelamentoJudicialAction.do', 'matriculaImovel');javaScript:somente_numero_zero_a_nove(this);"
							size="8"
							onkeypress="limparMatriculaImovelTecla();"
							onblur="somente_numero_zero_a_nove(this);"
							/>
									<a href="javascript:habilitarPesquisaMatriculaImovel(document.forms[0]);">	
										<img width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											style="cursor:hand;" alt="Pesquisar" border="0"/></a> 
									<logic:present name="matriculaInexistente" scope="request">
										<html:text property="inscricaoImovel" size="35" maxlength="35"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #ff0000" />
									</logic:present>
									<logic:notPresent name="matriculaInexistente" scope="request">
										<html:text property="inscricaoImovel" size="35" maxlength="35"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:notPresent> 
									<a href="javascript:limparMatriculaImovelForm();">
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"border="0" title="Apagar" />
									</a>								
							</td>
							
						</tr>
						
				<tr>
					<td><strong>Processo Judicial:</strong></td>
					
					<td>
						
						<html:text property="processoJudicial" size="23"
						maxlength="25"
						tabindex="5"
						styleClass="tipoProcessoJudicial"/>
						
					</td>
				</tr>
				
				<tr>
					<td><strong>Per&iacute;odo Parcelamento:</strong></td>
					
					<td>
					
					<html:text property="periodoInicialParcelamento" maxlength="10" size="11"
					tabindex="1"
					onkeyup="javascript:replicarCampo( document.forms[0].periodoFinalParcelamento, document.forms[0].periodoInicialParcelamento );mascaraData(this, event);"
					onkeypress="return isCampoNumerico(event);replicarPeriodo();"
					/>
					<a href="javascript:abrirCalendarioReplicando('ManterParcelamentoJudicialActionForm', 'periodoInicialParcelamento', 'periodoFinalParcelamento');">
							<img border="0" 
								src="<bean:message key='caminho.imagens'/>calendario.gif" 
								width="16" 
								height="15" 
								border="0" alt="Exibir Calendário" 
								tabindex="2"/></a>
						
					&nbsp;
					<strong>a</strong>	
					<html:text property="periodoFinalParcelamento" maxlength="10" size="11"
					tabindex="1"
					onkeyup="mascaraData(this, event);"
					onkeypress="return isCampoNumerico(event);replicarPeriodo();"
					/>
					<a href="javascript:abrirCalendarioReplicando('ManterParcelamentoJudicialActionForm', 'periodoFinalParcelamento');">
							<img border="0" 
								src="<bean:message key='caminho.imagens'/>calendario.gif" 
								width="16" 
								height="15" 
								border="0" alt="Exibir Calendário" 
								tabindex="2"/></a>
					<strong>dd/mm/aaaa</strong>
					</td>
				</tr>
				
				
				<tr height="30px">
				
				<td colspan="3"></td>
				
				</tr>				
				
				<tr>
				
				<td align="left"><input type="button" value="Limpar"
					class="bottonRightCol" onclick="javascript:limpar();"/>
					<input type="button" value="Cancelar" class="bottonRightCol"
					 onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
				</td>
				
				<td align="right"><input type="button" value="Filtrar" class="bottonRightCol" onclick="javascript:validarForm();"/></td>
				
				</tr>
				
			</table>
			
			</td>
			
			</tr>
			
			</table>
			<%@ include file="/jsp/util/rodape.jsp"%> 
		 </html:form>
		</div>
	</body>
</html:html>
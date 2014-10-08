<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"  formName="FiltrarProcessoActionForm" />

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>
<script language="JavaScript">

	$(document).ready(function(){
		desabilitaCobrancaGrupo();
		desabilitaCobrancaAcao();
		desabilitaFaturamentoGrupo();
		
		$('[name=idCobrancaGrupo]').change(function(){
			desabilitaCobrancaAcao();
			desabilitaFaturamentoGrupo();
		});
		
		$('[name=idFaturamentoGrupo]').change(function(){
			desabilitaCobrancaAcao();
			desabilitaCobrancaGrupo();
		});				
	});

	function submeter(){
		botaoAvancarTelaEspera('/gsan/filtrarProcessoAction.do');
	}
	
	function desabilitaCobrancaGrupo(){
		var form = document.forms[0];
		//Desabilita Grupo de Cobrança se Faturamento Grupo for selecionado. 
		if (form.idFaturamentoGrupo.selectedIndex!=-1 && form.idFaturamentoGrupo.selectedIndex!=0){
			form.idCobrancaGrupo.disabled = true;	
			form.idCobrancaGrupo.selectedIndex=-1
		} else {
			form.idCobrancaGrupo.disabled = false;
		}
	}
	
	function desabilitaFaturamentoGrupo(){
		var form = document.forms[0];
		//Desabilita Faturamento Grupo se Grupo de Cobrança for selecionado. 
		if (form.idCobrancaGrupo.selectedIndex!=-1 && form.idCobrancaGrupo.selectedIndex!=0){
			form.idFaturamentoGrupo.disabled = true;	
			form.idFaturamentoGrupo.selectedIndex=-1
		} else {
			form.idFaturamentoGrupo.disabled = false;
		}
	}
	
	function desabilitaCobrancaAcao(){
		var form = document.forms[0];
		//Desabilita ACAO COBRANCA se Grupo de Cobrança for selecionado. 
		if (form.idCobrancaGrupo.selectedIndex==-1 || form.idCobrancaGrupo.selectedIndex==0){
			form.idCobrancaAcao.disabled = true;	
			form.idCobrancaAcao.selectedIndex=-1
		} else {
			form.idCobrancaAcao.disabled = false;
		}
	}
	
	function validarForm(form){

		if(testarCampoValorZero(document.FiltrarProcessoActionForm.idProcesso, 'Código do Processo') && testarCampoValorZero(document.FiltrarProcessoActionForm.idProcessoIniciadoPrecedente, 'Código do Processo Iniciado Precedente')){

			if(validateFiltrarProcessoActionForm(form)){
		    	form.submit();
			}
		}
	}

	function limparPesquisarProcesso() {
		var form = document.forms[0];
	
		form.idProcesso.value = "";
	    form.descricaoProcesso.value = "";
	}
	
	 //Recebe os dados do(s) popup(s)
  	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];

	    if (tipoConsulta == 'processo') {
      		limparPesquisarProcesso();
	      	
	      	form.idProcesso.value = codigoRegistro;
	      	form.descricaoProcesso.value = descricaoRegistro;
	      	form.descricaoProcesso.style.color = "#000000";
	      	form.idProcesso.focus();
	    }
	    
	    if (tipoConsulta == 'usuario') {
      		limparPesquisarUsuario();
	      	
	      	form.usuarioId.value = codigoRegistro;
	      	form.usuarioDescricao.value = descricaoRegistro;
	      	form.usuarioDescricao.style.color = "#000000";
	      	form.usuarioId.focus();
	    }
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
					abrirPopup(url + "?" + "menu=sim&" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	}
	
	function limparPesquisarUsuario(){
		var form = document.forms[0];
		
		form.usuarioId.value = '';
		form.usuarioDescricao.value = '';
	}
	
	function filtrarProcessos(){
		var form = document.forms[0].submit();
		
		if(form.dataAgendamentoInicial != "")
			if(validaData(form.dataAgendamentoInicial))
		if (validaData(form.dataAgendamentoInicial) && validaData(form.dataAgendamentoFinal)) {
					
				form.submit();						
		}
	
	}
	
</script>
</head>

<body leftmargin="5" 
	topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<div id="formDiv">
<html:form action="/filtrarProcessoAction.do"
	name="FiltrarProcessoActionForm"
	type="gcom.gui.batch.FiltrarProcessoActionForm" 
	method="post"
	onsubmit="return validateFiltrarProcessoActionForm(this);">

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
					<td class="parabg">Filtrar Processo Iniciado</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>

				</tr>
			</table>

			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para consultar processos iniciados, informe os
					dados abaixo:</td>
					<td width="227">&nbsp;</td>
				</tr>
				
				<tr>
					<td height="31"><strong>Processo:</strong></td>
					
					<td colspan="3">
						<html:text property="idProcesso" size="5" maxlength="5"
							onkeypress="validaEnter(event, '/gsan/exibirFiltrarProcessoAction.do', 'idProcesso'); return isCampoNumerico(event);" 
							/>

					<a href="javascript:abrirPopup('exibirPesquisarProcessoAction.do?indicadorUsoTodos=1', 250, 495);">
						<img width="23" 
							height="21" 
							border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Processo" /></a>
							 

					<logic:notPresent name="idProcessoNaoEncontrado" scope="request">

						<html:text property="descricaoProcesso" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="37" 
							maxlength="40" />
					</logic:notPresent> 
					
					<logic:present name="idProcessoNaoEncontrado" scope="request">
						<html:text property="descricaoProcesso" 
							size="30" 
							maxlength="30"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: red" />
					</logic:present> 
					
					<a href="javascript:limparPesquisarProcesso();"> 
						<img border="0" 
							title="Apagar"
							src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
					</td>
				</tr>
				
				<tr>
					<td width="100"><strong>Situa&ccedil;&atilde;o do Processo:</strong></td>
					<td colspan="2">
						<html:select property="idSituacaoProcesso"> 
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoProcessoSituacao" 
								labelProperty="descricao" 
								property="id" />
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td height="31"><strong>Usuário:</strong></td>
					
					<td colspan="3">
						<html:text property="usuarioId" 
							size="11"
							maxlength="11"
							onkeypress="validaEnter(event, '/gsan/exibirFiltrarProcessoAction.do', 'usuarioId'); return isCampoNumerico(event);" />

					<a href="javascript:abrirPopup('exibirUsuarioPesquisar.do?indicadorUsoTodos=1', 250, 495);">
						<img width="23" 
							height="21" 
							border="0"
							src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							title="Pesquisar Usuário" /></a>
							 

					<logic:notPresent name="usuarioNaoEncontrada" scope="request">

						<html:text property="usuarioDescricao" 
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000"
							size="37" 
							maxlength="40" />
					</logic:notPresent> 
					
					<logic:present name="usuarioNaoEncontrada" scope="request">
						<html:text property="usuarioDescricao" 
							size="30" 
							maxlength="30"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: red" />
					</logic:present> 
					
					<a href="javascript:limparPesquisarUsuario();"> 
						<img border="0" 
							title="Apagar"
							src="<bean:message key='caminho.imagens'/>limparcampo.gif" /></a>
					</td>
				</tr>
				
				<!-- RM7891 - Identificar os processos batch de acordo com o Grupo de Faturamento e/ou Ação de Cobrança. -->
				<tr>
					<td width="100"><strong>Grupo de Faturamento:</strong></td>
					<td colspan="2">
						<html:select property="idFaturamentoGrupo"> 
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoFaturamentoGrupo" 
								labelProperty="descricao" 
								property="id" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td width="100"><strong>Grupo de Cobrança:</strong></td>
					<td colspan="2">
						<html:select property="idCobrancaGrupo"> 
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoCobrancaGrupo" 
								labelProperty="descricao" 
								property="id" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td width="100"><strong>Ação de Cobrança:</strong></td>
					<td colspan="2">
						<html:select property="idCobrancaAcao"> 
							<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
							<html:options collection="colecaoCobrancaAcao" 
								labelProperty="descricaoCobrancaAcao" 
								property="id" />
						</html:select>
					</td>
				</tr>
				<!-- Fim RM7891 -->
				
				<tr>
					<td rowspan="2">
						<strong>Per&iacute;odo de Agendamento:</strong>
					</td>
					<td><strong>Inicial:</strong></td>
					
					<td width="189">
						<html:text onkeyup="mascaraData(this, event);somente_numero(this);"
							property="dataAgendamentoInicial" 
							size="10" 
							maxlength="10"
							onkeypress="javascript:return isCampoNumerico(event);"
							 /> 
						<a href="javascript:abrirCalendario('FiltrarProcessoActionForm', 'dataAgendamentoInicial')">
							<img border="0" 
								width="16" 
								height="15"
								src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" 
								border="0" 
								align="absmiddle" 
								alt="Exibir Calendário" /></a> (dd/mm/aaaa)
					</td>
					
					<td>
						<html:text property="horaAgendamentoInicial" 
							size="8" 
							value="00:00:00" 
							maxlength="8"
							onkeyup="mascaraHoraMinutoSegundo(this, event);"
							 onkeypress="javascript:return isCampoNumerico(event);" /> (hh:mm:ss)
					</td>
				</tr>
				
				<tr>
					<td><strong>Final:</strong></td>
					
					<td>
						<html:text property="dataAgendamentoFinal" 
							onkeyup="mascaraData(this, event);somente_numero(this);"
							size="10" 
							maxlength="10" onkeypress="javascript:return isCampoNumerico(event);" /> 
						<a href="javascript:abrirCalendario('FiltrarProcessoActionForm', 'dataAgendamentoFinal')">
							<img border="0" 
								width="16" 
								height="15"
								src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" 
								border="0" 
								align="absmiddle" 
								alt="Exibir Calendário" /></a>(dd/mm/aaaa)
					</td>
					<td>
						<html:text property="horaAgendamentoFinal" 
							size="8" 
							value="23:59:59" 
							maxlength="8"
							onkeyup="mascaraHoraMinutoSegundo(this, event);" 
							onkeypress="javascript:return isCampoNumerico(event);" /> (hh:mm:ss)
					</td>
				</tr>
				
				<tr>
					<td rowspan="2"><strong>Per&iacute;odo de In&iacute;cio:</strong></td>
					<td><strong>Inicial:</strong></td>
					<td><strong> </strong> <html:text onkeyup="mascaraData(this, event);somente_numero(this);"
						property="dataPeriodoInicioInicial" size="10" maxlength="10" 
						 onkeypress="javascript:return isCampoNumerico(event);" /> <a
						href="javascript:abrirCalendario('FiltrarProcessoActionForm', 'dataPeriodoInicioInicial')">
					<img border="0" width="16" height="15"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					(dd/mm/aaaa)</td>
					<td><html:text property="horaPeriodoInicioInicial" size="8"
						value="00:00:00" maxlength="8"
						onkeyup="mascaraHoraMinutoSegundo(this, event);" 
						onkeypress="javascript:return isCampoNumerico(event);" /> (hh:mm:ss)</td>
				</tr>
				
				<tr>
					<td><strong>Final:</strong></td>
					<td>
						<html:text property="dataPeriodoInicioFinal" 
							onkeyup="mascaraData(this, event);somente_numero(this);"
							size="10" 
							maxlength="10" onkeypress="javascript:return isCampoNumerico(event);" /> 
						<a href="javascript:abrirCalendario('FiltrarProcessoActionForm', 'dataPeriodoInicioFinal')">
							<img border="0" 
								width="16" 
								height="15"
								src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" 
								border="0" 
								align="absmiddle" 
								alt="Exibir Calendário" /></a>(dd/mm/aaaa)
					</td>
					<td>
						<html:text property="horaPeriodoInicioFinal" 
							size="8"
							value="23:59:59" 
							maxlength="8"
							onkeyup="mascaraHoraMinutoSegundo(this, event);"
							 onkeypress="javascript:return isCampoNumerico(event);" /> (hh:mm:ss)
					</td>
				</tr>
				
				<tr>
					<td rowspan="2">
						<strong>Per&iacute;odo de Conclus&atilde;o:</strong>
					</td>
					
					<td><strong>Inicial:</strong></td>
					
					<td>
						<html:text property="dataConclusaoInicial" 
							onkeyup="mascaraData(this, event);somente_numero(this);"
							size="10" 
							maxlength="10" onkeypress="javascript:return isCampoNumerico(event);" /> 
						<a href="javascript:abrirCalendario('FiltrarProcessoActionForm', 'dataConclusaoInicial')">
							<img border="0" 
								width="16" 
								height="15"
								src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" 
								border="0" 
								align="absmiddle" 
								alt="Exibir Calendário" /></a>(dd/mm/aaaa)
					</td>
					<td>
						<html:text property="horaConclusaoInicial" 
							size="8"
							value="00:00:00" 
							maxlength="8"
							onkeyup="mascaraHoraMinutoSegundo(this, event);" 
							onkeypress="javascript:return isCampoNumerico(event);" /> (hh:mm:ss)
					</td>
				</tr>
				
				<tr>
					<td><strong>Final:</strong></td>
					<td>
						<html:text property="dataConclusaoFinal" 
							onkeyup="mascaraData(this, event);somente_numero(this);"
							size="10" 
							maxlength="10" onkeypress="javascript:return isCampoNumerico(event);" /> 
						
						<a href="javascript:abrirCalendario('FiltrarProcessoActionForm', 'dataConclusaoFinal')">
							<img border="0" 
								width="16" 
								height="15"
								src="<bean:message key="caminho.imagens"/>calendario.gif"
								width="20" 
								border="0" 
								align="absmiddle" 
								alt="Exibir Calendário" /></a>(dd/mm/aaaa)
					</td>
					<td>
						<html:text property="horaConclusaoFinal" 
							size="8" 
							maxlength="8"
							value="23:59:59" 
							onkeyup="mascaraHoraMinutoSegundo(this, event);"  
							onkeypress="javascript:return isCampoNumerico(event);" />(hh:mm:ss)
					</td>
				</tr>
				
				<tr>
					<td rowspan="2"><strong>Per&iacute;odo de Comando:</strong></td>
					<td><strong>Inicial: </strong></td>
					<td><strong> </strong> <html:text property="dataComandoInicial" onkeyup="mascaraData(this, event);somente_numero(this);"
						size="10" maxlength="10" onkeypress="javascript:return isCampoNumerico(event);" /> <a
						href="javascript:abrirCalendario('FiltrarProcessoActionForm', 'dataComandoInicial')">
					<img border="0" width="16" height="15"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					(dd/mm/aaaa)</td>
					<td><html:text property="horaComandoInicial" size="8" maxlength="8"
						value="00:00:00" onkeyup="mascaraHoraMinutoSegundo(this, event);" 
						onkeypress="javascript:return isCampoNumerico(event);" />
					(hh:mm:ss)</td>
				</tr>
				<tr>
					<td><strong>Final:</strong></td>
					<td><strong> </strong> <html:text property="dataComandoFinal" onkeyup="mascaraData(this, event);somente_numero(this);"
						size="10" maxlength="10" onkeypress="javascript:return isCampoNumerico(event);" /> 
						<a href="javascript:abrirCalendario('FiltrarProcessoActionForm', 'dataComandoFinal')">
					<img border="0" width="16" height="15"
						src="<bean:message key="caminho.imagens"/>calendario.gif"
						width="20" border="0" align="absmiddle" alt="Exibir Calendário" /></a>
					(dd/mm/aaaa)</td>
					<td><html:text property="horaComandoFinal" size="8" maxlength="8"
						value="23:59:59" onkeyup="mascaraHoraMinutoSegundo(this, event);"
						 onkeypress="javascript:return isCampoNumerico(event);" />
					(hh:mm:ss)</td>
				</tr>
				<tr>
					<td colspan="4">
					<p>&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td align="left" colspan="4"><input type="button"
						class="bottonRightCol" value="Limpar"
						onclick="window.location.href='/gsan/exibirFiltrarProcessoAction.do?menu=sim'" />

						<input type="button" name="ButtonCancelar"
						class="bottonRightCol" value="Cancelar"
						onClick="javascript:document.forms[0].target='';window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td width="52">&nbsp;</td>
					<td colspan="2">
					<div align="right"><input type="button" name="filtrar"
						class="bottonRightCol" value="Filtrar"
						onclick="javascript:submeter();"></div>
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>

			</td>
		</tr>
	</table>


	<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
<div id="formDiv">

</body>
</html:html>

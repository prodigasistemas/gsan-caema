<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.util.ConstantesSistema" %>

<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="ConcederCreditoConjuntoContaActionForm"/>

<SCRIPT LANGUAGE="JavaScript">
<!--

	function validarForm(form){
	
		if (validateConcederCreditoConjuntoContaActionForm(form)){
		
			var msgDataVencimento = "Data de Vencimento anterior à data corrente.";
			var msgDataVencimento60 = "Data de Vencimento posterior a data corrente mais 60 dias.";
			var msgDataVencimentoUltimoDiaMes = "Data de Vencimento foi alterada para o último dia do mês corrente.Confirma?";
			var DATA_ATUAL = document.getElementById("DATA_ATUAL").value;
			var DATA_ATUAL_60 = document.getElementById("DATA_ATUAL_60").value;
			var ANO_LIMITE = document.getElementById("ANO_LIMITE").value;
			
			var ULTIMA_DATA_MES = document.getElementById("ULTIMA_DATA_MES").value;
			var INDICADOR_CALCULA_VENCIMENTO = document.getElementById("INDICADOR_CALCULA_VENCIMENTO").value;
			
			if(INDICADOR_CALCULA_VENCIMENTO != 2){ 
	 	    	if(form.indicadorDataAlterada == "sim"){  	  
	 	  	   		if (comparaData(form.vencimentoConta.value, ">", ULTIMA_DATA_MES )){					
	 					if (confirm(msgDataVencimentoUltimoDiaMes)){ 
	 				    	form.vencimentoConta.value = ULTIMA_DATA_MES;	 			   
							form.vencimentoConta.focus();				
						}else{					
							form.vencimentoConta.focus();
						}	
					}
			  	}
			}
				
			if (form.vencimentoConta.value != "" && (form.vencimentoConta.value.substring(6, 10) * 1) < (ANO_LIMITE * 1)){
				alert("Ano do vencimento da conta não deve ser menor que " + ANO_LIMITE + ".");
				form.vencimentoConta.focus();
			}
			else if (form.vencimentoConta.value != "" && comparaData(form.vencimentoConta.value, "<", DATA_ATUAL )){
				
				if (confirm(msgDataVencimento)){
					submeterFormPadrao(form);
				}
			}
			else if (form.vencimentoConta.value != "" && comparaData(form.vencimentoConta.value, ">", DATA_ATUAL_60 )){
				
				if (confirm(msgDataVencimento60)){
					submeterFormPadrao(form);
				}
			}
			else {
				submeterFormPadrao(form);
			}
		}
	}
		
	function checkDataVencimento(){
		var form = document.forms[0];	
		form.indicadorDataAlterada = "sim";
	}
	
	function limparPesquisaRA() {
    	var form = document.forms[0];

    	form.numeroRA.value = "";
    	form.descricaoRA.value = "";
  	}
  	
  	function reload() {
  		var form = document.forms[0];
  		form.action = 'exibirConcederCreditoConjuntoContaAction.do?tipoConsulta=1';
  		form.submit();
  	}
  	
  	function chamarForm(caminhoAction,objetoRelacionado){
		if(objetoRelacionado.disabled != true){
			var form = document.forms[0];
	   		form.action = caminhoAction;
	   		form.submit();
		}
	}	   

//-->
</SCRIPT>

</head>

<logic:present name="reloadPageConcederCreditoConjuntoConta">
	<body leftmargin="0" topmargin="0" onload="chamarSubmitComUrl('exibirManterContaConjuntoImovelAction.do?mensagemSucesoConcederCreditoConjuntoConta=sim'); window.close();">
</logic:present>

<logic:present name="reloadPage">
	<body leftmargin="0" topmargin="0" onload="chamarForm('exibirPesquisarRegistroAtendimentoAction.do?caminhoRetornoTelaPesquisaRegistroAtendimento=exibirConcederCreditoConjuntoContaAction',document.forms[0].numeroRA);">
</logic:present>

<logic:notPresent name="reloadPage">
	
	<logic:notPresent name="reloadPageConcederCreditoConjuntoConta">
		<body leftmargin="0" topmargin="0" onload="resizePageSemLink(630, 460); setarFoco('${requestScope.nomeCampo}');">
	</logic:notPresent>
	
</logic:notPresent>

<INPUT TYPE="hidden" ID="DATA_ATUAL" value="${requestScope.dataAtual}"/>
<INPUT TYPE="hidden" ID="DATA_ATUAL_60" value="${requestScope.dataAtual60}"/>
<INPUT TYPE="hidden" ID="ANO_LIMITE" value="${requestScope.anoLimite}"/>

<INPUT TYPE="hidden" ID="ULTIMA_DATA_MES" value="${requestScope.ultimaDataMes}"/>
<INPUT TYPE="hidden" ID="INDICADOR_CALCULA_VENCIMENTO" value="${requestScope.indicadorCalculaVencimento}"/>

<html:form action="/concederCreditoConjuntoContaAction" method="post">

<html:hidden property="contaSelected" />

<table width="600" border="0" cellpadding="0" cellspacing="5">
  <tr>
    <td width="590" valign="top" class="centercoltext"> 
      <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_left.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_left.xws" border="0" /></td>
          <td class="parabg">Conceder Crédito</td>
          <td width="11"><img src="<bean:message key="caminho.imagens"/>parahead_right.gif" editor="Webstyle4" moduleid="Album Photos (Project)\toptab_page2_parahead_right.xws" border="0" /></td>
        </tr>
      </table> 
      <p>&nbsp;</p>

      <table width="100%" border="0">
        <tr>
          <td colspan="4">Informe os dados abaixo:</td>
          <td align="right"></td>
        </tr>
        </table>
      <table width="100%" border="0">
      	<tr> 
			<td width="150" height="10"><strong>Motivo da Retificação:<font color="#FF0000">*</font></strong></td>
			<td colspan="3">
				<html:select property="motivoRetificacaoID" style="width: 400px;" tabindex="1" onchange="reload();" >
					<html:option value="<%= ""+ConstantesSistema.NUMERO_NAO_INFORMADO %>">&nbsp;</html:option>
					<logic:present name="colecaoContaMotivoRetificacao">
						<html:options collection="colecaoContaMotivoRetificacao" labelProperty="descricao" property="id"/>
					</logic:present>
				</html:select>
			</td>
		</tr>
        <tr>
          <td height="20"><strong>Data de Vencimento:</strong></td>
          <td colspan="3">
          	<logic:present name="desabilitaCalendario" scope="request">
          		<html:text property="vencimentoConta" size="11" maxlength="10" tabindex="2"
          			disabled="true" onkeyup="mascaraData(this, event);" onkeypress="return isCampoNumerico(event);" />
          		<img border="0" style="cursor: pointer;" src="<bean:message key='caminho.imagens'/>calendario.gif" width="20" border="0" align="absmiddle" title="Exibir Calendário"/></a>&nbsp;dd/mm/aaaa
          	</logic:present>
          	<logic:notPresent name="desabilitaCalendario" scope="request">	
          		<html:text property="vencimentoConta" size="11" maxlength="10" tabindex="2" onclick="checkDataVencimento();"
          			onkeyup="mascaraData(this, event);" onkeypress="return isCampoNumerico(event);" />
				<a href="javascript:abrirCalendario('ConcederCreditoConjuntoContaActionForm', 'vencimentoConta');checkDataVencimento();" >
                    <img border="0" src="<bean:message key='caminho.imagens'/>calendario.gif" width="20" border="0" align="absmiddle" title="Exibir Calendário"/></a>&nbsp;dd/mm/aaaa          
          	</logic:notPresent>
          </td>
        </tr>

		<tr>
			<td colspan="4" height="10"></td>
		</tr>
		<tr>
			<td colspan="4">
			
				<table width="100%" align="center" bgcolor="#99CCFF">
				   	<tr>
						<td align="center"><strong>Crédito na Retificação sem interfirir no faturamento</strong></td>
					</tr>
					<tr bgcolor="#cbe5fe">
						<td width="100%" align="center">
							
							<table width="100%" border="0">
								<tr> 
									<td height="10" width="145">
										<strong>Número da RA:<font color="#FF0000">*</font></strong>
									</td>
									<td>
										<html:text maxlength="9" 
											tabindex="1"
											property="numeroRA" 
											size="9"
											onkeypress="validaEnterComMensagem(event, 'exibirConcederCreditoConjuntoContaAction.do?tipoConsulta=1','numeroRA','Numero RA');return isCampoNumerico(event);" />
											
											<a href="javascript:chamarForm('exibirConcederCreditoConjuntoContaAction.do?pesquisarRA=sim', document.forms[0].numeroRA);" >
											<!--<a href="javascript:chamarForm('exibirPesquisarRegistroAtendimentoAction.do?caminhoRetornoTelaPesquisaRegistroAtendimento=exibirConcederCreditoConjuntoContaAction',document.forms[0].numeroRA);"> -->
												<img width="23" 
													height="21" 
													border="0"
													src="<bean:message key="caminho.imagens"/>pesquisa.gif"
													title="Pesquisar RA" />
											</a> 
				
											<logic:present name="numeroRAEncontrada" scope="request">
												
												<html:text property="descricaoRA" 
													size="40"
													maxlength="40" 
													readonly="true"
													style="background-color:#EFEFEF; border:0; color: #000000" />
											</logic:present> 
				
											<logic:notPresent name="numeroRAEncontrada" scope="request">
												
												<html:text property="descricaoRA" 
													size="40"
													maxlength="40" 
													readonly="true"
													style="background-color:#EFEFEF; border:0; color: red" />
													
											</logic:notPresent>
				
											<a href="javascript:limparPesquisaRA();"> 
												<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
													border="0" 
													title="Apagar" />
											</a>
									</td>
								</tr>
								<tr> 
					   				<td height="10"><strong>Valor do Crédito: (Se 0, igual ao valor da conta):<font color="#FF0000">*</font></strong></td>
									<td>
										<html:text property="valorCredito" size="10" maxlength="10" tabindex="4" style="text-align: right;" onkeypress="return isCampoNumerico(event);" onkeyup="formataValorMonetario(this, 7);" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table> 
			</td>
		</tr>	
		<tr>
			<td colspan="4" height="10">&nbsp;</td>
		</tr>
		<tr>
          <td height="30" colspan="4"> 
          	<div align="right">
              <input type="button" tabindex="7" onclick="validarForm(document.forms[0]);" class="bottonRightCol" value="Retificar Conjunto Conta">&nbsp;
              <input type="button" tabindex="8" onclick="window.close();" class="bottonRightCol" value="Fechar" style="width: 70px;">
			</div>
		  </td>
        </tr>
	  </table>
      
      <p>&nbsp;</p>
      </td>
  </tr>
</table>

</html:form>

</body>
</html:html>

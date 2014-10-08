<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.gui.GcomAction"%>

<%@page import="gcom.util.ConstantesSistema"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	  href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	  type="text/css">

<%@ page import="gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento"%>

<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>


<script language="JavaScript">

var bCancel = false;
	
	function enviarDadosLocal(codigoRegistro, descricaoRegistro, tipoConsulta){
		enviarDados(codigoRegistro, descricaoRegistro, tipoConsulta);
	}

	
	function validarForm(){
		 var form = document.forms[0];
		 
		if(form.idMotivoNaoAceite.value == "-1" || form.idMotivoNaoAceite.value == "" ){
			alert("Informe o motivo.")
			return false;		
		}
		if(form.indicadorObrigatorio.value == "1"){
			if(trim(form.observacao.value) == ''){
				alert("Informe a observação.")
				return false;				
			}
   	   	}
		enviarDadosLocal(form.idMotivoNaoAceite.value,form.observacao.value,'motivo');
    }
 
	
	function limpar(){
	 var form = document.forms[0];
	 form.idMotivoNaoAceite.value = '-1';
	 form.observacao.value = '';
	}
	

	function carregarCampos(){
		var form = document.forms[0];

		if (form.idMotivoNaoAceite.value > 0){
			redirecionarSubmit('fiscalizarOSAcompanhamentoServicoMotivoPopupAction.do?pesquisarMotivo='+form.idMotivoNaoAceite.value);
		}
	}
	
</script>
<script>
function setCookie(c_name,value,exdays)
{
var exdate=new Date();
exdate.setDate(exdate.getDate() + exdays);
var c_value=escape(value) + ((exdays==null) ? "" : "; expires="+exdate.toUTCString());
document.cookie=c_name + "=" + c_value;
}

</script>
<script>

setCookie("desativaHistoryBack", "true", "1");

</script>
</head>
<logic:present name="fecharPopup">
	<body leftmargin="0" 
		  topmargin="0"
		  onload="javascript:chamarReload('${sessionScope.retornoTela}');window.close();">
</logic:present>

<logic:notPresent name="fecharPopup">
	<body leftmargin="0" 
	      topmargin="0"
		  onload="window.focus();resizePageSemLink(700, 600);javascript:setarFoco('${requestScope.nomeCampo}');">

</logic:notPresent>
<html:form  action="/atualizarFiscalizarOSAcompanhamentoServicoAction"
			name="FiltrarFiscalizarOSAcompanhamentoServicoActionForm"
			type="gcom.gui.atendimentopublico.ordemservico.FiltrarFiscalizarOSAcompanhamentoServicoActionForm"
			method="post">
			
			<html:hidden property="indicadorObrigatorio"/>
		<logic:present name="observacaoObrigatorio">
			<input type="hidden" name="obrigatorio" value="1"/>
		</logic:present>
		
		<logic:notPresent name="observacaoObrigatorio">
			<input type="hidden" name="obrigatorio" value="0"/>		
		</logic:notPresent>
	<table width="100%" border="0" cellspacing="5" cellpadding="0">

		<tr>
			<td width="100%" valign="top" class="centercoltext">

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
						<td class="parabg">Ordem de Serviço Não Confirmadas</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>

			<p>&nbsp;</p>

			<!--Inicio da Tabela Dados Gerais da Ordem de Serviço -->
			<table width="100%" border="0">

				<tr>
					<td height="31" colspan="2">
				
							
					<table width="100%" border="0" align="center">
	
	 					<tr> 
				          <td colspan="3">Preencha os campos para OS n&atilde;o Confirmadas</td>
				        </tr>
			        
						<tr>
							<td>
							<table width="100%" border="0" bgcolor="#99CCFF">
								<tr bgcolor="#99CCFF">
									<td align="center"><b>Dados do Encerramento da Ordem de Serviço</b></td>
								</tr>
								<tr bgcolor="#cbe5fe">

									<td>
									<table border="0" width="100%">
																					
										<tr>
											<td width="40%"><strong>Motivo de Não Aceitação: <font
												color="#ff0000">*</font></strong></td>

											<td width="50%"><html:select property="idMotivoNaoAceite"
												tabindex="2" onchange="carregarCampos('${sessionScope.observacaoObrigatorio}');">
												<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
												<html:options collection="colMotivoNaoAceite"
													labelProperty="descricao" property="id" />
											</html:select></td>
										</tr>
									
										
										<tr>
											<td width="40%"><strong>Observação:</strong></td>
											<td width="50%">
											
											<logic:equal value="OK" scope="session" name="habilitaMotivoObservacao">
												<html:textarea 	property="observacao"  cols="50" onkeyup="limitTextArea(document.forms[0].observacao, 300, document.getElementById('utilizado'), document.getElementById('limite'));"rows="5"/>
											</logic:equal>
											<logic:notEqual value="OK"  name="habilitaMotivoObservacao" scope="session">											
												<html:textarea property="observacao" cols="50"	rows="5"/>
											</logic:notEqual>	
												<span id="utilizado">0</span>/<span id="limite">300</span>
											
											</td>
										</tr>
								
	
						</table>
	
						</td>
					</tr>
				</table>
				<tr>
					<td colspan="2">
					<hr>
					</td>
				</tr>
				<tr>
					<td height="19">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>

					</td>
				</tr>
						<td>
						<table width="100%">
							
							<tr>
								<td>
								<div><input name="ButtonVoltar" type="button"
									class="bottonRightCol" value="Limpar"
									onclick="limpar();"></div>
								</td>
								
								<td>
								<div align="right">
								<table>
								<tr>
								<td><input name="ButtonEncerrar" type="button"
									class="bottonRightCol" value="Concluir"
									onclick="javascript:validarForm();"></td>
								
								
							  </tr>	
							</table>	
							</div>
						   </td>
						  </tr>
						</table>
						</td>
					</tr>
			</table>
			</td>
		</tr>
	</table>
	<!-- Fim do Corpo - Sávio Luiz -->
</html:form>
</body>
</html:html>
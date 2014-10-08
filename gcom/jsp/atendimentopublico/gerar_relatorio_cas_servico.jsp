<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%><%@ page import="gcom.cadastro.localidade.GerenciaRegional"%><%@ page import="gcom.gerencial.cadastro.localidade.GUnidadeNegocio"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GCOM - Sistema de Gest&atilde;o Comercial</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="GerarCertidaoNegativaActionForm"/>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script><html:javascript staticJavascript="false"  formName="GerarRelatorioDadosKitCASServicoActionForm"/>

<script language="JavaScript">

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

		var form = document.forms[0];

		if (tipoConsulta == 'localidade' && form.opcaoTotalizacao.value == '17') {      		
	      form.idLocalidade.value = codigoRegistro;
		  form.descLocalidade.value = descricaoRegistro;		  form.descLocalidade.style.color = "#000000";
		}				else if (tipoConsulta == 'municipio' && form.opcaoTotalizacao.value == '20') {			form.idMunicipio.value = codigoRegistro;			form.descMunicipio.value = descricaoRegistro;			form.descMunicipio.style.color = "#000000";		}
	}
		
	function validarForm(){
		
		var form = document.forms[0];					if(form.mesAnoReferencia.value == ''){				alert("Informe Mês/Ano de Referência");				return false;			}									if(form.opcaoTotalizacao.value == '6' && form.idGerenciaRegional.value == ""){				alert("Informe Gerência Regional");				return false;			}						if(form.opcaoTotalizacao.value == '10' && form.idUnidadeNegocio.value == ""){				alert("Informe Unidade Negócio");				return false;			}						if(form.opcaoTotalizacao.value == '17' && form.idLocalidade.value == ""){				alert("Informe Localidade");				return false;			}						if(form.opcaoTotalizacao.value == '20' && form.idMunicipio.value == ""){				alert("Informe Município");				return false;			}						if(validateGerarRelatorioDadosKitCASServicoActionForm(form)){
				toggleBox('demodiv', 1);			}		}
			function habilitarDesabilitar(obj){		var form = document.forms[0];										 if(obj.value == '6'){				form.idGerenciaRegional.disabled = false;				form.idMunicipio.disabled = true;				form.idMunicipio.value = "";				form.descMunicipio.value = "";				document.getElementById("idPesquisarMunicipio").href = "#";				form.idLocalidade.disabled = true;				form.idLocalidade.value = "";				form.descLocalidade.value = "";				document.getElementById("idPesquisarLocalidade").href = "#";				form.idUnidadeNegocio.disabled = true;				form.idUnidadeNegocio.value = "";		 }					 else if(obj.value == '10'){ 			 form.idMunicipio.disabled = true;				form.idMunicipio.value = "";				form.descMunicipio.value = "";				document.getElementById("idPesquisarMunicipio").href = "#";				form.idLocalidade.disabled = true;				form.idLocalidade.value = "";				form.descLocalidade.value = "";				document.getElementById("idPesquisarLocalidade").href = "#";				form.idGerenciaRegional.disabled = true;				form.idGerenciaRegional.value = "";				form.idUnidadeNegocio.disabled = false;		 }					 else if(obj.value == '17'){				form.idLocalidade.disabled = false;				document.getElementById("idPesquisarLocalidade").href = "javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 400, 800);";				form.idMunicipio.disabled = true;				form.idMunicipio.value = "";				form.descMunicipio.value = "";				document.getElementById("idPesquisarMunicipio").href = "#";				form.idUnidadeNegocio.disabled = true;				form.idUnidadeNegocio.value = "";				form.idGerenciaRegional.disabled = true;				form.idGerenciaRegional.value = "";		 }		 else if(obj.value == '20'){								form.idMunicipio.disabled = false;				document.getElementById("idPesquisarMunicipio").href = "javascript:abrirPopup('exibirPesquisarMunicipioAction.do', 250, 495);";				form.idLocalidade.disabled = true;				form.idLocalidade.value = "";				form.descLocalidade.value = "";				document.getElementById("idPesquisarLocalidade").href = "#";				form.idUnidadeNegocio.disabled = true;				form.idUnidadeNegocio.value = "";				form.idGerenciaRegional.disabled = true;				form.idGerenciaRegional.value = "";		}		 else{		 	form.idMunicipio.disabled = true;			form.idMunicipio.value = "";			form.descMunicipio.value = "";			document.getElementById("idPesquisarMunicipio").href = "#";			form.idLocalidade.disabled = true;			form.idLocalidade.value = "";			form.descLocalidade.value = "";			document.getElementById("idPesquisarLocalidade").href = "#";			form.idUnidadeNegocio.disabled = true;			form.idUnidadeNegocio.value = "";			form.idGerenciaRegional.disabled = true;			form.idGerenciaRegional.value = "";		 }			}	
	function validarCampos(){
		
	}		function limparLocalidade(){		var form = document.forms[0];		form.idLocalidade.value = "";		form.descLocalidade.value = "";	}		function limparMunicipio(){		var form = document.forms[0];		form.idMunicipio.value = "";		form.descMunicipio.value = "";	}
	
</script>

</head>

<body leftmargin="5" topmargin="5"><div id="formDiv">
<html:form action="/gerarRelatorioDadosKitCASServicoAction.do"
	name="GerarRelatorioDadosKitCASServicoActionForm"
	type="gcom.gui.atendimentopublico.GerarRelatorioDadosKitCASServicoActionForm"
	method="post" onsubmit="return validateGerarRelatorioDadosKitCASServicoActionForm(this);">

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
					<td class="parabg">Gerar Relatório de Serviços</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para gerar o Relatório de Serviços, informe os dados abaixo:</td>
				</tr>
			
				<tr>			      	<td HEIGHT="30"><strong>Mês/Ano de Referência:<font color="#FF0000">*</font></strong></td>			        <td>						<html:text property="mesAnoReferencia" 								   size="7" 								   maxlength="7" 								   tabindex="1" 								   onkeyup="mascaraAnoMes(this, event);"								   onblur="return isCampoNumerico(event);" 								   onkeypress="return isCampoNumerico(event);"								   />                  				                  				&nbsp;mm/aaaa					</td>			    </tr>			    			    <tr>			      	<td width="183" HEIGHT="30"><strong>Opção de Totalização:</strong></td>			        <td>						<html:select property="opcaoTotalizacao" style="width: 250px;" tabindex="5" onchange="habilitarDesabilitar(this)">									<html:option value="1">ESTADO</html:option>								<html:option value="2">ESTADO POR GERÊNCIA REGIONAL</html:option>								<html:option value="3">ESTADO POR UNIDADE DE NEGÓCIO</html:option>								<html:option value="6">GERÊNCIA REGIONAL</html:option>								<html:option value="10">UNIDADE DE NEGÓCIO</html:option>								<html:option value="17">LOCALIDADE</html:option>								<html:option value="20">MUNICÍPIO</html:option>						</html:select>					</td>		        </tr>
				<tr>			      	<td width="183" HEIGHT="30"><strong>Gerência Regional:</strong></td>			        <td>						<html:select property="idGerenciaRegional" style="width: 250px;" tabindex="5">							<html:option value="">&nbsp;</html:option>							<logic:present name="colecaoGerenciaRegional">								<logic:iterate name="colecaoGerenciaRegional" id="gerenciaRegional" type="GerenciaRegional">									<html:option value="${gerenciaRegional.id}">										<bean:write name="gerenciaRegional" property="nome" />									</html:option>								</logic:iterate>							</logic:present>						</html:select>					</td>			      </tr>			      			      <tr>			      	<td width="183" HEIGHT="30"><strong>Unidade de Negócio:</strong></td>			        <td>						<html:select property="idUnidadeNegocio" style="width: 250px;" tabindex="5">							<html:option value="">&nbsp;</html:option>							<logic:present name="colecaoUnidadeNegocio">								<logic:iterate name="colecaoUnidadeNegocio" id="unidadeNegocio" type="GUnidadeNegocio">									<html:option value="${unidadeNegocio.id}">										<bean:write name="unidadeNegocio" property="nome" />									</html:option>								</logic:iterate>							</logic:present>						</html:select>					</td>			      </tr>			      			      			      <tr>			      	<td width="183" HEIGHT="30"><strong>Localidade:</strong></td>			        <td width="432">        				        	<html:text property="idLocalidade" size="4" maxlength="4" tabindex="6" onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioDadosKitCASServicoAction.do?pesquisarLocalidade=OK', 'idLocalidade', 'Localidade');return isCampoNumerico(event);"/>						<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 400, 800);" title="Pesquisar" id="idPesquisarLocalidade"><img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" border="0"></a>						<logic:present name="localidadeException">														<html:text property="descLocalidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>						</logic:present>						<logic:notPresent name="localidadeException">							<html:text property="descLocalidade" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>						</logic:notPresent>			        	<a href="javascript:limparLocalidade()" title="Apagar"><img src="<bean:message key='caminho.imagens'/>limparcampo.gif" border="0"></a>					</td>			      </tr>			      			      			      <tr>			      	<td width="183" HEIGHT="30"><strong>Município:</strong></td>			        <td width="432">        				        	<html:text property="idMunicipio" size="4" maxlength="4" tabindex="6" onkeypress="validaEnterComMensagem(event, 'exibirGerarRelatorioDadosKitCASServicoAction.do?pesquisarMunicipio=OK', 'idMunicipio', 'Município');return isCampoNumerico(event);"/>						<a href="javascript:abrirPopup('exibirPesquisarMunicipioAction.do', 250, 495);" title="Pesquisar" id="idPesquisarMunicipio"><img src="<bean:message key='caminho.imagens'/>pesquisa.gif" width="23" height="21" border="0"></a>						<logic:present name="municipioException">														<html:text property="descMunicipio" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #ff0000"/>						</logic:present>						<logic:notPresent name="municipioException">							<html:text property="descMunicipio" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>						</logic:notPresent>			        	<a href="javascript:limparMunicipio()" title="Apagar"><img src="<bean:message key='caminho.imagens'/>limparcampo.gif" border="0"></a>					</td>			      </tr>			      
					
				<tr>
					<td colspan="2"><hr></td>
				</tr>				          	   
				<tr>
					<td>&nbsp;</td>
					<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
				</tr>				          					
				
				<tr>
					<td height="24" >
			          	<input type="button" 
			          		class="bottonRightCol" 
			          		value="Limpar" 
			          		onclick="javascript:window.location.href='/gsan/exibirGerarRelatorioDadosKitCASServicoAction.do?menu=sim'"/>
			          		<input name="Button" type="button" class="bottonRightCol" value="Cancelar" align="left" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">					
					</td>
					<td align="right">
						<input type="button" 
							name="Button" 
							class="bottonRightCol" 
							value="Gerar" 
							onClick="javascript:validarForm()" />
					</td>	
				</tr>								
				
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table><script>	javascript:habilitarDesabilitar(document.forms[0].opcaoTotalizacao);</script>
<%@ include file="/jsp/util/rodape.jsp"%>	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=gerarRelatorioDadosKitCASServicoAction.do" />
</html:form></div><%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>

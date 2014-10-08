<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<%@ page import="gcom.atendimentopublico.bean.AcoesParaCorrecaoAnormalidadesEncontradasHelper" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script>
    
    function validateConsultarDadosOrdemServicoVisitaActionForm(form) {
        return true;
    }

	
	function selecionouObj(nome){

		var form = document.forms[0];
	    var selecionados = form.elements[nome];
		var retorno = false;
		
		if (selecionados.value != null && selecionados.value != '') {
			
			retorno = true;
			
		} else {
		
			for (i=0; i< selecionados.length; i++) {
				if(selecionados[i].checked){
					retorno = true;
				}
			}
			
		}
		
		return retorno;
	}
	
	function objetoSelecionado(nome){

		var form = document.forms[0];
	    var selecionados = form.elements[nome];
		var retorno = false;
		
		if (selecionados.value != null && selecionados.value != '') {
			
			return selecionados.value;
			
		} else {
			for (i=0; i< selecionados.length; i++) {
				if(selecionados[i].checked){
					return selecionados[i].value;
				}
			}
		}
	}
	
    function facilitador(objeto){
        if (objeto.id == "0" || objeto.id == undefined){ 
            objeto.id = "1";
            
    		for (var i=0;i < document.forms[0].elements.length;i++){
    			var elemento = document.forms[0].elements[i];
    			if (elemento.type == "checkbox" && elemento.name == 'idsAcoes'){
    				elemento.checked = true;
    			}
    		}
        }
        else{
            objeto.id = "0";
            
            for (var i=0;i < document.forms[0].elements.length;i++){
    			var elemento = document.forms[0].elements[i];
    			if (elemento.type == "checkbox" && elemento.name == 'idsAcoes'){
    				
    				if (elemento.checked == true){
    					elemento.checked = false;
    				}
    			}
    		}
        }
    }
    
    function extendeTabela(tabela,display){
		var form = document.forms[0];

		if(display){
 			eval('layerHide'+tabela).style.display = 'none';
 			eval('layerShow'+tabela).style.display = 'block';
		}else{
			eval('layerHide'+tabela).style.display = 'block';
 			eval('layerShow'+tabela).style.display = 'none';
		}
	}
    
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form
    action="/consultarDadosOrdemServicoVisitaWizardAction"
    method="post"
>

<jsp:include page="/jsp/util/wizard/navegacao_abas_wizard_valida_avancar.jsp?numeroPagina=1"/>

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

<input type="hidden" name="tipoOs" value="<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="tipoOs"/>" />

<table width="770" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="165" valign="top" class="leftcoltext">
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
    <td width="650" valign="top" class="centercoltext">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg" colspan="2">Encerrar Ordem de Serviço de Correção de Anormalidade </td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0" dwcopytype="CopyTableRow">
        <tr>
          <td colspan="4">
            Para efetuar encerramento da Ordem de Serviço, informe os dados abaixo:
          </td>
        </tr>
		<tr>
			<td colspan="3" height="7"> </td>
		</tr>
		
		<tr>
			<td colspan="4">
				<table width="100%" border="0" bgcolor="#99CCFF">
					<tr bgcolor="#99CCFF">
             			<td align="center">
           					<span class="style2">
            					<b>Dados da Ordem de Serviço</b>
           					</span>
             			</td>
           			</tr>
           			<tr bgcolor="#cbe5fe">
           				<td>
           					<table border="0" width="100%">
           						<tr>
           							<td width="100px;" colspan="2" ><strong>Ordem de Serviço:</strong></td>
           							<td><strong>
           								<html:text maxlength="8" size="8" property="ordemServico" readonly="true"
           									 style="background-color:#EFEFEF; border:0; color: #000000"/>
           							</strong></td>
           						</tr>
           						<tr>
           							<td colspan="2" ><strong>Matrícula do Imóvel:</strong></td>
           							<td><strong>
           								<html:text maxlength="12" size="12" property="matriculaFormatada" readonly="true"
           									 style="background-color:#EFEFEF; border:0; color: #000000"/>
           							</strong></td>
           						</tr>
           						<tr>
           							<td colspan="2" ><strong>Motivo de Encerramento:</strong></td>
           							<td><strong>
           								<html:text maxlength="30" size="30" property="motivoEncerramento" readonly="true"
           									 style="background-color:#EFEFEF; border:0; color: #000000"/>
           							</strong></td>
           						</tr>
           						<tr>
           							<td colspan="2" width="30%"><strong>Parecer do Encerramento</strong></td>
           							<td width="69%"><strong>
           								<html:textarea
											property="observacaoEncerramento" 
    										cols="50" 
    										rows="5" readonly="true" />
           							</strong></td>
           						</tr>
           						<tr>
           							<td colspan="2" ><strong>Data/Hora Execucao:</strong></td>
           							<td><strong>
           								<html:text maxlength="20" size="20" property="dataExecucao" readonly="true"
           									 style="background-color:#EFEFEF; border:0; color: #000000"/>
           							</strong></td>
           						</tr>
           					</table>
           				</td>
           			</tr>
				</table>
			</td>
		</tr>
		
		<tr bgcolor="#cbe5fe">
			<logic:equal name="tipoOrdemServico" value="1" scope="request">
				<td align="center" colspan="4">
					<div id="layerHideSupressao" style="display:block">
						<table width="100%" border="0" bgcolor="#99CCFF">
	  						<tr bgcolor="#99CCFF">
	               				<td align="center">
	              					<span class="style2">
	               					<a href="javascript:extendeTabela('Supressao',true);">
	               						<b>Ordem de Serviço - Supressao </b>
	               					</a>
	              					</span>
	               				</td>
	                 		</tr>
		                </table>
		       		</div>
		       		<div id="layerShowSupressao" style="display:none">
						<table width="100%" border="0" bgcolor="#99CCFF">
							<tr bgcolor="#99CCFF">
		             			<td align="center">
		           					<span class="style2">
		            					<a href="javascript:extendeTabela('Supressao',false);">
		            						<b>Ordem de Serviço - Supressao</b>
		            					</a>
		           					</span>
		             			</td>
		           			</tr>
		           			<tr bgcolor="#cbe5fe">
		           				<td>
		           					<table width="100%" bgcolor="#90c7fc" border="0" class="fontePequena">
		           						<tr bgcolor="#FFFFFF">
											<td><strong>Motivo Supressao</strong></td>
	                         				<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="motivoSupressao"/>
	                         				</td>
		      							</tr>
		      							<tr bgcolor="#cbe5fe">
		      								<td><strong>Tipo Supressao</strong></td>
		      								<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="tipoSupressao"/>
	                         				</td>
		      							</tr>
		      							<tr bgcolor="#FFFFFF">
											<td><strong>Leitura Supressao</strong></td>
	                         				<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="leituraSupressao"/>
	                         				</td>
		      							</tr>
		      							<tr bgcolor="#cbe5fe">
		      								<td><strong>Numero Selo Supressao</strong></td>
		      								<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="numeroSelo"/>
	                         				</td>
		      							</tr>
		           					</table>
		           				</td>
		           			</tr>
		           		</table>
		           	</div>
				</td>
			</logic:equal>
			<logic:equal name="tipoOrdemServico" value="2" scope="request">
				<td align="center" colspan="4">	
					<div id="layerHideRemocao" style="display:block">
						<table width="100%" border="0" bgcolor="#99CCFF">
	  						<tr bgcolor="#99CCFF">
	               				<td align="center">
	              					<span class="style2">
	               					<a href="javascript:extendeTabela('Remocao',true);">
	               						<b>Ordem de Serviço - Remoção de Hidrometro </b>
	               					</a>
	              					</span>
	               				</td>
	                 		</tr>
		                </table>
		       		</div>	
		       		<div id="layerShowRemocao" style="display:none">
						<table width="100%" border="0" bgcolor="#99CCFF">
							<tr bgcolor="#99CCFF">
		             			<td align="center">
		           					<span class="style2">
		            					<a href="javascript:extendeTabela('Remocao',false);">
		            						<b>Ordem de Serviço - Remoção de Hidrometro </b>
		            					</a>
		           					</span>
		             			</td>
		           			</tr>
		           			<tr bgcolor="#cbe5fe">
		           				<td>	
		           					<table width="100%" bgcolor="#90c7fc" border="0" class="fontePequena">	
		           						<tr bgcolor="#FFFFFF">
											<td><strong>Tipo de Medição</strong></td>
	                         				<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="tipoMedicao"/>
	                         				</td>
		      							</tr>
		      							<tr bgcolor="#cbe5fe">
		      								<td><strong>Local de Instalação</strong></td>
		      								<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="localInstalacao"/>
	                         				</td>
		      							</tr>
		      							<tr bgcolor="#FFFFFF">
											<td><strong>Proteção</strong></td>
	                         				<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="protecao"/>
	                         				</td>
		      							</tr>
		      							<tr bgcolor="#cbe5fe">
		      								<td><strong>Cavalete</strong></td>
		      								<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="cavalete"/>
	                         				</td>
		      							</tr>
		           					</table>
		           				</td>
		           			</tr>
		           		</table>
					</div>
				</td>
			</logic:equal>
			<logic:equal name="tipoOrdemServico" value="3" scope="request">
				<td align="center" colspan="4">	
					<div id="layerHideSubstituicao" style="display:block">
						<table width="100%" border="0" bgcolor="#99CCFF">
	  						<tr bgcolor="#99CCFF">
	               				<td align="center">
	              					<span class="style2">
	               					<a href="javascript:extendeTabela('Substituicao',true);">
	               						<b>Ordem de Serviço - Substituição de Hidrometro </b>
	               					</a>
	              					</span>
	               				</td>
	                 		</tr>
		                </table>
		       		</div>	
		       		<div id="layerShowSubstituicao" style="display:none">
						<table width="100%" border="0" bgcolor="#99CCFF">
							<tr bgcolor="#99CCFF">
		             			<td align="center">
		           					<span class="style2">
		            					<a href="javascript:extendeTabela('Substituicao',false);">
		            						<b>Ordem de Serviço - Substituição de Hidrometro </b>
		            					</a>
		           					</span>
		             			</td>
		           			</tr>
		           			<tr bgcolor="#cbe5fe">
		           				<td>	
		           					<table width="100%" bgcolor="#90c7fc" border="0" class="fontePequena">	
		           						<tr bgcolor="#FFFFFF">
											<td><strong>Número da Leitura</strong></td>
	                         				<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="numeroLeitura"/>
	                         				</td>
		      							</tr>
		      							<tr bgcolor="#cbe5fe">
		      								<td><strong>Situação do Hidrômetro</strong></td>
		      								<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="situacaoHidrometro"/>
	                         				</td>
		      							</tr>
		      							<tr bgcolor="#FFFFFF">
											<td><strong>Local de Armazenagem</strong></td>
	                         				<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="localArmazenagem"/>
	                         				</td>
		      							</tr>
		      							<tr bgcolor="#cbe5fe">
		      								<td><strong>Tipo de Hidrômetro</strong></td>
		      								<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="tipoHidrometro"/>
	                         				</td>
		      							</tr>
		      							<tr bgcolor="#FFFFFF">
											<td><strong>Número do Hidrômetro Novo</strong></td>
	                         				<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="numeroHidrometroNovo"/>
	                         				</td>
		      							</tr>
		      							<tr bgcolor="#cbe5fe">
		      								<td><strong>Data de Instalação</strong></td>
		      								<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="dataInstalacao"/>
	                         				</td>
		      							</tr>
		      							<tr bgcolor="#FFFFFF">
											<td><strong>Tipo de Medição</strong></td>
	                         				<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="tipoMedicao"/>
	                         				</td>
		      							</tr>
		      							<tr bgcolor="#cbe5fe">
		      								<td><strong>Local de Instalação</strong></td>
		      								<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="localInstalacao"/>
	                         				</td>
		      							</tr>
		      							<tr bgcolor="#FFFFFF">
											<td><strong>Proteção</strong></td>
	                         				<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="protecao"/>
	                         				</td>
		      							</tr>
		      							<tr bgcolor="#cbe5fe">
		      								<td><strong>Troca de Proteção</strong></td>
		      								<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="trocaProtecao"/>
	                         				</td>
		      							</tr>
		      							<tr bgcolor="#FFFFFF">
											<td><strong>Troca de Registro</strong></td>
	                         				<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="trocaRegistro"/>
	                         				</td>
		      							</tr>
		      							<tr bgcolor="#cbe5fe">
		      								<td><strong>Leitura da Instalação</strong></td>
		      								<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="leituraInstalacao"/>
	                         				</td>
		      							</tr>
		      							<tr bgcolor="#FFFFFF">
											<td><strong>Número do Selo</strong></td>
	                         				<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="numeroSelo"/>
	                         				</td>
		      							</tr>
		      							<tr bgcolor="#cbe5fe">
		      								<td><strong>Cavalete</strong></td>
		      								<td>  
	                         					<bean:write name="ConsultarDadosOrdemServicoVisitaActionForm" property="cavalete"/>
	                         				</td>
		      							</tr>
		           					</table>
		           				</td>
		           			</tr>
		           		</table>
					</div>
				</td>	
			</logic:equal>
		</tr>
					
	</table>

     <table width="100%" border="0" >
       <tr>
          <td colspan="2"><div align="right"><jsp:include page="/jsp/util/wizard/navegacao_botoes_wizard_valida_avancar.jsp?numeroPagina=1"/></div></td>
        </tr>
     </table>
		
	<table width="100%" border="0">
		<tr>
			<td colspan="3" height="7"> </td>
		</tr>	
	</table>
      <p>&nbsp;</p>
    </td>
  </tr>

</table>

<%@ include file="/jsp/util/rodape.jsp"%>

</html:form>
</body>
</html:html>

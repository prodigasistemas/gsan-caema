<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<%@ page import="gcom.util.ConstantesSistema" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirLocalidadeActionForm"/>

<SCRIPT LANGUAGE="JavaScript">
<!--

	//variável que indica qual linha a pesquisa foi efetuada
	var linhaPesquisa;

	
	/* Recupera Dados Popup */
    function recuperarDadosPopup(codigoRegistro, descricaoRegistro,
            tipoConsulta) {
        var form = document.forms[0];
        form.action = "exibirInserirTramitesGruposSolicitacoesAction.do?consultaUnidade=1&posicaoUnidade="+linhaPesquisa
        form.idsUnidadeOrganizacional[linhaPesquisa].value = codigoRegistro;
        form.descricoesUnidadeOrganizacional[linhaPesquisa].value = descricaoRegistro;
        form.submit();
        
	}
	
	
	function limparUnidade(i){
  		document.forms[0].action = "exibirInserirTramitesGruposSolicitacoesAction.do?apagarUnidade=1&posicaoUnidade="+i;
  		document.forms[0].submit();
  	}

  	 
    function chamarPopup(url, linha, altura, largura){
    	   linhaPesquisa = linha;
    	   abrirPopup(url, altura, largura);
    }
  	
  	
//-->
</SCRIPT>

</head>

<body leftmargin="5" topmargin="5">

<html:form action="/inserirTramitesGruposSolicitacoesAction"
	name="InserirTramitesGruposSolicitacoesActionForm" method="post"
	type="gcom.gui.cadastro.localidade.InserirTramitesGruposSolicitacoesActionForm">


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

	<td width="625" valign="top" class="centercoltext">

        <table height="100%">
        <tr>
          <td></td>
        </tr>
      	</table>

      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif"/></td>
          <td class="parabg">Inserir Dados para Sugestão de Trâmite por GRUPO</td>
          <td width="11"><img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif"/></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      
      <table width="100%" border="0">
     
     <tr>
      	<td width="30%"><strong>Localidade:</strong></td>
        <td width="70%">
        	<html:text property="idLocalidade" size="4" maxlength="4" readonly="true" />
			<html:text property="descricaoLocalidade" 
				size="35"
				maxlength="35" 
				readonly="true"
				style="background-color:#EFEFEF; border:0; color: #000000" />
			</td>
  	</tr>
  	
  	<tr>
      	<td width="30%"><strong>Unidade Organizacional:</strong></td>
        <td width="70%">
        	<html:text property="idUnidadeOrganizacional" size="4" maxlength="4" readonly="true" />
			<html:text property="descricaoUnidadeOrganizacional" 
				size="35"
				maxlength="35" 
				readonly="true"
				style="background-color:#EFEFEF; border:0; color: #000000" />
		</td>
  	</tr>	
     
	   <tr>
	       <td>&nbsp;</td>
	   </tr>  
	   <tr rowspan=2>
	       <td colspan=2 align="center">Para concluir o processo de Inclusão da Localidade, informe os <b>Dados Para Sugestão de Trâmite por GRUPO</b> abaixo:</td>
	   </tr>
	   <tr>
	       <td>&nbsp;</td>
	   </tr>    
      
      
      <tr>
		<td colspan="3">
		<table width="100%" align="center" bgcolor="#90c7fc" border="0">
				<%String cor = "#cbe5fe"; %>
				
			<tr bordercolor="#000000">
				<td width="50%" bgcolor="#90c7fc">
				<div align="center" class="style9"><font color="#000000"
					style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <strong>GRUPO DE SOLICITAÇÕES</strong> </font></div>
				</td>
				<td width="50%" bgcolor="#90c7fc">
				<div align="center" class="style9"><font color="#000000"
					style="font-size:9px"
					face="Verdana, Arial, Helvetica, sans-serif"> <strong>UNIDADE SUGERIDA PARA TRÂMITE</strong> </font></div>
				</td>
			</tr>
			<%cor = "#cbe5fe";%>
			<logic:present name="colecaoTramitesGruposSolicitacoesHelper" scope="session">
				<logic:iterate name="colecaoTramitesGruposSolicitacoesHelper"
					id="tramitesGruposSolicitacoesHelper" indexId="i" scope="session">
					<%if (cor.equalsIgnoreCase("#cbe5fe")) {
						cor = "#FFFFFF";%>
					<tr bgcolor="#FFFFFF">
					<%} else {
							cor = "#cbe5fe";%>
					<tr bgcolor="#cbe5fe">
					<%}%>
						<td>
							<html:text property="idsGrupoSolicitacao" size="4" maxlength="4" 
							readonly="true" 	 
							value="${tramitesGruposSolicitacoesHelper.solicitacaoTipoGrupo.id}" />
							<html:text property="descricoesGrupoSolicitacao" 
								size="22"
								maxlength="35" 
								readonly="true"
								value="${tramitesGruposSolicitacoesHelper.solicitacaoTipoGrupo.descricao}"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</td>
						<td>
							<html:text property="idsUnidadeOrganizacional" size="4" maxlength="4" 
									   value="${tramitesGruposSolicitacoesHelper.unidadeOrganizacional.id}"
									   onkeyup="validaEnter(event, 'exibirInserirTramitesGruposSolicitacoesAction.do?consultaUnidade=1&posicaoUnidade=${i}', 'idsUnidadeOrganizacional[${i}]');return isCampoNumerico(event);" />
							<img src="<bean:message key='caminho.imagens'/>pesquisa.gif"
                                width="23" height="21" style="cursor: hand;" name="imagem"
                                onclick="chamarPopup('exibirPesquisarUnidadeOrganizacionalAction.do',${i},275, 480);"
                                alt="Pesquisar">	
                   			<html:text property="descricoesUnidadeOrganizacional" 
								size="22"
								maxlength="35" 
								readonly="true"
								value="${tramitesGruposSolicitacoesHelper.unidadeOrganizacional.descricao}"
								style="background-color:#EFEFEF; border:0; color: #000000" />
                     	<a href="javascript:limparUnidade(${i});"> <img
								src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" title="Apagar" />	
						</td>			
					</tr>
				</logic:iterate>
			</logic:present>
		</table>
		</td>
	</tr>
      
      
      
	 	   <tr>
	       <td></td>
	       <td><font color="#FF0000">*</font>&nbsp;Campos obrigat&oacute;rios</td>
	   </tr>
	
		<tr>
			<td colspan="2">
			 <input type="button" name="Button"
						class="bottonRightCol" value="Cancelar" tabindex="18"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"
						style="width: 80px" />
			</td>
			<td align="right">
				<gsan:controleAcessoBotao name="Button"
				value="Inserir" tabindex="16"
				onclick="javascript:document.forms[0].submit();"
				url="inserirTramitesGruposSolicitacoesAction.do" /><!-- 
				<input type="button" name="Button" class="bottonRightCol"
				value="Inserir" tabindex="6" style="width: 80px"
				onClick="javascript:validarForm(document.forms[0])" />  -->
			</td>
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


<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page isELIgnored="false"
	import="java.util.Collection,gcom.util.ConstantesSistema"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="FiltrarClienteReceitaFederalActionForm" dynamicJavascript="false" />
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript">

function verificaValidacaoDocumento(){

var retorno;
var form = document.forms[0];

     if ((form.indicadorValidacao[0].checked == false) && (form.indicadorValidacao[1].checked == false) &&
     (form.cpf.value == '') && (form.cnpj.value == '')){
            alert('Informe os dados de entrada.');
            setarFoco('indicadorValidacao');
            retorno = 1;  
     } else

     if ((form.indicadorValidacao[1].checked) && (form.cpf.value == '') && (form.cnpj.value == '')){
          alert('Informe CPF ou CNPJ.');
          setarFoco('indicadorValidacao');
          retorno = 1;
     } else 
     
     if (temLetra(form.cpf.value) == 1){
          alert('O campo CPF só aceita números.');
          setarFoco('cpf');
          retorno = 1;
     } else
     
     if (temLetra(form.cnpj.value) == 1){
          alert('O campo CNPJ só aceita números.');
          setarFoco('cnpj');
          retorno = 1;
     } else {
          retorno = 0;
     }
     
     return retorno;
            
}

function temLetra(texto){
        var numeros = ",.0123456789";
        var retorno;	
        texto = texto.toLowerCase();
        for(i=0; i<texto.length; i++){
             if (numeros.indexOf(texto.charAt(i),0)!=-1){
             retorno = 0;
             }else{
             retorno = 1;
             i = texto.length;
             }
        }
        return retorno;
   } 

function limpar(){

     var form = document.forms[0];
     
     form.indicadorValidacao[0].checked = false;
     form.indicadorValidacao[1].checked = false;
     form.cpf.value = '';
     form.cnpj.value = '';
     
     form.action = 'exibirFiltrarClienteReceitaFederalAction.do?limpar=sim';
	 form.submit();
     
     setarFoco('indicadorValidacao');
          
}

function filtrar(){
          var form = document.forms[0];
          
          if ((form.indicadorValidacao[0].checked == false) && (form.indicadorValidacao[1].checked == false)){
               alert('Informe o campo obrigatório.');
          } else   
          if ((form.indicadorValidacao[0].checked == true) && ((form.cpf.value != '') || (form.cnpj.value != ''))){
               alert('Para filtrar por cpf ou cnpj marque o indicador por documento.');
          } else
          if (verificaValidacaoDocumento() == 0){  
                     
               form.action = 'exibirFiltrarClienteReceitaFederalAction.do?filtrar=sim';
	           form.submit();
	      } 
}

function validar(){
          if (verificaValidacaoDocumento() == 0){  
               var form = document.forms[0];      
               form.action = 'filtrarClienteReceitaFederalAction.do';
	           toggleBox('demodiv',1);
	      }
}

// A função redirecionarSubmitAtualizar é definida dentro do cliente_inserir
function enviarIdParaInserir(idCliente) {
	opener.redirecionarSubmitAtualizar(idCliente);
	self.close();
}

//FUNCAO COM MAIS PARAMETROS. NECESSARIO NA BUSCA DO FILTRO CLIENTE EM INSERIR/MANTER CONTRATO PARCELAMENTO POR CLIENTE.
function enviarDadosConsultaContrParcel(codigoRegistro, descricaoRegistro, cnpj, tipoConsulta) {
	   opener.recuperarDadosPopupClienteContrParcel(codigoRegistro, descricaoRegistro, cnpj, tipoConsulta);
	   self.close();
	}
	

function getURLParameter( name )
{
  name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
  var regexS = "[\\?&]"+name+"=([^&#]*)";
  var regex = new RegExp( regexS );
  var results = regex.exec( window.location.href );
  if( results == null )
    return "";
  else
    return results[1];
}

</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">

<html:form action="/filtrarClienteReceitaFederalAction" method="post">

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
			<td width="620" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
					</td>
					<td class="parabg">Filtrar Clientes para Validação à Base Receita Federal</td>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<td colspan="2">
						<p>Para filtrar, informe os dados abaixo:</p>
					</td>
				</tr>
	        </table>
	        <table bordercolor="#000000" width="100%" cellspacing="0">    
	            <tr>
					<td>&nbsp;</td>
				</tr>
	            <tr height="30">
					<td>
						<strong>Validação:<font color="#FF0000">*</font></strong>
					</td>
					<td>
						<html:radio tabindex="1" name="FiltrarClienteReceitaFederalActionForm" property="indicadorValidacao"
							value="1" />
						<strong>Por Conjunto de Clientes</strong> 
						<html:radio tabindex="2" name="FiltrarClienteReceitaFederalActionForm" property="indicadorValidacao"
							value="2" />
						<strong>Por Documento</strong>
					</td>
				</tr>
				<tr>
					<td width="20%" height="30">
						<strong>CPF:</strong>
					</td>
					<td>
						<html:text tabindex="3" name="FiltrarClienteReceitaFederalActionForm" property="cpf" size="11" maxlength="11" onkeypress="return isCampoNumerico(event);" />
					</td>
				</tr>
				<tr>
					<td height="30">
						<strong>CNPJ:</strong>
					</td>
					<td>
						<html:text tabindex="4" name="FiltrarClienteReceitaFederalActionForm" property="cnpj" size="14" maxlength="14" onkeypress="return isCampoNumerico(event);" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<table width="100%" border="0">
							<tr>
								<td align="left" width="50%">
									<input tabindex="5" type="button" name="ButtonReset" class="bottonRightCol"
										value="Limpar" onClick="javascript:limpar();">
								</td>
								<td align="right">
								    <input tabindex="6" type="button" name="ButtonReset" class="bottonRightCol"
										value="Filtrar" onClick="javascript:filtrar();">
								</td>
							</tr>	
						</table>
					</td>
				</tr>
				
				
				<tr>
					<td colspan="2">
						<hr>
					</td>
				</tr>
				

			<logic:present name="colecaoCliente" scope="session">		
		
			
			
		    <table width="100%" border="0" bgcolor="#90c7fc">
			<tr align="left" bgcolor="#90c7fc">
				<td width="10%" align="center"><strong>Id Cliente</strong></td>
				<td width="20%" align="center"><strong>Documento</strong></td>
				<td width="70%" align="center"><strong>Nome do Cliente</strong></td>
			</tr>



			
			<tr>
			<pg:pager isOffset="true" index="half-full" maxIndexPages="20"
				export="currentPageNumber=pageNumber;pageOffset"
				maxPageItems="20" items="${sessionScope.totalRegistros}">
					<pg:param name="pg" />
					<pg:param name="q" />
					<%int cont = 0;%>
			        <logic:iterate name="colecaoCliente" id="cliente">
					<pg:item>
							<%cont = cont + 1;
							if (cont % 2 == 0) {%>
								<tr bgcolor="#cbe5fe">								
							<%} else {	%>
								<tr bgcolor="#FFFFFF">
							<%}%>
				
							<td width="10%" align="center"><bean:write
								name="cliente" property="id" /></td>

							<td width="20%" align="center">
							
							<logic:notEqual name="cliente" property="cnpj" value="">
							<bean:write name="cliente" property="cnpj" />
							</logic:notEqual>
							
							<logic:notEqual name="cliente" property="cpf" value="">
							<bean:write name="cliente" property="cpf" />
							</logic:notEqual>
							
							</td>
							
							<td width="70%"><bean:write
								name="cliente" property="nome" /></td>
						</tr>
					</pg:item>
				</logic:iterate>
				</tr>
			    </table>
			    
			    
			    
				
				<table width="100%" border="0">
			    <tr>
				<td>
				<div align="center"><strong>
				
				<%@ include file="/jsp/util/indice_pager_novo.jsp"%>
				
				</strong></div>
				</td>
			    </tr>
			    
			    
			    
			    
			    <tr>
					<td align="left">
						      <input tabindex="7" type="button" name="ButtonReset" class="bottonRightCol"
						      value="Cancelar" onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td align="right">
						      <input tabindex="8" type="button" name="ButtonReset" class="bottonRightCol"
						      value="Validar" onClick="javascript:validar();">
					</td>
				</tr>
				
				
				
				
		        </table>
		        
		        <div>
					<strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios
				</div>
		        
				</pg:pager>
		        
		        
		      
		    
		    					
			</logic:present>
			 
			   
			    
			</table>
			
			<logic:notPresent name="colecaoCliente" scope="session">     
				<div>
					<strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios
				</div>
			    </logic:notPresent> 
			 	
		</td>
	</tr>
</table>
<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=/gsan/filtrarClienteReceitaFederalAction.do" />
<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gcom.util.ConstantesSistema"%>

<%@ page import="gcom.gui.cobranca.cobrancaporresultado.MotivosNaoPagamentoCobrancaoResultadoHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="EfetuarLigacaoAguaActionForm" />


<script language="JavaScript">

function replicaDados( refInicial, refFinal){

	refFinal.value = refInicial.value;
}
function IsNumeric(strString)
//  check for valid numeric strings	
{
var strValidChars = "0123456789.-";
var strChar;
var blnResult = true;

if (strString.length == 0) return false;

//  test strString consists of valid characters listed above
for (i = 0; i < strString.length && blnResult == true; i++)
   {
   strChar = strString.charAt(i);
   if (strValidChars.indexOf(strChar) == -1)
      {
      blnResult = false;
      }
   }
return blnResult;
}


	function validaAnoMesSemAlertNovo(mydata) {
	
	var situacao = false;
	
	if (mydata.value.length == 7) {
	
    	var mes = mydata.value.substring(0,2); 
    	var ano = mydata.value.substring(3,7); 

    	
    	if ( !isNaN(mes) || !isNaN(ano)) {
    	
    		// verifica se o mes e valido 
	    	if ((mes * 1) >= 1 && (mes * 1) <= 12 ) { 
	        
	        	// verifica se o ano e valido
	        	if ((ano * 1) != 0 && (ano * 1) >= 1980) {
	        	
	       				situacao = true;
	   					mydata.focus(); 

	        	}
	        	else{
	        		situacao = false;
	        		mydata.value = "";
	   				mydata.focus();
	        	} 
	    	}
	    	else{
	    		situacao = false;
	    		mydata.value = "";
	   			mydata.focus();
	    	} 
		}
		else{
			situacao = false;
	   		mydata.value = "";
	   		mydata.focus();
		}
    }
    return situacao;
}
	</script>

<script language="JavaScript">


 
	
	
	function validaImovel(){
		var form = document.ConsultarMotivoNaoPagamentoCobracaResultadoActionForm;
		
		 if((form.idImovel.value == "")){
				alert('Informe Matrícula do Imóvel ');
				return false;
			}else {
				return testarCampoValorInteiroComZero(form.idImovel,'Imóvel');
			}
		
	}

	

	

	
	
	function limparForm(imovel, objetoRelacionado) {
		var form = document.ConsultarMotivoNaoPagamentoCobracaResultadoActionForm;
		window.location.href='/gsan/exibirConsultarMotivoNaoPagamentoCobracaResultadoAction.do?menu=sim';
       
	 }
     
	   function habilitaReferencias(){
		    var form = document.forms[0];
		    if(form.idImovel.value != ""){
				form.referenciaInicial.readOnly = false;
				form.referenciaInicial.style.backgroundColor = '';
				
				form.referenciaFinal.readOnly = false;
				form.referenciaFinal.style.backgroundColor = '';
				
				form.idImovel.readOnly = true;
				form.idImovel.style.backgroundColor = '#EFEFEF';

		    }else{
		     form.referenciaInicial.value ='';
			 form.referenciaInicial.readOnly = true;
			 form.referenciaInicial.style.backgroundColor = '#EFEFEF';
		     form.referenciaFinal.value ='';
			 form.referenciaFinal.readOnly = true;
			 form.referenciaFinal.style.backgroundColor = '#EFEFEF';

			 form.idImovel.readOnly = false;
			 form.idImovel.style.backgroundColor = '';
		     
		     }
		   }

	   function limparComandos() {
			var form = document.forms[0];
			if(form.idImovel.value == "" || validaImovel() == true ){
				form.action = 'exibirConsultarMotivoNaoPagamentoCobracaResultadoAction.do?limpar=sim';			
				form.submit(form);
	   		}
		}
		

	   function limparImovelTecla() {

			var form = document.forms[0];
			
			form.setMatriculaImovel("");
			form.setSituacaoLigacaoAgua("");
			form.setSituacaoLigacaoEsgoto("");
			form.setInscricaoImovel("");
			form.setClienteUsuario("");
			form.setCpfCnpjCliente("");
			form.setClienteUsuario("");
			form.setCpfCnpjCliente("");			
			form.setCpfCnpjCliente("");
			form.setReferenciaFinal("");
			form.setReferenciaInicial("");
			
			

		}
	  

	function validaMesAno(mesAno){
		if(mesAno.value != ""){
			return verificaAnoMesMensagemPersonalizada(mesAno,"Mês/Ano de Referência inválido");
		}else{
			return true;
		}
	}	
	
	function pesquisarDadosImovel(){
		var form =  document.forms[0];

		if(validaImovel() != false){	
			var comando = form.comando;
			var dataInicial = form.referenciaInicial;
			var dataFinal = form.referenciaFinal;
			
			var msg = '';

			if((dataInicial != null && dataInicial.value != "") && (dataFinal == null ||dataFinal.value == "" )){
				msg="Referência Inicial invalida.";
			}
	
		
			if((dataFinal.value != null && dataFinal.value != "") && (dataInicial == null || dataInicial.value == "" )){
				msg ="Referência Final ivalida. ";
			}

			
			if(msg =='' && dataInicial != null && dataInicial.value != ""){
				
				if(validaAnoMesSemAlertNovo(dataInicial) == false){
					msg ="Referência Inicial do Período inválida. Informe outra referência.";
				} 
			}
			
			if(msg =='' && dataFinal != null && dataFinal.value != ""){
				if(validaAnoMesSemAlertNovo(dataFinal) == false){
					msg="Referência Final do Período inválida. Informe outra referência.";
				} 
			}
			
			if (msg =='' && ((dataInicial.value != "" ||dataInicial.value != null) && (dataFinal.value != "" || dataFinal.value != null)) 
					&& (comparaMesAno(dataInicial.value,">", dataFinal.value))){
				msg = "Referência Inicial maior que Referência Final.";
			}

			
			
			if( msg != '' ){
				alert(msg);			
			}else{
				form.action = 'exibirConsultarMotivoNaoPagamentoCobracaResultadoAction.do?pesquisarDadosImovel=sim';
				form.submit();
			}
		}
	}	

	function consultarDadosImovel(){
		var form =  document.forms[0];
		if(validaImovel()){
			form.submit();
		}
	}
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form =  document.forms[0];
		if (tipoConsulta == 'imovel') {
			form.idImovel.value = codigoRegistro;
		}
	}
	 
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		if(objetoRelacionado.readOnly != true){
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			}else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	}
	 
		 
	


		
</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');habilitaReferencias();">

<html:form action="/exibirConsultarMotivoNaoPagamentoCobracaResultadoAction.do"
	name="ConsultarMotivoNaoPagamentoCobracaResultadoActionForm"
	type="gcom.gui.cobranca.cobrancaporresultado.ConsultarMotivoNaoPagamentoCobracaResultadoActionForm"
	method="post">
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
						<td class="parabg">Consultar Motivo de Não Pagamento na Cobrança por Resultado</td>
						<td width="11" valign="top"><img border="0"
							src="imagens/parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>				
				<table width="100%" border="0">
					<tr>
						<td colspan="2">Para verificar os motivos de não geração, informe os dados abaixo: </td>					
					</tr>
					<tr><td>
					<table width="100%" border="0">
					<tr>
						<td height="10">
							<strong>Matrícula do Imóvel:<font color="#FF0000">*</font></strong>
						</td>

						<td><span> <html:text
							property="idImovel" size="8" maxlength="9" onchange="habilitaReferencias();limparComandos();"							
							onkeypress="validaEnterComMensagem(event,'exibirConsultarMotivoNaoPagamentoCobracaResultadoAction.do','idImovel','Imóvel'); return isCampoNumerico(event);"
							/>
						<a href="javascript:chamarPopup('exibirPesquisarImovelAction.do', 'imovel', null, null, 275, 480, '',document.forms[0].idImovel);">	
							<img width="23" height="21" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
							style="cursor:hand;" alt="Pesquisar" border="0" title="Pesquisar Imóvel"/></a>
						  <a
							href="javascript:limparForm(1,document.forms[0].idImovel);">
						<img border="0" title="Apagar"
							src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
						</a> </span></td>    
						</tr>
						
						
          			
					</table>
        			</td>
        			</tr>
        			
        			<tr><td colspan="4" height="8"></td></tr>
					<tr bgcolor="#cbe5fe">
						<td align="center" colspan="2">
						<table width="100%" border="0" bgcolor="#99CCFF">
							<tr color="#ff0000" >
								<td height="18" colspan="2">
								<div align="center"><span class="style2"> Dados do Imóvel </span></div>
								</td>
							</tr>
							<tr bgcolor="#cbe5fe">
	
								<td>
								<table border="0" width="100%">
									<tr>
										<td width="37%" height="10"><strong>Matr&iacute;cula do
										Im&oacute;vel:</strong></td>
										<td width="58%"><html:text property="matriculaImovel"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="15" maxlength="20" /> <html:text
											property="inscricaoImovel" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="21" maxlength="20" /></td>
									</tr>
									<tr>
										<td><strong> Cliente Usu&aacute;rio:</strong></td>
										<td><html:text property="clienteUsuario" readonly="true" 
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="40" maxlength="40" /></td>
	
									</tr>
									<tr>
										<td><strong>CPF ou CNPJ:</strong></td>
										<td><html:text property="cpfCnpjCliente" readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="40" maxlength="40" /></td>
	
	
									</tr>
									<tr>
	
										<td><strong>Situa&ccedil;&atilde;o da
										Liga&ccedil;&atilde;o de &Aacute;gua:</strong></td>
										<td><html:text property="situacaoLigacaoAgua"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="40" maxlength="40" /></td>
	
	
									</tr>
									<tr>
										<td><strong>Situa&ccedil;&atilde;o da
										Liga&ccedil;&atilde;o de Esgoto:</strong></td>
	
										<td><html:text property="situacaoLigacaoEsgoto"
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000"
											size="40" maxlength="40" /></td>
									</tr>
								</table>
								</td>
							</tr>
	
						</table>
						</td>
					</tr>
				</table>
						
				<table width="100%" border="0">
					<tr>
						<td colspan="4" height="24"><hr></td>
					</tr>	
					
					<tr>
						<td width="30%"><strong>Período de Referência da Arrecadação :</strong></td>
							<td>
								<strong>
									<html:text maxlength="7" 
											   property="referenciaInicial" 
											   size="7"
	 										   onkeyup="mascaraAnoMes(this, event); replicaDados(document.forms[0].referenciaInicial, document.forms[0].referenciaFinal);" 
	 										   onkeypress="return isCampoNumerico(event);"/> 
									<strong> a</strong>
									<html:text maxlength="7" 
											   property="referenciaFinal" 
											   size="7" 
											   onkeyup="mascaraAnoMes(this, event);" 
											   onkeypress="return isCampoNumerico(event);"/>
									</strong> (mm/aaaa)
							</td>
					</tr>				
					<tr> 
						<td height="6"><strong>Comando(s): </strong></td>
						<td style="float:left">
							<logic:notEmpty name="colecaoHelper">
							<html:select property="idsComandos" style="width: 230px;" multiple="mutiple" size="4">
								<html:option value="-1">&nbsp;</html:option> 
								<logic:iterate name="colecaoHelper" id="helper">
									<option value="<bean:write property="comando" name="helper"/>">
										<bean:write property="comando" name="helper" />
									</option>
								</logic:iterate>
							</html:select>
							
							</logic:notEmpty>
							<logic:empty name="colecaoHelper">
								<select disabled="disabled">
									<option value="-1">&nbsp;</option>
								</select>
							</logic:empty>
						</td>	
					</tr>				
					<tr><td>&nbsp;</td></tr>
						
					
					
					<tr>
         				 <td></td>         
		  					<td align="right">
		  						<input type="button"
										name="selecionar" class="bottonRightCol"
										value="Selecionar"
										onClick="javascript:pesquisarDadosImovel();" />
		  					</td>
          				<td></td>
        			</tr>
				</table>
					
				<table width="100%" border="0">
					 <tr><td colspan="4" height="24"><hr></td></tr>
					 
					 
                     <tr bgcolor="#79bbfd">
							<td align="center" colspan="2">
								<table width="100%" border="0" bgcolor="#79bbfd">
									<tr bgcolor="#79bbfd">
										<td height="18" colspan="2" 
											    align="center" rowspan="2"><strong>Pagamentos não Gerados para Cobrança</strong>
										</td>
									</tr>
									<tr bgcolor="#cbe5fe"></tr>
								</table>
					</tr>                     
		              
		              <tr>
			              <td colspan="4">
			                <table width="100%" bgcolor="#99CCFF" border="0">
				               <tr bordercolor="#000000">
					              <td width="9%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Mês/ano</strong></td>
					              <td width="10%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Valor</strong></td>
					              <td width="9%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Dt.Pagamento</strong></td>
					              <td width="9%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Dt.Proc.</strong></td>
					              <td width="9%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Dt.Geração </strong></td>
					              <td width="30%" bgcolor="#90c7fc" align="center" rowspan="2"><strong>Motivo de não geração </strong></td>
					        </table>
			             </td>
		             </tr>
		             
		             	<tr>
							<td colspan="4">
								<div style="height: 100%; max-height: 300px; overflow: auto;">
									<table width="100%" bgcolor="#99CCFF">
											<logic:present
												name="colecaoMotivosNaoPagamentoCobrancaoResultadoHelper">
												<%int cont = 0;%>
												<logic:iterate
													name="colecaoMotivosNaoPagamentoCobrancaoResultadoHelper"
													id="motivosNaoPagamentoCobrancaoResultadoHelper"
													type="MotivosNaoPagamentoCobrancaoResultadoHelper"
													scope="session">
														<%cont = cont + 1;
												if (cont % 2 == 0) {%>
														<tr bgcolor="#cbe5fe">
															<%} else {%>
														</tr>
														<tr bgcolor="#FFFFFF">
															<%}%>
															<td align="center" width="10%">
																<logic:empty name="motivosNaoPagamentoCobrancaoResultadoHelper" property="tipoConta">
																	<bean:write name="motivosNaoPagamentoCobrancaoResultadoHelper" property="anoMes"/>
																</logic:empty>
																<logic:notEmpty name="motivosNaoPagamentoCobrancaoResultadoHelper" property="tipoConta">
																<logic:equal name="motivosNaoPagamentoCobrancaoResultadoHelper" property="tipoConta" value="conta">
																	<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:write name="motivosNaoPagamentoCobrancaoResultadoHelper" property="idConta" />&tipoConsulta=conta', 600, 800);"><bean:write name="motivosNaoPagamentoCobrancaoResultadoHelper" property="anoMes"/></a>
																</logic:equal>
																<logic:equal name="motivosNaoPagamentoCobrancaoResultadoHelper" property="tipoConta" value="contaHistorico">
																	<a href="javascript:abrirPopup('exibirConsultarContaAction.do?contaID=<bean:write name="motivosNaoPagamentoCobrancaoResultadoHelper" property="idConta" />&tipoConsulta=contaHistorico', 600, 800);"><bean:write name="motivosNaoPagamentoCobrancaoResultadoHelper" property="anoMes"/></a>
																</logic:equal>
																</logic:notEmpty>																 
															</td>
						
															<td align="center" width="10%">
																<bean:write name="motivosNaoPagamentoCobrancaoResultadoHelper" property="valorPagamento"/> 
															</td>
						
															<td align="center" width="13%">
																<bean:write name="motivosNaoPagamentoCobrancaoResultadoHelper" property="dataPagamento"/> 
															</td>
						
															<td align="center" width="9%">
																<bean:write name="motivosNaoPagamentoCobrancaoResultadoHelper" property="dtProcessamento"/> 
															</td>
															
															<td align="center" width="9%">
																<bean:write name="motivosNaoPagamentoCobrancaoResultadoHelper" property="dtNaoGeracao"/> 
															</td>
															
															<td align="center" width="30%">
																<bean:write name="motivosNaoPagamentoCobrancaoResultadoHelper" property="motivoNaoGeracao"/> 
															</td>
						
														</tr>
												</logic:iterate>
											</logic:present>
									</table>
								</div>
							</td>
						</tr>
				</table>
				<table width="100%" border="0">
					<tr><td>&nbsp;</td></tr>
					<tr>
						<td colspan="2">
							<input value="Limpar" class="bottonRightCol" type="button" onclick="window.location.href='/gsan/exibirConsultarMotivoNaoPagamentoCobracaResultadoAction.do?menu=sim';">
							&nbsp;
							<input name="Button" type="button" class="bottonRightCol"
								value="Cancelar" align="left"
								onclick="window.location.href='/gsan/telaPrincipal.do'">
						</td>
						<td colspan="3" align="right">
	       					<strong> <font align="right" color="#FF0000">* </font>  </strong> Campos obrigat&oacute;rios
	          			</td>
						
					</tr>
				</table>
			</td>
			</tr>
				
		</table>
			
			<%@ include file="/jsp/util/rodape.jsp"%>
	
	</html:form>
</body>
</html:html>

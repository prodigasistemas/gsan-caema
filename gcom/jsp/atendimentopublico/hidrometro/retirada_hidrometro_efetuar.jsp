<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="EfetuarRetiradaHidrometroActionForm" />

<script language="JavaScript">
	
	function validarForm(form) {
		if ((form.quantidadeParcelas != undefined) && (form.quantidadeParcelas.value == '')){
		     alert("� necess�rio informar a quatidade de parcelas.");
		} else
		if ((form.quantidadeParcelas != undefined) && (form.quantidadeParcelas.value == 0)){
		     alert("Quantidade de Parcelas informada n�o pode ser igual a 0(zero).");
		}
		else if(validateEfetuarRetiradaHidrometroActionForm(form) && validaDebito() && validarExtraviado()){
   			submeterFormPadrao(form);
		}
	}

	function validarExtraviado(){

		var form = document.EfetuarRetiradaHidrometroActionForm;

		var idSituacaoHidrometro = document.getElementById('situacaoHidrometro').value;
		
        if(idSituacaoHidrometro!=4 && form.hidrometroLocalArmazenagem.value == '-1'){
        	alert("Informe Local de Armazenagem do Hidr�metro.");
        	return false;
        } 

		return true;	
	}
	
	function limparForm() {

		var form = document.EfetuarRetiradaHidrometroActionForm;

		form.idOrdemServico.value = "";
		form.nomeOrdemServico.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		//form.hidrometro.value = "";
		
		if(form.indicadorMacromedidor.value != 2)
			form.numeroTombamento.value = "";
		
		if(form.veioEncerrarOS.value != 'true')
			form.dataRetirada.value = "";

	    form.medicaoTipo.value = "";
	    form.numeroLeitura.value = "";
	    form.idHidrometroSituacao.value = "-1";
	    form.hidrometroLocalArmazenagem.value = "-1";
	    if(form.idTipoDebito != null){
	      form.idTipoDebito.value = "";
	      form.descricaoTipoDebito.value = "";
	      form.valorDebito.value = "";
	      if(form.motivoNaoCobranca != null){
	        form.motivoNaoCobranca.value = "-1";
	      }
	      form.percentualCobranca.value = "-1"; 
	      form.quantidadeParcelas.value = "";
	      form.valorParcelas.value = "";
	      
		}
		form.action = 'exibirEfetuarRetiradaHidrometroAction.do';
		form.submit();
	 }
	
	function limparOrdemServico() {

		var form = document.EfetuarRetiradaHidrometroActionForm;

		form.nomeOrdemServico.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		form.hidrometro.value = "";
		form.medicaoTipo.value = "";
	    form.dataRetirada.value = "";
	    form.numeroLeitura.value = "";
	    form.idHidrometroSituacao.value = "-1";	
	    form.hidrometroLocalArmazenagem.value = "-1";	    
	    if(form.idTipoDebito != null){
	      form.idTipoDebito.value = "";
	      form.descricaoTipoDebito.value = "";
	      form.valorDebito.value = "";
	      if(form.motivoNaoCobranca != null){
	        form.motivoNaoCobranca.value = "-1";
	      }
	      form.percentualCobranca.value = "-1";
	      form.quantidadeParcelas.value = "";
	      form.valorParcelas.value = "";
	    }				
	}
	
	function limparOrdemServicoTecla() {

		var form = document.EfetuarRetiradaHidrometroActionForm;

		form.nomeOrdemServico.value = "";
		form.matriculaImovel.value = "";
		form.inscricaoImovel.value = "";
		form.clienteUsuario.value = "";
		form.cpfCnpjCliente.value = "";
		form.situacaoLigacaoAgua.value = "";
		form.situacaoLigacaoEsgoto.value = "";
		//form.hidrometro.value = "";
		form.medicaoTipo.value = "";
	    form.dataRetirada.value = "";
	    form.numeroLeitura.value = "";
	    
	    if(form.idTipoDebito != null){
	      form.idTipoDebito.value = "";
	      form.descricaoTipoDebito.value = "";
	      form.valorDebito.value = "";
	      if(form.motivoNaoCobranca != null){
  	        form.motivoNaoCobranca.value = "-1";
  	      }
	      form.percentualCobranca.value = "-1";
	      form.quantidadeParcelas.value = "";
	      form.valorParcelas.value = "";
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
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto + "&caminhoRetornoTelaPesquisaOrdemServico=" + tipo, altura, largura);
				}
			}
		}
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
   		var form = document.forms[0];
   
		if (tipoConsulta == 'ordemServico') {
	    	form.idOrdemServico.value = codigoRegistro;
	      	form.action='exibirEfetuarRetiradaHidrometroAction.do';
	      	form.submit();
	    }
  	}
  	
  		//verifica se existe alguma restri��o 
	//para exibi��o alguma campo no formulario
    function verificaForm() {

       	var form = document.forms[0];

		if(form.veioEncerrarOS.value == 'true'){
			form.idOrdemServico.disabled=true;
			document.getElementById("limparOS").href = "javascript:void(0)";
		}else{
			form.idOrdemServico.disabled=false;
			document.getElementById("limparOS").href = "javascript:limparOrdemServico();";
		}       	
	}	
	
	function calcularValores(){
	
		var form = document.EfetuarRetiradaHidrometroActionForm;
		
		if (form.quantidadeParcelas.value == ''){
		     alert("� necess�rio informar a quatidade de parcelas.");
		} else
		if (form.quantidadeParcelas.value == 0){
		     alert("Quantidade de Parcelas informada n�o pode ser igual a 0(zero).");
		}
		else if (validateEfetuarRetiradaHidrometroActionForm(form) && validaDebito()){
	   		form.action='exibirEfetuarRetiradaHidrometroAction.do?calculaValores=S';
	      	form.submit();
		}
	}
	
	//verifica se o motivo da n�o cobra�a naum foi informado
	//libera o campo quantidade de parcelas 
	function habilitarQtdParcelaButton(){
		var form = document.forms[0];
		if(form.motivoNaoCobranca.value == "-1"){	
			form.percentualCobranca.value = "-1"
			form.valorParcelas.value = "";
			form.percentualCobranca.disabled = false;
			form.quantidadeParcelas.disabled = false;
			form.valorParcelas.value = "";
			form.buttonCalcular.disabled = false;
		}else{
			form.percentualCobranca.value = "-1"
			form.percentualCobranca.disabled = true;
			form.quantidadeParcelas.disabled = true;
			form.quantidadeParcelas.value = "";
			form.valorParcelas.value = "";
			form.buttonCalcular.disabled = true;
		}
	}		
	
	/*
	Verifica se o usuario pesquisou a ordem de servico
	antes de efetuar a operacao.
	*/
  function verificaSepesquisouOrdemServico(){

  matriculaImovel = document.getElementById("matriculaImovel").value;
  if(matriculaImovel == "" || matriculaImovel == null){
  alert("Pesquise Ordem de servi�o primeiro para prosseguir.");
  }
  else{
  validarForm(document.forms[0]);
  }
  }
  
 function verificaSituacaoHidrometro(){
 	var form = document.forms[0];
 	var idSituacaoHidrometro = document.getElementById('situacaoHidrometro').value;
 	var bloquearLocalArmaz = form.bloquearLocalArmazenamento.value;
 	
  	if(idSituacaoHidrometro == 4){
  		document.getElementById('localArmazenagemHidrometro').disabled=true;	
  		document.getElementById('localArmazenagemObrigatorio').innerHTML="";
	}else{
  		document.getElementById('localArmazenagemHidrometro').disabled=false;
  		document.getElementById('localArmazenagemObrigatorio').innerHTML="*"; 		
  	}
  	
  	if(bloquearLocalArmaz == "1"){
  		document.getElementById('localArmazenagemHidrometro').disabled=true;
  	}
 }
 
 function changeSituacaoHidrometro(){ 	
 	var form = document.forms[0];
 	form.action='exibirEfetuarRetiradaHidrometroAction.do';
   	form.submit();
 }	
		
</script>


</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:verificaForm();setarFoco('${requestScope.nomeCampo}');verificaSituacaoHidrometro();">

<html:form action="/efetuarRetiradaHidrometroAction.do"
	name="EfetuarRetiradaHidrometroActionForm"
	type="gcom.gui.atendimentopublico.hidrometro.EfetuarRetiradaHidrometroActionForm"
	method="post">

	<html:hidden property="veioEncerrarOS" />
	<html:hidden property="hidrometroExtraviado" />
	<html:hidden property="bloquearLocalArmazenamento" />
	<html:hidden property="indicadorMacromedidor" />

	<logic:notPresent name="semMenu">

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
				<td width="615" valign="top" class="centercoltext">
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>

				</logic:notPresent> <logic:present name="semMenu">
					<table width="550" border="0" cellspacing="5" cellpadding="0">
						<tr>
							<td width="615" valign="top" class="centercoltext">
							<table height="100%">
								<tr>
									<td></td>
								</tr>
							</table>
							</logic:present>

							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="11"><img border="0"
										src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
									<td class="parabg">Efetuar Retirada de Hidr�metro</td>
									<td width="11"><img border="0"
										src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
								</tr>
							</table>
							<p>&nbsp;</p>
							<table width="100%" border="0">
								<tr>

									<td height="31">
									<table width="100%" border="0" align="center">
										<tr>
											<td height="10" colspan="2">Para efetuar a retirada do
											hidr�metro, informe os dados abaixo:.</td>
										</tr>
										<tr>
											<td height="10" colspan="2">
											<hr>
											</td>
										</tr>

										<tr>

											<td height="10"><strong>Ordem de Servi&ccedil;o:<span
												class="style2"> <strong><font color="#FF0000">*</font></strong></span>
											</strong></td>

											<td><span class="style2"> <html:text
												property="idOrdemServico" size="8" maxlength="9"
												onkeypress="validaEnterComMensagem(event, 'exibirEfetuarRetiradaHidrometroAction.do', 'idOrdemServico','Ordem de Servi�o');
												return isCampoNumerico(event);"
												onkeyup="javascript:limparOrdemServicoTecla()"
												 /> <a
												href="javascript:chamarPopup('exibirPesquisarOrdemServicoAction.do', 'ordemServico', null, null, 600, 500, '',document.forms[0].idOrdemServico);">
											<img width="23" height="21" border="0"
												src="<bean:message key="caminho.imagens"/>pesquisa.gif"
												title="Pesquisar OS" /></a> <html:text
												property="nomeOrdemServico" readonly="true"
												style="background-color:#EFEFEF; border:0; color: #000000"
												size="37" maxlength="40" /> <a
												href="javascript:limparForm();" id="limparOS"> <img border="0"
												title="Apagar"
												src="<bean:message key='caminho.imagens'/>limparcampo.gif" />
											</a> </span></td>
										</tr>

										<tr bgcolor="#cbe5fe">
											<td align="center" colspan="2">
											<table width="100%" border="0" bgcolor="#99CCFF">
												<tr bgcolor="#99CCFF">
													<td height="18" colspan="2">
													<div align="center"><span class="style2"> Dados do Im�vel </span></div>
													</td>
												</tr>
												<tr bgcolor="#cbe5fe">

													<td>
													<table border="0" width="100%">
														<tr>
															<td width="37%" height="10"><strong><span class="style2">Matr&iacute;cula
															do Im&oacute;vel:<strong></strong></span></strong></td>
															<td width="58%"><html:text property="matriculaImovel" styleId="matriculaImovel"
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
									</td>
								</tr>




								<tr>
									<td height="31">
									<table width="100%" border="0" align="center">
										<tr bgcolor="#cbe5fe">
											<td align="center" colspan="2">
											<table width="100%" border="0" bgcolor="#99CCFF">
												<tr bgcolor="#99CCFF">

													<td height="18" colspan="2">
													<div align="center"><span class="style2"> Dados da Retirada
													de Hidr&ocirc;metro</span></div>
													</td>
												</tr>
												<tr bgcolor="#cbe5fe">
													<td>
													<table border="0" width="100%">
													
													
														<c:choose>
															<c:when test="${EfetuarRetiradaHidrometroActionForm.indicadorMacromedidor == '2'}">
																<tr>
																	<td class="style3"><strong>N&uacute;mero do
																	Hidr&ocirc;metro Atual:</strong></td>
																	<td width="29%"><strong><b><span class="style2"> <html:text
																		property="hidrometro" readonly="true"
																		style="background-color:#EFEFEF; border:0;" size="15"
																		maxlength="15" /> </span></b></strong></td>

																</tr>
															</c:when>
															
															<c:otherwise>															
																<tr>
																	<td><strong> Tombamento do Hidr�metro:</strong></td>
																	<td><html:text property="numeroTombamento"
																		readonly="true"
																		style="background-color:#EFEFEF; border:0; color: #000000"
																		size="10" maxlength="10" /></td>
																</tr>
															</c:otherwise>
														</c:choose>
														

														<tr>
															<td><strong> Tipo da Medi&ccedil;&atilde;o :</strong></td>
															<td><html:text property="medicaoTipo" readonly="true"
																style="background-color:#EFEFEF; border:0; color: #000000"
																size="35" maxlength="40" /></td>

														</tr>
														<tr>

															<td width="45%" height="10"><strong>Data da Retirada:<span
																class="style2"><strong><font color="#FF0000">*</font></strong></span><span
																class="style2"></span></strong></td>
															<td colspan="2"><strong><b> <!-- Campo Data da Ligacao -->
															<html:text property="dataRetirada" readonly="true"
																style="background-color:#EFEFEF; border:0;" size="10"
																maxlength="10" /> </b></strong></td>
														</tr>

														<tr>
															<td width="42%"><strong> N&uacute;mero da Leitura:<span
																class="style2"><strong></strong></span></strong></td>
															<td width="58%"><strong><b> <span class="style2"> <html:text
																property="numeroLeitura" size="4" maxlength="4"
																onkeypress="return isCampoNumerico(event);" /> </span>
															</b></strong></td>

														</tr>

														<tr>
														
															<td class="style3"><strong>Situa��o do Hidr�metro:<span
																class="style2"><strong><font color="#FF0000">*</font></strong></span></strong></td>

															<td colspan="2">
															<strong> 
															<html:select property="idHidrometroSituacao" style="width: 230px;" 
															styleId="situacaoHidrometro" onchange="changeSituacaoHidrometro();">
																<html:option value="-1">&nbsp;</html:option>
																<html:options collection="colecaoHidrometroSituacao"
																	labelProperty="descricao" property="id"/>
															</html:select> 
															</strong>
															</td>
														</tr>

														<tr>
															<td><strong>Local de Armazenagem do Hidr�metro:</strong>								
																<font color="#FF0000">
																<b id="localArmazenagemObrigatorio">	
																</b>
																</font>															
															</td>

															<td>
															<html:select property="hidrometroLocalArmazenagem"
																tabindex="4" styleId="localArmazenagemHidrometro">
															<html:option
															value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;
													        </html:option>
															<html:options
																	collection="colecaoHidrometroLocalArmazenagem"
																	labelProperty="descricao" property="id" />

															</html:select>
															
															</td>
														</tr>

													</table>
													</td>
												</tr>
											</table>
											</td>
										</tr>
									</table>
									</td>
								</tr>
								<logic:notEmpty name="EfetuarRetiradaHidrometroActionForm"
									property="idTipoDebito">
									<tr>
										<td height="31">
										<table width="100%" border="0" bgcolor="#99CCFF">

											<tr bgcolor="#99CCFF">
												<td height="18" colspan="2">
												<div align="center"><span class="style2"> Dados da Gera��o
												do D�bito</span></div>
												</td>
											</tr>
											<tr bgcolor="#cbe5fe">
												<td>
												<table border="0" width="100%">
													<tr>
														<td width="38%" height="10"><strong>Tipo de D�bito:</strong></td>
														<td><strong><!-- Campo Data da Ligacao --> <html:text
															property="idTipoDebito" readonly="true"
															style="background-color:#EFEFEF; border:0;" size="5"
															maxlength="5" /> <html:text
															property="descricaoTipoDebito" readonly="true"
															style="background-color:#EFEFEF; border:0;" size="30"
															maxlength="30" /></strong></td>
													</tr>
													
													
													<!-- Colocado por Raphael Rossiter em 20/04/2007 -->
													<logic:notEqual name="EfetuarRetiradaHidrometroActionForm" property="alteracaoValor" value="OK">
														<tr>
															<td><strong>Valor do D�bito:</strong></td>
															<td align="rigth">
																<html:text property="valorDebito" 
																	readonly="true"
																	style="background-color:#EFEFEF; border:0; text-align: right;" 
																	size="15"
																	maxlength="15" />
															</td>
														</tr>
													</logic:notEqual>
													
													<logic:equal name="EfetuarRetiradaHidrometroActionForm" property="alteracaoValor" value="OK">
														<tr>
															<td><strong>Valor do D�bito:</strong></td>
															<td align="rigth">
																<html:text property="valorDebito" 
																	style="text-align: right;" 
																	size="15"
																	maxlength="15" />
															</td>
														</tr>
													</logic:equal>
													
													<logic:present name="permissaoMotivoNaoCobranca">
														
														<tr>
															<td><strong>Motivo da N�o Cobran�a:<font color="#FF0000">*</font></strong></td>

															<td colspan="2">
																<html:select property="motivoNaoCobranca"
																	style="width: 230px;" 
																	onchange="habilitarQtdParcelaButton();">
																	
																	<html:option value="-1">&nbsp;</html:option>
																	<logic:present name="colecaoNaoCobranca">
																		<html:options collection="colecaoNaoCobranca"
																			labelProperty="descricao" 
																			property="id" />
																	</logic:present>
																</html:select>  
															</td>
														</tr>
														
														<tr>
															<td><strong>Percentual de Cobran�a: <font color="#FF0000">*</font></strong></td>
															<td colspan="2">
																<html:select property="percentualCobranca" 
																	style="width: 70px;">
																	<html:option value="-1">&nbsp;</html:option>
																	<html:option value="100">100%</html:option>
																	<html:option value="70">70%</html:option>
																	<html:option value="50">50%</html:option>
																</html:select>
															</td>
														</tr>
														
														<tr>
															<td><strong>Quantidade de Parcelas:<font color="#FF0000">*</font></strong></td>
															<td colspan="2">
																<html:text property="quantidadeParcelas" 
																	size="5" 
																	maxlength="5"/> 
															</td>
														</tr>
														<tr>
															<td><strong>Valor da Parcela: </strong></td>
															<td colspan="2">
																<html:text property="valorParcelas"
																	readonly="true"
																	style="background-color:#EFEFEF; border:0;" size="15"
																	maxlength="15" />
															</td>
																
															<td colspan="4" align="right">
																<input type="button" 
																	class="bottonRightCol" 
																	value="Calcular"
																	name="buttonCalcular" 
																	tabindex="10"
																	onclick="calcularValores();" 
																	style="width: 80px">
															</td>																
														</tr>
													</logic:present>
													<logic:notPresent name="permissaoMotivoNaoCobranca">
														<tr>
															<td class="style3"><strong>Percentual de Cobran�a: <font
																color="#FF0000">*</font></strong></td>
															<td colspan="2"><strong> <html:select
																property="percentualCobranca" style="width: 70px;">

																<html:option value="100">100%</html:option>
															</html:select> </strong></td>
														</tr>
														<tr>
															<td class="style3"><strong>Quantidade de Parcelas:<font
																color="#FF0000">*</font></strong></td>
															<td colspan="2"><strong> <html:text
																property="quantidadeParcelas" size="5" maxlength="5"
																/> </strong></td>
														</tr>
														<tr>
															<td class="style3"><strong>Valor da Parcela:</strong></td>
															<td colspan="2"><html:text property="valorParcelas"
																readonly="true"
																style="background-color:#EFEFEF; border:0;text-align: right;"
																size="15" maxlength="15" /></td>
															<td colspan="4" align="right">
																<input type="button" 
																	class="bottonRightCol" 
																	value="Calcular"
																	name="buttonCalcular" 
																	tabindex="10"
																	onclick="calcularValores();" 
																	style="width: 80px">
															</td>
														</tr>
													</logic:notPresent>
												</table>
												</td>
											</tr>
										</table>
										</td>
									</tr>
								</logic:notEmpty>
								<tr>
									<td colspan="2">
									<table width="100%">
									  <tr colspan="1" align="center">
											<td height="19"><strong> <font color="#FF0000"></font></strong></td>
											<td align="center">
											<div align="left"><strong><font color="#FF0000">*</font></strong>
											Campos obrigat&oacute;rios</div>
											</td>
										</tr>
										<tr>
											<td width="40%" align="left"><logic:present
												name="caminhoRetornoIntegracaoComercial">
												<INPUT TYPE="button" class="bottonRightCol" value="Voltar"
													onclick="redirecionarSubmit('${caminhoRetornoIntegracaoComercial}')" />
											</logic:present> <input type="button" name="ButtonReset"
												class="bottonRightCol" value="Desfazer"
												onClick="limparForm();"> <input type="button"
												name="ButtonCancelar" class="bottonRightCol"
												value="Cancelar"
												onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
											</td>

											<td align="right"><input name="Button" type="button"
												class="bottonRightCol" value="Efetuar"
												onClick="javascript:verificaSepesquisouOrdemServico();"></td>

										</tr>
									</table>
									</td>

								</tr>
							</table>
							</td>
						</tr>
						<!--</tr></table></td>-->
					</table>
					<!-- Fim do Corpo - Romulo Aurelio-->

					<logic:notPresent name="semMenu">
						<%@ include file="/jsp/util/rodape.jsp"%>
					</logic:notPresent>

					</html:form>

					<script language="JavaScript">

</script>
					</html:html>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@ page import="gcom.util.ConstantesSistema" %>
<%@ page import="java.util.Collection" isELIgnored="false"%>
<%@ page import="gcom.micromedicao.consumo.ConsumoAnormalidadeAtividadeAcaoMensal" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<%@ include file="/jsp/util/telaespera.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js" ></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js" ></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="InserirConsumoAnormalidadeAcaoActionForm" dynamicJavascript="true" />

<script language="JavaScript">

	function validaForm(form) {
		if (form.consumoAnormalidade.value=="-1"){
			alert("Informe o Consumo Anormalidade.");
			return false;
		}
		botaoAvancarTelaEspera('/gsan/atualizarConsumoAnormalidadeAcaoAction.do');
	} 

	function validateAtualizarConsumoAnormalidadeAcaoActionForm(form) { return true;}	
 
 	function validaAdicionarAcaoMensal(form) {
		var msg = "";
		var titulo = "Os campos abaixos são obrigatórios: \n \n";
		
		if (form.codigoOrdemMes.value==""){
			msg = "Quantidade de ocorrências consecutivas da anormalidade. \n";
		} 
		if (form.leituraAnormalidadeConsumo.value == "-1"){
			msg = msg + "Consumo a cobrar. \n";
		} 
		if (form.numerofatorConsumo.value == ""){
			msg = msg + "Fator de consumo. \n";
		} 
		
		if (form.idServicoTipo.value != ""){
			if (form.solicitacaoTipo.value =="-1"){
				msg = msg+"Tipo de Solicitação. \n";
			} 
			if(form.solicitacaoTipoEspecificacao.value == "-1"){
				msg = msg+"Tipo de Especificação. \n";
			}			
		}
		
		if (msg!=""){
			titulo = titulo + msg;
			alert(titulo);
			return false;
		} else {
			form.action = 'exibirAtualizarConsumoAnormalidadeAcaoAction.do?objetoConsulta=4';
			form.submit();
		}
	}
	
	function limparForm() {
		var form = document.AtualizarConsumoAnormalidadeAcaoActionForm;
		form.consumoAnormalidade.value = "-1";
		form.categoria.value = "-1";
	    form.imovelPerfil.value = "-1";
	    form.leituraAnormalidadeConsumo.value = "-1";
		form.numerofatorConsumo.value = "";
		form.indicadorGeracaoCarta.value = "";
	    form.idServicoTipo.value = "";
	    form.desServicoTipo.value = "";
	    form.solicitacaoTipo = "-1";
	    form.solicitacaoTipoEspecificacao.value = "";
	    form.descricaoContaMensagem.value = "";
	    form.indicadorUso.value = "";
			
	}
	
	function desabilitaCombo(){
			var form = document.forms[0];
			
			if(form.idServicoTipo.value == null || form.idServicoTipo.value == '' ){
				form.solicitacaoTipo.disabled = true;
				form.solicitacaoTipo.values == '-1';
				form.solicitacaoTipoEspecificacao.disabled = true;
			}
			if(form.solicitacaoTipo.value == '-1') {
				form.solicitacaoTipoEspecificacao.disabled = true;
			}			
			
	}  
	
	function reload() {
		var form = document.AtualizarConsumoAnormalidadeAcaoActionForm;
		form.action = "/gsan/exibirAtualizarConsumoAnormalidadeAcaoAction.do";
		form.submit();
	}  
	

	function limparBorrachaOrigem(tipo){
		var form = document.forms[0];
		
		//Tipo de Servico 
		form.idServicoTipo.value = "";
		form.desServicoTipo.value = "";
		form.solicitacaoTipo.value = "-1";
		form.solicitacaoTipoEspecificacao.value = "-1";		
	}

	
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,campo){
		if(!campo.disabled){
	  		if (objeto == null || codigoObjeto == null){
	     		if(tipo == "" ){
	      			abrirPopup(url,altura, largura);
	     		}else{
		  			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		 		}
	 		}else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				}else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
  		}
	}

	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
		var form = document.forms[0];
		if (tipoConsulta == 'idServicoTipo') {      		
      		form.idServicoTipo.value = codigoRegistro;
	  		form.desServicoTipo.value = descricaoRegistro;
	  		form.idServicoTipo.style.color = "#000000";
			form.desServicoTipo.style.color = "#000000";
			habilitaSolicitacao();
		}
	}


	
	function habilitaSolicitacao(){
		var form = document.forms[0];
		
		if(form.idServicoTipo.value != '') {
			form.solicitacaoTipo.disabled = false;
		}
		if(form.solicitacaoTipo.value != '-1') {
			form.solicitacaoTipoEspecificacao.disabled = false;
		}
	}

	function desfazer(){
		var form = document.forms[0];
		var registro = form.consumoAnormalidadeAcaoId.value;
		form.action = 'exibirAtualizarConsumoAnormalidadeAcaoAction.do?menu=s&idRegistroAtualizacao='+registro;
		form.submit();		
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:desabilitaCombo();habilitaSolicitacao();">
<div id="formDiv">
<html:form action="/atualizarConsumoAnormalidadeAcaoAction"
	name="AtualizarConsumoAnormalidadeAcaoActionForm"
	type="gcom.gui.micromedicao.AtualizarConsumoAnormalidadeAcaoActionForm"
	method="post"
	onsubmit="return validateAtualizarConsumoAnormalidadeAcaoActionForm(this);">
	<html:hidden property="consumoAnormalidadeAcaoId" />
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

			<!-- centercoltext -->

			<td width="600" valign="top" class="centercoltext">

			<table>
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Atualizar Consumo Anormalidade e Ação</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para atualizar o consumo anormalidade e Ação, informe os dados abaixo:</td>
				</tr>

				<!-- Consumo Anormalidade -->
				<tr>
					<td><strong>Consumo Anormalidade:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select property="consumoAnormalidade">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoConsumoAnormalidade"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>

				<!-- Categoria -->
				<tr>
					<td><strong>Categoria:</strong></td>
					<td colspan="2" align="left"><html:select property="categoria">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoCategoria"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Perfil do Imóvel:</strong></td>
					<td colspan="2" align="left"><html:select property="imovelPerfil">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoImovelPerfil"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				<tr>
					<td><strong>Validar Retificação:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorValidarRetificacao"
						value="1" /> <strong>Sim <html:radio
						property="indicadorValidarRetificacao" value="2" /> Não </strong>
					</strong></td>
				</tr>
				
				<tr>
					<td><strong>Indicador de Uso:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorUso" value="1" />
					<strong>Ativo <html:radio property="indicadorUso" value="2" />
					Inativo</strong> </strong></td>

				</tr>
				
				<tr>
					<td colspan="2" ><hr/></td>
				</tr>
				
				<tr>
					<td colspan="2" >Informe a(s) Anormalidade(s) Ação(ões) Mensal(is):</td>
				</tr>
				
				<tr>
					<td><strong>Quantidade de Ocorrências <br/> Consecutivas da Anormalidade:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="codigoOrdemMes" 
						size="5" 
						maxlength="2" 
						onkeypress="return isCampoNumerico(event);"/> </span></td>
				</tr>
				
				<tr>
					<td><strong>Consumo a Cobrar:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:select property="leituraAnormalidadeConsumo">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoLeituraAnormalidadeConsumo"
							labelProperty="descricaoConsumoACobrar" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Fator de Consumo para Cálculo:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="numerofatorConsumo" 
						size="4" 
						maxlength="4" 
						onkeypress="return isBigDecimal(event);" /> </span></td>
				</tr>
			</table>
			
			<table width="100%">
				<tr>
					<td><strong>Indicador de Geração de Carta:<font
						color="#FF0000">*</font></strong></td>
					<td><strong> <html:radio property="indicadorGeracaoCarta"
						value="1" /> <strong>Gerar Carta <html:radio
						property="indicadorGeracaoCarta" value="2" /> Não Gerar Carta</strong>
					</strong></td>

				</tr>
				<tr>
					<td><strong>Tipo de Serviço:</strong></td>
					<td>	
						<html:text maxlength="4" 
							tabindex="1"
							property="idServicoTipo" 
							size="5"
							onkeypress="javascript:validaEnterComMensagem(event, 'exibirAtualizarConsumoAnormalidadeAcaoAction.do?objetoConsulta=1','idServicoTipo','Tipo de Serviço');return isCampoNumerico(event);"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarTipoServicoAction.do', 'idServicoTipo', null, null, 275, 480, '',document.forms[0].idServicoTipo);">
							<img width="23" 
								height="21" 
								border="0" 
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Tipo de Serviço" /></a>
								
						<logic:notPresent name="servicoTipoInexistente" scope="request">
							<html:text property="desServicoTipo" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:notPresent> 

						<logic:present name="servicoTipoInexistente" scope="request">
							<html:text property="desServicoTipo" 
								size="30"
								maxlength="30" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:present>

						<a href="javascript:limparBorrachaOrigem(1);desabilitaCombo()"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
					<td><strong>Tipo de Solicitação:</strong></td>
					<td colspan="2" align="left"><html:select property="solicitacaoTipo"
						 onchange="javascript:habilitaSolicitacao();desabilitaCombo();reload();">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoSolicitacaoTipo"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>
				
				<tr>
					<td><strong>Tipo de Especificação:</strong></td>
					<td colspan="2" align="left"><html:select property="solicitacaoTipoEspecificacao"
						 onchange="javascript:habilitaSolicitacao();desabilitaCombo();">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoSolicitacaoTipoEspecificacao"
							labelProperty="descricao" property="id" />
					</html:select></td>
				</tr>				
			
					
		 		
				<tr>
					<td><strong>Mensagem da Conta:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricaoContaMensagem" 
						size="52" 
						maxlength="120" /> </span></td>
				</tr>
				
				<!-- Botão de adicionar -->
				<tr>
				<td colspan="2" align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Adicionar" onclick="validaAdicionarAcaoMensal(document.forms[0]);"></td>
				</tr>
				
				<tr>
					<td colspan="2" ><hr/></td>
				</tr>
			</table>	
				
				
			<%-- início da tabela de consumo anormalidade atividade ação mensal --%>
			<table width="100%">
		           	<%int cont5 = 0;%>
					<tr>
						<td colspan="2">
						<table width="100%" border="0" bgcolor="90c7fc">
							<logic:empty name="colecaoAnormalidadeAcaoMensal" scope="session">
								<tr colspan="2" bgcolor="#90c7fc">
									<td align="center"><strong>Remover</strong></td>
									<td align="center"><strong>Qnt</strong></td>
									<td align="center"><strong>Consumo</strong></td>
									<td align="center"><strong>Fator de Consumo</strong></td>
									<td align="center"><strong>Gerar Carta</strong></td>
								</tr>
							</logic:empty>
							
							<logic:notEmpty name="colecaoAnormalidadeAcaoMensal" scope="session">
								
									<tr bgcolor="#90c7fc">
										<td align="center"><strong>Remover</strong></td>
										<td align="center"><strong>Qnt</strong></td>
										<td align="center"><strong>Consumo</strong></td>
										<td align="center"><strong>Fator de Consumo</strong></td>
										<td align="center"><strong>Gerar Carta</strong></td>
									</tr>
															
									<logic:iterate name="colecaoAnormalidadeAcaoMensal" 
										id="acao"
										type="ConsumoAnormalidadeAtividadeAcaoMensal">
										
										<%cont5 = cont5 + 1;
										if (cont5 % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
										<%} else {%>
										<tr bgcolor="#FFFFFF">
										<%}%>
	
											<td width="10%">
									            <div align="center"><font color="#333333"> 
									            
									            <logic:empty name="acao" property="id">
									            <img width="14" height="14" border="0"
									             src="<bean:message key="caminho.imagens"/>Error.gif"
										         onclick="javascript:document.forms[0].target='';if(confirm('Confirma remoção?')){redirecionarSubmit('exibirAtualizarConsumoAnormalidadeAcaoAction.do?objetoConsulta=5&idAcaoMensal=<bean:write name="acao" property="id2"/>');}"
										              />
										        </logic:empty>
										        <logic:notEmpty name="acao" property="id">
									            <img width="14" height="14" border="0"
									             src="<bean:message key="caminho.imagens"/>Error.gif"
										         onclick="javascript:document.forms[0].target='';if(confirm('Confirma remoção?')){redirecionarSubmit('exibirAtualizarConsumoAnormalidadeAcaoAction.do?objetoConsulta=5&idAcaoMensal=<bean:write name="acao" property="id"/>');}"
										              />
										        </logic:notEmpty>
										        
									            </font></div>
									       </td>	
									       <td align="center">
												<bean:write name="acao" property="codigoOrdemMes" />
											</td>
											<td align="center">
												<bean:write name="acao" property="leituraAnormalidadeConsumo.descricaoConsumoACobrar" />
											</td>
											<td align="center">
												<bean:write name="acao" property="numerofatorConsumo" />
											</td>
											<td align="center">
												<%if (acao.getIndicadorGeracaoCarta().intValue() == 1) {%>
													<%= "Sim"%>
												<%} else {%>
													<%= "Não"%>
												<%}%>												
											</td>
								
										</tr>
									</logic:iterate>
							</logic:notEmpty>
						</table>
						</td>
					</tr>
					
					<tr><td><hr/></td></tr>
					
		        </table>  		
		        <%-- final da tabela de consumo anormalidade atividade ação--%>
							           	
				<table width="100%">				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left" colspan="2"><strong><font color="#FF0000">*</font></strong>

					Campos obrigat&oacute;rios</div>
					</td>
				</tr>
				
				<!-- Botões -->

				<tr>
					<td align="left">
					<input type="button" name="ButtonReset" class="bottonRightCol"
							value="Voltar" onclick="window.location.href='/gsan/filtrarConsumoAnormalidadeAcaoAction.do'">
					<input name="Button" type="button"
						class="bottonRightCol" value="Desfazer" align="left"
						onclick="desfazer()">
					<input type="button" name="ButtonCancelar" class="bottonRightCol"
						value="Cancelar"
						onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
					</td>
					<td colspan="2" align="right"><input name="Button" type="button"
						class="bottonRightCol" value="Atualizar" onclick="validaForm(document.forms[0]);"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>	
	
</html:form>
</div>
</html:html>
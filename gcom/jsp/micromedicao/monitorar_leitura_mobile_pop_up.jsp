<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="MonitorarLeituraMobileActionForm"
	dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

var idRotaEscolhida;

function validaForm(form){
	document.forms[0].action ='exibirMonitorarLeituraMobilePopupAction.do?popup=sim';
	document.forms[0].submit();
}

function listarLeituras(form){
	if(validarCampos(form) && verificaAnoMes(form.mesAno)){
		if(form.idRota.value != "" && form.idRota.value != undefined){
			form.action ='exibirMonitorarLeituraMobilePopupAction.do?consultar=sim&anoMes='+form.mesAno.value+'&idRota='+form.idRota.value;
		}else{
			form.action ='exibirMonitorarLeituraMobilePopupAction.do?consultar=sim&anoMes='+form.mesAno.value;
		}
		form.submit();
	}
}

function controlarDivConsumos( div ){
  if ( div.style.display == 'block' ){
	div.style.display = 'none';
  } else {
  	div.style.display = 'block';
  }

}

function abrirPopupNovo(caminho,largura, altura){
	
	//Para abrir o popup centralizado ======
	var height = window.screen.height - 160;
	var width = window.screen.width;
	var top = (height - altura)/2;
	var left = (width - largura)/2;
   //======================================
	
	
	window.open(caminho,'','top=' + top + ',left='+ left +',_blank,location=no,screenY=0,screenX=0,menubar=no,status=' + status + ',toolbar=no,scrollbars=yes,resizable=no,width=' + largura + ',height=' + altura);

}
  
function alertaImovelRisco(){
	alert('O imóvel não possui foto de anormalidade por ser um imóvel de RISCO.');
}

function validarCampos(form){
	if(form.mesAno.value == ''){
		alert ('Informe o mês/ano de referência');
		return false;
	}
	
	if(form.rota.value == ''){
		alert ('Informe o código da rota');
		return false;
	}
	
	return true;
}

function receberRota(idRota, descricao, codigoRota) {
 	 var form = document.forms[0];
	 form.rota.value = codigoRota;
	 form.descricaoRota.value = descricao;
	 idRotaEscolhida = idRota;
	 form.idRota.value = idRota;
	  
}

function limparRota(form){
	form.rota.value = "";
	form.descricaoRota.value = "";
}

</script>

</head>

<logic:equal name="popup" value="true">
	<body leftmargin="5" topmargin="5" onload="toggleBox('demodiv',0);resizeTo(800,600);">
</logic:equal>
<logic:equal name="popup" value="false">
	<body leftmargin="5" topmargin="5">
</logic:equal>

<html:form action="/monitorarLeituraMobilePopupAction"
	name="ConsultarArquivoTextoLeituraActionForm"
	type="gcom.gui.micromedicao.ConsultarArquivoTextoLeituraActionForm"
	method="post">
	
	<input type="hidden" name="idRota" />
	
	<logic:equal name="popup" value="false" >
		<%@ include file="/jsp/util/cabecalho.jsp"%>
		<%@ include file="/jsp/util/menu.jsp"%>
	</logic:equal>
	
	<table width="780" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<logic:equal name="popup" value="false">
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
			</logic:equal>
				
			<logic:equal name="popup" value="true">
				<td width="100%" valign="top" class="centercoltext">
			</logic:equal>
			<logic:equal name="popup" value="false">
				<td width="615" valign="top" class="centercoltext">
			</logic:equal>
			
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<logic:equal name="popup" value="true">
				<p>&nbsp;</p>
			</logic:equal>
			
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Monitorar Leituras Transmitidas</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>

			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td height="10" colspan="3">Para listar as leituras já realizadas, 
					informe os dados abaixo:</td>
					<td align="right"></td>
				</tr>		
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<logic:equal name="popup" value="true">
					<tr>
						<td width="18%"><strong>Mês/Ano de Referência:</strong></td>
						<td>
							<input type="text" readonly="readonly" style="background-color:#EFEFEF;  
							border:0; text-align:right; color: #000000;" 
							value="<bean:write name="anoMes" scope="session" />" size="7">							
						</td>	
	
						<td align="right"><strong>Código da Rota:</strong>
							<input type="text" readonly="readonly" style="background-color:#EFEFEF;  
							border:0; text-align:right; color: #000000;" 
							value="<bean:write name="cdRota" scope="session" />" size="7">								
						</td>		
						<td width="10%">&nbsp;</td>
					</tr>
					<tr>
						<td><strong>Leiturista:</strong></td>
						<td colspan="2">
							<input type="text" readonly="readonly" style="background-color:#EFEFEF;  
							border:0; color: #000000;" 
							value="<bean:write name="nomeLeiturista" 
							scope="session" />" size="65">								
						</td>	
					</tr>
				</logic:equal>	
				
				<logic:equal name="popup" value="false">
					<tr>
						<td height="10" width="145"><strong>Mês/Ano de Referência:<font
							color="#FF0000">*</font></strong></td>
						<td><html:text property="mesAno" size="8" maxlength="7" onkeypress="return isCampoNumerico(event);"
							tabindex="1" onkeyup="mascaraAnoMes(this, event);validaAnoMesNumerico(this);" /><strong>&nbsp;mm/aaaa</strong></td>
					</tr>
					
					<tr>
						<td><strong>Código da Rota:<font color="#FF0000">*</font></strong></td>
						<td>
							<html:text maxlength="4" tabindex="1"
								property="rota" size="4" readonly = "true" />
							<a
								href="javascript:abrirPopup('exibirPesquisarInformarRotaLeituraAction.do?caminhoRetornoTelaPesquisaRota=exibirPesquisarInformarRotaLeituraAction');">
							<img width="23" height="21" border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Rota" /></a> 
						
								<html:text property="descricaoRota" size="30"
									maxlength="30" readonly="true"
									style="background-color:#EFEFEF; border:0; color: #000000" />						
							
						<a
							href="javascript:limparRota(document.forms[0]);">
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
							border="0" title="Apagar" />
						</a>
							
						</td>
					</tr>
					
					<tr>
						<td height="10" width="125"><strong>Leiturista :</strong></td>
						<td colspan="2" align="left">
							<html:text property="nomeLeiturista" size="40" maxlength="40" readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000"/>
						</td>
					</tr>
				</logic:equal>
				
				<tr>
					<td><strong>Im&oacute;vel Impresso:</strong></td>
					<td colspan="2">
						<html:radio property="contaImpressa"
							value="1"  />
						Sim
						<html:radio property="contaImpressa"
							value="2" />
						N&atilde;o
						<html:radio property="contaImpressa"
							value="" />
						Todos
					</td>
				</tr>
				
				<tr>
					<td><strong>Tipo Medição:</strong></td>
					<td colspan="2">
						<html:radio property="tipoMedicao"
							value="1"  />
						Medidos
						<html:radio property="tipoMedicao"
							value="2" />
						Não Medidos
						<html:radio property="tipoMedicao"
							value="" />
						Todos
					</td>
				</tr>
				
				<tr>
					<!--<td colspan="4" bgcolor="#3399FF"> -->
					<td colspan="4" bgcolor="#000000" height="2" valign="baseline"></td>
				</tr>
				
				<logic:equal name="popup" value="true">		
					<tr>
						<td align="left">
							<input type="button"
								onclick="window.close()" class="bottonRightCol" value="Fechar"
								style="width: 70px;">
						</td>
						<td align="right" colspan="4">
							<input type="button" class="bottonRightCol" value="Listar"
								onClick="validaForm(document.forms[0]);">	
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="popup" value="false">
					<tr>
						<td colspan="2"><input name="Button" type="button" class="bottonRightCol"
							value="Limpar" align="left"
							onclick="window.location.href='<html:rewrite page="/exibirMonitorarLeituraMobilePopupAction.do?menu=sim"/>'">
						<input type="button" name="ButtonCancelar" class="bottonRightCol"
							value="Cancelar"
							onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
						<td align="right">
							<gsan:controleAcessoBotao name="Botao" value="Listar"
									onclick="listarLeituras(document.forms[0]);"
									url="exibirMonitorarLeituraMobilePopupAction.do" tabindex="13" />
						</td>
					</tr>
				</logic:equal>
				
				<tr>
				<td colspan="4">		
				<table width="100%" align="center" bgcolor="#90c7fc" border="0"
					cellpadding="0" cellspacing="0">
					<tr bgcolor="#cbe5fe">
						<td width="100%" align="center">
						<div style="height:250px;overflow:auto">
						<table>	
							<tr bordercolor="#000000" bgcolor="#90c7fc">
							
								<c:if test="${sessionScope.temPermissao!=null && sessionScope.temPermissao}" >												
									<td bgcolor="#90c7fc">
										&nbsp;
									</td>
								</c:if>
								<td width="20%" bgcolor="#90c7fc">
								<div align="center"><strong>Inscrição</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Matrícula</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Seq. Rota</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Leit Ante.</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Leit Atual</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Anorm.</strong></div>
								</td>
								<td width="7%" bgcolor="#90c7fc">
								<div align="center"><strong>Cons. Anorm.</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Data/Hora Leitura</strong></div>
								</td>
								<td width="10%" bgcolor="#90c7fc">
								<div align="center"><strong>Data/Hora Recebimento</strong></div>
								</td>
								<td width="5%" bgcolor="#90c7fc">
								<div align="center"><strong>Impre.</strong></div>
								</td>
						
								<%int cont = 0;%>
								
								<logic:present name="colecao" scope="session">
									<logic:iterate name="colecao" id="helper" scope="session">
	
									<%cont = cont + 1; if (cont % 2 == 0) {%>
									
										<tr bgcolor="#cbe5fe">
											<%} else {%>
										<tr bgcolor="#FFFFFF">
											<%}%>
											
											<c:if test="${sessionScope.temPermissao!=null && sessionScope.temPermissao}" >												
												<td>
							                        <img border="0" src="imagens/nolines_plus.gif" onclick="javascript:controlarDivConsumos( document.getElementById('divConsumos${helper.idImovel}') );" />
												</td>
												
											</c:if>
	
											<td align="center" onmouseover="this.T_BGCOLOR='whitesmoke';this.T_LEFT=true;return escape( '${helper.dadosCliente}' ); ">
												${helper.inscricao}
											</td>									
											
											<td align="center">
												${helper.idImovel}
											</td>
											<td align="center">
												${helper.sequencialRota}
											</td >
											<td align="center">
												${helper.leituraAnterior}
											</td>
											
											<td align="center">
												${helper.leituraAtual}
											</td >						
													 
											<td align="center">
												
												<c:choose>
													<c:when test="${helper.indicadorRisco eq '1' }">
														<a href="javascript:alertaImovelRisco();">
			 												${helper.idAnormalidade} 
			  											</a>
													</c:when>
													<c:otherwise>
			 											<a href="javascript:abrirPopupNovo('exibirConsultarFotoMicroMedicaoPopupAction.do?id=${helper.idImovel}&anoMes=<bean:write name="anoMes" scope="session" />&metp=${helper.idTipoMedicao}&ltan=${helper.idAnormalidade}', 550, 450);">
			 												${helper.idAnormalidade} 
			  											</a>
		  											</c:otherwise>			 
												</c:choose>
											</td>
											
											<td width="15%" align="center">
											<c:choose>
												<c:when test='${helper.consumoAnormalidadeId != null && helper.consumoAnormalidadeId != 0 && helper.consumoAnormalidadeDescAbrv != null}'>
		 											
		 											
		 											 <logic:equal name="helper" property="indicadorRisco" value="1">
		 												<a href="javascript:alertaImovelRisco();">
			 												${helper.consumoAnormalidadeDescAbrv} 
			  											</a> 
		 											 </logic:equal>
		 											
		 											<logic:notEqual name="helper" property="indicadorRisco" value="1">
	 												 	<a href="javascript:abrirPopupNovo('exibirConsultarFotoConsumoAnormalidadePopupAction.do?id=${helper.idImovel}&anoMes=<bean:write name="anoMes" scope="session" />&medt=${helper.idTipoMedicao}&csan=${helper.consumoAnormalidadeId}', 550, 450);">
	 														${helper.consumoAnormalidadeDescAbrv}
	 													</a>  
		 											 </logic:notEqual>
		 											
	
												</c:when>
											 	<c:otherwise>
											 										 
												</c:otherwise>									
											</c:choose>
											</td>
																					
											<td align="center">
												${helper.dtLeitura}
											</td>
											
											<td align="center">
												${helper.dtRecebimento}
											</td>
											
											<td align="center"><a title="${helper.motivoNaoEmissao}">${helper.icEmissaoConta}</td>
										</tr>											
										
											<%if (cont % 2 == 0) {%>								
												<tr bgcolor="#cbe5fe" style="border-width: 0">
											<%} else {%>
												<tr bgcolor="#FFFFFF">
											<%}%>
	
							            		<td colspan="10">
							            			<div id="divConsumos${helper.idImovel}" style="display: none;">
							            				<table width="100%" align="center" bgcolor="#90c7fc" border="0"
															cellpadding="0" cellspacing="0">						            				
							            					
							            					<%int cont2 = cont % 2; %>
							            					
							            					<%cont2++; if (cont2 % 2 == 0) {%>								
															<tr bgcolor="#cbe5fe" style="border-width: 0">
																<%} else {%>
															<tr bgcolor="#FFFFFF">
																<%}%>
							            					
							            						<td align="center" colspan="10"><strong>&Uacute;ltimos Consumos</strong></td>
							            					</tr>
															
															
							            					<%if (cont2 % 2 == 0) {%>								
															<tr bgcolor="#cbe5fe" style="border-width: 0">
																<%} else {%>
															<tr bgcolor="#FFFFFF">
																<%}%>
							            						<td width="34%" align="center"><strong>Refer&ecirc;ncia</strong></td>
							            						<td width="33%" align="center"><strong>Consumo</strong></td>
							            						<td width="33%" align="center"><strong>Anormalidade</strong></td>
							            					</tr>
							            					
							            					<logic:present name="helper" property="colConsumos">
															<logic:notEmpty name="helper" property="colConsumos">
							            					<logic:iterate property="colConsumos" id="consumo" name="helper">
							            						<%cont2++; if (cont2 % 2 == 0) {%>								
																	<tr bgcolor="#cbe5fe" style="border-width: 0">
																<%} else {%>
																	<tr bgcolor="#FFFFFF">
																<%}%>
																
							            							<td align="center">${consumo.referenciaFaturamentoFormatado}</td>
							            							<td align="center">${consumo.numeroConsumoFaturadoMes}</td>
																	<td align="center">${consumo.consumoAnormalidade.descricaoAbreviada}</td>
																</tr>
							            					
							            					</logic:iterate>
							            					</logic:notEmpty>
							            					</logic:present>
							            				</table>						            		
							            			</div>
							                	</td>
							                </tr>
										</logic:iterate>
									</logic:present>
								</tr>
							</table>
						</div>		
					</td>
				</tr>
				
				<logic:present name="colecao" scope="session">
					<tr>
						<td valign="top">
                        	<strong>Quantidade: ${sessionScope.quantidade}</strong>
                        	<a href="javascript:toggleBox('demodiv',1);">
                            	<img align="right" border="0" src="<bean:message key='caminho.imagens'/>print.gif"  title="Imprimir Leituras Transmitidas"/></a>
	                     </td>
					</tr>
				</logic:present>				  
				
				</table>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
		<logic:equal name="popup" value="false">
			<%@ include file="/jsp/util/rodape.jsp"%>
		</logic:equal>
		
	</table>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioMonitorarLeituraMobileAction.do"/>
<%@ include file="/jsp/util/tooltip.jsp"%>
</html:form>
</body>
</html:html>


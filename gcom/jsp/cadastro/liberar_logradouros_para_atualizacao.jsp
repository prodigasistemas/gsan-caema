<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="gcom.util.ConstantesSistema"%>
<%@page import="gcom.cadastro.ImovelEnderecoArquivoTextoHelper"  isELIgnored="false"%>
<%@page import="gcom.cadastro.ImovelEnderecoArquivoTexto"  isELIgnored="false"%>
<%@page import="gcom.cadastro.endereco.Logradouro"  isELIgnored="false"%>
<%@page import="gcom.cadastro.geografico.Bairro"  isELIgnored="false"%>



<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>

<html:javascript staticJavascript="false" formName="LiberarLogradourosParaAtualizacaoActionForm" />

<script language="JavaScript">
<!--

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
	
	if(objetoRelacionado.disabled != true){
		
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		} else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			} else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
			}
		}
	}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
    
    var form = document.forms[0];
    
	if (tipoConsulta == 'municipio') {

		form.idMunicipio.value = codigoRegistro;
	    form.descricaoMunicipio.value = descricaoRegistro;
	    form.descricaoMunicipio.style.color = "#000000";
    
    }	    
    	
}

function limparMunicipio(){
	
	var form = document.forms[0];
    form.idMunicipio.value = "";
    form.descricaoMunicipio.value = "";
}

function limparFormulario(form) {
	limparMunicipio();
	form.action = "exibirLiberarLogradourosParaAtualizacaoAction.do?limpar=sim";
	form.submit();
}

function pesquisar(){
	var form = document.forms[0];
	if(validateLiberarLogradourosParaAtualizacaoActionForm(form)){
		form.action = "exibirLiberarLogradourosParaAtualizacaoAction.do?pesquisarLogradouros=sim";
		form.submit();
	}
}

function validarForm(){
	var form = document.forms[0];
	form.action = "liberarLogradourosParaAtualizacaoAction.do";
	form.submit();
}

function gerarRelatorio(){
	var form = document.forms[0];
	form.action = "gerarRelatorioLogradourosParaAtualizacao.do";
	form.submit();
}

function facilitador(objeto){
	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodos();
	}
	else{
		objeto.id = "0";
		desmarcarTodos();
	}
}

-->
</script>
</head>
<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');">
<div id="formDiv">
<html:form action="/liberarLogradourosParaAtualizacaoAction" 
	name="LiberarLogradourosParaAtualizacaoActionForm" 
	type="gcom.gui.cadastro.LiberarLogradourosParaAtualizacaoActionForm"
	method="post"
	onsubmit="return validateLiberarLogradourosParaAtualizacaoActionForm(this);">

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
			
			
				<table width="100%" 
					border="0" 
					align="center" 
					cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
						</td>
						<td class="parabg">Liberar  os Logradouros para Atualização no GSAN</td>
						<td width="11">
							<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
						</td>
					</tr>
				</table>
				
				<p>&nbsp;</p>
			
				<table bordercolor="#000000" width="100%" cellspacing="0">
					<tr>
						<td>
							<p>Para liberar os logradouros para Atualização no GSAN, informe os dados abaixo:</p>
						</td>
					</tr>
	        	</table>
	        	<p>&nbsp;</p>
	        	
	        	<table bordercolor="#000000" width="100%" cellspacing="0">
					<tr>
						<td>
						<table width="100%" border="0">
							<tr>
								<td><strong>Munic&iacute;pio:</strong></td>
								
								<td>
									
									<html:text maxlength="4" 
										tabindex="1"
										property="idMunicipio" 
										size="3"
										onkeypress="validaEnterComMensagem(event, 'exibirLiberarLogradourosParaAtualizacaoAction.do?pesquisarMunicipio=sim','idMunicipio','Município');
										            return isCampoNumerico(event);"/>
										
									<a href="javascript:chamarPopup('exibirPesquisarMunicipioAction.do', 'municipio', null, null, 275, 480, '', document.forms[0].idMunicipio);">
									
										<img width="23" 
											height="21" 
											border="0"
											src="<bean:message key="caminho.imagens"/>pesquisa.gif"
											title="Pesquisar Município" /></a> 
			
									<logic:present name="municipioEncontrado" scope="session">
										<html:text property="descricaoMunicipio" 
											size="30"
											maxlength="30" 
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:present> 
			
									<logic:notPresent name="municipioEncontrado" scope="session">
										<html:text property="descricaoMunicipio" 
											size="30"
											maxlength="30" 
											readonly="true"
											style="background-color:#EFEFEF; border:0; color: red" />
									</logic:notPresent>
			
									
									<a href="javascript:limparFormulario(document.forms[0]);"> 
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
											border="0" 
											title="Apagar" /></a>
								</td>
							</tr>

							<tr>
								<td valign="top" colspan="2">
									<div align="right">
										<input type="button"
											name="ButtonSelecionar" 
											class="bottonRightCol" 
											value="Selecionar"
											onclick="javascript:pesquisar();">
									</div>
								</td>
							</tr>	
							
							
							<tr>
			             		<td height="10" colspan="2"> 
				             		<div align="right"> 
				                 		<hr>
				               		</div>
				               		<div align="right"> </div>
			               		</td>
			           		</tr>	           				
	           				
	           				<logic:present name="colecaoImovelEnderecoArquivoTextoHelper" scope="session">
								<tr>
									<td align="left" colspan="2">
											<strong>Logradouros:</strong>
										</td>
								</tr>
								
								<table width="100%" cellpadding="0" cellspacing="0" >
								
									<tr bordercolor="#000000" height="45px">
										<td width="10%" bgcolor="#90c7fc" align="center" >
											<strong><a	href="javascript:facilitador(this);">Todos</a></strong>
										</td>
										<td width="10%" bgcolor="#90c7fc" align="center">
											<strong>Código</strong>
									   </td>
										<td width="30%" bgcolor="#90c7fc" align="center">
											<strong>Nome</strong>
									   </td>
									   <td width="20%" bgcolor="#90c7fc" align="center">
											<strong>Bairro</strong>
									   </td>
									   <td width="15%" bgcolor="#90c7fc" align="center" >
											<strong>Qtde Imóveis Transf.</strong>
									   </td>
									   <td width="15%" bgcolor="#90c7fc" align="center" >
											<strong>Qtde Imóveis Total</strong>
									   </td>
									  
									</tr>		
									<tr >
										<td height="250" colspan="7">
											<div style="width: 100%; height: 100%; overflow: auto;">
												<table width="100%" bgcolor="#90c7fc" >			
													<%	int cont = 0;	%> 
													<logic:iterate name="colecaoImovelEnderecoArquivoTextoHelper" 
														id="imovelEnderecoArquivoTextoHelper"
														scope="session" 
														type="ImovelEnderecoArquivoTextoHelper">
			                    
								                    <%	cont = cont + 1;
														if (cont % 2 == 0) { %>
									               				<tr bgcolor="#cbe5fe" >		
									             	<%	} else { %>
									               				<tr bgcolor="#FFFFFF" >

									             	<%	}	%>
													         <td width="10%" align="center" >
																<html:checkbox property="idsRegistros" value="${imovelEnderecoArquivoTextoHelper.idLogradouro}-${imovelEnderecoArquivoTextoHelper.idBairro}" />
															</td>
									             			<td width="10%" align="center" >
																<bean:write name="imovelEnderecoArquivoTextoHelper" property="idLogradouro" />
															</td>
								                            <td width="30%" align="center" >
																<bean:write name="imovelEnderecoArquivoTextoHelper" property="nomeLogradouro" />
															</td>
															<td width="20%" align="center">
																<bean:write name="imovelEnderecoArquivoTextoHelper" property="nomeBairro" />
															</td>
															<td width="15%" align="center">
																<logic:equal name="imovelEnderecoArquivoTextoHelper" property="qtdeImoveisTransferidos" value="0">
																	<bean:write name="imovelEnderecoArquivoTextoHelper" property="qtdeImoveisTransferidos" />
																</logic:equal>
																<logic:notEqual name="imovelEnderecoArquivoTextoHelper" property="qtdeImoveisTransferidos" value="0">
																	<html:link  href="/gsan/gerarRelatorioLogradourosParaAtualizacao.do?tipo=transferidos&idLogradouro=${imovelEnderecoArquivoTextoHelper.idLogradouro}&idBairro=${imovelEnderecoArquivoTextoHelper.idBairro}">
																		<bean:write name="imovelEnderecoArquivoTextoHelper" property="qtdeImoveisTransferidos" />
																	</html:link>
																</logic:notEqual>
															</td>
															<td width="15%" align="center">
																<logic:equal name="imovelEnderecoArquivoTextoHelper" property="qtdeImoveisTotal" value="0">
																	<bean:write name="imovelEnderecoArquivoTextoHelper" property="qtdeImoveisTotal" />
																</logic:equal>
																<logic:notEqual name="imovelEnderecoArquivoTextoHelper" property="qtdeImoveisTotal" value="0">
																	<html:link  href="/gsan/gerarRelatorioLogradourosParaAtualizacao.do?tipo=total&idLogradouro=${imovelEnderecoArquivoTextoHelper.idLogradouro}&idBairro=${imovelEnderecoArquivoTextoHelper.idBairro}">
																		<bean:write name="imovelEnderecoArquivoTextoHelper" property="qtdeImoveisTotal" />
																	</html:link>
																</logic:notEqual>
															</td>
								                      	</tr>
					                   				</logic:iterate>
				                   				</table>
			                   				</div>
		                   				</td>
		                   			</tr>
		                   		</table>
	           				</logic:present>
	           				
	           				
	           				
							<table width="100%" border="0">
								<tr>
									<td width="50%" valign="top" align="left">
										<input type="button"
											name="ButtonCancelar" 
											class="bottonRightCol" 
											value="Cancelar"											
											onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
									
										<input type="button"
											name="ButtonLimpar" 
											class="bottonRightCol" 
											value="Limpar"
											onClick="javascript:limparFormulario(document.forms[0]);">
									</td>
									<logic:present name="colecaoImovelEnderecoArquivoTextoHelper" scope="session">
										<td valign="top" align="right">
											<input type="button"
												align="right"
												name="ButtonLimpar" 
												class="bottonRightCol" 
												value="Imprimir"
												onClick="javascript:gerarRelatorio();">
											<input type="button"
												align="right"
												name="ButtonLimpar" 
												class="bottonRightCol" 
												value="Atualizar"
												onClick="javascript:validarForm();">
										</td>
									</logic:present>
								</tr>	
							</table>
						</table>
						</td>
					</tr>
					
				</table>
				
				
			</td>
		</tr>
	</table>


<%@ include file="/jsp/util/rodape.jsp"%>
<%@ include file="/jsp/util/tooltip.jsp"%>
<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioLogradourosParaAtualizacao.do" />

</html:form>
</div>
<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>
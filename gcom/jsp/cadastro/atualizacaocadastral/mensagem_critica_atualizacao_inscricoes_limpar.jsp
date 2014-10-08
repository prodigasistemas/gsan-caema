<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.ConstantesSistema" %>
<%@ page import="gcom.cadastro.sistemaparametro.SistemaParametro"%>
<%@ page import="gcom.cadastro.ImovelInscricaoResetorizada" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>

<html:javascript staticJavascript="false"  formName="LimparMensagemCriticaAtualizacaoInscricoesActionForm"/>

<script language="JavaScript">

	function validaForm() {
		var form = document.forms[0];
		
		if(validateLimparMensagemCriticaAtualizacaoInscricoesActionForm(form)) {
			form.action = 'exibirLimparMensagemCriticaAtualizacaoInscricoesAction.do?filtrar=sim';
			form.submit();
		}
	}
	
	function atualizar(){
		var form = document.forms[0];
		
		if(validateLimparMensagemCriticaAtualizacaoInscricoesActionForm(form)) {
			//form.action = 'limparMensagemCriticaAtualizacaoInscricoesAction.do';
			//form.submit();
			botaoAvancarTelaEspera('/gsan/limparMensagemCriticaAtualizacaoInscricoesAction.do');
		}
	}
	
	function gerarRelatorio(form){
		if(validateLimparMensagemCriticaAtualizacaoInscricoesActionForm(form)) {
			toggleBox('demodiv', 1);
			//botaoAvancarTelaEspera('/gsan/gerarRelatorioMensagemCriticaAtualizacaoInscricoesAction.do');		
		}	
	}
	
	function limparDescricaoLocalidade() {
		var form = document.forms[0];
	
		var mensagemLocalidade = document.getElementById("mensagemLocalidade");
		
		if (mensagemLocalidade != null){
			mensagemLocalidade.innerHTML = "";
		}
	}

	function limparDescricaoSetor() {
		var form = document.forms[0];
	
		var mensagemSetorComercial = document.getElementById("mensagemSetorComercial");
		
		if (mensagemSetorComercial != null){
			mensagemSetorComercial.innerHTML = "";
		}
	}

	function limparPesquisaSetorComercial() {
		var form = document.forms[0];
	
		form.codigoSetorComercial.value = "";
		var mensagemSetorComercial = document.getElementById("mensagemSetorComercial");
		
		if (mensagemSetorComercial != null){
			mensagemSetorComercial.innerHTML = "";
		}
		
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

</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.nomeCampo}');">

<div id="formDiv">
<html:form action="/exibirLimparMensagemCriticaAtualizacaoInscricoesAction"
	name="LimparMensagemCriticaAtualizacaoInscricoesActionForm"
	type="gcom.gui.cadastro.atualizacaocadastral.LimparMensagemCriticaAtualizacaoInscricoesActionForm"
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
			
			<td width="610" valign="top" bgcolor="#003399" class="centercoltext">
				
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
				
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
						<td class="parabg">Limpar Mensagem de Crítica para Atualização das Inscrições</td>
						<td width="11" valign="top">
							<img border="0" src="imagens/parahead_right.gif" />
						</td>
					</tr>
				</table>
				
				<p>&nbsp;</p>
				
				<table width="100%" border="0">
					<tr>
						<td colspan="2">Para limpar as mensagens de crítica para atualização das incrições, informe os dados abaixo:</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td><strong>Localidade:<font color="#FF0000">*</font></strong></td>
						
						<td>
							<html:text maxlength="3" 
								tabindex="1"
								property="idLocalidade" 
								size="4"
								onkeypress="javascript:limparDescricaoLocalidade();limparPesquisaSetorComercial();validaEnterComMensagem(event, 'exibirLimparMensagemCriticaAtualizacaoInscricoesAction.do','idLocalidade','Localidade'); return isCampoNumerico(event);"/>
								
							<logic:present name="mensagemLocalidade" scope="request">
					 			
					 			<logic:present name="localidadeNaoEncontrada" scope="request">
									<span style="color:#ff0000" id="mensagemLocalidade">
										<bean:write scope="request" name="mensagemLocalidade"/>
									</span>
                      			</logic:present>
                      			
                      			<logic:notPresent name="localidadeNaoEncontrada" scope="request">
									<span style="color:#000000" id="mensagemLocalidade">
										<bean:write scope="request" name="mensagemLocalidade"/>
									</span>
                      			</logic:notPresent>
                      			
                      		</logic:present>
								
						</td>
					</tr>					
						
	             	<tr>
	                	<td><strong>Setor Comercial:</strong></td>
	                   	<td>
	                   		<html:text maxlength="3" 
	                   			property="codigoSetorComercial" 
	                   			size="4"  
	                   			tabindex="2" 
	                   			onkeypress="javascript:limparDescricaoSetor();validaEnterDependencia(event, 'exibirLimparMensagemCriticaAtualizacaoInscricoesAction.do', this, document.forms[0].idLocalidade.value, 'Localidade');return isCampoNumerico(event);"/>
	                   			
							<logic:present name="mensagemSetorComercial" scope="request">
					 			
					 			<logic:present name="setorComercialNaoEncontrado" scope="request">
									<span style="color:#ff0000" id="mensagemSetorComercial">
										<bean:write scope="request" name="mensagemSetorComercial"/>
									</span>
                      			</logic:present>
                      			
                      			<logic:notPresent name="setorComercialNaoEncontrado" scope="request">
									<span style="color:#000000" id="mensagemSetorComercial">
										<bean:write scope="request" name="mensagemSetorComercial"/>
									</span>
                      			</logic:notPresent>

                      		</logic:present>
	                   	</td>
	             	</tr>
	             	
	             	<tr>
	             		<td><strong>Mensagens Crítica:</strong></td>
	             		<td>
	             			<html:select property="mensagemCritica" style="font-size: 11px;" >
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<logic:notEmpty name="collectionMensagemCritica" >
									<html:options collection="collectionMensagemCritica"
											  labelProperty="descricaoMensagem" 
											  property="id" />
								</logic:notEmpty>
							</html:select>	
	             		</td>
	             		<td align="right">
	             			<input name="Button" 
								type="button" 
								class="bottonRightCol" 
								value="Filtrar" 
								align="right"
								onclick="javascript:validaForm();">
	             		</td> 
	             	</tr>
	             	<tr>
	             		<td colspan="3"><hr /></td>
	             	</tr>
	             </table>
	             
	             <table width="100%" border="0">
	             	<tr>
						<td>
							<table width="100%" border="0" bordercolor="#79bbfd">
								<tr>
									<td colspan="2"><strong>Selecione as matrículas que terão as mensagem excluídas:</strong></td>
								</tr>
								<tr>
									<td>
									<table width="100%" bgcolor="#99CCFF" border="0">
										<tr bordercolor="#000000">
											<td width="40" bordercolor="#000000" bgcolor="#90c7fc" align="center">
												<div align="center"><strong><a href="javascript:facilitador(this);" id="0">Todos</a></strong></div>
											</td>
											<td width="70" bordercolor="#000000" bgcolor="#90c7fc" align="center">
												<div align="center"><strong>Matrícula</strong></div>
										   	</td>
										   	<td width="400" bordercolor="#000000" bgcolor="#90c7fc" align="center">
												<div align="center"><strong>Mensagem</strong></div>
										   	</td>
										</tr>
									</table>
									</td>
								</tr>
								
								<tr>
							  		<td>
							  			<div style="width: 100%; height: 100; overflow: auto;">
								  			<table width="100%" bgcolor="#99CCFF">
								  				<logic:present name="colecaoImoveisInscricaoResetorizada" scope="request">
									  				<%int cont = 0;%>
                      								<logic:iterate name="colecaoImoveisInscricaoResetorizada" id="imovelInscricaoResetorizada" scope="request" type="ImovelInscricaoResetorizada">								
									  					<%cont = cont + 1;
									  					if (cont % 2 == 0) {%>
								                       	<tr bgcolor="#cbe5fe">
								                        <%} else {%>
								                        <tr bgcolor="#FFFFFF">
								                        <%}%>
															<td width="8%" align="center">
								                            	<html:checkbox property="idsRegistro" value="${imovelInscricaoResetorizada.id}"/>	
								                            </td>
															<td width="12%" align="center"> 
								                            	<bean:write name="imovelInscricaoResetorizada" property="imovel.id"/>
								                            </td>
															<td width="70%" align="center"> 
								                            	<bean:write name="imovelInscricaoResetorizada" property="ocorrenciaResetorizacao.descricaoMensagem" />
								                            </td>
														</tr>
													</logic:iterate>	
												</logic:present>
											</table>
										</div>
									</td>
								</tr>
								
								<tr>
									<td>&nbsp;</td>
								</tr>
									
								<tr>
									<td align="left">
										<div align="left">
										<strong><font color="#FF0000">*</font></strong>
										Campos obrigatórios</div>
									</td>
								</tr>
								
								<tr>
				             		<td colspan="3"><hr /></td>
				             	</tr>
				             	
				             	<tr>
				             		<td>
				             			<table width="100%" border="0">
				             				<tr>
												<td colspan="2">
													<input name="Button" 
														type="button" 
														class="bottonRightCol" 
														value="Desfazer" 
														align="left"
														onclick="window.location.href='<html:rewrite page="/exibirLimparMensagemCriticaAtualizacaoInscricoesAction.do?menu=sim"/>'">
													
													<input name="Button" 
														type="button" 
														class="bottonRightCol" 
														value="Cancelar" 
														align="left"
														onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
												</td>
												
												<td align="right" colspan="2" >
													<input name="Button" 
														type="button" 
														class="bottonRightCol" 
														value="Gerar Relatório" 
														onclick="javascript:gerarRelatorio(document.forms[0]);">
													
													<input name="Button" 
														type="button" 
														class="bottonRightCol" 
														value="Atualizar" 
														onclick="javascript:atualizar();">
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
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	<jsp:include page="/jsp/relatorio/escolher_tipo_relatorio_tela_espera.jsp?relatorio=gerarRelatorioMensagemCriticaAtualizacaoInscricoesAction.do" /> 
</html:form>
</div>
	<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.gui.GcomAction"%>
<%@ page import="java.math.BigDecimal, gcom.util.Util" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript">

	function limpar(){
		var form = document.forms[0];
		form.idNovoSolicitacaoTipo.value="-1";
		form.idNovoGrupoSolicitacaoTipo.value="-1";
		 var selecionados = document.getElementsByName('idsRegistros');
		 for (i=0; i< selecionados.length; i++) {
			 selecionados[i].checked = false;
		 }
	}
	
	function selecionou(){
		var form = document.forms[0];
	    var selecionados = document.getElementsByName('idsRegistros');
		var jaSelecionado = false;
		var retorno = false;
		var count = 0;
	
		if(selecionados.length == null){
			if(selecionados.checked){
				retorno = selecionados.value;
				jaSelecionado = true;
			}
		}else{
			
			for (i=0; i< selecionados.length; i++) {
				
				if(selecionados[i].checked){
					count++;
					if(jaSelecionado == false){
						jaSelecionado = true;
						retorno = selecionados[i].value;
					}
				}
			}
			if(count == selecionados.length ){
				alert("Para transferir todas as especificações para um outro grupo de solicitação, utilize a opção Atualizar Tipo de Solicitação com Especificações");
				return false;
			}
		}
		
		if(jaSelecionado == false){
			alert('Selecione uma ou mais especificações para transferência');
			return false;
		}
		
		return retorno;
	}

	function validarForm(form){
		if(form.idNovoGrupoSolicitacaoTipo.value == "-1"){
			alert("Selecione o grupo de solicitação");
			return false;
		}
		if(form.idNovoSolicitacaoTipo.value == "-1"){
			alert("Selecione o tipo de solicitação");
			return false;
		}
		if(selecionou()){
			
			form.submit();
		}
	}
	
	function voltar(){
		$.ajax({
			   type: "POST",
			   url: "exibirTransferirSolicitacaoEspecificacaoAction.do?limpar=OK",
			   data: "",
			   success: function(msg){
				  
			   }
	});
		
		var form = document.forms[0];

		form.action = 'filtrarTipoSolicitacaoEspecificacaoAction.do';
		submeterFormPadrao(form);
	}
	
	function carregaTipoSolicitacao(){
		
		var form = document.forms[0];
		
		if (form.idNovoGrupoSolicitacaoTipo.value > 0){
			redirecionarSubmit('exibirTransferirSolicitacaoEspecificacaoAction.do?chave='+form.idSolicitacaoTipo.value+'&pesquisarSolicitacaoTipo=OK');
		}
	}
	
</script>

</head>

<body leftmargin="5" topmargin="5" >

<html:form action="/atualizarTransferirSolicitacaoEspecificacaoAction" 
	name="TransferirSolicitacaoTipoActionForm"
	type="gcom.gui.atendimentopublico.registroatendimento.TransferirSolicitacaoTipoActionForm"
	method="post">
	<html:hidden property="idSolicitacaoTipo"/>
	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>
	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>

			<td width="115" valign="top" class="leftcoltext">
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
			<td valign="top" bgcolor="#003399" class="centercoltext">
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
					<td class="parabg">Transferir Especificações</td>

					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td width="80%" colspan="2">Para transferir uma especificação, informe os dados abaixo :</td>
					
				</tr>
				<tr>
					<td></td>
				</tr>
			
               <tr>
                   <td width="40%" height="30"><strong>Grupo de Solicitação do Tipo: </strong></td>
                             		
                             		<td colspan="2"> 
									<html:text property="grupoSolicitacao" readonly="true"
										style="background-color:#EFEFEF; border:0;" size="35"
										maxlength="9" />
                                 	</td>
                          	</tr>


							<tr> 
                             		<td width="34%" height="30"><strong>Descrição do Tipo de Solicitação:</strong></td>
                              	<td width="25%">
									<html:text property="descricaoTipoSolicitacao" readonly="true"
										style="background-color:#EFEFEF; border:0;" size="50"
										maxlength="15" />
                                </td>
                           	</tr>
                           	
			  		<tr>
                             		<td height="30"><strong>Novo Grupo de Solicitação do Tipo: </strong></td>
                             		
                             		<td width="10%"> 
									<html:select property="idNovoGrupoSolicitacaoTipo" onchange="carregaTipoSolicitacao();">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<logic:present name="colSolicitacaoTipoGrupoNovo">
											<html:options collection="colSolicitacaoTipoGrupoNovo" labelProperty="descricao" property="id"/>
										</logic:present>	
									</html:select>				                                  	</td>
                           	</tr>
                           	
                           	<tr>
                             		<td height="30"><strong>Novo Tipo de Solicitação: </strong></td>
                             		
                             		<td width="10%"> 
									<html:select property="idNovoSolicitacaoTipo">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<logic:present name="colSolicitacaoTipoNovo">
											<html:options collection="colSolicitacaoTipoNovo" labelProperty="descricao" property="id"/>
										</logic:present>	
									</html:select>				                                  	</td>
                           	</tr>
							 <tr>
			                   <td width="40%" height="30"><strong>Especificação do Tipo de Solicitação<font color="#FF0000">*</font> </strong></td>
			               </tr>
                           
						</table>
						                  
						  
								<table width="100%" align="center" bgcolor="#90c7fc" border="0" cellpadding="0" cellspacing="0">
									
                                    	
				              		<tr bgcolor="#cbe5fe" >
				              			<td width="100%" align="center">
											<div  overflow: auto;">
												<table width="100%" bgcolor="#99CCFF">
													<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
														<td width="15%" bgcolor="#90c7fc">
														<div align="center"><strong>Marcar</strong></div>
														</td>
														<td width="85%" bgcolor="#90c7fc">
														<div align="center"><strong>Descrição da Especificação</strong></div>
														</td>
													</tr>
													
														<logic:present name="colSolicitacaoTipoEspecificacao">
															<%int cont = 0;%>
															<logic:iterate name="colSolicitacaoTipoEspecificacao"
																id="solTipoEspecificacao">
																<%cont = cont + 1;
																if (cont % 2 == 0) {%>
																	<tr bgcolor="#cbe5fe" class="styleFontePequena" >
																		<%} else {%>
																	<tr bgcolor="#FFFFFF" class="styleFontePequena">
																		<%}%>
																		<!-- <pg:item>  -->
																		<td width="10%">
																			<div align="center">																							
																				<html:checkbox property="idsRegistros"
																				value="${solTipoEspecificacao.id}"/>																									
																			</div>
																		</td>
																		<td width="15%" align="center">	
												                     		<span class="style2">
												                     		${solTipoEspecificacao.descricao}
												                     		</span>
																		</td>
																		
																	</tr>	
																</logic:iterate>
															</logic:present>
														</table>
													</div>
												</td>
											</tr>
											<tr><td>&nbsp;</td></tr>
												
                        
										</table>
						
					
				<table width="100%" border="0">
					<tr>
						<td valign="top">
							<input name="button" type="button"
								class="bottonRightCol" value="Voltar"
								onclick="limpar();voltar()">&nbsp;							
							<input name="button" type="button"
								class="bottonRightCol" value="Limpar"
								onclick="limpar()">&nbsp;
							<input name="Submit23" class="bottonRightCol" value="Cancelar"
							type="button"
							onclick="window.location.href='/gsan/telaPrincipal.do'"> 
						</td>
						<td valign="top">
						<div align="right"><input name="button" type="button"
							class="bottonRightCol" value="Atualizar"
							onclick="validarForm(document.forms[0]);" tabindex="8"></div>
						</td>
					</tr>
				
			    </table>
				
									</td>
				
								</tr>


				</table>
					

</html:form>
</body>
</html:html>
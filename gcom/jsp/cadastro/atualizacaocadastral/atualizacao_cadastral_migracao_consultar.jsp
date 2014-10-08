<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.cadastro.sistemaparametro.SistemaParametro"%>

<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<%@ include file="/jsp/util/telaespera.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>

<script language="JavaScript">
<!--
    function validateConsultarMigracaoAtualizacaoCadastralActionForm(form) {                                                                   
  		return true; 
	}

	function pesquisar(){
		var form = document.forms[0];

		botaoAvancarTelaEspera('/gsan/exibirConsultarMigracaoAtualizacaoCadastralAction.do');
		//form.action='exibirConsultarMigracaoAtualizacaoCadastralAction.do';
  		//form.submit();
	}
	
	function limpar(){
  		var form = document.forms[0];
  		form.action='exibirConsultarMigracaoAtualizacaoCadastralAction.do?menu=sim';
  		form.submit();
  	}
  	
  	function limparBorracha(){
		var form = document.forms[0];
		form.action='exibirConsultarMigracaoAtualizacaoCadastralAction.do?limpar=sim';
  		form.submit();
	}
	
-->
</script>
</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');" >

<div id="formDiv">
	<html:form action="/exibirConsultarMigracaoAtualizacaoCadastralAction" 
 		name="ConsultarMigracaoAtualizacaoCadastralActionForm" 
		type="gcom.gui.cadastro.atualizacaocadastral.ConsultarMigracaoAtualizacaoCadastralActionForm"
		method="post">
	
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
					<td class="parabg">Consultar Migração Setor/Quadra para Atualização Cadastral</td>
					<td width="11">
						<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
			
			<table bordercolor="#000000" width="100%" cellspacing="0">
				<tr>
					<td colspan="2">
						<p>Para consultar a migração do setor/quadra para atualização cadastral, informe os dados abaixo:</p>
					</td>
				</tr>
	        </table>
	        
	        <table width="100%" border="0">
		        <tr>
					<td height="10" colspan="3">
						<hr>
					</td>
				</tr>
			</table>
	        
	        <table bordercolor="#000000" width="100%" cellspacing="0">
	        	<tr>
					<td colspan="3">
						<table width="100%" border="0">
							<tr>
								<td style="width: 110px;">
									<strong>Empresa:<font color="#FF0000">*</font></strong>
								</td>
								<td align="left">
									<html:select property="idEmpresa" tabindex="1" onchange="pesquisar();" >
										<html:option value="-1">&nbsp;</html:option>
											<logic:present name="colecaoEmpresa" scope="request">					
												<html:options collection="colecaoEmpresa" labelProperty="descricao" property="id" />
											</logic:present>
									</html:select>
								</td>
							</tr>
							
							<tr>
								<td style="width: 110px;">
									<strong>Localidade:<font color="#FF0000">*</font></strong>
								</td>
								<td align="left">
									<html:select property="localidade" tabindex="1" onchange="pesquisar();" >
										<html:option value="-1">&nbsp;</html:option>
											<logic:present name="colecaoLocalidade" scope="request">					
												<html:options collection="colecaoLocalidade" labelProperty="descricao" property="id" />
											</logic:present>
									</html:select>
								</td>
							</tr>






<!--  
							<tr>
								<td style="width: 110px;">
									<strong>Localidade:<font color="#FF0000">*</font></strong>
								</td>
								<td>
									<html:text maxlength="3" tabindex="1" property="localidade" size="3" 
										onkeypress="validaEnterComMensagem(event, 'exibirConsultarMigracaoAtualizacaoCadastralAction.do','localidade','Localidade');return isCampoNumerico(event);"/>
										
									<a href="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 275, 480);">
										<img width="23" height="21" border="0" style="cursor:hand;"src="<bean:message key="caminho.imagens"/>pesquisa.gif" title="Pesquisar Localidade" />
									</a>
									
									<logic:present name="localidadeEncontrada" scope="request">
										<html:text property="nomeLocalidade" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
									</logic:present> 
			
									<logic:notPresent name="localidadeEncontrada" scope="request">
										<html:text property="nomeLocalidade" size="30" maxlength="30" readonly="true" style="background-color:#EFEFEF; border:0; color: red" />
									</logic:notPresent>
			
									<a href="javascript:limparBorracha();"> 
										<img src="<bean:message key="caminho.imagens"/>limparcampo.gif" border="0" title="Apagar" />
									</a>
								</td>
							</tr>
-->							
							<tr>
								<td height="10" colspan="3">
									<hr>
								</td>
							</tr>
							
							<tr>
								<td colspan="3">
									<strong>Setores / Quadras NÃO MIGRADOS para o ADMIN:</strong>
								</td>
							</tr>
							
							<tr>
								<td colspan="4">
									<table width="100%" border="0">
										<tr>
											<td>
												<div align="left"><strong>Setor Comercial</strong></div>
												<div id='setor1' style="OVERFLOW: auto;WIDTH: 120px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].setorNaoMigrado, 6);" >
													<html:select property="setorNaoMigrado" size="6" multiple="false" onfocus="OnSelectFocus(this, document.getElementById('setor1'), 6);" onchange="pesquisar();">										
														<html:option value="-1">&nbsp;</html:option>
														<logic:present name="colecaoSetor1" scope="request">
															<html:options collection="colecaoSetor1" labelProperty="codigoSetor" property="codigoSetor"/>
														</logic:present>
													</html:select>
												</div>
											</td>
											<td>	
												<div align="left"><strong>Quadra</strong></div>
												<div id='quadra1' style="OVERFLOW: auto;WIDTH: 120px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].idsQuadrasNaoMigradas, 6);" >
													<html:select property="idsQuadrasNaoMigradas" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('quadra1'), 6);" onchange="pesquisar();" >										
														<logic:present name="colecaoQuadra1" scope="request">
															<html:options collection="colecaoQuadra1" labelProperty="numeroQuadra" property="numeroQuadra"/>
														</logic:present>
													</html:select>
												</div>
											</td>
											<td>	
												<div align="left"><strong>Imóvel</strong></div>
												<div id='imovel1' style="OVERFLOW: auto;WIDTH: 120px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].imovelNaoMigrado, 6);" >
													<html:select property="imovelNaoMigrado" size="6" multiple="false" >										
														<html:option value="-1">&nbsp;</html:option>
														<logic:present name="colecaoImovel1" scope="request">
															<html:options collection="colecaoImovel1" labelProperty="idImovel" property="idImovel"/>
														</logic:present>
													</html:select>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="3">
												<div align="right">
													<strong>Qtde Imoveis:  </strong>
													<html:text property="totalImoveisNaoMigrados" size="10" maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
												</div>
											</td>	
										</tr>
									</table>
								</td>
							</tr>
							
							<tr>
								<td height="10" colspan="3">
									<hr>
								</td>
							</tr>
							
							<tr>
								<td colspan="3">
									<strong>Setores / Quadras MIGRADOS para o ADMIN:</strong>
								</td>
							</tr>
							
							<tr>
								<td colspan="4">
									<table width="100%" border="0">
										<tr>
											<td>
												<div align="left"><strong>Setor Comercial</strong></div>
												<div id='setor2' style="OVERFLOW: auto;WIDTH: 120px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].setorMigrado, 6);" >
													<html:select property="setorMigrado" size="6" multiple="false" onfocus="OnSelectFocus(this, document.getElementById('setor2'), 6);" onchange="pesquisar();" >										
														<html:option value="-1">&nbsp;</html:option>
														<logic:present name="colecaoSetor2" scope="request">
															<html:options collection="colecaoSetor2" labelProperty="codigoSetor" property="codigoSetor"/>
														</logic:present>
													</html:select>
												</div>
											</td>
											<td>	
												<div align="left"><strong>Quadra</strong></div>
												<div id='quadra2' style="OVERFLOW: auto;WIDTH: 120px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].idsQuadrasMigradas, 6);" >
													<html:select property="idsQuadrasMigradas" size="6" multiple="false" onfocus="OnSelectFocus(this, document.getElementById('quadra2'), 6);" onchange="pesquisar();" >										
														<logic:present name="colecaoQuadra2" scope="request">
															<html:options collection="colecaoQuadra2" labelProperty="numeroQuadra" property="numeroQuadra"/>
														</logic:present>
													</html:select>
												</div>
											</td>
											<td>	
												<div align="left"><strong>Imóvel</strong></div>
												<div id='imovel2' style="OVERFLOW: auto;WIDTH: 120px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].imovelMigrado, 6);" >
													<html:select property="imovelMigrado" size="6" multiple="false" >										
														<html:option value="-1">&nbsp;</html:option>
														<logic:present name="colecaoImovel2" scope="request">
															<html:options collection="colecaoImovel2" labelProperty="imovel.id" property="imovel.id"/>
														</logic:present>
													</html:select>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="3">
												<div align="right">
													<strong>Qtde Imoveis:  </strong>
													<html:text property="totalImoveisMigrados" size="10" maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
												</div>
											</td>	
										</tr>
									</table>
								</td>
							</tr>
							
							<tr>
								<td height="10" colspan="3">
									<hr>
								</td>
							</tr>
							
							<tr>
								<td colspan="3">
									<strong>Setores / Quadras RETORNADOS do ADMIN:</strong>
								</td>
							</tr>
							
							<tr>
								<td colspan="4">
									<table width="100%" border="0">
										<tr>
											<td>
												<div align="left"><strong>Setor Comercial</strong></div>
												<div id='setor3' style="OVERFLOW: auto;WIDTH: 120px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].setorRetornado, 6);" >
													<html:select property="setorRetornado" size="6" multiple="false" onfocus="OnSelectFocus(this, document.getElementById('setor3'), 6);" onchange="pesquisar();">										
														<html:option value="-1">&nbsp;</html:option>
														<logic:present name="colecaoSetor3" scope="request">
															<html:options collection="colecaoSetor3" labelProperty="codigoSetor" property="codigoSetor"/>
														</logic:present>
													</html:select>
												</div>
											</td>
											<td>	
												<div align="left"><strong>Quadra</strong></div>
												<div id='quadra3' style="OVERFLOW: auto;WIDTH: 120px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].idsQuadrasRetornadas, 6);" >
													<html:select property="idsQuadrasRetornadas" size="6" multiple="false" onfocus="OnSelectFocus(this, document.getElementById('quadra3'), 6);" onchange="pesquisar();" >										
														<logic:present name="colecaoQuadra3" scope="request">
															<html:options collection="colecaoQuadra3" labelProperty="numeroQuadra" property="numeroQuadra"/>
														</logic:present>
													</html:select>
												</div>
											</td>
											<td>	
												<div align="left"><strong>Imóvel</strong></div>
												<div id='imovel3' style="OVERFLOW: auto;WIDTH: 120px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].imovelRetornado, 6);" >
													<html:select property="imovelRetornado" size="6" multiple="false" >										
														<html:option value="-1">&nbsp;</html:option>
														<logic:present name="colecaoImovel3" scope="request">
															<html:options collection="colecaoImovel3" labelProperty="idImovel" property="idImovel"/>
														</logic:present>
													</html:select>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="3">
												<div align="right">
													<strong>Qtde Imoveis:  </strong>
													<html:text property="totalImoveisRetornados" size="10" maxlength="10" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000" />
												</div>
											</td>	
										</tr>
									</table>
								</td>
							</tr>
							
							<tr>
								<td height="19"><strong> <font color="#FF0000"></font></strong></td>
								<td align="right">
								<div align="left"><strong><font color="#FF0000">*</font></strong>
								Campos obrigat&oacute;rios</div>
								</td>
							</tr>
							
							<tr>
								<td height="10" colspan="3">
									<hr>
								</td>
							</tr>
						</table>
						
						<tr>
							<td align="left" colspan="2" >
					          	<input type="button" class="bottonRightCol" value="Limpar" onclick="javascript:limpar();"/>
					          	<input type="button" name="ButtonCancelar" class="bottonRightCol" value="Cancelar" 
					          		onClick="javascript:window.location.href='/gsan/telaPrincipal.do'">
							</td>
						</tr>
						
					</td>
				</tr>
			</table>			
			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
	</html:form>
</div>

</body>
</html:html>
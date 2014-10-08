<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.util.ConstantesSistema"%>
 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	<head>
		<%@ include file="/jsp/util/titulo.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
		
		<!--================================= SCRIPTS =============================================================-->
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>jquery_util.js"></script>
		<!--=======================================================================================================-->
		<script language="JavaScript">	

			$(document).ready(function(){
				
				$('[name=sistemaAbastecimento]').change(function() {
					
					apagarSelect('subsistemaAbastecimento');
					apagarSelect('setorAbastecimento');
					apagarSelect('distritoOperacional');
					apagarSelect('areaOperacional');
					apagarSelect('zonaPressao');
					
					chamarAjax('GerarRelatorioTotalizadorSistemaAbastecimentoActionForm',
							  	 'exibirGerarRelatorioTotalizadorSistemaAbastecimento.do?action=atualizarListaSubsistemaAbastecimento',
							     sucessoAtSubsistema,null);		
				});
			
			
				$('[name=subsistemaAbastecimento]').change(function() {
	
					apagarSelect('setorAbastecimento');
					apagarSelect('distritoOperacional');
					apagarSelect('areaOperacional');
					apagarSelect('zonaPressao');
					
					chamarAjax('GerarRelatorioTotalizadorSistemaAbastecimentoActionForm',
						  	 	'exibirGerarRelatorioTotalizadorSistemaAbastecimento.do?action=atualizarListaSetorAbastecimento',
					    	 	sucessoAtSetor,null);
				});
	
				$('[name=setorAbastecimento]').change(function() {
	
					apagarSelect('distritoOperacional');
					apagarSelect('areaOperacional');
					apagarSelect('zonaPressao');
					
					chamarAjax('GerarRelatorioTotalizadorSistemaAbastecimentoActionForm',
						  	 	'exibirGerarRelatorioTotalizadorSistemaAbastecimento.do?action=atualizarListaDistritoOperacional',
					    	 	sucessoAtDistrito,null);
				});
	
				$('[name=distritoOperacional]').change(function() {
	
					apagarSelect('areaOperacional');
					apagarSelect('zonaPressao');
					
					chamarAjax('GerarRelatorioTotalizadorSistemaAbastecimentoActionForm',
						  	 	'exibirGerarRelatorioTotalizadorSistemaAbastecimento.do?action=atualizarListaAreaOperacional',
					    	 	sucessoAtArea,null);
				});
	
				$('[name=areaOperacional]').change(function() {
					
					apagarSelect('zonaPressao');
					
					chamarAjax('GerarRelatorioTotalizadorSistemaAbastecimentoActionForm',
						  	 	'exibirGerarRelatorioTotalizadorSistemaAbastecimento.do?action=atualizarListaZonaPressao',
						  	 	sucessoAtZona,null);
				});
				
			});
	
			function sucessoAtSubsistema(data){
				var obj = JSON && JSON.parse(data) || $.parseJSON(data);
				preencherSelect(obj,'subsistemaAbastecimento');
			}
			
			function sucessoAtSetor(data){
				var obj = JSON && JSON.parse(data) || $.parseJSON(data);
				preencherSelect(obj,'setorAbastecimento');
			}
	
			function sucessoAtDistrito(data){
				var obj = JSON && JSON.parse(data) || $.parseJSON(data);
				preencherSelect(obj,'distritoOperacional');
			}
	
			function sucessoAtArea(data){
				var obj = JSON && JSON.parse(data) || $.parseJSON(data);
				preencherSelect(obj,'areaOperacional');
			}
	
			function sucessoAtZona(data){
				var obj = JSON && JSON.parse(data) || $.parseJSON(data);
				preencherSelect(obj,'zonaPressao');
			}
			
		</script>
	</head>
	<body leftmargin="5" topmargin="5" onload="" >
		<div id="formDiv">
			<html:form action="/gerarRelatorioTotalizadorSistemaAbastecimento"
				   name="GerarRelatorioTotalizadorSistemaAbastecimentoActionForm"
				   type="gcom.gui.relatorio.operacional.GerarRelatorioTotalizadorSistemaAbastecimentoActionForm"
				   method="post">			
				   
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
					<td width="600" valign="top" bgcolor="#003399" class="centercoltext">
						<table height="100%">
							<tr>
								<td></td>
							</tr>
						</table>
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td width="11">
									<img border="0"	src="<bean:message key="caminho.imagens"/>parahead_left.gif" />
								</td>
								<td class="parabg">Gerar Relatório Totalizador de Sistema de Abastecimento</td>
								<td width="11">
									<img border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" />
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td height="5" colspan="3"></td>
							</tr>
						</table>
						<table width="100%" border="0">
							<tr>
								<td colspan="2">
									<p>Para filtrar o relatório, informe os dados abaixo:</p>
									<p>&nbsp;</p>
								</td>
							</tr>
							<!--=========================================== CORPO DA VISÃO =========================================================-->
							
							<tr>
							   <td><strong>Sistema de Abastecimento:<font color="#FF0000">*</font></strong></td>
							   <td>
									<html:select property="sistemaAbastecimento" style="width: 200px;" tabindex="13">
										<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
										<html:options collection="colecaoSistemaAbastecimento" labelProperty="descricao" property="id"/>
									</html:select>
							   </td>
						   	</tr>
							<tr>
								<td width="40%" class="style3"><strong>Subsistema de Abastecimento:<font color="#FF0000">*</font></strong></td>
								<td width="60%" colspan="2"><html:select
									property="subsistemaAbastecimento" tabindex="3" style="width:200px;height:20px;">
									<html:option value="-1"> &nbsp; </html:option>
									<logic:present name="colecaoSubsistemaPrincipal" scope="session">
										<html:options collection="colecaoSubsistemaPrincipal" property="id"
											labelProperty="descricao" />
									</logic:present>	
								</html:select></td>
							</tr>
							<tr>
								 <td  width="40%"class="style3"><strong>Setor Abastecimento:</strong></td>
								 <td  width="60%" colspan="2">
						  			<html:select property="setorAbastecimento" tabindex="5" style="width:200px;height:20px;">
										<html:option value="-1"> &nbsp; </html:option>
										<logic:present name="colecaoSetorAbastecimento" scope="session">
											<html:options collection="colecaoSetorAbastecimento" property="id" labelProperty="descricao"/>
										</logic:present>
									</html:select>
								</td>
							</tr>
							<tr>
								 <td  width="40%"class="style3"><strong>Distrito Operacional:</strong></td>
								 <td  width="60%" colspan="2">
						  			<html:select property="distritoOperacional" tabindex="5" style="width:200px;height:20px;">
										<html:option value="-1"> &nbsp; </html:option>
										<logic:present name="colecaoDistritoOperacional" scope="session">
											<html:options collection="colecaoDistritoOperacional" property="id" labelProperty="descricao"/>
										</logic:present>
									</html:select>
								</td>
							</tr>
							<tr>
								 <td  width="40%"class="style3"><strong>Área Operacional:</strong></td>
								 <td  width="60%" colspan="2">
						  			<html:select property="areaOperacional" tabindex="5" style="width:200px;height:20px;">
										<html:option value="-1"> &nbsp; </html:option>
										<logic:present name="colecaoAreaOperacional" scope="session">
											<html:options collection="colecaoAreaOperacional" property="id" labelProperty="descricao"/>
										</logic:present>
									</html:select>
								</td>
							</tr>
							<tr>
								 <td  width="40%"class="style3"><strong>Zona de Pressão:</strong></td>
								 <td  width="60%" colspan="2">
						  			<html:select property="zonaPressao" tabindex="5" style="width:200px;height:20px;">
										<html:option value="-1"> &nbsp; </html:option>
										<logic:present name="colecaoZonaPressao" scope="session">
											<html:options collection="colecaoZonaPressao" property="id" labelProperty="descricaoZonaPressao"/>
										</logic:present>
									</html:select>
								</td>
							</tr>
							<tr>
						      	<td><strong>Tipo de Relatório:<font color="#ff0000">*</font></strong></td>
						        <td colspan="2">
						        	<html:radio property="tipoRelatorio" value="1"><strong>Sintético</strong></html:radio>
						        	<html:radio property="tipoRelatorio" value="2"><strong>Analítico</strong></html:radio>
						        </td>
					      	</tr>
							
							<!--=====================================================================================================================-->
							<tr>
								<td>&nbsp;</td>
							</tr>					 
                   			<tr>
								<td>&nbsp;</td>
								<td align="left"><font color="#FF0000">*</font> Campo Obrigatório</td>
							</tr>
							
							<tr>
								<td colspan="2"><input type="button" name="Button"
									class="bottonRightCol" value="Desfazer" tabindex="33"
									onClick="javascript:window.location.href='/gsan/exibirGerarRelatorioTotalizadorSistemaAbastecimento.do?menu=sim'"
									style="width: 80px" />&nbsp; <input type="button" name="Button"
									class="bottonRightCol" value="Cancelar" tabindex="32"
									onClick="javascript:window.location.href='/gsan/telaPrincipal.do'"
									style="width: 80px" /></td>
								<td align="right">
								<input type="submit" name="Button"
									   class="bottonRightCol" value="Gerar" tabindex="32" style="width: 80px" />
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

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="gcom.util.ConstantesSistema"%>
<%@ page import="gcom.faturamento.ComandoOsAnormalidade"%>
<%@ page import="gcom.util.Util"%>
<head>
<html:html>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="GerarOsAnormalidadeConsumoFiltrarActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
	<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
<script language="JavaScript">
<!-- Begin


-->
</script>
</head>

<body leftmargin="5" topmargin="5"
	>


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

			<td valign="top" class="centercoltext">
			<p>&nbsp;</p>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Consultar Comando OS Anormalidade Consumo</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a Páginação da Tela de Processo-->
			<p>&nbsp;</p>
			<logic:iterate
				name="colComandoOsAnormalidade"
				id="comandoOsAnormalidade"
				type="ComandoOsAnormalidade">
			<table width="100%" border="0">
				<tr>
					<td><strong>Mês/Ano Referência:<font color="#FF0000"></font></strong></td>
				    <td colspan="6">
				    	<span class="style2">
				    	<logic:present name="comandoOsAnormalidade" > 
				    		<strong> 
								<%= Util.formatarAnoMesParaMesAno(comandoOsAnormalidade.getAnoMesReferencia())%>
							</strong>  
							</logic:present>              
						</span>
						
					</td>
				</tr>
				<tr>
					<td><strong>Grupo:</strong></td>
				    <td colspan="6">
				    	<span class="style2">
				    		<strong> 
				    		<logic:present name="comandoOsAnormalidade" property="faturamentoGrupo" > 
								<bean:write name="comandoOsAnormalidade" property="faturamentoGrupo.descricao" />
							</logic:present>		
							</strong>                
						</span>
					</td>
				</tr>
				<tr>
					<td width="30%"><strong>Gerência Regional:</strong></td>
					<td colspan="3">
					<logic:present name="comandoOsAnormalidade" property="gerenciaRegional">
						<bean:write name="comandoOsAnormalidade" property="gerenciaRegional.nome"/>
					</logic:present>		
					</td>
				</tr>
				<tr>
					<td width="200">
						<strong>Unidade de Negócio:</strong>
					</td>
					<td colspan="3">
					<logic:present name="comandoOsAnormalidade" property="unidadeNegocio">
						<bean:write name="comandoOsAnormalidade"  property="unidadeNegocio.nome"  />
					</logic:present>			
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%" align="center" bgcolor="#99CCFF" border="0">
							<tr>
								<td colspan="2">
									<strong>Informe os dados da inscri&ccedil;&atilde;o inicial:</strong>
								</td>
							</tr>
							
							<tr bgcolor="#cbe5fe">
								<td width="100%" align="center" colspan="2">
									<table width="100%" border="0">
										<tr bgcolor="#cbe5fe">
											<td width="20%"><strong>Localidade:</strong></td>
											<td colspan="3">
												<logic:present name="comandoOsAnormalidade" property="localidadeInicial" >
													<bean:write name="comandoOsAnormalidade"  property="localidadeInicial.id"  /> - 
												</logic:present>	
						
												<logic:present name="comandoOsAnormalidade" property="localidadeInicial">
													<bean:write name="comandoOsAnormalidade"  property="localidadeInicial.descricao"  />
												</logic:present>	
												
												
										</td>
										</tr>
										<tr>
											<td><strong>Setor Comercial :</strong></td>
											<td colspan="3">
												<logic:present name="comandoOsAnormalidade" property="setorComercialInicial"> 
													<bean:write name="comandoOsAnormalidade"  property="setorComercialInicial.codigo"  />
												</logic:present>	
												<logic:present name="comandoOsAnormalidade" property="setorComercialInicial" > - 
													<bean:write name="comandoOsAnormalidade"  property="setorComercialInicial.descricao"  />
												</logic:present>	
												
										</tr>
										<tr>
										<tr>
											<td><strong>Quadra:</strong></td>
											<td colspan="2">
												<logic:present name="comandoOsAnormalidade" property="quadraInicial"> 
													<bean:write name="comandoOsAnormalidade"  property="quadraInicial.numeroQuadra"  />
												</logic:present>	
											</td>
											
										</tr>
				
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td colspan="4">
						<table width="100%" align="center" bgcolor="#99CCFF" border="0">
							<tr>
								<td colspan="2">
									<strong>Informe os dados da inscri&ccedil;&atilde;o Final:</strong></td>
							</tr>
							<tr bgcolor="#cbe5fe">
								<td width="100%" align="center" colspan="2">
									<table width="100%" border="0">
										<tr bgcolor="#cbe5fe">
											<td width="20%"><strong>Localidade:</strong></td>
											<td colspan="3" height="24">
												<logic:present name="comandoOsAnormalidade" property="localidadeFinal"> 
													<bean:write name="comandoOsAnormalidade"  property="localidadeFinal.id"  />
												</logic:present>	
											<logic:present name="comandoOsAnormalidade" property="localidadeFinal"> - 
													<bean:write name="comandoOsAnormalidade"  property="localidadeFinal.descricao"  />
												</logic:present>	
												
												
										</td>
										</tr>
										<tr>
											<td><strong>Setor Comercial :</strong></td>
											<td colspan="3">
												<logic:present name="comandoOsAnormalidade" property="setorComercialFinal"> 
													<bean:write name="comandoOsAnormalidade"  property="setorComercialFinal.codigo"  />
												</logic:present>	
												<logic:present name="comandoOsAnormalidade" property="setorComercialFinal"> - 
													<bean:write name="comandoOsAnormalidade"  property="setorComercialFinal.descricao"  />
												</logic:present>	
												
										</tr>
										<tr>
										<tr>
											<td><strong>Quadra:</strong></td>
											<td colspan="2">
												<logic:present name="comandoOsAnormalidade" property="quadraFinal" >
													<bean:write name="comandoOsAnormalidade"  property="quadraFinal.numeroQuadra"  />
												</logic:present>	
											</td>
											
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
					
				<tr>
					<td colspan="4" HEIGHT="15"></td>
				</tr>
				<tr>
					<td colspan="4">

					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td><strong>Informe os dados da Rota Inicial:</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								<tr>
									<td height="10" width="120"><strong>Rota:</strong></td>
									<td><logic:present name="comandoOsAnormalidade" property="rotaInicial">
													<bean:write name="comandoOsAnormalidade"  property="rotaInicial.id"  />
												</logic:present>	
									</td>
								</tr>


							</table>

							</td>
						</tr>
					</table>

					</td>
				</tr>

				<!-- FIM ROTA INICIAL -->

				<tr>
					<td colspan="4" HEIGHT="5"></td>
				</tr>

				<!-- ROTA FINAL -->

				<tr>
					<td colspan="4">

					<table width="100%" align="center" bgcolor="#99CCFF" border="0">
						<tr>
							<td><strong>Informe os dados da Rota Final:</strong></td>
						</tr>
						<tr bgcolor="#cbe5fe">
							<td width="100%" align="center">

							<table width="100%" border="0">
								<tr>
									<td height="10" width="120"><strong>Rota:</strong></td>
									<td><logic:present name="comandoOsAnormalidade" property="rotaFinal">
													<bean:write name="comandoOsAnormalidade"  property="rotaFinal.id"  />
												</logic:present>	
									</td>
								</tr>
 
							</table>

							</td>
						</tr>
					</table>
				<tr>
					<td colspan="4">
					<hr>
					</td>
				</tr>
	      
				

				<!-- FIM ROTA FINAL -->


			
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr><td>
						<input type="button" 
							   name="Button" 
							   class="bottonRightCol"
							   value="Voltar" 
							   tabindex="17"
							   onClick="javascript:history.back()"
							   style="width: 80px" />
					</td>
					
				</tr>
			</table>
			</logic:iterate>
			<p>&nbsp;</p>
		</tr>
	</table>
	<tr>
		<td colspan="3"><%@ include file="/jsp/util/rodape.jsp"%>
		<script>
	
			setCookie("desativaHistoryBack", "true", "1");
		
		</script>
	</tr>

</body>
</html:html>

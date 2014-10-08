<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	<head>
		<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
		
		<%@ page import="gcom.util.ConstantesSistema"%>
		<%@ page import="gcom.atendimentopublico.ordemservico.bean.ExibirEtapasOSAcompanhamentoServicoHelper"%>
		
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js">
		</script>
		<script src="<bean:message key="caminho.js"/>jquery/jquery.js"></script>
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.jquery"/>jquery.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>
		<!-- 
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>lightbox/jquery.js"></script>
		 -->
		<script type="text/javascript" src="<bean:message key="caminho.jquery"/>lightbox/jquery.lightbox-0.5.js"></script>
		<link rel="stylesheet" type="text/css" href="<bean:message key="caminho.css"/>jquery.lightbox-0.5.css" media="screen" />
		<html:javascript staticJavascript="false"  formName="AcompanhamentoArquivosRoteiroActionForm" dynamicJavascript="true"/>
		
		<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js">
		</script><html:javascript staticJavascript="false"  formName="AcompanharRoteiroProgramacaoOrdemServicoActionForm" dynamicJavascript="true"/>
		
		<style type="text/css">
			/* jQuery lightBox plugin - Gallery style */
			#gallery {
				background-color: #90C7FC;
				padding: 10px;
				width: 520px;
			}
			#gallery ul { list-style: none; }
			#gallery ul li { display: inline; }
			#gallery ul img {
				border: 5px solid #CBE5FE;
				border-width: 5px 5px 20px;
			}
			#gallery ul a:hover img {
				border: 5px solid #fff;
				border-width: 5px 5px 20px;
				color: #fff;
			}
			#gallery ul a:hover { color: #fff; }
		</style>
		
		
	
	</head>

	<body leftmargin="5" topmargin="5" >
							
			<table width="650" border="0" cellspacing="5" cellpadding="0">
				<tr>
					<td width="770" valign="top" class="centercoltext">
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
						<td class="parabg">Consultar Etapas Pendentes</td>
						<td width="11"><img border="0"
							src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
					</tr>
				</table>
				<p>&nbsp;</p>
				<table width="650" align="center" cellpadding="0" cellspacing="0">
									
              		<tr bgcolor="#cbe5fe" >
              			<td width="100%" align="center">
							<div style="height:500px; overflow: auto;">
								<table width="650" bgcolor="#99CCFF">
									<tr bordercolor="#000000" bgcolor="#90c7fc" class="styleFontePeqNegrito">
										<td width="15%" bgcolor="#90c7fc">
										<div align="center">EQUIPE</div>
										</td>
										<td width="35%" bgcolor="#90c7fc">
										<div align="center">ETAPAS</div>
										</td>
										<td width="10%" bgcolor="#90c7fc">
										<div align="center">Situa&ccedil;&atilde;o</div>
										</td>
									</tr>
									
									<logic:present name="colecaoEtapasOSAcompanhamentoServico">
										<%int cont = 0;%>
										<logic:iterate name="colecaoEtapasOSAcompanhamentoServico" type="ExibirEtapasOSAcompanhamentoServicoHelper"
											id="etapasOSAcompanhamentoServico">
											<%cont = cont + 1;
											if (cont % 2 == 0) {%>
												<tr bgcolor="#cbe5fe" class="styleFontePequena" >
													<%} else {%>
												<tr bgcolor="#FFFFFF" class="styleFontePequena">
													<%}%>
													<!-- <pg:item>  -->
													<td width="8%">
														<div align="center">
															${etapasOSAcompanhamentoServico.nmEquipe}
														</div>
													</td>
													<td width="15%" align="center">	
							                     		${etapasOSAcompanhamentoServico.nmEtapa}
													</td>
													<td width="15%">
														<div align="center">${etapasOSAcompanhamentoServico.dsSituacao}</div> 
													</td>
												</tr>
											</logic:iterate>
										</logic:present>
												
								</table>
							</div>
						</td>
					</tr>
				</table>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:html>
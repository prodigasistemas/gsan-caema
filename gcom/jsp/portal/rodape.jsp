<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html>
	<head>
	</head>
	
	<body bgcolor="#red">
	     <!-- Footer - Start -->
	     <div id="footer">
	     	<h4>Canais de atendimento</h4>
	             <ul>
		         	 <li id="link-1"><a href="http://www.smartok.com.br/smartok/Login_compesa_site.html" title="Consultar Telemetria">Consultar Telemetria</a></li>
		            <li id="link-2"><a href="exibirCanaisAtendimentoCompesaAction.do?method=teleatendimento" title="Tele atendimento 0800.081.0195 / 0800 081 0185 ">Tele atendimento 0800.081.0195 / 0800 081 0185 </a></li>
		            <li id="link-3"><a href="exibirCanaisAtendimentoCompesaAction.do?method=autoatendimento" title="Auto atendimento">Auto atendimento</a></li>
		            <li id="link-4"><a href="http://200.238.107.205:8080/multiwork/controller?action=atendimentoonline&origem=atendimentoOnline&INCODIGOORGAO=77&command=INSERT" title="Ouvidoria">Ouvidoria</a></li>
		         </ul>
		     
	         <address>Companhia Pernambucana de Saneamento - Todos os Direitos Reservados.</address>
	     </div>
	     <!--[if lt IE 7 ]>
	     	<script language="JavaScript" src="<bean:message key='caminho.portal.js'/>DD_belatedPNG_0.0.8a-min.js"></script>
    		<script>DD_belatedPNG.fix('img, .png_bg'); // Fix any <img> or .png_bg bg-images. Also, please read goo.gl/mZiyb </script>
	  	 <![endif]-->
	     <!-- Footer - End -->
	</body>
</html>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%@ page import="gcom.gui.portal.ConsultarEstruturaTarifariaPortalHelper" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>Compesa</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portal.css"/>internal.css" type="text/css">
	<style type="text/css">
						
			.lista-condicoes ul li {
				list-style: url("/gsan/imagens/portal/general/marcador.gif");
				margin: 0 0 0 15px;
				list-style-position: outside;
				padding: 0px;
			}
			
			.lista-condicoes li {
				list-style: decimal;
				list-style-position: inside;
				font-weight: bold;
				color: #008FD6;
				margin: 0 0 0 0px;
				padding: 0px;
							
			}
			
			.lista-condicoes li #lista ul li {
				list-style: upper-alpha inside none;				
				list-style-position: inside;
				font-weight: bold;
				color: #000;
				margin: 0 0 0 30px;
				padding: 0 0px;
			}
			
			em {
    			color: #008FD6;
    			font-style: normal;
   				font-weight: 700;
    			padding-right: 5px;
			}
			font span{
   				color: #008FD6;
    			float: none;
   				margin: 0;
    			padding-bottom: 10px;
    			text-indent: 0;
				font-style:italic;
    			float: right;
    		}
    		.paragrafo {
    			line-height: 30px;
    		}
    		
    		#lista-criterios ul li
    		{
    			font-weight: bold;
    			list-style-type: upper-alpha;
    			margin: 0 0 0 47px;
				padding:0 0px;				
    		}
			#atualizacao{
    			line-height:2.3em;
    			padding:0 15px; 
    			position:relative;    			
    			float:left; 
    			font-size:11px;
    			height:33px;
    			width: 315px;  	
    		}
    		span{
    			font-weight: normal;
    			color: #2f2f2f;
    		}
    					
		</style>
	</head>
	
	
	<body>
		<div id="container">
	    	<html:form action="/exibirInformacoesTarifaSocialPortalCompesaAction.do"
				name="ExibirInformacoesPortalCompesaActionForm"
				type="gcom.gui.portal.ExibirInformacoesPortalCompesaActionForm" method="post" >
						
	    	<%@ include file="/jsp/portal/cabecalho.jsp"%>
	        
	        <!-- Content - Start -->
		        <div id="content">
		        <%@ include file="/jsp/portal/cabecalhoInformacoes.jsp"%>
		        	<div id="tarifasocial" class="serv-int" style="width:880px;">	
	    	    		
							<p>&nbsp;</p>
						
	        				<h3>
								Tarifa social<span>&nbsp;</span>
							</h3>
						
							<br />
							<br />						
							<em>Cadastramento na tarifa social</em>
							<br />
							<br />	
							
							<p style="text-align:justify;">
								O Governo do Estado, conjuntamente com a Compesa, instituiu em novembro de 2003 a TARIFA SOCIAL com o objetivo de asistir as fam&iacute;lias de baixa renda.
								O cliente que se enquadrar e se cadastrar nos crit&eacute;rios e condi&ccedil;&otilde;es da Tarifa Social e se cadastrar,  ser&aacute; beneficiado com um subs&iacute;dio de mais de 78% sobre o valor da Tarifa M&iacute;nima de &aacute;gua que &eacute; de R$ <bean:write name="ExibirInformacoesPortalCompesaActionForm"  property="valorResidencialAtual"/>, passando a pagar R$ <bean:write name="ExibirInformacoesPortalCompesaActionForm"  property="valorResidencial" /> a partir do Extrato de Decis&atilde;o &#45; ARPE &#45; DOE &#45; 23/11/2010.
							</p>
							<p>&nbsp;</p>						
							<em>Crit&eacute;rios:</em>
							<br />
							<br />	
							
							<p style="text-align:justify;">
								Ter&aacute; direito ao benef&iacute;cio da Tarifa Social o cliente que seja morador de im&oacute;vel abastecido pela Compesa, cadastrado na categoria Residencial n&atilde;o medido ou medido que apresente nos &uacute;ltimos 6(seis) meses, para cada economia, consumo m&eacute;dio de &aacute;gua &#45; de at&eacute; 10m3/m&ecirc;s(dez metros c&uacute;bicos m&ecirc;s) e consumo m&eacute;dio de energia el&eacute;trica - na categoria residencial monof&aacute;sico - de at&eacute; 80 kwh/m&ecirc;s(oitenta quilowatts hora m&ecirc;s) e que tamb&eacute;m se enquadre em um dos crit&eacute;rios abaixo estabelecidos:
							</p>
							
							<br />
							<div id="lista-criterios" style="background-color: #e9e9e9;">
								<ul>
									<li>
										<p style="text-align: justify;" ><span>Seja Benefici&aacute;rio de Programa de Prote&ccedil;&atilde;o Social do Governo Federal ou Estadual, descritos a seguir: Bolsa Fam&iacute;lia, Programa de Erradica&ccedil;&atilde;o do Trabalho Infantil-PETI, Benef&iacute;cio de Presta&ccedil;&atilde;o Continuada(Amparo Assistencial ao idoso e ao Deficiente) e Seguro Desemprego.</span></p>
										<p style="text-align: justify;" class="paragrafo"><span>Cliente beneficiado com  Seguro Desemprego dever&aacute; estar recebendo o valor de 1(um) sal&aacute;rio m&iacute;nimo vigente, sendo o benef&iacute;cio da Tarifa Social concedido pelo per&iacute;odo m&aacute;ximo de 5 (cinco) meses.</span></p>
										<br />
									</li>
									<li>
										<p style="text-align: justify;" class="paragrafo"><span>Tenha Renda Familiar Mensal Comprovada de at&eacute; 1 (um) sal&aacute;rio m&iacute;nimo vigente.</span></p>
										<p style="text-align: justify;" class="paragrafo"><span>Entende-se por Renda Familiar Mensal Comprovada o somat&oacute;rio dos rendimentos de todos os moradores do im&oacute;vel advindos de sal&aacute;rios e vantagens (exceto Sal&aacute;rio-Fam&iacute;lia), pens&otilde;es, aposentadorias, benef&iacute;cios e outros.</span></p>
										<br />
									</li>
									<li>
										<p style="text-align: justify;"><span>Tenha Renda Familiar Mensal Declarada de at&eacute; 1 (um) sal&aacute;rio m&iacute;nimo vigente e seja morador de im&oacute;vel com &aacute;rea constru&iacute;da de at&eacute; 60m2 (sessenta metros quadrados).</span></p>
									</li>
								</ul>								
							</div>
							<br />
							<p style="text-align:justify;">
								Entende&#45;se por Renda Familiar Mensal Declarada o somat&oacute;rio dos recebimentos de todos os moradores do im&oacute;vel advindos de rendimentos de aut&ocirc;nomos, presta&ccedil;&atilde;o ou vendas de bens e servi&ccedil;os, alugueis e outros.
							</p>
							
							<p>&nbsp;</p>						
							<em>Condi&ccedil;&otilde;es</em>
							<br>&nbsp;</br>
							
							<ul class="lista-condicoes">
								<li><b>Para Cadastramento</b>
									<br />
									<br />
									<div id="lista" style="background-color: #e9e9e9;">
										<!-- Substituido por lista de criterios -->
										<ul style="" >
											<li>
												<span>O im&oacute;vel dever&aacute; estar na situa&ccedil;&atilde;o &#34;Ligado&#34;, &#34;Cortado&#34; ou &#34;Suprimido&#34; de &Aacute;gua;</span>
												<br />
												<br />
											</li>
											<li>
												<span>O cliente inadimplente que se enquadrar no crit&eacute;rios da Tarifa Social, ter&aacute; direito ao beneficio desde que se comprometa a liquidar ou negociar o d&eacute;bito, mediante Carta Cobran&ccedil;a, que a Compesa enviar&aacute; ao seu im&oacute;vel;</span>
												<br />
												<br />
											</li>
											<li>
												<span>O d&eacute;bito de fatura do per&iacute;odo n&atilde;o prescricional ser&aacute; convertido retroativamente para o valor da Tarifa Social da &eacute;poca.</span>
												<br />
												<br />
											</li>
											<li>
												<span>As multas, juros e corre&ccedil;&otilde;es do d&eacute;bito convertido ser&atilde;o cancelados.</span>
												<br />
												<br />
											</li>
											<li>
												<span>O cliente dever&eacute; apresentar original e c&oacute;pia do Cadastro de Pessoa F&iacute;sica - CPF, Carteira de Identidade, conta da Compesa, conta de Energia El&eacute;trica e demais documentos atualizados, conforme se enquadre.</span>
												<br />
											</li>
										</ul>
										
									</div>
									<br />
									<br />
									<p style="text-align: justify;" >
										<span>Caso o solicitante do benef&iacute;cio n&atilde;o seja propriet&aacute;rio do im&oacute;vel, ser&aacute; obrigat&oacute;rio anexar ao formul&aacute;rio de cadastramento c&oacute;pia do CPF e Carteira de Identidade do propriet&aacute;rio;</span>
									</p>
									<br />
									<br />
									<p style="color: #808080;"><b>Sendo Benefici&aacute;rio de Programa de Prote&ccedil;&atilde;o Social do Governo Federal:</b></p>
									<br />
									<ul>
										<li><span>Cart&atilde;o de Programa Social do Governo Federal.</span></li>
										<li><span>Comprovante de Pagamento do Benef&iacute;cio Social.</span></li>
									</ul>
									<br />
									<br />
									<p style="color: #808080;"><b>Tendo Renda Familiar Mensal Comprovada:</b></p>
									<br />
									<ul>
										<li><span>Recibo de Pagamento e Carteira profissional ou</span></li>
										<li><span>Contra-Cheque ou</span></li>
										<li><span>Demonstrativo de Pagamento.</span></li>
									</ul>
									<br />
									<br />
									<p style="color: #808080;"><b>Tendo Renda Familiar Mensal Declarada:</b></p>
									<br />
									<ul>
										<li><span>Imposto Predial e Territorial Urbano - IPTU ou</span></li>
										<li><span>Escritura com &aacute;rea construida do im&oacute;vel e</span></li>
										<li><span>Declarar Renda Familiar no Formul&oacute;rio &#34;Tarifa Social-Cadastramento&#34;.</span></li>
									</ul>
									<br />
									<br />
								</li>
								<li>
								<b>Para Implanta&ccedil;&atilde;o</b>
								
								<br />
								<br />
								
								<p style="text-align:justify;">
									<span>
										A implanta&ccedil;&atilde;o do cliente na Tarifa Social estar&aacute; condicionada a an&aacute;lise e aprova&ccedil;&atilde;o do cadastro pela Compesa.
										<br />
										<br />
										Os Clientes residentes em im&oacute;vel com mais de uma economia estar&atilde;o condicionados &aacute; aprova&ccedil;&atilde;o de todos os cadastros do referido im&oacute;vel.
									</span>
								</p>
								<br />
								<br />
								</li>
								
								<li><b>Espec&iacute;ficas </b>
								<br />
								<br />
								
								
								<div id="lista" style="background-color: #e9e9e9;">
									<!-- Substituido por lista de criterios -->
									<ul style="" >
										<li>
											<span>O cliente que ultrapassar o consumo de água de 10 m³/mês (dez metros cúbicos mês), pagará o excedente com base no valor da Tarifa Normal da categoria Residencial;</span>
											<br />
											<br />
										</li>
										<li>
											<span>O cliente só poderá cadastrar um imóvel em seu nome, no caso, aquele no qual  reside;</span>
											<br />
											<br />
										</li>
										<li>
											<span>Fica vedado o cadastramento na Tarifa Social de imóvel na categoria Residencial, destinado a lazer (casa de praia, casa de campo etc.), terreno, igreja, chafariz e entidade de utilidade pública;</span>
											<br />
											<br />
										</li>
										<li>
											<span>Fica vedado o cadastramento na Tarifa Social de imóvel na categoria Residencial que esteja na situação de Demolido, Desocupado, Abandonado.</span>
											<br />
											<br />
										</li>
										
										<li>
											<span>Fica vedado ao cliente proprietário de mais de um imóvel, o direito ao benefício da Tarifa Social.</span>
											<br />
											<br />
										</li>

										<li>
											<span>O cliente cadastrado na Tarifa Social terá sua tarifa fixada, exclusivamente, para o fornecimento de água, sendo vedada a fixação específica de tarifa para remunerar os custos de coleta, transporte e tratamento de esgoto.</span>
											<br />
											<br />
										</li>
										
										<li>
											<span>No caso de cliente que atenda aos critérios cumulativos e não cumulativos previstos no subitem 3.1, poderá, excepcionalmente, ser autorizado o cadastramento na Tarifa Social , quando o consumo de energia elétrica ultrapassar os 80 kWh/mês (oitenta quilowatts hora mês), em virtude de utilização de equipamentos para tratamento de saúde.</span>
											<br />
											<br />
										</li>

									</ul>
									
								</div>								
								
							</ul>
							
							
							
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
					
							<font><span style="font-size: 11px;">Fonte:COMPESA/DAC/09-03-12 (RD 13/2010)</span></font>
						
							<p align="left">&nbsp;</p>
							<p align="left">&nbsp;</p>
								
							<div id="atualizacao" style="background-image: url(/gsan/imagens/portal/general/ultima_atualizacao.png);background-repeat: no-repeat;">
									<span style="position: absolute; padding-top: 7px;"> &Uacute;ltima atualiza&ccedil;&atilde;o (sexta, 9 de Março de 2012)</span>
							</div>							
						 	             
		       	</div><!-- Content - End -->
	       </div>
	       
	     
	    	 <%@ include file="/jsp/portal/rodape.jsp"%>
	    	 </html:form>
	  	</div><!-- Container - End -->  
	  	    
	</body>
</html:html>
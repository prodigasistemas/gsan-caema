<%@page import="gcom.gui.cadastro.atualizacaocadastral.GerarRoteiroDispositivoMovelActionForm"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>

<%@ page import="gcom.util.ConstantesSistema" %>
<%@ page import="gcom.cadastro.sistemaparametro.SistemaParametro"%>
<%@ page import="gcom.cadastro.atualizacaocadastral.bean.ImoveisRoteiroDispositivoMovelHelper"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>scroll_horizontal.js"></script>

<html:javascript staticJavascript="false"  formName="GerarRoteiroDispositivoMovelActionForm"/>

<script language="JavaScript">

	function validaForm() {
		var form = document.forms[0];

		if(validateGerarRoteiroDispositivoMovelActionForm(form)) {
			if(form.indicadorGerarRoteiroPeloGEO[0].checked && form.codigoSetorComercial.value == "-1"){
				alert('Informe Setor Comercial.');
			}else{
				if(verificarSituacaoImoveis()){
					enviarSelectMultiplo('GerarRoteiroDispositivoMovelActionForm','quadrasSelecionadas');			
					botaoAvancarTelaEspera('/gsan/exibirGerarRoteiroDispositivoMovelAction.do?pesquisar=sim');
				}
			}
		}
	}
	
	function confirmar(){
		var form = document.forms[0];
		
		if(validateGerarRoteiroDispositivoMovelActionForm(form)) {
			if(form.cadastrador.value != "-1"){
				botaoAvancarTelaEspera('/gsan/gerarRoteiroDispositivoMovelAction.do');
			}else{
				alert('Informe Cadastrador.');
			}
		}
	}
	
	function reload(campo){
		var form = document.forms[0];
		var url = 'exibirGerarRoteiroDispositivoMovelAction.do';
		
		if(campo == 'setorComercial'){
			url = 'exibirGerarRoteiroDispositivoMovelAction.do?apagarQuadrasSelecionadas=ok';
		}
		
		form.action = url;
		form.submit();
	}
	
	function validarQuadra(){
		var form = document.forms[0];
		var valido = true;
		
		if(form.numeroQuadraInicial.value != "-1" && form.numeroQuadraFinal.value == "-1"){
			alert('Informe Quadra Final.');
			valido = false;
		}else if(form.numeroQuadraInicial.value == "-1" && form.numeroQuadraFinal.value != "-1"){
			alert('Informe Quadra Inicial.');
			valido = false;
		}
		
		return valido;
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
	
	function MoveSelectedDataFromMenu1TO2(form, object, selectedObject){
		var form = document.forms[0];
		
		if (form.quadra.value != '-1') {
			MoverDadosSelectMenu1PARAMenu2(form, object, selectedObject);
		}
	}
	
	function MoveSelectedDataFromMenu2TO1(form, object, selectedObject){
		var form = document.forms[0];
		
		if (form.quadra.value != '-1') {
			MoverDadosSelectMenu2PARAMenu1(form, object, selectedObject);
		}
	}
	
	function mudouGeracaoRoteiro(){
		var form = document.forms[0];
		
		if (form.indicadorGerarRoteiroPeloGEO[1].checked) {
			
			form.codigoSetorComercial.value = -1;
			MoverTodosDadosSelectMenu2PARAMenu1('GerarRoteiroDispositivoMovelActionForm', 'quadra', 'quadrasSelecionadas');
			form.ligacaoAguaSituacao.value = -1;
			form.clienteUsuario[0].checked = false;
			form.clienteUsuario[1].checked = false;
			form.clienteUsuario[2].checked = true;
			form.indicadorSituacaoImovel[0].checked = false;
			form.indicadorSituacaoImovel[1].checked = false;
			form.indicadorSituacaoImovel[2].checked = true;
			
			form.codigoSetorComercial.disabled = true;
			form.quadra.disabled = true;
			form.quadrasSelecionadas.disabled = true;
			form.ligacaoAguaSituacao.disabled = true;
			form.clienteUsuario[0].disabled = true;
			form.clienteUsuario[1].disabled = true;
			form.clienteUsuario[2].disabled = true;
			form.indicadorSituacaoImovel[0].disabled = true;
			form.indicadorSituacaoImovel[1].disabled = true;
			form.indicadorSituacaoImovel[2].disabled = true;
			document.getElementById("botaoPesquisar").disabled = true;
			
		}else{
			form.codigoSetorComercial.disabled = false;
			form.quadra.disabled = false;
			form.quadrasSelecionadas.disabled = false;
			form.ligacaoAguaSituacao.disabled = false;
			form.clienteUsuario[0].disabled = false;
			form.clienteUsuario[1].disabled = false;
			form.clienteUsuario[2].disabled = false;
			form.indicadorSituacaoImovel[0].disabled = false;
			form.indicadorSituacaoImovel[1].disabled = false;
			form.indicadorSituacaoImovel[2].disabled = false;
			document.getElementById("botaoPesquisar").disabled = false;
		}
	}

	function verificarSituacaoImoveis(){
		var form = document.forms[0];

		if(form.indicadorSituacaoImovel[0].checked==true
				|| form.indicadorSituacaoImovel[1].checked==true
				|| form.indicadorSituacaoImovel[2].checked==true){
			return true;
		}else{
			alert("Informe a Situação dos Imóveis");
			return false;
		}
	}

</script>

</head>

<body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');OnDivScroll(document.forms[0].quadra, 6);OnDivScroll(document.forms[0].quadrasSelecionadas, 6);mudouGeracaoRoteiro();">

<div id="formDiv">
<html:form action="/exibirGerarRoteiroDispositivoMovelAction"
	name="GerarRoteiroDispositivoMovelActionForm"
	type="gcom.gui.cadastro.atualizacaocadastral.GerarRoteiroDispositivoMovelActionForm"
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
			
			<td width="620" valign="top" bgcolor="#003399" class="centercoltext">
				
				<table height="100%">
					<tr>
						<td></td>
					</tr>
				</table>
				
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
						<td class="parabg">Gerar Roteiro Dispositivo Móvel</td>
						<td width="11" valign="top">
							<img border="0" src="imagens/parahead_right.gif" />
						</td>
					</tr>
				</table>
				
				<p>&nbsp;</p>
				
				<table width="100%" border="0">
					<tr>
						<td colspan="2">Para gerar o roteiro de dispositivo móvel, informe os dados abaixo:</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td><strong>Empresa:<font color="#FF0000">*</font></strong></td>
						<td>
							<html:select property="idEmpresa" onchange="reload('empresa');" >
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<logic:present name="colecaoEmpresa" scope="request" >
									<html:options collection="colecaoEmpresa"
											  labelProperty="descricao" 
											  property="id" />
								</logic:present>
							</html:select>	
						</td>
					</tr>
					<tr>
						<td><strong>Localidades Liberadas:<font color="#FF0000">*</font></strong></td>
						<td>
							<html:select property="idLocalidade" onchange="reload('localidade');" >
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<logic:present name="colecaoLocalidade" scope="request" >
									<html:options collection="colecaoLocalidade"
											  labelProperty="descricao" 
											  property="id" />
								</logic:present>
							</html:select>	
						</td>
					</tr>					
					<tr>
						<td>
							<strong>Geração do Roteiro:<font color="#FF0000">*</font></strong>
						</td>
						<td>
							<strong> 
								<html:radio property="indicadorGerarRoteiroPeloGEO" value="2" onchange="mudouGeracaoRoteiro()" /> Filtros para Pesquisa 
								<html:radio property="indicadorGerarRoteiroPeloGEO" value="1" onchange="mudouGeracaoRoteiro()" /> Arquivos Gerados pelo GEO
							</strong>
						</td>
					</tr>	
	             	<tr>
	                	<td><strong>Setores Liberados:<font color="#FF0000">*</font></strong></td>
	                   	<td>
	                   		<html:select property="codigoSetorComercial" onchange="reload('setorComercial');" >
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<logic:present name="colecaoSetorComercial" scope="request" >
									<html:options collection="colecaoSetorComercial"
												labelProperty="descricaoFormatada"
												property="codigo" />
								</logic:present>
							</html:select>
	                   	</td>
	             	</tr>

					<tr>
						<td width="120">
							<strong>Quadras:</strong>
						</td>
						<td colspan="2">
						<table width="100%" border=0 cellpadding=0 cellspacing=0 align="left">
							<tr>
								<td width="90">
								
									<div align="left"><strong>Disponíveis</strong></div>
	
									<div id='disponiveis' style="OVERFLOW: auto;WIDTH: 100px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].quadra, 6);" >
									
										<html:select style="WIDTH: 80px;" property="quadra" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('disponiveis'), 6);">
											<logic:present name="colecaoQuadra" scope="session">
												<html:options collection="colecaoQuadra" 
													labelProperty="descricao" 
													property="numeroQuadra"/>
												</logic:present>
										</html:select>
										
									</div>
								</td>
	
								<td width="5" valign="middle"><br>
									<table width="50" align="center">
										<tr>
											<td align="center">
												<input type="button" 
													class="bottonRightCol"
													onclick="javascript:MoverTodosDadosSelectMenu1PARAMenu2ComMensagem('GerarRoteiroDispositivoMovelActionForm', 'quadra', 'quadrasSelecionadas', 'Lista sem quadra. Não é possível efetuar a transferência');
													OnDivScroll(document.forms[0].quadra, 6); OnDivScroll(document.forms[0].quadrasSelecionadas, 6);"
													value=" &gt;&gt; ">
											</td>
										</tr>
		
										<tr>
											<td align="center">
												<input type="button" 
													class="bottonRightCol"
													onclick="javascript:MoverDadosSelectMenu1PARAMenu2ComMensagem('GerarRoteiroDispositivoMovelActionForm', 'quadra', 'quadrasSelecionadas', 'Lista sem quadra. Não é possível efetuar a transferência', 'Selecione primeiro as quadras para depois efetuar a transferência');
													OnDivScroll(document.forms[0].quadra, 6); OnDivScroll(document.forms[0].quadrasSelecionadas, 6);"
													value=" &nbsp;&gt;  ">
											</td>
										</tr>
		
										<tr>
											<td align="center">
												<input type="button" 
													class="bottonRightCol"
													onclick="javascript:MoverDadosSelectMenu2PARAMenu1ComMensagem('GerarRoteiroDispositivoMovelActionForm', 'quadra', 'quadrasSelecionadas', 'Lista sem quadra. Não é possível efetuar a transferência', 'Selecione primeiro as quadras para depois efetuar a transferência');
													OnDivScroll(document.forms[0].quadra, 6); OnDivScroll(document.forms[0].quadrasSelecionadas, 6);"
													value=" &nbsp;&lt;  ">
											</td>
										</tr>
		
										<tr>
											<td align="center">
												<input type="button" 
													class="bottonRightCol"
													onclick="javascript:MoverTodosDadosSelectMenu2PARAMenu1ComMensagem('GerarRoteiroDispositivoMovelActionForm', 'quadra', 'quadrasSelecionadas', 'Lista sem quadra. Não é possível efetuar a transferência');
													OnDivScroll(document.forms[0].quadra, 6); OnDivScroll(document.forms[0].quadrasSelecionadas, 6);"
													value=" &lt;&lt; ">
											</td>
										</tr>
									</table>
								</td>
	
								<td>
									<div align="left">
										<strong>Selecionados</strong>
									</div>
									
									<div id='selecionados' style="OVERFLOW: auto;WIDTH: 190px;HEIGHT: 120px" onscroll="OnDivScroll(document.forms[0].quadrasSelecionadas, 6);" >		
										<html:select style="WIDTH: 80px;" property="quadrasSelecionadas" size="6" multiple="true" onfocus="OnSelectFocus(this, document.getElementById('selecionados'), 6);">
											<logic:present name="colecaoQuadrasSelecionadas" scope="session">
												<html:options collection="colecaoQuadrasSelecionadas"
													labelProperty="descricao" 
													property="numeroQuadra"/>
											</logic:present>
										</html:select>		
									</div>								
								</td>
							</tr>
						</table>
						</td>
					</tr>
	             	
	             	<tr>
		            	<td><strong>Situação da Ligação de Água:</strong></td>
		              	<td colspan="6"><span class="style2"><strong>
							<html:select property="ligacaoAguaSituacao" style="width: 350px; height:100px;" multiple="true" >
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<logic:present name="colecaoLigacaoAguaSituacao" scope="request" >
									<html:options collection="colecaoLigacaoAguaSituacao" labelProperty="descricao" property="id" />
								</logic:present>
							</html:select>
						</strong></span></td>	
		            </tr>
		            
		            <tr>
						<td>
							<strong>Cliente Usuário:<font color="#FF0000">*</font></strong>
						</td>
						<td>
							<strong> 
								<html:radio property="clienteUsuario" value="1" /> Com Documento Principal 
								<html:radio property="clienteUsuario" value="2" /> Sem Documento Principal 
								<html:radio property="clienteUsuario" value="3" /> Todos
							</strong>
						</td>
					</tr>
	             	<tr>
						<td>
							<strong>Situação dos Imóveis:<font color="#FF0000">*</font></strong>
						</td>
						<td>
							<strong> 
								<html:multibox property="indicadorSituacaoImovel" value="1" /> Atualizados 
								<html:multibox property="indicadorSituacaoImovel" value="2" /> Não Atualizados 
								<html:multibox property="indicadorSituacaoImovel" value="3" /> Retornar para Campo
							</strong>
						</td>
					</tr>
	             	<tr>
	             		<td></td>
	             		<td align="right">
	             			<input 
	             				id="botaoPesquisar"
	             				name="Button" 
								type="button" 
								class="bottonRightCol" 
								value="Pesquisar" 
								align="right"
								onclick="javascript:validaForm();">
	             		</td> 
	             	</tr>
	             	
	             	<tr>
	             		<td colspan="3"><hr /></td>
	             	</tr>
	             	
	             	<tr>
	             		<td><strong>Cadastrador:<font color="#FF0000">*</font></strong></td>
	             		<td>
	             			<html:select property="cadastrador" >
								<html:option value="<%=""+ConstantesSistema.NUMERO_NAO_INFORMADO%>">&nbsp;</html:option>
								<logic:present name="colecaoLeiturista" scope="request" >
									<html:options collection="colecaoLeiturista"
											  labelProperty="descricao" 
											  property="id" />
								</logic:present>
							</html:select>	
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
									<td align="right">
										<strong>Total de Matrículas:</strong>&nbsp;&nbsp;
										<html:text property="totalMatriculas" size="5" maxlength="5" readonly="true" />
									</td>
								</tr>
							</table>
							
							<table width="100%" border="0" bordercolor="#79bbfd">
								<tr>
									<td>
										<div style="width: 100%; height: 100; overflow: auto;">
											<table width="100%" bgcolor="#99CCFF" border="0">
												<tr bordercolor="#000000">
													<td width="50" bordercolor="#000000" bgcolor="#90c7fc" align="center">
														<div align="center"><strong><a href="javascript:facilitador(this);" id="0">Todos</a></strong></div>
													</td>
													<td width="100" bordercolor="#000000" bgcolor="#90c7fc" align="center">
														<div align="center"><strong>Matrícula</strong></div>
												   	</td>
												   	<td width="70" bordercolor="#000000" bgcolor="#90c7fc" align="center">
														<div align="center"><strong>Setor</strong></div>
												   	</td>
												   	<td width="70" bordercolor="#000000" bgcolor="#90c7fc" align="center">
														<div align="center"><strong>Quadra</strong></div>
												   	</td>
												   	<td width="70" bordercolor="#000000" bgcolor="#90c7fc" align="center">
														<div align="center"><strong>Lote</strong></div>
												   	</td>
												   	<td width="70" bordercolor="#000000" bgcolor="#90c7fc" align="center">
														<div align="center"><strong>Sublote</strong></div>
												   	</td>
												   	<td width="100" bordercolor="#000000" bgcolor="#90c7fc" align="center">
														<div align="center"><strong>Rota</strong></div>
												   	</td>
												</tr>
												
												<logic:present name="colecaoImoveis" scope="request">
									  				<%int cont = 0;%>
                      								<logic:iterate name="colecaoImoveis" id="imovel" scope="request" type="ImoveisRoteiroDispositivoMovelHelper">								
									  					<%cont = cont + 1;
									  					if (cont % 2 == 0) {%>
								                       	<tr bgcolor="#cbe5fe">
								                        <%} else {%>
								                        <tr bgcolor="#FFFFFF">
								                        <%}%>
															<td width="8%" align="center">
								                            	<html:checkbox property="idsRegistros" value="${imovel.idImovelFormatado}"/>	
								                            </td>
								                            <td width="16%" align="center"> 
								                            	<bean:write name="imovel" property="matriculaFormatada"/>
								                            </td>
								                            <td width="12%" align="center"> 
								                            	<bean:write name="imovel" property="setor"/>
								                            </td>
								                            <td width="12%" align="center"> 
								                            	<bean:write name="imovel" property="quadra"/>
								                            </td>
								                            <td width="12%" align="center"> 
								                            	<bean:write name="imovel" property="lote"/>
								                            </td>
								                            <td width="12%" align="center"> 
								                            	<bean:write name="imovel" property="subLote"/>
								                            </td>
															<td width="12%" align="center"> 
								                            	<bean:write name="imovel" property="rota"/>
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
														onclick="window.location.href='<html:rewrite page="/exibirGerarRoteiroDispositivoMovelAction.do?menu=sim"/>'">
													
													<input name="Button" 
														type="button" 
														class="bottonRightCol" 
														value="Cancelar" 
														align="left"
														onclick="javascript:window.location.href='/gsan/telaPrincipal.do'">
												</td>
												
												<td align="right" >
													<input name="Button" 
														type="button" 
														class="bottonRightCol" 
														value="Atualizar" 
														onclick="javascript:confirmar();">
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
</html:form>
</div>
	<%@ include file="/jsp/util/telaespera.jsp"%>
</body>
</html:html>

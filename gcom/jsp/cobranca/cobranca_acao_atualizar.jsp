<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/ManutencaoRegistro.js"></script>
	
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>
	
<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script><html:javascript staticJavascript="false"  formName="AcaoCobrancaAtualizarActionForm" dynamicJavascript="false" />

<logic:present scope="request" name="exibirTextoPersonalizado">

	<script language="JavaScript">
	
		 var bCancel = false;
	
	    function validateAcaoCobrancaAtualizarActionForm(form) {
	        if (bCancel)
	      return true;
	        else
	      return validateRequired(form) && validateCaracterEspecial(form) && validateLong(form) 
			 && validaTodosRadioButton() && validarNumeroDiasEntreAcoes(form); 
	   }
	
	    function caracteresespeciais () {
	     this.aa = new Array("descricaoAcao", "Descri��o da A��o de Cobran�a possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	     this.ab = new Array("idCobrancaCriterio", "Crit�rio de Cobran�a possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	     this.ac = new Array("idServicoTipo", "Tipo de Servi�o da Ordem de Servi�o a ser Gerada possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	     
	    }
	    
	    function IntegerValidations () {
	         this.aa = new Array("numeroDiasValidade", "N�mero de Dias de Validade da A��o deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	         this.aa = new Array("numeroDiasEntreAcoes", "N�mero de Dias entre a A��o e sua Predecessora deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	         this.ac = new Array("ordemCronograma", "Ordem no Cronograma deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	         this.ad = new Array("numeroDiasVencimento", "N�mero de Dias de Vencimento deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	         this.ae = new Array("idServicoTipo", "Tipo de Servi�o da Ordem de Servi�o a ser Gerada deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	         this.af = new Array("idCobrancaCriterio", "Crit�rio de Cobran�a deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	         this.ag = new Array("numeroDiasRemuneracaoTerceiro", "Limite de Dias para Remunera��o de Terceiros deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	        }
	   
	        function required () {
	        this.aa = new Array("descricaoAcao", "Informe Descri��o da A��o de Cobran�a.", new Function ("varName", " return this[varName];"));
	        this.ab = new Array("numeroDiasValidade", "Informe N�mero de Dias de Validade da A��o.", new Function ("varName", " return this[varName];"));
	        this.ac = new Array("idTipoDocumentoGerado", "Informe Tipo de Documento a ser Gerado.", new Function ("varName", " return this[varName];"));
	        this.ad = new Array("textoPersonalizado", "Informe Texto personalizado.", new Function ("varName", " return this[varName];"));
	        this.ae = new Array("ordemCronograma", "Informe Ordem no Cronograma.", new Function ("varName", " return this[varName];"));
	        this.af = new Array("idCobrancaCriterio", "Informe Crit�rio de Cobran�a.", new Function ("varName", " return this[varName];"));
	       
	        }
	   
	    </script>

	</logic:present>

	<logic:notPresent scope="request" name="exibirTextoPersonalizado">

	    <script language="JavaScript">
	   
	         var bCancel = false;

	        function validateAcaoCobrancaAtualizarActionForm(form) {
	            if (bCancel)
	          return true;
	            else
	          return validateRequired(form) && validateCaracterEspecial(form) && validateLong(form)
	             && validaTodosRadioButton() && validarNumeroDiasEntreAcoes(form);
	       }
	   
	        function caracteresespeciais () {
	         this.aa = new Array("descricaoAcao", "Descri��o da A��o de Cobran�a possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	         this.ab = new Array("idCobrancaCriterio", "Crit�rio de Cobran�a possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	         this.ac = new Array("idServicoTipo", "Tipo de Servi�o da Ordem de Servi�o a ser Gerada possui caracteres especiais.", new Function ("varName", " return this[varName];"));
	        
	        }
	       
	         function IntegerValidations () {
	         this.aa = new Array("numeroDiasValidade", "N�mero de Dias de Validade da A��o deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	         this.ab = new Array("numeroDiasEntreAcoes", "N�mero de Dias entre a A��o e sua Predecessora deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	         this.ac = new Array("ordemCronograma", "Ordem no Cronograma deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	         this.ad = new Array("numeroDiasVencimento", "N�mero de Dias de Vencimento deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	         this.ae = new Array("idServicoTipo", "Tipo de Servi�o da Ordem de Servi�o a ser Gerada deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	         this.af = new Array("idCobrancaCriterio", "Crit�rio de Cobran�a deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	         this.ag = new Array("numeroDiasRemuneracaoTerceiro", "Limite de Dias para Remunera��o de Terceiros deve somente conter n�meros positivos.", new Function ("varName", " return this[varName];"));
	        }
	   
	        function required () {
	        this.aa = new Array("descricaoAcao", "Informe Descri��o da A��o de Cobran�a.", new Function ("varName", " return this[varName];"));
	        this.ab = new Array("numeroDiasValidade", "Informe N�mero de Dias de Validade da A��o.", new Function ("varName", " return this[varName];"));
	        this.ac = new Array("idTipoDocumentoGerado", "Informe Tipo de Documento a ser Gerado.", new Function ("varName", " return this[varName];"));
	        this.af = new Array("idCobrancaCriterio", "Informe Crit�rio de Cobran�a.", new Function ("varName", " return this[varName];"));
	        this.ad = new Array("ordemCronograma", "Informe Ordem no Cronograma.", new Function ("varName", " return this[varName];"));
	        }
	   
	    </script>

	</logic:notPresent>

	<script language="JavaScript">
	   
	function validaRadioButton(nomeCampo,mensagem){
	    var alerta = "";
	    var int = 0;
	    if(!nomeCampo[0].checked && !nomeCampo[1].checked){
	        alerta =  "Informe " + mensagem +".";
	    }
	    return alerta;
	}
	function validaTodosRadioButton(){
	    var form = document.forms[0];
	    var mensagem = "";
	    if(validaRadioButton(form.icCompoeCronograma,"Comp�e o Cronograma") != ""){
	            mensagem = mensagem + validaRadioButton(form.icCompoeCronograma,"Comp�e o Cronograma")+"\n";
	    }
	    if(validaRadioButton(form.icAcaoObrigatoria,"A��o Obrigat�ria") != ""){
	        mensagem = mensagem + validaRadioButton(form.icAcaoObrigatoria,"A��o Obrigat�ria")+"\n";
	    }
	    if(validaRadioButton(form.icRepetidaCiclo,"Pode ser Repetida no Ciclo") != ""){
	        mensagem = mensagem + validaRadioButton(form.icRepetidaCiclo,"Pode ser Repetida no Ciclo")+"\n";
	    }
	    if(validaRadioButton(form.icSuspensaoAbastecimento,"Provoca Suspens�o de Abastecimento") != ""){
	        mensagem = mensagem + validaRadioButton(form.icSuspensaoAbastecimento,"Provoca Suspens�o de Abastecimento")+"\n";
	    }
	    if(validaRadioButton(form.icDebitosACobrar,"Considera D�bitos a Cobrar") != ""){
	        mensagem = mensagem + validaRadioButton(form.icDebitosACobrar,"Considera D�bitos a Cobrar")+"\n";
	    }
	    if(validaRadioButton(form.icAcrescimosImpontualidade,"Considera Acr�scimos por Impontualidade") != ""){
	        mensagem = mensagem + validaRadioButton(form.icAcrescimosImpontualidade,"Considera Acr�scimos por Impontualidade")+"\n";
	    }
	    if(validaRadioButton(form.icGeraTaxa,"Gera Taxa") != ""){
	        mensagem = mensagem + validaRadioButton(form.icGeraTaxa,"Gera Taxa")+"\n";
	    }
	    if(validaRadioButton(form.icEmitirBoletimCadastro,"Pode Emitir Boletins de Cadastro") != ""){
	        mensagem = mensagem + validaRadioButton(form.icEmitirBoletimCadastro,"Pode Emitir Boletins de Cadastro")+"\n";
	    }
	    if(validaRadioButton(form.icImoveisSemDebitos,"Pode ser Executada para Im�veis sem D�bito") != ""){
	        mensagem = mensagem + validaRadioButton(form.icImoveisSemDebitos,"Pode ser Executada para Im�veis sem D�bito")+"\n";
	    }
	    if(validaRadioButton(form.icMetasCronograma,"Usa Metas no Cronograma") != ""){
	        mensagem = mensagem + validaRadioButton(form.icMetasCronograma,"Usa Metas no Cronograma")+"\n";
	    }
	    if(validaRadioButton(form.icOrdenamentoCronograma,"Usa Ordenamento: No Cronograma") != ""){
	        mensagem = mensagem + validaRadioButton(form.icMetasCronograma,"Usa Ordenamento: No Cronograma")+"\n";
	    }
	    if(validaRadioButton(form.icOrdenamentoEventual,"Usa Ordenamento: Nas Eventuais") != ""){
	        mensagem = mensagem + validaRadioButton(form.icOrdenamentoEventual,"Usa Ordenamento: Nas Evetuais")+"\n";
	    }
	    if(validaRadioButton(form.icDebitoInterfereAcao,"Situa��o do D�bito Interfere na Situa��o da A��o") != ""){
	        mensagem = mensagem + validaRadioButton(form.icDebitoInterfereAcao,"Situa��o do D�bito Interfere na Situa��o da A��o")+"\n";
	    }
	    if(validaRadioButton(form.icOrdenarMaiorValor,"Sele��o ordenando por valor decrescente") != ""){
	        mensagem = mensagem + validaRadioButton(form.icOrdenarMaiorValor,"Sele��o ordenando por valor decrescente")+"\n";
	    }
	    if(validaRadioButton(form.icValidarItem,"Validar por item cobrado") != ""){
	        mensagem = mensagem + validaRadioButton(form.icValidarItem,"Validar por item cobrado")+"\n";
	    }
	    if(validaRadioButton(form.icEfetuarAcaoCpfCnpjValido,"Efetuar A��o para CPF/CNPJ V�lido") != ""){
	        mensagem = mensagem + validaRadioButton(form.icEfetuarAcaoCpfCnpjValido,"Efetuar A��o para CPF/CNPJ V�lido")+"\n";
	    }
	   
	    if(mensagem != ""){
	        alert(mensagem);
	        return false;
	    }else{
	        return true;
	    }
	}

	   

	 
	function validarForm(form){

	if(validateAcaoCobrancaAtualizarActionForm(form)){
	    submeterFormPadrao(form);
	}
	}


	function desfazer(){
	 var form = document.forms[0];
	 form.action = '\exibirAtualizarAcaoCobrancaAction.do?limpar=1';
	 form.submit();
	}

	 function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

	    var form = document.forms[0];

	    if (tipoConsulta == 'criterioCobranca') {
	       form.idCobrancaCriterio.value = codigoRegistro;
	       form.descricaoCobrancaCriterio.value = descricaoRegistro;
	        form.descricaoCobrancaCriterio.style.color = "#000000";
	    }
	   
	    if (tipoConsulta == 'tipoServico') {
	          form.idServicoTipo.value = codigoRegistro;
	         form.descricaoServicoTipo.value = descricaoRegistro;
	        form.descricaoServicoTipo.style.color = "#000000";
	    }
	   
	   
	  }

	function habilitaNumeroDiasAcaoPredecessora(){
	 var form = document.forms[0];
	 if(form.idAcaoPredecessora.value != ""){
	   form.numeroDiasEntreAcoes.disabled = false;
	 }else{
	  form.numeroDiasEntreAcoes.value ='';
	  form.numeroDiasEntreAcoes.disabled = true;
	 }
	}

	function validarNumeroDiasEntreAcoes(form){
	   var retorno = true;
	    if(form.idAcaoPredecessora.value != ''){
	     if(form.numeroDiasEntreAcoes.value == ''){
	      alert('Informe N�mero de Dias entre a A��o e sua Predecessora');
	      retorno = false;
	     }
	    }
	    return retorno;
	}


	function limparCriterio(){
	 var form = document.forms[0];
	 form.idCobrancaCriterio.value = '';
	 form.descricaoCobrancaCriterio.value = '';
	}


	function limparServicoTipo(){
	 var form = document.forms[0];
	  form.idServicoTipo.value = '';
	 form.descricaoServicoTipo.value = '';
	}

	function limparForm(){
	    var form = document.forms[0];
	      form.descricaoAcao.value = '';
	      form.numeroDiasValidade.value = '';
	      form.numeroDiasEntreAcoes.value = '';
	      form.numeroDiasVencimento.value = '';
	      form.idTipoDocumentoGerado.value = '';
	      form.idTipoDocumentoGerado.value = '';
	      form.idTipoDocumentoGerado.value = '';
	      form.idSituacaoLigacaoEsgoto.value = '';
	      form.idServicoTipo.value = '';
	     form.descricaoServicoTipo.value = '';
	    form.idCobrancaCriterio.value = '';
	    form.descricaoCobrancaCriterio.value = '';
	    form.idAcaoPredecessora.value = "";
	    form.idCobrancaCriterio.value = '';
	    form.idServicoTipo.value = '';
	    form.ordemCronograma.value = '';

	    form.icEfetuarAcaoCpfCnpjValido[0].checked = false;
	    form.icEfetuarAcaoCpfCnpjValido[1].checked = false;
	   
	    form.textoPersonalizado.value = '';

	}


	</script>

	</head>

	<logic:present scope="request" name="exibirTextoPersonalizado">

	    <body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');habilitaNumeroDiasAcaoPredecessora();
	    limitTextArea(document.forms[0].textoPersonalizado, 500, document.getElementById('utilizado'), document.getElementById('limite'));">
	   
	</logic:present>

	<logic:notPresent scope="request" name="exibirTextoPersonalizado">

	    <body leftmargin="5" topmargin="5" onload="setarFoco('${requestScope.nomeCampo}');habilitaNumeroDiasAcaoPredecessora();">
	   
	</logic:notPresent>

	<html:form action="/atualizarAcaoCobrancaAction"
	    name="AcaoCobrancaAtualizarActionForm"
	    type="gcom.gui.cobranca.AcaoCobrancaAtualizarActionForm"
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
	            <table width="100%" border="0" align="center" cellpadding="0"
	                cellspacing="0">
	                <tr>
	                    <td width="11"><img border="0"
	                        src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
	                    <td class="parabg">Atualizar A��o de Cobran�a</td>

	                    <td width="11"><img border="0"
	                        src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
	                </tr>
	                <tr>
	                    <td height="5" colspan="3"></td>
	                </tr>
	            </table>
	            <table width="100%" border="0">
	                <tr>
	                    <td colspan="2">
	                    <p>Para atualizar a a��o de cobran�a, informe os dados abaixo:</p>
	                    <p>&nbsp;</p>
	                    </td>
	                </tr>
	                <tr>
	                    <td width="25%"><strong>Descri��o da A��o de Cobran�a:<font color="#FF0000">*</font></strong></td>
	                    <td><html:text property="descricaoAcao"    size="40" maxlength="40" tabindex="1"/></td>
	                </tr>
	                <tr>
	                    <td><strong>N�mero de Dias de Validade da A��o:<font color="#FF0000">*</font></strong></td>
	                     <td width="25%">
	                         <html:text maxlength="5"
	                                    property="numeroDiasValidade"
	                                    size="5"
	                                    tabindex="2"
	                                    onkeypress="return isCampoNumerico(event);"/>
	                     </td>
	              </tr>
	              <tr>
	                <td><strong>A��o Predecessora:</strong></td>
	                <td width="25%">
	                    <html:select property="idAcaoPredecessora" tabindex="3" onchange="habilitaNumeroDiasAcaoPredecessora();">
	                            <html:option value="">&nbsp;</html:option>
	                            <html:options collection="colecaoAcaoPredecessora" labelProperty="descricaoCobrancaAcao" property="id" />
	                    </html:select>
	                </td>
	              </tr>
	             
	               <tr>
	                      <td><strong>N�mero de Dias entre a A��o e sua Predecessora:</strong></td>
	                     <td>
	                              <html:text property="numeroDiasEntreAcoes"
	                                       size="5"
	                                       maxlength="5"
	                                       tabindex="5"
	                                           onkeypress="return isCampoNumerico(event);"/>
	                        </td>
	             </tr>
	             <tr>
	                      <td><strong>N�mero de Dias de Vencimento:</strong></td>
	                     <td>
	                              <html:text property="numeroDiasVencimento"
	                                         size="5"
	                                         maxlength="5"
	                                         tabindex="5"
	                                         onkeypress="return isCampoNumerico(event);"/>
	                        </td>
	             </tr>
	            
	             <tr>
	                <td><strong>Tipo de Documento a ser Gerado:<font color="#FF0000">*</font></strong></td>
	                <td width="25%">
	                    <html:select property="idTipoDocumentoGerado" tabindex="5" onchange="redirecionarSubmit('exibirAtualizarAcaoCobrancaAction.do?objetoConsulta=1');">
	                            <html:option value="">&nbsp;</html:option>
	                            <html:options collection="colecaoDocumentoTipo" labelProperty="descricaoDocumentoTipo" property="id" />
	                    </html:select>
	                </td>
	              </tr>
	             
	              <logic:present scope="request" name="exibirTextoPersonalizado">
	                 
	                  <tr>
	                    <td><strong>Texto personalizado:<font color="#FF0000">*</font></strong></td>
	                    <td width="25%">
	                        <html:textarea property="textoPersonalizado" cols="60" rows="5" onkeyup="limitTextArea(document.forms[0].textoPersonalizado, 500, document.getElementById('utilizado'), document.getElementById('limite'));"/><br>
	                        <strong><span id="utilizado">0</span>/<span id="limite">500</span></strong>
	                    </td>
	                  </tr>
	                 
	              </logic:present>
	             
	              <tr>
	                <td><strong>Situa��o da Liga��o de �gua dos Im�veis:</strong></td>
	                <td width="25%">
	                    <html:select property="idSituacaoLigacaoAgua" tabindex="6">
	                            <html:option value="">&nbsp;</html:option>
	                            <html:options collection="colecaoLigacaoAguaSituacao" labelProperty="descricao" property="id" />
	                    </html:select>
	                </td>
	              </tr>
	              <tr>
	                <td><strong>Situa��o da Liga��o de Esgoto dos Im�veis:</strong></td>
	                <td width="25%">
	                    <html:select property="idSituacaoLigacaoEsgoto" tabindex="7">
	                            <html:option value="">&nbsp;</html:option>
	                            <html:options collection="colecaoLigacaoEsgotoSituacao" labelProperty="descricao" property="id" />
	                    </html:select>
	                </td>
	              </tr> 
	              <tr>
	                <td><strong>Crit�rio de Cobran�a:<font color="#FF0000">*</font></strong></td>
	                <td>
	                   <html:text maxlength="4" property="idCobrancaCriterio" size="4" tabindex="8"
	                     onkeypress="javascript:validaEnterComMensagem(event, 'exibirAtualizarAcaoCobrancaAction.do?objetoConsulta=1', 'idCobrancaCriterio', 'Crit�rio de Cobran�a');document.forms[0].descricaoCobrancaCriterio.value = '';" />
	                        <a href="javascript:abrirPopup('exibirPesquisarCriterioCobrancaAction.do',400,600);">
	                            <img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
	                                title="Pesquisar Crit�rio de Cobran�a" /></a>
	                        <logic:notEmpty name="AcaoCobrancaAtualizarActionForm" property="idCobrancaCriterio">
	                            <html:text property="descricaoCobrancaCriterio" size="36" maxlength="36" readonly="true"
	                                    style="background-color:#EFEFEF; border:0; color: #000000" />
	                        </logic:notEmpty>
	                        <logic:empty name="AcaoCobrancaAtualizarActionForm" property="idCobrancaCriterio">
	                         <html:text property="descricaoCobrancaCriterio" size="36" maxlength="36" readonly="true"
	                                    style="background-color:#EFEFEF; border:0; color: #ff0000" />
	                        </logic:empty>
	                        <a href="javascript:limparCriterio();">
	                            <img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
	                            border="0" title="Apagar" /></a>
	                </td>
	              </tr>
	              <tr>
	                <td><strong>Tipo de Servi�o da Ordem de Servi�o a ser Gerada:</strong></td>
	                <td>
	                   <html:text maxlength="4" property="idServicoTipo" size="4" tabindex="9"
	                     onkeypress="javascript:validaEnterComMensagem(event, 'exibirAtualizarAcaoCobrancaAction.do?objetoConsulta=1', 'idServicoTipo', 'Tipo de Servi�o');document.forms[0].descricaoServicoTipo.value = '';" />
	                        <a href="javascript:abrirPopup('exibirPesquisarTipoServicoAction.do',400,600);">
	                            <img width="23" height="21" border="0" src="<bean:message key="caminho.imagens"/>pesquisa.gif"
	                                title="Pesquisar Tipo de Servi�o" /></a>
	                        <logic:notEmpty name="AcaoCobrancaAtualizarActionForm" property="idServicoTipo">
	                            <html:text property="descricaoServicoTipo" size="36" maxlength="36" readonly="true"
	                                    style="background-color:#EFEFEF; border:0; color: #000000" />
	                        </logic:notEmpty>
	                        <logic:empty name="AcaoCobrancaAtualizarActionForm" property="idServicoTipo">
	                         <html:text property="descricaoServicoTipo" size="36" maxlength="36" readonly="true"
	                                    style="background-color:#EFEFEF; border:0; color: #ff0000" />
	                        </logic:empty>
	                        <a href="javascript:limparServicoTipo();">
	                            <img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
	                            border="0" title="Apagar" /></a>
	                </td>
	              </tr>
	              <tr>
	                   <td><strong>Ordem no Cronograma:<font color="#FF0000">*</font></strong></td>
	                   <td>
	                        <html:text property="ordemCronograma"
	                                   size="4"
	                                   maxlength="4"
	                                   tabindex="4"
	                                   onkeypress="return isCampoNumerico(event);"/>
	                        </td>
	             </tr>
	                
	               <tr>
	                <td height="24" colspan="2"></td>
	              </tr>
	          
	              <tr>
	                <td colspan="2">
	                <table width="100%">
	                 <tr>
	                  <td width="45%"><strong>Comp�e o Cronograma:<font color="#FF0000">*</font></strong></td>
	                  <td><html:radio property="icCompoeCronograma" value="1" tabindex="10"/>
	                    <strong>Sim</strong></td>
	                  <td><html:radio property="icCompoeCronograma" value="2" tabindex="11"/>
	                      <strong>N&atilde;o</strong></td>
	                 </tr>
	                 <tr>
	                  <td width="45%"><strong>A��o Obrigat�ria:<font color="#FF0000"></font><font color="#FF0000">*</font></strong></td>
	                  <td><html:radio property="icAcaoObrigatoria" value="1" tabindex="12"/>
	                      <strong>Sim</strong></td>
	                  <td><html:radio property="icAcaoObrigatoria" value="2" tabindex="13"/>
	                      <strong>N&atilde;o</strong></td>
	                 </tr>
	                 <tr>
	                  <td width="45%"><strong>Pode ser Repetida no Ciclo:<font color="#FF0000"></font><font color="#FF0000">*</font></strong></td>
	                  <td><html:radio property="icRepetidaCiclo" value="1" tabindex="14"/>
	                      <strong>Sim</strong></td>
	                  <td><html:radio property="icRepetidaCiclo" value="2" tabindex="15"/>
	                      <strong>N&atilde;o</strong></td>
	                 </tr>
	                 <tr>
	                  <td width="45%"><strong>Provoca Suspens�o de Abastecimento:<font color="#FF0000"></font><font color="#FF0000">*</font></strong></td>
	                  <td><html:radio property="icSuspensaoAbastecimento" value="1" tabindex="16"/>
	                      <strong>Sim</strong></td>
	                  <td><html:radio property="icSuspensaoAbastecimento" value="2" tabindex="17"/>
	                      <strong>N&atilde;o</strong></td>
	                 </tr>
	                 <tr>
	                  <td width="45%"><strong>Considera D�bitos a Cobrar:<font color="#FF0000">*</font></strong></td>
	                  <td><html:radio property="icDebitosACobrar" value="1" tabindex="18"/>
	                      <strong>Sim</strong></td>
	                  <td><html:radio property="icDebitosACobrar" value="2" tabindex="19"/>
	                      <strong>N&atilde;o</strong></td>
	                 </tr>
	                 <tr>
	                  <td width="45%"><strong>Considera Cr�ditos a Realizar:</strong></td>
	                  <td><html:radio property="icCreditosARealizar" value="1" tabindex="21"/>
	                      <strong>Sim</strong></td>
	                  <td><html:radio property="icCreditosARealizar" value="2" tabindex="22"/>
	                      <strong>N&atilde;o</strong></td>
	                 </tr>
	                 <tr>
	                  <td width="45%"><strong>Considera Notas Promiss�ria:</strong></td>
	                  <td><html:radio property="icNotasPromissoria" value="1" tabindex="23"/>
	                      <strong>Sim</strong></td>
	                  <td><html:radio property="icNotasPromissoria" value="2" tabindex="24" />
	                      <strong>N&atilde;o</strong></td>
	                 </tr> 
	                 <tr>
	                  <td width="45%"><strong>Considera Acr�scimos por Impontualidade:<font color="#FF0000">*</font></strong></td>

	                  <td><html:radio property="icAcrescimosImpontualidade" value="1" tabindex="20"/>
	                      <strong>Sim</strong></td>
	                  <td><html:radio property="icAcrescimosImpontualidade" value="2" tabindex="21"/>
	                      <strong>N&atilde;o</strong></td>
	                 </tr>
	                 <tr>
	                  <td width="45%"><strong>Gera Taxa:<font color="#FF0000">*</font></strong></td>

	                  <td><html:radio property="icGeraTaxa" value="1" tabindex="17"/>
	                      <strong>Sim</strong></td>
	                  <td><html:radio property="icGeraTaxa" value="2" tabindex="18"/>
	                      <strong>N&atilde;o</strong></td>
	                 </tr>
	                 <tr>
	                  <td width="45%"><strong>Pode Emitir Boletins de Cadastro:<font color="#FF0000">*</font></strong></td>

	                  <td><html:radio property="icEmitirBoletimCadastro" value="1" tabindex="22"/>
	                      <strong>Sim</strong></td>
	                  <td><html:radio property="icEmitirBoletimCadastro" value="2" tabindex="23"/>
	                      <strong>N&atilde;o</strong></td>
	                 </tr>
	                 <tr>
	                  <td width="45%"><strong>Pode ser Executada para Im�veis sem D�bito:<font color="#FF0000">*</font></strong></td>

	                  <td><html:radio property="icImoveisSemDebitos" value="1" tabindex="24"/>
	                      <strong>Sim</strong></td>
	                  <td><html:radio property="icImoveisSemDebitos" value="2" tabindex="25"/>
	                      <strong>N&atilde;o</strong></td>
	                 </tr>
	                 <tr>
	                     <td width="45%"><strong>Usa Metas no Cronograma:<font color="#FF0000">*</font></strong></td>
	   
	                      <td><html:radio property="icMetasCronograma" value="1" tabindex="29"/>
	                          <strong>Sim</strong></td>
	                      <td><html:radio property="icMetasCronograma" value="2" tabindex="30"/>
	                          <strong>N&atilde;o</strong></td>
	                 </tr>
	                 <tr>
	                       <td><strong>Usa Ordenamento:</strong></td>
	                 </tr>
	                 <tr>
	                       
	                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                            <strong>No Cronograma:<font color="#FF0000">*</font></strong></td>
	                        <td><html:radio property="icOrdenamentoCronograma" value="1" tabindex="31"/>
	                             <strong>Sim</strong></td>
	                        <td><html:radio property="icOrdenamentoCronograma" value="2" tabindex="32"/>
	                             <strong>N&atilde;o</strong></td>
	                </tr>
	                 <tr>
	                       
	                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                            <strong>Nas Eventuais:<font color="#FF0000">*</font></strong></td>
	                        <td><html:radio property="icOrdenamentoEventual" value="1" tabindex="33"/>
	                              <strong>Sim</strong></td>
	                         <td><html:radio property="icOrdenamentoEventual" value="2" tabindex="34"/>
	                              <strong>N&atilde;o</strong></td>
	                </tr>
	                <tr>
	                     <td width="45%"><strong>Situa&ccedil;&atilde;o do D&eacute;bito Interfere na Situa&ccedil;&atilde;o da A&ccedil;&atilde;o:<font color="#FF0000">*</font></strong></td>
	   
	                      <td><html:radio property="icDebitoInterfereAcao" value="1" tabindex="35"/>
	                          <strong>Sim</strong></td>
	                      <td><html:radio property="icDebitoInterfereAcao" value="2" tabindex="36"/>
	                          <strong>N&atilde;o</strong></td>
	                 </tr>
	                 <tr>
	                     <td><strong>Limite de Dias para Remunera&ccedil;&atilde;o de Terceiros:</strong></td>
	                     <td>
	                              <html:text property="numeroDiasRemuneracaoTerceiro" size="4"
	                                maxlength="8" tabindex="37"/>
	                        </td>
	                 </tr>
	                
	                <tr>
	                     <td width="45%"><strong>Sele��o ordenando por valor decrescente:<font color="#FF0000">*</font></strong></td>
	   
	                      <td><html:radio property="icOrdenarMaiorValor" value="1" tabindex="38"/>
	                          <strong>Sim</strong></td>
	                      <td><html:radio property="icOrdenarMaiorValor" value="2" tabindex="39"/>
	                          <strong>N&atilde;o</strong></td>
	                 </tr>
	                <tr>
	                     <td width="45%"><strong>Validar por item cobrado:<font color="#FF0000">*</font></strong></td>
	   
	                      <td><html:radio property="icValidarItem" value="1" tabindex="40"/>
	                          <strong>Sim</strong></td>
	                      <td><html:radio property="icValidarItem" value="2" tabindex="41"/>
	                          <strong>N&atilde;o</strong></td>
	                 </tr>
	                
	                 <tr>
	                     <td width="45%"><strong>Efetuar A��o para CPF/CNPJ V�lido:<font color="#FF0000">*</font></strong></td>
	   
	                      <td><html:radio property="icEfetuarAcaoCpfCnpjValido" value="1" tabindex="39"/>
	                          <strong>Sim</strong></td>
	                      <td><html:radio property="icEfetuarAcaoCpfCnpjValido" value="2" tabindex="40"/>
	                          <strong>N&atilde;o</strong></td>
	                 </tr>
	                
	                 </table>
	                </td>
	               </tr> 
	               <tr>
	                <td height="24" colspan="2"></td>
	               </tr>
	               <tr>
	                <td colspan="2">
	                    <table width="100%" border="0">
	                        <tr>
	                            <td valign="top">
	                        <logic:present name="voltar">
	                        <logic:equal name="voltar" value="filtrar">
	                            <input name="Button" type="button" class="bottonRightCol"
	                            value="Voltar" align="left"
	                            onclick="document.forms[0].target='';window.location.href='<html:rewrite page="/exibirFiltrarAcaoCobrancaAction.do"/>'">
	                        </logic:equal>
	                        <logic:equal name="voltar" value="manter">
	                            <input name="Button" type="button" class="bottonRightCol"
	                            value="Voltar" align="left"
	                            onclick="document.forms[0].target='';window.location.href='<html:rewrite page="/exibirManterAcaoCobrancaAction.do"/>'">
	                        </logic:equal>
	                    </logic:present>
	                    <logic:notPresent name="voltar">
	                        <input name="Button" type="button" class="bottonRightCol"
	                        value="Voltar" align="left"
	                        onclick="document.forms[0].target='';window.location.href='<html:rewrite page="/exibirManterAcaoCobrancaAction.do"/>'">
	                    </logic:notPresent>
	                            &nbsp;
	                            <input name="button" type="button"
	                                class="bottonRightCol" value="Desfazer" onclick="desfazer();">&nbsp;<input type="button"
	                                name="ButtonCancelar" class="bottonRightCol" value="Cancelar"
	                                onClick="window.location.href='/gsan/telaPrincipal.do'"></td>
	                            <td valign="top">
	                              <div align="right">
	                                <gsan:controleAcessoBotao name="botaoAtualizar" value="Atualizar" onclick="validarForm(document.forms[0]);" url="atualizarAcaoCobrancaAction.do" tabindex="23"/>
	                                <%-- <input name="botaoInserir" type="button" class="bottonRightCol" value="Inserir" onclick="validarForm(document.forms[0]);" tabindex="17">--%>
	                              </div>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td>&nbsp;</td>
	                        </tr>
	                    </table>
	            </td>
	        </tr>           
	    </table>
	   </td>
	  </tr>
	 </table>


	    <%@ include file="/jsp/util/rodape.jsp"%>
	</body>
	</html:form>
	</html:html>


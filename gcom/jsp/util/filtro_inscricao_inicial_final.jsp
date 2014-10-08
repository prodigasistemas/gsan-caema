<!-- Classe de filtro que contem os dados de inscricao origem e destino do imóvel -->
<!-- Para utilizar essa classe é necessario implementar 3 etapas:
 1 - include file="/jsp/util/filtro_inscricao_inicial_final.jsp" incluir essa classe no jsp desejado.
 2 - adicionar o metodo pesquisarDadosDoFormulario(httpServletRequest.getParameter("acao"), form, httpServletRequest); no Action Exibir.  
 3 - colocar na sessao o caminho da pesquisa sessao.setAttribute("pesquisarObjetoPeloCaminho", "exibirInformarRetirarSituacaoFactivelFaturavelEsgotoAction.do");
 Ex: ExibirInformarRetirarSituacaoFactivelFaturavelEsgotoAction
 Ex: informar_retirar_situacao_factivel_faturavel_esgoto.jsp 
 4 - no submit do formulario validar se todos os campos da inscricao foram preenchidos corretamente: if (validarInscricaoInicialFinal()){ 
 5 - sobrescrever o metodo javascript habilitarCampos() no jsp principal;
 -->
 
<script type="text/javascript">

function replicarLocalidade() {
	var form = document.forms[0];
	form.idLocalidadeFinal.value = form.idLocalidadeInicial.value;
	habilitarCampos();
}

function replicarSetorComercial() {
	var form = document.forms[0];
	form.codigoSetorComercialFinal.value = form.codigoSetorComercialInicial.value;
}

function replicarQuadra() {
	var form = document.forms[0];

	form.numeroQuadraFinal.value = form.numeroQuadraInicial.value;
}

function replicarRota() {
	var form = document.forms[0];
	form.numeroRotaFinal.value = form.numeroRotaInicial.value;
}

function replicarSequencialRota() {
	var form = document.forms[0];
	form.numeroSequencialRotaFinal.value = form.numeroSequencialRotaInicial.value;
}

function limparCampos(tipo) {
	var form = document.forms[0];

	if ( tipo == '1') {

		form.idLocalidadeInicial.value = '';
		form.idLocalidadeFinal.value = '';
		form.descricaoLocalidadeInicial.value = '';
		form.descricaoLocalidadeFinal.value = '';
		form.descricaoSetorComercialInicial.value = '';
		form.descricaoSetorComercialFinal.value = '';
		form.codigoSetorComercialInicial.value = '';
		form.codigoSetorComercialFinal.value = '';
		form.numeroQuadraInicial.value = '';
		form.numeroQuadraFinal.value = '';
		form.numeroRotaInicial.value = '';
		form.numeroRotaFinal.value = '';
		form.numeroSequencialRotaInicial.value = '';
		form.numeroSequencialRotaFinal.value = '';
		
	} else if ( tipo == '2' ) {

		form.idLocalidadeFinal.value = '';
		form.descricaoLocalidadeFinal.value = '';
		form.descricaoSetorComercialInicial.value = '';
		form.descricaoSetorComercialFinal.value = '';
		form.codigoSetorComercialInicial.value = '';
		form.codigoSetorComercialFinal.value = '';
		form.numeroQuadraInicial.value = '';
		form.numeroQuadraFinal.value = '';
		form.numeroRotaInicial.value = '';
		form.numeroRotaFinal.value = '';
		form.numeroSequencialRotaInicial.value = '';
		form.numeroSequencialRotaFinal.value = '';
		
	} else if ( tipo == '3' ) {

		form.descricaoSetorComercialInicial.value = '';
		form.descricaoSetorComercialFinal.value = '';
		form.codigoSetorComercialInicial.value = '';
		form.codigoSetorComercialFinal.value = '';
		form.numeroQuadraInicial.value = '';
		form.numeroQuadraFinal.value = '';
		form.numeroRotaInicial.value = '';
		form.numeroRotaFinal.value = '';
		form.numeroSequencialRotaInicial.value = '';
		form.numeroSequencialRotaFinal.value = '';
		
	} else if ( tipo == '4' ) {

		form.descricaoSetorComercialFinal.value = '';
		form.codigoSetorComercialFinal.value = '';
		form.numeroQuadraInicial.value = '';
		form.numeroQuadraFinal.value = '';
		form.numeroRotaInicial.value = '';
		form.numeroRotaFinal.value = '';
		form.numeroSequencialRotaInicial.value = '';
		form.numeroSequencialRotaFinal.value = '';
	}
	habilitarCampos();
}

function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
	if(objetoRelacionado.disabled != true){
		if (objeto == null || codigoObjeto == null){
			abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
		}
		else{
			if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
				alert(msg);
			}
			else{
				abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
			}
		}
	}
}

function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    var form = document.forms[0];

    if (tipoConsulta == 'localidadeOrigem') {
      	form.idLocalidadeInicial.value = codigoRegistro;
      	form.descricaoLocalidadeInicial.value = descricaoRegistro;
      	form.descricaoLocalidadeInicial.style.color = "#000000";
      	form.idLocalidadeFinal.value = codigoRegistro;
      	form.descricaoLocalidadeFinal.value = descricaoRegistro;
      	form.descricaoLocalidadeFinal.style.color = "#000000";
      	form.codigoSetorComercialInicial.focus();
    } else if (tipoConsulta == 'localidadeDestino') {
        form.idLocalidadeFinal.value = codigoRegistro;
        form.descricaoLocalidadeFinal.value = descricaoRegistro;
        form.descricaoLocalidadeFinal.style.color = "#000000";
    	form.codigoSetorComercialInicial.focus();
    }
  }

function recuperarDadosQuatroParametros(codigoRegistro, descricaoRegistro1, descricaoRegistro2, tipoConsulta) { 
	var form = document.forms[0];
	
 	if (tipoConsulta == 'setorComercialOrigem') {
 		form.codigoSetorComercialInicial.value = descricaoRegistro2;
 		form.descricaoSetorComercialInicial.value = descricaoRegistro1;

 		form.codigoSetorComercialFinal.value = descricaoRegistro2;
 		form.descricaoSetorComercialFinal.value = descricaoRegistro1;
		
	} else if (tipoConsulta == 'setorComercialDestino') {
 		form.codigoSetorComercialFinal.value = descricaoRegistro2;
 		form.descricaoSetorComercialFinal.value = descricaoRegistro1;
		
	}
}

function habilitarCamposInscricao() {
	var form = document.forms[0];

	//caso a localidade seja informada habilita o setor
	if ( form.idLocalidadeFinal.value != null && form.idLocalidadeFinal.value != '' ) {

		form.codigoSetorComercialInicial.disabled = false;
		form.codigoSetorComercialFinal.disabled = false;

		//caso o setor seja informado habilita a quadra
		if ( form.codigoSetorComercialFinal.value != null && form.codigoSetorComercialFinal.value != '' ) {

			form.numeroQuadraInicial.disabled = false;
			form.numeroQuadraFinal.disabled = false;

			//caso a quadra seja informada habilita a rota
			if ( form.numeroQuadraFinal.value != null && form.numeroQuadraFinal.value != '' ) {
				form.numeroRotaInicial.disabled = false;
				form.numeroRotaFinal.disabled = false;

				if ( form.numeroRotaFinal.value != null && form.numeroRotaFinal.value != '' ) {
					form.numeroSequencialRotaInicial.disabled = false;
					form.numeroSequencialRotaFinal.disabled = false;
				} else {
					form.numeroSequencialRotaInicial.disabled = true;
					form.numeroSequencialRotaFinal.disabled = true;
					form.numeroSequencialRotaInicial.value = '';
					form.numeroSequencialRotaFinal.value = '';
				}
			} else {
				form.numeroRotaInicial.disabled = true;
				form.numeroRotaFinal.disabled = true;
				form.numeroSequencialRotaInicial.disabled = true;
				form.numeroSequencialRotaFinal.disabled = true;
				form.numeroRotaInicial.value = '';
				form.numeroRotaFinal.value = '';
				form.numeroSequencialRotaInicial.value = '';
				form.numeroSequencialRotaFinal.value = '';
			}
		} else {
			form.numeroQuadraInicial.disabled = true;
			form.numeroQuadraFinal.disabled = true;
			form.numeroRotaInicial.disabled = true;
			form.numeroRotaFinal.disabled = true;
			form.numeroSequencialRotaInicial.disabled = true;
			form.numeroSequencialRotaFinal.disabled = true;
			form.numeroQuadraInicial.value = '';
			form.numeroQuadraFinal.value = '';
			form.numeroRotaInicial.value = '';
			form.numeroRotaFinal.value = '';
			form.numeroSequencialRotaInicial.value = '';
			form.numeroSequencialRotaFinal.value = '';
		}
	} else {
		form.codigoSetorComercialInicial.disabled = true;
		form.codigoSetorComercialFinal.disabled = true;
		form.numeroQuadraInicial.disabled = true;
		form.numeroQuadraFinal.disabled = true;
		form.numeroRotaInicial.disabled = true;
		form.numeroRotaFinal.disabled = true;
		form.numeroSequencialRotaInicial.disabled = true;
		form.numeroSequencialRotaFinal.disabled = true;
		form.descricaoSetorComercialInicial.value = '';
		form.descricaoSetorComercialFinal.value = '';
		form.codigoSetorComercialInicial.value = '';
		form.codigoSetorComercialFinal.value = '';
		form.numeroQuadraInicial.value = '';
		form.numeroQuadraFinal.value = '';
		form.numeroRotaInicial.value = '';
		form.numeroRotaFinal.value = '';
		form.numeroSequencialRotaInicial.value = '';
		form.numeroSequencialRotaFinal.value = '';
	}
} 

</script>

<tr bgcolor="#90c7fc">
	<td colspan="2"><strong>Inscrição Inicial</strong></td>
</tr>
	
<tr>
	<td><strong>Localidade:</strong></td>
	
	<td>
		
		<html:text maxlength="3" 
			tabindex="1"
			property="idLocalidadeInicial" 
			size="3"
			onblur="replicarLocalidade();"
			onkeyup="replicarLocalidade();"
			onkeydown="replicarLocalidade();"
			onkeypress="javascript:validaEnterComMensagem(event, '${pesquisarObjetoPeloCaminho}'+'?acao=pesquisarLocalidadeInicial','idLocalidadeInicial','Localidade Inicial');return isCampoNumerico(event);"/>
			
		<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'origem', null, null, 275, 480, '',document.forms[0].idLocalidadeInicial);">
			<img width="23" 
				height="21" 
				border="0" 
				style="cursor:hand;"
				src="<bean:message key="caminho.imagens"/>pesquisa.gif"
				title="Pesquisar Localidade" /></a>
				
		<logic:present name="localidadeInicialEncontrada" scope="request">		
			<html:text property="descricaoLocalidadeInicial" 
				size="30"
				maxlength="30" 
				readonly="true"
				style="background-color:#EFEFEF; border:0; color: red" />
		</logic:present>
		
		<logic:notPresent name="localidadeInicialEncontrada" scope="request">
			<html:text property="descricaoLocalidadeInicial" 
				size="30"
				maxlength="30" 
				readonly="true"
				style="background-color:#EFEFEF; border:0; color: #000000" />
		</logic:notPresent> 
		
		<a href="javascript:limparCampos(1);"> 
			<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" 
				title="Apagar" /></a>
	</td>
</tr>

<tr>
	<td><strong>Setor Comercial:</strong></td>
	
	<td>
		
		<html:text maxlength="3" 
			tabindex="1"
			property="codigoSetorComercialInicial" 
			size="3"
			onblur="replicarSetorComercial();"
			onkeyup="replicarSetorComercial();"
			onkeydown="replicarSetorComercial();"
			onkeypress="return validaEnterDependencia(event, '${pesquisarObjetoPeloCaminho}'+'?acao=pesquisarSetorInicial', this, document.forms[0].idLocalidadeInicial.value, 'Localidade');return isCampoNumerico(event);"
			/>
		
		<a href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidadeInicial.value+'&tipo=setorComercialOrigem',document.forms[0].idLocalidadeInicial.value,'Localidade', 400, 800);">	
			<img width="23" 
				height="21" 
				border="0" 
				style="cursor:hand;"
				src="<bean:message key="caminho.imagens"/>pesquisa.gif"
				title="Pesquisar Setor Comercial" /></a>

		<logic:present name="setorComercialInicialEncontrado" scope="request">		
			<html:text property="descricaoSetorComercialInicial" 
				size="30"
				maxlength="30" 
				readonly="true"
				style="background-color:#EFEFEF; border:0; color: red" />
		</logic:present>
				
		<logic:notPresent name="setorComercialInicialEncontrado" scope="request">
			<html:text property="descricaoSetorComercialInicial" 
				size="30"
				maxlength="30" 
				readonly="true"
				style="background-color:#EFEFEF; border:0; color: #000000" />
		</logic:notPresent> 

		<a href="javascript:limparCampos(3);"> 
			<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" 
				title="Apagar" /></a>
	</td>
</tr>

<tr>
	<td>
		<strong>Quadra:</strong>
	</td>
     <td height="24" colspan="2">
     	<html:text maxlength="3" 
     			   property="numeroQuadraInicial" 
     			   size="4"  
     			   tabindex="1"
     			   onblur="replicarQuadra();"
				   onkeyup="replicarQuadra();"
				   onkeydown="replicarQuadra();"
     			   onkeypress="return validaEnterDependencia(event, '${pesquisarObjetoPeloCaminho}'+'?acao=pesquisarQuadraInicial', this, document.forms[0].codigoSetorComercialInicial.value, 'Setor Comercial');"
     			   />

			<logic:present name="codigoQuadraNaoEncontrada" scope="request">
				<span style="color:#ff0000" id="msgQuadra">
					<bean:write scope="request" name="msgQuadra"/>
				</span>
            </logic:present>
            
     </td>
</tr>
				
<tr>
	<td><strong>Rota:</strong></td>
	
	<td>
		
		<html:text maxlength="4" 
			tabindex="1"
			onblur="replicarRota();"
			onkeyup="replicarRota();"
			onkeydown="replicarRota();"
			property="numeroRotaInicial"
			onkeypress="return isCampoNumerico(event);"
			size="4"/>
	</td>
</tr>

<tr>
	<td><strong>Sequencial da Rota:</strong></td>
	
	<td>
		
		<html:text maxlength="4" 
			tabindex="1"
			onblur="replicarSequencialRota();"
			onkeyup="replicarSequencialRota();"
			onkeydown="replicarSequencialRota();"
			property="numeroSequencialRotaInicial"
			onkeypress="return isCampoNumerico(event);"
			size="4"/>
	</td>
</tr>
				
<tr bgcolor="#90c7fc" >
	<td colspan="2"><strong>Inscrição Final</strong></td>
</tr>
	
<tr>
	<td><strong>Localidade:</strong></td>
	
	<td>
		
		<html:text maxlength="3" 
			tabindex="1"
			property="idLocalidadeFinal" 
			size="3"
			onkeypress="return validaEnterDependencia(event, '${pesquisarObjetoPeloCaminho}'+'?acao=pesquisarLocalidadeFinal', this, document.forms[0].idLocalidadeInicial.value, 'Localidade Inicial');return isCampoNumerico(event);"/>
			
			
		<a href="javascript:chamarPopup('exibirPesquisarLocalidadeAction.do', 'destino', null, null, 275, 480, '',document.forms[0].idLocalidadeFinal);">
			<img width="23" 
				height="21" 
				border="0" 
				style="cursor:hand;"
				src="<bean:message key="caminho.imagens"/>pesquisa.gif"
				title="Pesquisar Localidade" /></a>
				

		<logic:present name="localidadeFinalEncontrada" scope="request">
			<html:text property="descricaoLocalidadeFinal" 
				size="30"
				maxlength="30" 
				readonly="true"
				style="background-color:#EFEFEF; border:0; color: red" />
		</logic:present> 

		<logic:notPresent name="localidadeFinalEncontrada" scope="request">		
			<html:text property="descricaoLocalidadeFinal" 
				size="30"
				maxlength="30" 
				readonly="true"
				style="background-color:#EFEFEF; border:0; color: #000000" />
		</logic:notPresent>		

		
		<a href="javascript:limparCampos(2);"> 
			<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" 
				title="Apagar" /></a>
	</td>
</tr>
				
<tr>
	<td><strong>Setor Comercial:</strong></td>
	
	<td>
		
		<html:text maxlength="3" 
			tabindex="1"
			property="codigoSetorComercialFinal" 
			size="3"
			onkeypress="return validaEnterDependencia(event, '${pesquisarObjetoPeloCaminho}'+'?acao=pesquisarSetorFinal', this, document.forms[0].idLocalidadeFinal.value, 'Localidade');return isCampoNumerico(event);"/>
			
		<a href="javascript:abrirPopupDependencia('exibirPesquisarSetorComercialAction.do?idLocalidade='+document.forms[0].idLocalidadeFinal.value+'&tipo=setorComercialDestino',document.forms[0].idLocalidadeFinal.value,'Localidade', 400, 800);">
			<img width="23" 
				height="21" 
				border="0" 
				style="cursor:hand;"
				src="<bean:message key="caminho.imagens"/>pesquisa.gif"
				title="Pesquisar Setor Comercial" /></a>
				
		<logic:present name="setorComercialFinalEncontrado" scope="request">
					<html:text property="descricaoSetorComercialFinal" 
						size="30"
						maxlength="30" 
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: red" />
		</logic:present> 

		<logic:notPresent name="setorComercialFinalEncontrado" scope="request">		
					<html:text property="descricaoSetorComercialFinal" 
						size="30"
						maxlength="30" 
						readonly="true"
						style="background-color:#EFEFEF; border:0; color: #000000" />
		</logic:notPresent>		
		
		
		<a href="javascript:limparCampos(4);"> 
			<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
				border="0" 
				title="Apagar" /></a>
	</td>
</tr>

<tr>
	<td>
		<strong>Quadra:</strong>
	</td>
     <td height="24" colspan="2">
     	<html:text maxlength="3" 
     			   property="numeroQuadraFinal" 
     			   size="4"  
     			   tabindex="1"
     			   onkeypress="return validaEnterDependencia(event, '${pesquisarObjetoPeloCaminho}'+'?acao=pesquisarQuadraFinal', this, document.forms[0].codigoSetorComercialFinal.value, 'Setor Comercial');"
     			   />

			<logic:present name="codigoQuadraFinalNaoEncontrada" scope="request">
				<span style="color:#ff0000" id="msgQuadraFinal">
					<bean:write scope="request" name="msgQuadraFinal"/>
				</span>
            </logic:present>                   
            
     </td>
</tr>

<tr>
	<td><strong>Rota:</strong></td>
	
	<td>
		
		<html:text maxlength="4" 
			tabindex="1"
			property="numeroRotaFinal"
			onkeypress="return isCampoNumerico(event);"
			size="4"/>
	</td>
</tr>
				
<tr>
	<td><strong>Sequencial da Rota:</strong></td>
	
	<td>
		<html:text maxlength="4" 
			tabindex="1"
			property="numeroSequencialRotaFinal"
			onkeypress="return isCampoNumerico(event);"
			size="4"/>
	</td>
</tr>
		

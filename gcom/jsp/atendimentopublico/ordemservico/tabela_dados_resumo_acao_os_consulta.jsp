<table width="100%" border="0" bgcolor="#99ccff">
	<tr heigth="20">
		<td align="center"><strong>Dados da Gera��o da Consulta</strong></td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" bgcolor="#cbe5fe">
			<tr>
				<td><strong>M�s/Ano de Refer�ncia:</strong></td>
				<td><input type="text" name="mesAnoReferencia"
					value="${dadosGeracaoResumoOSConsultaHelper.formatarAnoMesParaMesAno}"
					size="10" disabled="true" /></td>
			</tr>
			
			<tr>
				<td><strong>Tipo de Servi�o:</strong></td>
				<td><html:select property="idServicoTipo">
					<html:options collection="colecaoServicoTipoResultado"
						labelProperty="descricao" property="id" />
				</html:select></td>
			</tr>
			<tr>
				<td><strong>Ger�ncia Regional:</strong></td>
				<td><html:select property="idGerenciaRegional">
				<logic:iterate name="colecaoGerenciaRegionalResultado" id="gerenciaRegional" type="GerenciaRegional">
			        <logic:empty name="gerenciaRegional" property="nomeAbreviado">
			        <html:option value="<%=""+ gerenciaRegional.getId()%>">
					 <%=gerenciaRegional.getNome()%></html:option>
			        </logic:empty>
			        <logic:notEmpty name="gerenciaRegional" property="nomeAbreviado">
				 	 <html:option value="<%=""+ gerenciaRegional.getId()%>">
					 <%= gerenciaRegional.getNomeAbreviado() + " - " + gerenciaRegional.getNome()%></html:option>
				    </logic:notEmpty>	
				</logic:iterate>
				</html:select></td>
			</tr>
			
			<tr>
				<td><strong>Unidade de Neg�cio:</strong></td>
				<td><html:select property="idUnidadeNegocio">
				<logic:iterate name="colecaoUnidadeNegocioResultado" id="unidadeNegocio" type="UnidadeNegocio">
			        <logic:empty name="unidadeNegocio" property="nomeAbreviado">
			        <html:option value="<%=""+ unidadeNegocio.getId()%>">
					 <%=unidadeNegocio.getNome()%></html:option>
			        </logic:empty>
			        <logic:notEmpty name="unidadeNegocio" property="nomeAbreviado">
				 	 <html:option value="<%=""+ unidadeNegocio.getId()%>">
					 <%= unidadeNegocio.getNomeAbreviado() + " - " + unidadeNegocio.getNome()%></html:option>
				    </logic:notEmpty>	
				</logic:iterate>
				</html:select></td>
			</tr>
			
			<tr>
				<td><strong>Perfil do Im�vel:</strong></td>
				<td><html:select property="idPerfilImovel">
					<html:options collection="colecaoImovelPerfilResultado"
						labelProperty="descricao" property="id" />
				</html:select></td>
			</tr>
			<tr>
				<td><strong>Situa��o de Liga��o de �gua:</strong></td>
				<td><html:select property="idSituacaoLigacaoAgua">
					<html:options collection="colecaoLigacaoAguaSituacaoResultado"
						labelProperty="descricao" property="id" />
				</html:select></td>
			</tr>
			<tr>
				<td><strong>Situa��o de Liga��o de Esgoto:</strong></td>
				<td><html:select property="idSituacaoLigacaoEsgoto">
					<html:options collection="colecaoLigacaoEsgotoSituacaoResultado"
						labelProperty="descricao" property="id" />
				</html:select></td>
			</tr>
			<tr>
				<td><strong>Categoria:</strong></td>
				<td><html:select property="idCategoria">
					<html:options collection="colecaoCategoriaResultado"
						labelProperty="descricao" property="id" />
				</html:select></td>
			</tr>
			<tr>
				<td><strong>Esfera de Poder:</strong></td>
				<td><html:select property="idEsfera">
					<html:options collection="colecaoEsferaPoderResultado"
						labelProperty="descricao" property="id" />
				</html:select></td>
			</tr>
			<tr>
				<td><strong>Empresa:</strong></td>
				<td><html:select property="idEmpresa">
					<html:options collection="colecaoEmpresaResultado"
						labelProperty="descricao" property="id" />
				</html:select></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<br>

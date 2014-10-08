package gcom.gui.cadastro.atualizacaocadastral;

import org.apache.struts.action.ActionForm;

/**
 * [UC9999] Efetuar Digitacao de Dados para Atualização Cadastral
 * 
 * @since 13/07/2012
 * @author Rafael Pinto
 *
 */
public class EfetuarDigitacaoDadosAtualizacaoCadastralImovelActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
		
	private String matriculaImovel;
	private String cadastrador;
	private String nomeCadastrador;
	private String dataAtualizacao;
	private String quantidadeDocumentosIncluidos; 
	private String quantidadeDocumentos;
	
	//Dados do cliente proprietário
	private String documentoClienteProprietario;
	private String nomeClienteProprietario;
	private String sexoClienteProprietario;
	private String rgClienteProprietario;
	private String ufClienteProprietario;
	private String dddFoneClienteProprietario;
	private String foneClienteProprietario;
	private String foneTipoClienteProprietario;
	private String descricaoFoneTipoClienteProprietario;
	private String enderecoClienteProprietario;
	private String complementoClienteProprietario;
	private String bairroClienteProprietario;
	private String municipioClienteProprietario;
	private String cepClienteProprietario;
	
	
	//Dados do cliente usuário
	private String documentoClienteUsuario;
	private String nomeClienteUsuario;
	private String sexoClienteUsuario;
	private String rgClienteUsuario;
	private String ufClienteUsuario;
	
	private String dddFoneClienteUsuario;
	private String foneClienteUsuario;
	private String foneTipoClienteUsuario;
	private String descricaoFoneTipoClienteUsuario;

	
	
	//Dados do imóvel
	
	private String idLocalidade;
	private String codigoSetorComercial;
	private String numeroQuadra;
	private String lote;
	private String subLote;
	private String perfil;
	private String descricaoPerfil;
	private String analisarTarifaSocial;
	private String logradouro;
	private String referencia;
	private String descricaoReferencia;
	private String numero;
	private String complemento;
	private String idMunicipio;
	private String nomeMunicipio;
	private String bairro;
	private String nomeBairro;
	private String cep;

	private String subCategoria1;
	private String subCategoria2;
	private String subCategoria3;
	private String subCategoria4;
	private String subCategoria5;
	private String subCategoria6;
	
	
	private String numeroEconomias1;
	private String numeroEconomias2;
	private String numeroEconomias3;
	private String numeroEconomias4;
	private String numeroEconomias5;
	private String numeroEconomias6;
	
	private String numeroMoradores;
	private String medidorCelpe;
	private String pavimentoRua;
	private String descricaoPavimentoRua;
	private String pavimentoCalcada;
	private String descricaoPavimentoCalcada;
	
	private String fonteAbastecimento;
	private String descricaoFonteAbastecimento;
	
	//Dados da ligação
	private String situacaoAgua;
	private String descricaoSituacaoAgua;
	
	private String situacaoEsgoto;
	private String descricaoSituacaoEsgoto;
	
	private String indicadorHidrometro;
	private String numeroHidrometro;
	private String marca;
	private String capacidade;
	private String anoFabricacao;
	private String localInstalacao;
	private String descricaoLocalInstalacao;
	
	private String protecao;
	private String descricaoProtecao;
	
	private String cavalete;
	private String ocorrenciaCadastro;
	private String descricaoOcorrenciaCadastro;
	
	private String leitura;
	private String criterioTarifaSocial;
	private String observacao;
	private String confimacaoClienteUsuarioDesconhecido;
	private String nomeClienteReceitaFederal;
	private String matriculaNovoImovel;
	
	//Ids Dados do Ambiente Virtual 2
	private String idImovelAC;
	private String idClienteUsuarioAC;
	private String idClienteFoneAC;
	private String idHidrometroInstalacaoHistoricoAC;
	private String idImovelSubCategoria1;
	private String idImovelSubCategoria2;
	private String idImovelSubCategoria3;
	private String idImovelSubCategoria4;
	private String idImovelSubCategoria5;
	private String idImovelSubCategoria6;

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getCadastrador() {
		return cadastrador;
	}

	public void setCadastrador(String cadastrador) {
		this.cadastrador = cadastrador;
	}

	public String getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(String dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public String getDocumentoClienteProprietario() {
		return documentoClienteProprietario;
	}

	public void setDocumentoClienteProprietario(String documentoClienteProprietario) {
		this.documentoClienteProprietario = documentoClienteProprietario;
	}

	public String getNomeClienteProprietario() {
		return nomeClienteProprietario;
	}

	public void setNomeClienteProprietario(String nomeClienteProprietario) {
		this.nomeClienteProprietario = nomeClienteProprietario;
	}

	public String getSexoClienteProprietario() {
		return sexoClienteProprietario;
	}

	public void setSexoClienteProprietario(String sexoClienteProprietario) {
		this.sexoClienteProprietario = sexoClienteProprietario;
	}

	public String getRgClienteProprietario() {
		return rgClienteProprietario;
	}

	public void setRgClienteProprietario(String rgClienteProprietario) {
		this.rgClienteProprietario = rgClienteProprietario;
	}

	public String getUfClienteProprietario() {
		return ufClienteProprietario;
	}

	public void setUfClienteProprietario(String ufClienteProprietario) {
		this.ufClienteProprietario = ufClienteProprietario;
	}



	public String getFoneClienteProprietario() {
		return foneClienteProprietario;
	}

	public void setFoneClienteProprietario(String foneClienteProprietario) {
		this.foneClienteProprietario = foneClienteProprietario;
	}

	public String getFoneTipoClienteProprietario() {
		return foneTipoClienteProprietario;
	}

	public void setFoneTipoClienteProprietario(String foneTipoClienteProprietario) {
		this.foneTipoClienteProprietario = foneTipoClienteProprietario;
	}

	public String getFoneClienteUsuario() {
		return foneClienteUsuario;
	}

	public void setFoneClienteUsuario(String foneClienteUsuario) {
		this.foneClienteUsuario = foneClienteUsuario;
	}

	public String getFoneTipoClienteUsuario() {
		return foneTipoClienteUsuario;
	}

	public void setFoneTipoClienteUsuario(String foneTipoClienteUsuario) {
		this.foneTipoClienteUsuario = foneTipoClienteUsuario;
	}

	public String getEnderecoClienteProprietario() {
		return enderecoClienteProprietario;
	}

	public void setEnderecoClienteProprietario(String enderecoClienteProprietario) {
		this.enderecoClienteProprietario = enderecoClienteProprietario;
	}

	public String getComplementoClienteProprietario() {
		return complementoClienteProprietario;
	}

	public void setComplementoClienteProprietario(String complementoClienteProprietario) {
		this.complementoClienteProprietario = complementoClienteProprietario;
	}

	public String getBairroClienteProprietario() {
		return bairroClienteProprietario;
	}

	public void setBairroClienteProprietario(String bairroClienteProprietario) {
		this.bairroClienteProprietario = bairroClienteProprietario;
	}

	public String getMunicipioClienteProprietario() {
		return municipioClienteProprietario;
	}

	public void setMunicipioClienteProprietario(String municipioClienteProprietario) {
		this.municipioClienteProprietario = municipioClienteProprietario;
	}

	public String getCepClienteProprietario() {
		return cepClienteProprietario;
	}

	public void setCepClienteProprietario(String cepClienteProprietario) {
		this.cepClienteProprietario = cepClienteProprietario;
	}

	public String getDocumentoClienteUsuario() {
		return documentoClienteUsuario;
	}

	public void setDocumentoClienteUsuario(String documentoClienteUsuario) {
		this.documentoClienteUsuario = documentoClienteUsuario;
	}

	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}

	public String getSexoClienteUsuario() {
		return sexoClienteUsuario;
	}

	public void setSexoClienteUsuario(String sexoClienteUsuario) {
		this.sexoClienteUsuario = sexoClienteUsuario;
	}

	public String getRgClienteUsuario() {
		return rgClienteUsuario;
	}

	public void setRgClienteUsuario(String rgClienteUsuario) {
		this.rgClienteUsuario = rgClienteUsuario;
	}

	public String getUfClienteUsuario() {
		return ufClienteUsuario;
	}

	public void setUfClienteUsuario(String ufClienteUsuario) {
		this.ufClienteUsuario = ufClienteUsuario;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getSubLote() {
		return subLote;
	}

	public void setSubLote(String subLote) {
		this.subLote = subLote;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getAnalisarTarifaSocial() {
		return analisarTarifaSocial;
	}

	public void setAnalisarTarifaSocial(String analisarTarifaSocial) {
		this.analisarTarifaSocial = analisarTarifaSocial;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getSubCategoria1() {
		return subCategoria1;
	}

	public void setSubCategoria1(String subCategoria1) {
		this.subCategoria1 = subCategoria1;
	}

	public String getSubCategoria2() {
		return subCategoria2;
	}

	public void setSubCategoria2(String subCategoria2) {
		this.subCategoria2 = subCategoria2;
	}

	public String getSubCategoria3() {
		return subCategoria3;
	}

	public void setSubCategoria3(String subCategoria3) {
		this.subCategoria3 = subCategoria3;
	}

	public String getSubCategoria4() {
		return subCategoria4;
	}

	public void setSubCategoria4(String subCategoria4) {
		this.subCategoria4 = subCategoria4;
	}

	public String getSubCategoria5() {
		return subCategoria5;
	}

	public void setSubCategoria5(String subCategoria5) {
		this.subCategoria5 = subCategoria5;
	}

	public String getSubCategoria6() {
		return subCategoria6;
	}

	public void setSubCategoria6(String subCategoria6) {
		this.subCategoria6 = subCategoria6;
	}

	public String getNumeroEconomias1() {
		return numeroEconomias1;
	}

	public void setNumeroEconomias1(String numeroEconomias1) {
		this.numeroEconomias1 = numeroEconomias1;
	}

	public String getNumeroEconomias2() {
		return numeroEconomias2;
	}

	public void setNumeroEconomias2(String numeroEconomias2) {
		this.numeroEconomias2 = numeroEconomias2;
	}

	public String getNumeroEconomias3() {
		return numeroEconomias3;
	}

	public void setNumeroEconomias3(String numeroEconomias3) {
		this.numeroEconomias3 = numeroEconomias3;
	}

	public String getNumeroEconomias4() {
		return numeroEconomias4;
	}

	public void setNumeroEconomias4(String numeroEconomias4) {
		this.numeroEconomias4 = numeroEconomias4;
	}

	public String getNumeroEconomias5() {
		return numeroEconomias5;
	}

	public void setNumeroEconomias5(String numeroEconomias5) {
		this.numeroEconomias5 = numeroEconomias5;
	}

	public String getNumeroEconomias6() {
		return numeroEconomias6;
	}

	public void setNumeroEconomias6(String numeroEconomias6) {
		this.numeroEconomias6 = numeroEconomias6;
	}

	public String getNumeroMoradores() {
		return numeroMoradores;
	}

	public void setNumeroMoradores(String numeroMoradores) {
		this.numeroMoradores = numeroMoradores;
	}

	public String getMedidorCelpe() {
		return medidorCelpe;
	}

	public void setMedidorCelpe(String medidorCelpe) {
		this.medidorCelpe = medidorCelpe;
	}

	public String getPavimentoRua() {
		return pavimentoRua;
	}

	public void setPavimentoRua(String pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}

	public String getPavimentoCalcada() {
		return pavimentoCalcada;
	}

	public void setPavimentoCalcada(String pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}

	public String getFonteAbastecimento() {
		return fonteAbastecimento;
	}

	public void setFonteAbastecimento(String fonteAbastecimento) {
		this.fonteAbastecimento = fonteAbastecimento;
	}

	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	public String getIndicadorHidrometro() {
		return indicadorHidrometro;
	}

	public void setIndicadorHidrometro(String indicadorHidrometro) {
		this.indicadorHidrometro = indicadorHidrometro;
	}

	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(String capacidade) {
		this.capacidade = capacidade;
	}

	public String getLocalInstalacao() {
		return localInstalacao;
	}

	public void setLocalInstalacao(String localInstalacao) {
		this.localInstalacao = localInstalacao;
	}

	public String getProtecao() {
		return protecao;
	}

	public void setProtecao(String protecao) {
		this.protecao = protecao;
	}

	public String getCavalete() {
		return cavalete;
	}

	public void setCavalete(String cavalete) {
		this.cavalete = cavalete;
	}

	public String getOcorrenciaCadastro() {
		return ocorrenciaCadastro;
	}

	public void setOcorrenciaCadastro(String ocorrenciaCadastro) {
		this.ocorrenciaCadastro = ocorrenciaCadastro;
	}

	public String getLeitura() {
		return leitura;
	}

	public void setLeitura(String leitura) {
		this.leitura = leitura;
	}

	public String getCriterioTarifaSocial() {
		return criterioTarifaSocial;
	}

	public void setCriterioTarifaSocial(String criterioTarifaSocial) {
		this.criterioTarifaSocial = criterioTarifaSocial;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getDescricaoReferencia() {
		return descricaoReferencia;
	}

	public void setDescricaoReferencia(String descricaoReferencia) {
		this.descricaoReferencia = descricaoReferencia;
	}

	public String getDescricaoPavimentoRua() {
		return descricaoPavimentoRua;
	}

	public void setDescricaoPavimentoRua(String descricaoPavimentoRua) {
		this.descricaoPavimentoRua = descricaoPavimentoRua;
	}

	public String getDescricaoPavimentoCalcada() {
		return descricaoPavimentoCalcada;
	}

	public void setDescricaoPavimentoCalcada(String descricaoPavimentoCalcada) {
		this.descricaoPavimentoCalcada = descricaoPavimentoCalcada;
	}

	public String getDescricaoFonteAbastecimento() {
		return descricaoFonteAbastecimento;
	}

	public void setDescricaoFonteAbastecimento(String descricaoFonteAbastecimento) {
		this.descricaoFonteAbastecimento = descricaoFonteAbastecimento;
	}

	public String getDescricaoSituacaoAgua() {
		return descricaoSituacaoAgua;
	}

	public void setDescricaoSituacaoAgua(String descricaoSituacaoAgua) {
		this.descricaoSituacaoAgua = descricaoSituacaoAgua;
	}

	public String getDescricaoSituacaoEsgoto() {
		return descricaoSituacaoEsgoto;
	}

	public void setDescricaoSituacaoEsgoto(String descricaoSituacaoEsgoto) {
		this.descricaoSituacaoEsgoto = descricaoSituacaoEsgoto;
	}

	public String getDescricaoLocalInstalacao() {
		return descricaoLocalInstalacao;
	}

	public void setDescricaoLocalInstalacao(String descricaoLocalInstalacao) {
		this.descricaoLocalInstalacao = descricaoLocalInstalacao;
	}

	public String getDescricaoProtecao() {
		return descricaoProtecao;
	}

	public void setDescricaoProtecao(String descricaoProtecao) {
		this.descricaoProtecao = descricaoProtecao;
	}

	public String getDescricaoOcorrenciaCadastro() {
		return descricaoOcorrenciaCadastro;
	}

	public void setDescricaoOcorrenciaCadastro(String descricaoOcorrenciaCadastro) {
		this.descricaoOcorrenciaCadastro = descricaoOcorrenciaCadastro;
	}

	public String getDddFoneClienteProprietario() {
		return dddFoneClienteProprietario;
	}

	public void setDddFoneClienteProprietario(String dddFoneClienteProprietario) {
		this.dddFoneClienteProprietario = dddFoneClienteProprietario;
	}

	public String getDescricaoFoneTipoClienteProprietario() {
		return descricaoFoneTipoClienteProprietario;
	}

	public void setDescricaoFoneTipoClienteProprietario(String descricaoFoneTipoClienteProprietario) {
		this.descricaoFoneTipoClienteProprietario = descricaoFoneTipoClienteProprietario;
	}

	public String getDddFoneClienteUsuario() {
		return dddFoneClienteUsuario;
	}

	public void setDddFoneClienteUsuario(String dddFoneClienteUsuario) {
		this.dddFoneClienteUsuario = dddFoneClienteUsuario;
	}

	public String getDescricaoFoneTipoClienteUsuario() {
		return descricaoFoneTipoClienteUsuario;
	}

	public void setDescricaoFoneTipoClienteUsuario(String descricaoFoneTipoClienteUsuario) {
		this.descricaoFoneTipoClienteUsuario = descricaoFoneTipoClienteUsuario;
	}

	public String getNomeCadastrador() {
		return nomeCadastrador;
	}

	public void setNomeCadastrador(String nomeCadastrador) {
		this.nomeCadastrador = nomeCadastrador;
	}

	public String getQuantidadeDocumentosIncluidos() {
		return quantidadeDocumentosIncluidos;
	}

	public void setQuantidadeDocumentosIncluidos(String quantidadeDocumentosIncluidos) {
		this.quantidadeDocumentosIncluidos = quantidadeDocumentosIncluidos;
	}

	public String getQuantidadeDocumentos() {
		return quantidadeDocumentos;
	}

	public void setQuantidadeDocumentos(String quantidadeDocumentos) {
		this.quantidadeDocumentos = quantidadeDocumentos;
	}

	public String getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public String getDescricaoPerfil() {
		return descricaoPerfil;
	}

	public void setDescricaoPerfil(String descricaoPerfil) {
		this.descricaoPerfil = descricaoPerfil;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public String getNomeClienteReceitaFederal() {
		return nomeClienteReceitaFederal;
	}

	public void setNomeClienteReceitaFederal(String nomeClienteReceitaFederal) {
		this.nomeClienteReceitaFederal = nomeClienteReceitaFederal;
	}

	public String getConfimacaoClienteUsuarioDesconhecido() {
		return confimacaoClienteUsuarioDesconhecido;
	}

	public void setConfimacaoClienteUsuarioDesconhecido(
			String confimacaoClienteUsuarioDesconhecido) {
		this.confimacaoClienteUsuarioDesconhecido = confimacaoClienteUsuarioDesconhecido;
	}

	public String getMatriculaNovoImovel() {
		return matriculaNovoImovel;
	}

	public void setMatriculaNovoImovel(String matriculaNovoImovel) {
		this.matriculaNovoImovel = matriculaNovoImovel;
	}

	public String getIdImovelAC() {
		return idImovelAC;
	}

	public void setIdImovelAC(String idImovelAC) {
		this.idImovelAC = idImovelAC;
	}

	public String getIdClienteUsuarioAC() {
		return idClienteUsuarioAC;
	}

	public void setIdClienteUsuarioAC(String idClienteUsuarioAC) {
		this.idClienteUsuarioAC = idClienteUsuarioAC;
	}

	public String getIdClienteFoneAC() {
		return idClienteFoneAC;
	}

	public void setIdClienteFoneAC(String idClienteFoneAC) {
		this.idClienteFoneAC = idClienteFoneAC;
	}

	public String getIdHidrometroInstalacaoHistoricoAC() {
		return idHidrometroInstalacaoHistoricoAC;
	}

	public void setIdHidrometroInstalacaoHistoricoAC(
			String idHidrometroInstalacaoHistoricoAC) {
		this.idHidrometroInstalacaoHistoricoAC = idHidrometroInstalacaoHistoricoAC;
	}

	public String getIdImovelSubCategoria1() {
		return idImovelSubCategoria1;
	}

	public void setIdImovelSubCategoria1(String idImovelSubCategoria1) {
		this.idImovelSubCategoria1 = idImovelSubCategoria1;
	}

	public String getIdImovelSubCategoria2() {
		return idImovelSubCategoria2;
	}

	public void setIdImovelSubCategoria2(String idImovelSubCategoria2) {
		this.idImovelSubCategoria2 = idImovelSubCategoria2;
	}

	public String getIdImovelSubCategoria3() {
		return idImovelSubCategoria3;
	}

	public void setIdImovelSubCategoria3(String idImovelSubCategoria3) {
		this.idImovelSubCategoria3 = idImovelSubCategoria3;
	}

	public String getIdImovelSubCategoria4() {
		return idImovelSubCategoria4;
	}

	public void setIdImovelSubCategoria4(String idImovelSubCategoria4) {
		this.idImovelSubCategoria4 = idImovelSubCategoria4;
	}

	public String getIdImovelSubCategoria5() {
		return idImovelSubCategoria5;
	}

	public void setIdImovelSubCategoria5(String idImovelSubCategoria5) {
		this.idImovelSubCategoria5 = idImovelSubCategoria5;
	}

	public String getIdImovelSubCategoria6() {
		return idImovelSubCategoria6;
	}

	public void setIdImovelSubCategoria6(String idImovelSubCategoria6) {
		this.idImovelSubCategoria6 = idImovelSubCategoria6;
	}
	
}
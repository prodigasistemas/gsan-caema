package gcom.gerencial.faturamento.bean;


public class ResumoKitCasServicoHelper {
    
    private Integer anoMesFaturamento;
    private Integer idGerenciaRegional;
    private Integer idUnidadeNegocio;
    private Integer cdElo;
    private Integer idLocalidade;
    private Integer idSetorComercial;
    private Integer cdSetorComercial;
    private Integer idRota;
    private Short   cdRota;
    private Integer idQuadra;
    private Integer nmQuadra;
    private Integer idPerfilImovel;
    private Integer situacaoLigacaoAgua;
    private Integer situacaoLigacaoEsgoto;
    private Integer idCategoria;
    private Integer idSubcategoria;
    private Integer idEsferaPoder;
    private Integer idTipoCliente;
    private Integer idContaMotivoRevisao;
    private Integer quantidadeSupressoesExecutadas = 0;
    private Integer quantidadeRestabelecimentos = 0;
    private Integer quantidadeFiscalizacoesOriundasCortados = 0;
    private Integer quantidadeFiscalizacoesOriundasSuprimidos = 0;
    private Integer quantidadeAvisosCorteEmitidos = 0;
    private Integer quantidadeRAPendentesComerciaisNoPrazo = 0;
    private Integer quantidadeRAPendentesComerciaisForaPrazo = 0;
    private Integer quantidadeRAPendentesOperacionaisNoPrazo = 0;
    private Integer quantidadeRAPendentesOperacionaisForaPrazo = 0;
    private Integer quantidadeFaturasRevisao = 0;
    private Integer quantidadeCartasNegativasEmitidas = 0;
    private Integer quantidadeAnormalidadesInformadas = 0;
    private Integer quantidadeRegistrosRecebidos = 0;
    private Integer quantidadeImoveisEconomiaCres = 0;
    private Integer quantidadeEconomiaCres = 0;
    private Integer quantidadeImoveisEconomiaDecres = 0;
    private Integer quantidadeEconomiaDecres = 0;
    private Integer quantidadeAlteracaoCategoria = 0;
    private Integer quantidadeImoveisInclusaoTarSocial = 0;
    private Integer quantidadeImoveisExclusaoTarSocial = 0;
    private Integer quantidadeFaturamentoSuspenso = 0;
    private Integer quantidadeImoveisFaturasRevisao = 0;
    
    /**
     * Compara duas properiedades inteiras, comparando
     * seus valores para descobrirmos se sao iguais
     * @param pro1 Primeira propriedade
     * @param pro2 Segunda propriedade
     * @return Se iguais, retorna true
     */
    private boolean propriedadesIguais( Integer pro1, Integer pro2 ){
      if ( pro2 != null ){
          if ( !pro2.equals( pro1 ) ){
              return false;
          }
      } else if ( pro1 != null ){
          return false;
      }
      
      // Se chegou ate aqui quer dizer que as propriedades sao iguais
      return true;
    }
    
    /**
     * Compara duas properiedades inteiras, comparando
     * seus valores para descobrirmos se sao iguais
     * @param pro1 Primeira propriedade
     * @param pro2 Segunda propriedade
     * @return Se iguais, retorna true
     */
    private boolean propriedadesIguais( Short pro1, Short pro2 ){
      if ( pro2 != null ){
          if ( !pro2.equals( pro1 ) ){
              return false;
          }
      } else if ( pro1 != null ){
          return false;
      }
      
      // Se chegou ate aqui quer dizer que as propriedades sao iguais
      return true;
    }        

    public boolean equals( Object obj ){
        
    	ResumoKitCasServicoHelper objeto = ( ResumoKitCasServicoHelper ) obj;
        
        // Verificamos qual o tipo de quebra que precisa ser respeitado para cada tipo de resumo
        return
            propriedadesIguais( this.getAnoMesFaturamento(), objeto.getAnoMesFaturamento() ) &&
            propriedadesIguais( this.getIdGerenciaRegional(), objeto.getIdGerenciaRegional() ) &&
            propriedadesIguais( this.getIdUnidadeNegocio(), objeto.getIdUnidadeNegocio() ) &&
            propriedadesIguais( this.getCdElo(), objeto.getCdElo() ) &&
            propriedadesIguais( this.getIdLocalidade(), objeto.getIdLocalidade() ) &&
            propriedadesIguais( this.getIdSetorComercial(), objeto.getIdSetorComercial() ) &&
            propriedadesIguais( this.getCdSetorComercial(), objeto.getCdSetorComercial() ) &&
            propriedadesIguais( this.getIdRota(), objeto.getIdRota() ) &&
            propriedadesIguais( this.getCdRota(), objeto.getCdRota() ) &&
            propriedadesIguais( this.getIdQuadra(), objeto.getIdQuadra() ) &&
            propriedadesIguais( this.getNmQuadra(), objeto.getNmQuadra() ) &&
            propriedadesIguais( this.getIdPerfilImovel(), objeto.getIdPerfilImovel() ) &&
            propriedadesIguais( this.getSituacaoLigacaoAgua(), objeto.getSituacaoLigacaoAgua() ) &&
            propriedadesIguais( this.getSituacaoLigacaoEsgoto(), objeto.getSituacaoLigacaoEsgoto() ) &&
            propriedadesIguais( this.getIdCategoria(), objeto.getIdCategoria() ) &&
            propriedadesIguais( this.getIdSubcategoria(), objeto.getIdSubcategoria() ) &&
            propriedadesIguais( this.getIdEsferaPoder(), objeto.getIdEsferaPoder() ) &&
            propriedadesIguais( this.getIdTipoCliente(), objeto.getIdTipoCliente() ); 
    }

	public Integer getAnoMesFaturamento() {
		return anoMesFaturamento;
	}

	public void setAnoMesFaturamento(Integer anoMesFaturamento) {
		this.anoMesFaturamento = anoMesFaturamento;
	}

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public Integer getCdElo() {
		return cdElo;
	}

	public void setCdElo(Integer cdElo) {
		this.cdElo = cdElo;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public Integer getCdSetorComercial() {
		return cdSetorComercial;
	}

	public void setCdSetorComercial(Integer cdSetorComercial) {
		this.cdSetorComercial = cdSetorComercial;
	}

	public Integer getIdRota() {
		return idRota;
	}

	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}

	public Short getCdRota() {
		return cdRota;
	}

	public void setCdRota(Short cdRota) {
		this.cdRota = cdRota;
	}

	public Integer getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(Integer idQuadra) {
		this.idQuadra = idQuadra;
	}

	public Integer getNmQuadra() {
		return nmQuadra;
	}

	public void setNmQuadra(Integer nmQuadra) {
		this.nmQuadra = nmQuadra;
	}

	public Integer getIdPerfilImovel() {
		return idPerfilImovel;
	}

	public void setIdPerfilImovel(Integer idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}

	public Integer getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	public void setSituacaoLigacaoAgua(Integer situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	public Integer getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	public void setSituacaoLigacaoEsgoto(Integer situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdSubcategoria() {
		return idSubcategoria;
	}

	public void setIdSubcategoria(Integer idSubcategoria) {
		this.idSubcategoria = idSubcategoria;
	}

	public Integer getIdEsferaPoder() {
		return idEsferaPoder;
	}

	public void setIdEsferaPoder(Integer idEsferaPoder) {
		this.idEsferaPoder = idEsferaPoder;
	}

	public Integer getIdTipoCliente() {
		return idTipoCliente;
	}

	public void setIdTipoCliente(Integer idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}

	public Integer getQuantidadeSupressoesExecutadas() {
		return quantidadeSupressoesExecutadas;
	}

	public void setQuantidadeSupressoesExecutadas(Integer quantidadeSupressoesExecutadas) {
		this.quantidadeSupressoesExecutadas = quantidadeSupressoesExecutadas;
	}

	public Integer getQuantidadeRestabelecimentos() {
		return quantidadeRestabelecimentos;
	}

	public void setQuantidadeRestabelecimentos(Integer quantidadeRestabelecimentos) {
		this.quantidadeRestabelecimentos = quantidadeRestabelecimentos;
	}

	public Integer getQuantidadeFiscalizacoesOriundasCortados() {
		return quantidadeFiscalizacoesOriundasCortados;
	}

	public void setQuantidadeFiscalizacoesOriundasCortados(Integer quantidadeFiscalizacoesOriundasCortados) {
		this.quantidadeFiscalizacoesOriundasCortados = quantidadeFiscalizacoesOriundasCortados;
	}

	public Integer getQuantidadeFiscalizacoesOriundasSuprimidos() {
		return quantidadeFiscalizacoesOriundasSuprimidos;
	}

	public void setQuantidadeFiscalizacoesOriundasSuprimidos(Integer quantidadeFiscalizacoesOriundasSuprimidos) {
		this.quantidadeFiscalizacoesOriundasSuprimidos = quantidadeFiscalizacoesOriundasSuprimidos;
	}

	public Integer getQuantidadeAvisosCorteEmitidos() {
		return quantidadeAvisosCorteEmitidos;
	}

	public void setQuantidadeAvisosCorteEmitidos(Integer quantidadeAvisosCorteEmitidos) {
		this.quantidadeAvisosCorteEmitidos = quantidadeAvisosCorteEmitidos;
	}

	public Integer getQuantidadeRAPendentesComerciaisNoPrazo() {
		return quantidadeRAPendentesComerciaisNoPrazo;
	}

	public void setQuantidadeRAPendentesComerciaisNoPrazo(Integer quantidadeRAPendentesComerciaisNoPrazo) {
		this.quantidadeRAPendentesComerciaisNoPrazo = quantidadeRAPendentesComerciaisNoPrazo;
	}

	public Integer getQuantidadeRAPendentesComerciaisForaPrazo() {
		return quantidadeRAPendentesComerciaisForaPrazo;
	}

	public void setQuantidadeRAPendentesComerciaisForaPrazo(Integer quantidadeRAPendentesComerciaisForaPrazo) {
		this.quantidadeRAPendentesComerciaisForaPrazo = quantidadeRAPendentesComerciaisForaPrazo;
	}

	public Integer getQuantidadeRAPendentesOperacionaisNoPrazo() {
		return quantidadeRAPendentesOperacionaisNoPrazo;
	}

	public void setQuantidadeRAPendentesOperacionaisNoPrazo(Integer quantidadeRAPendentesOperacionaisNoPrazo) {
		this.quantidadeRAPendentesOperacionaisNoPrazo = quantidadeRAPendentesOperacionaisNoPrazo;
	}

	public Integer getQuantidadeRAPendentesOperacionaisForaPrazo() {
		return quantidadeRAPendentesOperacionaisForaPrazo;
	}

	public void setQuantidadeRAPendentesOperacionaisForaPrazo(Integer quantidadeRAPendentesOperacionaisForaPrazo) {
		this.quantidadeRAPendentesOperacionaisForaPrazo = quantidadeRAPendentesOperacionaisForaPrazo;
	}

	public Integer getQuantidadeFaturasRevisao() {
		return quantidadeFaturasRevisao;
	}

	public void setQuantidadeFaturasRevisao(Integer quantidadeFaturasRevisao) {
		this.quantidadeFaturasRevisao = quantidadeFaturasRevisao;
	}

	public Integer getQuantidadeCartasNegativasEmitidas() {
		return quantidadeCartasNegativasEmitidas;
	}

	public void setQuantidadeCartasNegativasEmitidas(Integer quantidadeCartasNegativasEmitidas) {
		this.quantidadeCartasNegativasEmitidas = quantidadeCartasNegativasEmitidas;
	}

	public Integer getIdContaMotivoRevisao() {
		return idContaMotivoRevisao;
	}

	public void setIdContaMotivoRevisao(Integer idContaMotivoRevisao) {
		this.idContaMotivoRevisao = idContaMotivoRevisao;
	}

	public Integer getQuantidadeAnormalidadesInformadas() {
		return quantidadeAnormalidadesInformadas;
	}

	public void setQuantidadeAnormalidadesInformadas(Integer quantidadeAnormalidadesInformadas) {
		this.quantidadeAnormalidadesInformadas = quantidadeAnormalidadesInformadas;
	}

	public Integer getQuantidadeRegistrosRecebidos() {
		return quantidadeRegistrosRecebidos;
	}

	public void setQuantidadeRegistrosRecebidos(Integer quantidadeRegistrosRecebidos) {
		this.quantidadeRegistrosRecebidos = quantidadeRegistrosRecebidos;
	}

	public Integer getQuantidadeImoveisEconomiaCres() {
		return quantidadeImoveisEconomiaCres;
	}

	public void setQuantidadeImoveisEconomiaCres(
			Integer quantidadeImoveisEconomiaCres) {
		this.quantidadeImoveisEconomiaCres = quantidadeImoveisEconomiaCres;
	}

	public Integer getQuantidadeEconomiaCres() {
		return quantidadeEconomiaCres;
	}

	public void setQuantidadeEconomiaCres(Integer quantidadeEconomiaCres) {
		this.quantidadeEconomiaCres = quantidadeEconomiaCres;
	}

	public Integer getQuantidadeImoveisEconomiaDecres() {
		return quantidadeImoveisEconomiaDecres;
	}

	public void setQuantidadeImoveisEconomiaDecres(
			Integer quantidadeImoveisEconomiaDecres) {
		this.quantidadeImoveisEconomiaDecres = quantidadeImoveisEconomiaDecres;
	}

	public Integer getQuantidadeEconomiaDecres() {
		return quantidadeEconomiaDecres;
	}

	public void setQuantidadeEconomiaDecres(Integer quantidadeEconomiaDecres) {
		this.quantidadeEconomiaDecres = quantidadeEconomiaDecres;
	}

	public Integer getQuantidadeAlteracaoCategoria() {
		return quantidadeAlteracaoCategoria;
	}

	public void setQuantidadeAlteracaoCategoria(Integer quantidadeAlteracaoCategoria) {
		this.quantidadeAlteracaoCategoria = quantidadeAlteracaoCategoria;
	}


	public Integer getQuantidadeImoveisExclusaoTarSocial() {
		return quantidadeImoveisExclusaoTarSocial;
	}

	public void setQuantidadeImoveisExclusaoTarSocial(
			Integer quantidadeImoveisExclusaoTarSocial) {
		this.quantidadeImoveisExclusaoTarSocial = quantidadeImoveisExclusaoTarSocial;
	}

	public Integer getQuantidadeImoveisInclusaoTarSocial() {
		return quantidadeImoveisInclusaoTarSocial;
	}

	public void setQuantidadeImoveisInclusaoTarSocial(
			Integer quantidadeImoveisInclusaoTarSocial) {
		this.quantidadeImoveisInclusaoTarSocial = quantidadeImoveisInclusaoTarSocial;
	}

	public Integer getQuantidadeFaturamentoSuspenso() {
		return quantidadeFaturamentoSuspenso;
	}

	public void setQuantidadeFaturamentoSuspenso(
			Integer quantidadeFaturamentoSuspenso) {
		this.quantidadeFaturamentoSuspenso = quantidadeFaturamentoSuspenso;
	}

	public Integer getQuantidadeImoveisFaturasRevisao() {
		return quantidadeImoveisFaturasRevisao;
	}

	public void setQuantidadeImoveisFaturasRevisao(
			Integer quantidadeImoveisFaturasRevisao) {
		this.quantidadeImoveisFaturasRevisao = quantidadeImoveisFaturasRevisao;
	}
   
}

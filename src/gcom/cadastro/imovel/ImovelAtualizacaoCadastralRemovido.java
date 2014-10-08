package gcom.cadastro.imovel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ImovelAtualizacaoCadastralRemovido implements Serializable {

   private static final long serialVersionUID = 1L;
	
   private Integer id;
   private Integer imovel;
   private Integer idLocalidade;
   private int codigoSetorComercial;
   private int numeroQuadra;
   private short lote;
   private short subLote;
   private Integer numeroSequencialRota;
   private Integer idLogradouroTipo;
   private String dsLogradouroTipo;
   private Integer idLogradouroTitulo;
   private String dsLogradouroTitulo;
   private Long idLogradouro;
   private String descricaoLogradouro;
   private Integer idBairro;
   private String nomeBairro;
   private Integer codigoCep;
   private String numeroImovel;
   private String complementoEndereco;
   private Integer idEnderecoReferencia;
   private Integer idDespejo;
   private BigDecimal volumeReservatorioSuperior;
   private BigDecimal volumeReservatorioInferior;
   private Integer idPavimentoCalcada;
   private Integer idPavimentoRua;
   private Integer idFonteAbastecimento;
   private Integer idLigacaoAguaSituacao;
   private Integer idLigacaoEsgotoSituacao;
   private Integer idImovelPerfil;
   private BigDecimal volumePiscina;
   private Integer idPocoTipo;
   private Short numeroPontosUtilizacao;
   private Short numeroMorador;
   private BigDecimal numeroIptu;
   private Long numeroContratoEnergia;
   private BigDecimal coordenadaX;
   private BigDecimal coordenadaY;
   private Date ultimaAlteracao;
   private BigDecimal areaConstruida;
   private Short indicadorJardim;
   private Integer numeroLeituraInstalacaoHidrometro;
   private Integer idCapacidadeHidrometro;
   private Integer idMarcaHidrometro;
   private Integer idLocalInstalacaoHidrometro;
   private Integer idProtecaoHidrometro;
   private String numeroHidrometro;
   private Short indicadorCavalete;
   private String numeroMedidirEnergia;
   private Integer idCadastroOcorrencia;
   private String descricaoOutrasInformacoes;
   private String nomeEntrevistado;
   private Integer codigoImovelPrincipal;
   private Integer idSituacaoAtualizacaoCadastral;
   private String descricaoObservacao;
   private Integer idEmpresa;
   private Integer idMunicipio;
   private String nomeMunicipio;
   private Integer idUinidadeFederacao;
   private String dsUFSiglaMunicipio;
   private Integer parametroTabelaAtualizacaoCadastro;
   private Short indicadorAtualizado;
   private Short indicadorExclusao;
   private Short indicadorPendente;
   private Short indicadorDadosRetorno;
   private Integer idAreaConstruidaFaixa;
   private Short indicadorAlertaTarifaSocial;
   private Integer cadastrador;
   private Date dataRecebimentoMovimento;
   private Date dataVisita;
   private Short indicadorPoco;
   private Short indicadorLogradouroNovo;
   private Short indicadorBairroNovo;
   private Short indicadorResetorizado;
   private Integer imovelReferencia;
   private byte[] imagemFachada;
   private byte[] imagemHidrometro;
   private Integer capaLoteAtualizacaoCadastral;
   private Integer idLogradouroCep;
   private Integer idLogradouroBairro;
   private Short indicadorImovelNovo;
   private String codigo;
   private String login;
   private Integer codigoSituacao;
	
	
	public ImovelAtualizacaoCadastralRemovido(){}
	
	public ImovelAtualizacaoCadastralRemovido(ImovelAtualizacaoCadastral imovelAtualizacaoCadastral){
		this.imovel = imovelAtualizacaoCadastral.getImovel();
		this.idLocalidade = imovelAtualizacaoCadastral.getIdLocalidade();
		this.codigoSetorComercial = imovelAtualizacaoCadastral.getCodigoSetorComercial();
		this.numeroQuadra = imovelAtualizacaoCadastral.getNumeroQuadra();
		this.lote = imovelAtualizacaoCadastral.getLote();
		this.subLote = imovelAtualizacaoCadastral.getSubLote();
		this.numeroSequencialRota = imovelAtualizacaoCadastral.getNumeroSequencialRota();
		this.idLogradouroTipo = imovelAtualizacaoCadastral.getIdLogradouroTipo();
		this.dsLogradouroTipo = imovelAtualizacaoCadastral.getDsLogradouroTipo();
		this.idLogradouroTitulo = imovelAtualizacaoCadastral.getIdLogradouroTitulo();
		this.dsLogradouroTitulo = imovelAtualizacaoCadastral.getDsLogradouroTitulo();
		this.idLogradouro = imovelAtualizacaoCadastral.getIdLogradouro();
		this.descricaoLogradouro = imovelAtualizacaoCadastral.getDescricaoLogradouro();
		this.idBairro = imovelAtualizacaoCadastral.getIdBairro();
		this.nomeBairro = imovelAtualizacaoCadastral.getNomeBairro();
		this.codigoCep = imovelAtualizacaoCadastral.getCodigoCep();
		this.numeroImovel = imovelAtualizacaoCadastral.getNumeroImovel();
		this.complementoEndereco = imovelAtualizacaoCadastral.getComplementoEndereco();
		this.idEnderecoReferencia = imovelAtualizacaoCadastral.getIdEnderecoReferencia();
		this.idDespejo = imovelAtualizacaoCadastral.getIdDespejo();
		this.volumeReservatorioSuperior = imovelAtualizacaoCadastral.getVolumeReservatorioSuperior();
		this.volumeReservatorioInferior = imovelAtualizacaoCadastral.getVolumeReservatorioInferior();
		this.idPavimentoCalcada = imovelAtualizacaoCadastral.getIdPavimentoCalcada();
		this.idPavimentoRua = imovelAtualizacaoCadastral.getIdPavimentoRua();
		this.idFonteAbastecimento = imovelAtualizacaoCadastral.getIdFonteAbastecimento();
		this.idLigacaoAguaSituacao = imovelAtualizacaoCadastral.getIdLigacaoAguaSituacao();
		this.idLigacaoEsgotoSituacao = imovelAtualizacaoCadastral.getIdLigacaoEsgotoSituacao();
		this.idImovelPerfil = imovelAtualizacaoCadastral.getIdImovelPerfil();
		this.volumePiscina = imovelAtualizacaoCadastral.getVolumePiscina();
		this.idPocoTipo = imovelAtualizacaoCadastral.getIdPocoTipo();
		this.numeroPontosUtilizacao = imovelAtualizacaoCadastral.getNumeroPontosUtilizacao();
		this.numeroMorador = imovelAtualizacaoCadastral.getNumeroMorador();
		this.numeroIptu = imovelAtualizacaoCadastral.getNumeroIptu();
		this.numeroContratoEnergia = imovelAtualizacaoCadastral.getNumeroContratoEnergia();
		this.coordenadaX = imovelAtualizacaoCadastral.getCoordenadaX();
		this.coordenadaY = imovelAtualizacaoCadastral.getCoordenadaY();
		this.ultimaAlteracao = imovelAtualizacaoCadastral.getUltimaAlteracao();
		this.areaConstruida = imovelAtualizacaoCadastral.getAreaConstruida();
		this.indicadorJardim = imovelAtualizacaoCadastral.getIndicadorJardim();
		this.numeroLeituraInstalacaoHidrometro = imovelAtualizacaoCadastral.getNumeroLeituraInstalacaoHidrometro();
		this.idCapacidadeHidrometro = imovelAtualizacaoCadastral.getIdCapacidadeHidrometro();
		this.idMarcaHidrometro = imovelAtualizacaoCadastral.getIdMarcaHidrometro();
		this.idLocalInstalacaoHidrometro = imovelAtualizacaoCadastral.getIdLocalInstalacaoHidrometro();
		this.idProtecaoHidrometro = imovelAtualizacaoCadastral.getIdProtecaoHidrometro();
		this.numeroHidrometro = imovelAtualizacaoCadastral.getNumeroHidrometro();
		this.indicadorCavalete = imovelAtualizacaoCadastral.getIndicadorCavalete();
		this.numeroMedidirEnergia = imovelAtualizacaoCadastral.getNumeroMedidirEnergia();
		this.idCadastroOcorrencia = imovelAtualizacaoCadastral.getIdCadastroOcorrencia();
		this.descricaoOutrasInformacoes = imovelAtualizacaoCadastral.getDescricaoOutrasInformacoes();
		this.nomeEntrevistado = imovelAtualizacaoCadastral.getNomeEntrevistado();
		this.codigoImovelPrincipal = imovelAtualizacaoCadastral.getCodigoImovelPrincipal();
		this.idSituacaoAtualizacaoCadastral = imovelAtualizacaoCadastral.getIdSituacaoAtualizacaoCadastral();
		this.descricaoObservacao = imovelAtualizacaoCadastral.getDescricaoObservacao();
		this.idEmpresa = imovelAtualizacaoCadastral.getIdEmpresa();
		this.idMunicipio = imovelAtualizacaoCadastral.getIdMunicipio();
		this.nomeMunicipio = imovelAtualizacaoCadastral.getNomeMunicipio();
		this.idUinidadeFederacao = imovelAtualizacaoCadastral.getIdUinidadeFederacao();
		this.dsUFSiglaMunicipio = imovelAtualizacaoCadastral.getDsUFSiglaMunicipio();
		if(imovelAtualizacaoCadastral.getParametroTabelaAtualizacaoCadastro()!=null){
			this.parametroTabelaAtualizacaoCadastro = imovelAtualizacaoCadastral.getParametroTabelaAtualizacaoCadastro().getId();
		}
		this.indicadorAtualizado = imovelAtualizacaoCadastral.getIndicadorAtualizado();
		this.indicadorExclusao = imovelAtualizacaoCadastral.getIndicadorExclusao();
		this.indicadorPendente = imovelAtualizacaoCadastral.getIndicadorPendente();
		this.indicadorDadosRetorno = imovelAtualizacaoCadastral.getIndicadorDadosRetorno();
		this.idAreaConstruidaFaixa = imovelAtualizacaoCadastral.getIdAreaConstruidaFaixa();
		this.indicadorAlertaTarifaSocial = imovelAtualizacaoCadastral.getIndicadorAlertaTarifaSocial();
		if(imovelAtualizacaoCadastral.getCadastrador()!=null){
			this.cadastrador = imovelAtualizacaoCadastral.getCadastrador().getId();
		}
		this.dataRecebimentoMovimento = imovelAtualizacaoCadastral.getDataRecebimentoMovimento();
		this.dataVisita = imovelAtualizacaoCadastral.getDataVisita();
		this.indicadorPoco = imovelAtualizacaoCadastral.getIndicadorPoco();
		this.indicadorLogradouroNovo = imovelAtualizacaoCadastral.getIndicadorLogradouroNovo();
		this.indicadorBairroNovo = imovelAtualizacaoCadastral.getIndicadorBairroNovo();
		this.indicadorResetorizado = imovelAtualizacaoCadastral.getIndicadorResetorizado();
		this.imovelReferencia = imovelAtualizacaoCadastral.getImovelReferencia();
		this.imagemFachada = imovelAtualizacaoCadastral.getImagemFachada();
		this.imagemHidrometro = imovelAtualizacaoCadastral.getImagemHidrometro();
		if(imovelAtualizacaoCadastral.getCapaLoteAtualizacaoCadastral()!=null){
			this.capaLoteAtualizacaoCadastral = imovelAtualizacaoCadastral.getCapaLoteAtualizacaoCadastral().getId();
		}
		this.idLogradouroCep = imovelAtualizacaoCadastral.getIdLogradouroCep();
		this.idLogradouroBairro = imovelAtualizacaoCadastral.getIdLogradouroBairro();
		this.indicadorImovelNovo = imovelAtualizacaoCadastral.getIndicadorImovelNovo();
		this.codigo = imovelAtualizacaoCadastral.getCodigo();
		this.login = imovelAtualizacaoCadastral.getLogin();
		this.codigoSituacao = imovelAtualizacaoCadastral.getCodigoSituacao();
	}

    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getImovel() {
		return imovel;
	}

	public void setImovel(Integer imovel) {
		this.imovel = imovel;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public short getLote() {
        return this.lote;
    }

    public void setLote(short lote) {
        this.lote = lote;
    }

    public short getSubLote() {
        return this.subLote;
    }

    public void setSubLote(short subLote) {
        this.subLote = subLote;
    }

    public String getNumeroImovel() {
        return this.numeroImovel;
    }

    public void setNumeroImovel(String numeroImovel) {
        this.numeroImovel = numeroImovel;
    }

    public String getComplementoEndereco() {
        return this.complementoEndereco;
    }

    public void setComplementoEndereco(String complementoEndereco) {
        this.complementoEndereco = complementoEndereco;
    }

    public Integer getIdDespejo() {
        return this.idDespejo;
    }

    public void setIdDespejo(Integer idDespejo) {
        this.idDespejo = idDespejo;
    }

    public BigDecimal getVolumeReservatorioSuperior() {
        return this.volumeReservatorioSuperior;
    }

    public void setVolumeReservatorioSuperior(BigDecimal volumeReservatorioSuperior) {
        this.volumeReservatorioSuperior = volumeReservatorioSuperior;
    }

    public BigDecimal getVolumeReservatorioInferior() {
        return this.volumeReservatorioInferior;
    }

    public void setVolumeReservatorioInferior(BigDecimal volumeReservatorioInferior) {
        this.volumeReservatorioInferior = volumeReservatorioInferior;
    }

    public Integer getIdPavimentoCalcada() {
        return this.idPavimentoCalcada;
    }

    public void setIdPavimentoCalcada(Integer idPavimentoCalcada) {
        this.idPavimentoCalcada = idPavimentoCalcada;
    }

    public Integer getIdPavimentoRua() {
        return this.idPavimentoRua;
    }

    public void setIdPavimentoRua(Integer idPavimentoRua) {
        this.idPavimentoRua = idPavimentoRua;
    }

    public Integer getIdFonteAbastecimento() {
        return this.idFonteAbastecimento;
    }

    public void setIdFonteAbastecimento(Integer idFonteAbastecimento) {
        this.idFonteAbastecimento = idFonteAbastecimento;
    }

    public Integer getIdLigacaoAguaSituacao() {
        return this.idLigacaoAguaSituacao;
    }

    public void setIdLigacaoAguaSituacao(Integer idLigacaoAguaSituacao) {
        this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
    }

    public Integer getIdLigacaoEsgotoSituacao() {
        return this.idLigacaoEsgotoSituacao;
    }

    public void setIdLigacaoEsgotoSituacao(Integer idLigacaoEsgotoSituacao) {
        this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
    }

    public Integer getIdImovelPerfil() {
        return this.idImovelPerfil;
    }

    public void setIdImovelPerfil(Integer idImovelPerfil) {
        this.idImovelPerfil = idImovelPerfil;
    }

    public BigDecimal getVolumePiscina() {
        return this.volumePiscina;
    }

    public void setVolumePiscina(BigDecimal volumePiscina) {
        this.volumePiscina = volumePiscina;
    }
    
    public Integer getIdPocoTipo() {
		return idPocoTipo;
	}

	public void setIdPocoTipo(Integer idPocoTipo) {
		this.idPocoTipo = idPocoTipo;
	}

	public Short getNumeroPontosUtilizacao() {
        return this.numeroPontosUtilizacao;
    }

    public void setNumeroPontosUtilizacao(Short numeroPontosUtilizacao) {
        this.numeroPontosUtilizacao = numeroPontosUtilizacao;
    }

    public Short getNumeroMorador() {
        return this.numeroMorador;
    }

    public void setNumeroMorador(Short numeroMorador) {
        this.numeroMorador = numeroMorador;
    }

    public BigDecimal getNumeroIptu() {
        return this.numeroIptu;
    }

    public void setNumeroIptu(BigDecimal numeroIptu) {
        this.numeroIptu = numeroIptu;
    }

    public Long getNumeroContratoEnergia() {
        return this.numeroContratoEnergia;
    }

    public void setNumeroContratoEnergia(Long numeroContratoEnergia) {
        this.numeroContratoEnergia = numeroContratoEnergia;
    }

    public BigDecimal getCoordenadaX() {
        return this.coordenadaX;
    }

    public void setCoordenadaX(BigDecimal coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public BigDecimal getCoordenadaY() {
        return this.coordenadaY;
    }

    public void setCoordenadaY(BigDecimal coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }


    public Integer getIdEnderecoReferencia() {
		return idEnderecoReferencia;
	}

	public void setIdEnderecoReferencia(Integer idEnderecoReferencia) {
		this.idEnderecoReferencia = idEnderecoReferencia;
	}

	public Long getIdLogradouro() {
        return this.idLogradouro;
    }

    public void setIdLogradouro(Long idLogradouro) {
        this.idLogradouro = idLogradouro;
    }

    public BigDecimal getAreaConstruida() {
        return this.areaConstruida;
    }

    public void setAreaConstruida(BigDecimal areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    public Integer getIdBairro() {
        return this.idBairro;
    }

    public void setIdBairro(Integer idBairro) {
        this.idBairro = idBairro;
    }

    public Short getIndicadorJardim() {
        return this.indicadorJardim;
    }

    public void setIndicadorJardim(Short indicadorJardim) {
        this.indicadorJardim = indicadorJardim;
    }

    public Integer getNumeroSequencialRota() {
        return this.numeroSequencialRota;
    }

    public void setNumeroSequencialRota(Integer numeroSequencialRota) {
        this.numeroSequencialRota = numeroSequencialRota;
    }

    public int getCodigoSetorComercial() {
        return this.codigoSetorComercial;
    }

    public void setCodigoSetorComercial(int codigoSetorComercial) {
        this.codigoSetorComercial = codigoSetorComercial;
    }

    public int getNumeroQuadra() {
        return this.numeroQuadra;
    }

    public void setNumeroQuadra(int numeroQuadra) {
        this.numeroQuadra = numeroQuadra;
    }

    public String getDescricaoLogradouro() {
        return this.descricaoLogradouro;
    }

    public void setDescricaoLogradouro(String descricaoLogradouro) {
        this.descricaoLogradouro = descricaoLogradouro;
    }

    public Integer getCodigoCep() {
        return this.codigoCep;
    }

    public void setCodigoCep(Integer codigoCep) {
        this.codigoCep = codigoCep;
    }

    public String getNomeBairro() {
        return this.nomeBairro;
    }

    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    public Integer getNumeroLeituraInstalacaoHidrometro() {
        return this.numeroLeituraInstalacaoHidrometro;
    }

    public void setNumeroLeituraInstalacaoHidrometro(Integer numeroLeituraInstalacaoHidrometro) {
        this.numeroLeituraInstalacaoHidrometro = numeroLeituraInstalacaoHidrometro;
    }

    public Integer getIdCapacidadeHidrometro() {
        return this.idCapacidadeHidrometro;
    }

    public void setIdCapacidadeHidrometro(Integer idCapacidadeHidrometro) {
        this.idCapacidadeHidrometro = idCapacidadeHidrometro;
    }

    public Integer getIdMarcaHidrometro() {
        return this.idMarcaHidrometro;
    }

    public void setIdMarcaHidrometro(Integer idMarcaHidrometro) {
        this.idMarcaHidrometro = idMarcaHidrometro;
    }

    public Integer getIdLocalInstalacaoHidrometro() {
        return this.idLocalInstalacaoHidrometro;
    }

    public void setIdLocalInstalacaoHidrometro(Integer idLocalInstalacaoHidrometro) {
        this.idLocalInstalacaoHidrometro = idLocalInstalacaoHidrometro;
    }

    public Integer getIdProtecaoHidrometro() {
        return this.idProtecaoHidrometro;
    }

    public void setIdProtecaoHidrometro(Integer idProtecaoHidrometro) {
        this.idProtecaoHidrometro = idProtecaoHidrometro;
    }

    public String getNumeroHidrometro() {
        return this.numeroHidrometro;
    }

    public void setNumeroHidrometro(String numeroHidrometro) {
        this.numeroHidrometro = numeroHidrometro;
    }

	public Short getIndicadorCavalete() {
		return indicadorCavalete;
	}

	public void setIndicadorCavalete(Short indicadorCavalete) {
		this.indicadorCavalete = indicadorCavalete;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
	
    public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdSituacaoAtualizacaoCadastral() {
		return idSituacaoAtualizacaoCadastral;
	}

	public void setIdSituacaoAtualizacaoCadastral(
			Integer idSituacaoAtualizacaoCadastral) {
		this.idSituacaoAtualizacaoCadastral = idSituacaoAtualizacaoCadastral;
	}
	
    public String getDsLogradouroTipo() {
		return dsLogradouroTipo;
	}

	public void setDsLogradouroTipo(String dsLogradouroTipo) {
		this.dsLogradouroTipo = dsLogradouroTipo;
	}

	public String getDsLogradouroTitulo() {
		return dsLogradouroTitulo;
	}

	public void setDsLogradouroTitulo(String dsLogradouroTitulo) {
		this.dsLogradouroTitulo = dsLogradouroTitulo;
	}

	public Integer getIdLogradouroTipo() {
		return idLogradouroTipo;
	}

	public void setIdLogradouroTipo(Integer idLogradouroTipo) {
		this.idLogradouroTipo = idLogradouroTipo;
	}

	public Integer getIdLogradouroTitulo() {
		return idLogradouroTitulo;
	}

	public void setIdLogradouroTitulo(Integer idLogradouroTitulo) {
		this.idLogradouroTitulo = idLogradouroTitulo;
	}
	

	public Integer getParametroTabelaAtualizacaoCadastro() {
		return parametroTabelaAtualizacaoCadastro;
	}

	public void setParametroTabelaAtualizacaoCadastro(
			Integer parametroTabelaAtualizacaoCadastro) {
		this.parametroTabelaAtualizacaoCadastro = parametroTabelaAtualizacaoCadastro;
	}
	
	public Short getIndicadorAtualizado() {
		return indicadorAtualizado;
	}

	public void setIndicadorAtualizado(Short indicadorAtualizado) {
		this.indicadorAtualizado = indicadorAtualizado;
	}

	/*
	 * Retorna a inscrição do imóvel.
	 */
	public String getInscricaoFormatada() {
		String inscricao = null;

		String zeroUm = "0";
		String zeroDois = "00";
		String zeroTres = "000";

		String localidade, setorComercial, quadra, lote, subLote;

		localidade = String.valueOf(this.getIdLocalidade().intValue());
		setorComercial = String.valueOf(this.getCodigoSetorComercial());
		quadra = String.valueOf(this.getNumeroQuadra());
		lote = String.valueOf(this.getLote());
		subLote = String.valueOf(this.getSubLote());

		if (String.valueOf(this.getIdLocalidade().intValue()).length() < 3
				&& String.valueOf(this.getIdLocalidade().intValue())
						.length() > 1) {
			localidade = zeroUm + this.getIdLocalidade().intValue();
		} else if (String.valueOf(this.getIdLocalidade().intValue())
				.length() < 3) {
			localidade = zeroDois + this.getIdLocalidade().intValue();
		}

		if (String.valueOf(this.getCodigoSetorComercial()).length() < 3
				&& String.valueOf(this.getCodigoSetorComercial())
						.length() > 1) {
			setorComercial = zeroUm + this.getCodigoSetorComercial();
		} else if (String.valueOf(this.getCodigoSetorComercial())
				.length() < 3) {
			setorComercial = zeroDois + this.getCodigoSetorComercial();
		}

		if (String.valueOf(this.getNumeroQuadra()).length() < 3
				&& String.valueOf(this.getNumeroQuadra()).length() > 1) {
			quadra = zeroUm + this.getNumeroQuadra();
		} else if (String.valueOf(this.getNumeroQuadra()).length() < 3) {
			quadra = zeroDois + this.getNumeroQuadra();
		}

		if (String.valueOf(this.getLote()).length() < 4
				&& String.valueOf(this.getLote()).length() > 2) {
			lote = zeroUm + this.getLote();
		} else if (String.valueOf(this.getLote()).length() < 3
				&& String.valueOf(this.getLote()).length() > 1) {
			lote = zeroDois + this.getLote();
		} else if (String.valueOf(this.getLote()).length() < 2) {
			lote = zeroTres + this.getLote();
		}

		if (String.valueOf(this.getSubLote()).length() < 3
				&& String.valueOf(this.getSubLote()).length() > 1) {
			subLote = zeroUm + this.getSubLote();
		} else if (String.valueOf(this.getSubLote()).length() < 3) {
			subLote = zeroDois + this.getSubLote();
		}

		inscricao = localidade + "." + setorComercial + "." + quadra + "."
				+ lote + "." + subLote;

		return inscricao;
	}

	/**
     *  Descrição do método>>
     * 
     * @param other
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof ImovelAtualizacaoCadastral)) {
            return false;
        }
        ImovelAtualizacaoCadastral castOther = (ImovelAtualizacaoCadastral) other;

        return new EqualsBuilder().append(this.getId(), castOther.getId())
                .isEquals();
    }

	public Integer getCodigoImovelPrincipal() {
		return codigoImovelPrincipal;
	}



	public void setCodigoImovelPrincipal(Integer codigoImovelPrincipal) {
		this.codigoImovelPrincipal = codigoImovelPrincipal;
	}

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public Integer getIdUinidadeFederacao() {
		return idUinidadeFederacao;
	}

	public void setIdUinidadeFederacao(Integer idUinidadeFederacao) {
		this.idUinidadeFederacao = idUinidadeFederacao;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getDsUFSiglaMunicipio() {
		return dsUFSiglaMunicipio;
	}

	public void setDsUFSiglaMunicipio(String dsUFSiglaMunicipio) {
		this.dsUFSiglaMunicipio = dsUFSiglaMunicipio;
	}

	public String getDescricaoOutrasInformacoes() {
		return descricaoOutrasInformacoes;
	}

	public void setDescricaoOutrasInformacoes(String descricaoOutrasInformacoes) {
		this.descricaoOutrasInformacoes = descricaoOutrasInformacoes;
	}

	public Integer getIdCadastroOcorrencia() {
		return idCadastroOcorrencia;
	}

	public void setIdCadastroOcorrencia(Integer idCadastroOcorrencia) {
		this.idCadastroOcorrencia = idCadastroOcorrencia;
	}

	public String getNomeEntrevistado() {
		return nomeEntrevistado;
	}

	public void setNomeEntrevistado(String nomeEntrevistado) {
		this.nomeEntrevistado = nomeEntrevistado;
	}

	public String getNumeroMedidirEnergia() {
		return numeroMedidirEnergia;
	}

	public void setNumeroMedidirEnergia(String numeroMedidirEnergia) {
		this.numeroMedidirEnergia = numeroMedidirEnergia;
	}

	public Short getIndicadorExclusao() {
		return indicadorExclusao;
	}

	public void setIndicadorExclusao(Short indicadorExclusao) {
		this.indicadorExclusao = indicadorExclusao;
	}

	public Short getIndicadorPendente() {
		return indicadorPendente;
	}

	public void setIndicadorPendente(Short indicadorPendente) {
		this.indicadorPendente = indicadorPendente;
	}

	public Short getIndicadorDadosRetorno() {
		return indicadorDadosRetorno;
	}

	public void setIndicadorDadosRetorno(Short indicadorDadosRetorno) {
		this.indicadorDadosRetorno = indicadorDadosRetorno;
	}

	public Integer getIdAreaConstruidaFaixa() {
		return idAreaConstruidaFaixa;
	}

	public void setIdAreaConstruidaFaixa(Integer idAreaConstruidaFaixa) {
		this.idAreaConstruidaFaixa = idAreaConstruidaFaixa;
	}

	public Short getIndicadorAlertaTarifaSocial() {
		return indicadorAlertaTarifaSocial;
	}

	public void setIndicadorAlertaTarifaSocial(Short indicadorAlertaTarifaSocial) {
		this.indicadorAlertaTarifaSocial = indicadorAlertaTarifaSocial;
	}

	public Integer getCadastrador() {
		return cadastrador;
	}

	public void setCadastrador(Integer cadastrador) {
		this.cadastrador = cadastrador;
	}

	public Date getDataRecebimentoMovimento() {
		return dataRecebimentoMovimento;
	}

	public void setDataRecebimentoMovimento(Date dataRecebimentoMovimento) {
		this.dataRecebimentoMovimento = dataRecebimentoMovimento;
	}

	public Date getDataVisita() {
		return dataVisita;
	}

	public void setDataVisita(Date dataVisita) {
		this.dataVisita = dataVisita;
	}

	public Short getIndicadorPoco() {
		return indicadorPoco;
	}

	public void setIndicadorPoco(Short indicadorPoco) {
		this.indicadorPoco = indicadorPoco;
	}

	public Short getIndicadorLogradouroNovo() {
		return indicadorLogradouroNovo;
	}

	public void setIndicadorLogradouroNovo(Short indicadorLogradouroNovo) {
		this.indicadorLogradouroNovo = indicadorLogradouroNovo;
	}

	public Short getIndicadorBairroNovo() {
		return indicadorBairroNovo;
	}

	public void setIndicadorBairroNovo(Short indicadorBairroNovo) {
		this.indicadorBairroNovo = indicadorBairroNovo;
	}
	
	public Short getIndicadorResetorizado() {
		return indicadorResetorizado;
	}

	public void setIndicadorResetorizado(Short indicadorResetorizado) {
		this.indicadorResetorizado = indicadorResetorizado;
	}

	public byte[] getImagemFachada() {
		return imagemFachada;
	}

	public void setImagemFachada(byte[] imagemFachada) {
		this.imagemFachada = imagemFachada;
	}

	public byte[] getImagemHidrometro() {
		return imagemHidrometro;
	}

	public void setImagemHidrometro(byte[] imagemHidrometro) {
		this.imagemHidrometro = imagemHidrometro;
	}

	public Integer getImovelReferencia() {
		return imovelReferencia;
	}

	public void setImovelReferencia(Integer imovelReferencia) {
		this.imovelReferencia = imovelReferencia;
	}

	public String getDescricaoObservacao() {
		return descricaoObservacao;
	}

	public void setDescricaoObservacao(String descricaoObservacao) {
		this.descricaoObservacao = descricaoObservacao;
	}

	public Integer getCapaLoteAtualizacaoCadastral() {
		return capaLoteAtualizacaoCadastral;
	}

	public void setCapaLoteAtualizacaoCadastral(Integer capaLoteAtualizacaoCadastral) {
		this.capaLoteAtualizacaoCadastral = capaLoteAtualizacaoCadastral;
	}

	public Integer getIdLogradouroCep() {
		return idLogradouroCep;
	}

	public void setIdLogradouroCep(Integer idLogradouroCep) {
		this.idLogradouroCep = idLogradouroCep;
	}

	public Integer getIdLogradouroBairro() {
		return idLogradouroBairro;
	}

	public void setIdLogradouroBairro(Integer idLogradouroBairro) {
		this.idLogradouroBairro = idLogradouroBairro;
	}

	public Short getIndicadorImovelNovo() {
		return indicadorImovelNovo;
	}

	public void setIndicadorImovelNovo(Short indicadorImovelNovo) {
		this.indicadorImovelNovo = indicadorImovelNovo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Integer getCodigoSituacao() {
		return codigoSituacao;
	}

	public void setCodigoSituacao(Integer codigoSituacao) {
		this.codigoSituacao = codigoSituacao;
	}
}

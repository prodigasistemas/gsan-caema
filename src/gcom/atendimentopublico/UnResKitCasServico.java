package gcom.atendimentopublico;

import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;
import gcom.gerencial.cadastro.cliente.GClienteTipo;
import gcom.gerencial.cadastro.cliente.GEsferaPoder;
import gcom.gerencial.cadastro.imovel.GCategoria;
import gcom.gerencial.cadastro.imovel.GImovelPerfil;
import gcom.gerencial.cadastro.imovel.GSubcategoria;
import gcom.gerencial.cadastro.localidade.GGerenciaRegional;
import gcom.gerencial.cadastro.localidade.GLocalidade;
import gcom.gerencial.cadastro.localidade.GQuadra;
import gcom.gerencial.cadastro.localidade.GSetorComercial;
import gcom.gerencial.cadastro.localidade.GUnidadeNegocio;
import gcom.gerencial.micromedicao.GRota;

import java.io.Serializable;
import java.util.Date;

public class UnResKitCasServico implements Serializable {

	private static final long serialVersionUID = 1L;

	/** identifier field */

	private Integer id;

	/** persistent field */

	private int referencia;

	/** persistent field */

	private int codigoSetorComercial;

	/** persistent field */

	private int numeroQuadra;

	/** persistent field */

	private int quantidadeSupressoesExecutadas;

	/** persistent field */

	private int quantidadeRestabelecimentos;

	/** persistent field */

	private int quantidadeFiscalizacoesOriundasCortados;

	/** persistent field */

	private int quantidadeFiscalizacoesOriundasSuprimidos;

	/** persistent field */

	private int quantidadeAvisosCorteEmitidos;

	/** persistent field */

	private int quantidadeRAPendentesComerciaisNoPrazo;

	/** persistent field */

	private int quantidadeRAPendentesComerciaisForaPrazo;

	/** persistent field */

	private int quantidadeRAPendentesOperacionaisNoPrazo;

	/** persistent field */

	private int quantidadeRAPendentesOperacionaisForaPrazo;

	/** persistent field */

	private int quantidadeFaturasRevisao;

	/** persistent field */

	private int quantidadeCartasNegativasEmitidas;

	/** persistent field */

	private Date ultimaAlteracao;

	/** persistent field */

	private GGerenciaRegional gerGerenciaRegional;

	/** persistent field */

	private GUnidadeNegocio gerUnidadeNegocio;

	/** persistent field */

	private GLocalidade gerLocalidade;

	/** persistent field */

	private GLocalidade gerLocalidadeElo;

	/** persistent field */

	private GSetorComercial gerSetorComercial;

	/** persistent field */

	private GRota gerRota;

	/** persistent field */

	private GQuadra gerQuadra;

	/** persistent field */

	private GImovelPerfil gerImovelPerfil;

	/** persistent field */

	private GLigacaoAguaSituacao gerLigacaoAguaSituacao;

	/** persistent field */

	private GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao;

	/** persistent field */

	private GCategoria gerCategoria;

	/** persistent field */

	private GSubcategoria gerSubcategoria;

	/** persistent field */

	private GEsferaPoder gerEsferaPoder;

	/** persistent field */

	private GClienteTipo gerClienteTipo;

	/** persistent field */

	private GLigacaoAguaPerfil gerLigacaoAguaPerfil;

	/** persistent field */

	private GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil;

	public UnResKitCasServico(
			Integer id, int referencia, int codigoSetorComercial, int numeroQuadra, int quantidadeSupressoesExecutadas,
			int quantidadeRestabelecimentos, int quantidadeFiscalizacoesOriundasCortados,
			int quantidadeFiscalizacoesOriundasSuprimidos, int quantidadeAvisosCorteEmitidos,
			int quantidadeRAPendentesComerciaisNoPrazo, int quantidadeRAPendentesComerciaisForaPrazo,
			int quantidadeRAPendentesOperacionaisNoPrazo, int quantidadeRAPendentesOperacionaisForaPrazo,
			int quantidadeFaturasRevisao, int quantidadeCartasNegativasEmitidas, Date ultimaAlteracao,
			GGerenciaRegional gerGerenciaRegional, GUnidadeNegocio gerUnidadeNegocio, GLocalidade gerLocalidade,
			GLocalidade gerLocalidadeElo, GSetorComercial gerSetorComercial, GRota gerRota, GQuadra gerQuadra,
			GImovelPerfil gerImovelPerfil, GLigacaoAguaSituacao gerLigacaoAguaSituacao,
			GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao, GCategoria gerCategoria, GSubcategoria gerSubcategoria,
			GEsferaPoder gerEsferaPoder, GClienteTipo gerClienteTipo, GLigacaoAguaPerfil gerLigacaoAguaPerfil,
			GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
		super();
		this.id = id;
		this.referencia = referencia;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.quantidadeSupressoesExecutadas = quantidadeSupressoesExecutadas;
		this.quantidadeRestabelecimentos = quantidadeRestabelecimentos;
		this.quantidadeFiscalizacoesOriundasCortados = quantidadeFiscalizacoesOriundasCortados;
		this.quantidadeFiscalizacoesOriundasSuprimidos = quantidadeFiscalizacoesOriundasSuprimidos;
		this.quantidadeAvisosCorteEmitidos = quantidadeAvisosCorteEmitidos;
		this.quantidadeRAPendentesComerciaisNoPrazo = quantidadeRAPendentesComerciaisNoPrazo;
		this.quantidadeRAPendentesComerciaisForaPrazo = quantidadeRAPendentesComerciaisForaPrazo;
		this.quantidadeRAPendentesOperacionaisNoPrazo = quantidadeRAPendentesOperacionaisNoPrazo;
		this.quantidadeRAPendentesOperacionaisForaPrazo = quantidadeRAPendentesOperacionaisForaPrazo;
		this.quantidadeFaturasRevisao = quantidadeFaturasRevisao;
		this.quantidadeCartasNegativasEmitidas = quantidadeCartasNegativasEmitidas;
		this.ultimaAlteracao = ultimaAlteracao;
		this.gerGerenciaRegional = gerGerenciaRegional;
		this.gerUnidadeNegocio = gerUnidadeNegocio;
		this.gerLocalidade = gerLocalidade;
		this.gerLocalidadeElo = gerLocalidadeElo;
		this.gerSetorComercial = gerSetorComercial;
		this.gerRota = gerRota;
		this.gerQuadra = gerQuadra;
		this.gerImovelPerfil = gerImovelPerfil;
		this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
		this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
		this.gerCategoria = gerCategoria;
		this.gerSubcategoria = gerSubcategoria;
		this.gerEsferaPoder = gerEsferaPoder;
		this.gerClienteTipo = gerClienteTipo;
		this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
		this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
	}

	public UnResKitCasServico() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getReferencia() {
		return referencia;
	}

	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}

	public int getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(int codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public int getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(int numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public int getQuantidadeSupressoesExecutadas() {
		return quantidadeSupressoesExecutadas;
	}

	public void setQuantidadeSupressoesExecutadas(int quantidadeSupressoesExecutadas) {
		this.quantidadeSupressoesExecutadas = quantidadeSupressoesExecutadas;
	}

	public int getQuantidadeRestabelecimentos() {
		return quantidadeRestabelecimentos;
	}

	public void setQuantidadeRestabelecimentos(int quantidadeRestabelecimentos) {
		this.quantidadeRestabelecimentos = quantidadeRestabelecimentos;
	}

	public int getQuantidadeFiscalizacoesOriundasCortados() {
		return quantidadeFiscalizacoesOriundasCortados;
	}

	public void setQuantidadeFiscalizacoesOriundasCortados(int quantidadeFiscalizacoesOriundasCortados) {
		this.quantidadeFiscalizacoesOriundasCortados = quantidadeFiscalizacoesOriundasCortados;
	}

	public int getQuantidadeFiscalizacoesOriundasSuprimidos() {
		return quantidadeFiscalizacoesOriundasSuprimidos;
	}

	public void setQuantidadeFiscalizacoesOriundasSuprimidos(int quantidadeFiscalizacoesOriundasSuprimidos) {
		this.quantidadeFiscalizacoesOriundasSuprimidos = quantidadeFiscalizacoesOriundasSuprimidos;
	}

	public int getQuantidadeAvisosCorteEmitidos() {
		return quantidadeAvisosCorteEmitidos;
	}

	public void setQuantidadeAvisosCorteEmitidos(int quantidadeAvisosCorteEmitidos) {
		this.quantidadeAvisosCorteEmitidos = quantidadeAvisosCorteEmitidos;
	}

	public int getQuantidadeRAPendentesComerciaisNoPrazo() {
		return quantidadeRAPendentesComerciaisNoPrazo;
	}

	public void setQuantidadeRAPendentesComerciaisNoPrazo(int quantidadeRAPendentesComerciaisNoPrazo) {
		this.quantidadeRAPendentesComerciaisNoPrazo = quantidadeRAPendentesComerciaisNoPrazo;
	}

	public int getQuantidadeRAPendentesComerciaisForaPrazo() {
		return quantidadeRAPendentesComerciaisForaPrazo;
	}

	public void setQuantidadeRAPendentesComerciaisForaPrazo(int quantidadeRAPendentesComerciaisForaPrazo) {
		this.quantidadeRAPendentesComerciaisForaPrazo = quantidadeRAPendentesComerciaisForaPrazo;
	}

	public int getQuantidadeRAPendentesOperacionaisNoPrazo() {
		return quantidadeRAPendentesOperacionaisNoPrazo;
	}

	public void setQuantidadeRAPendentesOperacionaisNoPrazo(int quantidadeRAPendentesOperacionaisNoPrazo) {
		this.quantidadeRAPendentesOperacionaisNoPrazo = quantidadeRAPendentesOperacionaisNoPrazo;
	}

	public int getQuantidadeRAPendentesOperacionaisForaPrazo() {
		return quantidadeRAPendentesOperacionaisForaPrazo;
	}

	public void setQuantidadeRAPendentesOperacionaisForaPrazo(int quantidadeRAPendentesOperacionaisForaPrazo) {
		this.quantidadeRAPendentesOperacionaisForaPrazo = quantidadeRAPendentesOperacionaisForaPrazo;
	}

	public int getQuantidadeFaturasRevisao() {
		return quantidadeFaturasRevisao;
	}

	public void setQuantidadeFaturasRevisao(int quantidadeFaturasRevisao) {
		this.quantidadeFaturasRevisao = quantidadeFaturasRevisao;
	}

	public int getQuantidadeCartasNegativasEmitidas() {
		return quantidadeCartasNegativasEmitidas;
	}

	public void setQuantidadeCartasNegativasEmitidas(int quantidadeCartasNegativasEmitidas) {
		this.quantidadeCartasNegativasEmitidas = quantidadeCartasNegativasEmitidas;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public GGerenciaRegional getGerGerenciaRegional() {
		return gerGerenciaRegional;
	}

	public void setGerGerenciaRegional(GGerenciaRegional gerGerenciaRegional) {
		this.gerGerenciaRegional = gerGerenciaRegional;
	}

	public GUnidadeNegocio getGerUnidadeNegocio() {
		return gerUnidadeNegocio;
	}

	public void setGerUnidadeNegocio(GUnidadeNegocio gerUnidadeNegocio) {
		this.gerUnidadeNegocio = gerUnidadeNegocio;
	}

	public GLocalidade getGerLocalidade() {
		return gerLocalidade;
	}

	public void setGerLocalidade(GLocalidade gerLocalidade) {
		this.gerLocalidade = gerLocalidade;
	}

	public GLocalidade getGerLocalidadeElo() {
		return gerLocalidadeElo;
	}

	public void setGerLocalidadeElo(GLocalidade gerLocalidadeElo) {
		this.gerLocalidadeElo = gerLocalidadeElo;
	}

	public GSetorComercial getGerSetorComercial() {
		return gerSetorComercial;
	}

	public void setGerSetorComercial(GSetorComercial gerSetorComercial) {
		this.gerSetorComercial = gerSetorComercial;
	}

	public GRota getGerRota() {
		return gerRota;
	}

	public void setGerRota(GRota gerRota) {
		this.gerRota = gerRota;
	}

	public GQuadra getGerQuadra() {
		return gerQuadra;
	}

	public void setGerQuadra(GQuadra gerQuadra) {
		this.gerQuadra = gerQuadra;
	}

	public GImovelPerfil getGerImovelPerfil() {
		return gerImovelPerfil;
	}

	public void setGerImovelPerfil(GImovelPerfil gerImovelPerfil) {
		this.gerImovelPerfil = gerImovelPerfil;
	}

	public GLigacaoAguaSituacao getGerLigacaoAguaSituacao() {
		return gerLigacaoAguaSituacao;
	}

	public void setGerLigacaoAguaSituacao(GLigacaoAguaSituacao gerLigacaoAguaSituacao) {
		this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
	}

	public GLigacaoEsgotoSituacao getGerLigacaoEsgotoSituacao() {
		return gerLigacaoEsgotoSituacao;
	}

	public void setGerLigacaoEsgotoSituacao(GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao) {
		this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
	}

	public GCategoria getGerCategoria() {
		return gerCategoria;
	}

	public void setGerCategoria(GCategoria gerCategoria) {
		this.gerCategoria = gerCategoria;
	}

	public GSubcategoria getGerSubcategoria() {
		return gerSubcategoria;
	}

	public void setGerSubcategoria(GSubcategoria gerSubcategoria) {
		this.gerSubcategoria = gerSubcategoria;
	}

	public GEsferaPoder getGerEsferaPoder() {
		return gerEsferaPoder;
	}

	public void setGerEsferaPoder(GEsferaPoder gerEsferaPoder) {
		this.gerEsferaPoder = gerEsferaPoder;
	}

	public GClienteTipo getGerClienteTipo() {
		return gerClienteTipo;
	}

	public void setGerClienteTipo(GClienteTipo gerClienteTipo) {
		this.gerClienteTipo = gerClienteTipo;
	}

	public GLigacaoAguaPerfil getGerLigacaoAguaPerfil() {
		return gerLigacaoAguaPerfil;
	}

	public void setGerLigacaoAguaPerfil(GLigacaoAguaPerfil gerLigacaoAguaPerfil) {
		this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
	}

	public GLigacaoEsgotoPerfil getGerLigacaoEsgotoPerfil() {
		return gerLigacaoEsgotoPerfil;
	}

	public void setGerLigacaoEsgotoPerfil(GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
		this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
	}

	

}
package gcom.atendimentopublico.registroatendimento;

import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */

@ControleAlteracao()
public class RaEncerramentoComando extends ObjetoTransacao{

    /**
	 * @since 28/01/2008
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_COMANDAR_ENCERRAMENTO_RA = 1141; //Operacao.OPERACAO_COMANDAR_ENCERRAMENTO_RA

	/** identifier field */
    private Integer id;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_COMANDAR_ENCERRAMENTO_RA})
    private Date dataAtendimentoInicial;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_COMANDAR_ENCERRAMENTO_RA})
    private Date dataAtendimentoFinal;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_COMANDAR_ENCERRAMENTO_RA})
    private Date tempoComando;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_COMANDAR_ENCERRAMENTO_RA})
    private Date tempoRealizacao;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_COMANDAR_ENCERRAMENTO_RA})
    private Integer quantidadeRasEncerradas;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_COMANDAR_ENCERRAMENTO_RA})
    private Integer quantidadeOsEncerradas;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_COMANDAR_ENCERRAMENTO_RA})
    private Date ultimaAlteracao;

    /** persistent field */
    @ControleAlteracao(value=FiltroRaEncerramentoComando.USUARIO,funcionalidade={ATRIBUTOS_COMANDAR_ENCERRAMENTO_RA})
    private Usuario usuario;

    /** persistent field */
    @ControleAlteracao(value=FiltroRaEncerramentoComando.ATENDIMENTO_MOTIVO_ENC,funcionalidade={ATRIBUTOS_COMANDAR_ENCERRAMENTO_RA})
    private gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento atendimentoMotivoEncerramento;

    /** persistent field */
    @ControleAlteracao(value=FiltroRaEncerramentoComando.UNIDADE_ATUAL,funcionalidade={ATRIBUTOS_COMANDAR_ENCERRAMENTO_RA})
    private UnidadeOrganizacional unidadeOrganizacionalAtual;

    /** persistent field */
    @ControleAlteracao(value=FiltroRaEncerramentoComando.UNIDADE_SUPERIOR,funcionalidade={ATRIBUTOS_COMANDAR_ENCERRAMENTO_RA})
    private UnidadeOrganizacional unidadeOrganizacionalSuperior;

    /** persistent field */
    @ControleAlteracao(value=FiltroRaEncerramentoComando.UNIDADE_ATENDIMENTO,funcionalidade={ATRIBUTOS_COMANDAR_ENCERRAMENTO_RA})
    private UnidadeOrganizacional unidadeOrganizacionalAtendimento;

    /** full constructor */
    public RaEncerramentoComando(
			Integer id,
			Date dataAtendimentoInicial,
			Date dataAtendimentoFinal,
			Date tempoComando,
			Date tempoRealizacao,
			Integer quantidadeRasEncerradas,
			Integer quantidadeOsEncerradas,
			Date ultimaAlteracao,
			Usuario usuario,
			gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento atendimentoMotivoEncerramento,
			UnidadeOrganizacional unidadeOrganizacionalAtual,
			UnidadeOrganizacional unidadeOrganizacionalSuperior,
			UnidadeOrganizacional unidadeOrganizacionalAtendimento) {
        this.id = id;
        this.dataAtendimentoInicial = dataAtendimentoInicial;
        this.dataAtendimentoFinal = dataAtendimentoFinal;
        this.tempoComando = tempoComando;
        this.tempoRealizacao = tempoRealizacao;
        this.quantidadeRasEncerradas = quantidadeRasEncerradas;
        this.quantidadeOsEncerradas = quantidadeOsEncerradas;
        this.ultimaAlteracao = ultimaAlteracao;
        this.usuario = usuario;
        this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
        this.unidadeOrganizacionalAtual = unidadeOrganizacionalAtual;
        this.unidadeOrganizacionalSuperior = unidadeOrganizacionalSuperior;
        this.unidadeOrganizacionalAtendimento = unidadeOrganizacionalAtendimento;
    }

    /** default constructor */
    public RaEncerramentoComando() {
    }

    /** minimal constructor */
    public RaEncerramentoComando(
			Integer id,
			Date dataAtendimentoInicial,
			Date dataAtendimentoFinal,
			Date tempoComando,
			Date ultimaAlteracao,
			Usuario usuario,
			gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento atendimentoMotivoEncerramento,
			UnidadeOrganizacional unidadeOrganizacionalAtual,
			UnidadeOrganizacional unidadeOrganizacionalSuperior,
			UnidadeOrganizacional unidadeOrganizacionalAtendimento) {
        this.id = id;
        this.dataAtendimentoInicial = dataAtendimentoInicial;
        this.dataAtendimentoFinal = dataAtendimentoFinal;
        this.tempoComando = tempoComando;
        this.ultimaAlteracao = ultimaAlteracao;
        this.usuario = usuario;
        this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
        this.unidadeOrganizacionalAtual = unidadeOrganizacionalAtual;
        this.unidadeOrganizacionalSuperior = unidadeOrganizacionalSuperior;
        this.unidadeOrganizacionalAtendimento = unidadeOrganizacionalAtendimento;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataAtendimentoInicial() {
        return this.dataAtendimentoInicial;
    }

    public void setDataAtendimentoInicial(Date dataAtendimentoInicial) {
        this.dataAtendimentoInicial = dataAtendimentoInicial;
    }

    public Date getDataAtendimentoFinal() {
        return this.dataAtendimentoFinal;
    }

    public void setDataAtendimentoFinal(Date dataAtendimentoFinal) {
        this.dataAtendimentoFinal = dataAtendimentoFinal;
    }

    public Date getTempoComando() {
        return this.tempoComando;
    }

    public void setTempoComando(Date tempoComando) {
        this.tempoComando = tempoComando;
    }

    public Date getTempoRealizacao() {
        return this.tempoRealizacao;
    }

    public void setTempoRealizacao(Date tempoRealizacao) {
        this.tempoRealizacao = tempoRealizacao;
    }

    public Integer getQuantidadeRasEncerradas() {
        return this.quantidadeRasEncerradas;
    }

    public void setQuantidadeRasEncerradas(Integer quantidadeRasEncerradas) {
        this.quantidadeRasEncerradas = quantidadeRasEncerradas;
    }

    public Integer getQuantidadeOsEncerradas() {
        return this.quantidadeOsEncerradas;
    }

    public void setQuantidadeOsEncerradas(Integer quantidadeOsEncerradas) {
        this.quantidadeOsEncerradas = quantidadeOsEncerradas;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento getAtendimentoMotivoEncerramento() {
        return this.atendimentoMotivoEncerramento;
    }

    public void setAtendimentoMotivoEncerramento(gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento atendimentoMotivoEncerramento) {
        this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
    }

    public UnidadeOrganizacional getUnidadeOrganizacionalAtual() {
        return this.unidadeOrganizacionalAtual;
    }

    public void setUnidadeOrganizacionalAtual(UnidadeOrganizacional unidadeOrganizacionalAtual) {
        this.unidadeOrganizacionalAtual = unidadeOrganizacionalAtual;
    }

    public UnidadeOrganizacional getUnidadeOrganizacionalSuperior() {
        return this.unidadeOrganizacionalSuperior;
    }

    public void setUnidadeOrganizacionalSuperior(UnidadeOrganizacional unidadeOrganizacionalSuperior) {
        this.unidadeOrganizacionalSuperior = unidadeOrganizacionalSuperior;
    }

    public UnidadeOrganizacional getUnidadeOrganizacionalAtendimento() {
        return this.unidadeOrganizacionalAtendimento;
    }

    public void setUnidadeOrganizacionalAtendimento(UnidadeOrganizacional unidadeOrganizacionalAtendimento) {
        this.unidadeOrganizacionalAtendimento = unidadeOrganizacionalAtendimento;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
    
    public Filtro retornaFiltro() {
    	FiltroRaEncerramentoComando filtroRAEncerramentoComando = new FiltroRaEncerramentoComando();
    	filtroRAEncerramentoComando.adicionarParametro(new ParametroSimples(FiltroRaEncerramentoComando.ID,
				this.getId()));
    	filtroRAEncerramentoComando.adicionarCaminhoParaCarregamentoEntidade("usuario");
    	filtroRAEncerramentoComando.adicionarCaminhoParaCarregamentoEntidade("atendimentoMotivoEncerramento");
    	filtroRAEncerramentoComando.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacionalAtual");
    	filtroRAEncerramentoComando.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacionalSuperior");
    	filtroRAEncerramentoComando.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacionalAtendimento");
    	return filtroRAEncerramentoComando;
    }
    
    
    @Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"dataAtendimentoInicial", 
						   "dataAtendimentoFinal",
						   "tempoComando",
						   "tempoRealizacao",
						   "quantidadeRasEncerradas",
						   "quantidadeOsEncerradas",
						   "ultimaAlteracao"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Data Inicial",
						   "Data Final",
						   "Tempo Comando",
						   "Tempo Realizacao",
						   "Qtd RA Encerrados", 
						   "Qtd OS Encerradas",
						   "Ultima Alteracao"};
		return labels;		
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId() + "";
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarCaminhoParaCarregamentoEntidade("usuario");
		filtro.adicionarCaminhoParaCarregamentoEntidade("atendimentoMotivoEncerramento");
		filtro.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacionalAtual");
		filtro.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacionalSuperior");
		filtro.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacionalAtendimento");
		return filtro;
	}
	
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}
    

}

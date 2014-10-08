package gcom.cobranca;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Hugo Azevedo
 **/
@ControleAlteracao()
public class MotivoNaoGeracaoCobrancaResultado  extends ObjetoTransacao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int ATUALIZA_MOTIVO_NAO_GERACAO_COBR_RESULT = 1925;
	
	public static final int REMOVE_MOTIVO_NAO_GERACAO_COBR_RESULT = 1926;
	
	public static final Integer MOTIVO_IMOVEL_EXCLUIDO = new Integer(1);
	
	public static final Integer MOTIVO_IMOVEL_EM_COBRANCA = new Integer(2);
	
	public static final Integer MOTIVO_CATEGORIA_DIFERENTE_FILTRO= new Integer(3);
	
	public static final Integer MOTIVO_PERFIL_DIFERENTE_FILTRO = new Integer(4);
	
	public static final Integer MOTIVO_SIT_LIG_AGUA_DIFERENTE_FILTRO = new Integer(5);
	
	public static final Integer MOTIVO_IMOVEL_SEM_DEBITOS = new Integer(6);
	
	public static final Integer MOTIVO_CONTAS_EM_REVISAO_TIPO_IMOVEL = new Integer(7);
	
	public static final Integer MOTIVO_CONTAS_DIF_NORMAL_INC_RET_TIPO_IMOVEL = new Integer(8);
	
	public static final Integer MOTIVO_CONTAS_FORA_PERIODO_REFERENCIA_TIPO_IMOVEL = new Integer(9);
	
	public static final Integer MOTIVO_CONTAS_FORA_PERIODO_VENCIMENTO_TIPO_IMOVEL = new Integer(10);
	
	public static final Integer MOTIVO_CONTAS_SEM_QTD_DIAS_VENCIMENTO_TIPO_IMOVEL = new Integer(11);
	
	public static final Integer MOTIVO_CONTAS_VALOR_FORA_FAIXA_VL_CONTA_TIPO_IMOVEL = new Integer(12);
	
	public static final Integer MOTIVO_CONTAS_FORA_CRITERIO = new Integer(13);
	
	public static final Integer MOTIVO_QTD_CONTAS_FORA_FAIXA = new Integer(14);
	
	public static final Integer MOTIVO_QTD_CONTAS_MENOR_QTD_MENOR_FAIXA_EMPR = new Integer(15);
	
	public static final Integer MOTIVO_VL_TOTAL_DEBITO_FORA_FAIXA = new Integer(16);
	
	public static final Integer MOTIVO_NAO_PREVISTO_IMOVEL = new Integer(17);
	
	public static final Integer MOTIVO_CONTAS_EM_REVISAO_TIPO_CONTA = new Integer(18);
	
	public static final Integer MOTIVO_CONTAS_DIF_NORMAL_INC_RET_TIPO_CONTA= new Integer(19);
	
	public static final Integer MOTIVO_CONTAS_FORA_PERIODO_REFERENCIA_TIPO_CONTA= new Integer(20);
	
	public static final Integer MOTIVO_CONTAS_FORA_PERIODO_VENCIMENTO_TIPO_CONTA= new Integer(21);
	
	public static final Integer MOTIVO_CONTAS_SEM_QTD_DIAS_VENCIMENTO_TIPO_CONTA= new Integer(22);
	
	public static final Integer MOTIVO_CONTAS_VALOR_FORA_FAIXA_VL_CONTA_TIPO_CONTA= new Integer(23);
	
	public static final Integer MOTIVO_NAO_PREVISTO_CONTA = new Integer(24);
	
	public static final int PAGAMENTO_SITUACAO_DIFERENTE_DE_CLASSIFICADO_A_CONTABILIZAR_E_VALOR_NAO_CONFERE = new Integer(25);
	
	public static final int DATA_PAGAMENTO_FORA_PERIODO_VALIDADE_COMANDO = new Integer(26);
	
	public static final int DATA_PAGAMENTO_SUPERIOR_DATA_ENCERRAMENTO_CONTRATO_EMPRESA = new Integer(27);

	public static final int DATA_PAGAMENTO_MENOR_IGUAL_DATA_VENCIMENTO_MAIS_QTD_DIAS_INFORMADA = new Integer(28);
	
	public static final int IMOVEL_JA_LIGADO_SIT_COBRANCA_INVALIDA_EMP_COBRANCA = new Integer(29);
	
	public static final int IMOVEL_SIT_ESPECIAL_COBRANCA = new Integer(30);
	
	public static final int CONTAS_REF_MAIOR_MAIOR_IGUAL_REF_ARRECADACAO_TIPO_IMOVEL = new Integer(31);
	
	public static final int CONTAS_REF_MAIOR_MAIOR_IGUAL_REF_ARRECADACAO_TIPO_CONTA = new Integer(32);
	
	public static final int IMOVEL_NAO_INCLUIDO_PROP_COMANDO = new Integer(33);
	
	public static final int IMOVEL_POSSUI_DOC_COB_ACAO_COBRANCA = new Integer(34);
	
	public static final int IMOVEL_COM_PERFIL_ATUAL_IGUAL_A_PUBLICO = new Integer(35);
	
	public static final int IMOVEL_NAO_POSSUI_SITUACAO_COBRANCA_SELECIONADA = new Integer(36);
	
	private Integer id;	
	@ControleAlteracao(funcionalidade={ATUALIZA_MOTIVO_NAO_GERACAO_COBR_RESULT,REMOVE_MOTIVO_NAO_GERACAO_COBR_RESULT})
	private String descricao;
	
	@ControleAlteracao(funcionalidade={ATUALIZA_MOTIVO_NAO_GERACAO_COBR_RESULT,REMOVE_MOTIVO_NAO_GERACAO_COBR_RESULT})
	private String descricaoAbreviada;
	
	@ControleAlteracao(funcionalidade={ATUALIZA_MOTIVO_NAO_GERACAO_COBR_RESULT,REMOVE_MOTIVO_NAO_GERACAO_COBR_RESULT})
	private Short indicadorUso;
	
	@ControleAlteracao(funcionalidade={ATUALIZA_MOTIVO_NAO_GERACAO_COBR_RESULT,REMOVE_MOTIVO_NAO_GERACAO_COBR_RESULT})
	private Integer codigoConstante;
	
	@ControleAlteracao(funcionalidade={ATUALIZA_MOTIVO_NAO_GERACAO_COBR_RESULT,REMOVE_MOTIVO_NAO_GERACAO_COBR_RESULT})
	private Integer codigoTipoMotivo;
	
	private Date ultimaAlteracao;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Short getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public Integer getCodigoConstante() {
		return codigoConstante;
	}
	public void setCodigoConstante(Integer codigoConstante) {
		this.codigoConstante = codigoConstante;
	}
	public Integer getCodigoTipoMotivo() {
		return codigoTipoMotivo;
	}
	public void setCodigoTipoMotivo(Integer codigoTipoMotivo) {
		this.codigoTipoMotivo = codigoTipoMotivo;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}
	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}
	
	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = { "descricao", 
				"descricaoAbreviada",
				"indicadorUso",
				"codigoConstante", 
				"codigoTipoMotivo",};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Descricao",
						"Descricao Abrev.",
						"Ind. de Uso",
						"Cod. Constante",
						"Cod. Tipo Motivo"};
		return labels;		
	}
	
	@Override
	public Filtro retornaFiltro() {
		FiltroMotivoNaoGeracaoCobrancaResultado filtro = new FiltroMotivoNaoGeracaoCobrancaResultado();
		filtro.adicionarParametro(new ParametroSimples(FiltroMotivoNaoGeracaoCobrancaResultado.ID,this.getId()));
		return filtro;
	}

	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		
		return filtro;
	}
}

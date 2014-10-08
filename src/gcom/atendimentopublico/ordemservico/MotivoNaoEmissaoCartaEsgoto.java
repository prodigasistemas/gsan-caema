package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;

public class MotivoNaoEmissaoCartaEsgoto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String descricao;
	
	private Date ultimaAlteracao;
	
	public final static Integer IMOVEL_NAO_MEDIDO_E_ENDERECO_ALTERNATIVO = 1;
	
	public final static Integer SITUACAO_LIGACAO_POTENCIA_FACTIVEL = 2;
	
	public final static Integer  ANORMALIDADE_IMPEDE_IMPRESSAO_CONTA = 3;
	
	public final static Integer CONTA_VALORES_IMPEDITIVOS_IMPRESSAO = 4;
	
	public final static Integer CONTA_ENTREGUE_OUTRO_ENDERECO = 5;
	
	public final static Integer IMOVEL_SEM_FATURAMENTO = 6;
	
	public final static Integer ROTEIRO_IMOVEL_FINALIZADO_INCOMPLETO = 7;
	
	
	
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

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

}

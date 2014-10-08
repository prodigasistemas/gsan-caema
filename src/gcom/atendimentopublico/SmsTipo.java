package gcom.atendimentopublico;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class SmsTipo extends ObjetoTransacao  {

	
	private static final long serialVersionUID = 1L;

	public static final Integer ENVIO_RA_INSERIDA = 1;
	public static final Integer ENVIO_RA_EXISTENTE = 2;
	public static final Integer ENVIO_SERVICO_REALIZADO = 3;
	public static final Integer ENVIO_ERRO = 4;
	public static final Integer ORDEM_SERVICO_CONCLUIDA_COM_SUCESSO = 5;
	
	private Integer id;
	private String descricaoTipo;
	private Short indicadorUso;
	private Date ultimaAlteracao;
	
	
	public SmsTipo() {
		super();
	}
	
	public SmsTipo(String descricaoTipo, Short indicadorUso,
			Date ultimaAlteracao) {
		super();
		this.descricaoTipo = descricaoTipo;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoTipo() {
		return descricaoTipo;
	}

	public void setDescricaoTipo(String descricaoTipo) {
		this.descricaoTipo = descricaoTipo;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}

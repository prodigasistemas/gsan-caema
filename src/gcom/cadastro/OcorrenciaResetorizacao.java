package gcom.cadastro;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

/**
 * [UC 1386] - Processar Arquivo de Resetorização dos Imóveis
 * 
 * @author Davi Menezes
 * @date 30/10/2012
 *
 */
public class OcorrenciaResetorizacao extends ObjetoTransacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String descricaoMensagem;
	
	private Date ultimaAlteracao;
	
	public final static Integer MATRICULA_IMOVEL_EXISTENTE = new Integer(1);
	
	public final static Integer CODIGO_SETOR_NAO_CADASTRADO = new Integer(2);
	
	public final static Integer NUMERO_QUADRA_NAO_CADASTRADO = new Integer(3);
	
	public final static Integer SETOR_QUADRA_NAO_ASSOCIADO_LOCALIDADE = new Integer(4);
	
	public final static Integer MATRICULA_IMOVEL_NAO_CADASTRADA = new Integer(5);
	
	public final static Integer LOCALIDADE_NAO_CADASTRADA = new Integer(6);
	
	/* Minimal Constructor */
	public OcorrenciaResetorizacao(Integer id) {
		this.id = id;
	}
	
	/* Default Constructor */
	public OcorrenciaResetorizacao(){
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoMensagem() {
		return descricaoMensagem;
	}

	public void setDescricaoMensagem(String descricaoMensagem) {
		this.descricaoMensagem = descricaoMensagem;
	}

	@Override
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroOcorrenciaResetorizacao filtroOcorrenciaResetorizacao = new FiltroOcorrenciaResetorizacao();
		filtroOcorrenciaResetorizacao.adicionarParametro(new ParametroSimples(FiltroOcorrenciaResetorizacao.ID, this.getId()));
		
		return filtroOcorrenciaResetorizacao;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

}

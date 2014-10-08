package gcom.operacional;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import java.util.Date;

/**
 * [UC1523] - Atualizar Subsistema Esgoto
 * 
 * @author Anderson Cabral
 * @date 07/07/2012
 *
 */
public class SubSistemaEsgotoArquivoTextoErro extends ObjetoTransacao{

	private static final long serialVersionUID = 1L;

    private Integer id;
    private SubSistemaEsgotoArquivoTexto subSistemaEsgotoArquivoTexto;
    private String descricaoRegistro;
    private String mensagemErro;
    private Date ultimaAlteracao;

	public SubSistemaEsgotoArquivoTextoErro() {
		super();	
	}

	public SubSistemaEsgotoArquivoTextoErro(Integer id,
			SubSistemaEsgotoArquivoTexto subSistemaEsgotoArquivoTexto,
			String descricaoRegistro, String mensagemErro, Date ultimaAlteracao) {
		super();
		this.id = id;
		this.subSistemaEsgotoArquivoTexto = subSistemaEsgotoArquivoTexto;
		this.descricaoRegistro = descricaoRegistro;
		this.mensagemErro = mensagemErro;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public SubSistemaEsgotoArquivoTexto getSubSistemaEsgotoArquivoTexto() {
		return subSistemaEsgotoArquivoTexto;
	}

	public void setSubSistemaEsgotoArquivoTexto(
			SubSistemaEsgotoArquivoTexto subSistemaEsgotoArquivoTexto) {
		this.subSistemaEsgotoArquivoTexto = subSistemaEsgotoArquivoTexto;
	}

	public String getDescricaoRegistro() {
		return descricaoRegistro;
	}
	public void setDescricaoRegistro(String descricaoRegistro) {
		this.descricaoRegistro = descricaoRegistro;
	}
	public String getMensagemErro() {
		return mensagemErro;
	}
	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}
    
        
}

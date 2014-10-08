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
public class SubSistemaEsgotoArquivoTexto extends ObjetoTransacao{

	private static final long serialVersionUID = 1L;
	
    private Integer id;
    private String descricaoArquivo;
    private Integer quantidadeImoveisLidos;
    private Integer quantidadeImoveisAtualizado;
    private Date ultimaAlteracao;

	public SubSistemaEsgotoArquivoTexto() {
		super();
	}
	
	public SubSistemaEsgotoArquivoTexto(Integer id, String descricaoArquivo,
			Integer quantidadeImoveisLidos,
			Integer quantidadeImoveisAtualizado, Date ultimaAlteracao) {
		super();
		this.id = id;
		this.descricaoArquivo = descricaoArquivo;
		this.quantidadeImoveisLidos = quantidadeImoveisLidos;
		this.quantidadeImoveisAtualizado = quantidadeImoveisAtualizado;
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricaoArquivo() {
		return descricaoArquivo;
	}
	public void setDescricaoArquivo(String descricaoArquivo) {
		this.descricaoArquivo = descricaoArquivo;
	}
	public Integer getQuantidadeImoveisLidos() {
		return quantidadeImoveisLidos;
	}
	public void setQuantidadeImoveisLidos(Integer quantidadeImoveisLidos) {
		this.quantidadeImoveisLidos = quantidadeImoveisLidos;
	}
	public Integer getQuantidadeImoveisAtualizado() {
		return quantidadeImoveisAtualizado;
	}
	public void setQuantidadeImoveisAtualizado(Integer quantidadeImoveisAtualizado) {
		this.quantidadeImoveisAtualizado = quantidadeImoveisAtualizado;
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

package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;

public class RelatorioArquivosSubSistemaErroBean  implements RelatorioBean {

    private String descricaoRegistro;
    private String mensagemErro;
    private String ultimaAlteracao;

	public RelatorioArquivosSubSistemaErroBean(String descricaoRegistro,
			String mensagemErro, String ultimaAlteracao) {
		super();
		this.descricaoRegistro = descricaoRegistro;
		this.mensagemErro = mensagemErro;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public RelatorioArquivosSubSistemaErroBean() {
		super();
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
	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
  
}

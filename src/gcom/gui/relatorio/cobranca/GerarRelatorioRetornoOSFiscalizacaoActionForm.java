package gcom.gui.relatorio.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * [UC1254] Relat�rio Ordem de Servi�o com valores de cobran�a
 * @author Ana Maria
 * @since 24/07/2012
 */
public class GerarRelatorioRetornoOSFiscalizacaoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String mesAno;
	private String[] situacoes;	
	
	public GerarRelatorioRetornoOSFiscalizacaoActionForm() {
		super();
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String[] getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(String[] situacoes) {
		this.situacoes = situacoes;
	}
	
}
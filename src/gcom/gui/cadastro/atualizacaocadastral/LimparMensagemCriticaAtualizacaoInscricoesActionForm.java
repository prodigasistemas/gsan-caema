package gcom.gui.cadastro.atualizacaocadastral;

import org.apache.struts.action.ActionForm;

/**
 * [UC 1389] - Limpar Mensagens de Crítica Para Atualização das Inscrições - GSAN
 * 
 * @author Davi Menezes
 * @date 09/11/2012
 *
 */
public class LimparMensagemCriticaAtualizacaoInscricoesActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String idLocalidade;
	
	private String codigoSetorComercial;
	
	private String mensagemCritica;
	
	private String[] idsRegistro;

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getMensagemCritica() {
		return mensagemCritica;
	}

	public void setMensagemCritica(String mensagemCritica) {
		this.mensagemCritica = mensagemCritica;
	}
	
	public String[] getIdsRegistro() {
		return idsRegistro;
	}

	public void setIdsRegistro(String[] idsRegistro) {
		this.idsRegistro = idsRegistro;
	}
	
}

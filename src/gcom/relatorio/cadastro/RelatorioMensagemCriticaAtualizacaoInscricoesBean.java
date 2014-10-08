package gcom.relatorio.cadastro;

import gcom.relatorio.RelatorioBean;

/**
 * [UC 1389] - Limpar Mensagens de Crítica Para Atualização das Inscrições
 * 
 * @author Davi Menezes
 * @date 12/11/2012
 *
 */
public class RelatorioMensagemCriticaAtualizacaoInscricoesBean implements RelatorioBean {

	private String idImovel;
	private String idMensagemCritica;
	private String descricaoMensagemCritica;
	
	public String getIdImovel() {
		return idImovel;
	}
	
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	
	public String getIdMensagemCritica() {
		return idMensagemCritica;
	}

	public void setIdMensagemCritica(String idMensagemCritica) {
		this.idMensagemCritica = idMensagemCritica;
	}

	public String getDescricaoMensagemCritica() {
		return descricaoMensagemCritica;
	}
	
	public void setDescricaoMensagemCritica(String descricaoMensagemCritica) {
		this.descricaoMensagemCritica = descricaoMensagemCritica;
	}
	
}

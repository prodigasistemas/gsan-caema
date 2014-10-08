package gcom.autoatendimento;

import gcom.gui.faturamento.bean.Emitir2ViaDeclaracaoAnualQuitacaoDebitosHelper;

import java.io.Serializable;
import java.util.Collection;

/**
 * [UC1546] - Consultar 2 Via de Conta
 * Classe que representa as contas que seram listadas no totem
 * 
 * @author Anderson Cabral
 * @date 27/08/2013
 */
public class DeclaracaoQuitacaoConsultarHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Collection<Emitir2ViaDeclaracaoAnualQuitacaoDebitosHelper> declaracao;
	
	public DeclaracaoQuitacaoConsultarHelper() {
		super();
	}

	public Collection<Emitir2ViaDeclaracaoAnualQuitacaoDebitosHelper> getDeclaracao() {
		return declaracao;
	}

	public void setDeclaracao(Collection<Emitir2ViaDeclaracaoAnualQuitacaoDebitosHelper> declaracao) {
		this.declaracao = declaracao;
	}
	
	
}

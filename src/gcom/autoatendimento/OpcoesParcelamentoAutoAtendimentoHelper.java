package gcom.autoatendimento;

import java.io.Serializable;
import java.util.ArrayList;

public class OpcoesParcelamentoAutoAtendimentoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	ArrayList<OpcaoParcelamentoAutoAtendimentoHelper> opcao;

	public ArrayList<OpcaoParcelamentoAutoAtendimentoHelper> getOpcao() {
		return opcao;
	}

	public void setOpcao(ArrayList<OpcaoParcelamentoAutoAtendimentoHelper> opcao) {
		this.opcao = opcao;
	}
}

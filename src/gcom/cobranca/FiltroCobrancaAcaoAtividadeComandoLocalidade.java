package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroCobrancaAcaoAtividadeComandoLocalidade extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	public FiltroCobrancaAcaoAtividadeComandoLocalidade() {
		super();
	}
	
	public final static String ID = "id";
	
	public final static String ID_COB_ACAO_ATV_COMAND = "cobrancaAcaoAtividadeComando.id";
	
	public final static String ID_LOCALIDADE = "localidade.id";

}

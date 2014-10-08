package gcom.faturamento.contratodemanda;

import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ObjetoGcom;

/**
 * [UC 0512] - Inserir Contrato de Demanda
 * 
 * @author Davi Menezes
 * @date 19/04/2013
 *
 */
public class ContratoDemandaImovelComercialIndustrialPK extends ObjetoGcom {

	private static final long serialVersionUID = 1L;
	
	private ContratoDemandaComercialIndustrial contratoDemandaComercialIndustrial;
	
	private Imovel imovel;
	
	public ContratoDemandaComercialIndustrial getContratoDemandaComercialIndustrial() {
		return contratoDemandaComercialIndustrial;
	}

	public void setContratoDemandaComercialIndustrial(ContratoDemandaComercialIndustrial contratoDemandaComercialIndustrial) {
		this.contratoDemandaComercialIndustrial = contratoDemandaComercialIndustrial;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[2];
 		retorno[0] = "contratoDemandaComercialIndustrial";
 		retorno[1] = "imovel";
 		return retorno;
	}

}

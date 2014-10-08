package gcom.faturamento.contratodemanda;

import gcom.util.filtro.Filtro;

public class FiltroContratoDemandaImovelComercialIndustrial extends Filtro {

	public FiltroContratoDemandaImovelComercialIndustrial() {
	}
	
	public FiltroContratoDemandaImovelComercialIndustrial(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}

	private static final long serialVersionUID = 1L;
	
	public final static String ID_IMOVEL = "comp_id.imovel.id";
	
	public final static String IMOVEL = "comp_id.imovel";
	
	public final static String ID_CONTRATO_DEMANDA_COMERCIAL_INDUSTRIAL = "comp_id.contratoDemandaComercialIndustrial.id";
	
	public final static String CONTRATO_DEMANDA_COMERCIAL_INDUSTRIAL = "comp_id.contratoDemandaComercialIndustrial";
	
	public final static String VOLUME_INFORMADO_ESGOTO_RATEADO = "volumeInformadoEsgotoRateado";
	
	public final static String INDICADOR_PRINCIPAL = "indicadorPrincipal";
	
	public final static String NUMERO_CONTRATO = "comp_id.contratoDemandaComercialIndustrial.numeroContrato";
	
	public final static String DATA_CONTRATO_INICIO = "comp_id.contratoDemandaComercialIndustrial.dataContratoInicio";
	
	public final static String DATA_CONTRATO_FIM = "comp_id.contratoDemandaComercialIndustrial.dataContratoFim";
	
	public final static String DATA_CONTRATO_ENCERRAMENTO = "comp_id.contratoDemandaComercialIndustrial.dataContratoEncerrado";
	
	public final static String DATA_CONTRATO_ENCERRADO = "dataContratoEncerrado";
	
}

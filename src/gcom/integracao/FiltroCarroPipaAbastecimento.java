package gcom.integracao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroCarroPipaAbastecimento extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroCarroPipaAbastecimento(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}
	
	public FiltroCarroPipaAbastecimento(){		
	}
	
	/**
     * Description of the Field
     */
    public final static String ID = "id";
    
    /**
     * Description of the Field
     */
    public final static String DESCRICAO_PLACA = "descricaoPlaca";
   
    /**
     * Description of the Field
     */
    public final static String DATA_ABASTECIMENTO = "dataAbastecimento";
    
    /**
     * Description of the Field
     */
    public final static String IMOVEL_ID = "imovel.id";
    
    /**
     * Description of the Field
     */
    public final static String IMOVEL = "imovel";
    
    /**
     * Description of the Field
     */
    public final static String VOLUME_ABASTECIMENTO = "volumeAbastecimento";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_COBRANCA = "indicadorCobranca";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_ABASTECIMENTO_AVULSO = "indicadorAbastecimentoAvulso";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_DEBITO_A_COBRAR_INCLUIDO = "indicadorDebitoACobrarIncluido";
    
    public final static String AM_REFERENCIA_FATURAMENTO = "amReferenciaFaturamento";
    

}

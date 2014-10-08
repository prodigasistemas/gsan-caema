package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroComandoEmpresaCobrancaContaFaixa extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroComandoEmpresaCobrancaContaFaixa(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroComandoEmpresaCobrancaContaFaixa() {
    }

    /**
     * Description of the Field
     */
    public final static String COMANDO_EMPRESA_COBRANCA_CONTA_ID = "comp_id.comandoEmpresaCobrancaContaId";
    
    public final static String EMPR_COB_FAIXA_ID = "comp_id.empresaCobrancaFaixaId";
    
    public final static String EMPRESA_COBRANCA_FAIXA = "empresaCobrancaFaixa";
    
}
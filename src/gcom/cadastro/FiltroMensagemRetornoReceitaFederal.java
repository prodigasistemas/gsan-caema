package gcom.cadastro;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

public class FiltroMensagemRetornoReceitaFederal extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroMensagemRetornoReceitaFederal object
     */
	public FiltroMensagemRetornoReceitaFederal(){
	}
    /**
     * Constructor for the FiltroMensagemRetornoReceitaFederal object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
	public FiltroMensagemRetornoReceitaFederal(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}
	/**
     * Description of the Field
     */
	public final static String ID = "id";
	
	/**
     * Description of the Field
     */
	public final static String CODIGO_MENSAGEM_RETORNO = "codigoMensagemRetorno";
	public final static String DESCRICAO_MENSAGEM_RETORNO = "descricaoMensagemRetorno";
	public final static String DATA_ULTIMA_ALTERACA0 = "dataUltimaAlteracao";

}

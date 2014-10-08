package gcom.cadastro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * [UC 1386] - Processar Arquivo de Resetorização dos Imóveis
 * 
 * @author Davi Menezes
 * @date 30/10/2012
 *
 */
public class FiltroImovelInscricaoResetorizada extends Filtro implements Serializable {
	
	public FiltroImovelInscricaoResetorizada() {
	
	}
	
	public FiltroImovelInscricaoResetorizada(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}

	private static final long serialVersionUID = 1L;
	
	public final static String ID = "id";
	
	public final static String CODIGO_SETOR_COMERCIAL = "codigoSetorComercial";
	
	public final static String NUMERO_QUADRA = "numeroQuadra";
	
	public final static String LOTE = "numeroLote";
	
	public final static String SUB_LOTE = "numeroSubLote";
	
	public final static String INDICADOR_ATUALIZACAO = "indicadorAtualizacao";
	
	public final static String IMOVEL = "imovel";
	
	public final static String ID_IMOVEL = "imovel.id";
	
	public final static String LOCALIDADE = "localidade";
	
	public final static String ID_LOCALIDADE = "localidade.id";
	
	public final static String OCORRENCIA_RESETORIZACAO = "ocorrenciaResetorizacao";
	
	public final static String ID_OCORRENCIA_RESETORIZACAO = "ocorrenciaResetorizacao.id";

}

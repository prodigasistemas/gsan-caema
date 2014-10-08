package gcom.cadastro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * [UC1561] - Liberar os Logradouros para Atualização no GSAN
 * 
 * @author Anderson Cabral
 * @since 25/09/2013
 */
public class FiltroImovelEnderecoArquivoTexto extends Filtro implements Serializable{
	
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroImovelEnderecoArquivoTexto object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroImovelEnderecoArquivoTexto(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
    
    /**
     * Construtor da classe FiltroImovelEnderecoArquivoTexto
     */
    public FiltroImovelEnderecoArquivoTexto() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    public final static String INDICADOR_ATUALIZACAO = "indicadorAtualizacao";
    public final static String MUNICIPIO_ID = "logradouro.municipio.id";
    public final static String LOGRADOURO = "logradouro";
    public final static String MUNICIPIO = "logradouro.municipio";
    public final static String BAIRRO = "bairro";
    public final static String IMOVEL = "imovel";
    public final static String SETOR_COMERCIAL = "imovel.setorComercial";
    public final static String QUADRA = "imovel.quadra";
    public final static String LOCALIDADE = "imovel.localidade";
    public final static String CEP = "cep";
    
}

package gcom.cadastro;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;


/** @author Hibernate CodeGenerator */
public class AtributoAtualizacaoCadastral extends ObjetoTransacao{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private String nomeAtributo;

    /** persistent field */
    private Date ultimaAlteracao;
    
    public static final Integer CPF_CNPJ = new Integer("1");
    public static final Integer CATEGORIA_SUBCATEGORIA = new Integer("2");
    public static final Integer SITUACAO_LIGACAO_AGUA = new Integer("3");
    public static final Integer ECONOMIA = new Integer("4");
    public static final Integer SITUACAO_HIDROMETRO = new Integer("5");
    public static final Integer IMOVEL = new Integer("6");
    public static final Integer INSCRICAO = new Integer("7");
    public static final Integer LOGRADOURO = new Integer("8");
    public static final Integer SITUACAO_LIGACAO_ESGOTO = new Integer("9");
    public static final Integer CLIENTE = new Integer(10);

    public static final Integer AREA_CONSTRUIDA  = new Integer(11);
//    public static final Integer CLIENTE_USUARIO = new Integer(12);
//    public static final Integer CLIENTE_PROPRIETARIO = new Integer(13);
    public static final Integer NUMERO_CONTRATO_DA_COMPANHIA_ENERGETICA = new Integer(14);
//  public static final Integer ECONOMIAS_POR_SUBCATEGORIA = new Integer(15);
//  public static final Integer CLIENTE_USUARIO_DESCONHECIDO_NULO = new Integer(16);
    
    public static final Integer LOCALIDADE = new Integer(17);
    public static final Integer SETOR_COMERCIAL = new Integer(18);
    public static final Integer QUADRA = new Integer(19);
    public static final Integer LOTE = new Integer(20);
    public static final Integer SUBLOTE = new Integer(21);
    public static final Integer PAVIMENTO_RUA = new Integer(22);
    public static final Integer PAVIMENTO_CALCADA = new Integer(23);
    
    public static final Integer LOGRADOURO_BAIRRO = new Integer(24);
    public static final Integer LOGRADOURO_CEP = new Integer(25);
    
    public static final Integer NUMERO_HIDROMETRO = new Integer(26);
    public static final Integer DATA_INST_HIDROMETRO = new Integer(27);
    public static final Integer HIDROMETRO_MEDICAO_TIPO = new Integer(28);
    public static final Integer LOCAL_INST_HIDROMETRO = new Integer(29);
    public static final Integer PROTECAO_HIDROMETRO = new Integer(30);
    public static final Integer LEITURA_INST_HIDROMETRO = new Integer(31);
    
    public AtributoAtualizacaoCadastral(){
    	
    }
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeAtributo() {
		return nomeAtributo;
	}

	public void setNomeAtributo(String nomeAtributo) {
		this.nomeAtributo = nomeAtributo;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		// TODO Auto-generated method stub
		return null;
	}

   
    
}

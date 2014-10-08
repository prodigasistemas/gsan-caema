package gcom.cadastro;

import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class MensagemAtualizacaoCadastral implements Serializable {

    /**
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private String mensagem;

    /** persistent field */
    private Date ultimaAlteracao;
    
    private Short indicadorOpcaoAprovado;
    
    public static final Integer CPF_CNPJ_NAO_INFORMADO = new Integer(1);
    
    public static final Integer CATEGORIA_INCONSISTENTE = new Integer(2);
    
    public static final Integer ALTERACAO_DA_LIGACAO_DE_AGUA_COM_REDUCAO_DE_FATURAMENTO = new Integer(3);
    
    public static final Integer SITUACAO_HIDROMETRO_INCONSISTENTE = new Integer(4);
    
    public static final Integer QUANTIDADE_ECONOMIA_INCONSISTENTE = new Integer(5);
    
    public static final Integer IMOVEL_CATEGORIA_COM_PUB_IND_ASSOCIADO_CPF = new Integer(6);
    
    public static final Integer IMOVEL_CATEGORIA_RES_ASSOCIADO_CNPJ = new Integer(7);
    
    public static final Integer IMOVEL_EXCLUIDO = new Integer(8);
    
    public static final Integer PENDENTE_POR_INSCRICAO = new Integer(9);
    
    public static final Integer ATUALIZACAO_COM_SUCESSO = new Integer(10);
    
    public static final Integer IMOVEL_POSSUI_MAIS_DE_UMA_CATEGORIA = new Integer(11);
    
    public static final Integer HIDROMETRO_INEXISTENTE = new Integer(12);
    
    public static final Integer HIDROMETRO_JA_INSTALADO_OUTRO_IMOVEL = new Integer(13);
    
    public static final Integer HIDROMETRO_NAO_PODE_SER_INSTALADO = new Integer(14);
    
//    public static final Integer HIDROMETRO_NAO_ESTA_NA_LOCALIDADE_DO_IMOVEL = new Integer(15);
    
    public static final Integer SITUACAO_LIGACAO_AGUA_INVALIDA = new Integer(16);
    
    public static final Integer HIDROMETRO_INSTALADO_LIGACAO_AGUA = new Integer(17);
    
    public static final Integer SITUACAO_LIGACAO_ESGOTO_INVALIDA = new Integer(18);
    
    public static final Integer INFORMACAO_OBRIGATORIA = new Integer(19);
    
    public static final Integer HIDROMETRO_INSTALADO_POCO = new Integer(20);
    
//    public static final Integer SITUACAO_LIGACAO_AGUA_INVALIDA_PARA_EFETUAR_LIGACAO = new Integer(21);
    
//    public static final Integer SITUACAO_LIGACAO_ESGOTO_INVALIDA_PARA_EFETUAR_LIGACAO = new Integer(22);
    
    public static final Integer ATUALIZACAO_PENDENTE_POR_LOGRADOURO = new Integer(23);
    
    public static final Integer CPF_CNPJ_ASSOCIADO_RA = new Integer(24);
    
    public static final Integer PERFIL_IMOVEL_TS_NAO_ATUALIZADA = new Integer(25);
    
    public static final Integer ALERTA_DE_TARIFA_SOCIAL = new Integer(26);
    
    public static final Integer IMOVEL_INATIVO = new Integer(27);
    
    public static final Integer IMOVEL_ASSOCIADO_A_MAIS_DE_UM_CLIENTE_USUARIO = new Integer(29);
    
    public static final Integer CLIENTE_USUARIO_OBRIGATORIO = new Integer(30);
    
    public static final Integer NUMERO_CPF_CNPJ_INVALIDO = new Integer(32);
    
    public static final Integer CLIENTE_PROPRIETARIO_NAO_PODE_SER_ALTERADO = new Integer(33);

//    public static final Integer VERIFICAR_PERFIL_PARA_TARIFA_SOCIAL = new Integer(34);

    public static final Integer CLIENTE_IMOVEL_PUBLICO_NAO_PODE_SER_ATUALIZADO = new Integer(35);
    
    public static final Integer CLIENTE_TS_NAO_PODE_SER_ATUALIZADO = new Integer(36);
    
    public static final Integer CLIENTE_INATIVO_NAO_PODE_SER_ATUALIZADO = new Integer(37);
    
    public static final Integer IMOVEL_INSCRICAO_EM_DUPLICIDADE = new Integer(38);
    
    public static final Integer CLIENTE_RESPONSAVEL_NAO_PODE_SER_ALTERADO = new Integer(40);

    public static final Integer ALTERACAO_DA_LIGACAO_DE_ESGOTO_COM_REDUCAO_DE_FATURAMENTO = new Integer(41);
    
    public static final Integer ALTERACAO_DA_LIGACAO_DE_AGUA_APOS_ENVIO_A_CONTRATADA = new Integer(42);
    
    public static final Integer ALTERACAO_DA_LIGACAO_DE_AGUA_APENAS_COM_AUTORIZACAO = new Integer(43);
    
    public static final Integer CLIENTE_NEGATIVADO = new Integer(44);
    
    
    public MensagemAtualizacaoCadastral(){
    	
    }


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getMensagem() {
		return mensagem;
	}


	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}


	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}


	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}


	public Short getIndicadorOpcaoAprovado() {
		return indicadorOpcaoAprovado;
	}


	public void setIndicadorOpcaoAprovado(Short indicadorOpcaoAprovado) {
		this.indicadorOpcaoAprovado = indicadorOpcaoAprovado;
	}

}

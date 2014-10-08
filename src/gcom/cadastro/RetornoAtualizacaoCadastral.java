package gcom.cadastro;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;

import java.util.Date;


/** @author Hibernate CodeGenerator */
public class RetornoAtualizacaoCadastral extends ObjetoTransacao {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private Short codigoSituacao;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private MensagemAtualizacaoCadastral mensagemAtualizacaoCadastral;

    /** persistent field */
    private ParametroTabelaAtualizacaoCadastro parametroTabelaAtualizacaoCadastro;

    /** persistent field */
    private AtributoAtualizacaoCadastral atributoAtualizacaoCadastral;

    /** persistent field */
    private Cliente cliente;

    /** persistent field */
    private Imovel imovel;
    
    private ImovelAtualizacaoCadastral imovelAtualizacaoCadastral;
    
    private Short codigoAlteracao;
    
    private MedicaoTipo medicaoTipo;
    
    private Usuario usuario;
    
    private Date dataAtualizacao;
    
    public static final Short SITUACAO_ATUALIZADO = new Short("1");
    public static final Short SITUACAO_PENDENTE = new Short("2");
    public static final Short PENDENTE_POR_INSCRICAO = new Short("3");
    public static final Short PENDENTE_POR_LOGRADOURO = new Short("4");
    
    
    public static final Short APROVADO = new Short("1");
    public static final Short ACEITO = new Short("2");
    public static final Short REPROVADO = new Short("3");

    public RetornoAtualizacaoCadastral(){
    	
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getCodigoSituacao() {
		return codigoSituacao;
	}

	public void setCodigoSituacao(Short codigoSituacao) {
		this.codigoSituacao = codigoSituacao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public MensagemAtualizacaoCadastral getMensagemAtualizacaoCadastral() {
		return mensagemAtualizacaoCadastral;
	}

	public void setMensagemAtualizacaoCadastral(
			MensagemAtualizacaoCadastral mensagemAtualizacaoCadastral) {
		this.mensagemAtualizacaoCadastral = mensagemAtualizacaoCadastral;
	}

	public ParametroTabelaAtualizacaoCadastro getParametroTabelaAtualizacaoCadastro() {
		return parametroTabelaAtualizacaoCadastro;
	}

	public void setParametroTabelaAtualizacaoCadastro(
			ParametroTabelaAtualizacaoCadastro parametroTabelaAtualizacaoCadastro) {
		this.parametroTabelaAtualizacaoCadastro = parametroTabelaAtualizacaoCadastro;
	}

	public AtributoAtualizacaoCadastral getAtributoAtualizacaoCadastral() {
		return atributoAtualizacaoCadastral;
	}

	public void setAtributoAtualizacaoCadastral(
			AtributoAtualizacaoCadastral atributoAtualizacaoCadastral) {
		this.atributoAtualizacaoCadastral = atributoAtualizacaoCadastral;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	

	public ImovelAtualizacaoCadastral getImovelAtualizacaoCadastral() {
		return imovelAtualizacaoCadastral;
	}

	public void setImovelAtualizacaoCadastral(
			ImovelAtualizacaoCadastral imovelAtualizacaoCadastral) {
		this.imovelAtualizacaoCadastral = imovelAtualizacaoCadastral;
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

	public Short getCodigoAlteracao() {
		return codigoAlteracao;
	}

	public void setCodigoAlteracao(Short codigoAlteracao) {
		this.codigoAlteracao = codigoAlteracao;
	}

	public MedicaoTipo getMedicaoTipo() {
		return medicaoTipo;
	}

	public void setMedicaoTipo(MedicaoTipo medicaoTipo) {
		this.medicaoTipo = medicaoTipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

}


package gcom.cadastro.imovel;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 * @created 23 de setembro de 2012
 */
@ControleAlteracao()
public class ImovelSubcategoriaHistorico extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

		/**
	 * persistent field
	 */
	@ControleAlteracao(funcionalidade={Imovel.ATRIBUTOS_IMOVEL_INSERIR, Imovel.ATRIBUTOS_IMOVEL_ATUALIZAR,Imovel.ATRIBUTOS_IMOVEL_REMOVER,Imovel.OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private short quantidadeEconomias;

	/**
	 * nullable persistent field
	 */
	private Integer id;
	
	private Short quantidadeUnidadesPrivativas;
	
	private Short quantidadeUnidadesColetivas;
	
	private Date ultimaAlteracao;
	
	private Date dataInclusao;
	
	private Date dataExclusao;
	
	private Date dataUltimaAlteracao;

	/**
	 * identifier field
	 */
	 /** identifier field */
    private gcom.cadastro.imovel.Imovel imovel;

    /** identifier field */
    private gcom.cadastro.imovel.Subcategoria subcategoriaHistorico;
    

    

	/**
	 * Construtor de ImovelSubcategoria 
	 * 
	 * @param comp_id
	 * @param quantidadeEconomias
	 * @param ultimaAlteracao
	 */
	public ImovelSubcategoriaHistorico(	Integer id,	short quantidadeEconomias, Date ultimaAlteracao) {
		this.id = id;
		this.quantidadeEconomias = quantidadeEconomias;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * default constructor
	 */
	public ImovelSubcategoriaHistorico() {
	}

	/**
	 * Construtor de ImovelSubcategoria 
	 * 
	 * @param comp_id
	 * @param quantidadeEconomias
	 */
	public ImovelSubcategoriaHistorico(	Integer id,	short quantidadeEconomias) {
		this.id = id;
		this.quantidadeEconomias = quantidadeEconomias;
	}
	
	public ImovelSubcategoriaHistorico(	Integer id,
			short quantidadeEconomias, Short quantidadeUnidadesPrivativas, Short quantidadeUnidadesColetivas,
			Date ultimaAlteracao) {
		this.id = id;
		this.quantidadeEconomias = quantidadeEconomias;
		this.quantidadeUnidadesPrivativas = quantidadeUnidadesPrivativas;
		this.quantidadeUnidadesColetivas = quantidadeUnidadesColetivas;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the quantidadeEconomias attribute of the ImovelSubcategoria object
	 * 
	 * @return The quantidadeEconomias value
	 */
	public short getQuantidadeEconomias() {
		return this.quantidadeEconomias;
	}

	/**
	 * Sets the quantidadeEconomias attribute of the ImovelSubcategoria object
	 * 
	 * @param quantidadeEconomias
	 *            The new quantidadeEconomias value
	 */
	public void setQuantidadeEconomias(short quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	/**
	 * Gets the ultimaAlteracao attribute of the ImovelSubcategoria object
	 * 
	 * @return The ultimaAlteracao value
	 */
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	/**
	 * Sets the ultimaAlteracao attribute of the ImovelSubcategoria object
	 * 
	 * @param ultimaAlteracao
	 *            The new ultimaAlteracao value
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * Description of the Method
	 * 
	 * @return Description of the Return Value
	 */
	public String toString() {
		return new ToStringBuilder(this).append("comp_id", getId())
				.toString();
	}
     
	/**
	 * Description of the Method
	 * 
	 * @param other
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof ImovelSubcategoria)) {
			return false;
		}
		ImovelSubcategoria castOther = (ImovelSubcategoria) other;

		// return
		// ((this.getComp_id().getSubcategoria().getId().equals(castOther.getComp_id().getSubcategoria().getId()))
		// &&
		// (this.getComp_id().getSubcategoria().getCategoria().getId().equals(castOther.getComp_id().getSubcategoria().getCategoria().getId())));
		return new EqualsBuilder().append(getId(),
				castOther.getComp_id()).isEquals();
	}

	/**
	 * Description of the Method
	 * 
	 * @return Description of the Return Value
	 */
	public int hashCode() {
		return this.ultimaAlteracao.hashCode();
	}

	

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = {"comp_id"};
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		Filtro filtro = new FiltroImovelSubCategoriaHistorico();
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelSubCategoriaHistorico.SUBCATEGORIA_CATEGORIA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelSubCategoriaHistorico.SUBCATEGORIA);
		filtro.adicionarParametro(
				new ParametroSimples(FiltroImovelSubCategoriaHistorico.IMOVEL_ID, this.getImovel().getId()));
		filtro.adicionarParametro(
				new ParametroSimples(FiltroImovelSubCategoriaHistorico.SUBCATEGORIA_ID, 
						this.getSubcategoriaHistorico().getId()));
		return filtro; 
	}
	
	public void initializeLazy(){
		if (getId() != null){
			if(this.getSubcategoriaHistorico() != null){
				this.getSubcategoriaHistorico().initializeLazy();
			}
			getDescricaoParaRegistroTransacao();
		}		
	}
	
	public String getDescricaoParaRegistroTransacao(){
		if (this.getId() != null && this.getSubcategoriaHistorico() != null 
				&& this.getSubcategoriaHistorico().getCategoria() != null){
			return this.getSubcategoriaHistorico().getCategoria().getDescricao() + " / " 
			    + this.getSubcategoriaHistorico().getDescricao();	
		} else {
			return "";
		}
		 	
	}

	public Short getQuantidadeUnidadesColetivas() {
		return quantidadeUnidadesColetivas;
	}

	public void setQuantidadeUnidadesColetivas(Short quantidadeUnidadesColetivas) {
		this.quantidadeUnidadesColetivas = quantidadeUnidadesColetivas;
	}

	public Short getQuantidadeUnidadesPrivativas() {
		return quantidadeUnidadesPrivativas;
	}

	public void setQuantidadeUnidadesPrivativas(Short quantidadeUnidadesPrivativas) {
		this.quantidadeUnidadesPrivativas = quantidadeUnidadesPrivativas;
	}
	  public gcom.cadastro.imovel.Imovel getImovel() {
	        return this.imovel;
	    }

	    public void setImovel(gcom.cadastro.imovel.Imovel imovel) {
	        this.imovel = imovel;
	    }
	 
		public Date getDataInclusao() {
			return dataInclusao;
		}

		public gcom.cadastro.imovel.Subcategoria getSubcategoriaHistorico() {
			return subcategoriaHistorico;
		}

		public void setSubcategoriaHistorico(
				gcom.cadastro.imovel.Subcategoria subcategoriaHistorico) {
			this.subcategoriaHistorico = subcategoriaHistorico;
		}

		public void setDataInclusao(Date dataInclusao) {
			this.dataInclusao = dataInclusao;
		}

		public Date getDataExclusao() {
			return dataExclusao;
		}

		public void setDataExclusao(Date dataExclusao) {
			this.dataExclusao = dataExclusao;
		}

		public Date getDataUltimaAlteracao() {
			return dataUltimaAlteracao;
		}

		public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
			this.dataUltimaAlteracao = dataUltimaAlteracao;
		}

	
	    
	
}

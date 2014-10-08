package gcom.operacional;

import gcom.cadastro.localidade.Localidade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


public class LocalidadeSistemaAbastecimento implements Serializable {

    
	private static final long serialVersionUID = 1L;

	
    private Integer id;
    private SistemaAbastecimento sistemaAbastecimento;
    private Localidade localidade;
    private Short indicadorPrincipalSetorAbastecimento;
    private Date ultimaAlteracao;
    
    public LocalidadeSistemaAbastecimento(){}
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public SistemaAbastecimento getSistemaAbastecimento() {
		return sistemaAbastecimento;
	}
	public void setSistemaAbastecimento(SistemaAbastecimento sistemaAbastecimento) {
		this.sistemaAbastecimento = sistemaAbastecimento;
	}
	public Localidade getLocalidade() {
		return localidade;
	}
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}
	public Short getIndicadorPrincipalSetorAbastecimento() {
		return indicadorPrincipalSetorAbastecimento;
	}
	public void setIndicadorPrincipalSetorAbastecimento(
			Short indicadorPrincipalSetorAbastecimento) {
		this.indicadorPrincipalSetorAbastecimento = indicadorPrincipalSetorAbastecimento;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
    

}

package gcom.cadastro;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Davi Menezes
 * @date 24/04/2012
 *
 */
public class AreaAtualizacaoCadastral extends ObjetoTransacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Short codigoSituacao;
	private Date dataLiberacao;
	private Date dataSuspensao;
	private Date dataConclusao;
	private Date ultimaAlteracao;
	private Localidade localidade;
	private Empresa empresa;
	private SetorComercial setorComercial;
	
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

	public Date getDataLiberacao() {
		return dataLiberacao;
	}

	public void setDataLiberacao(Date dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}

	public Date getDataSuspensao() {
		return dataSuspensao;
	}

	public void setDataSuspensao(Date dataSuspensao) {
		this.dataSuspensao = dataSuspensao;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public SetorComercial getSetorComercial() {
		return setorComercial;
	}


	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroAreaAtualizacaoCadastral filtroArea = new FiltroAreaAtualizacaoCadastral();
		filtroArea.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastral.ID, this.getId()));
		return filtroArea;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		// TODO Auto-generated method stub
		return null;
	}

}

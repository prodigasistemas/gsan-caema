package gcom.cadastro.atualizacaocadastral.bean;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.localidade.Localidade;
import gcom.micromedicao.Leiturista;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;

/**
 * [UC 1391] - Gerar Roteiro Dispositivo Móvel Atualização Cadastral
 * 
 * @author Davi Menezes
 * @date 20/11/2012
 *
 */
public class ComandoAtualizacaoCadastralHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Empresa empresa;
	
	private Localidade localidade;
	
	private Integer codigoSetorComercial;

	private Integer[] quadrasSelecionadas;
	
	private Usuario usuarioLogado;
	
	private Leiturista leiturista;
	
	private String indicadorGerarRoteiroPeloGEO;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer[] getQuadrasSelecionadas() {
		return quadrasSelecionadas;
	}

	public void setQuadrasSelecionadas(Integer[] quadrasSelecionadas) {
		this.quadrasSelecionadas = quadrasSelecionadas;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Leiturista getLeiturista() {
		return leiturista;
	}

	public void setLeiturista(Leiturista leiturista) {
		this.leiturista = leiturista;
	}

	public String getIndicadorGerarRoteiroPeloGEO() {
		return indicadorGerarRoteiroPeloGEO;
	}

	public void setIndicadorGerarRoteiroPeloGEO(String indicadorGerarRoteiroPeloGEO) {
		this.indicadorGerarRoteiroPeloGEO = indicadorGerarRoteiroPeloGEO;
	}
	
}
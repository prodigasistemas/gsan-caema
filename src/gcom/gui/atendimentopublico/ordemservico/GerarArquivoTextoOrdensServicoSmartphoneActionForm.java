/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.atendimentopublico.ordemservico;


import gcom.atendimentopublico.bean.GerarArquivoTxtSmartphoneHelper;

import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;

public class GerarArquivoTextoOrdensServicoSmartphoneActionForm extends ValidatorActionForm {
	

	private static final long serialVersionUID = 1L;
	
	private String empresa;
	
	private String descricaoEmpresa;
	
	private String idTipoOS;
	
	private String descricaoTipoOS;
	
	private String localidade;
	
	private String gerenciaRegional;
	
	private String unidadeNegocio;
	
	private String nomeLocalidade;
	
	private String idSetorComercialOrigem;
	
	private String codigoSetorComercialOrigem;
	
	private String descricaoSetorComercialOrigem;
	
	private String codigoQuadraInicial;
	
	private String idSetorComercialDestino;
	
	private String codigoSetorComercialDestino;
	
	private String descricaoSetorComercialDestino;
	
	private String codigoQuadraFinal;
	
	private Integer[] idsServicoTipo;
	
	private String qtdOsSeletiva;
	
	private String qtdMaxOS;
	
	private Collection<GerarArquivoTxtSmartphoneHelper> colecaoOS;
	
	private String[] idsRegistros;
	
	private Integer idComandoCorrecaoAnormalidade;
	
	private String descricaoComandoCorrecaoAnormalidade;
	
	private String idTipoServicoComando;
	
	private String descricaoTipoServicoComando;
	
	
	public String getQtdMaxOS() {
		return qtdMaxOS;
	}

	public void setQtdMaxOS(String qtdMaxOS) {
		this.qtdMaxOS = qtdMaxOS;
	}

	public GerarArquivoTextoOrdensServicoSmartphoneActionForm(){}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}

	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}

	public String getDescricaoTipoOS() {
		return descricaoTipoOS;
	}

	public void setDescricaoTipoOS(String descricaoTipoOS) {
		this.descricaoTipoOS = descricaoTipoOS;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getCodigoSetorComercialOrigem() {
		return codigoSetorComercialOrigem;
	}

	public void setCodigoSetorComercialOrigem(String codigoSetorComercialOrigem) {
		this.codigoSetorComercialOrigem = codigoSetorComercialOrigem;
	}

	public String getDescricaoSetorComercialOrigem() {
		return descricaoSetorComercialOrigem;
	}

	public void setDescricaoSetorComercialOrigem(String descricaoSetorComercialOrigem) {
		this.descricaoSetorComercialOrigem = descricaoSetorComercialOrigem;
	}

	public String getCodigoQuadraInicial() {
		return codigoQuadraInicial;
	}

	public void setCodigoQuadraInicial(String codigoQuadraInicial) {
		this.codigoQuadraInicial = codigoQuadraInicial;
	}

	public String getCodigoSetorComercialDestino() {
		return codigoSetorComercialDestino;
	}

	public void setCodigoSetorComercialDestino(String codigoSetorComercialDestino) {
		this.codigoSetorComercialDestino = codigoSetorComercialDestino;
	}

	public String getDescricaoSetorComercialDestino() {
		return descricaoSetorComercialDestino;
	}

	public void setDescricaoSetorComercialDestino(String descricaoSetorComercialDestino) {
		this.descricaoSetorComercialDestino = descricaoSetorComercialDestino;
	}

	public String getCodigoQuadraFinal() {
		return codigoQuadraFinal;
	}

	public void setCodigoQuadraFinal(String codigoQuadraFinal) {
		this.codigoQuadraFinal = codigoQuadraFinal;
	}

	public String getQtdOsSeletiva() {
		return qtdOsSeletiva;
	}

	public void setQtdOsSeletiva(String qtdOsSeletiva) {
		this.qtdOsSeletiva = qtdOsSeletiva;
	}

	public String getIdSetorComercialOrigem() {
		return idSetorComercialOrigem;
	}

	public void setIdSetorComercialOrigem(String idSetorComercialOrigem) {
		this.idSetorComercialOrigem = idSetorComercialOrigem;
	}

	public String getIdSetorComercialDestino() {
		return idSetorComercialDestino;
	}

	public void setIdSetorComercialDestino(String idSetorComercialDestino) {
		this.idSetorComercialDestino = idSetorComercialDestino;
	}

	public Integer[] getIdsServicoTipo() {
		return idsServicoTipo;
	}

	public void setIdsServicoTipo(Integer[] idsServicoTipo) {
		this.idsServicoTipo = idsServicoTipo;
	}

	public String getIdTipoOS() {
		return idTipoOS;
	}

	public void setIdTipoOS(String idTipoOS) {
		this.idTipoOS = idTipoOS;
	}
	
	public Collection<GerarArquivoTxtSmartphoneHelper> getColecaoOS() {
		return colecaoOS;
	}

	public void setColecaoOS(Collection<GerarArquivoTxtSmartphoneHelper> colecaoOS) {
		this.colecaoOS = colecaoOS;
	}

	public String[] getIdsRegistros() {
		return idsRegistros;
	}

	public void setIdsRegistros(String[] idsRegistros) {
		this.idsRegistros = idsRegistros;
	}

	public Integer getIdComandoCorrecaoAnormalidade() {
		return idComandoCorrecaoAnormalidade;
	}

	public String getDescricaoComandoCorrecaoAnormalidade() {
		return descricaoComandoCorrecaoAnormalidade;
	}

	public void setIdComandoCorrecaoAnormalidade(
			Integer idComandoCorrecaoAnormalidade) {
		this.idComandoCorrecaoAnormalidade = idComandoCorrecaoAnormalidade;
	}

	public void setDescricaoComandoCorrecaoAnormalidade(
			String descricaoComandoCorrecaoAnormalidade) {
		this.descricaoComandoCorrecaoAnormalidade = descricaoComandoCorrecaoAnormalidade;
	}

	public String getIdTipoServicoComando() {
		return idTipoServicoComando;
	}

	public String getDescricaoTipoServicoComando() {
		return descricaoTipoServicoComando;
	}

	public void setIdTipoServicoComando(String idTipoServicoComando) {
		this.idTipoServicoComando = idTipoServicoComando;
	}

	public void setDescricaoTipoServicoComando(String descricaoTipoServicoComando) {
		this.descricaoTipoServicoComando = descricaoTipoServicoComando;
	}

}
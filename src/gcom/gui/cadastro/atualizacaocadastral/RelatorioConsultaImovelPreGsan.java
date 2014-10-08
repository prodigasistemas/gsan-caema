/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
* Anderson Cabral do Nascimento
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/ 
package gcom.gui.cadastro.atualizacaocadastral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;

/**
 * [UC1446] Gerar Relatorio de Consulta aos Dados do Imovel no Ambiente Pre-GSAN
 * 
 * @author Anderson Cabral
 * @date 20/03/2013
 */
public class RelatorioConsultaImovelPreGsan extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	public RelatorioConsultaImovelPreGsan(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONSULTA_IMOVEL_PRE_GSAN);
	}

	public RelatorioConsultaImovelPreGsan(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public Object executar() throws TarefaException {
		Fachada fachada = Fachada.getInstancia();
		byte[] retorno = null;
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		ConsultarDadosImovelAmbienteVirtualPopupActionForm form = (ConsultarDadosImovelAmbienteVirtualPopupActionForm) getParametro("form");
		ArrayList<ImovelSubcategoriaAtualizacaoCadastral> colecaoSubCategoria = (ArrayList<ImovelSubcategoriaAtualizacaoCadastral>) getParametro("colecaoSubCategoria");
		
		Map<String, Object> parametros = montarMapParametros(form);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("numeroRelatorio", "R1446");
		int tipoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		JRBeanCollectionDataSource bds = new JRBeanCollectionDataSource(colecaoSubCategoria);

		parametros.put("categorias", bds);
		
		Object obj = new Object();
		ArrayList<Object> array = new ArrayList<Object>();
		array.add(obj);
		RelatorioDataSource ds = new RelatorioDataSource(array);			
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_CONSULTA_IMOVEL_PRE_GSAN, parametros, ds, tipoRelatorio);
		
		return retorno;
	}
	
	/***
	 * Monta um Map com os dados do imovel
	 * 
	 * @author Anderson Cabral
	 * @since 20/03/2013
	 * 
	 ****/
	private Map<String, Object> montarMapParametros(ConsultarDadosImovelAmbienteVirtualPopupActionForm form){
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("matricula", form.getMatricula());
		parametros.put("cadastrador", form.getCadastrador());
		
		//Localidade
		parametros.put("idLocalidade", form.getIdLocalidade());
		parametros.put("nomeLocalidade", form.getLocalidade());
		parametros.put("codigoSetorComercial", form.getSetorComercial());
		parametros.put("nomeSetorComercial", form.getDescricaoSetorComercial());
		parametros.put("numeroQuadra", form.getQuadra());
		parametros.put("numeroLote", form.getLote());
		parametros.put("numeroSublote", form.getSubLote());
		//Endereco
		parametros.put("tipoLogradouro", form.getTipoLogradouro());
		
		if(form.getTituloLogradouro() != null){
			parametros.put("tituloLogradouro", form.getTituloLogradouro());
		}else{
			parametros.put("tituloLogradouro", "");
		}
		
		parametros.put("nomeLogradouro", form.getDescricaoLogradouro());
		parametros.put("referencia", form.getDescricaoReferencia());
		parametros.put("numeroImovel", form.getNumeroImovel());
		parametros.put("complementoEndereco", form.getComplementoLogradouro());
		parametros.put("bairro", form.getBairro());
		parametros.put("cep", form.getCep());
		//Cliente
		parametros.put("tipoCliente", form.getTipoCliente());
		parametros.put("cpfCnpj", form.getCpfcnpj());
		parametros.put("nomeCliente", form.getNomeCliente());
		parametros.put("rg", form.getNumeroRg());
		parametros.put("orgaoExpedidor", form.getOrgaoExpedidor());
		parametros.put("uf", form.getUf());
		parametros.put("sexo", form.getSexo());	
		parametros.put("telefones", form.getTelefones());
		//Imovel
		parametros.put("idPerfil", form.getIdImovelPerfil());
		parametros.put("descricaoPerfil", form.getImovelPerfil());
		parametros.put("analisarTarifaSocial", form.getAlertaTarifaSocial());
		parametros.put("numeroMedidorEnergia", form.getNumeroMedidorEnergia());
		parametros.put("numeroMoradores", form.getNumeroMoradores());
		parametros.put("idPavimentoRua", form.getIdPavimentoRua());
		parametros.put("desPavimentoRua", form.getPavimentoRua());
		parametros.put("idPavimentoCalcada", form.getIdPavimentoCalcada());
		parametros.put("desPavimentoCalcada", form.getPavimentoCalcada());
		parametros.put("idFonteAbastecimento", form.getIdFonteAbastecimento());
		parametros.put("desFonteAbastecimento", form.getFonteAbastecimento());
		parametros.put("categoria", form.getCategoria());
		parametros.put("subCategoria", form.getSubCategoria());
		parametros.put("qtdEconomias", form.getQuantidadeEconomias());
		//Dados de Ligacao
		parametros.put("idSituacaoAgua", form.getIdSituacaoLigacaoAgua());
		parametros.put("desSituacaoAgua", form.getSituacaoLigacaoAgua());
		parametros.put("idSituacaoEsgoto", form.getIdSituacaoLigacaoEsgoto());
		parametros.put("desSituacaoEsgoto", form.getSituacaoLigacaoEsgoto());
		parametros.put("numeroHidrometro", form.getNumeroHidrometro());
		parametros.put("idLocalInstalacao", form.getIdLocalInstalacao());
		parametros.put("desLocalInstalacao", form.getLocalInstalacao());
		parametros.put("idTipoProtecao", form.getIdTipoProtecao());
		parametros.put("desTipoProtecao", form.getTipoProtecao());
		parametros.put("cavalete", form.getCavalete());
		parametros.put("leitura", form.getLeitura());
		parametros.put("observacao", form.getObservacao());
		
		return parametros;
	} 

	@Override
	public void agendarTarefaBatch() {
		
	}
}

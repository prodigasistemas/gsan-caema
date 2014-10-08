/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.relatorio.operacional;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.FiltroSubsistemaAbastecimento;
import gcom.operacional.SistemaAbastecimento;
import gcom.operacional.SubsistemaAbastecimento;
import gcom.operacional.bean.AreaOperacionalHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Title: GCOM
 * Description: Sistema de Gestão Comercial
 * Copyright: Copyright (c) 2004
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * 
 * @author Arthur Carvalho
 * @date 29/12/2009
 * @version 1.0
 */

public class RelatorioManterAreaOperacional extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterAreaOperacional(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_AREA_OPERACIONAL_MANTER);
	}
	
	@Deprecated
	public RelatorioManterAreaOperacional() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param situacao pagamento
	 *            Description of the Parameter
	 * @param SituacaoPagamentoParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String descricao = (String) getParametro("descricao");
		String tipoPesquisa = (String)getParametro("tipoPesquisa");
		String idSistemaAbastecimento = (String) getParametro("idSistemaAbastecimento");
		String idSubsistemaAbastecimento = (String) getParametro("idSubsistemaAbastecimento");
		String idDistritoOperacional = (String) getParametro("idDistritoOperacional");
		String indicadordeUso = (String) getParametro("indicadordeUso");		
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();
		
		Fachada fachada = Fachada.getInstancia();

		RelatorioManterAreaOperacionalBean relatorioBean = null;
		
		Collection<AreaOperacionalHelper> colecaoAreaOperacionalHelper = fachada.pesquisarAreaOperacional(descricao, tipoPesquisa, 
			idSistemaAbastecimento, idSubsistemaAbastecimento, idDistritoOperacional, indicadordeUso, null);
		
		SubsistemaAbastecimento subsistemaAbastecimento = null;
		if(idSubsistemaAbastecimento != null && !idSubsistemaAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			FiltroSubsistemaAbastecimento filtroSubsistemaAbastecimento = new FiltroSubsistemaAbastecimento();
			filtroSubsistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSubsistemaAbastecimento.ID_SUBSISTEMA_ABASTECIMENTO, idSubsistemaAbastecimento));

			Collection<SubsistemaAbastecimento> colecaoSubsistemaAbastecimento = fachada.pesquisar(filtroSubsistemaAbastecimento, SubsistemaAbastecimento.class.getName());
			
			subsistemaAbastecimento = (SubsistemaAbastecimento) Util.retonarObjetoDeColecao(colecaoSubsistemaAbastecimento);
		}
		
		DistritoOperacional distritoOperacional = null;
		if(idDistritoOperacional != null && !idDistritoOperacional.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();
			filtroDistritoOperacional.adicionarParametro(new ParametroSimples(FiltroDistritoOperacional.ID, idDistritoOperacional));

			Collection<DistritoOperacional> colecaoDistritoOperacional = fachada.pesquisar(filtroDistritoOperacional, DistritoOperacional.class.getName());
			
			distritoOperacional = (DistritoOperacional) Util.retonarObjetoDeColecao(colecaoDistritoOperacional);
		}
				
		// se a coleção de parâmetros da analise não for vazia
		if (colecaoAreaOperacionalHelper != null && !colecaoAreaOperacionalHelper.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator areaOperacionalHelperInt = colecaoAreaOperacionalHelper.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (areaOperacionalHelperInt.hasNext()) {

				AreaOperacionalHelper areaOperacionalHelper = (AreaOperacionalHelper) areaOperacionalHelperInt.next();		
				
				if(idSubsistemaAbastecimento != null && !idSubsistemaAbastecimento.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
					//Subsistema Abastecimento Informado
					areaOperacionalHelper.setSubSistema(subsistemaAbastecimento.getDescricao());
				}else{
					//Pesquisa Subsistema Principal
					SubsistemaAbastecimento subsistemaAbastecimentoPrincipal = fachada.obterSubsistemaPrincipalAreaOperacional(areaOperacionalHelper.getIdAreaOperacional());
					areaOperacionalHelper.setSubSistema(subsistemaAbastecimentoPrincipal.getDescricao());
				}
				
				if(idDistritoOperacional != null && !idDistritoOperacional.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
					//Distrito Operacional Informado
					areaOperacionalHelper.setDistrito(distritoOperacional.getDescricao());
				}else{
					//Pesquisa Distrito Principal
					DistritoOperacional distritoOperacionalPrincipal = fachada.obterDistritoPrincipalAreaOperacional(areaOperacionalHelper.getIdAreaOperacional());
					areaOperacionalHelper.setDistrito(distritoOperacionalPrincipal.getDescricao());
				}
				
				
				relatorioBean = new RelatorioManterAreaOperacionalBean(
						//Código
						"" + areaOperacionalHelper.getIdAreaOperacional(),
						//Descricao
						"" + areaOperacionalHelper.getDescricaoAreaOperacional(),						
						//Subsistema
						""+ areaOperacionalHelper.getSubSistema(),						
						//Distrito Operacional
						"" + areaOperacionalHelper.getDistrito());
						
				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("descricao", descricao);
		
		if (idSistemaAbastecimento != null){
			FiltroSistemaAbastecimento filtroSistemaAbastecimento = new FiltroSistemaAbastecimento();
			filtroSistemaAbastecimento.adicionarParametro(new ParametroSimples(FiltroSistemaAbastecimento.ID, idSistemaAbastecimento));

			Collection<SistemaAbastecimento> colecaoSistemaAbastecimento = fachada.pesquisar(filtroSistemaAbastecimento, SistemaAbastecimento.class.getName());
			
			SistemaAbastecimento sistemaAbastecimento = (SistemaAbastecimento) Util.retonarObjetoDeColecao(colecaoSistemaAbastecimento);
			
			parametros.put("sistemaAbastecimento", sistemaAbastecimento.getDescricao());
		}
		
		if (idSubsistemaAbastecimento != null){	
			parametros.put("subsistemaAbastecimento", subsistemaAbastecimento.getDescricao());
		}
		
		if (idDistritoOperacional != null){
			parametros.put("distritoOperacional", distritoOperacional.getDescricao());
		}
				
		if (indicadordeUso != null){
			if (indicadordeUso.equals("1")) {
				parametros.put("indicadorUso", "ATIVO");
			} else {
				parametros.put("indicadorUso", "INATIVO");
			}
		}
		

		

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_AREA_OPERACIONAL_MANTER, parametros,
				ds, tipoFormatoRelatorio);
		

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

//		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
//				(FiltroLeiturista) getParametro("filtroLeiturista"),
//				Leiturista.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterAreaOperacional", this);
	}

}
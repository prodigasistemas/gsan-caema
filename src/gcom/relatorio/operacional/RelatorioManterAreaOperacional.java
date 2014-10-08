/*
* Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
 * Description: Sistema de Gest�o Comercial
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
	 * < <Descri��o do m�todo>>
	 * 
	 * @param situacao pagamento
	 *            Description of the Parameter
	 * @param SituacaoPagamentoParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
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

		// cole��o de beans do relat�rio
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
				
		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoAreaOperacionalHelper != null && !colecaoAreaOperacionalHelper.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator areaOperacionalHelperInt = colecaoAreaOperacionalHelper.iterator();

			// la�o para criar a cole��o de par�metros da analise
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
						//C�digo
						"" + areaOperacionalHelper.getIdAreaOperacional(),
						//Descricao
						"" + areaOperacionalHelper.getDescricaoAreaOperacional(),						
						//Subsistema
						""+ areaOperacionalHelper.getSubSistema(),						
						//Distrito Operacional
						"" + areaOperacionalHelper.getDistrito());
						
				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
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
		

		

		// cria uma inst�ncia do dataSource do relat�rio

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_AREA_OPERACIONAL_MANTER, parametros,
				ds, tipoFormatoRelatorio);
		

		// retorna o relat�rio gerado
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
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
package gcom.relatorio.operacional;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.operacional.FiltroSubsistemaSistemaAbastecimento;
import gcom.operacional.SubsistemaSistemaAbastecimento;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioBean;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de bairro manter de água
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioManterSubsistemaAbastecimento extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterSubsistemaAbastecimento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_SUBSISTEMA_ABASTECIMENTO);
	}

	@Deprecated
	public RelatorioManterSubsistemaAbastecimento() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		FiltroSubsistemaSistemaAbastecimento filtroSubsistemaSistemaAbastecimento = (FiltroSubsistemaSistemaAbastecimento) getParametro("filtroSubsistemaSistemaAbastecimento");
		SubsistemaSistemaAbastecimento subsistemaSistemaAbastecimentoParametros = (SubsistemaSistemaAbastecimento) getParametro("subsistemaSistemaAbastecimentoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List<RelatorioBean> relatorioBeans = new ArrayList<RelatorioBean>();

		Fachada fachada = Fachada.getInstancia();

		filtroSubsistemaSistemaAbastecimento.adicionarCaminhoParaCarregamentoEntidade("subsistemaAbastecimento");
		filtroSubsistemaSistemaAbastecimento.adicionarCaminhoParaCarregamentoEntidade("sistemaAbastecimento");
		filtroSubsistemaSistemaAbastecimento.setConsultaSemLimites(true);

		Collection<SubsistemaSistemaAbastecimento> colecaoSubsistemaSistemaAbastecimento = fachada.pesquisar(filtroSubsistemaSistemaAbastecimento, SubsistemaSistemaAbastecimento.class
				.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoSubsistemaSistemaAbastecimento != null) {
			
			for (SubsistemaSistemaAbastecimento subsistemaSistemaAbastecimento : colecaoSubsistemaSistemaAbastecimento) {
				RelatorioManterSubsistemaAbastecimentoBean relatorioManterSubsistemaAbastecimentoBean = new RelatorioManterSubsistemaAbastecimentoBean();
				relatorioManterSubsistemaAbastecimentoBean.setCodigo(subsistemaSistemaAbastecimento.getSubsistemaAbastecimento().getId().toString());
				relatorioManterSubsistemaAbastecimentoBean.setDescricao(subsistemaSistemaAbastecimento.getSubsistemaAbastecimento().getDescricao());
				relatorioManterSubsistemaAbastecimentoBean.setDescricaoAbreviada(subsistemaSistemaAbastecimento.getSubsistemaAbastecimento().getDescricaoAbreviada());
				relatorioManterSubsistemaAbastecimentoBean.setSistemaAbastecimento(subsistemaSistemaAbastecimento.getSistemaAbastecimento().getDescricao());
				relatorioManterSubsistemaAbastecimentoBean.setIndicadorUso(subsistemaSistemaAbastecimento.getSubsistemaAbastecimento().getIndicadorUso().toString());
				
				relatorioBeans.add(relatorioManterSubsistemaAbastecimentoBean);
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		
		if (subsistemaSistemaAbastecimentoParametros.getSubsistemaAbastecimento().getId() != null){
			parametros.put("codigo", subsistemaSistemaAbastecimentoParametros.getSubsistemaAbastecimento().getId().toString());
		}
		
		parametros.put("descricao", subsistemaSistemaAbastecimentoParametros.getSubsistemaAbastecimento().getDescricao());
		parametros.put("descricaoAbreviada", subsistemaSistemaAbastecimentoParametros.getSubsistemaAbastecimento().getDescricaoAbreviada());

		if (subsistemaSistemaAbastecimentoParametros.getSistemaAbastecimento() != null){
			parametros.put("sistemaAbastecimento", subsistemaSistemaAbastecimentoParametros.getSistemaAbastecimento().getDescricao());
		}
		
		if (subsistemaSistemaAbastecimentoParametros.getSubsistemaAbastecimento().getIndicadorUso() != null){
			if (subsistemaSistemaAbastecimentoParametros.getSubsistemaAbastecimento().getIndicadorUso().equals(new Short("1"))) {
				parametros.put("indicadorUso", "Ativo");
			} else if (subsistemaSistemaAbastecimentoParametros.getSubsistemaAbastecimento().getIndicadorUso().equals(new Short("2"))){
				parametros.put("indicadorUso", "Inativo");
			}
			else{
				parametros.put("indicadorUso", "Todos");
			}
		}
		else{
			parametros.put("indicadorUso", "Todos");
		}
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_MANTER_SUBSISTEMA_ABASTECIMENTO,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_MANTER_SUBSISTEMA_ABASTECIMENTO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
				(FiltroSubsistemaSistemaAbastecimento) getParametro("filtroSubsistemaSistemaAbastecimento"),
				SubsistemaSistemaAbastecimento.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterSubsistemaAbastecimento", this);

	}

}
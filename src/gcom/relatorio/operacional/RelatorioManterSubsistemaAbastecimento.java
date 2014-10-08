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
 * classe respons�vel por criar o relat�rio de bairro manter de �gua
 * 
 * @author S�vio Luiz
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
	 * < <Descri��o do m�todo>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
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

		// cole��o de beans do relat�rio
		List<RelatorioBean> relatorioBeans = new ArrayList<RelatorioBean>();

		Fachada fachada = Fachada.getInstancia();

		filtroSubsistemaSistemaAbastecimento.adicionarCaminhoParaCarregamentoEntidade("subsistemaAbastecimento");
		filtroSubsistemaSistemaAbastecimento.adicionarCaminhoParaCarregamentoEntidade("sistemaAbastecimento");
		filtroSubsistemaSistemaAbastecimento.setConsultaSemLimites(true);

		Collection<SubsistemaSistemaAbastecimento> colecaoSubsistemaSistemaAbastecimento = fachada.pesquisar(filtroSubsistemaSistemaAbastecimento, SubsistemaSistemaAbastecimento.class
				.getName());

		// se a cole��o de par�metros da analise n�o for vazia
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

		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		
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
		
		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_MANTER_SUBSISTEMA_ABASTECIMENTO,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_MANTER_SUBSISTEMA_ABASTECIMENTO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
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
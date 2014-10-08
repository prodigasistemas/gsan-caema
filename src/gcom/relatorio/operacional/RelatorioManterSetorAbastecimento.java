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

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.FiltroSubsistemaAbastecimento;
import gcom.operacional.SistemaAbastecimento;
import gcom.operacional.SubsistemaAbastecimento;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioBean;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class RelatorioManterSetorAbastecimento extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterSetorAbastecimento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_SETOR_ABASTECIMENTO);
	}

	@Deprecated
	public RelatorioManterSetorAbastecimento() {
		super(null, "");
	}

	
	@SuppressWarnings("unchecked")
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String codigo = (String) getParametro("codigo");
		String descricao = (String) getParametro("descricao");
		String descricaoAbreviada = (String) getParametro("descricaoAbreviada");
		String indicadorPosicaoTexto = (String) getParametro("indicadorPosicaoTexto");
		String sistemaAgua = (String) getParametro("sistemaAgua");
		String subsistemaAgua =(String) getParametro("subsistemaAgua");
		String indicadorUso = (String) getParametro("indicadorUso");

		// coleção de beans do relatório
		List<RelatorioBean> relatorioBeans = new ArrayList<RelatorioBean>();

		Fachada fachada = Fachada.getInstancia();
		
		
		// Parâmetros do relatório
		Map<String,Object> parametros = new HashMap<String,Object>();
		parametros.put("descricao", descricao);
		parametros.put("descricaoAbreviada", descricaoAbreviada);
		
		if(Util.verificarIdNaoVazio(sistemaAgua)){
			
			
			FiltroSistemaAbastecimento filtro = new FiltroSistemaAbastecimento();
			filtro.adicionarParametro(new ParametroSimples(FiltroSistemaAbastecimento.ID, sistemaAgua));
			
			SistemaAbastecimento sistema = (SistemaAbastecimento)Util.retonarObjetoDeColecao(
					(Collection<SistemaAbastecimento>)fachada.pesquisar(filtro, SistemaAbastecimento.class.getName()));
			
			parametros.put("sistemaAgua", sistema.getDescricao());
		}
		else{
			parametros.put("sistemaAgua", null);
		}
		
		if(Util.verificarIdNaoVazio(subsistemaAgua)){
			
			FiltroSubsistemaAbastecimento filtro = new FiltroSubsistemaAbastecimento();
			filtro.adicionarParametro(new ParametroSimples("id", subsistemaAgua));
			
			SubsistemaAbastecimento sistema = (SubsistemaAbastecimento)Util.retonarObjetoDeColecao(
					(Collection<SubsistemaAbastecimento>)fachada.pesquisar(filtro, SubsistemaAbastecimento.class.getName()));
			
			parametros.put("subsistemaAgua", sistema.getDescricao());
			
		}
		else{
			parametros.put("subsistemaAgua", null);
		}
		
		if(indicadorUso != null){
			switch(Integer.parseInt(indicadorUso)){
				
				case 1:
					parametros.put("indicadorUso", "ATIVO");
					break;
				case 2:
					parametros.put("indicadorUso", "INATIVO");
					break;
				case 3:
					parametros.put("indicadorUso", "TODOS");
					break;	
			}
		}
		
		
		Collection<Object[]> objs = fachada.pesquisarSetorAbastecimento(codigo,descricao,descricaoAbreviada,
				 indicadorPosicaoTexto,sistemaAgua,
				 subsistemaAgua,indicadorUso,null, true);
		
		Iterator<Object[]> itRelatorio = objs.iterator();
		while(itRelatorio.hasNext()){
			Object[] obj = itRelatorio.next();
			RelatorioManterSetorAbastecimentoBean bean = new RelatorioManterSetorAbastecimentoBean();
			
			bean.setCodigo((String)obj[0]);
			bean.setDescricao((String)obj[1]);
			bean.setDescricaoAbreviada((String)obj[2]);
			bean.setSubsistemaAgua((String)obj[3]);
			bean.setIndicadorUso((String)obj[6]);
			
			relatorioBeans.add(bean);
		}
		
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_MANTER_SETOR_ABASTECIMENTO,
				parametros, ds, tipoFormatoRelatorio);

		
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterSetorAbastecimento", this);

	}

}
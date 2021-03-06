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

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.operacional.FiltroSetorAbastecimento;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.FiltroSubsistemaAbastecimento;
import gcom.operacional.SetorAbastecimento;
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


public class RelatorioManterDistritoOperacional extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterDistritoOperacional(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_DISTRITO_OPERACIONAL);
	}

	@Deprecated
	public RelatorioManterDistritoOperacional() {
		super(null, "");
	}

	
	@SuppressWarnings("unchecked")
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String descricao = (String) getParametro("descricao");
		String indicadorPosicaoTexto = (String) getParametro("indicadorPosicaoTexto");
		String setorAbastecimento = (String) getParametro("setorAbastecimento");
		String subsistemaAbastecimento =(String) getParametro("subsistemaAbastecimento");
		String sistemaAbastecimento =(String) getParametro("sistemaAbastecimento");
		String indicadorUso = (String) getParametro("indicadorUso");

		// cole��o de beans do relat�rio
		List<RelatorioBean> relatorioBeans = new ArrayList<RelatorioBean>();

		Fachada fachada = Fachada.getInstancia();
		
		
		// Par�metros do relat�rio
		Map<String,Object> parametros = new HashMap<String,Object>();
		parametros.put("descricao", descricao);
		
		//Setor Abastecimento
		if(Util.verificarIdNaoVazio(setorAbastecimento)){
			
			
			FiltroSetorAbastecimento filtro = new FiltroSetorAbastecimento();
			filtro.adicionarParametro(new ParametroSimples(FiltroSistemaAbastecimento.ID, Integer.valueOf(setorAbastecimento)));
			
			SetorAbastecimento setor = (SetorAbastecimento)Util.retonarObjetoDeColecao(
					(Collection<SetorAbastecimento>)fachada.pesquisar(filtro, SetorAbastecimento.class.getName()));
			
			parametros.put("setorAbastecimento", setor.getDescricao());
		}
		else{
			parametros.put("setorAbastecimento", null);
		}
		
		//Subsistema Abastecimento
		if(Util.verificarIdNaoVazio(subsistemaAbastecimento)){
			
			FiltroSubsistemaAbastecimento filtro = new FiltroSubsistemaAbastecimento();
			filtro.adicionarParametro(new ParametroSimples("id", Integer.valueOf(subsistemaAbastecimento)));
			
			SubsistemaAbastecimento sistema = (SubsistemaAbastecimento)Util.retonarObjetoDeColecao(
					(Collection<SubsistemaAbastecimento>)fachada.pesquisar(filtro, SubsistemaAbastecimento.class.getName()));
			
			parametros.put("subsistemaAbastecimento", sistema.getDescricao());
			
		}
		else{
			parametros.put("subsistemaAbastecimento", null);
		}
		
		//Sistema Abastecimento
		if(Util.verificarIdNaoVazio(sistemaAbastecimento)){
			
			FiltroSistemaAbastecimento filtro = new FiltroSistemaAbastecimento();
			filtro.adicionarParametro(new ParametroSimples("id", Integer.valueOf(sistemaAbastecimento)));
			
			SistemaAbastecimento sistema = (SistemaAbastecimento)Util.retonarObjetoDeColecao(
					(Collection<SubsistemaAbastecimento>)fachada.pesquisar(filtro, SistemaAbastecimento.class.getName()));
			
			parametros.put("sistemaAbastecimento", sistema.getDescricao());
			
		}
		else{
			parametros.put("sistemaAbastecimento", null);
		}
		
		if(Util.verificarNaoVazio(indicadorUso)){
			switch(Integer.parseInt(indicadorUso)){
				
				case 1:
					parametros.put("indicadorUso", "Ativo");
					break;
				case 2:
					parametros.put("indicadorUso", "Inativo");
					break;
			}
		}
		else{
			parametros.put("indicadorUso", "Todos");
		}
		
		
		Collection<Object[]> colTotal = Fachada.getInstancia().pesquisarDistritoOperacional(descricao,indicadorPosicaoTexto,
				sistemaAbastecimento,subsistemaAbastecimento,setorAbastecimento,
				indicadorUso,null,true);
		
		Iterator<Object[]> itRelatorio = colTotal.iterator();
		while(itRelatorio.hasNext()){
			
			Object[] obj = itRelatorio.next();
			RelatorioManterDistritoOperacionalBean bean = new RelatorioManterDistritoOperacionalBean();
			
			bean.setDescricao((String)obj[1]);
			bean.setSubsistemaAbastecimento((String)obj[2]);
			bean.setSetorAbastecimento((String)obj[3]);
			bean.setIndicadorUso((String)obj[4]);
			
			relatorioBeans.add(bean);
		}
		
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_MANTER_DISTRITO_OPERACIONAL,
				parametros, ds, tipoFormatoRelatorio);

		
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterDistritoOperacional", this);

	}

}
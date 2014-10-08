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

import gcom.cadastro.atualizacaocadastral.bean.DadosImovelPreGsanHelper;
import gcom.cadastro.atualizacaocadastral.bean.DadosImovelRedeAguaEsgotoEmQuadraSemRedeHelper;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * [UC1597] Gerar Relat�rio dos Im�veis Ligados para Quadra sem Rede
 * 
 * @author Vivianne Sousa
 * @date 17/04/2014
 */
public class RelatorioImoveisLigadosParaQuadraSemRede extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	public RelatorioImoveisLigadosParaQuadraSemRede(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_LIGADOS_PARA_QUADRA_SEM_REDE); 
	}

	public RelatorioImoveisLigadosParaQuadraSemRede(Usuario usuario, String nomeRelatorio) {
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
		
		String empresa 		  = (String) getParametro("empresa");
		String idLocalidade   = (String) getParametro("idLocalidade");
		String nomeLocalidade = (String) getParametro("nomeLocalidade");
		String total = "0";

		DadosImovelPreGsanHelper parametrosHelper = (DadosImovelPreGsanHelper) getParametro("parametrosHelper");
		
		ArrayList<DadosImovelRedeAguaEsgotoEmQuadraSemRedeHelper> colecaoImoveisLigadosParaQuadraSemRede = 
				(ArrayList<DadosImovelRedeAguaEsgotoEmQuadraSemRedeHelper>) getParametro("colecaoImoveisLigadosParaQuadraSemRede");
		
		ArrayList<RelatorioImoveisLigadosParaQuadraSemRedeBean> colecaoImoveisLigadosParaQuadraSemRedeBean = montaBean(colecaoImoveisLigadosParaQuadraSemRede);
		
		if(colecaoImoveisLigadosParaQuadraSemRedeBean != null && !colecaoImoveisLigadosParaQuadraSemRedeBean.isEmpty()){
			total = "" + colecaoImoveisLigadosParaQuadraSemRedeBean.size();
		}
		
		String setor = "Setor";
		String quadra = "Quadra";
		
		if(parametrosHelper.getParametroTipoSelecao().equals("2")){
			//IM�VEL COM OCORR�NCIA DE CADASTRO
			setor = "Setor GSAN";
			quadra = "Quadra GSAN";
		}
		
		String tituloRelatorio = "RELAT�RIO DOS IM�VEIS COM REDE DE �GUA E/OU ESGOTO EM UMA QUADRA QUE EST� SINALIZADA SEM REDE";
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("empresa", empresa);
		parametros.put("idLocalidade", idLocalidade);
		parametros.put("nomeLocalidade", nomeLocalidade);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("numeroRelatorio", "R1597");
		parametros.put("total", total);
		parametros.put("tituloRelatorio", tituloRelatorio);
		parametros.put("setor", setor);
		parametros.put("quadra", quadra);
		int tipoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		RelatorioDataSource ds = new RelatorioDataSource(colecaoImoveisLigadosParaQuadraSemRedeBean);			
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_IMOVEIS_LIGADOS_PARA_QUADRA_SEM_REDE, parametros, ds, tipoRelatorio); 
		
		return retorno;
	}
	
	private ArrayList<RelatorioImoveisLigadosParaQuadraSemRedeBean> montaBean(ArrayList<DadosImovelRedeAguaEsgotoEmQuadraSemRedeHelper> colecaoImoveisLigadosParaQuadraSemRede){
		
		ArrayList<RelatorioImoveisLigadosParaQuadraSemRedeBean> colecaoImoveisLigadosParaQuadraSemRedeBean = new ArrayList<RelatorioImoveisLigadosParaQuadraSemRedeBean>();
		
		for(DadosImovelRedeAguaEsgotoEmQuadraSemRedeHelper dadosHelper : colecaoImoveisLigadosParaQuadraSemRede){
			RelatorioImoveisLigadosParaQuadraSemRedeBean relatorioImoveisLigadosParaQuadraSemRedeBean = new RelatorioImoveisLigadosParaQuadraSemRedeBean();

			if(dadosHelper.getCodigoSetorComercial() != null){
				relatorioImoveisLigadosParaQuadraSemRedeBean.setCodigoSetorComercial(dadosHelper.getCodigoSetorComercial());
			}else{
				relatorioImoveisLigadosParaQuadraSemRedeBean.setCodigoSetorComercial("");
			}

			if(dadosHelper.getNumeroQuadra() != null){
				relatorioImoveisLigadosParaQuadraSemRedeBean.setNumeroQuadra(dadosHelper.getNumeroQuadra());
			}else{
				relatorioImoveisLigadosParaQuadraSemRedeBean.setNumeroQuadra("");
			}

			if(dadosHelper.getRedeAgua() != null){
				String redeAgua = "";
				if(dadosHelper.getRedeAgua().equals(Quadra.COM_REDE.toString())){
					redeAgua = "SIM";
				}else if(dadosHelper.getRedeAgua().equals(Quadra.SEM_REDE.toString())){
					redeAgua = "N�O";
				}else if(dadosHelper.getRedeAgua().equals(Quadra.REDE_PARCIAL.toString())){
					redeAgua = "PARCIAL";
				}
				relatorioImoveisLigadosParaQuadraSemRedeBean.setRedeAgua(redeAgua);
			}else{
				relatorioImoveisLigadosParaQuadraSemRedeBean.setRedeAgua("");
			}

			if(dadosHelper.getRedeEsgoto() != null){
				String redeEsgoto = "";
				if(dadosHelper.getRedeEsgoto().equals(Quadra.COM_REDE.toString())){
					redeEsgoto = "SIM";
				}else if(dadosHelper.getRedeEsgoto().equals(Quadra.SEM_REDE.toString())){
					redeEsgoto = "N�O";
				}else if(dadosHelper.getRedeEsgoto().equals(Quadra.REDE_PARCIAL.toString())){
					redeEsgoto = "PARCIAL";
				}
				relatorioImoveisLigadosParaQuadraSemRedeBean.setRedeEsgoto(redeEsgoto);
			}else{
				relatorioImoveisLigadosParaQuadraSemRedeBean.setRedeEsgoto("");
			}

			if(dadosHelper.getMatricula() != null){
				relatorioImoveisLigadosParaQuadraSemRedeBean.setMatricula(dadosHelper.getMatricula());
			}else{
				relatorioImoveisLigadosParaQuadraSemRedeBean.setMatricula("");
			}
			
			if(dadosHelper.getSituacaoAgua() != null){
				relatorioImoveisLigadosParaQuadraSemRedeBean.setSituacaoAgua(dadosHelper.getSituacaoAgua());
			}else{
				relatorioImoveisLigadosParaQuadraSemRedeBean.setSituacaoAgua("");
			}
				
			if(dadosHelper.getSituacaoEsgoto() != null){
				relatorioImoveisLigadosParaQuadraSemRedeBean.setSituacaoEsgoto(dadosHelper.getSituacaoEsgoto());
			}else{
				relatorioImoveisLigadosParaQuadraSemRedeBean.setSituacaoEsgoto("");
			}
			
			colecaoImoveisLigadosParaQuadraSemRedeBean.add(relatorioImoveisLigadosParaQuadraSemRedeBean);
		}
		
		return colecaoImoveisLigadosParaQuadraSemRedeBean;	
	}

	@Override
	public void agendarTarefaBatch() {
		
	}

}

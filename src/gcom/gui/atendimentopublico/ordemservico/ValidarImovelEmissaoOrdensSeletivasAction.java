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
* Ivan Sérgio Virginio da Silva Júnior
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

import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Ivan Sérgio
 */
public class ValidarImovelEmissaoOrdensSeletivasAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter*
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = null;
		
		ImovelEmissaoOrdensSeletivasActionForm form = 
			(ImovelEmissaoOrdensSeletivasActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if(form.getTipoRelatorio()!=null){
			if(form.getTipoRelatorio().equals("1")){
				sessao.setAttribute("tipoRelatorio", "SINTETICO");
			}else if(form.getTipoRelatorio().equals("2")){
				sessao.setAttribute("tipoRelatorio", "ANALITICO");
			}else{
				sessao.removeAttribute("tipoRelatorio");
			}
		}

		// [FS0006] - Validar Ano/Mes de Instalacao
		if (form.getMesAnoInstalacaoInicial() != null &&
				!form.getMesAnoInstalacaoInicial().trim().equals("")
				&& form.getMesAnoInstalacaoInicial().contains("/")) {
			Integer anoMesAtual = Util.converterStringParaInteger(
					Util.getAnoMesComoString(new Date()).replace("/", ""));
			
			Integer anoMesInstalacaoInicial = Util.converterStringParaInteger(
					Util.formatarMesAnoParaAnoMes(
							form.getMesAnoInstalacaoInicial().replace("/", "")));
			
			if (anoMesInstalacaoInicial > anoMesAtual) {
				throw new ActionServletException("atencao.mes_ano_instalacao_invalido", null, "");
			}
		}
		
		if (form.getMesAnoInstalacaoFinal() != null &&
				!form.getMesAnoInstalacaoFinal().trim().equals("")
				&& form.getMesAnoInstalacaoFinal().contains("/")) {
			Integer anoMesAtual = Util.converterStringParaInteger(
					Util.getAnoMesComoString(new Date()).replace("/", ""));
			
			Integer anoMesInstalacaoFinal = Util.converterStringParaInteger(
					Util.formatarMesAnoParaAnoMes(
							form.getMesAnoInstalacaoFinal().replace("/", "")));
			
			if (form.getMesAnoInstalacaoInicial() != null &&
					!form.getMesAnoInstalacaoInicial().trim().equals("")
					&& form.getMesAnoInstalacaoInicial().contains("/")){
				
				Integer anoMesInstalacaoInicial = Util.converterStringParaInteger(
						Util.formatarMesAnoParaAnoMes(
								form.getMesAnoInstalacaoInicial().replace("/", "")));
				
				if (anoMesInstalacaoFinal < anoMesInstalacaoInicial){
					
					throw new ActionServletException("atencao.mes_ano_final_instalacao_menor_mes_ano_inicial_instalacao", null, "");
					
				}
				
			}
			
			if (anoMesInstalacaoFinal > anoMesAtual) {
				throw new ActionServletException("atencao.mes_ano_instalacao_invalido", null, "");
			}
		}
		
		if(form.getTipoOrdem() == null || form.getTipoOrdem().equalsIgnoreCase("")
				|| form.getTipoOrdem().equalsIgnoreCase("-1")){
			throw new ActionServletException("atencao.campo.informado", null, "Tipo da Ordem");
		}
		if(form.getSugestao() != null && form.getSugestao().equals("2")){
			if(form.getDescricaoComando() == null || form.getDescricaoComando().equals("")){
				throw new ActionServletException("atencao.campo.informado", null, "Descrição Comando");
			}
		}
		
		/**
		 * Tipo da Ordem: Caso o usuario selecione a opcao INSTALACAO inibir todo os
		 * 				  os campos da Aba Hidrometro;
		 */
		if (form.getTipoOrdem() != null) {
			
			if(form.getTipoOrdem().equalsIgnoreCase(ImovelEmissaoOrdensSeletivasActionForm.TIPO_ORDEM_INSTALACAO)){
				
				form.limparCamposHidrometro();
				
			}else if(form.getTipoOrdem().equalsIgnoreCase(ImovelEmissaoOrdensSeletivasActionForm.TIPO_INSPECAO_ANORMALIDADE)){
			
				String concluir = httpServletRequest.getParameter("concluir");
				
				if(form.getIdImovel() == null ||form.getIdImovel().equals("")){
						
					if(form.getAnormalidadeHidrometro() == null || 
						(form.getAnormalidadeHidrometro().length == 1 && 
						((form.getAnormalidadeHidrometro())[0].equals(ConstantesSistema.INVALIDO_ID.toString())))){
						
						if(concluir != null){
							//1.3.3.Anormalidade de Leitura(obrigatório caso o tipo 
							//de ordem selecionado corresponda a ‘INSPEÇÃO DE ANORMALIDADE’
							throw new ActionServletException("atencao.campo.informado", null, "Anormalidade de Leitura");
						}
						else if(sessao.getAttribute("collectionHidrometroAnormalidade") != null){
							//1.3.3.Anormalidade de Leitura(obrigatório caso o tipo 
							//de ordem selecionado corresponda a ‘INSPEÇÃO DE ANORMALIDADE’
							throw new ActionServletException("atencao.campo.informado", null, "Anormalidade de Leitura");
						}	
					}
					else if(form.getNumeroOcorrenciasConsecutivas() == null || form.getNumeroOcorrenciasConsecutivas().equals("")){
						throw new ActionServletException("atencao.campo.informado", null, "Num. Ocorrências Consecutivas");
					}	
				}
				else{
					
					if(concluir != null){
						
						LeituraAnormalidade leituraAnormalidadeImovel = 
						Fachada.getInstancia().obterLeituraAnormalidadeValidaPorImovel(new Integer(form.getIdImovel()));
							
						if (leituraAnormalidadeImovel != null){
							
							String[] arrayLeituraAnormalidadeImovel = new String[1];
							arrayLeituraAnormalidadeImovel[0] = leituraAnormalidadeImovel.getId().toString();
							
							form.setAnormalidadeHidrometro(arrayLeituraAnormalidadeImovel);
						}
					}
					
					if(form.getAnormalidadeHidrometro() == null || 
					   (form.getAnormalidadeHidrometro().length == 1 && 
						((form.getAnormalidadeHidrometro())[0].equals(ConstantesSistema.INVALIDO_ID.toString())))){
						
						if(concluir != null){
							//1.3.3.Anormalidade de Leitura(obrigatório caso o tipo 
							//de ordem selecionado corresponda a ‘INSPEÇÃO DE ANORMALIDADE’
							throw new ActionServletException("atencao.imovel_sem_anormalidade_leitura_informada", null, form.getIdImovel());
						}
					}
				}
			}
		}
		
		ArrayList<Localidade> localidades = new ArrayList<Localidade>();
		ArrayList<SetorComercial> setores = new ArrayList<SetorComercial>();
		Collection<Localidade> locasSession = (Collection<Localidade>) httpServletRequest.getSession(false).getAttribute("colecaoLocalidadesContasEmCobrancaPorEmpresa");
		Collection<SetorComercial> setsSession = (Collection<SetorComercial>) httpServletRequest.getSession(false).getAttribute("colecaoSetoresContasEmCobrancaPorEmpresa");
		if(locasSession !=null){
			localidades.addAll(locasSession);
		}
		if(setsSession !=null){
			setores.addAll(setsSession);
		}
		if(form.getSetoresSelecionadosComponent() !=null && !form.getSetoresSelecionadosComponent().trim().equals("")){
			ArrayList<SetorComercial> setoresComponent = new ArrayList<SetorComercial>();
			String [] tempSet = form.getSetoresSelecionadosComponent().split("::");
			ArrayList<String> set = new ArrayList<String>();
			for(int i = 0; i < tempSet.length; i++){
				set.add(tempSet[i]);
			}	
			set.remove(0);
			
			for(int i = 0;  i < set.size(); i++){
				String[] vt2 = set.get(i+1).split(",");
				
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, set.get(i)));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, vt2[0]));
				filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade(FiltroSetorComercial.LOCALIDADE);
				
				Collection<SetorComercial> colecaoSetorComercial = Fachada.getInstancia().pesquisar(filtroSetorComercial,SetorComercial.class.getName());
				
				if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
					SetorComercial setTemp = (SetorComercial)Util.retonarObjetoDeColecao(colecaoSetorComercial);
					if(setores != null && setores.contains(setTemp)){
						Localidade locaTemp = setTemp.getLocalidade();
						if(localidades !=null ){
							localidades.remove(locaTemp);
						}
						for(int j = 0; j < setores.size(); j++){
							if(setores.get(j).getLocalidade().equals(locaTemp)){
								setores.remove(j);
							}
						}
					}
					setoresComponent.add(setTemp);
				}
				i++;
				if(i >= set.size()){
					break;
				}
			}
			
			String[] digitados = form.getDigitados().split("::");
			
			for(int i = 0;  i < setoresComponent.size(); i++){
				
				Integer idSetor = setoresComponent.get(i).getCodigo();
				Integer idLocalidade = setoresComponent.get(i).getLocalidade().getId();
								
				String qdrIni = httpServletRequest.getParameter("q_"+idLocalidade+"_"+idSetor+"_qdrIni");
				String qdrFin = httpServletRequest.getParameter("q_"+idLocalidade+"_"+idSetor+"_qdrFin");
				
				String rotIni = httpServletRequest.getParameter("r_"+idLocalidade+"_"+idSetor+"_rotIni");
				String rotFin = httpServletRequest.getParameter("r_"+idLocalidade+"_"+idSetor+"_rotFin");
				
				String seqIni = httpServletRequest.getParameter("s_"+idLocalidade+"_"+idSetor+"_seqIni");
				String seqFin = httpServletRequest.getParameter("s_"+idLocalidade+"_"+idSetor+"_seqFin");
				
				if(digitados.length > 0){
					for(int j = 0; j < digitados.length; j++){
						String[] dadosTemp = digitados[j].split("_");
						if(!digitados[j].equals("")){
							if(new Integer(dadosTemp[1]).compareTo(idLocalidade) == 0 && new Integer(dadosTemp[2]).compareTo(idSetor) == 0){
								String nome = dadosTemp[0]+"_"+dadosTemp[1]+"_"+dadosTemp[2]+"_";
								nome = nome.toLowerCase();
								dadosTemp[3] = dadosTemp[3].toLowerCase(); 
								String nomeTest = "";
								String term = "";								
								if(dadosTemp[3].equals("qdrini")){
									term = "qdrIni";									
								}
								else if(dadosTemp[3].equals("qdrfin")){
									term = "qdrFin";									
								}									
								else if(dadosTemp[3].equals("rotini")){
									term = "rotIni";									
								}
								else if(dadosTemp[3].equals("rotfin")){
									term = "rotFin";									
								}
								else if(dadosTemp[3].equals("seqini")){
									term = "seqIni";									
								}
								else if(dadosTemp[3].equals("seqfin")){
									term = "seqFin";									
								}
							
								nomeTest = nome+term;
								
								if(nomeTest.equals("q_"+dadosTemp[1]+"_"+dadosTemp[2]+"_qdrIni") && qdrIni == null){
									qdrIni = dadosTemp[4];
								}
								else if(nomeTest.equals("q_"+dadosTemp[1]+"_"+dadosTemp[2]+"_qdrFin") && qdrFin == null){
									qdrFin = dadosTemp[4];
								}
								else if(nomeTest.equals("r_"+dadosTemp[1]+"_"+dadosTemp[2]+"_rotIni") && rotIni == null){
									rotIni = dadosTemp[4];
								}
								else if(nomeTest.equals("r_"+dadosTemp[1]+"_"+dadosTemp[2]+"_rotFin") && rotFin == null){
									rotFin = dadosTemp[4];
								}
								else if(nomeTest.equals("s_"+dadosTemp[1]+"_"+dadosTemp[2]+"_seqIni") && seqIni == null){
									seqIni = dadosTemp[4];
								}
								else if(nomeTest.equals("s_"+dadosTemp[1]+"_"+dadosTemp[2]+"_seqFin") && seqFin == null){
									seqFin = dadosTemp[4];
								}															
							}
						}
					}
				}
				
				if(qdrIni !=null && !qdrIni.trim().equals("")){
					if(!Fachada.getInstancia().existeQuadra(setoresComponent.get(i).getId(), new Integer(qdrIni))){
						throw new ActionServletException("atencao.pesquisa_inexistente", "Quadra Inicial da Localidade "+idLocalidade+" e Setor "+idSetor+" é ");
					}
				}
				
				if(qdrFin !=null && !qdrFin.trim().equals("")){
					if(!Fachada.getInstancia().existeQuadra(setoresComponent.get(i).getId(), new Integer(qdrFin))){
						throw new ActionServletException("atencao.pesquisa_inexistente", "Quadra Final da Localidade "+idLocalidade+" e Setor "+idSetor+" é ");
					}
				}
				if((qdrIni !=null && !qdrIni.trim().equals("")) && (qdrFin == null || qdrFin.trim().equals(""))){
					throw new ActionServletException("atencao.msg_personalizada", "Informe a Quadra Final da Localidade "+idLocalidade+" e Setor "+idSetor);
				}
				if((qdrFin !=null && !qdrFin.trim().equals("")) && (qdrIni == null || qdrIni.trim().equals(""))){
					throw new ActionServletException("atencao.msg_personalizada", "Informe a Quadra Inicial da Localidade "+idLocalidade+" e Setor "+idSetor);
				}
				if(qdrIni !=null && !qdrIni.trim().equals("") && qdrFin !=null && !qdrFin.trim().equals("") && 
					new Integer(qdrIni).compareTo(new Integer(qdrFin)) > 0){
					throw new ActionServletException("atencao.msg_personalizada", "Quadra Final da Localidade "+idLocalidade+" e Setor "+idSetor+" é menor que a Quadra Inicial");
				}
				
				if((rotIni !=null && !rotIni.trim().equals("")) && (rotFin == null || rotFin.trim().equals(""))){
					throw new ActionServletException("atencao.msg_personalizada", "Informe a Rota Final da Localidade "+idLocalidade+" e Setor "+idSetor);
				}
				if((rotFin !=null && !rotFin.trim().equals("")) && (rotIni == null || rotIni.trim().equals(""))){
					throw new ActionServletException("atencao.msg_personalizada", "Informe a Rota Inicial da Localidade "+idLocalidade+" e Setor "+idSetor);
				}
				if(rotIni !=null && !rotIni.trim().equals("") && rotFin !=null && !rotFin.trim().equals("") && 
						new Integer(rotIni).compareTo(new Integer(rotFin)) > 0){
						throw new ActionServletException("atencao.msg_personalizada", "Rota Final da Localidade "+idLocalidade+" e Setor "+idSetor+" é menor que a Rota Inicial");
				}
				
				if((seqIni !=null && !seqIni.trim().equals("")) && (seqFin == null || seqFin.trim().equals(""))){
					throw new ActionServletException("atencao.msg_personalizada", "Informe a Seq. Final da Localidade "+idLocalidade+" e Setor "+idSetor);
				}
				if((seqFin !=null && !seqFin.trim().equals("")) && (seqIni == null || seqIni.trim().equals(""))){
					throw new ActionServletException("atencao.msg_personalizada", "Informe a Seq. Inicial da Localidade "+idLocalidade+" e Setor "+idSetor);
				}
				if(seqIni !=null && !seqIni.trim().equals("") && seqFin !=null && !seqFin.trim().equals("") && 
						new Integer(seqIni).compareTo(new Integer(seqFin)) > 0){
						throw new ActionServletException("atencao.msg_personalizada", "Seq. Final da Localidade "+idLocalidade+" e Setor "+idSetor+" é menor que a Seq. Inicial");
				}
			}
		}
		
		
		return retorno;
	}
}
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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroOSAtividadeProgramacaoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.FiltroOSProgramacaoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.OSAtividadeProgramacaoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.OSProgramacaoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoSituacao;
import gcom.atendimentopublico.ordemservico.OsAcompanhamentoMotivoNaoAceite;
import gcom.atendimentopublico.ordemservico.bean.PesquisarFiscalizarOSEncerradaAcompanhamentoHelper;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.integracao.FiltroServicoTerceiroAcompanhamentoServico;
import gcom.integracao.ServicoTerceiroAcompanhamentoServico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1199] � Acompanhar Arquivos de Roteiro
 * 
 * @author Th�lio Ara�jo
 *
 * @date 15/07/2011
 */
public class AtualizarFiscalizarOSAcompanhamentoServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);		

		// Precisa pegar a unidade do usuario do login que esta na sessao
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();		
				
		FiltrarFiscalizarOSAcompanhamentoServicoActionForm form = 
				(FiltrarFiscalizarOSAcompanhamentoServicoActionForm) actionForm;
				
		String metodo = httpServletRequest.getParameter("metodo");
		
		Collection<PesquisarFiscalizarOSEncerradaAcompanhamentoHelper> colecaoFiscalizarOSAcompanhamento = (Collection<PesquisarFiscalizarOSEncerradaAcompanhamentoHelper>) sessao
				.getAttribute("colecaoFiscalizarOSAcompanhamento");		
				
		if (Util.verificarNaoVazio(metodo) && (metodo.equals("concluir") || metodo.equals("ressalva"))){

			if (colecaoFiscalizarOSAcompanhamento != null && !colecaoFiscalizarOSAcompanhamento.isEmpty()) {
				Iterator<PesquisarFiscalizarOSEncerradaAcompanhamentoHelper> dadosFiscalizarOS = colecaoFiscalizarOSAcompanhamento.iterator();
				while (dadosFiscalizarOS.hasNext()) {
					PesquisarFiscalizarOSEncerradaAcompanhamentoHelper dadosFiscalizarOSHelper = (PesquisarFiscalizarOSEncerradaAcompanhamentoHelper) dadosFiscalizarOS
							.next();
					if (httpServletRequest.getParameter("idFiscalizarOSAcompanhamento"
							+dadosFiscalizarOSHelper.getIdOsAs().toString()) != null) {
						String indice = httpServletRequest
								.getParameter("idFiscalizarOSAcompanhamento"
									+ dadosFiscalizarOSHelper.getIdOsAs().toString());			
							
						Integer idOS = Integer.parseInt( dadosFiscalizarOSHelper.getIdOsAs() );
						//Integer idRA = dadosFiscalizarOSHelper.getIdRA();
						//System.out.println("id RA: "+idRA);
						
//						RegistroAtendimento registroAtendimento = new RegistroAtendimento();					
//						Date dataAtual = new Date();
//						registroAtendimento.setDataEncerramento(dataAtual);
//						registroAtendimento.setUltimaAlteracao(dataAtual);
//						registroAtendimento.setId(idRA);
//						AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
//						
//						RegistroAtendimentoUnidade registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
//						registroAtendimentoUnidade.setRegistroAtendimento(registroAtendimento);
//						registroAtendimentoUnidade.setUnidadeOrganizacional(usuario.getUnidadeOrganizacional());
//						registroAtendimentoUnidade.setUsuario(usuario);
//						registroAtendimentoUnidade.setUltimaAlteracao(dataAtual);
//						AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
//						atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
//						registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);						
					
						// Retorna as OsProgramacaoAcompanhamentoServico da RA em quest�o
//						ArrayList<Integer> colOSProgAS = (ArrayList<Integer>) fachada.pesquisarOSProgramacaoASPorIdRA(idRA);
						
						// Marca OS como validada
//						Iterator<Integer> it = colOSProgAS.iterator();
						
						Integer idMotivoENcerramento = null;
						
//						while(it.hasNext()){
//							Integer element =  (Integer) it.next();
							FiltroOSProgramacaoAcompanhamentoServico filtroOsProgamacaoAs = new FiltroOSProgramacaoAcompanhamentoServico();
							filtroOsProgamacaoAs.adicionarParametro(new ParametroSimples(FiltroOSProgramacaoAcompanhamentoServico.ORDEM_SERVICO_ID,idOS));
							filtroOsProgamacaoAs.adicionarCaminhoParaCarregamentoEntidade(FiltroOSProgramacaoAcompanhamentoServico.ORDEM_SERVICO);
							Collection<OSProgramacaoAcompanhamentoServico> colOsProgAcompServico = fachada.pesquisar(filtroOsProgamacaoAs, OSProgramacaoAcompanhamentoServico.class.getName());
							
							if(colOsProgAcompServico!=null && !colOsProgAcompServico.isEmpty()){
								
								for(OSProgramacaoAcompanhamentoServico osProgAcompanhamentoServico : colOsProgAcompServico){
									
									//[SB0009] 1.3
									if(indice.equals("2")){
							        	if(form.getObservacao().trim() != ""){
							        		osProgAcompanhamentoServico.setDescricaoObservacaoMotNaoAceite(form.getObservacao());
							        	}
										OsAcompanhamentoMotivoNaoAceite osAcompanhamentoMotivoNaoAceite = new OsAcompanhamentoMotivoNaoAceite();
										osAcompanhamentoMotivoNaoAceite.setId(new Integer(form.getIdMotivoNaoAceite()));
										osProgAcompanhamentoServico.setOsAcompanhamentoMotivoNaoAceite(osAcompanhamentoMotivoNaoAceite);
									}							
									// fim
									
									if(idMotivoENcerramento == null){
										if(osProgAcompanhamentoServico.getOrdemServicoSituacao() != null && osProgAcompanhamentoServico.getOrdemServicoSituacao().getId().intValue() == OrdemServicoSituacao.ENCERRADO){
											idMotivoENcerramento = osProgAcompanhamentoServico.getOrdemServico().getAtendimentoMotivoEncerramento().getId();
										}
									}
									
									osProgAcompanhamentoServico.setIndicadorValidaOS(ConstantesSistema.SIM);
									osProgAcompanhamentoServico.setDataUltimaAlteracao(new Date());
									fachada.atualizar(osProgAcompanhamentoServico);		
									
								}
								
							}
																		
//						}												
						
//						if (indice.equals("3")){							
//							atendimentoMotivoEncerramento.setId(idMotivoENcerramento);
//							registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
//							registroAtendimento.setParecerEncerramento(RegistroAtendimento.PARECER_CONFIRMADO_RESSALVA);
//						} else if(indice.equals("1")){
//							atendimentoMotivoEncerramento.setId(idMotivoENcerramento);
//							registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
//							registroAtendimento.setParecerEncerramento(RegistroAtendimento.PARECER_CONFIRMADO);
//						}else{
//							atendimentoMotivoEncerramento.setId(idMotivoENcerramento);
//							registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
//							registroAtendimento.setParecerEncerramento(RegistroAtendimento.PARECER_NAO_CONFIRMADO);
//						}
						
						// Encerrar RA
//						fachada.encerrarRegistroAtendimento(registroAtendimento, 
//							registroAtendimentoUnidade,
//							usuario,
//							null, 
//							null, 
//							null, 
//							null,
//			                false,null,false);
						
						//5.	Atualizar OSAtividadeProgramacaoAcompanhamentoServico
						if(indice!=null && indice.equals("3")){
							//Obtem a ultima programacao
							FiltroOSProgramacaoAcompanhamentoServico filtroOsProgamacaoResalva = new FiltroOSProgramacaoAcompanhamentoServico();
							filtroOsProgamacaoResalva.adicionarParametro(new ParametroSimples(FiltroOSProgramacaoAcompanhamentoServico.ORDEM_SERVICO_ID,idOS));
							filtroOsProgamacaoResalva.adicionarCaminhoParaCarregamentoEntidade(FiltroOSProgramacaoAcompanhamentoServico.ORDEM_SERVICO);
							Collection<?> colOsProgAcompServicoResalva = fachada.pesquisar(filtroOsProgamacaoResalva, OSProgramacaoAcompanhamentoServico.class.getName());
							OSProgramacaoAcompanhamentoServico osProgAcompanhamentoServicoResalva = 
									(OSProgramacaoAcompanhamentoServico) Util.retonarObjetoDeColecao(colOsProgAcompServicoResalva);
							
							OSAtividadeProgramacaoAcompanhamentoServico oSAtividade = null;
							ServicoTerceiroAcompanhamentoServico servico = null;
							
							FiltroOSAtividadeProgramacaoAcompanhamentoServico filtroOS = new FiltroOSAtividadeProgramacaoAcompanhamentoServico();
							filtroOS.adicionarParametro(new ParametroSimples(FiltroOSAtividadeProgramacaoAcompanhamentoServico.
									OS_PROG_ACOMP_SERVICO_ORDEM_SERVICO_ID,osProgAcompanhamentoServicoResalva.getOrdemServico().getId()));
							filtroOS.adicionarCaminhoParaCarregamentoEntidade(FiltroOSAtividadeProgramacaoAcompanhamentoServico.SERVICO_TERCEIRO_ACOMPANHAMENTO_SERVICO);
							Collection<OSAtividadeProgramacaoAcompanhamentoServico> colOSAtiv 
									= fachada.pesquisar(filtroOS, OSAtividadeProgramacaoAcompanhamentoServico.class.getName());
							oSAtividade = (OSAtividadeProgramacaoAcompanhamentoServico) Util.retonarObjetoDeColecao(colOSAtiv);
															
							if (oSAtividade.getServicoTerceiroAcompanhamentoServico()!=null){
								oSAtividade.setCodigoServTerceiroAcompServ(oSAtividade.getServicoTerceiroAcompanhamentoServico().getId());
							} else {
								oSAtividade.setCodigoServTerceiroAcompServ(null);
							}
							oSAtividade.setUsuario(usuario);
							
							//Obtem atividade pelo codigo de servico informado pelo usuario
							FiltroServicoTerceiroAcompanhamentoServico filtroServico = new FiltroServicoTerceiroAcompanhamentoServico();
							filtroServico.adicionarParametro(new ParametroSimples(
									FiltroServicoTerceiroAcompanhamentoServico.CODIGO,form.getIdTipoServico()));
							Collection<ServicoTerceiroAcompanhamentoServico> colServico
									= fachada.pesquisar(filtroServico, ServicoTerceiroAcompanhamentoServico.class.getName());
							servico = (ServicoTerceiroAcompanhamentoServico) Util.retonarObjetoDeColecao(colServico);
							oSAtividade.setServicoTerceiroAcompanhamentoServico(servico);
							
							oSAtividade.setDataAlteracaoCodServico( new Date());
							fachada.atualizar(oSAtividade);
							
							indice=null;
						}
						//Atualizar Parecer
//						String novoParecer = RegistroAtendimento.PARECER_CONFIRMADO_RESSALVA+
//								" O c�digo de servi�o "+codigoAntigo+
//								" informado em campo, foi alterado para o c�digo de servi�o "+servico.getCodigoServico()+".";
//						fachada.atualizarParecerRA(registroAtendimento.getId(), novoParecer);
					}
				}								
				
			}
		
		}
		//limpa os ampos do popup
		form.setIdMotivoNaoAceite("-1");
		form.setObservacao("");
		
		montarPaginaSucesso(httpServletRequest, "Registro(s) de Atendimento(s)  "
			    + "atualizados com sucesso.", "Fiscalizar OS Acompanhamento Servico",
	            "exibirFiltrarFiscalizarOSAcompanhamentoServicoAction.do?menu=sim");
	
		return retorno;
		
	}	
	
	
}
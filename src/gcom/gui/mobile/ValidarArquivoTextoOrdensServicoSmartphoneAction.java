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
package gcom.gui.mobile;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroSituacao;
import gcom.mobile.bean.ArquivoTxtOSCobrancaSmartphoneHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * 
 * 
 * @author Hugo Azevedo
 * @date 20/09/2011
 *  
 * 
 */
public class ValidarArquivoTextoOrdensServicoSmartphoneAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("consultarArquivoTextoOSCobrancaSmartphone");
		
		//Sess�o
		HttpSession sessao = this.getSessao(httpServletRequest);     
		
		//Form
		ConsultarArquivoTextoOSCobrancaSmartphoneActionForm form = (ConsultarArquivoTextoOSCobrancaSmartphoneActionForm) actionForm;
		
		//Fachada
		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		//Indicadores de confirma��o
		String confirmado = httpServletRequest.getParameter("confirmado");
		String finalizar = httpServletRequest.getParameter("tipoRelatorio");
		
		String idNovoLeiturista = "";
		
		
		String liberar = (String) httpServletRequest.getParameter("liberar");
		String opcao = "";
//		String situacao = "";
		Integer situacao = null;
		String idTipoOS = "";
		
		@SuppressWarnings("unchecked")
		Collection<ArquivoTxtOSCobrancaSmartphoneHelper> colecaoArquivoTextoOSCobrancaSmartphone = 
						(Collection<ArquivoTxtOSCobrancaSmartphoneHelper>) sessao.getAttribute("colecaoArquivoTextoOSCobrancaSmartphone");
		
		if(liberar == null)
			liberar = "";
	
		//Situa��o escolhida
		if (liberar.equals("0")) {
			opcao = "N�O LIBERAR";
		} else if (liberar.equals("1")) {
			opcao = "LIBERAR";
		} else if (liberar.equals("2")) {
			opcao = "EM CAMPO";
		} else if (liberar.equals("3") || (finalizar != null && finalizar.equals("FINALIZAR"))) {
			opcao = "FINALIZAR";
		} else if (liberar.equals("4")) {
			opcao = "INFORMAR LEITURISTA";
			idNovoLeiturista = httpServletRequest.getParameter("idNovoLeiturista");
			idTipoOS = form.getIdTipoOS();
		}
		
		
		if (form.getIdsRegistros() != null) {
			
			Vector<Integer> v = new Vector<Integer>();
			for (int i = 0; i < form.getIdsRegistros().length; i++) {
				v.add(new Integer(form.getIdsRegistros()[i]));

			}
			
			//Filtrar apenas os Arquivos escolhidos
			colecaoArquivoTextoOSCobrancaSmartphone = this.filtrarOSVisitas(v,colecaoArquivoTextoOSCobrancaSmartphone);
			
			
			
			//[FS0007] - Verificar situa��o do arquivo
			//********************************************************************
			
			//Caso algum dos  arquivos selecionados esteja na situa��o "Dispon�vel"
			if(this.validarSituacao(colecaoArquivoTextoOSCobrancaSmartphone, SituacaoTransmissaoLeitura.DISPONIVEL)){
				
				//N�o existe "Agente Comercial Informado"
				if(!this.validarAgenteComercial(colecaoArquivoTextoOSCobrancaSmartphone) && !opcao.equals("INFORMAR LEITURISTA")){
					throw new ActionServletException("atencao.agente_comercial_informado_txt");
				}
				
				//Usu�rio solicitou "N�o Libera��o do Arquivo"
				if(opcao.equals("N�O LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_sit_disponivel");
				}
				
				//Usu�rio solicitou "Colocar Arquivo em Campo"
				else if(opcao.equals("EM CAMPO")){
					throw new ActionServletException("atencao.arquivo_txt_sit_disponivel_campo");
				}
				
				else if(opcao.equals("FINALIZAR")){
					throw new ActionServletException("atencao.arquivo_txt_sit_disponivel_finalizado");
				}
				
			}
			
			//Caso algum dos  arquivos selecionados esteja na situa��o "Dispon�vel"
			if(this.validarSituacao(colecaoArquivoTextoOSCobrancaSmartphone, SituacaoTransmissaoLeitura.LIBERADO)){
				
				//Usu�rio solicitou "EM CAMPO"
				if(opcao.equals("EM CAMPO")){
					throw new ActionServletException("atencao.arquivo_txt_sit_liberado_campo");
				}
				
				//Usu�rio solicitou "Liberar Arquivo"
				else if(opcao.equals("LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_liberado");
				}
				
				else if(opcao.equals("FINALIZAR")){
					throw new ActionServletException("atencao.arquivo_txt_sit_liberado_finalizado");
				}
			}
			
			
			if(this.validarSituacao(colecaoArquivoTextoOSCobrancaSmartphone, SituacaoTransmissaoLeitura.TRANSMITIDO) || 
			   this.validarSituacao(colecaoArquivoTextoOSCobrancaSmartphone, SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO)) {
				
				if(opcao.equals("FINALIZAR")){
					throw new ActionServletException("atencao.arquivo_txt_finalizado");
				}
				else if(opcao.equals("N�O LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_finalizado");
				}
				else if(opcao.equals("LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_finalizado");
				}
				else if(opcao.equals("EM CAMPO")){
					throw new ActionServletException("atencao.arquivo_txt_finalizado");
				}
				else if(opcao.equals("INFORMAR LEITURISTA")){
					throw new ActionServletException("atencao.arquivo_txt_finalizado");
				}
			}
			
			if(this.validarSituacao(colecaoArquivoTextoOSCobrancaSmartphone, SituacaoTransmissaoLeitura.EM_CAMPO)){
				
				//Usu�rio solicitou "Colocar Arquivo em Campo"
				if(opcao.equals("EM CAMPO")){
					throw new ActionServletException("atencao.arquivo_txt_em_campo");
				}
				
				//Usu�rio solicitou "Liberar Arquivo"
				else if(opcao.equals("LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_em_campo_liberado");
				}
				
				else if(opcao.equals("N�O LIBERAR")){
					throw new ActionServletException("atencao.arquivo_txt_em_campo_liberado");
				}
				else if(opcao.equals("INFORMAR LEITURISTA")){
					throw new ActionServletException("atencao.arquivo_txt_em_campo_agente_comercial");
				}
				
			}

			//******************************************************************
			
			
			if(opcao.equals("LIBERAR")){
				fachada.atualizarListaArquivoTextoOSCobrancaSmartphone(
						colecaoArquivoTextoOSCobrancaSmartphone,
						SituacaoTransmissaoLeitura.LIBERADO,
						null,
						new Date()
				);
				
				situacao = SituacaoTransmissaoLeitura.LIBERADO; 
//				situacao = "LIBERADO";
			}
			else if(opcao.equals("N�O LIBERAR")){
				fachada.atualizarListaArquivoTextoOSCobrancaSmartphone(
						colecaoArquivoTextoOSCobrancaSmartphone,
						SituacaoTransmissaoLeitura.DISPONIVEL,
						null,
						new Date()
					);
				
				situacao = SituacaoTransmissaoLeitura.DISPONIVEL;
//				situacao = "DISPON�VEL";
			}
			else if(opcao.equals("EM CAMPO")){
					fachada.atualizarListaArquivoTextoOSCobrancaSmartphone(
						colecaoArquivoTextoOSCobrancaSmartphone,
						SituacaoTransmissaoLeitura.EM_CAMPO,
						null,
						new Date()
					);
				
					situacao = SituacaoTransmissaoLeitura.EM_CAMPO;
//					situacao = "EM CAMPO";
			}
			else if(opcao.equals("FINALIZAR")){
				if( (confirmado == null	|| !confirmado.trim().equalsIgnoreCase("ok")) && 
			 			verificarOSNaoEncerradas(colecaoArquivoTextoOSCobrancaSmartphone, fachada)){
					
						httpServletRequest.setAttribute("caminhoActionConclusao",
								"/gsan/validarArquivoTextoOSCobrancaSmartphoneAction.do");
						
						httpServletRequest.setAttribute("tipoRelatorio", "FINALIZAR");
						
						//Tela de confirma��o
						return montarPaginaConfirmacao(
								"atencao.atencao.os_nao_encerradas.confirmacao",
								httpServletRequest, actionMapping);
					
				}
				
				fachada.atualizarListaArquivoTextoOSCobrancaSmartphone(
						colecaoArquivoTextoOSCobrancaSmartphone,
						SituacaoTransmissaoLeitura.TRANSMITIDO,
						null,
						new Date()
					);
				
//				situacao = "FINALIZADO";
				situacao = SituacaoTransmissaoLeitura.TRANSMITIDO;
			}
			
			else if(opcao.equals("INFORMAR LEITURISTA")){
				FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
				filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.ID, new Integer(idNovoLeiturista)));
				
				@SuppressWarnings("unchecked")
				Collection<Leiturista> colecaoLeiturista = fachada.pesquisar(filtroLeiturista, Leiturista.class.getName());
				Leiturista leiturista = (Leiturista) Util.retonarObjetoDeColecao(colecaoLeiturista);
				
				
				if(this.validarSituacaoInformarAgenteComercial(colecaoArquivoTextoOSCobrancaSmartphone, SituacaoTransmissaoLeitura.DISPONIVEL)){
					throw new ActionServletException("atencao.arquivo_txt_informar_agente_comercial");
				}
				
				//2. Caso o Tipo da Ordem de Servi�o corresponda a "O.S. DE MICROMEDICAO"
				if(idTipoOS != null && (Integer.valueOf(idTipoOS)).equals(OrdemServico.ORDEM_SERVICO_MICROMEDICAO)){
					
					FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
					filtroHidrometro.adicionarParametro(new ParametroSimples(FiltroHidrometro.HIDROMETRO_LOCAL_ARMAZENAGEM_LEITURISTA_ID,idNovoLeiturista));
					filtroHidrometro.adicionarParametro(new ParametroSimples(FiltroHidrometro.HIDROMETRO_LOCAL_ARMAZENAGEM_IC_USO,ConstantesSistema.SIM));
					filtroHidrometro.adicionarParametro(new ParametroSimples(FiltroHidrometro.HIDROMETRO_SITUACAO_ID,HidrometroSituacao.DISPONIVEL));
					
					Collection<Hidrometro> colecaoHidrometros = fachada.pesquisar(filtroHidrometro,Hidrometro.class.getName());
					
					//1. Caso n�o exista registro na tabela MICROMEDICAO.HIDROMETRO
					if(colecaoHidrometros == null || colecaoHidrometros.isEmpty()){
						
						//1. exibir a mensagem "N�o existem Hidr�metros informados para o Agente Comercial selecionado."
						throw new ActionServletException("atencao.arquivo_txt_nao_ex_hidrometros_agente");
					}
					else{
						//2. Caso contr�rio, caso a quantidade de registros 
						//   seja superior a PARM_NNLIMITEOSCOBRANCA da tabela CADASTRO.SISTEMA_PARAMETROS
						if(colecaoHidrometros.size() > sistemaParametro.getNumeroLimiteOSCobranca().intValue()){
							
							//2. exibir a mensagem "Existem mais de 
							//   <<PARM_NNLIMITEOSCOBRANCA>> hidr�metros 
							//   informados para o Agente Comercial selecionado."
							throw new ActionServletException("atencao.arquivo_txt_existem_mais_hidr_limite",sistemaParametro.getNumeroLimiteOSCobranca().toString());
							
						}
					}
				}
				
				
				fachada.atualizarListaArquivoTextoOSCobrancaSmartphone(
						colecaoArquivoTextoOSCobrancaSmartphone,
						SituacaoTransmissaoLeitura.DISPONIVEL,
						leiturista,
						new Date()
					);
				
				situacao = SituacaoTransmissaoLeitura.DISPONIVEL;
//				situacao = "DISPON�VEL";
				
			}
			
		}
		
		httpServletRequest.setAttribute("situacao", situacao);
		
/*		montarPaginaSucesso(httpServletRequest,
				"Arquivo Texto Alterado para " + situacao.toLowerCase() + " com sucesso.",
				"Realizar outra Manuten��o de Arquivo Texto",
				"exibirConsultarDadosArquivoTextoOSCobrancaSmartphoneAction.do?menu=sim");
*/		
		sessao.removeAttribute("colecaoArquivoTextoOSCobrancaSmartphone");
		return retorno;

	}
	
	
	
	
	private boolean verificarOSNaoEncerradas(
			Collection<ArquivoTxtOSCobrancaSmartphoneHelper> colecaoArquivoTextoOSVisita, Fachada fachada) {
		boolean retorno = true;
		@SuppressWarnings("rawtypes")
		Iterator it = colecaoArquivoTextoOSVisita.iterator();
		while(it.hasNext()){
			ArquivoTxtOSCobrancaSmartphoneHelper at = (ArquivoTxtOSCobrancaSmartphoneHelper)it.next();
			if(fachada.verificarArquivoTextoOSPendente(at.getIdArquivo())){
				retorno = false;
				break;
			}
		}
		return retorno;
	}




	private Collection<ArquivoTxtOSCobrancaSmartphoneHelper> filtrarOSVisitas(
			Vector<Integer> v,
			Collection<ArquivoTxtOSCobrancaSmartphoneHelper> colecaoArquivoTextoOSVisita) {

			Collection<ArquivoTxtOSCobrancaSmartphoneHelper> retorno = new ArrayList<ArquivoTxtOSCobrancaSmartphoneHelper>();

			Iterator<Integer> it = v.iterator();
			Iterator<ArquivoTxtOSCobrancaSmartphoneHelper> it2 = null;
			
			while(it.hasNext()){
				Integer id = it.next();
				it2 = colecaoArquivoTextoOSVisita.iterator();
				while(it2.hasNext()){
					ArquivoTxtOSCobrancaSmartphoneHelper at = it2.next();
					if(at.getIdArquivo().intValue() == id.intValue())
						retorno.add(at);
				}
			}
			
			return retorno;
	}


	//Verificar se h� agentes comerciais em todos os registros
	private boolean validarAgenteComercial(Collection<ArquivoTxtOSCobrancaSmartphoneHelper> v){
		boolean retorno = true;

		Iterator<ArquivoTxtOSCobrancaSmartphoneHelper> it = v.iterator();
		while(it.hasNext()){
			ArquivoTxtOSCobrancaSmartphoneHelper at = it.next();
			if(at.getNomeFuncionario() == null && at.getNomeCliente() == null){
				retorno = false;
				break;
			}
		}
		
		return retorno;
	}
	
	
	private boolean validarSituacao(Collection<ArquivoTxtOSCobrancaSmartphoneHelper> v,Integer situacao){
		boolean retorno = false;
		Iterator<ArquivoTxtOSCobrancaSmartphoneHelper> it = v.iterator();
		while(it.hasNext()){
			ArquivoTxtOSCobrancaSmartphoneHelper at = it.next();
			if(at.getIdSituacao().intValue() == situacao.intValue()){
				retorno = true;
				break;
			}
		}
		
		return retorno;
	}
	
	private boolean validarSituacaoInformarAgenteComercial(Collection<ArquivoTxtOSCobrancaSmartphoneHelper> v,Integer situacao){
		boolean retorno = false;
		Iterator<ArquivoTxtOSCobrancaSmartphoneHelper> it = v.iterator();
		while(it.hasNext()){
			ArquivoTxtOSCobrancaSmartphoneHelper at = it.next();
			if(at.getIdSituacao().intValue() != situacao.intValue()){
				retorno = true;
				break;
			}
		}
		
		return retorno;
	}

}

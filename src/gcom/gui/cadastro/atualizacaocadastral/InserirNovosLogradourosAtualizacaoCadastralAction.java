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
* Anderson Italo Felinto de Lima
* Anderson Cabral do Nascimento
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
package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.atualizacaocadastral.CepAtlzCad;
import gcom.cadastro.atualizacaocadastral.FiltroImovelAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.LogradouroAtlzCad;
import gcom.cadastro.atualizacaocadastral.LogradouroBairroAtlzCad;
import gcom.cadastro.atualizacaocadastral.LogradouroCepAtlzCad;
import gcom.cadastro.atualizacaocadastral.bean.AtualizacaoCadastralLogradouroHelper;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.CepTipo;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroLogradouroBairro;
import gcom.cadastro.endereco.FiltroLogradouroCep;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
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

import sun.misc.FormattedFloatingDecimal.Form;

/**
 * UC1442 - InserirNovosLogradouros - AtualizacaoCadastra
 * 
 * Action responsavel por inserir os logradouros adicionados pelo tablet
 * 
 * @author Anderson Cabral
 * @date 11/03/2012
 */
public class InserirNovosLogradourosAtualizacaoCadastralAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
	
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		
		InserirNovosLogradourosAtualizacaoCadastralActionForm inserirNovosLogradourosActionForm = (InserirNovosLogradourosAtualizacaoCadastralActionForm) actionForm;

		//Atualizar Logradouros
		if(httpServletRequest.getParameter("atualizarLogradouro") != null){			
			ArrayList<AtualizacaoCadastralLogradouroHelper> colecaoLogradouroHelper = (ArrayList<AtualizacaoCadastralLogradouroHelper>) sessao.getAttribute("colecaoLogradouroHelper");

			
			//IT006 - Inserir Logradouro.
			String[] idsRegistrosAtualizar  = inserirNovosLogradourosActionForm.getIdsRegistros();			
			if(idsRegistrosAtualizar != null && idsRegistrosAtualizar.length > 0){
				Logradouro logradouro;
				for(String id : idsRegistrosAtualizar){
					boolean substituir = false;
					
					//1 - Inseri Logradouro
					AtualizacaoCadastralLogradouroHelper  logradouroHelper = pesquisarLogradouroAtlzCad(colecaoLogradouroHelper, id);
					LogradouroAtlzCad logradouroAtlzCad = null;
					
					if(logradouroHelper != null  && 
							(logradouroHelper.getIdSubstituirLogra() == null || logradouroHelper.getIdSubstituirLogra().equalsIgnoreCase(""))){
						logradouroAtlzCad = logradouroHelper.getLogradouroAtlzCad();
					}else{
						substituir = true;
					}
					
					if(!substituir){
						//1.1 - Verifica se o logradouro ja foi incluido no gsan
						if(logradouroAtlzCad.getLogradouro() == null || logradouroAtlzCad.getLogradouro().getId() == null){
							logradouro = new Logradouro();
							
							logradouro.setNome(logradouroAtlzCad.getNome());
							logradouro.setNomePopular(logradouroAtlzCad.getNomePopular());
							logradouro.setLoteamento(logradouroAtlzCad.getNomeLoteamento());
							logradouro.setMunicipio(logradouroAtlzCad.getMunicipio());
							logradouro.setLogradouroTitulo(logradouroAtlzCad.getLogradouroTitulo());
							logradouro.setLogradouroTipo(logradouroAtlzCad.getLogradouroTipo());
							logradouro.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
							logradouro.setIndicadorEnvioAtualizacaoCadastral(ConstantesSistema.NAO);
							logradouro.setUltimaAlteracao(new Date());
							
							Integer idLogradouro = (Integer) fachada.inserir(logradouro);
							logradouro.setId(idLogradouro);
							logradouroAtlzCad.setLogradouro(logradouro);
							fachada.atualizar(logradouroAtlzCad);
							
						}else{
							logradouro = new Logradouro();
							logradouro.setId(logradouroAtlzCad.getLogradouro().getId());
						}
						
						
						//4 - Inseri LogradouroBairro
						ArrayList<LogradouroBairroAtlzCad> colecaoLogradouroBairroAtlzCad = pesquisarLogradouroBairroAtlzCad(colecaoLogradouroHelper, id);
						ArrayList<LogradouroBairro> colecaoLogradouroBairro = null;
						
						if(colecaoLogradouroBairroAtlzCad != null){
							colecaoLogradouroBairro = new ArrayList<LogradouroBairro>();
							for(LogradouroBairroAtlzCad logradouroBairroAtlzCad : colecaoLogradouroBairroAtlzCad){
								LogradouroBairro logradouroBairro = new LogradouroBairro();
								logradouroBairro.setLogradouro(logradouro);
								logradouroBairro.setBairro(logradouroBairroAtlzCad.getBairro());
								logradouroBairro.setUltimaAlteracao(new Date());
								
								
								//verifica se existe logradouro bairro
								FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();
								filtroLogradouroBairro.adicionarParametro( new ParametroSimples( FiltroLogradouroBairro.ID_BAIRRO, logradouroBairroAtlzCad.getBairro().getId()));
								filtroLogradouroBairro.adicionarParametro( new ParametroSimples( FiltroLogradouroBairro.ID_LOGRADOURO, logradouro.getId()));
								filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.BAIRRO);
								
								Collection<LogradouroBairro> colecaoLogBairro = fachada.pesquisar(filtroLogradouroBairro, LogradouroBairro.class.getName());
								
								if ( colecaoLogBairro == null || colecaoLogBairro.isEmpty() ) {
									Integer idLogradouroBairro = (Integer) fachada.inserir(logradouroBairro);
									logradouroBairro.setId(idLogradouroBairro);
								}
								
								colecaoLogradouroBairro.add(logradouroBairro);
							}
						}
						
						//2 - Inseri Cep
						ArrayList<CepAtlzCad> colecaoCepAtlzCad = pesquisarCepAtlzCad(colecaoLogradouroHelper, id);
						ArrayList<LogradouroCep> colecaoLogradouroCep = null;
						
						if(colecaoCepAtlzCad != null){
							colecaoLogradouroCep = new ArrayList<LogradouroCep>();
							for(CepAtlzCad cepAtlzCad : colecaoCepAtlzCad){
								/************verificar se o cep ja existe no gsan **********/
								FiltroCep filtroCep = new FiltroCep();
								filtroCep.adicionarParametro(new ParametroSimples(FiltroCep.CODIGO,
										cepAtlzCad.getCodigoCep()));
	
								ArrayList<Cep> colecaoCep = (ArrayList<Cep>) fachada.pesquisar(filtroCep, Cep.class.getName());
								Cep cep = (Cep) Util.retonarObjetoDeColecao(colecaoCep);
								
								if(cep == null){
									cep = new Cep();
									cep.setCodigo(cepAtlzCad.getCodigoCep());
									
									if(logradouroAtlzCad != null && logradouroAtlzCad.getLogradouro() != null){
										cep.setLogradouro(logradouroAtlzCad.getLogradouro().getNome());
									}
									
									if(logradouroAtlzCad != null && logradouroAtlzCad.getLogradouro() != null 
											&& logradouroAtlzCad.getLogradouro().getMunicipio() != null){
										
										cep.setMunicipio(logradouroAtlzCad.getLogradouro().getMunicipio().getNome());
									}
									
									if(logradouroAtlzCad != null && logradouroAtlzCad.getLogradouro() != null 
											&& logradouroAtlzCad.getLogradouro().getMunicipio() != null && logradouroAtlzCad.getLogradouro().getMunicipio().getUnidadeFederacao() != null){
										cep.setSigla(logradouroAtlzCad.getLogradouro().getMunicipio().getUnidadeFederacao().getSigla());
									}
									
									if(colecaoLogradouroBairro != null && !colecaoLogradouroBairro.isEmpty()
											&& colecaoLogradouroBairro.get(0).getBairro() != null){
										cep.setBairro(colecaoLogradouroBairro.get(0).getBairro().getNome());
									}
									
									if (logradouroAtlzCad != null && logradouroAtlzCad.getLogradouroTipo() != null 
											&& logradouroAtlzCad.getLogradouroTipo().getDescricao() != null){
										
										cep.setDescricaoTipoLogradouro(logradouroAtlzCad.getLogradouroTipo().getDescricao());
									}
										
									cep.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
									cep.setUltimaAlteracao(new Date());
									
									Integer idCep = (Integer) fachada.inserir(cep);
									cep.setCepId(idCep);
								}
								
								//3 - Inseri LogradouroCep
								LogradouroCep logradouroCep = new LogradouroCep();
								logradouroCep.setLogradouro(logradouro);
								logradouroCep.setCep(cep);							
								logradouroCep.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
								logradouroCep.setUltimaAlteracao(new Date());
								
								
								//verifica se existe logradouro bairro
								FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();
								filtroLogradouroCep.adicionarParametro( new ParametroSimples( FiltroLogradouroCep.ID_CEP, cep.getCepId() ) );
								filtroLogradouroCep.adicionarParametro( new ParametroSimples( FiltroLogradouroCep.ID_LOGRADOURO, logradouro.getId() ) );
								
								Collection<LogradouroCep> colecaoLogCep = fachada.pesquisar(filtroLogradouroCep, LogradouroCep.class.getName());
								
								if ( colecaoLogCep == null || colecaoLogCep.isEmpty() ) {
									Integer idLogradouroCep = (Integer) fachada.inserir(logradouroCep);
									logradouroCep.setId(idLogradouroCep);
								
								}
	
								colecaoLogradouroCep.add(logradouroCep);							
							}
						}
						
						//5 - Atualiza ImovelAtlzCad
						ArrayList<ImovelAtualizacaoCadastral> colecaoImovelAtlzCad = pesquisarImovelAtlzCad(logradouroAtlzCad.getCodigo(), fachada);
						if(colecaoImovelAtlzCad != null){
							for(ImovelAtualizacaoCadastral imovelAtlzCad : colecaoImovelAtlzCad){
								imovelAtlzCad.setIdLogradouro(Long.valueOf(logradouro.getId()));
								
								//pesquisa o logradouroCep do imovel e insere o novo id
								if(colecaoLogradouroCep != null && imovelAtlzCad.getCodigoCep() != null){
									for(LogradouroCep logradouroCep : colecaoLogradouroCep){
										if(logradouroCep.getCep().getCodigo().equals(imovelAtlzCad.getCodigoCep())){
											imovelAtlzCad.setIdLogradouroCep(logradouroCep.getId());
											break;
										}
									}
								}
								
								//pesquisa o logradouroBairro do imovel e insere o novo id
								if(colecaoLogradouroBairro != null && imovelAtlzCad.getIdBairro() != null){
									for(LogradouroBairro logradouroBairro : colecaoLogradouroBairro){
										if(logradouroBairro.getBairro().getId().equals(imovelAtlzCad.getIdBairro())){
											imovelAtlzCad.setIdLogradouroBairro(logradouroBairro.getId());
											break;
										}
									}
								}
								
								imovelAtlzCad.setUltimaAlteracao(new Date());
								fachada.atualizar(imovelAtlzCad);
							}						
						}
						
						//6 - Atualiza LogradouroAtlzCad
						logradouroAtlzCad.setIndicadorAtualizado(ConstantesSistema.SIM);
						logradouroAtlzCad.setUltimaAlteracao(new Date());
						fachada.atualizar(logradouroAtlzCad);
					}
				}
			}
			
			//IT005 - Substituir Logradouro do Imovel Atualizacao Cadastral
			ArrayList<String> idsRegistrosSubstituir = new ArrayList<String>();
			ArrayList<AtualizacaoCadastralLogradouroHelper> colecaoLogradouroHelperSubstituir = new ArrayList<AtualizacaoCadastralLogradouroHelper>();
			
			for(AtualizacaoCadastralLogradouroHelper logradouroHelper : colecaoLogradouroHelper){
				if(logradouroHelper.getIdSubstituirLogra() != null){
					colecaoLogradouroHelperSubstituir.add(logradouroHelper);
				}
			}
			
			if(colecaoLogradouroHelperSubstituir != null){
				for(AtualizacaoCadastralLogradouroHelper logradouroHelperSubstituir : colecaoLogradouroHelperSubstituir){
					ArrayList<ImovelAtualizacaoCadastral> colecaoImoveisAtlzCad = pesquisarImovelAtlzCad(logradouroHelperSubstituir.getLogradouroAtlzCad().getCodigo().toString(), fachada);
				
					if(colecaoImoveisAtlzCad != null){
						for(ImovelAtualizacaoCadastral imovelAtualizacaoCadastral : colecaoImoveisAtlzCad){
							imovelAtualizacaoCadastral.setIdLogradouro(Long.valueOf(logradouroHelperSubstituir.getIdSubstituirLogra()));
							
							///Pesquisa o LogradouroBairro do logradouro substituto
							FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();
							filtroLogradouroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID_LOGRADOURO,
									logradouroHelperSubstituir.getIdSubstituirLogra()));
							filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.BAIRRO);
							
							ArrayList<LogradouroBairro> colecaoLogradouroBairro = (ArrayList<LogradouroBairro>) fachada.pesquisar(filtroLogradouroBairro, LogradouroBairro.class.getName());
							
							//Verifica se existe logradouroBairro com o bairro do imovel, 
							//caso tenha, inseri no imovel o id do logradouroBairro encontrado.
							//caso não tenha, inseri no imovel o id do primeiro logradouroBairro e Bairro encontrado
							if(colecaoLogradouroBairro != null){
								boolean existeBairro = false;
								for(LogradouroBairro logradouroBairro : colecaoLogradouroBairro){
									if(logradouroBairro.getBairro().getId().equals(imovelAtualizacaoCadastral.getIdBairro())){
										imovelAtualizacaoCadastral.setIdLogradouroBairro(logradouroBairro.getId());
										existeBairro = true;
									}
								}
								
								if(!existeBairro){
									imovelAtualizacaoCadastral.setIdLogradouroBairro(colecaoLogradouroBairro.get(0).getId());
									imovelAtualizacaoCadastral.setIdBairro(colecaoLogradouroBairro.get(0).getBairro().getId());
								}
							}
							
							//Pesquisa o LogradouroCep do logradouro substituto
							FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();
							filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_LOGRADOURO,
									logradouroHelperSubstituir.getIdSubstituirLogra()));
							filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.CODIGO_CEP,
									imovelAtualizacaoCadastral.getCodigoCep()));
							
							ArrayList<LogradouroCep> colecaoLogradouroCep = (ArrayList<LogradouroCep>) fachada.pesquisar(filtroLogradouroCep, LogradouroCep.class.getName());				
							LogradouroCep logradouroCep = (LogradouroCep) Util.retonarObjetoDeColecao(colecaoLogradouroCep);
							
							if(logradouroCep != null){
								imovelAtualizacaoCadastral.setIdLogradouroCep(logradouroCep.getId());
							}else{
								
								//inseri CEP
								/************verificar se o cep ja existe no gsan **********/
								FiltroCep filtroCep = new FiltroCep();
								filtroCep.adicionarParametro(new ParametroSimples(FiltroCep.CODIGO,
										imovelAtualizacaoCadastral.getCodigoCep()));
	
								ArrayList<Cep> colecaoCep = (ArrayList<Cep>) fachada.pesquisar(filtroCep, Cep.class.getName());
								Cep cep = (Cep) Util.retonarObjetoDeColecao(colecaoCep);
								
								// logradouro bairro substituto
								LogradouroBairro logrBairroSubstituto = null;
								FiltroLogradouroBairro filtroLogradouroBairroSubs = new FiltroLogradouroBairro();
								filtroLogradouroBairroSubs.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID_LOGRADOURO, logradouroHelperSubstituir.getIdSubstituirLogra()));
								filtroLogradouroBairroSubs.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.BAIRRO);
								filtroLogradouroBairroSubs.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.LOGRADOURO);
								filtroLogradouroBairroSubs.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.LOGRADOURO_MUNICIPIO);
								filtroLogradouroBairroSubs.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.LOGRADOURO_MUNICIPIO_UNIDADE_FEDERACAO);
								filtroLogradouroBairroSubs.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.LOGRADOURO_TIPO);	
								
								Collection<LogradouroBairro> colLograBairroSubs = fachada.pesquisar(filtroLogradouroBairroSubs, LogradouroBairro.class.getName());
								
								if(colLograBairroSubs != null && !colLograBairroSubs.isEmpty()){
									logrBairroSubstituto = (LogradouroBairro) Util.retonarObjetoDeColecao(colLograBairroSubs);
								}
								
								if(cep == null){
									cep = new Cep();
									cep.setCodigo(imovelAtualizacaoCadastral.getCodigoCep());
									
									//nomeLogradouro
									if(logrBairroSubstituto != null && !colLograBairroSubs.isEmpty() && logrBairroSubstituto.getLogradouro()!= null){
										cep.setLogradouro(logrBairroSubstituto.getLogradouro().getNome());
									}
								
									//nomeMunicipio
									if(logrBairroSubstituto != null && !colLograBairroSubs.isEmpty() && logrBairroSubstituto.getLogradouro() != null && logrBairroSubstituto.getLogradouro().getMunicipio()!= null){
										cep.setMunicipio(logrBairroSubstituto.getLogradouro().getMunicipio().getNome());
									}
									//ufSigla
									if(logrBairroSubstituto != null && !colLograBairroSubs.isEmpty() && logrBairroSubstituto.getLogradouro() != null && logrBairroSubstituto.getLogradouro().getMunicipio()!= null && logrBairroSubstituto.getLogradouro().getMunicipio().getUnidadeFederacao() != null){
										cep.setSigla(logrBairroSubstituto.getLogradouro().getMunicipio().getUnidadeFederacao().getSigla());
									}
									//nomeBairro
									if(logrBairroSubstituto != null && !colLograBairroSubs.isEmpty() && logrBairroSubstituto.getBairro() != null){
										cep.setBairro(logrBairroSubstituto.getBairro().getNome());
									}
									
									if (logrBairroSubstituto != null && logrBairroSubstituto.getLogradouro() != null &&
											logrBairroSubstituto.getLogradouro().getLogradouroTipo() != null && logrBairroSubstituto.getLogradouro().getLogradouroTipo().getDescricao() != null){
										
										cep.setDescricaoTipoLogradouro(logrBairroSubstituto.getLogradouro().getLogradouroTipo().getDescricao());
									}
									
									
									CepTipo cepTipo = new CepTipo();
									cepTipo.setId(0);
									
									cep.setCepTipo(cepTipo);
									cep.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
									cep.setUltimaAlteracao(new Date());
									
									Integer idCep = (Integer) fachada.inserir(cep);
									cep.setCepId(idCep);
								}
								
								//inseri LogradouroCep
								logradouroCep = new LogradouroCep();
								Logradouro logradouroSubstituto = new Logradouro();
								logradouroSubstituto.setId(Integer.parseInt(logradouroHelperSubstituir.getIdSubstituirLogra()));
								logradouroCep.setLogradouro(logradouroSubstituto);
								logradouroCep.setCep(cep);
								logradouroCep.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
								logradouroCep.setUltimaAlteracao(new Date());
								
								Integer idLogradouroCep = (Integer) fachada.inserir(logradouroCep);
								logradouroCep.setId(idLogradouroCep);
								
								imovelAtualizacaoCadastral.setIdLogradouroCep(logradouroCep.getId());
							}
							imovelAtualizacaoCadastral.setUltimaAlteracao(new Date());
							fachada.atualizar(imovelAtualizacaoCadastral);							
						}
					}
					
					//2 - Atualiza LogradouroAtlzCad
					Logradouro logradouro = new Logradouro();
					logradouro.setId(Integer.parseInt(logradouroHelperSubstituir.getIdSubstituirLogra()));
					logradouroHelperSubstituir.getLogradouroAtlzCad().setLogradouro(logradouro);
					logradouroHelperSubstituir.getLogradouroAtlzCad().setIndicadorAtualizado(ConstantesSistema.SIM);
					logradouroHelperSubstituir.getLogradouroAtlzCad().setUltimaAlteracao(new Date());
					fachada.atualizar(logradouroHelperSubstituir.getLogradouroAtlzCad());
				}
			}
		}
		
		// montando pagina de sucesso
		montarPaginaSucesso(httpServletRequest,
			"Logradouro(s) Atualizado(s) com sucesso.", 
			"Voltar",
			"/exibirInserirNovosLogradourosAtualizacaoCadastralAction.do");
		
		
		return retorno;
	}
	
	//Retorna o AtualizacaoCadastralLogradouroHelper da colecao
	private AtualizacaoCadastralLogradouroHelper pesquisarLogradouroAtlzCad(ArrayList<AtualizacaoCadastralLogradouroHelper>colecaoLogradouroHelper, String id){
		AtualizacaoCadastralLogradouroHelper logradouroHelperRetorno = null;
		for(AtualizacaoCadastralLogradouroHelper logradouroHelper : colecaoLogradouroHelper){
			if(logradouroHelper.getLogradouroAtlzCad().getId().toString().equals(id)){
				logradouroHelperRetorno = logradouroHelper;
			}
		}
		
		return logradouroHelperRetorno;		
	}
	
	
	/**
	 * Retorna os LogradouroCepAtlzCad (da colecao que esta na sessao) do logradouro informado
	 * @author Anderson Cabral
	 * @since 13/03/2013
	 * **/
	private ArrayList<LogradouroBairroAtlzCad> pesquisarLogradouroBairroAtlzCad(ArrayList<AtualizacaoCadastralLogradouroHelper>colecaoLogradouroHelper, String id){
		ArrayList<LogradouroBairroAtlzCad> logradouroBairroAtlzCad = null;
		
		for(AtualizacaoCadastralLogradouroHelper logradouroHelper : colecaoLogradouroHelper){
			if(logradouroHelper.getLogradouroAtlzCad().getId().toString().equals(id)){
				logradouroBairroAtlzCad = logradouroHelper.getColecaoLogardouroBairroAtlzCad();;
			}
		}		
		return logradouroBairroAtlzCad;		
	}
	
	/**
	 * Retorna os LogradouroCepAtlzCad (da colecao que esta na sessao) do logradouro informado
	 * @author Anderson Cabral
	 * @since 13/03/2013
	 * **/
	private ArrayList<LogradouroCepAtlzCad> pesquisarLogradouroCepAtlzCad(ArrayList<AtualizacaoCadastralLogradouroHelper>colecaoLogradouroHelper, String id){
		ArrayList<LogradouroCepAtlzCad> logradouroCepAtlzCad = null;
		
		for(AtualizacaoCadastralLogradouroHelper logradouroHelper : colecaoLogradouroHelper){
			if(logradouroHelper.getLogradouroAtlzCad().getId().toString().equals(id)){
				logradouroCepAtlzCad = logradouroHelper.getColecaoLogradouroCepAtlzCad();;
			}
		}		
		return logradouroCepAtlzCad;		
	}
	
	/**
	 * Retorna os CepAtlzCad (da colecao que esta na sessao) do logradouro informado
	 * @author Anderson Cabral
	 * @since 13/03/2013
	 * **/
	private ArrayList<CepAtlzCad> pesquisarCepAtlzCad(ArrayList<AtualizacaoCadastralLogradouroHelper>colecaoLogradouroHelper, String id){
		ArrayList<CepAtlzCad> colecaoCepAtlzCad = null;
		
		ArrayList<LogradouroCepAtlzCad> colecaoLogradouroCepAtlzCad = pesquisarLogradouroCepAtlzCad(colecaoLogradouroHelper, id);
		
		if(colecaoLogradouroCepAtlzCad != null){
			colecaoCepAtlzCad = new ArrayList<CepAtlzCad>();
			for(LogradouroCepAtlzCad logradouroCepAtlzCad : colecaoLogradouroCepAtlzCad){
				colecaoCepAtlzCad.add(logradouroCepAtlzCad.getCepAtlzCad());
			}
		}
		
		return colecaoCepAtlzCad;
	}
	
	//Retorna os ImovelAtualizacaoCadastral do logradouro informado
	private ArrayList<ImovelAtualizacaoCadastral> pesquisarImovelAtlzCad(String idLogradouro, Fachada fachada){
		FiltroImovelAtualizacaoCadastral filtroImovelAtualizacaoCadastral = new FiltroImovelAtualizacaoCadastral();
		filtroImovelAtualizacaoCadastral.adicionarParametro(new ParametroSimples(FiltroImovelAtualizacaoCadastral.ID_LOGRADOURO,
				idLogradouro));
		
		ArrayList<ImovelAtualizacaoCadastral> colecaoImovelAtualizacaoCadastral = (ArrayList<ImovelAtualizacaoCadastral>) fachada.pesquisar(filtroImovelAtualizacaoCadastral, ImovelAtualizacaoCadastral.class.getName());
		
		return colecaoImovelAtualizacaoCadastral;
	}
}

package gcom.gui.cadastro.atualizacaocadastral;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.atualizacaocadastral.FiltroLogradouroAtlzCad;
import gcom.cadastro.atualizacaocadastral.HidrometroInstalacaoHistoricoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.ImovelFotoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.LogradouroAtlzCad;
import gcom.cadastro.cliente.ClienteAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteFoneAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.cliente.FiltroFoneTipo;
import gcom.cadastro.cliente.FiltroOrgaoExpedidorRg;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.FiltroEnderecoReferencia;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.FiltroFonteAbastecimento;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroPavimentoCalcada;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.FonteAbastecimento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cobranca.FiltroIndicesAcrescimosImpontualidade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarDadosImovelAmbienteVirtualPopupAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping.findForward("consultarDadosImovelAmbienteVirtualPopup");
		
		Fachada fachada = this.getFachada();
		HttpSession sessao = httpServletRequest.getSession(false);   
		
		ConsultarDadosImovelAmbienteVirtualPopupActionForm form =
			(ConsultarDadosImovelAmbienteVirtualPopupActionForm) actionForm;
		
		/********RECUPERA  DADOS DOS POPUPs**********/
		if ( httpServletRequest.getParameter("idCampoEnviarDados") != null ) {
			String idImovel = (String) httpServletRequest.getParameter("idCampoEnviarDados");
			
			Imovel imovel = fachada.pesquisarImovel(Integer.valueOf(idImovel));
			
			if ( imovel != null ) {
				form.setIdImovel(String.valueOf(imovel.getId()));
				form.setInscricaoImovel(imovel.getInscricaoFormatada());
			} else {
				httpServletRequest.setAttribute("imovelInexistente", true);
				form.setIdImovel("");
				form.setInscricaoImovel("IMOVEL INEXISTENTE");
			}
		}else if ( httpServletRequest.getParameter("objetoConsulta") != null ) {
			
			Imovel imovel = fachada.pesquisarImovel(Integer.valueOf(form.getIdImovel()));
			
			if ( imovel != null ) {
				form.setIdImovel(String.valueOf(imovel.getId()));
				form.setInscricaoImovel(imovel.getInscricaoFormatada());
			} else {				
				httpServletRequest.setAttribute("imovelInexistente", true);
				form.setIdImovel("");
				form.setInscricaoImovel("IMOVEL INEXISTENTE");
			}
		}else{
			form.limpar();
			sessao.removeAttribute("colecaoFoto");
		}
		/********FIM RECUPERA  DADOS DOS POPUPs**********/
		
		
		
		if ( httpServletRequest.getParameter("idImovelAtualizacaoCadastral") != null || form.getIdImovelAtualizacaoCadastral() != null ) {
			
			if(httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("pesquisarImovel") ) {
				form.setIndicadorPesquisarImovel("1");
			} else if ( httpServletRequest.getParameter("acao") != null && httpServletRequest.getParameter("acao").equals("desabilitar") ) {
				form.setIndicadorPesquisarImovel("2");
			}
			
			Integer idImovelAtualizacaoCadastral = null;
			if ( httpServletRequest.getParameter("idImovelAtualizacaoCadastral") != null ) {
				idImovelAtualizacaoCadastral = Integer.parseInt(httpServletRequest.getParameter("idImovelAtualizacaoCadastral"));
				form.setIdImovelAtualizacaoCadastral(String.valueOf(idImovelAtualizacaoCadastral));
			} else {
				idImovelAtualizacaoCadastral = Integer.valueOf(form.getIdImovelAtualizacaoCadastral());
			}
			
			/**
			 * @author Jonathan Marcos
			 * @date 15/04/2014
			 * RM10711
			 */
			ArrayList<CadastroOcorrencia> listaCadastroOcorrencia = fachada.
					obterOcorrenciasImovelAtualizacaoCadastral(idImovelAtualizacaoCadastral);
			if(listaCadastroOcorrencia.size()!=0){
				httpServletRequest.getSession().setAttribute("listaCadastroOcorrencia", listaCadastroOcorrencia);
			}else{
				httpServletRequest.getSession().removeAttribute("listaCadastroOcorrencia");
			}
			
			ImovelAtualizacaoCadastral imovelAtualizacaoCadastral = fachada.pesquisarImovelAtualizacaoCadastral(idImovelAtualizacaoCadastral);
			this.pesquisarFotos(idImovelAtualizacaoCadastral, sessao);
			Collection<ClienteAtualizacaoCadastral> colecaoCliente = fachada.pesquisarClienteAtualizacaoCadastral(idImovelAtualizacaoCadastral);
			
			if(colecaoCliente != null && !colecaoCliente.isEmpty()){
				
				Iterator iterCliente = colecaoCliente.iterator();
				while (iterCliente.hasNext()) {
					ClienteAtualizacaoCadastral clienteAtualizacaoCadastral = (ClienteAtualizacaoCadastral) iterCliente.next();
					
					//Caso seja usuário, insere os dados no form, para ser exibido no relatorio
					if(clienteAtualizacaoCadastral.getIdClienteRelacaoTipo() != null 
							&& clienteAtualizacaoCadastral.getIdClienteRelacaoTipo().intValue() == 2){
						
						if(clienteAtualizacaoCadastral.getIdClienteTipo() != null){
							FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();
							filtroClienteTipo.adicionarParametro( new ParametroSimples(FiltroClienteTipo.ID, clienteAtualizacaoCadastral.getIdClienteTipo()));
							
							Collection<ClienteTipo> colecaoClienteTipo = fachada.pesquisar(filtroClienteTipo, ClienteTipo.class.getName());
							
							if ( colecaoClienteTipo != null && !colecaoClienteTipo.isEmpty() ) {
								ClienteTipo  clienteTipo = (ClienteTipo) Util.retonarObjetoDeColecao(colecaoClienteTipo);
								form.setTipoCliente(clienteTipo.getDescricao());
							}
						}
						
						if(clienteAtualizacaoCadastral.getCpfCnpj() != null && clienteAtualizacaoCadastral.getCpfCnpj().length() > 11){
							form.setCpfcnpj(Util.formatarCnpj(clienteAtualizacaoCadastral.getCpfCnpj()));
						}else{
							form.setCpfcnpj(Util.formatarCpf(clienteAtualizacaoCadastral.getCpfCnpj()));
						}
						
						if(clienteAtualizacaoCadastral.getNomeCliente() != null){
							form.setNomeCliente(clienteAtualizacaoCadastral.getNomeCliente());
						}
						
						if(clienteAtualizacaoCadastral.getRg() != null){
							form.setNumeroRg(clienteAtualizacaoCadastral.getRg());
						}
						
						if(clienteAtualizacaoCadastral.getDsUFSiglaOrgaoExpedidorRg() != null){
							form.setUf(clienteAtualizacaoCadastral.getDsUFSiglaOrgaoExpedidorRg());
						}
						
						if(clienteAtualizacaoCadastral.getOrgaoExpedidorRG() != null && clienteAtualizacaoCadastral.getOrgaoExpedidorRG().getId() != null ){
							
							FiltroOrgaoExpedidorRg filtroOrgaoExpedidorRg = new FiltroOrgaoExpedidorRg();
							filtroOrgaoExpedidorRg.adicionarParametro( new ParametroSimples(FiltroOrgaoExpedidorRg.ID, clienteAtualizacaoCadastral.getOrgaoExpedidorRG().getId()));
							
							Collection<OrgaoExpedidorRg> colecaoOrgao = fachada.pesquisar(filtroOrgaoExpedidorRg, OrgaoExpedidorRg.class.getName());
							
							if ( colecaoOrgao != null && !colecaoOrgao.isEmpty() ) {
								OrgaoExpedidorRg  orgao = (OrgaoExpedidorRg) Util.retonarObjetoDeColecao(colecaoOrgao);
								form.setOrgaoExpedidor(orgao.getDescricaoAbreviada());
							}else{
								form.setOrgaoExpedidor("");
							}
						}	
						
						if(clienteAtualizacaoCadastral.getIdUinidadeFederacao() != null){
							FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();
							filtroUnidadeFederacao.adicionarParametro( new ParametroSimples(FiltroUnidadeFederacao.ID, clienteAtualizacaoCadastral.getIdUinidadeFederacao()));
							
							Collection<UnidadeFederacao> colecaoUnidadeFederacao = fachada.pesquisar(filtroUnidadeFederacao, UnidadeFederacao.class.getName());
							
							if ( colecaoUnidadeFederacao != null && !colecaoUnidadeFederacao.isEmpty() ) {	
								UnidadeFederacao uf = (UnidadeFederacao) Util.retonarObjetoDeColecao(colecaoUnidadeFederacao);
								form.setUf(uf.getSigla());
							}
						}else{
							form.setUf("");
						}
						
						if(clienteAtualizacaoCadastral.getSexoFormatado() != null){
							form.setSexo(clienteAtualizacaoCadastral.getSexoFormatado());
						}else{
							form.setSexo("");
						}
					}
					
					String telefones = "";
					
					Collection<ClienteFoneAtualizacaoCadastral> colecaoClienteFoneAtualizacaoCadastral = (Collection<ClienteFoneAtualizacaoCadastral>) fachada.
							obterDadosClienteFoneAtualizacaoCadastral(clienteAtualizacaoCadastral.getId());
					
					if(colecaoClienteFoneAtualizacaoCadastral != null && !colecaoClienteFoneAtualizacaoCadastral.isEmpty()){
						ClienteFoneAtualizacaoCadastral clienteFoneAtualizacaoCadastral = (ClienteFoneAtualizacaoCadastral)
								Util.retonarObjetoDeColecao(colecaoClienteFoneAtualizacaoCadastral);
						
						clienteAtualizacaoCadastral.setTelefoneFormatadoCliente(clienteFoneAtualizacaoCadastral.getTelefoneFormatado());
						
						//Caso seja usuário, insere os dados no form, para ser exibido no relatorio
						if(clienteAtualizacaoCadastral.getIdClienteRelacaoTipo() != null 
								&& clienteAtualizacaoCadastral.getIdClienteRelacaoTipo().intValue() == 2){
							
							for(ClienteFoneAtualizacaoCadastral foneAtlzCad : colecaoClienteFoneAtualizacaoCadastral){
								FiltroFoneTipo filtroFoneTipo = new FiltroFoneTipo();
								filtroFoneTipo.adicionarParametro( new ParametroSimples(FiltroFoneTipo.ID,
										foneAtlzCad.getIdFoneTipo()));
								
								FoneTipo foneTipo = (FoneTipo) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroFoneTipo, FoneTipo.class.getName()));
			
								if(foneTipo != null){
									telefones = telefones + foneAtlzCad.getTelefoneFormatado() + " " + foneTipo.getDescricao() + ",";
								}
							}
						}
						
						clienteAtualizacaoCadastral.setTelefoneFormatadoCliente(telefones);
					}
					if(!telefones.equals("")){
						form.setTelefones(telefones.substring(0, telefones.length() - 1));
					}else{
						form.setTelefones("");
					}
				}
			}
			
			ArrayList<ImovelSubcategoriaAtualizacaoCadastral> colecaoSubCategoria = (ArrayList<ImovelSubcategoriaAtualizacaoCadastral>) fachada.
					pesquisarSubCategoriaAtualizacaoCadastral(idImovelAtualizacaoCadastral);
			
			sessao.setAttribute("colecaoSubCategoria", colecaoSubCategoria);
			
			Collection<HidrometroInstalacaoHistoricoAtualizacaoCadastral> colecaoHidrometroInstHistAtlzCad = fachada.
					pesquisarHidrometroInstalacaoHistoricoAtualizacaoCadastral(idImovelAtualizacaoCadastral, null);
			
			if(!colecaoHidrometroInstHistAtlzCad.isEmpty()){
				for(HidrometroInstalacaoHistoricoAtualizacaoCadastral hidrometroInstalacao : colecaoHidrometroInstHistAtlzCad){
					//Caso seja o hidrometro de ligacao agua, insere os dados no form, para ser exibido no relatorio
					if(hidrometroInstalacao.getMedicaoTipo().getId().equals(MedicaoTipo.LIGACAO_AGUA)){
						if(hidrometroInstalacao.getHidrometroLocalInstalacao() != null){
							if(hidrometroInstalacao.getHidrometroLocalInstalacao().getId() != null){
								form.setIdLocalInstalacao(hidrometroInstalacao.getHidrometroLocalInstalacao().getId().toString());
							}else{
								form.setIdLocalInstalacao("");
							}
							
							if(hidrometroInstalacao.getHidrometroLocalInstalacao().getDescricao() != null){
								form.setLocalInstalacao(hidrometroInstalacao.getHidrometroLocalInstalacao().getDescricao());
							}else{
								form.setLocalInstalacao("");
							}
						}else{
							form.setIdLocalInstalacao("");
							form.setLocalInstalacao("");
						}
						
						if(hidrometroInstalacao.getHidrometroProtecao() != null){
							if(hidrometroInstalacao.getHidrometroProtecao().getId() != null){
								form.setIdTipoProtecao(hidrometroInstalacao.getHidrometroProtecao().getId().toString());
							}else{
								form.setIdTipoProtecao("");
							}
							
							if(hidrometroInstalacao.getHidrometroProtecao().getDescricao() != null){
								form.setTipoProtecao(hidrometroInstalacao.getHidrometroProtecao().getDescricao());
							}else{
								form.setTipoProtecao("");
							}
						}else{
							form.setIdTipoProtecao("");
							form.setTipoProtecao("");
						}
						
						if(hidrometroInstalacao.getIndicadorCavalete().equals(ConstantesSistema.SIM)){
							form.setCavalete("SIM");
						}else{
							form.setCavalete("NÃO");
						}
						
						if(hidrometroInstalacao.getNumeroInstalacaoHidrometro() != null){
							form.setLeitura(hidrometroInstalacao.getNumeroInstalacaoHidrometro().toString());
						}else{
							form.setLeitura("");
						}
						
						if(hidrometroInstalacao.getNumeroHidrometro() != null){
							form.setNumeroHidrometro(hidrometroInstalacao.getNumeroHidrometro());
						}else{
							form.setNumeroHidrometro("");
						}
					}else{
						form.setIdLocalInstalacao("");
						form.setLocalInstalacao("");
						form.setIdTipoProtecao("");
						form.setTipoProtecao("");
						form.setLeitura("");
						form.setNumeroHidrometro("");
					}
				}
			}else{
				form.setIdLocalInstalacao("");
				form.setLocalInstalacao("");
				form.setIdTipoProtecao("");
				form.setTipoProtecao("");
				form.setLeitura("");
				form.setNumeroHidrometro("");
			}
			
			if(imovelAtualizacaoCadastral != null){

				form.setMatricula(getMatriculaFormatada(imovelAtualizacaoCadastral.getImovel()));
				form.setDataVisita(Util.formatarData(imovelAtualizacaoCadastral.getDataVisita()));
				
				String nomeCadastrador = imovelAtualizacaoCadastral.getLogin();
				
				FiltroUsuario filtroUsuario = new FiltroUsuario();
				filtroUsuario.adicionarParametro( new ParametroSimples(FiltroUsuario.LOGIN,imovelAtualizacaoCadastral.getLogin(), ConectorOr.CONECTOR_OR, 2));
				filtroUsuario.adicionarParametro( new ParametroSimples(FiltroUsuario.CPF,imovelAtualizacaoCadastral.getLogin()));
				
				Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
				if(colecaoUsuario != null && !colecaoUsuario.isEmpty()){
					Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
					nomeCadastrador = usuario.getNomeUsuario();
				}
				
				form.setCadastrador(nomeCadastrador);
				
				
				if(imovelAtualizacaoCadastral.getIdImovelPerfil() != null){
					
					FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
					filtroImovelPerfil.adicionarParametro( new ParametroSimples(FiltroImovelPerfil.ID,
							imovelAtualizacaoCadastral.getIdImovelPerfil()));
					
					ImovelPerfil imovelPerfil = (ImovelPerfil) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName()));
					
					if(imovelPerfil.getId() != null){
						form.setIdImovelPerfil(imovelPerfil.getId().toString());
					}else{
						form.setIdImovelPerfil("");
					}
					
					if(imovelPerfil.getDescricao() != null){
						form.setImovelPerfil(imovelPerfil.getDescricao());
					}else{
						form.setImovelPerfil("");
					}
				}
				
				if(imovelAtualizacaoCadastral.getIdEnderecoReferencia() != null){
					FiltroEnderecoReferencia filtroEnderecoReferencia = new FiltroEnderecoReferencia();
					filtroEnderecoReferencia.adicionarParametro(new ParametroSimples(FiltroEnderecoReferencia.ID,imovelAtualizacaoCadastral.getIdEnderecoReferencia()));
					
					EnderecoReferencia enderecoReferencia = (EnderecoReferencia) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroEnderecoReferencia, EnderecoReferencia.class.getName()));
				
					if(enderecoReferencia.getId() != null){
						form.setMatriculaReferencia(String.valueOf(enderecoReferencia.getId()));
					}else{
						form.setMatriculaReferencia("");
					}
					
					if(enderecoReferencia.getDescricao() != null){
						form.setDescricaoReferencia(enderecoReferencia.getDescricao());
					}else{
						form.setDescricaoReferencia("");
					}
				}
				if(imovelAtualizacaoCadastral.getIdPavimentoCalcada() != null){
					FiltroPavimentoCalcada filtroPavimentoCalcada = new FiltroPavimentoCalcada();
					filtroPavimentoCalcada.adicionarParametro(new ParametroSimples(FiltroPavimentoCalcada.ID, imovelAtualizacaoCadastral.getIdPavimentoCalcada()));
					
					PavimentoCalcada pavimentoCalcada = (PavimentoCalcada) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroPavimentoCalcada, PavimentoCalcada.class.getName()));
					
					if(pavimentoCalcada.getId() != null){
						form.setIdPavimentoCalcada(pavimentoCalcada.getId().toString());
					}else{
						form.setIdPavimentoCalcada("");
					}
					
					if(pavimentoCalcada.getDescricao() != null){
						form.setPavimentoCalcada(pavimentoCalcada.getDescricao());
					}else{
						form.setPavimentoCalcada("");
					}
				}
				
				if(imovelAtualizacaoCadastral.getIdPavimentoRua() != null){
					FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua();
					filtroPavimentoRua.adicionarParametro(new ParametroSimples(FiltroPavimentoRua.ID, imovelAtualizacaoCadastral.getIdPavimentoRua()));
					
					PavimentoRua pavimentoRua = (PavimentoRua) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroPavimentoRua, PavimentoRua.class.getName()));
					
					if(pavimentoRua.getId() != null){
						form.setIdPavimentoRua(pavimentoRua.getId().toString());
					}else{
						form.setIdPavimentoRua("");
					}
					
					if(pavimentoRua.getDescricao() != null){
						form.setPavimentoRua(pavimentoRua.getDescricao());
					}else{
						form.setPavimentoRua("");
					}
				}
				
				if(imovelAtualizacaoCadastral.getIdFonteAbastecimento() != null){
					FiltroFonteAbastecimento filtroFonteAbastecimento = new FiltroFonteAbastecimento();
					filtroFonteAbastecimento.adicionarParametro(new ParametroSimples(FiltroFonteAbastecimento.ID, imovelAtualizacaoCadastral.getIdFonteAbastecimento()));
					
					FonteAbastecimento fonteAbastecimento = (FonteAbastecimento) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroFonteAbastecimento, FonteAbastecimento.class.getName()));
					
					if(fonteAbastecimento.getId() != null){
						form.setIdFonteAbastecimento(fonteAbastecimento.getId().toString());
					}else{
						form.setIdFonteAbastecimento("");
					}
					
					if(fonteAbastecimento.getDescricao() != null){
						form.setFonteAbastecimento(fonteAbastecimento.getDescricao());
					}else{
						form.setFonteAbastecimento("");
					}
				}
				
				//Localidade
				if(imovelAtualizacaoCadastral.getIdLocalidade() != null){
					FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
					filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, imovelAtualizacaoCadastral.getIdLocalidade()));
					
					Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroLocalidade, Localidade.class.getName()));
					
					if(localidade.getId() != null){
						form.setIdLocalidade(localidade.getId().toString());
					}else{
						form.setIdLocalidade("");
					}
					
					if(localidade.getDescricao() != null){
						form.setLocalidade(localidade.getDescricao());
					}else{
						form.setLocalidade("");
					}
				}
				
				if(imovelAtualizacaoCadastral.getDescricaoObservacao() != null){
					form.setObservacao(imovelAtualizacaoCadastral.getDescricaoObservacao());
				}else{
					form.setObservacao("");
				}
				
				if(imovelAtualizacaoCadastral.getIndicadorAlertaTarifaSocial().equals(ConstantesSistema.SIM)){
					form.setAlertaTarifaSocial("SIM");
				}else{
					form.setAlertaTarifaSocial("NÃO");
				}
				
				form.setQuadra(String.valueOf(imovelAtualizacaoCadastral.getNumeroQuadra()));
				form.setLote(String.valueOf(imovelAtualizacaoCadastral.getLote()));
				form.setSubLote(String.valueOf(imovelAtualizacaoCadastral.getSubLote()));
				
				if(imovelAtualizacaoCadastral.getNumeroMorador() != null){
					form.setNumeroMoradores(""+imovelAtualizacaoCadastral.getNumeroMorador());
				}else{
					form.setNumeroMoradores("");
				}
				
				form.setSetorComercial(""+imovelAtualizacaoCadastral.getCodigoSetorComercial());
				
				if(!form.getSetorComercial().equals("")){
					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.LOCALIDADE_ID, form.getIdLocalidade()));
					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getSetorComercial()));
					
					SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName()));
					
					if(setorComercial != null){
						form.setDescricaoSetorComercial(setorComercial.getDescricao());
					}else{
						form.setDescricaoSetorComercial("");
					}
				}
				
				//Endereco
				Logradouro logradouro = fachada.pesquisarLogradouroImovelAtualizacaoCadastral(imovelAtualizacaoCadastral.getIdLogradouro());
				if(logradouro != null){
					if(logradouro.getLogradouroTipo() != null && logradouro.getLogradouroTipo().getDescricao() != null){
						form.setTipoLogradouro(logradouro.getLogradouroTipo().getDescricao());
					}else{
						form.setTipoLogradouro("");
					}
	
					if(logradouro.getLogradouroTitulo() != null && logradouro.getLogradouroTitulo().getDescricao() != null){
						form.setTituloLogradouro(logradouro.getLogradouroTitulo().getDescricao());
					}else{
						form.setTituloLogradouro("");
					}
					
					if(logradouro.getNome() != null){
						form.setDescricaoLogradouro(logradouro.getNome());
					}else{
						form.setDescricaoLogradouro("");
					}
				}else{
					
					FiltroLogradouroAtlzCad filtroLogradouroAtlzCad = new FiltroLogradouroAtlzCad();
					filtroLogradouroAtlzCad.adicionarParametro( new ParametroSimples(FiltroLogradouroAtlzCad.CODIGO, imovelAtualizacaoCadastral.getIdLogradouro()));
					filtroLogradouroAtlzCad.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroAtlzCad.LOGRADOUROTIPO);
					filtroLogradouroAtlzCad.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroAtlzCad.LOGRADOUROTITULO);
					
					Collection<LogradouroAtlzCad> colecaoLogradouroAtlzCad = fachada.pesquisar(filtroLogradouroAtlzCad, LogradouroAtlzCad.class.getName());
					
					if ( colecaoLogradouroAtlzCad != null && !colecaoLogradouroAtlzCad.isEmpty() ) {
						LogradouroAtlzCad logradouroAtlzCad = (LogradouroAtlzCad) Util.retonarObjetoDeColecao(colecaoLogradouroAtlzCad);
						
						if ( logradouroAtlzCad.getLogradouroTipo() != null ) {
							form.setTipoLogradouro(logradouroAtlzCad.getLogradouroTipo().getDescricao());
						}
						
						if ( logradouroAtlzCad.getLogradouroTitulo() != null ) {
							form.setTituloLogradouro(logradouroAtlzCad.getLogradouroTitulo().getDescricao());
						}
						
						form.setDescricaoLogradouro(logradouroAtlzCad.getNome());
						
					} else {

						form.setTipoLogradouro("");
						form.setTituloLogradouro("");
						form.setDescricaoLogradouro("");	
					}
				}
				
				if(imovelAtualizacaoCadastral.getNumeroImovel() != null){
					form.setNumeroImovel(imovelAtualizacaoCadastral.getNumeroImovel());
				}else{
					form.setNumeroImovel("");
				}
				
				if(imovelAtualizacaoCadastral.getComplementoEndereco() != null){
					form.setComplementoLogradouro(imovelAtualizacaoCadastral.getComplementoEndereco());
				}else{
					form.setComplementoLogradouro("");
				}
				
				if(imovelAtualizacaoCadastral.getIdBairro() != null){
					
					FiltroBairro filtroBairro = new FiltroBairro();
					filtroBairro.adicionarParametro( new ParametroSimples(FiltroBairro.ID, imovelAtualizacaoCadastral.getIdBairro()));
					Bairro bairro = (Bairro) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroBairro, Bairro.class.getName()));
					
					form.setBairro(bairro.getNome());
				}else{
					form.setBairro("");
				}
				
				if(imovelAtualizacaoCadastral.getCodigoCep() != null && imovelAtualizacaoCadastral.getCodigoCep() > 0){
					form.setCep(Util.formatarCEP(String.valueOf(imovelAtualizacaoCadastral.getCodigoCep())));
				}else{
					form.setCep("");
				}
				
				if(imovelAtualizacaoCadastral.getIdMunicipio() != null){
					
					FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
					filtroMunicipio.adicionarParametro( new ParametroSimples(FiltroMunicipio.ID, imovelAtualizacaoCadastral.getIdMunicipio()));
					filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade(FiltroMunicipio.UNIDADE_FEDERACAO);
					Municipio municipio = (Municipio) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroMunicipio, Municipio.class.getName()));
					
					form.setMunicipio(municipio.getNome());
				}else{
					form.setMunicipio("");
					form.setUf("");
				}
				
				//Características
				if(imovelAtualizacaoCadastral.getAreaConstruida() != null){
					form.setAreaConstruida(String.valueOf(imovelAtualizacaoCadastral.getAreaConstruida()));
				}else{
					form.setAreaConstruida("");
				}
				
				if(imovelAtualizacaoCadastral.getNumeroMedidirEnergia() != null){
					form.setNumeroMedidorEnergia(String.valueOf(imovelAtualizacaoCadastral.getNumeroMedidirEnergia()));
				}else{
					form.setNumeroMedidorEnergia("");
				}
				
				if(imovelAtualizacaoCadastral.getNumeroContratoEnergia() != null){
					form.setContratoEnergia(String.valueOf(imovelAtualizacaoCadastral.getNumeroContratoEnergia()));
				}else{
					form.setContratoEnergia("");
				}
				
				//Ligação
				if(imovelAtualizacaoCadastral.getIdLigacaoAguaSituacao() != null){
					
					FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
					filtroLigacaoAguaSituacao.adicionarParametro( new ParametroSimples(FiltroLigacaoAguaSituacao.ID, imovelAtualizacaoCadastral.getIdLigacaoAguaSituacao()));
					LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName()));
					
					form.setIdSituacaoLigacaoAgua(ligacaoAguaSituacao.getId().toString());
					form.setSituacaoLigacaoAgua(ligacaoAguaSituacao.getDescricao());
				}else{
					form.setSituacaoLigacaoAgua("");
				}
				
				if(imovelAtualizacaoCadastral.getIdLigacaoEsgotoSituacao() != null){
					
					FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
					filtroLigacaoEsgotoSituacao.adicionarParametro( new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, imovelAtualizacaoCadastral.getIdLigacaoEsgotoSituacao()));
					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName()));
					
					form.setIdSituacaoLigacaoEsgoto(ligacaoEsgotoSituacao.getId().toString());
					form.setSituacaoLigacaoEsgoto(ligacaoEsgotoSituacao.getDescricao());
				}else{
					form.setSituacaoLigacaoEsgoto("");
				}
				
				if ( colecaoHidrometroInstHistAtlzCad != null ) {
					
					httpServletRequest.setAttribute("colecaoHidrometro", colecaoHidrometroInstHistAtlzCad);
				}
				
				if(imovelAtualizacaoCadastral.getIdPocoTipo() != null){
					form.setIdentificacaoPoco(String.valueOf(imovelAtualizacaoCadastral.getIdPocoTipo()));
				}else{
					form.setIdentificacaoPoco("");
				}
			}
			
			if(colecaoSubCategoria != null){
				httpServletRequest.setAttribute("colecaoSubCategoria", colecaoSubCategoria);
			}
			
			if(colecaoCliente != null){
				httpServletRequest.setAttribute("colecaoCliente", colecaoCliente);
			}
		}
		
		return retorno;
	}
	
	public String getMatriculaFormatada(Integer matricula) {

		String matriculaImovelFormatada = "";
		
		Integer matriculaImovel = matricula;
		
		matriculaImovelFormatada = "" + matriculaImovel;
		
		int quantidadeCaracteresImovel = matriculaImovel.toString()
					.length();
			matriculaImovelFormatada = matriculaImovelFormatada.substring(0,
					quantidadeCaracteresImovel - 1)
					+ "."
					+ matriculaImovelFormatada.substring(
							quantidadeCaracteresImovel - 1,
							quantidadeCaracteresImovel);

		return matriculaImovelFormatada;
	}
	
	
	public void pesquisarFotos(Integer idImovelAtualizacaoCadastral, HttpSession sessao) {
		
		ArrayList<ImovelFotoAtualizacaoCadastral> fotos = (ArrayList<ImovelFotoAtualizacaoCadastral>) 
				Fachada.getInstancia().pesquisarImovelFotoAtualizacaoCadastral(idImovelAtualizacaoCadastral, true);
		if (!Util.isVazioOrNulo(fotos)){
			sessao.setAttribute("colecaoFoto", fotos);
		} else {
			sessao.removeAttribute("colecaoFoto");
		}
	}
}

package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.SmsTipo;
import gcom.atendimentopublico.registroatendimento.FiltroRAReiteracao;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.RAReiteracao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoAnexo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo;
import gcom.atendimentopublico.registroatendimento.bean.ProcessarRequisicaoIntegracaoProgisHelper;
import gcom.cadastro.endereco.FiltroLogradouroBairro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ProcessarRequisicaoIntegracaoProgisAction extends GcomAction {	
		
	/*Reposta de retorno*/
	private final char RESPOSTA_ERRO = '#';
	private final char RESPOSTA_OK = '*';
	private char resposta = RESPOSTA_ERRO;
	private String mensagemErro = "";
	
	private Fachada fachada = Fachada.getInstancia();
	private ProcessarRequisicaoIntegracaoProgisHelper helper;

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		
		helper = new ProcessarRequisicaoIntegracaoProgisHelper();
		OutputStream out = null;
		InputStream in = null;
		
		boolean parametrosIncorretos = false;
		
		String numeroProtocolo = "";

        try {
        	System.out.println("INICIO: Recebendo solicitacão PROGIS");
        	
        	out = response.getOutputStream();        	
			in = request.getInputStream();
			
			DataInputStream din = new DataInputStream(in);
			
			mensagemErro = "";
			
	    	parametrosIncorretos = validarParametrosInformados(din);
			
            System.out.println("Parametros recebidos com sucesso.");
            
	        if(!existeRegistroAtendimento(helper.getIdRegistroAtendimento()) && !parametrosIncorretos){
	        	parametrosIncorretos = true;
	        }else if(!existeTipoEspecificacao(helper.getIdTipoEspecificacao()) && !parametrosIncorretos){
	        	parametrosIncorretos = true;
	        }else if(!existeTipoSolicitacao(helper.getIdTipoSolicitacao()) && !parametrosIncorretos){
	        	parametrosIncorretos = true;
	        }else if(!existeEndereco(helper.getIdEndereco()) && !parametrosIncorretos){
	        	parametrosIncorretos = true;
	        }else if(!existeIdAtendenteAbertura(helper.getLoginAtendenteAbertura()) && !parametrosIncorretos){
	        	parametrosIncorretos = true;
	        }else if(!existeMatriculaImovel(helper.getIdImovel()) && !parametrosIncorretos){
	        	parametrosIncorretos = true;
	        }else if(!existeNumeroProtocolo(helper.getNumeroProtocolo()) && !parametrosIncorretos){
	        	parametrosIncorretos = true;
	        }else if (!parametrosIncorretos){            
	        	parametrosIncorretos = paramentrosInformadosIncorretamente(helper);
	        }

	        /*Caso os paramentros não estejam corretos
	        * Enviar SMS De Erro
	        */
	        if(parametrosIncorretos){
	        	throw new Exception("Parametros Informado Incorretamente");
	        }else{
	        	/* UC[1400][SB0001]* - Caso Seja informado um RA */
	        	if(helper.getIdRegistroAtendimento()!=null && !helper.getIdRegistroAtendimento().equals("") ){
	       
	        		if(helper.getFoto()!=null){
	        			anexarFotoAoRegistroAtendimento();
	        		}
	        		
	        		fachada.enviarSmsRegistroAtendimento(helper.getCelularSolicitante(), helper.getIdRegistroAtendimento(), SmsTipo.ENVIO_RA_EXISTENTE );
	        		
	        		numeroProtocolo = fachada.inserirRegistroAtendimentoReiteracaoProgis(helper);
	        		
	        	}else{
	        		if(helper.getTipoRequisicao().equals("1")){
		        		// [UC1400][SB0001] - Caso NAO Seja informado um RA
		        		numeroProtocolo = fachada.inserirRegistroAtendimentoProgis(helper);
	        		}else{
	        			// [UC1400][SB0003] - Reiterar Registro de Atendimento
	        			numeroProtocolo = fachada.reiterarRegistroAtendimentoProgis(helper.getNumeroProtocolo(), helper.getLoginAtendenteAbertura());
	        		}
	        	}
	        }
	        
	        resposta = RESPOSTA_OK;
	        System.out.println("FIM: Recebendo solicitacão PROGIS");

        }catch (Exception e) {
        	e.printStackTrace();
			fachada.enviarSmsRegistroAtendimento(helper.getCelularSolicitante(), helper.getIdRegistroAtendimento(), SmsTipo.ENVIO_ERRO);
			resposta = RESPOSTA_ERRO;
		}catch (Error e) {
			e.printStackTrace();
			fachada.enviarSmsRegistroAtendimento(helper.getCelularSolicitante(), helper.getIdRegistroAtendimento(), SmsTipo.ENVIO_ERRO);
			resposta = RESPOSTA_ERRO;
		}finally{
			
			try {
				if(resposta == RESPOSTA_OK){
					response.setContentLength( 1 + numeroProtocolo.getBytes().length );
					out.write(resposta);
					out.write(numeroProtocolo.getBytes());
					out.flush();
				}else{
					response.setContentLength( 1 + mensagemErro.getBytes().length );
					out.write(resposta);
					out.write(mensagemErro.getBytes());
					out.flush();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
		return null;
	}
	
	
	/**[UC1400][FE0001] - Validar Registro de atendimento
	 * @param idRegistroAtendimento
	 * @author Carlos Chaves
	 * @since 11/12/2012
	 */
	private boolean existeRegistroAtendimento(Integer idRegistroAtendimento){
		
		if(idRegistroAtendimento!=null){
			
			FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
			filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID, idRegistroAtendimento));
			
			@SuppressWarnings("rawtypes")
			Collection colecaoRegistroAtendimento = fachada
			.pesquisar(filtroRegistroAtendimento,
					RegistroAtendimento.class.getName());
			
			if(colecaoRegistroAtendimento==null || colecaoRegistroAtendimento.size() < 1){
				mensagemErro = "Registro de Atendimento inexistente "; 
				return false;
			}
		}
		return true;		
	}
	
	/**[UC1400][FE0002] - Validar tipo solicitacao
	 * @param idSolicitacaoTipo
	 * @author Carlos Chaves
	 * @since 11/12/2012
	 */
	private boolean existeTipoSolicitacao(Integer idSolicitacaoTipo){
		
		if(idSolicitacaoTipo!=null){
			
			FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();
			filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipo.ID, idSolicitacaoTipo));
			
			
			@SuppressWarnings("rawtypes")
			Collection colecaoSolicitacaoTipo = fachada
			.pesquisar(filtroSolicitacaoTipo,
					SolicitacaoTipo.class.getName());
			
			if(colecaoSolicitacaoTipo==null || colecaoSolicitacaoTipo.size() < 1){
				mensagemErro = "Tipo de Solicitacao Inexistente ";
				return false;
			}
		}
		
		return true;
	}
		
	/**[UC1400][FE0003] - Validar Registro tipo especificacao
	 * @param idTipoEspecificacao
	 * @author Carlos Chaves
	 * @since 11/12/2012
	 */
	private boolean existeTipoEspecificacao(Integer idSolicitacaoTipoEspecificacao){
		
		if(idSolicitacaoTipoEspecificacao!=null){
			
			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
			filtroSolicitacaoTipoEspecificacao.
							adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID, idSolicitacaoTipoEspecificacao));
			filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipo");
			
			@SuppressWarnings("rawtypes")
			Collection colecaoSolicitacaoTipoEspecificacao = fachada
			.pesquisar(filtroSolicitacaoTipoEspecificacao,
					SolicitacaoTipoEspecificacao.class.getName());
			
			if(colecaoSolicitacaoTipoEspecificacao==null || colecaoSolicitacaoTipoEspecificacao.size() < 1){
				mensagemErro = "Especificacao de Tipo de Solicitacao Invalido ";
				return false;
			}else{
				
				@SuppressWarnings("unchecked")
				SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = 
						(SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);
				
				if(helper.getIdTipoSolicitacao()==null){
					helper.setIdTipoSolicitacao(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getId());
				}
				
			}
		}
		
		return true;
	}
	
	
	/**[UC1400][FE0004] - Validar parametros informados
	 * @param ProcessarRequisicaoIntegracaoProgisActionHelper helper
	 * @author Carlos Chaves
	 * @since 11/12/2012
	 */
	private boolean paramentrosInformadosIncorretamente(ProcessarRequisicaoIntegracaoProgisHelper helper){
		
		if(helper.getIdRegistroAtendimento() == null || helper.getIdRegistroAtendimento().toString().trim().equals("") ){
			
			if(helper.getTipoRequisicao().equals("1")){
				if(	(helper.getCoordenadaX() == null || helper.getCoordenadaX().equals(""))
					|| (helper.getCoordenadaY() == null || helper.getCoordenadaY().equals(""))
					|| (helper.getIdEndereco() == null || helper.getIdEndereco().equals(""))
					|| (helper.getIdTipoEspecificacao() == null || helper.getIdTipoEspecificacao().equals(""))
					|| (helper.getIdTipoSolicitacao() == null || helper.getIdTipoSolicitacao().equals(""))
					|| (helper.getLoginAtendenteAbertura() == null || helper.getLoginAtendenteAbertura().equals(""))
					|| (helper.getFoto() == null || helper.getFoto().equals(""))
					|| (helper.getExtensao() == null || helper.getExtensao().equals(""))
					|| (helper.getFileSizeFoto() == 0 )
					){
					
					mensagemErro = "Parametros Obrigatorios nao informados: ";
					
					if(helper.getCoordenadaX() == null || helper.getCoordenadaX().equals("")){
						mensagemErro += "Coordenada X | ";
					}
					
					if(helper.getCoordenadaY() == null || helper.getCoordenadaY().equals("")){
						mensagemErro += "Coordenada Y | ";
					}
					
					if(helper.getIdEndereco() == null || helper.getIdEndereco().equals("")){
						mensagemErro += "Logradouro Bairro | ";
					}
					
					if(helper.getIdTipoEspecificacao() == null || helper.getIdTipoEspecificacao().equals("")){
						mensagemErro += "Tipo de Especificacao | ";
					}
					
					if(helper.getIdTipoSolicitacao() == null || helper.getIdTipoSolicitacao().equals("")){
						mensagemErro += "Tipo de Solicitacao | ";
					}
					
					if(helper.getLoginAtendenteAbertura() == null || helper.getLoginAtendenteAbertura().equals("")){
						mensagemErro += "Codigo do Atendente | ";
					}
					
					if(helper.getFoto() == null || helper.getFoto().equals("")){
						mensagemErro += "Foto da RA | ";
					}
					
					if(helper.getExtensao() == null || helper.getExtensao().equals("")){
						mensagemErro += "Extensao da Foto | ";
					}
					
					if(helper.getFileSizeFoto() == 0){
						mensagemErro += "Tamanho da Foto | ";
					}
								 
					return true;
				}
				
				if(helper.getIdTipoSolicitacao() != null && !helper.getIdTipoSolicitacao().equals("")){
					FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();
					filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipo.ID, helper.getIdTipoSolicitacao()));
					filtroSolicitacaoTipo.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipo.SOLICITACAO_TIPO_GRUPO);
					
					@SuppressWarnings("unchecked")
					Collection<SolicitacaoTipo> colecaoSolicitacaoTipo = fachada.pesquisar(filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());
					if(!Util.isVazioOrNulo(colecaoSolicitacaoTipo)){
						
						SolicitacaoTipo solicitacaoTipo = (SolicitacaoTipo) Util.retonarObjetoDeColecao(colecaoSolicitacaoTipo);
						
						if(solicitacaoTipo != null && solicitacaoTipo.getSolicitacaoTipoGrupo() != null 
							&& solicitacaoTipo.getSolicitacaoTipoGrupo().getId().equals(SolicitacaoTipoGrupo.ID_OPERACIONAL_ESGOTO)){
							
							if(helper.getIdImovel() == null || helper.getIdImovel().toString().equals("")){
								mensagemErro = "Matricula do Imovel mais proximo nao informada para RA de Esgoto | ";
								return true;
							}
						}else{
							if(helper.getIdImovel() != null && !helper.getIdImovel().toString().equals("")){
								helper.setIdImovel(null);
							}
						}
					}
				}
			}else{
				if(helper.getNumeroProtocolo() == null || helper.getNumeroProtocolo().equals("") 
						|| helper.getLoginAtendenteAbertura() == null || helper.getLoginAtendenteAbertura().equals("")){
					
					mensagemErro = "Parametros Obrigatorios nao informados: ";
					
					if(helper.getNumeroProtocolo() == null || helper.getNumeroProtocolo().equals("")){
						mensagemErro += "Número do Protocolo | ";
					}
					
					if(helper.getLoginAtendenteAbertura() == null || helper.getLoginAtendenteAbertura().equals("")){
						mensagemErro += "Codigo do Atendente | ";
					}
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	/**[UC1400][FE0003] - Validar endereco
	 * @param idTipoEspecificacao
	 * @author Carlos Chaves
	 * @since 11/12/2012
	 */
	private boolean existeEndereco(Integer idLogradouro){
		if(idLogradouro!=null){
			
			FiltroLogradouroBairro filtroLogradouroBairro= new FiltroLogradouroBairro();
			filtroLogradouroBairro.
							adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID, idLogradouro));
			
			@SuppressWarnings("rawtypes")
			Collection colecaoLogradouroBairro = fachada
			.pesquisar(filtroLogradouroBairro,
					LogradouroBairro.class.getName());
			
			if(colecaoLogradouroBairro==null || colecaoLogradouroBairro.size() < 1){
				mensagemErro = "Logradouro Bairro Inexistente ";
				return false;
			}
		}
		
		return true;
	}
	
	/**[UC1400] - Validar usuario
	 * @param idAtendenteAbertura
	 * @author Carlos Chaves
	 * @since 11/12/2012
	 */
	private boolean existeIdAtendenteAbertura(String loginAtendenteAbertura){
		if(loginAtendenteAbertura!=null){
			
			FiltroUsuario filtroUsuario= new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, loginAtendenteAbertura.toString()));
			
			@SuppressWarnings("rawtypes")
			Collection colecaoUsuario = fachada
			.pesquisar(filtroUsuario,
					Usuario.class.getName());
			
			if(colecaoUsuario==null || colecaoUsuario.size() < 1){
				mensagemErro = "Codigo do Atendente da abertura de RA inexistente ";
				return false;
			}
		}
		
		return true;
	}
	
	/** 
	 * [UC1400] - Validar Matricula do Imovel
	 * @param idImovel
	 * @author Davi Menezes
	 * @since 02/10/2013
	 */
	private boolean existeMatriculaImovel(Integer idImovel){
		if(idImovel != null){
			Imovel imovel = fachada.pesquisarImovelDigitado(idImovel);
			
			if(imovel == null){
				mensagemErro = "Imovel Inexistente ";
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * [UC1400] - Validar Numero de Protocolo Informado
	 * @param numeroProtocolo
	 * @author Davi Menezes
	 * @since 18/10/2013
	 */
	private boolean existeNumeroProtocolo(String numeroProtocolo){
		if(numeroProtocolo != null){
			Date dataAtual = new Date();
			
			FiltroRegistroAtendimentoSolicitante filtroRASolicitante = new FiltroRegistroAtendimentoSolicitante();
			filtroRASolicitante.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoSolicitante.NUMERO_PROTOCOLO, numeroProtocolo));
			filtroRASolicitante.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimentoSolicitante.REGISTRO_ATENDIMENTO);
			
			Collection<?> colecaoRASolicitante = fachada.pesquisar(filtroRASolicitante, RegistroAtendimentoSolicitante.class.getName());
			if(!Util.isVazioOrNulo(colecaoRASolicitante)){
				RegistroAtendimentoSolicitante raSolicitante = (RegistroAtendimentoSolicitante) Util.retonarObjetoDeColecao(colecaoRASolicitante);
				
				if(raSolicitante.getRegistroAtendimento() != null){
					if(String.valueOf(raSolicitante.getRegistroAtendimento().getCodigoSituacao()).equals(
							String.valueOf(RegistroAtendimento.SITUACAO_ENCERRADO))){
						mensagemErro = "Registro de Atendimento associado ao protocolo ja foi atendido ";
						return false;
					}else{
						/*
						 * Caso o usuário esteja tentando reiterar o registro de atendimento
						 * e o mesmo ainda esteja no prazo de atendimento, o sistema deverá
						 * exibir a mensagem: "Prazo previsto para o atendimento ainda não
						 * expirou. não possível reiterá-lo."
						 */
						if (Util.compararDiaMesAno(raSolicitante.getRegistroAtendimento().getDataPrevistaAtual(), dataAtual) >= 0) {
							mensagemErro = "Prazo Previsto para o atendimento ainda nao expirou. Nao e possivel reitera-lo";
							return false;
						}
					}
				}
			}else{
				FiltroRAReiteracao filtroRaReiteracao = new FiltroRAReiteracao();
				filtroRaReiteracao.adicionarParametro(new ParametroSimples(FiltroRAReiteracao.NUMERO_PROTOCOLO, numeroProtocolo));
				filtroRaReiteracao.adicionarCaminhoParaCarregamentoEntidade(FiltroRAReiteracao.REGISTRO_ATENDIMENTO);
				
				Collection<?> colecaoRaReiteracao = fachada.pesquisar(filtroRaReiteracao, RAReiteracao.class.getName());
				if(!Util.isVazioOrNulo(colecaoRaReiteracao)){
					RAReiteracao raReiteracao = (RAReiteracao) Util.retonarObjetoDeColecao(colecaoRaReiteracao);
					
					if(raReiteracao.getRegistroAtendimento() != null){
						if(String.valueOf(raReiteracao.getRegistroAtendimento().getCodigoSituacao()).equals(
								String.valueOf(RegistroAtendimento.SITUACAO_ENCERRADO))){
							mensagemErro = "Registro de Atendimento associado ao protocolo ja foi atendido ";
							return false;
						}else{
							/*
							 * Caso o usuário esteja tentando reiterar o registro de atendimento
							 * e o mesmo ainda esteja no prazo de atendimento, o sistema deverá
							 * exibir a mensagem: "Prazo previsto para o atendimento ainda não
							 * expirou. não possível reiterá-lo."
							 */
							if (Util.compararDiaMesAno(raReiteracao.getRegistroAtendimento().getDataPrevistaAtual(), dataAtual) >= 0) {
								mensagemErro = "Prazo Previsto para o atendimento ainda nao expirou. Nao e possivel reitera-lo";
								return false;
							}
						}
					}
				}else{
					mensagemErro = "Registro de Atendimento Inexistente para o protocolo informado ";
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	/**[UC1400]
	 * [IT007] - Anexar Foto ao Registro Atendimento
	 * @param 
	 * @author Carlos Chaves
	 * @since 11/12/2012
	 */
	private void anexarFotoAoRegistroAtendimento(){
		
		RegistroAtendimentoAnexo registroAtendimentoAnexo = new RegistroAtendimentoAnexo();
		RegistroAtendimento registroAtendimento;
		registroAtendimento = fachada.obterRegistroAtendimento(helper.getIdRegistroAtendimento());
		registroAtendimentoAnexo.setRegistroAtendimento(registroAtendimento); 
		registroAtendimentoAnexo.setImagemDocumento(helper.getFoto());
		registroAtendimentoAnexo.setDescricaoDocumento("Foto recebida do PROGIS");
		registroAtendimentoAnexo.setNomeExtensaoDocumento(helper.getExtensao());
		registroAtendimentoAnexo.setUltimaAlteracao(new Date());
		
		fachada.inserir(registroAtendimentoAnexo);
		
	}
	

	private boolean isParamentroNulo(String paramentro){
		if(paramentro.equalsIgnoreCase("NULL")){
			return true;
		}
		return false;
	}
	
	private boolean validarParametrosInformados(DataInputStream din) throws IOException{
		
		boolean parametrosIncorretos = false;
		
		//Tipo de Requisicao
		String tipoRequisicao = din.readUTF();
		if(!isParamentroNulo(tipoRequisicao)){
			if(tipoRequisicao.equals("1") || tipoRequisicao.equals("2")){
				helper.setTipoRequisicao(tipoRequisicao);
			}else{
				parametrosIncorretos = true;
				mensagemErro = "Tipo de Requisicao Invalido | ";
			}
		}else{
			parametrosIncorretos = true;
			mensagemErro = "Tipo de Requisicao não informado | ";
		}
		
		if(!isParamentroNulo(tipoRequisicao) && tipoRequisicao.equals("1")){
			//Celular
			String strTelefone = din.readUTF();
			if(!isParamentroNulo(strTelefone)){
				if(Util.isLong(strTelefone)){
					helper.setCelularSolicitante(new Long(strTelefone));
	            }else{
	            	parametrosIncorretos = true;
	            	mensagemErro += "Numero Celular Invalido | ";
	            }
			}
	
	        //idRa
			String strIdRa = din.readUTF();
			if(!isParamentroNulo(strIdRa)){
				if(Util.isInteger(strIdRa)){
					helper.setIdRegistroAtendimento(new Integer(strIdRa));
	            }else{
	            	parametrosIncorretos = true;
	            	mensagemErro += "Numero da RA Invalido | ";
	            }
			}
			
	        //idTipoSolicitacao
			String strIdTipoSolicitacao = din.readUTF();
			if(!isParamentroNulo(strIdTipoSolicitacao)){
				if(Util.isInteger(strIdTipoSolicitacao)){
					helper.setIdTipoSolicitacao(new Integer(strIdTipoSolicitacao));
	            }else{
	            	parametrosIncorretos = true;
	            	mensagemErro += "Tipo de Solicitacao Invalido | ";
	            }
			}
			
	        //idTipoSolicitacaoEspecificacao
			String strIdTipoSolicitacaoEspecificacao = din.readUTF();
			if(!isParamentroNulo(strIdTipoSolicitacaoEspecificacao)){
				if(Util.isInteger(strIdTipoSolicitacaoEspecificacao)){
					helper.setIdTipoEspecificacao(new Integer(strIdTipoSolicitacaoEspecificacao));
	            }else{
	            	parametrosIncorretos = true;
	            	mensagemErro += "Especificacao do Tipo de Solicitacao Invalido | ";
	            }
			}
			
	        //Endereco
			String strIdEndereco = din.readUTF();
			if(!isParamentroNulo(strIdEndereco)){
				if(Util.isInteger(strIdEndereco)){
					helper.setIdEndereco(new Integer(strIdEndereco));
	            }else{
	            	parametrosIncorretos = true;
	            	mensagemErro += "Endereco Invalido | ";
	            }
			}
			
	        //Nome
			String strNome = din.readUTF(); 
	        if(!isParamentroNulo(strNome)){
	        	helper.setNomeSolicitante(strNome);
	        }
	        
	        //PontoReferencia
	        String strPontoReferencia = din.readUTF(); 
	        if(!isParamentroNulo(strPontoReferencia)){
	        	helper.setPontoReferencia(strPontoReferencia);
	        }
	       
	        //CoordenadaX
	        String strCoordenadaX = din.readUTF(); 
	        if(!isParamentroNulo(strCoordenadaX)){
	            if(Util.isBigDecimal(strCoordenadaX)){
	            	helper.setCoordenadaX(new BigDecimal(strCoordenadaX));
	            }else{
	            	parametrosIncorretos = true;
	            	mensagemErro += "Coordenada X Invalida | ";
	            }
	        }
	        
	        //CoordenadaY
	        String strCoordenadaY = din.readUTF(); 
	        if(!isParamentroNulo(strCoordenadaY)){
	            if(Util.isBigDecimal(strCoordenadaY)){
	            	helper.setCoordenadaY(new BigDecimal(strCoordenadaY));
	            }else{
	            	parametrosIncorretos = true;
	            	mensagemErro += "Coordenada Y Invalida | ";
	            }
	        }
	        
	        //Email
	        String strEmail = din.readUTF(); 
	        if(!isParamentroNulo(strEmail)){
	        	helper.setEmail(strEmail);
	        }
	        
	        //Extensao
	        String strExtensao = din.readUTF(); 
	        if(!isParamentroNulo(strExtensao) ){
	        	if(strExtensao.length() < 5){
	        		helper.setExtensao(strExtensao.toUpperCase());
	        	}else{
	            	parametrosIncorretos = true;
	            	mensagemErro += "Extensao da Foto Invalida | ";
	            }
	        }
	
			// FileSizeFoto
	        String strFileSizeFoto = din.readUTF(); 
	        if(!isParamentroNulo(strFileSizeFoto)){
	        	if(Util.isLong(strFileSizeFoto)){
					helper.setFileSizeFoto(new Long(strFileSizeFoto));
	            }else{
	            	parametrosIncorretos = true;
	            	mensagemErro += "Tamanho da Foto Invalida | ";
	            }
	        }
	        
	        if(!isParamentroNulo(strFileSizeFoto) && !isParamentroNulo(strExtensao) ){
	        	// Foto
	            helper.setFoto(new byte[(int)helper.getFileSizeFoto()]);
				int read = 0;
				int numRead = 0;
				while(read < helper.getFoto().length && (numRead=din.read(helper.getFoto(), read, helper.getFoto().length-read))>= 0){
					read = read + numRead;
				}
	        }
	        
	        //Codigo do atendente
	  		String strIdAtendenteAbertura = din.readUTF();
	  		if(!isParamentroNulo(strIdAtendenteAbertura)){
	  			helper.setLoginAtendenteAbertura(strIdAtendenteAbertura);
	  		}
	  		
	  		//Matrícula do imovel mais perto
	  		String strIdImovel = din.readUTF();
	  		if(!isParamentroNulo(strIdImovel)){
	  			if(Util.isInteger(strIdImovel)){
	  				helper.setIdImovel(new Integer(strIdImovel));
	  			}else{
	  				parametrosIncorretos = true;
	  				mensagemErro += "Matricula do Imovel Invalida | ";
	  			}
	  		}
	  		
	  		//CPF do Solicitante
	  		String strCPF = din.readUTF();
	  		if(!isParamentroNulo(strCPF)){
	  			if(Util.validacaoCPF(strCPF)){
		  			helper.setNumeroCPF(strCPF);
	  			}else{
	  				parametrosIncorretos = true;
	  				mensagemErro += "Numero do CPF Invalido | ";
	  			}
	  		}
	  		
	  		//Observação
	  		String observacao = din.readUTF();
	  		if(!isParamentroNulo(observacao)){
	  			helper.setObservacao(observacao);
	  		}
		}else if(!isParamentroNulo(tipoRequisicao) && tipoRequisicao.equals("2")){
			//Número do Protocolo
			String numeroProtocolo = din.readUTF();
			if(!isParamentroNulo(numeroProtocolo)){
				helper.setNumeroProtocolo(numeroProtocolo);
			}
			
			//Codigo do atendente
	  		String strIdAtendenteAbertura = din.readUTF();
	  		if(!isParamentroNulo(strIdAtendenteAbertura)){
	  			helper.setLoginAtendenteAbertura(strIdAtendenteAbertura);
	  		}
		}
  		
  		return parametrosIncorretos;
	}
	
}

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
package gcom.gui.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelInscricaoAlterada;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class RemoverManterImovelAction extends GcomAction {

    /**
     * < <Descri��o do m�todo>>
     * 
     * @param actionMapping
     *            Descri��o do par�metro
     * @param actionForm
     *            Descri��o do par�metro
     * @param httpServletRequest
     *            Descri��o do par�metro
     * @param httpServletResponse
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

        String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        // Obtendo uma instancia da sessao
     	HttpSession sessao = httpServletRequest.getSession(false);
        
        Fachada fachada = Fachada.getInstancia();

        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        
        Usuario usuario = this.getUsuarioLogado(httpServletRequest);
        
        Collection<Imovel> colecaoImovelRemover = new ArrayList<Imovel>();
        
        boolean removerConcluido = false;
        
        //mensagem de erro quando o usu�rio tenta excluir sem ter selecionado
        // nenhum
        //registro
        if ( (httpServletRequest.getParameter("confirmado") == null || httpServletRequest.getParameter("confirmado").equals("")) ) {
	       
        	if (ids == null || ids.length == 0) {
	            throw new ActionServletException(
	                    "atencao.registros.nao_selecionados");
	        }
	        String[] idsFormatado = new String[ids.length];
	        
	        for (int i = 0; i < ids.length; i++) {
				String dadosImovel = ids[i];
				String[] idUltimaAlteracao = dadosImovel.split("-");
				String idImovel = idUltimaAlteracao[0].trim();
				
				// [FS0048 - Verificar permiss�o especial para o cliente associado];
				// . Caso o im�vel selecionado esteja associado a um cliente que 
				//   n�o permite altera��o sem permiss�o especial 
				if (fachada.verificarImovelAssociadoClienteBloqueiaAlteracao(new Integer(idImovel))) {
					// verificar se o usu�rio tem permiss�o especial para manter im�vel 
					boolean possuiPermissaoAlterarImoveisAssociados = 
			    			fachada.verificarPermissaoEspecialAtiva(
			    					PermissaoEspecial.ALTERAR_IMOVEIS_ASSOCIADOS,usuario);
			    		
			    		if(!possuiPermissaoAlterarImoveisAssociados){
			    			throw new ActionServletException(
			    					"atencao.usuario_permissao_alterar_imovel_associado");
			    		}
				}
				
				idsFormatado[i] = idImovel;
				Calendar dataInicio = new GregorianCalendar();
				dataInicio.set(Calendar.YEAR,1900);
				dataInicio.set(Calendar.MONTH,0);
				dataInicio.set(Calendar.DATE,01);
	
				Calendar dataFim = new GregorianCalendar();
				dataFim.set(Calendar.YEAR,9999);
				dataFim.set(Calendar.MONTH,11);
				dataFim.set(Calendar.DATE,31);			
				
				ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper =  fachada.obterDebitoImovelOuCliente(1,idImovel,null,null,"190001","999912",dataInicio.getTime(),dataFim.getTime(),1,1,1,1,1,1,1,null);
	        	boolean existeDebito = false;
	        	if(obterDebitoImovelOuClienteHelper != null){
	        		//contas
	        		if(obterDebitoImovelOuClienteHelper.getColecaoContasValores() != null && !obterDebitoImovelOuClienteHelper.getColecaoContasValores().isEmpty()){
	        			existeDebito = true;
	        		}else 
	        		//credito a realizar
	        		if(obterDebitoImovelOuClienteHelper.getColecaoCreditoARealizar() != null && !obterDebitoImovelOuClienteHelper.getColecaoCreditoARealizar().isEmpty()){
	        			existeDebito = true;	
	        		}else 
	        		//debito a cobrar
	        		if(obterDebitoImovelOuClienteHelper.getColecaoDebitoACobrar() != null && !obterDebitoImovelOuClienteHelper.getColecaoDebitoACobrar().isEmpty()){
	        			existeDebito = true;	
	        		}else 
	        		//guias pagamento
	        		if(obterDebitoImovelOuClienteHelper.getColecaoGuiasPagamentoValores() != null && !obterDebitoImovelOuClienteHelper.getColecaoGuiasPagamentoValores().isEmpty()){
	        			existeDebito = true;	
	        		}
	        		
	        	}
	        	
	        	//Se existir debito para o imovel
	        	if(existeDebito){
	        		throw new ActionServletException(
					"atencao.imovel.possui.debito.nao.excluir");
	        	}
	        	
	        	FiltroImovel filtroImovel = new FiltroImovel();
	        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);
	        	filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);
	        	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,idImovel));
	        	
	        	Collection<Imovel> colecaoImovel = fachada.pesquisar(filtroImovel,Imovel.class.getName());
	        	
	        	//[FS0004] - Im�vel possui v�nculos no sistema
	        	if(colecaoImovel != null && !colecaoImovel.isEmpty()){
	
	        		Imovel imovel = (Imovel)colecaoImovel.iterator().next();
	
	       		if((imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.LIGADO.intValue() 
	        				
	        			| imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.CORTADO.intValue())
	        				
	        			| (imovel.getLigacaoEsgotoSituacao().getId().intValue() == LigacaoEsgotoSituacao.LIGADO.intValue())){
	    				
	        			throw new ActionServletException(
	    					"atencao.imovel.possui.ligacao_agua.ligacao_esgoto");
	        		}
	
		        		
		        	if((imovel.getLigacaoAgua() != null && imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null) | 
		        		 imovel.getHidrometroInstalacaoHistorico() != null){
		        		//N�o � poss�vel excluir o(s) im�vel(is) selecionado(s) devido a exist�ncia de hidr�metro na liga��o de e/ou no po�o
		        		throw new ActionServletException(
						"atencao.imovel.possui.hidrometro.ligacao.poco");
		        	}
		        	
		        	FiltroImovel filtroImovelVinculados = new FiltroImovel();
		        	filtroImovelVinculados.adicionarParametro(new ParametroSimples("imovelCondominio.id", imovel.getId()));
		        	
		        	Collection<Imovel> imovelPesquisadoVinculados = fachada.pesquisar(filtroImovelVinculados, Imovel.class.getName());
		        	
		        	if((imovel.getIndicadorImovelCondominio() != null &&  imovel.getIndicadorImovelCondominio().shortValue() == Imovel.IMOVEL_CONDOMINIO.shortValue())
		        			& (imovelPesquisadoVinculados != null && !imovelPesquisadoVinculados.isEmpty())
		        		){
		        		//N�o � poss�vel excluir o(s) im�vel(is) selecionado(s) devido a ser(em) im�vel(is) condom�nio, com outros im�veis associados
		        		throw new ActionServletException(
						"atencao.imovel.ser.condominio.outros.imoveis");
		        	}
		        	
		        	colecaoImovelRemover.add(imovel);
	        		
	        	}
	        	
	        	
			}
        }
        //[SB0006] - Verificar Exclus�o do Im�vel
    	if ( (httpServletRequest.getParameter("confirmado") == null || httpServletRequest.getParameter("confirmado").equals("")) && 
    		sistemaParametro.getIndicadorAlteracaoInscricaoImovel() != null && 
    			sistemaParametro.getIndicadorAlteracaoInscricaoImovel().toString().equals(ConstantesSistema.SIM.toString())) {
    		
//    		if ( fachada.verificarImovelEmProcessoDeFaturamento(imovel.getId()) ) {
    			httpServletRequest.setAttribute("nomeBotao1", "Sim");
				httpServletRequest.setAttribute("nomeBotao3", "N�o");
				httpServletRequest.setAttribute("confirmado", "ok");
				sessao.setAttribute("colecaoImovelRemover", colecaoImovelRemover);

				sessao.setAttribute("remove" , "nao");
				
				return montarPaginaConfirmacao("atencao.imovel_em_processo_de_faturamento_excluir",
				httpServletRequest, actionMapping);
//    		}
    		
    	} else if ((httpServletRequest.getParameter("confirmado") == null || httpServletRequest.getParameter("confirmado").equals(""))){
    		 /** alterado por pedro alexandre dia 17/11/2006 
             * Recupera o usu�rio logado para passar no met�do de atualizar im�vel 
             * para verificar se o usu�rio tem abrang�ncia para atualizar o im�vel
             * informado.
             */
            Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
            
            fachada.removerImovel(ids, usuarioLogado);
            removerConcluido = true;
    	}
    	
    	String confirmado = httpServletRequest.getParameter("confirmado");
    	// Se tiver sido aprensentada a tela confirma��o e o usuario confirmou.
		if(confirmado != null && confirmado.equalsIgnoreCase("ok") ) {
			retorno = actionMapping.findForward("telaSucesso");
			 
			colecaoImovelRemover = (Collection<Imovel>) sessao.getAttribute("colecaoImovelRemover");
			
			Iterator<Imovel> iteratorImovel = colecaoImovelRemover.iterator();
	        while (iteratorImovel.hasNext() ) {

        		Imovel imovel = (Imovel)iteratorImovel.next();
				
        		//[SB0005] - Preparar Altera��o Inscri��o ou Exclus�o do Im�vel no Encerramento Faturamento
				prepararExclusaoImovelEncerramentoFaturamento(imovel, usuario);
        	}
	        removerConcluido = true;
	        sessao.removeAttribute("colecaoImovelRemover");
			
		}
	    
        //Monta a p�gina de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso") && removerConcluido) {
        	if ( ids != null ) {
        		
        		montarPaginaSucesso(httpServletRequest,
            		ids.length + " Im�vel(is) removido(s) com sucesso.",
                    "Realizar outra Manuten��o de Im�vel",
                    "exibirFiltrarImovelAction.do");
        	} else {
        		
        		montarPaginaSucesso(httpServletRequest,
            		colecaoImovelRemover.size() + " Im�vel(is) removido(s) com sucesso.",
                    "Realizar outra Manuten��o de Im�vel",
                    "exibirFiltrarImovelAction.do");
        	}
        } else {
        	montarPaginaSucesso(httpServletRequest,
         		"O Im�vel n�o teve seus dados alterados.",
                 "Realizar outra Manuten��o de Im�vel",
                 "exibirFiltrarImovelAction.do");        	
        }

        return retorno;
    }
    
    /**
	 * [SB0005] - Preparar Altera��o Inscri��o ou Exclus�o do Im�vel no Encerramento Faturamento
	 * @author Arthur Carvalho
	 * @date 26/10/2011
	 */
	private void prepararExclusaoImovelEncerramentoFaturamento( Imovel imovel,
			  Usuario usuario ){
		
		//[FS0038 - Verificar Exist�ncia de Altera��o de Inscri��o Pendente para o Im�vel]
		Fachada.getInstancia().verificarExistenciaAlteracaoInscricaoPendenteImovel(imovel);

		ImovelInscricaoAlterada imovelInscricaoAlterada = new ImovelInscricaoAlterada();
		
		imovelInscricaoAlterada.setImovel(imovel);
		imovelInscricaoAlterada.setFaturamentoGrupo(null);
		//Dados da inscricao Anterior
		imovelInscricaoAlterada.setLocalidadeAnterior(imovel.getLocalidade());
		imovelInscricaoAlterada.setSetorComercialAnterior(imovel.getSetorComercial());
		imovelInscricaoAlterada.setQuadraAnterior(imovel.getQuadra());
		imovelInscricaoAlterada.setQuadraFaceAnterior(imovel.getQuadraFace()!=null?imovel.getQuadraFace():null);
		imovelInscricaoAlterada.setLoteAnterior(imovel.getLote());
		imovelInscricaoAlterada.setSubLoteAnterior(imovel.getSubLote());
		//Dados da inscricao Atual
		imovelInscricaoAlterada.setLocalidadeAtual(imovel.getLocalidade());
		imovelInscricaoAlterada.setSetorComercialAtual(imovel.getSetorComercial());
		imovelInscricaoAlterada.setQuadraAtual(imovel.getQuadra());
		imovelInscricaoAlterada.setQuadraFaceAtual(imovel.getQuadraFace()!=null?imovel.getQuadraFace():null);
		imovelInscricaoAlterada.setLoteAtual(imovel.getLote());
		imovelInscricaoAlterada.setSubLoteAtual(imovel.getSubLote());
		
		imovelInscricaoAlterada.setIndicadorAtualizado(ConstantesSistema.NAO);
		imovelInscricaoAlterada.setIndicadorAtualizacaoExcluida(ConstantesSistema.NAO);
		imovelInscricaoAlterada.setIndicadorImovelExcluido(ConstantesSistema.SIM);
		imovelInscricaoAlterada.setIndicadorErroAlteracao(null);
		
		if (this.getSistemaParametro().getIndicadorAlteracaoInscricaoImovel().toString().equals(""+ConstantesSistema.SIM)){
			
			imovelInscricaoAlterada.setIndicadorAutorizado(ConstantesSistema.NAO);
		} else {
			
			imovelInscricaoAlterada.setIndicadorAutorizado(ConstantesSistema.SIM);
		}
		
		imovelInscricaoAlterada.setUsuarioAlteracao(usuario);
		imovelInscricaoAlterada.setDataAlteracaoOnline(new Date());
		imovelInscricaoAlterada.setDataAlteracaoBatch(null);
		imovelInscricaoAlterada.setUltimaAlteracao(new Date());
		
		Fachada.getInstancia().inserir(imovelInscricaoAlterada);
		
	}

}

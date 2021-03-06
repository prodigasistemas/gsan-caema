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

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action respons�vel por adicionar na cole��o a rela��o entre o cliente imovel,
 * o cliente e a data de inicio da rela��o
 * 
 * @author S�vio Luiz
 * @created 16 de Maio de 2004
 */
public class AdicionarAtualizarImovelClienteAction extends GcomAction {

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

        ActionForward retorno = actionMapping
                .findForward("adicionarAtualizarImovelCliente");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

        DynaValidatorForm inserirImovelActionForm = (DynaValidatorForm) actionForm;

        Fachada fachada = Fachada.getInstancia();

        Collection imovelClientesNovos = null;

        Imovel imovel = (Imovel) sessao.getAttribute("imovelAtualizacao");

        if (sessao.getAttribute("imovelClientesNovos") != null) {
            imovelClientesNovos = (Collection) sessao
                    .getAttribute("imovelClientesNovos");
        } else {
            imovelClientesNovos = new ArrayList();
        }

        //inst�ncia um cliente

        Cliente cliente = new Cliente();

        //teste se o cliente ja foi pesquisado com enter

        if (inserirImovelActionForm.get("idCliente") != null) {

            //recupera o id do cliente
            String idCliente = (String) inserirImovelActionForm
                    .get("idCliente");
            //inst�ncia o filtro do cliente
            FiltroCliente filtroCliente = new FiltroCliente();

            //adiciona o parametro no filtro
            filtroCliente.adicionarParametro(new ParametroSimples(
                    FiltroCliente.ID, idCliente));
            filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.INDICADOR_USO,
            	ConstantesSistema.INDICADOR_USO_ATIVO));

            //faz a pesquisa do cliente
            Collection clientesObjs = fachada.pesquisar(filtroCliente,
                    Cliente.class.getName());

            //recupera o cliente da cole��o pesquisada
            if (!clientesObjs.isEmpty()) {
                cliente = (Cliente) clientesObjs.iterator().next();
            } else {
                throw new ActionServletException("atencao.naocadastrado", null,
                        "Cliente");
            }

        }

        //inicializa o tipo do cliente imovel
        ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();

        //recupera id do tipo do cliente imovel
        clienteRelacaoTipo.setId((Integer) inserirImovelActionForm
                .get("tipoClienteImovel"));
        //recupera a descricao do tipo do cliente imovel
        clienteRelacaoTipo.setDescricao((String) inserirImovelActionForm
                .get("textoSelecionado"));

        
        
        
        SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");

        Date dataInicioRelacao = null;
        if(inserirImovelActionForm.get("dataInicioClienteImovelRelacao") != null && !((String)inserirImovelActionForm.get("dataInicioClienteImovelRelacao")).equals("")){
	        try {
	            dataInicioRelacao = dataFormato.parse((String)inserirImovelActionForm.get("dataInicioClienteImovelRelacao"));
	
	        } catch (ParseException ex) {
	            dataInicioRelacao = null;
	        }
        }

        Date dataCorrente = null;
        Calendar data = Calendar.getInstance();

        data.set(Calendar.SECOND, 0);
        data.set(Calendar.MILLISECOND, 0);
        data.set(Calendar.HOUR, 0);
        data.set(Calendar.MINUTE, 0);
        dataCorrente = data.getTime();

        //caso a data de inicio da rela��o seja anterior que a data atual
        if (dataInicioRelacao.after(dataCorrente)) {
            throw new ActionServletException(
                    "atencao.data_inicio_relacao_cliente_imovel");
        }
        
        if(dataInicioRelacao == null){
        	dataInicioRelacao = new Date();
        }
        
        //inicializa o cliente imovel
        ClienteImovel clienteImovel = new ClienteImovel(dataInicioRelacao, null, null,
                cliente, clienteRelacaoTipo);
        
        // Verifica permiss�o especial para manter cliente 
        // responsavel do imovel.
        Categoria categoria =
    		fachada.obterPrincipalCategoriaImovel(imovel.getId());
    	
    	if(categoria.getId().compareTo(Categoria.PUBLICO)==0
    			&& clienteImovel.getClienteRelacaoTipo().getId()
    				.compareTo(ClienteRelacaoTipo.RESPONSAVEL.intValue())==0){
    		
    		boolean possuiPermissaoManterClienteResponsavelImoveisPublicos = 
    			fachada.verificarPermissaoEspecialAtiva(
    					PermissaoEspecial.ALTERAR_CLIENTE_RESPONSAVEL_PARA_IMOVEIS_PUBLICOS,usuarioLogado);
    		
    		if(!possuiPermissaoManterClienteResponsavelImoveisPublicos){
    			throw new ActionServletException(
    					"atencao.nao_usuario_nao_possui_permissao_alterar_cliente_reponsavel");
    		}
    	 	
    	}
    	
    	//Adiciona o imovel ao cliente imovel
        clienteImovel.setImovel(imovel);
        //Coloca a data de ultima altera��o para identificar o objeto
        clienteImovel.setUltimaAlteracao(new Date());
        
        Iterator it = imovelClientesNovos.iterator();
        
        while(it.hasNext()){
        	ClienteImovel clie = (ClienteImovel) it.next();
        	
        	if((clie.getCliente().getId().intValue() == clienteImovel.getCliente().getId().intValue())
        			&& (clie.getClienteRelacaoTipo().getId().intValue() == clienteImovel.getClienteRelacaoTipo().getId().intValue())){
        		throw new ActionServletException( 
                    "atencao.ja_cadastradado.cliente_imovel", null,
                    clienteImovel.getClienteRelacaoTipo().getDescricao());
        	}
        }

        if (!imovelClientesNovos.contains(clienteImovel)) {
            //verifica se o tipo do cliente � usu�rio ou � respons�vel
            if (clienteImovel.getClienteRelacaoTipo().getId().intValue() == ConstantesSistema.CLIENTE_IMOVEL_TIPO_USUARIO.intValue() ) {
                
            	if (sessao.getAttribute("idClienteImovelUsuario") == null
                   || sessao.getAttribute("idClienteImovelUsuario").equals("")) {
                	if (imovel != null && imovel.getImovelPerfil() != null
                    && imovel.getImovelPerfil().getId() != null && imovel.getImovelPerfil().getId().equals(ConstantesSistema.INDICADOR_TARIFA_SOCIAL)) {
                        throw new ActionServletException( //cliente na tarifa social
                                "atencao.cliente_na_tarifa_social", null,
                                "usu�rio");
                    }
                    //inserirImovelActionForm.set("idClienteImovelUsuario",
                            //cliente.getId().toString());
                    sessao.setAttribute("idClienteImovelUsuario",
                            cliente.getId().toString());
                    httpServletRequest.setAttribute("idClienteImovelUsuario",cliente.getId().toString());
                    //httpServletRequest.setAttribute("idClienteImovelUsuario",
                    //        cliente.getId().toString());
                    //adiciona o cliente imovel na cole��o de
                    // imovelClientesNovos
                    clienteImovel.setIndicadorNomeConta(new Short((short)2));
                    
                    imovelClientesNovos.add(clienteImovel);
                    
                } else {
                    throw new ActionServletException(
                            "atencao.ja_cadastradado.cliente_imovel_usuario");
                }
            } else if (clienteImovel.getClienteRelacaoTipo().getId().intValue() == ConstantesSistema.CLIENTE_IMOVEL_TIPO_RESPONSAVEL.intValue()) {
                    
            		if (sessao.getAttribute("idClienteImovelResponsavel") == null
                            || sessao.getAttribute(
                                    "idClienteImovelResponsavel").equals("")) {

//                    	inserirImovelActionForm.set("idClienteImovelResponsavel",
  //                              cliente.getId().toString());
                        //httpServletRequest.setAttribute("idClienteImovelResponsavel",
                          //      cliente.getId().toString());
            			sessao.setAttribute("idClienteImovelResponsavel",
                                cliente.getId().toString());
                    	
                        //adiciona o cliente imovel na cole��o de
                        // imovelClientesNovos
            			clienteImovel.setIndicadorNomeConta(new Short((short)2));            			
                        imovelClientesNovos.add(clienteImovel);
                    }else{
                		throw new ActionServletException(
                                "atencao.ja_cadastradado.cliente_imovel_responsavel");
                    }
            } else {
                //sever para cliente do tipo proprietario
                //adiciona o cliente imovel na cole��o de
                // imovelClientesNovos
            	
            	if (clienteImovel.getClienteRelacaoTipo().getId().intValue() == ConstantesSistema.CLIENTE_IMOVEL_TIPO_PROPIETARIO.intValue()) {
            		
            		Iterator iImovelClientesNovos =  imovelClientesNovos.iterator();
            		while (iImovelClientesNovos.hasNext()){
            			
            			ClienteImovel clieImov = (ClienteImovel) iImovelClientesNovos.next();
            			
            			if (clienteImovel.getCliente().getId().equals(clieImov.getCliente().getId())
            					&& (clieImov.getClienteRelacaoTipo().getId().equals(ConstantesSistema.CLIENTE_IMOVEL_TIPO_PROPIETARIO.intValue()))){

                			throw new ActionServletException(
                                    "atencao.ja_cadastradado_imovel.cliente_proprietario");
                		}
            		}
            		
            	}
            	
            	clienteImovel.setIndicadorNomeConta(new Short((short)2));
                imovelClientesNovos.add(clienteImovel);
            }

            inserirImovelActionForm.set("idCliente", null);
            inserirImovelActionForm.set("nomeCliente", null);

            //manda para a sess�o a cole��o de imovelClienteNovos
            sessao.setAttribute("imovelClientesNovos", imovelClientesNovos);

        } else {
            throw new ActionServletException(
                    "atencao.ja_cadastradado.cliente_imovel");
        }

        return retorno;
    }

}

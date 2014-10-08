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
package gcom.gui.cobranca;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1384] - Reabrir Comando de Atividade de Ação de Cobrança
 * 
 * @author Hugo Azevedo
 * @date 29/10/2012
 * 
 */

public class ReabrirComandoAtividadeAcaoCobrancaCronogramaAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");
        ReabrirComandoAtividadeAcaoCobrancaActionForm form = (ReabrirComandoAtividadeAcaoCobrancaActionForm) actionForm;
        
        String[] idsSelecionados = form.getIdsSelecionados();
        String novaDataEncerramento = form.getNovaDataEncerramento();
        
        //[FE 0001] Verificar Comandos Selecionados
        //---------------------------------------------------------------------------
  		//1. Caso não tenha nenhum comando selecionado
  		if(form.getIdsSelecionados() == null || form.getIdsSelecionados().length == 0){
  			
  			//exibir a mensagem "Selecione pelo menos um comando de ação de cobrança"
  			throw new ActionServletException("atencao.selecione_um_comando_acao_cob");
  		}
  		//---------------------------------------------------------------------------
  			
  		
  		//[FE 0002] Validar Data
  		//---------------------------------------------------------------------------
  		//1. Caso a data não esteja informada
  		if(novaDataEncerramento == null || novaDataEncerramento.equals("")){
  			
  			//o sistema deverá exibir a mensagem "Nova Data de Encerramento deve ser informada"
  			throw new ActionServletException("atencao.nova_data_encerramento_deve_ser_informado");
  		}
  		
  		//2. Caso a data esteja inválida
  		if(!Util.verificaSeDataValida(novaDataEncerramento, "dd/MM/yyyy")){
  			
  			//exibir a mensagem "Data inválida"
  			throw new ActionServletException("atencao.nova_data_invalida");
  		}
  		
  		//3. Caso a nova data de encerramento informada esteja menor que a data corrente
  		if(Util.compararData(Util.converteStringParaDate(novaDataEncerramento), new Date()) < 0){
  			
  			// o sistema deverá exibir a mensagem "Nova Data de Encerramento menor que a data corrente"
  			throw new ActionServletException("atencao.data_encerramento_menor_data_corrente");
  		}
  		//---------------------------------------------------------------------------
  		
  		
  		//5.1. [IT 0003 - Reabrir Comandos Cronograma]
		this.getFachada().reabrirComandoAtividadeAcaoCobrancaCronograma(idsSelecionados,Util.converteStringParaDate(novaDataEncerramento));
        
        montarPaginaSucesso(httpServletRequest, "Reabrir Comando Enviado para Processamento.", "", "","exibirFiltrarComandosAcaoCobrancaCronogramaReabrirAction.do?ultimoacesso=-1&situacaoComando=reabrir&tipoComando=Cronograma","Reabrir Outros Comandos de Atividade de Ação de Cobrança");
        
		return retorno;
	}
	
}

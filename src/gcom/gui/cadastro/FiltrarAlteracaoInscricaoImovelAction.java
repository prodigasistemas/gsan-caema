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
package gcom.gui.cadastro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gcom.cadastro.ImovelInscricaoAlteradaHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1162]	AUTORIZAR ALTERACAO INSCRICAO IMOVEL
 * 
 * @author Rodrigo Cabral
 * @date 05/04/2011
 */

public class FiltrarAlteracaoInscricaoImovelAction extends GcomAction {


	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("autorizarAlteracaoInscricaoImovel");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarAlteracaoInscricaoImovelActionForm form = (FiltrarAlteracaoInscricaoImovelActionForm) actionForm;
		
		
		// Recupera todos os campos da p�gina para ser colocada no filtro
		// posteriormente
		Integer idLocalidade = null;
		String desLocalidade = null;
		ArrayList<Integer> colecaoIdSetorComercial = null;
		ArrayList<Integer> colecaoIdQuadra = null;
		ArrayList<Integer> colecaoMatriculaImovel = new ArrayList<Integer>();
		String indicadorImovelAlteradoExcluido = null;
				
		if (form.getIdLocalidade() != null && !form.getIdLocalidade().equals("")){
			idLocalidade = new Integer(form.getIdLocalidade());
			desLocalidade = form.getDesLocalidade();
		}
		
		if(form.getIndicadorSelecionarPor().equals("1")){
		
			if(form.getSetorComercialSelecionados() != null){
				
				boolean setoresValidos = true;
				if(form.getSetorComercialSelecionados().length == 1){
					Integer valorSetor = form.getSetorComercialSelecionados()[0];
					
					if(valorSetor.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
						setoresValidos = false;
					}
				}
				
				if(setoresValidos){
					List<Integer> listSetorComercial =  Arrays.asList(form.getSetorComercialSelecionados());
					colecaoIdSetorComercial = new ArrayList<Integer>(listSetorComercial);
				}
			}
			
			if(form.getQuadraSelecionados() != null){
				
				boolean quadrasValidas = true;
				if(form.getQuadraSelecionados().length == 1){
					Integer valorQuadra = form.getQuadraSelecionados()[0];
					
					if(valorQuadra.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
						quadrasValidas = false;
					}
				}
				
				if(quadrasValidas){
					List<Integer> listQuadras = Arrays.asList(form.getQuadraSelecionados());
					colecaoIdQuadra = new ArrayList<Integer>(listQuadras);
				}
			}
			
			if(Util.isVazioOrNulo(colecaoIdSetorComercial)){
				 throw new ActionServletException("atencao.nenhum_setor_selecionado");
			}
			
			
		}else{
			if(form.getMatriculaImovel1() != null && !form.getMatriculaImovel1().equals("")){
				colecaoMatriculaImovel.add(Integer.parseInt(form.getMatriculaImovel1()));
			}
			
			if(form.getMatriculaImovel2() != null && !form.getMatriculaImovel2().equals("")){
				colecaoMatriculaImovel.add(Integer.parseInt(form.getMatriculaImovel2()));
			}
			
			if(form.getMatriculaImovel3() != null && !form.getMatriculaImovel3().equals("")){
				colecaoMatriculaImovel.add(Integer.parseInt(form.getMatriculaImovel3()));
			}
			
			if(form.getMatriculaImovel4() != null && !form.getMatriculaImovel4().equals("")){
				colecaoMatriculaImovel.add(Integer.parseInt(form.getMatriculaImovel4()));
			}
			
			if(form.getMatriculaImovel5() != null && !form.getMatriculaImovel5().equals("")){
				colecaoMatriculaImovel.add(Integer.parseInt(form.getMatriculaImovel5()));
			}
			
			if(form.getMatriculaImovel6() != null && !form.getMatriculaImovel6().equals("")){
				colecaoMatriculaImovel.add(Integer.parseInt(form.getMatriculaImovel6()));
			}
			
			if(form.getMatriculaImovel7() != null && !form.getMatriculaImovel7().equals("")){
				colecaoMatriculaImovel.add(Integer.parseInt(form.getMatriculaImovel7()));
			}
			
			if(form.getMatriculaImovel8() != null && !form.getMatriculaImovel8().equals("")){
				colecaoMatriculaImovel.add(Integer.parseInt(form.getMatriculaImovel8()));
			}
			
			if(form.getMatriculaImovel9() != null && !form.getMatriculaImovel9().equals("")){
				colecaoMatriculaImovel.add(Integer.parseInt(form.getMatriculaImovel9()));
			}
			
			if(form.getMatriculaImovel10() != null && !form.getMatriculaImovel10().equals("")){
				colecaoMatriculaImovel.add(Integer.parseInt(form.getMatriculaImovel10()));
			}
			
			if(form.getMatriculaImovel11() != null && !form.getMatriculaImovel11().equals("")){
				colecaoMatriculaImovel.add(Integer.parseInt(form.getMatriculaImovel11()));
			}
			
			if(form.getMatriculaImovel12() != null && !form.getMatriculaImovel12().equals("")){
				colecaoMatriculaImovel.add(Integer.parseInt(form.getMatriculaImovel12()));
			}
			
			idLocalidade = null;
			desLocalidade = null;
			form.setIdLocalidade("");
			form.setDesLocalidade("");
			
			
			if(Util.isVazioOrNulo(colecaoMatriculaImovel)){
				 throw new ActionServletException("atencao.nenhuma_matricula_informada");
			}
		}
		
		if ( form.getIndicadorImovelAlteradoExcluido() != null && !form.getIndicadorImovelAlteradoExcluido().equals("") ) {
			indicadorImovelAlteradoExcluido = form.getIndicadorImovelAlteradoExcluido();
		}
		
		ImovelInscricaoAlteradaHelper helper = new ImovelInscricaoAlteradaHelper();
		helper.setIdLocalidade(idLocalidade);
		helper.setDesLocalidade(desLocalidade);
		helper.setIndicadorImovelAlteradoExcluido(indicadorImovelAlteradoExcluido);
		helper.setColecaoIdSetorComercial(colecaoIdSetorComercial);
		helper.setColecaoIdQuadra(colecaoIdQuadra);
		helper.setColecaoMatriculas(colecaoMatriculaImovel);
		helper.setIndicadorOrigemAtualizacao(form.getIndicadorOrigemAtualizacao());
		helper.setIndicadorSelecionarPorLocalizacaoGeografica(form.getIndicadorSelecionarPor());
		
		
		// Manda o filtro pela sessao para o
		// ExibirAutorizarAlteracaoInscricaoImovelAction		
		sessao.setAttribute("imovelInscricaoAlterada", helper);
				
		return retorno;
		}
}		
				
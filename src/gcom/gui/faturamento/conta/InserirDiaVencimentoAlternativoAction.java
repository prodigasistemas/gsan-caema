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
package gcom.gui.faturamento.conta;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroVencimentoAlternativo;
import gcom.faturamento.VencimentoAlternativo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirDiaVencimentoAlternativoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);

		boolean portalCompesa = false;
		if ( sessao.getAttribute("portalCompesa") != null &&
				sessao.getAttribute("portalCompesa").equals("ok") ) {
			
			portalCompesa  = true;
		}

		if(portalCompesa){
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.INDICADOR_USUARIO_INTERNET, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
			
			usuarioLogado = 
					(Usuario) Util.retonarObjetoDeColecao(
				
			this.getFachada().pesquisar(filtroUsuario, Usuario.class.getName()));
	
		}	

		// Inst�ncia do formul�rio que est� sendo utilizado
		InformarVencimentoAlternativoActionForm informarVencimentoAlternativoActionForm = (InformarVencimentoAlternativoActionForm) actionForm;

		
		Imovel imovel = (Imovel) sessao.getAttribute("imovel");
		
		VencimentoAlternativo vencimentoAlternativo = 
			(VencimentoAlternativo) sessao.getAttribute("vencimentoAlternativo");
		
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		
		Collection vencimentosAlternativos = new ArrayList();
		
		if(vencimentoAlternativo == null || vencimentoAlternativo.equals("")) {
			
			FiltroVencimentoAlternativo filtroVencimentoAlternativo = 
				new FiltroVencimentoAlternativo(FiltroVencimentoAlternativo.DATA_IMPLANTACAO);
			
			filtroVencimentoAlternativo.adicionarParametro(
				new ParametroSimples(FiltroVencimentoAlternativo.IMOVEL_ID, imovel.getId()));
	
			vencimentosAlternativos = 
					this.getFachada().pesquisar(filtroVencimentoAlternativo, VencimentoAlternativo.class.getName());
	
			if (vencimentosAlternativos != null && !vencimentosAlternativos.isEmpty()) {
	
				vencimentoAlternativo = 
					(VencimentoAlternativo) Util.retonarObjetoDeColecao(vencimentosAlternativos);
	
				Date dataVencimento = vencimentoAlternativo.getDataImplantacao();
	
				SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
				
				String dataVencimentoString = null;
				if (dataVencimento != null) {
					dataVencimentoString = dataFormato.format(dataVencimento);
				}
	
				informarVencimentoAlternativoActionForm.setDataAlteracaoVencimento(
					dataVencimentoString == null ? "": "" + dataVencimentoString);
			}
		
		}else {
		
			Short numeroMesesMinimoVencimentoAlternativo = sistemaParametro.getNumeroMesesMinimoAlteracaoVencimento();
			
			//verifica  se usuario possue permiss�o especial para informar 
			//o vencimento alternativo antes do per�odo v�lido
			boolean temPermissaoInformarVencimentoAlternativoAntesDoPeriodoValido = 
				this.getFachada().verificarPermissaoInformarVencimentoAlternativoAntesDoPeriodoValido(usuarioLogado);
			
			Date dataImplantacao = vencimentoAlternativo.getDataImplantacao();
			Date dataAtual = new Date();

			int diferencaMes = Util.dataDiff(dataAtual,dataImplantacao);

			if (diferencaMes < numeroMesesMinimoVencimentoAlternativo && !temPermissaoInformarVencimentoAlternativoAntesDoPeriodoValido) {
				
				if(portalCompesa){
					
					httpServletRequest.setAttribute("numeroMesesMinimo", numeroMesesMinimoVencimentoAlternativo +"");
					httpServletRequest.setAttribute("numeroMesesMinimoVencimentoAlternativoSuperiorPermitido", true);
					httpServletRequest.setAttribute("voltarServicos", true);
					
					return actionMapping.findForward("numeroMesesMinimoVencimentoAlternativoSuperiorPermitido");
					
				}else{
					throw new ActionServletException("atencao.imovel.vencimento.alterado", 
						null,
						numeroMesesMinimoVencimentoAlternativo.toString());					
				}
				
			} else {

				vencimentoAlternativo.setDateExclusao(new Date());
				vencimentoAlternativo.setUltimaAlteracao(new Date());
				
				this.getFachada().atualizar(vencimentoAlternativo);
			}
		}

		Short novoDiaVencimento = null;
		novoDiaVencimento = 
			new Short(informarVencimentoAlternativoActionForm.getNovoDiaVencimento());
		

		if (imovel != null && !imovel.equals("")) {
			
			imovel.setDiaVencimento(novoDiaVencimento);
			imovel.setUltimaAlteracao(new Date());

			//Alterado por R�mulo Aur�lio Data: 24/08/2010
			//Analista: Ana Cristina
			//Altera��o: Retirada do layout da funcionalidade o indicador �Vencimento � para m�s seguinte: SIM ou N�O�.
			FaturamentoGrupo faturamentoGrupo = 
				this.getFachada().recuperaGrupoFaturamentoDoImovel(imovel.getId());
			
			if(faturamentoGrupo.getDiaVencimento() > novoDiaVencimento){
				imovel.setIndicadorVencimentoMesSeguinte(ConstantesSistema.INDICADOR_USO_ATIVO);
			}else{
				imovel.setIndicadorVencimentoMesSeguinte(ConstantesSistema.INDICADOR_USO_DESATIVO);
			}
			
			this.getFachada().atualizar(imovel);
		}

		VencimentoAlternativo vencimentoAlternativoInserir = new VencimentoAlternativo();

		if (imovel != null && !imovel.equals("")) {
			vencimentoAlternativoInserir.setImovel(imovel);
		}

		vencimentoAlternativoInserir.setDataImplantacao(new Date());
		vencimentoAlternativoInserir.setDateVencimento(novoDiaVencimento);
		vencimentoAlternativoInserir.setDateExclusao(null);
		vencimentoAlternativoInserir.setUltimaAlteracao(new Date());

		// ------------ REGISTRAR TRANSA��O ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
			Operacao.OPERACAO_INSERIR_VENCIMENTO_ALTERNATIVO, 
			vencimentoAlternativoInserir.getImovel().getId(),
			vencimentoAlternativoInserir.getImovel().getId(), 
			new UsuarioAcaoUsuarioHelper(usuarioLogado,
			UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
    		
		registradorOperacao.registrarOperacao(vencimentoAlternativoInserir);
		
		this.getFachada().inserir(vencimentoAlternativoInserir);
		
		if (portalCompesa) {
			httpServletRequest.setAttribute("VencimentoAlteradoComSucesso", true);
			retorno = actionMapping.findForward("VencimentoAlteradoComSucesso");
		} else {
			
			sessao.removeAttribute("colecaoNovoDiaVencimento");
			sessao.removeAttribute("imovel");
			sessao.removeAttribute("vencimentoAlternativo");
			
			montarPaginaSucesso(httpServletRequest,
				"Vencimento Alternativo para o im�vel "+ informarVencimentoAlternativoActionForm.getIdImovel()+ " inserido com sucesso.",
				"Informar outro Vencimento Alternativo",
				"exibirInformarVencimentoAlternativoAction.do?menu=sim");
		}
		
		//Contabiliza os servicos utilizados
		if(httpServletRequest.getParameter("lojaVirtual") != null){
			String ip = httpServletRequest.getRemoteAddr(); 
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.ALTERAR_VENCIMENTO, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);
			
		}
		
		return retorno;
	}
	
}

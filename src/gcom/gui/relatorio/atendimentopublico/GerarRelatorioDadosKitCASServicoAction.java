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
package gcom.gui.relatorio.atendimentopublico;

import java.util.Collection;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gerencial.faturamento.bean.FiltrarResumoDadosCasHelper;
import gcom.gui.ActionServletException;
import gcom.gui.atendimentopublico.GerarRelatorioDadosKitCASServicoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioDadosKitCASServico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import gcom.relatorio.atendimentopublico.RelatorioDadosKitCASServicoBean;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class GerarRelatorioDadosKitCASServicoAction extends
		ExibidorProcessamentoTarefaRelatorio {


	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno
		ActionForward retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		GerarRelatorioDadosKitCASServicoActionForm form = (GerarRelatorioDadosKitCASServicoActionForm) actionForm;
		
		String anoMesReferencia = form.getMesAnoReferencia();
		String idUnidadeNegocio= form.getIdUnidadeNegocio();
		String idGerenciaRegional = form.getIdGerenciaRegional();
		String idLocalidade = form.getIdLocalidade();
		String idMunicipio = form.getIdMunicipio();
		String opcaoTotalizacao = form.getOpcaoTotalizacao();
		
		
		//[FS0001] - Validar refer�ncia
		//========================================
		
		
		//Caso o m�s/ano da refer�ncia estejam inv�lidos
        if(this.validarMesAno(form.getMesAnoReferencia()))
        	anoMesReferencia = Util.formatarMesAnoParaAnoMesSemBarra(form.getMesAnoReferencia());
        else
        	throw new ActionServletException("atencao.data_invalida", null,form.getMesAnoReferencia());
        
        
        //Caso o m�s/ano da refer�ncia sejam maiores que o m�s/ano corrente    	
        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        Integer mesAnoFaturamento = sistemaParametro.getAnoMesFaturamento();

        if(Util.converterStringParaInteger(anoMesReferencia).intValue() > mesAnoFaturamento.intValue() ){
        	throw new ActionServletException("atencao.referencia_informada_maior_sistema");
        }
		
        //Caso n�o existam dados nas tabelas resumo do m�dulo gerencial 
        //com o m�s/ano de refer�ncia correspondentes ao m�s/ano informados
        boolean validarMesAno = fachada.validarReferenciaGerencial(anoMesReferencia);
        if(!validarMesAno){
        	throw new ActionServletException("atencao.referencia_inexistencia_dados_gerenciais");
        }
 		
        FiltrarResumoDadosCasHelper helper = new FiltrarResumoDadosCasHelper();
        
        
        helper.setAnoMesReferencia(new Integer(anoMesReferencia));
        if(form.getIdGerenciaRegional() != null && !form.getIdGerenciaRegional().equals(""))
        	helper.setGerenciaRegional(new Integer(form.getIdGerenciaRegional()));
        if(form.getIdLocalidade() != null && !form.getIdLocalidade().equals(""))
        	helper.setLocalidade(new Integer(form.getIdLocalidade()));
        if(form.getIdMunicipio() != null && !form.getIdMunicipio().equals(""))
        	helper.setMunicipio(new Integer(form.getIdMunicipio()));
        if(form.getIdUnidadeNegocio() != null && !form.getIdUnidadeNegocio().equals(""))
        	helper.setUnidadeNegocio(new Integer(form.getIdUnidadeNegocio()));
        if(form.getOpcaoTotalizacao() != null && !form.getOpcaoTotalizacao().equals(""))
        	helper.setOpcaoTotalizacao(new Integer(form.getOpcaoTotalizacao()));

  		RelatorioDadosKitCASServico relatorioDadosKitCASServico = new RelatorioDadosKitCASServico(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));

  		relatorioDadosKitCASServico.addParametro("helper",helper);
  		
  		
  		//Validar Localidade
  		if("17".equals(opcaoTotalizacao) && idLocalidade != null){
	  		Localidade localidade = fachada.obterColecaoLocalidade(new Integer(idLocalidade), null,null);
				
			//[FS0002 - Verificar exist�ncia da localidade]
			if(localidade == null){
				throw new ActionServletException("pesquisa.localidade.inexistente");
			}
  		}
		
		//Validar Munic�pio
  		if("20".equals(opcaoTotalizacao) && idMunicipio != null){

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, idMunicipio));
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection colecao = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
			
			//[FS0003 - Verificar exist�ncia da Munic�pio]
			if(colecao == null || colecao.size() == 0){
				throw new ActionServletException("atencao.municipio.inexistente");
			}
		
  		}

  		httpServletRequest.setAttribute( "telaSucessoRelatorio", "sim" );
  		
		String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		relatorioDadosKitCASServico.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		
		
		retorno = processarExibicaoRelatorio(relatorioDadosKitCASServico,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);

		// devolve o mapeamento contido na vari�vel retorno
		return retorno;

	}
	
	private boolean validarMesAno(String mesAnoReferencia) {
		boolean mesAnoValido = true;

		if (mesAnoReferencia.length() == 7) {
			String mes = mesAnoReferencia.substring(0, 2);
			String barra = mesAnoReferencia.substring(2, 3);

			try {
				int mesInt = Integer.parseInt(mes);
				// int anoInt = Integer.parseInt(ano);

				if (mesInt > 12 || !barra.equals("/")) {
					mesAnoValido = false;
				}
			} catch (NumberFormatException e) {
				mesAnoValido = false;
			}

		} else {
			mesAnoValido = false;
		}

		return mesAnoValido;
	}
}

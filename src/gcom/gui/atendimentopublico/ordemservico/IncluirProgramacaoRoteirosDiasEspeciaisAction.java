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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.FiltroEquipe;
import gcom.atendimentopublico.ordemservico.FiltroProgramacaoDiasEspeciais;
import gcom.atendimentopublico.ordemservico.ProgramacaoDiasEspeciais;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.geografico.FiltroMunicipioFeriado;
import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.sistemaparametro.FiltroNacionalFeriado;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1199] – Acompanhar Arquivos de Roteiro
 * 
 * @author Thúlio Araújo
 *
 * @date 15/07/2011
 */
public class IncluirProgramacaoRoteirosDiasEspeciaisAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();
	
		IncluirProgramacaoRoteirosDiasEspeciaisActionForm form = 
			(IncluirProgramacaoRoteirosDiasEspeciaisActionForm) actionForm;
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		//verifica se os campos foram preenchidos
		// Data de Programação
		if(form.getDataProgramacao() == null || form.getDataProgramacao().equals("")){
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Data da Programação");
		}
		
		// Equipe
		if(form.getIdEquipe() == null || form.getIdEquipe().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Equipe");
		}
		
		ProgramacaoDiasEspeciais programacaoDiasEspeciais = new ProgramacaoDiasEspeciais();
		Date dataProgramacao = null;
		try {
		dataProgramacao = format.parse(form.getDataProgramacao());
	
			programacaoDiasEspeciais.setDataProgramacao(dataProgramacao);
		} catch (ParseException e) {
			throw new ActionServletException("erro.sistema");
		}
		
		//Valida a existencia da programação na base de dados
		FiltroProgramacaoDiasEspeciais filtroProgramacaoDiasEspeciais = new FiltroProgramacaoDiasEspeciais();
		filtroProgramacaoDiasEspeciais.adicionarParametro(new ParametroSimples(FiltroProgramacaoDiasEspeciais.DATA_PROGRAMACAO, dataProgramacao));
		filtroProgramacaoDiasEspeciais.adicionarParametro(new ParametroSimples(FiltroProgramacaoDiasEspeciais.EQUIPE_ID, Util.converterStringParaInteger(form.getIdEquipe())));
		
		Collection<?> colecaoProgramacaoDiaEspecial = fachada.pesquisar(filtroProgramacaoDiasEspeciais, ProgramacaoDiasEspeciais.class.getName());
		
		if(colecaoProgramacaoDiaEspecial != null && !colecaoProgramacaoDiaEspecial.isEmpty()){
			throw new ActionServletException("atencao.programacao_dia_especial");
		}
		
		Equipe equipe = new Equipe();
		equipe.setId(new Integer(form.getIdEquipe()));
		programacaoDiasEspeciais.setEquipe(equipe);
		
		Empresa empresa = new Empresa();
		empresa.setId(new Integer(form.getIdEmpresa()));
		programacaoDiasEspeciais.setEmpresa(empresa);
		
		programacaoDiasEspeciais.setDataUltimaAlteracao(new Date());
		
		//RM9335 - Foi solicitado por Aryed que essa parte de validação do feriado fosse tirado.
		//Autor: Sávio Luiz
		//Data: 18/11/2013
		//verificar existencia de programação dia especial 
		retorno = this.validarDiasEspeciais(dataProgramacao, form.getIdEquipe(), fachada, httpServletRequest, actionMapping);
		
		if ( httpServletRequest.getParameter("confirmado") != null  && httpServletRequest.getParameter("tipoRelatorio") != null && 
				httpServletRequest.getParameter("tipoRelatorio").toString().equals("1")) {
			form.setConfirmacao("1");
		}
		
		//Exibe tela de confirmacao montada no passo anterior
		if(retorno.getName().equalsIgnoreCase("telaConfirmacao") && (form.getConfirmacao() == null || form.getConfirmacao().equals("")) ){
			return retorno;
		}
		
		retorno = actionMapping.findForward("telaSucesso");
		
		//[IT0002]
		FiltroEquipe filtroEquipe = new FiltroEquipe();
		filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID, form.getIdEquipe()));
		Collection<?> colecaoEquipe= fachada.pesquisar(filtroEquipe, Equipe.class.getName());
	
		//[IT0004][IT0005]
		if(colecaoEquipe != null && colecaoEquipe.size() != 0 && dataProgramacao != null){
			Equipe equipeInserir = (Equipe) colecaoEquipe.iterator().next();
			dataProgramacao = Util.adicionarHorasDatas(dataProgramacao,1);
		
			fachada.inserirProgramacaoAcompServico(programacaoDiasEspeciais,equipeInserir, dataProgramacao);
		}
		
		montarPaginaSucesso(httpServletRequest, "Programação Roteiro em dias especiais inserida com sucesso.",
                "Realizar outra Programação Roteiro em dias especiais",
                "exibirIncluirProgramacaoRoteirosDiasEspeciaisAction.do?menu=sim");
		
		return retorno;
	}
	
	/**
	 * Validar Dias Especiais
	 * 
	 * @author Davi Menezes
	 * @date 30/09/2013
	 * 
	 * @param dataProgramacao
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward validarDiasEspeciais(Date dataProgramacao, String idEquipe, Fachada fachada, 
			HttpServletRequest request, ActionMapping mapping){
		
		ActionForward retorno = mapping.findForward("telaSucesso");
		
		FiltroNacionalFeriado filtroNacionalFeriado = new FiltroNacionalFeriado();
		
		Collection<NacionalFeriado> colecaoNacionalFeriados = fachada.pesquisar(filtroNacionalFeriado, NacionalFeriado.class.getName());
		
		FiltroEquipe filtroEquipe = new FiltroEquipe();
		filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID, idEquipe));
		filtroEquipe.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroEquipe.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional.localidade");
		filtroEquipe.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional.localidade.municipio");
		
		Collection<?> colecaoEquipe = fachada.pesquisar(filtroEquipe, Equipe.class.getName());
		Equipe equipe = (Equipe) Util.retonarObjetoDeColecao(colecaoEquipe);
		
//		if(equipe.getUnidadeOrganizacional().getLocalidade() == null){
//			String msg = equipe.getUnidadeOrganizacional().getId() + " - " + equipe.getUnidadeOrganizacional().getDescricao();
//			
//			throw new ActionServletException("atencao.localidade_nao_informada_unidade_organizacional", null, msg);
//		}else{
//			if(equipe.getUnidadeOrganizacional().getLocalidade().getMunicipio() == null){
//				String msg = equipe.getUnidadeOrganizacional().getLocalidade().getId() + " - " 
//					+ equipe.getUnidadeOrganizacional().getLocalidade().getDescricao();
//				
//				throw new ActionServletException("atencao.municipio_nao_informado_localidade", null, msg);
//			}
//		}
		
		Collection<MunicipioFeriado> colecaoMunicipioFeriados = null;
		
		if(equipe.getUnidadeOrganizacional().getLocalidade() != null && equipe.getUnidadeOrganizacional().getLocalidade().getMunicipio() != null){
			FiltroMunicipioFeriado filtroMunicipioFeriado = new FiltroMunicipioFeriado();
			filtroMunicipioFeriado.adicionarParametro(new ParametroSimples(FiltroMunicipioFeriado.ID_MUNICIPIO, 
					equipe.getUnidadeOrganizacional().getLocalidade().getMunicipio().getId()));
			
			colecaoMunicipioFeriados = fachada.pesquisar(filtroMunicipioFeriado, MunicipioFeriado.class.getName());
		}
		
		if(Util.ehDiaUtil(dataProgramacao, colecaoNacionalFeriados, colecaoMunicipioFeriados)){
			request.setAttribute("tipoRelatorio", "1");
			return montarPaginaConfirmacao("atencao.data_programacao_nao_corresponde_feriado",
					request, mapping);
		}
		
		return retorno;
	}
	
}
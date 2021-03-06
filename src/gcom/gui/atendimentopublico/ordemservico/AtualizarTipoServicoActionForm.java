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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.Material;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoAtividade;
import gcom.atendimentopublico.ordemservico.ServicoTipoMaterial;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.validator.ValidatorForm;

/**
 * [SB0001]Atualizar Tipo Perfil de Servi�o
 * 
 * @author K�ssia Albuquerque, Pedro Alexandre
 * @date 30/10/2006, 14/12/2007
 */

public class AtualizarTipoServicoActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String idTipoServico;

	private String codigoServico;

	private String descricao;

	private String abreviada;

	private String subgrupo;

	private String valor;

	// private String pavimento;

	private String atualizacaoComercial;

	private String servicoTerceirizado;

	private String indicadorFiscalizacaoInfracao;
	
	private String indicadorServicoOrdemSeletiva;
	
	private String indicadorEnvioPesquisaSatisfacao;
	
	private String indicadorInspecaoAnormalidade;
	
	private String indicadorProgramacaoAutomatica;

	private String indicadorVistoria;

	private String tempoMedioIncial;

	private String tempoMedioFinal;

	private String tempoMedioExecucao;

	private String idTipoDebito;

	private String descricaoTipoDebito;

	private String idTipoCredito;

	private String idPrioridadeServico;

	private String perfilServico;

	private String descricaoPerfilServico;

	private String idTipoServicoReferencia;

	private String descricaoTipoServicoReferencia;

	private String indicadorAtividadeUnica;

	private String indicadorUso;

	private String idAtividadeTipoServico;

	private String idAtividade;

	private String descricaoAtividadeTipoServico;

	private String idMaterialTipoServico;

	private String idMaterial;

	private String descricaoMaterialTipoServico;
	
	private String idMotivoEncerramento;
	
	private String descricaoMotivoEncerramento;

	private String servicoTipoMaterialQuantidadePadrao;

	private String indicadorAtualizar;

	private String servicoTipoAtividadeOrdemExecucao;

	private String valorServico;

	// private String tempoMedioExecusao;

	private String descricaoTipoCredito;

	private String method;

	String indicadorPermiteAlterarValor;

	Collection servicoTipoAtividades = new ArrayList();

	Collection servicoTipoMateriais = new ArrayList();
	
	Collection motivosEncerramento = new ArrayList();

	private String indicadorPavimentoRua;

	private String indicadorPavimentoCalcada;

	private String indicativoTipoSevicoEconomias;
	
	private String indicadorInformacoesBoletimMedicao;
	private String indicativoPavimento;
	private String indicativoReposicaoAsfalto;
	private String indicativoReposicaoParalelo;
	private String indicativoReposicaoCalcada;
	
	private String indicadorEmpresaCobranca;
	private String indicativoObrigatoriedadeAtividade;
	
	private String indicadorEncAutomaticoRAQndEncOS;
	
	private String indicativoObrigatoriedadeAtividadeValor;
	
	private String indicadorProgramacaoAutomaticaValor;
	
	private String indicadorCorrecaoAnormalidade;
	
	private String indicadorNovaEtapa;
	
	private String indicadorServicoCobranca;
	
	private String indicadorGerarResumo;
	
	private String constanteFuncionalidadeTipoServico;
	
	/* autor:Jonathan
	 * data:29/10/2013
	 * [UC0412] Manter Tipo de Servico
	 * [RM6643]
	 */
	private String indicadorTipoServicoMicromedicao;

	public void addServicoTipoAtividade() {
		Atividade atv;
		ServicoTipoAtividade stp;
		atv = new Atividade();
		atv.setId(Integer.parseInt(getIdAtividade()));
		atv.setDescricao(getDescricaoAtividadeTipoServico());
		stp = new ServicoTipoAtividade();
		stp.setNumeroExecucao(Short
				.parseShort(getServicoTipoAtividadeOrdemExecucao()));
		stp.setAtividade(atv);
		
		Boolean achou = false;
		if(!Util.isVazioOrNulo(servicoTipoAtividades)){
			
			Iterator it = servicoTipoAtividades.iterator();
			
			while(it.hasNext()){
				ServicoTipoAtividade ServTP = (ServicoTipoAtividade) it.next();
				if(ServTP.getNumeroExecucao().equals(stp.getNumeroExecucao())){
					achou = true;
				}
			}
		}else{
			servicoTipoAtividades = new ArrayList();
		}
		if(!achou){
			servicoTipoAtividades.add(stp);
		}
		
	}

	public void removeServicoTipoAtividade() {
		for (Iterator iter = getServicoTipoAtividades().iterator(); iter
				.hasNext();) {
			ServicoTipoAtividade stp = (ServicoTipoAtividade) iter.next();
			String string = "$" + stp.getAtividade().getId() + "$"
					+ stp.getNumeroExecucao() + "$";
			if (!string.equals(getIdAtividadeTipoServico())) {
				iter.remove();
			}
		}
	}

	public void removeAllServicoTipoAtividade() {
		if(!Util.isVazioOrNulo(getServicoTipoAtividades())){
			for (Iterator iter = getServicoTipoAtividades().iterator(); iter
					.hasNext();) {
				iter.next();
				iter.remove();
			}
		}
	}

	public void addServicoTipoMaterial() {
		Material mat;		
		ServicoTipoMaterial stm;
		mat = new Material();
		mat.setId(Integer.parseInt(getIdMaterial()));
		mat.setDescricao(getDescricaoMaterialTipoServico());
		stm = new ServicoTipoMaterial();
		Boolean verificar = false;
		try {
		if(!Util.isVazioOrNulo(servicoTipoMateriais)){
			
			Iterator it = servicoTipoMateriais.iterator();
			
			while(it.hasNext()){
				ServicoTipoMaterial serv = new ServicoTipoMaterial();
				serv = (ServicoTipoMaterial) it.next();
				if(serv.getMaterial().getId().equals(mat.getId()) 
						&& serv.getMaterial().getDescricao().equals(mat.getDescricao())){
					verificar = true;
				}
			}
			
		}else{
			servicoTipoMateriais = new ArrayList();
		}
			stm.setQuantidadePadrao(new BigDecimal(
					getServicoTipoMaterialQuantidadePadrao()));			
		
				stm.setMaterial(mat);
				if(!verificar){
					servicoTipoMateriais.add(stm);
					
					
				}
					
		}catch (NumberFormatException e) {
		}
	}

	public void removeServicoTipoMaterial() {
		for (Iterator iter = getServicoTipoMateriais().iterator(); iter
				.hasNext();) {
			ServicoTipoMaterial stm = (ServicoTipoMaterial) iter.next();
			String string = "$" + stm.getMaterial().getId() + "$";
			if (string.equals(getIdMaterialTipoServico())) {
				iter.remove();
			}
		}
	}
	
	public void addMotivosEncerramento(){
		AtendimentoMotivoEncerramento atd = new AtendimentoMotivoEncerramento();
		atd.setId(Integer.parseInt(getIdMotivoEncerramento()));
		atd.setDescricao(getDescricaoMotivoEncerramento());
	}
	
//	public void removerMotivosEncerramento(){
//		System.out.println("*********************");		
//		Iterator iter = getMotivosEncerramento().iterator();
//		while(iter.hasNext()){
//			AtendimentoMotivoEncerramento atd = (AtendimentoMotivoEncerramento) iter.next();
//			if(atd.getId() == Integer.parseInt(getIdMotivoEncerramento())){				
//				iter.remove();
//			}
//		}
//	}
	
	
	public String getDescricaoTipoCredito() {
		return descricaoTipoCredito;
	}

	public void setDescricaoTipoCredito(String descricaoTipoCredito) {
		this.descricaoTipoCredito = descricaoTipoCredito;
	}

	public ServicoPerfilTipo setFormValues(ServicoPerfilTipo servicoPerfilTipo) {

		/*
		 * Campos obrigat�rios
		 */

		// Descri��o
		/*
		 * Campos opcionais
		 */

		// data da retirada
		return servicoPerfilTipo;
	}

	public String getAbreviada() {
		return abreviada;
	}

	public void setAbreviada(String abreviada) {
		this.abreviada = abreviada;
	}

	public String getAtualizacaoComercial() {
		return atualizacaoComercial;
	}

	public void setAtualizacaoComercial(String atualizacaoComercial) {
		this.atualizacaoComercial = atualizacaoComercial;
	}

	public String getCodigoServico() {
		return codigoServico;
	}

	public void setCodigoServico(String codigoServico) {
		this.codigoServico = codigoServico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAtividadeTipoServico() {
		return descricaoAtividadeTipoServico;
	}

	public void setDescricaoAtividadeTipoServico(
			String descricaoAtividadeTipoServico) {
		this.descricaoAtividadeTipoServico = descricaoAtividadeTipoServico;
	}

	public String getDescricaoMaterialTipoServico() {
		return descricaoMaterialTipoServico;
	}

	public void setDescricaoMaterialTipoServico(
			String descricaoMaterialTipoServico) {
		this.descricaoMaterialTipoServico = descricaoMaterialTipoServico;
	}
	
	public String getDescricaoMotivoEncerramento() {
		return descricaoMotivoEncerramento;
	}

	public void setDescricaoMotivoEncerramento(String descricaoMotivoEncerramento) {
		this.descricaoMotivoEncerramento = descricaoMotivoEncerramento;
	}

	public String getDescricaoPerfilServico() {
		return descricaoPerfilServico;
	}

	public void setDescricaoPerfilServico(String descricaoPerfilServico) {
		this.descricaoPerfilServico = descricaoPerfilServico;
	}

	public String getDescricaoTipoDebito() {
		return descricaoTipoDebito;
	}

	public void setDescricaoTipoDebito(String descricaoTipoDebito) {
		this.descricaoTipoDebito = descricaoTipoDebito;
	}

	public String getIdAtividadeTipoServico() {
		return idAtividadeTipoServico;
	}

	public void setIdAtividadeTipoServico(String idAtividadeTipoServico) {
		this.idAtividadeTipoServico = idAtividadeTipoServico;
	}

	public String getIdMaterialTipoServico() {
		return idMaterialTipoServico;
	}

	public void setIdMaterialTipoServico(String idMaterialTipoServico) {
		this.idMaterialTipoServico = idMaterialTipoServico;
	}

	public String getIdPrioridadeServico() {
		return idPrioridadeServico;
	}

	public void setIdPrioridadeServico(String idPrioridadeServico) {
		this.idPrioridadeServico = idPrioridadeServico;
	}

	public String getIdTipoCredito() {
		return idTipoCredito;
	}

	public void setIdTipoCredito(String idTipoCredito) {
		this.idTipoCredito = idTipoCredito;
	}

	public String getIdTipoDebito() {
		return idTipoDebito;
	}

	public void setIdTipoDebito(String idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}

	public String getIdTipoServico() {
		return idTipoServico;
	}

	public void setIdTipoServico(String idTipoServico) {
		this.idTipoServico = idTipoServico;
	}

	public String getIdTipoServicoReferencia() {
		return idTipoServicoReferencia;
	}

	public void setIdTipoServicoReferencia(String idTipoServicoReferencia) {
		this.idTipoServicoReferencia = idTipoServicoReferencia;
	}

	public String getIndicadorAtividadeUnica() {
		return indicadorAtividadeUnica;
	}

	public void setIndicadorAtividadeUnica(String indicadorAtividadeUnica) {
		this.indicadorAtividadeUnica = indicadorAtividadeUnica;
	}

	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	public String getIndicadorFiscalizacaoInfracao() {
		return indicadorFiscalizacaoInfracao;
	}

	public void setIndicadorFiscalizacaoInfracao(
			String indicadorFiscalizacaoInfracao) {
		this.indicadorFiscalizacaoInfracao = indicadorFiscalizacaoInfracao;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getIndicadorVistoria() {
		return indicadorVistoria;
	}

	public void setIndicadorVistoria(String indicadorVistoria) {
		this.indicadorVistoria = indicadorVistoria;
	}

	/*
	 * public String getPavimento() { return pavimento; }
	 * 
	 * 
	 * public void setPavimento(String pavimento) { this.pavimento = pavimento; }
	 */

	public String getPerfilServico() {
		return perfilServico;
	}

	public void setPerfilServico(String perfilServico) {
		this.perfilServico = perfilServico;
	}

	public String getServicoTerceirizado() {
		return servicoTerceirizado;
	}

	public void setServicoTerceirizado(String servicoTerceirizado) {
		this.servicoTerceirizado = servicoTerceirizado;
	}

	public String getServicoTipoAtividadeOrdemExecucao() {
		return servicoTipoAtividadeOrdemExecucao;
	}

	public void setServicoTipoAtividadeOrdemExecucao(
			String servicoTipoAtividadeOrdemExecucao) {
		this.servicoTipoAtividadeOrdemExecucao = servicoTipoAtividadeOrdemExecucao;
	}

	public String getServicoTipoMaterialQuantidadePadrao() {
		return servicoTipoMaterialQuantidadePadrao;
	}

	public void setServicoTipoMaterialQuantidadePadrao(
			String servicoTipoMaterialQuantidadePadrao) {
		this.servicoTipoMaterialQuantidadePadrao = servicoTipoMaterialQuantidadePadrao;
	}

	public String getSubgrupo() {
		return subgrupo;
	}

	public void setSubgrupo(String subgrupo) {
		this.subgrupo = subgrupo;
	}

	public String getTempoMedioFinal() {
		return tempoMedioFinal;
	}

	public void setTempoMedioFinal(String tempoMedioFinal) {
		this.tempoMedioFinal = tempoMedioFinal;
	}

	public String getTempoMedioIncial() {
		return tempoMedioIncial;
	}

	public void setTempoMedioIncial(String tempoMedioIncial) {
		this.tempoMedioIncial = tempoMedioIncial;
	}

	public String getValorServico() {
		return valorServico;
	}

	public void setValorServico(String valorServico) {
		this.valorServico = valorServico;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(String idAtividade) {
		this.idAtividade = idAtividade;
	}

	public String getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(String idMaterial) {
		this.idMaterial = idMaterial;
	}
	
	public String getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}

	public void setIdMotivoEncerramento(String idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}

	public String getDescricaoTipoServicoReferencia() {
		return descricaoTipoServicoReferencia;
	}

	public void setDescricaoTipoServicoReferencia(
			String descricaoTipoServicoReferencia) {
		this.descricaoTipoServicoReferencia = descricaoTipoServicoReferencia;
	}

	public Collection getServicoTipoAtividades() {
		return servicoTipoAtividades;
	}

	public void setServicoTipoAtividades(Collection servicoTipoAtividades) {
		this.servicoTipoAtividades = servicoTipoAtividades;
	}

	public Collection getServicoTipoMateriais() {
		return servicoTipoMateriais;
	}

	public void setServicoTipoMateriais(Collection servicoTipoMateriais) {
		this.servicoTipoMateriais = servicoTipoMateriais;
	}
		
	public Collection getMotivosEncerramento() {
		return motivosEncerramento;
	}

	public void setMotivosEncerramento(Collection servicoMotivoEncerramento) {
		this.motivosEncerramento = servicoMotivoEncerramento;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTempoMedioExecucao() {
		return tempoMedioExecucao;
	}

	public void setTempoMedioExecucao(String tempoMedioExecucao) {
		this.tempoMedioExecucao = tempoMedioExecucao;
	}

	public String getIndicadorPavimentoCalcada() {
		return indicadorPavimentoCalcada;
	}

	public void setIndicadorPavimentoCalcada(String indicadorPavimentoCalcada) {
		this.indicadorPavimentoCalcada = indicadorPavimentoCalcada;
	}

	public String getIndicadorPavimentoRua() {
		return indicadorPavimentoRua;
	}

	public void setIndicadorPavimentoRua(String indicadorPavimentoRua) {
		this.indicadorPavimentoRua = indicadorPavimentoRua;
	}

	public String getIndicadorPermiteAlterarValor() {
		return indicadorPermiteAlterarValor;
	}

	public void setIndicadorPermiteAlterarValor(
			String indicadorPermiteAlterarValor) {
		this.indicadorPermiteAlterarValor = indicadorPermiteAlterarValor;
	}

	public String getIndicativoTipoSevicoEconomias() {
		return indicativoTipoSevicoEconomias;
	}

	public void setIndicativoTipoSevicoEconomias(
			String indicativoTipoSevicoEconomias) {
		this.indicativoTipoSevicoEconomias = indicativoTipoSevicoEconomias;
	}

	public String getIndicadorInformacoesBoletimMedicao() {
		return indicadorInformacoesBoletimMedicao;
	}

	public void setIndicadorInformacoesBoletimMedicao(
			String indicadorInformacoesBoletimMedicao) {
		this.indicadorInformacoesBoletimMedicao = indicadorInformacoesBoletimMedicao;
	}

	public String getIndicativoPavimento() {
		return indicativoPavimento;
	}

	public void setIndicativoPavimento(String indicativoPavimento) {
		this.indicativoPavimento = indicativoPavimento;
	}

	public String getIndicativoReposicaoAsfalto() {
		return indicativoReposicaoAsfalto;
	}

	public void setIndicativoReposicaoAsfalto(String indicativoReposicaoAsfalto) {
		this.indicativoReposicaoAsfalto = indicativoReposicaoAsfalto;
	}

	public String getIndicativoReposicaoCalcada() {
		return indicativoReposicaoCalcada;
	}

	public void setIndicativoReposicaoCalcada(String indicativoReposicaoCalcada) {
		this.indicativoReposicaoCalcada = indicativoReposicaoCalcada;
	}

	public String getIndicativoReposicaoParalelo() {
		return indicativoReposicaoParalelo;
	}

	public void setIndicativoReposicaoParalelo(String indicativoReposicaoParalelo) {
		this.indicativoReposicaoParalelo = indicativoReposicaoParalelo;
	}

	public String getIndicadorEmpresaCobranca() {
		return indicadorEmpresaCobranca;
	}

	public void setIndicadorEmpresaCobranca(String indicadorEmpresaCobranca) {
		this.indicadorEmpresaCobranca = indicadorEmpresaCobranca;
	}

	public String getIndicativoObrigatoriedadeAtividade() {
		return indicativoObrigatoriedadeAtividade;
	}

	public void setIndicativoObrigatoriedadeAtividade(
			String indicativoObrigatoriedadeAtividade) {
		this.indicativoObrigatoriedadeAtividade = indicativoObrigatoriedadeAtividade;
	}

	public String getIndicadorServicoOrdemSeletiva() {
		return indicadorServicoOrdemSeletiva;
	}

	public void setIndicadorServicoOrdemSeletiva(
			String indicadorServicoOrdemSeletiva) {
		this.indicadorServicoOrdemSeletiva = indicadorServicoOrdemSeletiva;
	}

	public String getIndicadorEnvioPesquisaSatisfacao() {
		return indicadorEnvioPesquisaSatisfacao;
	}

	public void setIndicadorEnvioPesquisaSatisfacao(
			String indicadorEnvioPesquisaSatisfacao) {
		this.indicadorEnvioPesquisaSatisfacao = indicadorEnvioPesquisaSatisfacao;
	}

	public String getIndicadorInspecaoAnormalidade() {
		return indicadorInspecaoAnormalidade;
	}

	public void setIndicadorInspecaoAnormalidade(
			String indicadorInspecaoAnormalidade) {
		this.indicadorInspecaoAnormalidade = indicadorInspecaoAnormalidade;
	}

	public String getIndicadorEncAutomaticoRAQndEncOS() {
		return indicadorEncAutomaticoRAQndEncOS;
	}

	public void setIndicadorEncAutomaticoRAQndEncOS(
			String indicadorEncAutomaticoRAQndEncOS) {
		this.indicadorEncAutomaticoRAQndEncOS = indicadorEncAutomaticoRAQndEncOS;
	}

	public String getIndicadorProgramacaoAutomatica() {
		return indicadorProgramacaoAutomatica;
	}

	public void setIndicadorProgramacaoAutomatica(
			String indicadorProgramacaoAutomatica) {
		this.indicadorProgramacaoAutomatica = indicadorProgramacaoAutomatica;
	}

	public String getIndicativoObrigatoriedadeAtividadeValor() {
		return indicativoObrigatoriedadeAtividadeValor;
	}

	public void setIndicativoObrigatoriedadeAtividadeValor(
			String indicativoObrigatoriedadeAtividadeValor) {
		this.indicativoObrigatoriedadeAtividadeValor = indicativoObrigatoriedadeAtividadeValor;
	}

	public String getIndicadorProgramacaoAutomaticaValor() {
		return indicadorProgramacaoAutomaticaValor;
	}

	public void setIndicadorProgramacaoAutomaticaValor(
			String indicadorProgramacaoAutomaticaValor) {
		this.indicadorProgramacaoAutomaticaValor = indicadorProgramacaoAutomaticaValor;
	}

	public String getIndicadorCorrecaoAnormalidade() {
		return indicadorCorrecaoAnormalidade;
	}

	public void setIndicadorCorrecaoAnormalidade(String indicadorCorrecaoAnormalidade) {
		this.indicadorCorrecaoAnormalidade = indicadorCorrecaoAnormalidade;
	}

	public String getIndicadorNovaEtapa() {
		return indicadorNovaEtapa;
	}

	public void setIndicadorNovaEtapa(String indicadorNovaEtapa) {
		this.indicadorNovaEtapa = indicadorNovaEtapa;
	}

	public String getIndicadorServicoCobranca() {
		return indicadorServicoCobranca;
	}

	public void setIndicadorServicoCobranca(String indicadorServicoCobranca) {
		this.indicadorServicoCobranca = indicadorServicoCobranca;
	}

	public String getIndicadorGerarResumo() {
		return indicadorGerarResumo;
	}

	public void setIndicadorGerarResumo(String indicadorGerarResumo) {
		this.indicadorGerarResumo = indicadorGerarResumo;
	}

	public String getConstanteFuncionalidadeTipoServico() {
		return constanteFuncionalidadeTipoServico;
	}

	public void setConstanteFuncionalidadeTipoServico(String constanteFuncionalidadeTipoServico) {
		this.constanteFuncionalidadeTipoServico = constanteFuncionalidadeTipoServico;
	}

	public String getIndicadorTipoServicoMicromedicao() {
		return indicadorTipoServicoMicromedicao;
	}

	public void setIndicadorTipoServicoMicromedicao(String indicadorTipoServicoMicromedicao) {
		this.indicadorTipoServicoMicromedicao = indicadorTipoServicoMicromedicao;
	}
	
}

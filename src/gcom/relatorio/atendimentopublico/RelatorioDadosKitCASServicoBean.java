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
package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;


public class RelatorioDadosKitCASServicoBean implements RelatorioBean, Comparable<RelatorioDadosKitCASServicoBean>, Cloneable {
	
	private static final long serialVersionUID = 1L;
	
	private String gerenciaRegionalID;
	private String gerenciaRegional;
	
	private String localidadeID;
	private String localidade;
	
	private String unidadeNegocioID;
	private String unidadeNegocio;
	
	private String municipioID;
	private String municipio;
	
	private String grupoDados;
	
	
	//Variáveis do Residencial
	private Integer hidSubstituidosRamalResidencial;
	private Integer hidSubstituidosPocoResidencial;
	private Integer hidInstaladosRamalResidencial;
	private Integer hidInstaladosPocoResidencial;
	private Integer cortesExecutadosResidencial;
	private Integer suprExecutadasResidencial;
	private Integer religacoesResidencial;
	private Integer reestabResidencial;
	private Integer clandCortadosResidencial;
	private Integer clandSuprimidosResidencial;
	private Integer contasEmitidasResidencial;
	private Integer leitEfetuadasResidencial;
	private Integer leitAnormalidadesResidencial;
	private Integer avisoCorteResidencial;
	private BigDecimal percAnormalidadeResidencial;
	private BigDecimal percHidrometracaoResidencial;
	private Integer ligImplantadasAguaResidencial;
	private Integer ligImplantadasEsgotoResidencial;
	private Integer raPendentesComPrazoResidencial;
	private Integer raPendentesComForaPrazoResidencial;
	private Integer raPendentesOpPrazoResidencial;
	private Integer raPendentesOpForaPrazoResidencial;
	private Integer faturaRevisaoResidencial;
	private Integer cartasNegativacaoResidencial;
	private Integer economiaAtivaResidencial;
	private Integer economiaInativaResidencial;
	private Integer ligacoesAtivasResidencial;
	private Integer ligacoesInativasResidencial;
	private Integer regAnormalidadeInformadaResidencial;
	private Integer regRecebidosResidencial;
	private Integer ligAtivasHidrometroResidencial;
	private Integer ligAtivasResidencial;	
	private Integer faturaRevisaoReclamacaoConsumoResidencial;
	private Integer faturaRevisaoRecorrenciaResidencial;
	private Integer faturaRevisaoFaturamentoIndevidoResidencial;	
	private Integer qtImoveisFaturaRevisaoReclamacaoConsumoResidencial;
	private Integer qtImoveisFaturaRevisaoRecorrenciaResidencial;
	private Integer qtImoveisFaturaRevisaoFaturamentoIndevidoResidencial;	
	private Integer alteracaoNumeroEconomiasAcrescidoResidencial;
	private Integer alteracaoNumeroEconomiasDecrescidoResidencial;
	private Integer alteracaoQuantidadeEconomiasAcrescidoResidencial;
	private Integer alteracaoQuantidadeEconomiasDecrescidoResidencial;
	private Integer alteracaoCategoriaResidencial;
	private Integer inclusaoTarifaSocialResidencial;
	private Integer exclusaoTarifaSocialResidencial;
	private Integer faturamentoSuspensoResidencial;
	
	//Variáveis do Comercial
	private Integer hidSubstituidosRamalComercial;
	private Integer hidSubstituidosPocoComercial;
	private Integer hidInstaladosRamalComercial;
	private Integer hidInstaladosPocoComercial;
	private Integer cortesExecutadosComercial;
	private Integer suprExecutadasComercial;
	private Integer religacoesComercial;
	private Integer reestabComercial;
	private Integer clandCortadosComercial;
	private Integer clandSuprimidosComercial;
	private Integer contasEmitidasComercial;
	private Integer leitEfetuadasComercial;
	private Integer leitAnormalidadesComercial;
	private Integer avisoCorteComercial;
	private BigDecimal percAnormalidadeComercial;
	private BigDecimal percHidrometracaoComercial;
	private Integer ligImplantadasAguaComercial;
	private Integer ligImplantadasEsgotoComercial;
	private Integer raPendentesComPrazoComercial;
	private Integer raPendentesComForaPrazoComercial;
	private Integer raPendentesOpPrazoComercial;
	private Integer raPendentesOpForaPrazoComercial;
	private Integer faturaRevisaoComercial;
	private Integer cartasNegativacaoComercial;
	private Integer economiaAtivaComercial;
	private Integer economiaInativaComercial;
	private Integer ligacoesAtivasComercial;
	private Integer ligacoesInativasComercial;
	private Integer regAnormalidadeInformadaComercial;
	private Integer regRecebidosComercial;
	private Integer ligAtivasHidrometroComercial;
	private Integer ligAtivasComercial;
	private Integer faturaRevisaoReclamacaoConsumoComercial;
	private Integer faturaRevisaoRecorrenciaComercial;
	private Integer faturaRevisaoFaturamentoIndevidoComercial;
	private Integer qtImoveisFaturaRevisaoReclamacaoConsumoComercial;
	private Integer qtImoveisFaturaRevisaoRecorrenciaComercial;
	private Integer qtImoveisFaturaRevisaoFaturamentoIndevidoComercial;
	private Integer alteracaoNumeroEconomiasAcrescidoComercial;
	private Integer alteracaoNumeroEconomiasDecrescidoComercial;
	private Integer alteracaoQuantidadeEconomiasAcrescidoComercial;
	private Integer alteracaoQuantidadeEconomiasDecrescidoComercial;
	private Integer alteracaoCategoriaComercial;
	private Integer inclusaoTarifaSocialComercial;
	private Integer exclusaoTarifaSocialComercial;
	private Integer faturamentoSuspensoComercial;
	
	//Variáveis do Industrial
	private Integer hidSubstituidosRamalIndustrial;
	private Integer hidSubstituidosPocoIndustrial;
	private Integer hidInstaladosRamalIndustrial;
	private Integer hidInstaladosPocoIndustrial;
	private Integer cortesExecutadosIndustrial;
	private Integer suprExecutadasIndustrial;
	private Integer religacoesIndustrial;
	private Integer reestabIndustrial;
	private Integer clandCortadosIndustrial;
	private Integer clandSuprimidosIndustrial;
	private Integer contasEmitidasIndustrial;
	private Integer leitEfetuadasIndustrial;
	private Integer leitAnormalidadesIndustrial;
	private Integer avisoCorteIndustrial;
	private BigDecimal percAnormalidadeIndustrial;
	private BigDecimal percHidrometracaoIndustrial;
	private Integer ligImplantadasAguaIndustrial;
	private Integer ligImplantadasEsgotoIndustrial;
	private Integer raPendentesComPrazoIndustrial;
	private Integer raPendentesComForaPrazoIndustrial;
	private Integer raPendentesOpPrazoIndustrial;
	private Integer raPendentesOpForaPrazoIndustrial;
	private Integer faturaRevisaoIndustrial;
	private Integer cartasNegativacaoIndustrial;
	private Integer economiaAtivaIndustrial;
	private Integer economiaInativaIndustrial;
	private Integer ligacoesAtivasIndustrial;
	private Integer ligacoesInativasIndustrial;
	private Integer regAnormalidadeInformadaIndustrial;
	private Integer regRecebidosIndustrial;
	private Integer ligAtivasHidrometroIndustrial;
	private Integer ligAtivasIndustrial;
	private Integer faturaRevisaoReclamacaoConsumoIndustrial;
	private Integer faturaRevisaoRecorrenciaIndustrial;
	private Integer faturaRevisaoFaturamentoIndevidoIndustrial;
	private Integer qtImoveisFaturaRevisaoReclamacaoConsumoIndustrial;
	private Integer qtImoveisFaturaRevisaoRecorrenciaIndustrial;
	private Integer qtImoveisFaturaRevisaoFaturamentoIndevidoIndustrial;
	private Integer alteracaoNumeroEconomiasAcrescidoIndustrial;
	private Integer alteracaoNumeroEconomiasDecrescidoIndustrial;
	private Integer alteracaoQuantidadeEconomiasAcrescidoIndustrial;
	private Integer alteracaoQuantidadeEconomiasDecrescidoIndustrial;
	private Integer alteracaoCategoriaIndustrial;
	private Integer inclusaoTarifaSocialIndustrial;
	private Integer exclusaoTarifaSocialIndustrial;
	private Integer faturamentoSuspensoIndustrial;
	
	//Variáveis do Público
	private Integer hidSubstituidosRamalPublico;
	private Integer hidSubstituidosPocoPublico;
	private Integer hidInstaladosRamalPublico;
	private Integer hidInstaladosPocoPublico;
	private Integer cortesExecutadosPublico;
	private Integer suprExecutadasPublico;
	private Integer religacoesPublico;
	private Integer reestabPublico;
	private Integer clandCortadosPublico;
	private Integer clandSuprimidosPublico;
	private Integer contasEmitidasPublico;
	private Integer leitEfetuadasPublico;
	private Integer leitAnormalidadesPublico;
	private Integer avisoCortePublico;
	private BigDecimal percAnormalidadePublico;
	private BigDecimal percHidrometracaoPublico;
	private Integer ligImplantadasAguaPublico;
	private Integer ligImplantadasEsgotoPublico;
	private Integer raPendentesComPrazoPublico;
	private Integer raPendentesComForaPrazoPublico;
	private Integer raPendentesOpPrazoPublico;
	private Integer raPendentesOpForaPrazoPublico;
	private Integer faturaRevisaoPublico;
	private Integer cartasNegativacaoPublico;
	private Integer economiaAtivaPublico;
	private Integer economiaInativaPublico;
	private Integer ligacoesAtivasPublico;
	private Integer ligacoesInativasPublico;
	private Integer regAnormalidadeInformadaPublico;
	private Integer regRecebidosPublico;
	private Integer ligAtivasHidrometroPublico;
	private Integer ligAtivasPublico;
	private Integer faturaRevisaoReclamacaoConsumoPublico;
	private Integer faturaRevisaoRecorrenciaPublico;
	private Integer faturaRevisaoFaturamentoIndevidoPublico;
	private Integer qtImoveisFaturaRevisaoReclamacaoConsumoPublico;
	private Integer qtImoveisFaturaRevisaoRecorrenciaPublico;
	private Integer qtImoveisFaturaRevisaoFaturamentoIndevidoPublico;
	private Integer alteracaoNumeroEconomiasAcrescidoPublico;
	private Integer alteracaoNumeroEconomiasDecrescidoPublico;
	private Integer alteracaoQuantidadeEconomiasAcrescidoPublico;
	private Integer alteracaoQuantidadeEconomiasDecrescidoPublico;
	private Integer alteracaoCategoriaPublico;
	private Integer inclusaoTarifaSocialPublico;
	private Integer exclusaoTarifaSocialPublico;
	private Integer faturamentoSuspensoPublico;
	
	
	
	//Perfil Normal
	private Integer hidSubstituidosRamalNormal;
	private Integer hidSubstituidosPocoNormal;
	private Integer hidInstaladosRamalNormal;
	private Integer hidInstaladosPocoNormal;
	private Integer cortesExecutadosNormal;
	private Integer suprExecutadasNormal;
	private Integer religacoesNormal;
	private Integer reestabNormal;
	private Integer clandCortadosNormal;
	private Integer clandSuprimidosNormal;
	private Integer contasEmitidasNormal;
	private Integer leitEfetuadasNormal;
	private Integer leitAnormalidadesNormal;
	private Integer avisoCorteNormal;
	private BigDecimal percAnormalidadeNormal;
	private BigDecimal percHidrometracaoNormal;
	private Integer ligImplantadasAguaNormal;
	private Integer ligImplantadasEsgotoNormal;
	private Integer raPendentesComPrazoNormal;
	private Integer raPendentesComForaPrazoNormal;
	private Integer raPendentesOpPrazoNormal;
	private Integer raPendentesOpForaPrazoNormal;
	private Integer faturaRevisaoNormal;
	private Integer cartasNegativacaoNormal;
	private Integer economiaAtivaNormal;
	private Integer economiaInativaNormal;
	private Integer ligacoesAtivasNormal;
	private Integer ligacoesInativasNormal;
	private Integer regAnormalidadeInformadaNormal;
	private Integer regRecebidosNormal;
	private Integer ligAtivasHidrometroNormal;
	private Integer ligAtivasNormal;
	private Integer faturaRevisaoReclamacaoConsumoNormal;
	private Integer faturaRevisaoRecorrenciaNormal;
	private Integer faturaRevisaoFaturamentoIndevidoNormal;
	private Integer qtImoveisFaturaRevisaoReclamacaoConsumoNormal;
	private Integer qtImoveisFaturaRevisaoRecorrenciaNormal;
	private Integer qtImoveisFaturaRevisaoFaturamentoIndevidoNormal;
	private Integer alteracaoNumeroEconomiasAcrescidoNormal;
	private Integer alteracaoNumeroEconomiasDecrescidoNormal;
	private Integer alteracaoQuantidadeEconomiasAcrescidoNormal;
	private Integer alteracaoQuantidadeEconomiasDecrescidoNormal;
	private Integer alteracaoCategoriaNormal;
	private Integer inclusaoTarifaSocialNormal;
	private Integer exclusaoTarifaSocialNormal;
	private Integer faturamentoSuspensoNormal;
	
		
	//Perfil Tarifa Social
	private Integer hidSubstituidosRamalTarSocial;
	private Integer hidSubstituidosPocoTarSocial;
	private Integer hidInstaladosRamalTarSocial;
	private Integer hidInstaladosPocoTarSocial;
	private Integer cortesExecutadosTarSocial;
	private Integer suprExecutadasTarSocial;
	private Integer religacoesTarSocial;
	private Integer reestabTarSocial;
	private Integer clandCortadosTarSocial;
	private Integer clandSuprimidosTarSocial;
	private Integer contasEmitidasTarSocial;
	private Integer leitEfetuadasTarSocial;
	private Integer leitAnormalidadesTarSocial;
	private Integer avisoCorteTarSocial;
	private BigDecimal percAnormalidadeTarSocial;
	private BigDecimal percHidrometracaoTarSocial;
	private Integer ligImplantadasAguaTarSocial;
	private Integer ligImplantadasEsgotoTarSocial;
	private Integer raPendentesComPrazoTarSocial;
	private Integer raPendentesComForaPrazoTarSocial;
	private Integer raPendentesOpPrazoTarSocial;
	private Integer raPendentesOpForaPrazoTarSocial;
	private Integer faturaRevisaoTarSocial;
	private Integer cartasNegativacaoTarSocial;
	private Integer economiaAtivaTarSocial;
	private Integer economiaInativaTarSocial;
	private Integer ligacoesAtivasTarSocial;
	private Integer ligacoesInativasTarSocial;
	private Integer regAnormalidadeInformadaTarSocial;
	private Integer regRecebidosTarSocial;
	private Integer ligAtivasHidrometroTarSocial;
	private Integer ligAtivasTarSocial;
	private Integer faturaRevisaoReclamacaoConsumoTarSocial;
	private Integer faturaRevisaoRecorrenciaTarSocial;
	private Integer faturaRevisaoFaturamentoIndevidoTarSocial;
	private Integer qtImoveisFaturaRevisaoReclamacaoConsumoTarSocial;
	private Integer qtImoveisFaturaRevisaoRecorrenciaTarSocial;
	private Integer qtImoveisFaturaRevisaoFaturamentoIndevidoTarSocial;
	private Integer alteracaoNumeroEconomiasAcrescidoTarSocial;
	private Integer alteracaoNumeroEconomiasDecrescidoTarSocial;
	private Integer alteracaoQuantidadeEconomiasAcrescidoTarSocial;
	private Integer alteracaoQuantidadeEconomiasDecrescidoTarSocial;
	private Integer alteracaoCategoriaTarSocial;
	private Integer inclusaoTarifaSocialTarSocial;
	private Integer exclusaoTarifaSocialTarSocial;
	private Integer faturamentoSuspensoTarSocial;
	
	
	//Perfil Grande
	private Integer hidSubstituidosRamalGrande;
	private Integer hidSubstituidosPocoGrande;
	private Integer hidInstaladosRamalGrande;
	private Integer hidInstaladosPocoGrande;
	private Integer cortesExecutadosGrande;
	private Integer suprExecutadasGrande;
	private Integer religacoesGrande;
	private Integer reestabGrande;
	private Integer clandCortadosGrande;
	private Integer clandSuprimidosGrande;
	private Integer contasEmitidasGrande;
	private Integer leitEfetuadasGrande;
	private Integer leitAnormalidadesGrande;
	private Integer avisoCorteGrande;
	private BigDecimal percAnormalidadeGrande;
	private BigDecimal percHidrometracaoGrande;
	private Integer ligImplantadasAguaGrande;
	private Integer ligImplantadasEsgotoGrande;
	private Integer raPendentesComPrazoGrande;
	private Integer raPendentesComForaPrazoGrande;
	private Integer raPendentesOpPrazoGrande;
	private Integer raPendentesOpForaPrazoGrande;
	private Integer faturaRevisaoGrande;
	private Integer cartasNegativacaoGrande;
	private Integer economiaAtivaGrande;
	private Integer economiaInativaGrande;
	private Integer ligacoesAtivasGrande;
	private Integer ligacoesInativasGrande;
	private Integer regAnormalidadeInformadaGrande;
	private Integer regRecebidosGrande;
	private Integer ligAtivasHidrometroGrande;
	private Integer ligAtivasGrande;
	private Integer faturaRevisaoReclamacaoConsumoGrande;
	private Integer faturaRevisaoRecorrenciaGrande;
	private Integer faturaRevisaoFaturamentoIndevidoGrande;
	private Integer qtImoveisFaturaRevisaoReclamacaoConsumoGrande;
	private Integer qtImoveisFaturaRevisaoRecorrenciaGrande;
	private Integer qtImoveisFaturaRevisaoFaturamentoIndevidoGrande;
	private Integer alteracaoNumeroEconomiasAcrescidoGrande;
	private Integer alteracaoNumeroEconomiasDecrescidoGrande;
	private Integer alteracaoQuantidadeEconomiasAcrescidoGrande;
	private Integer alteracaoQuantidadeEconomiasDecrescidoGrande;
	private Integer alteracaoCategoriaGrande;
	private Integer inclusaoTarifaSocialGrande;
	private Integer exclusaoTarifaSocialGrande;
	private Integer faturamentoSuspensoGrande;

	
	
	//Perfil Corporativo
	private Integer hidSubstituidosRamalCorporativo;
	private Integer hidSubstituidosPocoCorporativo;
	private Integer hidInstaladosRamalCorporativo;
	private Integer hidInstaladosPocoCorporativo;
	private Integer cortesExecutadosCorporativo;
	private Integer suprExecutadasCorporativo;
	private Integer religacoesCorporativo;
	private Integer reestabCorporativo;
	private Integer clandCortadosCorporativo;
	private Integer clandSuprimidosCorporativo;
	private Integer contasEmitidasCorporativo;
	private Integer leitEfetuadasCorporativo;
	private Integer leitAnormalidadesCorporativo;
	private Integer avisoCorteCorporativo;
	private BigDecimal percAnormalidadeCorporativo;
	private BigDecimal percHidrometracaoCorporativo;
	private Integer ligImplantadasAguaCorporativo;
	private Integer ligImplantadasEsgotoCorporativo;
	private Integer raPendentesComPrazoCorporativo;
	private Integer raPendentesComForaPrazoCorporativo;
	private Integer raPendentesOpPrazoCorporativo;
	private Integer raPendentesOpForaPrazoCorporativo;
	private Integer faturaRevisaoCorporativo;
	private Integer cartasNegativacaoCorporativo;
	private Integer economiaAtivaCorporativo;
	private Integer economiaInativaCorporativo;
	private Integer ligacoesAtivasCorporativo;
	private Integer ligacoesInativasCorporativo;
	private Integer regAnormalidadeInformadaCorporativo;
	private Integer regRecebidosCorporativo;
	private Integer ligAtivasHidrometroCorporativo;
	private Integer ligAtivasCorporativo;
	private Integer faturaRevisaoReclamacaoConsumoCorporativo;
	private Integer faturaRevisaoRecorrenciaCorporativo;
	private Integer faturaRevisaoFaturamentoIndevidoCorporativo;
	private Integer qtImoveisFaturaRevisaoReclamacaoConsumoCorporativo;
	private Integer qtImoveisFaturaRevisaoRecorrenciaCorporativo;
	private Integer qtImoveisFaturaRevisaoFaturamentoIndevidoCorporativo;
	private Integer alteracaoNumeroEconomiasAcrescidoCorporativo;
	private Integer alteracaoNumeroEconomiasDecrescidoCorporativo;
	private Integer alteracaoQuantidadeEconomiasAcrescidoCorporativo;
	private Integer alteracaoQuantidadeEconomiasDecrescidoCorporativo;
	private Integer alteracaoCategoriaCorporativo;
	private Integer inclusaoTarifaSocialCorporativo;
	private Integer exclusaoTarifaSocialCorporativo;
	private Integer faturamentoSuspensoCorporativo;

	
	
	//Perfil Grande Telemedido
	private Integer hidSubstituidosRamalGrandeTelemedido;
	private Integer hidSubstituidosPocoGrandeTelemedido;
	private Integer hidInstaladosRamalGrandeTelemedido;
	private Integer hidInstaladosPocoGrandeTelemedido;
	private Integer cortesExecutadosGrandeTelemedido;
	private Integer suprExecutadasGrandeTelemedido;
	private Integer religacoesGrandeTelemedido;
	private Integer reestabGrandeTelemedido;
	private Integer clandCortadosGrandeTelemedido;
	private Integer clandSuprimidosGrandeTelemedido;
	private Integer contasEmitidasGrandeTelemedido;
	private Integer leitEfetuadasGrandeTelemedido;
	private Integer leitAnormalidadesGrandeTelemedido;
	private Integer avisoCorteGrandeTelemedido;
	private BigDecimal percAnormalidadeGrandeTelemedido;
	private BigDecimal percHidrometracaoGrandeTelemedido;
	private Integer ligImplantadasAguaGrandeTelemedido;
	private Integer ligImplantadasEsgotoGrandeTelemedido;
	private Integer raPendentesComPrazoGrandeTelemedido;
	private Integer raPendentesComForaPrazoGrandeTelemedido;
	private Integer raPendentesOpPrazoGrandeTelemedido;
	private Integer raPendentesOpForaPrazoGrandeTelemedido;
	private Integer faturaRevisaoGrandeTelemedido;
	private Integer cartasNegativacaoGrandeTelemedido;
	private Integer economiaAtivaGrandeTelemedido;
	private Integer economiaInativaGrandeTelemedido;
	private Integer ligacoesAtivasGrandeTelemedido;
	private Integer ligacoesInativasGrandeTelemedido;
	private Integer regAnormalidadeInformadaGrandeTelemedido;
	private Integer regRecebidosGrandeTelemedido;
	private Integer ligAtivasHidrometroGrandeTelemedido;
	private Integer ligAtivasGrandeTelemedido;
	private Integer faturaRevisaoReclamacaoConsumoGrandeTelemedido;
	private Integer faturaRevisaoRecorrenciaGrandeTelemedido;
	private Integer faturaRevisaoFaturamentoIndevidoGrandeTelemedido;
	private Integer qtImoveisFaturaRevisaoReclamacaoConsumoGrandeTelemedido;
	private Integer qtImoveisFaturaRevisaoRecorrenciaGrandeTelemedido;
	private Integer qtImoveisFaturaRevisaoFaturamentoIndevidoGrandeTelemedido;
	private Integer alteracaoNumeroEconomiasAcrescidoGrandeTelemedido;
	private Integer alteracaoNumeroEconomiasDecrescidoGrandeTelemedido;
	private Integer alteracaoQuantidadeEconomiasAcrescidoGrandeTelemedido;
	private Integer alteracaoQuantidadeEconomiasDecrescidoGrandeTelemedido;
	private Integer alteracaoCategoriaGrandeTelemedido;
	private Integer inclusaoTarifaSocialGrandeTelemedido;
	private Integer exclusaoTarifaSocialGrandeTelemedido;
	private Integer faturamentoSuspensoGrandeTelemedido;

	

	//Perfil Corporativo Telemedido
	private Integer hidSubstituidosRamalCorpTelemedido;
	private Integer hidSubstituidosPocoCorpTelemedido;
	private Integer hidInstaladosRamalCorpTelemedido;
	private Integer hidInstaladosPocoCorpTelemedido;
	private Integer cortesExecutadosCorpTelemedido;
	private Integer suprExecutadasCorpTelemedido;
	private Integer religacoesCorpTelemedido;
	private Integer reestabCorpTelemedido;
	private Integer clandCortadosCorpTelemedido;
	private Integer clandSuprimidosCorpTelemedido;
	private Integer contasEmitidasCorpTelemedido;
	private Integer leitEfetuadasCorpTelemedido;
	private Integer leitAnormalidadesCorpTelemedido;
	private Integer avisoCorteCorpTelemedido;
	private BigDecimal percAnormalidadeCorpTelemedido;
	private BigDecimal percHidrometracaoCorpTelemedido;
	private Integer ligImplantadasAguaCorpTelemedido;
	private Integer ligImplantadasEsgotoCorpTelemedido;
	private Integer raPendentesComPrazoCorpTelemedido;
	private Integer raPendentesComForaPrazoCorpTelemedido;
	private Integer raPendentesOpPrazoCorpTelemedido;
	private Integer raPendentesOpForaPrazoCorpTelemedido;
	private Integer faturaRevisaoCorpTelemedido;
	private Integer cartasNegativacaoCorpTelemedido;
	private Integer economiaAtivaCorpTelemedido;
	private Integer economiaInativaCorpTelemedido;
	private Integer ligacoesAtivasCorpTelemedido;
	private Integer ligacoesInativasCorpTelemedido;
	private Integer regAnormalidadeInformadaCorpTelemedido;
	private Integer regRecebidosCorpTelemedido;
	private Integer ligAtivasHidrometroCorpTelemedido;
	private Integer ligAtivasCorpTelemedido;
	private Integer faturaRevisaoReclamacaoConsumoCorpTelemedido;
	private Integer faturaRevisaoRecorrenciaCorpTelemedido;
	private Integer faturaRevisaoFaturamentoIndevidoCorpTelemedido;
	private Integer qtImoveisFaturaRevisaoReclamacaoConsumoCorpTelemedido;
	private Integer qtImoveisFaturaRevisaoRecorrenciaCorpTelemedido;
	private Integer qtImoveisFaturaRevisaoFaturamentoIndevidoCorpTelemedido;
	private Integer alteracaoNumeroEconomiasAcrescidoCorpTelemedido;
	private Integer alteracaoNumeroEconomiasDecrescidoCorpTelemedido;
	private Integer alteracaoQuantidadeEconomiasAcrescidoCorpTelemedido;
	private Integer alteracaoQuantidadeEconomiasDecrescidoCorpTelemedido;
	private Integer alteracaoCategoriaCorpTelemedido;
	private Integer inclusaoTarifaSocialCorpTelemedido;
	private Integer exclusaoTarifaSocialCorpTelemedido;
	private Integer faturamentoSuspensoCorpTelemedido;
	
	
	//Chafariz
	private Integer hidSubstituidosRamalChafariz;
	private Integer hidSubstituidosPocoChafariz;
	private Integer hidInstaladosRamalChafariz;
	private Integer hidInstaladosPocoChafariz;
	private Integer cortesExecutadosChafariz;
	private Integer suprExecutadasChafariz;
	private Integer religacoesChafariz;
	private Integer reestabChafariz;
	private Integer clandCortadosChafariz;
	private Integer clandSuprimidosChafariz;
	private Integer contasEmitidasChafariz;
	private Integer leitEfetuadasChafariz;
	private Integer leitAnormalidadesChafariz;
	private Integer avisoCorteChafariz;
	private BigDecimal percAnormalidadeChafariz;
	private BigDecimal percHidrometracaoChafariz;
	private Integer ligImplantadasAguaChafariz;
	private Integer ligImplantadasEsgotoChafariz;
	private Integer raPendentesComPrazoChafariz;
	private Integer raPendentesComForaPrazoChafariz;
	private Integer raPendentesOpPrazoChafariz;
	private Integer raPendentesOpForaPrazoChafariz;
	private Integer faturaRevisaoChafariz;
	private Integer cartasNegativacaoChafariz;
	private Integer economiaAtivaChafariz;
	private Integer economiaInativaChafariz;
	private Integer ligacoesAtivasChafariz;
	private Integer ligacoesInativasChafariz;
	private Integer regAnormalidadeInformadaChafariz;
	private Integer regRecebidosChafariz;
	private Integer ligAtivasHidrometroChafariz;
	private Integer ligAtivasChafariz;
	private Integer faturaRevisaoReclamacaoConsumoChafariz;
	private Integer faturaRevisaoRecorrenciaChafariz;
	private Integer faturaRevisaoFaturamentoIndevidoChafariz;
	private Integer qtImoveisFaturaRevisaoReclamacaoConsumoChafariz;
	private Integer qtImoveisFaturaRevisaoRecorrenciaChafariz;
	private Integer qtImoveisFaturaRevisaoFaturamentoIndevidoChafariz;
	private Integer alteracaoNumeroEconomiasAcrescidoChafariz;
	private Integer alteracaoNumeroEconomiasDecrescidoChafariz;
	private Integer alteracaoQuantidadeEconomiasAcrescidoChafariz;
	private Integer alteracaoQuantidadeEconomiasDecrescidoChafariz;
	private Integer alteracaoCategoriaChafariz;
	private Integer inclusaoTarifaSocialChafariz;
	private Integer exclusaoTarifaSocialChafariz;
	private Integer faturamentoSuspensoChafariz;
	
	
	//Micro Telemedido
	private Integer hidSubstituidosRamalMicroTelemedido;
	private Integer hidSubstituidosPocoMicroTelemedido;
	private Integer hidInstaladosRamalMicroTelemedido;
	private Integer hidInstaladosPocoMicroTelemedido;
	private Integer cortesExecutadosMicroTelemedido;
	private Integer suprExecutadasMicroTelemedido;
	private Integer religacoesMicroTelemedido;
	private Integer reestabMicroTelemedido;
	private Integer clandCortadosMicroTelemedido;
	private Integer clandSuprimidosMicroTelemedido;
	private Integer contasEmitidasMicroTelemedido;
	private Integer leitEfetuadasMicroTelemedido;
	private Integer leitAnormalidadesMicroTelemedido;
	private Integer avisoCorteMicroTelemedido;
	private BigDecimal percAnormalidadeMicroTelemedido;
	private BigDecimal percHidrometracaoMicroTelemedido;
	private Integer ligImplantadasAguaMicroTelemedido;
	private Integer ligImplantadasEsgotoMicroTelemedido;
	private Integer raPendentesComPrazoMicroTelemedido;
	private Integer raPendentesComForaPrazoMicroTelemedido;
	private Integer raPendentesOpPrazoMicroTelemedido;
	private Integer raPendentesOpForaPrazoMicroTelemedido;
	private Integer faturaRevisaoMicroTelemedido;
	private Integer cartasNegativacaoMicroTelemedido;
	private Integer economiaAtivaMicroTelemedido;
	private Integer economiaInativaMicroTelemedido;
	private Integer ligacoesAtivasMicroTelemedido;
	private Integer ligacoesInativasMicroTelemedido;
	private Integer regAnormalidadeInformadaMicroTelemedido;
	private Integer regRecebidosMicroTelemedido;
	private Integer ligAtivasHidrometroMicroTelemedido;
	private Integer ligAtivasMicroTelemedido;
	private Integer faturaRevisaoReclamacaoConsumoMicroTelemedido;
	private Integer faturaRevisaoRecorrenciaMicroTelemedido;
	private Integer faturaRevisaoFaturamentoIndevidoMicroTelemedido;
	private Integer qtImoveisFaturaRevisaoReclamacaoConsumoMicroTelemedido;
	private Integer qtImoveisFaturaRevisaoRecorrenciaMicroTelemedido;
	private Integer qtImoveisFaturaRevisaoFaturamentoIndevidoMicroTelemedido;
	private Integer alteracaoNumeroEconomiasAcrescidoMicroTelemedido;
	private Integer alteracaoNumeroEconomiasDecrescidoMicroTelemedido;
	private Integer alteracaoQuantidadeEconomiasAcrescidoMicroTelemedido;
	private Integer alteracaoQuantidadeEconomiasDecrescidoMicroTelemedido;
	private Integer alteracaoCategoriaMicroTelemedido;
	private Integer inclusaoTarifaSocialMicroTelemedido;
	private Integer exclusaoTarifaSocialMicroTelemedido;
	private Integer faturamentoSuspensoMicroTelemedido;
	
	//Cadastro Provisorio
	private Integer hidSubstituidosRamalCadastroProvisorio;
	private Integer hidSubstituidosPocoCadastroProvisorio;
	private Integer hidInstaladosRamalCadastroProvisorio;
	private Integer hidInstaladosPocoCadastroProvisorio;
	private Integer cortesExecutadosCadastroProvisorio;
	private Integer suprExecutadasCadastroProvisorio;
	private Integer religacoesCadastroProvisorio;
	private Integer reestabCadastroProvisorio;
	private Integer clandCortadosCadastroProvisorio;
	private Integer clandSuprimidosCadastroProvisorio;
	private Integer contasEmitidasCadastroProvisorio;
	private Integer leitEfetuadasCadastroProvisorio;
	private Integer leitAnormalidadesCadastroProvisorio;
	private Integer avisoCorteCadastroProvisorio;
	private BigDecimal percAnormalidadeCadastroProvisorio;
	private BigDecimal percHidrometracaoCadastroProvisorio;
	private Integer ligImplantadasAguaCadastroProvisorio;
	private Integer ligImplantadasEsgotoCadastroProvisorio;
	private Integer raPendentesComPrazoCadastroProvisorio;
	private Integer raPendentesComForaPrazoCadastroProvisorio;
	private Integer raPendentesOpPrazoCadastroProvisorio;
	private Integer raPendentesOpForaPrazoCadastroProvisorio;
	private Integer faturaRevisaoCadastroProvisorio;
	private Integer cartasNegativacaoCadastroProvisorio;
	private Integer economiaAtivaCadastroProvisorio;
	private Integer economiaInativaCadastroProvisorio;
	private Integer ligacoesAtivasCadastroProvisorio;
	private Integer ligacoesInativasCadastroProvisorio;
	private Integer regAnormalidadeInformadaCadastroProvisorio;
	private Integer regRecebidosCadastroProvisorio;
	private Integer ligAtivasHidrometroCadastroProvisorio;
	private Integer ligAtivasCadastroProvisorio;
	private Integer faturaRevisaoReclamacaoConsumoCadastroProvisorio;
	private Integer faturaRevisaoRecorrenciaCadastroProvisorio;
	private Integer faturaRevisaoFaturamentoIndevidoCadastroProvisorio;
	private Integer qtImoveisFaturaRevisaoReclamacaoConsumoCadastroProvisorio;
	private Integer qtImoveisFaturaRevisaoRecorrenciaCadastroProvisorio;
	private Integer qtImoveisFaturaRevisaoFaturamentoIndevidoCadastroProvisorio;
	private Integer alteracaoNumeroEconomiasAcrescidoCadastroProvisorio;
	private Integer alteracaoNumeroEconomiasDecrescidoCadastroProvisorio;
	private Integer alteracaoQuantidadeEconomiasAcrescidoCadastroProvisorio;
	private Integer alteracaoQuantidadeEconomiasDecrescidoCadastroProvisorio;
	private Integer alteracaoCategoriaCadastroProvisorio;
	private Integer inclusaoTarifaSocialCadastroProvisorio;
	private Integer exclusaoTarifaSocialCadastroProvisorio;
	private Integer faturamentoSuspensoCadastroProvisorio;
	

	
	//Totais de cada caMicroTelemedidoate Integer hidSubstituidosRamalTotalPerfil;
		private Integer hidSubstituidosPocoTotalPerfil;
		private Integer hidInstaladosRamalTotalPerfil;
		private Integer hidInstaladosPocoTotalPerfil;
		private Integer cortesExecutadosTotalPerfil;
		private Integer suprExecutadasTotalPerfil;
		private Integer religacoesTotalPerfil;
		private Integer reestabTotalPerfil;
		private Integer clandCortadosTotalPerfil;
		private Integer clandSuprimidosTotalPerfil;
		private Integer contasEmitidasTotalPerfil;
		private Integer leitEfetuadasTotalPerfil;
		private Integer leitAnormalidadesTotalPerfil;
		private Integer avisoCorteTotalPerfil;
		private BigDecimal percAnormalidadeTotalPerfil;
		private BigDecimal percHidrometracaoTotalPerfil;
		private Integer ligImplantadasAguaTotalPerfil;
		private Integer ligImplantadasEsgotoTotalPerfil;
		private Integer raPendentesComPrazoTotalPerfil;
		private Integer raPendentesComForaPrazoTotalPerfil;
		private Integer raPendentesOpPrazoTotalPerfil;
		private Integer raPendentesOpForaPrazoTotalPerfil;
		private Integer faturaRevisaoTotalPerfil;
		private Integer cartasNegativacaoTotalPerfil;
		private Integer economiaAtivaTotalPerfil;
		private Integer economiaInativaTotalPerfil;
		private Integer ligacoesAtivasTotalPerfil;
		private Integer ligacoesInativasTotalPerfil;
		private Integer regAnormalidadeInformadaTotalPerfil;
		private Integer regRecebidosTotalPerfil;
		private Integer ligAtivasHidrometroTotalPerfil;
		private Integer ligAtivasTotalPerfil;
		private Integer faturaRevisaoReclamacaoConsumoTotalPerfil;
		private Integer faturaRevisaoRecorrenciaTotalPerfil;
		private Integer faturaRevisaoFaturamentoIndevidoTotalPerfil;
		private Integer qtImoveisFaturaRevisaoReclamacaoConsumoTotalPerfil;
		private Integer qtImoveisFaturaRevisaoRecorrenciaTotalPerfil;
		private Integer qtImoveisFaturaRevisaoFaturamentoIndevidoTotalPerfil;
		private Integer alteracaoNumeroEconomiasAcrescidoTotalPerfil;
		private Integer alteracaoNumeroEconomiasDecrescidoTotalPerfil;
		private Integer alteracaoQuantidadeEconomiasAcrescidoTotalPerfil;
		private Integer alteracaoQuantidadeEconomiasDecrescidoTotalPerfil;
		private Integer alteracaoCategoriaTotalPerfil;
		private Integer inclusaoTarifaSocialTotalPerfil;
		private Integer exclusaoTarifaSocialTotalPerfil;
		private Integer faturamentoSuspensoTotalPerfil;
		
		
		private Integer hidSubstituidosRamalTotalCategoria;
		private Integer hidSubstituidosPocoTotalCategoria;
		private Integer hidInstaladosRamalTotalCategoria;
		private Integer hidInstaladosPocoTotalCategoria;
		private Integer cortesExecutadosTotalCategoria;
		private Integer suprExecutadasTotalCategoria;
		private Integer religacoesTotalCategoria;
		private Integer reestabTotalCategoria;
		private Integer clandCortadosTotalCategoria;
		private Integer clandSuprimidosTotalCategoria;
		private Integer contasEmitidasTotalCategoria;
		private Integer leitEfetuadasTotalCategoria;
		private Integer leitAnormalidadesTotalCategoria;
		private Integer avisoCorteTotalCategoria;
		private BigDecimal percAnormalidadeTotalCategoria;
		private BigDecimal percHidrometracaoTotalCategoria;
		private Integer ligImplantadasAguaTotalCategoria;
		private Integer ligImplantadasEsgotoTotalCategoria;
		private Integer raPendentesComPrazoTotalCategoria;
		private Integer raPendentesComForaPrazoTotalCategoria;
		private Integer raPendentesOpPrazoTotalCategoria;
		private Integer raPendentesOpForaPrazoTotalCategoria;
		private Integer faturaRevisaoTotalCategoria;
		private Integer cartasNegativacaoTotalCategoria;
		private Integer economiaAtivaTotalCategoria;
		private Integer economiaInativaTotalCategoria;
		private Integer ligacoesAtivasTotalCategoria;
		private Integer ligacoesInativasTotalCategoria;
		private Integer regAnormalidadeInformadaTotalCategoria;
		private Integer regRecebidosTotalCategoria;
		private Integer ligAtivasHidrometroTotalCategoria;
		private Integer ligAtivasTotalCategoria;
		private Integer faturaRevisaoReclamacaoConsumoTotalCategoria;
		private Integer faturaRevisaoRecorrenciaTotalCategoria;
		private Integer faturaRevisaoFaturamentoIndevidoTotalCategoria;
		private Integer qtImoveisFaturaRevisaoReclamacaoConsumoTotalCategoria;
		private Integer qtImoveisFaturaRevisaoRecorrenciaTotalCategoria;
		private Integer qtImoveisFaturaRevisaoFaturamentoIndevidoTotalCategoria;
		private Integer alteracaoNumeroEconomiasAcrescidoTotalCategoria;
		private Integer alteracaoNumeroEconomiasDecrescidoTotalCategoria;
		private Integer alteracaoQuantidadeEconomiasAcrescidoTotalCategoria;
		private Integer alteracaoQuantidadeEconomiasDecrescidoTotalCategoria;
		private Integer alteracaoCategoriaTotalCategoria;
		private Integer inclusaoTarifaSocialTotalCategoria;
		private Integer exclusaoTarifaSocialTotalCategoria;
		private Integer faturamentoSuspensoTotalCategoria;
	
	public RelatorioDadosKitCASServicoBean() {
		
			this.gerenciaRegionalID = "0";
			this.gerenciaRegional = "0";
			this.localidadeID = "0";
			this.localidade = "0";
			this.unidadeNegocioID = "0";
			this.unidadeNegocio = "0";
			this.municipioID = "0";
			this.municipio = "0";
			this.grupoDados = "";
			this.hidSubstituidosRamalResidencial = new Integer(0);
			this.hidSubstituidosPocoResidencial = new Integer(0);
			this.hidInstaladosRamalResidencial = new Integer(0);
			this.hidInstaladosPocoResidencial = new Integer(0);
			this.cortesExecutadosResidencial = new Integer(0);
			this.suprExecutadasResidencial = new Integer(0);
			this.religacoesResidencial = new Integer(0);
			this.reestabResidencial = new Integer(0);
			this.clandCortadosResidencial = new Integer(0);
			this.clandSuprimidosResidencial = new Integer(0);
			this.contasEmitidasResidencial = new Integer(0);
			this.leitEfetuadasResidencial = new Integer(0);
			this.leitAnormalidadesResidencial = new Integer(0);
			this.avisoCorteResidencial = new Integer(0);
			this.percAnormalidadeResidencial = new BigDecimal(0);
			this.percHidrometracaoResidencial = new BigDecimal(0);
			this.ligImplantadasAguaResidencial = new Integer(0);
			this.ligImplantadasEsgotoResidencial = new Integer(0);
			this.raPendentesComPrazoResidencial = new Integer(0);
			this.raPendentesComForaPrazoResidencial = new Integer(0);
			this.raPendentesOpPrazoResidencial = new Integer(0);
			this.raPendentesOpForaPrazoResidencial = new Integer(0);
			this.faturaRevisaoResidencial = new Integer(0);
			this.cartasNegativacaoResidencial = new Integer(0);
			this.economiaAtivaResidencial = new Integer(0);
			this.economiaInativaResidencial = new Integer(0);
			this.ligacoesAtivasResidencial = new Integer(0);
			this.ligacoesInativasResidencial = new Integer(0);
			this.hidSubstituidosRamalComercial = new Integer(0);
			this.hidSubstituidosPocoComercial = new Integer(0);
			this.hidInstaladosRamalComercial = new Integer(0);
			this.hidInstaladosPocoComercial = new Integer(0);
			this.cortesExecutadosComercial = new Integer(0);
			this.suprExecutadasComercial = new Integer(0);
			this.religacoesComercial = new Integer(0);
			this.reestabComercial = new Integer(0);
			this.clandCortadosComercial = new Integer(0);
			this.clandSuprimidosComercial = new Integer(0);
			this.contasEmitidasComercial = new Integer(0);
			this.leitEfetuadasComercial = new Integer(0);
			this.leitAnormalidadesComercial = new Integer(0);
			this.avisoCorteComercial = new Integer(0);
			this.percAnormalidadeComercial = new BigDecimal(0);
			this.percHidrometracaoComercial = new BigDecimal(0);
			this.ligImplantadasAguaComercial = new Integer(0);
			this.ligImplantadasEsgotoComercial = new Integer(0);
			this.raPendentesComPrazoComercial = new Integer(0);
			this.raPendentesComForaPrazoComercial = new Integer(0);
			this.raPendentesOpPrazoComercial = new Integer(0);
			this.raPendentesOpForaPrazoComercial = new Integer(0);
			this.faturaRevisaoComercial = new Integer(0);
			this.cartasNegativacaoComercial = new Integer(0);
			this.economiaAtivaComercial = new Integer(0);
			this.economiaInativaComercial = new Integer(0);
			this.ligacoesAtivasComercial = new Integer(0);
			this.ligacoesInativasComercial = new Integer(0);
			this.hidSubstituidosRamalIndustrial = new Integer(0);
			this.hidSubstituidosPocoIndustrial = new Integer(0);
			this.hidInstaladosRamalIndustrial = new Integer(0);
			this.hidInstaladosPocoIndustrial = new Integer(0);
			this.cortesExecutadosIndustrial = new Integer(0);
			this.suprExecutadasIndustrial = new Integer(0);
			this.religacoesIndustrial = new Integer(0);
			this.reestabIndustrial = new Integer(0);
			this.clandCortadosIndustrial = new Integer(0);
			this.clandSuprimidosIndustrial = new Integer(0);
			this.contasEmitidasIndustrial = new Integer(0);
			this.leitEfetuadasIndustrial = new Integer(0);
			this.leitAnormalidadesIndustrial = new Integer(0);
			this.avisoCorteIndustrial = new Integer(0);
			this.percAnormalidadeIndustrial = new BigDecimal(0);
			this.percHidrometracaoIndustrial = new BigDecimal(0);
			this.ligImplantadasAguaIndustrial = new Integer(0);
			this.ligImplantadasEsgotoIndustrial = new Integer(0);
			this.raPendentesComPrazoIndustrial = new Integer(0);
			this.raPendentesComForaPrazoIndustrial = new Integer(0);
			this.raPendentesOpPrazoIndustrial = new Integer(0);
			this.raPendentesOpForaPrazoIndustrial = new Integer(0);
			this.faturaRevisaoIndustrial = new Integer(0);
			this.cartasNegativacaoIndustrial = new Integer(0);
			this.economiaAtivaIndustrial = new Integer(0);
			this.economiaInativaIndustrial = new Integer(0);
			this.ligacoesAtivasIndustrial = new Integer(0);
			this.ligacoesInativasIndustrial = new Integer(0);
			this.hidSubstituidosRamalPublico = new Integer(0);
			this.hidSubstituidosPocoPublico = new Integer(0);
			this.hidInstaladosRamalPublico = new Integer(0);
			this.hidInstaladosPocoPublico = new Integer(0);
			this.cortesExecutadosPublico = new Integer(0);
			this.suprExecutadasPublico = new Integer(0);
			this.religacoesPublico = new Integer(0);
			this.reestabPublico = new Integer(0);
			this.clandCortadosPublico = new Integer(0);
			this.clandSuprimidosPublico = new Integer(0);
			this.contasEmitidasPublico = new Integer(0);
			this.leitEfetuadasPublico = new Integer(0);
			this.leitAnormalidadesPublico = new Integer(0);
			this.avisoCortePublico = new Integer(0);
			this.percAnormalidadePublico = new BigDecimal(0);
			this.percHidrometracaoPublico = new BigDecimal(0);
			this.ligImplantadasAguaPublico = new Integer(0);
			this.ligImplantadasEsgotoPublico = new Integer(0);
			this.raPendentesComPrazoPublico = new Integer(0);
			this.raPendentesComForaPrazoPublico = new Integer(0);
			this.raPendentesOpPrazoPublico = new Integer(0);
			this.raPendentesOpForaPrazoPublico = new Integer(0);
			this.faturaRevisaoPublico = new Integer(0);
			this.cartasNegativacaoPublico = new Integer(0);
			this.economiaAtivaPublico = new Integer(0);
			this.economiaInativaPublico = new Integer(0);
			this.ligacoesAtivasPublico = new Integer(0);
			this.ligacoesInativasPublico = new Integer(0);
			this.hidSubstituidosRamalNormal = new Integer(0);
			this.hidSubstituidosPocoNormal = new Integer(0);
			this.hidInstaladosRamalNormal = new Integer(0);
			this.hidInstaladosPocoNormal = new Integer(0);
			this.cortesExecutadosNormal = new Integer(0);
			this.suprExecutadasNormal = new Integer(0);
			this.religacoesNormal = new Integer(0);
			this.reestabNormal = new Integer(0);
			this.clandCortadosNormal = new Integer(0);
			this.clandSuprimidosNormal = new Integer(0);
			this.contasEmitidasNormal = new Integer(0);
			this.leitEfetuadasNormal = new Integer(0);
			this.leitAnormalidadesNormal = new Integer(0);
			this.avisoCorteNormal = new Integer(0);
			this.percAnormalidadeNormal = new BigDecimal(0);
			this.percHidrometracaoNormal = new BigDecimal(0);
			this.ligImplantadasAguaNormal = new Integer(0);
			this.ligImplantadasEsgotoNormal = new Integer(0);
			this.raPendentesComPrazoNormal = new Integer(0);
			this.raPendentesComForaPrazoNormal = new Integer(0);
			this.raPendentesOpPrazoNormal = new Integer(0);
			this.raPendentesOpForaPrazoNormal = new Integer(0);
			this.faturaRevisaoNormal = new Integer(0);
			this.cartasNegativacaoNormal = new Integer(0);
			this.economiaAtivaNormal = new Integer(0);
			this.economiaInativaNormal = new Integer(0);
			this.ligacoesAtivasNormal = new Integer(0);
			this.ligacoesInativasNormal = new Integer(0);
			this.hidSubstituidosRamalTarSocial = new Integer(0);
			this.hidSubstituidosPocoTarSocial = new Integer(0);
			this.hidInstaladosRamalTarSocial = new Integer(0);
			this.hidInstaladosPocoTarSocial = new Integer(0);
			this.cortesExecutadosTarSocial = new Integer(0);
			this.suprExecutadasTarSocial = new Integer(0);
			this.religacoesTarSocial = new Integer(0);
			this.reestabTarSocial = new Integer(0);
			this.clandCortadosTarSocial = new Integer(0);
			this.clandSuprimidosTarSocial = new Integer(0);
			this.contasEmitidasTarSocial = new Integer(0);
			this.leitEfetuadasTarSocial = new Integer(0);
			this.leitAnormalidadesTarSocial = new Integer(0);
			this.avisoCorteTarSocial = new Integer(0);
			this.percAnormalidadeTarSocial = new BigDecimal(0);
			this.percHidrometracaoTarSocial = new BigDecimal(0);
			this.ligImplantadasAguaTarSocial = new Integer(0);
			this.ligImplantadasEsgotoTarSocial = new Integer(0);
			this.raPendentesComPrazoTarSocial = new Integer(0);
			this.raPendentesComForaPrazoTarSocial = new Integer(0);
			this.raPendentesOpPrazoTarSocial = new Integer(0);
			this.raPendentesOpForaPrazoTarSocial = new Integer(0);
			this.faturaRevisaoTarSocial = new Integer(0);
			this.cartasNegativacaoTarSocial = new Integer(0);
			this.economiaAtivaTarSocial = new Integer(0);
			this.economiaInativaTarSocial = new Integer(0);
			this.ligacoesAtivasTarSocial = new Integer(0);
			this.ligacoesInativasTarSocial = new Integer(0);
			this.hidSubstituidosRamalGrande = new Integer(0);
			this.hidSubstituidosPocoGrande = new Integer(0);
			this.hidInstaladosRamalGrande = new Integer(0);
			this.hidInstaladosPocoGrande = new Integer(0);
			this.cortesExecutadosGrande = new Integer(0);
			this.suprExecutadasGrande = new Integer(0);
			this.religacoesGrande = new Integer(0);
			this.reestabGrande = new Integer(0);
			this.clandCortadosGrande = new Integer(0);
			this.clandSuprimidosGrande = new Integer(0);
			this.contasEmitidasGrande = new Integer(0);
			this.leitEfetuadasGrande = new Integer(0);
			this.leitAnormalidadesGrande = new Integer(0);
			this.avisoCorteGrande = new Integer(0);
			this.percAnormalidadeGrande = new BigDecimal(0);
			this.percHidrometracaoGrande = new BigDecimal(0);
			this.ligImplantadasAguaGrande = new Integer(0);
			this.ligImplantadasEsgotoGrande = new Integer(0);
			this.raPendentesComPrazoGrande = new Integer(0);
			this.raPendentesComForaPrazoGrande = new Integer(0);
			this.raPendentesOpPrazoGrande = new Integer(0);
			this.raPendentesOpForaPrazoGrande = new Integer(0);
			this.faturaRevisaoGrande = new Integer(0);
			this.cartasNegativacaoGrande = new Integer(0);
			this.economiaAtivaGrande = new Integer(0);
			this.economiaInativaGrande = new Integer(0);
			this.ligacoesAtivasGrande = new Integer(0);
			this.ligacoesInativasGrande = new Integer(0);
			this.hidSubstituidosRamalCorporativo = new Integer(0);
			this.hidSubstituidosPocoCorporativo = new Integer(0);
			this.hidInstaladosRamalCorporativo = new Integer(0);
			this.hidInstaladosPocoCorporativo = new Integer(0);
			this.cortesExecutadosCorporativo = new Integer(0);
			this.suprExecutadasCorporativo = new Integer(0);
			this.religacoesCorporativo = new Integer(0);
			this.reestabCorporativo = new Integer(0);
			this.clandCortadosCorporativo = new Integer(0);
			this.clandSuprimidosCorporativo = new Integer(0);
			this.contasEmitidasCorporativo = new Integer(0);
			this.leitEfetuadasCorporativo = new Integer(0);
			this.leitAnormalidadesCorporativo = new Integer(0);
			this.avisoCorteCorporativo = new Integer(0);
			this.percAnormalidadeCorporativo = new BigDecimal(0);
			this.percHidrometracaoCorporativo = new BigDecimal(0);
			this.ligImplantadasAguaCorporativo = new Integer(0);
			this.ligImplantadasEsgotoCorporativo = new Integer(0);
			this.raPendentesComPrazoCorporativo = new Integer(0);
			this.raPendentesComForaPrazoCorporativo = new Integer(0);
			this.raPendentesOpPrazoCorporativo = new Integer(0);
			this.raPendentesOpForaPrazoCorporativo = new Integer(0);
			this.faturaRevisaoCorporativo = new Integer(0);
			this.cartasNegativacaoCorporativo = new Integer(0);
			this.economiaAtivaCorporativo = new Integer(0);
			this.economiaInativaCorporativo = new Integer(0);
			this.ligacoesAtivasCorporativo = new Integer(0);
			this.ligacoesInativasCorporativo = new Integer(0);
			this.hidSubstituidosRamalGrandeTelemedido = new Integer(0);
			this.hidSubstituidosPocoGrandeTelemedido = new Integer(0);
			this.hidInstaladosRamalGrandeTelemedido = new Integer(0);
			this.hidInstaladosPocoGrandeTelemedido = new Integer(0);
			this.cortesExecutadosGrandeTelemedido = new Integer(0);
			this.suprExecutadasGrandeTelemedido = new Integer(0);
			this.religacoesGrandeTelemedido = new Integer(0);
			this.reestabGrandeTelemedido = new Integer(0);
			this.clandCortadosGrandeTelemedido = new Integer(0);
			this.clandSuprimidosGrandeTelemedido = new Integer(0);
			this.contasEmitidasGrandeTelemedido = new Integer(0);
			this.leitEfetuadasGrandeTelemedido = new Integer(0);
			this.leitAnormalidadesGrandeTelemedido = new Integer(0);
			this.avisoCorteGrandeTelemedido = new Integer(0);
			this.percAnormalidadeGrandeTelemedido = new BigDecimal(0);
			this.percHidrometracaoGrandeTelemedido = new BigDecimal(0);
			this.ligImplantadasAguaGrandeTelemedido = new Integer(0);
			this.ligImplantadasEsgotoGrandeTelemedido = new Integer(0);
			this.raPendentesComPrazoGrandeTelemedido = new Integer(0);
			this.raPendentesComForaPrazoGrandeTelemedido = new Integer(0);
			this.raPendentesOpPrazoGrandeTelemedido = new Integer(0);
			this.raPendentesOpForaPrazoGrandeTelemedido = new Integer(0);
			this.faturaRevisaoGrandeTelemedido = new Integer(0);
			this.cartasNegativacaoGrandeTelemedido = new Integer(0);
			this.economiaAtivaGrandeTelemedido = new Integer(0);
			this.economiaInativaGrandeTelemedido = new Integer(0);
			this.ligacoesAtivasGrandeTelemedido = new Integer(0);
			this.ligacoesInativasGrandeTelemedido = new Integer(0);
			this.hidSubstituidosRamalCorpTelemedido = new Integer(0);
			this.hidSubstituidosPocoCorpTelemedido = new Integer(0);
			this.hidInstaladosRamalCorpTelemedido = new Integer(0);
			this.hidInstaladosPocoCorpTelemedido = new Integer(0);
			this.cortesExecutadosCorpTelemedido = new Integer(0);
			this.suprExecutadasCorpTelemedido = new Integer(0);
			this.religacoesCorpTelemedido = new Integer(0);
			this.reestabCorpTelemedido = new Integer(0);
			this.clandCortadosCorpTelemedido = new Integer(0);
			this.clandSuprimidosCorpTelemedido = new Integer(0);
			this.contasEmitidasCorpTelemedido = new Integer(0);
			this.leitEfetuadasCorpTelemedido = new Integer(0);
			this.leitAnormalidadesCorpTelemedido = new Integer(0);
			this.avisoCorteCorpTelemedido = new Integer(0);
			this.percAnormalidadeCorpTelemedido = new BigDecimal(0);
			this.percHidrometracaoCorpTelemedido = new BigDecimal(0);
			this.ligImplantadasAguaCorpTelemedido = new Integer(0);
			this.ligImplantadasEsgotoCorpTelemedido = new Integer(0);
			this.raPendentesComPrazoCorpTelemedido = new Integer(0);
			this.raPendentesComForaPrazoCorpTelemedido = new Integer(0);
			this.raPendentesOpPrazoCorpTelemedido = new Integer(0);
			this.raPendentesOpForaPrazoCorpTelemedido = new Integer(0);
			this.faturaRevisaoCorpTelemedido = new Integer(0);
			this.cartasNegativacaoCorpTelemedido = new Integer(0);
			this.economiaAtivaCorpTelemedido = new Integer(0);
			this.economiaInativaCorpTelemedido = new Integer(0);
			this.ligacoesAtivasCorpTelemedido = new Integer(0);
			this.ligacoesInativasCorpTelemedido = new Integer(0);
			
			this.cortesExecutadosTotalPerfil = new Integer(0);
			this.suprExecutadasTotalPerfil = new Integer(0);
			this.religacoesTotalPerfil = new Integer(0);
			this.reestabTotalPerfil = new Integer(0);
			this.clandCortadosTotalPerfil = new Integer(0);
			this.clandSuprimidosTotalPerfil = new Integer(0);
			this.contasEmitidasTotalPerfil = new Integer(0);
			this.leitEfetuadasTotalPerfil = new Integer(0);
			this.leitAnormalidadesTotalPerfil = new Integer(0);
			this.avisoCorteTotalPerfil = new Integer(0);
			this.percAnormalidadeTotalPerfil = new BigDecimal(0);
			this.percHidrometracaoTotalPerfil = new BigDecimal(0);
			this.ligImplantadasAguaTotalPerfil = new Integer(0);
			this.ligImplantadasEsgotoTotalPerfil = new Integer(0);
			this.raPendentesComPrazoTotalPerfil = new Integer(0);
			this.raPendentesComForaPrazoTotalPerfil = new Integer(0);
			this.raPendentesOpPrazoTotalPerfil = new Integer(0);
			this.raPendentesOpForaPrazoTotalPerfil = new Integer(0);
			this.faturaRevisaoTotalPerfil = new Integer(0);
			this.cartasNegativacaoTotalPerfil = new Integer(0);
			this.economiaAtivaTotalPerfil = new Integer(0);
			this.economiaInativaTotalPerfil = new Integer(0);
			this.ligacoesAtivasTotalPerfil = new Integer(0);
			this.ligacoesInativasTotalPerfil = new Integer(0);
			this.hidSubstituidosRamalTotalCategoria = new Integer(0);
			this.hidSubstituidosPocoTotalCategoria = new Integer(0);
			this.hidInstaladosRamalTotalCategoria = new Integer(0);
			this.hidInstaladosPocoTotalCategoria = new Integer(0);
			this.cortesExecutadosTotalCategoria = new Integer(0);
			this.suprExecutadasTotalCategoria = new Integer(0);
			this.religacoesTotalCategoria = new Integer(0);
			this.reestabTotalCategoria = new Integer(0);
			this.clandCortadosTotalCategoria = new Integer(0);
			this.clandSuprimidosTotalCategoria = new Integer(0);
			this.contasEmitidasTotalCategoria = new Integer(0);
			this.leitEfetuadasTotalCategoria = new Integer(0);
			this.leitAnormalidadesTotalCategoria = new Integer(0);
			this.avisoCorteTotalCategoria = new Integer(0);
			this.percAnormalidadeTotalCategoria = new BigDecimal(0);
			this.percHidrometracaoTotalCategoria = new BigDecimal(0);
			this.ligImplantadasAguaTotalCategoria = new Integer(0);
			this.ligImplantadasEsgotoTotalCategoria = new Integer(0);
			this.raPendentesComPrazoTotalCategoria = new Integer(0);
			this.raPendentesComForaPrazoTotalCategoria = new Integer(0);
			this.raPendentesOpPrazoTotalCategoria = new Integer(0);
			this.raPendentesOpForaPrazoTotalCategoria = new Integer(0);
			this.faturaRevisaoTotalCategoria = new Integer(0);
			this.cartasNegativacaoTotalCategoria = new Integer(0);
			this.economiaAtivaTotalCategoria = new Integer(0);
			this.economiaInativaTotalCategoria = new Integer(0);
			this.ligacoesAtivasTotalCategoria = new Integer(0);
			this.ligacoesInativasTotalCategoria = new Integer(0);
			this.regAnormalidadeInformadaResidencial = new Integer(0);
			this.regRecebidosResidencial = new Integer(0);
			this.regAnormalidadeInformadaIndustrial = new Integer(0);
			this.regRecebidosIndustrial = new Integer(0);
			this.regAnormalidadeInformadaComercial = new Integer(0);
			this.regRecebidosComercial = new Integer(0);
			this.regAnormalidadeInformadaPublico = new Integer(0);
			this.regRecebidosPublico = new Integer(0);
			this.regAnormalidadeInformadaNormal = new Integer(0);
			this.regRecebidosNormal = new Integer(0);
			this.regAnormalidadeInformadaTarSocial = new Integer(0);
			this.regRecebidosTarSocial = new Integer(0);
			this.regAnormalidadeInformadaGrande = new Integer(0);
			this.regRecebidosGrande = new Integer(0);
			this.regAnormalidadeInformadaCorporativo = new Integer(0);
			this.regRecebidosCorporativo = new Integer(0);
			this.regAnormalidadeInformadaGrandeTelemedido = new Integer(0);
			this.regRecebidosGrandeTelemedido = new Integer(0);
			this.regAnormalidadeInformadaCorpTelemedido = new Integer(0);
			this.regRecebidosCorpTelemedido = new Integer(0);
			this.regAnormalidadeInformadaTotalPerfil = new Integer(0);
			this.regRecebidosTotalCategoria = new Integer(0);
			this.ligAtivasHidrometroResidencial = new Integer(0);
			this.ligAtivasResidencial = new Integer(0);
			this.ligAtivasHidrometroComercial = new Integer(0);
			this.ligAtivasComercial = new Integer(0);
			this.ligAtivasHidrometroIndustrial = new Integer(0);
			this.ligAtivasIndustrial = new Integer(0);
			this.ligAtivasHidrometroPublico = new Integer(0);
			this.ligAtivasPublico = new Integer(0);
			this.ligAtivasHidrometroNormal = new Integer(0);
			this.ligAtivasNormal = new Integer(0);
			this.ligAtivasHidrometroTarSocial = new Integer(0);
			this.ligAtivasTarSocial = new Integer(0);
			this.ligAtivasHidrometroGrande = new Integer(0);
			this.ligAtivasGrande = new Integer(0);
			this.ligAtivasHidrometroCorporativo = new Integer(0);
			this.ligAtivasCorporativo = new Integer(0);
			this.ligAtivasHidrometroGrandeTelemedido = new Integer(0);
			this.ligAtivasGrandeTelemedido = new Integer(0);
			this.ligAtivasHidrometroCorpTelemedido = new Integer(0);
			this.ligAtivasCorpTelemedido = new Integer(0);
			this.ligAtivasHidrometroTotalPerfil = new Integer(0);
			this.ligAtivasTotalPerfil = new Integer(0);
			this.ligAtivasHidrometroTotalCategoria = new Integer(0);
			this.ligAtivasTotalCategoria = new Integer(0);
			
			this.faturaRevisaoReclamacaoConsumoResidencial = new Integer(0);
			this.faturaRevisaoRecorrenciaResidencial = new Integer(0);
			this.faturaRevisaoFaturamentoIndevidoResidencial = new Integer(0);
			this.faturaRevisaoReclamacaoConsumoComercial = new Integer(0);
			this.faturaRevisaoRecorrenciaComercial = new Integer(0);
			this.faturaRevisaoFaturamentoIndevidoComercial = new Integer(0);
			this.faturaRevisaoReclamacaoConsumoIndustrial = new Integer(0);
			this.faturaRevisaoRecorrenciaIndustrial = new Integer(0);
			this.faturaRevisaoFaturamentoIndevidoIndustrial = new Integer(0);
			this.faturaRevisaoReclamacaoConsumoPublico = new Integer(0);
			this.faturaRevisaoRecorrenciaPublico = new Integer(0);
			this.faturaRevisaoFaturamentoIndevidoPublico = new Integer(0);
			this.faturaRevisaoReclamacaoConsumoNormal = new Integer(0);
			this.faturaRevisaoRecorrenciaNormal = new Integer(0);
			this.faturaRevisaoFaturamentoIndevidoNormal = new Integer(0);			
			this.faturaRevisaoReclamacaoConsumoTarSocial = new Integer(0);
			this.faturaRevisaoRecorrenciaTarSocial = new Integer(0);
			this.faturaRevisaoFaturamentoIndevidoTarSocial = new Integer(0);
			this.faturaRevisaoReclamacaoConsumoGrande = new Integer(0);
			this.faturaRevisaoRecorrenciaGrande = new Integer(0);
			this.faturaRevisaoFaturamentoIndevidoGrande = new Integer(0);
			this.faturaRevisaoReclamacaoConsumoCorporativo = new Integer(0);
			this.faturaRevisaoRecorrenciaCorporativo = new Integer(0);
			this.faturaRevisaoFaturamentoIndevidoCorporativo = new Integer(0);
			this.faturaRevisaoReclamacaoConsumoGrandeTelemedido = new Integer(0);
			this.faturaRevisaoRecorrenciaGrandeTelemedido = new Integer(0);
			this.faturaRevisaoFaturamentoIndevidoGrandeTelemedido = new Integer(0);
			this.faturaRevisaoReclamacaoConsumoCorpTelemedido = new Integer(0);
			this.faturaRevisaoRecorrenciaCorpTelemedido = new Integer(0);
			this.faturaRevisaoFaturamentoIndevidoCorpTelemedido = new Integer(0);
			
			this.qtImoveisFaturaRevisaoReclamacaoConsumoResidencial = new Integer(0);
			this.qtImoveisFaturaRevisaoRecorrenciaResidencial = new Integer(0);
			this.qtImoveisFaturaRevisaoFaturamentoIndevidoResidencial = new Integer(0);
			this.qtImoveisFaturaRevisaoReclamacaoConsumoComercial = new Integer(0);
			this.qtImoveisFaturaRevisaoRecorrenciaComercial = new Integer(0);
			this.qtImoveisFaturaRevisaoFaturamentoIndevidoComercial = new Integer(0);
			this.qtImoveisFaturaRevisaoReclamacaoConsumoIndustrial = new Integer(0);
			this.qtImoveisFaturaRevisaoRecorrenciaIndustrial = new Integer(0);
			this.qtImoveisFaturaRevisaoFaturamentoIndevidoIndustrial = new Integer(0);
			this.qtImoveisFaturaRevisaoReclamacaoConsumoPublico  = new Integer(0);
			this.qtImoveisFaturaRevisaoRecorrenciaPublico  = new Integer(0);
			this.qtImoveisFaturaRevisaoFaturamentoIndevidoPublico  = new Integer(0);
			this.qtImoveisFaturaRevisaoReclamacaoConsumoNormal = new Integer(0);
			this.qtImoveisFaturaRevisaoRecorrenciaNormal = new Integer(0);
			this.qtImoveisFaturaRevisaoFaturamentoIndevidoNormal = new Integer(0);
			this.qtImoveisFaturaRevisaoReclamacaoConsumoTarSocial = new Integer(0);
			this.qtImoveisFaturaRevisaoRecorrenciaTarSocial = new Integer(0);
			this.qtImoveisFaturaRevisaoFaturamentoIndevidoTarSocial = new Integer(0);
			this.qtImoveisFaturaRevisaoReclamacaoConsumoGrande = new Integer(0);
			this.qtImoveisFaturaRevisaoRecorrenciaGrande = new Integer(0);
			this.qtImoveisFaturaRevisaoFaturamentoIndevidoGrande = new Integer(0);
			this.qtImoveisFaturaRevisaoReclamacaoConsumoCorporativo = new Integer(0);
			this.qtImoveisFaturaRevisaoRecorrenciaCorporativo = new Integer(0);
			this.qtImoveisFaturaRevisaoFaturamentoIndevidoCorporativo = new Integer(0);
			this.qtImoveisFaturaRevisaoReclamacaoConsumoGrandeTelemedido = new Integer(0);
			this.qtImoveisFaturaRevisaoRecorrenciaGrandeTelemedido = new Integer(0);
			this.qtImoveisFaturaRevisaoFaturamentoIndevidoGrandeTelemedido = new Integer(0);
			this.qtImoveisFaturaRevisaoReclamacaoConsumoCorpTelemedido = new Integer(0);
			this.qtImoveisFaturaRevisaoRecorrenciaCorpTelemedido = new Integer(0);
			this.qtImoveisFaturaRevisaoFaturamentoIndevidoCorpTelemedido = new Integer(0);

			this.hidSubstituidosRamalChafariz = new Integer(0);
			this.hidSubstituidosPocoChafariz  = new Integer(0);
			this.hidInstaladosRamalChafariz = new Integer(0);
			this.hidInstaladosPocoChafariz = new Integer(0);

			this.cortesExecutadosChafariz = new Integer(0);
			this.suprExecutadasChafariz = new Integer(0);
			this.religacoesChafariz = new Integer(0);
			this.reestabChafariz = new Integer(0);
			this.clandCortadosChafariz = new Integer(0);
			this.clandSuprimidosChafariz = new Integer(0);
			this.contasEmitidasChafariz = new Integer(0);
			this.leitEfetuadasChafariz = new Integer(0);
			this.leitAnormalidadesChafariz = new Integer(0);
			this.avisoCorteChafariz = new Integer(0);
			this.percAnormalidadeChafariz = new BigDecimal(0);
			this.percHidrometracaoChafariz = new BigDecimal(0);
			this.ligImplantadasAguaChafariz = new Integer(0);
			this.ligImplantadasEsgotoChafariz = new Integer(0);
			this.raPendentesComPrazoChafariz = new Integer(0);
			this.raPendentesComForaPrazoChafariz = new Integer(0);
			this.raPendentesOpPrazoChafariz = new Integer(0);
			this.raPendentesOpForaPrazoChafariz = new Integer(0);
			this.faturaRevisaoChafariz = new Integer(0);
			this.cartasNegativacaoChafariz = new Integer(0);
			this.economiaAtivaChafariz = new Integer(0);
			this.economiaInativaChafariz = new Integer(0);
			this.ligacoesAtivasChafariz = new Integer(0);
			this.ligacoesInativasChafariz = new Integer(0);
			this.regAnormalidadeInformadaChafariz = new Integer(0);
			this.regRecebidosChafariz = new Integer(0);
			this.ligAtivasHidrometroChafariz = new Integer(0);
			this.ligAtivasChafariz = new Integer(0);
			this.faturaRevisaoReclamacaoConsumoChafariz = new Integer(0);
			this.faturaRevisaoRecorrenciaChafariz = new Integer(0);
			this.faturaRevisaoFaturamentoIndevidoChafariz = new Integer(0);
			
			this.hidSubstituidosRamalCadastroProvisorio = new Integer(0);
			this.hidSubstituidosPocoCadastroProvisorio  = new Integer(0);
			this.hidInstaladosRamalCadastroProvisorio = new Integer(0);
			this.hidInstaladosPocoCadastroProvisorio = new Integer(0);

			this.cortesExecutadosCadastroProvisorio = new Integer(0);
			this.suprExecutadasCadastroProvisorio = new Integer(0);
			this.religacoesCadastroProvisorio = new Integer(0);
			this.reestabCadastroProvisorio = new Integer(0);
			this.clandCortadosCadastroProvisorio = new Integer(0);
			this.clandSuprimidosCadastroProvisorio = new Integer(0);
			this.contasEmitidasCadastroProvisorio = new Integer(0);
			this.leitEfetuadasCadastroProvisorio = new Integer(0);
			this.leitAnormalidadesCadastroProvisorio = new Integer(0);
			this.avisoCorteCadastroProvisorio = new Integer(0);
			this.percAnormalidadeCadastroProvisorio = new BigDecimal(0);
			this.percHidrometracaoCadastroProvisorio = new BigDecimal(0);
			this.ligImplantadasAguaCadastroProvisorio = new Integer(0);
			this.ligImplantadasEsgotoCadastroProvisorio = new Integer(0);
			this.raPendentesComPrazoCadastroProvisorio = new Integer(0);
			this.raPendentesComForaPrazoCadastroProvisorio = new Integer(0);
			this.raPendentesOpPrazoCadastroProvisorio = new Integer(0);
			this.raPendentesOpForaPrazoCadastroProvisorio = new Integer(0);
			this.faturaRevisaoCadastroProvisorio = new Integer(0);
			this.cartasNegativacaoCadastroProvisorio = new Integer(0);
			this.economiaAtivaCadastroProvisorio = new Integer(0);
			this.economiaInativaCadastroProvisorio = new Integer(0);
			this.ligacoesAtivasCadastroProvisorio = new Integer(0);
			this.ligacoesInativasCadastroProvisorio = new Integer(0);
			this.regAnormalidadeInformadaCadastroProvisorio = new Integer(0);
			this.regRecebidosCadastroProvisorio = new Integer(0);
			this.ligAtivasHidrometroCadastroProvisorio = new Integer(0);
			this.ligAtivasCadastroProvisorio = new Integer(0);
			this.faturaRevisaoReclamacaoConsumoCadastroProvisorio = new Integer(0);
			this.faturaRevisaoRecorrenciaCadastroProvisorio = new Integer(0);
			this.faturaRevisaoFaturamentoIndevidoCadastroProvisorio = new Integer(0);
			
			this.hidSubstituidosRamalMicroTelemedido = new Integer(0);
			this.hidSubstituidosPocoMicroTelemedido  = new Integer(0);
			this.hidInstaladosRamalMicroTelemedido = new Integer(0);
			this.hidInstaladosPocoMicroTelemedido = new Integer(0);

			this.cortesExecutadosMicroTelemedido = new Integer(0);
			this.suprExecutadasMicroTelemedido = new Integer(0);
			this.religacoesMicroTelemedido = new Integer(0);
			this.reestabMicroTelemedido = new Integer(0);
			this.clandCortadosMicroTelemedido = new Integer(0);
			this.clandSuprimidosMicroTelemedido = new Integer(0);
			this.contasEmitidasMicroTelemedido = new Integer(0);
			this.leitEfetuadasMicroTelemedido = new Integer(0);
			this.leitAnormalidadesMicroTelemedido = new Integer(0);
			this.avisoCorteMicroTelemedido = new Integer(0);
			this.percAnormalidadeMicroTelemedido = new BigDecimal(0);
			this.percHidrometracaoMicroTelemedido = new BigDecimal(0);
			this.ligImplantadasAguaMicroTelemedido = new Integer(0);
			this.ligImplantadasEsgotoMicroTelemedido = new Integer(0);
			this.raPendentesComPrazoMicroTelemedido = new Integer(0);
			this.raPendentesComForaPrazoMicroTelemedido = new Integer(0);
			this.raPendentesOpPrazoMicroTelemedido = new Integer(0);
			this.raPendentesOpForaPrazoMicroTelemedido = new Integer(0);
			this.faturaRevisaoMicroTelemedido = new Integer(0);
			this.cartasNegativacaoMicroTelemedido = new Integer(0);
			this.economiaAtivaMicroTelemedido = new Integer(0);
			this.economiaInativaMicroTelemedido = new Integer(0);
			this.ligacoesAtivasMicroTelemedido = new Integer(0);
			this.ligacoesInativasMicroTelemedido = new Integer(0);
			this.regAnormalidadeInformadaMicroTelemedido = new Integer(0);
			this.regRecebidosMicroTelemedido = new Integer(0);
			this.ligAtivasHidrometroMicroTelemedido = new Integer(0);
			this.ligAtivasMicroTelemedido = new Integer(0);
			this.faturaRevisaoReclamacaoConsumoMicroTelemedido = new Integer(0);
			this.faturaRevisaoRecorrenciaMicroTelemedido = new Integer(0);
			this.faturaRevisaoFaturamentoIndevidoMicroTelemedido = new Integer(0);

			this.alteracaoNumeroEconomiasAcrescidoResidencial = new Integer(0);
			this.alteracaoNumeroEconomiasDecrescidoResidencial = new Integer(0);
			this.alteracaoQuantidadeEconomiasAcrescidoResidencial = new Integer(0);
			this.alteracaoQuantidadeEconomiasDecrescidoResidencial = new Integer(0);
			this.alteracaoCategoriaResidencial = new Integer(0);
			this.inclusaoTarifaSocialResidencial  = new Integer(0);
			this.exclusaoTarifaSocialResidencial  = new Integer(0);
			this.faturamentoSuspensoResidencial  = new Integer(0);

			this.alteracaoNumeroEconomiasAcrescidoComercial = new Integer(0);
			this.alteracaoNumeroEconomiasDecrescidoComercial = new Integer(0);
			this.alteracaoQuantidadeEconomiasAcrescidoComercial = new Integer(0);
			this.alteracaoQuantidadeEconomiasDecrescidoComercial = new Integer(0);
			this.alteracaoCategoriaComercial = new Integer(0);
			this.inclusaoTarifaSocialComercial  = new Integer(0);
			this.exclusaoTarifaSocialComercial  = new Integer(0);
			this.faturamentoSuspensoComercial  = new Integer(0);

			this.alteracaoNumeroEconomiasAcrescidoIndustrial = new Integer(0);
			this.alteracaoNumeroEconomiasDecrescidoIndustrial = new Integer(0);
			this.alteracaoQuantidadeEconomiasAcrescidoIndustrial = new Integer(0);
			this.alteracaoQuantidadeEconomiasDecrescidoIndustrial = new Integer(0);
			this.alteracaoCategoriaIndustrial = new Integer(0);
			this.inclusaoTarifaSocialIndustrial  = new Integer(0);
			this.exclusaoTarifaSocialIndustrial  = new Integer(0);
			this.faturamentoSuspensoIndustrial  = new Integer(0);

			this.alteracaoNumeroEconomiasAcrescidoPublico = new Integer(0);
			this.alteracaoNumeroEconomiasDecrescidoPublico = new Integer(0);
			this.alteracaoQuantidadeEconomiasAcrescidoPublico = new Integer(0);
			this.alteracaoQuantidadeEconomiasDecrescidoPublico = new Integer(0);
			this.alteracaoCategoriaPublico = new Integer(0);
			this.inclusaoTarifaSocialPublico  = new Integer(0);
			this.exclusaoTarifaSocialPublico  = new Integer(0);
			this.faturamentoSuspensoPublico  = new Integer(0);

			this.alteracaoNumeroEconomiasAcrescidoNormal = new Integer(0);
			this.alteracaoNumeroEconomiasDecrescidoNormal = new Integer(0);
			this.alteracaoQuantidadeEconomiasAcrescidoNormal = new Integer(0);
			this.alteracaoQuantidadeEconomiasDecrescidoNormal = new Integer(0);
			this.alteracaoCategoriaNormal = new Integer(0);
			this.inclusaoTarifaSocialNormal  = new Integer(0);
			this.exclusaoTarifaSocialNormal  = new Integer(0);
			this.faturamentoSuspensoNormal  = new Integer(0);

			this.alteracaoNumeroEconomiasAcrescidoTarSocial = new Integer(0);
			this.alteracaoNumeroEconomiasDecrescidoTarSocial = new Integer(0);
			this.alteracaoQuantidadeEconomiasAcrescidoTarSocial = new Integer(0);
			this.alteracaoQuantidadeEconomiasDecrescidoTarSocial = new Integer(0);
			this.alteracaoCategoriaTarSocial = new Integer(0);
			this.inclusaoTarifaSocialTarSocial  = new Integer(0);
			this.exclusaoTarifaSocialTarSocial  = new Integer(0);
			this.faturamentoSuspensoTarSocial  = new Integer(0);

			this.alteracaoNumeroEconomiasAcrescidoGrande = new Integer(0);
			this.alteracaoNumeroEconomiasDecrescidoGrande = new Integer(0);
			this.alteracaoQuantidadeEconomiasAcrescidoGrande = new Integer(0);
			this.alteracaoQuantidadeEconomiasDecrescidoGrande = new Integer(0);
			this.alteracaoCategoriaGrande = new Integer(0);
			this.inclusaoTarifaSocialGrande  = new Integer(0);
			this.exclusaoTarifaSocialGrande  = new Integer(0);
			this.faturamentoSuspensoGrande  = new Integer(0);

			this.alteracaoNumeroEconomiasAcrescidoCorporativo = new Integer(0);
			this.alteracaoNumeroEconomiasDecrescidoCorporativo = new Integer(0);
			this.alteracaoQuantidadeEconomiasAcrescidoCorporativo = new Integer(0);
			this.alteracaoQuantidadeEconomiasDecrescidoCorporativo = new Integer(0);
			this.alteracaoCategoriaCorporativo = new Integer(0);
			this.inclusaoTarifaSocialCorporativo  = new Integer(0);
			this.exclusaoTarifaSocialCorporativo  = new Integer(0);
			this.faturamentoSuspensoCorporativo  = new Integer(0);

			this.alteracaoNumeroEconomiasAcrescidoGrandeTelemedido = new Integer(0);
			this.alteracaoNumeroEconomiasDecrescidoGrandeTelemedido = new Integer(0);
			this.alteracaoQuantidadeEconomiasAcrescidoGrandeTelemedido = new Integer(0);
			this.alteracaoQuantidadeEconomiasDecrescidoGrandeTelemedido = new Integer(0);
			this.alteracaoCategoriaGrandeTelemedido = new Integer(0);
			this.inclusaoTarifaSocialGrandeTelemedido  = new Integer(0);
			this.exclusaoTarifaSocialGrandeTelemedido  = new Integer(0);
			this.faturamentoSuspensoGrandeTelemedido  = new Integer(0);

			this.alteracaoNumeroEconomiasAcrescidoCorpTelemedido = new Integer(0);
			this.alteracaoNumeroEconomiasDecrescidoCorpTelemedido = new Integer(0);
			this.alteracaoQuantidadeEconomiasAcrescidoCorpTelemedido = new Integer(0);
			this.alteracaoQuantidadeEconomiasDecrescidoCorpTelemedido = new Integer(0);
			this.alteracaoCategoriaCorpTelemedido = new Integer(0);
			this.inclusaoTarifaSocialCorpTelemedido  = new Integer(0);
			this.exclusaoTarifaSocialCorpTelemedido  = new Integer(0);
			this.faturamentoSuspensoCorpTelemedido  = new Integer(0);

			this.alteracaoNumeroEconomiasAcrescidoChafariz = new Integer(0);
			this.alteracaoNumeroEconomiasDecrescidoChafariz = new Integer(0);
			this.alteracaoQuantidadeEconomiasAcrescidoChafariz = new Integer(0);
			this.alteracaoQuantidadeEconomiasDecrescidoChafariz = new Integer(0);
			this.alteracaoCategoriaChafariz = new Integer(0);
			this.inclusaoTarifaSocialChafariz  = new Integer(0);
			this.exclusaoTarifaSocialChafariz  = new Integer(0);
			this.faturamentoSuspensoChafariz  = new Integer(0);

			this.alteracaoNumeroEconomiasAcrescidoMicroTelemedido = new Integer(0);
			this.alteracaoNumeroEconomiasDecrescidoMicroTelemedido = new Integer(0);
			this.alteracaoQuantidadeEconomiasAcrescidoMicroTelemedido = new Integer(0);
			this.alteracaoQuantidadeEconomiasDecrescidoMicroTelemedido = new Integer(0);
			this.alteracaoCategoriaMicroTelemedido = new Integer(0);
			this.inclusaoTarifaSocialMicroTelemedido  = new Integer(0);
			this.exclusaoTarifaSocialMicroTelemedido  = new Integer(0);
			this.faturamentoSuspensoMicroTelemedido  = new Integer(0);

			this.alteracaoNumeroEconomiasAcrescidoCadastroProvisorio = new Integer(0);
			this.alteracaoNumeroEconomiasDecrescidoCadastroProvisorio = new Integer(0);
			this.alteracaoQuantidadeEconomiasAcrescidoCadastroProvisorio = new Integer(0);
			this.alteracaoQuantidadeEconomiasDecrescidoCadastroProvisorio = new Integer(0);
			this.alteracaoCategoriaCadastroProvisorio = new Integer(0);
			this.inclusaoTarifaSocialCadastroProvisorio  = new Integer(0);
			this.exclusaoTarifaSocialCadastroProvisorio  = new Integer(0);
			this.faturamentoSuspensoCadastroProvisorio  = new Integer(0);
			

		}
	
	
	public String getGerenciaRegionalID() {
		return gerenciaRegionalID;
	}
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	public String getLocalidadeID() {
		return localidadeID;
	}
	public String getLocalidade() {
		return localidade;
	}
	public String getUnidadeNegocioID() {
		return unidadeNegocioID;
	}
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public String getMunicipioID() {
		return municipioID;
	}
	public String getMunicipio() {
		return municipio;
	}
	public Integer getHidSubstituidosPocoResidencial() {
		return hidSubstituidosPocoResidencial;
	}
	public Integer getHidSubstituidosRamalResidencial() {
		return hidSubstituidosRamalResidencial;
	}
	public Integer getHidInstaladosPocoResidencial() {
		return hidInstaladosPocoResidencial;
	}
	
	public Integer getHidInstaladosRamalResidencial() {
		return hidInstaladosRamalResidencial;
	}
	
	public Integer getCortesExecutadosResidencial() {
		return cortesExecutadosResidencial;
	}
	public Integer getSuprExecutadasResidencial() {
		return suprExecutadasResidencial;
	}
	public Integer getReligacoesResidencial() {
		return religacoesResidencial;
	}
	public Integer getReestabResidencial() {
		return reestabResidencial;
	}
	public Integer getClandCortadosResidencial() {
		return clandCortadosResidencial;
	}
	public Integer getClandSuprimidosResidencial() {
		return clandSuprimidosResidencial;
	}
	public Integer getContasEmitidasResidencial() {
		return contasEmitidasResidencial;
	}
	public Integer getLeitEfetuadasResidencial() {
		return leitEfetuadasResidencial;
	}
	public Integer getLeitAnormalidadesResidencial() {
		return leitAnormalidadesResidencial;
	}
	public Integer getAvisoCorteResidencial() {
		return avisoCorteResidencial;
	}
	public BigDecimal getPercAnormalidadeResidencial() {
		if(this.getRegRecebidosResidencial().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getRegAnormalidadeInformadaResidencial());
		BigDecimal tempDenominador = new BigDecimal(this.getRegRecebidosResidencial());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public BigDecimal getPercHidrometracaoResidencial() {
		if(this.getLigAtivasResidencial().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getLigAtivasHidrometroResidencial());
		BigDecimal tempDenominador = new BigDecimal(this.getLigAtivasResidencial());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public Integer getLigImplantadasAguaResidencial() {
		return ligImplantadasAguaResidencial;
	}
	public Integer getLigImplantadasEsgotoResidencial() {
		return ligImplantadasEsgotoResidencial;
	}
	public Integer getRaPendentesComPrazoResidencial() {
		return raPendentesComPrazoResidencial;
	}
	public Integer getRaPendentesComForaPrazoResidencial() {
		return raPendentesComForaPrazoResidencial;
	}
	public Integer getRaPendentesOpPrazoResidencial() {
		return raPendentesOpPrazoResidencial;
	}
	public Integer getRaPendentesOpForaPrazoResidencial() {
		return raPendentesOpForaPrazoResidencial;
	}
	public Integer getFaturaRevisaoResidencial() {
		return faturaRevisaoResidencial;
	}
	public Integer getCartasNegativacaoResidencial() {
		return cartasNegativacaoResidencial;
	}
	public Integer getEconomiaAtivaResidencial() {
		return economiaAtivaResidencial;
	}
	public Integer getEconomiaInativaResidencial() {
		return economiaInativaResidencial;
	}
	public Integer getLigacoesAtivasResidencial() {
		return ligacoesAtivasResidencial;
	}
	public Integer getLigacoesInativasResidencial() {
		return ligacoesInativasResidencial;
	}
	public Integer getHidSubstituidosPocoComercial() {
		return hidSubstituidosPocoComercial;
	}
	public Integer getHidSubstituidosRamalComercial() {
		return hidSubstituidosRamalComercial;
	}
	public Integer getHidInstaladosPocoComercial() {
		return hidInstaladosPocoComercial;
	}
	
	public Integer getHidInstaladosRamalComercial() {
		return hidInstaladosRamalComercial;
	}
	public Integer getCortesExecutadosComercial() {
		return cortesExecutadosComercial;
	}
	public Integer getSuprExecutadasComercial() {
		return suprExecutadasComercial;
	}
	public Integer getReligacoesComercial() {
		return religacoesComercial;
	}
	public Integer getReestabComercial() {
		return reestabComercial;
	}
	public Integer getClandCortadosComercial() {
		return clandCortadosComercial;
	}
	public Integer getClandSuprimidosComercial() {
		return clandSuprimidosComercial;
	}
	public Integer getContasEmitidasComercial() {
		return contasEmitidasComercial;
	}
	public Integer getLeitEfetuadasComercial() {
		return leitEfetuadasComercial;
	}
	public Integer getLeitAnormalidadesComercial() {
		return leitAnormalidadesComercial;
	}
	public Integer getAvisoCorteComercial() {
		return avisoCorteComercial;
	}
	public BigDecimal getPercAnormalidadeComercial() {
		if(this.getRegRecebidosComercial().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getRegAnormalidadeInformadaComercial());
		BigDecimal tempDenominador = new BigDecimal(this.getRegRecebidosComercial());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public BigDecimal getPercHidrometracaoComercial() {
		if(this.getLigAtivasComercial().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getLigAtivasHidrometroComercial());
		BigDecimal tempDenominador = new BigDecimal(this.getLigAtivasComercial());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public Integer getLigImplantadasAguaComercial() {
		return ligImplantadasAguaComercial;
	}
	public Integer getLigImplantadasEsgotoComercial() {
		return ligImplantadasEsgotoComercial;
	}
	public Integer getRaPendentesComPrazoComercial() {
		return raPendentesComPrazoComercial;
	}
	public Integer getRaPendentesComForaPrazoComercial() {
		return raPendentesComForaPrazoComercial;
	}
	public Integer getRaPendentesOpPrazoComercial() {
		return raPendentesOpPrazoComercial;
	}
	public Integer getRaPendentesOpForaPrazoComercial() {
		return raPendentesOpForaPrazoComercial;
	}
	public Integer getFaturaRevisaoComercial() {
		return faturaRevisaoComercial;
	}
	public Integer getCartasNegativacaoComercial() {
		return cartasNegativacaoComercial;
	}
	public Integer getEconomiaAtivaComercial() {
		return economiaAtivaComercial;
	}
	public Integer getEconomiaInativaComercial() {
		return economiaInativaComercial;
	}
	public Integer getLigacoesAtivasComercial() {
		return ligacoesAtivasComercial;
	}
	public Integer getLigacoesInativasComercial() {
		return ligacoesInativasComercial;
	}
	public Integer getHidSubstituidosPocoIndustrial() {
		return hidSubstituidosPocoIndustrial;
	}
	public Integer getHidSubstituidosRamalIndustrial() {
		return hidSubstituidosRamalIndustrial;
	}
	public Integer getHidInstaladosPocoIndustrial() {
		return hidInstaladosPocoIndustrial;
	}
	
	public Integer getHidInstaladosRamalIndustrial() {
		return hidInstaladosRamalIndustrial;
	}
	public Integer getCortesExecutadosIndustrial() {
		return cortesExecutadosIndustrial;
	}
	public Integer getSuprExecutadasIndustrial() {
		return suprExecutadasIndustrial;
	}
	public Integer getReligacoesIndustrial() {
		return religacoesIndustrial;
	}
	public Integer getReestabIndustrial() {
		return reestabIndustrial;
	}
	public Integer getClandCortadosIndustrial() {
		return clandCortadosIndustrial;
	}
	public Integer getClandSuprimidosIndustrial() {
		return clandSuprimidosIndustrial;
	}
	public Integer getContasEmitidasIndustrial() {
		return contasEmitidasIndustrial;
	}
	public Integer getLeitEfetuadasIndustrial() {
		return leitEfetuadasIndustrial;
	}
	public Integer getLeitAnormalidadesIndustrial() {
		return leitAnormalidadesIndustrial;
	}
	public Integer getAvisoCorteIndustrial() {
		return avisoCorteIndustrial;
	}
	public BigDecimal getPercAnormalidadeIndustrial() {
		if(this.getRegRecebidosIndustrial().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getRegAnormalidadeInformadaIndustrial());
		BigDecimal tempDenominador = new BigDecimal(this.getRegRecebidosIndustrial());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public BigDecimal getPercHidrometracaoIndustrial() {
		if(this.getLigAtivasIndustrial().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getLigAtivasHidrometroIndustrial());
		BigDecimal tempDenominador = new BigDecimal(this.getLigAtivasIndustrial());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public Integer getLigImplantadasAguaIndustrial() {
		return ligImplantadasAguaIndustrial;
	}
	public Integer getLigImplantadasEsgotoIndustrial() {
		return ligImplantadasEsgotoIndustrial;
	}
	public Integer getRaPendentesComPrazoIndustrial() {
		return raPendentesComPrazoIndustrial;
	}
	public Integer getRaPendentesComForaPrazoIndustrial() {
		return raPendentesComForaPrazoIndustrial;
	}
	public Integer getRaPendentesOpPrazoIndustrial() {
		return raPendentesOpPrazoIndustrial;
	}
	public Integer getRaPendentesOpForaPrazoIndustrial() {
		return raPendentesOpForaPrazoIndustrial;
	}
	public Integer getFaturaRevisaoIndustrial() {
		return faturaRevisaoIndustrial;
	}
	public Integer getCartasNegativacaoIndustrial() {
		return cartasNegativacaoIndustrial;
	}
	public Integer getEconomiaAtivaIndustrial() {
		return economiaAtivaIndustrial;
	}
	public Integer getEconomiaInativaIndustrial() {
		return economiaInativaIndustrial;
	}
	public Integer getLigacoesAtivasIndustrial() {
		return ligacoesAtivasIndustrial;
	}
	public Integer getLigacoesInativasIndustrial() {
		return ligacoesInativasIndustrial;
	}
	public Integer getHidSubstituidosPocoPublico() {
		return hidSubstituidosPocoPublico;
	}
	public Integer getHidSubstituidosRamalPublico() {
		return hidSubstituidosRamalPublico;
	}
	public Integer getHidInstaladosPocoPublico() {
		return hidInstaladosPocoPublico;
	}
	
	public Integer getHidInstaladosRamalPublico() {
		return hidInstaladosRamalPublico;
	}
	public Integer getCortesExecutadosPublico() {
		return cortesExecutadosPublico;
	}
	public Integer getSuprExecutadasPublico() {
		return suprExecutadasPublico;
	}
	public Integer getReligacoesPublico() {
		return religacoesPublico;
	}
	public Integer getReestabPublico() {
		return reestabPublico;
	}
	public Integer getClandCortadosPublico() {
		return clandCortadosPublico;
	}
	public Integer getClandSuprimidosPublico() {
		return clandSuprimidosPublico;
	}
	public Integer getContasEmitidasPublico() {
		return contasEmitidasPublico;
	}
	public Integer getLeitEfetuadasPublico() {
		return leitEfetuadasPublico;
	}
	public Integer getLeitAnormalidadesPublico() {
		return leitAnormalidadesPublico;
	}
	public Integer getAvisoCortePublico() {
		return avisoCortePublico;
	}
	public BigDecimal getPercAnormalidadePublico() {
		if(this.getRegRecebidosPublico().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getRegAnormalidadeInformadaPublico());
		BigDecimal tempDenominador = new BigDecimal(this.getRegRecebidosPublico());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public BigDecimal getPercHidrometracaoPublico() {
		if(this.getLigAtivasPublico().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getLigAtivasHidrometroPublico());
		BigDecimal tempDenominador = new BigDecimal(this.getLigAtivasPublico());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public Integer getLigImplantadasAguaPublico() {
		return ligImplantadasAguaPublico;
	}
	public Integer getLigImplantadasEsgotoPublico() {
		return ligImplantadasEsgotoPublico;
	}
	public Integer getRaPendentesComPrazoPublico() {
		return raPendentesComPrazoPublico;
	}
	public Integer getRaPendentesComForaPrazoPublico() {
		return raPendentesComForaPrazoPublico;
	}
	public Integer getRaPendentesOpPrazoPublico() {
		return raPendentesOpPrazoPublico;
	}
	public Integer getRaPendentesOpForaPrazoPublico() {
		return raPendentesOpForaPrazoPublico;
	}
	public Integer getFaturaRevisaoPublico() {
		return faturaRevisaoPublico;
	}
	public Integer getCartasNegativacaoPublico() {
		return cartasNegativacaoPublico;
	}
	public Integer getEconomiaAtivaPublico() {
		return economiaAtivaPublico;
	}
	public Integer getEconomiaInativaPublico() {
		return economiaInativaPublico;
	}
	public Integer getLigacoesAtivasPublico() {
		return ligacoesAtivasPublico;
	}
	public Integer getLigacoesInativasPublico() {
		return ligacoesInativasPublico;
	}
	public Integer getHidSubstituidosPocoNormal() {
		return hidSubstituidosPocoNormal;
	}
	public Integer getHidSubstituidosRamalNormal() {
		return hidSubstituidosRamalNormal;
	}
	public Integer getHidInstaladosPocoNormal() {
		return hidInstaladosPocoNormal;
	}
	
	public Integer getHidInstaladosRamalNormal() {
		return hidInstaladosRamalNormal;
	}
	public Integer getCortesExecutadosNormal() {
		return cortesExecutadosNormal;
	}
	public Integer getSuprExecutadasNormal() {
		return suprExecutadasNormal;
	}
	public Integer getReligacoesNormal() {
		return religacoesNormal;
	}
	public Integer getReestabNormal() {
		return reestabNormal;
	}
	public Integer getClandCortadosNormal() {
		return clandCortadosNormal;
	}
	public Integer getClandSuprimidosNormal() {
		return clandSuprimidosNormal;
	}
	public Integer getContasEmitidasNormal() {
		return contasEmitidasNormal;
	}
	public Integer getLeitEfetuadasNormal() {
		return leitEfetuadasNormal;
	}
	public Integer getLeitAnormalidadesNormal() {
		return leitAnormalidadesNormal;
	}
	public Integer getAvisoCorteNormal() {
		return avisoCorteNormal;
	}
	public BigDecimal getPercAnormalidadeNormal() {
		if(this.getRegRecebidosNormal().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getRegAnormalidadeInformadaNormal());
		BigDecimal tempDenominador = new BigDecimal(this.getRegRecebidosNormal());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public BigDecimal getPercHidrometracaoNormal() {
		if(this.getLigAtivasNormal().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getLigAtivasHidrometroNormal());
		BigDecimal tempDenominador = new BigDecimal(this.getLigAtivasNormal());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public Integer getLigImplantadasAguaNormal() {
		return ligImplantadasAguaNormal;
	}
	public Integer getLigImplantadasEsgotoNormal() {
		return ligImplantadasEsgotoNormal;
	}
	public Integer getRaPendentesComPrazoNormal() {
		return raPendentesComPrazoNormal;
	}
	public Integer getRaPendentesComForaPrazoNormal() {
		return raPendentesComForaPrazoNormal;
	}
	public Integer getRaPendentesOpPrazoNormal() {
		return raPendentesOpPrazoNormal;
	}
	public Integer getRaPendentesOpForaPrazoNormal() {
		return raPendentesOpForaPrazoNormal;
	}
	public Integer getFaturaRevisaoNormal() {
		return faturaRevisaoNormal;
	}
	public Integer getCartasNegativacaoNormal() {
		return cartasNegativacaoNormal;
	}
	public Integer getEconomiaAtivaNormal() {
		return economiaAtivaNormal;
	}
	public Integer getEconomiaInativaNormal() {
		return economiaInativaNormal;
	}
	public Integer getLigacoesAtivasNormal() {
		return ligacoesAtivasNormal;
	}
	public Integer getLigacoesInativasNormal() {
		return ligacoesInativasNormal;
	}
	public Integer getHidSubstituidosPocoGrande() {
		return hidSubstituidosPocoGrande;
	}
	public Integer getHidSubstituidosRamalGrande() {
		return hidSubstituidosRamalGrande;
	}
	public Integer getHidInstaladosPocoGrande() {
		return hidInstaladosPocoGrande;
	}
	
	public Integer getHidInstaladosRamalGrande() {
		return hidInstaladosRamalGrande;
	}
	public Integer getCortesExecutadosTarSocial() {
		return cortesExecutadosTarSocial;
	}
	public Integer getSuprExecutadasTarSocial() {
		return suprExecutadasTarSocial;
	}
	public Integer getReligacoesTarSocial() {
		return religacoesTarSocial;
	}
	public Integer getReestabTarSocial() {
		return reestabTarSocial;
	}
	public Integer getClandCortadosTarSocial() {
		return clandCortadosTarSocial;
	}
	public Integer getClandSuprimidosTarSocial() {
		return clandSuprimidosTarSocial;
	}
	public Integer getContasEmitidasTarSocial() {
		return contasEmitidasTarSocial;
	}
	public Integer getLeitEfetuadasTarSocial() {
		return leitEfetuadasTarSocial;
	}
	public Integer getLeitAnormalidadesTarSocial() {
		return leitAnormalidadesTarSocial;
	}
	public Integer getAvisoCorteTarSocial() {
		return avisoCorteTarSocial;
	}
	public BigDecimal getPercAnormalidadeTarSocial() {
		if(this.getRegRecebidosTarSocial().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getRegAnormalidadeInformadaTarSocial());
		BigDecimal tempDenominador = new BigDecimal(this.getRegRecebidosTarSocial());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public BigDecimal getPercHidrometracaoTarSocial() {
		if(this.getLigAtivasTarSocial().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getLigAtivasHidrometroTarSocial());
		BigDecimal tempDenominador = new BigDecimal(this.getLigAtivasTarSocial());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public Integer getLigImplantadasAguaTarSocial() {
		return ligImplantadasAguaTarSocial;
	}
	public Integer getLigImplantadasEsgotoTarSocial() {
		return ligImplantadasEsgotoTarSocial;
	}
	public Integer getRaPendentesComPrazoTarSocial() {
		return raPendentesComPrazoTarSocial;
	}
	public Integer getRaPendentesComForaPrazoTarSocial() {
		return raPendentesComForaPrazoTarSocial;
	}
	public Integer getRaPendentesOpPrazoTarSocial() {
		return raPendentesOpPrazoTarSocial;
	}
	public Integer getRaPendentesOpForaPrazoTarSocial() {
		return raPendentesOpForaPrazoTarSocial;
	}
	public Integer getFaturaRevisaoTarSocial() {
		return faturaRevisaoTarSocial;
	}
	public Integer getCartasNegativacaoTarSocial() {
		return cartasNegativacaoTarSocial;
	}
	public Integer getEconomiaAtivaTarSocial() {
		return economiaAtivaTarSocial;
	}
	public Integer getEconomiaInativaTarSocial() {
		return economiaInativaTarSocial;
	}
	public Integer getLigacoesAtivasTarSocial() {
		return ligacoesAtivasTarSocial;
	}
	public Integer getLigacoesInativasTarSocial() {
		return ligacoesInativasTarSocial;
	}
	public Integer getHidSubstituidosPocoTarSocial() {
		return hidSubstituidosPocoTarSocial;
	}
	public Integer getHidSubstituidosRamalTarSocial() {
		return hidSubstituidosRamalTarSocial;
	}
	public Integer getHidInstaladosPocoTarSocial() {
		return hidInstaladosPocoTarSocial;
	}
	
	public Integer getHidInstaladosRamalTarSocial() {
		return hidInstaladosRamalTarSocial;
	}
	public Integer getCortesExecutadosGrande() {
		return cortesExecutadosGrande;
	}
	public Integer getSuprExecutadasGrande() {
		return suprExecutadasGrande;
	}
	public Integer getReligacoesGrande() {
		return religacoesGrande;
	}
	public Integer getReestabGrande() {
		return reestabGrande;
	}
	public Integer getClandCortadosGrande() {
		return clandCortadosGrande;
	}
	public Integer getClandSuprimidosGrande() {
		return clandSuprimidosGrande;
	}
	public Integer getContasEmitidasGrande() {
		return contasEmitidasGrande;
	}
	public Integer getLeitEfetuadasGrande() {
		return leitEfetuadasGrande;
	}
	public Integer getLeitAnormalidadesGrande() {
		return leitAnormalidadesGrande;
	}
	public Integer getAvisoCorteGrande() {
		return avisoCorteGrande;
	}
	public BigDecimal getPercAnormalidadeGrande() {
		if(this.getRegRecebidosGrande().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getRegAnormalidadeInformadaGrande());
		BigDecimal tempDenominador = new BigDecimal(this.getRegRecebidosGrande());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public BigDecimal getPercHidrometracaoGrande() {
		if(this.getLigAtivasGrande().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getLigAtivasHidrometroGrande());
		BigDecimal tempDenominador = new BigDecimal(this.getLigAtivasGrande());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public Integer getLigImplantadasAguaGrande() {
		return ligImplantadasAguaGrande;
	}
	public Integer getLigImplantadasEsgotoGrande() {
		return ligImplantadasEsgotoGrande;
	}
	public Integer getRaPendentesComPrazoGrande() {
		return raPendentesComPrazoGrande;
	}
	public Integer getRaPendentesComForaPrazoGrande() {
		return raPendentesComForaPrazoGrande;
	}
	public Integer getRaPendentesOpPrazoGrande() {
		return raPendentesOpPrazoGrande;
	}
	public Integer getRaPendentesOpForaPrazoGrande() {
		return raPendentesOpForaPrazoGrande;
	}
	public Integer getFaturaRevisaoGrande() {
		return faturaRevisaoGrande;
	}
	public Integer getCartasNegativacaoGrande() {
		return cartasNegativacaoGrande;
	}
	public Integer getEconomiaAtivaGrande() {
		return economiaAtivaGrande;
	}
	public Integer getEconomiaInativaGrande() {
		return economiaInativaGrande;
	}
	public Integer getLigacoesAtivasGrande() {
		return ligacoesAtivasGrande;
	}
	public Integer getLigacoesInativasGrande() {
		return ligacoesInativasGrande;
	}
	public Integer getHidSubstituidosPocoCorporativo() {
		return hidSubstituidosPocoCorporativo;
	}
	public Integer getHidSubstituidosRamalCorporativo() {
		return hidSubstituidosRamalCorporativo;
	}
	public Integer getHidInstaladosPocoCorporativo() {
		return hidInstaladosPocoCorporativo;
	}
	
	public Integer getHidInstaladosRamalCorporativo() {
		return hidInstaladosRamalCorporativo;
	}
	public Integer getCortesExecutadosCorporativo() {
		return cortesExecutadosCorporativo;
	}
	public Integer getSuprExecutadasCorporativo() {
		return suprExecutadasCorporativo;
	}
	public Integer getReligacoesCorporativo() {
		return religacoesCorporativo;
	}
	public Integer getReestabCorporativo() {
		return reestabCorporativo;
	}
	public Integer getClandCortadosCorporativo() {
		return clandCortadosCorporativo;
	}
	public Integer getClandSuprimidosCorporativo() {
		return clandSuprimidosCorporativo;
	}
	public Integer getContasEmitidasCorporativo() {
		return contasEmitidasCorporativo;
	}
	public Integer getLeitEfetuadasCorporativo() {
		return leitEfetuadasCorporativo;
	}
	public Integer getLeitAnormalidadesCorporativo() {
		return leitAnormalidadesCorporativo;
	}
	public Integer getAvisoCorteCorporativo() {
		return avisoCorteCorporativo;
	}
	public BigDecimal getPercAnormalidadeCorporativo() {
		if(this.getRegRecebidosCorporativo().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getRegAnormalidadeInformadaCorporativo());
		BigDecimal tempDenominador = new BigDecimal(this.getRegRecebidosCorporativo());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public BigDecimal getPercHidrometracaoCorporativo() {
		if(this.getLigAtivasCorporativo().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getLigAtivasHidrometroCorporativo());
		BigDecimal tempDenominador = new BigDecimal(this.getLigAtivasCorporativo());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public Integer getLigImplantadasAguaCorporativo() {
		return ligImplantadasAguaCorporativo;
	}
	public Integer getLigImplantadasEsgotoCorporativo() {
		return ligImplantadasEsgotoCorporativo;
	}
	public Integer getRaPendentesComPrazoCorporativo() {
		return raPendentesComPrazoCorporativo;
	}
	public Integer getRaPendentesComForaPrazoCorporativo() {
		return raPendentesComForaPrazoCorporativo;
	}
	public Integer getRaPendentesOpPrazoCorporativo() {
		return raPendentesOpPrazoCorporativo;
	}
	public Integer getRaPendentesOpForaPrazoCorporativo() {
		return raPendentesOpForaPrazoCorporativo;
	}
	public Integer getFaturaRevisaoCorporativo() {
		return faturaRevisaoCorporativo;
	}
	public Integer getCartasNegativacaoCorporativo() {
		return cartasNegativacaoCorporativo;
	}
	public Integer getEconomiaAtivaCorporativo() {
		return economiaAtivaCorporativo;
	}
	public Integer getEconomiaInativaCorporativo() {
		return economiaInativaCorporativo;
	}
	public Integer getLigacoesAtivasCorporativo() {
		return ligacoesAtivasCorporativo;
	}
	public Integer getLigacoesInativasCorporativo() {
		return ligacoesInativasCorporativo;
	}
	public Integer getHidSubstituidosPocoGrandeTelemedido() {
		return hidSubstituidosPocoGrandeTelemedido;
	}
	public Integer getHidSubstituidosRamalGrandeTelemedido() {
		return hidSubstituidosRamalGrandeTelemedido;
	}
	public Integer getHidInstaladosPocoGrandeTelemedido() {
		return hidInstaladosPocoGrandeTelemedido;
	}
	
	public Integer getHidInstaladosRamalGrandeTelemedido() {
		return hidInstaladosRamalGrandeTelemedido;
	}
	public Integer getCortesExecutadosGrandeTelemedido() {
		return cortesExecutadosGrandeTelemedido;
	}
	public Integer getSuprExecutadasGrandeTelemedido() {
		return suprExecutadasGrandeTelemedido;
	}
	public Integer getReligacoesGrandeTelemedido() {
		return religacoesGrandeTelemedido;
	}
	public Integer getReestabGrandeTelemedido() {
		return reestabGrandeTelemedido;
	}
	public Integer getClandCortadosGrandeTelemedido() {
		return clandCortadosGrandeTelemedido;
	}
	public Integer getClandSuprimidosGrandeTelemedido() {
		return clandSuprimidosGrandeTelemedido;
	}
	public Integer getContasEmitidasGrandeTelemedido() {
		return contasEmitidasGrandeTelemedido;
	}
	public Integer getLeitEfetuadasGrandeTelemedido() {
		return leitEfetuadasGrandeTelemedido;
	}
	public Integer getLeitAnormalidadesGrandeTelemedido() {
		return leitAnormalidadesGrandeTelemedido;
	}
	public Integer getAvisoCorteGrandeTelemedido() {
		return avisoCorteGrandeTelemedido;
	}
	public BigDecimal getPercAnormalidadeGrandeTelemedido() {
		if(this.getRegRecebidosGrandeTelemedido().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getRegAnormalidadeInformadaGrandeTelemedido());
		BigDecimal tempDenominador = new BigDecimal(this.getRegRecebidosGrandeTelemedido());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public BigDecimal getPercHidrometracaoGrandeTelemedido() {
		if(this.getLigAtivasGrandeTelemedido().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getLigAtivasHidrometroGrandeTelemedido());
		BigDecimal tempDenominador = new BigDecimal(this.getLigAtivasGrandeTelemedido());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public Integer getLigImplantadasAguaGrandeTelemedido() {
		return ligImplantadasAguaGrandeTelemedido;
	}
	public Integer getLigImplantadasEsgotoGrandeTelemedido() {
		return ligImplantadasEsgotoGrandeTelemedido;
	}
	public Integer getRaPendentesComPrazoGrandeTelemedido() {
		return raPendentesComPrazoGrandeTelemedido;
	}
	public Integer getRaPendentesComForaPrazoGrandeTelemedido() {
		return raPendentesComForaPrazoGrandeTelemedido;
	}
	public Integer getRaPendentesOpPrazoGrandeTelemedido() {
		return raPendentesOpPrazoGrandeTelemedido;
	}
	public Integer getRaPendentesOpForaPrazoGrandeTelemedido() {
		return raPendentesOpForaPrazoGrandeTelemedido;
	}
	public Integer getFaturaRevisaoGrandeTelemedido() {
		return faturaRevisaoGrandeTelemedido;
	}
	public Integer getCartasNegativacaoGrandeTelemedido() {
		return cartasNegativacaoGrandeTelemedido;
	}
	public Integer getEconomiaAtivaGrandeTelemedido() {
		return economiaAtivaGrandeTelemedido;
	}
	public Integer getEconomiaInativaGrandeTelemedido() {
		return economiaInativaGrandeTelemedido;
	}
	public Integer getLigacoesAtivasGrandeTelemedido() {
		return ligacoesAtivasGrandeTelemedido;
	}
	public Integer getLigacoesInativasGrandeTelemedido() {
		return ligacoesInativasGrandeTelemedido;
	}
	public Integer getHidSubstituidosPocoCorpTelemedido() {
		return hidSubstituidosPocoCorpTelemedido;
	}
	public Integer getHidSubstituidosRamalCorpTelemedido() {
		return hidSubstituidosRamalCorpTelemedido;
	}
	public Integer getHidInstaladosPocoCorpTelemedido() {
		return hidInstaladosPocoCorpTelemedido;
	}
	
	public Integer getHidInstaladosRamalCorpTelemedido() {
		return hidInstaladosRamalCorpTelemedido;
	}
	public Integer getCortesExecutadosCorpTelemedido() {
		return cortesExecutadosCorpTelemedido;
	}
	public Integer getSuprExecutadasCorpTelemedido() {
		return suprExecutadasCorpTelemedido;
	}
	public Integer getReligacoesCorpTelemedido() {
		return religacoesCorpTelemedido;
	}
	public Integer getReestabCorpTelemedido() {
		return reestabCorpTelemedido;
	}
	public Integer getClandCortadosCorpTelemedido() {
		return clandCortadosCorpTelemedido;
	}
	public Integer getClandSuprimidosCorpTelemedido() {
		return clandSuprimidosCorpTelemedido;
	}
	public Integer getContasEmitidasCorpTelemedido() {
		return contasEmitidasCorpTelemedido;
	}
	public Integer getLeitEfetuadasCorpTelemedido() {
		return leitEfetuadasCorpTelemedido;
	}
	public Integer getLeitAnormalidadesCorpTelemedido() {
		return leitAnormalidadesCorpTelemedido;
	}
	public Integer getAvisoCorteCorpTelemedido() {
		return avisoCorteCorpTelemedido;
	}
	public BigDecimal getPercAnormalidadeCorpTelemedido() {
		if(this.getRegRecebidosCorpTelemedido().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getRegAnormalidadeInformadaCorpTelemedido());
		BigDecimal tempDenominador = new BigDecimal(this.getRegRecebidosCorpTelemedido());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public BigDecimal getPercHidrometracaoCorpTelemedido() {
		if(this.getLigAtivasCorpTelemedido().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getLigAtivasHidrometroCorpTelemedido());
		BigDecimal tempDenominador = new BigDecimal(this.getLigAtivasCorpTelemedido());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public Integer getLigImplantadasAguaCorpTelemedido() {
		return ligImplantadasAguaCorpTelemedido;
	}
	public Integer getLigImplantadasEsgotoCorpTelemedido() {
		return ligImplantadasEsgotoCorpTelemedido;
	}
	public Integer getRaPendentesComPrazoCorpTelemedido() {
		return raPendentesComPrazoCorpTelemedido;
	}
	public Integer getRaPendentesComForaPrazoCorpTelemedido() {
		return raPendentesComForaPrazoCorpTelemedido;
	}
	public Integer getRaPendentesOpPrazoCorpTelemedido() {
		return raPendentesOpPrazoCorpTelemedido;
	}
	public Integer getRaPendentesOpForaPrazoCorpTelemedido() {
		return raPendentesOpForaPrazoCorpTelemedido;
	}
	public Integer getFaturaRevisaoCorpTelemedido() {
		return faturaRevisaoCorpTelemedido;
	}
	public Integer getCartasNegativacaoCorpTelemedido() {
		return cartasNegativacaoCorpTelemedido;
	}
	public Integer getEconomiaAtivaCorpTelemedido() {
		return economiaAtivaCorpTelemedido;
	}
	public Integer getEconomiaInativaCorpTelemedido() {
		return economiaInativaCorpTelemedido;
	}
	public Integer getLigacoesAtivasCorpTelemedido() {
		return ligacoesAtivasCorpTelemedido;
	}
	public Integer getLigacoesInativasCorpTelemedido() {
		return ligacoesInativasCorpTelemedido;
	}
	
	public Integer getHidSubstituidosPocoTotalCategoria() {
		return new Integer(
				this.getHidSubstituidosPocoIndustrial().intValue()+
				this.getHidSubstituidosPocoComercial().intValue() + 
				this.getHidSubstituidosPocoResidencial().intValue() +
				this.getHidSubstituidosPocoPublico().intValue()
		);
	}
	
	public Integer getHidSubstituidosRamalTotalCategoria() {
		return new Integer(
				this.getHidSubstituidosRamalIndustrial().intValue()+
				this.getHidSubstituidosRamalComercial().intValue() + 
				this.getHidSubstituidosRamalResidencial().intValue() +
				this.getHidSubstituidosRamalPublico().intValue()
		);
	}
	
	
	public Integer getHidInstaladosPocoTotalCategoria() {
		return new Integer(
				this.getHidInstaladosPocoIndustrial().intValue()+
				this.getHidInstaladosPocoComercial().intValue() + 
				this.getHidInstaladosPocoResidencial().intValue() +
				this.getHidInstaladosPocoPublico().intValue()
		);
	}
	
	public Integer getHidInstaladosRamalTotalCategoria() {
		return new Integer(
				this.getHidInstaladosRamalIndustrial().intValue()+
				this.getHidInstaladosRamalComercial().intValue() + 
				this.getHidInstaladosRamalResidencial().intValue() +
				this.getHidInstaladosRamalPublico().intValue()
		);
	}
	
	
	public Integer getCortesExecutadosTotalCategoria() {
		return new Integer(
				this.getCortesExecutadosIndustrial().intValue()+
				this.getCortesExecutadosComercial().intValue() + 
				this.getCortesExecutadosResidencial().intValue() +
				this.getCortesExecutadosPublico().intValue()
		);
	}
	public Integer getSuprExecutadasTotalCategoria() {
		return new Integer(
				this.getSuprExecutadasIndustrial().intValue()+
				this.getSuprExecutadasComercial().intValue() + 
				this.getSuprExecutadasResidencial().intValue() +
				this.getSuprExecutadasPublico().intValue()
		);
	}
	public Integer getReligacoesTotalCategoria() {
		return new Integer(
				this.getReligacoesIndustrial().intValue()+
				this.getReligacoesComercial().intValue() + 
				this.getReligacoesResidencial().intValue() +
				this.getReligacoesPublico().intValue()
		);
	}
	public Integer getReestabTotalCategoria() {
		return new Integer(
				this.getReestabIndustrial().intValue()+
				this.getReestabComercial().intValue() + 
				this.getReestabResidencial().intValue() +
				this.getReestabPublico().intValue()
		);
	}
	public Integer getClandCortadosTotalCategoria() {
		return new Integer(
				this.getClandCortadosIndustrial().intValue()+
				this.getClandCortadosComercial().intValue() + 
				this.getClandCortadosResidencial().intValue() +
				this.getClandCortadosPublico().intValue()
		);
	}
	public Integer getClandSuprimidosTotalCategoria() {
		return new Integer(
				this.getClandSuprimidosIndustrial().intValue()+
				this.getClandSuprimidosComercial().intValue() + 
				this.getClandSuprimidosResidencial().intValue() +
				this.getClandSuprimidosPublico().intValue()
		);
	}
	public Integer getContasEmitidasTotalCategoria() {
		return new Integer(
				this.getContasEmitidasIndustrial().intValue()+
				this.getContasEmitidasComercial().intValue() + 
				this.getContasEmitidasResidencial().intValue() +
				this.getContasEmitidasPublico().intValue()
		);
	}
	public Integer getLeitEfetuadasTotalCategoria() {
		return new Integer(
				this.getLeitEfetuadasIndustrial().intValue()+
				this.getLeitEfetuadasComercial().intValue() + 
				this.getLeitEfetuadasResidencial().intValue() +
				this.getLeitEfetuadasPublico().intValue()
		);
	}
	public Integer getLeitAnormalidadesTotalCategoria() {
		return new Integer(
				this.getLeitAnormalidadesIndustrial().intValue()+
				this.getLeitAnormalidadesComercial().intValue() + 
				this.getLeitAnormalidadesResidencial().intValue() +
				this.getLeitAnormalidadesPublico().intValue()
		);
	}
	public Integer getAvisoCorteTotalCategoria() {
		return new Integer(
				this.getAvisoCorteIndustrial().intValue()+
				this.getAvisoCorteComercial().intValue() + 
				this.getAvisoCorteResidencial().intValue() +
				this.getAvisoCortePublico().intValue()
		);
	}
	
	public BigDecimal getPercAnormalidadeTotalCategoria() {
		if(this.getRegRecebidosTotalCategoria().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getRegAnormalidadeInformadaTotalCategoria());
		BigDecimal tempDenominador = new BigDecimal(this.getRegRecebidosTotalCategoria());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public BigDecimal getPercHidrometracaoTotalCategoria() {
		if(this.getLigAtivasTotalCategoria().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getLigAtivasHidrometroTotalCategoria());
		BigDecimal tempDenominador = new BigDecimal(this.getLigAtivasTotalCategoria());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	
	public Integer getLigImplantadasAguaTotalCategoria() {
		return new Integer(
				this.getLigImplantadasAguaIndustrial().intValue()+
				this.getLigImplantadasAguaComercial().intValue() + 
				this.getLigImplantadasAguaResidencial().intValue() +
				this.getLigImplantadasAguaPublico().intValue()
		);
	}
	public Integer getLigImplantadasEsgotoTotalCategoria() {
		return new Integer(
				this.getLigImplantadasEsgotoIndustrial().intValue()+
				this.getLigImplantadasEsgotoComercial().intValue() + 
				this.getLigImplantadasEsgotoResidencial().intValue() +
				this.getLigImplantadasEsgotoPublico().intValue()
		);
	}
	public Integer getRaPendentesComPrazoTotalCategoria() {
		return new Integer(
				this.getRaPendentesComPrazoIndustrial().intValue()+
				this.getRaPendentesComPrazoComercial().intValue() + 
				this.getRaPendentesComPrazoResidencial().intValue() +
				this.getRaPendentesComPrazoPublico().intValue()
		);
	}
	public Integer getRaPendentesComForaPrazoTotalCategoria() {
		return new Integer(
				this.getRaPendentesComForaPrazoIndustrial().intValue()+
				this.getRaPendentesComForaPrazoComercial().intValue() + 
				this.getRaPendentesComForaPrazoResidencial().intValue() +
				this.getRaPendentesComForaPrazoPublico().intValue()
		);
	}
	public Integer getRaPendentesOpPrazoTotalCategoria() {
		return new Integer(
				this.getRaPendentesOpPrazoIndustrial().intValue()+
				this.getRaPendentesOpPrazoComercial().intValue() + 
				this.getRaPendentesOpPrazoResidencial().intValue() +
				this.getRaPendentesOpPrazoPublico().intValue()
		);
	}
	public Integer getRaPendentesOpForaPrazoTotalCategoria() {
		return new Integer(
				this.getRaPendentesOpForaPrazoIndustrial().intValue()+
				this.getRaPendentesOpForaPrazoComercial().intValue() + 
				this.getRaPendentesOpForaPrazoResidencial().intValue() +
				this.getRaPendentesOpForaPrazoPublico().intValue()
		);
	}
	public Integer getFaturaRevisaoTotalCategoria() {
		return new Integer(
				this.getFaturaRevisaoIndustrial().intValue()+
				this.getFaturaRevisaoComercial().intValue() + 
				this.getFaturaRevisaoResidencial().intValue() +
				this.getFaturaRevisaoPublico().intValue()
		);
	}
	public Integer getCartasNegativacaoTotalCategoria() {
		return new Integer(
				this.getCartasNegativacaoIndustrial().intValue()+
				this.getCartasNegativacaoComercial().intValue() + 
				this.getCartasNegativacaoResidencial().intValue() +
				this.getCartasNegativacaoPublico().intValue()
		);
	}
	public Integer getEconomiaAtivaTotalCategoria() {
		return new Integer(
				this.getEconomiaAtivaIndustrial().intValue()+
				this.getEconomiaAtivaComercial().intValue() + 
				this.getEconomiaAtivaResidencial().intValue() +
				this.getEconomiaAtivaPublico().intValue()
		);
	}
	public Integer getEconomiaInativaTotalCategoria() {
		return new Integer(
				this.getEconomiaInativaIndustrial().intValue()+
				this.getEconomiaInativaComercial().intValue() + 
				this.getEconomiaInativaResidencial().intValue() +
				this.getEconomiaInativaPublico().intValue()
		);
	}
	public Integer getLigacoesAtivasTotalCategoria() {
		return new Integer(
				this.getLigacoesAtivasIndustrial().intValue()+
				this.getLigacoesAtivasComercial().intValue() + 
				this.getLigacoesAtivasResidencial().intValue() +
				this.getLigacoesAtivasPublico().intValue()
		);
	}
	public Integer getLigacoesInativasTotalCategoria() {
		return new Integer(
				this.getLigacoesInativasIndustrial().intValue()+
				this.getLigacoesInativasComercial().intValue() + 
				this.getLigacoesInativasResidencial().intValue() +
				this.getLigacoesInativasPublico().intValue()
		);
	}
	public Integer getHidSubstituidosPocoTotalPerfil() {
		return new Integer(
				this.getHidSubstituidosPocoNormal().intValue()+
				this.getHidSubstituidosPocoTarSocial().intValue() + 
				this.getHidSubstituidosPocoGrande().intValue() +
				this.getHidSubstituidosPocoCorporativo().intValue()+
				this.getHidSubstituidosPocoGrandeTelemedido().intValue()+
				this.getHidSubstituidosPocoCorpTelemedido().intValue()+
				this.getHidSubstituidosPocoChafariz().intValue() +
				this.getHidSubstituidosPocoMicroTelemedido().intValue() +
				this.getHidSubstituidosPocoCadastroProvisorio().intValue()
		);
	}
	
	public Integer getHidSubstituidosRamalTotalPerfil() {
		return new Integer(
				this.getHidSubstituidosRamalNormal().intValue()+
				this.getHidSubstituidosRamalTarSocial().intValue() + 
				this.getHidSubstituidosRamalGrande().intValue() +
				this.getHidSubstituidosRamalCorporativo().intValue()+
				this.getHidSubstituidosRamalGrandeTelemedido().intValue()+
				this.getHidSubstituidosRamalCorpTelemedido().intValue()+
				this.getHidSubstituidosRamalChafariz().intValue() +
				this.getHidSubstituidosRamalMicroTelemedido().intValue() +
				this.getHidSubstituidosRamalCadastroProvisorio().intValue()

		);
	}
	
	
	public Integer getHidInstaladosPocoTotalPerfil() {
		return new Integer(
				this.getHidInstaladosPocoNormal().intValue()+
				this.getHidInstaladosPocoTarSocial().intValue() + 
				this.getHidInstaladosPocoGrande().intValue() +
				this.getHidInstaladosPocoCorporativo().intValue()+
				this.getHidInstaladosPocoGrandeTelemedido().intValue()+
				this.getHidInstaladosPocoCorpTelemedido().intValue()+
				this.getHidInstaladosPocoChafariz().intValue() +
				this.getHidInstaladosPocoMicroTelemedido().intValue() +
				this.getHidInstaladosPocoCadastroProvisorio().intValue()
		);
	}
	
	public Integer getHidInstaladosRamalTotalPerfil() {
		return new Integer(
				this.getHidInstaladosRamalNormal().intValue()+
				this.getHidInstaladosRamalTarSocial().intValue() + 
				this.getHidInstaladosRamalGrande().intValue() +
				this.getHidInstaladosRamalCorporativo().intValue()+
				this.getHidInstaladosRamalGrandeTelemedido().intValue()+
				this.getHidInstaladosRamalCorpTelemedido().intValue()+
				this.getHidInstaladosRamalChafariz().intValue() +
				this.getHidInstaladosRamalMicroTelemedido().intValue() +
				this.getHidInstaladosRamalCadastroProvisorio().intValue()
		);
	}
	
	
	public Integer getCortesExecutadosTotalPerfil() {
		return new Integer(
				this.getCortesExecutadosNormal().intValue()+
				this.getCortesExecutadosTarSocial().intValue() + 
				this.getCortesExecutadosGrande().intValue() +
				this.getCortesExecutadosCorporativo().intValue()+
				this.getCortesExecutadosGrandeTelemedido().intValue()+
				this.getCortesExecutadosCorpTelemedido().intValue()+
				this.getCortesExecutadosChafariz().intValue() +
				this.getCortesExecutadosMicroTelemedido().intValue() +
				this.getCortesExecutadosCadastroProvisorio().intValue()
		);
	}
	public Integer getSuprExecutadasTotalPerfil() {
		return new Integer(
				this.getSuprExecutadasNormal().intValue()+
				this.getSuprExecutadasTarSocial().intValue() + 
				this.getSuprExecutadasGrande().intValue() +
				this.getSuprExecutadasCorporativo().intValue()+
				this.getSuprExecutadasGrandeTelemedido().intValue()+
				this.getSuprExecutadasCorpTelemedido().intValue()+
				this.getSuprExecutadasChafariz().intValue() +
				this.getSuprExecutadasMicroTelemedido().intValue() +
				this.getSuprExecutadasCadastroProvisorio().intValue()
		);
	}
	public Integer getReligacoesTotalPerfil() {
		return new Integer(
				this.getReligacoesNormal().intValue()+
				this.getReligacoesTarSocial().intValue() + 
				this.getReligacoesGrande().intValue() +
				this.getReligacoesCorporativo().intValue()+
				this.getReligacoesGrandeTelemedido().intValue()+
				this.getReligacoesCorpTelemedido().intValue()+
				this.getReligacoesChafariz().intValue() +
				this.getReligacoesMicroTelemedido().intValue() +
				this.getReligacoesCadastroProvisorio().intValue()
		);
	}
	public Integer getReestabTotalPerfil() {
		return new Integer(
				this.getReestabNormal().intValue()+
				this.getReestabTarSocial().intValue() + 
				this.getReestabGrande().intValue() +
				this.getReestabCorporativo().intValue()+
				this.getReestabGrandeTelemedido().intValue()+
				this.getReestabCorpTelemedido().intValue()+
				this.getReestabChafariz().intValue() +
				this.getReestabMicroTelemedido().intValue() +
				this.getReestabCadastroProvisorio().intValue()
		);
	}
	public Integer getClandCortadosTotalPerfil() {
		return new Integer(
				this.getClandCortadosNormal().intValue()+
				this.getClandCortadosTarSocial().intValue() + 
				this.getClandCortadosGrande().intValue() +
				this.getClandCortadosCorporativo().intValue()+
				this.getClandCortadosGrandeTelemedido().intValue()+
				this.getClandCortadosCorpTelemedido().intValue()+
				this.getClandCortadosChafariz().intValue() +
				this.getClandCortadosMicroTelemedido().intValue() +
				this.getClandCortadosCadastroProvisorio().intValue()
		);
	}
	public Integer getClandSuprimidosTotalPerfil() {
		return new Integer(
				this.getClandSuprimidosNormal().intValue()+
				this.getClandSuprimidosTarSocial().intValue() + 
				this.getClandSuprimidosGrande().intValue() +
				this.getClandSuprimidosCorporativo().intValue()+
				this.getClandSuprimidosGrandeTelemedido().intValue()+
				this.getClandSuprimidosCorpTelemedido().intValue()+
				this.getClandSuprimidosChafariz().intValue() +
				this.getClandSuprimidosMicroTelemedido().intValue() +
				this.getClandSuprimidosCadastroProvisorio().intValue()
		);
	}
	public Integer getContasEmitidasTotalPerfil() {
		return new Integer(
				this.getContasEmitidasNormal().intValue()+
				this.getContasEmitidasTarSocial().intValue() + 
				this.getContasEmitidasGrande().intValue() +
				this.getContasEmitidasCorporativo().intValue()+
				this.getContasEmitidasGrandeTelemedido().intValue()+
				this.getContasEmitidasCorpTelemedido().intValue()+
				this.getContasEmitidasChafariz().intValue() +
				this.getContasEmitidasMicroTelemedido().intValue() +
				this.getContasEmitidasCadastroProvisorio().intValue()
		);
	}
	public Integer getLeitEfetuadasTotalPerfil() {
		return new Integer(
				this.getLeitEfetuadasNormal().intValue()+
				this.getLeitEfetuadasTarSocial().intValue() + 
				this.getLeitEfetuadasGrande().intValue() +
				this.getLeitEfetuadasCorporativo().intValue()+
				this.getLeitEfetuadasGrandeTelemedido().intValue()+
				this.getLeitEfetuadasCorpTelemedido().intValue()+
				this.getLeitEfetuadasChafariz().intValue() +
				this.getLeitEfetuadasMicroTelemedido().intValue() +
				this.getLeitEfetuadasCadastroProvisorio().intValue()
		);
	}
	public Integer getLeitAnormalidadesTotalPerfil() {
		return new Integer(
				this.getLeitAnormalidadesNormal().intValue()+
				this.getLeitAnormalidadesTarSocial().intValue() + 
				this.getLeitAnormalidadesGrande().intValue() +
				this.getLeitAnormalidadesCorporativo().intValue()+
				this.getLeitAnormalidadesGrandeTelemedido().intValue()+
				this.getLeitAnormalidadesCorpTelemedido().intValue()+
				this.getLeitAnormalidadesChafariz().intValue() +
				this.getLeitAnormalidadesMicroTelemedido().intValue() +
				this.getLeitAnormalidadesCadastroProvisorio().intValue()
		);
	}
	public Integer getAvisoCorteTotalPerfil() {
		return new Integer(
				this.getAvisoCorteNormal().intValue()+
				this.getAvisoCorteTarSocial().intValue() + 
				this.getAvisoCorteGrande().intValue() +
				this.getAvisoCorteCorporativo().intValue()+
				this.getAvisoCorteGrandeTelemedido().intValue()+
				this.getAvisoCorteCorpTelemedido().intValue()+
				this.getAvisoCorteChafariz().intValue() +
				this.getAvisoCorteMicroTelemedido().intValue() +
				this.getAvisoCorteCadastroProvisorio().intValue()
		);
	}
	public BigDecimal getPercAnormalidadeTotalPerfil() {
		if(this.getRegRecebidosTotalPerfil().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getRegAnormalidadeInformadaTotalPerfil());
		BigDecimal tempDenominador = new BigDecimal(this.getRegRecebidosTotalPerfil());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	
	public BigDecimal getPercHidrometracaoTotalPerfil() {
		if(this.getLigAtivasTotalPerfil().compareTo(new Integer(0)) == 0)
			return new BigDecimal(0);
		
		BigDecimal tempNumerador = new BigDecimal(this.getLigAtivasHidrometroTotalPerfil());
		BigDecimal tempDenominador = new BigDecimal(this.getLigAtivasTotalPerfil());
		
		return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
	}
	public Integer getLigImplantadasAguaTotalPerfil() {
		return new Integer(
				this.getLigImplantadasAguaNormal().intValue()+
				this.getLigImplantadasAguaTarSocial().intValue() + 
				this.getLigImplantadasAguaGrande().intValue() +
				this.getLigImplantadasAguaCorporativo().intValue()+
				this.getLigImplantadasAguaGrandeTelemedido().intValue()+
				this.getLigImplantadasAguaCorpTelemedido().intValue()+
				this.getLigImplantadasAguaChafariz().intValue() +
				this.getLigImplantadasAguaMicroTelemedido().intValue() +
				this.getLigImplantadasAguaCadastroProvisorio().intValue()
		);
	}
	public Integer getLigImplantadasEsgotoTotalPerfil() {
		return new Integer(
				this.getLigImplantadasEsgotoNormal().intValue()+
				this.getLigImplantadasEsgotoTarSocial().intValue() + 
				this.getLigImplantadasEsgotoGrande().intValue() +
				this.getLigImplantadasEsgotoCorporativo().intValue()+
				this.getLigImplantadasEsgotoGrandeTelemedido().intValue()+
				this.getLigImplantadasEsgotoCorpTelemedido().intValue()+
				this.getLigImplantadasEsgotoChafariz().intValue() +
				this.getLigImplantadasEsgotoMicroTelemedido().intValue() +
				this.getLigImplantadasEsgotoCadastroProvisorio().intValue()
		);
	}
	public Integer getRaPendentesComPrazoTotalPerfil() {
		return new Integer(
				this.getRaPendentesComPrazoNormal().intValue()+
				this.getRaPendentesComPrazoTarSocial().intValue() + 
				this.getRaPendentesComPrazoGrande().intValue() +
				this.getRaPendentesComPrazoCorporativo().intValue()+
				this.getRaPendentesComPrazoGrandeTelemedido().intValue()+
				this.getRaPendentesComPrazoCorpTelemedido().intValue()+
				this.getRaPendentesComPrazoChafariz().intValue() +
				this.getRaPendentesComPrazoMicroTelemedido().intValue() +
				this.getRaPendentesComPrazoCadastroProvisorio().intValue()
		);
	}
	public Integer getRaPendentesComForaPrazoTotalPerfil() {
		return new Integer(
				this.getRaPendentesComForaPrazoNormal().intValue()+
				this.getRaPendentesComForaPrazoTarSocial().intValue() + 
				this.getRaPendentesComForaPrazoGrande().intValue() +
				this.getRaPendentesComForaPrazoCorporativo().intValue()+
				this.getRaPendentesComForaPrazoGrandeTelemedido().intValue()+
				this.getRaPendentesComForaPrazoCorpTelemedido().intValue()+
				this.getRaPendentesComForaPrazoChafariz().intValue() +
				this.getRaPendentesComForaPrazoMicroTelemedido().intValue() +
				this.getRaPendentesComForaPrazoCadastroProvisorio().intValue()
		);
	}
	public Integer getRaPendentesOpPrazoTotalPerfil() {
		return new Integer(
				this.getRaPendentesOpPrazoNormal().intValue()+
				this.getRaPendentesOpPrazoTarSocial().intValue() + 
				this.getRaPendentesOpPrazoGrande().intValue() +
				this.getRaPendentesOpPrazoCorporativo().intValue()+
				this.getRaPendentesOpPrazoGrandeTelemedido().intValue()+
				this.getRaPendentesOpPrazoCorpTelemedido().intValue()+
				this.getRaPendentesOpPrazoChafariz().intValue() +
				this.getRaPendentesOpPrazoMicroTelemedido().intValue() +
				this.getRaPendentesOpPrazoCadastroProvisorio().intValue()
		);
	}
	public Integer getRaPendentesOpForaPrazoTotalPerfil() {
		return new Integer(
				this.getRaPendentesOpForaPrazoNormal().intValue()+
				this.getRaPendentesOpForaPrazoTarSocial().intValue() + 
				this.getRaPendentesOpForaPrazoGrande().intValue() +
				this.getRaPendentesOpForaPrazoCorporativo().intValue()+
				this.getRaPendentesOpForaPrazoGrandeTelemedido().intValue()+
				this.getRaPendentesOpForaPrazoCorpTelemedido().intValue()+
				this.getRaPendentesOpForaPrazoChafariz().intValue() +
				this.getRaPendentesOpForaPrazoMicroTelemedido().intValue() +
				this.getRaPendentesOpForaPrazoCadastroProvisorio().intValue()
		);
	}
	public Integer getFaturaRevisaoTotalPerfil() {
		return new Integer(
				this.getFaturaRevisaoNormal().intValue()+
				this.getFaturaRevisaoTarSocial().intValue() + 
				this.getFaturaRevisaoGrande().intValue() +
				this.getFaturaRevisaoCorporativo().intValue()+
				this.getFaturaRevisaoGrandeTelemedido().intValue()+
				this.getFaturaRevisaoCorpTelemedido().intValue()+
				this.getFaturaRevisaoChafariz().intValue() +
				this.getFaturaRevisaoMicroTelemedido().intValue() +
				this.getFaturaRevisaoCadastroProvisorio().intValue()
		);
	}
	public Integer getCartasNegativacaoTotalPerfil() {
		return new Integer(
				this.getCartasNegativacaoNormal().intValue()+
				this.getCartasNegativacaoTarSocial().intValue() + 
				this.getCartasNegativacaoGrande().intValue() +
				this.getCartasNegativacaoCorporativo().intValue()+
				this.getCartasNegativacaoGrandeTelemedido().intValue()+
				this.getCartasNegativacaoCorpTelemedido().intValue()+
				this.getCartasNegativacaoChafariz().intValue() +
				this.getCartasNegativacaoMicroTelemedido().intValue() +
				this.getCartasNegativacaoCadastroProvisorio().intValue()
		);
	}
	public Integer getEconomiaAtivaTotalPerfil() {
		return new Integer(
				this.getEconomiaAtivaNormal().intValue()+
				this.getEconomiaAtivaTarSocial().intValue() + 
				this.getEconomiaAtivaGrande().intValue() +
				this.getEconomiaAtivaCorporativo().intValue()+
				this.getEconomiaAtivaGrandeTelemedido().intValue()+
				this.getEconomiaAtivaCorpTelemedido().intValue()+
				this.getEconomiaAtivaChafariz().intValue() +
				this.getEconomiaAtivaMicroTelemedido().intValue() +
				this.getEconomiaAtivaCadastroProvisorio().intValue()
		);
	}
	public Integer getEconomiaInativaTotalPerfil() {
		return new Integer(
				this.getEconomiaInativaNormal().intValue()+
				this.getEconomiaInativaTarSocial().intValue() + 
				this.getEconomiaInativaGrande().intValue() +
				this.getEconomiaInativaCorporativo().intValue()+
				this.getEconomiaInativaGrandeTelemedido().intValue()+
				this.getEconomiaInativaCorpTelemedido().intValue()+
				this.getEconomiaInativaChafariz().intValue() +
				this.getEconomiaInativaMicroTelemedido().intValue() +
				this.getEconomiaInativaCadastroProvisorio().intValue()
		);
	}
	public Integer getLigacoesAtivasTotalPerfil() {
		return new Integer(
				this.getLigacoesAtivasNormal().intValue()+
				this.getLigacoesAtivasTarSocial().intValue() + 
				this.getLigacoesAtivasGrande().intValue() +
				this.getLigacoesAtivasCorporativo().intValue()+
				this.getLigacoesAtivasGrandeTelemedido().intValue()+
				this.getLigacoesAtivasCorpTelemedido().intValue()+
				this.getLigacoesAtivasChafariz().intValue() +
				this.getLigacoesAtivasMicroTelemedido().intValue() +
				this.getLigacoesAtivasCadastroProvisorio().intValue()
		);
	}
	public Integer getLigacoesInativasTotalPerfil() {
		return new Integer(
				this.getLigacoesInativasNormal().intValue()+
				this.getLigacoesInativasTarSocial().intValue() + 
				this.getLigacoesInativasGrande().intValue() +
				this.getLigacoesInativasCorporativo().intValue()+
				this.getLigacoesInativasGrandeTelemedido().intValue()+
				this.getLigacoesInativasCorpTelemedido().intValue()+
				this.getLigacoesInativasChafariz().intValue() +
				this.getLigacoesInativasMicroTelemedido().intValue() +
				this.getLigacoesInativasCadastroProvisorio().intValue()
		);
	}
	public void setGerenciaRegionalID(String gerenciaRegionalID) {
		this.gerenciaRegionalID = gerenciaRegionalID;
	}
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public void setLocalidadeID(String localidadeID) {
		this.localidadeID = localidadeID;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public void setUnidadeNegocioID(String unidadeNegocioID) {
		this.unidadeNegocioID = unidadeNegocioID;
	}
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	public void setMunicipioID(String municipioID) {
		this.municipioID = municipioID;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public void setHidSubstituidosPocoResidencial(Integer hidSubstituidosPocoResidencial) {
		this.hidSubstituidosPocoResidencial = hidSubstituidosPocoResidencial;
	}
	public void setHidInstaladosPocoResidencial(Integer hidInstaladosPocoResidencial) {
		this.hidInstaladosPocoResidencial = hidInstaladosPocoResidencial;
	}
	
	public void setHidSubstituidosRamalResidencial(Integer hidSubstituidosRamalResidencial) {
		this.hidSubstituidosRamalResidencial = hidSubstituidosRamalResidencial;
	}
	public void setHidInstaladosRamalResidencial(Integer hidInstaladosRamalResidencial) {
		this.hidInstaladosRamalResidencial = hidInstaladosRamalResidencial;
	}
	
	public void setCortesExecutadosResidencial(Integer cortesExecutadosResidencial) {
		this.cortesExecutadosResidencial = cortesExecutadosResidencial;
	}
	public void setSuprExecutadasResidencial(Integer suprExecutadasResidencial) {
		this.suprExecutadasResidencial = suprExecutadasResidencial;
	}
	public void setReligacoesResidencial(Integer religacoesResidencial) {
		this.religacoesResidencial = religacoesResidencial;
	}
	public void setReestabResidencial(Integer reestabResidencial) {
		this.reestabResidencial = reestabResidencial;
	}
	public void setClandCortadosResidencial(Integer clandCortadosResidencial) {
		this.clandCortadosResidencial = clandCortadosResidencial;
	}
	public void setClandSuprimidosResidencial(Integer clandSuprimidosResidencial) {
		this.clandSuprimidosResidencial = clandSuprimidosResidencial;
	}
	public void setContasEmitidasResidencial(Integer contasEmitidasResidencial) {
		this.contasEmitidasResidencial = contasEmitidasResidencial;
	}
	public void setLeitEfetuadasResidencial(Integer leitEfetuadasResidencial) {
		this.leitEfetuadasResidencial = leitEfetuadasResidencial;
	}
	public void setLeitAnormalidadesResidencial(Integer leitAnormalidadesResidencial) {
		this.leitAnormalidadesResidencial = leitAnormalidadesResidencial;
	}
	public void setAvisoCorteResidencial(Integer avisoCorteResidencial) {
		this.avisoCorteResidencial = avisoCorteResidencial;
	}
	public void setPercAnormalidadeResidencial(
			BigDecimal percAnormalidadeResidencial) {
		this.percAnormalidadeResidencial = percAnormalidadeResidencial;
	}
	public void setPercHidrometracaoResidencial(
			BigDecimal percHidrometracaoResidencial) {
		this.percHidrometracaoResidencial = percHidrometracaoResidencial;
	}
	public void setLigImplantadasAguaResidencial(
			Integer ligImplantadasAguaResidencial) {
		this.ligImplantadasAguaResidencial = ligImplantadasAguaResidencial;
	}
	public void setLigImplantadasEsgotoResidencial(
			Integer ligImplantadasEsgotoResidencial) {
		this.ligImplantadasEsgotoResidencial = ligImplantadasEsgotoResidencial;
	}
	public void setRaPendentesComPrazoResidencial(
			Integer raPendentesComPrazoResidencial) {
		this.raPendentesComPrazoResidencial = raPendentesComPrazoResidencial;
	}
	public void setRaPendentesComForaPrazoResidencial(
			Integer raPendentesComForaPrazoResidencial) {
		this.raPendentesComForaPrazoResidencial = raPendentesComForaPrazoResidencial;
	}
	public void setRaPendentesOpPrazoResidencial(
			Integer raPendentesOpPrazoResidencial) {
		this.raPendentesOpPrazoResidencial = raPendentesOpPrazoResidencial;
	}
	public void setRaPendentesOpForaPrazoResidencial(
			Integer raPendentesOpForaPrazoResidencial) {
		this.raPendentesOpForaPrazoResidencial = raPendentesOpForaPrazoResidencial;
	}
	public void setFaturaRevisaoResidencial(Integer faturaRevisaoResidencial) {
		this.faturaRevisaoResidencial = faturaRevisaoResidencial;
	}
	public void setCartasNegativacaoResidencial(Integer cartasNegativacaoResidencial) {
		this.cartasNegativacaoResidencial = cartasNegativacaoResidencial;
	}
	public void setEconomiaAtivaResidencial(Integer economiaAtivaResidencial) {
		this.economiaAtivaResidencial = economiaAtivaResidencial;
	}
	public void setEconomiaInativaResidencial(Integer economiaInativaResidencial) {
		this.economiaInativaResidencial = economiaInativaResidencial;
	}
	public void setLigacoesAtivasResidencial(Integer ligacoesAtivasResidencial) {
		this.ligacoesAtivasResidencial = ligacoesAtivasResidencial;
	}
	public void setLigacoesInativasResidencial(Integer ligacoesInativasResidencial) {
		this.ligacoesInativasResidencial = ligacoesInativasResidencial;
	}
	public void setHidSubstituidosPocoComercial(Integer hidSubstituidosPocoComercial) {
		this.hidSubstituidosPocoComercial = hidSubstituidosPocoComercial;
	}
	public void setHidInstaladosPocoComercial(Integer hidInstaladosPocoComercial) {
		this.hidInstaladosPocoComercial = hidInstaladosPocoComercial;
	}
	
	public void setHidSubstituidosRamalComercial(Integer hidSubstituidosRamalComercial) {
		this.hidSubstituidosRamalComercial = hidSubstituidosRamalComercial;
	}
	public void setHidInstaladosRamalComercial(Integer hidInstaladosRamalComercial) {
		this.hidInstaladosRamalComercial = hidInstaladosRamalComercial;
	}
	public void setCortesExecutadosComercial(Integer cortesExecutadosComercial) {
		this.cortesExecutadosComercial = cortesExecutadosComercial;
	}
	public void setSuprExecutadasComercial(Integer suprExecutadasComercial) {
		this.suprExecutadasComercial = suprExecutadasComercial;
	}
	public void setReligacoesComercial(Integer religacoesComercial) {
		this.religacoesComercial = religacoesComercial;
	}
	public void setReestabComercial(Integer reestabComercial) {
		this.reestabComercial = reestabComercial;
	}
	public void setClandCortadosComercial(Integer clandCortadosComercial) {
		this.clandCortadosComercial = clandCortadosComercial;
	}
	public void setClandSuprimidosComercial(Integer clandSuprimidosComercial) {
		this.clandSuprimidosComercial = clandSuprimidosComercial;
	}
	public void setContasEmitidasComercial(Integer contasEmitidasComercial) {
		this.contasEmitidasComercial = contasEmitidasComercial;
	}
	public void setLeitEfetuadasComercial(Integer leitEfetuadasComercial) {
		this.leitEfetuadasComercial = leitEfetuadasComercial;
	}
	public void setLeitAnormalidadesComercial(Integer leitAnormalidadesComercial) {
		this.leitAnormalidadesComercial = leitAnormalidadesComercial;
	}
	public void setAvisoCorteComercial(Integer avisoCorteComercial) {
		this.avisoCorteComercial = avisoCorteComercial;
	}
	public void setPercAnormalidadeComercial(BigDecimal percAnormalidadeComercial) {
		this.percAnormalidadeComercial = percAnormalidadeComercial;
	}
	public void setPercHidrometracaoComercial(BigDecimal percHidrometracaoComercial) {
		this.percHidrometracaoComercial = percHidrometracaoComercial;
	}
	public void setLigImplantadasAguaComercial(Integer ligImplantadasAguaComercial) {
		this.ligImplantadasAguaComercial = ligImplantadasAguaComercial;
	}
	public void setLigImplantadasEsgotoComercial(
			Integer ligImplantadasEsgotoComercial) {
		this.ligImplantadasEsgotoComercial = ligImplantadasEsgotoComercial;
	}
	public void setRaPendentesComPrazoComercial(Integer raPendentesComPrazoComercial) {
		this.raPendentesComPrazoComercial = raPendentesComPrazoComercial;
	}
	public void setRaPendentesComForaPrazoComercial(
			Integer raPendentesComForaPrazoComercial) {
		this.raPendentesComForaPrazoComercial = raPendentesComForaPrazoComercial;
	}
	public void setRaPendentesOpPrazoComercial(Integer raPendentesOpPrazoComercial) {
		this.raPendentesOpPrazoComercial = raPendentesOpPrazoComercial;
	}
	public void setRaPendentesOpForaPrazoComercial(
			Integer raPendentesOpForaPrazoComercial) {
		this.raPendentesOpForaPrazoComercial = raPendentesOpForaPrazoComercial;
	}
	public void setFaturaRevisaoComercial(Integer faturaRevisaoComercial) {
		this.faturaRevisaoComercial = faturaRevisaoComercial;
	}
	public void setCartasNegativacaoComercial(Integer cartasNegativacaoComercial) {
		this.cartasNegativacaoComercial = cartasNegativacaoComercial;
	}
	public void setEconomiaAtivaComercial(Integer economiaAtivaComercial) {
		this.economiaAtivaComercial = economiaAtivaComercial;
	}
	public void setEconomiaInativaComercial(Integer economiaInativaComercial) {
		this.economiaInativaComercial = economiaInativaComercial;
	}
	public void setLigacoesAtivasComercial(Integer ligacoesAtivasComercial) {
		this.ligacoesAtivasComercial = ligacoesAtivasComercial;
	}
	public void setLigacoesInativasComercial(Integer ligacoesInativasComercial) {
		this.ligacoesInativasComercial = ligacoesInativasComercial;
	}
	public void setHidSubstituidosPocoIndustrial(Integer hidSubstituidosPocoIndustrial) {
		this.hidSubstituidosPocoIndustrial = hidSubstituidosPocoIndustrial;
	}
	public void setHidInstaladosPocoIndustrial(Integer hidInstaladosPocoIndustrial) {
		this.hidInstaladosPocoIndustrial = hidInstaladosPocoIndustrial;
	}
	
	public void setHidSubstituidosRamalIndustrial(Integer hidSubstituidosRamalIndustrial) {
		this.hidSubstituidosRamalIndustrial = hidSubstituidosRamalIndustrial;
	}
	public void setHidInstaladosRamalIndustrial(Integer hidInstaladosRamalIndustrial) {
		this.hidInstaladosRamalIndustrial = hidInstaladosRamalIndustrial;
	}
	public void setCortesExecutadosIndustrial(Integer cortesExecutadosIndustrial) {
		this.cortesExecutadosIndustrial = cortesExecutadosIndustrial;
	}
	public void setSuprExecutadasIndustrial(Integer suprExecutadasIndustrial) {
		this.suprExecutadasIndustrial = suprExecutadasIndustrial;
	}
	public void setReligacoesIndustrial(Integer religacoesIndustrial) {
		this.religacoesIndustrial = religacoesIndustrial;
	}
	public void setReestabIndustrial(Integer reestabIndustrial) {
		this.reestabIndustrial = reestabIndustrial;
	}
	public void setClandCortadosIndustrial(Integer clandCortadosIndustrial) {
		this.clandCortadosIndustrial = clandCortadosIndustrial;
	}
	public void setClandSuprimidosIndustrial(Integer clandSuprimidosIndustrial) {
		this.clandSuprimidosIndustrial = clandSuprimidosIndustrial;
	}
	public void setContasEmitidasIndustrial(Integer contasEmitidasIndustrial) {
		this.contasEmitidasIndustrial = contasEmitidasIndustrial;
	}
	public void setLeitEfetuadasIndustrial(Integer leitEfetuadasIndustrial) {
		this.leitEfetuadasIndustrial = leitEfetuadasIndustrial;
	}
	public void setLeitAnormalidadesIndustrial(Integer leitAnormalidadesIndustrial) {
		this.leitAnormalidadesIndustrial = leitAnormalidadesIndustrial;
	}
	public void setAvisoCorteIndustrial(Integer avisoCorteIndustrial) {
		this.avisoCorteIndustrial = avisoCorteIndustrial;
	}
	public void setPercAnormalidadeIndustrial(BigDecimal percAnormalidadeIndustrial) {
		this.percAnormalidadeIndustrial = percAnormalidadeIndustrial;
	}
	public void setPercHidrometracaoIndustrial(
			BigDecimal percHidrometracaoIndustrial) {
		this.percHidrometracaoIndustrial = percHidrometracaoIndustrial;
	}
	public void setLigImplantadasAguaIndustrial(Integer ligImplantadasAguaIndustrial) {
		this.ligImplantadasAguaIndustrial = ligImplantadasAguaIndustrial;
	}
	public void setLigImplantadasEsgotoIndustrial(
			Integer ligImplantadasEsgotoIndustrial) {
		this.ligImplantadasEsgotoIndustrial = ligImplantadasEsgotoIndustrial;
	}
	public void setRaPendentesComPrazoIndustrial(
			Integer raPendentesComPrazoIndustrial) {
		this.raPendentesComPrazoIndustrial = raPendentesComPrazoIndustrial;
	}
	public void setRaPendentesComForaPrazoIndustrial(
			Integer raPendentesComForaPrazoIndustrial) {
		this.raPendentesComForaPrazoIndustrial = raPendentesComForaPrazoIndustrial;
	}
	public void setRaPendentesOpPrazoIndustrial(Integer raPendentesOpPrazoIndustrial) {
		this.raPendentesOpPrazoIndustrial = raPendentesOpPrazoIndustrial;
	}
	public void setRaPendentesOpForaPrazoIndustrial(
			Integer raPendentesOpForaPrazoIndustrial) {
		this.raPendentesOpForaPrazoIndustrial = raPendentesOpForaPrazoIndustrial;
	}
	public void setFaturaRevisaoIndustrial(Integer faturaRevisaoIndustrial) {
		this.faturaRevisaoIndustrial = faturaRevisaoIndustrial;
	}
	public void setCartasNegativacaoIndustrial(Integer cartasNegativacaoIndustrial) {
		this.cartasNegativacaoIndustrial = cartasNegativacaoIndustrial;
	}
	public void setEconomiaAtivaIndustrial(Integer economiaAtivaIndustrial) {
		this.economiaAtivaIndustrial = economiaAtivaIndustrial;
	}
	public void setEconomiaInativaIndustrial(Integer economiaInativaIndustrial) {
		this.economiaInativaIndustrial = economiaInativaIndustrial;
	}
	public void setLigacoesAtivasIndustrial(Integer ligacoesAtivasIndustrial) {
		this.ligacoesAtivasIndustrial = ligacoesAtivasIndustrial;
	}
	public void setLigacoesInativasIndustrial(Integer ligacoesInativasIndustrial) {
		this.ligacoesInativasIndustrial = ligacoesInativasIndustrial;
	}
	public void setHidSubstituidosPocoPublico(Integer hidSubstituidosPocoPublico) {
		this.hidSubstituidosPocoPublico = hidSubstituidosPocoPublico;
	}
	public void setHidInstaladosPocoPublico(Integer hidInstaladosPocoPublico) {
		this.hidInstaladosPocoPublico = hidInstaladosPocoPublico;
	}
	
	public void setHidSubstituidosRamalPublico(Integer hidSubstituidosRamalPublico) {
		this.hidSubstituidosRamalPublico = hidSubstituidosRamalPublico;
	}
	public void setHidInstaladosRamalPublico(Integer hidInstaladosRamalPublico) {
		this.hidInstaladosRamalPublico = hidInstaladosRamalPublico;
	}
	public void setCortesExecutadosPublico(Integer cortesExecutadosPublico) {
		this.cortesExecutadosPublico = cortesExecutadosPublico;
	}
	public void setSuprExecutadasPublico(Integer suprExecutadasPublico) {
		this.suprExecutadasPublico = suprExecutadasPublico;
	}
	public void setReligacoesPublico(Integer religacoesPublico) {
		this.religacoesPublico = religacoesPublico;
	}
	public void setReestabPublico(Integer reestabPublico) {
		this.reestabPublico = reestabPublico;
	}
	public void setClandCortadosPublico(Integer clandCortadosPublico) {
		this.clandCortadosPublico = clandCortadosPublico;
	}
	public void setClandSuprimidosPublico(Integer clandSuprimidosPublico) {
		this.clandSuprimidosPublico = clandSuprimidosPublico;
	}
	public void setContasEmitidasPublico(Integer contasEmitidasPublico) {
		this.contasEmitidasPublico = contasEmitidasPublico;
	}
	public void setLeitEfetuadasPublico(Integer leitEfetuadasPublico) {
		this.leitEfetuadasPublico = leitEfetuadasPublico;
	}
	public void setLeitAnormalidadesPublico(Integer leitAnormalidadesPublico) {
		this.leitAnormalidadesPublico = leitAnormalidadesPublico;
	}
	public void setAvisoCortePublico(Integer avisoCortePublico) {
		this.avisoCortePublico = avisoCortePublico;
	}
	public void setPercAnormalidadePublico(BigDecimal percAnormalidadePublico) {
		this.percAnormalidadePublico = percAnormalidadePublico;
	}
	public void setPercHidrometracaoPublico(BigDecimal percHidrometracaoPublico) {
		this.percHidrometracaoPublico = percHidrometracaoPublico;
	}
	public void setLigImplantadasAguaPublico(Integer ligImplantadasAguaPublico) {
		this.ligImplantadasAguaPublico = ligImplantadasAguaPublico;
	}
	public void setLigImplantadasEsgotoPublico(Integer ligImplantadasEsgotoPublico) {
		this.ligImplantadasEsgotoPublico = ligImplantadasEsgotoPublico;
	}
	public void setRaPendentesComPrazoPublico(Integer raPendentesComPrazoPublico) {
		this.raPendentesComPrazoPublico = raPendentesComPrazoPublico;
	}
	public void setRaPendentesComForaPrazoPublico(
			Integer raPendentesComForaPrazoPublico) {
		this.raPendentesComForaPrazoPublico = raPendentesComForaPrazoPublico;
	}
	public void setRaPendentesOpPrazoPublico(Integer raPendentesOpPrazoPublico) {
		this.raPendentesOpPrazoPublico = raPendentesOpPrazoPublico;
	}
	public void setRaPendentesOpForaPrazoPublico(
			Integer raPendentesOpForaPrazoPublico) {
		this.raPendentesOpForaPrazoPublico = raPendentesOpForaPrazoPublico;
	}
	public void setFaturaRevisaoPublico(Integer faturaRevisaoPublico) {
		this.faturaRevisaoPublico = faturaRevisaoPublico;
	}
	public void setCartasNegativacaoPublico(Integer cartasNegativacaoPublico) {
		this.cartasNegativacaoPublico = cartasNegativacaoPublico;
	}
	public void setEconomiaAtivaPublico(Integer economiaAtivaPublico) {
		this.economiaAtivaPublico = economiaAtivaPublico;
	}
	public void setEconomiaInativaPublico(Integer economiaInativaPublico) {
		this.economiaInativaPublico = economiaInativaPublico;
	}
	public void setLigacoesAtivasPublico(Integer ligacoesAtivasPublico) {
		this.ligacoesAtivasPublico = ligacoesAtivasPublico;
	}
	public void setLigacoesInativasPublico(Integer ligacoesInativasPublico) {
		this.ligacoesInativasPublico = ligacoesInativasPublico;
	}
	public void setHidSubstituidosPocoNormal(Integer hidSubstituidosPocoNormal) {
		this.hidSubstituidosPocoNormal = hidSubstituidosPocoNormal;
	}
	public void setHidInstaladosPocoNormal(Integer hidInstaladosPocoNormal) {
		this.hidInstaladosPocoNormal = hidInstaladosPocoNormal;
	}
	
	public void setHidSubstituidosRamalNormal(Integer hidSubstituidosRamalNormal) {
		this.hidSubstituidosRamalNormal = hidSubstituidosRamalNormal;
	}
	public void setHidInstaladosRamalNormal(Integer hidInstaladosRamalNormal) {
		this.hidInstaladosRamalNormal = hidInstaladosRamalNormal;
	}
	public void setCortesExecutadosNormal(Integer cortesExecutadosNormal) {
		this.cortesExecutadosNormal = cortesExecutadosNormal;
	}
	public void setSuprExecutadasNormal(Integer suprExecutadasNormal) {
		this.suprExecutadasNormal = suprExecutadasNormal;
	}
	public void setReligacoesNormal(Integer religacoesNormal) {
		this.religacoesNormal = religacoesNormal;
	}
	public void setReestabNormal(Integer reestabNormal) {
		this.reestabNormal = reestabNormal;
	}
	public void setClandCortadosNormal(Integer clandCortadosNormal) {
		this.clandCortadosNormal = clandCortadosNormal;
	}
	public void setClandSuprimidosNormal(Integer clandSuprimidosNormal) {
		this.clandSuprimidosNormal = clandSuprimidosNormal;
	}
	public void setContasEmitidasNormal(Integer contasEmitidasNormal) {
		this.contasEmitidasNormal = contasEmitidasNormal;
	}
	public void setLeitEfetuadasNormal(Integer leitEfetuadasNormal) {
		this.leitEfetuadasNormal = leitEfetuadasNormal;
	}
	public void setLeitAnormalidadesNormal(Integer leitAnormalidadesNormal) {
		this.leitAnormalidadesNormal = leitAnormalidadesNormal;
	}
	public void setAvisoCorteNormal(Integer avisoCorteNormal) {
		this.avisoCorteNormal = avisoCorteNormal;
	}
	public void setPercAnormalidadeNormal(BigDecimal percAnormalidadeNormal) {
		this.percAnormalidadeNormal = percAnormalidadeNormal;
	}
	public void setPercHidrometracaoNormal(BigDecimal percHidrometracaoNormal) {
		this.percHidrometracaoNormal = percHidrometracaoNormal;
	}
	public void setLigImplantadasAguaNormal(Integer ligImplantadasAguaNormal) {
		this.ligImplantadasAguaNormal = ligImplantadasAguaNormal;
	}
	public void setLigImplantadasEsgotoNormal(Integer ligImplantadasEsgotoNormal) {
		this.ligImplantadasEsgotoNormal = ligImplantadasEsgotoNormal;
	}
	public void setRaPendentesComPrazoNormal(Integer raPendentesComPrazoNormal) {
		this.raPendentesComPrazoNormal = raPendentesComPrazoNormal;
	}
	public void setRaPendentesComForaPrazoNormal(
			Integer raPendentesComForaPrazoNormal) {
		this.raPendentesComForaPrazoNormal = raPendentesComForaPrazoNormal;
	}
	public void setRaPendentesOpPrazoNormal(Integer raPendentesOpPrazoNormal) {
		this.raPendentesOpPrazoNormal = raPendentesOpPrazoNormal;
	}
	public void setRaPendentesOpForaPrazoNormal(Integer raPendentesOpForaPrazoNormal) {
		this.raPendentesOpForaPrazoNormal = raPendentesOpForaPrazoNormal;
	}
	public void setFaturaRevisaoNormal(Integer faturaRevisaoNormal) {
		this.faturaRevisaoNormal = faturaRevisaoNormal;
	}
	public void setCartasNegativacaoNormal(Integer cartasNegativacaoNormal) {
		this.cartasNegativacaoNormal = cartasNegativacaoNormal;
	}
	public void setEconomiaAtivaNormal(Integer economiaAtivaNormal) {
		this.economiaAtivaNormal = economiaAtivaNormal;
	}
	public void setEconomiaInativaNormal(Integer economiaInativaNormal) {
		this.economiaInativaNormal = economiaInativaNormal;
	}
	public void setLigacoesAtivasNormal(Integer ligacoesAtivasNormal) {
		this.ligacoesAtivasNormal = ligacoesAtivasNormal;
	}
	public void setLigacoesInativasNormal(Integer ligacoesInativasNormal) {
		this.ligacoesInativasNormal = ligacoesInativasNormal;
	}
	public void setHidSubstituidosPocoTarSocial(Integer hidSubstituidosPocoTarSocial) {
		this.hidSubstituidosPocoTarSocial = hidSubstituidosPocoTarSocial;
	}
	public void setHidInstaladosPocoTarSocial(Integer hidInstaladosPocoTarSocial) {
		this.hidInstaladosPocoTarSocial = hidInstaladosPocoTarSocial;
	}
	
	public void setHidSubstituidosRamalTarSocial(Integer hidSubstituidosRamalTarSocial) {
		this.hidSubstituidosRamalTarSocial = hidSubstituidosRamalTarSocial;
	}
	public void setHidInstaladosRamalTarSocial(Integer hidInstaladosRamalTarSocial) {
		this.hidInstaladosRamalTarSocial = hidInstaladosRamalTarSocial;
	}
	public void setCortesExecutadosTarSocial(Integer cortesExecutadosTarSocial) {
		this.cortesExecutadosTarSocial = cortesExecutadosTarSocial;
	}
	public void setSuprExecutadasTarSocial(Integer suprExecutadasTarSocial) {
		this.suprExecutadasTarSocial = suprExecutadasTarSocial;
	}
	public void setReligacoesTarSocial(Integer religacoesTarSocial) {
		this.religacoesTarSocial = religacoesTarSocial;
	}
	public void setReestabTarSocial(Integer reestabTarSocial) {
		this.reestabTarSocial = reestabTarSocial;
	}
	public void setClandCortadosTarSocial(Integer clandCortadosTarSocial) {
		this.clandCortadosTarSocial = clandCortadosTarSocial;
	}
	public void setClandSuprimidosTarSocial(Integer clandSuprimidosTarSocial) {
		this.clandSuprimidosTarSocial = clandSuprimidosTarSocial;
	}
	public void setContasEmitidasTarSocial(Integer contasEmitidasTarSocial) {
		this.contasEmitidasTarSocial = contasEmitidasTarSocial;
	}
	public void setLeitEfetuadasTarSocial(Integer leitEfetuadasTarSocial) {
		this.leitEfetuadasTarSocial = leitEfetuadasTarSocial;
	}
	public void setLeitAnormalidadesTarSocial(Integer leitAnormalidadesTarSocial) {
		this.leitAnormalidadesTarSocial = leitAnormalidadesTarSocial;
	}
	public void setAvisoCorteTarSocial(Integer avisoCorteTarSocial) {
		this.avisoCorteTarSocial = avisoCorteTarSocial;
	}
	public void setPercAnormalidadeTarSocial(BigDecimal percAnormalidadeTarSocial) {
		this.percAnormalidadeTarSocial = percAnormalidadeTarSocial;
	}
	public void setPercHidrometracaoTarSocial(BigDecimal percHidrometracaoTarSocial) {
		this.percHidrometracaoTarSocial = percHidrometracaoTarSocial;
	}
	public void setLigImplantadasAguaTarSocial(Integer ligImplantadasAguaTarSocial) {
		this.ligImplantadasAguaTarSocial = ligImplantadasAguaTarSocial;
	}
	public void setLigImplantadasEsgotoTarSocial(
			Integer ligImplantadasEsgotoTarSocial) {
		this.ligImplantadasEsgotoTarSocial = ligImplantadasEsgotoTarSocial;
	}
	public void setRaPendentesComPrazoTarSocial(Integer raPendentesComPrazoTarSocial) {
		this.raPendentesComPrazoTarSocial = raPendentesComPrazoTarSocial;
	}
	public void setRaPendentesComForaPrazoTarSocial(
			Integer raPendentesComForaPrazoTarSocial) {
		this.raPendentesComForaPrazoTarSocial = raPendentesComForaPrazoTarSocial;
	}
	public void setRaPendentesOpPrazoTarSocial(Integer raPendentesOpPrazoTarSocial) {
		this.raPendentesOpPrazoTarSocial = raPendentesOpPrazoTarSocial;
	}
	public void setRaPendentesOpForaPrazoTarSocial(
			Integer raPendentesOpForaPrazoTarSocial) {
		this.raPendentesOpForaPrazoTarSocial = raPendentesOpForaPrazoTarSocial;
	}
	public void setFaturaRevisaoTarSocial(Integer faturaRevisaoTarSocial) {
		this.faturaRevisaoTarSocial = faturaRevisaoTarSocial;
	}
	public void setCartasNegativacaoTarSocial(Integer cartasNegativacaoTarSocial) {
		this.cartasNegativacaoTarSocial = cartasNegativacaoTarSocial;
	}
	public void setEconomiaAtivaTarSocial(Integer economiaAtivaTarSocial) {
		this.economiaAtivaTarSocial = economiaAtivaTarSocial;
	}
	public void setEconomiaInativaTarSocial(Integer economiaInativaTarSocial) {
		this.economiaInativaTarSocial = economiaInativaTarSocial;
	}
	public void setLigacoesAtivasTarSocial(Integer ligacoesAtivasTarSocial) {
		this.ligacoesAtivasTarSocial = ligacoesAtivasTarSocial;
	}
	public void setLigacoesInativasTarSocial(Integer ligacoesInativasTarSocial) {
		this.ligacoesInativasTarSocial = ligacoesInativasTarSocial;
	}
	public void setHidSubstituidosPocoGrande(Integer hidSubstituidosPocoGrande) {
		this.hidSubstituidosPocoGrande = hidSubstituidosPocoGrande;
	}
	public void setHidInstaladosPocoGrande(Integer hidInstaladosPocoGrande) {
		this.hidInstaladosPocoGrande = hidInstaladosPocoGrande;
	}
	
	public void setHidSubstituidosRamalGrande(Integer hidSubstituidosRamalGrande) {
		this.hidSubstituidosRamalGrande = hidSubstituidosRamalGrande;
	}
	public void setHidInstaladosRamalGrande(Integer hidInstaladosRamalGrande) {
		this.hidInstaladosRamalGrande = hidInstaladosRamalGrande;
	}
	public void setCortesExecutadosGrande(Integer cortesExecutadosGrande) {
		this.cortesExecutadosGrande = cortesExecutadosGrande;
	}
	public void setSuprExecutadasGrande(Integer suprExecutadasGrande) {
		this.suprExecutadasGrande = suprExecutadasGrande;
	}
	public void setReligacoesGrande(Integer religacoesGrande) {
		this.religacoesGrande = religacoesGrande;
	}
	public void setReestabGrande(Integer reestabGrande) {
		this.reestabGrande = reestabGrande;
	}
	public void setClandCortadosGrande(Integer clandCortadosGrande) {
		this.clandCortadosGrande = clandCortadosGrande;
	}
	public void setClandSuprimidosGrande(Integer clandSuprimidosGrande) {
		this.clandSuprimidosGrande = clandSuprimidosGrande;
	}
	public void setContasEmitidasGrande(Integer contasEmitidasGrande) {
		this.contasEmitidasGrande = contasEmitidasGrande;
	}
	public void setLeitEfetuadasGrande(Integer leitEfetuadasGrande) {
		this.leitEfetuadasGrande = leitEfetuadasGrande;
	}
	public void setLeitAnormalidadesGrande(Integer leitAnormalidadesGrande) {
		this.leitAnormalidadesGrande = leitAnormalidadesGrande;
	}
	public void setAvisoCorteGrande(Integer avisoCorteGrande) {
		this.avisoCorteGrande = avisoCorteGrande;
	}
	public void setPercAnormalidadeGrande(BigDecimal percAnormalidadeGrande) {
		this.percAnormalidadeGrande = percAnormalidadeGrande;
	}
	public void setPercHidrometracaoGrande(BigDecimal percHidrometracaoGrande) {
		this.percHidrometracaoGrande = percHidrometracaoGrande;
	}
	public void setLigImplantadasAguaGrande(Integer ligImplantadasAguaGrande) {
		this.ligImplantadasAguaGrande = ligImplantadasAguaGrande;
	}
	public void setLigImplantadasEsgotoGrande(Integer ligImplantadasEsgotoGrande) {
		this.ligImplantadasEsgotoGrande = ligImplantadasEsgotoGrande;
	}
	public void setRaPendentesComPrazoGrande(Integer raPendentesComPrazoGrande) {
		this.raPendentesComPrazoGrande = raPendentesComPrazoGrande;
	}
	public void setRaPendentesComForaPrazoGrande(
			Integer raPendentesComForaPrazoGrande) {
		this.raPendentesComForaPrazoGrande = raPendentesComForaPrazoGrande;
	}
	public void setRaPendentesOpPrazoGrande(Integer raPendentesOpPrazoGrande) {
		this.raPendentesOpPrazoGrande = raPendentesOpPrazoGrande;
	}
	public void setRaPendentesOpForaPrazoGrande(Integer raPendentesOpForaPrazoGrande) {
		this.raPendentesOpForaPrazoGrande = raPendentesOpForaPrazoGrande;
	}
	public void setFaturaRevisaoGrande(Integer faturaRevisaoGrande) {
		this.faturaRevisaoGrande = faturaRevisaoGrande;
	}
	public void setCartasNegativacaoGrande(Integer cartasNegativacaoGrande) {
		this.cartasNegativacaoGrande = cartasNegativacaoGrande;
	}
	public void setEconomiaAtivaGrande(Integer economiaAtivaGrande) {
		this.economiaAtivaGrande = economiaAtivaGrande;
	}
	public void setEconomiaInativaGrande(Integer economiaInativaGrande) {
		this.economiaInativaGrande = economiaInativaGrande;
	}
	public void setLigacoesAtivasGrande(Integer ligacoesAtivasGrande) {
		this.ligacoesAtivasGrande = ligacoesAtivasGrande;
	}
	public void setLigacoesInativasGrande(Integer ligacoesInativasGrande) {
		this.ligacoesInativasGrande = ligacoesInativasGrande;
	}
	public void setHidSubstituidosPocoCorporativo(Integer hidSubstituidosPocoCorporativo) {
		this.hidSubstituidosPocoCorporativo = hidSubstituidosPocoCorporativo;
	}
	public void setHidInstaladosPocoCorporativo(Integer hidInstaladosPocoCorporativo) {
		this.hidInstaladosPocoCorporativo = hidInstaladosPocoCorporativo;
	}
	
	public void setHidSubstituidosRamalCorporativo(Integer hidSubstituidosRamalCorporativo) {
		this.hidSubstituidosRamalCorporativo = hidSubstituidosRamalCorporativo;
	}
	public void setHidInstaladosRamalCorporativo(Integer hidInstaladosRamalCorporativo) {
		this.hidInstaladosRamalCorporativo = hidInstaladosRamalCorporativo;
	}
	public void setCortesExecutadosCorporativo(Integer cortesExecutadosCorporativo) {
		this.cortesExecutadosCorporativo = cortesExecutadosCorporativo;
	}
	public void setSuprExecutadasCorporativo(Integer suprExecutadasCorporativo) {
		this.suprExecutadasCorporativo = suprExecutadasCorporativo;
	}
	public void setReligacoesCorporativo(Integer religacoesCorporativo) {
		this.religacoesCorporativo = religacoesCorporativo;
	}
	public void setReestabCorporativo(Integer reestabCorporativo) {
		this.reestabCorporativo = reestabCorporativo;
	}
	public void setClandCortadosCorporativo(Integer clandCortadosCorporativo) {
		this.clandCortadosCorporativo = clandCortadosCorporativo;
	}
	public void setClandSuprimidosCorporativo(Integer clandSuprimidosCorporativo) {
		this.clandSuprimidosCorporativo = clandSuprimidosCorporativo;
	}
	public void setContasEmitidasCorporativo(Integer contasEmitidasCorporativo) {
		this.contasEmitidasCorporativo = contasEmitidasCorporativo;
	}
	public void setLeitEfetuadasCorporativo(Integer leitEfetuadasCorporativo) {
		this.leitEfetuadasCorporativo = leitEfetuadasCorporativo;
	}
	public void setLeitAnormalidadesCorporativo(Integer leitAnormalidadesCorporativo) {
		this.leitAnormalidadesCorporativo = leitAnormalidadesCorporativo;
	}
	public void setAvisoCorteCorporativo(Integer avisoCorteCorporativo) {
		this.avisoCorteCorporativo = avisoCorteCorporativo;
	}
	public void setPercAnormalidadeCorporativo(
			BigDecimal percAnormalidadeCorporativo) {
		this.percAnormalidadeCorporativo = percAnormalidadeCorporativo;
	}
	public void setPercHidrometracaoCorporativo(
			BigDecimal percHidrometracaoCorporativo) {
		this.percHidrometracaoCorporativo = percHidrometracaoCorporativo;
	}
	public void setLigImplantadasAguaCorporativo(
			Integer ligImplantadasAguaCorporativo) {
		this.ligImplantadasAguaCorporativo = ligImplantadasAguaCorporativo;
	}
	public void setLigImplantadasEsgotoCorporativo(
			Integer ligImplantadasEsgotoCorporativo) {
		this.ligImplantadasEsgotoCorporativo = ligImplantadasEsgotoCorporativo;
	}
	public void setRaPendentesComPrazoCorporativo(
			Integer raPendentesComPrazoCorporativo) {
		this.raPendentesComPrazoCorporativo = raPendentesComPrazoCorporativo;
	}
	public void setRaPendentesComForaPrazoCorporativo(
			Integer raPendentesComForaPrazoCorporativo) {
		this.raPendentesComForaPrazoCorporativo = raPendentesComForaPrazoCorporativo;
	}
	public void setRaPendentesOpPrazoCorporativo(
			Integer raPendentesOpPrazoCorporativo) {
		this.raPendentesOpPrazoCorporativo = raPendentesOpPrazoCorporativo;
	}
	public void setRaPendentesOpForaPrazoCorporativo(
			Integer raPendentesOpForaPrazoCorporativo) {
		this.raPendentesOpForaPrazoCorporativo = raPendentesOpForaPrazoCorporativo;
	}
	public void setFaturaRevisaoCorporativo(Integer faturaRevisaoCorporativo) {
		this.faturaRevisaoCorporativo = faturaRevisaoCorporativo;
	}
	public void setCartasNegativacaoCorporativo(Integer cartasNegativacaoCorporativo) {
		this.cartasNegativacaoCorporativo = cartasNegativacaoCorporativo;
	}
	public void setEconomiaAtivaCorporativo(Integer economiaAtivaCorporativo) {
		this.economiaAtivaCorporativo = economiaAtivaCorporativo;
	}
	public void setEconomiaInativaCorporativo(Integer economiaInativaCorporativo) {
		this.economiaInativaCorporativo = economiaInativaCorporativo;
	}
	public void setLigacoesAtivasCorporativo(Integer ligacoesAtivasCorporativo) {
		this.ligacoesAtivasCorporativo = ligacoesAtivasCorporativo;
	}
	public void setLigacoesInativasCorporativo(Integer ligacoesInativasCorporativo) {
		this.ligacoesInativasCorporativo = ligacoesInativasCorporativo;
	}
	public void setHidSubstituidosPocoGrandeTelemedido(Integer hidSubstituidosPocoGrandeTelemedido) {
		this.hidSubstituidosPocoGrandeTelemedido = hidSubstituidosPocoGrandeTelemedido;
	}
	public void setHidInstaladosPocoGrandeTelemedido(Integer hidInstaladosPocoGrandeTelemedido) {
		this.hidInstaladosPocoGrandeTelemedido = hidInstaladosPocoGrandeTelemedido;
	}
	
	public void setHidSubstituidosRamalGrandeTelemedido(Integer hidSubstituidosRamalGrandeTelemedido) {
		this.hidSubstituidosRamalGrandeTelemedido = hidSubstituidosRamalGrandeTelemedido;
	}
	public void setHidInstaladosRamalGrandeTelemedido(Integer hidInstaladosRamalGrandeTelemedido) {
		this.hidInstaladosRamalGrandeTelemedido = hidInstaladosRamalGrandeTelemedido;
	}
	public void setCortesExecutadosGrandeTelemedido(
			Integer cortesExecutadosGrandeTelemedido) {
		this.cortesExecutadosGrandeTelemedido = cortesExecutadosGrandeTelemedido;
	}
	public void setSuprExecutadasGrandeTelemedido(
			Integer suprExecutadasGrandeTelemedido) {
		this.suprExecutadasGrandeTelemedido = suprExecutadasGrandeTelemedido;
	}
	public void setReligacoesGrandeTelemedido(Integer religacoesGrandeTelemedido) {
		this.religacoesGrandeTelemedido = religacoesGrandeTelemedido;
	}
	public void setReestabGrandeTelemedido(Integer reestabGrandeTelemedido) {
		this.reestabGrandeTelemedido = reestabGrandeTelemedido;
	}
	public void setClandCortadosGrandeTelemedido(
			Integer clandCortadosGrandeTelemedido) {
		this.clandCortadosGrandeTelemedido = clandCortadosGrandeTelemedido;
	}
	public void setClandSuprimidosGrandeTelemedido(
			Integer clandSuprimidosGrandeTelemedido) {
		this.clandSuprimidosGrandeTelemedido = clandSuprimidosGrandeTelemedido;
	}
	public void setContasEmitidasGrandeTelemedido(
			Integer contasEmitidasGrandeTelemedido) {
		this.contasEmitidasGrandeTelemedido = contasEmitidasGrandeTelemedido;
	}
	public void setLeitEfetuadasGrandeTelemedido(
			Integer leitEfetuadasGrandeTelemedido) {
		this.leitEfetuadasGrandeTelemedido = leitEfetuadasGrandeTelemedido;
	}
	public void setLeitAnormalidadesGrandeTelemedido(
			Integer leitAnormalidadesGrandeTelemedido) {
		this.leitAnormalidadesGrandeTelemedido = leitAnormalidadesGrandeTelemedido;
	}
	public void setAvisoCorteGrandeTelemedido(Integer avisoCorteGrandeTelemedido) {
		this.avisoCorteGrandeTelemedido = avisoCorteGrandeTelemedido;
	}
	public void setPercAnormalidadeGrandeTelemedido(
			BigDecimal percAnormalidadeGrandeTelemedido) {
		this.percAnormalidadeGrandeTelemedido = percAnormalidadeGrandeTelemedido;
	}
	public void setPercHidrometracaoGrandeTelemedido(
			BigDecimal percHidrometracaoGrandeTelemedido) {
		this.percHidrometracaoGrandeTelemedido = percHidrometracaoGrandeTelemedido;
	}
	public void setLigImplantadasAguaGrandeTelemedido(
			Integer ligImplantadasAguaGrandeTelemedido) {
		this.ligImplantadasAguaGrandeTelemedido = ligImplantadasAguaGrandeTelemedido;
	}
	public void setLigImplantadasEsgotoGrandeTelemedido(
			Integer ligImplantadasEsgotoGrandeTelemedido) {
		this.ligImplantadasEsgotoGrandeTelemedido = ligImplantadasEsgotoGrandeTelemedido;
	}
	public void setRaPendentesComPrazoGrandeTelemedido(
			Integer raPendentesComPrazoGrandeTelemedido) {
		this.raPendentesComPrazoGrandeTelemedido = raPendentesComPrazoGrandeTelemedido;
	}
	public void setRaPendentesComForaPrazoGrandeTelemedido(
			Integer raPendentesComForaPrazoGrandeTelemedido) {
		this.raPendentesComForaPrazoGrandeTelemedido = raPendentesComForaPrazoGrandeTelemedido;
	}
	public void setRaPendentesOpPrazoGrandeTelemedido(
			Integer raPendentesOpPrazoGrandeTelemedido) {
		this.raPendentesOpPrazoGrandeTelemedido = raPendentesOpPrazoGrandeTelemedido;
	}
	public void setRaPendentesOpForaPrazoGrandeTelemedido(
			Integer raPendentesOpForaPrazoGrandeTelemedido) {
		this.raPendentesOpForaPrazoGrandeTelemedido = raPendentesOpForaPrazoGrandeTelemedido;
	}
	public void setFaturaRevisaoGrandeTelemedido(
			Integer faturaRevisaoGrandeTelemedido) {
		this.faturaRevisaoGrandeTelemedido = faturaRevisaoGrandeTelemedido;
	}
	public void setCartasNegativacaoGrandeTelemedido(
			Integer cartasNegativacaoGrandeTelemedido) {
		this.cartasNegativacaoGrandeTelemedido = cartasNegativacaoGrandeTelemedido;
	}
	public void setEconomiaAtivaGrandeTelemedido(
			Integer economiaAtivaGrandeTelemedido) {
		this.economiaAtivaGrandeTelemedido = economiaAtivaGrandeTelemedido;
	}
	public void setEconomiaInativaGrandeTelemedido(
			Integer economiaInativaGrandeTelemedido) {
		this.economiaInativaGrandeTelemedido = economiaInativaGrandeTelemedido;
	}
	public void setLigacoesAtivasGrandeTelemedido(
			Integer ligacoesAtivasGrandeTelemedido) {
		this.ligacoesAtivasGrandeTelemedido = ligacoesAtivasGrandeTelemedido;
	}
	public void setLigacoesInativasGrandeTelemedido(
			Integer ligacoesInativasGrandeTelemedido) {
		this.ligacoesInativasGrandeTelemedido = ligacoesInativasGrandeTelemedido;
	}
	public void setHidSubstituidosPocoCorpTelemedido(Integer hidSubstituidosPocoCorpTelemedido) {
		this.hidSubstituidosPocoCorpTelemedido = hidSubstituidosPocoCorpTelemedido;
	}
	public void setHidInstaladosPocoCorpTelemedido(Integer hidInstaladosPocoCorpTelemedido) {
		this.hidInstaladosPocoCorpTelemedido = hidInstaladosPocoCorpTelemedido;
	}
	
	public void setHidSubstituidosRamalCorpTelemedido(Integer hidSubstituidosRamalCorpTelemedido) {
		this.hidSubstituidosRamalCorpTelemedido = hidSubstituidosRamalCorpTelemedido;
	}
	public void setHidInstaladosRamalCorpTelemedido(Integer hidInstaladosRamalCorpTelemedido) {
		this.hidInstaladosRamalCorpTelemedido = hidInstaladosRamalCorpTelemedido;
	}
	public void setCortesExecutadosCorpTelemedido(
			Integer cortesExecutadosCorpTelemedido) {
		this.cortesExecutadosCorpTelemedido = cortesExecutadosCorpTelemedido;
	}
	public void setSuprExecutadasCorpTelemedido(Integer suprExecutadasCorpTelemedido) {
		this.suprExecutadasCorpTelemedido = suprExecutadasCorpTelemedido;
	}
	public void setReligacoesCorpTelemedido(Integer religacoesCorpTelemedido) {
		this.religacoesCorpTelemedido = religacoesCorpTelemedido;
	}
	public void setReestabCorpTelemedido(Integer reestabCorpTelemedido) {
		this.reestabCorpTelemedido = reestabCorpTelemedido;
	}
	public void setClandCortadosCorpTelemedido(Integer clandCortadosCorpTelemedido) {
		this.clandCortadosCorpTelemedido = clandCortadosCorpTelemedido;
	}
	public void setClandSuprimidosCorpTelemedido(
			Integer clandSuprimidosCorpTelemedido) {
		this.clandSuprimidosCorpTelemedido = clandSuprimidosCorpTelemedido;
	}
	public void setContasEmitidasCorpTelemedido(Integer contasEmitidasCorpTelemedido) {
		this.contasEmitidasCorpTelemedido = contasEmitidasCorpTelemedido;
	}
	public void setLeitEfetuadasCorpTelemedido(Integer leitEfetuadasCorpTelemedido) {
		this.leitEfetuadasCorpTelemedido = leitEfetuadasCorpTelemedido;
	}
	public void setLeitAnormalidadesCorpTelemedido(
			Integer leitAnormalidadesCorpTelemedido) {
		this.leitAnormalidadesCorpTelemedido = leitAnormalidadesCorpTelemedido;
	}
	public void setAvisoCorteCorpTelemedido(Integer avisoCorteCorpTelemedido) {
		this.avisoCorteCorpTelemedido = avisoCorteCorpTelemedido;
	}
	public void setPercAnormalidadeCorpTelemedido(
			BigDecimal percAnormalidadeCorpTelemedido) {
		this.percAnormalidadeCorpTelemedido = percAnormalidadeCorpTelemedido;
	}
	public void setPercHidrometracaoCorpTelemedido(
			BigDecimal percHidrometracaoCorpTelemedido) {
		this.percHidrometracaoCorpTelemedido = percHidrometracaoCorpTelemedido;
	}
	public void setLigImplantadasAguaCorpTelemedido(
			Integer ligImplantadasAguaCorpTelemedido) {
		this.ligImplantadasAguaCorpTelemedido = ligImplantadasAguaCorpTelemedido;
	}
	public void setLigImplantadasEsgotoCorpTelemedido(
			Integer ligImplantadasEsgotoCorpTelemedido) {
		this.ligImplantadasEsgotoCorpTelemedido = ligImplantadasEsgotoCorpTelemedido;
	}
	public void setRaPendentesComPrazoCorpTelemedido(
			Integer raPendentesComPrazoCorpTelemedido) {
		this.raPendentesComPrazoCorpTelemedido = raPendentesComPrazoCorpTelemedido;
	}
	public void setRaPendentesComForaPrazoCorpTelemedido(
			Integer raPendentesComForaPrazoCorpTelemedido) {
		this.raPendentesComForaPrazoCorpTelemedido = raPendentesComForaPrazoCorpTelemedido;
	}
	public void setRaPendentesOpPrazoCorpTelemedido(
			Integer raPendentesOpPrazoCorpTelemedido) {
		this.raPendentesOpPrazoCorpTelemedido = raPendentesOpPrazoCorpTelemedido;
	}
	public void setRaPendentesOpForaPrazoCorpTelemedido(
			Integer raPendentesOpForaPrazoCorpTelemedido) {
		this.raPendentesOpForaPrazoCorpTelemedido = raPendentesOpForaPrazoCorpTelemedido;
	}
	public void setFaturaRevisaoCorpTelemedido(Integer faturaRevisaoCorpTelemedido) {
		this.faturaRevisaoCorpTelemedido = faturaRevisaoCorpTelemedido;
	}
	public void setCartasNegativacaoCorpTelemedido(
			Integer cartasNegativacaoCorpTelemedido) {
		this.cartasNegativacaoCorpTelemedido = cartasNegativacaoCorpTelemedido;
	}
	public void setEconomiaAtivaCorpTelemedido(Integer economiaAtivaCorpTelemedido) {
		this.economiaAtivaCorpTelemedido = economiaAtivaCorpTelemedido;
	}
	public void setEconomiaInativaCorpTelemedido(
			Integer economiaInativaCorpTelemedido) {
		this.economiaInativaCorpTelemedido = economiaInativaCorpTelemedido;
	}
	public void setLigacoesAtivasCorpTelemedido(Integer ligacoesAtivasCorpTelemedido) {
		this.ligacoesAtivasCorpTelemedido = ligacoesAtivasCorpTelemedido;
	}
	public void setLigacoesInativasCorpTelemedido(
			Integer ligacoesInativasCorpTelemedido) {
		this.ligacoesInativasCorpTelemedido = ligacoesInativasCorpTelemedido;
	}
	
	public String getGrupoDados() {
		return grupoDados;
	}
	public void setGrupoDados(String grupoDados) {
		this.grupoDados = grupoDados;
	}
	
	
   public int compareTo(RelatorioDadosKitCASServicoBean rel) {
	   int valor = 0;
	   
	   if(gerenciaRegionalID != null && !gerenciaRegional.equals("0")
			   && rel.getGerenciaRegionalID() != null )
		   valor = gerenciaRegionalID.compareTo(rel.getGerenciaRegionalID());
	   else if(unidadeNegocioID != null && !unidadeNegocioID.equals("0")
			   && rel.getUnidadeNegocioID() != null)
		   valor = unidadeNegocioID.compareTo(rel.getUnidadeNegocioID());
	   
        return (valor != 0 ? valor : 1);
    }



@Override
protected Object clone() throws CloneNotSupportedException {
	// TODO Auto-generated method stub
	return super.clone();
}



public Integer getRegAnormalidadeInformadaResidencial() {
	return regAnormalidadeInformadaResidencial;
}



public Integer getRegRecebidosResidencial() {
	return regRecebidosResidencial;
}



public Integer getRegAnormalidadeInformadaComercial() {
	return regAnormalidadeInformadaComercial;
}



public Integer getRegRecebidosComercial() {
	return regRecebidosComercial;
}



public Integer getRegAnormalidadeInformadaIndustrial() {
	return regAnormalidadeInformadaIndustrial;
}



public Integer getRegRecebidosIndustrial() {
	return regRecebidosIndustrial;
}



public Integer getRegAnormalidadeInformadaPublico() {
	return regAnormalidadeInformadaPublico;
}



public Integer getRegRecebidosPublico() {
	return regRecebidosPublico;
}



public Integer getRegAnormalidadeInformadaNormal() {
	return regAnormalidadeInformadaNormal;
}



public Integer getRegRecebidosNormal() {
	return regRecebidosNormal;
}



public Integer getRegAnormalidadeInformadaTarSocial() {
	return regAnormalidadeInformadaTarSocial;
}



public Integer getRegRecebidosTarSocial() {
	return regRecebidosTarSocial;
}



public Integer getRegAnormalidadeInformadaGrande() {
	return regAnormalidadeInformadaGrande;
}



public Integer getRegRecebidosGrande() {
	return regRecebidosGrande;
}



public Integer getRegAnormalidadeInformadaCorporativo() {
	return regAnormalidadeInformadaCorporativo;
}



public Integer getRegRecebidosCorporativo() {
	return regRecebidosCorporativo;
}



public Integer getRegAnormalidadeInformadaGrandeTelemedido() {
	return regAnormalidadeInformadaGrandeTelemedido;
}



public Integer getRegRecebidosGrandeTelemedido() {
	return regRecebidosGrandeTelemedido;
}



public Integer getRegAnormalidadeInformadaCorpTelemedido() {
	return regAnormalidadeInformadaCorpTelemedido;
}



public Integer getRegRecebidosCorpTelemedido() {
	return regRecebidosCorpTelemedido;
}



public Integer getRegAnormalidadeInformadaTotalPerfil() {
	return new Integer(
			this.getRegAnormalidadeInformadaNormal().intValue()+
			this.getRegAnormalidadeInformadaTarSocial().intValue() + 
			this.getRegAnormalidadeInformadaGrande().intValue() +
			this.getRegAnormalidadeInformadaCorporativo().intValue()+
			this.getRegAnormalidadeInformadaGrandeTelemedido().intValue()+
			this.getRegAnormalidadeInformadaCorpTelemedido().intValue()+
			this.getRegAnormalidadeInformadaChafariz().intValue() +
			this.getRegAnormalidadeInformadaMicroTelemedido().intValue() +
			this.getRegAnormalidadeInformadaCadastroProvisorio().intValue()
	);
}



public Integer getRegRecebidosTotalPerfil() {
	return new Integer(
			this.getRegRecebidosNormal().intValue()+
			this.getRegRecebidosTarSocial().intValue() + 
			this.getRegRecebidosGrande().intValue() +
			this.getRegRecebidosCorporativo().intValue()+
			this.getRegRecebidosGrandeTelemedido().intValue()+
			this.getRegRecebidosCorpTelemedido().intValue()+
			this.getRegRecebidosChafariz().intValue() +
			this.getRegRecebidosMicroTelemedido().intValue() +
			this.getRegRecebidosCadastroProvisorio().intValue()
	);
}



public Integer getRegAnormalidadeInformadaTotalCategoria() {
	return new Integer(
			this.getRegAnormalidadeInformadaIndustrial().intValue()+
			this.getRegAnormalidadeInformadaComercial().intValue() + 
			this.getRegAnormalidadeInformadaResidencial().intValue() +
			this.getRegAnormalidadeInformadaPublico().intValue()
	);
}


public Integer getRegRecebidosTotalCategoria() {
	return new Integer(
			this.getRegRecebidosIndustrial().intValue()+
			this.getRegRecebidosComercial().intValue() + 
			this.getRegRecebidosResidencial().intValue() +
			this.getRegRecebidosPublico().intValue()
	);
}



public void setRegAnormalidadeInformadaResidencial(
		Integer regAnormalidadeInformadaResidencial) {
	this.regAnormalidadeInformadaResidencial = regAnormalidadeInformadaResidencial;
}



public void setRegRecebidosResidencial(Integer regRecebidosResidencial) {
	this.regRecebidosResidencial = regRecebidosResidencial;
}



public void setRegAnormalidadeInformadaComercial(
		Integer regAnormalidadeInformadaComercial) {
	this.regAnormalidadeInformadaComercial = regAnormalidadeInformadaComercial;
}



public void setRegRecebidosComercial(Integer regRecebidosComercial) {
	this.regRecebidosComercial = regRecebidosComercial;
}



public void setRegAnormalidadeInformadaIndustrial(
		Integer regAnormalidadeInformadaIndustrial) {
	this.regAnormalidadeInformadaIndustrial = regAnormalidadeInformadaIndustrial;
}



public void setRegRecebidosIndustrial(Integer regRecebidosIndustrial) {
	this.regRecebidosIndustrial = regRecebidosIndustrial;
}



public void setRegAnormalidadeInformadaPublico(
		Integer regAnormalidadeInformadaPublico) {
	this.regAnormalidadeInformadaPublico = regAnormalidadeInformadaPublico;
}



public void setRegRecebidosPublico(Integer regRecebidosPublico) {
	this.regRecebidosPublico = regRecebidosPublico;
}



public void setRegAnormalidadeInformadaNormal(
		Integer regAnormalidadeInformadaNormal) {
	this.regAnormalidadeInformadaNormal = regAnormalidadeInformadaNormal;
}



public void setRegRecebidosNormal(Integer regRecebidosNormal) {
	this.regRecebidosNormal = regRecebidosNormal;
}



public void setRegAnormalidadeInformadaTarSocial(
		Integer regAnormalidadeInformadaTarSocial) {
	this.regAnormalidadeInformadaTarSocial = regAnormalidadeInformadaTarSocial;
}



public void setRegRecebidosTarSocial(Integer regRecebidosTarSocial) {
	this.regRecebidosTarSocial = regRecebidosTarSocial;
}



public void setRegAnormalidadeInformadaGrande(
		Integer regAnormalidadeInformadaGrande) {
	this.regAnormalidadeInformadaGrande = regAnormalidadeInformadaGrande;
}



public void setRegRecebidosGrande(Integer regRecebidosGrande) {
	this.regRecebidosGrande = regRecebidosGrande;
}



public void setRegAnormalidadeInformadaCorporativo(
		Integer regAnormalidadeInformadaCorporativo) {
	this.regAnormalidadeInformadaCorporativo = regAnormalidadeInformadaCorporativo;
}



public void setRegRecebidosCorporativo(Integer regRecebidosCorporativo) {
	this.regRecebidosCorporativo = regRecebidosCorporativo;
}



public void setRegAnormalidadeInformadaGrandeTelemedido(
		Integer regAnormalidadeInformadaGrandeTelemedido) {
	this.regAnormalidadeInformadaGrandeTelemedido = regAnormalidadeInformadaGrandeTelemedido;
}



public void setRegRecebidosGrandeTelemedido(Integer regRecebidosGrandeTelemedido) {
	this.regRecebidosGrandeTelemedido = regRecebidosGrandeTelemedido;
}



public void setRegAnormalidadeInformadaCorpTelemedido(
		Integer regAnormalidadeInformadaCorpTelemedido) {
	this.regAnormalidadeInformadaCorpTelemedido = regAnormalidadeInformadaCorpTelemedido;
}



public void setRegRecebidosCorpTelemedido(Integer regRecebidosCorpTelemedido) {
	this.regRecebidosCorpTelemedido = regRecebidosCorpTelemedido;
}



public void setRegAnormalidadeInformadaTotalPerfil(
		Integer regAnormalidadeInformadaTotalPerfil) {
	this.regAnormalidadeInformadaTotalPerfil = regAnormalidadeInformadaTotalPerfil;
}



public void setRegRecebidosTotalPerfil(Integer regRecebidosTotalPerfil) {
	this.regRecebidosTotalPerfil = regRecebidosTotalPerfil;
}



public void setRegAnormalidadeInformadaTotalCategoria(
		Integer regAnormalidadeInformadaTotalCategoria) {
	this.regAnormalidadeInformadaTotalCategoria = regAnormalidadeInformadaTotalCategoria;
}



public void setRegRecebidosTotalCategoria(Integer regRecebidosTotalCategoria) {
	this.regRecebidosTotalCategoria = regRecebidosTotalCategoria;
}


public Integer getLigAtivasHidrometroResidencial() {
	return ligAtivasHidrometroResidencial;
}


public Integer getLigAtivasResidencial() {
	return ligAtivasResidencial;
}


public Integer getLigAtivasHidrometroComercial() {
	return ligAtivasHidrometroComercial;
}


public Integer getLigAtivasComercial() {
	return ligAtivasComercial;
}


public Integer getLigAtivasHidrometroIndustrial() {
	return ligAtivasHidrometroIndustrial;
}


public Integer getLigAtivasIndustrial() {
	return ligAtivasIndustrial;
}


public Integer getLigAtivasHidrometroPublico() {
	return ligAtivasHidrometroPublico;
}


public Integer getLigAtivasPublico() {
	return ligAtivasPublico;
}


public Integer getLigAtivasHidrometroNormal() {
	return ligAtivasHidrometroNormal;
}


public Integer getLigAtivasNormal() {
	return ligAtivasNormal;
}


public Integer getLigAtivasHidrometroTarSocial() {
	return ligAtivasHidrometroTarSocial;
}


public Integer getLigAtivasTarSocial() {
	return ligAtivasTarSocial;
}


public Integer getLigAtivasHidrometroGrande() {
	return ligAtivasHidrometroGrande;
}


public Integer getLigAtivasGrande() {
	return ligAtivasGrande;
}


public Integer getLigAtivasHidrometroCorporativo() {
	return ligAtivasHidrometroCorporativo;
}


public Integer getLigAtivasCorporativo() {
	return ligAtivasCorporativo;
}


public Integer getLigAtivasHidrometroGrandeTelemedido() {
	return ligAtivasHidrometroGrandeTelemedido;
}


public Integer getLigAtivasGrandeTelemedido() {
	return ligAtivasGrandeTelemedido;
}


public Integer getLigAtivasHidrometroCorpTelemedido() {
	return ligAtivasHidrometroCorpTelemedido;
}


public Integer getLigAtivasCorpTelemedido() {
	return ligAtivasCorpTelemedido;
}


public Integer getLigAtivasHidrometroTotalPerfil() {
	return new Integer(
			this.getLigAtivasHidrometroNormal().intValue()+
			this.getLigAtivasHidrometroTarSocial().intValue() + 
			this.getLigAtivasHidrometroGrande().intValue() +
			this.getLigAtivasHidrometroCorporativo().intValue()+
			this.getLigAtivasHidrometroGrandeTelemedido().intValue()+
			this.getLigAtivasHidrometroCorpTelemedido().intValue()+
			this.getLigAtivasHidrometroChafariz().intValue() +
			this.getLigAtivasHidrometroMicroTelemedido().intValue() +
			this.getLigAtivasHidrometroCadastroProvisorio().intValue()
	);
}


public Integer getLigAtivasTotalPerfil() {
	
	return new Integer(
			this.getLigAtivasNormal().intValue()+
			this.getLigAtivasTarSocial().intValue() + 
			this.getLigAtivasGrande().intValue() +
			this.getLigAtivasCorporativo().intValue()+
			this.getLigAtivasGrandeTelemedido().intValue()+
			this.getLigAtivasCorpTelemedido().intValue()+
			this.getLigAtivasChafariz().intValue() +
			this.getLigAtivasMicroTelemedido().intValue() +
			this.getLigAtivasCadastroProvisorio().intValue()
	);
}


public Integer getLigAtivasHidrometroTotalCategoria() {
	
	return new Integer(
			this.getLigAtivasHidrometroIndustrial().intValue()+
			this.getLigAtivasHidrometroComercial().intValue() + 
			this.getLigAtivasHidrometroResidencial().intValue() +
			this.getLigAtivasHidrometroPublico().intValue()
	);
}


public Integer getLigAtivasTotalCategoria() {
	return new Integer(
			this.getLigAtivasIndustrial().intValue()+
			this.getLigAtivasComercial().intValue() + 
			this.getLigAtivasResidencial().intValue() +
			this.getLigAtivasPublico().intValue()
	);
}


public void setLigAtivasHidrometroResidencial(
		Integer ligAtivasHidrometroResidencial) {
	this.ligAtivasHidrometroResidencial = ligAtivasHidrometroResidencial;
}


public void setLigAtivasResidencial(Integer ligAtivasResidencial) {
	this.ligAtivasResidencial = ligAtivasResidencial;
}


public void setLigAtivasHidrometroComercial(Integer ligAtivasHidrometroComercial) {
	this.ligAtivasHidrometroComercial = ligAtivasHidrometroComercial;
}


public void setLigAtivasComercial(Integer ligAtivasComercial) {
	this.ligAtivasComercial = ligAtivasComercial;
}


public void setLigAtivasHidrometroIndustrial(
		Integer ligAtivasHidrometroIndustrial) {
	this.ligAtivasHidrometroIndustrial = ligAtivasHidrometroIndustrial;
}


public void setLigAtivasIndustrial(Integer ligAtivasIndustrial) {
	this.ligAtivasIndustrial = ligAtivasIndustrial;
}


public void setLigAtivasHidrometroPublico(Integer ligAtivasHidrometroPublico) {
	this.ligAtivasHidrometroPublico = ligAtivasHidrometroPublico;
}


public void setLigAtivasPublico(Integer ligAtivasPublico) {
	this.ligAtivasPublico = ligAtivasPublico;
}


public void setLigAtivasHidrometroNormal(Integer ligAtivasHidrometroNormal) {
	this.ligAtivasHidrometroNormal = ligAtivasHidrometroNormal;
}


public void setLigAtivasNormal(Integer ligAtivasNormal) {
	this.ligAtivasNormal = ligAtivasNormal;
}


public void setLigAtivasHidrometroTarSocial(Integer ligAtivasHidrometroTarSocial) {
	this.ligAtivasHidrometroTarSocial = ligAtivasHidrometroTarSocial;
}


public void setLigAtivasTarSocial(Integer ligAtivasTarSocial) {
	this.ligAtivasTarSocial = ligAtivasTarSocial;
}


public void setLigAtivasHidrometroGrande(Integer ligAtivasHidrometroGrande) {
	this.ligAtivasHidrometroGrande = ligAtivasHidrometroGrande;
}


public void setLigAtivasGrande(Integer ligAtivasGrande) {
	this.ligAtivasGrande = ligAtivasGrande;
}


public void setLigAtivasHidrometroCorporativo(
		Integer ligAtivasHidrometroCorporativo) {
	this.ligAtivasHidrometroCorporativo = ligAtivasHidrometroCorporativo;
}


public void setLigAtivasCorporativo(Integer ligAtivasCorporativo) {
	this.ligAtivasCorporativo = ligAtivasCorporativo;
}


public void setLigAtivasHidrometroGrandeTelemedido(
		Integer ligAtivasHidrometroGrandeTelemedido) {
	this.ligAtivasHidrometroGrandeTelemedido = ligAtivasHidrometroGrandeTelemedido;
}


public void setLigAtivasGrandeTelemedido(Integer ligAtivasGrandeTelemedido) {
	this.ligAtivasGrandeTelemedido = ligAtivasGrandeTelemedido;
}


public void setLigAtivasHidrometroCorpTelemedido(
		Integer ligAtivasHidrometroCorpTelemedido) {
	this.ligAtivasHidrometroCorpTelemedido = ligAtivasHidrometroCorpTelemedido;
}


public void setLigAtivasCorpTelemedido(Integer ligAtivasCorpTelemedido) {
	this.ligAtivasCorpTelemedido = ligAtivasCorpTelemedido;
}


public void setLigAtivasHidrometroTotalPerfil(
		Integer ligAtivasHidrometroTotalPerfil) {
	this.ligAtivasHidrometroTotalPerfil = ligAtivasHidrometroTotalPerfil;
}


public void setLigAtivasTotalPerfil(Integer ligAtivasTotalPerfil) {
	this.ligAtivasTotalPerfil = ligAtivasTotalPerfil;
}


public void setLigAtivasHidrometroTotalCategoria(
		Integer ligAtivasHidrometroTotalCategoria) {
	this.ligAtivasHidrometroTotalCategoria = ligAtivasHidrometroTotalCategoria;
}


public void setLigAtivasTotalCategoria(Integer ligAtivasTotalCategoria) {
	this.ligAtivasTotalCategoria = ligAtivasTotalCategoria;
}


public Integer getFaturaRevisaoReclamacaoConsumoResidencial() {
	return faturaRevisaoReclamacaoConsumoResidencial;
}


public Integer getFaturaRevisaoRecorrenciaResidencial() {
	return faturaRevisaoRecorrenciaResidencial;
}


public Integer getFaturaRevisaoFaturamentoIndevidoResidencial() {
	return faturaRevisaoFaturamentoIndevidoResidencial;
}


public Integer getFaturaRevisaoReclamacaoConsumoComercial() {
	return faturaRevisaoReclamacaoConsumoComercial;
}


public Integer getFaturaRevisaoRecorrenciaComercial() {
	return faturaRevisaoRecorrenciaComercial;
}


public Integer getFaturaRevisaoFaturamentoIndevidoComercial() {
	return faturaRevisaoFaturamentoIndevidoComercial;
}


public Integer getFaturaRevisaoReclamacaoConsumoIndustrial() {
	return faturaRevisaoReclamacaoConsumoIndustrial;
}


public Integer getFaturaRevisaoRecorrenciaIndustrial() {
	return faturaRevisaoRecorrenciaIndustrial;
}


public Integer getFaturaRevisaoFaturamentoIndevidoIndustrial() {
	return faturaRevisaoFaturamentoIndevidoIndustrial;
}


public Integer getFaturaRevisaoReclamacaoConsumoPublico() {
	return faturaRevisaoReclamacaoConsumoPublico;
}


public Integer getFaturaRevisaoRecorrenciaPublico() {
	return faturaRevisaoRecorrenciaPublico;
}


public Integer getFaturaRevisaoFaturamentoIndevidoPublico() {
	return faturaRevisaoFaturamentoIndevidoPublico;
}


public Integer getFaturaRevisaoReclamacaoConsumoNormal() {
	return faturaRevisaoReclamacaoConsumoNormal;
}


public Integer getFaturaRevisaoRecorrenciaNormal() {
	return faturaRevisaoRecorrenciaNormal;
}


public Integer getFaturaRevisaoFaturamentoIndevidoNormal() {
	return faturaRevisaoFaturamentoIndevidoNormal;
}


public Integer getFaturaRevisaoReclamacaoConsumoTarSocial() {
	return faturaRevisaoReclamacaoConsumoTarSocial;
}


public Integer getFaturaRevisaoRecorrenciaTarSocial() {
	return faturaRevisaoRecorrenciaTarSocial;
}


public Integer getFaturaRevisaoFaturamentoIndevidoTarSocial() {
	return faturaRevisaoFaturamentoIndevidoTarSocial;
}


public Integer getFaturaRevisaoReclamacaoConsumoGrande() {
	return faturaRevisaoReclamacaoConsumoGrande;
}


public Integer getFaturaRevisaoRecorrenciaGrande() {
	return faturaRevisaoRecorrenciaGrande;
}


public Integer getFaturaRevisaoFaturamentoIndevidoGrande() {
	return faturaRevisaoFaturamentoIndevidoGrande;
}


public Integer getFaturaRevisaoReclamacaoConsumoCorporativo() {
	return faturaRevisaoReclamacaoConsumoCorporativo;
}


public Integer getFaturaRevisaoRecorrenciaCorporativo() {
	return faturaRevisaoRecorrenciaCorporativo;
}


public Integer getFaturaRevisaoFaturamentoIndevidoCorporativo() {
	return faturaRevisaoFaturamentoIndevidoCorporativo;
}


public Integer getFaturaRevisaoReclamacaoConsumoGrandeTelemedido() {
	return faturaRevisaoReclamacaoConsumoGrandeTelemedido;
}


public Integer getFaturaRevisaoRecorrenciaGrandeTelemedido() {
	return faturaRevisaoRecorrenciaGrandeTelemedido;
}


public Integer getFaturaRevisaoFaturamentoIndevidoGrandeTelemedido() {
	return faturaRevisaoFaturamentoIndevidoGrandeTelemedido;
}


public Integer getFaturaRevisaoReclamacaoConsumoCorpTelemedido() {
	return faturaRevisaoReclamacaoConsumoCorpTelemedido;
}


public Integer getFaturaRevisaoRecorrenciaCorpTelemedido() {
	return faturaRevisaoRecorrenciaCorpTelemedido;
}


public Integer getFaturaRevisaoFaturamentoIndevidoCorpTelemedido() {
	return faturaRevisaoFaturamentoIndevidoCorpTelemedido;
}


public Integer getFaturaRevisaoReclamacaoConsumoTotalPerfil() {
	return new Integer(
			this.getFaturaRevisaoReclamacaoConsumoNormal().intValue()+
			this.getFaturaRevisaoReclamacaoConsumoTarSocial().intValue() + 
			this.getFaturaRevisaoReclamacaoConsumoGrande().intValue() +
			this.getFaturaRevisaoReclamacaoConsumoCorporativo().intValue()+
			this.getFaturaRevisaoReclamacaoConsumoGrandeTelemedido().intValue()+
			this.getFaturaRevisaoReclamacaoConsumoCorpTelemedido().intValue()+
			this.getFaturaRevisaoReclamacaoConsumoChafariz().intValue() +
			this.getFaturaRevisaoReclamacaoConsumoMicroTelemedido().intValue() +
			this.getFaturaRevisaoReclamacaoConsumoCadastroProvisorio().intValue()
	);
}


public Integer getFaturaRevisaoRecorrenciaTotalPerfil() {
	return new Integer(
			this.getFaturaRevisaoRecorrenciaNormal().intValue()+
			this.getFaturaRevisaoRecorrenciaTarSocial().intValue() + 
			this.getFaturaRevisaoRecorrenciaGrande().intValue() +
			this.getFaturaRevisaoRecorrenciaCorporativo().intValue()+
			this.getFaturaRevisaoRecorrenciaGrandeTelemedido().intValue()+
			this.getFaturaRevisaoRecorrenciaCorpTelemedido().intValue()+
			this.getFaturaRevisaoRecorrenciaChafariz().intValue() +
			this.getFaturaRevisaoRecorrenciaMicroTelemedido().intValue() +
			this.getFaturaRevisaoRecorrenciaCadastroProvisorio().intValue()
	);
}


public Integer getFaturaRevisaoFaturamentoIndevidoTotalPerfil() {
	return new Integer(
			this.getFaturaRevisaoFaturamentoIndevidoNormal().intValue()+
			this.getFaturaRevisaoFaturamentoIndevidoTarSocial().intValue() + 
			this.getFaturaRevisaoFaturamentoIndevidoGrande().intValue() +
			this.getFaturaRevisaoFaturamentoIndevidoCorporativo().intValue()+
			this.getFaturaRevisaoFaturamentoIndevidoGrandeTelemedido().intValue()+
			this.getFaturaRevisaoFaturamentoIndevidoCorpTelemedido().intValue()+
			this.getFaturaRevisaoFaturamentoIndevidoChafariz().intValue() +
			this.getFaturaRevisaoFaturamentoIndevidoMicroTelemedido().intValue() +
			this.getFaturaRevisaoFaturamentoIndevidoCadastroProvisorio().intValue()
	);
}

public Integer getQtImoveisFaturaRevisaoReclamacaoConsumoTotalPerfil() {
	return new Integer(
			this.getQtImoveisFaturaRevisaoReclamacaoConsumoNormal().intValue()+
			this.getQtImoveisFaturaRevisaoReclamacaoConsumoTarSocial().intValue() + 
			this.getQtImoveisFaturaRevisaoReclamacaoConsumoGrande().intValue() +
			this.getQtImoveisFaturaRevisaoReclamacaoConsumoCorporativo().intValue()+
			this.getQtImoveisFaturaRevisaoReclamacaoConsumoGrandeTelemedido().intValue()+
			this.getQtImoveisFaturaRevisaoReclamacaoConsumoCorpTelemedido().intValue()+
			this.getQtImoveisFaturaRevisaoReclamacaoConsumoChafariz().intValue() +
			this.getQtImoveisFaturaRevisaoReclamacaoConsumoMicroTelemedido().intValue() +
			this.getQtImoveisFaturaRevisaoReclamacaoConsumoCadastroProvisorio().intValue()
	);
}

public Integer getQtImoveisFaturaRevisaoRecorrenciaTotalPerfil() {
	return new Integer(
			this.getQtImoveisFaturaRevisaoRecorrenciaNormal().intValue()+
			this.getQtImoveisFaturaRevisaoRecorrenciaTarSocial().intValue() + 
			this.getQtImoveisFaturaRevisaoRecorrenciaGrande().intValue() +
			this.getQtImoveisFaturaRevisaoRecorrenciaCorporativo().intValue()+
			this.getQtImoveisFaturaRevisaoRecorrenciaGrandeTelemedido().intValue()+
			this.getQtImoveisFaturaRevisaoRecorrenciaCorpTelemedido().intValue()+
			this.getQtImoveisFaturaRevisaoRecorrenciaChafariz().intValue() +
			this.getQtImoveisFaturaRevisaoRecorrenciaMicroTelemedido().intValue() +
			this.getQtImoveisFaturaRevisaoRecorrenciaCadastroProvisorio().intValue()
	);
}

public Integer getQtImoveisFaturaRevisaoFaturamentoIndevidoTotalPerfil() {
	return new Integer(
			this.getQtImoveisFaturaRevisaoFaturamentoIndevidoNormal().intValue()+
			this.getQtImoveisFaturaRevisaoFaturamentoIndevidoTarSocial().intValue() + 
			this.getQtImoveisFaturaRevisaoFaturamentoIndevidoGrande().intValue() +
			this.getQtImoveisFaturaRevisaoFaturamentoIndevidoCorporativo().intValue()+
			this.getQtImoveisFaturaRevisaoFaturamentoIndevidoGrandeTelemedido().intValue()+
			this.getQtImoveisFaturaRevisaoFaturamentoIndevidoCorpTelemedido().intValue()+
			this.getQtImoveisFaturaRevisaoFaturamentoIndevidoChafariz().intValue() +
			this.getQtImoveisFaturaRevisaoFaturamentoIndevidoMicroTelemedido().intValue() +
			this.getQtImoveisFaturaRevisaoFaturamentoIndevidoCadastroProvisorio().intValue()
	);
}


public Integer getFaturaRevisaoReclamacaoConsumoTotalCategoria() {
	return new Integer(
			this.getFaturaRevisaoReclamacaoConsumoIndustrial().intValue()+
			this.getFaturaRevisaoReclamacaoConsumoComercial().intValue() + 
			this.getFaturaRevisaoReclamacaoConsumoResidencial().intValue() +
			this.getFaturaRevisaoReclamacaoConsumoPublico().intValue()
	);
}


public Integer getFaturaRevisaoRecorrenciaTotalCategoria() {
	return new Integer(
			this.getFaturaRevisaoRecorrenciaIndustrial().intValue()+
			this.getFaturaRevisaoRecorrenciaComercial().intValue() + 
			this.getFaturaRevisaoRecorrenciaResidencial().intValue() +
			this.getFaturaRevisaoRecorrenciaPublico().intValue()
	);
}


public Integer getFaturaRevisaoFaturamentoIndevidoTotalCategoria() {
	return new Integer(
			this.getFaturaRevisaoFaturamentoIndevidoIndustrial().intValue()+
			this.getFaturaRevisaoFaturamentoIndevidoComercial().intValue() + 
			this.getFaturaRevisaoFaturamentoIndevidoResidencial().intValue() +
			this.getFaturaRevisaoFaturamentoIndevidoPublico().intValue()
	);
}

public Integer getQtImoveisFaturaRevisaoReclamacaoConsumoTotalCategoria() {
	return new Integer(
			this.getQtImoveisFaturaRevisaoReclamacaoConsumoIndustrial().intValue()+
			this.getQtImoveisFaturaRevisaoReclamacaoConsumoComercial().intValue() + 
			this.getQtImoveisFaturaRevisaoReclamacaoConsumoResidencial().intValue() +
			this.getQtImoveisFaturaRevisaoReclamacaoConsumoPublico().intValue()
	);
}

public Integer getQtImoveisFaturaRevisaoRecorrenciaTotalCategoria() {
	return new Integer(
			this.getQtImoveisFaturaRevisaoRecorrenciaIndustrial().intValue()+
			this.getQtImoveisFaturaRevisaoRecorrenciaComercial().intValue() + 
			this.getQtImoveisFaturaRevisaoRecorrenciaResidencial().intValue() +
			this.getQtImoveisFaturaRevisaoRecorrenciaPublico().intValue()
	);
}

public Integer getQtImoveisFaturaRevisaoFaturamentoIndevidoTotalCategoria() {
	return new Integer(
			this.getQtImoveisFaturaRevisaoFaturamentoIndevidoIndustrial().intValue()+
			this.getQtImoveisFaturaRevisaoFaturamentoIndevidoComercial().intValue() + 
			this.getQtImoveisFaturaRevisaoFaturamentoIndevidoResidencial().intValue() +
			this.getQtImoveisFaturaRevisaoFaturamentoIndevidoPublico().intValue()
	);
}


public void setFaturaRevisaoReclamacaoConsumoResidencial(
		Integer faturaRevisaoReclamacaoConsumoResidencial) {
	this.faturaRevisaoReclamacaoConsumoResidencial = faturaRevisaoReclamacaoConsumoResidencial;
}


public void setFaturaRevisaoRecorrenciaResidencial(
		Integer faturaRevisaoRecorrenciaResidencial) {
	this.faturaRevisaoRecorrenciaResidencial = faturaRevisaoRecorrenciaResidencial;
}


public void setFaturaRevisaoFaturamentoIndevidoResidencial(
		Integer faturaRevisaoFaturamentoIndevidoResidencial) {
	this.faturaRevisaoFaturamentoIndevidoResidencial = faturaRevisaoFaturamentoIndevidoResidencial;
}


public void setFaturaRevisaoReclamacaoConsumoComercial(
		Integer faturaRevisaoReclamacaoConsumoComercial) {
	this.faturaRevisaoReclamacaoConsumoComercial = faturaRevisaoReclamacaoConsumoComercial;
}


public void setFaturaRevisaoRecorrenciaComercial(
		Integer faturaRevisaoRecorrenciaComercial) {
	this.faturaRevisaoRecorrenciaComercial = faturaRevisaoRecorrenciaComercial;
}


public void setFaturaRevisaoFaturamentoIndevidoComercial(
		Integer faturaRevisaoFaturamentoIndevidoComercial) {
	this.faturaRevisaoFaturamentoIndevidoComercial = faturaRevisaoFaturamentoIndevidoComercial;
}


public void setFaturaRevisaoReclamacaoConsumoIndustrial(
		Integer faturaRevisaoReclamacaoConsumoIndustrial) {
	this.faturaRevisaoReclamacaoConsumoIndustrial = faturaRevisaoReclamacaoConsumoIndustrial;
}


public void setFaturaRevisaoRecorrenciaIndustrial(
		Integer faturaRevisaoRecorrenciaIndustrial) {
	this.faturaRevisaoRecorrenciaIndustrial = faturaRevisaoRecorrenciaIndustrial;
}


public void setFaturaRevisaoFaturamentoIndevidoIndustrial(
		Integer faturaRevisaoFaturamentoIndevidoIndustrial) {
	this.faturaRevisaoFaturamentoIndevidoIndustrial = faturaRevisaoFaturamentoIndevidoIndustrial;
}


public void setFaturaRevisaoReclamacaoConsumoPublico(
		Integer faturaRevisaoReclamacaoConsumoPublico) {
	this.faturaRevisaoReclamacaoConsumoPublico = faturaRevisaoReclamacaoConsumoPublico;
}


public void setFaturaRevisaoRecorrenciaPublico(
		Integer faturaRevisaoRecorrenciaPublico) {
	this.faturaRevisaoRecorrenciaPublico = faturaRevisaoRecorrenciaPublico;
}


public void setFaturaRevisaoFaturamentoIndevidoPublico(
		Integer faturaRevisaoFaturamentoIndevidoPublico) {
	this.faturaRevisaoFaturamentoIndevidoPublico = faturaRevisaoFaturamentoIndevidoPublico;
}


public void setFaturaRevisaoReclamacaoConsumoNormal(
		Integer faturaRevisaoReclamacaoConsumoNormal) {
	this.faturaRevisaoReclamacaoConsumoNormal = faturaRevisaoReclamacaoConsumoNormal;
}


public void setFaturaRevisaoRecorrenciaNormal(
		Integer faturaRevisaoRecorrenciaNormal) {
	this.faturaRevisaoRecorrenciaNormal = faturaRevisaoRecorrenciaNormal;
}


public void setFaturaRevisaoFaturamentoIndevidoNormal(
		Integer faturaRevisaoFaturamentoIndevidoNormal) {
	this.faturaRevisaoFaturamentoIndevidoNormal = faturaRevisaoFaturamentoIndevidoNormal;
}


public void setFaturaRevisaoReclamacaoConsumoTarSocial(
		Integer faturaRevisaoReclamacaoConsumoTarSocial) {
	this.faturaRevisaoReclamacaoConsumoTarSocial = faturaRevisaoReclamacaoConsumoTarSocial;
}


public void setFaturaRevisaoRecorrenciaTarSocial(
		Integer faturaRevisaoRecorrenciaTarSocial) {
	this.faturaRevisaoRecorrenciaTarSocial = faturaRevisaoRecorrenciaTarSocial;
}


public void setFaturaRevisaoFaturamentoIndevidoTarSocial(
		Integer faturaRevisaoFaturamentoIndevidoTarSocial) {
	this.faturaRevisaoFaturamentoIndevidoTarSocial = faturaRevisaoFaturamentoIndevidoTarSocial;
}


public void setFaturaRevisaoReclamacaoConsumoGrande(
		Integer faturaRevisaoReclamacaoConsumoGrande) {
	this.faturaRevisaoReclamacaoConsumoGrande = faturaRevisaoReclamacaoConsumoGrande;
}


public void setFaturaRevisaoRecorrenciaGrande(
		Integer faturaRevisaoRecorrenciaGrande) {
	this.faturaRevisaoRecorrenciaGrande = faturaRevisaoRecorrenciaGrande;
}


public void setFaturaRevisaoFaturamentoIndevidoGrande(
		Integer faturaRevisaoFaturamentoIndevidoGrande) {
	this.faturaRevisaoFaturamentoIndevidoGrande = faturaRevisaoFaturamentoIndevidoGrande;
}


public void setFaturaRevisaoReclamacaoConsumoCorporativo(
		Integer faturaRevisaoReclamacaoConsumoCorporativo) {
	this.faturaRevisaoReclamacaoConsumoCorporativo = faturaRevisaoReclamacaoConsumoCorporativo;
}


public void setFaturaRevisaoRecorrenciaCorporativo(
		Integer faturaRevisaoRecorrenciaCorporativo) {
	this.faturaRevisaoRecorrenciaCorporativo = faturaRevisaoRecorrenciaCorporativo;
}


public void setFaturaRevisaoFaturamentoIndevidoCorporativo(
		Integer faturaRevisaoFaturamentoIndevidoCorporativo) {
	this.faturaRevisaoFaturamentoIndevidoCorporativo = faturaRevisaoFaturamentoIndevidoCorporativo;
}


public void setFaturaRevisaoReclamacaoConsumoGrandeTelemedido(
		Integer faturaRevisaoReclamacaoConsumoGrandeTelemedido) {
	this.faturaRevisaoReclamacaoConsumoGrandeTelemedido = faturaRevisaoReclamacaoConsumoGrandeTelemedido;
}


public void setFaturaRevisaoRecorrenciaGrandeTelemedido(
		Integer faturaRevisaoRecorrenciaGrandeTelemedido) {
	this.faturaRevisaoRecorrenciaGrandeTelemedido = faturaRevisaoRecorrenciaGrandeTelemedido;
}


public void setFaturaRevisaoFaturamentoIndevidoGrandeTelemedido(
		Integer faturaRevisaoFaturamentoIndevidoGrandeTelemedido) {
	this.faturaRevisaoFaturamentoIndevidoGrandeTelemedido = faturaRevisaoFaturamentoIndevidoGrandeTelemedido;
}


public void setFaturaRevisaoReclamacaoConsumoCorpTelemedido(
		Integer faturaRevisaoReclamacaoConsumoCorpTelemedido) {
	this.faturaRevisaoReclamacaoConsumoCorpTelemedido = faturaRevisaoReclamacaoConsumoCorpTelemedido;
}


public void setFaturaRevisaoRecorrenciaCorpTelemedido(
		Integer faturaRevisaoRecorrenciaCorpTelemedido) {
	this.faturaRevisaoRecorrenciaCorpTelemedido = faturaRevisaoRecorrenciaCorpTelemedido;
}


public void setFaturaRevisaoFaturamentoIndevidoCorpTelemedido(
		Integer faturaRevisaoFaturamentoIndevidoCorpTelemedido) {
	this.faturaRevisaoFaturamentoIndevidoCorpTelemedido = faturaRevisaoFaturamentoIndevidoCorpTelemedido;
}


public void setFaturaRevisaoReclamacaoConsumoTotalPerfil(
		Integer faturaRevisaoReclamacaoConsumoTotalPerfil) {
	this.faturaRevisaoReclamacaoConsumoTotalPerfil = faturaRevisaoReclamacaoConsumoTotalPerfil;
}


public void setFaturaRevisaoRecorrenciaTotalPerfil(
		Integer faturaRevisaoRecorrenciaTotalPerfil) {
	this.faturaRevisaoRecorrenciaTotalPerfil = faturaRevisaoRecorrenciaTotalPerfil;
}


public void setFaturaRevisaoFaturamentoIndevidoTotalPerfil(
		Integer faturaRevisaoFaturamentoIndevidoTotalPerfil) {
	this.faturaRevisaoFaturamentoIndevidoTotalPerfil = faturaRevisaoFaturamentoIndevidoTotalPerfil;
}


public void setFaturaRevisaoReclamacaoConsumoTotalCategoria(
		Integer faturaRevisaoReclamacaoConsumoTotalCategoria) {
	this.faturaRevisaoReclamacaoConsumoTotalCategoria = faturaRevisaoReclamacaoConsumoTotalCategoria;
}


public void setFaturaRevisaoRecorrenciaTotalCategoria(
		Integer faturaRevisaoRecorrenciaTotalCategoria) {
	this.faturaRevisaoRecorrenciaTotalCategoria = faturaRevisaoRecorrenciaTotalCategoria;
}


public void setFaturaRevisaoFaturamentoIndevidoTotalCategoria(
		Integer faturaRevisaoFaturamentoIndevidoTotalCategoria) {
	this.faturaRevisaoFaturamentoIndevidoTotalCategoria = faturaRevisaoFaturamentoIndevidoTotalCategoria;
}


public Integer getHidSubstituidosRamalChafariz() {
	return hidSubstituidosRamalChafariz;
}


public Integer getHidInstaladosRamalChafariz() {
	return hidInstaladosRamalChafariz;
}

public Integer getHidSubstituidosPocoChafariz() {
	return hidSubstituidosPocoChafariz;
}


public Integer getHidInstaladosPocoChafariz() {
	return hidInstaladosPocoChafariz;
}


public Integer getCortesExecutadosChafariz() {
	return cortesExecutadosChafariz;
}


public Integer getSuprExecutadasChafariz() {
	return suprExecutadasChafariz;
}


public Integer getReligacoesChafariz() {
	return religacoesChafariz;
}


public Integer getReestabChafariz() {
	return reestabChafariz;
}


public Integer getClandCortadosChafariz() {
	return clandCortadosChafariz;
}


public Integer getClandSuprimidosChafariz() {
	return clandSuprimidosChafariz;
}


public Integer getContasEmitidasChafariz() {
	return contasEmitidasChafariz;
}


public Integer getLeitEfetuadasChafariz() {
	return leitEfetuadasChafariz;
}


public Integer getLeitAnormalidadesChafariz() {
	return leitAnormalidadesChafariz;
}


public Integer getAvisoCorteChafariz() {
	return avisoCorteChafariz;
}


public BigDecimal getPercAnormalidadeChafariz() {
	if(this.getRegRecebidosChafariz().compareTo(new Integer(0)) == 0)
		return new BigDecimal(0);
	
	BigDecimal tempNumerador = new BigDecimal(this.getRegAnormalidadeInformadaChafariz());
	BigDecimal tempDenominador = new BigDecimal(this.getRegRecebidosChafariz());
	
	return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
}


public BigDecimal getPercHidrometracaoChafariz() {
	if(this.getLigAtivasChafariz().compareTo(new Integer(0)) == 0)
		return new BigDecimal(0);
	
	BigDecimal tempNumerador = new BigDecimal(this.getLigAtivasHidrometroChafariz());
	BigDecimal tempDenominador = new BigDecimal(this.getLigAtivasChafariz());
	
	return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
}


public Integer getLigImplantadasAguaChafariz() {
	return ligImplantadasAguaChafariz;
}


public Integer getLigImplantadasEsgotoChafariz() {
	return ligImplantadasEsgotoChafariz;
}


public Integer getRaPendentesComPrazoChafariz() {
	return raPendentesComPrazoChafariz;
}


public Integer getRaPendentesComForaPrazoChafariz() {
	return raPendentesComForaPrazoChafariz;
}


public Integer getRaPendentesOpPrazoChafariz() {
	return raPendentesOpPrazoChafariz;
}


public Integer getRaPendentesOpForaPrazoChafariz() {
	return raPendentesOpForaPrazoChafariz;
}


public Integer getFaturaRevisaoChafariz() {
	return faturaRevisaoChafariz;
}


public Integer getCartasNegativacaoChafariz() {
	return cartasNegativacaoChafariz;
}


public Integer getEconomiaAtivaChafariz() {
	return economiaAtivaChafariz;
}


public Integer getEconomiaInativaChafariz() {
	return economiaInativaChafariz;
}


public Integer getLigacoesAtivasChafariz() {
	return ligacoesAtivasChafariz;
}


public Integer getLigacoesInativasChafariz() {
	return ligacoesInativasChafariz;
}


public Integer getRegAnormalidadeInformadaChafariz() {
	return regAnormalidadeInformadaChafariz;
}


public Integer getRegRecebidosChafariz() {
	return regRecebidosChafariz;
}


public Integer getLigAtivasHidrometroChafariz() {
	return ligAtivasHidrometroChafariz;
}


public Integer getLigAtivasChafariz() {
	return ligAtivasChafariz;
}


public Integer getFaturaRevisaoReclamacaoConsumoChafariz() {
	return faturaRevisaoReclamacaoConsumoChafariz;
}


public Integer getFaturaRevisaoRecorrenciaChafariz() {
	return faturaRevisaoRecorrenciaChafariz;
}


public Integer getFaturaRevisaoFaturamentoIndevidoChafariz() {
	return faturaRevisaoFaturamentoIndevidoChafariz;
}


public Integer getHidSubstituidosRamalMicroTelemedido() {
	return hidSubstituidosRamalMicroTelemedido;
}


public Integer getHidInstaladosRamalMicroTelemedido() {
	return hidInstaladosRamalMicroTelemedido;
}

public Integer getHidSubstituidosPocoMicroTelemedido() {
	return hidSubstituidosPocoMicroTelemedido;
}


public Integer getHidInstaladosPocoMicroTelemedido() {
	return hidInstaladosPocoMicroTelemedido;
}


public Integer getCortesExecutadosMicroTelemedido() {
	return cortesExecutadosMicroTelemedido;
}


public Integer getSuprExecutadasMicroTelemedido() {
	return suprExecutadasMicroTelemedido;
}


public Integer getReligacoesMicroTelemedido() {
	return religacoesMicroTelemedido;
}


public Integer getReestabMicroTelemedido() {
	return reestabMicroTelemedido;
}


public Integer getClandCortadosMicroTelemedido() {
	return clandCortadosMicroTelemedido;
}


public Integer getClandSuprimidosMicroTelemedido() {
	return clandSuprimidosMicroTelemedido;
}


public Integer getContasEmitidasMicroTelemedido() {
	return contasEmitidasMicroTelemedido;
}


public Integer getLeitEfetuadasMicroTelemedido() {
	return leitEfetuadasMicroTelemedido;
}


public Integer getLeitAnormalidadesMicroTelemedido() {
	return leitAnormalidadesMicroTelemedido;
}


public Integer getAvisoCorteMicroTelemedido() {
	return avisoCorteMicroTelemedido;
}


public BigDecimal getPercAnormalidadeMicroTelemedido() {
	if(this.getRegRecebidosMicroTelemedido().compareTo(new Integer(0)) == 0)
		return new BigDecimal(0);
	
	BigDecimal tempNumerador = new BigDecimal(this.getRegAnormalidadeInformadaMicroTelemedido());
	BigDecimal tempDenominador = new BigDecimal(this.getRegRecebidosMicroTelemedido());
	
	return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
}


public BigDecimal getPercHidrometracaoMicroTelemedido() {
	if(this.getLigAtivasMicroTelemedido().compareTo(new Integer(0)) == 0)
		return new BigDecimal(0);
	
	BigDecimal tempNumerador = new BigDecimal(this.getLigAtivasHidrometroMicroTelemedido());
	BigDecimal tempDenominador = new BigDecimal(this.getLigAtivasMicroTelemedido());
	
	return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
}


public Integer getLigImplantadasAguaMicroTelemedido() {
	return ligImplantadasAguaMicroTelemedido;
}


public Integer getLigImplantadasEsgotoMicroTelemedido() {
	return ligImplantadasEsgotoMicroTelemedido;
}


public Integer getRaPendentesComPrazoMicroTelemedido() {
	return raPendentesComPrazoMicroTelemedido;
}


public Integer getRaPendentesComForaPrazoMicroTelemedido() {
	return raPendentesComForaPrazoMicroTelemedido;
}


public Integer getRaPendentesOpPrazoMicroTelemedido() {
	return raPendentesOpPrazoMicroTelemedido;
}


public Integer getRaPendentesOpForaPrazoMicroTelemedido() {
	return raPendentesOpForaPrazoMicroTelemedido;
}


public Integer getFaturaRevisaoMicroTelemedido() {
	return faturaRevisaoMicroTelemedido;
}


public Integer getCartasNegativacaoMicroTelemedido() {
	return cartasNegativacaoMicroTelemedido;
}


public Integer getEconomiaAtivaMicroTelemedido() {
	return economiaAtivaMicroTelemedido;
}


public Integer getEconomiaInativaMicroTelemedido() {
	return economiaInativaMicroTelemedido;
}


public Integer getLigacoesAtivasMicroTelemedido() {
	return ligacoesAtivasMicroTelemedido;
}


public Integer getLigacoesInativasMicroTelemedido() {
	return ligacoesInativasMicroTelemedido;
}


public Integer getRegAnormalidadeInformadaMicroTelemedido() {
	return regAnormalidadeInformadaMicroTelemedido;
}


public Integer getRegRecebidosMicroTelemedido() {
	return regRecebidosMicroTelemedido;
}


public Integer getLigAtivasHidrometroMicroTelemedido() {
	return ligAtivasHidrometroMicroTelemedido;
}


public Integer getLigAtivasMicroTelemedido() {
	return ligAtivasMicroTelemedido;
}


public Integer getFaturaRevisaoReclamacaoConsumoMicroTelemedido() {
	return faturaRevisaoReclamacaoConsumoMicroTelemedido;
}


public Integer getFaturaRevisaoRecorrenciaMicroTelemedido() {
	return faturaRevisaoRecorrenciaMicroTelemedido;
}


public Integer getFaturaRevisaoFaturamentoIndevidoMicroTelemedido() {
	return faturaRevisaoFaturamentoIndevidoMicroTelemedido;
}

public Integer getHidSubstituidosRamalCadastroProvisorio() {
	return hidSubstituidosRamalCadastroProvisorio;
}


public Integer getHidInstaladosRamalCadastroProvisorio() {
	return hidInstaladosRamalCadastroProvisorio;
}

public Integer getHidSubstituidosPocoCadastroProvisorio() {
	return hidSubstituidosPocoCadastroProvisorio;
}


public Integer getHidInstaladosPocoCadastroProvisorio() {
	return hidInstaladosPocoCadastroProvisorio;
}

public Integer getCortesExecutadosCadastroProvisorio() {
	return cortesExecutadosCadastroProvisorio;
}


public Integer getSuprExecutadasCadastroProvisorio() {
	return suprExecutadasCadastroProvisorio;
}


public Integer getReligacoesCadastroProvisorio() {
	return religacoesCadastroProvisorio;
}


public Integer getReestabCadastroProvisorio() {
	return reestabCadastroProvisorio;
}


public Integer getClandCortadosCadastroProvisorio() {
	return clandCortadosCadastroProvisorio;
}


public Integer getClandSuprimidosCadastroProvisorio() {
	return clandSuprimidosCadastroProvisorio;
}


public Integer getContasEmitidasCadastroProvisorio() {
	return contasEmitidasCadastroProvisorio;
}


public Integer getLeitEfetuadasCadastroProvisorio() {
	return leitEfetuadasCadastroProvisorio;
}


public Integer getLeitAnormalidadesCadastroProvisorio() {
	return leitAnormalidadesCadastroProvisorio;
}


public Integer getAvisoCorteCadastroProvisorio() {
	return avisoCorteCadastroProvisorio;
}


public BigDecimal getPercAnormalidadeCadastroProvisorio() {
	if(this.getRegRecebidosCadastroProvisorio().compareTo(new Integer(0)) == 0)
		return new BigDecimal(0);
	
	BigDecimal tempNumerador = new BigDecimal(this.getRegAnormalidadeInformadaCadastroProvisorio());
	BigDecimal tempDenominador = new BigDecimal(this.getRegRecebidosCadastroProvisorio());
	
	return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
}


public BigDecimal getPercHidrometracaoCadastroProvisorio() {
	if(this.getLigAtivasCadastroProvisorio().compareTo(new Integer(0)) == 0)
		return new BigDecimal(0);
	
	BigDecimal tempNumerador = new BigDecimal(this.getLigAtivasHidrometroCadastroProvisorio());
	BigDecimal tempDenominador = new BigDecimal(this.getLigAtivasCadastroProvisorio());
	
	return tempNumerador.divide(tempDenominador, 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));
}


public Integer getLigImplantadasAguaCadastroProvisorio() {
	return ligImplantadasAguaCadastroProvisorio;
}


public Integer getLigImplantadasEsgotoCadastroProvisorio() {
	return ligImplantadasEsgotoCadastroProvisorio;
}


public Integer getRaPendentesComPrazoCadastroProvisorio() {
	return raPendentesComPrazoCadastroProvisorio;
}


public Integer getRaPendentesComForaPrazoCadastroProvisorio() {
	return raPendentesComForaPrazoCadastroProvisorio;
}


public Integer getRaPendentesOpPrazoCadastroProvisorio() {
	return raPendentesOpPrazoCadastroProvisorio;
}


public Integer getRaPendentesOpForaPrazoCadastroProvisorio() {
	return raPendentesOpForaPrazoCadastroProvisorio;
}


public Integer getFaturaRevisaoCadastroProvisorio() {
	return faturaRevisaoCadastroProvisorio;
}


public Integer getCartasNegativacaoCadastroProvisorio() {
	return cartasNegativacaoCadastroProvisorio;
}


public Integer getEconomiaAtivaCadastroProvisorio() {
	return economiaAtivaCadastroProvisorio;
}


public Integer getEconomiaInativaCadastroProvisorio() {
	return economiaInativaCadastroProvisorio;
}


public Integer getLigacoesAtivasCadastroProvisorio() {
	return ligacoesAtivasCadastroProvisorio;
}


public Integer getLigacoesInativasCadastroProvisorio() {
	return ligacoesInativasCadastroProvisorio;
}


public Integer getRegAnormalidadeInformadaCadastroProvisorio() {
	return regAnormalidadeInformadaCadastroProvisorio;
}


public Integer getRegRecebidosCadastroProvisorio() {
	return regRecebidosCadastroProvisorio;
}


public Integer getLigAtivasHidrometroCadastroProvisorio() {
	return ligAtivasHidrometroCadastroProvisorio;
}


public Integer getLigAtivasCadastroProvisorio() {
	return ligAtivasCadastroProvisorio;
}


public Integer getFaturaRevisaoReclamacaoConsumoCadastroProvisorio() {
	return faturaRevisaoReclamacaoConsumoCadastroProvisorio;
}


public Integer getFaturaRevisaoRecorrenciaCadastroProvisorio() {
	return faturaRevisaoRecorrenciaCadastroProvisorio;
}


public Integer getFaturaRevisaoFaturamentoIndevidoCadastroProvisorio() {
	return faturaRevisaoFaturamentoIndevidoCadastroProvisorio;
}

public void setHidSubstituidosRamalChafariz(Integer hidSubstituidosRamalChafariz) {
	this.hidSubstituidosRamalChafariz = hidSubstituidosRamalChafariz;
}


public void setHidInstaladosRamalChafariz(Integer hidInstaladosRamalChafariz) {
	this.hidInstaladosRamalChafariz = hidInstaladosRamalChafariz;
}

public void setHidSubstituidosPocoChafariz(Integer hidSubstituidosPocoChafariz) {
	this.hidSubstituidosPocoChafariz = hidSubstituidosPocoChafariz;
}


public void setHidInstaladosPocoChafariz(Integer hidInstaladosPocoChafariz) {
	this.hidInstaladosPocoChafariz = hidInstaladosPocoChafariz;
}

public void setCortesExecutadosChafariz(Integer cortesExecutadosChafariz) {
	this.cortesExecutadosChafariz = cortesExecutadosChafariz;
}


public void setSuprExecutadasChafariz(Integer suprExecutadasChafariz) {
	this.suprExecutadasChafariz = suprExecutadasChafariz;
}


public void setReligacoesChafariz(Integer religacoesChafariz) {
	this.religacoesChafariz = religacoesChafariz;
}


public void setReestabChafariz(Integer reestabChafariz) {
	this.reestabChafariz = reestabChafariz;
}


public void setClandCortadosChafariz(Integer clandCortadosChafariz) {
	this.clandCortadosChafariz = clandCortadosChafariz;
}


public void setClandSuprimidosChafariz(Integer clandSuprimidosChafariz) {
	this.clandSuprimidosChafariz = clandSuprimidosChafariz;
}


public void setContasEmitidasChafariz(Integer contasEmitidasChafariz) {
	this.contasEmitidasChafariz = contasEmitidasChafariz;
}


public void setLeitEfetuadasChafariz(Integer leitEfetuadasChafariz) {
	this.leitEfetuadasChafariz = leitEfetuadasChafariz;
}


public void setLeitAnormalidadesChafariz(Integer leitAnormalidadesChafariz) {
	this.leitAnormalidadesChafariz = leitAnormalidadesChafariz;
}


public void setAvisoCorteChafariz(Integer avisoCorteChafariz) {
	this.avisoCorteChafariz = avisoCorteChafariz;
}


public void setPercAnormalidadeChafariz(BigDecimal percAnormalidadeChafariz) {
	this.percAnormalidadeChafariz = percAnormalidadeChafariz;
}


public void setPercHidrometracaoChafariz(BigDecimal percHidrometracaoChafariz) {
	this.percHidrometracaoChafariz = percHidrometracaoChafariz;
}


public void setLigImplantadasAguaChafariz(Integer ligImplantadasAguaChafariz) {
	this.ligImplantadasAguaChafariz = ligImplantadasAguaChafariz;
}


public void setLigImplantadasEsgotoChafariz(Integer ligImplantadasEsgotoChafariz) {
	this.ligImplantadasEsgotoChafariz = ligImplantadasEsgotoChafariz;
}


public void setRaPendentesComPrazoChafariz(Integer raPendentesComPrazoChafariz) {
	this.raPendentesComPrazoChafariz = raPendentesComPrazoChafariz;
}


public void setRaPendentesComForaPrazoChafariz(
		Integer raPendentesComForaPrazoChafariz) {
	this.raPendentesComForaPrazoChafariz = raPendentesComForaPrazoChafariz;
}


public void setRaPendentesOpPrazoChafariz(Integer raPendentesOpPrazoChafariz) {
	this.raPendentesOpPrazoChafariz = raPendentesOpPrazoChafariz;
}


public void setRaPendentesOpForaPrazoChafariz(
		Integer raPendentesOpForaPrazoChafariz) {
	this.raPendentesOpForaPrazoChafariz = raPendentesOpForaPrazoChafariz;
}


public void setFaturaRevisaoChafariz(Integer faturaRevisaoChafariz) {
	this.faturaRevisaoChafariz = faturaRevisaoChafariz;
}


public void setCartasNegativacaoChafariz(Integer cartasNegativacaoChafariz) {
	this.cartasNegativacaoChafariz = cartasNegativacaoChafariz;
}


public void setEconomiaAtivaChafariz(Integer economiaAtivaChafariz) {
	this.economiaAtivaChafariz = economiaAtivaChafariz;
}


public void setEconomiaInativaChafariz(Integer economiaInativaChafariz) {
	this.economiaInativaChafariz = economiaInativaChafariz;
}


public void setLigacoesAtivasChafariz(Integer ligacoesAtivasChafariz) {
	this.ligacoesAtivasChafariz = ligacoesAtivasChafariz;
}


public void setLigacoesInativasChafariz(Integer ligacoesInativasChafariz) {
	this.ligacoesInativasChafariz = ligacoesInativasChafariz;
}


public void setRegAnormalidadeInformadaChafariz(
		Integer regAnormalidadeInformadaChafariz) {
	this.regAnormalidadeInformadaChafariz = regAnormalidadeInformadaChafariz;
}


public void setRegRecebidosChafariz(Integer regRecebidosChafariz) {
	this.regRecebidosChafariz = regRecebidosChafariz;
}


public void setLigAtivasHidrometroChafariz(Integer ligAtivasHidrometroChafariz) {
	this.ligAtivasHidrometroChafariz = ligAtivasHidrometroChafariz;
}


public void setLigAtivasChafariz(Integer ligAtivasChafariz) {
	this.ligAtivasChafariz = ligAtivasChafariz;
}


public void setFaturaRevisaoReclamacaoConsumoChafariz(
		Integer faturaRevisaoReclamacaoConsumoChafariz) {
	this.faturaRevisaoReclamacaoConsumoChafariz = faturaRevisaoReclamacaoConsumoChafariz;
}


public void setFaturaRevisaoRecorrenciaChafariz(
		Integer faturaRevisaoRecorrenciaChafariz) {
	this.faturaRevisaoRecorrenciaChafariz = faturaRevisaoRecorrenciaChafariz;
}


public void setFaturaRevisaoFaturamentoIndevidoChafariz(
		Integer faturaRevisaoFaturamentoIndevidoChafariz) {
	this.faturaRevisaoFaturamentoIndevidoChafariz = faturaRevisaoFaturamentoIndevidoChafariz;
}

public void setHidSubstituidosRamalMicroTelemedido(Integer hidSubstituidosRamalMicroTelemedido) {
	this.hidSubstituidosRamalMicroTelemedido = hidSubstituidosRamalMicroTelemedido;
}


public void setHidInstaladosRamalMicroTelemedido(Integer hidInstaladosRamalMicroTelemedido) {
	this.hidInstaladosRamalMicroTelemedido = hidInstaladosRamalMicroTelemedido;
}

public void setHidSubstituidosPocoMicroTelemedido(Integer hidSubstituidosPocoMicroTelemedido) {
	this.hidSubstituidosPocoMicroTelemedido = hidSubstituidosPocoMicroTelemedido;
}


public void setHidInstaladosPocoMicroTelemedido(Integer hidInstaladosPocoMicroTelemedido) {
	this.hidInstaladosPocoMicroTelemedido = hidInstaladosPocoMicroTelemedido;
}

public void setCortesExecutadosMicroTelemedido(
		Integer cortesExecutadosMicroTelemedido) {
	this.cortesExecutadosMicroTelemedido = cortesExecutadosMicroTelemedido;
}


public void setSuprExecutadasMicroTelemedido(
		Integer suprExecutadasMicroTelemedido) {
	this.suprExecutadasMicroTelemedido = suprExecutadasMicroTelemedido;
}


public void setReligacoesMicroTelemedido(Integer religacoesMicroTelemedido) {
	this.religacoesMicroTelemedido = religacoesMicroTelemedido;
}


public void setReestabMicroTelemedido(Integer reestabMicroTelemedido) {
	this.reestabMicroTelemedido = reestabMicroTelemedido;
}


public void setClandCortadosMicroTelemedido(Integer clandCortadosMicroTelemedido) {
	this.clandCortadosMicroTelemedido = clandCortadosMicroTelemedido;
}


public void setClandSuprimidosMicroTelemedido(
		Integer clandSuprimidosMicroTelemedido) {
	this.clandSuprimidosMicroTelemedido = clandSuprimidosMicroTelemedido;
}


public void setContasEmitidasMicroTelemedido(
		Integer contasEmitidasMicroTelemedido) {
	this.contasEmitidasMicroTelemedido = contasEmitidasMicroTelemedido;
}


public void setLeitEfetuadasMicroTelemedido(Integer leitEfetuadasMicroTelemedido) {
	this.leitEfetuadasMicroTelemedido = leitEfetuadasMicroTelemedido;
}


public void setLeitAnormalidadesMicroTelemedido(
		Integer leitAnormalidadesMicroTelemedido) {
	this.leitAnormalidadesMicroTelemedido = leitAnormalidadesMicroTelemedido;
}


public void setAvisoCorteMicroTelemedido(Integer avisoCorteMicroTelemedido) {
	this.avisoCorteMicroTelemedido = avisoCorteMicroTelemedido;
}


public void setPercAnormalidadeMicroTelemedido(
		BigDecimal percAnormalidadeMicroTelemedido) {
	this.percAnormalidadeMicroTelemedido = percAnormalidadeMicroTelemedido;
}


public void setPercHidrometracaoMicroTelemedido(
		BigDecimal percHidrometracaoMicroTelemedido) {
	this.percHidrometracaoMicroTelemedido = percHidrometracaoMicroTelemedido;
}


public void setLigImplantadasAguaMicroTelemedido(
		Integer ligImplantadasAguaMicroTelemedido) {
	this.ligImplantadasAguaMicroTelemedido = ligImplantadasAguaMicroTelemedido;
}


public void setLigImplantadasEsgotoMicroTelemedido(
		Integer ligImplantadasEsgotoMicroTelemedido) {
	this.ligImplantadasEsgotoMicroTelemedido = ligImplantadasEsgotoMicroTelemedido;
}


public void setRaPendentesComPrazoMicroTelemedido(
		Integer raPendentesComPrazoMicroTelemedido) {
	this.raPendentesComPrazoMicroTelemedido = raPendentesComPrazoMicroTelemedido;
}


public void setRaPendentesComForaPrazoMicroTelemedido(
		Integer raPendentesComForaPrazoMicroTelemedido) {
	this.raPendentesComForaPrazoMicroTelemedido = raPendentesComForaPrazoMicroTelemedido;
}


public void setRaPendentesOpPrazoMicroTelemedido(
		Integer raPendentesOpPrazoMicroTelemedido) {
	this.raPendentesOpPrazoMicroTelemedido = raPendentesOpPrazoMicroTelemedido;
}


public void setRaPendentesOpForaPrazoMicroTelemedido(
		Integer raPendentesOpForaPrazoMicroTelemedido) {
	this.raPendentesOpForaPrazoMicroTelemedido = raPendentesOpForaPrazoMicroTelemedido;
}


public void setFaturaRevisaoMicroTelemedido(Integer faturaRevisaoMicroTelemedido) {
	this.faturaRevisaoMicroTelemedido = faturaRevisaoMicroTelemedido;
}


public void setCartasNegativacaoMicroTelemedido(
		Integer cartasNegativacaoMicroTelemedido) {
	this.cartasNegativacaoMicroTelemedido = cartasNegativacaoMicroTelemedido;
}


public void setEconomiaAtivaMicroTelemedido(Integer economiaAtivaMicroTelemedido) {
	this.economiaAtivaMicroTelemedido = economiaAtivaMicroTelemedido;
}


public void setEconomiaInativaMicroTelemedido(
		Integer economiaInativaMicroTelemedido) {
	this.economiaInativaMicroTelemedido = economiaInativaMicroTelemedido;
}


public void setLigacoesAtivasMicroTelemedido(
		Integer ligacoesAtivasMicroTelemedido) {
	this.ligacoesAtivasMicroTelemedido = ligacoesAtivasMicroTelemedido;
}


public void setLigacoesInativasMicroTelemedido(
		Integer ligacoesInativasMicroTelemedido) {
	this.ligacoesInativasMicroTelemedido = ligacoesInativasMicroTelemedido;
}


public void setRegAnormalidadeInformadaMicroTelemedido(
		Integer regAnormalidadeInformadaMicroTelemedido) {
	this.regAnormalidadeInformadaMicroTelemedido = regAnormalidadeInformadaMicroTelemedido;
}


public void setRegRecebidosMicroTelemedido(Integer regRecebidosMicroTelemedido) {
	this.regRecebidosMicroTelemedido = regRecebidosMicroTelemedido;
}


public void setLigAtivasHidrometroMicroTelemedido(
		Integer ligAtivasHidrometroMicroTelemedido) {
	this.ligAtivasHidrometroMicroTelemedido = ligAtivasHidrometroMicroTelemedido;
}


public void setLigAtivasMicroTelemedido(Integer ligAtivasMicroTelemedido) {
	this.ligAtivasMicroTelemedido = ligAtivasMicroTelemedido;
}


public void setFaturaRevisaoReclamacaoConsumoMicroTelemedido(
		Integer faturaRevisaoReclamacaoConsumoMicroTelemedido) {
	this.faturaRevisaoReclamacaoConsumoMicroTelemedido = faturaRevisaoReclamacaoConsumoMicroTelemedido;
}


public void setFaturaRevisaoRecorrenciaMicroTelemedido(
		Integer faturaRevisaoRecorrenciaMicroTelemedido) {
	this.faturaRevisaoRecorrenciaMicroTelemedido = faturaRevisaoRecorrenciaMicroTelemedido;
}


public void setFaturaRevisaoFaturamentoIndevidoMicroTelemedido(
		Integer faturaRevisaoFaturamentoIndevidoMicroTelemedido) {
	this.faturaRevisaoFaturamentoIndevidoMicroTelemedido = faturaRevisaoFaturamentoIndevidoMicroTelemedido;
}


public void setHidSubstituidosRamalCadastroProvisorio(Integer hidSubstituidosRamalCadastroProvisorio) {
	this.hidSubstituidosRamalCadastroProvisorio = hidSubstituidosRamalCadastroProvisorio;
}


public void setHidInstaladosRamalCadastroProvisorio(Integer hidInstaladosRamalCadastroProvisorio) {
	this.hidInstaladosRamalCadastroProvisorio = hidInstaladosRamalCadastroProvisorio;
}

public void setHidSubstituidosPocoCadastroProvisorio(Integer hidSubstituidosPocoCadastroProvisorio) {
	this.hidSubstituidosPocoCadastroProvisorio = hidSubstituidosPocoCadastroProvisorio;
}


public void setHidInstaladosPocoCadastroProvisorio(Integer hidInstaladosPocoCadastroProvisorio) {
	this.hidInstaladosPocoCadastroProvisorio = hidInstaladosPocoCadastroProvisorio;
}


public void setCortesExecutadosCadastroProvisorio(
		Integer cortesExecutadosCadastroProvisorio) {
	this.cortesExecutadosCadastroProvisorio = cortesExecutadosCadastroProvisorio;
}


public void setSuprExecutadasCadastroProvisorio(
		Integer suprExecutadasCadastroProvisorio) {
	this.suprExecutadasCadastroProvisorio = suprExecutadasCadastroProvisorio;
}


public void setReligacoesCadastroProvisorio(Integer religacoesCadastroProvisorio) {
	this.religacoesCadastroProvisorio = religacoesCadastroProvisorio;
}


public void setReestabCadastroProvisorio(Integer reestabCadastroProvisorio) {
	this.reestabCadastroProvisorio = reestabCadastroProvisorio;
}


public void setClandCortadosCadastroProvisorio(
		Integer clandCortadosCadastroProvisorio) {
	this.clandCortadosCadastroProvisorio = clandCortadosCadastroProvisorio;
}


public void setClandSuprimidosCadastroProvisorio(
		Integer clandSuprimidosCadastroProvisorio) {
	this.clandSuprimidosCadastroProvisorio = clandSuprimidosCadastroProvisorio;
}


public void setContasEmitidasCadastroProvisorio(
		Integer contasEmitidasCadastroProvisorio) {
	this.contasEmitidasCadastroProvisorio = contasEmitidasCadastroProvisorio;
}


public void setLeitEfetuadasCadastroProvisorio(
		Integer leitEfetuadasCadastroProvisorio) {
	this.leitEfetuadasCadastroProvisorio = leitEfetuadasCadastroProvisorio;
}


public void setLeitAnormalidadesCadastroProvisorio(
		Integer leitAnormalidadesCadastroProvisorio) {
	this.leitAnormalidadesCadastroProvisorio = leitAnormalidadesCadastroProvisorio;
}


public void setAvisoCorteCadastroProvisorio(Integer avisoCorteCadastroProvisorio) {
	this.avisoCorteCadastroProvisorio = avisoCorteCadastroProvisorio;
}


public void setPercAnormalidadeCadastroProvisorio(
		BigDecimal percAnormalidadeCadastroProvisorio) {
	this.percAnormalidadeCadastroProvisorio = percAnormalidadeCadastroProvisorio;
}


public void setPercHidrometracaoCadastroProvisorio(
		BigDecimal percHidrometracaoCadastroProvisorio) {
	this.percHidrometracaoCadastroProvisorio = percHidrometracaoCadastroProvisorio;
}


public void setLigImplantadasAguaCadastroProvisorio(
		Integer ligImplantadasAguaCadastroProvisorio) {
	this.ligImplantadasAguaCadastroProvisorio = ligImplantadasAguaCadastroProvisorio;
}


public void setLigImplantadasEsgotoCadastroProvisorio(
		Integer ligImplantadasEsgotoCadastroProvisorio) {
	this.ligImplantadasEsgotoCadastroProvisorio = ligImplantadasEsgotoCadastroProvisorio;
}


public void setRaPendentesComPrazoCadastroProvisorio(
		Integer raPendentesComPrazoCadastroProvisorio) {
	this.raPendentesComPrazoCadastroProvisorio = raPendentesComPrazoCadastroProvisorio;
}


public void setRaPendentesComForaPrazoCadastroProvisorio(
		Integer raPendentesComForaPrazoCadastroProvisorio) {
	this.raPendentesComForaPrazoCadastroProvisorio = raPendentesComForaPrazoCadastroProvisorio;
}


public void setRaPendentesOpPrazoCadastroProvisorio(
		Integer raPendentesOpPrazoCadastroProvisorio) {
	this.raPendentesOpPrazoCadastroProvisorio = raPendentesOpPrazoCadastroProvisorio;
}


public void setRaPendentesOpForaPrazoCadastroProvisorio(
		Integer raPendentesOpForaPrazoCadastroProvisorio) {
	this.raPendentesOpForaPrazoCadastroProvisorio = raPendentesOpForaPrazoCadastroProvisorio;
}


public void setFaturaRevisaoCadastroProvisorio(
		Integer faturaRevisaoCadastroProvisorio) {
	this.faturaRevisaoCadastroProvisorio = faturaRevisaoCadastroProvisorio;
}


public void setCartasNegativacaoCadastroProvisorio(
		Integer cartasNegativacaoCadastroProvisorio) {
	this.cartasNegativacaoCadastroProvisorio = cartasNegativacaoCadastroProvisorio;
}


public void setEconomiaAtivaCadastroProvisorio(
		Integer economiaAtivaCadastroProvisorio) {
	this.economiaAtivaCadastroProvisorio = economiaAtivaCadastroProvisorio;
}


public void setEconomiaInativaCadastroProvisorio(
		Integer economiaInativaCadastroProvisorio) {
	this.economiaInativaCadastroProvisorio = economiaInativaCadastroProvisorio;
}


public void setLigacoesAtivasCadastroProvisorio(
		Integer ligacoesAtivasCadastroProvisorio) {
	this.ligacoesAtivasCadastroProvisorio = ligacoesAtivasCadastroProvisorio;
}


public void setLigacoesInativasCadastroProvisorio(
		Integer ligacoesInativasCadastroProvisorio) {
	this.ligacoesInativasCadastroProvisorio = ligacoesInativasCadastroProvisorio;
}


public void setRegAnormalidadeInformadaCadastroProvisorio(
		Integer regAnormalidadeInformadaCadastroProvisorio) {
	this.regAnormalidadeInformadaCadastroProvisorio = regAnormalidadeInformadaCadastroProvisorio;
}


public void setRegRecebidosCadastroProvisorio(
		Integer regRecebidosCadastroProvisorio) {
	this.regRecebidosCadastroProvisorio = regRecebidosCadastroProvisorio;
}


public void setLigAtivasHidrometroCadastroProvisorio(
		Integer ligAtivasHidrometroCadastroProvisorio) {
	this.ligAtivasHidrometroCadastroProvisorio = ligAtivasHidrometroCadastroProvisorio;
}


public void setLigAtivasCadastroProvisorio(Integer ligAtivasCadastroProvisorio) {
	this.ligAtivasCadastroProvisorio = ligAtivasCadastroProvisorio;
}


public void setFaturaRevisaoReclamacaoConsumoCadastroProvisorio(
		Integer faturaRevisaoReclamacaoConsumoCadastroProvisorio) {
	this.faturaRevisaoReclamacaoConsumoCadastroProvisorio = faturaRevisaoReclamacaoConsumoCadastroProvisorio;
}


public void setFaturaRevisaoRecorrenciaCadastroProvisorio(
		Integer faturaRevisaoRecorrenciaCadastroProvisorio) {
	this.faturaRevisaoRecorrenciaCadastroProvisorio = faturaRevisaoRecorrenciaCadastroProvisorio;
}


public void setFaturaRevisaoFaturamentoIndevidoCadastroProvisorio(
		Integer faturaRevisaoFaturamentoIndevidoCadastroProvisorio) {
	this.faturaRevisaoFaturamentoIndevidoCadastroProvisorio = faturaRevisaoFaturamentoIndevidoCadastroProvisorio;
}


public static long getSerialversionuid() {
	return serialVersionUID;
}

public Integer getAlteracaoCategoriaResidencial() {
	return alteracaoCategoriaResidencial;
}


public Integer getInclusaoTarifaSocialResidencial() {
	return inclusaoTarifaSocialResidencial;
}


public Integer getExclusaoTarifaSocialResidencial() {
	return exclusaoTarifaSocialResidencial;
}


public Integer getFaturamentoSuspensoResidencial() {
	return faturamentoSuspensoResidencial;
}



public Integer getAlteracaoCategoriaComercial() {
	return alteracaoCategoriaComercial;
}


public Integer getInclusaoTarifaSocialComercial() {
	return inclusaoTarifaSocialComercial;
}


public Integer getExclusaoTarifaSocialComercial() {
	return exclusaoTarifaSocialComercial;
}


public Integer getFaturamentoSuspensoComercial() {
	return faturamentoSuspensoComercial;
}

public Integer getAlteracaoCategoriaIndustrial() {
	return alteracaoCategoriaIndustrial;
}


public Integer getInclusaoTarifaSocialIndustrial() {
	return inclusaoTarifaSocialIndustrial;
}


public Integer getExclusaoTarifaSocialIndustrial() {
	return exclusaoTarifaSocialIndustrial;
}


public Integer getFaturamentoSuspensoIndustrial() {
	return faturamentoSuspensoIndustrial;
}

public Integer getAlteracaoCategoriaPublico() {
	return alteracaoCategoriaPublico;
}


public Integer getInclusaoTarifaSocialPublico() {
	return inclusaoTarifaSocialPublico;
}


public Integer getExclusaoTarifaSocialPublico() {
	return exclusaoTarifaSocialPublico;
}


public Integer getFaturamentoSuspensoPublico() {
	return faturamentoSuspensoPublico;
}

public Integer getAlteracaoCategoriaNormal() {
	return alteracaoCategoriaNormal;
}


public Integer getInclusaoTarifaSocialNormal() {
	return inclusaoTarifaSocialNormal;
}


public Integer getExclusaoTarifaSocialNormal() {
	return exclusaoTarifaSocialNormal;
}


public Integer getFaturamentoSuspensoNormal() {
	return faturamentoSuspensoNormal;
}

public Integer getAlteracaoCategoriaTarSocial() {
	return alteracaoCategoriaTarSocial;
}


public Integer getInclusaoTarifaSocialTarSocial() {
	return inclusaoTarifaSocialTarSocial;
}


public Integer getExclusaoTarifaSocialTarSocial() {
	return exclusaoTarifaSocialTarSocial;
}


public Integer getFaturamentoSuspensoTarSocial() {
	return faturamentoSuspensoTarSocial;
}

public Integer getAlteracaoCategoriaGrande() {
	return alteracaoCategoriaGrande;
}


public Integer getInclusaoTarifaSocialGrande() {
	return inclusaoTarifaSocialGrande;
}


public Integer getExclusaoTarifaSocialGrande() {
	return exclusaoTarifaSocialGrande;
}


public Integer getFaturamentoSuspensoGrande() {
	return faturamentoSuspensoGrande;
}


public Integer getAlteracaoCategoriaCorporativo() {
	return alteracaoCategoriaCorporativo;
}


public Integer getInclusaoTarifaSocialCorporativo() {
	return inclusaoTarifaSocialCorporativo;
}


public Integer getExclusaoTarifaSocialCorporativo() {
	return exclusaoTarifaSocialCorporativo;
}


public Integer getFaturamentoSuspensoCorporativo() {
	return faturamentoSuspensoCorporativo;
}


public Integer getAlteracaoCategoriaGrandeTelemedido() {
	return alteracaoCategoriaGrandeTelemedido;
}


public Integer getInclusaoTarifaSocialGrandeTelemedido() {
	return inclusaoTarifaSocialGrandeTelemedido;
}


public Integer getExclusaoTarifaSocialGrandeTelemedido() {
	return exclusaoTarifaSocialGrandeTelemedido;
}


public Integer getFaturamentoSuspensoGrandeTelemedido() {
	return faturamentoSuspensoGrandeTelemedido;
}


public Integer getAlteracaoCategoriaCorpTelemedido() {
	return alteracaoCategoriaCorpTelemedido;
}


public Integer getInclusaoTarifaSocialCorpTelemedido() {
	return inclusaoTarifaSocialCorpTelemedido;
}


public Integer getExclusaoTarifaSocialCorpTelemedido() {
	return exclusaoTarifaSocialCorpTelemedido;
}


public Integer getFaturamentoSuspensoCorpTelemedido() {
	return faturamentoSuspensoCorpTelemedido;
}


public Integer getAlteracaoCategoriaChafariz() {
	return alteracaoCategoriaChafariz;
}


public Integer getInclusaoTarifaSocialChafariz() {
	return inclusaoTarifaSocialChafariz;
}


public Integer getExclusaoTarifaSocialChafariz() {
	return exclusaoTarifaSocialChafariz;
}


public Integer getFaturamentoSuspensoChafariz() {
	return faturamentoSuspensoChafariz;
}


public Integer getAlteracaoCategoriaMicroTelemedido() {
	return alteracaoCategoriaMicroTelemedido;
}


public Integer getInclusaoTarifaSocialMicroTelemedido() {
	return inclusaoTarifaSocialMicroTelemedido;
}


public Integer getExclusaoTarifaSocialMicroTelemedido() {
	return exclusaoTarifaSocialMicroTelemedido;
}


public Integer getFaturamentoSuspensoMicroTelemedido() {
	return faturamentoSuspensoMicroTelemedido;
}


public Integer getAlteracaoCategoriaCadastroProvisorio() {
	return alteracaoCategoriaCadastroProvisorio;
}


public Integer getInclusaoTarifaSocialCadastroProvisorio() {
	return inclusaoTarifaSocialCadastroProvisorio;
}


public Integer getExclusaoTarifaSocialCadastroProvisorio() {
	return exclusaoTarifaSocialCadastroProvisorio;
}


public Integer getFaturamentoSuspensoCadastroProvisorio() {
	return faturamentoSuspensoCadastroProvisorio;
}


public Integer getAlteracaoNumeroEconomiasAcrescidoGrande() {
	return alteracaoNumeroEconomiasAcrescidoGrande;
}


public Integer getAlteracaoNumeroEconomiasDecrescidoGrande() {
	return alteracaoNumeroEconomiasDecrescidoGrande;
}


public Integer getAlteracaoNumeroEconomiasAcrescidoResidencial() {
	return alteracaoNumeroEconomiasAcrescidoResidencial;
}


public void setAlteracaoNumeroEconomiasAcrescidoResidencial(Integer alteracaoNumeroEconomiasAcrescidoResidencial) {
	this.alteracaoNumeroEconomiasAcrescidoResidencial = alteracaoNumeroEconomiasAcrescidoResidencial;
}


public Integer getAlteracaoNumeroEconomiasDecrescidoResidencial() {
	return alteracaoNumeroEconomiasDecrescidoResidencial;
}


public void setAlteracaoNumeroEconomiasDecrescidoResidencial(Integer alteracaoNumeroEconomiasDecrescidoResidencial) {
	this.alteracaoNumeroEconomiasDecrescidoResidencial = alteracaoNumeroEconomiasDecrescidoResidencial;
}


public Integer getAlteracaoQuantidadeEconomiasAcrescidoResidencial() {
	return alteracaoQuantidadeEconomiasAcrescidoResidencial;
}


public void setAlteracaoQuantidadeEconomiasAcrescidoResidencial(Integer alteracaoQuantidadeEconomiasAcrescidoResidencial) {
	this.alteracaoQuantidadeEconomiasAcrescidoResidencial = alteracaoQuantidadeEconomiasAcrescidoResidencial;
}


public Integer getAlteracaoQuantidadeEconomiasDecrescidoResidencial() {
	return alteracaoQuantidadeEconomiasDecrescidoResidencial;
}


public void setAlteracaoQuantidadeEconomiasDecrescidoResidencial(Integer alteracaoQuantidadeEconomiasDecrescidoResidencial) {
	this.alteracaoQuantidadeEconomiasDecrescidoResidencial = alteracaoQuantidadeEconomiasDecrescidoResidencial;
}


public Integer getAlteracaoNumeroEconomiasAcrescidoComercial() {
	return alteracaoNumeroEconomiasAcrescidoComercial;
}


public void setAlteracaoNumeroEconomiasAcrescidoComercial(Integer alteracaoNumeroEconomiasAcrescidoComercial) {
	this.alteracaoNumeroEconomiasAcrescidoComercial = alteracaoNumeroEconomiasAcrescidoComercial;
}


public Integer getAlteracaoNumeroEconomiasDecrescidoComercial() {
	return alteracaoNumeroEconomiasDecrescidoComercial;
}


public void setAlteracaoNumeroEconomiasDecrescidoComercial(Integer alteracaoNumeroEconomiasDecrescidoComercial) {
	this.alteracaoNumeroEconomiasDecrescidoComercial = alteracaoNumeroEconomiasDecrescidoComercial;
}


public Integer getAlteracaoQuantidadeEconomiasAcrescidoComercial() {
	return alteracaoQuantidadeEconomiasAcrescidoComercial;
}


public void setAlteracaoQuantidadeEconomiasAcrescidoComercial(Integer alteracaoQuantidadeEconomiasAcrescidoComercial) {
	this.alteracaoQuantidadeEconomiasAcrescidoComercial = alteracaoQuantidadeEconomiasAcrescidoComercial;
}


public Integer getAlteracaoQuantidadeEconomiasDecrescidoComercial() {
	return alteracaoQuantidadeEconomiasDecrescidoComercial;
}


public void setAlteracaoQuantidadeEconomiasDecrescidoComercial(Integer alteracaoQuantidadeEconomiasDecrescidoComercial) {
	this.alteracaoQuantidadeEconomiasDecrescidoComercial = alteracaoQuantidadeEconomiasDecrescidoComercial;
}


public Integer getAlteracaoNumeroEconomiasAcrescidoIndustrial() {
	return alteracaoNumeroEconomiasAcrescidoIndustrial;
}


public void setAlteracaoNumeroEconomiasAcrescidoIndustrial(Integer alteracaoNumeroEconomiasAcrescidoIndustrial) {
	this.alteracaoNumeroEconomiasAcrescidoIndustrial = alteracaoNumeroEconomiasAcrescidoIndustrial;
}


public Integer getAlteracaoNumeroEconomiasDecrescidoIndustrial() {
	return alteracaoNumeroEconomiasDecrescidoIndustrial;
}


public void setAlteracaoNumeroEconomiasDecrescidoIndustrial(Integer alteracaoNumeroEconomiasDecrescidoIndustrial) {
	this.alteracaoNumeroEconomiasDecrescidoIndustrial = alteracaoNumeroEconomiasDecrescidoIndustrial;
}


public Integer getAlteracaoQuantidadeEconomiasAcrescidoIndustrial() {
	return alteracaoQuantidadeEconomiasAcrescidoIndustrial;
}


public void setAlteracaoQuantidadeEconomiasAcrescidoIndustrial(Integer alteracaoQuantidadeEconomiasAcrescidoIndustrial) {
	this.alteracaoQuantidadeEconomiasAcrescidoIndustrial = alteracaoQuantidadeEconomiasAcrescidoIndustrial;
}


public Integer getAlteracaoQuantidadeEconomiasDecrescidoIndustrial() {
	return alteracaoQuantidadeEconomiasDecrescidoIndustrial;
}


public void setAlteracaoQuantidadeEconomiasDecrescidoIndustrial(Integer alteracaoQuantidadeEconomiasDecrescidoIndustrial) {
	this.alteracaoQuantidadeEconomiasDecrescidoIndustrial = alteracaoQuantidadeEconomiasDecrescidoIndustrial;
}


public Integer getAlteracaoNumeroEconomiasAcrescidoPublico() {
	return alteracaoNumeroEconomiasAcrescidoPublico;
}


public void setAlteracaoNumeroEconomiasAcrescidoPublico(Integer alteracaoNumeroEconomiasAcrescidoPublico) {
	this.alteracaoNumeroEconomiasAcrescidoPublico = alteracaoNumeroEconomiasAcrescidoPublico;
}


public Integer getAlteracaoNumeroEconomiasDecrescidoPublico() {
	return alteracaoNumeroEconomiasDecrescidoPublico;
}


public void setAlteracaoNumeroEconomiasDecrescidoPublico(Integer alteracaoNumeroEconomiasDecrescidoPublico) {
	this.alteracaoNumeroEconomiasDecrescidoPublico = alteracaoNumeroEconomiasDecrescidoPublico;
}


public Integer getAlteracaoQuantidadeEconomiasAcrescidoPublico() {
	return alteracaoQuantidadeEconomiasAcrescidoPublico;
}


public void setAlteracaoQuantidadeEconomiasAcrescidoPublico(Integer alteracaoQuantidadeEconomiasAcrescidoPublico) {
	this.alteracaoQuantidadeEconomiasAcrescidoPublico = alteracaoQuantidadeEconomiasAcrescidoPublico;
}


public Integer getAlteracaoQuantidadeEconomiasDecrescidoPublico() {
	return alteracaoQuantidadeEconomiasDecrescidoPublico;
}


public void setAlteracaoQuantidadeEconomiasDecrescidoPublico(Integer alteracaoQuantidadeEconomiasDecrescidoPublico) {
	this.alteracaoQuantidadeEconomiasDecrescidoPublico = alteracaoQuantidadeEconomiasDecrescidoPublico;
}


public Integer getAlteracaoNumeroEconomiasAcrescidoNormal() {
	return alteracaoNumeroEconomiasAcrescidoNormal;
}


public void setAlteracaoNumeroEconomiasAcrescidoNormal(Integer alteracaoNumeroEconomiasAcrescidoNormal) {
	this.alteracaoNumeroEconomiasAcrescidoNormal = alteracaoNumeroEconomiasAcrescidoNormal;
}


public Integer getAlteracaoNumeroEconomiasDecrescidoNormal() {
	return alteracaoNumeroEconomiasDecrescidoNormal;
}


public void setAlteracaoNumeroEconomiasDecrescidoNormal(Integer alteracaoNumeroEconomiasDecrescidoNormal) {
	this.alteracaoNumeroEconomiasDecrescidoNormal = alteracaoNumeroEconomiasDecrescidoNormal;
}


public Integer getAlteracaoQuantidadeEconomiasAcrescidoNormal() {
	return alteracaoQuantidadeEconomiasAcrescidoNormal;
}


public void setAlteracaoQuantidadeEconomiasAcrescidoNormal(Integer alteracaoQuantidadeEconomiasAcrescidoNormal) {
	this.alteracaoQuantidadeEconomiasAcrescidoNormal = alteracaoQuantidadeEconomiasAcrescidoNormal;
}


public Integer getAlteracaoQuantidadeEconomiasDecrescidoNormal() {
	return alteracaoQuantidadeEconomiasDecrescidoNormal;
}


public void setAlteracaoQuantidadeEconomiasDecrescidoNormal(Integer alteracaoQuantidadeEconomiasDecrescidoNormal) {
	this.alteracaoQuantidadeEconomiasDecrescidoNormal = alteracaoQuantidadeEconomiasDecrescidoNormal;
}


public Integer getAlteracaoNumeroEconomiasAcrescidoTarSocial() {
	return alteracaoNumeroEconomiasAcrescidoTarSocial;
}


public void setAlteracaoNumeroEconomiasAcrescidoTarSocial(Integer alteracaoNumeroEconomiasAcrescidoTarSocial) {
	this.alteracaoNumeroEconomiasAcrescidoTarSocial = alteracaoNumeroEconomiasAcrescidoTarSocial;
}


public Integer getAlteracaoNumeroEconomiasDecrescidoTarSocial() {
	return alteracaoNumeroEconomiasDecrescidoTarSocial;
}


public void setAlteracaoNumeroEconomiasDecrescidoTarSocial(Integer alteracaoNumeroEconomiasDecrescidoTarSocial) {
	this.alteracaoNumeroEconomiasDecrescidoTarSocial = alteracaoNumeroEconomiasDecrescidoTarSocial;
}


public Integer getAlteracaoQuantidadeEconomiasAcrescidoTarSocial() {
	return alteracaoQuantidadeEconomiasAcrescidoTarSocial;
}


public void setAlteracaoQuantidadeEconomiasAcrescidoTarSocial(Integer alteracaoQuantidadeEconomiasAcrescidoTarSocial) {
	this.alteracaoQuantidadeEconomiasAcrescidoTarSocial = alteracaoQuantidadeEconomiasAcrescidoTarSocial;
}


public Integer getAlteracaoQuantidadeEconomiasDecrescidoTarSocial() {
	return alteracaoQuantidadeEconomiasDecrescidoTarSocial;
}


public void setAlteracaoQuantidadeEconomiasDecrescidoTarSocial(Integer alteracaoQuantidadeEconomiasDecrescidoTarSocial) {
	this.alteracaoQuantidadeEconomiasDecrescidoTarSocial = alteracaoQuantidadeEconomiasDecrescidoTarSocial;
}


public Integer getAlteracaoQuantidadeEconomiasAcrescidoGrande() {
	return alteracaoQuantidadeEconomiasAcrescidoGrande;
}


public void setAlteracaoQuantidadeEconomiasAcrescidoGrande(Integer alteracaoQuantidadeEconomiasAcrescidoGrande) {
	this.alteracaoQuantidadeEconomiasAcrescidoGrande = alteracaoQuantidadeEconomiasAcrescidoGrande;
}


public Integer getAlteracaoQuantidadeEconomiasDecrescidoGrande() {
	return alteracaoQuantidadeEconomiasDecrescidoGrande;
}


public void setAlteracaoQuantidadeEconomiasDecrescidoGrande(Integer alteracaoQuantidadeEconomiasDecrescidoGrande) {
	this.alteracaoQuantidadeEconomiasDecrescidoGrande = alteracaoQuantidadeEconomiasDecrescidoGrande;
}


public Integer getAlteracaoNumeroEconomiasAcrescidoCorporativo() {
	return alteracaoNumeroEconomiasAcrescidoCorporativo;
}


public void setAlteracaoNumeroEconomiasAcrescidoCorporativo(Integer alteracaoNumeroEconomiasAcrescidoCorporativo) {
	this.alteracaoNumeroEconomiasAcrescidoCorporativo = alteracaoNumeroEconomiasAcrescidoCorporativo;
}


public Integer getAlteracaoNumeroEconomiasDecrescidoCorporativo() {
	return alteracaoNumeroEconomiasDecrescidoCorporativo;
}


public void setAlteracaoNumeroEconomiasDecrescidoCorporativo(Integer alteracaoNumeroEconomiasDecrescidoCorporativo) {
	this.alteracaoNumeroEconomiasDecrescidoCorporativo = alteracaoNumeroEconomiasDecrescidoCorporativo;
}


public Integer getAlteracaoQuantidadeEconomiasAcrescidoCorporativo() {
	return alteracaoQuantidadeEconomiasAcrescidoCorporativo;
}


public void setAlteracaoQuantidadeEconomiasAcrescidoCorporativo(Integer alteracaoQuantidadeEconomiasAcrescidoCorporativo) {
	this.alteracaoQuantidadeEconomiasAcrescidoCorporativo = alteracaoQuantidadeEconomiasAcrescidoCorporativo;
}


public Integer getAlteracaoQuantidadeEconomiasDecrescidoCorporativo() {
	return alteracaoQuantidadeEconomiasDecrescidoCorporativo;
}


public void setAlteracaoQuantidadeEconomiasDecrescidoCorporativo(Integer alteracaoQuantidadeEconomiasDecrescidoCorporativo) {
	this.alteracaoQuantidadeEconomiasDecrescidoCorporativo = alteracaoQuantidadeEconomiasDecrescidoCorporativo;
}


public Integer getAlteracaoNumeroEconomiasAcrescidoGrandeTelemedido() {
	return alteracaoNumeroEconomiasAcrescidoGrandeTelemedido;
}


public void setAlteracaoNumeroEconomiasAcrescidoGrandeTelemedido(Integer alteracaoNumeroEconomiasAcrescidoGrandeTelemedido) {
	this.alteracaoNumeroEconomiasAcrescidoGrandeTelemedido = alteracaoNumeroEconomiasAcrescidoGrandeTelemedido;
}


public Integer getAlteracaoNumeroEconomiasDecrescidoGrandeTelemedido() {
	return alteracaoNumeroEconomiasDecrescidoGrandeTelemedido;
}


public void setAlteracaoNumeroEconomiasDecrescidoGrandeTelemedido(Integer alteracaoNumeroEconomiasDecrescidoGrandeTelemedido) {
	this.alteracaoNumeroEconomiasDecrescidoGrandeTelemedido = alteracaoNumeroEconomiasDecrescidoGrandeTelemedido;
}


public Integer getAlteracaoQuantidadeEconomiasAcrescidoGrandeTelemedido() {
	return alteracaoQuantidadeEconomiasAcrescidoGrandeTelemedido;
}


public void setAlteracaoQuantidadeEconomiasAcrescidoGrandeTelemedido(
		Integer alteracaoQuantidadeEconomiasAcrescidoGrandeTelemedido) {
	this.alteracaoQuantidadeEconomiasAcrescidoGrandeTelemedido = alteracaoQuantidadeEconomiasAcrescidoGrandeTelemedido;
}


public Integer getAlteracaoQuantidadeEconomiasDecrescidoGrandeTelemedido() {
	return alteracaoQuantidadeEconomiasDecrescidoGrandeTelemedido;
}


public void setAlteracaoQuantidadeEconomiasDecrescidoGrandeTelemedido(
		Integer alteracaoQuantidadeEconomiasDecrescidoGrandeTelemedido) {
	this.alteracaoQuantidadeEconomiasDecrescidoGrandeTelemedido = alteracaoQuantidadeEconomiasDecrescidoGrandeTelemedido;
}


public Integer getAlteracaoNumeroEconomiasAcrescidoCorpTelemedido() {
	return alteracaoNumeroEconomiasAcrescidoCorpTelemedido;
}


public void setAlteracaoNumeroEconomiasAcrescidoCorpTelemedido(Integer alteracaoNumeroEconomiasAcrescidoCorpTelemedido) {
	this.alteracaoNumeroEconomiasAcrescidoCorpTelemedido = alteracaoNumeroEconomiasAcrescidoCorpTelemedido;
}


public Integer getAlteracaoNumeroEconomiasDecrescidoCorpTelemedido() {
	return alteracaoNumeroEconomiasDecrescidoCorpTelemedido;
}


public void setAlteracaoNumeroEconomiasDecrescidoCorpTelemedido(Integer alteracaoNumeroEconomiasDecrescidoCorpTelemedido) {
	this.alteracaoNumeroEconomiasDecrescidoCorpTelemedido = alteracaoNumeroEconomiasDecrescidoCorpTelemedido;
}


public Integer getAlteracaoQuantidadeEconomiasAcrescidoCorpTelemedido() {
	return alteracaoQuantidadeEconomiasAcrescidoCorpTelemedido;
}


public void setAlteracaoQuantidadeEconomiasAcrescidoCorpTelemedido(
		Integer alteracaoQuantidadeEconomiasAcrescidoCorpTelemedido) {
	this.alteracaoQuantidadeEconomiasAcrescidoCorpTelemedido = alteracaoQuantidadeEconomiasAcrescidoCorpTelemedido;
}


public Integer getAlteracaoQuantidadeEconomiasDecrescidoCorpTelemedido() {
	return alteracaoQuantidadeEconomiasDecrescidoCorpTelemedido;
}


public void setAlteracaoQuantidadeEconomiasDecrescidoCorpTelemedido(
		Integer alteracaoQuantidadeEconomiasDecrescidoCorpTelemedido) {
	this.alteracaoQuantidadeEconomiasDecrescidoCorpTelemedido = alteracaoQuantidadeEconomiasDecrescidoCorpTelemedido;
}


public Integer getAlteracaoNumeroEconomiasAcrescidoChafariz() {
	return alteracaoNumeroEconomiasAcrescidoChafariz;
}


public void setAlteracaoNumeroEconomiasAcrescidoChafariz(Integer alteracaoNumeroEconomiasAcrescidoChafariz) {
	this.alteracaoNumeroEconomiasAcrescidoChafariz = alteracaoNumeroEconomiasAcrescidoChafariz;
}


public Integer getAlteracaoNumeroEconomiasDecrescidoChafariz() {
	return alteracaoNumeroEconomiasDecrescidoChafariz;
}


public void setAlteracaoNumeroEconomiasDecrescidoChafariz(Integer alteracaoNumeroEconomiasDecrescidoChafariz) {
	this.alteracaoNumeroEconomiasDecrescidoChafariz = alteracaoNumeroEconomiasDecrescidoChafariz;
}


public Integer getAlteracaoQuantidadeEconomiasAcrescidoChafariz() {
	return alteracaoQuantidadeEconomiasAcrescidoChafariz;
}


public void setAlteracaoQuantidadeEconomiasAcrescidoChafariz(Integer alteracaoQuantidadeEconomiasAcrescidoChafariz) {
	this.alteracaoQuantidadeEconomiasAcrescidoChafariz = alteracaoQuantidadeEconomiasAcrescidoChafariz;
}


public Integer getAlteracaoQuantidadeEconomiasDecrescidoChafariz() {
	return alteracaoQuantidadeEconomiasDecrescidoChafariz;
}


public void setAlteracaoQuantidadeEconomiasDecrescidoChafariz(Integer alteracaoQuantidadeEconomiasDecrescidoChafariz) {
	this.alteracaoQuantidadeEconomiasDecrescidoChafariz = alteracaoQuantidadeEconomiasDecrescidoChafariz;
}


public Integer getAlteracaoNumeroEconomiasAcrescidoMicroTelemedido() {
	return alteracaoNumeroEconomiasAcrescidoMicroTelemedido;
}


public void setAlteracaoNumeroEconomiasAcrescidoMicroTelemedido(Integer alteracaoNumeroEconomiasAcrescidoMicroTelemedido) {
	this.alteracaoNumeroEconomiasAcrescidoMicroTelemedido = alteracaoNumeroEconomiasAcrescidoMicroTelemedido;
}


public Integer getAlteracaoNumeroEconomiasDecrescidoMicroTelemedido() {
	return alteracaoNumeroEconomiasDecrescidoMicroTelemedido;
}


public void setAlteracaoNumeroEconomiasDecrescidoMicroTelemedido(Integer alteracaoNumeroEconomiasDecrescidoMicroTelemedido) {
	this.alteracaoNumeroEconomiasDecrescidoMicroTelemedido = alteracaoNumeroEconomiasDecrescidoMicroTelemedido;
}


public Integer getAlteracaoQuantidadeEconomiasAcrescidoMicroTelemedido() {
	return alteracaoQuantidadeEconomiasAcrescidoMicroTelemedido;
}


public void setAlteracaoQuantidadeEconomiasAcrescidoMicroTelemedido(
		Integer alteracaoQuantidadeEconomiasAcrescidoMicroTelemedido) {
	this.alteracaoQuantidadeEconomiasAcrescidoMicroTelemedido = alteracaoQuantidadeEconomiasAcrescidoMicroTelemedido;
}


public Integer getAlteracaoQuantidadeEconomiasDecrescidoMicroTelemedido() {
	return alteracaoQuantidadeEconomiasDecrescidoMicroTelemedido;
}


public void setAlteracaoQuantidadeEconomiasDecrescidoMicroTelemedido(
		Integer alteracaoQuantidadeEconomiasDecrescidoMicroTelemedido) {
	this.alteracaoQuantidadeEconomiasDecrescidoMicroTelemedido = alteracaoQuantidadeEconomiasDecrescidoMicroTelemedido;
}


public Integer getAlteracaoNumeroEconomiasAcrescidoCadastroProvisorio() {
	return alteracaoNumeroEconomiasAcrescidoCadastroProvisorio;
}


public void setAlteracaoNumeroEconomiasAcrescidoCadastroProvisorio(
		Integer alteracaoNumeroEconomiasAcrescidoCadastroProvisorio) {
	this.alteracaoNumeroEconomiasAcrescidoCadastroProvisorio = alteracaoNumeroEconomiasAcrescidoCadastroProvisorio;
}


public Integer getAlteracaoNumeroEconomiasDecrescidoCadastroProvisorio() {
	return alteracaoNumeroEconomiasDecrescidoCadastroProvisorio;
}


public void setAlteracaoNumeroEconomiasDecrescidoCadastroProvisorio(
		Integer alteracaoNumeroEconomiasDecrescidoCadastroProvisorio) {
	this.alteracaoNumeroEconomiasDecrescidoCadastroProvisorio = alteracaoNumeroEconomiasDecrescidoCadastroProvisorio;
}


public Integer getAlteracaoQuantidadeEconomiasAcrescidoCadastroProvisorio() {
	return alteracaoQuantidadeEconomiasAcrescidoCadastroProvisorio;
}


public void setAlteracaoQuantidadeEconomiasAcrescidoCadastroProvisorio(
		Integer alteracaoQuantidadeEconomiasAcrescidoCadastroProvisorio) {
	this.alteracaoQuantidadeEconomiasAcrescidoCadastroProvisorio = alteracaoQuantidadeEconomiasAcrescidoCadastroProvisorio;
}


public Integer getAlteracaoQuantidadeEconomiasDecrescidoCadastroProvisorio() {
	return alteracaoQuantidadeEconomiasDecrescidoCadastroProvisorio;
}


public void setAlteracaoQuantidadeEconomiasDecrescidoCadastroProvisorio(
		Integer alteracaoQuantidadeEconomiasDecrescidoCadastroProvisorio) {
	this.alteracaoQuantidadeEconomiasDecrescidoCadastroProvisorio = alteracaoQuantidadeEconomiasDecrescidoCadastroProvisorio;
}


public void setAlteracaoQuantidadeEconomiasAcrescidoTotalPerfil(Integer alteracaoQuantidadeEconomiasAcrescidoTotalPerfil) {
	this.alteracaoQuantidadeEconomiasAcrescidoTotalPerfil = alteracaoQuantidadeEconomiasAcrescidoTotalPerfil;
}


public void setAlteracaoQuantidadeEconomiasDecrescidoTotalPerfil(Integer alteracaoQuantidadeEconomiasDecrescidoTotalPerfil) {
	this.alteracaoQuantidadeEconomiasDecrescidoTotalPerfil = alteracaoQuantidadeEconomiasDecrescidoTotalPerfil;
}


public void setAlteracaoQuantidadeEconomiasAcrescidoTotalCategoria(
		Integer alteracaoQuantidadeEconomiasAcrescidoTotalCategoria) {
	this.alteracaoQuantidadeEconomiasAcrescidoTotalCategoria = alteracaoQuantidadeEconomiasAcrescidoTotalCategoria;
}


public void setAlteracaoQuantidadeEconomiasDecrescidoTotalCategoria(
		Integer alteracaoQuantidadeEconomiasDecrescidoTotalCategoria) {
	this.alteracaoQuantidadeEconomiasDecrescidoTotalCategoria = alteracaoQuantidadeEconomiasDecrescidoTotalCategoria;
}


public void setAlteracaoNumeroEconomiasAcrescidoGrande(Integer alteracaoNumeroEconomiasAcrescidoGrande) {
	this.alteracaoNumeroEconomiasAcrescidoGrande = alteracaoNumeroEconomiasAcrescidoGrande;
}


public void setAlteracaoNumeroEconomiasDecrescidoGrande(Integer alteracaoNumeroEconomiasDecrescidoGrande) {
	this.alteracaoNumeroEconomiasDecrescidoGrande = alteracaoNumeroEconomiasDecrescidoGrande;
}


public void setAlteracaoNumeroEconomiasAcrescidoTotalPerfil(Integer alteracaoNumeroEconomiasAcrescidoTotalPerfil) {
	this.alteracaoNumeroEconomiasAcrescidoTotalPerfil = alteracaoNumeroEconomiasAcrescidoTotalPerfil;
}


public void setAlteracaoNumeroEconomiasDecrescidoTotalPerfil(Integer alteracaoNumeroEconomiasDecrescidoTotalPerfil) {
	this.alteracaoNumeroEconomiasDecrescidoTotalPerfil = alteracaoNumeroEconomiasDecrescidoTotalPerfil;
}


public void setAlteracaoNumeroEconomiasAcrescidoTotalCategoria(Integer alteracaoNumeroEconomiasAcrescidoTotalCategoria) {
	this.alteracaoNumeroEconomiasAcrescidoTotalCategoria = alteracaoNumeroEconomiasAcrescidoTotalCategoria;
}


public void setAlteracaoNumeroEconomiasDecrescidoTotalCategoria(Integer alteracaoNumeroEconomiasDecrescidoTotalCategoria) {
	this.alteracaoNumeroEconomiasDecrescidoTotalCategoria = alteracaoNumeroEconomiasDecrescidoTotalCategoria;
}


public Integer getAlteracaoNumeroEconomiasAcrescidoTotalPerfil() {
	return  this.getAlteracaoNumeroEconomiasAcrescidoNormal() +
			this.getAlteracaoNumeroEconomiasAcrescidoTarSocial() +
			this.getAlteracaoNumeroEconomiasAcrescidoGrande() +
			this.getAlteracaoNumeroEconomiasAcrescidoCorporativo() +
			this.getAlteracaoNumeroEconomiasAcrescidoGrandeTelemedido() +
			this.getAlteracaoNumeroEconomiasAcrescidoCorpTelemedido() +
			this.getAlteracaoNumeroEconomiasAcrescidoChafariz() +
			this.getAlteracaoNumeroEconomiasAcrescidoMicroTelemedido() +
			this.getAlteracaoNumeroEconomiasAcrescidoCadastroProvisorio();
}


public Integer getAlteracaoQuantidadeEconomiasAcrescidoTotalPerfil() {
	return  this.getAlteracaoQuantidadeEconomiasAcrescidoNormal() +
			this.getAlteracaoQuantidadeEconomiasAcrescidoTarSocial() +
			this.getAlteracaoQuantidadeEconomiasAcrescidoGrande() +
			this.getAlteracaoQuantidadeEconomiasAcrescidoCorporativo() +
			this.getAlteracaoQuantidadeEconomiasAcrescidoGrandeTelemedido() +
			this.getAlteracaoQuantidadeEconomiasAcrescidoCorpTelemedido() +
			this.getAlteracaoQuantidadeEconomiasAcrescidoChafariz() +
			this.getAlteracaoQuantidadeEconomiasAcrescidoMicroTelemedido() +
			this.getAlteracaoQuantidadeEconomiasAcrescidoCadastroProvisorio();
}

public Integer getAlteracaoNumeroEconomiasDecrescidoTotalPerfil() {
	return  this.getAlteracaoNumeroEconomiasDecrescidoNormal() +
			this.getAlteracaoNumeroEconomiasDecrescidoTarSocial() + 
			this.getAlteracaoNumeroEconomiasDecrescidoGrande() +
			this.getAlteracaoNumeroEconomiasDecrescidoCorporativo() +
			this.getAlteracaoNumeroEconomiasDecrescidoGrandeTelemedido() +
			this.getAlteracaoNumeroEconomiasDecrescidoCorpTelemedido() +
			this.getAlteracaoNumeroEconomiasDecrescidoChafariz() +
			this.getAlteracaoNumeroEconomiasDecrescidoMicroTelemedido() +
			this.getAlteracaoNumeroEconomiasDecrescidoCadastroProvisorio();
}

public Integer getAlteracaoQuantidadeEconomiasDecrescidoTotalPerfil() {
	return  this.getAlteracaoQuantidadeEconomiasDecrescidoNormal() +
			this.getAlteracaoQuantidadeEconomiasDecrescidoTarSocial() + 
			this.getAlteracaoQuantidadeEconomiasDecrescidoGrande() +
			this.getAlteracaoQuantidadeEconomiasDecrescidoCorporativo() +
			this.getAlteracaoQuantidadeEconomiasDecrescidoGrandeTelemedido() +
			this.getAlteracaoQuantidadeEconomiasDecrescidoCorpTelemedido() +
			this.getAlteracaoQuantidadeEconomiasDecrescidoChafariz() +
			this.getAlteracaoQuantidadeEconomiasDecrescidoMicroTelemedido() +
			this.getAlteracaoQuantidadeEconomiasDecrescidoCadastroProvisorio();
}

public Integer getAlteracaoCategoriaTotalPerfil() {
	return new Integer(
			this.getAlteracaoCategoriaNormal().intValue()+
			this.getAlteracaoCategoriaTarSocial().intValue() + 
			this.getAlteracaoCategoriaGrande().intValue() +
			this.getAlteracaoCategoriaCorporativo().intValue()+
			this.getAlteracaoCategoriaGrandeTelemedido().intValue()+
			this.getAlteracaoCategoriaCorpTelemedido().intValue()+
			this.getAlteracaoCategoriaChafariz().intValue() +
			this.getAlteracaoCategoriaMicroTelemedido().intValue() +
			this.getAlteracaoCategoriaCadastroProvisorio().intValue()
	);
}


public Integer getInclusaoTarifaSocialTotalPerfil() {
	return new Integer(
			this.getInclusaoTarifaSocialNormal().intValue()+
			this.getInclusaoTarifaSocialTarSocial().intValue() + 
			this.getInclusaoTarifaSocialGrande().intValue() +
			this.getInclusaoTarifaSocialCorporativo().intValue()+
			this.getInclusaoTarifaSocialGrandeTelemedido().intValue()+
			this.getInclusaoTarifaSocialCorpTelemedido().intValue()+
			this.getInclusaoTarifaSocialChafariz().intValue() +
			this.getInclusaoTarifaSocialMicroTelemedido().intValue() +
			this.getInclusaoTarifaSocialCadastroProvisorio().intValue()
	);
}


public Integer getExclusaoTarifaSocialTotalPerfil() {
	return new Integer(
			this.getExclusaoTarifaSocialNormal().intValue()+
			this.getExclusaoTarifaSocialTarSocial().intValue() + 
			this.getExclusaoTarifaSocialGrande().intValue() +
			this.getExclusaoTarifaSocialCorporativo().intValue()+
			this.getExclusaoTarifaSocialGrandeTelemedido().intValue()+
			this.getExclusaoTarifaSocialCorpTelemedido().intValue()+
			this.getExclusaoTarifaSocialChafariz().intValue() +
			this.getExclusaoTarifaSocialMicroTelemedido().intValue() +
			this.getExclusaoTarifaSocialCadastroProvisorio().intValue()
	);
}


public Integer getFaturamentoSuspensoTotalPerfil() {
	return new Integer(
			this.getFaturamentoSuspensoNormal().intValue()+
			this.getFaturamentoSuspensoTarSocial().intValue() + 
			this.getFaturamentoSuspensoGrande().intValue() +
			this.getFaturamentoSuspensoCorporativo().intValue()+
			this.getFaturamentoSuspensoGrandeTelemedido().intValue()+
			this.getFaturamentoSuspensoCorpTelemedido().intValue()+
			this.getFaturamentoSuspensoChafariz().intValue() +
			this.getFaturamentoSuspensoMicroTelemedido().intValue() +
			this.getFaturamentoSuspensoCadastroProvisorio().intValue()
	);
}


public Integer getAlteracaoNumeroEconomiasAcrescidoTotalCategoria() {
	
	return	this.getAlteracaoNumeroEconomiasAcrescidoIndustrial() +
			this.getAlteracaoNumeroEconomiasAcrescidoComercial() +
			this.getAlteracaoNumeroEconomiasAcrescidoResidencial() +
			this.getAlteracaoNumeroEconomiasAcrescidoPublico();
	
}


public Integer getAlteracaoQuantidadeEconomiasAcrescidoTotalCategoria() {
	
	return	this.getAlteracaoQuantidadeEconomiasAcrescidoIndustrial() +
			this.getAlteracaoQuantidadeEconomiasAcrescidoComercial() +
			this.getAlteracaoQuantidadeEconomiasAcrescidoResidencial() +
			this.getAlteracaoQuantidadeEconomiasAcrescidoPublico();
	
}


public Integer getAlteracaoNumeroEconomiasDecrescidoTotalCategoria() {
	return	this.getAlteracaoNumeroEconomiasDecrescidoIndustrial() +
			this.getAlteracaoNumeroEconomiasDecrescidoComercial() +
			this.getAlteracaoNumeroEconomiasDecrescidoResidencial() +
			this.getAlteracaoNumeroEconomiasDecrescidoPublico();
}


public Integer getAlteracaoQuantidadeEconomiasDecrescidoTotalCategoria() {
	return	this.getAlteracaoQuantidadeEconomiasDecrescidoIndustrial() +
			this.getAlteracaoQuantidadeEconomiasDecrescidoComercial() +
			this.getAlteracaoQuantidadeEconomiasDecrescidoResidencial() +
			this.getAlteracaoQuantidadeEconomiasDecrescidoPublico();
}


public Integer getAlteracaoCategoriaTotalCategoria() {
	return new Integer(
			this.getAlteracaoCategoriaIndustrial().intValue()+
			this.getAlteracaoCategoriaComercial().intValue() + 
			this.getAlteracaoCategoriaResidencial().intValue() +
			this.getAlteracaoCategoriaPublico().intValue()
	);
}


public Integer getInclusaoTarifaSocialTotalCategoria() {
	return new Integer(
			this.getInclusaoTarifaSocialIndustrial().intValue()+
			this.getInclusaoTarifaSocialComercial().intValue() + 
			this.getInclusaoTarifaSocialResidencial().intValue() +
			this.getInclusaoTarifaSocialPublico().intValue()
	);
}


public Integer getExclusaoTarifaSocialTotalCategoria() {
	return new Integer(
			this.getExclusaoTarifaSocialIndustrial().intValue()+
			this.getExclusaoTarifaSocialComercial().intValue() + 
			this.getExclusaoTarifaSocialResidencial().intValue() +
			this.getExclusaoTarifaSocialPublico().intValue()
	);
}


public Integer getFaturamentoSuspensoTotalCategoria() {
	return new Integer(
			this.getFaturamentoSuspensoIndustrial().intValue()+
			this.getFaturamentoSuspensoComercial().intValue() + 
			this.getFaturamentoSuspensoResidencial().intValue() +
			this.getFaturamentoSuspensoPublico().intValue()
	);
}

public void setAlteracaoCategoriaResidencial(
		Integer alteracaoCategoriaResidencial) {
	this.alteracaoCategoriaResidencial = alteracaoCategoriaResidencial;
}


public void setInclusaoTarifaSocialResidencial(
		Integer inclusaoTarifaSocialResidencial) {
	this.inclusaoTarifaSocialResidencial = inclusaoTarifaSocialResidencial;
}


public void setExclusaoTarifaSocialResidencial(
		Integer exclusaoTarifaSocialResidencial) {
	this.exclusaoTarifaSocialResidencial = exclusaoTarifaSocialResidencial;
}


public void setFaturamentoSuspensoResidencial(
		Integer faturamentoSuspensoResidencial) {
	this.faturamentoSuspensoResidencial = faturamentoSuspensoResidencial;
}

public void setAlteracaoCategoriaComercial(Integer alteracaoCategoriaComercial) {
	this.alteracaoCategoriaComercial = alteracaoCategoriaComercial;
}


public void setInclusaoTarifaSocialComercial(
		Integer inclusaoTarifaSocialComercial) {
	this.inclusaoTarifaSocialComercial = inclusaoTarifaSocialComercial;
}


public void setExclusaoTarifaSocialComercial(
		Integer exclusaoTarifaSocialComercial) {
	this.exclusaoTarifaSocialComercial = exclusaoTarifaSocialComercial;
}


public void setFaturamentoSuspensoComercial(Integer faturamentoSuspensoComercial) {
	this.faturamentoSuspensoComercial = faturamentoSuspensoComercial;
}

public void setAlteracaoCategoriaIndustrial(Integer alteracaoCategoriaIndustrial) {
	this.alteracaoCategoriaIndustrial = alteracaoCategoriaIndustrial;
}


public void setInclusaoTarifaSocialIndustrial(
		Integer inclusaoTarifaSocialIndustrial) {
	this.inclusaoTarifaSocialIndustrial = inclusaoTarifaSocialIndustrial;
}


public void setExclusaoTarifaSocialIndustrial(
		Integer exclusaoTarifaSocialIndustrial) {
	this.exclusaoTarifaSocialIndustrial = exclusaoTarifaSocialIndustrial;
}


public void setFaturamentoSuspensoIndustrial(
		Integer faturamentoSuspensoIndustrial) {
	this.faturamentoSuspensoIndustrial = faturamentoSuspensoIndustrial;
}

public void setAlteracaoCategoriaPublico(Integer alteracaoCategoriaPublico) {
	this.alteracaoCategoriaPublico = alteracaoCategoriaPublico;
}


public void setInclusaoTarifaSocialPublico(Integer inclusaoTarifaSocialPublico) {
	this.inclusaoTarifaSocialPublico = inclusaoTarifaSocialPublico;
}


public void setExclusaoTarifaSocialPublico(Integer exclusaoTarifaSocialPublico) {
	this.exclusaoTarifaSocialPublico = exclusaoTarifaSocialPublico;
}


public void setFaturamentoSuspensoPublico(Integer faturamentoSuspensoPublico) {
	this.faturamentoSuspensoPublico = faturamentoSuspensoPublico;
}

public void setAlteracaoCategoriaNormal(Integer alteracaoCategoriaNormal) {
	this.alteracaoCategoriaNormal = alteracaoCategoriaNormal;
}


public void setInclusaoTarifaSocialNormal(Integer inclusaoTarifaSocialNormal) {
	this.inclusaoTarifaSocialNormal = inclusaoTarifaSocialNormal;
}


public void setExclusaoTarifaSocialNormal(Integer exclusaoTarifaSocialNormal) {
	this.exclusaoTarifaSocialNormal = exclusaoTarifaSocialNormal;
}


public void setFaturamentoSuspensoNormal(Integer faturamentoSuspensoNormal) {
	this.faturamentoSuspensoNormal = faturamentoSuspensoNormal;
}


public void setAlteracaoCategoriaTarSocial(Integer alteracaoCategoriaTarSocial) {
	this.alteracaoCategoriaTarSocial = alteracaoCategoriaTarSocial;
}


public void setInclusaoTarifaSocialTarSocial(
		Integer inclusaoTarifaSocialTarSocial) {
	this.inclusaoTarifaSocialTarSocial = inclusaoTarifaSocialTarSocial;
}


public void setExclusaoTarifaSocialTarSocial(
		Integer exclusaoTarifaSocialTarSocial) {
	this.exclusaoTarifaSocialTarSocial = exclusaoTarifaSocialTarSocial;
}


public void setFaturamentoSuspensoTarSocial(Integer faturamentoSuspensoTarSocial) {
	this.faturamentoSuspensoTarSocial = faturamentoSuspensoTarSocial;
}


public void setAlteracaoCategoriaGrande(Integer alteracaoCategoriaGrande) {
	this.alteracaoCategoriaGrande = alteracaoCategoriaGrande;
}


public void setInclusaoTarifaSocialGrande(Integer inclusaoTarifaSocialGrande) {
	this.inclusaoTarifaSocialGrande = inclusaoTarifaSocialGrande;
}


public void setExclusaoTarifaSocialGrande(Integer exclusaoTarifaSocialGrande) {
	this.exclusaoTarifaSocialGrande = exclusaoTarifaSocialGrande;
}


public void setFaturamentoSuspensoGrande(Integer faturamentoSuspensoGrande) {
	this.faturamentoSuspensoGrande = faturamentoSuspensoGrande;
}


public void setAlteracaoCategoriaCorporativo(
		Integer alteracaoCategoriaCorporativo) {
	this.alteracaoCategoriaCorporativo = alteracaoCategoriaCorporativo;
}


public void setInclusaoTarifaSocialCorporativo(
		Integer inclusaoTarifaSocialCorporativo) {
	this.inclusaoTarifaSocialCorporativo = inclusaoTarifaSocialCorporativo;
}


public void setExclusaoTarifaSocialCorporativo(
		Integer exclusaoTarifaSocialCorporativo) {
	this.exclusaoTarifaSocialCorporativo = exclusaoTarifaSocialCorporativo;
}


public void setFaturamentoSuspensoCorporativo(
		Integer faturamentoSuspensoCorporativo) {
	this.faturamentoSuspensoCorporativo = faturamentoSuspensoCorporativo;
}


public void setAlteracaoCategoriaGrandeTelemedido(
		Integer alteracaoCategoriaGrandeTelemedido) {
	this.alteracaoCategoriaGrandeTelemedido = alteracaoCategoriaGrandeTelemedido;
}


public void setInclusaoTarifaSocialGrandeTelemedido(
		Integer inclusaoTarifaSocialGrandeTelemedido) {
	this.inclusaoTarifaSocialGrandeTelemedido = inclusaoTarifaSocialGrandeTelemedido;
}


public void setExclusaoTarifaSocialGrandeTelemedido(
		Integer exclusaoTarifaSocialGrandeTelemedido) {
	this.exclusaoTarifaSocialGrandeTelemedido = exclusaoTarifaSocialGrandeTelemedido;
}


public void setFaturamentoSuspensoGrandeTelemedido(
		Integer faturamentoSuspensoGrandeTelemedido) {
	this.faturamentoSuspensoGrandeTelemedido = faturamentoSuspensoGrandeTelemedido;
}


public void setAlteracaoCategoriaCorpTelemedido(
		Integer alteracaoCategoriaCorpTelemedido) {
	this.alteracaoCategoriaCorpTelemedido = alteracaoCategoriaCorpTelemedido;
}


public void setInclusaoTarifaSocialCorpTelemedido(
		Integer inclusaoTarifaSocialCorpTelemedido) {
	this.inclusaoTarifaSocialCorpTelemedido = inclusaoTarifaSocialCorpTelemedido;
}


public void setExclusaoTarifaSocialCorpTelemedido(
		Integer exclusaoTarifaSocialCorpTelemedido) {
	this.exclusaoTarifaSocialCorpTelemedido = exclusaoTarifaSocialCorpTelemedido;
}


public void setFaturamentoSuspensoCorpTelemedido(
		Integer faturamentoSuspensoCorpTelemedido) {
	this.faturamentoSuspensoCorpTelemedido = faturamentoSuspensoCorpTelemedido;
}


public void setAlteracaoCategoriaChafariz(Integer alteracaoCategoriaChafariz) {
	this.alteracaoCategoriaChafariz = alteracaoCategoriaChafariz;
}


public void setInclusaoTarifaSocialChafariz(Integer inclusaoTarifaSocialChafariz) {
	this.inclusaoTarifaSocialChafariz = inclusaoTarifaSocialChafariz;
}


public void setExclusaoTarifaSocialChafariz(Integer exclusaoTarifaSocialChafariz) {
	this.exclusaoTarifaSocialChafariz = exclusaoTarifaSocialChafariz;
}


public void setFaturamentoSuspensoChafariz(Integer faturamentoSuspensoChafariz) {
	this.faturamentoSuspensoChafariz = faturamentoSuspensoChafariz;
}


public void setAlteracaoCategoriaMicroTelemedido(
		Integer alteracaoCategoriaMicroTelemedido) {
	this.alteracaoCategoriaMicroTelemedido = alteracaoCategoriaMicroTelemedido;
}


public void setInclusaoTarifaSocialMicroTelemedido(
		Integer inclusaoTarifaSocialMicroTelemedido) {
	this.inclusaoTarifaSocialMicroTelemedido = inclusaoTarifaSocialMicroTelemedido;
}


public void setExclusaoTarifaSocialMicroTelemedido(
		Integer exclusaoTarifaSocialMicroTelemedido) {
	this.exclusaoTarifaSocialMicroTelemedido = exclusaoTarifaSocialMicroTelemedido;
}


public void setFaturamentoSuspensoMicroTelemedido(
		Integer faturamentoSuspensoMicroTelemedido) {
	this.faturamentoSuspensoMicroTelemedido = faturamentoSuspensoMicroTelemedido;
}


public void setAlteracaoCategoriaCadastroProvisorio(
		Integer alteracaoCategoriaCadastroProvisorio) {
	this.alteracaoCategoriaCadastroProvisorio = alteracaoCategoriaCadastroProvisorio;
}


public void setInclusaoTarifaSocialCadastroProvisorio(
		Integer inclusaoTarifaSocialCadastroProvisorio) {
	this.inclusaoTarifaSocialCadastroProvisorio = inclusaoTarifaSocialCadastroProvisorio;
}


public void setExclusaoTarifaSocialCadastroProvisorio(
		Integer exclusaoTarifaSocialCadastroProvisorio) {
	this.exclusaoTarifaSocialCadastroProvisorio = exclusaoTarifaSocialCadastroProvisorio;
}


public void setFaturamentoSuspensoCadastroProvisorio(
		Integer faturamentoSuspensoCadastroProvisorio) {
	this.faturamentoSuspensoCadastroProvisorio = faturamentoSuspensoCadastroProvisorio;
}


public void setFaturaRevisaoTotalPerfil(Integer faturaRevisaoTotalPerfil) {
	this.faturaRevisaoTotalPerfil = faturaRevisaoTotalPerfil;
}


public void setAlteracaoCategoriaTotalPerfil(
		Integer alteracaoCategoriaTotalPerfil) {
	this.alteracaoCategoriaTotalPerfil = alteracaoCategoriaTotalPerfil;
}


public void setInclusaoTarifaSocialTotalPerfil(
		Integer inclusaoTarifaSocialTotalPerfil) {
	this.inclusaoTarifaSocialTotalPerfil = inclusaoTarifaSocialTotalPerfil;
}


public void setExclusaoTarifaSocialTotalPerfil(
		Integer exclusaoTarifaSocialTotalPerfil) {
	this.exclusaoTarifaSocialTotalPerfil = exclusaoTarifaSocialTotalPerfil;
}


public void setFaturamentoSuspensoTotalPerfil(
		Integer faturamentoSuspensoTotalPerfil) {
	this.faturamentoSuspensoTotalPerfil = faturamentoSuspensoTotalPerfil;
}


public void setFaturaRevisaoTotalCategoria(Integer faturaRevisaoTotalCategoria) {
	this.faturaRevisaoTotalCategoria = faturaRevisaoTotalCategoria;
}


public void setAlteracaoCategoriaTotalCategoria(
		Integer alteracaoCategoriaTotalCategoria) {
	this.alteracaoCategoriaTotalCategoria = alteracaoCategoriaTotalCategoria;
}


public void setInclusaoTarifaSocialTotalCategoria(
		Integer inclusaoTarifaSocialTotalCategoria) {
	this.inclusaoTarifaSocialTotalCategoria = inclusaoTarifaSocialTotalCategoria;
}


public void setExclusaoTarifaSocialTotalCategoria(
		Integer exclusaoTarifaSocialTotalCategoria) {
	this.exclusaoTarifaSocialTotalCategoria = exclusaoTarifaSocialTotalCategoria;
}


public void setFaturamentoSuspensoTotalCategoria(
		Integer faturamentoSuspensoTotalCategoria) {
	this.faturamentoSuspensoTotalCategoria = faturamentoSuspensoTotalCategoria;
}


public Integer getQtImoveisFaturaRevisaoReclamacaoConsumoResidencial() {
	return qtImoveisFaturaRevisaoReclamacaoConsumoResidencial;
}


public void setQtImoveisFaturaRevisaoReclamacaoConsumoResidencial(
		Integer qtImoveisFaturaRevisaoReclamacaoConsumoResidencial) {
	this.qtImoveisFaturaRevisaoReclamacaoConsumoResidencial = qtImoveisFaturaRevisaoReclamacaoConsumoResidencial;
}


public Integer getQtImoveisFaturaRevisaoRecorrenciaResidencial() {
	return qtImoveisFaturaRevisaoRecorrenciaResidencial;
}


public void setQtImoveisFaturaRevisaoRecorrenciaResidencial(
		Integer qtImoveisFaturaRevisaoRecorrenciaResidencial) {
	this.qtImoveisFaturaRevisaoRecorrenciaResidencial = qtImoveisFaturaRevisaoRecorrenciaResidencial;
}


public Integer getQtImoveisFaturaRevisaoFaturamentoIndevidoResidencial() {
	return qtImoveisFaturaRevisaoFaturamentoIndevidoResidencial;
}


public void setQtImoveisFaturaRevisaoFaturamentoIndevidoResidencial(
		Integer qtImoveisFaturaRevisaoFaturamentoIndevidoResidencial) {
	this.qtImoveisFaturaRevisaoFaturamentoIndevidoResidencial = qtImoveisFaturaRevisaoFaturamentoIndevidoResidencial;
}


public Integer getQtImoveisFaturaRevisaoReclamacaoConsumoComercial() {
	return qtImoveisFaturaRevisaoReclamacaoConsumoComercial;
}


public void setQtImoveisFaturaRevisaoReclamacaoConsumoComercial(
		Integer qtImoveisFaturaRevisaoReclamacaoConsumoComercial) {
	this.qtImoveisFaturaRevisaoReclamacaoConsumoComercial = qtImoveisFaturaRevisaoReclamacaoConsumoComercial;
}


public Integer getQtImoveisFaturaRevisaoRecorrenciaComercial() {
	return qtImoveisFaturaRevisaoRecorrenciaComercial;
}


public void setQtImoveisFaturaRevisaoRecorrenciaComercial(
		Integer qtImoveisFaturaRevisaoRecorrenciaComercial) {
	this.qtImoveisFaturaRevisaoRecorrenciaComercial = qtImoveisFaturaRevisaoRecorrenciaComercial;
}


public Integer getQtImoveisFaturaRevisaoFaturamentoIndevidoComercial() {
	return qtImoveisFaturaRevisaoFaturamentoIndevidoComercial;
}


public void setQtImoveisFaturaRevisaoFaturamentoIndevidoComercial(
		Integer qtImoveisFaturaRevisaoFaturamentoIndevidoComercial) {
	this.qtImoveisFaturaRevisaoFaturamentoIndevidoComercial = qtImoveisFaturaRevisaoFaturamentoIndevidoComercial;
}


public Integer getQtImoveisFaturaRevisaoReclamacaoConsumoIndustrial() {
	return qtImoveisFaturaRevisaoReclamacaoConsumoIndustrial;
}


public void setQtImoveisFaturaRevisaoReclamacaoConsumoIndustrial(
		Integer qtImoveisFaturaRevisaoReclamacaoConsumoIndustrial) {
	this.qtImoveisFaturaRevisaoReclamacaoConsumoIndustrial = qtImoveisFaturaRevisaoReclamacaoConsumoIndustrial;
}


public Integer getQtImoveisFaturaRevisaoRecorrenciaIndustrial() {
	return qtImoveisFaturaRevisaoRecorrenciaIndustrial;
}


public void setQtImoveisFaturaRevisaoRecorrenciaIndustrial(
		Integer qtImoveisFaturaRevisaoRecorrenciaIndustrial) {
	this.qtImoveisFaturaRevisaoRecorrenciaIndustrial = qtImoveisFaturaRevisaoRecorrenciaIndustrial;
}


public Integer getQtImoveisFaturaRevisaoFaturamentoIndevidoIndustrial() {
	return qtImoveisFaturaRevisaoFaturamentoIndevidoIndustrial;
}


public void setQtImoveisFaturaRevisaoFaturamentoIndevidoIndustrial(
		Integer qtImoveisFaturaRevisaoFaturamentoIndevidoIndustrial) {
	this.qtImoveisFaturaRevisaoFaturamentoIndevidoIndustrial = qtImoveisFaturaRevisaoFaturamentoIndevidoIndustrial;
}


public Integer getQtImoveisFaturaRevisaoReclamacaoConsumoPublico() {
	return qtImoveisFaturaRevisaoReclamacaoConsumoPublico;
}


public void setQtImoveisFaturaRevisaoReclamacaoConsumoPublico(
		Integer qtImoveisFaturaRevisaoReclamacaoConsumoPublico) {
	this.qtImoveisFaturaRevisaoReclamacaoConsumoPublico = qtImoveisFaturaRevisaoReclamacaoConsumoPublico;
}


public Integer getQtImoveisFaturaRevisaoRecorrenciaPublico() {
	return qtImoveisFaturaRevisaoRecorrenciaPublico;
}


public void setQtImoveisFaturaRevisaoRecorrenciaPublico(
		Integer qtImoveisFaturaRevisaoRecorrenciaPublico) {
	this.qtImoveisFaturaRevisaoRecorrenciaPublico = qtImoveisFaturaRevisaoRecorrenciaPublico;
}


public Integer getQtImoveisFaturaRevisaoFaturamentoIndevidoPublico() {
	return qtImoveisFaturaRevisaoFaturamentoIndevidoPublico;
}


public void setQtImoveisFaturaRevisaoFaturamentoIndevidoPublico(
		Integer qtImoveisFaturaRevisaoFaturamentoIndevidoPublico) {
	this.qtImoveisFaturaRevisaoFaturamentoIndevidoPublico = qtImoveisFaturaRevisaoFaturamentoIndevidoPublico;
}


public Integer getQtImoveisFaturaRevisaoReclamacaoConsumoNormal() {
	return qtImoveisFaturaRevisaoReclamacaoConsumoNormal;
}


public void setQtImoveisFaturaRevisaoReclamacaoConsumoNormal(
		Integer qtImoveisFaturaRevisaoReclamacaoConsumoNormal) {
	this.qtImoveisFaturaRevisaoReclamacaoConsumoNormal = qtImoveisFaturaRevisaoReclamacaoConsumoNormal;
}


public Integer getQtImoveisFaturaRevisaoRecorrenciaNormal() {
	return qtImoveisFaturaRevisaoRecorrenciaNormal;
}


public void setQtImoveisFaturaRevisaoRecorrenciaNormal(
		Integer qtImoveisFaturaRevisaoRecorrenciaNormal) {
	this.qtImoveisFaturaRevisaoRecorrenciaNormal = qtImoveisFaturaRevisaoRecorrenciaNormal;
}


public Integer getQtImoveisFaturaRevisaoFaturamentoIndevidoNormal() {
	return qtImoveisFaturaRevisaoFaturamentoIndevidoNormal;
}


public void setQtImoveisFaturaRevisaoFaturamentoIndevidoNormal(
		Integer qtImoveisFaturaRevisaoFaturamentoIndevidoNormal) {
	this.qtImoveisFaturaRevisaoFaturamentoIndevidoNormal = qtImoveisFaturaRevisaoFaturamentoIndevidoNormal;
}


public Integer getQtImoveisFaturaRevisaoReclamacaoConsumoTarSocial() {
	return qtImoveisFaturaRevisaoReclamacaoConsumoTarSocial;
}


public void setQtImoveisFaturaRevisaoReclamacaoConsumoTarSocial(
		Integer qtImoveisFaturaRevisaoReclamacaoConsumoTarSocial) {
	this.qtImoveisFaturaRevisaoReclamacaoConsumoTarSocial = qtImoveisFaturaRevisaoReclamacaoConsumoTarSocial;
}


public Integer getQtImoveisFaturaRevisaoRecorrenciaTarSocial() {
	return qtImoveisFaturaRevisaoRecorrenciaTarSocial;
}


public void setQtImoveisFaturaRevisaoRecorrenciaTarSocial(
		Integer qtImoveisFaturaRevisaoRecorrenciaTarSocial) {
	this.qtImoveisFaturaRevisaoRecorrenciaTarSocial = qtImoveisFaturaRevisaoRecorrenciaTarSocial;
}


public Integer getQtImoveisFaturaRevisaoFaturamentoIndevidoTarSocial() {
	return qtImoveisFaturaRevisaoFaturamentoIndevidoTarSocial;
}


public void setQtImoveisFaturaRevisaoFaturamentoIndevidoTarSocial(
		Integer qtImoveisFaturaRevisaoFaturamentoIndevidoTarSocial) {
	this.qtImoveisFaturaRevisaoFaturamentoIndevidoTarSocial = qtImoveisFaturaRevisaoFaturamentoIndevidoTarSocial;
}


public Integer getQtImoveisFaturaRevisaoReclamacaoConsumoGrande() {
	return qtImoveisFaturaRevisaoReclamacaoConsumoGrande;
}


public void setQtImoveisFaturaRevisaoReclamacaoConsumoGrande(
		Integer qtImoveisFaturaRevisaoReclamacaoConsumoGrande) {
	this.qtImoveisFaturaRevisaoReclamacaoConsumoGrande = qtImoveisFaturaRevisaoReclamacaoConsumoGrande;
}


public Integer getQtImoveisFaturaRevisaoRecorrenciaGrande() {
	return qtImoveisFaturaRevisaoRecorrenciaGrande;
}


public void setQtImoveisFaturaRevisaoRecorrenciaGrande(
		Integer qtImoveisFaturaRevisaoRecorrenciaGrande) {
	this.qtImoveisFaturaRevisaoRecorrenciaGrande = qtImoveisFaturaRevisaoRecorrenciaGrande;
}


public Integer getQtImoveisFaturaRevisaoFaturamentoIndevidoGrande() {
	return qtImoveisFaturaRevisaoFaturamentoIndevidoGrande;
}


public void setQtImoveisFaturaRevisaoFaturamentoIndevidoGrande(
		Integer qtImoveisFaturaRevisaoFaturamentoIndevidoGrande) {
	this.qtImoveisFaturaRevisaoFaturamentoIndevidoGrande = qtImoveisFaturaRevisaoFaturamentoIndevidoGrande;
}


public Integer getQtImoveisFaturaRevisaoReclamacaoConsumoCorporativo() {
	return qtImoveisFaturaRevisaoReclamacaoConsumoCorporativo;
}


public void setQtImoveisFaturaRevisaoReclamacaoConsumoCorporativo(
		Integer qtImoveisFaturaRevisaoReclamacaoConsumoCorporativo) {
	this.qtImoveisFaturaRevisaoReclamacaoConsumoCorporativo = qtImoveisFaturaRevisaoReclamacaoConsumoCorporativo;
}


public Integer getQtImoveisFaturaRevisaoRecorrenciaCorporativo() {
	return qtImoveisFaturaRevisaoRecorrenciaCorporativo;
}


public void setQtImoveisFaturaRevisaoRecorrenciaCorporativo(
		Integer qtImoveisFaturaRevisaoRecorrenciaCorporativo) {
	this.qtImoveisFaturaRevisaoRecorrenciaCorporativo = qtImoveisFaturaRevisaoRecorrenciaCorporativo;
}


public Integer getQtImoveisFaturaRevisaoFaturamentoIndevidoCorporativo() {
	return qtImoveisFaturaRevisaoFaturamentoIndevidoCorporativo;
}


public void setQtImoveisFaturaRevisaoFaturamentoIndevidoCorporativo(
		Integer qtImoveisFaturaRevisaoFaturamentoIndevidoCorporativo) {
	this.qtImoveisFaturaRevisaoFaturamentoIndevidoCorporativo = qtImoveisFaturaRevisaoFaturamentoIndevidoCorporativo;
}


public Integer getQtImoveisFaturaRevisaoReclamacaoConsumoGrandeTelemedido() {
	return qtImoveisFaturaRevisaoReclamacaoConsumoGrandeTelemedido;
}


public void setQtImoveisFaturaRevisaoReclamacaoConsumoGrandeTelemedido(
		Integer qtImoveisFaturaRevisaoReclamacaoConsumoGrandeTelemedido) {
	this.qtImoveisFaturaRevisaoReclamacaoConsumoGrandeTelemedido = qtImoveisFaturaRevisaoReclamacaoConsumoGrandeTelemedido;
}


public Integer getQtImoveisFaturaRevisaoRecorrenciaGrandeTelemedido() {
	return qtImoveisFaturaRevisaoRecorrenciaGrandeTelemedido;
}


public void setQtImoveisFaturaRevisaoRecorrenciaGrandeTelemedido(
		Integer qtImoveisFaturaRevisaoRecorrenciaGrandeTelemedido) {
	this.qtImoveisFaturaRevisaoRecorrenciaGrandeTelemedido = qtImoveisFaturaRevisaoRecorrenciaGrandeTelemedido;
}


public Integer getQtImoveisFaturaRevisaoFaturamentoIndevidoGrandeTelemedido() {
	return qtImoveisFaturaRevisaoFaturamentoIndevidoGrandeTelemedido;
}


public void setQtImoveisFaturaRevisaoFaturamentoIndevidoGrandeTelemedido(
		Integer qtImoveisFaturaRevisaoFaturamentoIndevidoGrandeTelemedido) {
	this.qtImoveisFaturaRevisaoFaturamentoIndevidoGrandeTelemedido = qtImoveisFaturaRevisaoFaturamentoIndevidoGrandeTelemedido;
}


public Integer getQtImoveisFaturaRevisaoReclamacaoConsumoCorpTelemedido() {
	return qtImoveisFaturaRevisaoReclamacaoConsumoCorpTelemedido;
}


public void setQtImoveisFaturaRevisaoReclamacaoConsumoCorpTelemedido(
		Integer qtImoveisFaturaRevisaoReclamacaoConsumoCorpTelemedido) {
	this.qtImoveisFaturaRevisaoReclamacaoConsumoCorpTelemedido = qtImoveisFaturaRevisaoReclamacaoConsumoCorpTelemedido;
}


public Integer getQtImoveisFaturaRevisaoRecorrenciaCorpTelemedido() {
	return qtImoveisFaturaRevisaoRecorrenciaCorpTelemedido;
}


public void setQtImoveisFaturaRevisaoRecorrenciaCorpTelemedido(
		Integer qtImoveisFaturaRevisaoRecorrenciaCorpTelemedido) {
	this.qtImoveisFaturaRevisaoRecorrenciaCorpTelemedido = qtImoveisFaturaRevisaoRecorrenciaCorpTelemedido;
}


public Integer getQtImoveisFaturaRevisaoFaturamentoIndevidoCorpTelemedido() {
	return qtImoveisFaturaRevisaoFaturamentoIndevidoCorpTelemedido;
}


public void setQtImoveisFaturaRevisaoFaturamentoIndevidoCorpTelemedido(
		Integer qtImoveisFaturaRevisaoFaturamentoIndevidoCorpTelemedido) {
	this.qtImoveisFaturaRevisaoFaturamentoIndevidoCorpTelemedido = qtImoveisFaturaRevisaoFaturamentoIndevidoCorpTelemedido;
}


public Integer getQtImoveisFaturaRevisaoReclamacaoConsumoChafariz() {
	return qtImoveisFaturaRevisaoReclamacaoConsumoChafariz;
}


public void setQtImoveisFaturaRevisaoReclamacaoConsumoChafariz(
		Integer qtImoveisFaturaRevisaoReclamacaoConsumoChafariz) {
	this.qtImoveisFaturaRevisaoReclamacaoConsumoChafariz = qtImoveisFaturaRevisaoReclamacaoConsumoChafariz;
}


public Integer getQtImoveisFaturaRevisaoRecorrenciaChafariz() {
	return qtImoveisFaturaRevisaoRecorrenciaChafariz;
}


public void setQtImoveisFaturaRevisaoRecorrenciaChafariz(
		Integer qtImoveisFaturaRevisaoRecorrenciaChafariz) {
	this.qtImoveisFaturaRevisaoRecorrenciaChafariz = qtImoveisFaturaRevisaoRecorrenciaChafariz;
}


public Integer getQtImoveisFaturaRevisaoFaturamentoIndevidoChafariz() {
	return qtImoveisFaturaRevisaoFaturamentoIndevidoChafariz;
}


public void setQtImoveisFaturaRevisaoFaturamentoIndevidoChafariz(
		Integer qtImoveisFaturaRevisaoFaturamentoIndevidoChafariz) {
	this.qtImoveisFaturaRevisaoFaturamentoIndevidoChafariz = qtImoveisFaturaRevisaoFaturamentoIndevidoChafariz;
}


public Integer getQtImoveisFaturaRevisaoReclamacaoConsumoMicroTelemedido() {
	return qtImoveisFaturaRevisaoReclamacaoConsumoMicroTelemedido;
}


public void setQtImoveisFaturaRevisaoReclamacaoConsumoMicroTelemedido(
		Integer qtImoveisFaturaRevisaoReclamacaoConsumoMicroTelemedido) {
	this.qtImoveisFaturaRevisaoReclamacaoConsumoMicroTelemedido = qtImoveisFaturaRevisaoReclamacaoConsumoMicroTelemedido;
}


public Integer getQtImoveisFaturaRevisaoRecorrenciaMicroTelemedido() {
	return qtImoveisFaturaRevisaoRecorrenciaMicroTelemedido;
}


public void setQtImoveisFaturaRevisaoRecorrenciaMicroTelemedido(
		Integer qtImoveisFaturaRevisaoRecorrenciaMicroTelemedido) {
	this.qtImoveisFaturaRevisaoRecorrenciaMicroTelemedido = qtImoveisFaturaRevisaoRecorrenciaMicroTelemedido;
}


public Integer getQtImoveisFaturaRevisaoFaturamentoIndevidoMicroTelemedido() {
	return qtImoveisFaturaRevisaoFaturamentoIndevidoMicroTelemedido;
}


public void setQtImoveisFaturaRevisaoFaturamentoIndevidoMicroTelemedido(
		Integer qtImoveisFaturaRevisaoFaturamentoIndevidoMicroTelemedido) {
	this.qtImoveisFaturaRevisaoFaturamentoIndevidoMicroTelemedido = qtImoveisFaturaRevisaoFaturamentoIndevidoMicroTelemedido;
}


public Integer getQtImoveisFaturaRevisaoReclamacaoConsumoCadastroProvisorio() {
	return qtImoveisFaturaRevisaoReclamacaoConsumoCadastroProvisorio;
}


public void setQtImoveisFaturaRevisaoReclamacaoConsumoCadastroProvisorio(
		Integer qtImoveisFaturaRevisaoReclamacaoConsumoCadastroProvisorio) {
	this.qtImoveisFaturaRevisaoReclamacaoConsumoCadastroProvisorio = qtImoveisFaturaRevisaoReclamacaoConsumoCadastroProvisorio;
}


public Integer getQtImoveisFaturaRevisaoRecorrenciaCadastroProvisorio() {
	return qtImoveisFaturaRevisaoRecorrenciaCadastroProvisorio;
}


public void setQtImoveisFaturaRevisaoRecorrenciaCadastroProvisorio(
		Integer qtImoveisFaturaRevisaoRecorrenciaCadastroProvisorio) {
	this.qtImoveisFaturaRevisaoRecorrenciaCadastroProvisorio = qtImoveisFaturaRevisaoRecorrenciaCadastroProvisorio;
}


public Integer getQtImoveisFaturaRevisaoFaturamentoIndevidoCadastroProvisorio() {
	return qtImoveisFaturaRevisaoFaturamentoIndevidoCadastroProvisorio;
}


public void setQtImoveisFaturaRevisaoFaturamentoIndevidoCadastroProvisorio(
		Integer qtImoveisFaturaRevisaoFaturamentoIndevidoCadastroProvisorio) {
	this.qtImoveisFaturaRevisaoFaturamentoIndevidoCadastroProvisorio = qtImoveisFaturaRevisaoFaturamentoIndevidoCadastroProvisorio;
}


public void setQtImoveisFaturaRevisaoReclamacaoConsumoTotalPerfil(
		Integer qtImoveisFaturaRevisaoReclamacaoConsumoTotalPerfil) {
	this.qtImoveisFaturaRevisaoReclamacaoConsumoTotalPerfil = qtImoveisFaturaRevisaoReclamacaoConsumoTotalPerfil;
}


public void setQtImoveisFaturaRevisaoRecorrenciaTotalPerfil(
		Integer qtImoveisFaturaRevisaoRecorrenciaTotalPerfil) {
	this.qtImoveisFaturaRevisaoRecorrenciaTotalPerfil = qtImoveisFaturaRevisaoRecorrenciaTotalPerfil;
}


public void setQtImoveisFaturaRevisaoFaturamentoIndevidoTotalPerfil(
		Integer qtImoveisFaturaRevisaoFaturamentoIndevidoTotalPerfil) {
	this.qtImoveisFaturaRevisaoFaturamentoIndevidoTotalPerfil = qtImoveisFaturaRevisaoFaturamentoIndevidoTotalPerfil;
}


public void setQtImoveisFaturaRevisaoReclamacaoConsumoTotalCategoria(
		Integer qtImoveisFaturaRevisaoReclamacaoConsumoTotalCategoria) {
	this.qtImoveisFaturaRevisaoReclamacaoConsumoTotalCategoria = qtImoveisFaturaRevisaoReclamacaoConsumoTotalCategoria;
}


public void setQtImoveisFaturaRevisaoRecorrenciaTotalCategoria(
		Integer qtImoveisFaturaRevisaoRecorrenciaTotalCategoria) {
	this.qtImoveisFaturaRevisaoRecorrenciaTotalCategoria = qtImoveisFaturaRevisaoRecorrenciaTotalCategoria;
}


public void setQtImoveisFaturaRevisaoFaturamentoIndevidoTotalCategoria(
		Integer qtImoveisFaturaRevisaoFaturamentoIndevidoTotalCategoria) {
	this.qtImoveisFaturaRevisaoFaturamentoIndevidoTotalCategoria = qtImoveisFaturaRevisaoFaturamentoIndevidoTotalCategoria;
}



}

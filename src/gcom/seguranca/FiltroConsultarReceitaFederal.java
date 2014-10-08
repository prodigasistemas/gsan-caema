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
package gcom.seguranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Descri��o da classe
 * 
 * @author Ricardo Germinio
 * @date 18/07/2012
 */
public class FiltroConsultarReceitaFederal extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public final static String ID = "id";
	
	public final static String NNDDDCOM = "codigoDDDComercial";
	public final static String NNFONECOM = "telefoneComercialCliente";
	public final static String NMLOGIN = "loginUsuario";
	public final static String USUR_NNCPF = "cpfUsuario";
	public final static String CDACAO = "acaoUsuario";
	public final static String DSUF = "ufCliente";
	public final static String CDCEP = "cepCliente";
	public final static String DSCIDADE = "cidadeCliente";
	public final static String DSBAIRRO = "bairroCliente";
	public final static String DSCOMPLEMENTO = "complementoEnderecoCliente";
	public final static String NMLOGRADOURO = "logradouroCliente";
	public final static String NNFONEFAX = "numeroFaxCliente";
	public final static String NNDDDFAX = "codigoDDDFaxCliente";
	public final static String NNDDDCEL = "codigoDDDCelularCliente";
	public final static String NNFONECEL = "numeroCelularCliente";
	public final static String NNDDDRES = "codigoDDDTelefoneResidencialCliente";
	public final static String NNFONERES = "numeroTelefoneResidencialCliente";
	public final static String NMCLIENTE = "nomeCliente";
	public final static String NNCPF = "cpfCliente";
	public final static String DSSIGNO = "signoCliente";
	public final static String DSSEXO = "sexoCliente";
	public final static String NNTITULO = "tituloEleitorCliente";
	public final static String NNRG = "numeroRGCliente";
	public final static String NMPAI = "nomeDoPaiCliente";
	public final static String NMMAE = "nomeDaMaeCliente";
	public final static String NNIDADE = "idade";
	public final static String DSESTADOCIVIL = "estadoCivil";
	public final static String DTNASCIMENTO = "dataNascimento";
	public final static String DSSITUACAORG = "situacaoRG";
	public final static String DSSITUACAOCPF = "situacaoCPF";
	public final static String NNCNPJ = "cnpjCliente";
	public final static String DSSITUACAOCNPJ = "situacaoCNPJ";
	public final static String DSSITUACAOINSCESTADUAL = "situacaoInscricaoEstadual";
	public final static String DSNATUREZAJURIDICA = "naturezaJuridica";
	public final static String DSATIVPRINCIPAL = "atividadeEconomicaPrincipal";
	public final static String DSATIVSECUNDARIA = "atividadeEconomicaSecundaria";
	public final static String DTFUNDACAO = "dataFundacao";
	public final static String NNINSCRICAOESTADUAL = "inscricaoEstadual";
	public final static String NMCOMERCIAL = "nomeComercial";
	public final static String NNNIRENIEC = "numeroNIRENIEC";
	public final static String DSRAZAOSOCIAL = "razaoSocial";
	public final static String DSRAZAOSOCIALANT = "razaoSocialAnterior";
	public final static String VLCAPITALSOCIAL = "valorCapitalSocial";
	public final static String TMULTIMAALTERACAO = "dataUltimaAlteracaoConsulta";
	public final static String NNIMOVEL = "numeroEnderecoCliente";
	public final static String IDLOG = "idLogConsulta";
	public final static String RAMOATIVIDADE = "ramoAtividade";
	public final static String CDORIGEMCONSULTA = "codigoOrigemConsulta";
	public final static String DSSITUACAOESPECIAL = "situacaoEspecial";
	public final static String DTACESSO = "dataAcesso";

	/**
	 * 
	 * 
	 * Construtor de FiltroConsultarReceitaFederal<ate
	 * 
	 */

	public FiltroConsultarReceitaFederal() {

	}

	/**
	 * Construtor da classe FiltroConsultarReceitaFederal
	 * 
	 * @param campoOrderBy
	 *            Descri��o do par�metro
	 */
	public FiltroConsultarReceitaFederal(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

}
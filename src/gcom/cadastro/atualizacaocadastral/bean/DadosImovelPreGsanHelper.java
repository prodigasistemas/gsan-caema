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
package gcom.cadastro.atualizacaocadastral.bean;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Collection;



public class DadosImovelPreGsanHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//Atributos usados para exibir na tela o conteudo dos im�veis pesquisados
	private String idImovelAtualizacaoCadastral;
	private String situacao;
	private String idLocalidade;
	private String codigoSetorComercial;
	private String numeroQuadra;
	private String numeroLote;
	private String numeroSubLote;
	private String enderecoFormatado;
	private String numero;
	private String matricula;
	private String matriculaGsan;
	private Collection colecaoMatriculaGsan;
	private String descricaoCadastroOcorrencia;
	private String numeroVisitas;
	private String indicadorLogradouroNaoInformado;
	private String indicadorSetorComercialInexistente;
	private String indicadorQuadraInexistente;
	private String indicadorCpfCnpjNaoInformado;
	private String indicadorHabilitaTipoSituacao;
	private String indicadorValueSelect;
	private String indicadorImovelNovo;
	private String idComando;
	private String loginCadastrador;
	private String nomeCadastrador;
	private String idCadastroOcorrencia;
	private String qtdImoveis;
	private String totalImovelPorCadastrador;
	private String totalImovel;
	private String dadosMatriculaAssociadaHint;
	
	//Atributos usados como parametros para receber os dados da tela e fazer a pesquisa com os dados informados.
	private String parametroEmpresa;
	private String parametroLocalidade;
	private String parametroSetorComercial;
	private String parametroQuadra;
	private String parametroCadastroOcorrencia;
	private String parametroTipoSelecao;
	private String parametroCadastrador;
	
	//Atributos usados para validar os dados do cliente na receita federal
	private String indicadorCpfCnpjValidadoNaReceita;
	private String nomeClienteReceitaFederal;
	private String nomeCliente;
	private String cpfCnpj;
	
	private String indicadorCpfCnpjConfirmado;
	private String indicadorDesconsiderarImoveldaAtualizacao;
	

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public String getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	public String getEnderecoFormatado() {
		return enderecoFormatado;
	}

	public void setEnderecoFormatado(String enderecoFormatado) {
		this.enderecoFormatado = enderecoFormatado;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getMatriculaGsan() {
		return matriculaGsan;
	}

	public void setMatriculaGsan(String matriculaGsan) {
		this.matriculaGsan = matriculaGsan;
	}

	public String getDescricaoCadastroOcorrencia() {
		return descricaoCadastroOcorrencia;
	}

	public void setDescricaoCadastroOcorrencia(String descricaoCadastroOcorrencia) {
		this.descricaoCadastroOcorrencia = descricaoCadastroOcorrencia;
	}

	public String getNumeroVisitas() {
		return numeroVisitas;
	}

	public void setNumeroVisitas(String numeroVisitas) {
		this.numeroVisitas = numeroVisitas;
	}

	public String getIdImovelAtualizacaoCadastral() {
		return idImovelAtualizacaoCadastral;
	}

	public void setIdImovelAtualizacaoCadastral(String idImovelAtualizacaoCadastral) {
		this.idImovelAtualizacaoCadastral = idImovelAtualizacaoCadastral;
	}

	public String getParametroEmpresa() {
		return parametroEmpresa;
	}

	public void setParametroEmpresa(String parametroEmpresa) {
		this.parametroEmpresa = parametroEmpresa;
	}

	public String getParametroLocalidade() {
		return parametroLocalidade;
	}

	public void setParametroLocalidade(String parametroLocalidade) {
		this.parametroLocalidade = parametroLocalidade;
	}

	public String getParametroSetorComercial() {
		return parametroSetorComercial;
	}

	public void setParametroSetorComercial(String parametroSetorComercial) {
		this.parametroSetorComercial = parametroSetorComercial;
	}

	public String getParametroQuadra() {
		return parametroQuadra;
	}

	public void setParametroQuadra(Integer[] parametroQuadra) {
		String retorno = "";

		if ( parametroQuadra != null && parametroQuadra.length > 0 ) {
			for (int i = 0; i < parametroQuadra.length; i++) {
				retorno += parametroQuadra[i] + ",";
			}
			retorno = retorno.substring(0, retorno.length() -1);
		}
		
		this.parametroQuadra = retorno;
	}

	public String getParametroCadastroOcorrencia() {
		return parametroCadastroOcorrencia;
	}

	public void setParametroCadastroOcorrencia(String parametroCadastroOcorrencia) {
		this.parametroCadastroOcorrencia = parametroCadastroOcorrencia;
	}

	public String getParametroTipoSelecao() {
		return parametroTipoSelecao;
	}

	public void setParametroTipoSelecao(String parametroTipoSelecao) {
		this.parametroTipoSelecao = parametroTipoSelecao;
	}

	public String getIndicadorLogradouroNaoInformado() {
		return indicadorLogradouroNaoInformado;
	}

	public void setIndicadorLogradouroNaoInformado(
			String indicadorLogradouroNaoInformado) {
		this.indicadorLogradouroNaoInformado = indicadorLogradouroNaoInformado;
	}

	public String getIndicadorCpfCnpjNaoInformado() {
		return indicadorCpfCnpjNaoInformado;
	}

	public void setIndicadorCpfCnpjNaoInformado(String indicadorCpfCnpjNaoInformado) {
		this.indicadorCpfCnpjNaoInformado = indicadorCpfCnpjNaoInformado;
	}

	public String getIndicadorCpfCnpjValidadoNaReceita() {
		return indicadorCpfCnpjValidadoNaReceita;
	}

	public void setIndicadorCpfCnpjValidadoNaReceita(
			String indicadorCpfCnpjValidadoNaReceita) {
		this.indicadorCpfCnpjValidadoNaReceita = indicadorCpfCnpjValidadoNaReceita;
	}

	public String getNomeClienteReceitaFederal() {
		return nomeClienteReceitaFederal;
	}

	public void setNomeClienteReceitaFederal(String nomeClienteReceitaFederal) {
		this.nomeClienteReceitaFederal = nomeClienteReceitaFederal;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getIndicadorHabilitaTipoSituacao() {
		return indicadorHabilitaTipoSituacao;
	}

	public void setIndicadorHabilitaTipoSituacao(
			String indicadorHabilitaTipoSituacao) {
		this.indicadorHabilitaTipoSituacao = indicadorHabilitaTipoSituacao;
	}

	public String getIndicadorImovelNovo() {
		return indicadorImovelNovo;
	}

	public void setIndicadorImovelNovo(String indicadorImovelNovo) {
		this.indicadorImovelNovo = indicadorImovelNovo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getIndicadorCpfCnpjConfirmado() {
		return indicadorCpfCnpjConfirmado;
	}

	public void setIndicadorCpfCnpjConfirmado(String indicadorCpfCnpjConfirmado) {
		this.indicadorCpfCnpjConfirmado = indicadorCpfCnpjConfirmado;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNumeroSubLote() {
		return numeroSubLote;
	}

	public void setNumeroSubLote(String numeroSubLote) {
		this.numeroSubLote = numeroSubLote;
	}

	public String getIndicadorValueSelect() {
		return indicadorValueSelect;
	}

	public void setIndicadorValueSelect(String indicadorValueSelect) {
		this.indicadorValueSelect = indicadorValueSelect;
	}

	public Collection getColecaoMatriculaGsan() {
		return colecaoMatriculaGsan;
	}

	public void setColecaoMatriculaGsan(Collection colecaoMatriculaGsan) {
		this.colecaoMatriculaGsan = colecaoMatriculaGsan;
	}

	public String getIndicadorSetorComercialInexistente() {
		return indicadorSetorComercialInexistente;
	}

	public void setIndicadorSetorComercialInexistente(
			String indicadorSetorComercialInexistente) {
		this.indicadorSetorComercialInexistente = indicadorSetorComercialInexistente;
	}

	public String getIndicadorQuadraInexistente() {
		return indicadorQuadraInexistente;
	}

	public void setIndicadorQuadraInexistente(String indicadorQuadraInexistente) {
		this.indicadorQuadraInexistente = indicadorQuadraInexistente;
	}

	public String getIdComando() {
		return idComando;
	}

	public void setIdComando(String idComando) {
		this.idComando = idComando;
	}
	
	public String getLoginCadastrador() {
		return loginCadastrador;
	}

	public void setLoginCadastrador(String loginCadastrador) {
		this.loginCadastrador = loginCadastrador;
	}

	public String getNomeCadastrador() {
		return nomeCadastrador;
	}

	public void setNomeCadastrador(String nomeCadastrador) {
		this.nomeCadastrador = nomeCadastrador;
	}

	public String getIdCadastroOcorrencia() {
		return idCadastroOcorrencia;
	}

	public void setIdCadastroOcorrencia(String idCadastroOcorrencia) {
		this.idCadastroOcorrencia = idCadastroOcorrencia;
	}
	
	public String getQtdImoveis() {
		return qtdImoveis;
	}
	
	public void setQtdImoveis(String qtdImoveis) {
		this.qtdImoveis = qtdImoveis;
	}	
	
	public String getTotalImovelPorCadastrador() {
		return totalImovelPorCadastrador;
	}

	public void setTotalImovelPorCadastrador(String totalImovelPorCadastrador) {
		this.totalImovelPorCadastrador = totalImovelPorCadastrador;
	}

	public String getTotalImovel() {
		return totalImovel;
	}

	public void setTotalImovel(String totalImovel) {
		this.totalImovel = totalImovel;
	}

	public String getIndicadorDesconsiderarImoveldaAtualizacao() {
		return indicadorDesconsiderarImoveldaAtualizacao;
	}

	public void setIndicadorDesconsiderarImoveldaAtualizacao(
			String indicadorDesconsiderarImoveldaAtualizacao) {
		this.indicadorDesconsiderarImoveldaAtualizacao = indicadorDesconsiderarImoveldaAtualizacao;
	}

	public String getInscricaoFormatada(){
		String inscricao = this.getIdLocalidade()+"."+ this.getCodigoSetorComercial() +"."+ this.getNumeroQuadra() +"."+this.getNumeroLote() +"."+ this.getNumeroSubLote();
		return inscricao;
	}
	
	public String getDadosMatriculaAssociadaHint() {
        String hint2 = "";
        
        String matricula = getMatricula();
        
        if ( matricula != null && !matricula.equals( "" ) ){
            Fachada fachada = Fachada.getInstancia();
            
            String inscricao = fachada.pesquisarInscricaoImovelExcluidoOuNao( Integer.parseInt( matricula ) );
             
            Cliente clienteUsuario = fachada.pesquisarClienteUsuarioImovelExcluidoOuNao( Integer.parseInt( matricula ) );
            
            FiltroImovel filtro = new FiltroImovel();
            filtro.adicionarParametro( new ParametroSimples( FiltroImovel.ID, matricula ) );
            filtro.adicionarCaminhoParaCarregamentoEntidade( "ligacaoAgua.hidrometroInstalacaoHistorico.hidrometro" );
            Collection<Imovel> colImovel = fachada.pesquisar( filtro, Imovel.class.getName() );
            
            Imovel imovel = ( Imovel ) colImovel.iterator().next();
            
            String numeroHidrometro = 
                ( imovel.getLigacaoAgua() != null &&
                  imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null &&
                  imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro() != null &&
                  imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro().getNumero() != null ? 
                  imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro().getNumero() : "-" );
            
            String usuario = "";
            
            if (clienteUsuario != null) {
            	usuario = clienteUsuario.getCodigoNome();
            }
            
    		try {
                hint2 =  "Inscri��o: " + 
                         inscricao + "<br>" +
                         "Cliente Usu�rio: " +
                         usuario + "<br>" +
                         "Hidr�metro: " +
                         numeroHidrometro + "<br>" +                     
                         "Endere�o: " +
                         fachada.pesquisarEndereco( Integer.parseInt( matricula ) ) + "<br>";        
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        
        return hint2.replace( '\n', ' ' );
	}

	public void setDadosMatriculaAssociadaHint(String dadosMatriculaAssociadaHint) {
		this.dadosMatriculaAssociadaHint = dadosMatriculaAssociadaHint;
	}

	public String getParametroCadastrador() {
		return parametroCadastrador;
	}

	public void setParametroCadastrador(String parametroCadastrador) {
		this.parametroCadastrador = parametroCadastrador;
	}
	
}

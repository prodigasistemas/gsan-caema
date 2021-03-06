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
package gcom.cadastro;

import gcom.micromedicao.Leiturista;
import gcom.micromedicao.SituacaoTransmissaoLeitura;

import java.io.Serializable;
import java.util.Date;

/**
 * [UC 1391] - Gerar Roteiro Dispositivo M�vel Atualiza��o Cadastral
 * 
 * @author Davi Menezes
 * @date 20/11/2012
 *
 */
public class AtualizacaoCadastralArquivoTexto implements Serializable {

	private static final long serialVersionUID = 1L;

    private Integer id;
    
    private String descricaoArquivo;

    private Integer quantidadeImovel;

    private byte[] arquivoTexto;

    private Date ultimaAlteracao;

    private Date dataArquivoLiberado;
    
    private ParametroTabelaAtualizacaoCadastro parametroTabelaAtualizacaoCadastro;

    private gcom.micromedicao.Leiturista leiturista;

    private SituacaoTransmissaoLeitura situacaoTransmissaoLeitura;
    
    /*
	 * @author Jonathan Marcos
	 * @date 13/02/2014
	 * [OBSERVACOES]
	 * 		- [SB0014] ATUALIZAR A SITUACAO DO ARQUIVO FINALIZADO
	 * 		  SETA A DATA CORRENTE DE FINALIZACAO DO ARQUIVO
	 */
    private Date dataFinalizacaoArquivo;

	/**
	 * @return Retorna o campo arquivoTexto.
	 */
	public byte[] getArquivoTexto() {
		return arquivoTexto;
	}

	/**
	 * @param arquivoTexto O arquivoTexto a ser setado.
	 */
	public void setArquivoTexto(byte[] arquivoTexto) {
		this.arquivoTexto = arquivoTexto;
	}

	/**
	 * @return Retorna o campo descricaoArquivo.
	 */
	public String getDescricaoArquivo() {
		return descricaoArquivo;
	}

	/**
	 * @param descricaoArquivo O descricaoArquivo a ser setado.
	 */
	public void setDescricaoArquivo(String descricaoArquivo) {
		this.descricaoArquivo = descricaoArquivo;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo leiturista.
	 */
	public gcom.micromedicao.Leiturista getLeiturista() {
		return leiturista;
	}

	/**
	 * @param leiturista O leiturista a ser setado.
	 */
	public void setLeiturista(gcom.micromedicao.Leiturista leiturista) {
		this.leiturista = leiturista;
	}

	/**
	 * @return Retorna o campo quantidadeImovel.
	 */
	public Integer getQuantidadeImovel() {
		return quantidadeImovel;
	}

	/**
	 * @param quantidadeImovel O quantidadeImovel a ser setado.
	 */
	public void setQuantidadeImovel(Integer quantidadeImovel) {
		this.quantidadeImovel = quantidadeImovel;
	}

	/**
	 * @return Retorna o campo situacaoTransmissaoLeitura.
	 */
	public SituacaoTransmissaoLeitura getSituacaoTransmissaoLeitura() {
		return situacaoTransmissaoLeitura;
	}

	/**
	 * @param situacaoTransmissaoLeitura O situacaoTransmissaoLeitura a ser setado.
	 */
	public void setSituacaoTransmissaoLeitura(
			SituacaoTransmissaoLeitura situacaoTransmissaoLeitura) {
		this.situacaoTransmissaoLeitura = situacaoTransmissaoLeitura;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	/**
	 * @return Retorna o campo dataArquivoLiberado
	 */
	public Date getDataArquivoLiberado() {
		return dataArquivoLiberado;
	}

	/**
	 * @param dataArquivoLiberado O dataArquivoLiberado a ser setado.
	 */
	public void setDataArquivoLiberado(Date dataArquivoLiberado) {
		this.dataArquivoLiberado = dataArquivoLiberado;
	}

	/**
	 * @return Retorna o campo parametroTabelaAtualizacaoCadastro
	 */
	public ParametroTabelaAtualizacaoCadastro getParametroTabelaAtualizacaoCadastro() {
		return parametroTabelaAtualizacaoCadastro;
	}

	/**
	 * @param parametroTabelaAtualizacaoCadastro O parametroTabelaAtualizacaoCadastro a ser setado.
	 */
	public void setParametroTabelaAtualizacaoCadastro(
			ParametroTabelaAtualizacaoCadastro parametroTabelaAtualizacaoCadastro) {
		this.parametroTabelaAtualizacaoCadastro = parametroTabelaAtualizacaoCadastro;
	}

	/**
	 * @return Retorna o campo dataFinalizacaoArquivo
	 */
	public Date getDataFinalizacaoArquivo() {
		return dataFinalizacaoArquivo;
	}
	
	/**
	 * @param dataFinalizacaoArquivo O dataFinalizacaoArquivo a ser setado.
	 */
	public void setDataFinalizacaoArquivo(Date dataFinalizacaoArquivo) {
		this.dataFinalizacaoArquivo = dataFinalizacaoArquivo;
	}

	public AtualizacaoCadastralArquivoTexto() {
	}
	
	public AtualizacaoCadastralArquivoTexto(Integer id, String descricaoArquivo, Date dataArquivoLiberado, 
			Integer quantidadeImovel, byte[] arquivoTexto, Date ultimaAlteracao, 
			Leiturista leiturista, SituacaoTransmissaoLeitura situacaoTransmissaoLeitura,
			ParametroTabelaAtualizacaoCadastro parametroTabelaAtualizacaoCadastro) {
		this.id = id;
		this.descricaoArquivo = descricaoArquivo;
		this.quantidadeImovel = quantidadeImovel;
		this.dataArquivoLiberado = dataArquivoLiberado;
		this.arquivoTexto = arquivoTexto;
		this.ultimaAlteracao = ultimaAlteracao;
		this.leiturista = leiturista;
		this.situacaoTransmissaoLeitura = situacaoTransmissaoLeitura;
		this.parametroTabelaAtualizacaoCadastro = parametroTabelaAtualizacaoCadastro;
	}
   
    
}

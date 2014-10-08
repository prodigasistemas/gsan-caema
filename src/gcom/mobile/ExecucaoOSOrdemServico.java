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
package gcom.mobile;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.fachada.Fachada;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ExecucaoOSOrdemServico extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	/**
	 * identifier field
	 */
	private ExecucaoOSPK comp_id;

    /** persistent field */
    private short situacao;
    
    /** nullable persistent field */
    private String parecerEncerramento;
    
    /** persistent field */
    private short indicadorConferida;
    
    /** persistent field */
    private Date dataRecebimento;
    
    /** persistent field */
    private Date dataExecucao;

    /** persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private ServicoTipo servicoTipo;
    
    /** persistent field */
    private AtendimentoMotivoEncerramento atendimentoMotivoEncerramento;

    /** persistent field */
    private Usuario usuario;
    
    /** full constructor */
    public ExecucaoOSOrdemServico(ExecucaoOSPK comp_id, short situacao, String parecerEncerramento, short indicadorConferida, Date dataRecebimento, Date dataExecucao, Date ultimaAlteracao, ServicoTipo servicoTipo, AtendimentoMotivoEncerramento atendimentoMotivoEncerramento, Usuario usuario) {
        this.comp_id = comp_id;
        this.situacao = situacao;
        this.parecerEncerramento = parecerEncerramento;
        this.indicadorConferida = indicadorConferida;
        this.dataRecebimento = dataRecebimento;
        this.dataExecucao = dataExecucao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.servicoTipo = servicoTipo;
        this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
        this.usuario = usuario;
    }
    
    public ExecucaoOSOrdemServico( Integer idArquivoTexto, String linha ){
        String[] colunas = linha.split( "\\|" );
        
        Integer idOs = Integer.parseInt( colunas[1] );
        Integer idUsuario = Integer.parseInt( colunas[2] );
        Integer idAtendimentoMotivoEncerramento = Integer.parseInt( colunas[3] );
        Date dtExecucao = Util.converteStringParaDateHora( colunas[4]+"" );
        Date dtRecebimento = new Date();
        String parecer = colunas[5]+"";
        Integer codigoTipoServico =  Integer.parseInt( colunas[6] );        
        
        ExecucaoOSPK pk = new ExecucaoOSPK();
        
        ArquivoTextoOSCobranca atoc = new ArquivoTextoOSCobranca();
        atoc.setId( idArquivoTexto );
        pk.setArquivoTextoOSCobranca( atoc );
        
        OrdemServico os = new OrdemServico();
        os.setId( idOs );
        pk.setOrdemServico( os );
        
        this.setComp_id( pk );
        
        Usuario usuario = new Usuario();
        usuario.setId( idUsuario );        
        this.setUsuario( usuario );
        
        AtendimentoMotivoEncerramento ame = new AtendimentoMotivoEncerramento();
        ame.setId( idAtendimentoMotivoEncerramento );
        this.setAtendimentoMotivoEncerramento( ame );
        
        this.setDataExecucao(dtExecucao);
        this.setDataRecebimento(dtRecebimento);
        this.setParecerEncerramento(parecer);
        
        FiltroServicoTipo fst = new FiltroServicoTipo();
        fst.adicionarParametro( new ParametroSimples( FiltroServicoTipo.CONSTANTE_FUNCIONALIDADE_TIPO_SERVICO, codigoTipoServico.toString() ) );
		Collection<ServicoTipo> colecaoServTipo = Fachada.getInstancia().pesquisar(fst, ServicoTipo.class.getName());
		this.setServicoTipo( (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServTipo) );
		
        this.setIndicadorConferida( ConstantesSistema.NAO );
        this.setSituacao( Short.parseShort( "1" ) );
        this.setUltimaAlteracao( new Date() );
    }

    /** default constructor */
    public ExecucaoOSOrdemServico() {
    }
    
    public ExecucaoOSPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(ExecucaoOSPK comp_id) {
		this.comp_id = comp_id;
	}

	public short getSituacao() {
		return situacao;
	}

	public void setSituacao(short situacao) {
		this.situacao = situacao;
	}

	public String getParecerEncerramento() {
		return parecerEncerramento;
	}

	public void setParecerEncerramento(String parecerEncerramento) {
		this.parecerEncerramento = parecerEncerramento;
	}

	public short getIndicadorConferida() {
		return indicadorConferida;
	}

	public void setIndicadorConferida(short indicadorConferida) {
		this.indicadorConferida = indicadorConferida;
	}

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public Date getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ServicoTipo getServicoTipo() {
		return servicoTipo;
	}

	public void setServicoTipo(ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}

	public AtendimentoMotivoEncerramento getAtendimentoMotivoEncerramento() {
		return atendimentoMotivoEncerramento;
	}

	public void setAtendimentoMotivoEncerramento(
			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento) {
		this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String toString() {
		return new ToStringBuilder(this).append("comp_id", getComp_id())
				.toString();
	}
    
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = {"comp_id"};
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		
		return null;
	}

}

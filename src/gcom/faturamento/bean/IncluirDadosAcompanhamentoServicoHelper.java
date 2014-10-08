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
package gcom.faturamento.bean;

import gcom.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * [UC1225] Incluir Dados Acompanhamento de Serviço
 * 
 * Este caso de uso permite a inserção de dados na tabela de ordem de serviço para acompanhamento do serviço.
 * 
 * @author Thúlio Araújo
 * @date 22/09/2011
 */
public class IncluirDadosAcompanhamentoServicoHelper {
    
    public static final String REGISTRO_TIPO_1 = "01";
    public static final String REGISTRO_TIPO_2 = "02";
    public static final String REGISTRO_TIPO_3 = "03";
    public static final String REGISTRO_TIPO_4 = "04";
    public static final String REGISTRO_TIPO_5 = "05";
    public static final String REGISTRO_TIPO_6 = "06";    
    public static final String REGISTRO_TIPO_7 = "07";
    public static final String REGISTRO_TIPO_8 = "08";
    
    // Campos comuns a todos
    private String tipoRegistro;
    private String idOrdemServico;
    private String idEquipe;
    
    // Tipo de registro 1
    private String rg1IdEquipamentoEspecialFaltante;
    private String rg1IdOsProgramacaoNaoEncerrarMotivo;
    private String rg1IdOsSituacao;
    private String rg1DsPontoReferencia;
    private String rg1DtProgramacamo;
    private String rg1NumeroImovel;
    private String rg1IndicadorAtualizarOS;
    private String rg1IdTipoPvtoRuaRetornado;
    private String rg1IdTipoPvtoCalcadaRetornado;
    private String rg1AreaPavimentoRetornado;
    private String rg1IdMotivoNaoExecucao;
    private String rg1IdMaterialLigacaoAgua;
    private String rg1NumeroProfundidade;
    private String rg1NumeroDiametro;
    private String rg1NumeroDistanciaRede;
    private String rg1DescricaoElementoReferencia;
    
    // Tipo de registro 2
    private String rg2IdOsAtividade;
    private String rg2IdAtividade;
    private String rg2IdOsSituacao;
    private String rg2IdEquipamentoEspecialFaltante;
    private String rg2IdPrestadorServico;
    private String rg2QtdMaterialExcedente;
    
    // Tipo de registro 3
    private String rg3IdOsAtividadePrgAcompServico;
    private String rg3IdMaterial;
    private String rg3QtdMaterial;
    
    // Tipo de registro 4
    private String rg4DataExecucaoInicio;
    private String rg4DataExecucaoFim;
    private String rg4IdOsAtividadePrgAcompServico;
    
    // Tipo de registro 6
    private String rg6idOrdemServico;
    private String rg6idHidrometroLocalInstalacao;
    private String rg6idHidrometroProtecao;
    private String rg6matriculaImovel;
    private String rg6leituraHidrometroAtual;
    private String rg6numeroHidrometroInstalado;
    private String rg6indicadorTipoMedicao;
    private String rg6leituraInstalacao;
    private String rg6seloHidrometro;
    private String rg6indicadorCavalete;
    private String rg6descricaoFoto;

    //Tipo de registro 7
    private String reg7IdTipoServico;
    private String reg7TipoPavimento;
    private String reg7IdPavimento;
    private String reg7Altura;
    private String reg7Largura;
    private String reg7M3;
    
    //Tipo de registro 8
    private String rg8IdServicoRepavimentadora;
    private String rg8QtdServico;

    /**
     * Método usado para extrair uma linha do buffer e enviar para o parse
     * 
     * @author Thúlio Aráujo
     * @param buffer
     * @return Collection<IncluirDadosAcompanhamentoServicoHelper>
     * @throws IOException
     */
    public Collection<IncluirDadosAcompanhamentoServicoHelper> parseHelperArquivo(BufferedReader buffer, boolean prefeitura) throws IOException {
		String linha = "";
		
		Collection<IncluirDadosAcompanhamentoServicoHelper> retorno = new ArrayList<IncluirDadosAcompanhamentoServicoHelper>();
		IncluirDadosAcompanhamentoServicoHelper helper = null;
		
		while ( ( linha = buffer.readLine() ) != null ){
			helper = parseHelper( linha, prefeitura );
			if (helper != null && !helper.equals(null)){
				retorno.add(helper);
			}
		}
		
		return retorno;
    }
    
    /**
     * Retorna o helper com cada registro ara inserir na coleção
     * 
     * @author Thúlio Araújo
     * @date 23/09/2011
     * 
     * @param linha
     * @return IncluirDadosAcompanhamentoServicoHelper
     * @throws IOException
     */
    public IncluirDadosAcompanhamentoServicoHelper 
        parseHelper( String linha, boolean prefeitura ) throws IOException{
        
    	// Vetor que contem todos os elementos que presentes
		// linha
		ArrayList<String> linhaElementos = new ArrayList<String>();
		IncluirDadosAcompanhamentoServicoHelper helper = null;
		
		if(!prefeitura){
			
			String linhaFormatada[] = linha.split("\\|");
			
			for (int j = 0; j < linhaFormatada.length; j++) { 
				linhaElementos.add(linhaFormatada[j]);
			}
			
			// Quebramos a linha em um vetor de Strings
			//linhaElementos = Util.split(linha);
			
		}else{
			linhaElementos = Util.split(linha);
		}

		// Registro Tipo 1
		if (linha.substring(0, 2).equals(REGISTRO_TIPO_1)) {
			helper = parserRegistroTipo1(linhaElementos, prefeitura);
		}
		
		// Registro Tipo 2
		if (linha.substring(0, 2).equals(REGISTRO_TIPO_2)) {
			helper = parserRegistroTipo2(linhaElementos);
		}
		
		// Registro Tipo 3
		if (linha.substring(0, 2).equals(REGISTRO_TIPO_3)) {
			helper = parserRegistroTipo3(linhaElementos);
		}
		
		// Registro Tipo 4
		if (linha.substring(0, 2).equals(REGISTRO_TIPO_4)) {
			helper = parserRegistroTipo4(linhaElementos);
		}
		
		// Registro Tipo 6
		if (linha.substring(0, 2).equals(REGISTRO_TIPO_6)) {
			helper = parserRegistroTipo6(linhaElementos);
		}

		// Registro Tipo 7
		if (linha.substring(0, 2).equals(REGISTRO_TIPO_7)) {
			helper = parserRegistroTipo7(linhaElementos);
		}
		
		// Registro Tipo 8
		if(prefeitura){
			if(linha.substring(0, 2).equals(REGISTRO_TIPO_8)) {
				helper = parserRegistroTipo8(linhaElementos);
			}
		}
				
        return helper;           
    }
   
    /**
     * Método usado para preencher os campos do Registro Tipo 1
     * 
     * @author Thúlio Araújo
     * @since 23/09/2011
     * @param obj
     * @param prefeitura 
     * @return IncluirDadosAcompanhamentoServicoHelper
     */
    private IncluirDadosAcompanhamentoServicoHelper parserRegistroTipo1(ArrayList<String> obj, boolean prefeitura){
    	IncluirDadosAcompanhamentoServicoHelper incluirDadosAcompanhamentoServicoHelper = 
    		new IncluirDadosAcompanhamentoServicoHelper();
    	
    	try{
	    	incluirDadosAcompanhamentoServicoHelper.setTipoRegistro(ignoreNull( obj.get(0)));
	    	incluirDadosAcompanhamentoServicoHelper.setIdOrdemServico(ignoreNull( obj.get(1)));
	    	incluirDadosAcompanhamentoServicoHelper.setRg1IdEquipamentoEspecialFaltante(ignoreNull( obj.get(2)));
	    	incluirDadosAcompanhamentoServicoHelper.setRg1IdOsProgramacaoNaoEncerrarMotivo(ignoreNull( obj.get(3)));
	    	incluirDadosAcompanhamentoServicoHelper.setRg1IdOsSituacao(ignoreNull( obj.get(4)));
	    	incluirDadosAcompanhamentoServicoHelper.setRg1DtProgramacamo(ignoreNull( obj.get(5)));
	    	incluirDadosAcompanhamentoServicoHelper.setRg1DsPontoReferencia(ignoreNull( obj.get(6)));
	    	incluirDadosAcompanhamentoServicoHelper.setRg1NumeroImovel(ignoreNull( obj.get(7)));
	    	
	    	if(prefeitura){
	    		incluirDadosAcompanhamentoServicoHelper.setRg1IndicadorAtualizarOS(ignoreNull(obj.get(8)));
	    		incluirDadosAcompanhamentoServicoHelper.setRg1IdTipoPvtoRuaRetornado(ignoreNull( obj.get(9)));
	    		incluirDadosAcompanhamentoServicoHelper.setRg1IdTipoPvtoCalcadaRetornado(ignoreNull(obj.get(10)));
	    		incluirDadosAcompanhamentoServicoHelper.setRg1AreaPavimentoRetornado(ignoreNull(obj.get(11)));
	    		incluirDadosAcompanhamentoServicoHelper.setRg1IdMotivoNaoExecucao(ignoreNull(obj.get(12)));
	    	}else{
		    	if(obj.size() > 8){
		    		incluirDadosAcompanhamentoServicoHelper.setRg1IdMaterialLigacaoAgua(ignoreNull(obj.get(8)));
			    	incluirDadosAcompanhamentoServicoHelper.setRg1NumeroProfundidade(ignoreNull(obj.get(9)));
			    	incluirDadosAcompanhamentoServicoHelper.setRg1NumeroDiametro(ignoreNull(obj.get(10)));
			    	incluirDadosAcompanhamentoServicoHelper.setRg1NumeroDistanciaRede(ignoreNull(obj.get(11)));
			    	incluirDadosAcompanhamentoServicoHelper.setRg1DescricaoElementoReferencia(ignoreNull(obj.get(12)));
		    	}
	    	}
    	}catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
    		System.out.println("Ordem de Servico: " + incluirDadosAcompanhamentoServicoHelper.getIdOrdemServico());
		}
		
		return incluirDadosAcompanhamentoServicoHelper;
	}
    
    /**
     * Método usado para preencher os campos do Registro Tipo 2
     * 
     * @author Thúlio Araújo
     * @since 23/09/2011
     * @param obj
     * @return IncluirDadosAcompanhamentoServicoHelper
     */
    public static IncluirDadosAcompanhamentoServicoHelper parserRegistroTipo2(ArrayList<String> obj){
    	IncluirDadosAcompanhamentoServicoHelper incluirDadosAcompanhamentoServicoHelper = 
    		new IncluirDadosAcompanhamentoServicoHelper();
    	
    	incluirDadosAcompanhamentoServicoHelper.setTipoRegistro(ignoreNull( obj.get(0)));
    	incluirDadosAcompanhamentoServicoHelper.setRg2IdOsAtividade(ignoreNull( obj.get(1)));
    	incluirDadosAcompanhamentoServicoHelper.setIdOrdemServico(ignoreNull( obj.get(2)));
    	incluirDadosAcompanhamentoServicoHelper.setRg2IdAtividade(ignoreNull( obj.get(3)));
    	incluirDadosAcompanhamentoServicoHelper.setRg2IdOsSituacao(ignoreNull( obj.get(4)));
    	incluirDadosAcompanhamentoServicoHelper.setRg2IdEquipamentoEspecialFaltante(ignoreNull( obj.get(5)));
    	incluirDadosAcompanhamentoServicoHelper.setRg2IdPrestadorServico(ignoreNull( obj.get(6)));
    	incluirDadosAcompanhamentoServicoHelper.setRg2QtdMaterialExcedente(ignoreNull( obj.get(7)));
    	
		return incluirDadosAcompanhamentoServicoHelper;
	}
    
    /**
     * 
     * Método usado para preencher os campos do Registro Tipo 3
     * @author Thúlio Araújo
     * @since 23/09/2011
     * @param obj
     * @return IncluirDadosAcompanhamentoServicoHelper
     */
    public static IncluirDadosAcompanhamentoServicoHelper parserRegistroTipo3(ArrayList<String> obj){
    	IncluirDadosAcompanhamentoServicoHelper incluirDadosAcompanhamentoServicoHelper = 
    		new IncluirDadosAcompanhamentoServicoHelper();
    	
    	incluirDadosAcompanhamentoServicoHelper.setTipoRegistro(ignoreNull( obj.get(0)));
    	//incluirDadosAcompanhamentoServicoHelper.setIdOrdemServico(ignoreNull( obj.get(1)));
    	incluirDadosAcompanhamentoServicoHelper.setRg3IdMaterial(ignoreNull( obj.get(1)));
    	incluirDadosAcompanhamentoServicoHelper.setRg3IdOsAtividadePrgAcompServico(ignoreNull( obj.get(2)));
    	incluirDadosAcompanhamentoServicoHelper.setRg3QtdMaterial(ignoreNull( obj.get(3)));
    	
		return incluirDadosAcompanhamentoServicoHelper;
	}
    
    /**
     * Método usado para preencher os campos do Registro Tipo4
     * 
     * @author Thúlio Araújo
     * @since 23/09/2011
     * @param obj
     * @return IncluirDadosAcompanhamentoServicoHelper
     */
    public static IncluirDadosAcompanhamentoServicoHelper parserRegistroTipo4(ArrayList<String> obj){
    	IncluirDadosAcompanhamentoServicoHelper incluirDadosAcompanhamentoServicoHelper = 
    		new IncluirDadosAcompanhamentoServicoHelper();
    	
    	incluirDadosAcompanhamentoServicoHelper.setTipoRegistro(ignoreNull( obj.get(0)));
    	incluirDadosAcompanhamentoServicoHelper.setRg4IdOsAtividadePrgAcompServico(ignoreNull( obj.get(1)));
    	incluirDadosAcompanhamentoServicoHelper.setRg4DataExecucaoInicio(ignoreNull( obj.get(2)));
    	incluirDadosAcompanhamentoServicoHelper.setRg4DataExecucaoFim(ignoreNull( obj.get(3)));
    	//incluirDadosAcompanhamentoServicoHelper.setRg4IdOsProgramacaoAcompanhamentoServico(ignoreNull( obj.get(4)));

    	return incluirDadosAcompanhamentoServicoHelper;
	}
    
    /**
     * Método usado para preencher os campos do Registro Tipo6
     * 
     * @author Fernanda Almeida
     * @since 17/04/2012
     * @param obj
     * @return IncluirDadosAcompanhamentoServicoHelper
     */
    public static IncluirDadosAcompanhamentoServicoHelper parserRegistroTipo6(ArrayList<String> obj){
    	IncluirDadosAcompanhamentoServicoHelper incluirDadosAcompanhamentoServicoHelper = 
    		new IncluirDadosAcompanhamentoServicoHelper();

    	incluirDadosAcompanhamentoServicoHelper.setTipoRegistro(ignoreNull( obj.get(0)));
    	incluirDadosAcompanhamentoServicoHelper.setRg6idOrdemServico(ignoreNull( obj.get(1)));
    	incluirDadosAcompanhamentoServicoHelper.setRg6matriculaImovel(ignoreNull( obj.get(2)));
    	incluirDadosAcompanhamentoServicoHelper.setRg6leituraHidrometroAtual(ignoreNull( obj.get(3)));
    	incluirDadosAcompanhamentoServicoHelper.setRg6numeroHidrometroInstalado(ignoreNull( obj.get(4)));
    	incluirDadosAcompanhamentoServicoHelper.setRg6indicadorTipoMedicao(ignoreNull( obj.get(5)));
    	incluirDadosAcompanhamentoServicoHelper.setRg6leituraInstalacao(ignoreNull( obj.get(6)));
    	incluirDadosAcompanhamentoServicoHelper.setRg6seloHidrometro(ignoreNull( obj.get(7)));
    	incluirDadosAcompanhamentoServicoHelper.setRg6indicadorCavalete(ignoreNull( obj.get(8)));
    	incluirDadosAcompanhamentoServicoHelper.setRg6idHidrometroLocalInstalacao(ignoreNull( obj.get(9)));
    	incluirDadosAcompanhamentoServicoHelper.setRg6idHidrometroProtecao(ignoreNull( obj.get(10)));
    	incluirDadosAcompanhamentoServicoHelper.setRg6descricaoFoto(ignoreNull( obj.get(11)));

    	return incluirDadosAcompanhamentoServicoHelper;
	}

    /**
     * Método usado para preencher os campos do Registro Tipo7
     * 
     * @author Amelia Pessoa
     * @since 04/05/2012
     * @param obj
     * @return IncluirDadosAcompanhamentoServicoHelper
     */
    public static IncluirDadosAcompanhamentoServicoHelper parserRegistroTipo7(ArrayList<String> obj){
    	IncluirDadosAcompanhamentoServicoHelper incluirDadosAcompanhamentoServicoHelper = 
    		new IncluirDadosAcompanhamentoServicoHelper();
    	
    	incluirDadosAcompanhamentoServicoHelper.setTipoRegistro(ignoreNull(obj.get(0)));
    	incluirDadosAcompanhamentoServicoHelper.setReg7IdTipoServico(ignoreNull(obj.get(2)));
    	incluirDadosAcompanhamentoServicoHelper.setReg7TipoPavimento(ignoreNull(obj.get(3)));
    	incluirDadosAcompanhamentoServicoHelper.setReg7IdPavimento(ignoreNull(obj.get(4)));
    	incluirDadosAcompanhamentoServicoHelper.setReg7Altura(ignoreNull(obj.get(5)));
    	incluirDadosAcompanhamentoServicoHelper.setReg7Largura(ignoreNull(obj.get(6)));
    	incluirDadosAcompanhamentoServicoHelper.setReg7M3(ignoreNull(obj.get(7)));
    	
    	return incluirDadosAcompanhamentoServicoHelper;
	}
    
    public static IncluirDadosAcompanhamentoServicoHelper parserRegistroTipo8(ArrayList<String> obj){
    	IncluirDadosAcompanhamentoServicoHelper incluirDadosAcompanhamentoServicoHelper = 
        		new IncluirDadosAcompanhamentoServicoHelper();
    	
    	incluirDadosAcompanhamentoServicoHelper.setTipoRegistro(ignoreNull(obj.get(0)));
    	incluirDadosAcompanhamentoServicoHelper.setRg8IdServicoRepavimentadora(ignoreNull(obj.get(1)));
    	incluirDadosAcompanhamentoServicoHelper.setRg4IdOsAtividadePrgAcompServico(ignoreNull( obj.get(2)));
    	incluirDadosAcompanhamentoServicoHelper.setRg8QtdServico(ignoreNull(obj.get(3)));
    	
    	return incluirDadosAcompanhamentoServicoHelper;
    }

	public String getRg1DsPontoReferencia() {
		return rg1DsPontoReferencia;
	}

	public void setRg1DsPontoReferencia(String rg1DsPontoReferencia) {
		this.rg1DsPontoReferencia = rg1DsPontoReferencia;
	}

	public String getRg1IdEquipamentoEspecialFaltante() {
		return rg1IdEquipamentoEspecialFaltante;
	}

	public void setRg1IdEquipamentoEspecialFaltante(
			String rg1IdEquipamentoEspecialFaltante) {
		this.rg1IdEquipamentoEspecialFaltante = rg1IdEquipamentoEspecialFaltante;
	}

	public String getRg1IdOsProgramacaoNaoEncerrarMotivo() {
		return rg1IdOsProgramacaoNaoEncerrarMotivo;
	}

	public void setRg1IdOsProgramacaoNaoEncerrarMotivo(
			String rg1IdOsProgramacaoNaoEncerrarMotivo) {
		this.rg1IdOsProgramacaoNaoEncerrarMotivo = rg1IdOsProgramacaoNaoEncerrarMotivo;
	}

	public String getRg1IdOsSituacao() {
		return rg1IdOsSituacao;
	}

	public String getRg6idOrdemServico() {
		return rg6idOrdemServico;
	}

	public void setRg6idOrdemServico(String rg6idOrdemServico) {
		this.rg6idOrdemServico = rg6idOrdemServico;
	}

	public String getRg6idHidrometroLocalInstalacao() {
		return rg6idHidrometroLocalInstalacao;
	}

	public void setRg6idHidrometroLocalInstalacao(String rg6idHidrometroLocalInstalacao) {
		this.rg6idHidrometroLocalInstalacao = rg6idHidrometroLocalInstalacao;
	}

	public String getRg6idHidrometroProtecao() {
		return rg6idHidrometroProtecao;
	}

	public void setRg6idHidrometroProtecao(String rg6idHidrometroProtecao) {
		this.rg6idHidrometroProtecao = rg6idHidrometroProtecao;
	}

	public String getRg6matriculaImovel() {
		return rg6matriculaImovel;
	}

	public void setRg6matriculaImovel(String rg6matriculaImovel) {
		this.rg6matriculaImovel = rg6matriculaImovel;
	}

	public String getRg6leituraHidrometroAtual() {
		return rg6leituraHidrometroAtual;
	}

	public void setRg6leituraHidrometroAtual(String rg6leituraHidrometroAtual) {
		this.rg6leituraHidrometroAtual = rg6leituraHidrometroAtual;
	}

	public String getRg6numeroHidrometroInstalado() {
		return rg6numeroHidrometroInstalado;
	}

	public void setRg6numeroHidrometroInstalado(String rg6numeroHidrometroInstalado) {
		this.rg6numeroHidrometroInstalado = rg6numeroHidrometroInstalado;
	}

	public String getRg6indicadorTipoMedicao() {
		return rg6indicadorTipoMedicao;
	}

	public void setRg6indicadorTipoMedicao(String rg6indicadorTipoMedicao) {
		this.rg6indicadorTipoMedicao = rg6indicadorTipoMedicao;
	}

	public String getRg6leituraInstalacao() {
		return rg6leituraInstalacao;
	}

	public void setRg6leituraInstalacao(String rg6leituraInstalacao) {
		this.rg6leituraInstalacao = rg6leituraInstalacao;
	}

	public String getRg6seloHidrometro() {
		return rg6seloHidrometro;
	}

	public void setRg6seloHidrometro(String rg6seloHidrometro) {
		this.rg6seloHidrometro = rg6seloHidrometro;
	}

	public String getRg6indicadorCavalete() {
		return rg6indicadorCavalete;
	}

	public void setRg6indicadorCavalete(String rg6indicadorCavalete) {
		this.rg6indicadorCavalete = rg6indicadorCavalete;
	}

	public String getRg6descricaoFoto() {
		return rg6descricaoFoto;
	}

	public void setRg6descricaoFoto(String rg6descricaoFoto) {
		this.rg6descricaoFoto = rg6descricaoFoto;
	}

	public void setRg1IdOsSituacao(String rg1IdOsSituacao) {
		this.rg1IdOsSituacao = rg1IdOsSituacao;
	}

	public String getRg1NumeroImovel() {
		return rg1NumeroImovel;
	}

	public void setRg1NumeroImovel(String rg1NumeroImovel) {
		this.rg1NumeroImovel = rg1NumeroImovel;
	}

	public String getRg2IdOsAtividade() {
		return rg2IdOsAtividade;
	}

	public void setRg2IdOsAtividade(String rg2IdOsAtividade) {
		this.rg2IdOsAtividade = rg2IdOsAtividade;
	}

	public String getRg2IdAtividade() {
		return rg2IdAtividade;
	}

	public void setRg2IdAtividade(String rg2IdAtividade) {
		this.rg2IdAtividade = rg2IdAtividade;
	}

	public String getRg2IdEquipamentoEspecialFaltante() {
		return rg2IdEquipamentoEspecialFaltante;
	}

	public void setRg2IdEquipamentoEspecialFaltante(
			String rg2IdEquipamentoEspecialFaltante) {
		this.rg2IdEquipamentoEspecialFaltante = rg2IdEquipamentoEspecialFaltante;
	}

	public String getRg2IdOsSituacao() {
		return rg2IdOsSituacao;
	}

	public void setRg2IdOsSituacao(String rg2IdOsSituacao) {
		this.rg2IdOsSituacao = rg2IdOsSituacao;
	}

	public String getRg2IdPrestadorServico() {
		return rg2IdPrestadorServico;
	}

	public void setRg2IdPrestadorServico(String rg2IdPrestadorServico) {
		this.rg2IdPrestadorServico = rg2IdPrestadorServico;
	}

	public String getRg2QtdMaterialExcedente() {
		return rg2QtdMaterialExcedente;
	}

	public void setRg2QtdMaterialExcedente(String rg2QtdMaterialExcedente) {
		this.rg2QtdMaterialExcedente = rg2QtdMaterialExcedente;
	}

	public String getRg3IdMaterial() {
		return rg3IdMaterial;
	}

	public void setRg3IdMaterial(String rg3IdMaterial) {
		this.rg3IdMaterial = rg3IdMaterial;
	}

	public String getRg3IdOsAtividadePrgAcompServico() {
		return rg3IdOsAtividadePrgAcompServico;
	}

	public void setRg3IdOsAtividadePrgAcompServico(
			String rg3IdOsAtividadePrgAcompServico) {
		this.rg3IdOsAtividadePrgAcompServico = rg3IdOsAtividadePrgAcompServico;
	}

	public String getRg3QtdMaterial() {
		return rg3QtdMaterial;
	}

	public void setRg3QtdMaterial(String rg3QtdMaterial) {
		this.rg3QtdMaterial = rg3QtdMaterial;
	}

	public String getRg4DataExecucaoFim() {
		return rg4DataExecucaoFim;
	}

	public void setRg4DataExecucaoFim(String rg4DataExecucaoFim) {
		this.rg4DataExecucaoFim = rg4DataExecucaoFim;
	}

	public String getRg4DataExecucaoInicio() {
		return rg4DataExecucaoInicio;
	}

	public void setRg4DataExecucaoInicio(String rg4DataExecucaoInicio) {
		this.rg4DataExecucaoInicio = rg4DataExecucaoInicio;
	}

	

	public String getRg4IdOsAtividadePrgAcompServico() {
		return rg4IdOsAtividadePrgAcompServico;
	}

	public void setRg4IdOsAtividadePrgAcompServico(String rg4IdOsAtividadePrgAcompServico) {
		this.rg4IdOsAtividadePrgAcompServico = rg4IdOsAtividadePrgAcompServico;
	}

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public String getRg1DtProgramacamo() {
		return rg1DtProgramacamo;
	}

	public void setRg1DtProgramacamo(String rg1DtProgramacamo) {
		this.rg1DtProgramacamo = rg1DtProgramacamo;
	}
	
	public static String ignoreNull( String string ){
		if (string != null && (string.trim().equals( "" ) || string.trim().equals( "null" )) ){
			return null;
		} else {
			return string;
		}
	}

	public String getReg7IdTipoServico() {
		return reg7IdTipoServico;
	}

	public void setReg7IdTipoServico(String reg7IdTipoServico) {
		this.reg7IdTipoServico = reg7IdTipoServico;
	}

	public String getReg7IdPavimento() {
		return reg7IdPavimento;
	}

	public void setReg7IdPavimento(String reg7IdPavimento) {
		this.reg7IdPavimento = reg7IdPavimento;
	}

	public String getReg7Altura() {
		return reg7Altura;
	}

	public void setReg7Altura(String reg7Altura) {
		this.reg7Altura = reg7Altura;
	}

	public String getReg7Largura() {
		return reg7Largura;
	}

	public void setReg7Largura(String reg7Largura) {
		this.reg7Largura = reg7Largura;
	}

	public String getReg7M3() {
		return reg7M3;
	}

	public void setReg7M3(String reg7M3) {
		this.reg7M3 = reg7M3;
	}

	public String getReg7TipoPavimento() {
		return reg7TipoPavimento;
	}

	public void setReg7TipoPavimento(String reg7TipoPavimento) {
		this.reg7TipoPavimento = reg7TipoPavimento;
	}

	public String getIdEquipe() {
		return idEquipe;
	}

	public void setidEquipe(String idEquipe) {
		this.idEquipe = idEquipe;
	}
	
	public String getRg1IndicadorAtualizarOS() {
		return rg1IndicadorAtualizarOS;
	}

	public void setRg1IndicadorAtualizarOS(String rg1IndicadorAtualizarOS) {
		this.rg1IndicadorAtualizarOS = rg1IndicadorAtualizarOS;
	}

	public String getRg1IdTipoPvtoRuaRetornado() {
		return rg1IdTipoPvtoRuaRetornado;
	}

	public void setRg1IdTipoPvtoRuaRetornado(String rg1IdTipoPvtoRuaRetornado) {
		this.rg1IdTipoPvtoRuaRetornado = rg1IdTipoPvtoRuaRetornado;
	}

	public String getRg1IdTipoPvtoCalcadaRetornado() {
		return rg1IdTipoPvtoCalcadaRetornado;
	}

	public void setRg1IdTipoPvtoCalcadaRetornado(String rg1IdTipoPvtoCalcadaRetornado) {
		this.rg1IdTipoPvtoCalcadaRetornado = rg1IdTipoPvtoCalcadaRetornado;
	}

	public String getRg1AreaPavimentoRetornado() {
		return rg1AreaPavimentoRetornado;
	}

	public void setRg1AreaPavimentoRetornado(String rg1AreaPavimentoRetornado) {
		this.rg1AreaPavimentoRetornado = rg1AreaPavimentoRetornado;
	}

	public String getRg1IdMotivoNaoExecucao() {
		return rg1IdMotivoNaoExecucao;
	}

	public void setRg1IdMotivoNaoExecucao(String rg1IdMotivoNaoExecucao) {
		this.rg1IdMotivoNaoExecucao = rg1IdMotivoNaoExecucao;
	}

	public String getRg8IdServicoRepavimentadora() {
		return rg8IdServicoRepavimentadora;
	}

	public void setRg8IdServicoRepavimentadora(String rg8IdServicoRepavimentadora) {
		this.rg8IdServicoRepavimentadora = rg8IdServicoRepavimentadora;
	}

	public String getRg8QtdServico() {
		return rg8QtdServico;
	}

	public void setRg8QtdServico(String rg8QtdServico) {
		this.rg8QtdServico = rg8QtdServico;
	}

	public String getRg1IdMaterialLigacaoAgua() {
		return rg1IdMaterialLigacaoAgua;
	}

	public void setRg1IdMaterialLigacaoAgua(String rg1IdMaterialLigacaoAgua) {
		this.rg1IdMaterialLigacaoAgua = rg1IdMaterialLigacaoAgua;
	}

	public String getRg1NumeroProfundidade() {
		return rg1NumeroProfundidade;
	}

	public void setRg1NumeroProfundidade(String rg1NumeroProfundidade) {
		this.rg1NumeroProfundidade = rg1NumeroProfundidade;
	}

	public String getRg1NumeroDiametro() {
		return rg1NumeroDiametro;
	}

	public void setRg1NumeroDiametro(String rg1NumeroDiametro) {
		this.rg1NumeroDiametro = rg1NumeroDiametro;
	}

	public String getRg1NumeroDistanciaRede() {
		return rg1NumeroDistanciaRede;
	}

	public void setRg1NumeroDistanciaRede(String rg1NumeroDistanciaRede) {
		this.rg1NumeroDistanciaRede = rg1NumeroDistanciaRede;
	}

	public String getRg1DescricaoElementoReferencia() {
		return rg1DescricaoElementoReferencia;
	}

	public void setRg1DescricaoElementoReferencia(String rg1DescricaoElementoReferencia) {
		this.rg1DescricaoElementoReferencia = rg1DescricaoElementoReferencia;
	}
	
}

package gcom.autoatendimento;

import gcom.faturamento.bean.EmitirContaHelper;
import gcom.relatorio.faturamento.conta.ContaLinhasDescricaoServicosTarifasTotalHelper;
import gcom.util.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * [UC1547] - Consultar Impressao 2 Via de Conta
 * Classe que representa a conta que sera impressa no totem
 * 
 * @author Anderson Cabral
 * @date 30/08/2013
 */
public class SegundaViaContaHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//Conta
	private String descricaoTitulo;
	private String escritorio;
	private String codigoDebitoAutomatico;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	private String ultimosConsumos1;
	private String ultimosConsumos2;
	private String ultimosConsumos3;
	private String ultimosConsumos4;
	private String ultimosConsumos5;
	private String ultimosConsumos6;
	private String numeroEconomias;
	private String consumoPorEconomia;
	private String codigoAuxiliar;
	private String leituraAnterior;
	private String leituraAtual;
	private String consumoFaturamento;
	private String diasConsumo;
	private String consumoMedioDiario;
	private ArrayList<ContaLinhasDescricaoServicosTarifasTotalHelper> taxasDeServicos;
	private String idClienteResponsavel;
	private String enderecoClienteResponsavel;
	private String dataLeituraAnterior;
	private String dataLeituraAtual;
	private String nomeGerenciaRegional;
	private String numeroCpfCnpj;	
	private String emitirPor;
	
	//Geral
	private String idConta;
	private String matriculaImovel;
	private String nomeCliente;
	private String enderecoImovel;
	private String fatura;
	private String inscricao;
	private String valorTotalConta;
	private String primeiraParte;
	private String segundaParte;
	private String terceiraParte;
	private String numeroIndiceTurbidez;
	private String numeroCloroResidual;
	private String representacaoNumericaCodBarraFormatada;
	private String representacaoNumericaCodBarraSemDigito;
	private String dataValidade;
	private String mesAnoFormatado;
	private String grupo;
	private String firma;
	private String vencimento;
	private String numeroDocumento;
	private String mensagemConta;
	//Data do Documento = Data do Processamento = Data de Emissao = Data Atual
	private String dataAtual;
	
	//Boleto
	private String localPagamento;
	private String cedente;
	private String especieDocumento;
	private String aceite;
	private String agenciaCodigoCetente;
	private String carteira;
	private String nossoNumero;
	
	public SegundaViaContaHelper(EmitirContaHelper emitirContaHelper){
		
		//CONTA
		this.escritorio = emitirContaHelper.getDescricaoLocalidade();
		this.codigoDebitoAutomatico = emitirContaHelper.getCodigoDebitoAutomaticoFormatado();						
		this.situacaoLigacaoAgua = emitirContaHelper.getDescricaoLigacaoAguaSituacao();
		this.situacaoLigacaoEsgoto = emitirContaHelper.getDescricaoLigacaoEsgotoSituacao();
		this.ultimosConsumos1 = emitirContaHelper.getDadosConsumoMes1();
		this.ultimosConsumos2 = emitirContaHelper.getDadosConsumoMes2();
		this.ultimosConsumos3 = emitirContaHelper.getDadosConsumoMes3();
		this.ultimosConsumos4 = emitirContaHelper.getDadosConsumoMes4();		
		this.ultimosConsumos5 = emitirContaHelper.getDadosConsumoMes5();
		this.ultimosConsumos6 = emitirContaHelper.getDadosConsumoMes6();
		this.numeroEconomias = emitirContaHelper.getQuantidadeEconomiaConta();
		this.consumoPorEconomia = emitirContaHelper.getConsumoEconomia();
		this.codigoAuxiliar = emitirContaHelper.getCodigoAuxiliarString();
		this.leituraAnterior = emitirContaHelper.getLeituraAnterior();
		this.leituraAtual = emitirContaHelper.getLeituraAtual();
		this.consumoFaturamento = emitirContaHelper.getConsumoFaturamento();
		this.diasConsumo = emitirContaHelper.getDiasConsumo();
		this.consumoMedioDiario = emitirContaHelper.getConsumoMedioDiario();
		this.taxasDeServicos = (ArrayList<ContaLinhasDescricaoServicosTarifasTotalHelper>) emitirContaHelper.getColecaoContaLinhasDescricaoServicosTarifasTotalHelper();
		this.idClienteResponsavel = emitirContaHelper.getIdClienteResponsavel();
		this.enderecoClienteResponsavel = emitirContaHelper.getEnderecoClienteResponsavel();
		this.dataLeituraAnterior = emitirContaHelper.getDataLeituraAnterior();
		this.dataLeituraAtual = emitirContaHelper.getDataLeituraAtual();
		this.nomeGerenciaRegional = emitirContaHelper.getNomeGerenciaRegional();				
		if (emitirContaHelper.getCpf() != null && !emitirContaHelper.getCpf().equals("")){
			this.numeroCpfCnpj = Util.formatarCpf(emitirContaHelper.getCpf()) ;
		}else if (emitirContaHelper.getCnpj() != null && !emitirContaHelper.getCnpj().equals("")){
			this.numeroCpfCnpj = Util.formatarCnpj(emitirContaHelper.getCnpj());
		}		
		this.emitirPor = "INTERNET";
		
		//GERAL
		this.idConta = emitirContaHelper.getIdConta().toString();
		this.matriculaImovel = emitirContaHelper.getMatriculaImovelFormatada();
		this.nomeCliente = emitirContaHelper.getNomeCliente();
		this.enderecoImovel = emitirContaHelper.getEnderecoImovel();
		this.fatura = emitirContaHelper.getFatura();
		this.inscricao = emitirContaHelper.getInscricaoImovel();
		this.valorTotalConta = emitirContaHelper.getValorContaString();
		this.primeiraParte = emitirContaHelper.getPrimeiraParte();
		this.segundaParte = emitirContaHelper.getSegundaParte();
		this.terceiraParte = emitirContaHelper.getTerceiraParte();	
		this.numeroIndiceTurbidez = emitirContaHelper.getNumeroIndiceTurbidez();
		this.numeroCloroResidual = emitirContaHelper.getNumeroCloroResidual();
		this.representacaoNumericaCodBarraFormatada = emitirContaHelper.getRepresentacaoNumericaCodBarraFormatada();
		this.representacaoNumericaCodBarraSemDigito = emitirContaHelper.getRepresentacaoNumericaCodBarraSemDigito();
		this.dataValidade = emitirContaHelper.getDataValidade();
		this.mesAnoFormatado = emitirContaHelper.getMesAnoFormatado();
		this.grupo = emitirContaHelper.getIdFaturamentoGrupo().toString();
		this.firma = emitirContaHelper.getIdEmpresa().toString();	;
		this.vencimento = Util.formatarData(emitirContaHelper.getDataVencimentoConta());
		this.numeroDocumento = (""+emitirContaHelper.getAmReferencia() ) + (emitirContaHelper.getIdImovel().toString());
		this.mensagemConta = emitirContaHelper.getMensagemConsumoString();
		this.dataAtual = Util.formatarData(new Date());	
//		this.descricaoTipoConsumo = emitirContaHelper.getDescricaoTipoConsumo();
//		this.descricaoAnormalidadeConsumo = emitirContaHelper.getDescricaoAnormalidadeConsumo();	
//		this.totalPaginasRelatorio = "" + totalPaginasRelatorio;
//		this.contaSemCodigoBarras = emitirContaHelper.getContaSemCodigoBarras();		
		
		//BOLETO    	
    	this.nossoNumero = emitirContaHelper.getNossoNumero();
		this.localPagamento = "PAGÁVEL EM QUALQUER BANCO ATÉ O VENCIMENTO";
		this.especieDocumento = "FAT";
		this.aceite = "N";
		this.agenciaCodigoCetente = "3234-4/2868-1";
		this.carteira = "18";
	}
	
	public SegundaViaContaHelper(
			String descricaoTitulo,
			String escritorio,
			String codigoDebitoAutomatico,
			String situacaoLigacaoAgua,
			String situacaoLigacaoEsgoto,
			String ultimosConsumos1,
			String ultimosConsumos2,
			String ultimosConsumos3,
			String ultimosConsumos4,
			String ultimosConsumos5,
			String ultimosConsumos6,
			String numeroEconomias,
			String consumoPorEconomia,
			String codigoAuxiliar,
			String leituraAnterior,
			String leituraAtual,
			String consumoFaturamento,
			String diasConsumo,
			String consumoMedioDiario,
			ArrayList<ContaLinhasDescricaoServicosTarifasTotalHelper> taxasDeServicos,
			String idClienteResponsavel, String enderecoClienteResponsavel,
			String dataLeituraAnterior, String dataLeituraAtual,
			String nomeGerenciaRegional, String numeroCpfCnpj,
			String emitirPor, String idConta,
			String matriculaImovel, String nomeCliente, String enderecoImovel,
			String fatura, String inscricao, String valorTotalConta,
			String primeiraParte, String segundaParte, String terceiraParte,
			String numeroIndiceTurbidez, String numeroCloroResidual,
			String representacaoNumericaCodBarraFormatada,
			String representacaoNumericaCodBarraSemDigito, String dataValidade,
			String mesAnoFormatado, String grupo, String firma,
			String vencimento, String numeroDocumento,
			String mensagemConta, String dataAtual, String localPagamento,
			String cedente, String especieDocumento, String aceite,
			String agenciaCodigoCetente, String carteira, String nossoNumero) {
		super();
		this.descricaoTitulo = descricaoTitulo;
		this.escritorio = escritorio;
		this.codigoDebitoAutomatico = codigoDebitoAutomatico;
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
		this.ultimosConsumos1 = ultimosConsumos1;
		this.ultimosConsumos2 = ultimosConsumos2;
		this.ultimosConsumos3 = ultimosConsumos3;
		this.ultimosConsumos4 = ultimosConsumos4;
		this.ultimosConsumos5 = ultimosConsumos5;
		this.ultimosConsumos6 = ultimosConsumos6;
		this.numeroEconomias = numeroEconomias;
		this.consumoPorEconomia = consumoPorEconomia;
		this.codigoAuxiliar = codigoAuxiliar;
		this.leituraAnterior = leituraAnterior;
		this.leituraAtual = leituraAtual;
		this.consumoFaturamento = consumoFaturamento;
		this.diasConsumo = diasConsumo;
		this.consumoMedioDiario = consumoMedioDiario;
		this.taxasDeServicos = taxasDeServicos;
		this.idClienteResponsavel = idClienteResponsavel;
		this.enderecoClienteResponsavel = enderecoClienteResponsavel;
		this.dataLeituraAnterior = dataLeituraAnterior;
		this.dataLeituraAtual = dataLeituraAtual;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.numeroCpfCnpj = numeroCpfCnpj;
		this.emitirPor = emitirPor;
		this.idConta = idConta;
		this.matriculaImovel = matriculaImovel;
		this.nomeCliente = nomeCliente;
		this.enderecoImovel = enderecoImovel;
		this.fatura = fatura;
		this.inscricao = inscricao;
		this.valorTotalConta = valorTotalConta;
		this.primeiraParte = primeiraParte;
		this.segundaParte = segundaParte;
		this.terceiraParte = terceiraParte;
		this.numeroIndiceTurbidez = numeroIndiceTurbidez;
		this.numeroCloroResidual = numeroCloroResidual;
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
		this.dataValidade = dataValidade;
		this.mesAnoFormatado = mesAnoFormatado;
		this.grupo = grupo;
		this.firma = firma;
		this.vencimento = vencimento;
		this.numeroDocumento = numeroDocumento;
		this.mensagemConta = mensagemConta;
		this.dataAtual = dataAtual;
		this.localPagamento = localPagamento;
		this.cedente = cedente;
		this.especieDocumento = especieDocumento;
		this.aceite = aceite;
		this.agenciaCodigoCetente = agenciaCodigoCetente;
		this.carteira = carteira;
		this.nossoNumero = nossoNumero;
	}

	public SegundaViaContaHelper() {
		super();
	}
	
	public String getDescricaoTitulo() {
		return descricaoTitulo;
	}
	public void setDescricaoTitulo(String descricaoTitulo) {
		this.descricaoTitulo = descricaoTitulo;
	}
	public String getEscritorio() {
		return escritorio;
	}
	public void setEscritorio(String escritorio) {
		this.escritorio = escritorio;
	}
	public String getCodigoDebitoAutomatico() {
		return codigoDebitoAutomatico;
	}
	public void setCodigoDebitoAutomatico(String codigoDebitoAutomatico) {
		this.codigoDebitoAutomatico = codigoDebitoAutomatico;
	}
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}
	public String getUltimosConsumos1() {
		return ultimosConsumos1;
	}
	public void setUltimosConsumos1(String ultimosConsumos1) {
		this.ultimosConsumos1 = ultimosConsumos1;
	}
	public String getUltimosConsumos2() {
		return ultimosConsumos2;
	}
	public void setUltimosConsumos2(String ultimosConsumos2) {
		this.ultimosConsumos2 = ultimosConsumos2;
	}
	public String getUltimosConsumos3() {
		return ultimosConsumos3;
	}
	public void setUltimosConsumos3(String ultimosConsumos3) {
		this.ultimosConsumos3 = ultimosConsumos3;
	}
	public String getUltimosConsumos4() {
		return ultimosConsumos4;
	}
	public void setUltimosConsumos4(String ultimosConsumos4) {
		this.ultimosConsumos4 = ultimosConsumos4;
	}
	public String getUltimosConsumos5() {
		return ultimosConsumos5;
	}
	public void setUltimosConsumos5(String ultimosConsumos5) {
		this.ultimosConsumos5 = ultimosConsumos5;
	}
	public String getUltimosConsumos6() {
		return ultimosConsumos6;
	}
	public void setUltimosConsumos6(String ultimosConsumos6) {
		this.ultimosConsumos6 = ultimosConsumos6;
	}
	public String getNumeroEconomias() {
		return numeroEconomias;
	}
	public void setNumeroEconomias(String numeroEconomias) {
		this.numeroEconomias = numeroEconomias;
	}
	public String getConsumoPorEconomia() {
		return consumoPorEconomia;
	}
	public void setConsumoPorEconomia(String consumoPorEconomia) {
		this.consumoPorEconomia = consumoPorEconomia;
	}
	public String getCodigoAuxiliar() {
		return codigoAuxiliar;
	}
	public void setCodigoAuxiliar(String codigoAuxiliar) {
		this.codigoAuxiliar = codigoAuxiliar;
	}
	public String getLeituraAnterior() {
		return leituraAnterior;
	}
	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}
	public String getLeituraAtual() {
		return leituraAtual;
	}
	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}
	public String getConsumoFaturamento() {
		return consumoFaturamento;
	}
	public void setConsumoFaturamento(String consumoFaturamento) {
		this.consumoFaturamento = consumoFaturamento;
	}
	public String getDiasConsumo() {
		return diasConsumo;
	}
	public void setDiasConsumo(String diasConsumo) {
		this.diasConsumo = diasConsumo;
	}
	public String getConsumoMedioDiario() {
		return consumoMedioDiario;
	}
	public void setConsumoMedioDiario(String consumoMedioDiario) {
		this.consumoMedioDiario = consumoMedioDiario;
	}
	public ArrayList<ContaLinhasDescricaoServicosTarifasTotalHelper> getTaxasDeServicos() {
		return taxasDeServicos;
	}
	public void setTaxasDeServicos(
			ArrayList<ContaLinhasDescricaoServicosTarifasTotalHelper> taxasDeServicos) {
		this.taxasDeServicos = taxasDeServicos;
	}
	public String getMensagemConta() {
		return mensagemConta;
	}
	public void setMensagemConta(String mensagemConta) {
		this.mensagemConta = mensagemConta;
	}
	public String getIdClienteResponsavel() {
		return idClienteResponsavel;
	}
	public void setIdClienteResponsavel(String idClienteResponsavel) {
		this.idClienteResponsavel = idClienteResponsavel;
	}
	public String getEnderecoClienteResponsavel() {
		return enderecoClienteResponsavel;
	}
	public void setEnderecoClienteResponsavel(String enderecoClienteResponsavel) {
		this.enderecoClienteResponsavel = enderecoClienteResponsavel;
	}
	public String getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}
	public void setDataLeituraAnterior(String dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}
	public String getDataLeituraAtual() {
		return dataLeituraAtual;
	}
	public void setDataLeituraAtual(String dataLeituraAtual) {
		this.dataLeituraAtual = dataLeituraAtual;
	}
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	public String getNumeroCpfCnpj() {
		return numeroCpfCnpj;
	}
	public void setNumeroCpfCnpj(String numeroCpfCnpj) {
		this.numeroCpfCnpj = numeroCpfCnpj;
	}
	/**Data do Documento = Data do Processamento = Data de Emissao = Data Atual**/
	public String getDataAtual() {
		return dataAtual;
	}
	/**Data do Documento = Data do Processamento = Data de Emissao = Data Atual**/
	public void setDataAtual(String dataAtual) {
		this.dataAtual = dataAtual;
	}
	public String getEmitirPor() {
		return emitirPor;
	}
	public void setEmitirPor(String emitirPor) {
		this.emitirPor = emitirPor;
	}
	public String getIdConta() {
		return idConta;
	}
	public void setIdConta(String idConta) {
		this.idConta = idConta;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getEnderecoImovel() {
		return enderecoImovel;
	}
	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}
	public String getFatura() {
		return fatura;
	}
	public void setFatura(String fatura) {
		this.fatura = fatura;
	}
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	public String getValorTotalConta() {
		return valorTotalConta;
	}
	public void setValorTotalConta(String valorTotalConta) {
		this.valorTotalConta = valorTotalConta;
	}
	public String getPrimeiraParte() {
		return primeiraParte;
	}
	public void setPrimeiraParte(String primeiraParte) {
		this.primeiraParte = primeiraParte;
	}
	public String getSegundaParte() {
		return segundaParte;
	}
	public void setSegundaParte(String segundaParte) {
		this.segundaParte = segundaParte;
	}
	public String getTerceiraParte() {
		return terceiraParte;
	}
	public void setTerceiraParte(String terceiraParte) {
		this.terceiraParte = terceiraParte;
	}
	public String getNumeroIndiceTurbidez() {
		return numeroIndiceTurbidez;
	}
	public void setNumeroIndiceTurbidez(String numeroIndiceTurbidez) {
		this.numeroIndiceTurbidez = numeroIndiceTurbidez;
	}
	public String getNumeroCloroResidual() {
		return numeroCloroResidual;
	}
	public void setNumeroCloroResidual(String numeroCloroResidual) {
		this.numeroCloroResidual = numeroCloroResidual;
	}
	public String getRepresentacaoNumericaCodBarraFormatada() {
		return representacaoNumericaCodBarraFormatada;
	}
	public void setRepresentacaoNumericaCodBarraFormatada(
			String representacaoNumericaCodBarraFormatada) {
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
	}
	public String getRepresentacaoNumericaCodBarraSemDigito() {
		return representacaoNumericaCodBarraSemDigito;
	}
	public void setRepresentacaoNumericaCodBarraSemDigito(
			String representacaoNumericaCodBarraSemDigito) {
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
	}
	public String getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}
	public String getMesAnoFormatado() {
		return mesAnoFormatado;
	}
	public void setMesAnoFormatado(String mesAnoFormatado) {
		this.mesAnoFormatado = mesAnoFormatado;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getFirma() {
		return firma;
	}
	public void setFirma(String firma) {
		this.firma = firma;
	}
	public String getVencimento() {
		return vencimento;
	}
	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getLocalPagamento() {
		return localPagamento;
	}
	public void setLocalPagamento(String localPagamento) {
		this.localPagamento = localPagamento;
	}
	public String getCedente() {
		return cedente;
	}
	public void setCedente(String cedente) {
		this.cedente = cedente;
	}
	public String getEspecieDocumento() {
		return especieDocumento;
	}
	public void setEspecieDocumento(String especieDocumento) {
		this.especieDocumento = especieDocumento;
	}
	public String getAceite() {
		return aceite;
	}
	public void setAceite(String aceite) {
		this.aceite = aceite;
	}
	public String getAgenciaCodigoCetente() {
		return agenciaCodigoCetente;
	}
	public void setAgenciaCodigoCetente(String agenciaCodigoCetente) {
		this.agenciaCodigoCetente = agenciaCodigoCetente;
	}
	public String getCarteira() {
		return carteira;
	}
	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}
	public String getNossoNumero() {
		return nossoNumero;
	}
	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}
}

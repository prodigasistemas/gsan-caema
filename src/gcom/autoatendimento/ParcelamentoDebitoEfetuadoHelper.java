package gcom.autoatendimento;

import gcom.relatorio.arrecadacao.pagamento.RelatorioEmitirGuiaPagamentoDetailBean;
import gcom.relatorio.cobranca.parcelamento.RelatorioParcelamentoDetalhamentoBean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * [UC1556] Consultar Parcelar Parcelamento de Débitos Webservice
 * 
 * @author Anderson Cabral
 * @date 11/09/2013
 */
public class ParcelamentoDebitoEfetuadoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//***********GERAL************//
	private String matriculaImovel; //idImovel = numeroDocumentoFicha
	private String dataAtual; //dataEmissao = dataDocumento = dataProcessamento
	private String valorDebito; //  = valorDocumentoFicha
	
	//***********GUIA***************//
	private String nomeEmpresa;
	private String cnpjEmpresa;
	private String anoGuiaPagamento; //docNumero
	private String idGuiaPagamento; //docNumero
	private String nomeCliente;
	private String dataVencimento;
	private String cpfCnpjCliente;
	private String inscricao;
	private String enderecoImovel;
	private String enderecoEntrega; //enderecoClienteResponsavel
	private String observacao;
	private ArrayList<RelatorioEmitirGuiaPagamentoDetailBean> taxasDeServicos;
	private String representacaoNumericaCodBarraFormatada ;
	private String representacaoNumericaCodBarraSemDigito ;
	private String emitidoPor; //nomeUsuario
	private String dataValidade ;
	private String idFuncionario;

	//***********FICHA DE COMPENSACAO***************//
	private String localPagamento; //PAGÁVEL EM QUALQUER BANCO ATÉ O VENCIMENTO
	private String cedente; //COMPESA – Companhia Pernambucana de Saneamento
	private String vencimentoFicha; //Contra-apresentação
	private String especieDocumento; //EXT
	private String aceite; //N
	private String agenciaCodigoCedente; //3234-4/2868-1
	private String carteira; //18
	private String especieMoeda; //R$
	private String nossoNumero;
	private String mensagem; //Instruções(Todas informações deste bloqueto são de exclusiva responsabilidade do cedente)
	private String nomeSacador;
	private String enderecoImovelSacador;	
	
	//*************TERMO******************//
	private String telefone;
	private String dataParcelamento;
	
	//****************DESCRICAO DEBITO********************//
	private String faturasEmAberto;
	private String servicosACobrar;
	private String atualizacaoMonetaria;
	private String jurosMora;
	private String multa;
	private String guiaPagamento;
	private String parcelamentoACobrar;
	private String totalDebitos;
	
	//************DESCONTOS/CREDITOS***************//
	private String descontoAcrescimo;
	private String descontosAntiguidade;
	private String descontoInatividade;
	private String valorDescontoSancoesRegulamentares;
	private String valorDescontoTarifaSocial;
    private String valorDescontoDebitoTotal;	
	private String creditosARealizar;
	private String totalDescontos;
		
	private String valorASerNegociado;	
	
	//********************CONDICOES DE NEGOCIACAO************************//
	private String valorEntrada;
	private String numeroParcelas;
	private String valorParcela;
	private String valorASerParcelado;
	private String valorJuros;
		
	private String solicitacaoRestabelecimento;
		
	//*************************TERMO COMPROMISSO************************************//
	private String municipio;
	private String sequencial; //idParcelamento
	
	//**************************DETALHAMENTO DOS DEBITOS E CREDITOS*****************************//
	private ArrayList<RelatorioParcelamentoDetalhamentoBean> detalhamentoCreditoDebito;
	
	/**+++++++++++++++VARIFICAR DADOS+++++++++++++++*/
//	private String unidadeUsuario;
//	private String idFuncionario;
//	private String cpfClienteParcelamento;
//	private JRBeanCollectionDataSource arrayJRDetalhamento;
//	private ArrayList arrayRelatorioParcelamentoDetalhamentoBean;
//	private String codigoEmpresaFebraban;
//	private String rgCliente;
//	private String nomeUsuario;
//	private String matriculaUsuario;
//	private String mesAnoInicioParcelamento;
//	private String mesAnoFinalParcelamento;	
//	private String cpfUsuario;
//	private String nomeDiretorComercial;
//	private String cpfDiretorComercial;
//	private String profissao;
//	private String nomeDevedor;
//	private String cnpjDevedor;
//	private String enderecoDevedor;
//	private String telefoneDevedor;
//	private Short indicadorPessoaJuridica;
//	private Integer maiorAnoMesReferenciaDocumentos = new Integer(0);
//	private Integer menorAnoMesReferenciaDocumentos = new Integer(0);    
//	private String nomeUsuarioParcelamento;
//	private String bairro;
//	private String codigoRota;
	
	
	public ParcelamentoDebitoEfetuadoHelper() {
		super();
	}
	
	public ParcelamentoDebitoEfetuadoHelper(
			String matriculaImovel,
			String dataAtual,
			String valorDebito,
			String nomeEmpresa,
			String cnpjEmpresa,
			String anoGuiaPagamento,
			String idGuiaPagamento,
			String nomeCliente,
			String dataVencimento,
			String cpfCnpjCliente,
			String inscricao,
			String enderecoImovel,
			String enderecoEntrega,
			String observacao,
			ArrayList<RelatorioEmitirGuiaPagamentoDetailBean> taxasDeServicos,
			String representacaoNumericaCodBarraFormatada,
			String representacaoNumericaCodBarraSemDigito,
			String emitidoPor,
			String dataValidade,
			String idFuncionario,
			String localPagamento,
			String cedente,
			String vencimentoFicha,
			String especieDocumento,
			String aceite,
			String agenciaCodigoCedente,
			String carteira,
			String especieMoeda,
			String nossoNumero,
			String mensagem,
			String nomeSacador,
			String enderecoImovelSacador,
			String telefone,
			String dataParcelamento,
			String faturasEmAberto,
			String servicosACobrar,
			String atualizacaoMonetaria,
			String jurosMora,
			String multa,
			String guiaPagamento,
			String parcelamentoACobrar,
			String totalDebitos,
			String descontoAcrescimo,
			String descontosAntiguidade,
			String descontoInatividade,
			String valorDescontoSancoesRegulamentares,
			String valorDescontoTarifaSocial,
			String valorDescontoDebitoTotal,
			String creditosARealizar,
			String totalDescontos,
			String valorASerNegociado,
			String valorEntrada,
			String numeroParcelas,
			String valorParcela,
			String valorASerParcelado,
			String valorJuros,
			String solicitacaoRestabelecimento,
			String municipio,
			String sequencial,
			ArrayList<RelatorioParcelamentoDetalhamentoBean> detalhamentoCreditoDebito) {
		
		super();
		this.matriculaImovel = matriculaImovel;
		this.dataAtual = dataAtual;
		this.valorDebito = valorDebito;
		this.nomeEmpresa = nomeEmpresa;
		this.cnpjEmpresa = cnpjEmpresa;
		this.anoGuiaPagamento = anoGuiaPagamento;
		this.idGuiaPagamento = idGuiaPagamento;
		this.nomeCliente = nomeCliente;
		this.dataVencimento = dataVencimento;
		this.cpfCnpjCliente = cpfCnpjCliente;
		this.inscricao = inscricao;
		this.enderecoImovel = enderecoImovel;
		this.enderecoEntrega = enderecoEntrega;
		this.observacao = observacao;
		this.taxasDeServicos = taxasDeServicos;
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
		this.emitidoPor = emitidoPor;
		this.dataValidade = dataValidade;
		this.idFuncionario = idFuncionario;
		this.localPagamento = localPagamento;
		this.cedente = cedente;
		this.vencimentoFicha = vencimentoFicha;
		this.especieDocumento = especieDocumento;
		this.aceite = aceite;
		this.agenciaCodigoCedente = agenciaCodigoCedente;
		this.carteira = carteira;
		this.especieMoeda = especieMoeda;
		this.nossoNumero = nossoNumero;
		this.mensagem = mensagem;
		this.nomeSacador = nomeSacador;
		this.enderecoImovelSacador = enderecoImovelSacador;
		this.telefone = telefone;
		this.dataParcelamento = dataParcelamento;
		this.faturasEmAberto = faturasEmAberto;
		this.servicosACobrar = servicosACobrar;
		this.atualizacaoMonetaria = atualizacaoMonetaria;
		this.jurosMora = jurosMora;
		this.multa = multa;
		this.guiaPagamento = guiaPagamento;
		this.parcelamentoACobrar = parcelamentoACobrar;
		this.totalDebitos = totalDebitos;
		this.descontoAcrescimo = descontoAcrescimo;
		this.descontosAntiguidade = descontosAntiguidade;
		this.descontoInatividade = descontoInatividade;
		this.valorDescontoSancoesRegulamentares = valorDescontoSancoesRegulamentares;
		this.valorDescontoTarifaSocial = valorDescontoTarifaSocial;
		this.valorDescontoDebitoTotal = valorDescontoDebitoTotal;
		this.creditosARealizar = creditosARealizar;
		this.totalDescontos = totalDescontos;
		this.valorASerNegociado = valorASerNegociado;
		this.valorEntrada = valorEntrada;
		this.numeroParcelas = numeroParcelas;
		this.valorParcela = valorParcela;
		this.valorASerParcelado = valorASerParcelado;
		this.valorJuros = valorJuros;
		this.solicitacaoRestabelecimento = solicitacaoRestabelecimento;
		this.municipio = municipio;
		this.sequencial = sequencial;
		this.detalhamentoCreditoDebito = detalhamentoCreditoDebito;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(String dataAtual) {
		this.dataAtual = dataAtual;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getCnpjEmpresa() {
		return cnpjEmpresa;
	}

	public void setCnpjEmpresa(String cnpjEmpresa) {
		this.cnpjEmpresa = cnpjEmpresa;
	}

	public String getAnoGuiaPagamento() {
		return anoGuiaPagamento;
	}

	public void setAnoGuiaPagamento(String anoGuiaPagamento) {
		this.anoGuiaPagamento = anoGuiaPagamento;
	}

	public String getIdGuiaPagamento() {
		return idGuiaPagamento;
	}

	public void setIdGuiaPagamento(String idGuiaPagamento) {
		this.idGuiaPagamento = idGuiaPagamento;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}

	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(String enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public ArrayList<RelatorioEmitirGuiaPagamentoDetailBean> getTaxasDeServicos() {
		return taxasDeServicos;
	}

	public void setTaxasDeServicos(
			ArrayList<RelatorioEmitirGuiaPagamentoDetailBean> taxasDeServicos) {
		this.taxasDeServicos = taxasDeServicos;
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

	public String getEmitidoPor() {
		return emitidoPor;
	}

	public void setEmitidoPor(String emitidoPor) {
		this.emitidoPor = emitidoPor;
	}

	public String getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
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

	public String getVencimentoFicha() {
		return vencimentoFicha;
	}

	public void setVencimentoFicha(String vencimentoFicha) {
		this.vencimentoFicha = vencimentoFicha;
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

	public String getAgenciaCodigoCedente() {
		return agenciaCodigoCedente;
	}

	public void setAgenciaCodigoCedente(String agenciaCodigoCedente) {
		this.agenciaCodigoCedente = agenciaCodigoCedente;
	}

	public String getCarteira() {
		return carteira;
	}

	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}

	public String getEspecieMoeda() {
		return especieMoeda;
	}

	public void setEspecieMoeda(String especieMoeda) {
		this.especieMoeda = especieMoeda;
	}

	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getNomeSacador() {
		return nomeSacador;
	}

	public void setNomeSacador(String nomeSacador) {
		this.nomeSacador = nomeSacador;
	}

	public String getEnderecoImovelSacador() {
		return enderecoImovelSacador;
	}

	public void setEnderecoImovelSacador(String enderecoImovelSacador) {
		this.enderecoImovelSacador = enderecoImovelSacador;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDataParcelamento() {
		return dataParcelamento;
	}

	public void setDataParcelamento(String dataParcelamento) {
		this.dataParcelamento = dataParcelamento;
	}

	public String getFaturasEmAberto() {
		return faturasEmAberto;
	}

	public void setFaturasEmAberto(String faturasEmAberto) {
		this.faturasEmAberto = faturasEmAberto;
	}

	public String getServicosACobrar() {
		return servicosACobrar;
	}

	public void setServicosACobrar(String servicosACobrar) {
		this.servicosACobrar = servicosACobrar;
	}

	public String getAtualizacaoMonetaria() {
		return atualizacaoMonetaria;
	}

	public void setAtualizacaoMonetaria(String atualizacaoMonetaria) {
		this.atualizacaoMonetaria = atualizacaoMonetaria;
	}

	public String getJurosMora() {
		return jurosMora;
	}

	public void setJurosMora(String jurosMora) {
		this.jurosMora = jurosMora;
	}

	public String getMulta() {
		return multa;
	}

	public void setMulta(String multa) {
		this.multa = multa;
	}

	public String getGuiaPagamento() {
		return guiaPagamento;
	}

	public void setGuiaPagamento(String guiaPagamento) {
		this.guiaPagamento = guiaPagamento;
	}

	public String getParcelamentoACobrar() {
		return parcelamentoACobrar;
	}

	public void setParcelamentoACobrar(String parcelamentoACobrar) {
		this.parcelamentoACobrar = parcelamentoACobrar;
	}

	public String getTotalDebitos() {
		return totalDebitos;
	}

	public void setTotalDebitos(String totalDebitos) {
		this.totalDebitos = totalDebitos;
	}

	public String getDescontoAcrescimo() {
		return descontoAcrescimo;
	}

	public void setDescontoAcrescimo(String descontoAcrescimo) {
		this.descontoAcrescimo = descontoAcrescimo;
	}

	public String getDescontosAntiguidade() {
		return descontosAntiguidade;
	}

	public void setDescontosAntiguidade(String descontosAntiguidade) {
		this.descontosAntiguidade = descontosAntiguidade;
	}

	public String getDescontoInatividade() {
		return descontoInatividade;
	}

	public void setDescontoInatividade(String descontoInatividade) {
		this.descontoInatividade = descontoInatividade;
	}

	public String getValorDescontoSancoesRegulamentares() {
		return valorDescontoSancoesRegulamentares;
	}

	public void setValorDescontoSancoesRegulamentares(
			String valorDescontoSancoesRegulamentares) {
		this.valorDescontoSancoesRegulamentares = valorDescontoSancoesRegulamentares;
	}

	public String getValorDescontoTarifaSocial() {
		return valorDescontoTarifaSocial;
	}

	public void setValorDescontoTarifaSocial(String valorDescontoTarifaSocial) {
		this.valorDescontoTarifaSocial = valorDescontoTarifaSocial;
	}

	public String getValorDescontoDebitoTotal() {
		return valorDescontoDebitoTotal;
	}

	public void setValorDescontoDebitoTotal(String valorDescontoDebitoTotal) {
		this.valorDescontoDebitoTotal = valorDescontoDebitoTotal;
	}

	public String getCreditosARealizar() {
		return creditosARealizar;
	}

	public void setCreditosARealizar(String creditosARealizar) {
		this.creditosARealizar = creditosARealizar;
	}

	public String getTotalDescontos() {
		return totalDescontos;
	}

	public void setTotalDescontos(String totalDescontos) {
		this.totalDescontos = totalDescontos;
	}

	public String getValorASerNegociado() {
		return valorASerNegociado;
	}

	public void setValorASerNegociado(String valorASerNegociado) {
		this.valorASerNegociado = valorASerNegociado;
	}

	public String getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(String valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public String getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(String numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public String getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(String valorParcela) {
		this.valorParcela = valorParcela;
	}

	public String getValorASerParcelado() {
		return valorASerParcelado;
	}

	public void setValorASerParcelado(String valorASerParcelado) {
		this.valorASerParcelado = valorASerParcelado;
	}

	public String getValorJuros() {
		return valorJuros;
	}

	public void setValorJuros(String valorJuros) {
		this.valorJuros = valorJuros;
	}

	public String getSolicitacaoRestabelecimento() {
		return solicitacaoRestabelecimento;
	}

	public void setSolicitacaoRestabelecimento(String solicitacaoRestabelecimento) {
		this.solicitacaoRestabelecimento = solicitacaoRestabelecimento;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getSequencial() {
		return sequencial;
	}

	public void setSequencial(String sequencial) {
		this.sequencial = sequencial;
	}

	public ArrayList<RelatorioParcelamentoDetalhamentoBean> getDetalhamentoCreditoDebito() {
		return detalhamentoCreditoDebito;
	}

	public void setDetalhamentoCreditoDebito(
			ArrayList<RelatorioParcelamentoDetalhamentoBean> detalhamentoCreditoDebito) {
		this.detalhamentoCreditoDebito = detalhamentoCreditoDebito;
	}
	
}

package gcom.relatorio.cadastro;

/**
 * [UC1428] Unificar ID de Clientes com o Mesmo Documento
 * 2. O sistema executa o step "Gerar Relatorio de Clientes com o Mesmo Cpf/Cnpj"
 *
 * @author Mariana Victor
 * @date 22/01/2013
 */
public class RelatorioClientesMesmoCpfCnpjHelper {

	private String idClienteAtual;
	
	private String idClienteAnterior;

	private String nomeClienteAtual;
	
	private String nomeClienteAnterior;

	private String imovel;

	private String documento;
	
	public RelatorioClientesMesmoCpfCnpjHelper(){
		
	}

	public String getIdClienteAtual() {
		return idClienteAtual;
	}

	public void setIdClienteAtual(String idClienteAtual) {
		this.idClienteAtual = idClienteAtual;
	}

	public String getIdClienteAnterior() {
		return idClienteAnterior;
	}

	public void setIdClienteAnterior(String idClienteAnterior) {
		this.idClienteAnterior = idClienteAnterior;
	}

	public String getNomeClienteAtual() {
		return nomeClienteAtual;
	}

	public void setNomeClienteAtual(String nomeClienteAtual) {
		this.nomeClienteAtual = nomeClienteAtual;
	}

	public String getNomeClienteAnterior() {
		return nomeClienteAnterior;
	}

	public void setNomeClienteAnterior(String nomeClienteAnterior) {
		this.nomeClienteAnterior = nomeClienteAnterior;
	}

	public String getImovel() {
		return imovel;
	}

	public void setImovel(String imovel) {
		this.imovel = imovel;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
		
}
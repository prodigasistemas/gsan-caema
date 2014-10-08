package gcom.operacional.bean;

import java.io.Serializable;
import java.util.ArrayList;

import gcom.operacional.SubSistemaEsgotoArquivoTexto;
import gcom.operacional.SubSistemaEsgotoArquivoTextoErro;
/**
 * [UC1522] - Consultar Atualizações de Subsistema Esgoto
 * @author Anderson Cabral
 * @since 07/07/2013
 * 
 */
public class SubSistemaEsgotoArquivoTextoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private SubSistemaEsgotoArquivoTexto subSistemaEsgotoArquivoTexto;
	private ArrayList<SubSistemaEsgotoArquivoTextoErro>  subSistemaEsgotoArquivoTextoErro;
	private Integer quantidadeErros;
	
	public SubSistemaEsgotoArquivoTexto getSubSistemaEsgotoArquivoTexto() {
		return subSistemaEsgotoArquivoTexto;
	}
	public void setSubSistemaEsgotoArquivoTexto(
			SubSistemaEsgotoArquivoTexto subSistemaEsgotoArquivoTexto) {
		this.subSistemaEsgotoArquivoTexto = subSistemaEsgotoArquivoTexto;
	}
	public ArrayList<SubSistemaEsgotoArquivoTextoErro> getSubSistemaEsgotoArquivoTextoErro() {
		return subSistemaEsgotoArquivoTextoErro;
	}
	public void setSubSistemaEsgotoArquivoTextoErro(
			ArrayList<SubSistemaEsgotoArquivoTextoErro> subSistemaEsgotoArquivoTextoErro) {
		this.subSistemaEsgotoArquivoTextoErro = subSistemaEsgotoArquivoTextoErro;
	}
	public Integer getQuantidadeErros() {
		return quantidadeErros;
	}
	public void setQuantidadeErros(Integer quantidadeErros) {
		this.quantidadeErros = quantidadeErros;
	}
}

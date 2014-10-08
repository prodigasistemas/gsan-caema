package gcom.atendimentopublico.registroatendimento.bean;

import gcom.atendimentopublico.registroatendimento.NormaArquivos;
import gcom.atendimentopublico.registroatendimento.NormaProcedimentos;

import java.io.Serializable;
import java.util.Collection;

public class NormasProcedimentosHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private NormaProcedimentos normaProcedimentos;
	
	private Collection<NormaArquivos> colecaoNormaArquivos;

	public NormasProcedimentosHelper(NormaProcedimentos normaProcedimentos,
			Collection<NormaArquivos> colecaoNormaArquivos) {
		super();
		this.normaProcedimentos = normaProcedimentos;
		this.colecaoNormaArquivos = colecaoNormaArquivos;
	}

	public NormasProcedimentosHelper() {
		super();
	}

	public NormaProcedimentos getNormaProcedimentos() {
		return normaProcedimentos;
	}

	public void setNormaProcedimentos(NormaProcedimentos normaProcedimentos) {
		this.normaProcedimentos = normaProcedimentos;
	}

	public Collection<NormaArquivos> getColecaoNormaArquivos() {
		return colecaoNormaArquivos;
	}

	public void setColecaoNormaArquivos(Collection<NormaArquivos> colecaoNormaArquivos) {
		this.colecaoNormaArquivos = colecaoNormaArquivos;
	}

}

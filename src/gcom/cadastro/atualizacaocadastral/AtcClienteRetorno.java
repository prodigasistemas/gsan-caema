package gcom.cadastro.atualizacaocadastral;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AtcClienteRetorno implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private AtcClienteRetornoPK comp_id;

    /** nullable persistent field */
    private String atcCliCnpj;

    /** nullable persistent field */
    private String atcCliEmail;

    /** nullable persistent field */
    private String atcCliNomeFantasia;

    /** nullable persistent field */
    private String atcCliNomeReceita;

    /** nullable persistent field */
    private String atcCliCpf;

    /** nullable persistent field */
    private String atcCliRg;

    /** nullable persistent field */
    private String atcCliDataExpedicao;

    /** nullable persistent field */
    private String atcCliDataNascimento;

    /** nullable persistent field */
    private Integer atcOrgId;

    /** nullable persistent field */
    private Integer atcUnfId;

    /** nullable persistent field */
    private Integer atcProId;

    /** nullable persistent field */
    private Integer atcRamId;

    /** nullable persistent field */
    private Integer atcCltId;

    /** persistent field */
    private int atcCliTipoPessoa;

    /** nullable persistent field */
    private Integer atcCliSexo;

    /** nullable persistent field */
    private String atcCliNomeMae;

    /** full constructor */
    public AtcClienteRetorno(AtcClienteRetornoPK comp_id, String atcCliCnpj, String atcCliEmail, String atcCliNomeFantasia, String atcCliNomeReceita, String atcCliCpf, String atcCliRg, String atcCliDataExpedicao, String atcCliDataNascimento, Integer atcOrgId, Integer atcUnfId, Integer atcProId, Integer atcRamId, Integer atcCltId, int atcCliTipoPessoa, Integer atcCliSexo, String atcCliNomeMae) {
        this.comp_id = comp_id;
        this.atcCliCnpj = atcCliCnpj;
        this.atcCliEmail = atcCliEmail;
        this.atcCliNomeFantasia = atcCliNomeFantasia;
        this.atcCliNomeReceita = atcCliNomeReceita;
        this.atcCliCpf = atcCliCpf;
        this.atcCliRg = atcCliRg;
        this.atcCliDataExpedicao = atcCliDataExpedicao;
        this.atcCliDataNascimento = atcCliDataNascimento;
        this.atcOrgId = atcOrgId;
        this.atcUnfId = atcUnfId;
        this.atcProId = atcProId;
        this.atcRamId = atcRamId;
        this.atcCltId = atcCltId;
        this.atcCliTipoPessoa = atcCliTipoPessoa;
        this.atcCliSexo = atcCliSexo;
        this.atcCliNomeMae = atcCliNomeMae;
    }

    /** default constructor */
    public AtcClienteRetorno() {
    }

    /** minimal constructor */
    public AtcClienteRetorno(AtcClienteRetornoPK comp_id, int atcCliTipoPessoa) {
        this.comp_id = comp_id;
        this.atcCliTipoPessoa = atcCliTipoPessoa;
    }

    public AtcClienteRetornoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(AtcClienteRetornoPK comp_id) {
        this.comp_id = comp_id;
    }

    public String getAtcCliCnpj() {
        return this.atcCliCnpj;
    }

    public void setAtcCliCnpj(String atcCliCnpj) {
        this.atcCliCnpj = atcCliCnpj;
    }

    public String getAtcCliEmail() {
        return this.atcCliEmail;
    }

    public void setAtcCliEmail(String atcCliEmail) {
        this.atcCliEmail = atcCliEmail;
    }

    public String getAtcCliNomeFantasia() {
        return this.atcCliNomeFantasia;
    }

    public void setAtcCliNomeFantasia(String atcCliNomeFantasia) {
        this.atcCliNomeFantasia = atcCliNomeFantasia;
    }

    public String getAtcCliNomeReceita() {
        return this.atcCliNomeReceita;
    }

    public void setAtcCliNomeReceita(String atcCliNomeReceita) {
        this.atcCliNomeReceita = atcCliNomeReceita;
    }

    public String getAtcCliCpf() {
        return this.atcCliCpf;
    }

    public void setAtcCliCpf(String atcCliCpf) {
        this.atcCliCpf = atcCliCpf;
    }

    public String getAtcCliRg() {
        return this.atcCliRg;
    }

    public void setAtcCliRg(String atcCliRg) {
        this.atcCliRg = atcCliRg;
    }

    public String getAtcCliDataExpedicao() {
        return this.atcCliDataExpedicao;
    }

    public void setAtcCliDataExpedicao(String atcCliDataExpedicao) {
        this.atcCliDataExpedicao = atcCliDataExpedicao;
    }

    public String getAtcCliDataNascimento() {
        return this.atcCliDataNascimento;
    }

    public void setAtcCliDataNascimento(String atcCliDataNascimento) {
        this.atcCliDataNascimento = atcCliDataNascimento;
    }

    public Integer getAtcOrgId() {
        return this.atcOrgId;
    }

    public void setAtcOrgId(Integer atcOrgId) {
        this.atcOrgId = atcOrgId;
    }

    public Integer getAtcUnfId() {
        return this.atcUnfId;
    }

    public void setAtcUnfId(Integer atcUnfId) {
        this.atcUnfId = atcUnfId;
    }

    public Integer getAtcProId() {
        return this.atcProId;
    }

    public void setAtcProId(Integer atcProId) {
        this.atcProId = atcProId;
    }

    public Integer getAtcRamId() {
        return this.atcRamId;
    }

    public void setAtcRamId(Integer atcRamId) {
        this.atcRamId = atcRamId;
    }

    public Integer getAtcCltId() {
        return this.atcCltId;
    }

    public void setAtcCltId(Integer atcCltId) {
        this.atcCltId = atcCltId;
    }

    public int getAtcCliTipoPessoa() {
        return this.atcCliTipoPessoa;
    }

    public void setAtcCliTipoPessoa(int atcCliTipoPessoa) {
        this.atcCliTipoPessoa = atcCliTipoPessoa;
    }

    public Integer getAtcCliSexo() {
        return this.atcCliSexo;
    }

    public void setAtcCliSexo(Integer atcCliSexo) {
        this.atcCliSexo = atcCliSexo;
    }

    public String getAtcCliNomeMae() {
        return this.atcCliNomeMae;
    }

    public void setAtcCliNomeMae(String atcCliNomeMae) {
        this.atcCliNomeMae = atcCliNomeMae;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof AtcClienteRetorno) ) return false;
        AtcClienteRetorno castOther = (AtcClienteRetorno) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}

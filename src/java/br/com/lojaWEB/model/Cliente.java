package br.com.lojaWEB.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
    private String email;
    private String numero;
    private String complemento;
    private String pws;
    private String foto;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco = new Endereco();

    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataNasc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.lojaWEB.model.Cliente[ id=" + id + " ]";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPws() {
        return pws;
    }

    public void setPws(String pws) {
        this.pws = pws;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Calendar getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Calendar dataNasc) {
        this.dataNasc = dataNasc;
    }

    //Validação
    private String isDados() {
        String erros = "";
        if (nome.equals("")) {
            erros += "Nome em branco.\n";
        }
        if (email.equals("")) {
            erros += "E-mail em branco.\n";
        }

        if (!dataNasc.isWeekDateSupported()) {
            erros += "Data Nasc. em branco.\n";
        }

        if (numero.equals("")) {
            erros += "Numero em branco.\n";
        }
        return erros;
    }

    private String isSenha(String confirmSenha) {
        String erros = "";
        if (pws.equals("")) {
            erros += "Senha em branco.\n";
        } else if (pws.length() < 5) {
            erros += "Senha muito curta. Minimo de 6 caracteres.\n";
        } else if (!pws.equals(confirmSenha)) {
            erros += "Senhas diferentes.\n";
        }

        return erros;
    }

    public boolean isCliente(String pws) throws Exception {
        String erros = isDados() + isSenha(pws) + endereco.isDados();
        if (!erros.equals("")) {
            throw new Exception(erros);
        }
        return true;
    }

    public boolean isCliente() throws Exception {
        String erros = isDados() + endereco.isDados();
        if (!erros.equals("")) {
            throw new Exception(erros);
        }
        return true;
    }

    public boolean isPassword(String confirmaSenha) throws Exception {
        String erros = isSenha(confirmaSenha);
        if (!erros.equals("")) {
            throw new Exception(erros);
        }
        return true;
    }
}

package org.serratec.Aula03.Domain;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class PessoaBase {
    
    private String telefone;
    private String endereco;
    private Boolean ativo;

    public PessoaBase() {
    }

    public PessoaBase(String telefone, String endereco, Boolean ativo) {
        this.telefone = telefone;
        this.endereco = endereco;
        this.ativo = ativo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }


}

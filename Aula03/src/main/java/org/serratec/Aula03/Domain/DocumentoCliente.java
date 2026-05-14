package org.serratec.Aula03.Domain;

import org.serratec.Aula03.enumerated.StatusCliente;
import org.serratec.Aula03.enumerated.TipoCliente;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class DocumentoCliente {
    
    @NotBlank(message = "CPF ou CNPJ é obrigatório")
    @Column(name = "cpf_ou_cnpj", nullable = false, length = 20)
    private String cpfOuCnpj;
    
    @NotBlank(message = "RG ou Inscrição Estadual é obrigatório")
    @Column(name = "rg_inscricao_estadual", nullable = false, length = 20)
    private String rgInscricaoEstadual;
    
    @Enumerated(EnumType.STRING)
     @Column(name = "tipo_cliente")
     private TipoCliente tipoCliente;
     
     @NotNull(message = "Status do cliente é obrigatório")
     @Enumerated(EnumType.ORDINAL)
     @Column(name = "status_cliente")
     private StatusCliente statusCliente;

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public String getRgInscricaoEstadual() {
        return rgInscricaoEstadual;
    }

    public void setRgInscricaoEstadual(String rgInscricaoEstadual) {
        this.rgInscricaoEstadual = rgInscricaoEstadual;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public StatusCliente getStatusCliente() {
        return statusCliente;
    }

    public void setStatusCliente(StatusCliente statusCliente) {
        this.statusCliente = statusCliente;
    }

    
}

package org.serratec.Aula03.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cliente_vip")
@DiscriminatorValue("VIP")
@PrimaryKeyJoinColumn(name = "id_cliente")
public class ClienteVip extends Cliente {

    @NotBlank(message = "Consultor responsável é obrigatório")
    @Size(max = 100, message = "Consultor responsável deve conter no máximo 100 caracteres")
    @Column(name = "consultor_responsavel", length = 100, nullable = false)
    private String consultorResponsavel;

    public ClienteVip() {
    }

    public String getConsultorResponsavel() {
        return consultorResponsavel;
    }

    public void setConsultorResponsavel(String consultorResponsavel) {
        this.consultorResponsavel = consultorResponsavel;
    }
}

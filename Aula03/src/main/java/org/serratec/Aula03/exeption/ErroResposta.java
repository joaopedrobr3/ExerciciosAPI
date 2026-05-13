package org.serratec.Aula03.exeption;

import java.time.LocalDate;
import java.util.List;

public class ErroResposta {
   
    private String mensagem;
    private int status;
    private LocalDate dataHora;
    private List<String> erros;

    

    public ErroResposta(String mensagem, int status) {
        this.mensagem = mensagem;
        this.status = status;

    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

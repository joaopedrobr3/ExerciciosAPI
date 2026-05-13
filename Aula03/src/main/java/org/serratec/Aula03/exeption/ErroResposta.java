package org.serratec.Aula03.exeption;


import java.time.LocalDateTime;
import java.util.List;

public class ErroResposta {
   
    private String titulo;
    private int status;
    private LocalDateTime dataHora;
    private List<String> erros;
    
    public ErroResposta(String titulo, int status, LocalDateTime dataHora, List<String> erros) {
        this.titulo = titulo;
        this.status = status;
        this.dataHora = dataHora;
        this.erros = erros;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }

    
    
    
}

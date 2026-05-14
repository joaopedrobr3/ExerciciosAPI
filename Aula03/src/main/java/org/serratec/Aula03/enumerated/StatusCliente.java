package org.serratec.Aula03.enumerated;

import org.serratec.Aula03.exeption.EnumValidationException;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatusCliente {
   ATIVO ,
   INATIVO,
   BLOQUEADO;



   @JsonCreator
public static StatusCliente verifica(String value) throws EnumValidationException{
    for (StatusCliente status : values()) {
        if (value.equals(status.name())) {
            return status;
        }
    }
    throw new EnumValidationException("Status preenchido incorretamente! Opções válidas: ATIVO, INATIVO, BLOQUEADO");
}



}

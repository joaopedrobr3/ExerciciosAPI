package org.serratec.Aula03.enumerated;

import org.serratec.Aula03.exeption.EnumValidationException;

import com.fasterxml.jackson.annotation.JsonCreator;

public  enum TipoCliente {
    PJ,
    PF;

@JsonCreator
public static TipoCliente verifica(String value) throws EnumValidationException{
    for(TipoCliente tipo : TipoCliente.values()){
        if(value.equals(tipo.name())){
            return tipo;
        }
    }
    throw new EnumValidationException("Tipo de cliente inválido! Valores aceitos: PJ, PF");
}
}
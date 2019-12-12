package com.pe.ventas.back.dtos.rest.base;

import java.io.Serializable;

import com.pe.ventas.back.dtos.base.JsonDto;
import com.pe.ventas.back.dtos.rest.base.ResultResponse.Builder;

import lombok.Getter;

@Getter
public class UsuarioResponse implements Serializable {

	private static final long serialVersionUID = -6166392272525761290L;
	
    private final String code;
    private final Object usuario;
    private final String message;
    
    private UsuarioResponse(final Builder builder) {
        code = builder.code;
        usuario = builder.usuario;
        message = builder.message;
    }
    
    public String aJson() {
        return JsonDto.aJson(this);
    }

    @Override
    public String toString() {
        return aJson();
    }
    
    public static class Builder {
        private String code;
        private Object usuario;
        private String message;

        public Builder() {
            code = "200";
            message = "successful";
        }

        public Builder message(final String message) {
            this.message = message;
            return this;
        }

        public Builder code(final String code) {
            this.code = code;
            return this;
        }

        public Builder code(final Integer code) {
            this.code = code.toString();
            return this;
        }

        public Builder usuario(final Object usuario) {
            this.usuario = usuario;
            return this;
        }

        public UsuarioResponse build() {
            return new UsuarioResponse(this);
        }
    }

}

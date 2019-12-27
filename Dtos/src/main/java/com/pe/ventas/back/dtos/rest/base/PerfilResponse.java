package com.pe.ventas.back.dtos.rest.base;

import java.io.Serializable;
import com.pe.ventas.back.dtos.base.JsonDto;
import lombok.Getter;

@Getter
public class PerfilResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1531076571103622167L;
	
    private final String code;
    private final Object perfil;
    private final String message;
    
    private PerfilResponse(final Builder builder) {
        code = builder.code;
        perfil = builder.perfil;
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
        private Object perfil;
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

        public Builder perfil(final Object perfil) {
            this.perfil = perfil;
            return this;
        }

        public PerfilResponse build() {
            return new PerfilResponse(this);
        }
    }    
    
}

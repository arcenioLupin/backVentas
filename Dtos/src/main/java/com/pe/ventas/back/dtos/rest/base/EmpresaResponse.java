package com.pe.ventas.back.dtos.rest.base;

import java.io.Serializable;
import com.pe.ventas.back.dtos.base.JsonDto;
import lombok.Getter;

@Getter
public class EmpresaResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private final String code;
    private final Object empresa;
    private final String message;
    
    private EmpresaResponse(final Builder builder) {
        code = builder.code;
        empresa = builder.empresa;
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
        private Object empresa;
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

        public Builder empresa(final Object empresa) {
            this.empresa = empresa;
            return this;
        }

        public EmpresaResponse build() {
            return new EmpresaResponse(this);
        }
    }    
    
}

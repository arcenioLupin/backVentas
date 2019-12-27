package com.pe.ventas.back.dtos.rest.base;

import java.io.Serializable;

import com.pe.ventas.back.dtos.base.JsonDto;
import lombok.Getter;

@Getter
public class RegimenLaboralResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -326571764805009414L;
	
    private final String code;
    private final Object regimenLaboral;
    private final String message;
    
    private RegimenLaboralResponse(final Builder builder) {
        code = builder.code;
        regimenLaboral = builder.regimenLaboral;
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
        private Object regimenLaboral;
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

        public Builder regimenLaboral(final Object regimenLaboral) {
            this.regimenLaboral = regimenLaboral;
            return this;
        }

        public RegimenLaboralResponse build() {
            return new RegimenLaboralResponse(this);
        }
    }    
    
    	
}

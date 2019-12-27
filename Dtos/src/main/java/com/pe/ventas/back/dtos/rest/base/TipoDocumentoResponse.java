package com.pe.ventas.back.dtos.rest.base;

import java.io.Serializable;
import com.pe.ventas.back.dtos.base.JsonDto;
import lombok.Getter;

@Getter
public class TipoDocumentoResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3194677264145043573L;
	
    private final String code;
    private final Object tipoDocumento;
    private final String message;
    
    private TipoDocumentoResponse(final Builder builder) {
        code = builder.code;
        tipoDocumento = builder.tipoDocumento;
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
	        private Object tipoDocumento;
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

	        public Builder tipoDocumento(final Object tipoDocumento) {
	            this.tipoDocumento = tipoDocumento;
	            return this;
	        }

	        public TipoDocumentoResponse build() {
	            return new TipoDocumentoResponse(this);
	        }
	    }  	    
}

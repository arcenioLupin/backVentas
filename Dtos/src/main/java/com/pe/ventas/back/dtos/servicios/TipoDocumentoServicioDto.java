package com.pe.ventas.back.dtos.servicios;

import java.io.Serializable;
import java.util.Date;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TipoDocumentoServicioDto implements Serializable {
	
	private static final long serialVersionUID = 8787302421398708029L;
	private Integer tipoDocId;
	private String tipoDocDesc;
	private Date tipoDocFechaCreacion;
	private Date tipoDocFechaMod;
	private String tipoDocUsuCreacion;
	private String tipoDocUsuMod;
	
    public String aJson() {
        return JsonDto.aJson(this);
    }

}

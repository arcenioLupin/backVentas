package com.pe.ventas.back.dtos.daos.dao;

import java.io.Serializable;
import java.util.Date;
import com.pe.ventas.back.dtos.base.JsonDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PerfilDaoDto implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -7012649790518271793L;
	private Integer perfilId;
	private String perfilDescripcion;
	private String perfilActivo;
	private Date perfilFechaCreacion;
	private Date perfilFechaMod;
	private String perfilUsuCreacion;
	private String perfilUsuMod;
	
    public String aJson() {
        return JsonDto.aJson(this);
    }

}

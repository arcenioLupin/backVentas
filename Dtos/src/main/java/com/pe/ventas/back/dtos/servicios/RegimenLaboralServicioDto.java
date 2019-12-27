package com.pe.ventas.back.dtos.servicios;

import java.io.Serializable;
import java.util.Date;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegimenLaboralServicioDto implements Serializable   {/**
	 * 
	 */
	private static final long serialVersionUID = 7100460916942317679L;

	private Integer regLaboId;
	private String  regLaboTipo;
	private String  regLaboJld;
	private float   regLaboPhe;
	private String  regLaboUsuCrea;
	private String  regLaboUsuMod;	
	private Date    regLaboFecCrea;
	private Date    regLaboFecMod;
	
	  public String aJson() {
	       return JsonDto.aJson(this);
	    }
}

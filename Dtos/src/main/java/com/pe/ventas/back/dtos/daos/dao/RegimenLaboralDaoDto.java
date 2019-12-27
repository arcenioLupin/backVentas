package com.pe.ventas.back.dtos.daos.dao;

import java.io.Serializable;
import java.util.Date;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegimenLaboralDaoDto implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -2324536846795906616L;
	
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

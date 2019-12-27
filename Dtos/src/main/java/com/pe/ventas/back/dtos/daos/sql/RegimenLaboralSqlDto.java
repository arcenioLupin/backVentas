package com.pe.ventas.back.dtos.daos.sql;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegimenLaboralSqlDto implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -8309468708388443175L;
	
	private Integer regLaboId;
	private String  regLaboTipo;
	private String  regLaboJld;
	private float   regLaboPhe;
	private String  regLaboUsuCrea;
	private String  regLaboUsuMod;
	private Date    regLaboFecCrea;
	private Date    regLaboFecMod;

}

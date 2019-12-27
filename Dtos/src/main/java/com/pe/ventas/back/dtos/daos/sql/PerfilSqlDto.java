package com.pe.ventas.back.dtos.daos.sql;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PerfilSqlDto implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -4079974785104974646L;
	
	private Integer perfilId;
	private String perfilDescripcion;
	private String perfilActivo;
	private Date perfilFechaCreacion;
	private Date perfilFechaMod;
	private String perfilUsuCreacion;
	private String perfilUsuMod;

}

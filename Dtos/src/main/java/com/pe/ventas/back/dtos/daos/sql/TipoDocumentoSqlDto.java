package com.pe.ventas.back.dtos.daos.sql;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TipoDocumentoSqlDto implements Serializable {
	
	private static final long serialVersionUID = -77943450629349650L;

	private Integer tipoDocId;
	private String tipoDocDesc;
	private Date tipoDocFechaCreacion;
	private Date tipoDocFechaMod;
	private String tipoDocUsuCreacion;
	private String tipoDocUsuMod;
}

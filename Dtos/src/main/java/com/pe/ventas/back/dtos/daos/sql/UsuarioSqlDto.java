package com.pe.ventas.back.dtos.daos.sql;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioSqlDto implements Serializable {

    private static final long serialVersionUID = -6638350426618012372L;

	private String usuarioId;
	private String usuarioNombres;
	private String usuarioApePaterno;
	private String usuarioApeMaterno;
	private String usuarioPassword;
	private String usuarioActivo;
	private Date   usuarioFechaInicio;
	private Date   usuarioFechaFin;
	private Date   usuarioAcceso;
	private String usuarioEmail;
	private String usuarioTelefono;
	private String usuarioMovil;
	private String usuarioTelefono2;
	private String usuarioNumAdmin;
	private String usuarioUser;
	private String usuarioNumDoc;
	private String tipoDocId;
}

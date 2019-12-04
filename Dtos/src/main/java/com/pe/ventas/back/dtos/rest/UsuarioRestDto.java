package com.pe.ventas.back.dtos.rest;

import java.io.Serializable;
import java.util.Date;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioRestDto implements Serializable {

    private static final long serialVersionUID = 605689904521722810L;

	private Integer usuarioId;
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
	private Integer tipoDocId;
	private Date usuarioFechaMod;
	private Date usuarioFechaCreacion;
	private String usuarioUsuMod;
	private String usuarioUsuCrea;


    public String aJson() {
        return JsonDto.aJson(this);
    }

}

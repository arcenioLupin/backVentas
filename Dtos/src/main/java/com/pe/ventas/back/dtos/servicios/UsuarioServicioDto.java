package com.pe.ventas.back.dtos.servicios;

import java.io.Serializable;
import java.util.Date;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioServicioDto implements Serializable {

    private static final long serialVersionUID = 3295452824284665006L;
    
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


    public String aJson() {
        return JsonDto.aJson(this);
    }

}

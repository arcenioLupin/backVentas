package com.pe.ventas.back.servicios;

import java.util.List;

import com.pe.ventas.back.dtos.servicios.UsuarioServicioDto;

public interface IUsuarioServicio {

	UsuarioServicioDto autenticar(UsuarioServicioDto usuario);

    Boolean actualizar(UsuarioServicioDto usuario);
    
    Boolean eliminar(UsuarioServicioDto usuario);
    
    Boolean insertarUsuario(final UsuarioServicioDto usuario);
    
    List<UsuarioServicioDto> todosLosUsuarios();
    
    UsuarioServicioDto obtenerUnUsuario(UsuarioServicioDto usuario);
    
    void limpiarCache();

}

package com.pe.ventas.back.servicios;

import java.util.List;

import com.pe.ventas.back.dtos.servicios.PerfilServicioDto;

public interface IPerfilServicio {

    Boolean actualizar(PerfilServicioDto perfil);
    
    Boolean eliminar(PerfilServicioDto perfil);
    
    Boolean insertarPerfil(final PerfilServicioDto perfil);
    
    List<PerfilServicioDto> todosLosPerfiles();
    
    PerfilServicioDto obtenerUnPerfil(PerfilServicioDto perfil);
    
    void limpiarCache();

}

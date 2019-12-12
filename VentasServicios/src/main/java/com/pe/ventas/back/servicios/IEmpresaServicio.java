package com.pe.ventas.back.servicios;

import java.util.List;

import com.pe.ventas.back.dtos.servicios.EmpresaServicioDto;

public interface IEmpresaServicio {

    Boolean actualizar(EmpresaServicioDto empresa);
    
    Boolean eliminar(EmpresaServicioDto empresa);
    
    Boolean insertarEmpresa(final EmpresaServicioDto empresa);
    
    List<EmpresaServicioDto> todasLasEmpresas();
    
    EmpresaServicioDto obtenerUnaEmpresa(EmpresaServicioDto empresa);
    
    void limpiarCache();
		
}

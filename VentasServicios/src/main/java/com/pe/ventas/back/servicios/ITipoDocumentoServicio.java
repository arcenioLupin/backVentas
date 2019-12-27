package com.pe.ventas.back.servicios;

import java.util.List;

import com.pe.ventas.back.dtos.servicios.TipoDocumentoServicioDto;

public interface ITipoDocumentoServicio {
	
    Boolean actualizar(TipoDocumentoServicioDto tipoDocumento);
    
    Boolean eliminar(TipoDocumentoServicioDto tipoDocumento);
    
    Boolean insertarTipoDocumento(final TipoDocumentoServicioDto tipoDocumento);
    
    List<TipoDocumentoServicioDto> todosLosTipoDocumentos();
    
    TipoDocumentoServicioDto obtenerUnTipoDocumento(TipoDocumentoServicioDto tipoDocumento);
    
    void limpiarCache();	

}

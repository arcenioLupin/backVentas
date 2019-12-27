package com.pe.ventas.back.servicios;

import java.util.List;

import com.pe.ventas.back.dtos.servicios.RegimenLaboralServicioDto;

public interface IRegimenLaboralServicio {
	
    Boolean actualizar(RegimenLaboralServicioDto regimenLaboral);
    
    Boolean eliminar(RegimenLaboralServicioDto regimenLaboral);
    
    Boolean insertarRegimenLaboral(final RegimenLaboralServicioDto regimenLaboral);
    
    List<RegimenLaboralServicioDto> todasLosRegimenLaboral();
    
    RegimenLaboralServicioDto obtenerUnRegimenLaboral(RegimenLaboralServicioDto regimenLaboral);
    
    void limpiarCache();	

}

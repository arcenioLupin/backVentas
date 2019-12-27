package com.pe.ventas.back.utilidades.mapeos;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.pe.ventas.back.dtos.daos.dao.RegimenLaboralDaoDto;
import com.pe.ventas.back.dtos.daos.sql.RegimenLaboralSqlDto;
import com.pe.ventas.back.dtos.rest.RegimenLaboralRestDto;
import com.pe.ventas.back.dtos.servicios.RegimenLaboralServicioDto;

@Mapper
public interface RegimenLaboralDtoMaper {
	   RegimenLaboralDtoMaper INSTANCE = Mappers.getMapper(RegimenLaboralDtoMaper.class);


	    RegimenLaboralDaoDto RegimenLaboralSqlDtoARegimenLaboralDaoDto(RegimenLaboralSqlDto regimenLaboral);
	    RegimenLaboralSqlDto RegimenLaboralDaoDtoARegimenLaboralSqlDto(RegimenLaboralDaoDto regimenLaboral);

	    
	    RegimenLaboralDaoDto RegimenLaboralServicioDtoARegimenLaboralDaoDto(RegimenLaboralServicioDto regimenLaboral);  
	    RegimenLaboralServicioDto RegimenLaboralDaoDtoARegimenLaboralServicioDto(RegimenLaboralDaoDto regimenLaboral);
	    

	    RegimenLaboralServicioDto RegimenLaboralRestDtoARegimenLaboralServicioDto(RegimenLaboralRestDto regimenLaboral);
	    RegimenLaboralRestDto RegimenLaboralServicioDtoARegimenLaboralRestDto(RegimenLaboralServicioDto regimenLaboral);

	   
	    List<RegimenLaboralRestDto> convertirListaRegimenLaboralServicioDtoARegimenLaboralRestDto(List<RegimenLaboralServicioDto> regimenLaboral);

	    List<RegimenLaboralDaoDto> convertirListaRegimenLaboralSqlDtoARegimenLaboralDaoDto(List<RegimenLaboralSqlDto> regimenLaboral);

	    List<RegimenLaboralSqlDto> convertirListaRegimenLaboralDaoDtoARegimenLaboralSqlDto(List<RegimenLaboralDaoDto> regimenLaboral);

	    List<RegimenLaboralServicioDto> convertirListaRegimenLaboralDaoDtoARegimenLaboralServicioDto(List<RegimenLaboralDaoDto> regimenLaboral);		

}

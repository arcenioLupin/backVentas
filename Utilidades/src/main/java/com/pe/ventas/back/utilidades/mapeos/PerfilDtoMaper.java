package com.pe.ventas.back.utilidades.mapeos;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.pe.ventas.back.dtos.daos.dao.PerfilDaoDto;
import com.pe.ventas.back.dtos.daos.sql.PerfilSqlDto;
import com.pe.ventas.back.dtos.rest.PerfilRestDto;
import com.pe.ventas.back.dtos.servicios.PerfilServicioDto;

@Mapper
public interface PerfilDtoMaper {

	PerfilDtoMaper INSTANCE = Mappers.getMapper(PerfilDtoMaper.class);


    PerfilDaoDto PerfilSqlDtoAPerfilDaoDto(PerfilSqlDto perfil);
    PerfilSqlDto PerfilDaoDtoAPerfilSqlDto(PerfilDaoDto perfil);

    
    PerfilDaoDto PerfilServicioDtoAPerfilDaoDto(PerfilServicioDto perfil);  
    PerfilServicioDto PerfilDaoDtoAPerfilServicioDto(PerfilDaoDto perfil);
    

    PerfilServicioDto PerfilRestDtoAPerfilServicioDto(PerfilRestDto perfil);
    PerfilRestDto PerfilServicioDtoAPerfilRestDto(PerfilServicioDto perfil);

   

    List<PerfilRestDto> convertirListaPerfilServicioDtoAPerfilRestDto(List<PerfilServicioDto> perfil);

    List<PerfilDaoDto> convertirListaPerfilSqlDtoAPerfilDaoDto(List<PerfilSqlDto> perfil);

    List<PerfilSqlDto> convertirListaPerfilDaoDtoAPerfilSqlDto(List<PerfilDaoDto> perfil);

    List<PerfilServicioDto> convertirListaPerfilDaoDtoAPerfilServicioDto(List<PerfilDaoDto> perfil);

}

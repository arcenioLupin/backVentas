package com.pe.ventas.back.utilidades.mapeos;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.pe.ventas.back.dtos.daos.dao.UsuarioDaoDto;
import com.pe.ventas.back.dtos.daos.sql.UsuarioSqlDto;
import com.pe.ventas.back.dtos.rest.UsuarioRestDto;
import com.pe.ventas.back.dtos.servicios.UsuarioServicioDto;

@Mapper
public interface UsuarioDtoMaper {

    UsuarioDtoMaper INSTANCE = Mappers.getMapper(UsuarioDtoMaper.class);


    UsuarioDaoDto usuarioSqlDtoAUsuarioDaoDto(UsuarioSqlDto usuario);
    UsuarioSqlDto usuarioDaoDtoAUsuarioSqlDto(UsuarioDaoDto usuario);

    
    UsuarioDaoDto usuarioServicioDtoAUsuarioDaoDto(UsuarioServicioDto usuario);  
    UsuarioServicioDto usuarioDaoDtoAUsuarioServicioDto(UsuarioDaoDto usuario);
    

    UsuarioServicioDto usuarioRestDtoAUsuarioServicioDto(UsuarioRestDto usuario);
    
    @Mapping(target = "usuarioPassword", ignore = true)
    UsuarioRestDto usuarioServicioDtoAUsuarioRestDto(UsuarioServicioDto usuario);

   

    List<UsuarioRestDto> convertirListaUsuarioServicioDtoAUsuarioRestDto(List<UsuarioServicioDto> usuario);

    List<UsuarioDaoDto> convertirListaUsuarioSqlDtoAUsuarioDaoDto(List<UsuarioSqlDto> usuario);

    List<UsuarioSqlDto> convertirListaUsuarioDaoDtoAUsuarioSqlDto(List<UsuarioDaoDto> usuario);

    List<UsuarioServicioDto> convertirListaUsuarioDaoDtoAUsuarioServicioDto(List<UsuarioDaoDto> usuario);

}

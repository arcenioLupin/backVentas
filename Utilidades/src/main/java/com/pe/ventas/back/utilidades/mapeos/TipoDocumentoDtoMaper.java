package com.pe.ventas.back.utilidades.mapeos;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.pe.ventas.back.dtos.daos.dao.TipoDocumentoDaoDto;
import com.pe.ventas.back.dtos.daos.sql.TipoDocumentoSqlDto;
import com.pe.ventas.back.dtos.rest.TipoDocumentoRestDto;
import com.pe.ventas.back.dtos.servicios.TipoDocumentoServicioDto;

@Mapper
public interface TipoDocumentoDtoMaper {
	
	TipoDocumentoDtoMaper INSTANCE = Mappers.getMapper(TipoDocumentoDtoMaper.class);


    TipoDocumentoDaoDto TipoDocumentoSqlDtoATipoDocumentoDaoDto(TipoDocumentoSqlDto tipoDocumento);
    TipoDocumentoSqlDto TipoDocumentoDaoDtoATipoDocumentoSqlDto(TipoDocumentoDaoDto tipoDocumento);

    
    TipoDocumentoDaoDto TipoDocumentoServicioDtoATipoDocumentoDaoDto(TipoDocumentoServicioDto tipoDocumento);  
    TipoDocumentoServicioDto TipoDocumentoDaoDtoATipoDocumentoServicioDto(TipoDocumentoDaoDto tipoDocumento);
    

    TipoDocumentoServicioDto TipoDocumentoRestDtoATipoDocumentoServicioDto(TipoDocumentoRestDto tipoDocumento);
    TipoDocumentoRestDto TipoDocumentoServicioDtoATipoDocumentoRestDto(TipoDocumentoServicioDto tipoDocumento);

   

    List<TipoDocumentoRestDto> convertirListaTipoDocumentoServicioDtoATipoDocumentoRestDto(List<TipoDocumentoServicioDto> tipoDocumento);

    List<TipoDocumentoDaoDto> convertirListaTipoDocumentoSqlDtoATipoDocumentoDaoDto(List<TipoDocumentoSqlDto> tipoDocumento);

    List<TipoDocumentoSqlDto> convertirListaTipoDocumentoDaoDtoATipoDocumentoSqlDto(List<TipoDocumentoDaoDto> tipoDocumento);

    List<TipoDocumentoServicioDto> convertirListaTipoDocumentoDaoDtoATipoDocumentoServicioDto(List<TipoDocumentoDaoDto> tipoDocumento);

}

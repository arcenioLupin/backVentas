package com.pe.ventas.back.daos.sql.mapeos;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pe.ventas.back.dtos.daos.sql.TipoDocumentoSqlDto;

@Mapper
public interface TipoDocumentoSqlMaper {
	
    public List<TipoDocumentoSqlDto> selectTodosTipoDocumentos();

    public TipoDocumentoSqlDto selectUnTipoDocumento(TipoDocumentoSqlDto tipoDocumento);   
 
    public Integer insert(TipoDocumentoSqlDto tipoDocumento);
 
    public Integer update(TipoDocumentoSqlDto tipoDocumento);

    public Integer delete(TipoDocumentoSqlDto tipoDocumento);

}

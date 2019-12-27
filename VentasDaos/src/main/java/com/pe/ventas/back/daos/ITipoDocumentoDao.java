package com.pe.ventas.back.daos;

import java.util.List;

import com.pe.ventas.back.dtos.daos.dao.TipoDocumentoDaoDto;

public interface ITipoDocumentoDao {
	
    List<TipoDocumentoDaoDto> obtenerTodos();

    TipoDocumentoDaoDto obtenerUnTipoDocumento(TipoDocumentoDaoDto tipoDocumento);

    TipoDocumentoDaoDto crear(TipoDocumentoDaoDto tipoDocumento);

    Boolean actualizar(TipoDocumentoDaoDto tipoDocumento);

    Boolean eliminar(TipoDocumentoDaoDto tipoDocumento);

    void limpiarCache();		

}

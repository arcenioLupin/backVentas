package com.pe.ventas.back.daos.impl;

import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pe.ventas.back.daos.ITipoDocumentoDao;
import com.pe.ventas.back.daos.sql.mapeos.TipoDocumentoSqlMaper;
import com.pe.ventas.back.dtos.daos.dao.TipoDocumentoDaoDto;
import com.pe.ventas.back.dtos.daos.sql.TipoDocumentoSqlDto;
import com.pe.ventas.back.utilidades.mapeos.TipoDocumentoDtoMaper;

@Repository("tipoDocumentoDao")
public class TipoDocumentoDao implements ITipoDocumentoDao {

	private static final Logger LOG = LogManager.getLogger(TipoDocumentoDao.class);

	@Autowired
    private TipoDocumentoSqlMaper tipoDocumentoSqlMaper;

    @Override
    @Cacheable(value = "tipoDocumento")
    @Transactional(readOnly = true)
    public List<TipoDocumentoDaoDto> obtenerTodos() {
        final List<TipoDocumentoSqlDto> listaTipoDocumento = tipoDocumentoSqlMaper.selectTodosTipoDocumentos();
        return TipoDocumentoDtoMaper.INSTANCE.convertirListaTipoDocumentoSqlDtoATipoDocumentoDaoDto(listaTipoDocumento);
    }

    @Override
    @CachePut(value = "tipoDocumento")
    @Transactional
    public TipoDocumentoDaoDto crear(final TipoDocumentoDaoDto tipoDocumento) {
        final TipoDocumentoSqlDto tipoDocumentoSqlDto = TipoDocumentoDtoMaper.INSTANCE.TipoDocumentoDaoDtoATipoDocumentoSqlDto(tipoDocumento);
        final Integer resultado = tipoDocumentoSqlMaper.insert(tipoDocumentoSqlDto);

        if ((resultado != null) && BooleanUtils.toBoolean(resultado)) {
            return TipoDocumentoDtoMaper.INSTANCE.TipoDocumentoSqlDtoATipoDocumentoDaoDto(tipoDocumentoSqlDto);
        }

        return null;
    }

    @Override
    @CachePut(value = "tipoDocumento")
    @Transactional
    public Boolean actualizar(final TipoDocumentoDaoDto tipoDocumento) {
        final TipoDocumentoSqlDto tipoDocumentoSqlDto = TipoDocumentoDtoMaper.INSTANCE.TipoDocumentoDaoDtoATipoDocumentoSqlDto(tipoDocumento);
        LOG.debug(tipoDocumentoSqlDto);
        final Integer resultado = tipoDocumentoSqlMaper.update(tipoDocumentoSqlDto);
        return (resultado != null) && (resultado > 0) ? true : false;
    }

    @Override
    @CacheEvict(value = "tipoDocumento", key = "#tipoDocumento.tipoDocId")
    @Transactional
    public Boolean eliminar(final TipoDocumentoDaoDto tipoDocumento) {
        final TipoDocumentoSqlDto tipoDocumentoSqlDto = TipoDocumentoDtoMaper.INSTANCE.TipoDocumentoDaoDtoATipoDocumentoSqlDto(tipoDocumento);
        final Integer resultado = tipoDocumentoSqlMaper.delete(tipoDocumentoSqlDto);
        return (resultado != null) && (resultado > 0) ? true : false;
    }

    @Override
    @Cacheable(value = "tipoDocumento")
    @Transactional(readOnly = true)
    public TipoDocumentoDaoDto obtenerUnTipoDocumento(final TipoDocumentoDaoDto tipoDocumento) {
        LOG.debug("Obtener TipoDocumento: TipoDocumentoDaoDto: " + tipoDocumento);
        TipoDocumentoSqlDto tipoDocumentoSqlDto = TipoDocumentoDtoMaper.INSTANCE.TipoDocumentoDaoDtoATipoDocumentoSqlDto(tipoDocumento);
        tipoDocumentoSqlDto = tipoDocumentoSqlMaper.selectUnTipoDocumento(tipoDocumentoSqlDto);
        return TipoDocumentoDtoMaper.INSTANCE.TipoDocumentoSqlDtoATipoDocumentoDaoDto(tipoDocumentoSqlDto);
    }

    @Override
    @CacheEvict(value = "tipoDocumento", allEntries = true)
    public void limpiarCache() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Se ejecuto la limpieza del cache TipoDocumento");
        }
    }


}

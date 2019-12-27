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

import com.pe.ventas.back.daos.IPerfilDao;
import com.pe.ventas.back.daos.sql.mapeos.PerfilSqlMaper;
import com.pe.ventas.back.dtos.daos.dao.PerfilDaoDto;
import com.pe.ventas.back.dtos.daos.sql.PerfilSqlDto;
import com.pe.ventas.back.utilidades.mapeos.PerfilDtoMaper;

@Repository("perfilDao")
public class PerfilDao implements IPerfilDao {
	
	private static final Logger LOG = LogManager.getLogger(PerfilDao.class);

	@Autowired
    private PerfilSqlMaper perfilSqlMaper;

    @Override
    @Cacheable(value = "perfil")
    @Transactional(readOnly = true)
    public List<PerfilDaoDto> obtenerTodos() {
        final List<PerfilSqlDto> listaPerfil = perfilSqlMaper.selectTodosPerfiles();
        return PerfilDtoMaper.INSTANCE.convertirListaPerfilSqlDtoAPerfilDaoDto(listaPerfil);
    }

    @Override
    @CachePut(value = "perfil")
    @Transactional
    public PerfilDaoDto crear(final PerfilDaoDto perfil) {
        final PerfilSqlDto perfilSqlDto = PerfilDtoMaper.INSTANCE.PerfilDaoDtoAPerfilSqlDto(perfil);
        final Integer resultado = perfilSqlMaper.insert(perfilSqlDto);

        if ((resultado != null) && BooleanUtils.toBoolean(resultado)) {
            return PerfilDtoMaper.INSTANCE.PerfilSqlDtoAPerfilDaoDto(perfilSqlDto);
        }

        return null;
    }

    @Override
    @CachePut(value = "perfil")
    @Transactional
    public Boolean actualizar(final PerfilDaoDto perfil) {
        final PerfilSqlDto perfilSqlDto = PerfilDtoMaper.INSTANCE.PerfilDaoDtoAPerfilSqlDto(perfil);
        LOG.debug(perfilSqlDto);
        final Integer resultado = perfilSqlMaper.update(perfilSqlDto);
        return (resultado != null) && (resultado > 0) ? true : false;
    }

    @Override
    @CacheEvict(value = "perfil", key = "#perfil.perfilId")
    @Transactional
    public Boolean eliminar(final PerfilDaoDto perfil) {
        final PerfilSqlDto perfilSqlDto = PerfilDtoMaper.INSTANCE.PerfilDaoDtoAPerfilSqlDto(perfil);
        final Integer resultado = perfilSqlMaper.delete(perfilSqlDto);
        return (resultado != null) && (resultado > 0) ? true : false;
    }

    @Override
    @Cacheable(value = "perfil")
    @Transactional(readOnly = true)
    public PerfilDaoDto obtenerUnPerfil(final PerfilDaoDto perfil) {
        LOG.debug("Obtener Perfil: PerfilDaoDto: " + perfil);
        PerfilSqlDto perfilSqlDto = PerfilDtoMaper.INSTANCE.PerfilDaoDtoAPerfilSqlDto(perfil);
        perfilSqlDto = perfilSqlMaper.selectUnPerfil(perfilSqlDto);
        return PerfilDtoMaper.INSTANCE.PerfilSqlDtoAPerfilDaoDto(perfilSqlDto);
    }

    @Override
    @CacheEvict(value = "perfil", allEntries = true)
    public void limpiarCache() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Se ejecuto la limpieza del cache Perfil");
        }
    }


}

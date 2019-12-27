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

import com.pe.ventas.back.daos.IRegimenLaboralDao;
import com.pe.ventas.back.daos.sql.mapeos.EmpresaSqlMaper;
import com.pe.ventas.back.daos.sql.mapeos.RegimenLaboralSqlMaper;
import com.pe.ventas.back.dtos.daos.dao.RegimenLaboralDaoDto;
import com.pe.ventas.back.dtos.daos.sql.EmpresaSqlDto;
import com.pe.ventas.back.dtos.daos.sql.RegimenLaboralSqlDto;
import com.pe.ventas.back.utilidades.mapeos.EmpresaDtoMaper;
import com.pe.ventas.back.utilidades.mapeos.RegimenLaboralDtoMaper;

@Repository("regimenLaboralDao")
public class RegimenLaboralDao implements IRegimenLaboralDao{
	
	private static final Logger LOG = LogManager.getLogger(RegimenLaboralDao.class);
	
	 @Autowired
	 private RegimenLaboralSqlMaper regimenLaboralSqlMaper;

	@Override
    @Cacheable(value = "regimenLaboral")
    @Transactional(readOnly = true)
	public List<RegimenLaboralDaoDto> obtenerTodos() {
		// TODO Auto-generated method stub
        final List<RegimenLaboralSqlDto> listaRegimenLaboral = regimenLaboralSqlMaper.selectTodosRegimenLaboral();
        return RegimenLaboralDtoMaper.INSTANCE.convertirListaRegimenLaboralSqlDtoARegimenLaboralDaoDto(listaRegimenLaboral);
	}

	@Override
    @Cacheable(value = "regimenLaboral")
    @Transactional(readOnly = true)
	public RegimenLaboralDaoDto obtenerUnRegimenLaboral(RegimenLaboralDaoDto regimenLaboral) {
		// TODO Auto-generated method stub
        LOG.debug("Obtener regimenlaboral: RegimenLaboralDaoDto: " + regimenLaboral);
        RegimenLaboralSqlDto regimenLaboralSqlDto = RegimenLaboralDtoMaper.INSTANCE.RegimenLaboralDaoDtoARegimenLaboralSqlDto(regimenLaboral);
        regimenLaboralSqlDto = regimenLaboralSqlMaper.selectUnRegimenLaboral(regimenLaboralSqlDto);
        return RegimenLaboralDtoMaper.INSTANCE.RegimenLaboralSqlDtoARegimenLaboralDaoDto(regimenLaboralSqlDto);
	}

	@Override
    @CachePut(value = "regimenLaboral")
    @Transactional
	public RegimenLaboralDaoDto crear(RegimenLaboralDaoDto regimenLaboral) {
        final RegimenLaboralSqlDto regimenLaboralSqlDto = RegimenLaboralDtoMaper.INSTANCE.RegimenLaboralDaoDtoARegimenLaboralSqlDto(regimenLaboral);
        final Integer resultado = regimenLaboralSqlMaper.insert(regimenLaboralSqlDto);

        if ((resultado != null) && BooleanUtils.toBoolean(resultado)) {
            return RegimenLaboralDtoMaper.INSTANCE.RegimenLaboralSqlDtoARegimenLaboralDaoDto(regimenLaboralSqlDto);
        }

        return null;
	}

	@Override
    @CachePut(value = "regimenLaboral")
    @Transactional
	public Boolean actualizar(RegimenLaboralDaoDto regimenLaboral) {
        final RegimenLaboralSqlDto regimenLaboralSqlDto = RegimenLaboralDtoMaper.INSTANCE.RegimenLaboralDaoDtoARegimenLaboralSqlDto(regimenLaboral);
        LOG.debug(regimenLaboralSqlDto);
        final Integer resultado = regimenLaboralSqlMaper.update(regimenLaboralSqlDto);
        return (resultado != null) && (resultado > 0) ? true : false;
	}

	@Override
    @CacheEvict(value = "regimenLaboral", key = "#regimenLaboral.regLaboId")
    @Transactional
	public Boolean eliminar(RegimenLaboralDaoDto regimenLaboral) {
		final RegimenLaboralSqlDto regimenLaboralSqlDto = RegimenLaboralDtoMaper.INSTANCE.RegimenLaboralDaoDtoARegimenLaboralSqlDto(regimenLaboral);
        final Integer resultado = regimenLaboralSqlMaper.delete(regimenLaboralSqlDto);
        return (resultado != null) && (resultado > 0) ? true : false;
	}

	@Override
    @CacheEvict(value = "regimenLaboral", allEntries = true)
	public void limpiarCache() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Se ejecuto la limpieza del cache regimen laboral");
        }
		
	}

}

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

import com.pe.ventas.back.daos.IEmpresaDao;
import com.pe.ventas.back.daos.sql.mapeos.EmpresaSqlMaper;
import com.pe.ventas.back.dtos.daos.dao.EmpresaDaoDto;
import com.pe.ventas.back.dtos.daos.sql.EmpresaSqlDto;
import com.pe.ventas.back.utilidades.mapeos.EmpresaDtoMaper;

@Repository("empresaDao")
public class EmpresaDao implements IEmpresaDao {
	
	 private static final Logger LOG = LogManager.getLogger(EmpresaDao.class);
	 
	 @Autowired
	 private EmpresaSqlMaper empresaSqlMaper;

	@Override
    @Cacheable(value = "empresas")
    @Transactional(readOnly = true)
	public List<EmpresaDaoDto> obtenerTodos() {
        final List<EmpresaSqlDto> listaEmpresas = empresaSqlMaper.selectTodasEmpresas();
        return EmpresaDtoMaper.INSTANCE.convertirListaEmpresaSqlDtoAEmpresaDaoDto(listaEmpresas);
	}

	@Override
    @Cacheable(value = "empresas")
    @Transactional(readOnly = true)
	public EmpresaDaoDto obtenerUnEmpresa(EmpresaDaoDto empresa) {
        LOG.debug("Obtener empresa: EmpresaDaoDto: " + empresa);
        EmpresaSqlDto empresaSqlDto = EmpresaDtoMaper.INSTANCE.empresaDaoDtoAEmpresaSqlDto(empresa);
        empresaSqlDto = empresaSqlMaper.selectUnaEmpresa(empresaSqlDto);
        return EmpresaDtoMaper.INSTANCE.empresaSqlDtoAEmpresaDaoDto(empresaSqlDto);
	}

	@Override
    @CachePut(value = "empresas")
    @Transactional
	public EmpresaDaoDto crear(EmpresaDaoDto empresa) {
        final EmpresaSqlDto empresaSqlDto = EmpresaDtoMaper.INSTANCE.empresaDaoDtoAEmpresaSqlDto(empresa);
        final Integer resultado = empresaSqlMaper.insert(empresaSqlDto);

        if ((resultado != null) && BooleanUtils.toBoolean(resultado)) {
            return EmpresaDtoMaper.INSTANCE.empresaSqlDtoAEmpresaDaoDto(empresaSqlDto);
        }

        return null;
	}
	

	@Override
    @CachePut(value = "empresas")
    @Transactional
	public Boolean actualizar(EmpresaDaoDto empresa) {
        final EmpresaSqlDto empresaSqlDto = EmpresaDtoMaper.INSTANCE.empresaDaoDtoAEmpresaSqlDto(empresa);
        LOG.debug(empresaSqlDto);
        final Integer resultado = empresaSqlMaper.update(empresaSqlDto);
        return (resultado != null) && (resultado > 0) ? true : false;
	}

	@Override
    @CacheEvict(value = "empresas", key = "#empresa.empresaId")
    @Transactional
	public Boolean eliminar(EmpresaDaoDto empresa) {
        final EmpresaSqlDto empresaSqlDto = EmpresaDtoMaper.INSTANCE.empresaDaoDtoAEmpresaSqlDto(empresa);
        final Integer resultado = empresaSqlMaper.delete(empresaSqlDto);
        return (resultado != null) && (resultado > 0) ? true : false;
	}

	@Override
    @CacheEvict(value = "empresas", allEntries = true)
	public void limpiarCache() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Se ejecuto la limpieza del cache empresa");
        }
		
	}

}

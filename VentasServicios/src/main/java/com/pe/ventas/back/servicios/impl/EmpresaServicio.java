package com.pe.ventas.back.servicios.impl;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.ventas.back.daos.IEmpresaDao;
import com.pe.ventas.back.daos.impl.EmpresaDao;
import com.pe.ventas.back.dtos.daos.dao.EmpresaDaoDto;
import com.pe.ventas.back.dtos.servicios.EmpresaServicioDto;
import com.pe.ventas.back.servicios.IEmpresaServicio;
import com.pe.ventas.back.utilidades.mapeos.EmpresaDtoMaper;

@Service("empresaServicio")
public class EmpresaServicio implements IEmpresaServicio {
	
	private static final Logger LOG = LogManager.getLogger(EmpresaServicio.class);
	
    @Autowired
    private IEmpresaDao empresaDao;

	@Override
	public Boolean actualizar(EmpresaServicioDto empresa) {
        final EmpresaDaoDto empresaDaoDto = EmpresaDtoMaper.INSTANCE.empresaServicioDtoAEmpresaDaoDto(empresa);
        return empresaDao.actualizar(empresaDaoDto);
	}

	@Override
	public Boolean eliminar(EmpresaServicioDto empresa) {
		LOG.debug("Eliminar empresa: "+empresa);
    	EmpresaDaoDto empresaDaoDto = EmpresaDtoMaper.INSTANCE.empresaServicioDtoAEmpresaDaoDto(empresa);   	
    	return  empresaDao.eliminar(empresaDaoDto);
	}

	@Override
	public Boolean insertarEmpresa(EmpresaServicioDto empresa) {
    	LOG.debug("Insertar empresa: "+empresa);
    	EmpresaDaoDto empresaDaoDto = EmpresaDtoMaper.INSTANCE.empresaServicioDtoAEmpresaDaoDto(empresa);
    	empresaDaoDto = empresaDao.crear(empresaDaoDto);
    	LOG.debug("Resultado empresaDaoDto: "+empresaDaoDto);
    	 if ((empresaDaoDto != null) && (empresaDaoDto.getEmpresaId() != null)) {
    		 
    		  return true;
    		 
    	 }
    	return false;
	}

	@Override
	public List<EmpresaServicioDto> todasLasEmpresas() {
		LOG.debug("Listar empresas: ");
		List<EmpresaDaoDto> listaEmpresaDaoDto =empresaDao.obtenerTodos();
		return EmpresaDtoMaper.INSTANCE.convertirListaEmpresaDaoDtoAEmpresaServicioDto(listaEmpresaDaoDto);
	}

	@Override
	public EmpresaServicioDto obtenerUnaEmpresa(EmpresaServicioDto empresa) {
		EmpresaDaoDto empresaDaoDto = EmpresaDtoMaper.INSTANCE.empresaServicioDtoAEmpresaDaoDto(empresa);
		empresaDaoDto = empresaDao.obtenerUnEmpresa(empresaDaoDto);
		EmpresaServicioDto empresaServicioDto = EmpresaDtoMaper.INSTANCE.empresaDaoDtoAEmpresaServicioDto(empresaDaoDto);
		return empresaServicioDto;
	}

	@Override
	public void limpiarCache() {
		empresaDao.limpiarCache();
		
	}

}

package com.pe.ventas.back.servicios.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.ventas.back.daos.IEmpresaDao;
import com.pe.ventas.back.daos.IRegimenLaboralDao;
import com.pe.ventas.back.dtos.daos.dao.EmpresaDaoDto;
import com.pe.ventas.back.dtos.daos.dao.RegimenLaboralDaoDto;
import com.pe.ventas.back.dtos.servicios.EmpresaServicioDto;
import com.pe.ventas.back.dtos.servicios.RegimenLaboralServicioDto;
import com.pe.ventas.back.servicios.IRegimenLaboralServicio;
import com.pe.ventas.back.utilidades.mapeos.EmpresaDtoMaper;
import com.pe.ventas.back.utilidades.mapeos.RegimenLaboralDtoMaper;

@Service("regimenLaboralServicio")
public class RegimenLaboralServicio implements IRegimenLaboralServicio {
	
	private static final Logger LOG = LogManager.getLogger(RegimenLaboralServicio.class);
	
    @Autowired
    private IRegimenLaboralDao regimenLaboralDao;

	@Override
	public Boolean actualizar(RegimenLaboralServicioDto regimenLaboral) {
        final RegimenLaboralDaoDto regimenLaboralDaoDto = RegimenLaboralDtoMaper.INSTANCE.RegimenLaboralServicioDtoARegimenLaboralDaoDto(regimenLaboral);
        regimenLaboralDaoDto.setRegLaboFecMod(new Date());
        return regimenLaboralDao.actualizar(regimenLaboralDaoDto);
	}

	@Override
	public Boolean eliminar(RegimenLaboralServicioDto regimenLaboral) {
		LOG.debug("Eliminar regimen laboral: "+regimenLaboral);
    	RegimenLaboralDaoDto regimenLaboralDaoDto = RegimenLaboralDtoMaper.INSTANCE.RegimenLaboralServicioDtoARegimenLaboralDaoDto(regimenLaboral);  	
    	return  regimenLaboralDao.eliminar(regimenLaboralDaoDto);
	}

	@Override
	public Boolean insertarRegimenLaboral(RegimenLaboralServicioDto regimenLaboral) {
    	LOG.debug("Insertar regimen laboral: "+regimenLaboral);
    	RegimenLaboralDaoDto regimenLaboralDaoDto = RegimenLaboralDtoMaper.INSTANCE.RegimenLaboralServicioDtoARegimenLaboralDaoDto(regimenLaboral);
    	regimenLaboralDaoDto.setRegLaboFecCrea(new Date());
    	regimenLaboralDaoDto = regimenLaboralDao.crear(regimenLaboralDaoDto);
    	LOG.debug("Resultado regimenLaboralDaoDto: "+regimenLaboralDaoDto);
    	 if ((regimenLaboralDaoDto != null) && (regimenLaboralDaoDto.getRegLaboId() != null)) {
    		 
    		  return true;
    		 
    	 }
    	return false;
	}

	@Override
	public List<RegimenLaboralServicioDto> todasLosRegimenLaboral() {
		LOG.debug("Listar regimen laboral: ");
		List<RegimenLaboralDaoDto> listaRegimenLaboralDaoDto =regimenLaboralDao.obtenerTodos();
		return RegimenLaboralDtoMaper.INSTANCE.convertirListaRegimenLaboralDaoDtoARegimenLaboralServicioDto(listaRegimenLaboralDaoDto);
	}

	@Override
	public RegimenLaboralServicioDto obtenerUnRegimenLaboral(RegimenLaboralServicioDto regimenLaboral) {
		RegimenLaboralDaoDto regimenLaboralDaoDto = RegimenLaboralDtoMaper.INSTANCE.RegimenLaboralServicioDtoARegimenLaboralDaoDto(regimenLaboral);
		regimenLaboralDaoDto = regimenLaboralDao.obtenerUnRegimenLaboral(regimenLaboralDaoDto);
		RegimenLaboralServicioDto regimenLaboralServicioDto = RegimenLaboralDtoMaper.INSTANCE.RegimenLaboralDaoDtoARegimenLaboralServicioDto(regimenLaboralDaoDto);
		return regimenLaboralServicioDto;
	}

	@Override
	public void limpiarCache() {
		regimenLaboralDao.limpiarCache();
		
	}

}

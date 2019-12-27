package com.pe.ventas.back.servicios.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.ventas.back.daos.IPerfilDao;
import com.pe.ventas.back.dtos.daos.dao.PerfilDaoDto;
import com.pe.ventas.back.dtos.servicios.PerfilServicioDto;
import com.pe.ventas.back.servicios.IPerfilServicio;
import com.pe.ventas.back.utilidades.mapeos.PerfilDtoMaper;

@Service("perfilServicio")
public class PerfilServicio implements IPerfilServicio{
	
	private static final Logger LOG = LogManager.getLogger(PerfilServicio.class);
	  @Autowired
	    private IPerfilDao perfilDao;

		@Override
		public Boolean actualizar(PerfilServicioDto perfil) {
	        final PerfilDaoDto perfilDaoDto = PerfilDtoMaper.INSTANCE.PerfilServicioDtoAPerfilDaoDto(perfil);
	        perfilDaoDto.setPerfilFechaMod(new Date());
	        return perfilDao.actualizar(perfilDaoDto);
		}

		@Override
		public Boolean eliminar(PerfilServicioDto perfil) {
			LOG.debug("Eliminar Perfil: "+perfil);
	    	PerfilDaoDto perfilDaoDto = PerfilDtoMaper.INSTANCE.PerfilServicioDtoAPerfilDaoDto(perfil);   	
	    	return  perfilDao.eliminar(perfilDaoDto);
		}

		@Override
		public Boolean insertarPerfil(PerfilServicioDto perfil) {
	    	LOG.debug("Insertar Perfil: "+perfil);
	    	PerfilDaoDto perfilDaoDto = PerfilDtoMaper.INSTANCE.PerfilServicioDtoAPerfilDaoDto(perfil);
	    	perfilDaoDto.setPerfilFechaCreacion(new Date());
	    	perfilDaoDto = perfilDao.crear(perfilDaoDto);
	    	LOG.debug("Resultado PerfilDaoDto: "+perfilDaoDto);
	    	 if ((perfilDaoDto != null) && (perfilDaoDto.getPerfilId() != null)) {
	    		 
	    		  return true;
	    		 
	    	 }
	    	return false;
		}

		@Override
		public List<PerfilServicioDto> todosLosPerfiles() {
			LOG.debug("Listar Perfiles: ");
			List<PerfilDaoDto> listaPerfilDaoDto =perfilDao.obtenerTodos();
			return PerfilDtoMaper.INSTANCE.convertirListaPerfilDaoDtoAPerfilServicioDto(listaPerfilDaoDto);
		}

		@Override
		public PerfilServicioDto obtenerUnPerfil(PerfilServicioDto perfil) {
			PerfilDaoDto perfilDaoDto = PerfilDtoMaper.INSTANCE.PerfilServicioDtoAPerfilDaoDto(perfil);
			perfilDaoDto = perfilDao.obtenerUnPerfil(perfilDaoDto);
			PerfilServicioDto perfilServicioDto = PerfilDtoMaper.INSTANCE.PerfilDaoDtoAPerfilServicioDto(perfilDaoDto);
			return perfilServicioDto;
		}

		@Override
		public void limpiarCache() {
			perfilDao.limpiarCache();
			
		}
}

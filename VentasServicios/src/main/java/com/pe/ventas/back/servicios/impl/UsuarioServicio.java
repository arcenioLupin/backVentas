package com.pe.ventas.back.servicios.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.ventas.back.daos.IUsuarioDao;
import com.pe.ventas.back.dtos.daos.dao.UsuarioDaoDto;
import com.pe.ventas.back.dtos.servicios.UsuarioServicioDto;
import com.pe.ventas.back.servicios.IUsuarioServicio;
import com.pe.ventas.back.utilidades.mapeos.UsuarioDtoMaper;

@Service("usuarioServicio")
public class UsuarioServicio implements IUsuarioServicio {

    private static final Logger LOG = LogManager.getLogger(UsuarioServicio.class);

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    public UsuarioServicioDto autenticar(final UsuarioServicioDto usuario) {
        LOG.debug("autenticar: " + usuario);
        UsuarioDaoDto usuarioDaoDto = UsuarioDtoMaper.INSTANCE.usuarioServicioDtoAUsuarioDaoDto(usuario);
        usuarioDaoDto = usuarioDao.obtenerUnUsuario(usuarioDaoDto);
        LOG.debug("Resultado Dao: " + usuarioDaoDto);
        
        UsuarioServicioDto usuarioServicioDto = UsuarioDtoMaper.INSTANCE.usuarioDaoDtoAUsuarioServicioDto(usuarioDaoDto);

        if ((usuarioDaoDto != null) && (usuarioDaoDto.getUsuarioId() != null) ) {

            final UsuarioDaoDto usuarioDaoDtoTmp = new UsuarioDaoDto();
            usuarioDaoDtoTmp.setUsuarioId(usuarioDaoDto.getUsuarioId());
            usuarioDaoDtoTmp.setUsuarioAcceso(new Date());
            final Boolean actualizar = usuarioDao.actualizar(usuarioDaoDtoTmp);

            if (!actualizar) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Error al actualizar la fecha de acceso");
                }
            }

            
        }

        return usuarioServicioDto;
    }

    @Override
    public List<UsuarioServicioDto> todosLosUsuarios() {
        final List<UsuarioDaoDto> obtenerTodos = usuarioDao.obtenerTodos();

        if (CollectionUtils.isNotEmpty(obtenerTodos)) {
            return UsuarioDtoMaper.INSTANCE.convertirListaUsuarioDaoDtoAUsuarioServicioDto(obtenerTodos);
        }

        return null;
    }

    @Override
    public Boolean actualizar(final UsuarioServicioDto usuario) {
        final UsuarioDaoDto usuarioDaoDto = UsuarioDtoMaper.INSTANCE.usuarioServicioDtoAUsuarioDaoDto(usuario);
        usuarioDaoDto.setUsuarioFechaMod(new Date());
        return usuarioDao.actualizar(usuarioDaoDto);
    }
    
    @Override
    public Boolean insertarUsuario(final UsuarioServicioDto usuario) {
    	LOG.debug("Insertar usuario: "+usuario);
    	UsuarioDaoDto usuarioDaoDto = UsuarioDtoMaper.INSTANCE.usuarioServicioDtoAUsuarioDaoDto(usuario);
    	usuarioDaoDto.setUsuarioFechaCreacion(new Date());
    	usuarioDaoDto = usuarioDao.crear(usuarioDaoDto);
    	LOG.debug("Resultado usuarioDaoDto: "+usuarioDaoDto);
    	 if ((usuarioDaoDto != null) && (usuarioDaoDto.getUsuarioId() != null)) {
    		 
    		  return true;
    		 
    	 }
    	return false;
    }

    @Override
    public void limpiarCache() {
        usuarioDao.limpiarCache();
    }

	@Override
	public Boolean eliminar(UsuarioServicioDto usuario) {
		LOG.debug("Eliminar usuario: "+usuario);
    	UsuarioDaoDto usuarioDaoDto = UsuarioDtoMaper.INSTANCE.usuarioServicioDtoAUsuarioDaoDto(usuario);   	
    	return  usuarioDao.eliminar(usuarioDaoDto);
    	
	}
	

	@Override
	public UsuarioServicioDto obtenerUnUsuario(UsuarioServicioDto usuario) {
		UsuarioDaoDto usuarioDaoDto = UsuarioDtoMaper.INSTANCE.usuarioServicioDtoAUsuarioDaoDto(usuario); 
		usuarioDaoDto = usuarioDao.obtenerUnUsuario(usuarioDaoDto);
		UsuarioServicioDto usuarioServicioDto = UsuarioDtoMaper.INSTANCE.usuarioDaoDtoAUsuarioServicioDto(usuarioDaoDto);
		return usuarioServicioDto;
	}

}

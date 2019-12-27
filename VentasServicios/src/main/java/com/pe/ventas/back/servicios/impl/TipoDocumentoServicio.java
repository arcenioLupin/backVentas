package com.pe.ventas.back.servicios.impl;


import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pe.ventas.back.daos.ITipoDocumentoDao;
import com.pe.ventas.back.dtos.daos.dao.TipoDocumentoDaoDto;
import com.pe.ventas.back.dtos.servicios.TipoDocumentoServicioDto;
import com.pe.ventas.back.servicios.ITipoDocumentoServicio;
import com.pe.ventas.back.utilidades.mapeos.TipoDocumentoDtoMaper;

@Service("tipoDocumentoServicio")
public class TipoDocumentoServicio implements ITipoDocumentoServicio {

	private static final Logger LOG = LogManager.getLogger(TipoDocumentoServicio.class);
	  @Autowired
	    private ITipoDocumentoDao tipoDocumentoDao;

		@Override
		public Boolean actualizar(TipoDocumentoServicioDto tipoDocumento) {
	        final TipoDocumentoDaoDto tipoDocumentoDaoDto = TipoDocumentoDtoMaper.INSTANCE.TipoDocumentoServicioDtoATipoDocumentoDaoDto(tipoDocumento);
	        return tipoDocumentoDao.actualizar(tipoDocumentoDaoDto);
		}

		@Override
		public Boolean eliminar(TipoDocumentoServicioDto tipoDocumento) {
			LOG.debug("Eliminar TipoDocumento: "+tipoDocumento);
	    	TipoDocumentoDaoDto tipoDocumentoDaoDto = TipoDocumentoDtoMaper.INSTANCE.TipoDocumentoServicioDtoATipoDocumentoDaoDto(tipoDocumento);   	
	    	return  tipoDocumentoDao.eliminar(tipoDocumentoDaoDto);
		}

		@Override
		public Boolean insertarTipoDocumento(TipoDocumentoServicioDto tipoDocumento) {
	    	LOG.debug("Insertar TipoDocumento: "+tipoDocumento);
	    	TipoDocumentoDaoDto tipoDocumentoDaoDto = TipoDocumentoDtoMaper.INSTANCE.TipoDocumentoServicioDtoATipoDocumentoDaoDto(tipoDocumento);
	    	tipoDocumentoDaoDto = tipoDocumentoDao.crear(tipoDocumentoDaoDto);
	    	LOG.debug("Resultado TipoDocumentoDaoDto: "+tipoDocumentoDaoDto);
	    	 if ((tipoDocumentoDaoDto != null) && (tipoDocumentoDaoDto.getTipoDocId() != null)) {
	    		 
	    		  return true;
	    		 
	    	 }
	    	return false;
		}

		@Override
		public List<TipoDocumentoServicioDto> todosLosTipoDocumentos() {
			LOG.debug("Listar TipoDocumentos: ");
			List<TipoDocumentoDaoDto> listaTipoDocumentoDaoDto =tipoDocumentoDao.obtenerTodos();
			return TipoDocumentoDtoMaper.INSTANCE.convertirListaTipoDocumentoDaoDtoATipoDocumentoServicioDto(listaTipoDocumentoDaoDto);
		}

		@Override
		public TipoDocumentoServicioDto obtenerUnTipoDocumento(TipoDocumentoServicioDto tipoDocumento) {
			TipoDocumentoDaoDto tipoDocumentoDaoDto = TipoDocumentoDtoMaper.INSTANCE.TipoDocumentoServicioDtoATipoDocumentoDaoDto(tipoDocumento);
			tipoDocumentoDaoDto = tipoDocumentoDao.obtenerUnTipoDocumento(tipoDocumentoDaoDto);
			TipoDocumentoServicioDto tipoDocumentoServicioDto = TipoDocumentoDtoMaper.INSTANCE.TipoDocumentoDaoDtoATipoDocumentoServicioDto(tipoDocumentoDaoDto);
			return tipoDocumentoServicioDto;
		}

		@Override
		public void limpiarCache() {
			tipoDocumentoDao.limpiarCache();
			
		}
}

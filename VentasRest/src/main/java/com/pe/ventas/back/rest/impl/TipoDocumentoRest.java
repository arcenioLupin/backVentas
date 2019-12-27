package com.pe.ventas.back.rest.impl;

import static com.pe.ventas.back.dtos.base.Constantes.CONTENT_TYPE;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.path;
import static spark.Spark.put;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pe.ventas.back.dtos.base.JsonDto;
import com.pe.ventas.back.dtos.rest.TipoDocumentoRestDto;
import com.pe.ventas.back.dtos.rest.base.TipoDocumentoResponse;
import com.pe.ventas.back.dtos.rest.base.ResultResponse;
import com.pe.ventas.back.dtos.servicios.TipoDocumentoServicioDto;
import com.pe.ventas.back.rest.main.IVentaRest;
import com.pe.ventas.back.servicios.ITipoDocumentoServicio;
import com.pe.ventas.back.utilidades.mapeos.TipoDocumentoDtoMaper;

import spark.Request;
import spark.Response;

@Component("tipoDocumentoRest")
public class TipoDocumentoRest implements IVentaRest {
	
    private static final Logger LOG = LogManager.getLogger(TipoDocumentoRest.class);

    @Autowired
    private ITipoDocumentoServicio tipoDocumentoServicio;

	@Override
	public void routers() {
        path("/api", () -> {
            path("/v1", () -> {
                get("/tipoDocumentos/estadoDelServicio", CONTENT_TYPE,
                        (request, response) -> new ResultResponse.Builder().code(200).message("Funcionando").build());
                get("/tipoDocumentos", CONTENT_TYPE, (request, response) -> todosLosTipoDocumentos(request, response));
                get("/tipoDocumentos/tipoDocumento/:id", CONTENT_TYPE, (request, response) -> obtenerUnTipoDocumento(request, response));
                put("/tipoDocumentos", CONTENT_TYPE, (request, response) -> insertarTipoDocumento(request, response));
                put("/tipoDocumentos/actualizar", CONTENT_TYPE, (request, response) -> actualizarTipoDocumento(request, response));
                delete("/tipoDocumentos/delete/:id", CONTENT_TYPE, (request, response) -> eliminarTipoDocumento(request, response));
                get("/tipoDocumentos/clear", CONTENT_TYPE, (request, response) -> limpiarCache(request, response));
            });
        });
		
	}


    private void validarContentType(final Request request, final Response response) {
        if (!StringUtils.equals(request.contentType(), CONTENT_TYPE)) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Fallo en el tipo de content-type en el request");
            }
            response.type(CONTENT_TYPE);
            halt(500, new ResultResponse.Builder().code(500).message("Error en el Content-Type").build().aJson());
        }
    }

    private ResultResponse limpiarCache(final Request request, final Response response) {
        validarContentType(request, response);

        tipoDocumentoServicio.limpiarCache();
        response.type(CONTENT_TYPE);
        return new ResultResponse.Builder().code(200).message("Cache limpio").build();

    }

 

    private TipoDocumentoResponse todosLosTipoDocumentos(final Request request, final Response response) {
        validarContentType(request, response);

        final List<TipoDocumentoServicioDto> todasLosTipoDocumentos = tipoDocumentoServicio.todosLosTipoDocumentos();

        response.type(CONTENT_TYPE);
        if (CollectionUtils.isNotEmpty(todasLosTipoDocumentos)) {
            return new TipoDocumentoResponse.Builder()
                    .tipoDocumento(TipoDocumentoDtoMaper.INSTANCE.convertirListaTipoDocumentoServicioDtoATipoDocumentoRestDto(todasLosTipoDocumentos))
                    .build();
        }

        response.status(500);
        return new TipoDocumentoResponse.Builder().code(500).message("Fallo al obtener todos las TipoDocumentoes").build();
    }
    
    private ResultResponse insertarTipoDocumento(final Request request, final Response response) {
    	 validarContentType(request,response);
    	 final TipoDocumentoRestDto tipoDocumento = JsonDto.aJson(request.body(),TipoDocumentoRestDto.class);
    	 final TipoDocumentoServicioDto tipoDocumentoServicioDto = TipoDocumentoDtoMaper.INSTANCE.TipoDocumentoRestDtoATipoDocumentoServicioDto(tipoDocumento);
    	 final Boolean isTipoDocumentoInsertado = tipoDocumentoServicio.insertarTipoDocumento(tipoDocumentoServicioDto);
    	 
    	 response.type(CONTENT_TYPE);
    	 if(isTipoDocumentoInsertado) {
    		 
    		 return new ResultResponse.Builder().build();
    	 }
    	 
    	 response.status(500);
    	 return new ResultResponse.Builder().code(500).message("Fallo al insertar TipoDocumento").build();
    	
    }
    
    private ResultResponse actualizarTipoDocumento(final Request request, final Response response) {
   	 validarContentType(request,response);
   	 final TipoDocumentoRestDto tipoDocumento = JsonDto.aJson(request.body(),TipoDocumentoRestDto.class);
   	 final TipoDocumentoServicioDto tipoDocumentoServicioDto = TipoDocumentoDtoMaper.INSTANCE.TipoDocumentoRestDtoATipoDocumentoServicioDto(tipoDocumento);
   	 final Boolean isTipoDocumentoActualizado = tipoDocumentoServicio.actualizar(tipoDocumentoServicioDto);
   	 
   	 response.type(CONTENT_TYPE);
   	 if(isTipoDocumentoActualizado) {
   		 
   		 return new ResultResponse.Builder().build();
   	 }
   	 
   	 response.status(500);
   	 return new ResultResponse.Builder().code(500).message("Fallo al actualizar la TipoDocumento").build();
   	
   }    
    
  private ResultResponse eliminarTipoDocumento(final Request request, final Response response) {
      	 validarContentType(request,response);
      	 
      	 String id = request.params(":id");      	 
      	 TipoDocumentoServicioDto tipoDocumentoServicioDto = new TipoDocumentoServicioDto();
      	 tipoDocumentoServicioDto.setTipoDocId(Integer.parseInt(id));
      	 final Boolean isTipoDocumentoEliminado = tipoDocumentoServicio.eliminar(tipoDocumentoServicioDto);
      	 
      	 response.type(CONTENT_TYPE);
      	 if(isTipoDocumentoEliminado) {
      		 
      		 return new ResultResponse.Builder().build();
      	 }
      	 
      	 response.status(500);
      	 return new ResultResponse.Builder().code(500).message("Fallo al eliminar la TipoDocumento").build();
      	
      }
  
  private TipoDocumentoResponse obtenerUnTipoDocumento(final Request request, final Response response) {
  	LOG.info("entro a obtenerUnTipoDocumento()");
      validarContentType(request, response);
      
      String id = request.params(":id");
      TipoDocumentoServicioDto tipoDocumentoServicioDto = new TipoDocumentoServicioDto();
      tipoDocumentoServicioDto.setTipoDocId(Integer.parseInt(id));
      tipoDocumentoServicioDto = tipoDocumentoServicio.obtenerUnTipoDocumento(tipoDocumentoServicioDto);
	   	 
	   	 response.type(CONTENT_TYPE);
	   	 if(tipoDocumentoServicioDto != null) {
	   		 
	   	  return new TipoDocumentoResponse.Builder()
                  .tipoDocumento(TipoDocumentoDtoMaper.INSTANCE.TipoDocumentoServicioDtoATipoDocumentoRestDto(tipoDocumentoServicioDto))
                  .build();
	   	 }
	   	 
	   	 response.status(500);
	   	 return new TipoDocumentoResponse.Builder().code(500).message("Fallo al obtener la TipoDocumento").build();

 }

}

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
import com.pe.ventas.back.dtos.rest.PerfilRestDto;
import com.pe.ventas.back.dtos.rest.base.PerfilResponse;
import com.pe.ventas.back.dtos.rest.base.ResultResponse;
import com.pe.ventas.back.dtos.servicios.PerfilServicioDto;
import com.pe.ventas.back.rest.main.IVentaRest;
import com.pe.ventas.back.servicios.IPerfilServicio;
import com.pe.ventas.back.utilidades.mapeos.PerfilDtoMaper;

import spark.Request;
import spark.Response;

@Component("perfilRest")
public class PerfilRest implements IVentaRest {
	
    private static final Logger LOG = LogManager.getLogger(PerfilRest.class);

    @Autowired
    private IPerfilServicio perfilServicio;

	@Override
	public void routers() {
        path("/api", () -> {
            path("/v1", () -> {
                get("/perfiles/estadoDelServicio", CONTENT_TYPE,
                        (request, response) -> new ResultResponse.Builder().code(200).message("Funcionando").build());
                get("/perfiles", CONTENT_TYPE, (request, response) -> todosLosPerfiles(request, response));
                get("/perfiles/perfil/:id", CONTENT_TYPE, (request, response) -> obtenerUnPerfil(request, response));
                put("/perfiles", CONTENT_TYPE, (request, response) -> insertarPerfil(request, response));
                put("/perfiles/actualizar", CONTENT_TYPE, (request, response) -> actualizarPerfil(request, response));
                delete("/perfiles/delete/:id", CONTENT_TYPE, (request, response) -> eliminarPerfil(request, response));
                get("/perfiles/clear", CONTENT_TYPE, (request, response) -> limpiarCache(request, response));
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

        perfilServicio.limpiarCache();
        response.type(CONTENT_TYPE);
        return new ResultResponse.Builder().code(200).message("Cache limpio").build();

    }

 

    private PerfilResponse todosLosPerfiles(final Request request, final Response response) {
        validarContentType(request, response);

        final List<PerfilServicioDto> todasLosPerfiles = perfilServicio.todosLosPerfiles();

        response.type(CONTENT_TYPE);
        if (CollectionUtils.isNotEmpty(todasLosPerfiles)) {
            return new PerfilResponse.Builder()
                    .perfil(PerfilDtoMaper.INSTANCE.convertirListaPerfilServicioDtoAPerfilRestDto(todasLosPerfiles))
                    .build();
        }

        response.status(500);
        return new PerfilResponse.Builder().code(500).message("Fallo al obtener todos las Perfiles").build();
    }
    
    private ResultResponse insertarPerfil(final Request request, final Response response) {
    	 validarContentType(request,response);
    	 final PerfilRestDto perfil = JsonDto.aJson(request.body(),PerfilRestDto.class);
    	 final PerfilServicioDto perfilServicioDto = PerfilDtoMaper.INSTANCE.PerfilRestDtoAPerfilServicioDto(perfil);
    	 final Boolean isPerfilInsertado = perfilServicio.insertarPerfil(perfilServicioDto);
    	 
    	 response.type(CONTENT_TYPE);
    	 if(isPerfilInsertado) {
    		 
    		 return new ResultResponse.Builder().build();
    	 }
    	 
    	 response.status(500);
    	 return new ResultResponse.Builder().code(500).message("Fallo al insertar Perfil").build();
    	
    }
    
    private ResultResponse actualizarPerfil(final Request request, final Response response) {
   	 validarContentType(request,response);
   	 final PerfilRestDto perfil = JsonDto.aJson(request.body(),PerfilRestDto.class);
   	 final PerfilServicioDto perfilServicioDto = PerfilDtoMaper.INSTANCE.PerfilRestDtoAPerfilServicioDto(perfil);
   	 final Boolean isPerfilActualizado = perfilServicio.actualizar(perfilServicioDto);
   	 
   	 response.type(CONTENT_TYPE);
   	 if(isPerfilActualizado) {
   		 
   		 return new ResultResponse.Builder().build();
   	 }
   	 
   	 response.status(500);
   	 return new ResultResponse.Builder().code(500).message("Fallo al actualizar la Perfil").build();
   	
   }    
    
  private ResultResponse eliminarPerfil(final Request request, final Response response) {
      	 validarContentType(request,response);
      	 
      	 String id = request.params(":id");      	 
      	 PerfilServicioDto perfilServicioDto = new PerfilServicioDto();
      	perfilServicioDto.setPerfilId(Integer.parseInt(id));
      	 final Boolean isPerfilEliminado = perfilServicio.eliminar(perfilServicioDto);
      	 
      	 response.type(CONTENT_TYPE);
      	 if(isPerfilEliminado) {
      		 
      		 return new ResultResponse.Builder().build();
      	 }
      	 
      	 response.status(500);
      	 return new ResultResponse.Builder().code(500).message("Fallo al eliminar la Perfil").build();
      	
      }
  
  private PerfilResponse obtenerUnPerfil(final Request request, final Response response) {
  	LOG.info("entro a obtenerUnPerfil()");
      validarContentType(request, response);
      
      String id = request.params(":id");
      PerfilServicioDto perfilServicioDto = new PerfilServicioDto();
      perfilServicioDto.setPerfilId(Integer.parseInt(id));
      perfilServicioDto = perfilServicio.obtenerUnPerfil(perfilServicioDto);
	   	 
	   	 response.type(CONTENT_TYPE);
	   	 if(perfilServicioDto != null) {
	   		 
	   	  return new PerfilResponse.Builder()
                  .perfil(PerfilDtoMaper.INSTANCE.PerfilServicioDtoAPerfilRestDto(perfilServicioDto))
                  .build();
	   	 }
	   	 
	   	 response.status(500);
	   	 return new PerfilResponse.Builder().code(500).message("Fallo al obtener la Perfil").build();

 }
  

}

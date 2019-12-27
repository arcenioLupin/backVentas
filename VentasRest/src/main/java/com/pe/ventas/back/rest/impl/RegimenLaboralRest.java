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
import com.pe.ventas.back.dtos.rest.RegimenLaboralRestDto;
import com.pe.ventas.back.dtos.rest.base.RegimenLaboralResponse;
import com.pe.ventas.back.dtos.rest.base.ResultResponse;
import com.pe.ventas.back.dtos.servicios.RegimenLaboralServicioDto;
import com.pe.ventas.back.rest.main.IVentaRest;
import com.pe.ventas.back.servicios.IRegimenLaboralServicio;
import com.pe.ventas.back.utilidades.mapeos.RegimenLaboralDtoMaper;

import spark.Request;
import spark.Response;

@Component("regimenLaboralRest")
public class RegimenLaboralRest implements IVentaRest {
	
	private static final Logger LOG = LogManager.getLogger(RegimenLaboralRest.class);
	
	@Autowired
	private IRegimenLaboralServicio regimenLaboralServicio;

	@Override
	public void routers() {
		// TODO Auto-generated method stub
        path("/api", () -> {
            path("/v1", () -> {
                get("/regimenLaboral/estadoDelServicio", CONTENT_TYPE,
                        (request, response) -> new ResultResponse.Builder().code(200).message("Funcionando").build());
                get("/regimenLaboral", CONTENT_TYPE, (request, response) -> todasLosRegimenLaboral(request, response));
                get("/regimenLaboral/regLaboral/:id", CONTENT_TYPE, (request, response) -> obtenerUnRegimenLaboral(request, response));
                put("/regimenLaboral", CONTENT_TYPE, (request, response) -> insertarRegimenLaboral(request, response));
                put("/regimenLaboral/actualizar", CONTENT_TYPE, (request, response) -> actualizarRegimenLaboral(request, response));
                delete("/regimenLaboral/delete/:id", CONTENT_TYPE, (request, response) -> eliminarRegimenLaboral(request, response));
                get("/regimenLaboral/clear", CONTENT_TYPE, (request, response) -> limpiarCache(request, response));
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

        regimenLaboralServicio.limpiarCache();
        response.type(CONTENT_TYPE);
        return new ResultResponse.Builder().code(200).message("Cache limpio").build();

    }
 
    private RegimenLaboralResponse todasLosRegimenLaboral(final Request request, final Response response) {
        validarContentType(request, response);

        final List<RegimenLaboralServicioDto> todosRegimenLaboral = regimenLaboralServicio.todasLosRegimenLaboral();

        response.type(CONTENT_TYPE);
        if (CollectionUtils.isNotEmpty(todosRegimenLaboral)) {
            return new RegimenLaboralResponse.Builder()
                    .regimenLaboral(RegimenLaboralDtoMaper.INSTANCE.convertirListaRegimenLaboralServicioDtoARegimenLaboralRestDto(todosRegimenLaboral))
                    .build();
        }

        response.status(500);
        return new RegimenLaboralResponse.Builder().code(500).message("Fallo al obtener todos las empresas").build();
    } 
    
    private ResultResponse insertarRegimenLaboral(final Request request, final Response response) {
   	 validarContentType(request,response);
   	 final RegimenLaboralRestDto regimenLaboral = JsonDto.aJson(request.body(),RegimenLaboralRestDto.class);
   	 final RegimenLaboralServicioDto regimenLaboralServicioDto = RegimenLaboralDtoMaper.INSTANCE.RegimenLaboralRestDtoARegimenLaboralServicioDto(regimenLaboral);
   	 final Boolean isEmpresaInsertada = regimenLaboralServicio.insertarRegimenLaboral(regimenLaboralServicioDto);
   	 
   	 response.type(CONTENT_TYPE);
   	 if(isEmpresaInsertada) {
   		 
   		 return new ResultResponse.Builder().build();
   	 }
   	 
   	 response.status(500);
   	 return new ResultResponse.Builder().code(500).message("Fallo al insertar el regimen laboral").build();
   	
   } 

    private ResultResponse actualizarRegimenLaboral(final Request request, final Response response) {
      	 validarContentType(request,response);
    	 final RegimenLaboralRestDto regimenLaboral = JsonDto.aJson(request.body(),RegimenLaboralRestDto.class);
    	 final RegimenLaboralServicioDto regimenLaboralServicioDto = RegimenLaboralDtoMaper.INSTANCE.RegimenLaboralRestDtoARegimenLaboralServicioDto(regimenLaboral);
      	 final Boolean isRegimenLaboralActualizado = regimenLaboralServicio.actualizar(regimenLaboralServicioDto);
      	 
      	 response.type(CONTENT_TYPE);
      	 if(isRegimenLaboralActualizado) {
      		 
      		 return new ResultResponse.Builder().build();
      	 }
      	 
      	 response.status(500);
      	 return new ResultResponse.Builder().code(500).message("Fallo al actualizar el regimen laboral").build();
      	
      } 
    
  private ResultResponse eliminarRegimenLaboral(final Request request, final Response response) {
     	 validarContentType(request,response);
     	 
     	 String id = request.params(":id");      	 
     	 RegimenLaboralServicioDto regimenLaboralServicioDto = new RegimenLaboralServicioDto();
     	 regimenLaboralServicioDto.setRegLaboId(Integer.parseInt(id));
     	 final Boolean isRegimenLaboralEliminado = regimenLaboralServicio.eliminar(regimenLaboralServicioDto);
     	 
     	 response.type(CONTENT_TYPE);
     	 if(isRegimenLaboralEliminado) {
     		 
     		 return new ResultResponse.Builder().build();
     	 }
     	 
     	 response.status(500);
     	 return new ResultResponse.Builder().code(500).message("Fallo al eliminar el régimen laboral").build();
     	
     } 
  
  private RegimenLaboralResponse obtenerUnRegimenLaboral(final Request request, final Response response) {
	  	LOG.info("entro a obtenerUnRegimenLaboral()");
	      validarContentType(request, response);
	      
	      String id = request.params(":id");
	      RegimenLaboralServicioDto regimenLaboralServicioDto = new RegimenLaboralServicioDto();
	      regimenLaboralServicioDto.setRegLaboId(Integer.parseInt(id));
	      regimenLaboralServicioDto = regimenLaboralServicio.obtenerUnRegimenLaboral(regimenLaboralServicioDto);
		   	 
		   	 response.type(CONTENT_TYPE);
		   	 if(regimenLaboralServicioDto != null) {
		   		 
		   	  return new RegimenLaboralResponse.Builder()
	                  .regimenLaboral(RegimenLaboralDtoMaper.INSTANCE.RegimenLaboralServicioDtoARegimenLaboralRestDto(regimenLaboralServicioDto))
	                  .build();
		   	 }
		   	 
		   	 response.status(500);
		   	 return new RegimenLaboralResponse.Builder().code(500).message("Fallo al obtener el regimen laboral").build();

	 }  

}

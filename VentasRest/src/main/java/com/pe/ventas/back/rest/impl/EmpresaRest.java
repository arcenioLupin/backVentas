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
import com.pe.ventas.back.dtos.rest.EmpresaRestDto;
import com.pe.ventas.back.dtos.rest.base.EmpresaResponse;
import com.pe.ventas.back.dtos.rest.base.ResultResponse;
import com.pe.ventas.back.dtos.servicios.EmpresaServicioDto;
import com.pe.ventas.back.rest.main.IVentaRest;
import com.pe.ventas.back.servicios.IEmpresaServicio;
import com.pe.ventas.back.utilidades.mapeos.EmpresaDtoMaper;


import spark.Request;
import spark.Response;

@Component("empresaRest")
public class EmpresaRest implements IVentaRest {
	
    private static final Logger LOG = LogManager.getLogger(EmpresaRest.class);

    @Autowired
    private IEmpresaServicio empresaServicio;

	@Override
	public void routers() {
        path("/api", () -> {
            path("/v1", () -> {
                get("/empresas/estadoDelServicio", CONTENT_TYPE,
                        (request, response) -> new ResultResponse.Builder().code(200).message("Funcionando").build());
                get("/empresas", CONTENT_TYPE, (request, response) -> todasLasEmpresas(request, response));
                get("/empresas/empresa/:id", CONTENT_TYPE, (request, response) -> obtenerUnaEmpresa(request, response));
                put("/empresas", CONTENT_TYPE, (request, response) -> insertarEmpresa(request, response));
                put("/empresas/actualizar", CONTENT_TYPE, (request, response) -> actualizarEmpresa(request, response));
                delete("/empresas/delete/:id", CONTENT_TYPE, (request, response) -> eliminarEmpresa(request, response));
                get("/empresas/clear", CONTENT_TYPE, (request, response) -> limpiarCache(request, response));
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

        empresaServicio.limpiarCache();
        response.type(CONTENT_TYPE);
        return new ResultResponse.Builder().code(200).message("Cache limpio").build();

    }

 

    private EmpresaResponse todasLasEmpresas(final Request request, final Response response) {
        validarContentType(request, response);

        final List<EmpresaServicioDto> todasLasEmpresas = empresaServicio.todasLasEmpresas();

        response.type(CONTENT_TYPE);
        if (CollectionUtils.isNotEmpty(todasLasEmpresas)) {
            return new EmpresaResponse.Builder()
                    .empresa(EmpresaDtoMaper.INSTANCE.convertirListaEmpresaServicioDtoAEmpresaRestDto(todasLasEmpresas))
                    .build();
        }

        response.status(500);
        return new EmpresaResponse.Builder().code(500).message("Fallo al obtener todos las empresas").build();
    }
    
    private ResultResponse insertarEmpresa(final Request request, final Response response) {
    	 validarContentType(request,response);
    	 final EmpresaRestDto empresa = JsonDto.aJson(request.body(),EmpresaRestDto.class);
    	 final EmpresaServicioDto empresaServicioDto = EmpresaDtoMaper.INSTANCE.empresaRestDtoAEmpresaServicioDto(empresa);
    	 final Boolean isEmpresaInsertada = empresaServicio.insertarEmpresa(empresaServicioDto);
    	 
    	 response.type(CONTENT_TYPE);
    	 if(isEmpresaInsertada) {
    		 
    		 return new ResultResponse.Builder().build();
    	 }
    	 
    	 response.status(500);
    	 return new ResultResponse.Builder().code(500).message("Fallo al insertar empresa").build();
    	
    }
    
    private ResultResponse actualizarEmpresa(final Request request, final Response response) {
   	 validarContentType(request,response);
   	 final EmpresaRestDto empresa = JsonDto.aJson(request.body(),EmpresaRestDto.class);
   	 final EmpresaServicioDto empresaServicioDto = EmpresaDtoMaper.INSTANCE.empresaRestDtoAEmpresaServicioDto(empresa);
   	 final Boolean isEmpresaActualizado = empresaServicio.actualizar(empresaServicioDto);
   	 
   	 response.type(CONTENT_TYPE);
   	 if(isEmpresaActualizado) {
   		 
   		 return new ResultResponse.Builder().build();
   	 }
   	 
   	 response.status(500);
   	 return new ResultResponse.Builder().code(500).message("Fallo al actualizar la empresa").build();
   	
   }    
    
  private ResultResponse eliminarEmpresa(final Request request, final Response response) {
      	 validarContentType(request,response);
      	 
      	 String id = request.params(":id");      	 
      	 EmpresaServicioDto empresaServicioDto = new EmpresaServicioDto();
      	 empresaServicioDto.setEmpresaId(Integer.parseInt(id));
      	 final Boolean isEmpresaEliminado = empresaServicio.eliminar(empresaServicioDto);
      	 
      	 response.type(CONTENT_TYPE);
      	 if(isEmpresaEliminado) {
      		 
      		 return new ResultResponse.Builder().build();
      	 }
      	 
      	 response.status(500);
      	 return new ResultResponse.Builder().code(500).message("Fallo al eliminar la empresa").build();
      	
      }
  
  private EmpresaResponse obtenerUnaEmpresa(final Request request, final Response response) {
  	LOG.info("entro a obtenerUnaEmpresa()");
      validarContentType(request, response);
      
      String id = request.params(":id");
      EmpresaServicioDto empresaServicioDto = new EmpresaServicioDto();
      empresaServicioDto.setEmpresaId(Integer.parseInt(id));
      empresaServicioDto = empresaServicio.obtenerUnaEmpresa(empresaServicioDto);
	   	 
	   	 response.type(CONTENT_TYPE);
	   	 if(empresaServicioDto != null) {
	   		 
	   	  return new EmpresaResponse.Builder()
                  .empresa(EmpresaDtoMaper.INSTANCE.EmpresaServicioDtoAEmpresaRestDto(empresaServicioDto))
                  .build();
	   	 }
	   	 
	   	 response.status(500);
	   	 return new EmpresaResponse.Builder().code(500).message("Fallo al obtener la empresa").build();

 }
  
}

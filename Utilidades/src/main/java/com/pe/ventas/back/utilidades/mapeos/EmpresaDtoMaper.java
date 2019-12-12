package com.pe.ventas.back.utilidades.mapeos;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.pe.ventas.back.dtos.daos.dao.EmpresaDaoDto;
import com.pe.ventas.back.dtos.daos.sql.EmpresaSqlDto;
import com.pe.ventas.back.dtos.rest.EmpresaRestDto;
import com.pe.ventas.back.dtos.servicios.EmpresaServicioDto;

@Mapper
public interface EmpresaDtoMaper {
	
    EmpresaDtoMaper INSTANCE = Mappers.getMapper(EmpresaDtoMaper.class);


    EmpresaDaoDto empresaSqlDtoAEmpresaDaoDto(EmpresaSqlDto empresa);
    EmpresaSqlDto empresaDaoDtoAEmpresaSqlDto(EmpresaDaoDto empresa);

    
    EmpresaDaoDto empresaServicioDtoAEmpresaDaoDto(EmpresaServicioDto empresa);  
    EmpresaServicioDto empresaDaoDtoAEmpresaServicioDto(EmpresaDaoDto empresa);
    

    EmpresaServicioDto empresaRestDtoAEmpresaServicioDto(EmpresaRestDto empresa);
    EmpresaRestDto EmpresaServicioDtoAEmpresaRestDto(EmpresaServicioDto empresa);

   
    List<EmpresaRestDto> convertirListaEmpresaServicioDtoAEmpresaRestDto(List<EmpresaServicioDto> empresa);

    List<EmpresaDaoDto> convertirListaEmpresaSqlDtoAEmpresaDaoDto(List<EmpresaSqlDto> empresa);

    List<EmpresaSqlDto> convertirListaEmpresaDaoDtoAEmpresaSqlDto(List<EmpresaDaoDto> empresa);

    List<EmpresaServicioDto> convertirListaEmpresaDaoDtoAEmpresaServicioDto(List<EmpresaDaoDto> empresa);	

}

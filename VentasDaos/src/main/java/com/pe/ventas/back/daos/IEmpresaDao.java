package com.pe.ventas.back.daos;

import java.util.List;
import com.pe.ventas.back.dtos.daos.dao.EmpresaDaoDto;

public interface IEmpresaDao {
	
    List<EmpresaDaoDto> obtenerTodos();

    EmpresaDaoDto obtenerUnEmpresa(EmpresaDaoDto empresa);

    EmpresaDaoDto crear(EmpresaDaoDto empresa);

    Boolean actualizar(EmpresaDaoDto empresa);

    Boolean eliminar(EmpresaDaoDto empresa);

    void limpiarCache();	

}

package com.pe.ventas.back.daos;

import java.util.List;
import com.pe.ventas.back.dtos.daos.dao.PerfilDaoDto;

public interface IPerfilDao {
	
    List<PerfilDaoDto> obtenerTodos();

    PerfilDaoDto obtenerUnPerfil(PerfilDaoDto perfil);

    PerfilDaoDto crear(PerfilDaoDto perfil);

    Boolean actualizar(PerfilDaoDto perfil);

    Boolean eliminar(PerfilDaoDto perfil);

    void limpiarCache();	

}

package com.pe.ventas.back.daos;

import java.util.List;

import com.pe.ventas.back.dtos.daos.dao.RegimenLaboralDaoDto;

public interface IRegimenLaboralDao {
	
    List<RegimenLaboralDaoDto> obtenerTodos();

    RegimenLaboralDaoDto obtenerUnRegimenLaboral(RegimenLaboralDaoDto regimenLaboral);

    RegimenLaboralDaoDto crear(RegimenLaboralDaoDto regimenLaboral);

    Boolean actualizar(RegimenLaboralDaoDto regimenLaboral);

    Boolean eliminar(RegimenLaboralDaoDto regimenLaboral);

    void limpiarCache();	

}

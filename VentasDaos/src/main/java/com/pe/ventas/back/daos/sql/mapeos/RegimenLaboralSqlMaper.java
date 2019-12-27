package com.pe.ventas.back.daos.sql.mapeos;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.pe.ventas.back.dtos.daos.sql.RegimenLaboralSqlDto;

@Mapper
public interface RegimenLaboralSqlMaper {
	
    public List<RegimenLaboralSqlDto> selectTodosRegimenLaboral();

    public RegimenLaboralSqlDto selectUnRegimenLaboral(RegimenLaboralSqlDto regimenLaboral);
    
 
    public Integer insert(RegimenLaboralSqlDto regimenLaboral);

   
    public Integer update(RegimenLaboralSqlDto regimenLaboral);


    public Integer delete(RegimenLaboralSqlDto regimenLaboral);	

}

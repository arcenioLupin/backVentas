package com.pe.ventas.back.daos.sql.mapeos;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pe.ventas.back.dtos.daos.sql.PerfilSqlDto;

@Mapper
public interface PerfilSqlMaper {
	
    public List<PerfilSqlDto> selectTodosPerfiles();

    public PerfilSqlDto selectUnPerfil(PerfilSqlDto perfil);   
 
    public Integer insert(PerfilSqlDto perfil);
 
    public Integer update(PerfilSqlDto perfil);

    public Integer delete(PerfilSqlDto perfil);

}

package com.pe.ventas.back.daos.sql.mapeos;

import static com.pe.ventas.back.daos.sql.mapeos.UsuarioConsultas.SELECT_USUARIOS;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.pe.ventas.back.dtos.daos.sql.UsuarioSqlDto;

@Mapper
public interface UsuarioSqlMaper {

    @Select(SELECT_USUARIOS)
    public List<UsuarioSqlDto> select();

    public UsuarioSqlDto selectUnUsuario(UsuarioSqlDto usuario);
    
 
    public Integer insert(UsuarioSqlDto usuario);

   
    public Integer update(UsuarioSqlDto usuario);


    public Integer delete(UsuarioSqlDto usuario);
}

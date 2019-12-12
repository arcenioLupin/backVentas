package com.pe.ventas.back.daos.sql.mapeos;



import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.pe.ventas.back.dtos.daos.sql.EmpresaSqlDto;

@Mapper
public interface EmpresaSqlMaper {


    public List<EmpresaSqlDto> selectTodasEmpresas();

    public EmpresaSqlDto selectUnaEmpresa(EmpresaSqlDto empresa);
    
 
    public Integer insert(EmpresaSqlDto empresa);

   
    public Integer update(EmpresaSqlDto empresa);


    public Integer delete(EmpresaSqlDto empresa);	
	

}

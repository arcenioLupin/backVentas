package com.pe.ventas.back.dtos.daos.sql;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmpresaSqlDto implements Serializable  {
	
  private static final long serialVersionUID = -124011319139502712L;
  
      private Integer empresaId;
	  private String empresaCodigo;
	  private String empresaRazonSoc;
	  private String empresaGiro;
	  private String empresaSigla;
	  private String empresaRepLegal;
	  private String empresaDireccion;
	  private String empresaCodPais;
	  private String empresaCodProv;
	  private String empresaCodDep;
	  private String empresaTelf;
	  private String empresaEmail;
	  private String empresaRegEsp;
	  private String empresaRutaLogo;
	  private String empresaTipo;
	  private String empresaRuc;
	  private String empresaAbiCerr;
	  private String empresaRutRepLeg;
	  private String empresaCiudad;
	  private String empresaRegLab;
	  private String empresaSiteWeb;
	

}

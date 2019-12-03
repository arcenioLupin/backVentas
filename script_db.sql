-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "public"."tipo_documento"

CREATE TABLE "public"."tipo_documento"
(
 "td_id"          integer NOT NULL GENERATED ALWAYS AS IDENTITY (
 start 1
 cycle
 ),
 "td_descripcion" varchar(200) NULL

);

CREATE UNIQUE INDEX "PK_tipo_documento" ON "public"."tipo_documento"
(
 "td_id"
);








-- ************************************** "public"."usuarios"

CREATE TABLE "public"."usuarios"
(
 "us_id"        integer NOT NULL GENERATED ALWAYS AS IDENTITY (
 start 1
 ),
 "us_nombres"   varchar(300) NULL,
 "us_ape_pat"   varchar(200) NULL,
 "us_ape_mat"   varchar(200) NULL,
 "us_pass"      varchar(300) NULL,
 "us_activo"    varchar(2) NULL,
 "us_fec_ini"   date NULL,
 "us_fec_fin"   date NULL,
 "us_email"     varchar(500) NULL,
 "us_telf"      varchar(50) NULL,
 "us_movil"     varchar(50) NULL,
 "us_telf_2"    varchar(50) NULL,
 "us_num_admin" varchar(50) NULL,
 "us_user"      varchar(50) NULL,
 "us_fec_crea"  date NULL,
 "us_usu_crea"  varchar(50) NOT NULL,
 "us_fec_modi"  date NOT NULL,
 "us_usu_mod"   varchar(50) NULL,
 "us_num_doc"   varchar(50) NULL,
 "td_id"        integer NOT NULL,
 CONSTRAINT "FK_149" FOREIGN KEY ( "td_id" ) REFERENCES "public"."tipo_documento" ( "td_id" )
);

CREATE UNIQUE INDEX "PK_usuarios" ON "public"."usuarios"
(
 "us_id"
);

CREATE INDEX "fkIdx_149" ON "public"."usuarios"
(
 "td_id"
);

-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "public"."perfiles"

CREATE TABLE "public"."perfiles"
(
 "pf_id"          integer NOT NULL GENERATED ALWAYS AS IDENTITY (
 start 1
 ),
 "pf_descripcion" varchar(200) NULL,
 "pf_activo"      varchar(2) NULL,
 "pf_fec_crea"    date NULL,
 "pf__usu_crea"   varchar(50) NULL,
 "pf_fec_mod"     date NULL,
 "pf_usu_mod"     varchar(50) NULL

);

CREATE UNIQUE INDEX "PK_perfiles" ON "public"."perfiles"
(
 "pf_id"
);

-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "public"."usuario_perfil"

CREATE TABLE "public"."usuario_perfil"
(
 "us_id"          integer NOT NULL,
 "pf_id"          integer NOT NULL,
 "us_pf_usu_crea" varchar(50) NULL,
 "us_pf_fec_crea" date NULL,
 "us_pf_usu_mod"  varchar(50) NULL,
 "us_pf_fec_mod"  date NULL,
 CONSTRAINT "FK_92" FOREIGN KEY ( "us_id" ) REFERENCES "public"."usuarios" ( "us_id" ),
 CONSTRAINT "FK_99" FOREIGN KEY ( "pf_id" ) REFERENCES "public"."perfiles" ( "pf_id" )
);

CREATE UNIQUE INDEX "PK_usuario_perfil" ON "public"."usuario_perfil"
(
 "us_id",
 "pf_id"
);

CREATE INDEX "fkIdx_92" ON "public"."usuario_perfil"
(
 "us_id"
);

CREATE INDEX "fkIdx_99" ON "public"."usuario_perfil"
(
 "pf_id"
);
-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "public"."empresas"

CREATE TABLE "public"."empresas"
(
 "em_id"          integer NOT NULL GENERATED ALWAYS AS IDENTITY (
 start 1
 ),
 "em_codigo"      varchar(20) NULL,
 "em_razon_soc"   varchar(200) NULL,
 "em_giro"        varchar(200) NULL,
 "em_sigla"       varchar(50) NULL,
 "em_rep_legal"   varchar(300) NULL,
 "em_direccion"   varchar(300) NULL,
 "em_cod_pais"    varchar(5) NULL,
 "em_cod_prov"    varchar(5) NULL,
 "em_cod_dep"     varchar(5) NULL,
 "em_telf"        varchar(20) NULL,
 "em_email"       varchar(20) NULL,
 "em_reg_esp"     varchar(2) NULL,
 "em_ruta_logo"   varchar(200) NULL,
 "em_tipo"        varchar(10) NULL,
 "em_ruc"         varchar(20) NULL,
 "em_abi_cerr"    varchar(10) NULL,
 "em_rut_rep_leg" varchar(50) NULL,
 "em_ciudad"      varchar(50) NULL,
 "em_reg_lab"     varchar(50) NULL,
 "em_site_web"    varchar(50) NULL

);

CREATE UNIQUE INDEX "PK_empresas" ON "public"."empresas"
(
 "em_id"
);
-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "public"."usuario_empresa"

CREATE TABLE "public"."usuario_empresa"
(
 "us_id"                    integer NOT NULL,
 "em_id"                    integer NOT NULL,
 "usuario_empresa_usu_crea" varchar(100) NULL,
 "usuario_empresa_fec_crea" date NULL,
 "usuario_empresa_usu_mod"  varchar(100) NULL,
 "usuario_empresa_fec_mod"  varchar(100) NULL,
 CONSTRAINT "FK_127" FOREIGN KEY ( "us_id" ) REFERENCES "public"."usuarios" ( "us_id" ),
 CONSTRAINT "FK_131" FOREIGN KEY ( "em_id" ) REFERENCES "public"."empresas" ( "em_id" )
);

CREATE UNIQUE INDEX "PK_usuario_empresa" ON "public"."usuario_empresa"
(
 "us_id",
 "em_id"
);

CREATE INDEX "fkIdx_127" ON "public"."usuario_empresa"
(
 "us_id"
);

CREATE INDEX "fkIdx_131" ON "public"."usuario_empresa"
(
 "em_id"
);
-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "public"."almacen"

CREATE TABLE "public"."almacen"
(
 "al_id"          integer NOT NULL GENERATED ALWAYS AS IDENTITY (
 start 1
 cycle
 ),
 "al_codigo"      varchar(100) NULL,
 "al_descripcion" varchar(300) NOT NULL

);

CREATE UNIQUE INDEX "PK_almacen" ON "public"."almacen"
(
 "al_id"
);

-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "public"."parametros"

CREATE TABLE "public"."parametros"
(
 "pa_id"          integer NOT NULL GENERATED ALWAYS AS IDENTITY (
 start 1
 cycle
 ),
 "pa_descripcion" varchar(100) NULL,
 "pa_abreviatura" varchar(200) NOT NULL,
 "pa_sbn"         varchar(50) NULL,
 "pa_activo"      varchar(2) NULL,
 "pa_visible"     varchar(2) NULL,
 "pa_valor"       varchar(50) NULL,
 "pa_nombre"      varchar(50) NULL

);

CREATE UNIQUE INDEX "PK_linea_articulo" ON "public"."parametros"
(
 "pa_id"
);
-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "public"."proveedor"

CREATE TABLE "public"."proveedor"
(
 "pv_id"              integer NOT NULL GENERATED ALWAYS AS IDENTITY (
 start 1
 cycle
 ),
 "pv_codigo"          varchar(50) NULL,
 "pv_rut"             varchar(50) NULL,
 "pv_nombre"          varchar(50) NULL,
 "pv_giro"            varchar(50) NULL,
 "pv_sigla"           varchar(50) NULL,
 "pv_direccion"       varchar(200) NULL,
 "pv_departamento"    varchar(50) NULL,
 "pv_provincia"       varchar(50) NULL,
 "pv_distrito"        varchar(50) NULL,
 "pv_ciudad"          varchar(100) NULL,
 "pv_telefono"        varchar(50) NULL,
 "pv_email"           varchar(100) NULL,
 "pv_ficha_activa"    varchar(2) NULL,
 "pv_pais"            varchar(50) NULL,
 "pv_fax"             varchar(50) NULL,
 "pv_casilla"         varchar(50) NULL,
 "pv_sitio_web"       varchar(100) NULL,
 "pv_rubro"           varchar(50) NULL,
 "pv_nom_contacto"    varchar(50) NULL,
 "pv_dir_contacto"    varchar(200) NULL,
 "pv_telf_contacto"   varchar(100) NULL,
 "pv_cell_contacto"   varchar(50) NULL,
 "pv_email_contacto"  varchar(100) NULL,
 "pv_ciudad_contacto" varchar(100) NULL,
 "pv_pais_contacto"   varchar(100) NULL

);

CREATE UNIQUE INDEX "PK_proveedor" ON "public"."proveedor"
(
 "pv_id"
);
-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "public"."regimen_laboral"

CREATE TABLE "public"."regimen_laboral"
(
 "rl_id"       integer NOT NULL GENERATED ALWAYS AS IDENTITY (
 start 1
 cycle
 ),
 "rl_tipo"     varchar(100) NULL,
 "rl_jld"      varchar(100) NULL,
 "rl_phe"      decimal(3,2) NULL,
 "rl_usu_crea" varchar(100) NULL,
 "rl_fec_crea" date NULL,
 "rl_usu_mod"  varchar(100) NULL,
 "rl_fec_mod"  date NULL

);

CREATE UNIQUE INDEX "PK_regimen_laboral" ON "public"."regimen_laboral"
(
 "rl_id"
);
-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "public"."empresa_reg_lab"

CREATE TABLE "public"."empresa_reg_lab"
(
 "em_id" integer NOT NULL,
 "rl_id" integer NOT NULL,
 CONSTRAINT "FK_120" FOREIGN KEY ( "em_id" ) REFERENCES "public"."empresas" ( "em_id" ),
 CONSTRAINT "FK_123" FOREIGN KEY ( "rl_id" ) REFERENCES "public"."regimen_laboral" ( "rl_id" )
);

CREATE UNIQUE INDEX "PK_emprea_reg_lab" ON "public"."empresa_reg_lab"
(
 "em_id",
 "rl_id"
);

CREATE INDEX "fkIdx_120" ON "public"."empresa_reg_lab"
(
 "em_id"
);

CREATE INDEX "fkIdx_123" ON "public"."empresa_reg_lab"
(
 "rl_id"
);
-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "public"."rubro"

CREATE TABLE "public"."rubro"
(
 "rb_id"          integer NOT NULL GENERATED ALWAYS AS IDENTITY (
 start 1
 cycle
 ),
 "rb_descripcion" varchar(100) NULL

);

CREATE UNIQUE INDEX "PK_rubro" ON "public"."rubro"
(
 "rb_id"
);
-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "public"."tipo_movimiento"

CREATE TABLE "public"."tipo_movimiento"
(
 "tm_id"          integer NOT NULL GENERATED ALWAYS AS IDENTITY (
 start 1
 cycle
 ),
 "tm_descripcion" varchar(200) NULL

);

CREATE UNIQUE INDEX "PK_tipo_movimiento" ON "public"."tipo_movimiento"
(
 "tm_id"
);

-- *************** SqlDBM: PostgreSQL ****************;
-- ***************************************************;


-- ************************************** "public"."unidad_medida"

CREATE TABLE "public"."unidad_medida"
(
 "um_id"     integer NOT NULL GENERATED ALWAYS AS IDENTITY (
 start 1
 cycle
 ),
 "um_abrev"  varchar(50) NULL,
 "um_nombre" varchar(100) NULL

);

CREATE UNIQUE INDEX "PK_unidad_medida" ON "public"."unidad_medida"
(
 "um_id"
);


















































































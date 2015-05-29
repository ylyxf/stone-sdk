
/* Drop Tables */

DROP TABLE IF EXISTS public.st_config;
DROP TABLE IF EXISTS public.st_enum_value;
DROP TABLE IF EXISTS public.st_data_filter_item;
DROP TABLE IF EXISTS public.st_data_filter;
DROP TABLE IF EXISTS public.st_dict_item;
DROP TABLE IF EXISTS public.st_dict;
DROP TABLE IF EXISTS public.st_group_user;
DROP TABLE IF EXISTS public.st_group;
DROP TABLE IF EXISTS public.st_role_user;
DROP TABLE IF EXISTS public.st_role_operation;
DROP TABLE IF EXISTS public.st_role;
DROP TABLE IF EXISTS public.st_user;




/* Create Tables */

CREATE TABLE public.st_config
(
	-- 类的属性名
	code varchar(120) NOT NULL,
	
	-- 类名
	class_code varchar(256) NOT NULL,
	
	-- 值
	value varchar(256)
	
	CONSTRAINT st_config_pkey PRIMARY KEY (code, class_code)
) WITHOUT OIDS;



CREATE TABLE public.st_data_filter
(
	-- 数据过滤器
	id serial NOT NULL,
	-- 名称
	name varchar(64),
	-- 备注
	remark varchar(256),
	CONSTRAINT pk_st_data_filter PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE public.st_data_filter_item
(
	-- 表达式id
	id serial NOT NULL,
	-- 前缀
	prefix_code varchar(5),
	-- 列编码
	column_code varchar(64),
	-- 操作符
	compare_symbol varchar(64),
	-- 第一值
	first_value varchar(2000),
	-- 第二值
	second_value varchar(2000),
	-- 后缀
	suffix_code varchar(5),
	-- 数值类型
	data_type varchar(128),
	-- 表达式类型
	type varchar(16),
	-- 序号
	sort_no int,
	-- 数据过滤器
	data_filter_id int,
	CONSTRAINT pk_st_data_filter_item PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE public.st_dict
(
	code varchar(64) NOT NULL,
	name varchar(20),
	sort_no int,
	remark varchar(100),
	CONSTRAINT agv_dictionary_pkey PRIMARY KEY (code)
) WITHOUT OIDS;


CREATE TABLE public.st_dict_item
(
	id serial NOT NULL,
	label varchar(20),
	value varchar(20),
	sort_no int,
	remark varchar(50),
	dict_code varchar(64),
	CONSTRAINT agv_dictionary_itme_pkey PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE public.st_enum_value
(
	id serial NOT NULL,
	-- 表达式id
	data_filter_item_id int,
	data_value varchar(64),
	CONSTRAINT pk_st_enum_value PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE public.st_group
(
	-- 群组id
	id serial NOT NULL,
	-- 父群组id
	parent_id int,
	-- 群组编码
	code varchar(20),
	-- 群组名称
	name varchar(20),
	-- 是否叶子节点
	is_leaf boolean,
	-- 序号
	sort_no int,
	-- 组织类型
	type varchar(20),
	-- 逻辑删除
	logic_deleted boolean,
	CONSTRAINT agv_group_pkey PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE public.st_group_user
(
	-- id
	id serial NOT NULL,
	-- 用户Id
	user_id int NOT NULL,
	-- 群组id
	group_id int NOT NULL,
	-- 是否默认群组
	is_default boolean DEFAULT 'false',
	-- 序号
	sort_no int,
	CONSTRAINT agv_group_re_user_pkey PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE public.st_role
(
	-- 角色Id
	id serial NOT NULL,
	-- 角色名称
	name varchar(64),
	-- 是否启用
	enabled boolean,
	-- 序号
	sort_no int,
	CONSTRAINT agv_role_pkey PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE public.st_role_operation
(
	-- 主键
	id serial NOT NULL,
	-- 角色Id
	role_id int NOT NULL,
	-- 操作代码
	operation_code varchar(100),
	-- 数据集合Id
	data_filter_id int,
	CONSTRAINT agv_role_operation_pkey PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE public.st_role_user
(
	id serial NOT NULL,
	-- 用户Id
	user_id int NOT NULL,
	-- 角色Id
	role_id int NOT NULL,
	CONSTRAINT agv_role_user_pkey PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE public.st_user
(
	-- 用户Id
	id serial NOT NULL,
	-- 账户
	account varchar(64) NOT NULL,
	-- 密码
	password varchar(256),
	-- 用户名
	name varchar(64),
	-- 手机号码
	phone varchar(64),
	-- 电子邮箱
	email varchar(64),
	-- 是否可用
	enabled boolean,
	-- 是否删除
	logic_deleted boolean,
	CONSTRAINT agv_user_pkey PRIMARY KEY (id)
) WITHOUT OIDS;





ALTER TABLE public.st_data_filter_item
	ADD CONSTRAINT fk_st_data__reference_st_data_ FOREIGN KEY (data_filter_id)
	REFERENCES public.st_data_filter (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE public.st_enum_value
	ADD CONSTRAINT fk_st_enum__reference_st_data_ FOREIGN KEY (data_filter_item_id)
	REFERENCES public.st_data_filter_item (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE public.st_dict_item
	ADD FOREIGN KEY (dict_code)
	REFERENCES public.st_dict (code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE public.st_group_user
	ADD FOREIGN KEY (group_id)
	REFERENCES public.st_group (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE public.st_role_user
	ADD CONSTRAINT agv_role_user_role_id_fkey FOREIGN KEY (role_id)
	REFERENCES public.st_role (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE public.st_role_operation
	ADD CONSTRAINT agv_role_operation_role_id_fkey FOREIGN KEY (role_id)
	REFERENCES public.st_role (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE public.st_group_user
	ADD CONSTRAINT agv_group_re_user_user_id_fkey FOREIGN KEY (user_id)
	REFERENCES public.st_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE public.st_role_user
	ADD CONSTRAINT agv_role_user_user_id_fkey FOREIGN KEY (user_id)
	REFERENCES public.st_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



/* Comments */

COMMENT ON COLUMN public.st_config.code IS '类的属性名';
COMMENT ON COLUMN public.st_config.class_code IS '类名';
COMMENT ON COLUMN public.st_config.value IS '值';
COMMENT ON COLUMN public.st_data_filter.id IS '数据过滤器';
COMMENT ON COLUMN public.st_data_filter.name IS '名称';
COMMENT ON COLUMN public.st_data_filter.remark IS '备注';
COMMENT ON COLUMN public.st_data_filter_item.id IS '表达式id';
COMMENT ON COLUMN public.st_data_filter_item.prefix_code IS '前缀';
COMMENT ON COLUMN public.st_data_filter_item.column_code IS '列编码';
COMMENT ON COLUMN public.st_data_filter_item.compare_symbol IS '操作符';
COMMENT ON COLUMN public.st_data_filter_item.first_value IS '第一值';
COMMENT ON COLUMN public.st_data_filter_item.second_value IS '第二值';
COMMENT ON COLUMN public.st_data_filter_item.suffix_code IS '后缀';
COMMENT ON COLUMN public.st_data_filter_item.data_type IS '数值类型';
COMMENT ON COLUMN public.st_data_filter_item.type IS '表达式类型';
COMMENT ON COLUMN public.st_data_filter_item.sort_no IS '序号';
COMMENT ON COLUMN public.st_data_filter_item.data_filter_id IS '数据过滤器';
COMMENT ON COLUMN public.st_enum_value.data_filter_item_id IS '表达式id';
COMMENT ON COLUMN public.st_group.id IS '群组id';
COMMENT ON COLUMN public.st_group.parent_id IS '父群组id';
COMMENT ON COLUMN public.st_group.code IS '群组编码';
COMMENT ON COLUMN public.st_group.name IS '群组名称';
COMMENT ON COLUMN public.st_group.is_leaf IS '是否叶子节点';
COMMENT ON COLUMN public.st_group.sort_no IS '序号';
COMMENT ON COLUMN public.st_group.type IS '组织类型';
COMMENT ON COLUMN public.st_group.logic_deleted IS '逻辑删除';
COMMENT ON COLUMN public.st_group_user.id IS 'id';
COMMENT ON COLUMN public.st_group_user.user_id IS '用户Id';
COMMENT ON COLUMN public.st_group_user.group_id IS '群组id';
COMMENT ON COLUMN public.st_group_user.is_default IS '是否默认群组';
COMMENT ON COLUMN public.st_group_user.sort_no IS '序号';
COMMENT ON COLUMN public.st_role.id IS '角色Id';
COMMENT ON COLUMN public.st_role.name IS '角色名称';
COMMENT ON COLUMN public.st_role.enabled IS '是否启用';
COMMENT ON COLUMN public.st_role.sort_no IS '序号';
COMMENT ON COLUMN public.st_role_operation.id IS '主键';
COMMENT ON COLUMN public.st_role_operation.role_id IS '角色Id';
COMMENT ON COLUMN public.st_role_operation.operation_code IS '操作代码';
COMMENT ON COLUMN public.st_role_operation.data_filter_id IS '数据集合Id';
COMMENT ON COLUMN public.st_role_user.user_id IS '用户Id';
COMMENT ON COLUMN public.st_role_user.role_id IS '角色Id';
COMMENT ON COLUMN public.st_user.id IS '用户Id';
COMMENT ON COLUMN public.st_user.account IS '账户';
COMMENT ON COLUMN public.st_user.password IS '密码';
COMMENT ON COLUMN public.st_user.name IS '用户名';
COMMENT ON COLUMN public.st_user.phone IS '手机号码';
COMMENT ON COLUMN public.st_user.email IS '电子邮箱';
COMMENT ON COLUMN public.st_user.enabled IS '是否可用';
COMMENT ON COLUMN public.st_user.logic_deleted IS '是否删除';


/**
 * 获得树的路径函数
 */
CREATE OR REPLACE FUNCTION get_path (
  current_id integer,
  table_name varchar
)
RETURNS varchar AS
$body$
DECLARE
	v_sqlstring varchar(240); 
    v_result character varying;
    v_current_name character varying;
    v_parent_id integer;
BEGIN
	v_sqlstring :=  'SELECT parent_id  FROM ' ||table_name ||' WHERE id ='||current_id||';';
    EXECUTE v_sqlstring into v_parent_id; 
        IF v_parent_id is not NULL THEN
        	v_sqlstring :=  'SELECT name  FROM ' ||table_name ||' WHERE id ='||current_id||';';
        	EXECUTE v_sqlstring into v_current_name; 
            v_result := get_path(v_parent_id,table_name) || '/' || v_current_name;
        ELSE
            RETURN '';
        END IF;
    RETURN v_result;
END;
$body$
LANGUAGE 'plpgsql'
VOLATILE
CALLED ON NULL INPUT
SECURITY INVOKER
COST 100;


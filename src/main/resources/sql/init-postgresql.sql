INSERT INTO st_user ("id", "account", "password", "name", "phone", "email", "enabled", "logic_deleted")
VALUES (0,  'admin', 'admin',  '管理员', NULL, NULL, true, false);

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
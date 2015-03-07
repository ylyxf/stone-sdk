--用户表
DROP TABLE  IF EXISTS st_user;

CREATE TABLE  st_user (
  id SERIAL NOT NULL,
  account VARCHAR(64) NOT NULL,
  password VARCHAR(256),
  name VARCHAR(64),
  phone_number VARCHAR(64),
  email VARCHAR(64),
  enabled BOOLEAN,
  deleted BOOLEAN,
  CONSTRAINT agv_user_pkey PRIMARY KEY(id)
) ;

COMMENT ON COLUMN st_user.id
IS '用户Id';

COMMENT ON COLUMN st_user.account
IS '账户';

COMMENT ON COLUMN st_user.password
IS '密码';

COMMENT ON COLUMN st_user.name
IS '用户名';

COMMENT ON COLUMN st_user.mobile_phone_no
IS '手机号码';

COMMENT ON COLUMN st_user.email
IS '电子邮箱';

COMMENT ON COLUMN st_user.enabled
IS '是否可用';

COMMENT ON COLUMN st_user.deleted
IS '是否删除';

--角色表
DROP TABLE  IF EXISTS st_role;

CREATE TABLE st_role (
  id SERIAL NOT NULL,
  name VARCHAR(64),
  enabled BOOLEAN,
  remark VARCHAR(64),
  CONSTRAINT agv_role_pkey PRIMARY KEY(id)
);

COMMENT ON COLUMN st_role.id
IS '角色Id';

COMMENT ON COLUMN st_role.name
IS '角色名称';

COMMENT ON COLUMN st_role.enabled
IS '是否启用';

--用户角色表
DROP TABLE  IF EXISTS st_role_user;
CREATE TABLE st_role_user (
  id SERIAL,
  user_id INTEGER NOT NULL,
  role_id INTEGER NOT NULL,
  CONSTRAINT agv_role_user_pkey PRIMARY KEY(id),
  CONSTRAINT agv_role_user_role_id_fkey FOREIGN KEY (role_id)
    REFERENCES st_role(id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
    NOT DEFERRABLE,
  CONSTRAINT agv_role_user_user_id_fkey FOREIGN KEY (user_id)
    REFERENCES st_user(id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
    NOT DEFERRABLE
)  ;

COMMENT ON COLUMN st_role_user.user_id
IS '用户Id';

COMMENT ON COLUMN st_role_user.role_id
IS '角色Id';
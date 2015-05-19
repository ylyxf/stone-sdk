INSERT INTO st_user ("id", "account", "password", "name", "phone", "email", "enabled", "logic_deleted")
VALUES (0,  'admin', 'admin',  '管理员', NULL, NULL, true, false);

INSERT INTO st_group ("id", "parent_id", "code", "name", "is_leaf", "sort_no", "type", "logic_deleted")
VALUES (0, -1, 'root', '组织结构', true, 1, '', false);

INSERT INTO st_group_user ("id", "user_id", "group_id", "is_default", "sort_no")
VALUES (0, 0, 0, true, 1);
 
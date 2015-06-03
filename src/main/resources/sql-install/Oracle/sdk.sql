INSERT INTO st_user (id, account, password, name, phone, email, enabled, logic_deleted)
VALUES (1,  'admin', '',  '管理员', NULL, NULL, '0', '0')^_^

INSERT INTO st_group (id, parent_id, code, name, is_leaf, sort_no, type, logic_deleted)
VALUES (1, -1, 'root', '组织结构', '1', 1, '', '0')^_^

INSERT INTO st_group_user (id, user_id, group_id, is_default, sort_no)
VALUES (0, 1, 1, '1', 1)^_^
 
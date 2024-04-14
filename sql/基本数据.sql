INSERT INTO quiz.t_role (role_name)
VALUES ('ADMIN'),
       ('USER');

INSERT INTO quiz.t_permission (permission_name)
VALUES ('CREATE'),
       ('READ'),
       ('UPDATE'),
       ('DELETE');

-- ADMIN 角色权限
INSERT INTO quiz.role_permissions (role_id, permission_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4);

-- USER 角色权限
INSERT INTO role_permissions (role_id, permission_id)
VALUES (2, 2);

-- 添加测试用户
INSERT INTO quiz.t_user (username, password, created_at)
VALUES ('admin', '$2a$10$CyxrV5piFp1AdrNAzBlLHeGYCI1kr4ajrulkyJsZTlAeRz9.ENL7q', sysdate()),
       ('user', '$2a$10$qWlGYKJQF.Mv7P1KcmV8nuHSLF8HqcBT91/nts5h9h7lUtfHBFfwq', sysdate());

-- 添加用户角色
INSERT INTO quiz.user_roles (user_id, role_id)
VALUES ('1', '1');

-- 添加路径所需权限
INSERT INTO quiz.t_path (pattern, permission_id)
VALUES ('/t-permission/test3/{current:\d+}/{size:\d+}', '1'),
       ('/t-permission/test2', '2'),
       ('/t-permission/test1', null);
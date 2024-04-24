INSERT INTO quiz.t_role (role_name)
VALUES ('ADMIN'),
       ('USER');

INSERT INTO quiz.t_permission (permission_name)
VALUES ('CREATE'),
       ('READ'),
       ('UPDATE'),
       ('DELETE');

-- ADMIN 角色权限
INSERT INTO quiz.t_role_permissions (role_id, permission_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4);

-- USER 角色权限
INSERT INTO quiz.t_role_permissions (role_id, permission_id)
VALUES (2, 2);

-- 添加测试用户
INSERT INTO quiz.t_user (username, password, created_at)
VALUES ('admin', '$2a$10$CyxrV5piFp1AdrNAzBlLHeGYCI1kr4ajrulkyJsZTlAeRz9.ENL7q', sysdate()),
       ('user', '$2a$10$qWlGYKJQF.Mv7P1KcmV8nuHSLF8HqcBT91/nts5h9h7lUtfHBFfwq', sysdate());

-- 添加用户角色
INSERT INTO quiz.t_user_roles (user_id, role_id)
VALUES ('1', '1');

-- 添加路径所需权限
INSERT INTO quiz.t_path (pattern, permission_id)
VALUES ('/permission/test3/{current:\\d+}/{size:\\d+}', '1'),
       ('/permission/test2', '2'),
       ('/permission/test1', null);

-- 题目数据
insert into quiz.q_question
SELECT id      as question_id
     , title
     , options as options_
     , created_at
     , updated_at
FROM questions_and_answers.question
;

-- 添加试卷标签
INSERT INTO quiz.q_tag (tag_id, tag_name)
VALUES ('1', '测试标签1'),
       ('2', '测试标签2'),
       ('3', '测试标签3'),
       ('4', '测试标签4'),
       ('5', '测试标签5');
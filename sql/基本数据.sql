INSERT INTO quiz.t_role (role_name, describe_)
VALUES ('ADMIN', '管理员'),
       ('USER'.'普通用户'),
       ('DING_DONG'.'勇者管理员');

INSERT INTO quiz.t_permission (permission_name, describe_)
VALUES ('CREATE', '添加'),
       ('READ', '读取'),
       ('UPDATE', '更新'),
       ('DELETE', '删除'),
       ('DD_CHANGE', '勇者的修改权限'),
       ('UPDATE_OWN', '修改用户自己');

-- ADMIN 角色权限
INSERT INTO quiz.t_role_permissions (role_id, permission_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 2);
    (2, 6);
    (3, 5);

-- 添加测试用户
INSERT INTO quiz.t_user (username, password, created_at)
VALUES ('admin', '$2a$10$CyxrV5piFp1AdrNAzBlLHeGYCI1kr4ajrulkyJsZTlAeRz9.ENL7q', sysdate()),
       ('user', '$2a$10$qWlGYKJQF.Mv7P1KcmV8nuHSLF8HqcBT91/nts5h9h7lUtfHBFfwq', sysdate());

-- 添加用户角色
INSERT INTO quiz.t_user_roles (user_id, role_id)
VALUES ('1', '1'),
       ('2', '2');

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

-- 添加道具
INSERT INTO quiz.q_prop (prop_id, prop_name, prop_describe)
VALUES ('1', '成就分数', '所有答题的统计分'),
       ('2', '复活宝石', '复活宝石让尊贵的您获得重新再次答题的机会！'),
       ('3', '提示宝石', '提示宝石让尊贵的您答题时获得答案相关的提示！');
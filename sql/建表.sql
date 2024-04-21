create database quiz;

create table if not exists quiz.t_user
(
    user_id       int unsigned auto_increment comment '主键，用户ID。',
    nickname      varchar(64)      null comment '用户昵称',
    avatar_url    varchar(255)     null comment '头像地址',
    username      varchar(64)      null comment '用户账号',
    password      varchar(64)      null comment '密码',
    phone         varchar(32)      null comment '手机号',
    mail          varchar(64)      null comment '邮箱',
    created_at    datetime         not null comment '创建时间',
    updated_at    datetime         null comment '更新时间',
    last_login_at datetime         null comment '用户上次登录时间',
    enabled       bigint default 0 not null comment '账号是否启用:0=正常,-1=禁用',
    primary key (user_id),
    INDEX username (username),
    INDEX phone (phone),
    INDEX mail (mail),
    INDEX username_phone_mail (username, phone, mail)
) comment '用户信息表';

create table if not exists quiz.m_user_auth
(
    auth_id     int unsigned auto_increment comment '主键，ID。',
    user_id     int unsigned not null comment '用户ID。',
    provider    varchar(32)  not null comment '第三方服务的名称（如Google、Facebook等）。',
    provider_id varchar(64)  not null comment '第三方提供的唯一标识符。比如微信的openId',
    primary key (auth_id),
    INDEX user_id (user_id),
    INDEX provider_id (provider_id)
) comment '第三方登录表';

create table if not exists quiz.m_role
(
    role_id   int auto_increment comment '主键，角色ID。',
    role_name varchar(255) not null comment '角色名称，如ROLE_ADMIN、ROLE_USER。',
    primary key (role_id)
) comment '角色表';

create table if not exists quiz.m_permission
(
    permission_id   int auto_increment comment '主键，权限ID。',
    permission_name varchar(255) not null comment '权限名称，如read、write。',
    primary key (permission_id)
) comment '权限表';

create table if not exists quiz.t_path
(
    path_id       int auto_increment comment '主键，路径ID。',
    pattern       varchar(255) not null comment '路径模式，如/api/users/**，/device/list/{current:\d+}/{size:\d+}。',
    http_method   varchar(255) null comment 'HTTP 方法,如 POST, GET',
    permission_id varchar(255) null comment '关联权限ID。',
    primary key (path_id),
    INDEX permission_id (permission_id)
) comment '路径表';

create table if not exists quiz.user_roles
(
    user_id int not null comment '关联用户ID。',
    role_id int not null comment '关联角色ID。',
    INDEX user_id (user_id),
    INDEX role_id (role_id),
    INDEX user_id_role_id (user_id, role_id)
) comment '用户角色关联表';

create table if not exists quiz.role_permissions
(
    role_id       int not null comment '关联角色ID。',
    permission_id int not null comment '关联权限ID。',
    INDEX role_id (role_id),
    INDEX permission_id (permission_id),
    INDEX role_id_permission_id (role_id, permission_id)
) comment '角色权限关联表';

create table quiz.t_question
(
    question_id int auto_increment comment '唯一主键',
    class_code  int                                null comment '类型代码',
    title       varchar(512)                       not null comment '题目',
    options_    varchar(1024)                      not null comment '选项',
    created_at  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at  datetime                           null comment '修改时间',
    primary key (question_id)
) comment '题目表';

create table quiz.m_question_class
(
    class_id   int auto_increment comment '唯一主键',
    class_code int          not null comment '类型代码',
    class_name varchar(128) null comment '类型名称',
    primary key (class_id)
) comment '题目类别';

create table quiz.question_paper
(
    paper_id  int auto_increment comment '唯一主键',
    user_id   int comment '出题用户id',
    questions varchar(128) not null comment '题目id集合,以","分割',
    answers   varchar(64) comment '答案下标集合,以","分割',
    primary key (paper_id)
) comment '题目试卷';

create table quiz.question_selects
(
    answer_id int auto_increment comment '唯一主键',
    paper_id  int comment '关联试卷id',
    user_id   int comment '答题用户id',
    selects   varchar(64) comment '选择下标集合,以","分割',
    primary key (answer_id)
) comment '题目答卷';
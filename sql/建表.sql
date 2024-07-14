create database quiz;

create table if not exists quiz.t_user
(
    user_id       int unsigned auto_increment comment '主键，用户ID。',
    nickname      varchar(63)                        null comment '用户昵称',
    avatar_url    varchar(255)                       null comment '头像地址',
    username      varchar(63)                        null comment '用户账号',
    password      varchar(63)                        null comment '密码',
    phone         varchar(31)                        null comment '手机号',
    mail          varchar(63)                        null comment '邮箱',
    created_at    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at    datetime                           null comment '更新时间',
    last_login_at datetime                           null comment '用户上次登录时间',
    enabled       bigint   default 0                 not null comment '账号是否启用:0=正常,-1=禁用',
    primary key (user_id),
    INDEX username (username),
    INDEX phone (phone),
    INDEX mail (mail),
    INDEX username_phone_mail (username, phone, mail)
) comment '用户信息表';

create table if not exists quiz.t_user_auth
(
    auth_id     int unsigned auto_increment comment '主键，ID。',
    user_id     int unsigned not null comment '用户ID。',
    provider    varchar(31)  not null comment '第三方服务的名称（如Google、Facebook等）。',
    provider_id varchar(63)  not null comment '第三方提供的唯一标识符。比如微信的openId',
    primary key (auth_id),
    INDEX user_id (user_id),
    INDEX provider_id (provider_id)
) comment '第三方登录表';

create table if not exists quiz.t_role
(
    role_id   int auto_increment comment '主键，角色ID。',
    role_name varchar(255) not null comment '角色名称，如ROLE_ADMIN、ROLE_USER。',
    primary key (role_id)
) comment '角色表';

create table if not exists quiz.t_permission
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
    describe_     varchar(255) null comment '路径描述。',
    permission_id varchar(255) null comment '关联权限ID。',
    primary key (path_id),
    INDEX permission_id (permission_id)
) comment '路径表';

create table if not exists quiz.t_user_roles
(
    user_id int not null comment '关联用户ID。',
    role_id int not null comment '关联角色ID。',
    INDEX user_id (user_id),
    INDEX role_id (role_id),
    INDEX user_id_role_id (user_id, role_id)
) comment '用户角色关联表';

create table if not exists quiz.t_role_permissions
(
    role_id       int not null comment '关联角色ID。',
    permission_id int not null comment '关联权限ID。',
    INDEX role_id (role_id),
    INDEX permission_id (permission_id),
    INDEX role_id_permission_id (role_id, permission_id)
) comment '角色权限关联表';

create table if not exists quiz.q_paper
(
    paper_id        int auto_increment comment '唯一主键',
    creator_user_id int comment '出题用户id',
    cover_url       varchar(255) comment '封面url',
    title           varchar(255)                       not null comment '标题',
    describe_       varchar(511) comment '描述',
    answers         varchar(63) comment '答案下标集合,以@@分隔',
    created_at      datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    primary key (paper_id),
    INDEX user_id (creator_user_id)
) comment '题目试卷';

create table if not exists quiz.q_tag
(
    tag_id   int auto_increment comment '唯一主键',
    tag_name varchar(127) null comment '标签名称',
    primary key (tag_id)
) comment '试卷标签';

create table if not exists quiz.q_paper_tags
(
    paper_id int not null comment '试卷ID。',
    tag_id   int not null comment '标签ID。',
    INDEX paper_id (paper_id),
    INDEX tag_id (tag_id),
    INDEX paper_id_tag_id (paper_id, tag_id)
) comment '试卷关联标签';

create table if not exists quiz.q_question
(
    question_id int auto_increment comment '唯一主键',
    title       varchar(511)                       not null comment '题目',
    options_    varchar(1023)                      not null comment '选项,以@@分隔',
    created_at  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at  datetime                           null comment '修改时间',
    primary key (question_id)
) comment '题目表';

create table if not exists quiz.q_paper_questions
(
    paper_id    int not null comment '试卷ID。',
    question_id int not null comment '题目ID。',
    INDEX paper_id (paper_id),
    INDEX question_id (question_id),
    INDEX paper_id_question_id (paper_id, question_id)
) comment '试卷关联题目';

create table if not exists quiz.q_answers
(
    answer_id         int auto_increment comment '唯一主键',
    paper_id          int comment '关联试卷id',
    responder_user_id int comment '答题用户id',
    selects           varchar(63) comment '选择下标集合,以@@分隔',
    created_at        datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    primary key (answer_id),
    INDEX paper_id (paper_id),
    INDEX user_id (responder_user_id)
) comment '题目答卷';

create table if not exists quiz.q_classes
(
    class_id   int auto_increment comment '唯一主键',
    user_id    int comment '出题用户id(哪个用户的类别)',
    class_name varchar(255)                       not null comment '类别名称',
    sort       int                                not null comment '类别排序',
    created_at datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at datetime comment '修改时间',
    primary key (class_id),
    INDEX user_id (user_id)
) comment '试卷分类';

create table if not exists quiz.q_paper_classes
(
    paper_id int not null comment '试卷ID。',
    class_id int not null comment '分类ID。',
    INDEX paper_id (paper_id),
    INDEX question_id (class_id),
    INDEX paper_id_question_id (paper_id, class_id)
) comment '试卷关联试卷分类';


# 勇者相关
create table yz_device
(
    device_id varchar(18)    not null comment '安卓设备ID',
    username  varchar(255)   not null comment '用户名',
    end_time  datetime       null comment '过期时间',
    lv        int default -1 null comment '-1封禁,0试用,1vip,2svip,3max',
    primary key (device_id),
    INDEX username (username)
) comment '设备卡密信息';

create table yz_use_info
(
    use_id        int auto_increment comment '唯一主键',
    username      varchar(64)  not null comment '卡号',
    account_      varchar(64)  not null comment '账号(游戏的)',
    password      varchar(64)  null comment '密码(游戏的)',
    function_name varchar(128) null comment '调用的方法名称',
    server_id     int          null comment '区服id',
    server_name   varchar(64)  null comment '区服名称',
    brave_id      int          null comment '勇者id',
    player_key    varchar(32)  null comment '玩家key',
    player_name   varchar(64)  null comment '玩家昵称',
    role_id       int          null comment '玩家角色id',
    role_name     varchar(32)  null comment '玩家角色名称',
    date_time     datetime     null comment '使用时间',
    primary key (use_id),
    INDEX username (username),
    INDEX account_ (account_)
) comment '脚本使用信息';
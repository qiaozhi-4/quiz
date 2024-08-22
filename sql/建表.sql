create table if not exists quiz.q_answer
(
    answer_id         int auto_increment comment '主键,答卷id'
        primary key,
    paper_id          int                                null comment '关联试卷id',
    responder_user_id int                                null comment '答题用户id',
    score             int                                null comment '得分',
    created_at        datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at        datetime                           null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '题目答卷';

create index paper_id
    on quiz.q_answer (paper_id);

create index user_id
    on quiz.q_answer (responder_user_id);

create table if not exists quiz.q_answer_questions
(
    aq_id             int auto_increment comment '主键'
        primary key,
    answer_id         int                                null comment '答卷id',
    question_id       int                                null comment '题目ID',
    aq_select_index   int                                null comment '答题人选择下标',
    aq_extra_describe varchar(255)                       null comment '答题人额外描述',
    created_at        datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at        datetime                           null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '答题关联题目';

create index paper_id
    on quiz.q_answer_questions (answer_id);

create index question_id
    on quiz.q_answer_questions (question_id);

create table if not exists quiz.q_classes
(
    id                 int auto_increment comment '唯一id'
        primary key,
    class_id           int                                null comment '类别id',
    class_name         varchar(255)                       null comment '类别名称',
    class_describe     varchar(255)                       null comment '类别描述',
    sub_class_id       int                                null comment '子类别id',
    sub_class_name     varchar(255)                       null comment '子类别名称',
    sub_class_describe varchar(255)                       null comment '子类别描述',
    created_at         datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at         datetime                           null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '试卷分类';

create index q_classes_class_id_index
    on quiz.q_classes (class_id);

create index q_classes_class_id_sub_class_id_index
    on quiz.q_classes (class_id, sub_class_id);

create index q_classes_sub_class_id_index
    on quiz.q_classes (sub_class_id);

create table if not exists quiz.q_paper
(
    paper_id        int auto_increment comment '主键,试卷id'
        primary key,
    creator_user_id int                                null comment '出题用户id',
    order_          int                                null comment '序号',
    state           int      default 0                 null comment '状态',
    cover_url       varchar(255)                       null comment '封面url',
    title           varchar(255)                       null comment '标题',
    describe_       varchar(511)                       null comment '描述',
    created_at      datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at      datetime                           null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '题目试卷';

create index user_id
    on quiz.q_paper (creator_user_id);

create table if not exists quiz.q_paper_classes
(
    id       int auto_increment comment '主键'
        primary key,
    paper_id int null comment '试卷ID',
    class_id int null comment '分类ID'
)
    comment '试卷关联分类';

create index paper_id
    on quiz.q_paper_classes (paper_id);

create index paper_id_question_id
    on quiz.q_paper_classes (paper_id, class_id);

create index question_id
    on quiz.q_paper_classes (class_id);

create table if not exists quiz.q_paper_questions
(
    pq_id             int auto_increment comment '主键'
        primary key,
    paper_id          int                                null comment '试卷ID',
    question_id       int                                null comment '题目ID',
    pq_select_index   int                                null comment '出题人选择下标',
    pq_extra_describe varchar(255)                       null comment '出题人额外描述',
    created_at        datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at        datetime                           null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '试卷/答题关联题目';

create index paper_id
    on quiz.q_paper_questions (paper_id);

create index question_id
    on quiz.q_paper_questions (question_id);

create table if not exists quiz.q_prop
(
    prop_id       int auto_increment comment '主键,道具id'
        primary key,
    prop_name     varchar(127) null comment '道具名称',
    prop_describe varchar(511) null comment '道具描述'
)
    comment '道具表';

create table if not exists quiz.q_question
(
    question_id int auto_increment comment '主键,题目id'
        primary key,
    title       varchar(511)                       null comment '题目',
    options_    varchar(1023)                      null comment '选项,以@@分隔',
    class_id    int                                null comment '类别id',
    created_at  datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at  datetime                           null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '题目表';

create index class_id
    on quiz.q_question (class_id);

create table if not exists quiz.q_question_tags
(
    id          int auto_increment comment '主键'
        primary key,
    question_id int null comment '题目ID',
    tag_id      int null comment '标签ID'
)
    comment '题目关联标签';

create index paper_id_tag_id
    on quiz.q_question_tags (question_id, tag_id);

create index question_id
    on quiz.q_question_tags (question_id);

create index tag_id
    on quiz.q_question_tags (tag_id);

create table if not exists quiz.q_tag
(
    tag_id   int auto_increment comment '主键,标签id'
        primary key,
    tag_name varchar(127) null comment '标签名称'
)
    comment '题目标签';

create table if not exists quiz.q_task
(
    task_id          int auto_increment comment '主键,任务ID'
        primary key,
    award_id         int                                null comment '奖励id',
    award_number     int      default 1                 null comment '奖励数量',
    describe_        varchar(511)                       null comment '任务描述',
    class_id         int                                null comment '任务条件类别',
    condition_number int      default 1                 null comment '任务条件计数',
    created_at       datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at       datetime                           null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '任务数据';

create index award_id
    on quiz.q_task (award_id);

create index q_task_class_id_index
    on quiz.q_task (class_id);

create table if not exists quiz.q_task_record
(
    id               int auto_increment comment '主键'
        primary key,
    task_id          int                                  null comment '任务ID',
    user_id          int                                  null comment '用户id',
    finish_number    int        default 0                 null comment '任务完成计数',
    is_receive_award tinyint(1) default 0                 null comment '任务奖励是否领取',
    created_at       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at       datetime                             null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '任务记录';

create index task_id
    on quiz.q_task_record (task_id);

create index user_id
    on quiz.q_task_record (user_id);

create table if not exists quiz.q_user_prop
(
    id      int auto_increment comment '主键'
        primary key,
    user_id int           null comment '用户id',
    prop_id int           null comment '道具id',
    number  int default 0 null comment '道具数量'
)
    comment '用户关联道具表';

create index prop_id
    on quiz.q_user_prop (prop_id);

create index user_id
    on quiz.q_user_prop (user_id);

create table if not exists quiz.t_path
(
    path_id         int auto_increment comment '主键，路径ID。'
        primary key,
    pattern         varchar(255) null comment '路径模式，如/api/users/**，/device/list/{current:d+}/{size:d+}。',
    http_method     varchar(255) null comment 'HTTP 方法,如 POST, GET',
    describe_       varchar(255) null comment '路径描述。',
    permission_name varchar(255) null comment '路径权限名称'
)
    comment '路径表';

create table if not exists quiz.t_permission
(
    permission_id   int auto_increment comment '主键，权限ID。'
        primary key,
    permission_name varchar(255) null comment '权限名称，如read、write。',
    describe_       varchar(511) null comment '描述'
)
    comment '权限表';

create table if not exists quiz.t_role
(
    role_id   int auto_increment comment '主键，角色ID。'
        primary key,
    role_name varchar(255) null comment '角色名称，如ROLE_ADMIN、ROLE_USER。',
    describe_ varchar(511) null comment '描述'
)
    comment '角色表';

create table if not exists quiz.t_role_permissions
(
    id            int auto_increment comment '主键'
        primary key,
    role_id       int null comment '关联角色ID',
    permission_id int null comment '关联权限ID'
)
    comment '角色权限关联表';

create index permission_id
    on quiz.t_role_permissions (permission_id);

create index role_id
    on quiz.t_role_permissions (role_id);

create index role_id_permission_id
    on quiz.t_role_permissions (role_id, permission_id);

create table if not exists quiz.t_user
(
    user_id       int unsigned auto_increment comment '主键，用户ID。'
        primary key,
    nickname      varchar(63)                        null comment '用户昵称',
    avatar_url    varchar(255)                       null comment '头像地址',
    username      varchar(63)                        null comment '用户账号',
    password      varchar(63)                        null comment '密码',
    phone         varchar(31)                        null comment '手机号',
    mail          varchar(63)                        null comment '邮箱',
    created_at    datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at    datetime                           null on update CURRENT_TIMESTAMP comment '更新时间',
    last_login_at datetime                           null comment '用户上次登录时间',
    enabled       bigint   default 0                 null comment '账号是否启用:0=正常,-1=禁用'
)
    comment '用户信息表';

create index mail
    on quiz.t_user (mail);

create index phone
    on quiz.t_user (phone);

create index username
    on quiz.t_user (username);

create index username_phone_mail
    on quiz.t_user (username, phone, mail);

create table if not exists quiz.t_user_auth
(
    auth_id     int unsigned auto_increment comment 'id'
        primary key,
    user_id     int unsigned null comment '用户ID。',
    provider_id varchar(63)  null comment '第三方提供的唯一标识符。比如微信的openId',
    provider    varchar(31)  null comment '第三方服务的名称（如Google、Facebook等）。'
)
    comment '第三方登录表';

create index provider_id
    on quiz.t_user_auth (provider_id);

create index user_id
    on quiz.t_user_auth (user_id);

create table if not exists quiz.t_user_roles
(
    id      int auto_increment comment '主键'
        primary key,
    user_id int null comment '关联用户ID',
    role_id int null comment '关联角色ID'
)
    comment '用户角色关联表';

create index role_id
    on quiz.t_user_roles (role_id);

create index user_id
    on quiz.t_user_roles (user_id);

create index user_id_role_id
    on quiz.t_user_roles (user_id, role_id);

create table if not exists quiz.yz_device
(
    device_id varchar(18)    not null comment '主键,安卓设备ID'
        primary key,
    username  varchar(255)   null comment '用户名',
    end_time  datetime       null comment '过期时间',
    lv        int default -1 null comment '-1封禁,0试用,1vip,2svip,3max'
)
    comment '设备卡密信息';

create index username
    on quiz.yz_device (username);

create table if not exists quiz.yz_use_info
(
    use_id        int auto_increment comment '主键,使用功能记录id'
        primary key,
    username      varchar(64)  null comment '卡号',
    account_      varchar(64)  null comment '账号(游戏的)',
    password      varchar(64)  null comment '密码(游戏的)',
    function_name varchar(128) null comment '调用的方法名称',
    server_id     int          null comment '区服id',
    server_name   varchar(64)  null comment '区服名称',
    brave_id      int          null comment '勇者id',
    player_key    varchar(32)  null comment '玩家key',
    player_name   varchar(64)  null comment '玩家昵称',
    role_id       int          null comment '玩家角色id',
    role_name     varchar(32)  null comment '玩家角色名称',
    date_time     datetime     null comment '使用时间'
)
    comment '脚本使用信息';

create index account_
    on quiz.yz_use_info (account_);

create index username
    on quiz.yz_use_info (username);


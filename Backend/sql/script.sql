create table log
(
    id         int auto_increment
        primary key,
    catalog    int                                 not null,
    user_id    int                                 null,
    role_id    int                                 null,
    order_id   int                                 null,
    item_id    int                                 null,
    project_id int                                 null,
    operation  varchar(100)                        not null,
    timestamp  timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint log_id_uindex
        unique (id)
);

create table position
(
    id          int auto_increment
        primary key,
    name        varchar(50)  not null,
    description varchar(100) not null,
    constraint position_id_uindex
        unique (id),
    constraint position_name_uindex
        unique (name)
);

create table project
(
    id          int auto_increment
        primary key,
    name        varchar(50)   not null,
    description varchar(200)  null,
    budget      decimal(9, 2) not null,
    used        decimal(9, 2) null,
    constraint project_id_uindex
        unique (id),
    constraint project_name_uindex
        unique (name)
);

create table role
(
    id          int auto_increment
        primary key,
    name        varchar(100) not null,
    description varchar(500) not null,
    constraint role_description_uindex
        unique (description),
    constraint role_id_uindex
        unique (id),
    constraint role_name_uindex
        unique (name)
);

create table role_page
(
    id           int auto_increment
        primary key,
    role_id      int                  not null,
    page         varchar(20)          not null,
    allow_modify tinyint(1) default 0 not null,
    constraint role_page_id_uindex
        unique (id),
    constraint role_page_role_id_fk
        foreign key (role_id) references role (id)
);

create table user
(
    id       int auto_increment
        primary key,
    username varchar(100) not null,
    password varchar(512) not null,
    salt     varchar(128) not null,
    email    varchar(100) not null,
    name     varchar(100) not null,
    session  varchar(128) null,
    constraint user_id_uindex
        unique (id),
    constraint user_username_uindex
        unique (username)
);

create table fresh
(
    id            int auto_increment
        primary key,
    name          varchar(50)  not null,
    chinese_name  varchar(20)  null,
    nick_name     varchar(50)  null,
    gender        varchar(10)  not null,
    itsc          varchar(50)  not null,
    grade         varchar(10)  not null,
    major         varchar(50)  null,
    info          varchar(500) null,
    register_time timestamp    null,
    user_id       int          not null,
    constraint fresh_user_id_uindex
        unique (user_id),
    constraint key_itsc
        unique (itsc),
    constraint fresh_user_id_fk
        foreign key (user_id) references user (id)
            on update cascade on delete cascade
)
    collate = utf8mb4_unicode_ci;

create table fresh_position
(
    fresh_id    int not null,
    position_id int not null,
    primary key (fresh_id, position_id),
    constraint fresh_position_fresh_id_fk
        foreign key (fresh_id) references fresh (id)
            on update cascade on delete cascade,
    constraint fresh_position_position_id_fk
        foreign key (position_id) references position (id)
            on update cascade on delete cascade
);

create table message_feedback
(
    id       int auto_increment
        primary key,
    content  varchar(512) null,
    from_uid int          not null,
    status   varchar(10)  not null,
    time     datetime     not null,
    title    varchar(100) not null,
    constraint message_feedback_id_uindex
        unique (id),
    constraint message_feedback_user_id_fk
        foreign key (from_uid) references user (id)
            on delete cascade
);

create table comment_message_feedback
(
    id         int auto_increment
        primary key,
    message_id int          not null,
    content    varchar(512) null,
    parent_id  int          null,
    from_uid   int          null,
    time       datetime     not null,
    constraint comment_message_feedback_id_uindex
        unique (id),
    constraint comment_message_feedback_comment_message_feedback_id_fk
        foreign key (parent_id) references comment_message_feedback (id),
    constraint comment_message_feedback_message_feedback_id_fk
        foreign key (message_id) references message_feedback (id),
    constraint comment_message_feedback_user_id_fk
        foreign key (from_uid) references user (id)
            on delete cascade
);

create table `order`
(
    id           int auto_increment
        primary key,
    state        varchar(30)   not null,
    project_id   int           not null,
    total_price  decimal(9, 2) null,
    proposer     int           null,
    propose_time datetime      null,
    reviewer     int           null,
    review_time  datetime      null,
    constraint order_id_uindex
        unique (id),
    constraint order_project_id_fk
        foreign key (project_id) references project (id),
    constraint order_user_id_fk
        foreign key (proposer) references user (id)
            on delete cascade,
    constraint order_user_id_fk_2
        foreign key (reviewer) references user (id)
            on delete cascade
);

create table item
(
    id            int auto_increment
        primary key,
    order_id      int           not null,
    state         varchar(30)   not null,
    platform      varchar(50)   not null,
    link          varchar(500)  null,
    name          varchar(100)  not null,
    `usage`       varchar(200)  null,
    price         decimal(9, 2) null,
    purchase_num  varchar(200)  null,
    shipping_num  varchar(200)  null,
    payer_id      int           null,
    has_receipt   tinyint(1)    null,
    purchaser     int           null,
    purchase_time datetime      null,
    collector     int           null,
    collect_time  datetime      null,
    manager       int           null,
    settle_time   datetime      null,
    constraint item_id_uindex
        unique (id),
    constraint item_order_id_fk
        foreign key (order_id) references `order` (id),
    constraint item_user_id_fk
        foreign key (purchaser) references user (id)
            on delete set null,
    constraint item_user_id_fk_2
        foreign key (collector) references user (id)
            on delete set null,
    constraint item_user_id_fk_3
        foreign key (manager) references user (id)
            on delete set null,
    constraint item_user_id_fk_4
        foreign key (payer_id) references user (id)
            on delete set null
);

create table user_project
(
    user_id    int         not null,
    project_id int         not null,
    role       varchar(20) not null,
    primary key (project_id, user_id),
    constraint user_project_project_id_fk
        foreign key (project_id) references project (id),
    constraint user_project_user_id_fk
        foreign key (user_id) references user (id)
            on delete cascade
);

create index user_project_project_id_index
    on user_project (project_id);

create table user_role
(
    user_id int not null,
    role_id int not null,
    primary key (role_id, user_id),
    constraint user_role_role_id_fk
        foreign key (role_id) references role (id),
    constraint user_role_user_id_fk
        foreign key (user_id) references user (id)
            on delete cascade
);



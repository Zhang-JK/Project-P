/******************************************/
/*   DatabaseName = rm-purchase   */
/*   TableName = item   */
/******************************************/
CREATE TABLE `item`
(
    `id`            int          NOT NULL AUTO_INCREMENT,
    `order_id`      int          NOT NULL,
    `state`         int          NOT NULL,
    `platform`      varchar(50)  NOT NULL,
    `link`          varchar(500)  DEFAULT NULL,
    `name`          varchar(100) NOT NULL,
    `usage`         varchar(200)  DEFAULT NULL,
    `price`         decimal(9, 2) DEFAULT NULL,
    `purchase_num`  varchar(200)  DEFAULT NULL,
    `shipping_num`  varchar(200)  DEFAULT NULL,
    `payer_id`      int           DEFAULT NULL,
    `has_receipt`   tinyint(1)    DEFAULT NULL,
    `purchaser`     int           DEFAULT NULL,
    `purchase_time` datetime      DEFAULT NULL,
    `collector`     int           DEFAULT NULL,
    `collect_time`  datetime      DEFAULT NULL,
    `manager`       int           DEFAULT NULL,
    `settle_time`   datetime      DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `item_id_uindex` (`id`),
    KEY `item_order_id_fk` (`order_id`),
    KEY `item_user_id_fk` (`purchaser`),
    KEY `item_user_id_fk_2` (`collector`),
    KEY `item_user_id_fk_3` (`manager`),
    KEY `item_user_id_fk_4` (`payer_id`),
    CONSTRAINT `item_order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
    CONSTRAINT `item_user_id_fk` FOREIGN KEY (`purchaser`) REFERENCES `user` (`id`),
    CONSTRAINT `item_user_id_fk_2` FOREIGN KEY (`collector`) REFERENCES `user` (`id`),
    CONSTRAINT `item_user_id_fk_3` FOREIGN KEY (`manager`) REFERENCES `user` (`id`),
    CONSTRAINT `item_user_id_fk_4` FOREIGN KEY (`payer_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
;

/******************************************/
/*   DatabaseName = rm-purchase   */
/*   TableName = log   */
/******************************************/
CREATE TABLE `log`
(
    `id`         int          NOT NULL AUTO_INCREMENT,
    `catalog`    int          NOT NULL,
    `user_id`    int                   DEFAULT NULL,
    `role_id`    int                   DEFAULT NULL,
    `order_id`   int                   DEFAULT NULL,
    `item_id`    int                   DEFAULT NULL,
    `project_id` int                   DEFAULT NULL,
    `operation`  varchar(100) NOT NULL,
    `timestamp`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `log_id_uindex` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
;

/******************************************/
/*   DatabaseName = rm-purchase   */
/*   TableName = order   */
/******************************************/
CREATE TABLE `order`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `state`        int NOT NULL,
    `project_id`   int NOT NULL,
    `total_price`  decimal(9, 2) DEFAULT NULL,
    `proposer`     int           DEFAULT NULL,
    `propose_time` datetime      DEFAULT NULL,
    `reviewer`     int           DEFAULT NULL,
    `review_time`  datetime      DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `order_id_uindex` (`id`),
    KEY `order_project_id_fk` (`project_id`),
    KEY `order_user_id_fk` (`proposer`),
    KEY `order_user_id_fk_2` (`reviewer`),
    CONSTRAINT `order_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
    CONSTRAINT `order_user_id_fk` FOREIGN KEY (`proposer`) REFERENCES `user` (`id`),
    CONSTRAINT `order_user_id_fk_2` FOREIGN KEY (`reviewer`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
;

/******************************************/
/*   DatabaseName = rm-purchase   */
/*   TableName = project   */
/******************************************/
CREATE TABLE `project`
(
    `id`          int           NOT NULL AUTO_INCREMENT,
    `name`        varchar(50)   NOT NULL,
    `description` varchar(200)  DEFAULT NULL,
    `budget`      decimal(9, 2) NOT NULL,
    `used`        decimal(9, 2) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `project_id_uindex` (`id`),
    UNIQUE KEY `project_name_uindex` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
;

/******************************************/
/*   DatabaseName = rm-purchase   */
/*   TableName = role   */
/******************************************/
CREATE TABLE `role`
(
    `id`          int          NOT NULL AUTO_INCREMENT,
    `name`        varchar(100) NOT NULL,
    `description` varchar(500) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `role_description_uindex` (`description`),
    UNIQUE KEY `role_id_uindex` (`id`),
    UNIQUE KEY `role_name_uindex` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
;

/******************************************/
/*   DatabaseName = rm-purchase   */
/*   TableName = user   */
/******************************************/
CREATE TABLE `user`
(
    `id`       int          NOT NULL AUTO_INCREMENT,
    `username` varchar(100) NOT NULL,
    `password` varchar(512) NOT NULL,
    `salt`     varchar(128) NOT NULL,
    `email`    varchar(100) NOT NULL,
    `name`     varchar(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_email_uindex` (`email`),
    UNIQUE KEY `user_id_uindex` (`id`),
    UNIQUE KEY `user_name_uindex` (`name`),
    UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
;

/******************************************/
/*   DatabaseName = rm-purchase   */
/*   TableName = user_project   */
/******************************************/
CREATE TABLE `user_project`
(
    `user_id`    int NOT NULL,
    `project_id` int NOT NULL,
    `role`       int DEFAULT NULL,
    PRIMARY KEY (`project_id`),
    KEY `user_project_user_id_fk` (`user_id`),
    CONSTRAINT `user_project_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
    CONSTRAINT `user_project_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
;

/******************************************/
/*   DatabaseName = rm-purchase   */
/*   TableName = user_role   */
/******************************************/
CREATE TABLE `user_role`
(
    `user_id` int NOT NULL,
    `role_id` int NOT NULL,
    PRIMARY KEY (`role_id`),
    UNIQUE KEY `user_role_role_id_uindex` (`role_id`),
    UNIQUE KEY `user_role_user_id_uindex` (`user_id`),
    CONSTRAINT `user_role_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
    CONSTRAINT `user_role_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
;

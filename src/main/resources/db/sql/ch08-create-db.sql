-- Copy & Paste what you need only
START TRANSACTION;
----------------------------------------------------------------------
DROP SCHEMA IF EXISTS `hb-01-one-to-one-uni`;

CREATE SCHEMA `hb-01-one-to-one-uni`;

USE `hb-01-one-to-one-uni`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `instructor_detail`
(
    `id`              int NOT NULL AUTO_INCREMENT,
    `youtube_channel` varchar(128) DEFAULT NULL,
    `hobby`           varchar(45)  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `instructor`
(
    `id`                   int NOT NULL AUTO_INCREMENT,
    `first_name`           varchar(45) DEFAULT NULL,
    `last_name`            varchar(45) DEFAULT NULL,
    `email`                varchar(45) DEFAULT NULL,
    `instructor_detail_id` int         DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                    `FK_DETAIL_idx` (`instructor_detail_id`),
    CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`) REFERENCES `instructor_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

----------------------------------------------------------------------
-- might be wrong because this is supposed to
-- be hb-02-one-to-one-bi
DROP SCHEMA IF EXISTS `hb-01-one-to-one-uni`;

CREATE SCHEMA `hb-01-one-to-one-uni`;

USE `hb-01-one-to-one-uni`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `instructor_detail`
(
    `id`              int NOT NULL AUTO_INCREMENT,
    `youtube_channel` varchar(128) DEFAULT NULL,
    `hobby`           varchar(45)  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `instructor`
(
    `id`                   int NOT NULL AUTO_INCREMENT,
    `first_name`           varchar(45) DEFAULT NULL,
    `last_name`            varchar(45) DEFAULT NULL,
    `email`                varchar(45) DEFAULT NULL,
    `instructor_detail_id` int         DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                    `FK_DETAIL_idx` (`instructor_detail_id`),
    CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`) REFERENCES `instructor_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
----------------------------------------------------------------------
DROP SCHEMA IF EXISTS `hb-03-one-to-many`;

CREATE SCHEMA `hb-03-one-to-many`;

USE `hb-03-one-to-many`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `instructor_detail`;

CREATE TABLE `instructor_detail`
(
    `id`              int NOT NULL AUTO_INCREMENT,
    `youtube_channel` varchar(128) DEFAULT NULL,
    `hobby`           varchar(45)  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `instructor`;

CREATE TABLE `instructor`
(
    `id`                   int NOT NULL AUTO_INCREMENT,
    `first_name`           varchar(45) DEFAULT NULL,
    `last_name`            varchar(45) DEFAULT NULL,
    `email`                varchar(45) DEFAULT NULL,
    `instructor_detail_id` int         DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                    `FK_DETAIL_idx` (`instructor_detail_id`),
    CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`)
        REFERENCES `instructor_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course`
(
    `id`            int NOT NULL AUTO_INCREMENT,
    `title`         varchar(128) DEFAULT NULL,
    `instructor_id` int          DEFAULT NULL,

    PRIMARY KEY (`id`),

    UNIQUE KEY `TITLE_UNIQUE` (`title`),

    KEY             `FK_INSTRUCTOR_idx` (`instructor_id`),

    CONSTRAINT `FK_INSTRUCTOR`
        FOREIGN KEY (`instructor_id`)
            REFERENCES `instructor` (`id`)
            ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;


SET FOREIGN_KEY_CHECKS = 1;
----------------------------------------------------------------------
DROP SCHEMA IF EXISTS `hb-04-one-to-many-uni`;

CREATE SCHEMA `hb-04-one-to-many-uni`;

USE `hb-04-one-to-many-uni`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `instructor_detail`
(
    `id`              int NOT NULL AUTO_INCREMENT,
    `youtube_channel` varchar(128) DEFAULT NULL,
    `hobby`           varchar(45)  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `instructor`
(
    `id`                   int NOT NULL AUTO_INCREMENT,
    `first_name`           varchar(45) DEFAULT NULL,
    `last_name`            varchar(45) DEFAULT NULL,
    `email`                varchar(45) DEFAULT NULL,
    `instructor_detail_id` int         DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                    `FK_DETAIL_idx` (`instructor_detail_id`),
    CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`)
        REFERENCES `instructor_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `course`
(
    `id`            int NOT NULL AUTO_INCREMENT,
    `title`         varchar(128) DEFAULT NULL,
    `instructor_id` int          DEFAULT NULL,

    PRIMARY KEY (`id`),

    UNIQUE KEY `TITLE_UNIQUE` (`title`),

    KEY             `FK_INSTRUCTOR_idx` (`instructor_id`),

    CONSTRAINT `FK_INSTRUCTOR`
        FOREIGN KEY (`instructor_id`)
            REFERENCES `instructor` (`id`)
            ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;


CREATE TABLE `review`
(
    `id`        int NOT NULL AUTO_INCREMENT,
    `comment`   varchar(256) DEFAULT NULL,
    `course_id` int          DEFAULT NULL,

    PRIMARY KEY (`id`),

    KEY         `FK_COURSE_ID_idx` (`course_id`),

    CONSTRAINT `FK_COURSE`
        FOREIGN KEY (`course_id`)
            REFERENCES `course` (`id`)
            ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


SET FOREIGN_KEY_CHECKS = 1;
----------------------------------------------------------------------
DROP SCHEMA IF EXISTS `hb-05-many-to-many`;

CREATE SCHEMA `hb-05-many-to-many`;

USE `hb-05-many-to-many`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `instructor_detail`
(
    `id`              int NOT NULL AUTO_INCREMENT,
    `youtube_channel` varchar(128) DEFAULT NULL,
    `hobby`           varchar(45)  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `instructor`
(
    `id`                   int NOT NULL AUTO_INCREMENT,
    `first_name`           varchar(45) DEFAULT NULL,
    `last_name`            varchar(45) DEFAULT NULL,
    `email`                varchar(45) DEFAULT NULL,
    `instructor_detail_id` int         DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                    `FK_DETAIL_idx` (`instructor_detail_id`),
    CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`)
        REFERENCES `instructor_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `course`
(
    `id`            int NOT NULL AUTO_INCREMENT,
    `title`         varchar(128) DEFAULT NULL,
    `instructor_id` int          DEFAULT NULL,

    PRIMARY KEY (`id`),

    UNIQUE KEY `TITLE_UNIQUE` (`title`),

    KEY             `FK_INSTRUCTOR_idx` (`instructor_id`),

    CONSTRAINT `FK_INSTRUCTOR`
        FOREIGN KEY (`instructor_id`)
            REFERENCES `instructor` (`id`)
            ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;


CREATE TABLE `review`
(
    `id`        int NOT NULL AUTO_INCREMENT,
    `comment`   varchar(256) DEFAULT NULL,
    `course_id` int          DEFAULT NULL,

    PRIMARY KEY (`id`),

    KEY         `FK_COURSE_ID_idx` (`course_id`),

    CONSTRAINT `FK_COURSE`
        FOREIGN KEY (`course_id`)
            REFERENCES `course` (`id`)
            ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `student`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `first_name` varchar(45) DEFAULT NULL,
    `last_name`  varchar(45) DEFAULT NULL,
    `email`      varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `course_student`
(
    `course_id`  int NOT NULL,
    `student_id` int NOT NULL,

    PRIMARY KEY (`course_id`, `student_id`),

    KEY          `FK_STUDENT_idx` (`student_id`),

    CONSTRAINT `FK_COURSE_05` FOREIGN KEY (`course_id`)
        REFERENCES `course` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT `FK_STUDENT` FOREIGN KEY (`student_id`)
        REFERENCES `student` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
----------------------------------------------------------------------
ROLLBACK;
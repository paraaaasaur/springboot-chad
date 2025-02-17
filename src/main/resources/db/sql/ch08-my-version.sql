START TRANSACTION;
----------------------------------------------------------------------
-- we follow Chad's FK on instructor side only in hb-01-one-to-one-uni
DROP SCHEMA IF EXISTS `hb-01-one-to-one-uni`;
CREATE SCHEMA `hb-01-one-to-one-uni`;
USE `hb-01-one-to-one-uni`;

DROP TABLE IF EXISTS `instructor_detail`;
DROP TABLE IF EXISTS `instructor`;

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
        REFERENCES `instructor_detail` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

-- initial data
INSERT INTO `instructor_detail` VALUES (1, 'https://www.luv2code.com/youtube', 'coding');
INSERT INTO `instructor_detail` VALUES (2, 'https://www.youtube.com/@RMVideos_Jukin', 'play ball with cow');
INSERT INTO `instructor` VALUES (1, 'Chad', 'Darby', 'darby@luv2code.com', 1);
INSERT INTO `instructor` VALUES (2, 'Foo', 'Bar', 'luv4cow@gmail.com', 2);
----------------------------------------------------------------------
-- hb-02-one-to-one-bi: moved FK to instructor_detail side because why not...
DROP SCHEMA IF EXISTS `hb-02-one-to-one-bi`;
CREATE SCHEMA `hb-02-one-to-one-bi`;
USE `hb-02-one-to-one-bi`;

DROP TABLE IF EXISTS `instructor`;
DROP TABLE IF EXISTS `instructor_detail`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `instructor`
(
    `id`                   int NOT NULL AUTO_INCREMENT,
    `first_name`           varchar(45) DEFAULT NULL,
    `last_name`            varchar(45) DEFAULT NULL,
    `email`                varchar(45) DEFAULT NULL,

    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `instructor_detail`
(
    `id`              int NOT NULL AUTO_INCREMENT,
    `youtube_channel` varchar(128) DEFAULT NULL,
    `hobby`           varchar(45)  DEFAULT NULL,
    `instructor_id`   int UNIQUE   DEFAULT NULL,

    PRIMARY KEY (`id`),
    KEY                    `FK_INSTRUCTOR_idx` (`instructor_id`),
    CONSTRAINT `FK_INSTRUCTOR` FOREIGN KEY (`instructor_id`)
        REFERENCES `instructor` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

-- initial data
INSERT INTO `instructor` VALUES (1, 'Chad', 'Darby', 'darby@luv2code.com');
INSERT INTO `instructor` VALUES (2, 'Foo', 'Bar', 'luv4cow@gmail.com');
INSERT INTO `instructor_detail` VALUES (1, 'https://www.luv2code.com/youtube', 'coding', 1);
INSERT INTO `instructor_detail` VALUES (2, 'https://www.youtube.com/@RMVideos_Jukin', 'play ball with cow', 2);
----------------------------------------------------------------------
DROP SCHEMA IF EXISTS `hb-03-one-to-many`;
CREATE SCHEMA `hb-03-one-to-many`;
USE `hb-03-one-to-many`;

DROP TABLE IF EXISTS `instructor`;
DROP TABLE IF EXISTS `instructor_detail`;
DROP TABLE IF EXISTS `course`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `instructor`
(
    `id`                   int NOT NULL AUTO_INCREMENT,
    `first_name`           varchar(45) DEFAULT NULL,
    `last_name`            varchar(45) DEFAULT NULL,
    `email`                varchar(45) DEFAULT NULL,

    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `instructor_detail`
(
    `id`              int NOT NULL AUTO_INCREMENT,
    `youtube_channel` varchar(128) DEFAULT NULL,
    `hobby`           varchar(45)  DEFAULT NULL,
    `instructor_id`   int UNIQUE   DEFAULT NULL,

    PRIMARY KEY (`id`),
    KEY                    `FK_INSTRUCTOR_idx` (`instructor_id`),
    CONSTRAINT `FK_INSTRUCTOR` FOREIGN KEY (`instructor_id`)
        REFERENCES `instructor` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `course`
(
    `id`            int NOT NULL AUTO_INCREMENT,
    `title`         varchar(128) DEFAULT NULL,
    `instructor_id` int          DEFAULT NULL,

    PRIMARY KEY (`id`),
    UNIQUE KEY `TITLE_UNIQUE` (`title`),
    KEY             `FK_INSTRUCTOR_C_idx` (`instructor_id`),
    CONSTRAINT `FK_INSTRUCTOR_C` FOREIGN KEY (`instructor_id`)
        REFERENCES `instructor` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;


SET FOREIGN_KEY_CHECKS = 1;
----------------------------------------------------------------------
DROP SCHEMA IF EXISTS `hb-04-one-to-many-uni`;
CREATE SCHEMA `hb-04-one-to-many-uni`;
USE `hb-04-one-to-many-uni`;

DROP TABLE IF EXISTS `instructor`;
DROP TABLE IF EXISTS `instructor_detail`;
DROP TABLE IF EXISTS `course`;
DROP TABLE IF EXISTS `review`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `instructor`
(
    `id`                   int NOT NULL AUTO_INCREMENT,
    `first_name`           varchar(45) DEFAULT NULL,
    `last_name`            varchar(45) DEFAULT NULL,
    `email`                varchar(45) DEFAULT NULL,

    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `instructor_detail`
(
    `id`              int NOT NULL AUTO_INCREMENT,
    `youtube_channel` varchar(128) DEFAULT NULL,
    `hobby`           varchar(45)  DEFAULT NULL,
    `instructor_id`   int UNIQUE   DEFAULT NULL,

    PRIMARY KEY (`id`),
    KEY                    `FK_INSTRUCTOR_idx` (`instructor_id`),
    CONSTRAINT `FK_INSTRUCTOR` FOREIGN KEY (`instructor_id`)
        REFERENCES `instructor` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `course`
(
    `id`            int NOT NULL AUTO_INCREMENT,
    `title`         varchar(128) DEFAULT NULL,
    `instructor_id` int          DEFAULT NULL,

    PRIMARY KEY (`id`),
    UNIQUE KEY `TITLE_UNIQUE` (`title`),
    KEY             `FK_INSTRUCTOR_C_idx` (`instructor_id`),
    CONSTRAINT `FK_INSTRUCTOR_C` FOREIGN KEY (`instructor_id`)
        REFERENCES `instructor` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

CREATE TABLE `review`
(
    `id`        int NOT NULL AUTO_INCREMENT,
    `comment`   varchar(256) DEFAULT NULL,
    `course_id` int          DEFAULT NULL,

    PRIMARY KEY (`id`),
    KEY         `FK_COURSE_ID_idx` (`course_id`),
    CONSTRAINT `FK_COURSE` FOREIGN KEY (`course_id`)
        REFERENCES `course` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


SET FOREIGN_KEY_CHECKS = 1;
----------------------------------------------------------------------
DROP SCHEMA IF EXISTS `hb-05-many-to-many`;
CREATE SCHEMA `hb-05-many-to-many`;
USE `hb-05-many-to-many`;

DROP TABLE IF EXISTS `instructor`;
DROP TABLE IF EXISTS `instructor_detail`;
DROP TABLE IF EXISTS `course`;
DROP TABLE IF EXISTS `review`;
DROP TABLE IF EXISTS `student`;
DROP TABLE IF EXISTS `course_student`;

SET FOREIGN_KEY_CHECKS = 0;

-- Instructor Table
CREATE TABLE `instructor`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `first_name` varchar(45) DEFAULT NULL,
    `last_name`  varchar(45) DEFAULT NULL,
    `email`      varchar(45) DEFAULT NULL,

    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Instructor Detail Table (One-to-One with Instructor)
CREATE TABLE `instructor_detail`
(
    `id`              int NOT NULL AUTO_INCREMENT,
    `youtube_channel` varchar(128) DEFAULT NULL,
    `hobby`           varchar(45)  DEFAULT NULL,
    `instructor_id`   int UNIQUE   DEFAULT NULL, -- Enforce strict 1-to-1 mapping

    PRIMARY KEY (`id`),
    KEY `FK_INSTRUCTOR_idx` (`instructor_id`),

    CONSTRAINT `FK_INSTRUCTOR` FOREIGN KEY (`instructor_id`)
        REFERENCES `instructor` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Course Table (One-to-Many with Instructor)
CREATE TABLE `course`
(
    `id`            int NOT NULL AUTO_INCREMENT,
    `title`         varchar(128) DEFAULT NULL,
    `instructor_id` int          DEFAULT NULL,

    PRIMARY KEY (`id`),
    UNIQUE KEY `TITLE_UNIQUE` (`title`),
    KEY `FK_INSTRUCTOR_C_idx` (`instructor_id`),

    CONSTRAINT `FK_INSTRUCTOR_C` FOREIGN KEY (`instructor_id`)
        REFERENCES `instructor` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Review Table (One-to-Many with Course)
CREATE TABLE `review`
(
    `id`        int NOT NULL AUTO_INCREMENT,
    `comment`   varchar(256) DEFAULT NULL,
    `course_id` int          DEFAULT NULL,

    PRIMARY KEY (`id`),
    KEY `FK_COURSE_ID_idx` (`course_id`),

    CONSTRAINT `FK_COURSE` FOREIGN KEY (`course_id`)
        REFERENCES `course` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Student Table (Many-to-Many with Course)
CREATE TABLE `student`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `first_name` varchar(45) DEFAULT NULL,
    `last_name`  varchar(45) DEFAULT NULL,
    `email`      varchar(45) DEFAULT NULL,

    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Join Table: Course-Student (Many-to-Many Relationship)
CREATE TABLE `course_student`
(
    `course_id`  int NOT NULL,
    `student_id` int NOT NULL,

    PRIMARY KEY (`course_id`, `student_id`),  -- Composite PK
    KEY `FK_STUDENT_idx` (`student_id`),

    CONSTRAINT `FK_COURSE_05` FOREIGN KEY (`course_id`)
        REFERENCES `course` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    CONSTRAINT `FK_STUDENT` FOREIGN KEY (`student_id`)
        REFERENCES `student` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

----------------------------------------------------------------------
ROLLBACK;
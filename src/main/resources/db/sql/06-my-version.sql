USE `employee_directory`;

DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `members`;

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `user_id` varchar(50) NOT NULL,
  `pw` char(68) NOT NULL,
  `active` tinyint NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `members`
--

INSERT INTO `members`
VALUES
('akutan','{bcrypt}$2a$10$kIhhBuhAAx7GQ28lGA96mu/M2YXLVaY5ey0X9Y0Ba3ZonADAevmmG',1),
('takuan','{bcrypt}$2a$10$c9bT2i021BxkvNC.jXWgEeRLDfgHAcm9pzE9RGNTn4dzEQx6eGzKa',1),
('toyota','{bcrypt}$2a$10$ZcppmMAo2YR3l4GFO8Zk9u1cH.OJNGueoWgtYBEzSIfWKA5r52iv.',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `roles` (
  `user_id` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `authorities5_idx_1` (`user_id`,`role`),
  CONSTRAINT `authorities5_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `roles`
--

INSERT INTO `roles`
VALUES
('akutan','ROLE_EMPLOYEE'),
('takuan','ROLE_EMPLOYEE'),
('takuan','ROLE_MANAGER'),
('toyota','ROLE_EMPLOYEE'),
('toyota','ROLE_MANAGER'),
('toyota','ROLE_ADMIN');

USE `employee_directory`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: https://www.bcryptcalculator.com/encode
--
-- Default passwords here are: fun123
--

INSERT INTO `users` 
VALUES 
('akutan','{bcrypt}$2a$10$kIhhBuhAAx7GQ28lGA96mu/M2YXLVaY5ey0X9Y0Ba3ZonADAevmmG',1),
('takuan','{bcrypt}$2a$10$c9bT2i021BxkvNC.jXWgEeRLDfgHAcm9pzE9RGNTn4dzEQx6eGzKa',1),
('toyota','{bcrypt}$2a$10$ZcppmMAo2YR3l4GFO8Zk9u1cH.OJNGueoWgtYBEzSIfWKA5r52iv.',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities4_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities4_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('akutan','ROLE_EMPLOYEE'),
('takuan','ROLE_EMPLOYEE'),
('takuan','ROLE_MANAGER'),
('toyota','ROLE_EMPLOYEE'),
('toyota','ROLE_MANAGER'),
('toyota','ROLE_ADMIN');
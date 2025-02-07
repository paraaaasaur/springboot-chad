USE `employee_directory`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
                         `username` varchar(50) NOT NULL,
                         `password` varchar(50) NOT NULL,
                         `enabled` tinyint NOT NULL,
                         PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--

INSERT INTO `users`
VALUES
    ('akutan','{noop}athc',1),
    ('takuan','{noop}majidesaa',1),
    ('toyota','{noop}!?!?!?',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
                               `username` varchar(50) NOT NULL,
                               `authority` varchar(50) NOT NULL,
                               UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
                               CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
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



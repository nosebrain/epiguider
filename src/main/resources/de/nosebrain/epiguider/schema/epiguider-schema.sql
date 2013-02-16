CREATE TABLE `series` (
  `name` varchar(255) NOT NULL DEFAULT '',
  `parser_id` varchar(255) DEFAULT NULL,
  `series_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
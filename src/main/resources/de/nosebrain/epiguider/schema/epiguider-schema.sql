CREATE TABLE `series` (
  `name` varchar(255) NOT NULL DEFAULT '',
  `parser_id` varchar(255) NOT NULL,
  `series_id` varchar(255) NOT NULL,
  PRIMARY KEY (`name`,`parser_id`,`series_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
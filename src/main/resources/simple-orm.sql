CREATE DATABASE IF NOT EXISTS `simple_orm`
  DEFAULT CHARSET utf8
  COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS `simple_orm`.`employee` (
  `id`          BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `first_name`  VARCHAR(30) NOT NULL,
  `last_name`   VARCHAR(30) NOT NULL,
  `birth_date`  DATE        NOT NULL,
  `employed`    VARCHAR(3)  NOT NULL,
  `occupation`  VARCHAR(30) NULL,
  `is_active`   TINYINT(1)  NOT NULL DEFAULT '1',
  `insert_time` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_by`   VARCHAR(64)          DEFAULT 'unknown',
  `update_time` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by`   VARCHAR(64)          DEFAULT 'unknown',
  PRIMARY KEY (id),
  INDEX `idx_firstname` (`first_name`),
  INDEX `idx_lastname` (`last_name`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = 'employee';

INSERT INTO `simple_orm`.`employee` (`first_name`, `last_name`, `birth_date`, `employed`, `occupation`)
VALUES ('michael', 'huang', '2018-06-16', 'yes', 'developer'),
  ('jelen', 'hu', '2018-06-16', 'no', 'manager'),
  ('jacky', 'chen', '2018-06-16', 'no', 'sales'),
  ('tom', 'li', '2018-06-16', 'no', 'ceo');
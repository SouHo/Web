SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `test` ;
CREATE SCHEMA IF NOT EXISTS `test` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `test` ;

-- -----------------------------------------------------
-- Table `csbit`.`user_list`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `csbit`.`user_list` ;

CREATE TABLE IF NOT EXISTS `test`.`user_list` (
  `name` VARCHAR(50) NOT NULL,
  `id_role` SMALLINT(6) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `last_login` TIMESTAMP NOT NULL,
  `error_count` TINYINT(4) NOT NULL DEFAULT 0 COMMENT '连续密码错误的次数',
  `is_expire` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '密码是否过期,  1:过期',
  `last_modify` BIGINT NOT NULL COMMENT '最后一次修改密码的日期的毫秒数',
  PRIMARY KEY (`name`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  INDEX `fk_user_role1_idx` (`id_role` ASC))
ENGINE = InnoDB
COMMENT = '用户表';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `csbit`.`user_list`
-- -----------------------------------------------------
START TRANSACTION;
USE `test`;
INSERT INTO `test`.`user_list` (`name`, `id_role`, `password`, `last_login`, `error_count`, `is_expire`, `last_modify`) VALUES ('SysAdmin', 1, 'dIjjMbi2TleU2j+k6xCtXQ==', '2013-05-20 09:55', 0, 0, -1);
INSERT INTO `test`.`user_list` (`name`, `id_role`, `password`, `last_login`, `error_count`, `is_expire`, `last_modify`) VALUES ('Auditor', 3, 'dIjjMbi2TleU2j+k6xCtXQ==', '2013-05-20 09:55', 0, 0, -1);
INSERT INTO `test`.`user_list` (`name`, `id_role`, `password`, `last_login`, `error_count`, `is_expire`, `last_modify`) VALUES ('SecAdmin', 2, 'dIjjMbi2TleU2j+k6xCtXQ==', '2013-05-20 09:55', 0, 0, -1);

COMMIT;


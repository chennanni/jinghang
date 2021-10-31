-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema db01
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db01
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db01` DEFAULT CHARACTER SET utf8 ;
USE `db01` ;

-- -----------------------------------------------------
-- Table `db01`.`region`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db01`.`region` ;

CREATE TABLE IF NOT EXISTS `db01`.`region` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db01`.`system`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db01`.`system` ;

CREATE TABLE IF NOT EXISTS `db01`.`system` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NULL,
  `region_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_system_region_id_idx` (`region_id` ASC) VISIBLE,
  CONSTRAINT `fk_system_region_id`
    FOREIGN KEY (`region_id`)
    REFERENCES `db01`.`region` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db01`.`feed`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db01`.`feed` ;

CREATE TABLE IF NOT EXISTS `db01`.`feed` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(100) NULL,
  `system_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_system_id_idx` (`system_id` ASC) VISIBLE,
  CONSTRAINT `fk_system_id`
    FOREIGN KEY (`system_id`)
    REFERENCES `db01`.`system` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db01`.`dataset`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db01`.`dataset` ;

CREATE TABLE IF NOT EXISTS `db01`.`dataset` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cob_date` INT NULL,
  `feed_id` INT NULL,
  `status` VARCHAR(1) NULL,
  `active` VARCHAR(1) NULL,
  `creation_time` DATETIME NULL,
  `last_update_time` DATETIME NULL,
  `parse_start` DATETIME NULL,
  `parse_end` DATETIME NULL,
  `load_start` DATETIME NULL,
  `load_end` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_feed_id_idx` (`feed_id` ASC) VISIBLE,
  CONSTRAINT `fk_feed_id`
    FOREIGN KEY (`feed_id`)
    REFERENCES `db01`.`feed` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db01`.`country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db01`.`country` ;

CREATE TABLE IF NOT EXISTS `db01`.`country` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `region_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_country_region_id_idx` (`region_id` ASC) VISIBLE,
  CONSTRAINT `fk_country_region_id`
    FOREIGN KEY (`region_id`)
    REFERENCES `db01`.`region` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db01`.`organization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db01`.`organization` ;

CREATE TABLE IF NOT EXISTS `db01`.`organization` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `label` VARCHAR(45) NULL,
  `country_id` INT NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_country_id_idx` (`country_id` ASC) INVISIBLE,
  INDEX `uid` (`uid` ASC) VISIBLE,
  CONSTRAINT `fk_country_id`
    FOREIGN KEY (`country_id`)
    REFERENCES `db01`.`country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db01`.`account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db01`.`account` ;

CREATE TABLE IF NOT EXISTS `db01`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `type` VARCHAR(45) NULL,
  `org_uid` INT NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_org_uid_idx` (`org_uid` ASC) VISIBLE,
  INDEX `uid` (`uid` ASC) VISIBLE,
  CONSTRAINT `fk_org_uid`
    FOREIGN KEY (`org_uid`)
    REFERENCES `db01`.`organization` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db01`.`instrument`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db01`.`instrument` ;

CREATE TABLE IF NOT EXISTS `db01`.`instrument` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `label` VARCHAR(45) NULL,
  `org_uid` INT NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ins_org_uid_idx` (`org_uid` ASC) VISIBLE,
  INDEX `uid` (`uid` ASC) VISIBLE,
  CONSTRAINT `fk_ins_org_uid`
    FOREIGN KEY (`org_uid`)
    REFERENCES `db01`.`organization` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db01`.`trade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db01`.`trade` ;

CREATE TABLE IF NOT EXISTS `db01`.`trade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NOT NULL,
  `trade_type` VARCHAR(45) NULL,
  `action` VARCHAR(45) NULL,
  `dataset_id` INT NULL,
  `system_id` INT NULL,
  `account_uid` INT NULL,
  `instrument_uid` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_t_dataset_id_idx` (`dataset_id` ASC) INVISIBLE,
  INDEX `fk_t_system_id_idx` (`system_id` ASC) VISIBLE,
  INDEX `uid` (`uid` ASC) VISIBLE,
  INDEX `fk_t_ins_uid_idx` (`instrument_uid` ASC) VISIBLE,
  INDEX `fk_t_account_uid_idx` (`account_uid` ASC) VISIBLE,
  CONSTRAINT `fk_t_dataset_id`
    FOREIGN KEY (`dataset_id`)
    REFERENCES `db01`.`dataset` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_system_id`
    FOREIGN KEY (`system_id`)
    REFERENCES `db01`.`system` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_account_uid`
    FOREIGN KEY (`account_uid`)
    REFERENCES `db01`.`account` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_ins_uid`
    FOREIGN KEY (`instrument_uid`)
    REFERENCES `db01`.`instrument` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db01`.`infotype`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db01`.`infotype` ;

CREATE TABLE IF NOT EXISTS `db01`.`infotype` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db01`.`risk`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db01`.`risk` ;

CREATE TABLE IF NOT EXISTS `db01`.`risk` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `trade_uid` INT NULL,
  `infotype_id` INT NULL,
  `amount` DECIMAL(20) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_infotype_id_idx` (`infotype_id` ASC) VISIBLE,
  INDEX `fk_trade_uid_idx` (`trade_uid` ASC) VISIBLE,
  CONSTRAINT `fk_trade_uid`
    FOREIGN KEY (`trade_uid`)
    REFERENCES `db01`.`trade` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_infotype_id`
    FOREIGN KEY (`infotype_id`)
    REFERENCES `db01`.`infotype` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

ALTER TABLE risk ADD COLUMN dataset_id INT;

-- -----------------------------------------------------
-- Table `db01`.`source_file`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db01`.`source_file` ;

CREATE TABLE IF NOT EXISTS `db01`.`source_file` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `feed_code` VARCHAR(20) NULL,
  `feed_file` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db01`.`feed_file_mapping`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db01`.`feed_file_mapping` ;

CREATE TABLE IF NOT EXISTS `db01`.`feed_file_mapping` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `feed_id` INT NULL,
  `source_file_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ffm_feed_id_idx` (`feed_id` ASC) VISIBLE,
  INDEX `fk_ffm_source_file_id_idx` (`source_file_id` ASC) VISIBLE,
  CONSTRAINT `fk_ffm_feed_id`
    FOREIGN KEY (`feed_id`)
    REFERENCES `db01`.`feed` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ffm_source_file_id`
    FOREIGN KEY (`source_file_id`)
    REFERENCES `db01`.`source_file` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db01`.`currency`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db01`.`currency` ;

CREATE TABLE IF NOT EXISTS `db01`.`currency` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(10) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db01`.`fx_rate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db01`.`fx_rate` ;

CREATE TABLE IF NOT EXISTS `db01`.`fx_rate` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cob_date` INT NULL,
  `currency_id` INT NULL,
  `amount` DECIMAL(20) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_currency_id_idx` (`currency_id` ASC) VISIBLE,
  CONSTRAINT `fk_currency_id`
    FOREIGN KEY (`currency_id`)
    REFERENCES `db01`.`currency` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db01`.`market_data`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db01`.`market_data` ;

CREATE TABLE IF NOT EXISTS `db01`.`market_data` (
  `id` INT NOT NULL,
  `uid` INT NOT NULL,
  `cob_date` INT NULL,
  `amount` DECIMAL(20) NULL,
  PRIMARY KEY (`id`),
  INDEX `uid` (`uid` ASC) VISIBLE,
  CONSTRAINT `fk_uid`
    FOREIGN KEY (`uid`)
    REFERENCES `db01`.`instrument` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

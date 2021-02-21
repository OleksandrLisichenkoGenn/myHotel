-- MySQL Workbench Forward Engineering
drop schema if exists my_hotel;
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema my_hotel
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema my_hotel
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `my_hotel` DEFAULT CHARACTER SET utf8 ;
USE `my_hotel` ;

-- -----------------------------------------------------
-- Table `my_hotel`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `my_hotel`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `my_hotel`.`accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `my_hotel`.`accounts` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `role_id` INT NOT NULL DEFAULT 2,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_accounts_roles1_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_accounts_roles1`
    FOREIGN KEY (`role_id`)
    REFERENCES `my_hotel`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `my_hotel`.`room_statuses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `my_hotel`.`room_statuses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(24) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `my_hotel`.`room_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `my_hotel`.`room_types` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(24) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `my_hotel`.`rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `my_hotel`.`rooms` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `price` DECIMAL UNSIGNED NOT NULL,
  `places` INT UNSIGNED NOT NULL,
  `room_status_id` INT NOT NULL,
  `room_type_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_rooms_statusRooms1_idx` (`room_status_id` ASC) VISIBLE,
  INDEX `fk_rooms_classOfRooms1_idx` (`room_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_rooms_statusRooms1`
    FOREIGN KEY (`room_status_id`)
    REFERENCES `my_hotel`.`room_statuses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rooms_classOfRooms1`
    FOREIGN KEY (`room_type_id`)
    REFERENCES `my_hotel`.`room_types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `my_hotel`.`order_statuses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `my_hotel`.`order_statuses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(24) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `my_hotel`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `my_hotel`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `places` INT NULL,
  `days` INT NOT NULL,
  `room_type_id` INT NULL,
  `total_price` DECIMAL UNSIGNED,
  `account_id` INT UNSIGNED NOT NULL,
  `order_status_id` INT NOT NULL,
  `room_id` INT,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`id`),
  INDEX `fk_orders_accounts1_idx` (`account_id` ASC) VISIBLE,
  INDEX `fk_orders_statusReceipts1_idx` (`order_status_id` ASC) VISIBLE,
  INDEX `fk_orders_rooms1_idx` (`room_id` ASC) VISIBLE,
  INDEX `fk_orders_classOfRooms1_idx` (`room_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_orders_accounts1`
    FOREIGN KEY (`account_id`)
    REFERENCES `my_hotel`.`accounts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_statusReceipts1`
    FOREIGN KEY (`order_status_id`)
    REFERENCES `my_hotel`.`order_statuses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_rooms1`
    FOREIGN KEY (`room_id`)
    REFERENCES `my_hotel`.`rooms` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_classOfRooms1`
    FOREIGN KEY (`room_type_id`)
    REFERENCES `my_hotel`.`room_types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

insert into room_types (type) value ('ECONOMY');
insert into room_types (type) value ('STANDARD');
insert into room_types (type) value ('LUX');

insert into room_statuses (status) value ('FREE');
insert into room_statuses (status) value ('BOOKED');
insert into room_statuses (status) value ('OCCUPIED');
insert into room_statuses (status) value ('NOT_AVAILABLE');

insert into order_statuses (status) value ('NEW');
insert into order_statuses (status) value ('ACCEPT_BY_ADMIN');
insert into order_statuses (status) value ('PAID');
insert into order_statuses (status) value ('NOT_PAID');

insert into roles (role) value ('ADMIN');
insert into roles (role) value ('CUSTOMER');

insert into accounts(name, email, password, role_id)
 values           ('admin', 'admin@db.com', '356a192b7913b04c54574d18c28d46e6395428ab', 1);
 
 insert into accounts(name, email, password, role_id)
 values           ('jenya', 'j@db.com', '356a192b7913b04c54574d18c28d46e6395428ab', 2);
 
insert into rooms (price, places, room_status_id, room_type_id) values (800,4,1,3);
insert into rooms (price, places, room_status_id, room_type_id) values (650,2,1,3);
insert into rooms (price, places, room_status_id, room_type_id) values (1200,4,1,3);
insert into rooms (price, places, room_status_id, room_type_id) values (500,4,1,2);
insert into rooms (price, places, room_status_id, room_type_id) values (135,2,1,1);
insert into rooms (price, places, room_status_id, room_type_id) values (90,1,1,1);
insert into rooms (price, places, room_status_id, room_type_id) values (175,1,1,2);
insert into rooms (price, places, room_status_id, room_type_id) values (250,4,1,1);
insert into rooms (price, places, room_status_id, room_type_id) values (215,2,1,2);

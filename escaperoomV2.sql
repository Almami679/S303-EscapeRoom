-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema EscapeRoomDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema EscapeRoomDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `EscapeRoomDB` DEFAULT CHARACTER SET utf8 ;
USE `EscapeRoomDB` ;

-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`escaperoom`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`escaperoom` (
  `escaperoom_id` INT NOT NULL AUTO_INCREMENT,
  `escaperoom_name` VARCHAR(45) NULL,
  `escaperoom_price` DOUBLE NULL,
  `escaperoom_theme` VARCHAR(45) NULL,
  `escaperoom_deleted` TINYINT NOT NULL,
  `escaperoom_createdAt` TIMESTAMP NOT NULL,
  `escaperoom_updatedAt` TIMESTAMP NULL,
  PRIMARY KEY (`escaperoom_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`room` (
  `room_id` INT NOT NULL AUTO_INCREMENT,
  `room_name` VARCHAR(45) NULL,
  `room_difficulty` VARCHAR(45) NULL,
  `room_price` DOUBLE NULL,
  `room_deleted` TINYINT NOT NULL,
  `room_createdAt` TIMESTAMP NOT NULL,
  `room_updatedAt` TIMESTAMP NULL,
  PRIMARY KEY (`room_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`tips`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`tips` (
  `tips_id` INT NOT NULL AUTO_INCREMENT,
  `tips_text` VARCHAR(100) NULL,
  `tips_deleted` TINYINT NOT NULL,
  PRIMARY KEY (`tips_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`objectdeco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`objectdeco` (
  `objectdeco_id` INT NOT NULL AUTO_INCREMENT,
  `objectdeco_name` VARCHAR(45) NULL,
  `objectdeco_material` VARCHAR(100) NULL,
  `objectdeco_price` DOUBLE NULL,
  `objectdeco_deleted` TINYINT NOT NULL,
  `objectdeco_createdAt` TIMESTAMP NOT NULL,
  `objectdeco_updatedAt` TIMESTAMP NULL,
  PRIMARY KEY (`objectdeco_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`game` (
  `game_id` INT NOT NULL AUTO_INCREMENT,
  `game_escapeRoomId` INT NOT NULL,
  `game_finished` TINYINT NULL,
  `game_deleted` TINYINT NOT NULL,
  `game_createdAt` TIMESTAMP NOT NULL,
  `game_updatedAt` TIMESTAMP NULL,
  PRIMARY KEY (`game_id`),
  INDEX `fk_escapeRoom_game_idx` (`game_escapeRoomId` ASC) VISIBLE,
  CONSTRAINT `fk_escapeRoom_game`
    FOREIGN KEY (`game_escapeRoomId`)
    REFERENCES `EscapeRoomDB`.`escaperoom` (`escaperoom_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`player` (
  `player_id` INT NOT NULL AUTO_INCREMENT,
  `player_name` VARCHAR(45) NULL,
  `player_email` VARCHAR(100) NULL,
  `Player_consentNotif` TINYINT NULL,
  `player_deleted` TINYINT NOT NULL,
  `player_createdAt` TIMESTAMP NOT NULL,
  `player_updatedAt` TIMESTAMP NULL,
  PRIMARY KEY (`player_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`game_has_player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`game_has_player` (
  `game_game_id` INT NOT NULL,
  `player_player_id` INT NOT NULL,
  PRIMARY KEY (`game_game_id`, `player_player_id`),
  INDEX `fk_game_has_player_player1_idx` (`player_player_id` ASC) VISIBLE,
  CONSTRAINT `fk_game_has_player_game1`
    FOREIGN KEY (`game_game_id`)
    REFERENCES `EscapeRoomDB`.`game` (`game_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_game_has_player_player1`
    FOREIGN KEY (`player_player_id`)
    REFERENCES `EscapeRoomDB`.`player` (`player_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`sale`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`sale` (
  `sale_id` INT NOT NULL AUTO_INCREMENT,
  `sale_price` DOUBLE NULL,
  `sale_gameId` INT NOT NULL,
  `sale_deleted` TINYINT NOT NULL,
  `sale_createdAt` TIMESTAMP NOT NULL,
  PRIMARY KEY (`sale_id`),
  INDEX `fk_game_sale_idx` (`sale_gameId` ASC) VISIBLE,
  CONSTRAINT `fk_game_sale`
    FOREIGN KEY (`sale_gameId`)
    REFERENCES `EscapeRoomDB`.`game` (`game_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`ticket` (
  `ticket_id` INT NOT NULL AUTO_INCREMENT,
  `ticket_saleId` INT NOT NULL,
  `ticket_text` VARCHAR(100) NULL,
  `ticket_playerId` INT NOT NULL,
  `ticket_createdAt` TIMESTAMP NOT NULL,
  PRIMARY KEY (`ticket_id`),
  INDEX `fk_sale_ticket_idx` (`ticket_saleId` ASC) VISIBLE,
  CONSTRAINT `fk_sale_ticket`
    FOREIGN KEY (`ticket_saleId`)
    REFERENCES `EscapeRoomDB`.`sale` (`sale_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`certificate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`certificate` (
  `certificate_id` INT NOT NULL AUTO_INCREMENT,
  `certificate_gameId` INT NOT NULL,
  `certificate_text` TEXT NULL,
  `certificate_createdAt` TIMESTAMP NULL,
  `player_player_id` INT NOT NULL,
  PRIMARY KEY (`certificate_id`),
  INDEX `fk_game_certificate_idx` (`certificate_gameId` ASC) VISIBLE,
  INDEX `fk_certificate_player1_idx` (`player_player_id` ASC) VISIBLE,
  CONSTRAINT `fk_game_certificate`
    FOREIGN KEY (`certificate_gameId`)
    REFERENCES `EscapeRoomDB`.`game` (`game_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_certificate_player1`
    FOREIGN KEY (`player_player_id`)
    REFERENCES `EscapeRoomDB`.`player` (`player_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`gift`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`gift` (
  `gift_id` INT NOT NULL AUTO_INCREMENT,
  `gift_gameId` INT NOT NULL,
  `gift_text` TEXT NULL,
  `gift_key` VARCHAR(45) NULL,
  `gift_createdAt` TIMESTAMP NOT NULL,
  `player_player_id` INT NOT NULL,
  PRIMARY KEY (`gift_id`),
  INDEX `fk_game_gift_idx` (`gift_gameId` ASC) VISIBLE,
  INDEX `fk_gift_player1_idx` (`player_player_id` ASC) VISIBLE,
  CONSTRAINT `fk_game_gift`
    FOREIGN KEY (`gift_gameId`)
    REFERENCES `EscapeRoomDB`.`game` (`game_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_gift_player1`
    FOREIGN KEY (`player_player_id`)
    REFERENCES `EscapeRoomDB`.`player` (`player_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`notification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`notification` (
  `notification_id` INT NOT NULL AUTO_INCREMENT,
  `notification_playerId` INT NULL,
  `notification_text` VARCHAR(100) NULL,
  `notification_createdAt` TIMESTAMP NOT NULL,
  PRIMARY KEY (`notification_id`, `notification_createdAt`),
  INDEX `fk_player_notification_idx` (`notification_playerId` ASC) VISIBLE,
  CONSTRAINT `fk_player_notification`
    FOREIGN KEY (`notification_playerId`)
    REFERENCES `EscapeRoomDB`.`player` (`player_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`room_has_tips`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`room_has_tips` (
  `tips_tips_id` INT NOT NULL,
  `room_room_id` INT NOT NULL,
  PRIMARY KEY (`tips_tips_id`, `room_room_id`),
  INDEX `fk_room_has_tips_room1_idx` (`room_room_id` ASC) VISIBLE,
  CONSTRAINT `fk_room_has_tips_tips1`
    FOREIGN KEY (`tips_tips_id`)
    REFERENCES `EscapeRoomDB`.`tips` (`tips_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_room_has_tips_room1`
    FOREIGN KEY (`room_room_id`)
    REFERENCES `EscapeRoomDB`.`room` (`room_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`room_has_objectdeco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`room_has_objectdeco` (
  `room_room_id` INT NOT NULL,
  `objectdeco_objectdeco_id` INT NOT NULL,
  PRIMARY KEY (`room_room_id`, `objectdeco_objectdeco_id`),
  INDEX `fk_room_has_objectdeco_ObjectDeco1_idx` (`objectdeco_objectdeco_id` ASC) VISIBLE,
  CONSTRAINT `fk_room_has_objectdeco_room1`
    FOREIGN KEY (`room_room_id`)
    REFERENCES `EscapeRoomDB`.`room` (`room_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_room_has_objectdeco_ObjectDeco1`
    FOREIGN KEY (`objectdeco_objectdeco_id`)
    REFERENCES `EscapeRoomDB`.`objectdeco` (`objectdeco_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`escaperoom_has_room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`escaperoom_has_room` (
  `escaperoom_escaperoom_id` INT NOT NULL,
  `room_room_id` INT NOT NULL,
  PRIMARY KEY (`escaperoom_escaperoom_id`, `room_room_id`),
  INDEX `fk_escaperoom_has_room_room1_idx` (`room_room_id` ASC) VISIBLE,
  CONSTRAINT `fk_escaperoom_has_room_escaperoom1`
    FOREIGN KEY (`escaperoom_escaperoom_id`)
    REFERENCES `EscapeRoomDB`.`escaperoom` (`escaperoom_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_escaperoom_has_room_room1`
    FOREIGN KEY (`room_room_id`)
    REFERENCES `EscapeRoomDB`.`room` (`room_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

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
-- Table `EscapeRoomDB`.`EscapeRoom`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`EscapeRoom` (
  `EscapeRoom_id` INT NOT NULL AUTO_INCREMENT,
  `EscapeRoom_name` VARCHAR(45) NULL,
  `EscapeRoom_price` DOUBLE NULL,
  `EscapeRoom_theme` VARCHAR(45) NULL,
  `EscapeRoom_deleted` TINYINT NOT NULL,
  `EscapeRoom_createdAt` TIMESTAMP NOT NULL,
  `EscapeRoom_updatedAt` TIMESTAMP NULL,
  PRIMARY KEY (`EscapeRoom_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`Room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`Room` (
  `Room_id` INT NOT NULL AUTO_INCREMENT,
  `Room_name` VARCHAR(45) NULL,
  `Room_difficulty` VARCHAR(45) NULL,
  `Room_price` DOUBLE NULL,
  `Room_escapeRoomId` INT NOT NULL,
  `Room_deleted` TINYINT NOT NULL,
  `Room_createdAt` TIMESTAMP NOT NULL,
  `Room_updatedAt` TIMESTAMP NULL,
  PRIMARY KEY (`Room_id`),
  INDEX `fk_room_escaperoom_idx` (`Room_escapeRoomId` ASC) VISIBLE,
  CONSTRAINT `fk_room_escaperoom`
    FOREIGN KEY (`Room_escapeRoomId`)
    REFERENCES `EscapeRoomDB`.`EscapeRoom` (`EscapeRoom_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`Tips`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`Tips` (
  `Tips_id` INT NOT NULL AUTO_INCREMENT,
  `Tips_text` VARCHAR(100) NULL,
  `Tips_Room_id` INT NOT NULL,
  PRIMARY KEY (`Tips_id`),
  INDEX `fk_rooms_tips_idx` (`Tips_Room_id` ASC) VISIBLE,
  CONSTRAINT `fk_rooms_tips`
    FOREIGN KEY (`Tips_Room_id`)
    REFERENCES `EscapeRoomDB`.`Room` (`Room_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`ObjectDeco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`ObjectDeco` (
  `ObjectDeco_id` INT NOT NULL AUTO_INCREMENT,
  `ObjectDeco_name` VARCHAR(45) NULL,
  `ObjectDeco_material` VARCHAR(100) NULL,
  `ObjectDeco_roomId` INT NOT NULL,
  `ObjectDeco_price` DOUBLE NULL,
  `ObjectDeco_deleted` TINYINT NOT NULL,
  `ObjectDeco_createdAt` TIMESTAMP NOT NULL,
  `ObjectDeco_updatedAt` TIMESTAMP NULL,
  PRIMARY KEY (`ObjectDeco_id`),
  INDEX `fk_room_objectdeco_idx` (`ObjectDeco_roomId` ASC) VISIBLE,
  CONSTRAINT `fk_room_objectdeco`
    FOREIGN KEY (`ObjectDeco_roomId`)
    REFERENCES `EscapeRoomDB`.`Room` (`Room_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`Game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`Game` (
  `Game_id` INT NOT NULL AUTO_INCREMENT,
  `Game_date` TIMESTAMP NULL,
  `Game_escapeRoomId` INT NOT NULL,
  `Game_finished` TINYINT NULL,
  `Game_deleted` TINYINT NOT NULL,
  PRIMARY KEY (`Game_id`),
  INDEX `fk_escapeRoom_game_idx` (`Game_escapeRoomId` ASC) VISIBLE,
  CONSTRAINT `fk_escapeRoom_game`
    FOREIGN KEY (`Game_escapeRoomId`)
    REFERENCES `EscapeRoomDB`.`EscapeRoom` (`EscapeRoom_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`Player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`Player` (
  `Player_id` INT NOT NULL AUTO_INCREMENT,
  `Player_name` VARCHAR(45) NULL,
  `Player_email` VARCHAR(100) NULL,
  `Player_consentNotif` TINYINT NULL,
  `Player_deleted` TINYINT NOT NULL,
  PRIMARY KEY (`Player_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`gameHasPlayer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`gameHasPlayer` (
  `gameHasPlayer_gameId` INT NOT NULL,
  `gameHasPlayer_playerId` INT NOT NULL,
  INDEX `fk_game_gameHasPlayer_idx` (`gameHasPlayer_gameId` ASC) VISIBLE,
  INDEX `fk_player_gameHasPlayer_idx` (`gameHasPlayer_playerId` ASC) VISIBLE,
  PRIMARY KEY (`gameHasPlayer_gameId`, `gameHasPlayer_playerId`),
  CONSTRAINT `fk_game_gameHasPlayer`
    FOREIGN KEY (`gameHasPlayer_gameId`)
    REFERENCES `EscapeRoomDB`.`Game` (`Game_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_player_gameHasPlayer`
    FOREIGN KEY (`gameHasPlayer_playerId`)
    REFERENCES `EscapeRoomDB`.`Player` (`Player_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`Sale`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`Sale` (
  `Sale_id` INT NOT NULL AUTO_INCREMENT,
  `Sale_date` TIMESTAMP NULL,
  `Sale_price` DOUBLE NULL,
  `Sale_gameId` INT NOT NULL,
  `Sale_deleted` TINYINT NOT NULL,
  PRIMARY KEY (`Sale_id`),
  INDEX `fk_game_sale_idx` (`Sale_gameId` ASC) VISIBLE,
  CONSTRAINT `fk_game_sale`
    FOREIGN KEY (`Sale_gameId`)
    REFERENCES `EscapeRoomDB`.`Game` (`Game_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`Ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`Ticket` (
  `Ticket_id` INT NOT NULL AUTO_INCREMENT,
  `Ticket_saleId` INT NOT NULL,
  `Ticket_text` VARCHAR(100) NULL,
  `Ticket_playerId` INT NOT NULL,
  PRIMARY KEY (`Ticket_id`),
  INDEX `fk_sale_ticket_idx` (`Ticket_saleId` ASC) VISIBLE,
  CONSTRAINT `fk_sale_ticket`
    FOREIGN KEY (`Ticket_saleId`)
    REFERENCES `EscapeRoomDB`.`Sale` (`Sale_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`Certificate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`Certificate` (
  `Certificate_id` INT NOT NULL AUTO_INCREMENT,
  `Certificate_gameId` INT NOT NULL,
  `Certificate_text` TEXT NULL,
  `Certificate_playerId` INT NOT NULL,
  PRIMARY KEY (`Certificate_id`),
  INDEX `fk_game_certificate_idx` (`Certificate_gameId` ASC) VISIBLE,
  CONSTRAINT `fk_game_certificate`
    FOREIGN KEY (`Certificate_gameId`)
    REFERENCES `EscapeRoomDB`.`Game` (`Game_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`Gift`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`Gift` (
  `Gift_id` INT NOT NULL AUTO_INCREMENT,
  `Gift_gameId` INT NOT NULL,
  `Gift_text` TEXT NULL,
  `Gift_playerId` INT NOT NULL,
  PRIMARY KEY (`Gift_id`),
  INDEX `fk_game_gift_idx` (`Gift_gameId` ASC) VISIBLE,
  CONSTRAINT `fk_game_gift`
    FOREIGN KEY (`Gift_gameId`)
    REFERENCES `EscapeRoomDB`.`Game` (`Game_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EscapeRoomDB`.`Notification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EscapeRoomDB`.`Notification` (
  `Notification_id` INT NOT NULL AUTO_INCREMENT,
  `Notification_playerId` INT NULL,
  `Notification_text` VARCHAR(100) NULL,
  PRIMARY KEY (`Notification_id`),
  INDEX `fk_player_notification_idx` (`Notification_playerId` ASC) VISIBLE,
  CONSTRAINT `fk_player_notification`
    FOREIGN KEY (`Notification_playerId`)
    REFERENCES `EscapeRoomDB`.`Player` (`Player_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

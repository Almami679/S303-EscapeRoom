-- MySQL Workbench Synchronization
-- Generated: 2025-01-10 18:51
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Pau

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

ALTER SCHEMA `escaperoomdb`  DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_general_ci ;

ALTER TABLE `escaperoomdb`.`EscapeRoom` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `escaperoomdb`.`Room` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `escaperoomdb`.`Tips` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `escaperoomdb`.`ObjectDeco` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ,
DROP COLUMN `ObjectDeco_deleted`,
ADD COLUMN `ObjectDeco_deleted` TINYINT(4) NULL DEFAULT NULL AFTER `ObjectDeco_price`;

ALTER TABLE `escaperoomdb`.`Game` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ,
DROP COLUMN `Game_deleted`,
ADD COLUMN `Game_deleted` TINYINT(4) NULL DEFAULT NULL AFTER `Game_escapeRoomId`;

ALTER TABLE `escaperoomdb`.`gameHasPlayer` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `escaperoomdb`.`Player` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ,
DROP COLUMN `Player_deleted`,
ADD COLUMN `Player_deleted` TINYINT(4) NULL DEFAULT NULL AFTER `Player_consentNotif`;

ALTER TABLE `escaperoomdb`.`Sale` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ,
DROP COLUMN `Sale_deleted`,
ADD COLUMN `Sale_deleted` TINYINT(4) NULL DEFAULT NULL AFTER `Sale_gameId`;

ALTER TABLE `escaperoomdb`.`Ticket` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ,
ADD COLUMN `Ticket_text` VARCHAR(100) NULL DEFAULT NULL AFTER `Ticket_saleId`;

ALTER TABLE `escaperoomdb`.`Certificate` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `escaperoomdb`.`Gift` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `escaperoomdb`.`Notification` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

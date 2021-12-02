-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema mpdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mpdb` ;

-- -----------------------------------------------------
-- Schema mpdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mpdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `mpdb` ;

-- -----------------------------------------------------
-- Table `mpdb`.`user_disks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mpdb`.`user_disks` ;

CREATE TABLE IF NOT EXISTS `mpdb`.`user_disks` (
                                                   `id` INT NOT NULL AUTO_INCREMENT,
                                                   `name` VARCHAR(45) NOT NULL,
                                                   PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mpdb`.`music_disk`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mpdb`.`music_disk` ;

CREATE TABLE IF NOT EXISTS `mpdb`.`music_disk` (
                                                   `id` INT NOT NULL AUTO_INCREMENT,
                                                   `name` VARCHAR(45) NULL DEFAULT NULL,
                                                   `duration` INT NULL DEFAULT NULL,
                                                   `style_id` INT NULL DEFAULT NULL,
                                                   `disk_id` INT NULL DEFAULT NULL,
                                                   PRIMARY KEY (`id`),
                                                   INDEX `fk_disks_idx` (`disk_id` ASC) VISIBLE,
                                                   CONSTRAINT `fk_disks`
                                                       FOREIGN KEY (`disk_id`)
                                                           REFERENCES `mpdb`.`user_disks` (`id`)
                                                           ON DELETE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `fk_disks_idx` ON `mpdb`.`music_disk` (`disk_id` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

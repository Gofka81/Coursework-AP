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
-- Table `mpdb`.`music_genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mpdb`.`music_genre` ;

CREATE TABLE IF NOT EXISTS `mpdb`.`music_genre` (
                                                    `id_genre` INT NOT NULL,
                                                    `name_genre` VARCHAR(45) NOT NULL,
                                                    PRIMARY KEY (`id_genre`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `mpdb`.`music_genre` (`id_genre`, `name_genre`) VALUES ('1', 'Country');
INSERT INTO `mpdb`.`music_genre` (`id_genre`, `name_genre`) VALUES ('2', 'Electronic');
INSERT INTO `mpdb`.`music_genre` (`id_genre`, `name_genre`) VALUES ('3', 'Jazz');
INSERT INTO `mpdb`.`music_genre` (`id_genre`, `name_genre`) VALUES ('4', 'Pop');
INSERT INTO `mpdb`.`music_genre` (`id_genre`, `name_genre`) VALUES ('5', 'Rock');
INSERT INTO `mpdb`.`music_genre` (`id_genre`, `name_genre`) VALUES ('6', 'Hip hop');
INSERT INTO `mpdb`.`music_genre` (`id_genre`, `name_genre`) VALUES ('7', 'Another genre');

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
                                                   CONSTRAINT `fk_disks`
                                                       FOREIGN KEY (`disk_id`)
                                                           REFERENCES `mpdb`.`user_disks` (`id`)
                                                           ON DELETE CASCADE,
                                                   CONSTRAINT `fk_genre`
                                                       FOREIGN KEY (`style_id`)
                                                           REFERENCES `mpdb`.`music_genre` (`id_genre`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `fk_disks_idx` ON `mpdb`.`music_disk` (`disk_id` ASC) VISIBLE;

CREATE INDEX `fk_genre_idx` ON `mpdb`.`music_disk` (`style_id` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

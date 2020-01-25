-- -----------------------------------------------------
-- Schema pts_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pts_db` DEFAULT CHARACTER SET utf8 ;
USE `pts_db` ;

-- -----------------------------------------------------
-- Table `pts_db`.`parking_slot_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pts_db`.`parking_slot_type` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `pts_db`.`parking_slot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pts_db`.`parking_slot` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `parking_slot_type_id` INT UNSIGNED NOT NULL,
    `is_free` TINYINT(1) NOT NULL,
    `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC),
    INDEX `parking_slot_type_id` (`parking_slot_type_id` ASC),
    INDEX `is_free` (`is_free` ASC),
    CONSTRAINT `parking_slot_parking_slot_type_id`
    FOREIGN KEY (`parking_slot_type_id`)
    REFERENCES `pts_db`.`parking_slot_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `pts_db`.`car`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pts_db`.`car` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `registration_plate` VARCHAR(255) NOT NULL,
    `parking_slot_type_id` INT UNSIGNED NOT NULL,
    `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC),
    UNIQUE INDEX `registration_plate_UNIQUE` (`registration_plate` ASC),
    INDEX `parking_slot_type_id` (`parking_slot_type_id` ASC),
    CONSTRAINT `car_parking_slot_type_id`
    FOREIGN KEY (`parking_slot_type_id`)
    REFERENCES `pts_db`.`parking_slot_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `pts_db`.`pricing_policy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pts_db`.`pricing_policy` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `formula` BLOB NOT NULL,
    `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC));


-- -----------------------------------------------------
-- Table `pts_db`.`visit_log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pts_db`.`visit_log` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `car_id` INT UNSIGNED NOT NULL,
    `entry_time` DATETIME NOT NULL,
    `exit_time` DATETIME NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `visit_log_car_id` (`car_id` ASC),
    CONSTRAINT `car_id`
    FOREIGN KEY (`car_id`)
    REFERENCES `pts_db`.`car` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `pts_db`.`bill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pts_db`.`bill` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `car_id` INT UNSIGNED NOT NULL,
    `parking_slot_id` INT UNSIGNED NOT NULL,
    `pricing_policy_id` INT UNSIGNED NOT NULL,
    `visit_log_id` INT UNSIGNED NOT NULL,
    `amount` DOUBLE NOT NULL,
    `vat` DOUBLE NOT NULL,
    `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC),
    INDEX `car_id` (`car_id` ASC),
    INDEX `parking_slot_id` (`parking_slot_id` ASC),
    INDEX `pricing_policy_id` (`pricing_policy_id` ASC),
    INDEX `visit_log_id` (`visit_log_id` ASC),
    CONSTRAINT `bill_car_id`
    FOREIGN KEY (`car_id`)
    REFERENCES `pts_db`.`car` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `bill_parking_slot_id`
    FOREIGN KEY (`parking_slot_id`)
    REFERENCES `pts_db`.`parking_slot` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `pricing_policy_id`
    FOREIGN KEY (`pricing_policy_id`)
    REFERENCES `pts_db`.`pricing_policy` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `visit_log_id`
    FOREIGN KEY (`visit_log_id`)
    REFERENCES `pts_db`.`visit_log` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

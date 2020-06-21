
CREATE SCHEMA IF NOT EXISTS `stray` DEFAULT CHARACTER SET utf8 ;
USE `stray` ;

-- -----------------------------------------------------
-- Table `stray`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stray`.`role` (
  `id` INT NULL AUTO_INCREMENT,
  `type` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `type_UNIQUE` ON `stray`.`role` (`type` ASC);


-- -----------------------------------------------------
-- Table `stray`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stray`.`user` (
  `id` INT NULL AUTO_INCREMENT,
  `login` VARCHAR(25) NOT NULL,
  `password` CHAR(64) NOT NULL,
  `email` VARCHAR(30) NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `stray`.`role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `email_UNIQUE` ON `stray`.`user` (`email` ASC) ;

CREATE INDEX `fk_user_role_idx` ON `stray`.`user` (`role_id` ASC) ;

CREATE UNIQUE INDEX `userName_UNIQUE` ON `stray`.`user` (`login` ASC) ;


-- -----------------------------------------------------
-- Table `stray`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stray`.`post` (
  `id` INT NULL AUTO_INCREMENT,
  `title` VARCHAR(25) NOT NULL,
  `text` TEXT(500) NOT NULL,
  `date` TIMESTAMP NOT NULL,
  `isModerated` TINYINT NOT NULL,
  `isActual` TINYINT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_post_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `stray`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `title_UNIQUE` ON `stray`.`post` (`title` ASC) ;

CREATE INDEX `fk_post_user_idx` ON `stray`.`post` (`user_id` ASC) ;


-- -----------------------------------------------------
-- Table `stray`.`help`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stray`.`help` (
  `id` INT NULL AUTO_INCREMENT,
  `type` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `type_UNIQUE` ON `stray`.`help` (`type` ASC) ;

-- -----------------------------------------------------
-- Table `stray`.`help_offer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stray`.`help_offer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `post_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `heip_type` INT NOT NULL,
  `isDone` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `id`
    FOREIGN KEY (`heip_type`)
    REFERENCES `stray`.`help` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `stray`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `post_id`
    FOREIGN KEY (`post_id`)
    REFERENCES `stray`.`post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `id_idx` ON `stray`.`help_offer` (`heip_type` ASC);

CREATE INDEX `user_id_idx` ON `stray`.`help_offer` (`user_id` ASC);

CREATE INDEX `post_id_idx` ON `stray`.`help_offer` (`post_id` ASC) ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

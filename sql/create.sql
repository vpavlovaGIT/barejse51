CREATE TABLE `app_project` (
	`id` VARCHAR(255) NOT NULL DEFAULT '' COLLATE 'latin1_swedish_ci',
	`name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`description` TEXT NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`dateStart` DATETIME NULL DEFAULT NULL,
	`dateFinish` DATETIME NULL DEFAULT NULL,
	`userId` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='latin1_swedish_ci' ENGINE=InnoDB;

CREATE TABLE `app_session` (
	`id` VARCHAR(255) NOT NULL DEFAULT '' COLLATE 'latin1_swedish_ci',
	`timestamp` BIGINT(20) NULL DEFAULT NULL,
	`signature` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`userId` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='latin1_swedish_ci' ENGINE=InnoDB;

CREATE TABLE `app_task` (
	`id` VARCHAR(255) NOT NULL DEFAULT '' COLLATE 'latin1_swedish_ci',
	`name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`description` TEXT NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`dateStart` DATETIME NULL DEFAULT NULL,
	`dateFinish` DATETIME NULL DEFAULT NULL,
	`projectId` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`userId` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='latin1_swedish_ci' ENGINE=InnoDB;

CREATE TABLE `app_user` (
	`id` VARCHAR(255) NOT NULL DEFAULT '' COLLATE 'latin1_swedish_ci',
	`login` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`passwordHash` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`role` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`email` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`locked` TINYINT(1) NULL DEFAULT NULL,
	`firstName` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`lastName` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`midName` VARCHAR(255) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='latin1_swedish_ci' ENGINE=InnoDB;
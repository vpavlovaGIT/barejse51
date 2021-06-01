CREATE TABLE `app_project` (
	`id` VARCHAR(255) NOT NULL DEFAULT '' COLLATE 'utf8_general_ci',
	`name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`description` TEXT NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`dateStart` DATETIME NULL DEFAULT NULL,
	`dateFinish` DATETIME NULL DEFAULT NULL,
	`userId` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `app_session` (
	`id` VARCHAR(255) NOT NULL DEFAULT '' COLLATE 'utf8_general_ci',
	`timestamp` BIGINT(20) NULL DEFAULT NULL,
	`signature` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`userId` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `app_task` (
	`id` VARCHAR(255) NOT NULL DEFAULT '' COLLATE 'utf8_general_ci',
	`name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`description` TEXT NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`dateStart` DATETIME NULL DEFAULT NULL,
	`dateFinish` DATETIME NULL DEFAULT NULL,
	`projectId` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`userId` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `app_user` (
	`id` VARCHAR(255) NOT NULL DEFAULT '' COLLATE 'utf8_general_ci',
	`login` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`passwordHash` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`role` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`email` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`locked` TINYINT(1) NULL DEFAULT NULL,
	`firstName` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`lastName` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`middleName` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8_general_ci' ENGINE=InnoDB;
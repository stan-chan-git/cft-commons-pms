CREATE SCHEMA `pms` DEFAULT CHARACTER SET utf8 ;

/*
 * Drop Table
 */
DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS APP_ROLE;
DROP TABLE IF EXISTS APP_USER_ROLE;
DROP TABLE IF EXISTS app_daily_record;/*日志表*/
DROP TABLE IF EXISTS app_task_types;
DROP TABLE IF EXISTS app_user_groups;


/*
 * User Table 
 */
CREATE TABLE `app_user` (
  `USERID` VARCHAR(100) NOT NULL,
  `LOGINNAME` VARCHAR(100) NOT NULL,
  `NAME` VARCHAR(100) NULL,
  `PASSWORD` VARCHAR(255) NOT NULL,
  `SALT` VARCHAR(100) NOT NULL,
  `EMAIL` VARCHAR(100) NULL,
  `PHONE` VARCHAR(100) NULL,
  `OFFICE` VARCHAR(100) NULL,
  `DEPT` VARCHAR(100) NULL,
  `LANGUAGE` VARCHAR(20) NULL,
  `STATUS` VARCHAR(20) NOT NULL,
  `REMARK` VARCHAR(400) NULL,
  PRIMARY KEY (`USERID`),
  UNIQUE INDEX `LOGINNAME_UNIQUE` (`LOGINNAME` ASC),
  INDEX `IDX1_APP_USER` (`LOGINNAME` ASC, `STATUS` ASC));

CREATE TABLE `app_role` 
   (`ROLEID` VARCHAR(20) NOT NULL, 
	`NAME` VARCHAR(255) NOT NULL, 
	`PERMISSIONS` VARCHAR(1000), 
	`STATUS` VARCHAR(20), 
	`REMARK` VARCHAR(400), 
	 PRIMARY KEY (`ROLEID`)
   );
   
CREATE TABLE `APP_USER_ROLE` 
   (`USERID` VARCHAR(20) NOT NULL, 
	`ROLEID` VARCHAR(20) NOT NULL, 
	 CONSTRAINT `APP_USER_ROLE_PK` PRIMARY KEY (`USERID`, `ROLEID`)
   );
-- Table "app_daily_record" DDL
CREATE TABLE `app_daily_record` (
  `id` varchar(20) NOT NULL DEFAULT '',
  `today` varchar(100) DEFAULT NULL,
  `mainTask` varchar(20) DEFAULT NULL,
  `taskTypes` varchar(20) DEFAULT NULL,
  `taskContent` varchar(200) DEFAULT NULL,
  `userId` varchar(100) DEFAULT NULL,
  `opDate` varchar(100) DEFAULT NULL,
  `isExpired` varchar(2) DEFAULT NULL,
  `isApproval` varchar(2) DEFAULT NULL,
  `type` varchar(2) DEFAULT NULL,
  `hours` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Table "app_task_types" DDL

CREATE TABLE `app_task_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mainTask` varchar(100) DEFAULT NULL,
  `superTask` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- Table "app_user_groups" DDL

CREATE TABLE `app_user_groups` ( 	
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `learder` varchar(100) DEFAULT NULL,
  `crew` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
*Sample Data
*/
TRUNCATE `app_user`;
TRUNCATE `app_role`;
TRUNCATE `app_user_role`;
TRUNCATE `app_daily_record`;
TRUNCATE `app_task_types`;
TRUNCATE `app_user_groups`;


insert into app_user (userId,loginname, name, email, password, salt, phone, office, dept, language, status, remark) values('U1001','ceditor','Messi','admin@spring.org','70b2e3b7a573e9010daaf72c861689cc5f2bd80f','41d005939da010b5','28888888','LHK11','CHB7','en_US','active','');
insert into app_user (userId,loginname, name, email, password, salt, phone, office, dept, language, status, remark) values('U1002','neditor','Rain','user@spring.org','70b2e3b7a573e9010daaf72c861689cc5f2bd80f','41d005939da010b5','28888887','LHK12','CHB7','en_US','active','');
insert into app_user (userId,loginname, name, email, password, salt, phone, office, dept, language, status, remark) values('U1003','ieditor','Backham','jack@spring.org','70b2e3b7a573e9010daaf72c861689cc5f2bd80f','41d005939da010b5','28888886','LHK11','CHB7','en_US','active','');
insert into app_user (userId,loginname, name, email, password, salt, phone, office, dept, language, status, remark) values('U1004','meditor','Kitty','kate@spring.org','70b2e3b7a573e9010daaf72c861689cc5f2bd80f','41d005939da010b5','28888885','LHK12','CHB7','en_US','active','');


insert into app_role (roleid, name, permissions,status,remark) values('R1001','Super Editor','news:create,news:update,news:delete','active','');
insert into app_role (roleid, name, permissions,status,remark) values('R1002','NewsContent Editor','news:create','active','');
insert into app_role (roleid, name, permissions,status,remark) values('R1003','NewsImage Editor','news:create','active','');
insert into app_role (roleid, name, permissions,status,remark) values('R1004','MusicProduct Editor','news:create','active','');




insert into app_user_role (userid, roleid) values('U1001','R1001');
insert into app_user_role (userid, roleid) values('U1002','R1002');
insert into app_user_role (userid, roleid) values('U1003','R1003');
insert into app_user_role (userid, roleid) values('U1004','R1004');


INSERT INTO app_task_types (id,mainTask) VALUES (NULL,'开发');
INSERT INTO app_task_types (id,mainTask) VALUES (NULL,'设计');
INSERT INTO app_task_types (id,mainTask) VALUES (NULL,'测试');
INSERT INTO app_task_types (id,mainTask) VALUES (NULL,'学习');
INSERT INTO app_task_types (id,mainTask) VALUES (NULL,'其他');

INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'单元测试',1);
INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'数据处理',1);
INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'功能优化',1);
INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'功能开发',1);
INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'功能调整',1);
INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'工作交接',1);

INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'学习',2);
INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'技术研发',2);
INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'技术共享',2);

INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'概要设计',3);
INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'详细设计',3);
INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'设计评审',3);
INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'UI设计',3);
INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'文档编写',3);

INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'测试实施',4);
INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'UAT测试',4);
INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'集成测试',4);
INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'系统测试',4);
INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'回归测试',4);
INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'测试评审',4);
INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'编写测试用例',4);
INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'编写测试计划',4);

INSERT INTO app_task_types (id,mainTask,superTask) VALUES (NULL,'其他',5);


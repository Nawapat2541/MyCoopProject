--14012019
ALTER TABLE `leaves` CHANGE `no_day` `no_day` DECIMAL(6,3) NOT NULL;

--25012019
ALTER TABLE user ADD facebookid VARCHAR(64) NULL DEFAULT NULL AFTER path;

--30012019 Line Login
ALTER TABLE `user` ADD `line_id` VARCHAR(64) NULL DEFAULT NULL;

--31012019 เน€เธ�เธฅเธตเน�เธขเธ�เธฅเธฒเธ�เธดเน€เธจเธฉ เน€เธ�เน�เธ�เธฅเธฒเธญเธทเน�เธ�เน�
UPDATE `leave_type` SET `leave_type_name`="เธฅเธฒเธญเธทเน�เธ�เน�",`description`="เธฅเธฒเธญเธทเน�เธ�เน�"WHERE `leave_type_id` = '2'
-- เน€เธ�เธดเน�เธก เธฅเธฒเธ�เธฑเธ�เธฃเน�เธญเธ�เธ—เธตเน�เน€เธซเธฅเธทเธญเธ�เธฒเธ�เธ�เธตเธ�เน�เธญเธ� เน�เธ� leave_type
INSERT INTO `leave_type`(`leave_type_id`, `leave_type_name`, `description`, `user_create`, `user_update`, `time_create`, `time_update`) VALUES ('6','เธฅเธฒเธ�เธฑเธ�เธฃเน�เธญเธ�เธ—เธตเน�เน€เธซเธฅเธทเธญเธ�เธฒเธ�เธ�เธตเธ�เน�เธญเธ�','เธฅเธฒเธ�เธฑเธ�เธฃเน�เธญเธ�เธ—เธตเน�เน€เธซเธฅเธทเธญเธ�เธฒเธ�เธ�เธตเธ�เน�เธญเธ�','Patipat.n','weerawat.p','2019-01-31','2019-01-31')

--06022019
ALTER TABLE `user` CHANGE `work_time_start` `work_time_start` CHAR(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '9:00';
ALTER TABLE `user` CHANGE `work_time_end` `work_time_end` CHAR(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '18:00';

--27022019 default working time start and end
update `user` set work_time_start = 09.00 , work_time_end = 18.00 where work_time_start is null

--Add equipment_status
CREATE TABLE `equipment_status` (
  `status` VARCHAR(3) NOT NULL,
  `description` VARCHAR(45) NULL,
  `color` VARCHAR(45) NULL,  
  `user_create` VARCHAR(45) NULL,
  `time_create` TIMESTAMP NULL,
  `user_update` VARCHAR(45) NULL,
  `time_update` TIMESTAMP NULL,
  PRIMARY KEY (`status`));
  
INSERT INTO `equipment_status`
(`status`,`description`,`color`,`user_create`,`time_create`)
VALUES
('A','Available','green-jungle','noppakaw.b','2019-03-22 10:30:00')
,('B','Borrowed','yellow-lemon','noppakaw.b','2019-03-22 10:30:00')
,('C','Corrupted','red-mint','noppakaw.b','2019-03-22 10:30:00')
,('W','Wait for approve','default','noppakaw.b','2019-03-22 10:30:00')
,('L','Lost','dark','noppakaw.b','2019-03-22 10:30:00')
,('F','Fixed','blue','noppakaw.b','2019-03-22 10:30:00')
,('Z','Disabled','grey-mint','noppakaw.b','2019-03-22 10:30:00')


CREATE TABLE `equipment_type` (
  `Type` VARCHAR(3) NOT NULL,
  `description` VARCHAR(45) NULL,
  `user_create` VARCHAR(45) NULL,
  `time_create` TIMESTAMP NULL,
  `user_update` VARCHAR(45) NULL,
  `time_update` TIMESTAMP NULL,
  PRIMARY KEY (`Type`));
  
 INSERT INTO `equipment_type` (`Type`, `description`, `user_create`, `time_create`, `user_update`, `time_update`) 
 VALUES 
 ('c', 'Computer', 'chitsanucha.s', '2019-03-27 12:27:18', NULL, NULL), 
 ('o', 'Other', 'chitsanucha.s', '2019-03-27 12:27:18', NULL, NULL);

--11/9/2019-----------------------------------------------------------------------
CREATE TABLE `article` (
  `article_id` INT NOT NULL,
  `article_type_id` CHAR(1) NOT NULL,
  `topic` VARCHAR(255) NULL,
  `user_id` VARCHAR(32) NULL,
  `detail` TEXT NULL,
  `file_id` BIGINT(20) NULL,
  `user_create` VARCHAR(32) NULL,
  `time_create` TIMESTAMP NULL,
  `user_update` VARCHAR(32) NULL,
  `time_update` TIMESTAMP NULL,
  PRIMARY KEY (`article_id`))CHARSET=utf8;

INSERT INTO `article` (`article_id`, `article_type_id`, `topic`,`user_id`,`detail`,`file_id`,`user_create`,`time_create`, `user_update`, `time_update`) 
 VALUES 
 (1, 2, 'โซดาผสมมะนาว', 'anan.n', 'tejhykutel,sthksjfjsrtkykfjgsfhsrt', NULL,'apichat.s' ,'2019-09-11 17:00:00', NULL, NULL), 
 (2, 1, 'แจ้งจับบุด้าอุลตร้าแมน', 'anan.n', 'sgoodgbnodkuyprayutfdbnaldbn;ndndmn', NULL,'apichat.s' ,'2019-09-11 17:00:00', NULL, NULL);

CREATE TABLE `article_type` (
  `article_type_id` CHAR(1) NOT NULL,
  `name` VARCHAR(128) NULL,
  `description` VARCHAR(255) NULL,
  `user_create` VARCHAR(32) NULL,
  `time_create` TIMESTAMP NULL,
  `user_update` VARCHAR(32) NULL,
  `time_update` TIMESTAMP NULL,
  PRIMARY KEY (`article_type_id`))CHARSET=utf8;

INSERT INTO `article_type` (`article_type_id`, `name`, `description`,`user_create`,`time_create`, `user_update`, `time_update`) 
 VALUES 
 (1, 'ข่าว', 'ก็ข่าวแหละ', 'apichat.s' ,'2019-09-11 17:00:00', NULL, NULL), 
 (2, 'เกร็ดความรู้', 'เกร็ดน่ารู้แหละ', 'apichat.s', '2019-09-11 17:00:00', NULL, NULL),
 (3, 'เทคโนโลยี', 'เทคโนโลยีแหละ', 'apichat.s', '2019-09-11 17:00:00', NULL, NULL),
 (4, 'การศึกษา', 'การศึกษาแหละ', 'apichat.s', '2019-09-11 17:00:00', NULL, NULL),
 (5, 'ธุรกิจ', 'ธุรกิจแหละ', 'apichat.s', '2019-09-11 17:00:00', NULL, NULL);


CREATE TABLE `tag` (
  `tag_id` CHAR(1) NOT NULL,
  `name` VARCHAR(128) NULL,
  `description` VARCHAR(255) NULL,
  `user_create` VARCHAR(32) NULL,
  `time_create` TIMESTAMP NULL,
  `user_update` VARCHAR(32) NULL,
  `time_update` TIMESTAMP NULL,
  PRIMARY KEY (`tag_id`))CHARSET=utf8;

INSERT INTO `tag` (`tag_id`, `name`, `description`,`user_create`,`time_create`, `user_update`, `time_update`) 
 VALUES 
 (1, 'สุขภาพ', 'ก็เกี่ยวกับสุขภาพอ่ะ', 'apichat.s' ,'2019-09-11 17:00:00', NULL, NULL), 
 (2, 'เทคโนโลยี', 'ก็เกี่ยวกับเทคโนโลยีอ่ะ', 'apichat.s', '2019-09-11 17:00:00', NULL, NULL),
 (3, 'วัฒนธรรม', 'ก็เกี่ยวกับวัฒนธรรมอ่ะ', 'apichat.s', '2019-09-11 17:00:00', NULL, NULL);

CREATE TABLE `article_tag` (
  `article_id` CHAR(1) NOT NULL,
  `tag_id` CHAR(1) NOT NULL
   )ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO `article_tag` (`article_id`, `tag_id`) 
 VALUES 
 (1,1),
 (1,2),
 (2,3);
 
 ALTER TABLE `user` ADD `leave_quota_lastyear` DECIMAL(3,1) NULL AFTER `leave_quota_3`

 INSERT INTO `article_tag` (`article_id`, `tag_id`) 
 VALUES 
 (1,2);
 
 ALTER TABLE `user` ADD `flag_search` varchar(1) DEFAULT 1 AFTER `address`
 
 --PROD 19 SEP 2019

 ALTER TABLE `tag`
MODIFY COLUMN `tag_id` int

 ALTER TABLE `article_tag`
MODIFY COLUMN `article_id` CHARACTER(3)

 ALTER TABLE `article_tag`
MODIFY COLUMN `tag_id` CHARACTER(3)

 --PROD 25 SEP 2019
 
DELETE FROM authorized_object WHERE `authorized_object_id` = 'address.edit';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'address.view';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'contact.edit';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'contact.view';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'cust.edit';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'cust.view';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'email.send';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'email.server.delete';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'inventroy.view';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'job.edit';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'job.view';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'mq.edit';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'mq.view';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'mqg.edit';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'mqg.view';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'mypm.view';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'opt.edit';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'opt.view';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'other.view';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'pm.edit';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'pm.view';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'pm.view.all';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'resource.edit';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'resource.view';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'salary.approve';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'task.edit';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'task.view';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'webboard.all';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'webboard.product';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'wh.view.all';
DELETE FROM authorized_object WHERE `authorized_object_id` = 'productdetail.edit';


delete from role_authorized_object where authorized_object_id not in (select authorized_object_id from authorized_object);

INSERT INTO `authorized_object` (`authorized_object_id`, `name`, `description`, `time_create`, `time_update`, `authorized_object_group_id`) VALUES ('article.view', 'article.view', 'ดูรายการ article ได้', CURRENT_TIME(), CURRENT_TIME(), '1');

INSERT INTO `authorized_object` (`authorized_object_id`, `name`, `description`, `time_create`, `time_update`, `authorized_object_group_id`) VALUES ('article.edit', 'article.edit', 'แก้ไขรายการ article ได้', CURRENT_TIME(), CURRENT_TIME(), '1');

 --PROD 21 OCT 2019

ALTER TABLE user ADD phone_num varchar(10);
update  user set    phone_num = '0855555555' where  id = 'apichat.s'
 

--PROD 6 NOV 2019

ALTER TABLE user ADD gender char(1);

update user set gender = 'M' where id = 'apichat.s'

-- QRCode Not Use Now
CREATE TABLE `qrcode_list` ( `qr_id` INT(20) NOT NULL , `status` CHAR(1) NULL , `time_create` TIMESTAMP NULL , `time_update` TIMESTAMP NULL , PRIMARY KEY (`qr_id`))

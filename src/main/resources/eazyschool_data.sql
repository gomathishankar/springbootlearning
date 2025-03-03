INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
 VALUES (' Jan 1 ','New Year''s Day','FESTIVAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
 VALUES (' Oct 31 ','Halloween','FESTIVAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
 VALUES (' Nov 24 ','Thanksgiving Day','FESTIVAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
 VALUES (' Dec 25 ','Christmas','FESTIVAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
 VALUES (' Jan 17 ','Martin Luther King Jr. Day','FEDERAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
 VALUES (' July 4 ','Independence Day','FEDERAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
 VALUES (' Sep 5 ','Labor Day','FEDERAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
  VALUES (' Nov 11 ','Veterans Day','FEDERAL',CURDATE(),'DBA');

INSERT INTO `roles` (`role_name`,`created_at`, `created_by`)
VALUES ('ADMIN',CURDATE(),'DBA');

INSERT INTO `roles` (`role_name`,`created_at`, `created_by`)
VALUES ('STUDENT',CURDATE(),'DBA');

INSERT INTO `person` (`name`,`email`,`mobile_number`,`pwd`,`role_id`,`address_id`,`created_at`,`created_by`)
VALUES ('admin','admin@test.com','9876543210','$2a$12$RCvL3GU0xecTxlVmi6h8J.og6SKksp.ADd51iTh5F.oTIrdGu3wIS',1,null,CURDATE(),'DBA');

INSERT INTO `person` (`name`,`email`,`mobile_number`,`pwd`,`role_id`,`address_id`,`created_at`,`created_by`)
VALUES ('saranya','sar@test.com','1231231231','$2a$12$0/RIoFDcjsrfSBv9ntKzQeNd30ZN51Ec/F.zj5PgHb2TerNZzFiTu',2,null,CURDATE(),'DBA');
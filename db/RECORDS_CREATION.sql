DESC db01.region;
SELECT * FROM db01.region;
INSERT INTO db01.region (id,name) VALUES (1,'AEJ');
INSERT INTO db01.region (id,name) VALUES (2,'AMERICA');
INSERT INTO db01.region (id,name) VALUES (3,'EURO');

DESC db01.system;
SELECT * FROM db01.system;
INSERT INTO db01.system (id,name,region_id) VALUES (1,'Totoro',1);
INSERT INTO db01.system (id,name,region_id) VALUES (2,'Euclid',2);
INSERT INTO db01.system (id,name,region_id) VALUES (3,'Spear',3);

DESC db01.feed;
SELECT * FROM db01.feed;
INSERT INTO db01.feed (id,name,description,system_id) VALUES (1,'Totoro_01','Totoro_01', 1);
INSERT INTO db01.feed (id,name,description,system_id) VALUES (2,'Totoro_02','Totoro_02', 1);
INSERT INTO db01.feed (id,name,description,system_id) VALUES (3,'Euclid_01','Euclid_01', 2);
INSERT INTO db01.feed (id,name,description,system_id) VALUES (4,'Euclid_02','Euclid_02', 2);
INSERT INTO db01.feed (id,name,description,system_id) VALUES (5,'Spear_01','Spear_01', 3);
INSERT INTO db01.feed (id,name,description,system_id) VALUES (6,'Spear_02','Spear_02', 3);

desc db01.infotype;
SELECT * FROM db01.infotype;
INSERT INTO db01.infotype (name,start_date,end_date) VALUES ('info_pnl','2000-01-01', '9999-01-01');
INSERT INTO db01.infotype (name,start_date,end_date) VALUES ('info_vod0','2000-01-01', '9999-01-01');
INSERT INTO db01.infotype (name,start_date,end_date) VALUES ('info_risk_3','2000-01-01', '9999-01-01');
INSERT INTO db01.infotype (name,start_date,end_date) VALUES ('info_risk_4','2000-01-01', '9999-01-01');

desc db01.country;
SELECT * FROM db01.country;
INSERT INTO db01.country (name,region_id) VALUES ('dummy','1');

desc db01.organization;
SELECT * FROM db01.organization;
INSERT INTO db01.organization (uid,name,label,country_id,start_date,end_date) VALUES ('0','dummy','dummy_label',1,'2000-01-01', '9999-01-01');

desc db01.account;
SELECT * FROM db01.account;
INSERT INTO db01.account (uid,name,type,org_uid,start_date,end_date) VALUES ('0','dummy','dummy_type',0,'2000-01-01', '9999-01-01');

desc db01.instrument;
SELECT * FROM db01.instrument;
INSERT INTO db01.instrument (uid,name,label,org_uid,start_date,end_date) VALUES ('0','dummy','dummy_label','0','2000-01-01', '9999-01-01');
SHOW DATABASES;

SELECT LAST_INSERT_ID();

-- check dataset
DESC dataset;
SELECT * FROM dataset order by 1 desc;
SELECT * FROM dataset WHERE active = 'Y';
INSERT INTO dataset (cob_date,feed_id,status,active,creation_time,last_update_time) VALUES (20210930,1,'F','Y',current_timestamp,current_timestamp);
DELETE FROM dataset WHERE id = 3;
UPDATE dataset SET active = 'N', last_update_time = current_timestamp WHERE cob_date = 20210930 AND feed_id = 1;
UPDATE dataset SET status = 'r', last_update_time = current_timestamp WHERE id = 18;
SELECT * FROM dataset WHERE ID = 18 AND cob_date = 20210930 AND feed_id = 1 AND active = 'Y';
SELECT f.name FROM dataset d, feed f WHERE d.id = 43 AND d.feed_id = f.id;

-- check infotype
select * from infotype;

-- check trade
-- trade_type: market, credit, others
-- action: buy, sell
desc trade;
select * from trade;
select * from trade order by 1 desc;
INSERT INTO db01.trade (uid,trade_type,action,dataset_id,system_id,account_uid,instrument_uid) VALUES (1000,'others','buy',36,1,null,null);

load data infile "D:\\tmp\\jh\\output\\20210930_totoro01_trade.csv" into table `db01`.`trade` 
fields terminated by ';' 
lines terminated by '\n'
ignore 1 lines
(`uid`,`action`,`trade_type`,`dataset_id`,`system_id`,`account_uid`,`instrument_uid`)
;

-- check risk
select * from risk;
select * from risk order by 1 desc;
load data infile "D:\\tmp\\jh\\output\\20210930_totoro01_risk.csv" into table `db01`.`risk` 
fields terminated by ';' 
lines terminated by '\n'
ignore 1 lines
(`trade_uid`,`infotype_id`,`amount`)
;

show global variables like "%datadir%";
SHOW VARIABLES LIKE "secure_file_priv";

load data infile "D:\\tmp\\jh\\output\\20210930_totoro01_trade.csv" into table `db01`.`trade` 
fields terminated by ';' 
lines terminated by '\n'
ignore 1 lines
(`uid`,`action`,`trade_type`,`dataset_id`,`system_id`,`account_uid`,`instrument_uid`)
;

# JingHang

An ETL Operation System

Note: this application is developed and tested/run on Windows OS. (with minor changes, can be compatible to Linux server as well.)

## Environment Setup

- install IDE, recommend IntelliJ IDEA
- install Java 8
- install Maven 3

## Database Setup

**1.install MySQL 8**

add config to my.ini

~~~
# allow read file
secure-file-priv =
~~~

**2.install MySQL Workbench**

create a schema: `db01`

apply below scripts:

~~~
db/TABLE_CREATION.sql
db/RECORDS_CREATION.sql
~~~

## Zookeeper Setup

- download package and unzip
- change `\conf\zoo.cfg`

~~~
dataDir=F:\\apache-zookeeper-3.7.0-bin\\data
dataqLogDir=F:\\apache-zookeeper-3.7.0-bin\\logs
~~~

- change start.cmd, the folder location in the beginning
- start server `\bin\zkServer.cmd` (it is also included in startup steps later)
- start client `\bin\zkCli.cmd`

## Geode Setup

Install: https://geode.apache.org/docs/guide/114/prereq_and_install.html

- download package and unzip
- setup path and classpath

Setup: change `start.cmd`, the folder location in the beginning

- `set geode_folder="xxx"`

## Folder Setup

- create `F:\CODEBASE\tmp\jh\download`
- create `F:\CODEBASE\tmp\jh\input`
- create `F:\CODEBASE\tmp\jh\output`

## Build

1. pull the code from git.
2. run `mvn package` with cmd or IDE plugin

if successful, you should see several packages generated like below:

~~~
ftp/target/ftp-1.0-SNAPSHOT.jar
receiver/target/receiver-1.0-SNAPSHOT.jar
parser/target/parser-1.0-SNAPSHOT.jar
loader/target/loader-1.0-SNAPSHOT.jar
~~~

## Deployment

For simplicity, we are directly using the jars in each target folder.

## Startup

**Method 1**: run from IDE Maven plugins

- run msg/MsgApplication (for embedded msg server)
- run receiver/ReceiverApplication
- run parser/ParserApplication
- run loader/LoaderApplication
- run ftp/FtpApplication

**Method 2**: run from cmd

cd to scripts folder, modify [parent_folder] in start.cmd

- `dostart.py -m all`
  - `dostart.py -m msg`
  - `dostart.py -m zkserver`
  - `dostart.py -m receiver`
  - `dostart.py -m parser`
  - `dostart.py -m loader`
  - `dostart.py -m geode_start`
  - `dostart.py -m geode_cache`
- `dostart.py -m ftp`

## FTP Cmd

start default ftp job
> dostart.py -m ftp

start ftp job with a specific `cob_date` and `feed_id`
> dostart.py -m ftp -d 20211002 -f 1
> 
> dostart.py -m ftp -d 20211002 -f 2
> 
> dostart.py -m ftp -d 20211002 -f 3

## Parser Cmd

start parer instance, instance id will be auto generated
> dostart.py -m parser

start a specific parer instance
> dostart.py -m parser --id 1

## Geode Cmd

start cache locator and server (it's included in dostart all)

> dostart.py -m geode_start

insert some records (it's included in dostart all)
> dostart.py -m geode_cache

stop all (**MAKE SURE TO STOP EVERYTIME**)
> dostart.py -m geode_stop

## TODO

- the parser seems only works in IDE, not by start script, need to check
- the cache seems needs to be manually started
- fix the log location when starting with dostart.py script, currently it's not generating in target location
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

## Folder Setup

- create `D:\tmp\jh\download`
- create `D:\tmp\jh\input`
- create `D:\tmp\jh\output`

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
  - `dostart.py -m receiver`
  - `dostart.py -m parser`
  - `dostart.py -m loader`
- `dostart.py -m ftp`

## FTP Cmd

start default ftp job
> dostart.py -m ftp

start ftp job with a specific `cob_date` and `feed_id`
> dostart.py -m ftp -d 20211002 -f 2
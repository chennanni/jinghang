@echo off
echo "*** start - calling start.cmd script ***"

Rem change folder path to where the project is located at
set jh_folder="F:\CODEBASE"
set zk_folder="F:\apache-zookeeper-3.7.0-bin"
set geode_folder="F:\apache-geode-1.14.0"

if %1==msg (
echo start msg
java -jar %jh_folder%\jinghang\msg\target\msg-1.0-SNAPSHOT.jar
)

if %1==ftp (
echo start ftp
java -jar %jh_folder%\jinghang\ftp\target\ftp-1.0-SNAPSHOT.jar %2 %3 %4 %5
)

if %1==receiver (
echo start receiver
java -jar %jh_folder%\jinghang\receiver\target\receiver-1.0-SNAPSHOT.jar
)

if %1==dispatcher (
echo start dispatcher
java -jar %jh_folder%\jinghang\dispatcher\target\dispatcher-1.0-SNAPSHOT.jar
)

if %1==parser (
echo start parser
java -jar %jh_folder%\jinghang\parser\target\parser-1.0-SNAPSHOT.jar %2 %3 %4 %5
)

if %1==loader (
echo start loader
java -jar %jh_folder%\jinghang\loader\target\loader-1.0-SNAPSHOT.jar
)

if %1==zkserver (
echo start zookeeper server
%zk_folder%\bin\zkServer.cmd
)

if %1==zkclient (
echo start zookeeper client
%zk_folder%\bin\zkCli.cmd
)

if %1==geode_start (
echo start geode
%geode_folder%\bin\gfsh run --file=start.gfsh
)

if %1==geode_stop (
echo stop geode
%geode_folder%\bin\gfsh run --file=stop.gfsh
)

if %1==cache (
echo start cache
java -jar %jh_folder%\jinghang\cache\target\cache-1.0-SNAPSHOT.jar
)

echo "*** end - calling start.cmd script***"
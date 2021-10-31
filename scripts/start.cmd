@echo off
echo "*** start - calling start.cmd script ***"

Rem change folder path to where the project is located at
set parent_folder="D:\codebase"

if %1==msg (
echo start msg
java -jar %parent_folder%\jinghang\msg\target\msg-1.0-SNAPSHOT.jar
)

if %1==ftp (
echo start ftp
java -jar %parent_folder%\jinghang\ftp\target\ftp-1.0-SNAPSHOT.jar %2 %3 %4 %5
)

if %1==receiver (
echo start receiver
java -jar %parent_folder%\jinghang\receiver\target\receiver-1.0-SNAPSHOT.jar
)

if %1==parser (
echo start parser
java -jar %parent_folder%\jinghang\parser\target\parser-1.0-SNAPSHOT.jar
)

if %1==loader (
echo start loader
java -jar %parent_folder%\jinghang\loader\target\loader-1.0-SNAPSHOT.jar
)

echo "*** end - calling start.cmd script***"
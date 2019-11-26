#!/bin/bash
export JAVA_HOME=/opt/jdk/jdk1.8.0_141
export CATALINA_HOME=/opt/tomcat/apache-tomcat-8.5.43
export CATALINA_BASE="`pwd`"
export JAVA_OPTS="-Xms100m -Xmx200m"

case $1 in
     start)
     $CATALINA_HOME/bin/catalina.sh start
     echo "start sucess!!!"
     ;;
     stop)
     $CATALINA_HOME/bin/catalina.sh stop
     echo "stop sucess!!!"
     ;;
     restart)
     $CATALINA_HOME/bin/catalina.sh stop
     echo "stop sucess!!!"
     sleep 2
     $CATALINA_HOME/bin/catalina.sh start
     echo "start sucess!!!"
     ;;
     esac
exit 0



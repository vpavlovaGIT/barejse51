FROM java:8
MAINTAINER vpavlova@t1-consulting.ru
ADD ./tm-client/target/task-manager-client-1.30.0.jar /opt/tm/
ENTRYPOINT exec usr/bin/java -jar /opt/tm/task-manager-client-1.30.0.jar
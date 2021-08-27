FROM java:8
MAINTAINER vpavlova@t1-consulting.ru
ADD ./tm-server/target/task-manager-server-1.35.0.jar /opt/tm/
EXPOSE 8080
EXPOSE 5701
EXPOSE 61616
ENTRYPOINT exec usr/bin/java -jar /opt/tm/task-manager-server-1.35.0.jar
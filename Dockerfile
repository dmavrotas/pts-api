FROM tomcat:9-jre11-slim

ARG pts_api_version=0.0.1-SNAPSHOT

RUN rm -rf ./webapps/*

# copy the WAR bundle to tomcat
COPY /build/libs/pts-api-${pts_api_version}.war ./webapps/ROOT.war

CMD ["catalina.sh", "run"]
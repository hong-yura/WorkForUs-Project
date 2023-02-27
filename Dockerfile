FROM tomcat:9.0.64-jdk17-temurin

RUN rm -Rf /usr/local/tomcat/webapps/ROOT

COPY ./workforus/target/ROOT.war /usr/local/tomcat/webapps
# 현재 디렉토리에서 forus.war 옮기기

COPY ./wallet /usr/local/wallet

EXPOSE 8080
EXPOSE 1521
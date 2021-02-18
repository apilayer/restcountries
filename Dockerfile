FROM maven:3-jdk-11
WORKDIR /app
RUN apt-get update && \
    apt-get install -y git
RUN git clone https://github.com/apilayer/restcountries .
RUN mvn -q package

FROM jboss/wildfly
WORKDIR /app
COPY --from=0 /app/target/restcountries-*.war /opt/jboss/wildfly/standalone/deployments/restcountries.war

EXPOSE 8080

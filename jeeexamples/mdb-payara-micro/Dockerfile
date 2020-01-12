FROM adoptopenjdk/openjdk11:alpine-slim

WORKDIR /opt/payara
ENV INSTALL_DIR /opt/payara
ENV PAYARA_ARCHIVE payara-micro.jar
ENV MQ_ARCHIVE activemq-rar-5.15.9.rar

RUN mkdir -p $INSTALL_DIR/deployments && \
 mkdir -p $INSTALL_DIR/microdomain && \
 mkdir -p $INSTALL_DIR/asadmin && \
 mkdir -p $INSTALL_DIR/jdbc && \
 apk update && \
 apk add curl

ENV PAYARA_PKG "https://repo1.maven.org/maven2/fish/payara/extras/payara-micro/5.194/payara-micro-5.194.jar"
ENV MQ_PKG "https://repo1.maven.org/maven2/org/apache/activemq/activemq-rar/5.15.9/activemq-rar-5.15.9.rar"

#download payara-micro to folder
RUN curl -o $INSTALL_DIR/$PAYARA_ARCHIVE $PAYARA_PKG
RUN curl -o $INSTALL_DIR/$MQ_ARCHIVE $MQ_PKG

ENV PAYARA_HOME ${INSTALL_DIR}
ENV DEPLOYMENT_DIR ${PAYARA_HOME}

ENV DEPLOY_DIR $INSTALL_DIR/deployments

# war to directory
COPY ./target/ROOT.war $DEPLOY_DIR

COPY postdeploy.txt $DEPLOY_DIR

EXPOSE 8080

# Use tini as PID1 to avoid p
RUN apk add --no-cache tini
# Tini is now available at /sbin/tini
ENTRYPOINT ["/sbin/tini", "--"]

CMD ["java", "-jar", "payara-micro.jar", "--nocluster", "--deploy", "activemq-rar-5.15.9.rar", "--postdeploycommandfile", \
   "./deployments/postdeploy.txt"]

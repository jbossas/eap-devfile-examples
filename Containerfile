ARG BUILDER_IMAGE_VERSION=1.0.0.GA-8
ARG RUNTIME_IMAGE_VERSION=1.0.0.GA-5

FROM registry.redhat.io/jboss-eap-8/eap8-openjdk17-builder-openshift-rhel8:$BUILDER_IMAGE_VERSION AS builder

ENV MVN_ARGS_APPEND="-s /home/jboss/.m2/settings.xml -Dmaven.repo.local=/home/jboss/.m2/repository -Dcom.redhat.xpaas.repo.jbossorg"
ENV JAVA_OPTS="-Djava.security.egd=file:/dev/urandom"
ENV S2I_DESTINATION_DIR=/build

WORKDIR /build
RUN mkdir src
COPY --chown=jboss:root pom.xml .
COPY --chown=jboss:root src src
RUN mvn $MVN_ARGS_APPEND -Popenshift -Dmaven.test.skip=true -Dmaven.wagon.http.ssl.insecure=true clean package

FROM registry.redhat.io/jboss-eap-8/eap8-openjdk17-runtime-openshift-rhel8:$RUNTIME_IMAGE_VERSION AS runtime

COPY --chown=jboss:root --from=builder  /build/target/server $JBOSS_HOME
RUN chmod -R ug+rwX $JBOSS_HOME 
EXPOSE 8080
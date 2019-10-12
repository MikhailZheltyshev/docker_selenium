FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

WORKDIR /test_executables
COPY target/tests_and_dependencies *-tests.xml healthcheck.sh ./

ENTRYPOINT sh healthcheck.sh
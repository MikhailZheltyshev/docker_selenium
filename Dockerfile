FROM openjdk:8u191-jre-alpine3.8

WORKDIR /test_executables
COPY target/tests_and_dependencies *-tests.xml ./

ENTRYPOINT [ "java", "cp", "selenium-docker.jar:selenium-docker-tests.jar:libs/*", "-DBROWSER=$BROWSER", "-DHUB_HOST=$HUB_HOST", "org.testng.TestNG", "$MODULE"]
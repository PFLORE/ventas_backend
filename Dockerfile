FROM openjdk:8-jdk-alpine

EXPOSE 8080

ADD ./target/ws-venta.jar ws-venta.jar

ENTRYPOINT ["java","-jar","/ws-venta.jar"]
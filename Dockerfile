FROM openjdk:11.0-jre

WORKDIR /tmp

COPY target/booking-0.0.1-SNAPSHOT.jar /tmp/booking-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/tmp/booking-0.0.1-SNAPSHOT.jar"]

EXPOSE 9094 
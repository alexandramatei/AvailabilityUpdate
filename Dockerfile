FROM amazoncorretto:11

COPY build/libs/AvailabilityUpdate-0.0.1-SNAPSHOT.jar .

EXPOSE 8087

CMD ["java", "-jar", "/AvailabilityUpdate-0.0.1-SNAPSHOT.jar" ]


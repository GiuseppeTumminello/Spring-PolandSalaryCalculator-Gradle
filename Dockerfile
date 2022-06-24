FROM gradle:jdk11-alpine AS build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD pom.xml $HOME
ADD . $HOME
RUN gradle build


FROM openjdk:11-jre-slim
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle
WORKDIR application
COPY --from=build/lib /usr/app/build/lib/Spring-PolandSalaryCalculator-Gradle-0.0.1-SNAPSHOT-plain.jar ./
ENTRYPOINT ["java", "-jar", "Spring-PolandSalaryCalculator-Gradle-0.0.1-SNAPSHOT-plain.jar"]
CMD java $JAVA_OPTIONS -jar Spring-PolandSalaryCalculator-Gradle-0.0.1-SNAPSHOT-plain.jar


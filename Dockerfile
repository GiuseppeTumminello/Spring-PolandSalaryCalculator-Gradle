FROM tomcat:10-jdk11
WORKDIR /
ADD ./build/libs/Spring-PolandSalaryCalculator-Gradle-0.0.1-SNAPSHOT.jar ./
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Spring-PolandSalaryCalculator-Gradle-0.0.1-SNAPSHOT.jar"]
CMD java $JAVA_OPTIONS -jar Spring-PolandSalaryCalculator-Gradle-0.0.1-SNAPSHOT.jar
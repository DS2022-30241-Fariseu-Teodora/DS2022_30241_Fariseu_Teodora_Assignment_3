FROM maven:latest AS builder

COPY ./src/ /root/DS2022_30241_Fariseu_Teodora/src
COPY ./pom.xml /root/DS2022_30241_Fariseu_Teodora

WORKDIR /root/DS2022_30241_Fariseu_Teodora
RUN mvn package -DskipTests
RUN java -Djarmode=layertools -jar /root/DS2022_30241_Fariseu_Teodora/target/DS2022_30241_Fariseu_Teodora-0.0.1-SNAPSHOT.jar list
RUN java -Djarmode=layertools -jar /root/DS2022_30241_Fariseu_Teodora/target/DS2022_30241_Fariseu_Teodora-0.0.1-SNAPSHOT.jar extract
RUN ls -l /root

FROM openjdk

ENV TZ=UTC
ENV DB_IP=localhost
ENV DB_PORT=5432
ENV DB_USER=postgres
ENV DB_PASSWORD=root
ENV DB_DBNAME=energy-db

ENV RMQ_IP=localhost
ENV RMQ_PORT=5672
ENV RMQ_USER=guest
ENV RMQ_PASSWORD=guest

COPY --from=builder /root/DS2022_30241_Fariseu_Teodora/dependencies/ ./
COPY --from=builder /root/DS2022_30241_Fariseu_Teodora/snapshot-dependencies/ ./

RUN sleep 10
COPY --from=builder /root/DS2022_30241_Fariseu_Teodora/spring-boot-loader/ ./
COPY --from=builder /root/DS2022_30241_Fariseu_Teodora/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher","-XX:+UseContainerSupport -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:MaxRAMFraction=1 -Xms512m -Xmx512m -XX:+UseG1GC -XX:+UseSerialGC -Xss512k -XX:MaxRAM=72m"]
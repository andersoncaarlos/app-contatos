# Stage 1: Build Stage
FROM openjdk:21-jdk-slim as build

WORKDIR /workspace/app

# Copiando arquivos do Maven
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# Concedendo permissões ao Maven wrapper
RUN chmod +x ./mvnw

# Executando o build com Maven
RUN ./mvnw install -DskipTests

# Criando diretório para as dependências e extraindo o JAR
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Stage 2: Runtime Stage
FROM openjdk:21-jdk-slim

# Volume temporário para o container
VOLUME /tmp

# Diretório de dependências
ARG DEPENDENCY=/workspace/app/target/dependency

# Copiando dependências do build stage
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Ponto de entrada da aplicação
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "br.com.AppContatos.AppContatosApplication"]

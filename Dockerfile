# Используем официальный образ OpenJDK для Java 22
FROM openjdk:22-jdk-slim

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем JAR файл из целевой директории вашего Spring Boot приложения в контейнер
COPY target/demo-0.0.1-SNAPSHOT.jar /app/app.jar

# Определяем команду для запуска приложения в контейнере
CMD ["java", "-jar", "app.jar"]

# EXPOSE не обязательно, поскольку Spring Boot автоматически обрабатывает порты

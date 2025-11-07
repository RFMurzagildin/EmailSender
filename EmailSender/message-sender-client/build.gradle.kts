plugins {
    java
    id("org.springframework.boot") version "3.5.7"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "ru.webbyskysender"
version = "0.0.1-SNAPSHOT"
description = "message-sender-client"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2025.0.0"

dependencies {
    //Spring Web
    implementation("org.springframework.boot:spring-boot-starter-web")

    //Eureka Client
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

    //Thymeleaf
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    //Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    //Kafka
    implementation("org.springframework.kafka:spring-kafka")

    //Mail Sender
    implementation("org.springframework.boot:spring-boot-starter-mail")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

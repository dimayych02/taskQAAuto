plugins {
    id("java")
    id("io.qameta.allure") version "2.11.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.testng:testng:7.3.0")
    implementation("org.seleniumhq.selenium:selenium-java:4.8.3")
    implementation("io.github.bonigarcia:webdrivermanager:5.3.0")
    testImplementation("org.aspectj:aspectjweaver:1.9.8")
}

tasks.test {
    useTestNG()
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
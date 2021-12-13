import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.0"
	kotlin("plugin.spring") version "1.6.0"
}

group = "com.ex"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	// https://mvnrepository.com/artifact/ca.uhn.hapi.fhir/hapi-fhir-base
	implementation("ca.uhn.hapi.fhir:hapi-fhir-base:5.6.0")
	// https://mvnrepository.com/artifact/ca.uhn.hapi.fhir/hapi-fhir-structures-r5
	implementation("ca.uhn.hapi.fhir:hapi-fhir-structures-dstu3:5.6.0")
	// https://mvnrepository.com/artifact/ca.uhn.hapi.fhir/hapi-fhir-client
	implementation("ca.uhn.hapi.fhir:hapi-fhir-client:5.6.0")

//	 https://mvnrepository.com/artifact/ca.uhn.hapi-fhir-client.fhir/org.hl7.fhir.dstu3
	// https://mvnrepository.com/artifact/ca.uhn.hapi.fhir/hapi-fhir-client




	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

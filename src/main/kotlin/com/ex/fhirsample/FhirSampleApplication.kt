package com.ex.fhirsample

import ca.uhn.fhir.context.FhirContext
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class FhirSampleApplication {

	@Bean
	fun fhirContext(): FhirContext? {
		return FhirContext.forDstu3()
	}
}

fun main(args: Array<String>) {
	runApplication<FhirSampleApplication>(*args)
}

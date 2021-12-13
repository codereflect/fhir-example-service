package com.ex.fhirsample

import ca.uhn.fhir.context.FhirContext
import com.ex.fhirsample.converters.PatientConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter


@Configuration
class AppConfig {

    @Autowired
    lateinit var fhirContext: FhirContext

//    @Value("serverbase")
    var serverBase = "http://hapi.fhir.org/baseDstu3"

    @Bean
    fun customConverters(): HttpMessageConverters? {
        val converters: MutableList<HttpMessageConverter<*>> = ArrayList()
        converters.add(PatientConverter(fhirContext))
        return HttpMessageConverters(true, converters)
    }
}
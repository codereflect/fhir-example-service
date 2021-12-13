package com.ex.fhirsample.converters

import ca.uhn.fhir.context.FhirContext
import ca.uhn.fhir.parser.IParser
import ca.uhn.fhir.rest.api.EncodingEnum
import org.springframework.http.MediaType
import org.springframework.http.converter.AbstractHttpMessageConverter
import java.nio.charset.Charset


abstract class FhirJsonHttpMessageConverter<T> protected constructor(private val fhirContext: FhirContext) :
    AbstractHttpMessageConverter<T>(mediaType) {

    protected val parser: IParser
        get() = EncodingEnum.JSON.newParser(fhirContext)

    companion object {
        @JvmStatic
        protected val mediaType: MediaType = MediaType("application", "json", Charset.forName("UTF-8"))
    }
}
package com.ex.fhirsample.converters

import ca.uhn.fhir.context.FhirContext
import org.hl7.fhir.dstu3.model.OperationOutcome
import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.http.converter.HttpMessageNotWritableException
import java.io.IOException


class OperationOutcomeConverter(fhirContext: FhirContext?) :
    FhirJsonHttpMessageConverter<OperationOutcome?>(fhirContext!!) {

    override fun supports(clazz: Class<*>): Boolean {
        return OperationOutcome::class.java.isAssignableFrom(clazz)
    }

    override fun getDefaultContentType(oo: OperationOutcome): MediaType? {
        return mediaType
    }

    @Throws(IOException::class, HttpMessageNotReadableException::class)
    override fun readInternal(clazz: Class<out OperationOutcome?>, inputMessage: HttpInputMessage): OperationOutcome {
        throw HttpMessageNotReadableException("Could not read op outcome json message.", inputMessage)
    }

    @Throws(IOException::class, HttpMessageNotWritableException::class)
    override fun writeInternal(t: OperationOutcome, outputMessage: HttpOutputMessage) {
        val outputStream = outputMessage.body
        val body = parser.encodeResourceToString(t)
        outputStream.write(body.toByteArray())
        outputStream.close()
    }


}
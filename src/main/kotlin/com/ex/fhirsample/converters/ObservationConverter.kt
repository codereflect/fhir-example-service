package com.ex.fhirsample.converters

import ca.uhn.fhir.context.FhirContext
import org.hl7.fhir.dstu3.model.Observation
import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.http.converter.HttpMessageNotWritableException
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader


class ObservationConverter(fhirContext: FhirContext?) : FhirJsonHttpMessageConverter<Observation?>(
    fhirContext!!
) {
    override fun supports(clazz: Class<*>): Boolean {
        return Observation::class.java.isAssignableFrom(clazz)
    }

    override fun getDefaultContentType(obs: Observation): MediaType? {
        return mediaType
    }

    @Throws(IOException::class, HttpMessageNotReadableException::class)
    override fun readInternal(clazz: Class<out Observation>, inputMessage: HttpInputMessage): Observation {
        val obs: Observation
        try {
            val reader: Reader = InputStreamReader(inputMessage.body)
            obs = parser.parseResource(Observation::class.java, reader)
        } catch (ex: Exception) {
            throw HttpMessageNotReadableException("Could not read obs json message: " + ex.message, ex)
        }
        return obs
    }

    @Throws(IOException::class, HttpMessageNotWritableException::class)
    override fun writeInternal(obs: Observation, outputMessage: HttpOutputMessage) {
        val outputStream = outputMessage.body
        val body = parser.encodeResourceToString(obs)
        outputStream.write(body.toByteArray())
        outputStream.close()
    }


}
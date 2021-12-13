package com.ex.fhirsample.converters

import ca.uhn.fhir.context.FhirContext
import org.hl7.fhir.dstu3.model.Patient
import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.http.converter.HttpMessageNotWritableException
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader


class PatientConverter(fhirContext: FhirContext?) : FhirJsonHttpMessageConverter<Patient?>(fhirContext!!) {
    override fun supports(clazz: Class<*>): Boolean {
        return Patient::class.java.isAssignableFrom(clazz)
    }

    override fun getDefaultContentType(patient: Patient): MediaType? {
        return mediaType
    }

    @Throws(IOException::class, HttpMessageNotReadableException::class)
    override fun readInternal(clazz: Class<out Patient>, inputMessage: HttpInputMessage): Patient {
        val patient: Patient
        try {
            val reader: Reader = InputStreamReader(inputMessage.body)
            patient = parser.parseResource(Patient::class.java, reader)
        } catch (ex: Exception) {
            throw HttpMessageNotReadableException("Could not read patient json message: " + ex.message, inputMessage)
        }
        return patient
    }

    @Throws(IOException::class, HttpMessageNotWritableException::class)
    override fun writeInternal(patient: Patient, outputMessage: HttpOutputMessage) {
        val outputStream = outputMessage.body
        val body = parser.encodeResourceToString(patient)
        outputStream.write(body.toByteArray())
        outputStream.close()
    }


}
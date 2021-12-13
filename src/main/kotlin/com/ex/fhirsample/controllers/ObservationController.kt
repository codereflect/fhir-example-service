package com.ex.fhirsample.controllers

import ca.uhn.fhir.context.FhirContext
import ca.uhn.fhir.rest.api.MethodOutcome
import ca.uhn.fhir.rest.client.api.IGenericClient
import org.hl7.fhir.dstu3.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/fhir/dstu3/Observation")
class ObservationRestController {
    @Autowired
    lateinit var ctx: FhirContext



    @PostMapping
    fun create(@RequestBody observation: Observation): Observation {

        val client: IGenericClient = ctx.newRestfulGenericClient("http://hapi.fhir.org/baseDstu3")

        val outcome: MethodOutcome = client.create()
            .resource(observation)
            .prettyPrint()
            .encodedJson()
            .execute()

        val id = outcome.id
        println("Got ID: " + id.idPart)

        return outcome.resource as Observation
    }

    @GetMapping(path = ["/{id}"], produces = ["application/*"])
    fun find(@PathVariable("id") id: String): Observation? {
        val client: IGenericClient = ctx.newRestfulGenericClient("http://hapi.fhir.org/baseDstu3")

        val observation: Observation = client.read()
            .resource(Observation::class.java)
            .withId(id)
            .execute()

        return observation

    }


}
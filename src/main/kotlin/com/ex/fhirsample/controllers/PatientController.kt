package com.ex.fhirsample.controllers

import ca.uhn.fhir.context.FhirContext
import ca.uhn.fhir.context.FhirVersionEnum
import ca.uhn.fhir.rest.api.MethodOutcome
import ca.uhn.fhir.rest.client.api.IGenericClient
import org.hl7.fhir.dstu3.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/fhir/dstu3/Patient")
class PatientController {

    @Autowired
    lateinit var ctx: FhirContext

    @PostMapping
    fun create(@RequestBody patient: Patient): Patient {

        val client: IGenericClient = ctx.newRestfulGenericClient("http://hapi.fhir.org/baseDstu3")

        //store patient to test server

        val outcome: MethodOutcome = client.create()
            .resource(patient)
            .prettyPrint()
            .encodedJson()
            .execute()

        val id = outcome.id
        println("Got ID: " + id.idPart)


        return outcome.resource as Patient
    }

    @GetMapping(path = ["/{id}"], produces = ["application/*"])
    fun find(@PathVariable("id") id: String): Patient? {
        val client: IGenericClient = ctx.newRestfulGenericClient("http://hapi.fhir.org/baseDstu3")

        val patient: Patient = client.read()
            .resource(Patient::class.java)
            .withId(id)
            .execute()

        return patient

    }

    //        return OperationOutcome().addIssue(
//            OperationOutcome.OperationOutcomeIssueComponent()
//                .setDetails(CodeableConcept().setText("ALL OK"))
//                .setCode(OperationOutcome.IssueType.INFORMATIONAL)
//        ).setMeta(Meta().lastUpdated()).

}
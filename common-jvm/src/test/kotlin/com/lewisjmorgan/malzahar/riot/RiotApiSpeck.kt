package com.lewisjmorgan.malzahar.riot

import com.lewisjmorgan.malzahar.riot.test.fromTestApi
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class RiotApiSpeck : Spek({
  describe("Riot Summoner API") {
    val api by memoized { RiotApi.fromTestApi() }

    on("a request") {
      val path = "/static-data/v3/tarball-links"
      it("should return an OK response") {
        api.request(path, listOf())
            .test()
            .assertComplete()
            .assertValue { (response, _) -> response.responseMessage == "OK" }
      }
      it("should return a message body for a 200 request") {
        api.request(path, listOf())
            .test()
            .assertComplete()
            .assertValue { (_, message) -> !message.isEmpty() }
      }
      it("should throw a RiotApiException result for an empty request") {
        api.request("", listOf())
            .test()
            .assertError(RiotApiException::class.java)
            .assertNotComplete()
      }
    }
    on("getting a json response string") {
      it("should return a response message") {
        api.getJsonResponseString("/static-data/v3/tarball-links")
            .test()
            .assertComplete()
            .assertValue { message -> !message.isEmpty() }
      }
    }
  }
})
